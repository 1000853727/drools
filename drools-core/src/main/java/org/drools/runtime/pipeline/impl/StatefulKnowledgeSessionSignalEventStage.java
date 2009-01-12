package org.drools.runtime.pipeline.impl;

import org.drools.runtime.pipeline.KnowledgeRuntimeCommand;
import org.drools.runtime.pipeline.PipelineContext;
import org.drools.runtime.pipeline.Receiver;
import org.drools.runtime.pipeline.StatefulKnowledgeSessionPipelineContext;

public class StatefulKnowledgeSessionSignalEventStage extends BaseEmitter
    implements
    KnowledgeRuntimeCommand {
    private long   id;
    private String eventType;

    public StatefulKnowledgeSessionSignalEventStage(String eventType) {
        this.eventType = eventType;
        this.id = -1;
    }

    public StatefulKnowledgeSessionSignalEventStage(String eventType,
                                                    long id) {
        this.eventType = eventType;
        this.id = id;
    }

    public void receive(Object object,
                        PipelineContext context) {
        StatefulKnowledgeSessionPipelineContext kContext = (StatefulKnowledgeSessionPipelineContext) context;

        if ( this.id != -1 ) {
            kContext.getStatefulKnowledgeSession().getProcessInstance( this.id ).signalEvent( this.eventType,
                                                                                              object );
        } else {
            kContext.getStatefulKnowledgeSession().signalEvent( this.eventType,
                                                                object );
        }

        emit( object,
              kContext );
    }

}
