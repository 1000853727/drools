package org.drools.compiler.integrationtests;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.drools.compiler.CommonTestMethodBase;
import org.drools.compiler.Message;
import org.drools.compiler.kie.builder.impl.KieContainerImpl;
import org.drools.core.RuleBase;
import org.drools.core.common.InternalRuleBase;
import org.drools.core.impl.KnowledgeBaseImpl;
import org.drools.core.reteoo.RuleTerminalNode;
import org.kie.api.definition.rule.Rule;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.ReleaseId;
import org.kie.api.definition.KiePackage;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.IncrementalResults;
import org.kie.internal.builder.InternalKieBuilder;
import org.kie.internal.builder.KieBuilderSet;

import java.util.HashMap;

import static java.util.Arrays.asList;

public class IncrementalCompilationTest extends CommonTestMethodBase {


    @Test
    public void testLoadOrderAfterRuleRemoval() throws Exception {
        String header = "package org.drools.compiler\n";

        String drl1 = "rule R1 when\n" +
                      "   $m : Message( message == \"Hello World1\" )\n" +
                      "then\n" +
                      "end\n";

        String drl2 = "rule R2 when\n" +
                      "   $m : Message( message == \"Hello World2\" )\n" +
                      "then\n" +
                      "end\n";

        String drl3 = "rule R3 when\n" +
                      "   $m : Message( message == \"Hello World3\" )\n" +
                      "then\n" +
                      "end\n";

        String drl4 = "rule R4 when\n" +
                      "   $m : Message( message == \"Hello World4\" )\n" +
                      "then\n" +
                      "end\n";

        KieServices ks = KieServices.Factory.get();
        ReleaseId releaseId1 = ks.newReleaseId("org.kie", "test-upgrade", "1");
        KieModule km = createAndDeployJar(ks, releaseId1,header );
        KieContainer kc = ks.newKieContainer(km.getReleaseId());


        createAndDeployAndTest(kc, "2", header, drl1 + drl2 + drl3, "R1", "R2", "R3" );

        createAndDeployAndTest(kc, "3", header, drl1 + drl3, "R1","R3" );

        createAndDeployAndTest(kc, "4", header, drl2 + drl1 + drl4, "R2","R1", "R4" );

        createAndDeployAndTest(kc, "5", header, drl2 + drl1, "R2","R1");

        createAndDeployAndTest(kc, "6", header, "" );

        createAndDeployAndTest(kc, "7", header, drl3, "R3" );
    }

    private  void createAndDeployAndTest(KieContainer kc, String version, String header, String  drls, String... ruleNames) {
        if ( ruleNames == null ) {
            ruleNames = new String[0];
        }
        KieServices ks = KieServices.Factory.get();

        StringBuilder sbuilder = new StringBuilder();
        sbuilder.append(header);
        sbuilder.append( drls );

        ReleaseId releaseId1 = ks.newReleaseId("org.kie", "test-upgrade", version);
        KieModule km = createAndDeployJar(ks, releaseId1, sbuilder.toString() );

        kc.updateToVersion(km.getReleaseId());

        KiePackage kpkg = ((KieContainerImpl) kc).getKieBase().getKiePackage( "org.drools.compiler");
        assertEquals( ruleNames.length,  kpkg.getRules().size() );
        Map<String, Rule> rules = rulestoMap(kpkg.getRules() );

        int i = 0;
        for ( String ruleName : ruleNames ) {
            assertEquals( ruleName, i++, ((org.drools.core.definitions.rule.impl.RuleImpl ) rules.get( ruleName )).getRule().getLoadOrder() );
        }
    }

