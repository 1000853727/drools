/**
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

package org.drools.common;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.drools.RuleBaseConfiguration;
import org.drools.base.evaluators.Operator;
import org.drools.core.util.LeftTupleIndexHashTable;
import org.drools.core.util.LeftTupleList;
import org.drools.core.util.LinkedList;
import org.drools.core.util.LinkedListEntry;
import org.drools.core.util.RightTupleIndexHashTable;
import org.drools.core.util.RightTupleList;
import org.drools.core.util.AbstractHashTable.FieldIndex;
import org.drools.reteoo.BetaMemory;
import org.drools.reteoo.LeftTuple;
import org.drools.reteoo.LeftTupleMemory;
import org.drools.reteoo.RightTupleMemory;
import org.drools.rule.ContextEntry;
import org.drools.rule.UnificationRestriction;
import org.drools.rule.VariableConstraint;
import org.drools.spi.BetaNodeFieldConstraint;

public class SingleBetaConstraints
    implements
    BetaConstraints {

    /**
     *
     */
    private static final long             serialVersionUID = 510l;

    private BetaNodeFieldConstraint constraint;

    private boolean                       indexed;

    private RuleBaseConfiguration         conf;

    public SingleBetaConstraints() {

    }

    public SingleBetaConstraints(final BetaNodeFieldConstraint[] constraint,
                                 final RuleBaseConfiguration conf) {
        this( constraint[0],
              conf,
              false );
    }

    public SingleBetaConstraints(final BetaNodeFieldConstraint constraint,
                                 final RuleBaseConfiguration conf) {
        this( constraint,
              conf,
              false );
    }

    public SingleBetaConstraints(final BetaNodeFieldConstraint constraint,
                                 final RuleBaseConfiguration conf,
                                 final boolean disableIndex) {
        this.conf = conf;
        if ( (disableIndex) || (!conf.isIndexLeftBetaMemory() && !conf.isIndexRightBetaMemory()) ) {
            this.indexed = false;
        } else {
            final int depth = conf.getCompositeKeyDepth();
            // Determine  if this constraint is indexable
            this.indexed = depth >= 1 && isIndexable( constraint );
        }

        this.constraint = constraint;
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        constraint  = (BetaNodeFieldConstraint)in.readObject();
        indexed     = in.readBoolean();
        conf        = (RuleBaseConfiguration)in.readObject();

    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(constraint);
        out.writeBoolean(indexed);
        out.writeObject(conf);
    }

    public  static boolean isIndexable(final BetaNodeFieldConstraint constraint) {
        if ( constraint instanceof VariableConstraint  ) {
            final VariableConstraint variableConstraint = (VariableConstraint) constraint;
            if ( (!(variableConstraint.getRestriction() instanceof UnificationRestriction )) ) {
                return (variableConstraint.getEvaluator().getOperator() == Operator.EQUAL);
            }
        } 
        
        return false;
    }

    public ContextEntry[] createContext() {
        return new ContextEntry[] { this.constraint.createContextEntry() };
    }

    /* (non-Javadoc)
     * @see org.drools.common.BetaNodeConstraints#updateFromTuple(org.drools.reteoo.ReteTuple)
     */
    public void updateFromTuple(final ContextEntry[] context,
                                final InternalWorkingMemory workingMemory,
                                final LeftTuple tuple) {
        context[0].updateFromTuple( workingMemory,
                                 tuple );
    }

    /* (non-Javadoc)
     * @see org.drools.common.BetaNodeConstraints#updateFromFactHandle(org.drools.common.InternalFactHandle)
     */
    public void updateFromFactHandle(final ContextEntry[] context,
                                     final InternalWorkingMemory workingMemory,
                                     final InternalFactHandle handle) {
        context[0].updateFromFactHandle( workingMemory,
                                           handle );
    }

    /* (non-Javadoc)
     * @see org.drools.common.BetaNodeConstraints#isAllowedCachedLeft(java.lang.Object)
     */
    public boolean isAllowedCachedLeft(final ContextEntry[] context,
                                       final InternalFactHandle handle) {
        return this.indexed || this.constraint.isAllowedCachedLeft( context[0],
                                                                    handle );
    }

    /* (non-Javadoc)
     * @see org.drools.common.BetaNodeConstraints#isAllowedCachedRight(org.drools.reteoo.ReteTuple)
     */
    public boolean isAllowedCachedRight(final ContextEntry[] context,
                                        final LeftTuple tuple) {
        return this.constraint.isAllowedCachedRight( tuple,
                                                     context[0] );
    }

    public boolean isIndexed() {
        return this.indexed;
    }

    public int getIndexCount() {
        return (this.indexed ? 1 : 0);
    }

    public boolean isEmpty() {
        return false;
    }

    public BetaMemory createBetaMemory(final RuleBaseConfiguration config) {
        BetaMemory memory;
        if ( this.indexed ) {
            final VariableConstraint variableConstraint = (VariableConstraint) this.constraint;
            final FieldIndex index = new FieldIndex( variableConstraint.getFieldExtractor(),
                                                     variableConstraint.getRequiredDeclarations()[0],
                                                     variableConstraint.getEvaluator() );
            LeftTupleMemory tupleMemory;
            if ( this.conf.isIndexLeftBetaMemory() ) {
                tupleMemory = new LeftTupleIndexHashTable( new FieldIndex[]{index} );
            } else {
                tupleMemory = new LeftTupleList();
            }

            RightTupleMemory factHandleMemory;
            if ( this.conf.isIndexRightBetaMemory() ) {
                factHandleMemory = new RightTupleIndexHashTable( new FieldIndex[]{index} );
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
        return this.constraint.hashCode();
    }

    /* (non-Javadoc)
     * @see org.drools.common.BetaNodeConstraints#getConstraints()
     */
    public LinkedList getConstraints() {
        final LinkedList list = new LinkedList();
        list.add( new LinkedListEntry( this.constraint ) );
        return list;
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

        if ( object == null || getClass() != object.getClass() ) {
            return false;
        }

        final SingleBetaConstraints other = (SingleBetaConstraints) object;

        return this.constraint == other.constraint || this.constraint.equals( other.constraint );
    }

    public void resetFactHandle(ContextEntry[] context) {
        context[0].resetFactHandle();
    }

    public void resetTuple(ContextEntry[] context) {
        context[0].resetTuple();
    }

}
