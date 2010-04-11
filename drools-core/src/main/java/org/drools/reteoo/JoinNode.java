package org.drools.reteoo;

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

import org.drools.common.BetaConstraints;
import org.drools.common.InternalFactHandle;
import org.drools.common.InternalWorkingMemory;
import org.drools.core.util.Iterator;
import org.drools.reteoo.builder.BuildContext;
import org.drools.rule.Behavior;
import org.drools.spi.PropagationContext;

/**
 *
 */
public class JoinNode extends BetaNode {

    /**
     *
     */
    private static final long serialVersionUID = 400L;

    public JoinNode() {

    }

    public JoinNode(final int id,
                    final LeftTupleSource leftInput,
                    final ObjectSource rightInput,
                    final BetaConstraints binder,
                    final Behavior[] behaviors,
                    final BuildContext context) {
        super( id,
               context.getPartitionId(),
               context.getRuleBase().getConfiguration().isMultithreadEvaluation(),
               leftInput,
               rightInput,
               binder,
               behaviors );
        tupleMemoryEnabled = context.isTupleMemoryEnabled();
    }

    public void assertLeftTuple(final LeftTuple leftTuple,
                                final PropagationContext context,
                                final InternalWorkingMemory workingMemory) {
        final BetaMemory memory = (BetaMemory) workingMemory.getNodeMemory( this );

        if ( this.tupleMemoryEnabled ) {
            memory.getLeftTupleMemory().add( leftTuple );
        }

        this.constraints.updateFromTuple( memory.getContext(),
                                          workingMemory,
                                          leftTuple );
        for ( RightTuple rightTuple = memory.getRightTupleMemory().getFirst( leftTuple ); rightTuple != null; rightTuple = (RightTuple) rightTuple.getNext() ) {
            final InternalFactHandle handle = rightTuple.getFactHandle();
            if ( this.constraints.isAllowedCachedLeft( memory.getContext(),
                                                       handle ) ) {
                this.sink.propagateAssertLeftTuple( leftTuple,
                                                    rightTuple,
                                                    null,
                                                    null,
                                                    context,
                                                    workingMemory,
                                                    this.tupleMemoryEnabled );
            }
        }

        this.constraints.resetTuple( memory.getContext() );
    }

    public void assertObject(final InternalFactHandle factHandle,
                             final PropagationContext context,
                             final InternalWorkingMemory workingMemory) {
        final BetaMemory memory = (BetaMemory) workingMemory.getNodeMemory( this );

        RightTuple rightTuple = createRightTuple( factHandle,
                                                  this );

        if ( !behavior.assertRightTuple( memory.getBehaviorContext(),
                                         rightTuple,
                                         workingMemory ) ) {
            // destroy right tuple
            rightTuple.unlinkFromRightParent();
            return;
        }

        memory.getRightTupleMemory().add( rightTuple );
        if ( !this.tupleMemoryEnabled ) {
            // do nothing here, as we know there are no left tuples at this stage in sequential mode.
            return;
        }

        this.constraints.updateFromFactHandle( memory.getContext(),
                                               workingMemory,
                                               factHandle );
        int i = 0;
        for ( LeftTuple leftTuple = memory.getLeftTupleMemory().getFirst( rightTuple ); leftTuple != null; leftTuple = (LeftTuple) leftTuple.getNext() ) {
            if ( this.constraints.isAllowedCachedRight( memory.getContext(),
                                                        leftTuple ) ) {
                // wm.marshaller.write( i, leftTuple )
                this.sink.propagateAssertLeftTuple( leftTuple,
                                                    rightTuple,
                                                    null,
                                                    null,
                                                    context,
                                                    workingMemory,
                                                    this.tupleMemoryEnabled );
            }
            i++;
        }
        this.constraints.resetFactHandle( memory.getContext() );
    }

    public void retractRightTuple(final RightTuple rightTuple,
                                  final PropagationContext context,
                                  final InternalWorkingMemory workingMemory) {
        final BetaMemory memory = (BetaMemory) workingMemory.getNodeMemory( this );
        behavior.retractRightTuple( memory.getBehaviorContext(),
                                    rightTuple,
                                    workingMemory );
        memory.getRightTupleMemory().remove( rightTuple );

        if ( rightTuple.firstChild != null ) {
            this.sink.propagateRetractRightTuple( rightTuple,
                                                  context,
                                                  workingMemory );
        }
    }

    public void retractLeftTuple(final LeftTuple leftTuple,
                                 final PropagationContext context,
                                 final InternalWorkingMemory workingMemory) {
        final BetaMemory memory = (BetaMemory) workingMemory.getNodeMemory( this );
        memory.getLeftTupleMemory().remove( leftTuple );
        if ( leftTuple.firstChild != null ) {
            this.sink.propagateRetractLeftTuple( leftTuple,
                                                 context,
                                                 workingMemory );
        }
    }

