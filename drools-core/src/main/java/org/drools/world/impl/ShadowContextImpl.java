package org.drools.world.impl;

import org.drools.command.impl.ContextImpl;
import org.kie.command.Context;
import org.kie.command.ShadowContext;
import org.kie.command.ShadowWorld;

public class ShadowContextImpl extends ContextImpl implements ShadowContext {
    
    private Context actualCtx;

    public ShadowContextImpl(String name,
                             Context actualCtx,
                             ShadowWorld manager) {
        super( name,
               manager );
        this.actualCtx = actualCtx;
    }

    public ShadowWorld getContextManager() {
        return (ShadowWorld) super.getContextManager();
    }

    public void set(String identifier,
                    Object value,
                    boolean shadow) {
        if ( !shadow ) {
            actualCtx.set( identifier, value );
        } else {
            super.set( identifier,value );
        }
    }
    
    

}
