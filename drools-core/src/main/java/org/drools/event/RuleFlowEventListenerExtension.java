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

package org.drools.event;

import org.drools.WorkingMemory;
import org.drools.event.process.ProcessEventListener;

/**
 *
 * @author salaboy
 */
public interface RuleFlowEventListenerExtension extends RuleFlowEventListener{
    /**
     * this will be triggered before a variable change
     * @param event
     * @param workingMemory
     */
    void beforeVariableChange(RuleFlowVariableChangeEvent event,
                                WorkingMemory workingMemory);

    /**
     * this will be triggered after a variable was changed
     * @param event
     * @param workingMemory
     */
    void afterVariableChange(RuleFlowVariableChangeEvent event,
                                WorkingMemory workingMemory);
}
