package org.drools.agent;

import java.util.ArrayList;
import java.util.List;

public class MockListener
        implements
        AgentEventListener {

    public List exceptions = new ArrayList();

    public void debug(String message) {


    }

    public void exception(String message, Throwable e) {
        exceptions.add(e);
    }

    public void exception(Throwable e) {
        exceptions.add(e);

    }

    public void info(String message) {


    }

    public void setAgentName(String name) {


    }

    public void warning(String message) {


    }

    public void debug(String message,
                      Object object) {
        // TODO Auto-generated method stub

    }

    public void info(String message,
                     Object object) {
        // TODO Auto-generated method stub

    }

    public void warning(String message,
                        Object object) {
        // TODO Auto-generated method stub

    }

}