    @Test
    public void testKJarUpgrade() throws Exception {
        String drl1 = "package org.drools.compiler\n" +
                "rule R1 when\n" +
                "   $m : Message()\n" +
                "then\n" +
                "end\n";

        String drl2_1 = "package org.drools.compiler\n" +
                "rule R2_1 when\n" +
                "   $m : Message( message == \"Hi Universe\" )\n" +
                "then\n" +
                "end\n";

        String drl2_2 = "package org.drools.compiler\n" +
                "rule R2_2 when\n" +
                "   $m : Message( message == \"Hello World\" )\n" +
                "then\n" +
                "end\n";

        KieServices ks = KieServices.Factory.get();
        
        // Create an in-memory jar for version 1.0.0
        ReleaseId releaseId1 = ks.newReleaseId("org.kie", "test-upgrade", "1.0.0");
        KieModule km = createAndDeployJar(ks, releaseId1, drl1, drl2_1);

        // Create a session and fire rules
        KieContainer kc = ks.newKieContainer(km.getReleaseId());
        KieSession ksession = kc.newKieSession();
        ksession.insert(new Message("Hello World"));
        assertEquals( 1, ksession.fireAllRules() );
        ksession.dispose();

        // Create a new jar for version 1.1.0
        ReleaseId releaseId2 = ks.newReleaseId("org.kie", "test-upgrade", "1.1.0");
        km = createAndDeployJar( ks, releaseId2, drl1, drl2_2 );

        // try to update the container to version 1.1.0
        kc.updateToVersion(releaseId2);
        
        // create and use a new session
        ksession = kc.newKieSession();
        ksession.insert(new Message("Hello World"));
        assertEquals( 2, ksession.fireAllRules() );
    }

    @Test
    public void testKJarUpgradeSameSession() throws Exception {
        String drl1 = "package org.drools.compiler\n" +
                "rule R1 when\n" +
                "   $m : Message()\n" +
                "then\n" +
                "end\n";

        String drl2_1 = "package org.drools.compiler\n" +
                "rule R2_1 when\n" +
                "   $m : Message( message == \"Hi Universe\" )\n" +
                "then\n" +
                "end\n";

        String drl2_2 = "package org.drools.compiler\n" +
                "rule R2_2 when\n" +
                "   $m : Message( message == \"Hello World\" )\n" +
                "then\n" +
                "end\n";

        KieServices ks = KieServices.Factory.get();
        
        // Create an in-memory jar for version 1.0.0
        ReleaseId releaseId1 = ks.newReleaseId("org.kie", "test-upgrade", "1.0.0");
        KieModule km = createAndDeployJar( ks, releaseId1, drl1, drl2_1 );

        // Create a session and fire rules
        KieContainer kc = ks.newKieContainer( km.getReleaseId() );
        KieSession ksession = kc.newKieSession();
        ksession.insert(new Message("Hello World"));
        assertEquals( 1, ksession.fireAllRules() );

        // Create a new jar for version 1.1.0
        ReleaseId releaseId2 = ks.newReleaseId("org.kie", "test-upgrade", "1.1.0");
        km = createAndDeployJar( ks, releaseId2, drl1, drl2_2 );

        // try to update the container to version 1.1.0
        kc.updateToVersion(releaseId2);
        
        // continue working with the session
        ksession.insert(new Message("Hello World"));
        assertEquals( 3, ksession.fireAllRules() );
    }

    public static KieModule createAndDeployJar(KieServices ks,
                                         ReleaseId releaseId,
                                         String... drls ) {
        byte[] jar = createKJar(ks, releaseId, null, drls);
        return deployJar(ks, jar);
    }

    @Test
    public void testDeletedFile() throws Exception {
        String drl1 = "package org.drools.compiler\n" +
                "rule R1 when\n" +
                "   $m : Message()\n" +
                "then\n" +
                "end\n";

        String drl2 = "package org.drools.compiler\n" +
                "rule R2_2 when\n" +
                "   $m : Message( message == \"Hello World\" )\n" +
                "then\n" +
                "end\n";

        KieServices ks = KieServices.Factory.get();

        // Create an in-memory jar for version 1.0.0
        ReleaseId releaseId1 = ks.newReleaseId("org.kie", "test-delete", "1.0.0");
        KieModule km = createAndDeployJar( ks, releaseId1, drl1, drl2 );

        KieContainer kieContainer = ks.newKieContainer(releaseId1);
        KieContainer kieContainer2 = ks.newKieContainer(releaseId1);

        KieSession ksession = kieContainer.newKieSession();
        ksession.insert(new Message("Hello World"));
        assertEquals( 2, ksession.fireAllRules() );

        ReleaseId releaseId2 = ks.newReleaseId("org.kie", "test-delete", "1.0.1");
        km = createAndDeployJar( ks, releaseId2, null, drl2 );

        kieContainer.updateToVersion(releaseId2);
        
        // test with the old ksession ...
        ksession.insert(new Message("Hello World"));
        assertEquals( 1, ksession.fireAllRules() );

        // ... and with a brand new one
        ksession = kieContainer.newKieSession();
        ksession.insert(new Message("Hello World"));
        assertEquals( 1, ksession.fireAllRules() );

        // check that the second kieContainer hasn't been affected by the update of the first one
        KieSession ksession2 = kieContainer2.newKieSession();
        ksession2.insert(new Message("Hello World"));
        assertEquals( 2, ksession2.fireAllRules() );
    }

