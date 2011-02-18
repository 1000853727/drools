/**
 * Copyright 2010 JBoss Inc
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

package org.drools.clips.functions;

import org.drools.clips.Appendable;
import org.drools.clips.Function;
import org.drools.clips.FunctionHandlers;
import org.drools.clips.LispAtom;
import org.drools.clips.LispForm;
import org.drools.clips.SExpression;

public class PrintoutFunction implements Function {
    private static final String name = "printout";
    
    private static final int route = 1;

    public String getName() {
        return name;
    }
    
    public void dump(LispForm lispForm, Appendable appendable) {
        SExpression[] sExpressions = lispForm.getSExpressions();
        appendable.append( "printrouters.get(" + ( ( LispAtom ) lispForm.getSExpressions()[route]).getValue()+ ").print(" );        
        
        for ( int i = 2, length = sExpressions.length; i < length; i++) {            
            FunctionHandlers.dump( sExpressions[i], appendable );         
            
            if ( i != length -1 ) { 
                appendable.append( "+" );
            }
        }        
        appendable.append( ")" );
    }
}
