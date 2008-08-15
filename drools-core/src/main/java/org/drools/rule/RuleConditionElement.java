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
 */

package org.drools.rule;

import java.io.Externalizable;
import java.util.List;
import java.util.Map;

import org.drools.spi.RuleComponent;

/**
 * @author etirelli
 *
 */
public interface RuleConditionElement
    extends
    RuleComponent,
    Externalizable,
    Cloneable {

    /**
     * Returns a Map of declarations that are
     * visible inside this conditional element
     * 
     * @return
     */
    public Map getInnerDeclarations();

    /**
     * Returns a Map of declarations that are visible
     * outside this conditional element. 
     * 
     * @return
     */
    public Map getOuterDeclarations();

    /**
     * Resolves the given identifier in the current scope and
     * returns the Declaration object for the declaration.
     * Returns null if identifier can not be resolved.
     *  
     * @param identifier
     * @return
     */
    public Declaration resolveDeclaration(String identifier);

    /**
     * Returns a clone from itself
     * @return
     */
    public Object clone();
    
    /**
     * Returs a list of RuleConditionElement's that are nested
     * inside the current element
     * @return
     */
    public List<RuleConditionElement> getNestedElements();
    
    /**
     * Returns true in case this RuleConditionElement delimits
     * a pattern visibility scope.
     * 
     * For instance, AND CE is not a scope delimiter, while 
     * NOT CE is a scope delimiter
     * @return
     */
    public boolean isPatternScopeDelimiter();

}
