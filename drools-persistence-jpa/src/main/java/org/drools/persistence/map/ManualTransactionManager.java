package org.drools.persistence.map;

import org.drools.persistence.TransactionManager;
import org.drools.persistence.TransactionSynchronization;
import org.drools.persistence.info.SessionInfo;
import org.drools.persistence.info.WorkItemInfo;

public class ManualTransactionManager
    implements
    TransactionManager {
    
    private NonTransactionalPersistentSession session;
    private KnowledgeSessionStorage storage;
    
    public ManualTransactionManager(NonTransactionalPersistentSession session,
                                    KnowledgeSessionStorage storage) {
        this.session = session;
        this.storage = storage;
    }
    
    public int getStatus() {
        return 0;
    }

    public void begin() {
        session.clear();
    }

    public void commit() {
        for(SessionInfo sessionInfo : session.getStoredKnowledgeSessions()){
            storage.saveOrUpdate(sessionInfo);
        }
        
        for(WorkItemInfo workItemInfo : session.getStoredWorkItems()){
            storage.saveOrUpdate( workItemInfo );
        }
    }

    public void rollback() {
    }

    public void registerTransactionSynchronization(TransactionSynchronization ts) {

    }
}
