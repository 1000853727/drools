package org.drools.event.knowlegebase.impl;

import org.drools.KnowledgeBase;
import org.drools.event.knowledgebase.AfterFunctionRemovedEvent;

public class AfterFunctionRemovedEventImpl extends KnowledgeBaseEventImpl implements AfterFunctionRemovedEvent {
    String function;
    
    public AfterFunctionRemovedEventImpl(KnowledgeBase knowledgeBase, String function) {
        super( knowledgeBase );
        this.function = function;
    }

    public String getFunction() {
        return this.function;
    }
}
