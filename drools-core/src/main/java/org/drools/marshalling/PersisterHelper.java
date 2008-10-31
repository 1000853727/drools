package org.drools.marshalling;

import java.io.IOException;

import org.drools.common.WorkingMemoryAction;
import org.drools.common.RuleFlowGroupImpl.DeactivateCallback;
import org.drools.common.TruthMaintenanceSystem.LogicalRetractCallback;
import org.drools.reteoo.PropagationQueuingNode.PropagateAction;
import org.drools.reteoo.ReteooWorkingMemory.WorkingMemoryReteAssertAction;

public class PersisterHelper {
    public static WorkingMemoryAction readWorkingMemoryAction(MarshallerReaderContext context) throws IOException {
        int type = context.readInt();
        switch(type) {
            case WorkingMemoryAction.WorkingMemoryReteAssertAction : {
                return new WorkingMemoryReteAssertAction(context);
            }
            case WorkingMemoryAction.DeactivateCallback : {
                return new DeactivateCallback(context);
            }
            case WorkingMemoryAction.PropagateAction : {
                return new PropagateAction(context);
            }
            case WorkingMemoryAction.LogicalRetractCallback : {
                return new LogicalRetractCallback(context);
            }
        }    
        return null;
    }
    
    public void write(MarshallerWriteContext context) throws IOException {
        
    }
}
