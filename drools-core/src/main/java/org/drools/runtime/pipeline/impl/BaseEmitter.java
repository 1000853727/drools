package org.drools.runtime.pipeline.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.drools.runtime.pipeline.Emitter;
import org.drools.runtime.pipeline.PipelineContext;
import org.drools.runtime.pipeline.Receiver;

public class BaseEmitter extends BaseStage
    implements
    Emitter {
    protected List<Receiver> receivers;

    public BaseEmitter() {
        this.receivers = new CopyOnWriteArrayList<Receiver>();
    }

    public void addReceiver(Receiver receiver) {
        this.receivers.add( receiver );
    }

    public void removeReceiver(Receiver receiver) {
        this.receivers.remove( receiver );
    }

    public Collection<Receiver> getReceivers() {
        return Collections.unmodifiableCollection( this.receivers );
    }

    protected void emit(Object object,
                        PipelineContext context) {
        for ( Receiver receiver : this.receivers ) {
            receiver.receive( object,
                             context );
        }
    }

}
