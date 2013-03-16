/*
 * Copyright 2005 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.core.rule;

import org.drools.core.base.ValueType;
import org.drools.core.base.evaluators.AfterEvaluatorDefinition.AfterEvaluator;
import org.drools.core.base.evaluators.BeforeEvaluatorDefinition.BeforeEvaluator;
import org.drools.core.base.evaluators.MeetsEvaluatorDefinition.MeetsEvaluator;
import org.drools.core.base.evaluators.MetByEvaluatorDefinition.MetByEvaluator;
import org.drools.core.common.EventFactHandle;
import org.drools.core.common.InternalFactHandle;
import org.drools.core.common.InternalWorkingMemory;
import org.drools.core.reteoo.LeftTuple;
import org.drools.core.spi.Evaluator;
import org.drools.core.spi.InternalReadAccessor;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class VariableRestriction {

    private VariableRestriction() { }

    public static VariableContextEntry createContextEntry(InternalReadAccessor fieldExtractor,
                                                          Declaration declaration,
                                                          Evaluator evaluator) {
        ValueType coerced = evaluator.getCoercedValueType();
        
        if ( evaluator.isTemporal() ) {
            if ( evaluator instanceof BeforeEvaluator || evaluator instanceof MeetsEvaluator) {
                return new LeftStartRightEndContextEntry( fieldExtractor,
                                                         declaration, 
                                                         evaluator );
            }
            
            if ( evaluator instanceof AfterEvaluator || evaluator instanceof MetByEvaluator  ) {
                return new LeftEndRightStartContextEntry( fieldExtractor,
                                                         declaration, 
                                                         evaluator );
            }        

            // else
            return new TemporalVariableContextEntry( fieldExtractor,
                                                     declaration,
                                                     evaluator );          
        }
        


        if ( coerced.isBoolean() ) {
            return new BooleanVariableContextEntry( fieldExtractor,
                                                    declaration,
                                                    evaluator );
        } else if ( coerced.isFloatNumber() ) {
            return new DoubleVariableContextEntry( fieldExtractor,
                                                   declaration,
                                                   evaluator );
        } else if ( coerced.isIntegerNumber() || coerced.isEvent() ) {
            return new LongVariableContextEntry( fieldExtractor,
                                                 declaration,
                                                 evaluator );
        } else if ( coerced.isChar() ) {
            return new CharVariableContextEntry( fieldExtractor,
                                                 declaration,
                                                 evaluator );
        } else {
            return new ObjectVariableContextEntry( fieldExtractor,
                                                   declaration,
                                                   evaluator );
        }
    }

    public static abstract class VariableContextEntry
        implements
        ContextEntry {
        public InternalReadAccessor  extractor;
        public Evaluator             evaluator;
        public Object                object;
        public Declaration           declaration;
        public LeftTuple             reteTuple;
        public ContextEntry          entry;
        public boolean               leftNull;
        public boolean               rightNull;
        public InternalWorkingMemory workingMemory;

        public VariableContextEntry() {
        }

        public VariableContextEntry(final InternalReadAccessor extractor,
                                    final Declaration declaration,
                                    final Evaluator evaluator) {
            this.extractor = extractor;
            this.declaration = declaration;
            this.evaluator = evaluator;
        }

        public void readExternal(ObjectInput in) throws IOException,
                                                ClassNotFoundException {
            workingMemory = (InternalWorkingMemory) in.readObject();
            extractor = (InternalReadAccessor) in.readObject();
            evaluator = (Evaluator) in.readObject();
            object = in.readObject();
            declaration = (Declaration) in.readObject();
            reteTuple = (LeftTuple) in.readObject();
            entry = (ContextEntry) in.readObject();
            leftNull = in.readBoolean();
            rightNull = in.readBoolean();
        }

        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject( workingMemory );
            out.writeObject( extractor );
            out.writeObject( evaluator );
            out.writeObject( object );
            out.writeObject( declaration );
            out.writeObject( reteTuple );
            out.writeObject( entry );
            out.writeBoolean( leftNull );
            out.writeBoolean( rightNull );
        }

        public ContextEntry getNext() {
            return this.entry;
        }

        public void setNext(final ContextEntry entry) {
            this.entry = entry;
        }

        public InternalReadAccessor getFieldExtractor() {
            return this.extractor;
        }

        public Object getObject() {
            return this.object;
        }

        public LeftTuple getTuple() {
            return this.reteTuple;
        }

        public Declaration getVariableDeclaration() {
            return this.declaration;
        }

        public boolean isLeftNull() {
            return this.leftNull;
        }

        public boolean isRightNull() {
            return this.rightNull;
        }

        public void resetTuple() {
            this.reteTuple = null;
        }

        public void resetFactHandle() {
            this.object = null;
        }
    }

    public static class ObjectVariableContextEntry extends VariableContextEntry {

        private static final long serialVersionUID = 510l;
        public Object             left;
        public Object             right;

        public ObjectVariableContextEntry() {
        }

        public ObjectVariableContextEntry(final InternalReadAccessor extractor,
                                          final Declaration declaration,
                                          final Evaluator evaluator) {
            super( extractor,
                   declaration,
                   evaluator );
        }

        public void readExternal(ObjectInput in) throws IOException,
                                                ClassNotFoundException {
            super.readExternal( in );
            left = in.readObject();
            right = in.readObject();
        }

        public void writeExternal(ObjectOutput out) throws IOException {
            super.writeExternal( out );
            out.writeObject( left );
            out.writeObject( right );
        }

        public void updateFromTuple(final InternalWorkingMemory workingMemory,
                                    final LeftTuple tuple) {
            this.reteTuple = tuple;
            this.workingMemory = workingMemory;
            this.leftNull = this.declaration.getExtractor().isNullValue( workingMemory,
                                                                         tuple.get( this.declaration ).getObject() );
            this.left = this.declaration.getExtractor().getValue( workingMemory,
                                                                  tuple.get( this.declaration ).getObject() );
        }

        public void updateFromFactHandle(final InternalWorkingMemory workingMemory,
                                         final InternalFactHandle handle) {
            this.object = handle.getObject();
            this.workingMemory = workingMemory;
            this.rightNull = this.extractor.isNullValue( workingMemory,
                                                         handle.getObject() );
            this.right = this.extractor.getValue( workingMemory,
                                                  handle.getObject() );
        }

        public void resetTuple() {
            this.left = null;
            this.reteTuple = null;
        }

        public void resetFactHandle() {
            this.right = null;
            this.object = null;
        }
    }

    public static class PrimitiveArrayVariableContextEntry extends VariableContextEntry {

        private static final long serialVersionUID = 510l;
        public Object             left;
        public Object             right;

        public PrimitiveArrayVariableContextEntry() {
        }

        public PrimitiveArrayVariableContextEntry(final InternalReadAccessor extractor,
                                                  final Declaration declaration,
                                                  final Evaluator evaluator) {
            super( extractor,
                   declaration,
                   evaluator );
        }

        public void readExternal(ObjectInput in) throws IOException,
                                                ClassNotFoundException {
            super.readExternal( in );
            left = in.readObject();
            right = in.readObject();
        }

        public void writeExternal(ObjectOutput out) throws IOException {
            super.writeExternal( out );
            out.writeObject( left );
            out.writeObject( right );
        }

        public void updateFromTuple(final InternalWorkingMemory workingMemory,
                                    final LeftTuple tuple) {
            this.reteTuple = tuple;
            this.workingMemory = workingMemory;
            this.leftNull = this.declaration.getExtractor().isNullValue( workingMemory,
                                                                         tuple.get( this.declaration ).getObject() );
            this.left = this.declaration.getExtractor().getValue( workingMemory,
                                                                  tuple.get( this.declaration ).getObject() );
        }

        public void updateFromFactHandle(final InternalWorkingMemory workingMemory,
                                         final InternalFactHandle handle) {
            this.object = handle.getObject();
            this.workingMemory = workingMemory;
            this.rightNull = this.extractor.isNullValue( workingMemory,
                                                         handle.getObject());
            this.right = this.extractor.getValue( workingMemory,
                                                  handle.getObject() );
        }

        public void resetTuple() {
            this.left = null;
            this.reteTuple = null;
        }

        public void resetFactHandle() {
            this.right = null;
            this.object = null;
        }
    }

    public static class LongVariableContextEntry extends VariableContextEntry {

        private static final long serialVersionUID = 510l;

        public long               left;
        public long               right;

        public LongVariableContextEntry() {
        }

        public LongVariableContextEntry(final InternalReadAccessor extractor,
                                        final Declaration declaration,
                                        final Evaluator evaluator) {
            super( extractor,
                   declaration,
                   evaluator );
        }

        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            super.readExternal( in );
            left = in.readLong();
            right = in.readLong();
        }

        public void writeExternal(ObjectOutput out) throws IOException {
            super.writeExternal( out );
            out.writeLong( left );
            out.writeLong( right );
        }

        public void updateFromTuple(final InternalWorkingMemory workingMemory,
                                    final LeftTuple tuple) {
            this.reteTuple = tuple;
            this.workingMemory = workingMemory;
            this.leftNull = this.declaration.getExtractor().isNullValue( workingMemory,
                                                                         tuple.get( this.declaration ).getObject() );

            if ( !leftNull ) {
                this.left = this.declaration.getExtractor().getLongValue( workingMemory,
                                                                          tuple.get( this.declaration ).getObject() );
            } else {
                this.left = 0;
            }
        }

        public void updateFromFactHandle(final InternalWorkingMemory workingMemory,
                                         final InternalFactHandle handle) {
            this.object = handle.getObject();
            this.workingMemory = workingMemory;
            this.rightNull = this.extractor.isNullValue( workingMemory,
                                                         handle.getObject());

            if ( !rightNull ) { // avoid a NullPointerException
                this.right = this.extractor.getLongValue( workingMemory,
                                                          handle.getObject() );
            } else {
                this.right = 0;
            }
        }
    }

    public static class CharVariableContextEntry extends VariableContextEntry {

        private static final long serialVersionUID = 510l;

        public char               left;
        public char               right;

        public CharVariableContextEntry() {
        }

        public CharVariableContextEntry(final InternalReadAccessor extractor,
                                        final Declaration declaration,
                                        final Evaluator evaluator) {
            super( extractor,
                   declaration,
                   evaluator );
        }

        public void readExternal(ObjectInput in) throws IOException,
                                                ClassNotFoundException {
            super.readExternal( in );
            left = in.readChar();
            right = in.readChar();
        }

        public void writeExternal(ObjectOutput out) throws IOException {
            super.writeExternal( out );
            out.writeChar( left );
            out.writeChar( right );
        }

        public void updateFromTuple(final InternalWorkingMemory workingMemory,
                                    final LeftTuple tuple) {
            this.reteTuple = tuple;
            this.workingMemory = workingMemory;
            this.leftNull = this.declaration.getExtractor().isNullValue( workingMemory,
                                                                         tuple.get( this.declaration ).getObject());

            if ( !leftNull ) {
                this.left = this.declaration.getExtractor().getCharValue( workingMemory,
                                                                          tuple.get( this.declaration ).getObject() );
            } else {
                this.left = 0;
            }
        }

        public void updateFromFactHandle(final InternalWorkingMemory workingMemory,
                                         final InternalFactHandle handle) {
            this.object = handle.getObject();
            this.workingMemory = workingMemory;
            this.rightNull = this.extractor.isNullValue( workingMemory,
                                                         handle.getObject());

            if ( !rightNull ) { // avoid a NullPointerException
                this.right = this.extractor.getCharValue( workingMemory,
                                                          handle.getObject() );
            } else {
                this.right = 0;
            }
        }
    }

    public static class DoubleVariableContextEntry extends VariableContextEntry {

        private static final long serialVersionUID = 510l;

        public double             left;
        public double             right;

        public DoubleVariableContextEntry() {
        }

        public DoubleVariableContextEntry(final InternalReadAccessor extractor,
                                          final Declaration declaration,
                                          final Evaluator evaluator) {
            super( extractor,
                   declaration,
                   evaluator );
        }

        public void readExternal(ObjectInput in) throws IOException,
                                                ClassNotFoundException {
            super.readExternal( in );
            left = in.readDouble();
            right = in.readDouble();
        }

        public void writeExternal(ObjectOutput out) throws IOException {
            super.writeExternal( out );
            out.writeDouble( left );
            out.writeDouble( right );
        }

        public void updateFromTuple(final InternalWorkingMemory workingMemory,
                                    final LeftTuple tuple) {
            this.reteTuple = tuple;
            this.workingMemory = workingMemory;
            this.leftNull = this.declaration.getExtractor().isNullValue( workingMemory,
                                                                         tuple.get( this.declaration ).getObject() );

            if ( !leftNull ) {
                this.left = this.declaration.getExtractor().getDoubleValue( workingMemory,
                                                                            tuple.get( this.declaration ).getObject() );
            } else {
                this.left = 0;
            }
        }

        public void updateFromFactHandle(final InternalWorkingMemory workingMemory,
                                         final InternalFactHandle handle) {
            this.object = handle.getObject();
            this.workingMemory = workingMemory;
            this.rightNull = this.extractor.isNullValue( workingMemory,
                                                         handle.getObject() );

            if ( !rightNull ) { // avoid a NullPointerException
                this.right = this.extractor.getDoubleValue( workingMemory,
                                                            handle.getObject() );
            } else {
                this.right = 0;
            }
        }
    }

    public static class BooleanVariableContextEntry extends VariableContextEntry {

        private static final long serialVersionUID = 510l;
        public boolean            left;
        public boolean            right;

        public BooleanVariableContextEntry() {
        }

        public BooleanVariableContextEntry(final InternalReadAccessor extractor,
                                           final Declaration declaration,
                                           final Evaluator evaluator) {
            super( extractor,
                   declaration,
                   evaluator );
        }

        public void readExternal(ObjectInput in) throws IOException,
                                                ClassNotFoundException {
            super.readExternal( in );
            left = in.readBoolean();
            right = in.readBoolean();
        }

        public void writeExternal(ObjectOutput out) throws IOException {
            super.writeExternal( out );
            out.writeBoolean( left );
            out.writeBoolean( right );
        }

        public void updateFromTuple(final InternalWorkingMemory workingMemory,
                                    final LeftTuple tuple) {
            this.reteTuple = tuple;
            this.workingMemory = workingMemory;
            this.leftNull = this.declaration.getExtractor().isNullValue( workingMemory,
                                                                         tuple.get( this.declaration ).getObject() );

            if ( !leftNull ) {
                this.left = this.declaration.getExtractor().getBooleanValue( workingMemory,
                                                                             tuple.get( this.declaration ).getObject() );
            } else {
                this.left = false;
            }
        }

        public void updateFromFactHandle(final InternalWorkingMemory workingMemory,
                                         final InternalFactHandle handle) {
            this.object = handle.getObject();
            this.workingMemory = workingMemory;
            this.rightNull = this.extractor.isNullValue( workingMemory,
                                                         handle.getObject() );

            if ( !rightNull ) { // avoid a NullPointerException
                this.right = this.extractor.getBooleanValue( workingMemory,
                                                             handle.getObject() );
            } else {
                this.right = false;
            }
        }
    }
    
    public static class LeftStartRightEndContextEntry extends VariableContextEntry {

        private static final long serialVersionUID = 510l;

        public long               timestamp;

        public LeftStartRightEndContextEntry() {
        }

        public LeftStartRightEndContextEntry(final InternalReadAccessor extractor,
                                             final Declaration declaration,
                                             final Evaluator evaluator) {
            super( extractor,
                   declaration,
                   evaluator );
        }

        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            super.readExternal( in );
            timestamp = in.readLong();
        }

        public void writeExternal(ObjectOutput out) throws IOException {
            super.writeExternal( out );
            out.writeLong( timestamp );
        }

        public void updateFromTuple(final InternalWorkingMemory workingMemory,
                                    final LeftTuple tuple) {
            this.reteTuple = tuple;
            this.workingMemory = workingMemory;
            
            if ( this.declaration.getExtractor().isSelfReference() ) {
                this.timestamp = ((EventFactHandle)tuple.get( this.declaration )).getStartTimestamp();
            } else {
              this.leftNull = this.declaration.getExtractor().isNullValue( workingMemory,
                                                                           tuple.get( this.declaration ).getObject() );
              if ( !leftNull ) { // avoid a NullPointerException
                  this.timestamp = this.declaration.getExtractor().getLongValue( workingMemory,
                                                                            tuple.get( this.declaration ).getObject() );
                } else {
                    this.timestamp = 0;
                }                
            }
        }

        public void updateFromFactHandle(final InternalWorkingMemory workingMemory,
                                         final InternalFactHandle handle) {
            this.object = handle.getObject(); 
            this.workingMemory = workingMemory;
            
            if ( this.extractor.isSelfReference() ) {
                this.timestamp = ((EventFactHandle)handle).getEndTimestamp();
            } else {
              this.rightNull = this.extractor.isNullValue( workingMemory,
                                                           handle.getObject());
              if ( !rightNull ) { // avoid a NullPointerException
                  this.timestamp = this.extractor.getLongValue( workingMemory,
                                                            handle.getObject() );
                } else {
                    this.timestamp = 0;
                }                
            }
        }
    }
    
    public static class LeftEndRightStartContextEntry extends VariableContextEntry {

        private static final long serialVersionUID = 510l;

        public long               timestamp;

        public LeftEndRightStartContextEntry() {
        }

        public LeftEndRightStartContextEntry(final InternalReadAccessor extractor,
                                            final Declaration declaration,
                                            final Evaluator evaluator) {
            super( extractor,
                   declaration,
                   evaluator );
        }

        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            super.readExternal( in );
            timestamp = in.readLong();
        }

        public void writeExternal(ObjectOutput out) throws IOException {
            super.writeExternal( out );
            out.writeLong( timestamp );
        }

        public void updateFromTuple(final InternalWorkingMemory workingMemory,
                                    final LeftTuple tuple) {
            this.reteTuple = tuple;
            this.workingMemory = workingMemory;
            
            if ( this.declaration.getExtractor().isSelfReference() ) {
                this.timestamp = ((EventFactHandle)tuple.get( this.declaration )).getEndTimestamp();
            } else {
              this.leftNull = this.declaration.getExtractor().isNullValue( workingMemory,
                                                                           tuple.get( this.declaration ).getObject() );
              if ( !leftNull ) { // avoid a NullPointerException
                  this.timestamp = this.declaration.getExtractor().getLongValue( workingMemory,
                                                                             tuple.get( this.declaration ).getObject() );
                } else {
                    this.timestamp = 0;
                }                
            }
        }

        public void updateFromFactHandle(final InternalWorkingMemory workingMemory,
                                         final InternalFactHandle handle) {
            this.object = handle.getObject(); 
            this.workingMemory = workingMemory;
            
            if ( this.extractor.isSelfReference() ) {
                this.timestamp = ((EventFactHandle)handle).getStartTimestamp();
            } else {
              this.rightNull = this.extractor.isNullValue( workingMemory,
                                                           handle.getObject());
              if ( !rightNull ) { // avoid a NullPointerException
                  this.timestamp = this.extractor.getLongValue( workingMemory,
                                                            handle.getObject() );
                } else {
                    this.timestamp = 0;
                }                
            }
        }
    }    
    
    public static class TemporalVariableContextEntry extends VariableContextEntry {

        private static final long serialVersionUID = 510l;

        public long               startTS;
        public long               endTS;

        public TemporalVariableContextEntry() {
        }

        public TemporalVariableContextEntry(final InternalReadAccessor extractor,
                                            final Declaration declaration,
                                            final Evaluator evaluator) {
            super( extractor,
                   declaration,
                   evaluator );
        }

        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            super.readExternal( in );
            startTS = in.readLong();
            endTS = in.readLong();
        }

        public void writeExternal(ObjectOutput out) throws IOException {
            super.writeExternal( out );
            out.writeLong( startTS );
            out.writeLong( startTS );
        }

        public void updateFromTuple(final InternalWorkingMemory workingMemory,
                                    final LeftTuple tuple) {
            this.reteTuple = tuple;
            this.workingMemory = workingMemory;
            
            if ( this.declaration.getExtractor().isSelfReference() ) {
                this.startTS = ((EventFactHandle)tuple.get( this.declaration )).getStartTimestamp();
                this.endTS = ((EventFactHandle)tuple.get( this.declaration )).getEndTimestamp();
            } else {
              this.leftNull = this.declaration.getExtractor().isNullValue( workingMemory,
                                                                           tuple.get( this.declaration ).getObject() );
              if ( !leftNull ) { // avoid a NullPointerException
                  this.startTS = this.declaration.getExtractor().getLongValue( workingMemory,
                                                                             tuple.get( this.declaration ).getObject() );
                } else {
                    this.startTS = 0;
                }    
              endTS = startTS;
            }
        }

        public void updateFromFactHandle(final InternalWorkingMemory workingMemory,
                                         final InternalFactHandle handle) {
            this.object = handle.getObject(); 
            this.workingMemory = workingMemory;
            
            if ( this.extractor.isSelfReference() ) {
                this.startTS = ((EventFactHandle)handle).getStartTimestamp();
                this.endTS = ((EventFactHandle)handle).getEndTimestamp();
            } else {
              this.rightNull = this.extractor.isNullValue( workingMemory,
                                                           handle.getObject());
              if ( !rightNull ) { // avoid a NullPointerException
                  this.startTS = this.extractor.getLongValue( workingMemory,
                                                              handle.getObject() );
              } else {
                    this.startTS = 0;
              }  
              endTS = startTS;
            }
        }
    }       
}