    @Test
    public void testIncrementalCompilationWithAddedError() throws Exception {
        String drl1 = "package org.drools.compiler\n" +
                "rule R1 when\n" +
                "   $m : Message()\n" +
                "then\n" +
                "end\n";

        String drl2_1 = "package org.drools.compiler\n" +
                "rule R2_1 when\n" +
                "   $m : Message( message == \"Hi Universe\" )\n" +
                "then\n" +
                "end\n";

        String drl2_2 = "package org.drools.compiler\n" +
                "rule R2_2 when\n" +
                "   $m : Message( mesage == \"Hello World\" )\n" +
                "then\n" +
                "end\n";

        KieServices ks = KieServices.Factory.get();

        KieFileSystem kfs = ks.newKieFileSystem()
                .write("src/main/resources/r1.drl", drl1)
                .write("src/main/resources/r2.drl", drl2_1);

        KieBuilder kieBuilder = ks.newKieBuilder( kfs ).buildAll();
        KieContainer kieContainer = ks.newKieContainer(ks.getRepository().getDefaultReleaseId());

        KieSession ksession = kieContainer.newKieSession();
        ksession.insert(new Message("Hello World"));
        assertEquals( 1, ksession.fireAllRules() );

        kfs.write("src/main/resources/r2.drl", drl2_2);
        IncrementalResults results = ((InternalKieBuilder) kieBuilder).createFileSet("src/main/resources/r2.drl").build();

        assertEquals(2, results.getAddedMessages().size());
        assertEquals(0, results.getRemovedMessages().size());

        kieContainer.updateToVersion(ks.getRepository().getDefaultReleaseId());
        ksession = kieContainer.newKieSession();
        ksession.insert(new Message("Hello World"));
        assertEquals( 1, ksession.fireAllRules() );
    }

    @Test
    public void testIncrementalCompilationWithRemovedError() throws Exception {
        String drl1 = "package org.drools.compiler\n" +
                "rule R1 when\n" +
                "   $m : Message()\n" +
                "then\n" +
                "end\n";

        String drl2_1 = "package org.drools.compiler\n" +
                "rule R2_1 when\n" +
                "   $m : Message( mesage == \"Hello World\" )\n" +
                "then\n" +
                "end\n";

        String drl2_2 = "package org.drools.compiler\n" +
                "rule R2_2 when\n" +
                "   $m : Message( message == \"Hello World\" )\n" +
                "then\n" +
                "end\n";

        KieServices ks = KieServices.Factory.get();

        KieFileSystem kfs = ks.newKieFileSystem()
                .write("src/main/resources/r1.drl", drl1)
                .write("src/main/resources/r2.drl", drl2_1);

        KieBuilder kieBuilder = ks.newKieBuilder( kfs ).buildAll();
        assertEquals( 2, kieBuilder.getResults().getMessages(org.kie.api.builder.Message.Level.ERROR).size() );

        kfs.write("src/main/resources/r2.drl", drl2_2);
        IncrementalResults results = ((InternalKieBuilder) kieBuilder).createFileSet("src/main/resources/r2.drl").build();

        assertEquals( 0, results.getAddedMessages().size() );
        assertEquals( 2, results.getRemovedMessages().size() );

        KieContainer kieContainer = ks.newKieContainer(ks.getRepository().getDefaultReleaseId());
        KieSession ksession = kieContainer.newKieSession();
        ksession.insert(new Message("Hello World"));
        assertEquals( 2, ksession.fireAllRules() );
    }

