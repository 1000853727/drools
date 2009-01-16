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

package org.drools;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import org.drools.common.AgendaGroupFactory;
import org.drools.common.ArrayAgendaGroupFactory;
import org.drools.common.PriorityQueueAgendaGroupFactory;
import org.drools.definition.process.Process;
import org.drools.process.core.Context;
import org.drools.process.core.ParameterDefinition;
import org.drools.process.core.WorkDefinition;
import org.drools.process.core.datatype.DataType;
import org.drools.process.core.impl.ParameterDefinitionImpl;
import org.drools.process.core.impl.WorkDefinitionExtensionImpl;
import org.drools.process.instance.ProcessInstanceFactory;
import org.drools.process.instance.ProcessInstanceFactoryRegistry;
import org.drools.process.instance.ProcessInstanceManagerFactory;
import org.drools.process.instance.WorkItemManagerFactory;
import org.drools.process.instance.event.SignalManagerFactory;
import org.drools.process.instance.impl.ContextInstanceFactory;
import org.drools.process.instance.impl.ContextInstanceFactoryRegistry;
import org.drools.runtime.process.WorkItemHandler;
import org.drools.spi.ConflictResolver;
import org.drools.util.ChainedProperties;
import org.drools.util.ConfFileUtils;
import org.drools.util.StringUtils;
import org.drools.workflow.core.Node;
import org.drools.workflow.instance.impl.NodeInstanceFactory;
import org.drools.workflow.instance.impl.NodeInstanceFactoryRegistry;
import org.mvel2.MVEL;

/**
 * RuleBaseConfiguration
 *
 * A class to store RuleBase related configuration. It must be used at rule base instantiation time
 * or not used at all.
 * This class will automatically load default values from system properties, so if you want to set
 * a default configuration value for all your new rule bases, you can simply set the property as
 * a System property.
 *
 * After RuleBase is created, it makes the configuration immutable and there is no way to make it
 * mutable again. This is to avoid inconsistent behavior inside rulebase.
 *
 * NOTE: This API is under review and may change in the future.
 */

/**
 * drools.maintainTms = <true|false>
 * drools.sequential = <true|false>
 * drools.sequential.agenda = <sequential|dynamic>
 * drools.removeIdentities = <true|false>
 * drools.shareAlphaNodes  = <true|false>
 * drools.shareBetaNodes = <true|false>
 * drools.alphaMemory <true/false>
 * drools.alphaNodeHashingThreshold = <1...n>
 * drools.compositeKeyDepth  =<1..3>
 * drools.indexLeftBetaMemory = <true/false>
 * drools.indexRightBetaMemory = <true/false>
 * drools.assertBehaviour = <identity|equality>
 * drools.logicalOverride = <discard|preserve>
 * drools.executorService = <qualified class name>
 * drools.conflictResolver = <qualified class name>
 * drools.consequenceExceptionHandler = <qualified class name>
 * drools.ruleBaseUpdateHandler = <qualified class name>
 * drools.sessionClock = <qualified class name>
 * 
 */
