package org.drools.common;

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

import java.util.Map;

import org.drools.FactException;
import org.drools.FactHandle;
import org.drools.RuleBase;
import org.drools.RuleBaseConfiguration;
import org.drools.StatefulSession;
import org.drools.objenesis.Objenesis;
import org.drools.reteoo.Rete;
import org.drools.reteoo.ReteooWorkingMemory;
import org.drools.rule.CompositePackageClassLoader;
import org.drools.rule.MapBackedClassLoader;
import org.drools.rule.Package;
import org.drools.ruleflow.common.core.Process;
import org.drools.spi.FactHandleFactory;
import org.drools.spi.PropagationContext;

public interface InternalRuleBase
    extends
    RuleBase {

    /**
     * @return the id
     */
    public String getId();
    
    public int nextWorkingMemoryCounter();

    public FactHandleFactory newFactHandleFactory();

    public Map getGlobals();
    
    public Map getAgendaGroupRuleTotals();
    
    public RuleBaseConfiguration getConfiguration();
    
    public Package getPackage(String name);
    
    public Map getPackagesMap();

    void disposeStatefulSession(StatefulSession statefulSession);
    
    void executeQueuedActions();

    /**
     * Assert a fact object.
     * 
     * @param handle
     *            The handle.
     * @param object
     *            The fact.
     * @param workingMemory
     *            The working-memory.
     * 
     * @throws FactException
     *             If an error occurs while performing the assertion.
     */
    public void assertObject(FactHandle handle,
                             Object object,
                             PropagationContext context,
                             InternalWorkingMemory workingMemory) throws FactException;

    /**
     * Retract a fact object.
     * 
     * @param handle
     *            The handle.
     * @param workingMemory
     *            The working-memory.
     * 
     * @throws FactException
     *             If an error occurs while performing the retraction.
     */
    public void retractObject(FactHandle handle,
                              PropagationContext context,
                              ReteooWorkingMemory workingMemory) throws FactException;
    
    public void addClass(String className, byte[] bytes);
    
    public CompositePackageClassLoader getCompositePackageClassLoader();
    
    public MapBackedClassLoader getMapBackedClassLoader();
    
    public Rete getRete();
    
    public InternalWorkingMemory[] getWorkingMemories();
    
    public Process getProcess(String id);

	public Objenesis getObjenesis();
}
