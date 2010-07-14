package org.drools.persistence.session;

import java.lang.reflect.Constructor;
import java.util.Collections;
import java.util.Date;
import java.util.IdentityHashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory; //import javax.transaction.RollbackException;
//import javax.transaction.Status;
//import javax.transaction.Synchronization;
//import javax.transaction.SystemException;
//import javax.transaction.TransactionManager;
//import javax.transaction.UserTransaction;

import org.apache.commons.collections.map.HashedMap;
import org.drools.KnowledgeBase;
import org.drools.RuleBase;
import org.drools.SessionConfiguration;
import org.drools.command.Command;
import org.drools.command.Context;
import org.drools.command.impl.ContextImpl;
import org.drools.command.impl.GenericCommand;
import org.drools.command.impl.KnowledgeCommandContext;
import org.drools.command.runtime.DisposeCommand;
import org.drools.common.AbstractWorkingMemory.EndOperationListener;
import org.drools.impl.KnowledgeBaseImpl;
import org.drools.impl.StatefulKnowledgeSessionImpl;
import org.drools.persistence.processinstance.JPAProcessInstanceManager;
import org.drools.persistence.processinstance.JPASignalManager;
import org.drools.persistence.processinstance.JPAWorkItemManager;
import org.drools.reteoo.ReteooStatefulSession;
import org.drools.reteoo.ReteooWorkingMemory;
import org.drools.runtime.Environment;
import org.drools.runtime.EnvironmentName;
import org.drools.runtime.KnowledgeSessionConfiguration;
import org.drools.runtime.StatefulKnowledgeSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SingleSessionCommandService
    implements
    org.drools.command.SingleSessionCommandService {
    
    Logger                               logger                                            = LoggerFactory.getLogger( getClass() );    

    private SessionInfo                 sessionInfo;
    private JPASessionMarshallingHelper marshallingHelper;

    private StatefulKnowledgeSession    ksession;
    private Environment                 env;
    private KnowledgeCommandContext     kContext;

    private TransactionManager          txm;
    private JpaManager                  jpm;
    
    private boolean                     internalAppScopedEntityManager;
    private boolean                     internalCmdScopedEntityManager;
    
    private volatile boolean  doRollback;
    
    private static Map<Object, Object> synchronizations = Collections.synchronizedMap( new IdentityHashMap() );
    
    public static Map<Object, Object> txManagerClasses = Collections.synchronizedMap( new IdentityHashMap() );

    public void checkEnvironment(Environment env) {
        if ( env.get( EnvironmentName.ENTITY_MANAGER_FACTORY ) == null ) {
            throw new IllegalArgumentException( "Environment must have an EntityManagerFactory" );
        }

        // @TODO log a warning that all transactions will be locally scoped using the EntityTransaction
        //        if ( env.get( EnvironmentName.TRANSACTION_MANAGER ) == null ) {
        //            throw new IllegalArgumentException( "Environment must have an EntityManagerFactory" );
        //        }        
    }

    public SingleSessionCommandService(RuleBase ruleBase,
                                       SessionConfiguration conf,
                                       Environment env) {
        this( new KnowledgeBaseImpl( ruleBase ),
              conf,
              env );
    }

    public SingleSessionCommandService(int sessionId,
                                       RuleBase ruleBase,
                                       SessionConfiguration conf,
                                       Environment env) {
        this( sessionId,
              new KnowledgeBaseImpl( ruleBase ),
              conf,
              env );
    }

    public SingleSessionCommandService(KnowledgeBase kbase,
                                       KnowledgeSessionConfiguration conf,
                                       Environment env) {
        if ( conf == null ) {
            conf = new SessionConfiguration();
        }
        this.env = env;        
        
        checkEnvironment( this.env );        
        
        this.sessionInfo = new SessionInfo();

        initTransactionManager( this.env );
        
        ReteooStatefulSession session = (ReteooStatefulSession) ((KnowledgeBaseImpl) kbase).ruleBase.newStatefulSession( (SessionConfiguration) conf,
                                                                                                                         this.env );
        this.ksession = new StatefulKnowledgeSessionImpl( session,
                                                          kbase );

        this.kContext = new KnowledgeCommandContext( new ContextImpl( "ksession",
                                                                      null ),
                                                     null,
                                                     null,
                                                     this.ksession,
                                                     null );

        ((JPASignalManager) ((StatefulKnowledgeSessionImpl) ksession).session.getSignalManager()).setCommandService( this );
        this.marshallingHelper = new JPASessionMarshallingHelper( this.ksession,
                                                                  conf );
        this.sessionInfo.setJPASessionMashallingHelper( this.marshallingHelper );
        ((StatefulKnowledgeSessionImpl) this.ksession).session.setEndOperationListener( new EndOperationListenerImpl( this.sessionInfo ) );        
        
        // Use the App scoped EntityManager if the user has provided it, and it is open.

        try {
            this.txm.begin();
 
            //this.appScopedEntityManager.joinTransaction();
            registerRollbackSync();

            jpm.getApplicationScopedEntityManager().persist( this.sessionInfo );

            this.txm.commit();

        } catch ( Exception t1 ) {
            try {
                this.txm.rollback();
            } catch ( Throwable t2 ) {
                throw new RuntimeException( "Could not commit session or rollback",
                                            t2 );
            }
            throw new RuntimeException( "Could not commit session",
                                        t1 );
        }

        // update the session id to be the same as the session info id
        ((StatefulKnowledgeSessionImpl) ksession).session.setId( this.sessionInfo.getId() );

    }

    public SingleSessionCommandService(int sessionId,
                                       KnowledgeBase kbase,
                                       KnowledgeSessionConfiguration conf,
                                       Environment env) {
        if ( conf == null ) {
            conf = new SessionConfiguration();
        }
                

        this.env = env;
        
        checkEnvironment( this.env );
        
        initTransactionManager( this.env );

        initKsession( sessionId,
                      kbase,
                      conf );
    }

    public void initKsession(int sessionId,
                             KnowledgeBase kbase,
                             KnowledgeSessionConfiguration conf) {
        if ( !doRollback && this.ksession != null ) {
            return;
            // nothing to initialise
        }
        
        this.doRollback = false;       

        try {
            this.sessionInfo = jpm.getApplicationScopedEntityManager().find( SessionInfo.class,
                                                                             sessionId );
        } catch ( Exception e ) {
            throw new RuntimeException( "Could not find session data for id " + sessionId,
                                        e );
        }

        if ( sessionInfo == null ) {
            throw new RuntimeException( "Could not find session data for id " + sessionId );
        }

        if ( this.marshallingHelper == null ) {
            // this should only happen when this class is first constructed
            this.marshallingHelper = new JPASessionMarshallingHelper( kbase,
                                                                      conf,
                                                                      env );
        }

        this.sessionInfo.setJPASessionMashallingHelper( this.marshallingHelper );

        // if this.ksession is null, it'll create a new one, else it'll use the existing one
        this.ksession = this.marshallingHelper.loadSnapshot( this.sessionInfo.getData(),
                                                             this.ksession );

        // update the session id to be the same as the session info id
        ((StatefulKnowledgeSessionImpl) ksession).session.setId( this.sessionInfo.getId() );

        ((StatefulKnowledgeSessionImpl) this.ksession).session.setEndOperationListener( new EndOperationListenerImpl( this.sessionInfo ) );

        ((JPASignalManager) ((StatefulKnowledgeSessionImpl) ksession).session.getSignalManager()).setCommandService( this );
        
        if ( this.kContext == null ) {
            // this should only happen when this class is first constructed
            this.kContext = new KnowledgeCommandContext( new ContextImpl( "ksession",
                                                                          null ),
                                                         null,
                                                         null,
                                                         this.ksession,
                                                         null );
        }

    }
    
    public void initTransactionManager(Environment env) {
        Object tm = env.get( EnvironmentName.TRANSACTION_MANAGER );
        if ( tm != null && tm.getClass().getName().startsWith( "org.springframework" ) ) {
            try {
                Class cls = Class.forName( "org.drools.container.spring.beans.persistence.DroolsSpringTransactionManager" );
                Constructor con = cls.getConstructors()[0];
                this.txm = (TransactionManager) con.newInstance( tm );
                logger.debug( "Instantiating  DroolsSpringTransactionManager" );
                                
                if ( tm.getClass().getName().toLowerCase().contains( "jpa" ) ) {
                    // configure spring for JPA and local transactions
                    cls = Class.forName( "org.drools.container.spring.beans.persistence.DroolsSpringJpaManager" );
                    con = cls.getConstructors()[0];
                    this.jpm =  ( JpaManager) con.newInstance( new Object[] { this.env } );
                } else {
                    // configure spring for JPA and distributed transactions 
                }
            } catch ( Exception e ) {
                logger.warn( "Could not instatiate DroolsSpringTransactionManager" );
                throw new RuntimeException( "Could not instatiate org.drools.container.spring.beans.persistence.DroolsSpringTransactionManager", e );
            }
        } else {
            logger.debug( "Instantiating  JtaTransactionManager" );
            this.txm = new JtaTransactionManager( env.get( EnvironmentName.TRANSACTION ),
                                                  env.get( EnvironmentName.TRANSACTION_SYNCHRONIZATION_REGISTRY ),
                                                  tm ); 
            this.jpm = new DefaultJpaManager(this.env);
        }
    }

    public static class EndOperationListenerImpl
        implements
        EndOperationListener {
        private SessionInfo info;

        public EndOperationListenerImpl(SessionInfo info) {
            this.info = info;
        }

        public void endOperation(ReteooWorkingMemory wm) {
            this.info.setLastModificationDate( new Date( wm.getLastIdleTimestamp() ) );
        }
    }

    public Context getContext() {
        return this.kContext;
    }

    public synchronized <T> T execute(Command<T> command) {
        try {
            txm.begin();
            
            initKsession( this.sessionInfo.getId(),
                          this.marshallingHelper.getKbase(),
                          this.marshallingHelper.getConf() );
            
            this.jpm.beginCommandScopedEntityManager();

            //this.appScopedEntityManager.joinTransaction();
            registerRollbackSync();

            T result = ((GenericCommand<T>) command).execute( this.kContext );

            txm.commit();

            return result;

        } catch ( Exception t1 ) {
            try {
                txm.rollback();
            } catch ( Exception t2 ) {
                throw new RuntimeException( "Could not commit session or rollback",
                                            t2 );
            }
            throw new RuntimeException( "Could not commit session",
                                        t1 );
        } finally {
            if ( command instanceof DisposeCommand ) {
                this.jpm.dispose();
            }
        }
    }

    public void dispose() {
        if ( ksession != null ) {
            ksession.dispose();
        }
    }

    public int getSessionId() {
        return sessionInfo.getId();
    }

    @SuppressWarnings("unchecked")
    private void registerRollbackSync() {
        if ( synchronizations.get( this ) == null ) {
            txm.registerTransactionSynchronization( new SynchronizationImpl( this ) );
            synchronizations.put( this,
                                  this );
        }

    }

    private static class SynchronizationImpl
        implements
        TransactionSynchronization {

        SingleSessionCommandService service;

        public SynchronizationImpl(SingleSessionCommandService service) {
            this.service = service;
        }

        @SuppressWarnings("unchecked")
        public void afterCompletion(int status) {
            if ( status != TransactionManager.STATUS_COMMITTED ) {
                this.service.rollback();                
            }

            // always cleanup thread local whatever the result
            this.service.synchronizations.remove( this.service );
            
            this.service.jpm.endCommandScopedEntityManager();

            StatefulKnowledgeSessionImpl ksession = ((StatefulKnowledgeSessionImpl) this.service.ksession);
            // clean up cached process and work item instances
            if ( ksession != null ) {
                ((JPAProcessInstanceManager) ((StatefulKnowledgeSessionImpl) ksession).session.getProcessInstanceManager()).clearProcessInstances();
                ((JPAWorkItemManager) ((StatefulKnowledgeSessionImpl) ksession).session.getWorkItemManager()).clearWorkItems();
            }

        }

        public void beforeCompletion() {

        }

    }

    private void rollback() {
        this.doRollback = true;
    }
}