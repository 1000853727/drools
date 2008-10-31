/**
 * 
 */
package org.drools.concurrent;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.drools.WorkingMemory;

/**
 * The CommandExecutor is a Producer/Consumer style classes that provides a queue of Commands
 * in a LinkedBlockingQueue. This the run() method loops for continously until shutdown() is 
 * called.
 *
 */
public class CommandExecutor implements Runnable, Externalizable {

    private static final long serialVersionUID = 5924295088331461167L;
    
    private WorkingMemory workingMemory;
    private BlockingQueue queue;
    
    private volatile boolean run;
    

    public CommandExecutor() {
    }

    public CommandExecutor(WorkingMemory workingMemory) {
        this.workingMemory = workingMemory;            
        this.queue = new LinkedBlockingQueue();
    }        
    
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        workingMemory   = (WorkingMemory)in.readObject();
        queue           = (BlockingQueue)in.readObject();
        run             = in.readBoolean();
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(workingMemory);
        out.writeObject(queue);
        out.writeBoolean(run);
    }

    /**
     * Allows the looping run() method to execute. 
     *
     */
    public void shutdown() {
        this.run = false;
    }        
    
    /**
     * Submit a Command for execution
     * 
     * @param command
     * 
     * @return
     *     return the Future
     */
    public Future submit(Command command) {
        this.queue.offer( command );
        // we know our commands also implement Future
        return (Future) command;
    }

    public void run() {
        this.run = true;
        while (this.run) {
            try {
                Command executor = ( Command ) this.queue.take();
                executor.execute( this.workingMemory );
            } catch(InterruptedException e) {
                return;
            }
        }
    }        
}