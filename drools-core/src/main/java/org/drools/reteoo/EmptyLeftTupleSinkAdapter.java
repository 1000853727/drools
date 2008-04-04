package org.drools.reteoo;

import org.drools.common.InternalFactHandle;
import org.drools.common.InternalWorkingMemory;
import org.drools.common.BaseNode;
import org.drools.spi.PropagationContext;

import java.io.ObjectOutput;
import java.io.IOException;
import java.io.ObjectInput;

public class EmptyLeftTupleSinkAdapter
    implements
    LeftTupleSinkPropagator {

    private static final EmptyLeftTupleSinkAdapter instance = new EmptyLeftTupleSinkAdapter();

    public static final EmptyLeftTupleSinkAdapter getInstance() {
        return instance;
    }

    public EmptyLeftTupleSinkAdapter() {
        // constructor needed for serialisation
    }

    public void propagateAssertLeftTuple(final LeftTuple leftTuple,
                                         final RightTuple rightTuple,
                                         final PropagationContext context,
                                         final InternalWorkingMemory workingMemory,
                                         final boolean leftTupleMemoryEnabled) {
    }

    public void propagateAssertLeftTuple(final LeftTuple tuple,
                                         final PropagationContext context,
                                         final InternalWorkingMemory workingMemory,
                                         final boolean leftTupleMemoryEnabled) {
    }

    public void createAndPropagateAssertLeftTuple(final InternalFactHandle factHandle,
                                                  final PropagationContext context,
                                                  final InternalWorkingMemory workingMemory,
                                                  final boolean leftTupleMemoryEnabled) {
    }

    public void propagateRetractLeftTuple(final LeftTuple tuple,
                                          final PropagationContext context,
                                          final InternalWorkingMemory workingMemory) {
    }

    public void propagateRetractRightTuple(RightTuple tuple,
                                           PropagationContext context,
                                           InternalWorkingMemory workingMemory) {
    }

    public BaseNode getMatchingNode(BaseNode candidate) {
        return null;
    }

    public void propagateRetractLeftTupleDestroyRightTuple(LeftTuple tuple,
                                                           PropagationContext context,
                                                           InternalWorkingMemory workingMemory) {
    }

    public void readExternal(ObjectInput in) throws IOException,
                                            ClassNotFoundException {
    }

    public void writeExternal(ObjectOutput out) throws IOException {
    }

    public LeftTupleSink[] getSinks() {
        return new LeftTupleSink[]{};
    }

    public int size() {
        return 0;
    }

}
