package org.drools.event.rule.impl;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.drools.definition.rule.Rule;
import org.drools.impl.SerializedRule;
import org.drools.runtime.rule.FactHandle;
import org.drools.runtime.rule.PropagationContext;

public class SerializablePropagationContext
    implements
    PropagationContext,
    Externalizable {
    private FactHandle factHandle;
    private long propgationNumber;
    private Rule rule;
    private int type;
    
    public SerializablePropagationContext() {

    }
    
    public SerializablePropagationContext(PropagationContext propagationContext) {
        this.factHandle = propagationContext.getFactHandle();
        this.propgationNumber = propagationContext.getPropagationNumber();
        this.rule = propagationContext.getRule();
        this.type = propagationContext.getType();
    }    

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject( this.factHandle );
        out.writeLong( this.propgationNumber );
        new SerializedRule( rule ).writeExternal( out );
    }
    
    public void readExternal(ObjectInput in) throws IOException,
                                            ClassNotFoundException {
        this.factHandle = ( FactHandle ) in.readObject();
        this.propgationNumber = in.readLong();
        this.rule = new SerializedRule();
        ((SerializedRule)this.rule).readExternal( in );
    }

    public FactHandle getFactHandle() {
        return this.factHandle;
    }

    public long getPropagationNumber() {
        return this.propgationNumber;
    }

    public Rule getRule() {
        return this.rule;
    }

    public int getType() {
        return this.type;
    }
}
