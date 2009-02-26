package org.drools.marshalling.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.RuleBaseConfiguration;
import org.drools.SessionConfiguration;
import org.drools.StatefulSession;
import org.drools.common.InternalRuleBase;
import org.drools.common.InternalWorkingMemory;
import org.drools.concurrent.CommandExecutor;
import org.drools.concurrent.ExecutorService;
import org.drools.impl.KnowledgeBaseImpl;
import org.drools.impl.StatefulKnowledgeSessionImpl;
import org.drools.marshalling.Marshaller;
import org.drools.reteoo.ReteooRuleBase;
import org.drools.reteoo.ReteooStatefulSession;
import org.drools.runtime.Environment;
import org.drools.runtime.KnowledgeSessionConfiguration;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.spi.ExecutorServiceFactory;
import org.drools.spi.GlobalResolver;

public class DefaultMarshaller
    implements
    Marshaller {
    KnowledgeBase                  kbase;
    GlobalResolver                 globalResolver;
    RuleBaseConfiguration          ruleBaseConfig;
    MarshallingConfiguration       marshallingConfig;
    ObjectMarshallingStrategyStore strategyStore;

    public DefaultMarshaller(KnowledgeBase kbase,
                             MarshallingConfiguration marshallingConfig) {
        this.kbase = kbase;
        this.ruleBaseConfig = (ruleBaseConfig != null) ? ruleBaseConfig : new RuleBaseConfiguration();
        this.marshallingConfig = marshallingConfig;
        this.strategyStore = this.marshallingConfig.getObjectMarshallingStrategyStore();
    }

    
    public StatefulKnowledgeSession unmarshall(final InputStream stream) throws IOException,
                                                                             ClassNotFoundException {
        return unmarshall( stream, null, null );
    }
    /* (non-Javadoc)
     * @see org.drools.marshalling.Marshaller#read(java.io.InputStream, org.drools.common.InternalRuleBase, int, org.drools.concurrent.ExecutorService)
     */
    public StatefulKnowledgeSession unmarshall(final InputStream stream,
                                               KnowledgeSessionConfiguration config,
                                               Environment environment) throws IOException,
                                                                             ClassNotFoundException {
        if ( config == null ) {
            config = KnowledgeBaseFactory.newKnowledgeSessionConfiguration();
        }
        
        if ( environment == null ) {
            environment = KnowledgeBaseFactory.newEnvironment();
        }
        
        MarshallerReaderContext context = new MarshallerReaderContext( stream,
                                                                       (InternalRuleBase) ((KnowledgeBaseImpl) kbase).ruleBase,
                                                                       RuleBaseNodes.getNodeMap( (InternalRuleBase) ((KnowledgeBaseImpl) kbase).ruleBase ),
                                                                       this.strategyStore,
                                                                       this.marshallingConfig.isMarshallProcessInstances(),
                                                                       this.marshallingConfig.isMarshallWorkItems() );

        int id = ((ReteooRuleBase) ((KnowledgeBaseImpl) this.kbase).ruleBase).nextWorkingMemoryCounter();
        RuleBaseConfiguration conf = ((ReteooRuleBase) ((KnowledgeBaseImpl) this.kbase).ruleBase).getConfiguration();
        ExecutorService executor = ExecutorServiceFactory.createExecutorService( conf.getExecutorService() );

        ReteooStatefulSession session = InputMarshaller.readSession( context,
                                                                     id,
                                                                     executor,
                                                                     environment,
                                                                     (SessionConfiguration) config );
        executor.setCommandExecutor( new CommandExecutor( session ) );
        context.close();
        if ( ((SessionConfiguration) config).isKeepReference() ) {
            ((ReteooRuleBase)((KnowledgeBaseImpl)this.kbase).ruleBase).addStatefulSession( session );
        }
        return new StatefulKnowledgeSessionImpl( session );

    }

    public void unmarshall(final InputStream stream,
                           final StatefulKnowledgeSession ksession) throws IOException,
                                                                  ClassNotFoundException {
        MarshallerReaderContext context = new MarshallerReaderContext( stream,
                                                                       (InternalRuleBase) ((KnowledgeBaseImpl) kbase).ruleBase,
                                                                       RuleBaseNodes.getNodeMap( (InternalRuleBase) ((KnowledgeBaseImpl) kbase).ruleBase ),
                                                                       this.strategyStore,
                                                                       this.marshallingConfig.isMarshallProcessInstances(),
                                                                       marshallingConfig.isMarshallWorkItems() );

        InputMarshaller.readSession( (ReteooStatefulSession) ((StatefulKnowledgeSessionImpl)ksession).session,
                                     context );
        context.close();

    }

    /* (non-Javadoc)
     * @see org.drools.marshalling.Marshaller#write(java.io.OutputStream, org.drools.common.InternalRuleBase, org.drools.StatefulSession)
     */
    public void marshall(final OutputStream stream,
                         final StatefulKnowledgeSession session) throws IOException {
        MarshallerWriteContext context = new MarshallerWriteContext( stream,
                                                                     (InternalRuleBase) ((KnowledgeBaseImpl) kbase).ruleBase,
                                                                     (InternalWorkingMemory) ((StatefulKnowledgeSessionImpl) session).session,
                                                                     RuleBaseNodes.getNodeMap( (InternalRuleBase) ((KnowledgeBaseImpl) kbase).ruleBase ),
                                                                     this.strategyStore,
                                                                     this.marshallingConfig.isMarshallProcessInstances(),
                                                                     this.marshallingConfig.isMarshallWorkItems() );
        OutputMarshaller.writeSession( context );
        context.close();
    }

}
