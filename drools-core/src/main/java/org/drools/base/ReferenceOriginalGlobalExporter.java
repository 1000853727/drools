/**
 * 
 */
package org.drools.base;

import org.drools.WorkingMemory;
import org.drools.spi.GlobalExporter;
import org.drools.spi.GlobalResolver;

/**
 * This implementation does nothing other than pass by reference the original GlobalResolver as used in the StatelessSession.
 * Care should be taken if you use this strategy, as later executes may change those globals. The GlobalResolver of the StatelessSession
 * may also not be serialisable friendly.
 *
 */
public class ReferenceOriginalGlobalExporter implements GlobalExporter {       
    public GlobalResolver export(WorkingMemory workingMemory) {
        return workingMemory.getGlobalResolver();
    }
}