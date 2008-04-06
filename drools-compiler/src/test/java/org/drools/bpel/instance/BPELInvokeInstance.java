package org.drools.bpel.instance;

import org.drools.process.core.context.exception.ExceptionScope;
import org.drools.process.instance.WorkItem;
import org.drools.process.instance.context.exception.ExceptionScopeInstance;
import org.drools.workflow.instance.NodeInstance;
import org.drools.workflow.instance.node.WorkItemNodeInstance;

/**
 * 
 * @author <a href="mailto:kris_verlaenen@hotmail.com">Kris Verlaenen</a>
 */
public class BPELInvokeInstance extends WorkItemNodeInstance {
    
    private static final long serialVersionUID = 400L;

    public void internalTrigger(NodeInstance from, String type) {
        if (BPELLinkManager.checkActivityEnabled(this)) {
            super.internalTrigger(from, type);
        }
    }
    
    public void triggerCompleted(WorkItem workItem) {
        String faultName = (String) workItem.getResult("FaultName");
        if (faultName == null) {
            super.triggerCompleted(workItem);
            BPELLinkManager.activateTargetLinks(this);
        } else {
            String faultMessage = (String) workItem.getResult("Result");
            ExceptionScopeInstance exceptionScopeInstance = (ExceptionScopeInstance)
                resolveContextInstance(ExceptionScope.EXCEPTION_SCOPE, faultName);
            exceptionScopeInstance.handleException(faultName, faultMessage);
        }
    }
    
}
