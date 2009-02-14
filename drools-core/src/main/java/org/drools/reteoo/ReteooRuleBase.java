package org.drools.reteoo;

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

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.drools.FactException;
import org.drools.RuleBaseConfiguration;
import org.drools.SessionConfiguration;
import org.drools.StatefulSession;
import org.drools.StatelessSession;
import org.drools.common.AbstractRuleBase;
import org.drools.common.DefaultFactHandle;
import org.drools.common.InternalFactHandle;
import org.drools.common.InternalWorkingMemory;
import org.drools.common.RuleBasePartitionId;
import org.drools.concurrent.CommandExecutor;
import org.drools.concurrent.ExecutorService;
import org.drools.event.RuleBaseEventListener;
import org.drools.impl.EnvironmentFactory;
import org.drools.marshalling.Marshaller;
import org.drools.reteoo.ReteooWorkingMemory.WorkingMemoryReteAssertAction;
import org.drools.rule.EntryPoint;
import org.drools.rule.InvalidPatternException;
import org.drools.rule.Package;
import org.drools.rule.Rule;
import org.drools.runtime.Environment;
import org.drools.FactHandle;
import org.drools.spi.ExecutorServiceFactory;
import org.drools.spi.FactHandleFactory;
import org.drools.spi.PropagationContext;

/**
 * Implementation of <code>RuleBase</code>.
 *
 * @author <a href="mailto:bob@werken.com">bob mcwhirter</a>
 * @author <a href="mailto:mark.proctor@jboss.com">Mark Proctor</a>
 *
 * @version $Id: RuleBaseImpl.java,v 1.5 2005/08/14 22:44:12 mproctor Exp $
 */
public class ReteooRuleBase extends AbstractRuleBase {
    /**
     * DO NOT CHANGE BELLOW SERIAL_VERSION_ID UNLESS YOU ARE CHANGING DROOLS VERSION
     * SERIAL_VERSION_ID=320 stands for version 3.2.0
     */
    private static final long serialVersionUID = 500L;

    /** The root Rete-OO for this <code>RuleBase</code>. */
    private transient Rete    rete;

    private ReteooBuilder     reteooBuilder;

    // ------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------

    /**
     * Default constructor - for Externalizable. This should never be used by a user, as it
     * will result in an invalid state for the instance.
     */
    public ReteooRuleBase() {

    }

    /**
     * Construct.
     *
     * @param id
     *            The rete network.
     */
    public ReteooRuleBase(final String id) {
        this( id,
              null,
              new ReteooFactHandleFactory() );
        
    }

    /**
     * @param factHandleFactory
     */
    public ReteooRuleBase(final String id,
                          final FactHandleFactory factHandleFactory) {
        this( id,
              null,
              factHandleFactory );
    }

    public ReteooRuleBase(final String id,
                          final RuleBaseConfiguration config) {
        this( id,
              config,
              new ReteooFactHandleFactory() );
    }

    /**
     * @param config
     */
    public ReteooRuleBase(final RuleBaseConfiguration config) {
        this( null,
              config,
              new ReteooFactHandleFactory() );
    }

    /**
     * Construct.
     *
     * @param id
     *            The rete network.
     */
    public ReteooRuleBase(final String id,
                          final RuleBaseConfiguration config,
                          final FactHandleFactory factHandleFactory) {
        super( id,
               config,
               factHandleFactory );
        setupRete();
    }

    private void setupRete() {
        this.rete = new Rete( this );
        this.reteooBuilder = new ReteooBuilder( this );

        // always add the default entry point
        EntryPointNode epn = new EntryPointNode( this.reteooBuilder.getIdGenerator().getNextId(),
                                                 RuleBasePartitionId.MAIN_PARTITION,
                                                 this.config.isMultithreadEvaluation(),
                                                 this.rete,
                                                 EntryPoint.DEFAULT );
        epn.attach();
    }