    @Test
    public void testIncrementalCompilationAddErrorThenRemoveError() throws Exception {
        //Valid
        String drl1 = "package org.drools.compiler\n" +
                "rule R1 when\n" +
                "   $m : Message()\n" +
                "then\n" +
                "end\n";

        //Field is unknown ("mesage" not "message")
        String drl2_1 = "package org.drools.compiler\n" +
                "rule R2_1 when\n" +
                "   $m : Message( mesage == \"Hello World\" )\n" +
                "then\n" +
                "end\n";

        //Valid
        String drl2_2 = "package org.drools.compiler\n" +
                "rule R2_2 when\n" +
                "   $m : Message( message == \"Hello World\" )\n" +
                "then\n" +
                "end\n";

        KieServices ks = KieServices.Factory.get();

        KieFileSystem kfs = ks.newKieFileSystem()
                .write( "src/main/resources/r1.drl", drl1 );

        KieBuilder kieBuilder = ks.newKieBuilder( kfs ).buildAll();
        assertEquals( 0, kieBuilder.getResults().getMessages( org.kie.api.builder.Message.Level.ERROR ).size() );

        //Add file with error - expect 1 "added" error message
        kfs.write( "src/main/resources/r2.drl", drl2_1 );
        IncrementalResults addResults = ( (InternalKieBuilder) kieBuilder ).createFileSet( "src/main/resources/r2.drl" ).build();

        assertEquals( 2, addResults.getAddedMessages().size() );
        assertEquals( 0, addResults.getRemovedMessages().size() );

        //Update flawed file with correct version - expect 0 "added" error messages and removal of 1 previous error
        kfs.write( "src/main/resources/r2.drl", drl2_2 );
        IncrementalResults removeResults = ( (InternalKieBuilder) kieBuilder ).createFileSet( "src/main/resources/r2.drl" ).build();

        assertEquals( 0, removeResults.getAddedMessages().size() );
        assertEquals( 2, removeResults.getRemovedMessages().size() );
    }

    @Test
    public void testIncrementalCompilationAddTwoErrorsThenRemove1Error() throws Exception {
        //Fact Type is unknown ("Mesage" not "Message")
        String drl1 = "package org.drools.compiler\n" +
                "rule R1 when\n" +
                "   $m : Mesage()\n" +
                "then\n" +
                "end\n";

        //Field is unknown ("mesage" not "message")
        String drl2_1 = "package org.drools.compiler\n" +
                "rule R2_1 when\n" +
                "   $m : Message( mesage == \"Hello World\" )\n" +
                "then\n" +
                "end\n";

        //Valid
        String drl2_2 = "package org.drools.compiler\n" +
                "rule R2_2 when\n" +
                "   $m : Message( message == \"Hello World\" )\n" +
                "then\n" +
                "end\n";

        KieServices ks = KieServices.Factory.get();

        KieFileSystem kfs = ks.newKieFileSystem()
                .write( "src/main/resources/r1.drl", drl1 );

        //Initial file contains errors
        KieBuilder kieBuilder = ks.newKieBuilder( kfs ).buildAll();
        assertEquals( 1, kieBuilder.getResults().getMessages( org.kie.api.builder.Message.Level.ERROR ).size() );

        //Add file with error - expect 1 "added" error message
        kfs.write( "src/main/resources/r2.drl", drl2_1  );
        IncrementalResults addResults = ( (InternalKieBuilder) kieBuilder ).createFileSet( "src/main/resources/r2.drl" ).build();

        assertEquals( 2, addResults.getAddedMessages().size() );
        assertEquals( 0, addResults.getRemovedMessages().size() );

        //Update flawed file with correct version - expect 0 "added" error messages and removal of 1 previous error relating to updated file
        kfs.write( "src/main/resources/r2.drl", drl2_2 );
        IncrementalResults removeResults = ( (InternalKieBuilder) kieBuilder ).createFileSet( "src/main/resources/r2.drl" ).build();

        assertEquals( 0, removeResults.getAddedMessages().size() );
        assertEquals( 2, removeResults.getRemovedMessages().size() );
    }

