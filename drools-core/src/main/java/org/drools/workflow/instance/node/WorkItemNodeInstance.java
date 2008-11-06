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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.drools.WorkingMemory;
import org.drools.common.InternalRuleBase;
import org.drools.process.core.Work;
import org.drools.process.core.context.variable.VariableScope;
import org.drools.process.instance.ProcessInstance;
import org.drools.process.instance.WorkItem;
import org.drools.process.instance.WorkItemManager;
import org.drools.process.instance.context.variable.VariableScopeInstance;
import org.drools.process.instance.impl.WorkItemImpl;
import org.drools.runtime.process.EventListener;
import org.drools.runtime.process.NodeInstance;
import org.drools.workflow.core.node.WorkItemNode;

/**
 * Runtime counterpart of a work item node.
 * 
 * @author <a href="mailto:kris_verlaenen@hotmail.com">Kris Verlaenen</a>
 */
public class WorkItemNodeInstance extends EventBasedNodeInstance implements EventListener {

    private static final long serialVersionUID = 400L;
    private static final Pattern PARAMETER_MATCHER = Pattern.compile("#\\{(\\S+)\\}", Pattern.DOTALL);
    
    private long workItemId = -1;
    private transient WorkItem workItem;
    
    protected WorkItemNode getWorkItemNode() {
        return (WorkItemNode) getNode();
    }
    
    public WorkItem getWorkItem() {
    	if (workItem == null && workItemId >= 0) {
    		workItem = ((WorkItemManager) ((ProcessInstance) getProcessInstance())
				.getWorkingMemory().getWorkItemManager()).getWorkItem(workItemId);
    	}
        return workItem;
    }
    
    public long getWorkItemId() {
        return workItemId;
    }
    
    public void internalSetWorkItemId(long workItemId) {
    	this.workItemId = workItemId;
    }
    
    public boolean isInversionOfControl() {
        return ((InternalRuleBase) ((ProcessInstance) getProcessInstance())
    		.getWorkingMemory().getRuleBase()).getConfiguration().isAdvancedProcessRuleIntegration();
    }

    public void internalTrigger(final NodeInstance from, String type) {
    	super.internalTrigger(from, type);
        // TODO this should be included for ruleflow only, not for BPEL
//        if (!Node.CONNECTION_DEFAULT_TYPE.equals(type)) {
//            throw new IllegalArgumentException(
//                "A WorkItemNode only accepts default incoming connections!");
//        }
        WorkItemNode workItemNode = getWorkItemNode();
        createWorkItem(workItemNode);
		if (workItemNode.isWaitForCompletion()) {
		    addWorkItemListener();
        }
		if (isInversionOfControl()) {
			((ProcessInstance) getProcessInstance()).getWorkingMemory()
				.update(((ProcessInstance) getProcessInstance()).getWorkingMemory().getFactHandle(this), this);
		} else {
		    ((WorkItemManager) ((ProcessInstance) getProcessInstance())
	    		.getWorkingMemory().getWorkItemManager()).internalExecuteWorkItem(workItem);
		}
        if (!workItemNode.isWaitForCompletion()) {
            triggerCompleted();
        } else {
        	this.workItemId = workItem.getId();
        }
    }
    
