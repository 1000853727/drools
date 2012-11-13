package org.drools.agent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.drools.Person;
import org.junit.Test;
import org.kie.KnowledgeBase;
import org.kie.KnowledgeBaseFactory;
import org.kie.agent.KnowledgeAgent;
import org.kie.event.knowledgeagent.AfterChangeSetAppliedEvent;
import org.kie.event.knowledgeagent.AfterChangeSetProcessedEvent;
import org.kie.event.knowledgeagent.AfterResourceProcessedEvent;
import org.kie.event.knowledgeagent.BeforeChangeSetAppliedEvent;
import org.kie.event.knowledgeagent.BeforeChangeSetProcessedEvent;
import org.kie.event.knowledgeagent.BeforeResourceProcessedEvent;
import org.kie.event.knowledgeagent.KnowledgeAgentEventListener;
import org.kie.event.knowledgeagent.KnowledgeBaseUpdatedEvent;
import org.kie.event.knowledgeagent.ResourceCompilationFailedEvent;
import org.kie.io.ResourceFactory;
import org.kie.runtime.StatefulKnowledgeSession;

public class KnowledgeAgentEventListenerTest extends BaseKnowledgeAgentTest {

    private volatile boolean changeSetApplied;
    private volatile boolean compilationErrors;
    private volatile boolean kbaseUpdated;
    private volatile int     beforeChangeSetProcessed;
    private volatile int     afterChangeSetProcessed;
    private volatile int     beforeChangeSetApplied;
    private volatile int     afterChangeSetApplied;
    private volatile int     beforeResourceProcessed;
    private volatile int     afterResourceProcessed;

