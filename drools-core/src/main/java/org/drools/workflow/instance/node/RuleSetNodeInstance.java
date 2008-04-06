package org.drools.workflow.instance.node;

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

import org.drools.common.InternalRuleFlowGroup;
import org.drools.common.RuleFlowGroupListener;
import org.drools.workflow.core.Node;
import org.drools.workflow.core.node.RuleSetNode;
import org.drools.workflow.instance.NodeInstance;

/**
 * Runtime counterpart of a ruleset node.
 * 
 * @author <a href="mailto:kris_verlaenen@hotmail.com">Kris Verlaenen</a>
 */
public class RuleSetNodeInstance extends EventNodeInstance implements RuleFlowGroupListener {

    private static final long serialVersionUID = 400L;
    
    private transient InternalRuleFlowGroup ruleFlowGroup;
    
    protected RuleSetNode getRuleSetNode() {
        return (RuleSetNode) getNode();
    }

    public void internalTrigger(final NodeInstance from, String type) {
        if (!Node.CONNECTION_DEFAULT_TYPE.equals(type)) {
            throw new IllegalArgumentException(
                "A RuleSetNode only accepts default incoming connections!");
        }
        addEventListeners();
        getProcessInstance().getAgenda().activateRuleFlowGroup( getRuleSetNode().getRuleFlowGroup() );
    }

    public void addEventListeners() {
        super.addEventListeners();
        getRuleFlowGroup().addRuleFlowGroupListener(this);
    }

    public void removeEventListeners() {
        super.removeEventListeners();
        getRuleFlowGroup().removeRuleFlowGroupListener(this);
    }

    public void cancel() {
        super.cancel();
    	getProcessInstance().getAgenda().deactivateRuleFlowGroup( getRuleSetNode().getRuleFlowGroup() );
    }

    public void ruleFlowGroupDeactivated() {
        removeEventListeners();
        triggerCompleted();
    }
    
    private InternalRuleFlowGroup getRuleFlowGroup() {
        if (this.ruleFlowGroup == null) {
            this.ruleFlowGroup = (InternalRuleFlowGroup) getProcessInstance()
                .getWorkingMemory().getAgenda()
                    .getRuleFlowGroup(getRuleSetNode().getRuleFlowGroup());
        }
        return this.ruleFlowGroup;
    }

}