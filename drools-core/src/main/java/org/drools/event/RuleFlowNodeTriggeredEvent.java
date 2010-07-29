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

package org.drools.event;

import org.drools.runtime.process.NodeInstance;


/**
 * @author <a href="mailto:kris_verlaenen@hotmail.com">Kris Verlaenen</a>
 */
public class RuleFlowNodeTriggeredEvent extends ProcessEvent {

    private static final long serialVersionUID = 510l;
    
    private NodeInstance nodeInstance;

    public RuleFlowNodeTriggeredEvent(final NodeInstance nodeInstance) {
        super( nodeInstance.getProcessInstance() );
        this.nodeInstance = nodeInstance;
    }
    
    public NodeInstance getRuleFlowNodeInstance() {
        return nodeInstance;
    }

    public String toString() {
        return "==>[WorkflowNodeTriggered(nodeId=" + nodeInstance.getNodeId() + "; id=" + nodeInstance.getId() 
            + "; processName=" + getProcessInstance().getProcessName() + "; processId=" + getProcessInstance().getProcessId() + ")]";
    }
}
