package org.drools.spi;

import org.drools.WorkingMemory;

public interface ReturnValueEvaluator {
    
    public Object evaluate(WorkingMemory workingMemory, ProcessContext processContext) throws Exception;
}
