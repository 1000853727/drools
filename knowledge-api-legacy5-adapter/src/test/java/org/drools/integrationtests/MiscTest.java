package org.drools.integrationtests;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseConfiguration;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderConfiguration;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.KnowledgeSessionConfiguration;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.conf.ClockTypeOption;
import org.drools.runtime.rule.WorkingMemoryEntryPoint;
import org.drools.time.SessionPseudoClock;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.fail;

public class MiscTest {

    protected KnowledgeBase loadKnowledgeBaseFromString(String... drlContentStrings) {
        return loadKnowledgeBaseFromString( null, null, drlContentStrings );
    }

    protected KnowledgeBase loadKnowledgeBaseFromString(KnowledgeBuilderConfiguration config,
                                                        String... drlContentStrings) {
        return loadKnowledgeBaseFromString( config, null, drlContentStrings );
    }

    protected KnowledgeBase loadKnowledgeBaseFromString(KnowledgeBaseConfiguration kBaseConfig,
                                                        String... drlContentStrings) {
        return loadKnowledgeBaseFromString( null, kBaseConfig, drlContentStrings );
    }

    protected KnowledgeBase loadKnowledgeBaseFromString(KnowledgeBuilderConfiguration config,
                                                        KnowledgeBaseConfiguration kBaseConfig,
                                                        String... drlContentStrings) {
        KnowledgeBuilder kbuilder = config == null ? KnowledgeBuilderFactory.newKnowledgeBuilder() : KnowledgeBuilderFactory.newKnowledgeBuilder( config );
        for ( String drlContentString : drlContentStrings ) {
            kbuilder.add( ResourceFactory.newByteArrayResource(drlContentString.getBytes()),
                          ResourceType.DRL );
        }

        if ( kbuilder.hasErrors() ) {
            fail( kbuilder.getErrors().toString() );
        }
        if ( kBaseConfig == null ) {
            kBaseConfig = KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
        }
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase( kBaseConfig );
        kbase.addKnowledgePackages( kbuilder.getKnowledgePackages() );
        return kbase;
    }

    @Test
    public void testNullValueInFrom() {
        // DROOLS-71
        String str =
                "global java.util.List list\n" +
                "\n" +
                "rule R\n" +
                "when\n" +
                "    $i : Integer( ) from list\n" +
                "then\n" +
                "end";

        KnowledgeBase kbase = loadKnowledgeBaseFromString(str);
        StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();

        List<Integer> list = new ArrayList<Integer>();
        ksession.setGlobal("list", list);

        list.add(1);
        list.add(null);
        list.add(2);

        ksession.fireAllRules();
    }

    @Test
    public void testErrorReporting() {
        String str =
                "global java.util.List list\n" +
                "\n" +
                "rule R\n" +
                "when\n" +
                "    $i : Intege( ) from list\n" +
                "then\n" +
                "end";

        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add( ResourceFactory.newByteArrayResource(str.getBytes()),
                      ResourceType.DRL );

        assertTrue(kbuilder.hasErrors());
        assertTrue(kbuilder.getErrors().toString().contains("Intege"));
    }

    @Test
    public void testUsingSessionClock() {
        //BZ-1058687
        String str =
                "global java.util.List list\n" +
                "\n" +
                "rule R\n" +
                "when\n" +
                "    $i : Integer( ) from list\n" +
                "then\n" +
                "end";

        KnowledgeBase kbase = loadKnowledgeBaseFromString(str);
        KnowledgeSessionConfiguration ksconfig = KnowledgeBaseFactory.newKnowledgeSessionConfiguration();
        ksconfig.setOption(ClockTypeOption.get("pseudo"));
        StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession(ksconfig, null);

        // compilation fails on Java 6
        SessionPseudoClock clock = ksession.getSessionClock();
    }

    @Test
    public void testUsingNullConfiguration() {
        String str =
                "global java.util.List list\n" +
                "\n" +
                "rule R\n" +
                "when\n" +
                "    $i : Integer( ) from list\n" +
                "then\n" +
                "end";

        KnowledgeBase kbase = loadKnowledgeBaseFromString(str);
        StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession(null, null);

    }
    
    @Test
    public void testConsequenceException() {
        String str =
                "package foo.bar\n" +
                "rule R\n" +
                "when\n" +
                "then\n" +
                "    throw new RuntimeException(\"foo\");" +
                "end";

        KnowledgeBase kbase = loadKnowledgeBaseFromString(str);
        StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
        
        try {
            ksession.fireAllRules();
        } catch( org.drools.runtime.rule.ConsequenceException e ) {
            // this is correct, succeeds
        } catch( Exception other) {
            fail("Wrong exception raised = "+other.getClass().getCanonicalName());
        }
    }

    @Test
    public void testNotExistingEntryPoint() {
        // BZ-1099767
        String str =
                "package foo.bar\n" +
                "rule R\n" +
                "when\n" +
                "then\n" +
                "end";

        KnowledgeBase kbase = loadKnowledgeBaseFromString(str);
        StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();

        WorkingMemoryEntryPoint entryPoint = ksession.getWorkingMemoryEntryPoint("x");
        assertNull(entryPoint);
    }

}