    @Test
    public void testIncrementalCompilationWithDuplicatedRule() throws Exception {
        String drl1 = "package org.drools.compiler\n" +
                "rule R1 when\n" +
                "   $m : Message()\n" +
                "then\n" +
                "end\n";

        String drl2 = "package org.drools.compiler\n" +
                "rule R2 when\n" +
                "   $m : Message( message == \"Hello World\" )\n" +
                "then\n" +
                "end\n";

        KieServices ks = KieServices.Factory.get();

        KieFileSystem kfs = ks.newKieFileSystem()
                .write( "src/main/resources/r1.drl", drl1 );

        KieBuilder kieBuilder = ks.newKieBuilder( kfs ).buildAll();
        assertEquals( 0, kieBuilder.getResults().getMessages( org.kie.api.builder.Message.Level.ERROR ).size() );

        kfs.write( "src/main/resources/r2_1.drl", drl2  );
        IncrementalResults addResults = ( (InternalKieBuilder) kieBuilder ).createFileSet( "src/main/resources/r2_1.drl" ).build();

        assertEquals( 0, addResults.getAddedMessages().size() );
        assertEquals( 0, addResults.getRemovedMessages().size() );

        kfs.write( "src/main/resources/r2_2.drl", drl2 );
        IncrementalResults removeResults = ( (InternalKieBuilder) kieBuilder ).createFileSet( "src/main/resources/r2_2.drl" ).build();

        assertEquals( 1, removeResults.getAddedMessages().size() );
        assertEquals( 0, removeResults.getRemovedMessages().size() );
    }

    @Test
    public void testIncrementalCompilationWithDuplicatedRuleInSameDRL() throws Exception {
        String drl1 = "package org.drools.compiler\n" +
                "rule R1 when\n" +
                "   $m : Message()\n" +
                "then\n" +
                "end\n" +

                "rule R1 when\n" +
                "   $m : Message( message == \"Hello World\" )\n" +
                "then\n" +
                "end\n";

        KieServices ks = KieServices.Factory.get();

        KieFileSystem kfs = ks.newKieFileSystem()
                .write( "src/main/resources/r1.drl", drl1 );

        KieBuilder kieBuilder = ks.newKieBuilder( kfs ).buildAll();
        assertEquals( 1, kieBuilder.getResults().getMessages( org.kie.api.builder.Message.Level.ERROR ).size() );
    }

    @Test
    public void testIncrementalCompilationAddErrorBuildAllMessages() throws Exception {
        //Valid
        String drl1 = "package org.drools.compiler\n" +
                "rule R1 when\n" +
                "   $m : Message()\n" +
                "then\n" +
                "end\n";

        //Field is unknown ("mesage" not "message")
        String drl2_1 = "package org.drools.compiler\n" +
                "rule R2_1 when\n" +
                "   $m : Message( mesage == \"Hello World\" )\n" +
                "then\n" +
                "end\n";

        KieServices ks = KieServices.Factory.get();

        KieFileSystem kfs = ks.newKieFileSystem()
                .write( "src/main/resources/r1.drl", drl1 );

        KieBuilder kieBuilder = ks.newKieBuilder( kfs ).buildAll();
        assertEquals( 0, kieBuilder.getResults().getMessages( org.kie.api.builder.Message.Level.ERROR ).size() );

        //Add file with error - expect 1 "added" error message
        kfs.write( "src/main/resources/r2.drl", drl2_1 );
        IncrementalResults addResults = ( (InternalKieBuilder) kieBuilder ).createFileSet( "src/main/resources/r2.drl" ).build();

        assertEquals( 2, addResults.getAddedMessages().size() );
        assertEquals( 0, addResults.getRemovedMessages().size() );

        //Check errors on a full build
        assertEquals( 2, ks.newKieBuilder( kfs ).buildAll().getResults().getMessages().size() );
    }

