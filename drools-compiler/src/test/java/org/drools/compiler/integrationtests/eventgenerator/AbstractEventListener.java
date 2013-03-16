package org.drools.compiler.integrationtests.eventgenerator;

import org.drools.core.WorkingMemory;


public abstract class AbstractEventListener {

    WorkingMemory wm;

    /**
     * @param wm
     */
    public AbstractEventListener(WorkingMemory wm) {
        this.wm = wm;
    }

    public void addEventToWM (Event ev){
        wm.insert(ev);
        wm.fireAllRules();
    }

    // send generated event and execute corresponding actions
    public abstract void generatedEventSent(Event e);

}
