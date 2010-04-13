package org.drools.command.impl;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.command.Context;
import org.drools.command.ContextManager;
import org.drools.runtime.ExecutionResults;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.process.WorkItemManager;
import org.drools.runtime.rule.WorkingMemoryEntryPoint;

public class KnowledgeCommandContext
    implements
    Context {
    private Context                  context;
    private KnowledgeBuilder         kbuilder;
    private KnowledgeBase            kbase;
    private StatefulKnowledgeSession statefulKsession;
    private WorkingMemoryEntryPoint  workingMemoryEntryPoint;
    private ExecutionResults         kresults;

    public KnowledgeCommandContext(Context context,
                                   KnowledgeBuilder kbuilder,
                                   KnowledgeBase kbase,
                                   StatefulKnowledgeSession statefulKsession,
                                   ExecutionResults         kresults) {
        this.context = context;
        this.kbuilder = kbuilder;
        this.kbase = kbase;
        this.statefulKsession = statefulKsession;
        this.kresults = kresults;
    }
    public KnowledgeCommandContext(Context context,
                                   KnowledgeBuilder kbuilder,
                                   KnowledgeBase kbase,
                                   StatefulKnowledgeSession statefulKsession,
                                   WorkingMemoryEntryPoint  workingMemoryEntryPoint,
                                   ExecutionResults         kresults) {
        this(context, kbuilder, kbase, statefulKsession, kresults);
        this.workingMemoryEntryPoint = workingMemoryEntryPoint;
    }

    public KnowledgeBuilder getKnowledgeBuilder() {
        return kbuilder;
    }

    public KnowledgeBase getKnowledgeBase() {
        return this.kbase;
    }       

    public StatefulKnowledgeSession getStatefulKnowledgesession() {
        return statefulKsession;
    }
    
    public WorkItemManager getWorkItemManager() {
    	return statefulKsession.getWorkItemManager();
    }
    
    public ExecutionResults getExecutionResults() {
        return this.kresults;
    }

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

}
