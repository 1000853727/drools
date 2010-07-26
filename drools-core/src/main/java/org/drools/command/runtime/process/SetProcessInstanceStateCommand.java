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

package org.drools.command.runtime.process;

import org.drools.command.Context;
import org.drools.command.impl.GenericCommand;
import org.drools.command.impl.KnowledgeCommandContext;
import org.drools.process.instance.ProcessInstance;
import org.drools.runtime.StatefulKnowledgeSession;

public class SetProcessInstanceStateCommand implements GenericCommand<Object> {
	
	private Long processInstanceId;
	private int state;
	
	public Long getProcessInstanceId() {
		return processInstanceId;
	}
	
	public void setProcessInstanceId(Long processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

    public Object execute(Context context) {
        StatefulKnowledgeSession ksession = ((KnowledgeCommandContext) context).getStatefulKnowledgesession();
		if (processInstanceId == null) {
			return null;
		}
		((ProcessInstance) ksession.getProcessInstance(processInstanceId)).setState(state);
		return null;
	}

	public String toString() {
		return "session.getProcessInstance(" + processInstanceId + ");";
	}

}