    @Test
    public void testIncrementalCompilationAddErrorThenEmptyWithoutError() throws Exception {
        // BZ-1009369

        //Invalid. Type "Smurf" is unknown
        String drl1 = "Smurf";

        //Valid
        String drl2 = "package org.drools.compiler\n" +
                "rule R2_2 when\n" +
                "   $m : Message( message == \"Hello World\" )\n" +
                "then\n" +
                "end\n";

        KieServices ks = KieServices.Factory.get();

        //Add file with error - expect 2 build messages
        KieFileSystem kfs = ks.newKieFileSystem()
                .write( "src/main/resources/r1.drl", drl1 );

        KieBuilder kieBuilder = ks.newKieBuilder( kfs ).buildAll();
        assertEquals( 2, kieBuilder.getResults().getMessages( org.kie.api.builder.Message.Level.ERROR ).size() );

        //Add empty file - expect no "added" messages and no "removed" messages
        kfs.write( "src/main/resources/r2.drl",
                   "" );
        IncrementalResults addResults1 = ( (InternalKieBuilder) kieBuilder ).createFileSet( "src/main/resources/r2.drl" ).build();
        assertEquals( 0, addResults1.getAddedMessages().size() );
        assertEquals( 0, addResults1.getRemovedMessages().size() );

        //Update file with no errors - expect no "added" messages and no "removed" messages
        kfs.write( "src/main/resources/r2.drl",
                   drl2 );
        IncrementalResults addResults2 = ( (InternalKieBuilder) kieBuilder ).createFileSet( "src/main/resources/r2.drl" ).build();
        assertEquals( 0, addResults2.getAddedMessages().size() );
        assertEquals( 0, addResults2.getRemovedMessages().size() );
    }
    
    @Test
    public void testRuleRemoval() throws Exception {
        String drl1 = "package org.drools.compiler\n" +
                      "rule R1 when\n" +
                      "   $m : Message()\n" +
                      "then\n" +
                      "end\n";
 
        String drl2 = "rule R2 when\n" +
                        "   $m : Message( message == \"Hi Universe\" )\n" +
                        "then\n" +
                        "end\n";
 
        String drl3 = "rule R3 when\n" +
                        "   $m : Message( message == \"Hello World\" )\n" +
                        "then\n" +
                        "end\n";
 
        KieServices ks = KieServices.Factory.get();
 
        // Create an in-memory jar for version 1.0.0
        ReleaseId releaseId1 = ks.newReleaseId("org.kie", "test-upgrade", "1.0.0");
        KieModule km = createAndDeployJar(ks, releaseId1, drl1 + drl2 +drl3 );
 
        // Create a session and fire rules
        KieContainer kc = ks.newKieContainer(km.getReleaseId());
        KiePackage kpkg = ((KieContainerImpl) kc).getKieBase().getKiePackage( "org.drools.compiler");
        assertEquals( 3,  kpkg.getRules().size() );
        Map<String, Rule> rules = rulestoMap( kpkg.getRules() );
 
 
        assertNotNull(((org.drools.core.definitions.rule.impl.RuleImpl) rules.get("R1")));
        assertNotNull(((org.drools.core.definitions.rule.impl.RuleImpl) rules.get("R2")));
        assertNotNull(((org.drools.core.definitions.rule.impl.RuleImpl) rules.get("R3")));
 
        RuleBase rb_1 = ((InternalRuleBase) ((KnowledgeBaseImpl) kc.getKieBase()).getRuleBase());
 
        RuleTerminalNode rtn1_1  = (RuleTerminalNode) ((InternalRuleBase) ((KnowledgeBaseImpl)kc.getKieBase()).getRuleBase()).getReteooBuilder().getTerminalNodes( "R1" )[0];
        RuleTerminalNode rtn2_1  = (RuleTerminalNode) ((InternalRuleBase) ((KnowledgeBaseImpl)kc.getKieBase()).getRuleBase()).getReteooBuilder().getTerminalNodes( "R2" )[0];
        RuleTerminalNode rtn3_1  = (RuleTerminalNode) ((InternalRuleBase) ((KnowledgeBaseImpl)kc.getKieBase()).getRuleBase()).getReteooBuilder().getTerminalNodes( "R3" )[0];
 
        // Create a new jar for version 1.1.0
        ReleaseId releaseId2 = ks.newReleaseId("org.kie", "test-upgrade", "1.1.0");
        km = createAndDeployJar( ks, releaseId2, drl1 + drl3 );
 
        // try to update the container to version 1.1.0
        kc.updateToVersion(releaseId2);
 
        InternalRuleBase rb_2 = ((InternalRuleBase) ((KnowledgeBaseImpl) kc.getKieBase()).getRuleBase());
        assertSame ( rb_1, rb_2 );
 
        RuleTerminalNode rtn1_2  = (RuleTerminalNode) rb_2.getReteooBuilder().getTerminalNodes( "R1" )[0];
        RuleTerminalNode rtn3_2  = (RuleTerminalNode) rb_2.getReteooBuilder().getTerminalNodes( "R3" )[0];
        assertNull( rb_2.getReteooBuilder().getTerminalNodes( "R2" ) );
 
        assertSame( rtn3_1, rtn3_2 );
        assertSame( rtn1_1, rtn1_2 );
 
        kpkg = ((KieContainerImpl) kc).getKieBase().getKiePackage( "org.drools.compiler");
        assertEquals( 2,  kpkg.getRules().size() );
        rules = rulestoMap( kpkg.getRules() );
 
        assertNotNull( ((org.drools.core.definitions.rule.impl.RuleImpl ) rules.get( "R1" )) );
        assertNull(((org.drools.core.definitions.rule.impl.RuleImpl) rules.get("R2")));
        assertNotNull(((org.drools.core.definitions.rule.impl.RuleImpl) rules.get("R3")));
    }

