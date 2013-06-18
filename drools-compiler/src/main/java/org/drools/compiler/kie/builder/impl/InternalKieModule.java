package org.drools.compiler.kie.builder.impl;

import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.api.builder.ReleaseId;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.builder.Results;
import org.kie.internal.definition.KnowledgePackage;

import java.io.File;
import java.util.Collection;
import java.util.Map;

public interface InternalKieModule extends KieModule {

    void cacheKnowledgeBuilderForKieBase(String kieBaseName, KnowledgeBuilder kbuilder);

    KnowledgeBuilder getKnowledgeBuilderForKieBase(String kieBaseName);

    Collection<KnowledgePackage> getKnowledgePackagesForKieBase(String kieBaseName);

    void cacheResultsForKieBase(String kieBaseName, Results results);

    Map<String, Results> getKnowledgeResultsCache();    
    
    KieModuleModel getKieModuleModel();    
    
    byte[] getBytes( );    
    
    Map<ReleaseId, InternalKieModule> getDependencies();

    void addDependency(InternalKieModule dependency);

    boolean isAvailable( final String pResourceName );
    
    byte[] getBytes( final String pResourceName );
    
    Collection<String> getFileNames();  
    
    File getFile();

    Map<String, byte[]> getClassesMap(boolean includeTypeDeclarations);
}
