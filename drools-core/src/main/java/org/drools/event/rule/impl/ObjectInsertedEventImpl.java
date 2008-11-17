package org.drools.event.rule.impl;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.drools.common.InternalWorkingMemory;
import org.drools.event.rule.ObjectInsertedEvent;
import org.drools.impl.StatefulKnowledgeSessionImpl;
import org.drools.reteoo.ReteooStatefulSession;
import org.drools.reteoo.ReteooWorkingMemory;
import org.drools.FactHandle;
import org.drools.runtime.rule.WorkingMemory;

public class ObjectInsertedEventImpl extends WorkingMemoryEventImpl
    implements
    ObjectInsertedEvent {
    private FactHandle  factHandle;
    private Object      object;

    public ObjectInsertedEventImpl(org.drools.event.ObjectInsertedEvent event) {
        super( ((InternalWorkingMemory) event.getWorkingMemory() ).getKnowledgeRuntime(), event.getPropagationContext() );
        factHandle = event.getFactHandle();
        object = event.getObject();
    }
    
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal( out );
        out.writeObject( factHandle );
        out.writeObject( object );
    }
    
    public void readExternal(ObjectInput in) throws IOException,
                                            ClassNotFoundException {
        super.readExternal( in );
        this.factHandle = ( FactHandle ) in.readObject();
        this.object = in.readObject();
    }

    public FactHandle getFactHandle() {
        return this.factHandle;
    }

    public Object getObject() {
        return this.object;
    }
}