    protected WorkItem createWorkItem(WorkItemNode workItemNode) {
        Work work = workItemNode.getWork();
        workItem = new WorkItemImpl();
        ((WorkItem) workItem).setName(work.getName());
        ((WorkItem) workItem).setProcessInstanceId(getProcessInstance().getId());
        ((WorkItem) workItem).setParameters(new HashMap<String, Object>(work.getParameters()));
        for (Iterator<Map.Entry<String, String>> iterator = workItemNode.getInMappings().entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<String, String> mapping = iterator.next();
            VariableScopeInstance variableScopeInstance = (VariableScopeInstance)
                resolveContextInstance(VariableScope.VARIABLE_SCOPE, mapping.getValue());
            if (variableScopeInstance != null) {
            	((WorkItem) workItem).setParameter(mapping.getKey(), variableScopeInstance.getVariable(mapping.getValue()));
            } else {
                System.err.println("Could not find variable scope for variable " + mapping.getValue());
                System.err.println("when trying to execute Work Item " + work.getName());
                System.err.println("Continuing without setting parameter.");
            }
        }
        for (Map.Entry<String, Object> entry: workItem.getParameters().entrySet()) {
        	if (entry.getValue() instanceof String) {
        		String s = (String) entry.getValue();
        		Map<String, String> replacements = new HashMap<String, String>();
        		Matcher matcher = PARAMETER_MATCHER.matcher(s);
                while (matcher.find()) {
                	String paramName = matcher.group(1);
                	if (replacements.get(paramName) == null) {
		            	VariableScopeInstance variableScopeInstance = (VariableScopeInstance)
		                	resolveContextInstance(VariableScope.VARIABLE_SCOPE, paramName);
		                if (variableScopeInstance != null) {
		                    Object variableValue = variableScopeInstance.getVariable(paramName);
		                	String variableValueString = variableValue == null ? "" : variableValue.toString(); 
			                replacements.put(paramName, variableValueString);
		                } else {
		                    System.err.println("Could not find variable scope for variable " + paramName);
		                    System.err.println("when trying to replace variable in string for Work Item " + work.getName());
		                    System.err.println("Continuing without setting parameter.");
		                }
                	}
                }
                for (Map.Entry<String, String> replacement: replacements.entrySet()) {
                	s = s.replace("#{" + replacement.getKey() + "}", replacement.getValue());
                }
                ((WorkItem) workItem).setParameter(entry.getKey(), s);
        	}
        }
        return workItem;
    }

    public void triggerCompleted(WorkItem workItem) {
        for (Iterator<Map.Entry<String, String>> iterator = getWorkItemNode().getOutMappings().entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<String, String> mapping = iterator.next();
            VariableScopeInstance variableScopeInstance = (VariableScopeInstance)
                resolveContextInstance(VariableScope.VARIABLE_SCOPE, mapping.getValue());
            if (variableScopeInstance != null) {
                variableScopeInstance.setVariable(mapping.getValue(), workItem.getResult(mapping.getKey()));
            } else {
                System.err.println("Could not find variable scope for variable " + mapping.getValue());
                System.err.println("when trying to complete Work Item " + workItem.getName());
                System.err.println("Continuing without setting variable.");
            }
        }
        if (isInversionOfControl()) {
            WorkingMemory workingMemory = ((ProcessInstance) getProcessInstance()).getWorkingMemory();
            workingMemory.update(workingMemory.getFactHandle(this), this);
        } else {
            triggerCompleted();
        }
    }
    
    public void cancel() {
    	if (workItemId != -1) {
    		((WorkItemManager) ((ProcessInstance) getProcessInstance())
				.getWorkingMemory().getWorkItemManager()).internalAbortWorkItem(workItemId);
    	}
        super.cancel();
    }
    
    public void addEventListeners() {
        super.addEventListeners();
        addWorkItemListener();
    }
    
    private void addWorkItemListener() {
    	((ProcessInstance) getProcessInstance()).addEventListener("workItemCompleted", this, false);
    	((ProcessInstance) getProcessInstance()).addEventListener("workItemAborted", this, false);
    }
    
    public void removeEventListeners() {
        super.removeEventListeners();
        ((ProcessInstance) getProcessInstance()).removeEventListener("workItemCompleted", this, false);
        ((ProcessInstance) getProcessInstance()).removeEventListener("workItemAborted", this, false);
    }
    
    public void signalEvent(String type, Object event) {
    	if ("workItemCompleted".equals(type)) {
    		workItemCompleted((WorkItem) event);
    	} else if ("workItemAborted".equals(type)) {
    		workItemAborted((WorkItem) event);
    	} else {
    		super.signalEvent(type, event);
    	}
    }

    public String[] getEventTypes() {
    	return new String[] { "workItemCompleted" };
    }
    
    public void workItemAborted(WorkItem workItem) {
        if ( getWorkItem().getId() == workItem.getId() ) {
            removeEventListeners();
            triggerCompleted(workItem);
        }
    }

    public void workItemCompleted(WorkItem workItem) {
        if ( getWorkItem().getId() == workItem.getId() ) {
            removeEventListeners();
            triggerCompleted(workItem);
        }
    }

}