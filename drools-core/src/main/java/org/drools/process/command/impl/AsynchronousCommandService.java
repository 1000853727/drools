package org.drools.process.command.impl;

import org.drools.WorkingMemory;
import org.drools.process.command.Command;
import org.drools.process.command.CommandService;

public class AsynchronousCommandService implements CommandService {

	private WorkingMemory workingMemory;
	
	public AsynchronousCommandService(WorkingMemory workingMemory) {
		this.workingMemory = workingMemory;
	}
	
	public Object execute(final Command command) {
		new Thread(new Runnable() {
			public void run() {
				command.execute(workingMemory);	
			}
		}).start();
		return null;
	}

}