    public void modifyRightTuple(final RightTuple rightTuple,
                                 final PropagationContext context,
                                 final InternalWorkingMemory workingMemory) {
        final BetaMemory memory = (BetaMemory) workingMemory.getNodeMemory( this );

        // WTD here
        //                if ( !behavior.assertRightTuple( memory.getBehaviorContext(),
        //                                                 rightTuple,
        //                                                 workingMemory ) ) {
        //                    // destroy right tuple
        //                    rightTuple.unlinkFromRightParent();
        //                    return;
        //                }

        // Add and remove to make sure we are in the right bucket and at the end
        // this is needed to fix for indexing and deterministic iteration
        memory.getRightTupleMemory().remove( rightTuple );
        memory.getRightTupleMemory().add( rightTuple );
        
        if ( !this.tupleMemoryEnabled ) {
            // do nothing here, as we know there are no left tuples at this stage in sequential mode.
            return;
        }        

        LeftTuple childLeftTuple = rightTuple.firstChild;

        LeftTupleMemory leftMemory = memory.getLeftTupleMemory();

        LeftTuple leftTuple = leftMemory.getFirst( rightTuple );

        this.constraints.updateFromFactHandle( memory.getContext(),
                                               workingMemory,
                                               rightTuple.getFactHandle() );

        // first check our index (for indexed nodes only) hasn't changed and we are returning the same bucket
        if ( childLeftTuple != null && leftMemory.isIndexed() && leftTuple != leftMemory.getFirst( childLeftTuple.getLeftParent() ) ) {
            // our index has changed, so delete all the previous propagations
            this.sink.propagateRetractRightTuple( rightTuple,
                                                  context,
                                                  workingMemory );

            childLeftTuple = null; // null so the next check will attempt matches for new bucket
        }

        // we can't do anything if LeftTupleMemory is empty
        if ( leftTuple != null ) {
            if ( childLeftTuple == null ) {
                // either we are indexed and changed buckets or
                // we had no children before, but there is a bucket to potentially match, so try as normal assert
                for ( ; leftTuple != null; leftTuple = (LeftTuple) leftTuple.getNext() ) {
                    if ( this.constraints.isAllowedCachedRight( memory.getContext(),
                                                                leftTuple ) ) {
                        this.sink.propagateAssertLeftTuple( leftTuple,
                                                            rightTuple,
                                                            null,
                                                            null,
                                                            context,
                                                            workingMemory,
                                                            this.tupleMemoryEnabled );
                    }
                }
            } else {
                // in the same bucket, so iterate and compare
                for ( ; leftTuple != null; leftTuple = (LeftTuple) leftTuple.getNext() ) {
                    if ( this.constraints.isAllowedCachedRight( memory.getContext(),
                                                                leftTuple ) ) {
                        if ( childLeftTuple == null || childLeftTuple.getLeftParent() != leftTuple ) {
                            this.sink.propagateAssertLeftTuple( leftTuple,
                                                                rightTuple,
                                                                null,
                                                                childLeftTuple,
                                                                context,
                                                                workingMemory,
                                                                this.tupleMemoryEnabled );
                        } else {
                            // preserve the current LeftTuple, as we need to iterate to the next before re-adding
                            LeftTuple temp = childLeftTuple;
                            childLeftTuple = this.sink.propagateModifyChildLeftTuple( childLeftTuple,
                                                                                      leftTuple,
                                                                                      context,
                                                                                      workingMemory,
                                                                                      this.tupleMemoryEnabled );
                            // we must re-add this to ensure deterministic iteration
                            temp.reAddLeft();
                        }
                    } else if ( childLeftTuple != null && childLeftTuple.getLeftParent() == leftTuple ) {
                        childLeftTuple = this.sink.propagateRetractChildLeftTuple( childLeftTuple,
                                                                                   leftTuple,
                                                                                   context,
                                                                                   workingMemory );
                    }
                    // else do nothing, was false before and false now.
                }
            }
        }

        this.constraints.resetFactHandle( memory.getContext() );
    }

