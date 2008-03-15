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

package org.drools.rule;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.io.ObjectOutput;
import java.io.IOException;
import java.io.ObjectInput;

import org.drools.RuntimeDroolsException;
import org.drools.WorkingMemory;
import org.drools.common.InternalFactHandle;
import org.drools.spi.Accumulator;
import org.drools.spi.Tuple;

/**
 * A class to represent the Accumulate CE
 */
public class Accumulate extends ConditionalElement
    implements
    PatternSource {

    private static final long    serialVersionUID = 400L;

    private Accumulator          accumulator;
    private RuleConditionElement source;
    private Declaration[]        requiredDeclarations;
    private Declaration[]        innerDeclarations;

    public Accumulate() {

    }

    public Accumulate(final RuleConditionElement source) {

        this( source,
              new Declaration[0],
              new Declaration[0],
              null );
    }

    public Accumulate(final RuleConditionElement source,
                      final Declaration[] requiredDeclarations,
                      final Declaration[] innerDeclarations) {

        this( source,
              requiredDeclarations,
              innerDeclarations,
              null );
    }

    public Accumulate(final RuleConditionElement source,
                      final Declaration[] requiredDeclarations,
                      final Declaration[] innerDeclarations,
                      final Accumulator accumulator) {

        this.source = source;
        this.requiredDeclarations = requiredDeclarations;
        this.innerDeclarations = innerDeclarations;
        this.accumulator = accumulator;
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        accumulator = (Accumulator)in.readObject();
        source = (RuleConditionElement)in.readObject();
        requiredDeclarations = (Declaration[])in.readObject();
        innerDeclarations = (Declaration[])in.readObject();
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(accumulator);
        out.writeObject(source);
        out.writeObject(requiredDeclarations);
        out.writeObject(innerDeclarations);
    }

    public Accumulator getAccumulator() {
        return this.accumulator;
    }

    public void setAccumulator(final Accumulator accumulator) {
        this.accumulator = accumulator;
    }

    public Object createContext() {
        return this.accumulator.createContext();
    }

    /**
     * Executes the initialization block of code
     *
     * @param leftTuple tuple causing the rule fire
     * @param declarations previous declarations
     * @param workingMemory
     * @throws Exception
     */
    public void init(final Object workingMemoryContext,
                     final Object context,
                     final Tuple leftTuple,
                     final WorkingMemory workingMemory) {
        try {
            this.accumulator.init( workingMemoryContext,
                                   context,
                                   leftTuple,
                                   this.requiredDeclarations,
                                   workingMemory );
        } catch ( final Exception e ) {
            throw new RuntimeDroolsException( e );
        }
    }

    /**
     * Executes the accumulate (action) code for the given fact handle
     *
     * @param leftTuple
     * @param handle
     * @param declarations
     * @param innerDeclarations
     * @param workingMemory
     * @throws Exception
     */
    public void accumulate(final Object workingMemoryContext,
                           final Object context,
                           final Tuple leftTuple,
                           final InternalFactHandle handle,
                           final WorkingMemory workingMemory) {
        try {
            this.accumulator.accumulate( workingMemoryContext,
                                         context,
                                         leftTuple,
                                         handle,
                                         this.requiredDeclarations,
                                         this.innerDeclarations,
                                         workingMemory );
        } catch ( final Exception e ) {
            throw new RuntimeDroolsException( e );
        }
    }

    /**
     * Executes the reverse (action) code for the given fact handle
     *
     * @param leftTuple
     * @param handle
     * @param declarations
     * @param innerDeclarations
     * @param workingMemory
     * @throws Exception
     */
    public void reverse(final Object workingMemoryContext,
                        final Object context,
                        final Tuple leftTuple,
                        final InternalFactHandle handle,
                        final WorkingMemory workingMemory) {
        try {
            this.accumulator.reverse( workingMemoryContext,
                                      context,
                                      leftTuple,
                                      handle,
                                      this.requiredDeclarations,
                                      this.innerDeclarations,
                                      workingMemory );
        } catch ( final Exception e ) {
            throw new RuntimeDroolsException( e );
        }
    }

    /**
     * Gets the result of the accummulation
     *
     * @param leftTuple
     * @param declarations
     * @param workingMemory
     * @return
     * @throws Exception
     */
    public Object getResult(final Object workingMemoryContext,
                            final Object context,
                            final Tuple leftTuple,
                            final WorkingMemory workingMemory) {
        try {
            return this.accumulator.getResult( workingMemoryContext,
                                               context,
                                               leftTuple,
                                               this.requiredDeclarations,
                                               workingMemory );
        } catch ( final Exception e ) {
            throw new RuntimeDroolsException( e );
        }
    }

    /**
     * Returns true if this accumulate supports reverse
     * @return
     */
    public boolean supportsReverse() {
        return this.accumulator.supportsReverse();
    }

    public Object clone() {
        return new Accumulate( this.source,
                               this.requiredDeclarations,
                               this.innerDeclarations,
                               this.accumulator );
    }

    public RuleConditionElement getSource() {
        return this.source;
    }

    public Map getInnerDeclarations() {
        return this.source.getInnerDeclarations();
    }

    public Map getOuterDeclarations() {
        return Collections.EMPTY_MAP;
    }

    /**
     * @inheritDoc
     */
    public Declaration resolveDeclaration(final String identifier) {
        return (Declaration) this.source.getInnerDeclarations().get( identifier );
    }

    public Object createWorkingMemoryContext() {
        return this.accumulator.createWorkingMemoryContext();
    }

    public List getNestedElements() {
        return Collections.singletonList( this.source );
    }

    public boolean isPatternScopeDelimiter() {
        return true;
    }

}
