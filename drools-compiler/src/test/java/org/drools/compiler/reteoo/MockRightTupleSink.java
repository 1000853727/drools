package org.drools.compiler.reteoo;

import org.drools.core.common.InternalWorkingMemory;
import org.drools.core.common.RuleBasePartitionId;
import org.drools.core.reteoo.NodeTypeEnums;
import org.drools.core.reteoo.ObjectTypeNode;
import org.drools.core.reteoo.RightTuple;
import org.drools.core.reteoo.RightTupleSink;
import org.drools.core.spi.PropagationContext;
import org.drools.core.spi.RuleComponent;
import org.kie.api.definition.rule.Rule;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MockRightTupleSink
    implements
        RightTupleSink {
    
    private final List        retracted        = new ArrayList();

    public void retractRightTuple(RightTuple rightTuple,
                                  PropagationContext context,
                                  InternalWorkingMemory workingMemory) {
        this.retracted.add( new Object[]{rightTuple, context, workingMemory} );

    }
    
    public List getRetracted() {
        return this.retracted;
    }

    public int getId() {
        return 0;
    }

    public RuleBasePartitionId getPartitionId() {
        return null;
    }

    public void writeExternal( ObjectOutput out ) throws IOException {
    }

    public void readExternal( ObjectInput in ) throws IOException, ClassNotFoundException {
    }

    public short getType() {
        return NodeTypeEnums.JoinNode;
    }

    @Override
    public void assertRightTuple(RightTuple rightTuple, PropagationContext context, InternalWorkingMemory workingMemory) {
    }

    public void modifyRightTuple(RightTuple rightTuple,
                                 PropagationContext context,
                                 InternalWorkingMemory workingMemory) {
    }

    public Map<Rule, RuleComponent> getAssociations() {
        return null;
    }

    public ObjectTypeNode.Id getRightInputOtnId() {
        return null;
    }
}