    /**
     * Handles the write serialization of the Package. Patterns in Rules may reference generated data which cannot be serialized by default methods.
     * The Package uses PackageCompilationData to hold a reference to the generated bytecode. The generated bytecode must be restored before any Rules.
     *
     */
    public void writeExternal(final ObjectOutput stream) throws IOException {
        super.writeExternal( stream );
        stream.writeObject( this.reteooBuilder );
        stream.writeObject( this.rete );
    }

    /**
     * Handles the read serialization of the Package. Patterns in Rules may reference generated data which cannot be serialized by default methods.
     * The Package uses PackageCompilationData to hold a reference to the generated bytecode; which must be restored before any Rules.
     * A custom ObjectInputStream, able to resolve classes against the bytecode in the PackageCompilationData, is used to restore the Rules.
     *
     */
    public void readExternal(final ObjectInput stream) throws IOException,
                                                      ClassNotFoundException {
        super.readExternal( stream );
        this.reteooBuilder = (ReteooBuilder) stream.readObject();
        this.rete = (Rete) stream.readObject();
    }

    // ------------------------------------------------------------
    // Instance methods
    // ------------------------------------------------------------

    /**
     * Retrieve the Rete-OO network for this <code>RuleBase</code>.
     *
     * @return The RETE-OO network.
     */
    public Rete getRete() {
        return this.rete;
    }

    public ReteooBuilder getReteooBuilder() {
        return this.reteooBuilder;
    }

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
    public void assertObject(final FactHandle handle,
                             final Object object,
                             final PropagationContext context,
                             final InternalWorkingMemory workingMemory) throws FactException {
        getRete().assertObject( (DefaultFactHandle) handle,
                                context,
                                workingMemory );
    }

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
    public void retractObject(final FactHandle handle,
                              final PropagationContext context,
                              final ReteooWorkingMemory workingMemory) throws FactException {
        getRete().retractObject( (InternalFactHandle) handle,
                                 context,
                                 workingMemory );
    }
    

    public synchronized StatefulSession newStatefulSession(boolean keepReference) {
        SessionConfiguration config = new SessionConfiguration();
        config.setKeepReference( keepReference );
                       
        return newStatefulSession( config, EnvironmentFactory.newEnvironment() );
    }
    
    public synchronized StatefulSession newStatefulSession(final SessionConfiguration sessionConfig, final Environment environment) {
        return newStatefulSession( nextWorkingMemoryCounter(), sessionConfig, environment );
    }
    
    public synchronized StatefulSession newStatefulSession(int id, final SessionConfiguration sessionConfig, final Environment environment) {
        if ( this.config.isSequential() ) {
            throw new RuntimeException( "Cannot have a stateful rule session, with sequential configuration set to true" );
        }
        ReteooStatefulSession session = null;

        synchronized ( this.pkgs ) {
            ExecutorService executor = ExecutorServiceFactory.createExecutorService( this.config.getExecutorService() );;
            session = new ReteooStatefulSession( id,
                                                 this,
                                                 executor,
                                                 sessionConfig,
                                                 environment );

            executor.setCommandExecutor( new CommandExecutor( session ) );

            if ( sessionConfig.isKeepReference() ) {
                super.addStatefulSession( session );
                for ( Iterator it = session.getRuleBaseUpdateListeners().iterator(); it.hasNext(); ) {
                    addEventListener( (RuleBaseEventListener) it.next() );
                }
            }

            session.startPartitionManagers();

            session.queueWorkingMemoryAction( new WorkingMemoryReteAssertAction( session.getInitialFactHandle(),
                                                                                 false,
                                                                                 true,
                                                                                 null,
                                                                                 null ) );
        }

        return session;
    }