    @Test
    public void testEventListenerWithIncrementalChangeSet() throws Exception {
        this.resetEventCounters();
        fileManager.write( "myExpander.dsl",
                           this.createCommonDSL( null ) );

        fileManager.write( "rules.drl",
                           createCommonDSLRRule( "Rule1" ) );

        String xml = "";
        xml += "<change-set xmlns='http://drools.org/drools-5.0/change-set'";
        xml += "    xmlns:xs='http://www.w3.org/2001/XMLSchema-instance'";
        xml += "    xs:schemaLocation='http://drools.org/drools-5.0/change-set http://anonsvn.jboss.org/repos/labs/labs/jbossrules/trunk/drools-api/src/main/resources/change-set-1.0.0.xsd' >";
        xml += "    <add> ";
        xml += "        <resource source='http://localhost:" + this.getPort() + "/rules.drl' type='DSLR' />";
        xml += "        <resource source='http://localhost:" + this.getPort() + "/myExpander.dsl' type='DSL' />";
        xml += "    </add> ";
        xml += "</change-set>";
        File fxml = fileManager.write( "changeset.xml",
                                       xml );

        List<String> list = new ArrayList<String>();

        //Create a new Agent with newInstance=true
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        KnowledgeAgent kagent = this.createKAgent( kbase,
                                                   false );

        StatefulKnowledgeSession ksession = createKnowledgeSession(kbase);

        //Agent: take care of them!
        applyChangeSet( kagent,
                        ResourceFactory.newUrlResource( fxml.toURI().toURL() ) );

        assertEquals( 1,
                      this.beforeChangeSetApplied );
        assertEquals( 1,
                      this.afterChangeSetApplied );
        assertEquals( 1,
                      this.beforeChangeSetProcessed );
        assertEquals( 1,
                      this.afterChangeSetProcessed );
        assertEquals( 2,
                      this.beforeResourceProcessed );
        assertEquals( 2,
                      this.afterResourceProcessed );
        assertFalse( this.compilationErrors );
        assertTrue( this.kbaseUpdated );
        this.resetEventCounters();

        ksession.setGlobal( "list",
                            list );
        ksession.insert( new Person( "John", 34 ) );
        ksession.fireAllRules();

        assertEquals( 1,
                      list.size() );
        assertTrue( list.contains( "Rule1" ) );
        list.clear();

        File f2 = fileManager.write( "myExpander.dsl",
                                     this.createCommonDSL( "name == \"John\"" ) );

        fileManager.write( "rules.drl",
                           createCommonDSLRRule( "Rule1" ) );

        scan( kagent );

        assertEquals( 1,
                      this.beforeChangeSetApplied );
        assertEquals( 1,
                      this.afterChangeSetApplied );
        assertEquals( 1,
                      this.beforeChangeSetProcessed );
        assertEquals( 1,
                      this.afterChangeSetProcessed );
        assertEquals( 2,
                      this.beforeResourceProcessed );
        assertEquals( 2,
                      this.afterResourceProcessed );
        assertFalse( this.compilationErrors );
        assertTrue( this.kbaseUpdated );
        this.resetEventCounters();

        ksession.fireAllRules();
        assertEquals( 1,
                      list.size() );
        assertTrue( list.contains( "Rule1" ) );
        list.clear();

        fileManager.write( "rules.drl",
                           createCommonDSLRRule( new String[]{"Rule1", "Rule2"} ) );

        scan( kagent );

        assertEquals( 1,
                      this.beforeChangeSetApplied );
        assertEquals( 1,
                      this.afterChangeSetApplied );
        assertEquals( 1,
                      this.beforeChangeSetProcessed );
        assertEquals( 1,
                      this.afterChangeSetProcessed );
        assertEquals( 1,
                      this.beforeResourceProcessed );
        assertEquals( 1,
                      this.afterResourceProcessed );
        assertFalse( this.compilationErrors );
        assertTrue( this.kbaseUpdated );
        this.resetEventCounters();

        ksession.fireAllRules();
        assertEquals( 1,
                      list.size() );
        assertTrue( list.contains( "Rule2" ) );
        list.clear();

        //let's remove Rule1 and Rule2 and add a new rule: Rule3
        fileManager.write( "rules.drl",
                           createCommonDSLRRule( "Rule3" ) );
        scan( kagent );

        assertEquals( 1,
                      this.beforeChangeSetApplied );
        assertEquals( 1,
                      this.afterChangeSetApplied );
        assertEquals( 1,
                      this.beforeChangeSetProcessed );
        assertEquals( 1,
                      this.afterChangeSetProcessed );
        assertEquals( 1,
                      this.beforeResourceProcessed );
        assertEquals( 1,
                      this.afterResourceProcessed );
        assertFalse( this.compilationErrors );
        assertTrue( this.kbaseUpdated );
        this.resetEventCounters();

        ksession.fireAllRules();
        assertEquals( 1,
                      list.size() );
        assertTrue( list.contains( "Rule3" ) );
        list.clear();

        //let's delete the dsl file (errors are expected)
        this.fileManager.deleteFile( f2 );
        scan( kagent );

        fileManager.write( "rules.drl",
                           createCommonDSLRRule( "Rule1" ) );
        try{
            scan( kagent );
            fail("An exception was expected");
        } catch (Exception e){
            //We have 2 listeners: one defined in this.createKAgent() and another
            //that is dinamically set in scan() method. The later will throw
            //this exception
        }
        assertEquals( 2,
                      this.beforeChangeSetApplied );
        assertEquals( 2,
                      this.afterChangeSetApplied );
        assertEquals( 2,
                      this.beforeChangeSetProcessed );
        assertEquals( 2,
                      this.afterChangeSetProcessed );
        assertEquals( 2,
                      this.beforeResourceProcessed );
        assertEquals( 2,
                      this.afterResourceProcessed );
        assertTrue( this.compilationErrors );
        assertTrue( this.kbaseUpdated );
        this.resetEventCounters();

        ksession.dispose();
        kagent.dispose();
    }

