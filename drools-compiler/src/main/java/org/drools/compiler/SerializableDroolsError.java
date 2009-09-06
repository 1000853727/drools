package org.drools.compiler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/*
 * Copyright 2005 JBoss Inc
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class SerializableDroolsError extends DroolsError implements Externalizable {
    private String message;
    private int[] errorLines;
    private String errorClassName;
    
    public SerializableDroolsError() {
        
    }
    
    public SerializableDroolsError(String message, int[] errorLines, String errorClassName) {
        this.message = message;
        this.errorLines = errorLines;
        this.errorClassName = errorClassName;
    }
    
    /**
     * Classes that extend this must provide a printable message,
     * which summarises the error.
     */
    public String getMessage() {
        return this.message;
    }
    
    /**
     * Returns the lines of the error in the source file
     * @return
     */
    public int[] getErrorLines() {
        return this.errorLines;
    }
    
    public String toString() {
        return this.errorClassName + ": " + getMessage();
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject( this.message );
        out.writeObject( this.errorLines );
        out.writeObject( this.errorClassName );
    }
    
    public void readExternal(ObjectInput in) throws IOException,
                                            ClassNotFoundException {
        this.message = ( String ) in.readObject();
        this.errorLines = ( int[] ) in.readObject();
        this.errorClassName = ( String ) in.readObject();
    }
    
}