    public StatefulSession readStatefulSession(final InputStream stream,
                                               final boolean keepReference,
                                               Marshaller marshaller) throws IOException,
                                                                          ClassNotFoundException {

        if ( this.config.isSequential() ) {
            throw new RuntimeException( "Cannot have a stateful rule session, with sequential configuration set to true" );
        }

//        PlaceholderResolverStrategyFactory factory = new PlaceholderResolverStrategyFactory();
//        factory.addPlaceholderResolverStrategy( new SerializablePlaceholderResolverStrategy() );

        StatefulSession session = null;
        synchronized ( this.pkgs ) {
            ExecutorService executor = ExecutorServiceFactory.createExecutorService( this.config.getExecutorService() );
            
            session = marshaller.read( stream, this, nextWorkingMemoryCounter(), executor );

//            WMSerialisationInContext context = new WMSerialisationInContext( stream,
//                                                                             this,
//                                                                             RuleBaseNodes.getNodeMap( this ),
//                                                                             factory );
//
//            session = InputPersister.readSession( context, nextWorkingMemoryCounter(), executor );

            executor.setCommandExecutor( new CommandExecutor( session ) );


            if ( keepReference ) {
                super.addStatefulSession( session );
                for ( Iterator it = session.getRuleBaseUpdateListeners().iterator(); it.hasNext(); ) {
                    addEventListener( (RuleBaseEventListener) it.next() );
                }
            }
        }
        return session;

        //        final DroolsObjectInputStream streamWithLoader = new DroolsObjectInputStream( stream,
        //                                                                                      this.packageClassLoader );
        //        streamWithLoader.setRuleBase( this );
        //
        //        final StatefulSession session = (StatefulSession) streamWithLoader.readObject();
        //
        //        synchronized ( this.pkgs ) {
        //            ((InternalWorkingMemory) session).setRuleBase( this );
        //            ((InternalWorkingMemory) session).setId( (nextWorkingMemoryCounter()) );
        //
        //            ExecutorService executor = ExecutorServiceFactory.createExecutorService( this.config.getExecutorService() );
        //            executor.setCommandExecutor( new CommandExecutor( session ) );
        //            ((InternalWorkingMemory) session).setExecutorService( executor );
        //
        //            if ( keepReference ) {
        //                addStatefulSession( session );
        //                for ( Iterator it = session.getRuleBaseUpdateListeners().iterator(); it.hasNext(); ) {
        //                    addEventListener( (RuleBaseEventListener) it.next() );
        //                }
        //            }
        //
        //            return session;
        //        }
    }

    public void writeStatefulSession(final StatefulSession session,
                                     final OutputStream stream,
                                     Marshaller marshaller) throws IOException {
        
//        PlaceholderResolverStrategyFactory factory = new PlaceholderResolverStrategyFactory();
//        factory.addPlaceholderResolverStrategy( new SerializablePlaceholderResolverStrategy() );
//
//        WMSerialisationOutContext context = new WMSerialisationOutContext( stream,
//                                                                           this,
//                                                                           (InternalWorkingMemory) session,
//                                                                           RuleBaseNodes.getNodeMap( this ),
//                                                                           factory );
//        OutputPersister.writeSession( context );
        marshaller.write( stream, this, session );
        //((ReteooStatefulSession) session).write( context );
        

        //        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //        OutputPersister op = new OutputPersister( this,
        //                                                  (InternalWorkingMemory) wm,
        //                                                  baos,
        //                                                  factory );
        //        op.write();
    }

    public StatelessSession newStatelessSession() {

        //orders the rules
        if ( this.config.isSequential() ) {
            this.reteooBuilder.order();
        }

        synchronized ( this.pkgs ) {
            return new ReteooStatelessSession( this );
        }
    }

    protected synchronized void addRule(final Rule rule) throws InvalidPatternException {
        // This adds the rule. ReteBuilder has a reference to the WorkingMemories and will propagate any existing facts.
        this.reteooBuilder.addRule( rule );
    }

    protected synchronized void removeRule(final Rule rule) {
        this.reteooBuilder.removeRule( rule );
    }

    public int getNodeCount() {
        // may start in 0
        return this.reteooBuilder.getIdGenerator().getLastId() + 1;
    }
    
    public void addPackages(Package[] pkgs ) {
        addPackages( Arrays.asList( pkgs ) );
    }
    
    public void addPackages(Collection<Package> pkgs ) {
        super.addPackages( pkgs );
    }    

    public synchronized void addPackage(final Package newPkg) {
        List<Package> list = new ArrayList<Package>();
        list.add( newPkg );
        super.addPackages( list );
        if ( this.config.isSequential() ) {
            this.reteooBuilder.setOrdered( false );
        }
    }

}
