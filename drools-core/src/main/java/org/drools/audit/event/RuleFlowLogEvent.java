package org.drools.audit.event;

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

/**
 * A ruleflow event logged by the WorkingMemoryLogger.
 * It is a snapshot of the event as it was thrown by the working memory.
 * It contains the process name and id.
 * 
 * @author <a href="mailto:kris_verlaenen@hotmail.com">Kris Verlaenen </a>
 */
public class RuleFlowLogEvent extends LogEvent {

    private String processId;
    private String processName;

    /**
     * Create a new ruleflow log event.
     * 
     * @param type The type of event.  This can only be RULEFLOW_CREATED or RULEFLOW_COMPLETED.
     * @param processId The id of the process
     * @param processName The name of the process
     */
    public RuleFlowLogEvent(final int type,
                            final String processId,
                            final String processName) {
        super( type );
        this.processId = processId;
        this.processName = processName;
    }

    public String getProcessId() {
        return this.processId;
    }

    public String getProcessName() {
        return this.processName;
    }

    public String toString() {

        String msg = null;
        switch ( this.getType() ) {
            case RULEFLOW_CREATED :
                msg = "RULEFLOW STARTED";
                break;
            case RULEFLOW_COMPLETED :
                msg = "ACTIVATION CREATED";
                break;
        }
        return msg + " process:" + this.processName + "[id=" + this.processId + "]";
    }
}
