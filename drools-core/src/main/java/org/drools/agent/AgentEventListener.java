package org.drools.agent;

/**
 * This interface is used to provide callback style logging for the agents
 * async events.
 * 
 * @author Michael Neale
 */
public interface AgentEventListener {

    /**
     * This sets the name for logging.
     */
    public void setAgentName(String name);
    
    /**
     * For general info messages.
     */
    public void info(String message);
    
    /**
     * For a warning (useful when tracking down problems).
     */
    public void warning(String message);
    
    /**
     * An exception occurred.
     */
    public void exception(Exception e);
    
    
    /**
     * These should not be logged, just shown if needed.
     */
    public void debug(String message);
}
