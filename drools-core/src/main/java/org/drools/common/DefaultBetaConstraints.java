package org.drools.common;

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

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

import org.drools.RuleBaseConfiguration;
import org.drools.base.evaluators.Operator;
import org.drools.reteoo.BetaMemory;
import org.drools.reteoo.LeftTuple;
import org.drools.reteoo.LeftTupleMemory;
import org.drools.reteoo.RightTupleMemory;
import org.drools.rule.ContextEntry;
import org.drools.rule.VariableConstraint;
import org.drools.spi.BetaNodeFieldConstraint;
import org.drools.spi.Constraint;
import org.drools.util.LeftTupleIndexHashTable;
import org.drools.util.LeftTupleList;
import org.drools.util.LinkedList;
import org.drools.util.LinkedListEntry;
import org.drools.util.RightTupleIndexHashTable;
import org.drools.util.RightTupleList;
import org.drools.util.AbstractHashTable.FieldIndex;

public class DefaultBetaConstraints
    implements
    BetaConstraints {

    /**
     *
     */
    private static final long serialVersionUID = 400L;

    private LinkedList  constraints;

    private int               indexed;

    public DefaultBetaConstraints() {

    }
    public DefaultBetaConstraints(final BetaNodeFieldConstraint[] constraints,
                                  final RuleBaseConfiguration conf) {
        this( constraints,
              conf,
              false );

    }

    public DefaultBetaConstraints(final BetaNodeFieldConstraint[] constraints,
                                  final RuleBaseConfiguration conf,
                                  final boolean disableIndexing) {
        this.indexed = -1;
        this.constraints = new LinkedList();
        final int depth = conf.getCompositeKeyDepth();

        // First create a LinkedList of constraints, with the indexed constraints first.
        for ( int i = 0, length = constraints.length; i < length; i++ ) {
            // Determine  if this constraint is indexable
            if ( (!disableIndexing) && conf.isIndexLeftBetaMemory() && conf.isIndexRightBetaMemory() && isIndexable( constraints[i] ) && (this.indexed < depth - 1) ) {
                if ( depth >= 1 && this.indexed == -1 ) {
                    // first index, so just add to the front
                    this.constraints.insertAfter( null,
                                                  new LinkedListEntry( constraints[i] ) );
                    this.indexed++;
                } else {
                    // insert this index after  the previous index
                    this.constraints.insertAfter( findNode( this.indexed++ ),
                                                  new LinkedListEntry( constraints[i] ) );
                }
            } else {
                // not indexed, so just add to the  end
                this.constraints.add( new LinkedListEntry( constraints[i] ) );
            }
        }

    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        constraints = (LinkedList)in.readObject();
        indexed     = in.readInt();
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(constraints);
        out.writeInt(indexed);
    }

    public ContextEntry[] createContext() {
        // Now create the ContextEntries  in the same order the constraints
        ContextEntry[] contexts = new ContextEntry[this.constraints.size()];
        int i = 0;
        for ( LinkedListEntry entry = (LinkedListEntry) this.constraints.getFirst(); entry != null; entry = (LinkedListEntry) entry.getNext() ) {
            final BetaNodeFieldConstraint constraint = (BetaNodeFieldConstraint) entry.getObject();
            contexts[i++] = constraint.createContextEntry();
        }
        return contexts;
    }

    private LinkedListEntry findNode(final int pos) {
        LinkedListEntry current = (LinkedListEntry) this.constraints.getFirst();
        for ( int i = 0; i < pos; i++ ) {
            current = (LinkedListEntry) current.getNext();
        }
        return current;
    }

    private boolean isIndexable(final BetaNodeFieldConstraint constraint) {
        if ( constraint instanceof VariableConstraint ) {
            final VariableConstraint variableConstraint = (VariableConstraint) constraint;
            return (variableConstraint.getEvaluator().getOperator() == Operator.EQUAL);
        } else {
            return false;
        }
    }

    /* (non-Javadoc)
     * @see org.drools.common.BetaNodeConstraints#updateFromTuple(org.drools.reteoo.ReteTuple)
     */
    public void updateFromTuple(final ContextEntry[] context,
                                final InternalWorkingMemory workingMemory,
                                final LeftTuple tuple) {
        for ( int i = 0; i < context.length; i++ ) {
            context[i].updateFromTuple( workingMemory,
                                        tuple );
        }
    }

    /* (non-Javadoc)
     * @see org.drools.common.BetaNodeConstraints#updateFromFactHandle(org.drools.common.InternalFactHandle)
     */
    public void updateFromFactHandle(final ContextEntry[] context,
                                     final InternalWorkingMemory workingMemory,
                                     final InternalFactHandle handle) {
        for ( int i = 0; i < context.length; i++ ) {
            context[i].updateFromFactHandle( workingMemory,
                                             handle );
        }
    }

    public void resetTuple(final ContextEntry[] context) {
        for ( int i = 0; i < context.length; i++ ) {
            context[i].resetTuple();
        }
    }

    public void resetFactHandle(final ContextEntry[] context) {
        for ( int i = 0; i < context.length; i++ ) {
            context[i].resetFactHandle();
        }
    }

    /* (non-Javadoc)
     * @see org.drools.common.BetaNodeConstraints#isAllowedCachedLeft(java.lang.Object)
     */
    public boolean isAllowedCachedLeft(final ContextEntry[] context,
                                       final InternalFactHandle handle) {
        // skip the indexed constraints
        LinkedListEntry entry = (LinkedListEntry) findNode( this.indexed+1 );

        int i = 1;
        while ( entry != null ) {
            if ( !((BetaNodeFieldConstraint) entry.getObject()).isAllowedCachedLeft( context[this.indexed + i],
                                                                                     handle ) ) {
                return false;
            }
            entry = (LinkedListEntry) entry.getNext();
            i++;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see org.drools.common.BetaNodeConstraints#isAllowedCachedRight(org.drools.reteoo.ReteTuple)
     */
    public boolean isAllowedCachedRight(final ContextEntry[] context,
                                        final LeftTuple tuple) {
        // skip the indexed constraints
        LinkedListEntry entry = (LinkedListEntry) findNode( this.indexed+1 );

        int i = 1;
        while ( entry != null ) {
            if ( !((BetaNodeFieldConstraint) entry.getObject()).isAllowedCachedRight( tuple,
                                                                                      context[this.indexed + i] ) ) {
                return false;
            }
            entry = (LinkedListEntry) entry.getNext();
            i++;
        }
        return true;
    }

    public boolean isIndexed() {
        // false if -1
        return this.indexed >= 0;
    }

    public int getIndexCount() {
        return this.indexed + 1;
    }

    public boolean isEmpty() {
        return false;
    }

    public BetaMemory createBetaMemory(RuleBaseConfiguration config) {
        BetaMemory memory;
        if ( this.indexed >= 0 ) {
            LinkedListEntry entry = (LinkedListEntry) this.constraints.getFirst();
            final List list = new ArrayList();

            for ( int pos = 0; pos <= this.indexed; pos++ ) {
                final Constraint constraint = (Constraint) entry.getObject();
                final VariableConstraint variableConstraint = (VariableConstraint) constraint;
                final FieldIndex index = new FieldIndex( variableConstraint.getFieldExtractor(),
                                                         variableConstraint.getRequiredDeclarations()[0],
                                                         variableConstraint.getEvaluator() );
                list.add( index );
                entry = (LinkedListEntry) entry.getNext();
            }

            final FieldIndex[] indexes = (FieldIndex[]) list.toArray( new FieldIndex[list.size()] );
            LeftTupleMemory tupleMemory;
            if ( config.isIndexLeftBetaMemory() ) {
                tupleMemory = new LeftTupleIndexHashTable( indexes );
            } else {
                tupleMemory = new LeftTupleList();
            }

            RightTupleMemory factHandleMemory;
            if ( config.isIndexRightBetaMemory() ) {
                factHandleMemory = new RightTupleIndexHashTable( indexes );
            } else {
                factHandleMemory = new RightTupleList();
            }
            memory = new BetaMemory( config.isSequential() ? null : tupleMemory,
                                     factHandleMemory,
                                     this.createContext() );
        } else {
            memory = new BetaMemory( config.isSequential() ? null : new LeftTupleList(),
                                     new RightTupleList(),
                                     this.createContext() );
        }

        return memory;
    }

    public int hashCode() {
        return this.constraints.hashCode();
    }

    /* (non-Javadoc)
     * @see org.drools.common.BetaNodeConstraints#getConstraints()
     */
    public LinkedList getConstraints() {
        return this.constraints;
    }

    /**
     * Determine if another object is equal to this.
     *
     * @param object
     *            The object to test.
     *
     * @return <code>true</code> if <code>object</code> is equal to this,
     *         otherwise <code>false</code>.
     */
    public boolean equals(final Object object) {
        if ( this == object ) {
            return true;
        }

        if ( object == null || !(object instanceof DefaultBetaConstraints) ) {
            return false;
        }

        final DefaultBetaConstraints other = (DefaultBetaConstraints) object;

        if ( this.constraints == other.constraints ) {
            return true;
        }

        if ( this.constraints.size() != other.constraints.size() ) {
            return false;
        }

        return this.constraints.equals( other.constraints );
    }

}