    public void modifyLeftTuple(final LeftTuple leftTuple,
                                final PropagationContext context,
                                final InternalWorkingMemory workingMemory) {
        final BetaMemory memory = (BetaMemory) workingMemory.getNodeMemory( this );

        // Add and remove to make sure we are in the right bucket and at the end
        // this is needed to fix for indexing and deterministic iteration
        memory.getLeftTupleMemory().remove( leftTuple );
        memory.getLeftTupleMemory().add( leftTuple );

        this.constraints.updateFromTuple( memory.getContext(),
                                          workingMemory,
                                          leftTuple );
        LeftTuple childLeftTuple = leftTuple.firstChild;

        RightTupleMemory rightMemory = memory.getRightTupleMemory();

        RightTuple rightTuple = rightMemory.getFirst( leftTuple );

        // first check our index (for indexed nodes only) hasn't changed and we are returning the same bucket
        if ( childLeftTuple != null && rightMemory.isIndexed() && rightTuple != rightMemory.getFirst( childLeftTuple.getRightParent() ) ) {
            // our index has changed, so delete all the previous propagations
            this.sink.propagateRetractLeftTuple( leftTuple,
                                                 context,
                                                 workingMemory );

            childLeftTuple = null; // null so the next check will attempt matches for new bucket
        }

        // we can't do anything if RightTupleMemory is empty
        if ( rightTuple != null ) {
            if ( childLeftTuple == null ) {
                // either we are indexed and changed buckets or
                // we had no children before, but there is a bucket to potentially match, so try as normal assert
                for ( ; rightTuple != null; rightTuple = (RightTuple) rightTuple.getNext() ) {
                    final InternalFactHandle handle = rightTuple.getFactHandle();
                    if ( this.constraints.isAllowedCachedLeft( memory.getContext(),
                                                               handle ) ) {
                        this.sink.propagateAssertLeftTuple( leftTuple,
                                                            rightTuple,
                                                            null,
                                                            null,
                                                            context,
                                                            workingMemory,
                                                            this.tupleMemoryEnabled );
                    }
                }
            } else {
                // in the same bucket, so iterate and compare
                for ( ; rightTuple != null; rightTuple = (RightTuple) rightTuple.getNext() ) {
                    final InternalFactHandle handle = rightTuple.getFactHandle();

                    if ( this.constraints.isAllowedCachedLeft( memory.getContext(),
                                                               handle ) ) {
                        if ( childLeftTuple == null || childLeftTuple.getRightParent() != rightTuple ) {
                            this.sink.propagateAssertLeftTuple( leftTuple,
                                                                rightTuple,
                                                                childLeftTuple,
                                                                null,
                                                                context,
                                                                workingMemory,
                                                                this.tupleMemoryEnabled );
                        } else {
                            // preserve the current LeftTuple, as we need to iterate to the next before re-adding
                            LeftTuple temp = childLeftTuple;
                            childLeftTuple = this.sink.propagateModifyChildLeftTuple( childLeftTuple,
                                                                                      rightTuple,
                                                                                      context,
                                                                                      workingMemory,
                                                                                      this.tupleMemoryEnabled );
                            // we must re-add this to ensure deterministic iteration
                            temp.reAddRight();
                        }
                    } else if ( childLeftTuple != null && childLeftTuple.getRightParent() == rightTuple ) {
                        childLeftTuple = this.sink.propagateRetractChildLeftTuple( childLeftTuple,
                                                                                   rightTuple,
                                                                                   context,
                                                                                   workingMemory );
                    }
                    // else do nothing, was false before and false now.
                }
            }
        }

        this.constraints.resetTuple( memory.getContext() );
    }

    /* (non-Javadoc)
     * @see org.drools.reteoo.BaseNode#updateNewNode(org.drools.reteoo.WorkingMemoryImpl, org.drools.spi.PropagationContext)
     */
    public void updateSink(final LeftTupleSink sink,
                           final PropagationContext context,
                           final InternalWorkingMemory workingMemory) {

        final BetaMemory memory = (BetaMemory) workingMemory.getNodeMemory( this );

        final Iterator tupleIter = memory.getLeftTupleMemory().iterator();
        for ( LeftTuple leftTuple = (LeftTuple) tupleIter.next(); leftTuple != null; leftTuple = (LeftTuple) tupleIter.next() ) {
            this.constraints.updateFromTuple( memory.getContext(),
                                              workingMemory,
                                              leftTuple );
            for ( RightTuple rightTuple = memory.getRightTupleMemory().getFirst( leftTuple ); rightTuple != null; rightTuple = (RightTuple) rightTuple.getNext() ) {
                if ( this.constraints.isAllowedCachedLeft( memory.getContext(),
                                                           rightTuple.getFactHandle() ) ) {
                    sink.assertLeftTuple( new LeftTuple( leftTuple,
                                                         rightTuple,
                                                         null,
                                                         null,
                                                         sink,
                                                         this.tupleMemoryEnabled ),
                                          context,
                                          workingMemory );
                }
            }

            this.constraints.resetTuple( memory.getContext() );
        }
    }

    public short getType() {
        return NodeTypeEnums.JoinNode;
    }

    public String toString() {
        ObjectSource source = this.rightInput;
        while ( !(source instanceof ObjectTypeNode) ) {
            source = source.source;
        }

        return "[JoinNode(" + this.getId() + ") - " + ((ObjectTypeNode) source).getObjectType() + "]";
    }
}
