package org.drools.compiler.kproject;

import org.kie.api.KieBase;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;

public class KProjectTestClassImpl implements KProjectTestClass {
    
    KieContainer kContainer;
    String namespace;
    
    public KProjectTestClassImpl(String namespace, KieContainer kContainer) {
        this.namespace = namespace;
        this.kContainer = kContainer;
    }

    @Override
    public KieBase getKBase1() {
        return this.kContainer.getKieBase( namespace + ".KBase1" );
    }

    @Override
    public KieBase getKBase2() {
        return this.kContainer.getKieBase( namespace + ".KBase2" );
    }

    @Override
    public KieBase getKBase3() {
        return this.kContainer.getKieBase( namespace + ".KBase3" );
    }

    @Override
    public StatelessKieSession getKBase1KSession1() {
        return this.kContainer.newStatelessKieSession(namespace + ".KSession1");
    }

    @Override
    public KieSession getKBase1KSession2() {
        return this.kContainer.newKieSession(namespace + ".KSession2");
    }

    @Override
    public KieSession getKBase2KSession3() {
        return this.kContainer.newKieSession(namespace + ".KSession3");
    }

    @Override
    public StatelessKieSession getKBase3KSession4() {
        return this.kContainer.newStatelessKieSession(namespace + ".KSession4");
    }

}
