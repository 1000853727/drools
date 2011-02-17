/*
 * Copyright 2006 JBoss Inc
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
 *
 * Created on Jun 18, 2007
 */
package org.drools.rule.builder.dialect.mvel;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.drools.compiler.AnalysisResult;
import org.drools.compiler.BoundIdentifiers;

/**
 * An analysis result implementation for the MVEL dialect
 * 
 * @author etirelli
 */
public class MVELAnalysisResult implements AnalysisResult {
    
    private BoundIdentifiers boundIdentifiers = null;
    private Set identifiers = Collections.EMPTY_SET;
    private Set localVariables = Collections.EMPTY_SET;
    private Set notBoundedIdentifiers = Collections.EMPTY_SET;
    
    private Map mvelVariables;
    private Map mvelInputs;
    
    private Class returnType;
    
    public BoundIdentifiers getBoundIdentifiers() {
        return boundIdentifiers;
    }
    public void setBoundIdentifiers(BoundIdentifiers boundIdentifiers) {
        this.boundIdentifiers = boundIdentifiers;
        
    }
    public Set getIdentifiers() {
        return identifiers;
    }
    public void setIdentifiers(Set identifiers) {
        this.identifiers = identifiers;
    }
    public Set getLocalVariables() {
        return this.localVariables;
    }
    public void setLocalVariables(Set localVariables) {
        this.localVariables = localVariables;
    }
    public Set getNotBoundedIdentifiers() {
        return notBoundedIdentifiers;
    }
    public void setNotBoundedIdentifiers(Set notBoundedIdentifiers) {
        this.notBoundedIdentifiers = notBoundedIdentifiers;
    }

    public Map getMvelVariables() {
        return mvelVariables;
    }
    
    public void setMvelVariables(Map mvelVariables) {
        this.mvelVariables = mvelVariables;
    }
    public Class getReturnType() {
        return returnType;
    }
    public void setReturnType(Class returnType) {
        this.returnType = returnType;
    }
    @Override
    public String toString() {
        return "MVELAnalysisResult [boundIdentifiers=" + boundIdentifiers + 
               ",\n identifiers=" + identifiers + 
               ",\n localVariables=" + localVariables + 
               ",\n notBoundedIdentifiers=" + notBoundedIdentifiers + 
               ",\n mvelVariables=" + mvelVariables+ 
               ",\n mvelInputs=" + mvelInputs + 
               ",\n returnType=" + returnType + "]";
    }

    
    
    
    
}
