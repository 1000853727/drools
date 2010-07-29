/**
 * Copyright 2010 JBoss Inc
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

package org.drools.process.instance.impl;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.drools.WorkingMemory;
import org.drools.process.instance.ProcessInstance;
import org.drools.process.instance.WorkItem;
import org.drools.process.instance.WorkItemManager;
import org.drools.runtime.process.WorkItemHandler;

/**
 *
 * @author <a href="mailto:kris_verlaenen@hotmail.com">Kris Verlaenen</a>
 */
public class DefaultWorkItemManager implements WorkItemManager, Externalizable {

    private static final long serialVersionUID = 510l;

    private long workItemCounter;
	private Map<Long, WorkItem> workItems = new ConcurrentHashMap<Long, WorkItem>();
	private WorkingMemory workingMemory;
	private Map<String, WorkItemHandler> workItemHandlers = new HashMap<String, WorkItemHandler>();

    public DefaultWorkItemManager(WorkingMemory workingMemory) {
	    this.workingMemory = workingMemory;
	}

    @SuppressWarnings("unchecked")
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        workItemCounter = in.readLong();
        workItems = (Map<Long, WorkItem>)in.readObject();
        workingMemory = (WorkingMemory)in.readObject();
        workItemHandlers = (Map<String, WorkItemHandler>) in.readObject();
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(workItemCounter);
        out.writeObject(workItems);
        out.writeObject(workingMemory);
        out.writeObject(workItemHandlers);
    }

	public void internalExecuteWorkItem(WorkItem workItem) {
	    ((WorkItemImpl) workItem).setId(++workItemCounter);
	    internalAddWorkItem(workItem);
	    WorkItemHandler handler = (WorkItemHandler) this.workItemHandlers.get(workItem.getName());
	    if (handler != null) {
	        handler.executeWorkItem(workItem, this);
	    } else {
	        System.err.println("Could not find work item handler for " + workItem.getName());
	    }
	}
	
	public void internalAddWorkItem(WorkItem workItem) {
	    workItems.put(new Long(workItem.getId()), workItem);
	    // fix to reset workItemCounter after deserialization
	    if (workItem.getId() > workItemCounter) {
	    	workItemCounter = workItem.getId();
	    }
	}

    public void internalAbortWorkItem(long id) {
        WorkItemImpl workItem = (WorkItemImpl) workItems.get(new Long(id));
        // work item may have been aborted
        if (workItem != null) {
            WorkItemHandler handler = (WorkItemHandler) this.workItemHandlers.get(workItem.getName());
            if (handler != null) {
                handler.abortWorkItem(workItem, this);
            } else {
                System.err.println("Could not find work item handler for " + workItem.getName());
            }
            workItems.remove(workItem.getId());
        }
    }
    
	public Set<WorkItem> getWorkItems() {
	    return new HashSet<WorkItem>(workItems.values());
	}
	
	public WorkItem getWorkItem(long id) {
		return workItems.get(id);
	}

    public void completeWorkItem(long id, Map<String, Object> results) {
        WorkItem workItem = (WorkItem) workItems.get(new Long(id));
        // work item may have been aborted
        if (workItem != null) {
            ((org.drools.process.instance.WorkItem) workItem).setResults(results);
            ProcessInstance processInstance = ( ProcessInstance ) workingMemory.getProcessInstance(workItem.getProcessInstanceId());
            ((org.drools.process.instance.WorkItem) workItem).setState(WorkItem.COMPLETED);
            // process instance may have finished already
            if (processInstance != null) {
                processInstance.signalEvent("workItemCompleted", workItem);
            }
            workItems.remove(new Long(id));
            workingMemory.fireAllRules();
        }
    }

    public void abortWorkItem(long id) {
        WorkItemImpl workItem = (WorkItemImpl) workItems.get(new Long(id));
        // work item may have been aborted
        if (workItem != null) {
            ProcessInstance processInstance = ( ProcessInstance ) workingMemory.getProcessInstance(workItem.getProcessInstanceId());
            workItem.setState(WorkItem.ABORTED);
            // process instance may have finished already
            if (processInstance != null) {
                processInstance.signalEvent("workItemAborted", workItem);
            }
            workItems.remove(new Long(id));
            workingMemory.fireAllRules();
        }
    }

    public void registerWorkItemHandler(String workItemName, WorkItemHandler handler) {
        this.workItemHandlers.put(workItemName, handler);
    }

}
