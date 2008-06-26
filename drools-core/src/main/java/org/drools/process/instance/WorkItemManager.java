package org.drools.process.instance;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.drools.WorkingMemory;
import org.drools.process.instance.impl.WorkItemImpl;

/**
 *
 * @author <a href="mailto:kris_verlaenen@hotmail.com">Kris Verlaenen</a>
 */
public class WorkItemManager implements Externalizable {

    private static final long serialVersionUID = 400L;

    private long workItemCounter;
	private Map<Long, WorkItem> workItems = new HashMap<Long, WorkItem>();
	private WorkingMemory workingMemory;
	private Map<String, WorkItemHandler> workItemHandlers = new HashMap<String, WorkItemHandler>();

    public WorkItemManager() {

    }
    public WorkItemManager(WorkingMemory workingMemory) {
	    this.workingMemory = workingMemory;
	}

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        workItemCounter = in.readLong();
        workItems   = (Map<Long, WorkItem>)in.readObject();
        workingMemory   = (WorkingMemory)in.readObject();
        workItemHandlers   = (Map<String, WorkItemHandler>)in.readObject();
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
        }
    }
    
	public Set<WorkItem> getWorkItems() {
	    return new HashSet<WorkItem>(workItems.values());
	}
	
	public WorkItem getWorkItem(long id) {
		return workItems.get(id);
	}

    public void completeWorkItem(long id, Map<String, Object> results) {
        WorkItemImpl workItem = (WorkItemImpl) workItems.get(new Long(id));
        // work item may have been aborted
        if (workItem != null) {
            workItem.setResults(results);
            ProcessInstance processInstance = workingMemory.getProcessInstance(workItem.getProcessInstanceId());
            workItem.setState(WorkItem.COMPLETED);
            // process instance may have finished already
            if (processInstance != null) {
                processInstance.workItemCompleted(workItem);
            }
            workItems.remove(new Long(id));
            workingMemory.fireAllRules();
        }
    }

    public void abortWorkItem(long id) {
        WorkItemImpl workItem = (WorkItemImpl) workItems.get(new Long(id));
        // work item may have been aborted
        if (workItem != null) {
            ProcessInstance processInstance = workingMemory.getProcessInstance(workItem.getProcessInstanceId());
            workItem.setState(WorkItem.ABORTED);
            // process instance may have finished already
            if (processInstance != null) {
                processInstance.workItemAborted(workItem);
            }
            workItems.remove(new Long(id));
            workingMemory.fireAllRules();
        }
    }

    public void registerWorkItemHandler(String workItemName, WorkItemHandler handler) {
        this.workItemHandlers.put(workItemName, handler);
    }

}
