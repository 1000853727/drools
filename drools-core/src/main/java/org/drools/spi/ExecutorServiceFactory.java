package org.drools.spi;

import org.drools.util.ClassUtils;
import org.drools.concurrent.ExecutorService;

/** 
 * Factory class that will return a ExecutorService based on the provided string className
 *
 */
public class ExecutorServiceFactory {
    
    public static ExecutorService createExecutorService(String className) {        

        try {
            ExecutorService service = ( ExecutorService ) ClassUtils.instantiateObject( className );
            
            return service;            
        } catch ( Throwable e ) {
            throw new RuntimeException("Unable to instantiate ExecutorService '" + className + "'", e );
        }        
    }

}
