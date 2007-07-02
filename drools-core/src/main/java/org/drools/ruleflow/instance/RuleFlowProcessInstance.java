package org.drools.ruleflow.instance;

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

import java.util.Collection;

import org.drools.Agenda;
import org.drools.WorkingMemory;
import org.drools.common.InternalWorkingMemory;
import org.drools.ruleflow.common.instance.ProcessInstance;
import org.drools.ruleflow.core.Node;
import org.drools.ruleflow.core.RuleFlowProcess;

/**
 * A process instance for a RuleFlow process.
 * Contains a reference to all its node instances, and the agenda that
 * is controlling the RuleFlow process.
 * 
 * @author <a href="mailto:kris_verlaenen@hotmail.com">Kris Verlaenen</a>
 */
public interface RuleFlowProcessInstance
    extends
    ProcessInstance {

    RuleFlowProcess getRuleFlowProcess();

    void addNodeInstance(RuleFlowNodeInstance nodeInstance);

    void removeNodeInstance(RuleFlowNodeInstance nodeInstance);

    Collection getNodeInstances();

    RuleFlowNodeInstance getFirstNodeInstance(long nodeId);

    void setWorkingMemory(InternalWorkingMemory workingMemory);
    
    WorkingMemory getWorkingMemory();

    Agenda getAgenda();

    RuleFlowNodeInstance getNodeInstance(Node node);

    void start();

}