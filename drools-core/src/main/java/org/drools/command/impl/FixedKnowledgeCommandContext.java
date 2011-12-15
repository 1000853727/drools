/*
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

package org.drools.command.impl;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.command.Context;
import org.drools.command.ContextManager;
import org.drools.runtime.ExecutionResults;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.process.WorkItemManager;
import org.drools.runtime.rule.WorkingMemoryEntryPoint;

public class FixedKnowledgeCommandContext
    implements
    KnowledgeCommandContext {
    
    private Context                  context;
    private KnowledgeBuilder         kbuilder;
    private KnowledgeBase            kbase;
    private StatefulKnowledgeSession statefulKsession;
    private WorkingMemoryEntryPoint  workingMemoryEntryPoint;
    private ExecutionResults         kresults;

    public FixedKnowledgeCommandContext(Context context,
                                        KnowledgeBuilder kbuilder,
                                        KnowledgeBase kbase,
                                        StatefulKnowledgeSession statefulKsession,
                                        ExecutionResults kresults) {
        this.context = context;
        this.kbuilder = kbuilder;
        this.kbase = kbase;
        this.statefulKsession = statefulKsession;
        this.kresults = kresults;
    }

    public FixedKnowledgeCommandContext(Context context,
                                        KnowledgeBuilder kbuilder,
                                        KnowledgeBase kbase,
                                        StatefulKnowledgeSession statefulKsession,
                                        WorkingMemoryEntryPoint workingMemoryEntryPoint,
                                        ExecutionResults kresults) {
        this( context,
              kbuilder,
              kbase,
              statefulKsession,
              kresults );
        this.workingMemoryEntryPoint = workingMemoryEntryPoint;
    }

    /* (non-Javadoc)
     * @see org.drools.command.impl.KnowledgeCommandContext#getKnowledgeBuilder()
     */
    public KnowledgeBuilder getKnowledgeBuilder() {
        return kbuilder;
    }

    /* (non-Javadoc)
     * @see org.drools.command.impl.KnowledgeCommandContext#getKnowledgeBase()
     */
    public KnowledgeBase getKnowledgeBase() {
        return this.kbase;
    }

    /* (non-Javadoc)
     * @see org.drools.command.impl.KnowledgeCommandContext#getStatefulKnowledgesession()
     */
    public StatefulKnowledgeSession getStatefulKnowledgesession() {
        return statefulKsession;
    }

    /* (non-Javadoc)
     * @see org.drools.command.impl.KnowledgeCommandContext#getWorkItemManager()
     */
    public WorkItemManager getWorkItemManager() {
        return statefulKsession.getWorkItemManager();
    }

    /* (non-Javadoc)
     * @see org.drools.command.impl.KnowledgeCommandContext#getExecutionResults()
     */
    public ExecutionResults getExecutionResults() {
        return this.kresults;
    }

    /* (non-Javadoc)
     * @see org.drools.command.impl.KnowledgeCommandContext#getWorkingMemoryEntryPoint()
     */
    public WorkingMemoryEntryPoint getWorkingMemoryEntryPoint() {
        return workingMemoryEntryPoint;
    }

    public void setWorkingMemoryEntryPoint(WorkingMemoryEntryPoint workingMemoryEntryPoint) {
        this.workingMemoryEntryPoint = workingMemoryEntryPoint;
    }

    public ContextManager getContextManager() {
        return context.getContextManager();
    }

    public String getName() {
        return context.getName();
    }

    public Object get(String identifier) {
        return context.get( identifier );
    }

    public void set(String identifier,
                    Object value) {
        context.set( identifier,
                     value );
    }

    public void remove(String name) {
        context.remove( name );
    }

}