    private Map<String, Rule> rulestoMap(Collection<Rule> rules) {
        Map<String, Rule> ret = new HashMap<String, Rule>();
        for( Rule rule : rules ) {
            ret.put( rule.getName(), rule );
        }
        return ret;
    }

    @Test
    public void testIncrementalCompilationWithSnapshots() throws Exception {
        // DROOLS-358
        ReleaseId releaseId = KieServices.Factory.get().newReleaseId( "org.test", "test", "1.0.0-SNAPSHOT" );
        testIncrementalCompilation(releaseId, releaseId);
    }

    @Test
    public void testIncrementalCompilationWithFixedVersions() throws Exception {
        // DROOLS-358
        ReleaseId releaseId1 = KieServices.Factory.get().newReleaseId( "org.test", "test", "1.0.1" );
        ReleaseId releaseId2 = KieServices.Factory.get().newReleaseId( "org.test", "test", "1.0.2" );
        testIncrementalCompilation(releaseId1, releaseId2);
    }

    private void testIncrementalCompilation(ReleaseId releaseId1, ReleaseId releaseId2) {
        String drl1 = "package org.drools.compiler\n" +
                      "global java.util.List list\n" +
                      "rule R0 when then list.add( \"000\" ); end \n" +
                      "" +
                      "rule R1 when\n" +
                      " $s : String() " +
                      "then\n" +
                      " list.add( \"a\" + $s );" +
                      "end\n";

        String drl2 = "package org.drools.compiler\n" +
                      "global java.util.List list\n" +
                      "rule R2 when\n" +
                      " $s : String() \n" +
                      "then\n" +
                      " list.add( \"b\" + $s );" +
                      "end\n";

        KieServices ks = KieServices.Factory.get();
        KieFileSystem kfs = ks.newKieFileSystem();

        KieBuilder kieBuilder = ks.newKieBuilder( kfs );

        kfs.generateAndWritePomXML( releaseId1 );
        kfs.write( ks.getResources()
                     .newReaderResource( new StringReader(drl1) )
                     .setResourceType(ResourceType.DRL)
                     .setSourcePath("drl1.txt") );

        kieBuilder.buildAll();
        KieModule kieModule = kieBuilder.getKieModule();
        assertEquals(releaseId1, kieModule.getReleaseId());

        KieContainer kc = ks.newKieContainer( releaseId1 );

        KieSession ksession = kc.newKieSession();
        List<String> list = new ArrayList<String>();
        ksession.setGlobal("list", list);
        ksession.insert("Foo");
        ksession.fireAllRules();

        assertEquals(2, list.size());
        assertTrue(list.containsAll(asList("000", "aFoo")));
        list.clear();

        kfs.generateAndWritePomXML( releaseId2 );
        kfs.write( ks.getResources()
                     .newReaderResource( new StringReader(drl2) )
                     .setResourceType(ResourceType.DRL)
                     .setSourcePath("drl2.txt") );

        IncrementalResults results = ((InternalKieBuilder) kieBuilder).incrementalBuild();
        assertEquals(0, results.getAddedMessages().size());

        kieModule = kieBuilder.getKieModule();
        assertEquals(releaseId2, kieModule.getReleaseId());

        kc.updateToVersion( releaseId2 );

        ksession.insert( "Bar" );
        ksession.fireAllRules();

        assertEquals(3, list.size());
        assertTrue(list.containsAll(asList("bBar", "bFoo", "aBar")));
    }
}
