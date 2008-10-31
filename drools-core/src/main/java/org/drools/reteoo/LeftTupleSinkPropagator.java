package org.drools.reteoo;

import java.io.Externalizable;

import org.drools.common.BaseNode;
import org.drools.common.InternalFactHandle;
import org.drools.common.InternalWorkingMemory;
import org.drools.spi.PropagationContext;

public interface LeftTupleSinkPropagator
    extends
    Externalizable {
    public void propagateAssertLeftTuple(LeftTuple leftTuple,
                                         RightTuple rightTuple,
                                         PropagationContext context,
                                         InternalWorkingMemory workingMemory,
                                         boolean leftTupleMemoryEnabled);

    public void propagateAssertLeftTuple(LeftTuple tuple,
                                         PropagationContext context,
                                         InternalWorkingMemory workingMemory,
                                         boolean leftTupleMemoryEnabled);

    public void createAndPropagateAssertLeftTuple(InternalFactHandle factHandle,
                                                  PropagationContext context,
                                                  InternalWorkingMemory workingMemory,
                                                  boolean leftTupleWorkingMemoryEnabled );

    public void propagateRetractLeftTuple(LeftTuple tuple,
                                          PropagationContext context,
                                          InternalWorkingMemory workingMemory);

    public void propagateRetractLeftTupleDestroyRightTuple(LeftTuple tuple,
                                                           PropagationContext context,
                                                           InternalWorkingMemory workingMemory);

    public void propagateRetractRightTuple(RightTuple tuple,
                                           PropagationContext context,
                                           InternalWorkingMemory workingMemory);

    public BaseNode getMatchingNode(BaseNode candidate);

    public LeftTupleSink[] getSinks();

    //    public void propagateNewTupleSink(TupleMatch tupleMatch,
    //                                      PropagationContext context,
    //                                      InternalWorkingMemory workingMemory);
    //
    //    public void propagateNewTupleSink(InternalFactHandle handle,
    //                                      LinkedList list,
    //                                      PropagationContext context,
    //                                      InternalWorkingMemory workingMemory);
    //
    //    public void propagateNewTupleSink(ReteTuple tuple,
    //                                      PropagationContext context,
    //                                      InternalWorkingMemory workingMemory);
    //
    //    public List getPropagatedTuples(final Map memory,
    //                                    final InternalWorkingMemory workingMemory,
    //                                    final TupleSink sink);

    public int size();

}
