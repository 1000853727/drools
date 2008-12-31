package org.drools.runtime.dataloader.impl;

import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.StatelessKnowledgeSession;
import org.drools.runtime.dataloader.DataLoaderProvider;
import org.drools.runtime.dataloader.StatefulKnowledgeSessionDataLoader;
import org.drools.runtime.dataloader.StatelessKnowledgeSessionDataLoader;
import org.drools.runtime.pipeline.Receiver;

public class DataLoaderProviderImpl
    implements
    DataLoaderProvider {
      
    public StatefulKnowledgeSessionDataLoader newStatefulKnowledgeSessionDataLoader(StatefulKnowledgeSession ksession,
                                                                                    Receiver pipeline) {
        return new StatefulKnowledgeSessionDataLoaderImpl(ksession, pipeline);
    }    

    public StatefulKnowledgeSessionDataLoader newStatefulKnowledgeSessionDataLoader(StatefulKnowledgeSession ksession,
                                                                                    String entryPointName,
                                                                                    Receiver pipeline) {
        return new StatefulKnowledgeSessionDataLoaderImpl(ksession, entryPointName, pipeline);
    }
    
    public StatelessKnowledgeSessionDataLoader newStatelessKnowledgeSessionDataLoader(StatelessKnowledgeSession ksession,
                                                                                      Receiver pipeline) {
        return newStatelessKnowledgeSessionDataLoader(ksession, pipeline);
    }

}
