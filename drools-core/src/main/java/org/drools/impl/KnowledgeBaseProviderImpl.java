package org.drools.impl;

import java.util.Properties;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseConfiguration;
import org.drools.KnowledgeBaseProvider;
import org.drools.RuleBaseConfiguration;
import org.drools.RuleBaseFactory;
import org.drools.SessionConfiguration;
import org.drools.runtime.Environment;
import org.drools.runtime.KnowledgeSessionConfiguration;

public class KnowledgeBaseProviderImpl implements KnowledgeBaseProvider {

    public KnowledgeBaseConfiguration newKnowledgeBaseConfiguration() {
        return new RuleBaseConfiguration();
    }
        
    public KnowledgeBaseConfiguration newKnowledgeBaseConfiguration(Properties properties, ClassLoader classLoader) {
        return new RuleBaseConfiguration(classLoader, properties);
    }        
    
    public KnowledgeSessionConfiguration newKnowledgeSessionConfiguration() {
        return new SessionConfiguration();
    }
        
    public KnowledgeSessionConfiguration newKnowledgeSessionConfiguration(Properties properties) {
        return new SessionConfiguration(properties);
    }        
    
	public KnowledgeBase newKnowledgeBase() {		
		return new KnowledgeBaseImpl( RuleBaseFactory.newRuleBase() );		
	}	
	
    public KnowledgeBase newKnowledgeBase(KnowledgeBaseConfiguration conf) {
        return new KnowledgeBaseImpl( RuleBaseFactory.newRuleBase( ( RuleBaseConfiguration ) conf ) );
    }

	public Environment newEnvironment() {
		return new EnvironmentImpl(); //EnvironmentFactory.newEnvironment();
	}
}