    @Test
    public void testEventListenerWithoutIncrementalChangeSet() throws Exception {
        this.resetEventCounters();
        fileManager.write( "myExpander.dsl",
                           this.createCommonDSL( null ) );

        fileManager.write( "rules.drl",
                           createCommonDSLRRule( "Rule1" ) );

        String xml = "";
        xml += "<change-set xmlns='http://drools.org/drools-5.0/change-set'";
        xml += "    xmlns:xs='http://www.w3.org/2001/XMLSchema-instance'";
        xml += "    xs:schemaLocation='http://drools.org/drools-5.0/change-set http://anonsvn.jboss.org/repos/labs/labs/jbossrules/trunk/drools-api/src/main/resources/change-set-1.0.0.xsd' >";
        xml += "    <add> ";
        xml += "        <resource source='http://localhost:" + this.getPort() + "/rules.drl' type='DSLR' />";
        xml += "        <resource source='http://localhost:" + this.getPort() + "/myExpander.dsl' type='DSL' />";
        xml += "    </add> ";
        xml += "</change-set>";
        File fxml = fileManager.write( "changeset.xml",
                                       xml );

        List<String> list = new ArrayList<String>();

        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();

        //Create a new Agent with newInstance=true
        KnowledgeAgent kagent = this.createKAgent( kbase,
                                                   false );

        //Agent: take care of them!
        applyChangeSet( kagent,
                        ResourceFactory.newUrlResource( fxml.toURI().toURL() ) );

        assertEquals( 1,
                      this.beforeChangeSetApplied );
        assertEquals( 1,
                      this.afterChangeSetApplied );
        assertEquals( 1,
                      this.beforeChangeSetProcessed );
        assertEquals( 1,
                      this.afterChangeSetProcessed );
        assertEquals( 2,
                      this.beforeResourceProcessed );
        assertEquals( 2,
                      this.afterResourceProcessed );
        assertFalse( this.compilationErrors );
        assertTrue( this.kbaseUpdated );
        this.resetEventCounters();

        StatefulKnowledgeSession ksession = createKnowledgeSession(kagent.getKnowledgeBase());
        ksession.setGlobal( "list",
                            list );
        ksession.insert( new Person( "John", 34  ) );
        ksession.fireAllRules();
        ksession.dispose();
        assertEquals( 1,
                      list.size() );
        assertTrue( list.contains( "Rule1" ) );
        list.clear();

        File f2 = fileManager.write( "myExpander.dsl",
                                     this.createCommonDSL( "name == \"John\"" ) );

        fileManager.write( "rules.drl",
                           createCommonDSLRRule( "Rule1" ) );

        scan( kagent );

        ksession = createKnowledgeSession(kagent.getKnowledgeBase());
        ksession.setGlobal( "list",
                            list );
        ksession.insert( new Person( "John", 34  ) );
        ksession.fireAllRules();
        ksession.dispose();
        assertEquals( 1,
                      list.size() );
        assertTrue( list.contains( "Rule1" ) );
        list.clear();

        assertEquals( 1,
                      this.beforeChangeSetApplied );
        assertEquals( 1,
                      this.afterChangeSetApplied );
        assertEquals( 1,
                      this.beforeChangeSetProcessed );
        assertEquals( 1,
                      this.afterChangeSetProcessed );
        assertEquals( 2,
                      this.beforeResourceProcessed );
        assertEquals( 2,
                      this.afterResourceProcessed );
        assertFalse( this.compilationErrors );
        assertTrue( this.kbaseUpdated );
        this.resetEventCounters();

        ksession = createKnowledgeSession(kagent.getKnowledgeBase());
        ksession.setGlobal( "list",
                            list );
        ksession.insert( new Person( "John", 34  ) );
        ksession.fireAllRules();
        ksession.dispose();
        assertEquals( 1,
                      list.size() );
        assertTrue( list.contains( "Rule1" ) );
        list.clear();

        fileManager.write( "rules.drl",
                           createCommonDSLRRule( new String[]{"Rule1", "Rule2"} ) );

        scan( kagent );

        assertEquals( 1,
                      this.beforeChangeSetApplied );
        assertEquals( 1,
                      this.afterChangeSetApplied );
        assertEquals( 1,
                      this.beforeChangeSetProcessed );
        assertEquals( 1,
                      this.afterChangeSetProcessed );
        assertEquals( 1,
                      this.beforeResourceProcessed );
        assertEquals( 1,
                      this.afterResourceProcessed );
        assertFalse( this.compilationErrors );
        assertTrue( this.kbaseUpdated );
        this.resetEventCounters();

        ksession = createKnowledgeSession(kagent.getKnowledgeBase());
        ksession.setGlobal( "list",
                            list );
        ksession.insert( new Person( "John", 34  ) );
        ksession.fireAllRules();
        ksession.dispose();
        assertEquals( 2,
                      list.size() );
        assertTrue( list.contains( "Rule1" ) );
        assertTrue( list.contains( "Rule2" ) );
        list.clear();

        //let's remove Rule1 and Rule2 and add a new rule: Rule3
        fileManager.write( "rules.drl",
                           createCommonDSLRRule( "Rule3" ) );
        scan( kagent );

        assertEquals( 1,
                      this.beforeChangeSetApplied );
        assertEquals( 1,
                      this.afterChangeSetApplied );
        assertEquals( 1,
                      this.beforeChangeSetProcessed );
        assertEquals( 1,
                      this.afterChangeSetProcessed );
        assertEquals( 1,
                      this.beforeResourceProcessed );
        assertEquals( 1,
                      this.afterResourceProcessed );
        assertFalse( this.compilationErrors );
        assertTrue( this.kbaseUpdated );
        this.resetEventCounters();

        ksession = createKnowledgeSession(kagent.getKnowledgeBase());
        ksession.setGlobal( "list",
                            list );
        ksession.insert( new Person( "John", 34  ) );
        ksession.fireAllRules();
        ksession.dispose();
        assertEquals( 1,
                      list.size() );
        assertTrue( list.contains( "Rule3" ) );
        list.clear();

        //let's delete the dsl file (errors are expected)
        this.fileManager.deleteFile( f2 );
        scan( kagent );

        fileManager.write( "rules.drl",
                           createCommonDSLRRule( "Rule1" ) );
        
        try{
            scan( kagent );
            fail("An exception was expected");
        } catch (Exception e){
            //We have 2 listeners: one defined in this.createKAgent() and another
            //that is dinamically set in scan() method. The later will throw
            //this exception
        }
        
        
        assertEquals( 2,
                      this.beforeChangeSetApplied );
        assertEquals( 2,
                      this.afterChangeSetApplied );
        assertEquals( 2,
                      this.beforeChangeSetProcessed );
        assertEquals( 2,
                      this.afterChangeSetProcessed );
        assertEquals( 2,
                      this.beforeResourceProcessed );
        assertEquals( 2,
                      this.afterResourceProcessed );
        assertTrue( this.compilationErrors );
        assertTrue( this.kbaseUpdated );
        this.resetEventCounters();

        ksession.dispose();
        kagent.dispose();
    }