public class RuleBaseConfiguration
    implements
    KnowledgeBaseConfiguration,
    Externalizable {
    private static final long              serialVersionUID = 400L;

    private ChainedProperties              chainedProperties;

    private boolean                        immutable;

    private boolean                        sequential;
    private SequentialAgenda               sequentialAgenda;

    private boolean                        maintainTms;
    private boolean                        removeIdentities;
    private boolean                        shareAlphaNodes;
    private boolean                        shareBetaNodes;
    private int                            alphaNodeHashingThreshold;
    private int                            compositeKeyDepth;
    private boolean                        indexLeftBetaMemory;
    private boolean                        indexRightBetaMemory;
    private AssertBehaviour                assertBehaviour;
    private LogicalOverride                logicalOverride;
    private String                         executorService;
    private String                         consequenceExceptionHandler;
    private String                         ruleBaseUpdateHandler;

    private EventProcessingMode            eventProcessingMode;

    // if "true", rulebase builder will try to split 
    // the rulebase into multiple partitions that can be evaluated
    // in parallel by using multiple internal threads
    private boolean                        multithread;
    private int                            maxThreads;

    private ConflictResolver               conflictResolver;

    private ContextInstanceFactoryRegistry processContextInstanceFactoryRegistry;
    private Map<String, WorkDefinition>    workDefinitions;
    private boolean                        advancedProcessRuleIntegration;

    private ProcessInstanceFactoryRegistry processInstanceFactoryRegistry;
    private NodeInstanceFactoryRegistry    processNodeInstanceFactoryRegistry;

    private transient ClassLoader          classLoader;

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject( chainedProperties );
        out.writeBoolean( immutable );
        out.writeBoolean( sequential );
        out.writeObject( sequentialAgenda );
        out.writeBoolean( maintainTms );
        out.writeBoolean( removeIdentities );
        out.writeBoolean( shareAlphaNodes );
        out.writeBoolean( shareBetaNodes );
        out.writeInt( alphaNodeHashingThreshold );
        out.writeInt( compositeKeyDepth );
        out.writeBoolean( indexLeftBetaMemory );
        out.writeBoolean( indexRightBetaMemory );
        out.writeObject( assertBehaviour );
        out.writeObject( logicalOverride );
        out.writeObject( executorService );
        out.writeObject( consequenceExceptionHandler );
        out.writeObject( ruleBaseUpdateHandler );
        out.writeObject( conflictResolver );
        out.writeObject( processNodeInstanceFactoryRegistry );
        out.writeBoolean( advancedProcessRuleIntegration );
        out.writeBoolean( multithread );
        out.writeInt( maxThreads );
        out.writeObject( eventProcessingMode );
    }

    public void readExternal(ObjectInput in) throws IOException,
                                            ClassNotFoundException {
        chainedProperties = (ChainedProperties) in.readObject();
        immutable = in.readBoolean();
        sequential = in.readBoolean();
        sequentialAgenda = (SequentialAgenda) in.readObject();
        maintainTms = in.readBoolean();
        removeIdentities = in.readBoolean();
        shareAlphaNodes = in.readBoolean();
        shareBetaNodes = in.readBoolean();
        alphaNodeHashingThreshold = in.readInt();
        compositeKeyDepth = in.readInt();
        indexLeftBetaMemory = in.readBoolean();
        indexRightBetaMemory = in.readBoolean();
        assertBehaviour = (AssertBehaviour) in.readObject();
        logicalOverride = (LogicalOverride) in.readObject();
        executorService = (String) in.readObject();
        consequenceExceptionHandler = (String) in.readObject();
        ruleBaseUpdateHandler = (String) in.readObject();
        conflictResolver = (ConflictResolver) in.readObject();
        processNodeInstanceFactoryRegistry = (NodeInstanceFactoryRegistry) in.readObject();
        advancedProcessRuleIntegration = in.readBoolean();
        multithread = in.readBoolean();
        maxThreads = in.readInt();
        eventProcessingMode = (EventProcessingMode) in.readObject();
    }

    /**
     * Creates a new rulebase configuration using the provided properties
     * as configuration options. Also, if a Thread.currentThread().getContextClassLoader()
     * returns a non-null class loader, it will be used as the parent classloader
     * for this rulebase class loaders, otherwise, the RuleBaseConfiguration.class.getClassLoader()
     * class loader will be used.
     *
     * @param properties
     */
    public RuleBaseConfiguration(Properties properties) {
        init( null,
              properties );
    }

    /**
     * Creates a new rulebase with a default parent class loader set according
     * to the following algorithm:
     *
     * If a Thread.currentThread().getContextClassLoader() returns a non-null class loader,
     * it will be used as the parent class loader for this rulebase class loaders, otherwise,
     * the RuleBaseConfiguration.class.getClassLoader() class loader will be used.
     *
     * @param properties
     */
    public RuleBaseConfiguration() {
        init( null,
              null );
    }

    /**
     * A constructor that sets the parent classloader to be used
     * while dealing with this rule base
     *
     * @param classLoader
     */
    public RuleBaseConfiguration(ClassLoader classLoader) {
        init( classLoader,
              null );
    }


    public void setProperty(String name,
                            String value) {
        name = name.trim();
        if ( StringUtils.isEmpty( name ) ) {
            return;
        }

        if ( name.equals( "drools.sequential.agenda" ) ) {
            setSequentialAgenda( SequentialAgenda.determineSequentialAgenda( StringUtils.isEmpty( value ) ? "sequential" : value ) );
        } else if ( name.equals( "drools.sequential" ) ) {
            setSequential( StringUtils.isEmpty( value ) ? false : Boolean.valueOf( value ) );
        } else if ( name.equals( "drools.maintainTms" ) ) {
            setMaintainTms( StringUtils.isEmpty( value ) ? false : Boolean.valueOf( value ) );
        } else if ( name.equals( "drools.removeIdentities" ) ) {
            setRemoveIdentities( StringUtils.isEmpty( value ) ? false : Boolean.valueOf( value ) );
        } else if ( name.equals( "drools.shareAlphaNodes" ) ) {
            setShareAlphaNodes( StringUtils.isEmpty( value ) ? false : Boolean.valueOf( value ) );
        } else if ( name.equals( "drools.shareBetaNodes" ) ) {
            setShareBetaNodes( StringUtils.isEmpty( value ) ? false : Boolean.valueOf( value ) );
        } else if ( name.equals( "drools.alphaNodeHashingThreshold" ) ) {
            setAlphaNodeHashingThreshold( StringUtils.isEmpty( value ) ? 3 : Integer.parseInt( value ) );
        } else if ( name.equals( "drools.compositeKeyDepth" ) ) {
            setCompositeKeyDepth( StringUtils.isEmpty( value ) ? 3 : Integer.parseInt( value ) );
        } else if ( name.equals( "drools.indexLeftBetaMemory" ) ) {
            setIndexLeftBetaMemory( StringUtils.isEmpty( value ) ? true : Boolean.valueOf( value ) );
        } else if ( name.equals( "drools.indexRightBetaMemory" ) ) {
            setIndexRightBetaMemory( StringUtils.isEmpty( value ) ? true : Boolean.valueOf( value ) );
        } else if ( name.equals( "drools.assertBehaviour" ) ) {
            setAssertBehaviour( AssertBehaviour.determineAssertBehaviour( StringUtils.isEmpty( value ) ? "identity" : value ) );
        } else if ( name.equals( "drools.logicalOverride" ) ) {
            setLogicalOverride( LogicalOverride.determineLogicalOverride( StringUtils.isEmpty( value ) ? "discard" : value ) );
        } else if ( name.equals( "drools.executorService" ) ) {
            setExecutorService( StringUtils.isEmpty( value ) ? "org.drools.concurrent.DefaultExecutorService" : value );
        } else if ( name.equals( "drools.consequenceExceptionHandler" ) ) {
            setConsequenceExceptionHandler( StringUtils.isEmpty( value ) ? "org.drools.runtime.rule.impl.DefaultConsequenceExceptionHandler" : value );
        } else if ( name.equals( "drools.ruleBaseUpdateHandler" ) ) {
            setRuleBaseUpdateHandler( StringUtils.isEmpty( value ) ? "org.drools.base.FireAllRulesRuleBaseUpdateListener" : value );
        } else if ( name.equals( "drools.conflictResolver" ) ) {
            setConflictResolver( RuleBaseConfiguration.determineConflictResolver( StringUtils.isEmpty( value ) ? "org.drools.conflict.DepthConflictResolver" : value ) );
        } else if ( name.equals( "drools.advancedProcessRuleIntegration" ) ) {
            setAdvancedProcessRuleIntegration( StringUtils.isEmpty( value ) ? false : Boolean.valueOf( value ) );
        } else if ( name.equals( "drools.multithreadEvaluation" ) ) {
            setMultithreadEvaluation( StringUtils.isEmpty( value ) ? false : Boolean.valueOf( value ) );
        } else if ( name.equals( "drools.maxThreads" ) ) {
            setMaxThreads( StringUtils.isEmpty( value ) ? -1 : Integer.parseInt( value ) );
        } else if ( name.equals( "drools.eventProcessingMode" ) ) {
            setEventProcessingMode( EventProcessingMode.determineAssertBehaviour( StringUtils.isEmpty( value ) ? "cloud" : value ) );
        }
    }

    public String getProperty(String name) {
        name = name.trim();
        if ( StringUtils.isEmpty( name ) ) {
            return null;
        }

        if ( name.equals( "drools.sequential.agenda" ) ) {
            return getSequentialAgenda().toExternalForm();
        } else if ( name.equals( "drools.sequential" ) ) {
            return Boolean.toString( isSequential() );
        } else if ( name.equals( "drools.maintainTms" ) ) {
            return Boolean.toString( isMaintainTms() );
        } else if ( name.equals( "drools.removeIdentities" ) ) {
            return Boolean.toString( isRemoveIdentities() );
        } else if ( name.equals( "drools.shareAlphaNodes" ) ) {
            return Boolean.toString( isShareAlphaNodes() );
        } else if ( name.equals( "drools.shareBetaNodes" ) ) {
            return Boolean.toString( isShareBetaNodes() );
        } else if ( name.equals( "drools.alphaNodeHashingThreshold" ) ) {
            return Integer.toString( getAlphaNodeHashingThreshold() );
        } else if ( name.equals( "drools.compositeKeyDepth" ) ) {
            return Integer.toString( getCompositeKeyDepth() );
        } else if ( name.equals( "drools.indexLeftBetaMemory" ) ) {
            return Boolean.toString( isIndexLeftBetaMemory() );
        } else if ( name.equals( "drools.indexRightBetaMemory" ) ) {
            return Boolean.toString( isIndexRightBetaMemory() );
        } else if ( name.equals( "drools.assertBehaviour" ) ) {
            return getAssertBehaviour().toExternalForm();
        } else if ( name.equals( "drools.logicalOverride" ) ) {
            return getLogicalOverride().toExternalForm();
        } else if ( name.equals( "drools.executorService" ) ) {
            return getExecutorService();
        } else if ( name.equals( "drools.consequenceExceptionHandler" ) ) {
            return getConsequenceExceptionHandler().getClass().getName();
        } else if ( name.equals( "drools.ruleBaseUpdateHandler" ) ) {
            return getRuleBaseUpdateHandler();
        } else if ( name.equals( "drools.conflictResolver" ) ) {
            return getConflictResolver().getClass().getName();
        } else if ( name.equals( "drools.advancedProcessRuleIntegration" ) ) {
            return Boolean.toString( isAdvancedProcessRuleIntegration() );
        } else if ( name.equals( "drools.multithreadEvaluation" ) ) {
            Boolean.toString( isMultithreadEvaluation() );
        } else if ( name.equals( "drools.maxThreads" ) ) {
            return Integer.toString( getMaxThreads() );
        } else if ( name.equals( "drools.eventProcessingMode" ) ) {
            return getEventProcessingMode().toExternalForm();
        }

        return null;
    }

    /**
     * A constructor that sets the classloader to be used as the parent classloader
     * of this rule base classloaders, and the properties to be used
     * as base configuration options
     *
     * @param classLoder
     * @param properties
     */
    public RuleBaseConfiguration(ClassLoader classLoader,
                                 Properties properties) {
        init( classLoader,
              properties );
    }

    private void init(ClassLoader classLoader,
                      Properties properties) {
        this.immutable = false;

        if ( classLoader != null ) {
            this.classLoader = classLoader;
        } else if ( Thread.currentThread().getContextClassLoader() != null ) {
            this.classLoader = Thread.currentThread().getContextClassLoader();
        } else {
            this.classLoader = this.getClass().getClassLoader();
        }

        this.chainedProperties = new ChainedProperties( "rulebase.conf" );

        if ( properties != null ) {
            this.chainedProperties.addProperties( properties );
        }

        setSequentialAgenda( SequentialAgenda.determineSequentialAgenda( this.chainedProperties.getProperty( "drools.sequential.agenda",
                                                                                                             "sequential" ) ) );

        setSequential( Boolean.valueOf( this.chainedProperties.getProperty( "drools.sequential",
                                                                            "false" ) ).booleanValue() );

        setMaintainTms( Boolean.valueOf( this.chainedProperties.getProperty( "drools.maintainTms",
                                                                             "true" ) ).booleanValue() );

        setRemoveIdentities( Boolean.valueOf( this.chainedProperties.getProperty( "drools.removeIdentities",
                                                                                  "false" ) ).booleanValue() );

        setShareAlphaNodes( Boolean.valueOf( this.chainedProperties.getProperty( "drools.shareAlphaNodes",
                                                                                 "true" ) ).booleanValue() );

        setShareBetaNodes( Boolean.valueOf( this.chainedProperties.getProperty( "drools.shareBetaNodes",
                                                                                "true" ) ).booleanValue() );

        setAlphaNodeHashingThreshold( Integer.parseInt( this.chainedProperties.getProperty( "drools.alphaNodeHashingThreshold",
                                                                                            "3" ) ) );

        setCompositeKeyDepth( Integer.parseInt( this.chainedProperties.getProperty( "drools.compositeKeyDepth",
                                                                                    "3" ) ) );

        setIndexLeftBetaMemory( Boolean.valueOf( this.chainedProperties.getProperty( "drools.indexLeftBetaMemory",
                                                                                     "true" ) ).booleanValue() );
        setIndexRightBetaMemory( Boolean.valueOf( this.chainedProperties.getProperty( "drools.indexRightBetaMemory",
                                                                                      "true" ) ).booleanValue() );

        setAssertBehaviour( AssertBehaviour.determineAssertBehaviour( this.chainedProperties.getProperty( "drools.assertBehaviour",
                                                                                                          "identity" ) ) );
        setLogicalOverride( LogicalOverride.determineLogicalOverride( this.chainedProperties.getProperty( "drools.logicalOverride",
                                                                                                          "discard" ) ) );

        setExecutorService( this.chainedProperties.getProperty( "drools.executorService",
                                                                "org.drools.concurrent.DefaultExecutorService" ) );

        setConsequenceExceptionHandler( this.chainedProperties.getProperty( "drools.consequenceExceptionHandler",
                                                                            "org.drools.runtime.rule.impl.DefaultConsequenceExceptionHandler" ) );

        setRuleBaseUpdateHandler( this.chainedProperties.getProperty( "drools.ruleBaseUpdateHandler",
                                                                      "org.drools.base.FireAllRulesRuleBaseUpdateListener" ) );

        setConflictResolver( RuleBaseConfiguration.determineConflictResolver( this.chainedProperties.getProperty( "drools.conflictResolver",
                                                                                                                  "org.drools.conflict.DepthConflictResolver" ) ) );

        setAdvancedProcessRuleIntegration( Boolean.valueOf( this.chainedProperties.getProperty( "drools.advancedProcessRuleIntegration",
                                                                                                "false" ) ).booleanValue() );

        setMultithreadEvaluation( Boolean.valueOf( this.chainedProperties.getProperty( "drools.multithreadEvaluation",
                                                                                       "false" ) ).booleanValue() );

        setMaxThreads( Integer.parseInt( this.chainedProperties.getProperty( "drools.maxThreads",
                                                                             "-1" ) ) );
        setEventProcessingMode( EventProcessingMode.determineAssertBehaviour( this.chainedProperties.getProperty( "drools.eventProcessingMode",
                                                                                                                  "cloud" ) ) );
    }

    /**
     * Makes the configuration object immutable. Once it becomes immutable,
     * there is no way to make it mutable again.
     * This is done to keep consistency.
     */
    public void makeImmutable() {
        this.immutable = true;
    }

    /**
     * Returns true if this configuration object is immutable or false otherwise.
     * @return
     */
    public boolean isImmutable() {
        return this.immutable;
    }

    private void checkCanChange() {
        if ( this.immutable ) {
            throw new UnsupportedOperationException( "Can't set a property after configuration becomes immutable" );
        }
    }

    public void setSequential(boolean sequential) {
        this.sequential = sequential;
    }

    public boolean isSequential() {
        return this.sequential;
    }

    public boolean isMaintainTms() {
        return this.maintainTms;
    }

    public void setMaintainTms(final boolean maintainTms) {
        checkCanChange(); // throws an exception if a change isn't possible;
        this.maintainTms = maintainTms;
    }

    public boolean isRemoveIdentities() {
        return this.removeIdentities;
    }

    public void setRemoveIdentities(final boolean removeIdentities) {
        checkCanChange(); // throws an exception if a change isn't possible;
        this.removeIdentities = removeIdentities;
    }

    public boolean isShareAlphaNodes() {
        return this.shareAlphaNodes;
    }

    public void setShareAlphaNodes(final boolean shareAlphaNodes) {
        checkCanChange(); // throws an exception if a change isn't possible;
        this.shareAlphaNodes = shareAlphaNodes;
    }

    public boolean isShareBetaNodes() {
        return this.shareBetaNodes;
    }

    public void setShareBetaNodes(final boolean shareBetaNodes) {
        checkCanChange(); // throws an exception if a change isn't possible;
        this.shareBetaNodes = shareBetaNodes;
    }

    public int getAlphaNodeHashingThreshold() {
        return this.alphaNodeHashingThreshold;
    }

    public void setAlphaNodeHashingThreshold(final int alphaNodeHashingThreshold) {
        checkCanChange(); // throws an exception if a change isn't possible;
        this.alphaNodeHashingThreshold = alphaNodeHashingThreshold;
    }

    public AssertBehaviour getAssertBehaviour() {
        return this.assertBehaviour;
    }

    public void setAssertBehaviour(final AssertBehaviour assertBehaviour) {
        checkCanChange(); // throws an exception if a change isn't possible;
        this.assertBehaviour = assertBehaviour;
    }

    public EventProcessingMode getEventProcessingMode() {
        return this.eventProcessingMode;
    }

    public void setEventProcessingMode(final EventProcessingMode mode) {
        checkCanChange(); // throws an exception if a change isn't possible;
        this.eventProcessingMode = mode;
    }

    public int getCompositeKeyDepth() {
        return this.compositeKeyDepth;
    }

    public void setCompositeKeyDepth(final int compositeKeyDepth) {
        if ( !this.immutable ) {
            if ( compositeKeyDepth > 3 ) {
                throw new UnsupportedOperationException( "compositeKeyDepth cannot be greater than 3" );
            }
            this.compositeKeyDepth = compositeKeyDepth;
        } else {
            throw new UnsupportedOperationException( "Can't set a property after configuration becomes immutable" );
        }
    }

    public boolean isIndexLeftBetaMemory() {
        return this.indexLeftBetaMemory;
    }

    public void setIndexLeftBetaMemory(final boolean indexLeftBetaMemory) {
        checkCanChange(); // throws an exception if a change isn't possible;
        this.indexLeftBetaMemory = indexLeftBetaMemory;
    }

    public boolean isIndexRightBetaMemory() {
        return this.indexRightBetaMemory;
    }

    public void setIndexRightBetaMemory(final boolean indexRightBetaMemory) {
        checkCanChange(); // throws an exception if a change isn't possible;
        this.indexRightBetaMemory = indexRightBetaMemory;
    }

    public LogicalOverride getLogicalOverride() {
        return this.logicalOverride;
    }

    public void setLogicalOverride(final LogicalOverride logicalOverride) {
        checkCanChange(); // throws an exception if a change isn't possible;
        this.logicalOverride = logicalOverride;
    }

    public String getExecutorService() {
        return executorService;
    }

    public void setExecutorService(String executorService) {
        checkCanChange(); // throws an exception if a change isn't possible;
        this.executorService = executorService;
    }

    public String getConsequenceExceptionHandler() {
        return consequenceExceptionHandler;
    }

    public void setConsequenceExceptionHandler(String consequenceExceptionHandler) {
        checkCanChange(); // throws an exception if a change isn't possible;
        this.consequenceExceptionHandler = consequenceExceptionHandler;
    }

    public String getRuleBaseUpdateHandler() {
        return ruleBaseUpdateHandler;
    }

    public void setRuleBaseUpdateHandler(String ruleBaseUpdateHandler) {
        checkCanChange(); // throws an exception if a change isn't possible;
        this.ruleBaseUpdateHandler = ruleBaseUpdateHandler;
    }

    public AgendaGroupFactory getAgendaGroupFactory() {
        if ( isSequential() ) {
            if ( this.sequentialAgenda == SequentialAgenda.SEQUENTIAL ) {
                return ArrayAgendaGroupFactory.getInstance();
            } else {
                return PriorityQueueAgendaGroupFactory.getInstance();
            }
        } else {
            return PriorityQueueAgendaGroupFactory.getInstance();
        }
    }

    public SequentialAgenda getSequentialAgenda() {
        return this.sequentialAgenda;
    }

    public void setSequentialAgenda(final SequentialAgenda sequentialAgenda) {
        checkCanChange(); // throws an exception if a change isn't possible;
        this.sequentialAgenda = sequentialAgenda;
    }

    public NodeInstanceFactoryRegistry getProcessNodeInstanceFactoryRegistry() {
        if ( this.processNodeInstanceFactoryRegistry == null ) {
            initProcessNodeInstanceFactoryRegistry();
        }
        return this.processNodeInstanceFactoryRegistry;

    }

    /**
     * Defines if the RuleBase should be executed using a pool of
     * threads for evaluating the rules ("true"), or if the rulebase 
     * should work in classic single thread mode ("false").
     * 
     * @param enableMultithread true for multi-thread or 
     *                     false for single-thread. Default is false.
     */
    public void setMultithreadEvaluation(boolean enableMultithread) {
        checkCanChange();
        this.multithread = enableMultithread;
    }

    /**
     * Returns true if the partitioning of the rulebase is enabled
     * and false otherwise. Default is false.
     * 
     * @return
     */
    public boolean isMultithreadEvaluation() {
        return this.multithread;
    }

    /**
     * If multi-thread evaluation is enabled, this parameter configures the 
     * maximum number of threads each session can use for concurrent Rete
     * propagation. 
     * 
     * @param maxThreads the maximum number of threads to use. If 0 or a 
     *                   negative number is set, the engine will use number
     *                   of threads equal to the number of partitions in the
     *                   rule base. Default number of threads is 0. 
     */
    public void setMaxThreads(final int maxThreads) {
        this.maxThreads = maxThreads;
    }

    /**
     * Returns the configured number of maximum threads to use for concurrent
     * propagation when multi-thread evaluation is enabled. Default is zero.
     * 
     * @return
     */
    public int getMaxThreads() {
        return this.maxThreads;
    }

    private void initProcessNodeInstanceFactoryRegistry() {
        this.processNodeInstanceFactoryRegistry = new NodeInstanceFactoryRegistry();

        // split on each space
        String locations[] = this.chainedProperties.getProperty( "processNodeInstanceFactoryRegistry",
                                                                 "" ).split( "\\s" );

        int i = 0;
        // load each SemanticModule
        for ( String factoryLocation : locations ) {
            // trim leading/trailing spaces and quotes
            factoryLocation = factoryLocation.trim();
            if ( factoryLocation.startsWith( "\"" ) ) {
                factoryLocation = factoryLocation.substring( 1 );
            }
            if ( factoryLocation.endsWith( "\"" ) ) {
                factoryLocation = factoryLocation.substring( 0,
                                                             factoryLocation.length() - 1 );
            }
            if ( !factoryLocation.equals( "" ) ) {
                loadProcessNodeInstanceFactoryRegistry( factoryLocation );
            }
        }
    }

    private void loadProcessNodeInstanceFactoryRegistry(String factoryLocation) {
        String content = ConfFileUtils.URLContentsToString( ConfFileUtils.getURL( factoryLocation,
                                                                                  null,
                                                                                  RuleBaseConfiguration.class ) );

        Map<Class< ? extends Node>, NodeInstanceFactory> map = (Map<Class< ? extends Node>, NodeInstanceFactory>) MVEL.eval( content,
                                                                                                                             new HashMap() );

        if ( map != null ) {
            for ( Entry<Class< ? extends Node>, NodeInstanceFactory> entry : map.entrySet() ) {
                this.processNodeInstanceFactoryRegistry.register( entry.getKey(),
                                                                  entry.getValue() );
            }
        }
    }

    public ProcessInstanceFactoryRegistry getProcessInstanceFactoryRegistry() {
        if ( this.processInstanceFactoryRegistry == null ) {
            initProcessInstanceFactoryRegistry();
        }
        return this.processInstanceFactoryRegistry;

    }

    private void initProcessInstanceFactoryRegistry() {
        this.processInstanceFactoryRegistry = new ProcessInstanceFactoryRegistry();

        // split on each space
        String locations[] = this.chainedProperties.getProperty( "processInstanceFactoryRegistry",
                                                                 "" ).split( "\\s" );

        int i = 0;
        // load each SemanticModule
        for ( String factoryLocation : locations ) {
            // trim leading/trailing spaces and quotes
            factoryLocation = factoryLocation.trim();
            if ( factoryLocation.startsWith( "\"" ) ) {
                factoryLocation = factoryLocation.substring( 1 );
            }
            if ( factoryLocation.endsWith( "\"" ) ) {
                factoryLocation = factoryLocation.substring( 0,
                                                             factoryLocation.length() - 1 );
            }
            if ( !factoryLocation.equals( "" ) ) {
                loadProcessInstanceFactoryRegistry( factoryLocation );
            }
        }
    }

    private void loadProcessInstanceFactoryRegistry(String factoryLocation) {
        String content = ConfFileUtils.URLContentsToString( ConfFileUtils.getURL( factoryLocation,
                                                                                  null,
                                                                                  RuleBaseConfiguration.class ) );

        Map<Class< ? extends Process>, ProcessInstanceFactory> map = (Map<Class< ? extends Process>, ProcessInstanceFactory>) MVEL.eval( content,
                                                                                                                                         new HashMap() );

        if ( map != null ) {
            for ( Entry<Class< ? extends Process>, ProcessInstanceFactory> entry : map.entrySet() ) {
                this.processInstanceFactoryRegistry.register( entry.getKey(),
                                                              entry.getValue() );
            }
        }
    }

    public Map<String, WorkDefinition> getProcessWorkDefinitions() {
        if ( this.workDefinitions == null ) {
            initWorkDefinitions();
        }
        return this.workDefinitions;

    }

    private void initWorkDefinitions() {
        this.workDefinitions = new HashMap<String, WorkDefinition>();

        // split on each space
        String locations[] = this.chainedProperties.getProperty( "drools.workDefinitions",
                                                                 "" ).split( "\\s" );

        // load each SemanticModule
        for ( String factoryLocation : locations ) {
            // trim leading/trailing spaces and quotes
            factoryLocation = factoryLocation.trim();
            if ( factoryLocation.startsWith( "\"" ) ) {
                factoryLocation = factoryLocation.substring( 1 );
            }
            if ( factoryLocation.endsWith( "\"" ) ) {
                factoryLocation = factoryLocation.substring( 0,
                                                             factoryLocation.length() - 1 );
            }
            if ( !factoryLocation.equals( "" ) ) {
                loadWorkItems( factoryLocation );
            }
        }
    }

    private void loadWorkItems(String location) {
        String content = ConfFileUtils.URLContentsToString( ConfFileUtils.getURL( location,
                                                                                  null,
                                                                                  RuleBaseConfiguration.class ) );
        try {
            List<Map<String, Object>> workDefinitionsMap = (List<Map<String, Object>>) MVEL.eval( content,
                                                                                                  new HashMap() );
            for ( Map<String, Object> workDefinitionMap : workDefinitionsMap ) {
                WorkDefinitionExtensionImpl workDefinition = new WorkDefinitionExtensionImpl();
                workDefinition.setName( (String) workDefinitionMap.get( "name" ) );
                workDefinition.setDisplayName( (String) workDefinitionMap.get( "displayName" ) );
                workDefinition.setIcon( (String) workDefinitionMap.get( "icon" ) );
                workDefinition.setCustomEditor( (String) workDefinitionMap.get( "customEditor" ) );
                Set<ParameterDefinition> parameters = new HashSet<ParameterDefinition>();
                Map<String, DataType> parameterMap = (Map<String, DataType>) workDefinitionMap.get( "parameters" );
                if ( parameterMap != null ) {
                    for ( Map.Entry<String, DataType> entry : parameterMap.entrySet() ) {
                        parameters.add( new ParameterDefinitionImpl( entry.getKey(),
                                                                     entry.getValue() ) );
                    }
                }
                workDefinition.setParameters( parameters );
                Set<ParameterDefinition> results = new HashSet<ParameterDefinition>();
                Map<String, DataType> resultMap = (Map<String, DataType>) workDefinitionMap.get( "results" );
                if ( resultMap != null ) {
                    for ( Map.Entry<String, DataType> entry : resultMap.entrySet() ) {
                        results.add( new ParameterDefinitionImpl( entry.getKey(),
                                                                  entry.getValue() ) );
                    }
                }
                workDefinition.setResults( results );
                this.workDefinitions.put( workDefinition.getName(),
                                          workDefinition );
            }
        } catch ( Throwable t ) {
            System.err.println( "Error occured while loading work definitions " + location );
            System.err.println( "Continuing without reading these work definitions" );
            t.printStackTrace();
            throw new RuntimeException("Could not parse work definitions " + location + ": " + t.getMessage());
        }
    }

    public ContextInstanceFactoryRegistry getProcessContextInstanceFactoryRegistry() {
        if ( this.processContextInstanceFactoryRegistry == null ) {
            initProcessContextInstanceFactoryRegistry();
        }
        return this.processContextInstanceFactoryRegistry;

    }

    private void initProcessContextInstanceFactoryRegistry() {
        this.processContextInstanceFactoryRegistry = new ContextInstanceFactoryRegistry();

        // split on each space
        String locations[] = this.chainedProperties.getProperty( "processContextInstanceFactoryRegistry",
                                                                 "" ).split( "\\s" );

        int i = 0;
        // load each SemanticModule
        for ( String factoryLocation : locations ) {
            // trim leading/trailing spaces and quotes
            factoryLocation = factoryLocation.trim();
            if ( factoryLocation.startsWith( "\"" ) ) {
                factoryLocation = factoryLocation.substring( 1 );
            }
            if ( factoryLocation.endsWith( "\"" ) ) {
                factoryLocation = factoryLocation.substring( 0,
                                                             factoryLocation.length() - 1 );
            }
            if ( !factoryLocation.equals( "" ) ) {
                loadProcessContextInstanceFactoryRegistry( factoryLocation );
            }
        }
    }

    private void loadProcessContextInstanceFactoryRegistry(String factoryLocation) {
        String content = ConfFileUtils.URLContentsToString( ConfFileUtils.getURL( factoryLocation,
                                                                                  null,
                                                                                  RuleBaseConfiguration.class ) );

        Map<Class< ? extends Context>, ContextInstanceFactory> map = (Map<Class< ? extends Context>, ContextInstanceFactory>) MVEL.eval( content,
                                                                                                                                         new HashMap() );

        if ( map != null ) {
            for ( Entry<Class< ? extends Context>, ContextInstanceFactory> entry : map.entrySet() ) {
                this.processContextInstanceFactoryRegistry.register( entry.getKey(),
                                                                     entry.getValue() );
            }
        }
    }

    public boolean isAdvancedProcessRuleIntegration() {
        return advancedProcessRuleIntegration;
    }

    public void setAdvancedProcessRuleIntegration(boolean advancedProcessRuleIntegration) {
        this.advancedProcessRuleIntegration = advancedProcessRuleIntegration;
    }

    private boolean determineShadowProxy(String userValue) {
        if ( this.isSequential() ) {
            // sequential never needs shadowing, so always override
            return false;
        }

        if ( userValue != null ) {
            return Boolean.valueOf( userValue ).booleanValue();
        } else {
            return true;
        }
    }

    private static ConflictResolver determineConflictResolver(String className) {
        Class clazz = null;
        try {
            clazz = Thread.currentThread().getContextClassLoader().loadClass( className );
        } catch ( ClassNotFoundException e ) {
        }

        if ( clazz == null ) {
            try {
                clazz = RuleBaseConfiguration.class.getClassLoader().loadClass( className );
            } catch ( ClassNotFoundException e ) {
            }
        }

        if ( clazz != null ) {
            try {
                return (ConflictResolver) clazz.getMethod( "getInstance",
                                                           null ).invoke( null,
                                                                          null );
            } catch ( Exception e ) {
                throw new IllegalArgumentException( "Unable to Conflict Resolver '" + className + "'" );
            }
        } else {
            throw new IllegalArgumentException( "conflict Resolver '" + className + "' not found" );
        }
    }

    public void setConflictResolver(ConflictResolver conflictResolver) {
        checkCanChange(); // throws an exception if a change isn't possible;
        this.conflictResolver = conflictResolver;
    }

    public ConflictResolver getConflictResolver() {
        return this.conflictResolver;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public static class AssertBehaviour
        implements
        Externalizable {
        private static final long           serialVersionUID = 400L;

        public static final AssertBehaviour IDENTITY         = new AssertBehaviour( 0 );
        public static final AssertBehaviour EQUALITY         = new AssertBehaviour( 1 );

        private int                         value;

        public void readExternal(ObjectInput in) throws IOException,
                                                ClassNotFoundException {
            value = in.readInt();
        }

        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeInt( value );
        }

        public AssertBehaviour() {

        }

        private AssertBehaviour(final int value) {
            this.value = value;
        }

        public boolean equals(Object obj) {
            if ( obj == this ) return true;
            else if ( obj instanceof AssertBehaviour ) {
                AssertBehaviour that = (AssertBehaviour) obj;

                return value == that.value;
            }
            return false;
        }

        public static AssertBehaviour determineAssertBehaviour(final String value) {
            if ( "IDENTITY".equalsIgnoreCase( value ) ) {
                return IDENTITY;
            } else if ( "EQUALITY".equalsIgnoreCase( value ) ) {
                return EQUALITY;
            } else {
                throw new IllegalArgumentException( "Illegal enum value '" + value + "' for AssertBehaviour" );
            }
        }

        private Object readResolve() throws java.io.ObjectStreamException {
            switch ( this.value ) {
                case 0 :
                    return IDENTITY;
                case 1 :
                    return EQUALITY;
                default :
                    throw new IllegalArgumentException( "Illegal enum value '" + this.value + "' for AssertBehaviour" );
            }
        }

        public String toExternalForm() {
            return (this.value == 0) ? "identity" : "equality";
        }

        public String toString() {
            return "AssertBehaviour : " + ((this.value == 0) ? "identity" : "equality");
        }
    }

    public static class LogicalOverride
        implements
        Externalizable {
        private static final long           serialVersionUID = 400L;

        public static final LogicalOverride PRESERVE         = new LogicalOverride( 0 );
        public static final LogicalOverride DISCARD          = new LogicalOverride( 1 );

        private int                         value;

        public void readExternal(ObjectInput in) throws IOException,
                                                ClassNotFoundException {
            value = in.readInt();
        }

        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeInt( value );
        }

        public LogicalOverride() {

        }

        private LogicalOverride(final int value) {
            this.value = value;
        }

        public static LogicalOverride determineLogicalOverride(final String value) {
            if ( "PRESERVE".equalsIgnoreCase( value ) ) {
                return PRESERVE;
            } else if ( "DISCARD".equalsIgnoreCase( value ) ) {
                return DISCARD;
            } else {
                throw new IllegalArgumentException( "Illegal enum value '" + value + "' for LogicalOverride" );
            }
        }

        private Object readResolve() throws java.io.ObjectStreamException {
            switch ( this.value ) {
                case 0 :
                    return PRESERVE;
                case 1 :
                    return DISCARD;
                default :
                    throw new IllegalArgumentException( "Illegal enum value '" + this.value + "' for LogicalOverride" );
            }
        }

        public boolean equals(Object obj) {
            if ( obj == this ) {
                return true;
            } else if ( obj instanceof LogicalOverride ) {
                return value == ((LogicalOverride) obj).value;
            }
            return false;
        }

        public String toExternalForm() {
            return (this.value == 0) ? "preserve" : "discard";
        }

        public String toString() {
            return "LogicalOverride : " + ((this.value == 0) ? "preserve" : "discard");
        }
    }

    public static class SequentialAgenda
        implements
        Externalizable {
        private static final long            serialVersionUID = 400L;

        public static final SequentialAgenda SEQUENTIAL       = new SequentialAgenda( 0 );
        public static final SequentialAgenda DYNAMIC          = new SequentialAgenda( 1 );

        private int                          value;

        public void readExternal(ObjectInput in) throws IOException,
                                                ClassNotFoundException {
            value = in.readInt();
        }

        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeInt( value );
        }

        public SequentialAgenda() {

        }

        private SequentialAgenda(final int value) {
            this.value = value;
        }

        public static SequentialAgenda determineSequentialAgenda(final String value) {
            if ( "sequential".equalsIgnoreCase( value ) ) {
                return SEQUENTIAL;
            } else if ( "dynamic".equalsIgnoreCase( value ) ) {
                return DYNAMIC;
            } else {
                throw new IllegalArgumentException( "Illegal enum value '" + value + "' for SequentialAgenda" );
            }
        }

        private Object readResolve() throws java.io.ObjectStreamException {
            switch ( this.value ) {
                case 0 :
                    return SEQUENTIAL;
                case 1 :
                    return DYNAMIC;
                default :
                    throw new IllegalArgumentException( "Illegal enum value '" + this.value + "' for SequentialAgenda" );
            }
        }

        public String toExternalForm() {
            return (this.value == 0) ? "sequential" : "dynamic";
        }

        public String toString() {
            return "SequentialAgenda : " + ((this.value == 0) ? "sequential" : "dynamic");
        }
    }

    /**
     * An enum for the valid event processing modes.
     * 
     * When the rulebase is compiled in the CLOUD (default) event processing mode,
     * it behaves just like a regular rulebase.
     * 
     * When the rulebase is compiled in the STREAM event processing mode, additional
     * assumptions are made. These assumptions allow the engine to perform a few optimisations
     * like:
     * 
     * <li> reasoning over absence of events (NOT CE), automatically adds an appropriate duration attribute
     * to the rule in order to avoid early rule firing. </li>
     * <li> memory management techniques may be employed when an event no longer can match other events
     * due to session clock continuous increment. </li>
     * 
     * @author etirelli
     *
     */
    public static enum EventProcessingMode {

        CLOUD("cloud"), 
        STREAM("stream");

        private String string;

        EventProcessingMode(String mode) {
            this.string = mode;
        }

        public String getId() {
            return string;
        }

        public String toString() {
            return string;
        }

        public String toExternalForm() {
            return this.string;
        }

        public static EventProcessingMode determineAssertBehaviour(String mode) {
            if ( STREAM.getId().equalsIgnoreCase( mode ) ) {
                return STREAM;
            } else if ( CLOUD.getId().equalsIgnoreCase( mode ) ) {
                return CLOUD;
            }
            throw new IllegalArgumentException( "Illegal enum value '" + mode + "' for EventProcessingMode" );
        }
    }

}
