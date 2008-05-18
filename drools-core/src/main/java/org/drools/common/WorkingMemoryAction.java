/**
 *
 */
package org.drools.common;

import java.io.Externalizable;
import java.io.IOException;

import org.drools.marshalling.MarshallerWriteContext;

public interface WorkingMemoryAction extends Externalizable {
    public static final int WorkingMemoryReteAssertAction = 1;
    public static final int DeactivateCallback = 2;
    public static final int PropagateAction = 3;
    public static final int LogicalRetractCallback = 4;
    
    
    public void execute(InternalWorkingMemory workingMemory);
    
    public void write(MarshallerWriteContext context) throws IOException;
}