    @Override
    public KnowledgeAgent createKAgent(KnowledgeBase kbase,
                                       boolean newInstance) {
        KnowledgeAgent kagent = super.createKAgent( kbase,
                                                    newInstance );

        kagent.addEventListener( new KnowledgeAgentEventListener() {

            public void beforeChangeSetApplied(BeforeChangeSetAppliedEvent event) {
                beforeChangeSetApplied++;
            }

            public synchronized void afterChangeSetApplied(AfterChangeSetAppliedEvent event) {
                afterChangeSetApplied++;
                changeSetApplied = true;
            }

            public void beforeChangeSetProcessed(BeforeChangeSetProcessedEvent event) {
                beforeChangeSetProcessed++;
            }

            public void afterChangeSetProcessed(AfterChangeSetProcessedEvent event) {
                afterChangeSetProcessed++;
            }

            public void beforeResourceProcessed(BeforeResourceProcessedEvent event) {
                beforeResourceProcessed++;
            }

            public void afterResourceProcessed(AfterResourceProcessedEvent event) {
                afterResourceProcessed++;
            }

            public void knowledgeBaseUpdated(KnowledgeBaseUpdatedEvent event) {
                kbaseUpdated = true;
            }

            public void resourceCompilationFailed(ResourceCompilationFailedEvent event) {
                compilationErrors = true;
                System.out.println( event.getKnowledgeBuilder().getErrors().toString() );
            }
        } );

        assertEquals( "test agent",
                      kagent.getName() );

        return kagent;
    }

    private void resetEventCounters() {
        this.beforeChangeSetApplied = 0;
        this.beforeChangeSetProcessed = 0;
        this.beforeResourceProcessed = 0;
        this.afterChangeSetApplied = 0;
        this.afterChangeSetProcessed = 0;
        this.afterResourceProcessed = 0;
        this.compilationErrors = false;
        this.changeSetApplied = false;
        this.kbaseUpdated = false;
    }
}
