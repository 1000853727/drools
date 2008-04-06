package org.drools.process.instance;

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

import org.drools.Agenda;
import org.drools.WorkingMemory;
import org.drools.common.InternalWorkingMemory;
import org.drools.process.core.Process;
import org.drools.process.instance.timer.TimerListener;

/**
 * A process instance is the representation of a process during its execution.
 * It contains all the runtime status information about the running process.
 * A process can have multiple instances.
 * 
 * @author <a href="mailto:kris_verlaenen@hotmail.com">Kris Verlaenen</a>
 */
public interface ProcessInstance extends ContextInstanceContainer, ContextableInstance, WorkItemListener, TimerListener {

    int STATE_PENDING   = 0;
    int STATE_ACTIVE    = 1;
    int STATE_COMPLETED = 2;
    int STATE_ABORTED   = 3;
    int STATE_SUSPENDED = 4;

    void setId(long id);

    long getId();

    void setProcess(Process process);

    Process getProcess();
    
    String getProcessId();

    void setState(int state);

    int getState();
    
    void setWorkingMemory(InternalWorkingMemory workingMemory);
    
    WorkingMemory getWorkingMemory();

    Agenda getAgenda();
    
    void start();

}
