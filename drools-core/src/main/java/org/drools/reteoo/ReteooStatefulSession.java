package org.drools.reteoo;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.drools.SessionConfiguration;
import org.drools.StatefulSession;
import org.drools.common.InternalAgenda;
import org.drools.common.InternalRuleBase;
import org.drools.concurrent.AssertObject;
import org.drools.concurrent.AssertObjects;
import org.drools.concurrent.ExecutorService;
import org.drools.concurrent.FireAllRules;
import org.drools.concurrent.Future;
import org.drools.concurrent.RetractObject;
import org.drools.concurrent.UpdateObject;
import org.drools.FactHandle;
import org.drools.spi.AgendaFilter;
import org.drools.spi.FactHandleFactory;
import org.drools.spi.RuleBaseUpdateListener;
import org.drools.spi.RuleBaseUpdateListenerFactory;

public class ReteooStatefulSession extends ReteooWorkingMemory
    implements
    StatefulSession {

    private static final long         serialVersionUID = -5360554247241558374L;
    private transient ExecutorService executor;

    private transient List            ruleBaseListeners;

    public ReteooStatefulSession(final int id,
                                 final InternalRuleBase ruleBase,
                                 final ExecutorService executorService) {
        this( id,
              ruleBase,
              executorService,
              new SessionConfiguration() );
    }

    public ReteooStatefulSession(final int id,
                                 final InternalRuleBase ruleBase,
                                 final ExecutorService executorService,
                                 final SessionConfiguration config) {
        super( id,
               ruleBase,
               config );
        this.executor = executorService;
    }

    public ReteooStatefulSession(final int id,
                                 final InternalRuleBase ruleBase,
                                 final ExecutorService executorService,
                                 final FactHandleFactory handleFactory,
                                 final InitialFactHandle initialFactHandle,
                                 final long propagationContext,
                                 final SessionConfiguration config,
                                 final InternalAgenda agenda) {
        super( id,
               ruleBase,
               handleFactory,
               initialFactHandle,
               propagationContext,
               config,
               agenda );
        this.executor = executorService;
    }

    public Future asyncInsert(final Object object) {
        final AssertObject assertObject = new AssertObject( object );
        this.executor.submit( assertObject );
        return assertObject;
    }

    public Future asyncRetract(final FactHandle factHandle) {
        return this.executor.submit( new RetractObject( factHandle ) );
    }

    public Future asyncUpdate(final FactHandle factHandle,
                              final Object object) {
        return this.executor.submit( new UpdateObject( (org.drools.FactHandle)factHandle,
                                                       object ) );
    }

    public Future asyncInsert(final Object[] array) {
        final AssertObjects assertObjects = new AssertObjects( array );
        this.executor.submit( assertObjects );
        return assertObjects;
    }

    public Future asyncInsert(final Collection collection) {
        final AssertObjects assertObjects = new AssertObjects( collection );
        this.executor.submit( assertObjects );
        return assertObjects;
    }
    
    public Future asyncInsert(final Iterable<?> iterable) {
        final AssertObjects assertObjects = new AssertObjects( iterable );
        this.executor.submit( assertObjects );
        return assertObjects;
    }    

    public Future asyncFireAllRules(final AgendaFilter agendaFilter) {
        final FireAllRules fireAllRules = new FireAllRules( agendaFilter );
        this.executor.submit( fireAllRules );
        return fireAllRules;
    }

    public Future asyncFireAllRules() {
        final FireAllRules fireAllRules = new FireAllRules( null );
        this.executor.submit( fireAllRules );
        return fireAllRules;
    }

    public void dispose() {
        this.ruleBase.disposeStatefulSession( this );
    	super.dispose();
        this.executor.shutDown();
    }

    @Override
    protected void finalize() throws Throwable {
    	dispose();
    }

    public List getRuleBaseUpdateListeners() {
        if ( this.ruleBaseListeners == null || this.ruleBaseListeners == Collections.EMPTY_LIST ) {
            String listenerName = this.ruleBase.getConfiguration().getRuleBaseUpdateHandler();
            if ( listenerName != null && listenerName.length() > 0 ) {
                RuleBaseUpdateListener listener = RuleBaseUpdateListenerFactory.createListener( listenerName,
                                                                                                this );
                this.ruleBaseListeners = Collections.singletonList( listener );
            } else {
                this.ruleBaseListeners = Collections.EMPTY_LIST;
            }
        }
        return this.ruleBaseListeners;
    }

    //    public StatefulSession getEntryPoint(String id) {
    //        EntryPoint ep = new EntryPoint( id );
    //        return new EntryPointInterfaceImpl( ep,
    //                                            this );
    //    }

    public ExecutorService getExecutorService() {
        return executor;
    }

    public void setExecutorService(ExecutorService executor) {
        this.executor = executor;
    }
}
