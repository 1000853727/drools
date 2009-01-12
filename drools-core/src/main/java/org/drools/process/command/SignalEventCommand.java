package org.drools.process.command;

import org.drools.process.instance.ProcessInstance;
import org.drools.WorkingMemory;

public class SignalEventCommand implements Command<Object> {
	
	private long processInstanceId;
	private String eventType;
	private Object event;
	
	public long getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(long processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Object getEvent() {
		return event;
	}

	public void setEvent(Object event) {
		this.event = event;
	}

	public Object execute(WorkingMemory workingMemory) {
		if (processInstanceId == -1) {
			workingMemory.getSignalManager().signalEvent(eventType, event);
		} else {
			ProcessInstance processInstance = ( ProcessInstance ) workingMemory.getProcessInstance(processInstanceId);
			if (processInstance != null) {
				processInstance.signalEvent(eventType, processInstance);
			}
		}
		return null;
	}

}
