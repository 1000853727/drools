package org.drools.event;

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

import java.util.EventListener;

import org.drools.WorkingMemory;

/**
 * @author <a href="mailto:kris_verlaenen@hotmail.com">Kris Verlaenen</a>
 */
public interface RuleFlowEventListener
    extends
    EventListener {

    void beforeRuleFlowStarted(RuleFlowStartedEvent event,
                               WorkingMemory workingMemory);

    void afterRuleFlowStarted(RuleFlowStartedEvent event,
                              WorkingMemory workingMemory);

    void beforeRuleFlowCompleted(RuleFlowCompletedEvent event,
                                 WorkingMemory workingMemory);

    void afterRuleFlowCompleted(RuleFlowCompletedEvent event,
                                WorkingMemory workingMemory);

    void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event,
                                      WorkingMemory workingMemory);

    void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event,
                                     WorkingMemory workingMemory);
    
    void beforeRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event,
                                        WorkingMemory workingMemory);

    void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event,
                                       WorkingMemory workingMemory);

    void beforeRuleFlowNodeTriggered(RuleFlowNodeTriggeredEvent event,
                                     WorkingMemory workingMemory);

    void afterRuleFlowNodeTriggered(RuleFlowNodeTriggeredEvent event,
                                    WorkingMemory workingMemory);

    void beforeRuleFlowNodeLeft(RuleFlowNodeTriggeredEvent event,
            			        WorkingMemory workingMemory);

    void afterRuleFlowNodeLeft(RuleFlowNodeTriggeredEvent event,
    						   WorkingMemory workingMemory);

}