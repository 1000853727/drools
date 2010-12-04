package org.drools.agent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.builder.impl.KnowledgeBuilderImpl;
import org.drools.command.runtime.rule.InsertObjectCommand;
import org.drools.core.util.DroolsStreamUtils;
import org.drools.core.util.FileManager;
import org.drools.core.util.IoUtils;
import org.drools.definition.KnowledgePackage;
import org.drools.event.knowledgeagent.AfterChangeSetAppliedEvent;
import org.drools.event.knowledgeagent.AfterChangeSetProcessedEvent;
import org.drools.event.knowledgeagent.AfterResourceProcessedEvent;
import org.drools.event.knowledgeagent.BeforeChangeSetAppliedEvent;
import org.drools.event.knowledgeagent.BeforeChangeSetProcessedEvent;
import org.drools.event.knowledgeagent.BeforeResourceProcessedEvent;
import org.drools.event.knowledgeagent.KnowledgeAgentEventListener;
import org.drools.event.knowledgeagent.KnowledgeBaseUpdatedEvent;
import org.drools.event.knowledgeagent.ResourceCompilationFailedEvent;
import org.drools.io.Resource;
import org.drools.io.ResourceFactory;
import org.drools.io.impl.ResourceChangeNotifierImpl;
import org.drools.io.impl.ResourceChangeScannerImpl;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.StatelessKnowledgeSession;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.ResourceHandler;

public class KnowledgeAgentTest extends TestCase {

    FileManager              fileManager;
    private Server           server;
    
    private ResourceChangeScannerImpl scanner;

    @Override
    protected void setUp() throws Exception {
        this.fileManager = new FileManager();
        this.fileManager.setUp();
        ((ResourceChangeScannerImpl) ResourceFactory.getResourceChangeScannerService()).reset();

        ResourceFactory.getResourceChangeNotifierService().start();
        
        // we don't start the scanner, as we call it manually;
        this.scanner = (ResourceChangeScannerImpl) ResourceFactory.getResourceChangeScannerService();

        this.server = new Server( IoUtils.findPort() );
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase( fileManager.getRootDirectory().getPath() );

        this.server.setHandler( resourceHandler );

        this.server.start();
    }
    
    @Override
    protected void tearDown() throws Exception {
        fileManager.tearDown();
        ResourceFactory.getResourceChangeNotifierService().stop();
        ((ResourceChangeNotifierImpl) ResourceFactory.getResourceChangeNotifierService()).reset();
        ((ResourceChangeScannerImpl) ResourceFactory.getResourceChangeScannerService()).reset();

        server.stop();
    }    

    private int getPort() {
        return this.server.getConnectors()[0].getLocalPort();
    }
    
    private void scan(KnowledgeAgent kagent) {
        // Calls the Resource Scanner and sets up a listener and a latch so we can wait until it's finished processing, instead of using timers
        final CountDownLatch latch = new CountDownLatch( 1 );
        
        KnowledgeAgentEventListener l = new KnowledgeAgentEventListener() {
            
            public void resourceCompilationFailed(ResourceCompilationFailedEvent event) {
            }
            
            public void knowledgeBaseUpdated(KnowledgeBaseUpdatedEvent event) {
            }
            
            public void beforeResourceProcessed(BeforeResourceProcessedEvent event) {
            }
            
            public void beforeChangeSetProcessed(BeforeChangeSetProcessedEvent event) {                              
            }
            
            public void beforeChangeSetApplied(BeforeChangeSetAppliedEvent event) {
            }
            
            public void afterResourceProcessed(AfterResourceProcessedEvent event) {
            }
            
            public void afterChangeSetProcessed(AfterChangeSetProcessedEvent event) {
            }
            
            public void afterChangeSetApplied(AfterChangeSetAppliedEvent event) {
                latch.countDown();
            }
        };        
        
        kagent.addEventListener( l );
        
        this.scanner.scan();
        
        try {
            latch.await( 10, TimeUnit.SECONDS );
        } catch ( InterruptedException e ) {
            throw new RuntimeException( "Unable to wait for latch countdown", e);
        }
        
        if ( latch.getCount() > 0 ) {            
            throw new RuntimeException( "Event for KnowlegeBase update, due to scan, was never received" );
        }
        
        kagent.removeEventListener( l );
    }
    
    void applyChangeSet(KnowledgeAgent kagent, String xml) {
        // Calls the Resource Scanner and sets up a listener and a latch so we can wait until it's finished processing, instead of using timers
        final CountDownLatch latch = new CountDownLatch( 1 );
        
        KnowledgeAgentEventListener l = new KnowledgeAgentEventListener() {
            
            public void resourceCompilationFailed(ResourceCompilationFailedEvent event) {
            }
            
            public void knowledgeBaseUpdated(KnowledgeBaseUpdatedEvent event) {
            }
            
            public void beforeResourceProcessed(BeforeResourceProcessedEvent event) {
            }
            
            public void beforeChangeSetProcessed(BeforeChangeSetProcessedEvent event) {                              
            }
            
            public void beforeChangeSetApplied(BeforeChangeSetAppliedEvent event) {
            }
            
            public void afterResourceProcessed(AfterResourceProcessedEvent event) {
            }
            
            public void afterChangeSetProcessed(AfterChangeSetProcessedEvent event) {
            }
            
            public void afterChangeSetApplied(AfterChangeSetAppliedEvent event) {
                latch.countDown();
            }
        };        
        
        kagent.addEventListener( l );
        
        kagent.applyChangeSet( ResourceFactory.newByteArrayResource( xml.getBytes() ) );
        
        try {
            latch.await( 10, TimeUnit.SECONDS );
        } catch ( InterruptedException e ) {
            throw new RuntimeException( "Unable to wait for latch countdown", e);
        }
        
        if ( latch.getCount() > 0 ) {            
            throw new RuntimeException( "Event for KnowlegeBase update, due to scan, was never received" );
        }
        
        kagent.removeEventListener( l );        
    }
    
    void applyChangeSet(KnowledgeAgent kagent, Resource r) {
        // Calls the Resource Scanner and sets up a listener and a latch so we can wait until it's finished processing, instead of using timers
        final CountDownLatch latch = new CountDownLatch( 1 );
        
        KnowledgeAgentEventListener l = new KnowledgeAgentEventListener() {
            
            public void resourceCompilationFailed(ResourceCompilationFailedEvent event) {
            }
            
            public void knowledgeBaseUpdated(KnowledgeBaseUpdatedEvent event) {
            }
            
            public void beforeResourceProcessed(BeforeResourceProcessedEvent event) {
            }
            
            public void beforeChangeSetProcessed(BeforeChangeSetProcessedEvent event) {                              
            }
            
            public void beforeChangeSetApplied(BeforeChangeSetAppliedEvent event) {
            }
            
            public void afterResourceProcessed(AfterResourceProcessedEvent event) {
            }
            
            public void afterChangeSetProcessed(AfterChangeSetProcessedEvent event) {
            }
            
            public void afterChangeSetApplied(AfterChangeSetAppliedEvent event) {
                latch.countDown();
            }
        };        
        
        kagent.addEventListener( l );
        
        kagent.applyChangeSet( r );
        
        try {
            latch.await( 10, TimeUnit.SECONDS );
        } catch ( InterruptedException e ) {
            throw new RuntimeException( "Unable to wait for latch countdown", e);
        }
        
        if ( latch.getCount() > 0 ) {            
            throw new RuntimeException( "Event for KnowlegeBase update, due to scan, was never received" );
        }
        
        kagent.removeEventListener( l );        
    }    


    public void testModifyFileUrl() throws Exception {
        fileManager.write( "rule1.drl",
                           createDefaultRule( "rule1" ) );        
        
        fileManager.write( "rule2.drl",
                           createDefaultRule( "rule2" ) );
        
        String xml = "";
        xml += "<change-set xmlns='http://drools.org/drools-5.0/change-set'";
        xml += "    xmlns:xs='http://www.w3.org/2001/XMLSchema-instance'";
        xml += "    xs:schemaLocation='http://drools.org/drools-5.0/change-set http://anonsvn.jboss.org/repos/labs/labs/jbossrules/trunk/drools-api/src/main/resources/change-set-1.0.0.xsd' >";
        xml += "    <add> ";
        xml += "        <resource source='http://localhost:" + this.getPort() + "/rule1.drl' type='DRL' />";
        xml += "        <resource source='http://localhost:" + this.getPort() + "/rule2.drl' type='DRL' />";
        xml += "    </add> ";
        xml += "</change-set>";

        File fxml = fileManager.write( "changeset.xml",
                                       xml );

        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        KnowledgeAgent kagent = createKAgent( kbase );

        kagent.applyChangeSet( ResourceFactory.newUrlResource( fxml.toURI().toURL() ) );

        StatefulKnowledgeSession ksession = kagent.getKnowledgeBase().newStatefulKnowledgeSession();
        List<String> list = new ArrayList<String>();
        ksession.setGlobal( "list",
                            list );
        ksession.fireAllRules();
        ksession.dispose();

        assertEquals( 2,
                      list.size() );
        assertTrue( list.contains( "rule1" ) );
        assertTrue( list.contains( "rule2" ) );

        list.clear();

        this.fileManager.write( "rule1.drl", createDefaultRule( "rule3" ) );
        
        scan(kagent);
        
        ksession = kagent.getKnowledgeBase().newStatefulKnowledgeSession();
        list = new ArrayList<String>();
        ksession.setGlobal( "list",
                            list );
        ksession.fireAllRules();
        ksession.dispose();

        assertEquals( 2,
                      list.size() );
        
        assertTrue( list.contains( "rule3" ) );
        assertTrue( list.contains( "rule2" ) );
        
        kagent.dispose();
    }

    /**
     * Tests that if we change a ChangeSet that is referenced by another change
     * set or added by another ChangeSet, that the changes are picked up.
     *
     * @throws Exception
     *             If an unexpected exception occurs.
     */
    public void testChangeSetInChangeSet() throws Exception {
        fileManager.write( "rule1.drl",
                           createDefaultRule( "rule1" ) );        
        
        fileManager.write( "rule2.drl",
                           createDefaultRule( "rule2" ) );

        String xml1 = "";
        xml1 += "<change-set xmlns='http://drools.org/drools-5.0/change-set'";
        xml1 += "    xmlns:xs='http://www.w3.org/2001/XMLSchema-instance'";
        xml1 += "    xs:schemaLocation='http://drools.org/drools-5.0/change-set http://anonsvn.jboss.org/repos/labs/labs/jbossrules/trunk/drools-api/src/main/resources/change-set-1.0.0.xsd' >";
        xml1 += "    <add> ";
        xml1 += "        <resource source='http://localhost:" + this.getPort() + "/rule1.drl' type='DRL' />";
        xml1 += "        <resource source='http://localhost:" + this.getPort() + "/rule2.drl' type='DRL' />";
        xml1 += "    </add> ";
        xml1 += "</change-set>";
        File fxml = fileManager.write( "changeset2.xml",
                                       xml1 );
        String xml2 = "";
        xml2 += "<change-set xmlns='http://drools.org/drools-5.0/change-set'";
        xml2 += "    xmlns:xs='http://www.w3.org/2001/XMLSchema-instance'";
        xml2 += "    xs:schemaLocation='http://drools.org/drools-5.0/change-set http://anonsvn.jboss.org/repos/labs/labs/jbossrules/trunk/drools-api/src/main/resources/change-set-1.0.0.xsd' >";
        xml2 += "    <add> ";
        xml2 += "        <resource source='http://localhost:" + this.getPort() + "/changeset2.xml' type='CHANGE_SET' />";
        xml2 += "    </add> ";
        xml2 += "</change-set>";
        File fxm2 = fileManager.write( "changeset.xml",
                                       xml1 );

        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        KnowledgeAgent kagent = this.createKAgent( kbase );

        kagent.applyChangeSet( ResourceFactory.newUrlResource( fxm2.toURI().toURL() ) );

        StatefulKnowledgeSession ksession = kagent.getKnowledgeBase().newStatefulKnowledgeSession();
        List<String> list = new ArrayList<String>();
        ksession.setGlobal( "list",
                            list );
        ksession.fireAllRules();
        ksession.dispose();

        assertEquals( 2,
                      list.size() );
        assertTrue( list.contains( "rule1" ) );
        assertTrue( list.contains( "rule2" ) );

        list.clear();
        
        fileManager.write( "rule1.drl",
                           createDefaultRule( "rule3" ) );        

        scan(kagent);

        ksession = kagent.getKnowledgeBase().newStatefulKnowledgeSession();
        list = new ArrayList<String>();
        ksession.setGlobal( "list",
                            list );
        ksession.fireAllRules();
        ksession.dispose();

        assertEquals( 2,
                      list.size() );

        assertTrue( list.contains( "rule3" ) );
        assertTrue( list.contains( "rule2" ) );

        kagent.dispose();
    }

    public void testModifyFileUrlWithStateless() throws Exception {
        fileManager.write( "rule1.drl",
                           createDefaultRule( "rule1" ) );        
        
        fileManager.write( "rule2.drl",
                           createDefaultRule( "rule2" ) );

        String xml = "";
        xml += "<change-set xmlns='http://drools.org/drools-5.0/change-set'";
        xml += "    xmlns:xs='http://www.w3.org/2001/XMLSchema-instance'";
        xml += "    xs:schemaLocation='http://drools.org/drools-5.0/change-set http://anonsvn.jboss.org/repos/labs/labs/jbossrules/trunk/drools-api/src/main/resources/change-set-1.0.0.xsd' >";
        xml += "    <add> ";
        xml += "        <resource source='http://localhost:" + this.getPort() + "/rule1.drl' type='DRL' />";
        xml += "        <resource source='http://localhost:" + this.getPort() + "/rule2.drl' type='DRL' />";
        xml += "    </add> ";
        xml += "</change-set>";
        File fxml = fileManager.write( "changeset.xml",
                                       xml );

        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        KnowledgeAgent kagent = this.createKAgent( kbase );

        kagent.applyChangeSet( ResourceFactory.newUrlResource( fxml.toURI().toURL() ) );

        StatelessKnowledgeSession ksession = kagent.newStatelessKnowledgeSession();
        List<String> list = new ArrayList<String>();
        ksession.setGlobal( "list",
                            list );
        ksession.execute( "hello" );

        assertEquals( 2,
                      list.size() );
        assertTrue( list.contains( "rule1" ) );
        assertTrue( list.contains( "rule2" ) );

        list.clear();

        fileManager.write( "rule1.drl",
                           createDefaultRule( "rule3" ) );          
        
        scan(kagent);

        ksession.execute( "hello" );

        assertEquals( 2,
                      list.size() );

        assertTrue( list.contains( "rule3" ) );
        assertTrue( list.contains( "rule2" ) );
        kagent.dispose();
    }

    public void testModifyPackageUrl() throws Exception {
        String rule1 = this.createDefaultRule( "rule1" );

        String rule2 = this.createDefaultRule( "rule2" );

        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add( ResourceFactory.newByteArrayResource( rule1.getBytes() ),
                      ResourceType.DRL );
        kbuilder.add( ResourceFactory.newByteArrayResource( rule2.getBytes() ),
                      ResourceType.DRL );
        if ( kbuilder.hasErrors() ) {
            fail( kbuilder.getErrors().toString() );
        }
        KnowledgePackage pkg = (KnowledgePackage) kbuilder.getKnowledgePackages().iterator().next();
        writePackage( pkg,
                      fileManager.newFile( "pkg1.pkg" ) );

        String xml = "";
        xml += "<change-set xmlns='http://drools.org/drools-5.0/change-set'";
        xml += "    xmlns:xs='http://www.w3.org/2001/XMLSchema-instance'";
        xml += "    xs:schemaLocation='http://drools.org/drools-5.0/change-set http://anonsvn.jboss.org/repos/labs/labs/jbossrules/trunk/drools-api/src/main/resources/change-set-1.0.0.xsd' >";
        xml += "    <add> ";
        xml += "        <resource source='http://localhost:" + this.getPort() + "/pkg1.pkg' type='PKG' />";
        xml += "    </add> ";
        xml += "</change-set>";
        File fxml = fileManager.write( "changeset.xml",
                                       xml );

        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();

        KnowledgeAgent kagent = this.createKAgent( kbase );

        kagent.applyChangeSet( ResourceFactory.newUrlResource( fxml.toURI().toURL() ) );

        StatefulKnowledgeSession ksession = kagent.getKnowledgeBase().newStatefulKnowledgeSession();
        List<String> list = new ArrayList<String>();
        ksession.setGlobal( "list",
                            list );
        ksession.fireAllRules();
        ksession.dispose();

        assertEquals( 2,
                      list.size() );
        assertTrue( list.contains( "rule1" ) );
        assertTrue( list.contains( "rule2" ) );

        list.clear();

        rule1 = this.createDefaultRule( "rule3" );

        kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add( ResourceFactory.newByteArrayResource( rule1.getBytes() ),
                      ResourceType.DRL );
        kbuilder.add( ResourceFactory.newByteArrayResource( rule2.getBytes() ),
                      ResourceType.DRL );
        if ( kbuilder.hasErrors() ) {
            fail( kbuilder.getErrors().toString() );
        }
        pkg = (KnowledgePackage) kbuilder.getKnowledgePackages().iterator().next();
        writePackage( pkg,
                      fileManager.newFile( "pkg1.pkg" ) );

        scan( kagent );

        ksession = kagent.getKnowledgeBase().newStatefulKnowledgeSession();
        list = new ArrayList<String>();
        ksession.setGlobal( "list",
                            list );
        ksession.fireAllRules();
        ksession.dispose();

        assertEquals( 2,
                      list.size() );

        assertTrue( list.contains( "rule3" ) );
        assertTrue( list.contains( "rule2" ) );
        kagent.dispose();
    }

    public void testDeletePackageUrl() throws Exception {
        String rule1 = this.createDefaultRule( "rule1",
                                               "org.drools.test1" );

        String rule2 = this.createDefaultRule( "rule2",
                                               "org.drools.test2" );

        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add( ResourceFactory.newByteArrayResource( rule1.getBytes() ),
                      ResourceType.DRL );
        kbuilder.add( ResourceFactory.newByteArrayResource( rule2.getBytes() ),
                      ResourceType.DRL );
        if ( kbuilder.hasErrors() ) {
            fail( kbuilder.getErrors().toString() );
        }

        Map<String, KnowledgePackage> map = new HashMap<String, KnowledgePackage>();
        for ( KnowledgePackage pkg : kbuilder.getKnowledgePackages() ) {
            map.put( pkg.getName(),
                     pkg );
        }
        writePackage( (KnowledgePackage) map.get( "org.drools.test1" ),
                      fileManager.newFile( "pkg1.pkg" ) );
        writePackage( (KnowledgePackage) map.get( "org.drools.test2" ),
                      fileManager.newFile( "pkg2.pkg" ) );

        String xml = "";
        xml += "<change-set xmlns='http://drools.org/drools-5.0/change-set'";
        xml += "    xmlns:xs='http://www.w3.org/2001/XMLSchema-instance'";
        xml += "    xs:schemaLocation='http://drools.org/drools-5.0/change-set http://anonsvn.jboss.org/repos/labs/labs/jbossrules/trunk/drools-api/src/main/resources/change-set-1.0.0.xsd' >";
        xml += "    <add> ";
        xml += "        <resource source='http://localhost:" + this.getPort() + "/pkg1.pkg' type='PKG' />";
        xml += "        <resource source='http://localhost:" + this.getPort() + "/pkg2.pkg' type='PKG' />";
        xml += "    </add> ";
        xml += "</change-set>";

        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();

        KnowledgeAgent kagent = this.createKAgent( kbase );

        kagent.applyChangeSet( ResourceFactory.newByteArrayResource( xml.getBytes() ) );

        StatefulKnowledgeSession ksession = kagent.getKnowledgeBase().newStatefulKnowledgeSession();
        List<String> list = new ArrayList<String>();
        ksession.setGlobal( "list",
                            list );
        ksession.fireAllRules();
        ksession.dispose();

        assertEquals( 2,
                      list.size() );
        assertTrue( list.contains( "rule1" ) );
        assertTrue( list.contains( "rule2" ) );

        list.clear();

        xml = "";
        xml += "<change-set xmlns='http://drools.org/drools-5.0/change-set'";
        xml += "    xmlns:xs='http://www.w3.org/2001/XMLSchema-instance'";
        xml += "    xs:schemaLocation='http://drools.org/drools-5.0/change-set http://anonsvn.jboss.org/repos/labs/labs/jbossrules/trunk/drools-api/src/main/resources/change-set-1.0.0.xsd' >";
        xml += "    <remove> ";
        xml += "        <resource source='http://localhost:" + this.getPort() + "/pkg2.pkg' type='PKG' />";
        xml += "    </remove> ";
        xml += "</change-set>";
        
        applyChangeSet( kagent, xml );
        
        ksession = kagent.getKnowledgeBase().newStatefulKnowledgeSession();
        list = new ArrayList<String>();
        ksession.setGlobal( "list",
                            list );
        ksession.fireAllRules();
        ksession.dispose();

        assertEquals( 1,
                      list.size() );

        assertTrue( list.contains( "rule1" ) );
        kagent.dispose();
    }

    public void testOldSchoolPackageUrl() throws Exception {
        String rule1 = this.createDefaultRule( "rule1" );

        String rule2 = this.createDefaultRule( "rule2" );

        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add( ResourceFactory.newByteArrayResource( rule1.getBytes() ),
                      ResourceType.DRL );
        kbuilder.add( ResourceFactory.newByteArrayResource( rule2.getBytes() ),
                      ResourceType.DRL );
        if ( kbuilder.hasErrors() ) {
            fail( kbuilder.getErrors().toString() );
        }

        KnowledgeBuilderImpl kbi = (KnowledgeBuilderImpl) kbuilder;

        writePackage( kbi.getPackageBuilder().getPackage(),
                      fileManager.newFile( "pkgold.pkg" ) );

        String xml = "";
        xml += "<change-set xmlns='http://drools.org/drools-5.0/change-set'";
        xml += "    xmlns:xs='http://www.w3.org/2001/XMLSchema-instance'";
        xml += "    xs:schemaLocation='http://drools.org/drools-5.0/change-set http://anonsvn.jboss.org/repos/labs/labs/jbossrules/trunk/drools-api/src/main/resources/change-set-1.0.0.xsd' >";
        xml += "    <add> ";
        xml += "        <resource source='http://localhost:" + this.getPort() + "/pkgold.pkg' type='PKG' />";
        xml += "    </add> ";
        xml += "</change-set>";
        File fxml = fileManager.write( "changeset.xml",
                                       xml );

        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();

        KnowledgeAgent kagent = this.createKAgent( kbase );

        applyChangeSet( kagent, ResourceFactory.newUrlResource( fxml.toURI().toURL() )  );

        StatefulKnowledgeSession ksession = kagent.getKnowledgeBase().newStatefulKnowledgeSession();
        List<String> list = new ArrayList<String>();
        ksession.setGlobal( "list",
                            list );
        ksession.fireAllRules();
        ksession.dispose();

        assertEquals( 2,
                      list.size() );
        assertTrue( list.contains( "rule1" ) );
        assertTrue( list.contains( "rule2" ) );
        
        kagent.dispose();

    }

    public void testModifyFile() throws IOException,
                                InterruptedException {
        File f1 = fileManager.write( "rule1.drl",
                                     createDefaultRule( "rule1" ) );        
        
        File f2 = fileManager.write( "rule2.drl",
                                     createDefaultRule( "rule2" ) );

        String xml = "";
        xml += "<change-set xmlns='http://drools.org/drools-5.0/change-set'";
        xml += "    xmlns:xs='http://www.w3.org/2001/XMLSchema-instance'";
        xml += "    xs:schemaLocation='http://drools.org/drools-5.0/change-set http://anonsvn.jboss.org/repos/labs/labs/jbossrules/trunk/drools-api/src/main/resources/change-set-1.0.0.xsd' >";
        xml += "    <add> ";
        xml += "        <resource source='" + f1.toURI().toURL() + "' type='DRL' />";
        xml += "        <resource source='" + f2.toURI().toURL() + "' type='DRL' />";
        xml += "    </add> ";
        xml += "</change-set>";
        File fxml = fileManager.write( "changeset.xml",
                                       xml );

        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();

        KnowledgeAgent kagent = this.createKAgent( kbase );
        
        applyChangeSet( kagent,ResourceFactory.newUrlResource( fxml.toURI().toURL() ) );

        StatefulKnowledgeSession ksession = kagent.getKnowledgeBase().newStatefulKnowledgeSession();
        List<String> list = new ArrayList<String>();
        ksession.setGlobal( "list",
                            list );
        ksession.fireAllRules();
        ksession.dispose();

        assertEquals( 2,
                      list.size() );
        assertTrue( list.contains( "rule1" ) );
        assertTrue( list.contains( "rule2" ) );

        list.clear();

        fileManager.write( "rule1.drl",
                           createDefaultRule( "rule3" ) ); 

        scan( kagent );

        ksession = kagent.getKnowledgeBase().newStatefulKnowledgeSession();
        list = new ArrayList<String>();
        ksession.setGlobal( "list",
                            list );
        ksession.fireAllRules();
        ksession.dispose();

        assertEquals( 2,
                      list.size() );

        assertTrue( list.contains( "rule3" ) );
        assertTrue( list.contains( "rule2" ) );
        kagent.monitorResourceChangeEvents( false );
    }

    public void testModifyDirectory() throws IOException,
                                     InterruptedException {
        // adds 2 files to a dir and executes then adds one and removes one and
        // detects changes
        File f1 = fileManager.write( "rule1.drl",
                                     createDefaultRule( "rule1" ) );        
        
        File f2 = fileManager.write( "rule2.drl",
                                     createDefaultRule( "rule2" ) );

        String xml = "";
        xml += "<change-set xmlns='http://drools.org/drools-5.0/change-set'";
        xml += "    xmlns:xs='http://www.w3.org/2001/XMLSchema-instance'";
        xml += "    xs:schemaLocation='http://drools.org/drools-5.0/change-set http://anonsvn.jboss.org/repos/labs/labs/jbossrules/trunk/drools-api/src/main/resources/change-set-1.0.0.xsd' >";
        xml += "    <add> ";
        xml += "        <resource source='"
                + f1.getParentFile().toURI().toURL() + "' type='DRL' />";
        xml += "    </add> ";
        xml += "</change-set>";
        File fxml = fileManager.write( "changeset",
                                       "changeset.xml",
                                       xml );

        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();

        KnowledgeAgent kagent = this.createKAgent( kbase );

        applyChangeSet( kagent, ResourceFactory.newUrlResource( fxml.toURI().toURL() ) );

        StatefulKnowledgeSession ksession = kagent.getKnowledgeBase().newStatefulKnowledgeSession();
        List<String> list = new ArrayList<String>();
        ksession.setGlobal( "list",
                            list );
        ksession.fireAllRules();
        ksession.dispose();

        assertEquals( 2,
                      list.size() );
        assertTrue( list.contains( "rule1" ) );
        assertTrue( list.contains( "rule2" ) );

        list.clear();

        fileManager.write( "rule3.drl",
                           createDefaultRule( "rule3" ) );
        fileManager.deleteFile( f1 );

        scan( kagent );

        ksession = kagent.getKnowledgeBase().newStatefulKnowledgeSession();
        list = new ArrayList<String>();
        ksession.setGlobal( "list",
                            list );
        ksession.fireAllRules();
        ksession.dispose();

        assertEquals( 2,
                      list.size() );
        assertTrue( list.contains( "rule2" ) );
        assertTrue( list.contains( "rule3" ) );

        kagent.dispose();
    }

    public void testModifyFileInDirectory() throws Exception {
        // Create the test directory
        File testDirectory = fileManager.newFile( "test" );
        testDirectory.mkdir();

        File f1 = fileManager.write( "test",
                                     "rule1.drl",
                                     createDefaultRule( "rule1" ) );        
        
        File f2 = fileManager.write( "test",
                                     "rule2.drl",
                                     createDefaultRule( "rule2" ) );

        String xml = "";
        xml += "<change-set xmlns='http://drools.org/drools-5.0/change-set'";
        xml += "    xmlns:xs='http://www.w3.org/2001/XMLSchema-instance'";
        xml += "    xs:schemaLocation='http://drools.org/drools-5.0/change-set http://anonsvn.jboss.org/repos/labs/labs/jbossrules/trunk/drools-api/src/main/resources/change-set-1.0.0.xsd' >";
        xml += "    <add> ";
        xml += "        <resource source='file:"
                + fileManager.getRootDirectory().getAbsolutePath()
                + "/test' type='DRL' />";
        xml += "    </add> ";
        xml += "</change-set>";
        File fxml = fileManager.write( "changeset",
                                       "changeset.xml",
                                       xml );
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();

        KnowledgeAgent kagent = this.createKAgent( kbase );

        applyChangeSet( kagent, ResourceFactory.newUrlResource( fxml.toURI().toURL() ) );
        
        StatefulKnowledgeSession ksession = kagent.getKnowledgeBase().newStatefulKnowledgeSession();
        List<String> list = new ArrayList<String>();
        ksession.setGlobal( "list",
                            list );
        ksession.fireAllRules();
        ksession.dispose();

        assertEquals( 2,
                      list.size() );
        assertTrue( list.contains( "rule1" ) );
        assertTrue( list.contains( "rule2" ) );

        list.clear();
        
        fileManager.write( "test",
                           "rule1.drl",
                           createDefaultRule( "rule3" ) );          

        scan(kagent);

        ksession = kagent.getKnowledgeBase().newStatefulKnowledgeSession();
        list = new ArrayList<String>();
        ksession.setGlobal( "list",
                            list );
        ksession.fireAllRules();
        ksession.dispose();

        assertEquals( 2,
                      list.size() );

        assertTrue( list.contains( "rule3" ) );
        assertTrue( list.contains( "rule2" ) );
        kagent.dispose();
    }

    public void testStatelessWithCommands() throws Exception {
        File f1 = fileManager.write( "rule1.drl",
                                     createDefaultRule( "rule1" ) );        
        
        File f2 = fileManager.write( "rule2.drl",
                                     createDefaultRule( "rule2" ) );

        String xml = "";
        xml += "<change-set xmlns='http://drools.org/drools-5.0/change-set'";
        xml += "    xmlns:xs='http://www.w3.org/2001/XMLSchema-instance'";
        xml += "    xs:schemaLocation='http://drools.org/drools-5.0/change-set http://anonsvn.jboss.org/repos/labs/labs/jbossrules/trunk/drools-api/src/main/resources/change-set-1.0.0.xsd' >";
        xml += "    <add> ";
        xml += "        <resource source='"
                + f1.getParentFile().toURI().toURL() + "' type='DRL' />";
        xml += "    </add> ";
        xml += "</change-set>";
        File fxml = fileManager.write( "changeset",
                                       "changeset.xml",
                                       xml );

        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();

        KnowledgeAgent kagent = this.createKAgent( kbase );
        
        applyChangeSet(kagent, ResourceFactory.newUrlResource( fxml.toURI().toURL() ) );

        StatelessKnowledgeSession ksession = kagent.newStatelessKnowledgeSession();

        List<String> list = new ArrayList<String>();
        ksession.setGlobal( "list",
                            list );

        ksession.execute( new InsertObjectCommand( "hello" ) );

        assertEquals( 2,
                      list.size() );
        assertTrue( list.contains( "rule1" ) );
        assertTrue( list.contains( "rule2" ) );
    }

    private static void writePackage(Object pkg,
                                     File p1file )throws IOException, FileNotFoundException {
        if ( p1file.exists() ) {
            // we want to make sure there is a time difference for lastModified and lastRead checks as Linux and http often round to seconds
            // http://saloon.javaranch.com/cgi-bin/ubb/ultimatebb.cgi?ubb=get_topic&f=1&t=019789
            try {
                Thread.sleep( 1000 );
            } catch (Exception e) {
                throw new RuntimeException( "Unable to sleep" );
            }            
        }
        FileOutputStream out = new FileOutputStream( p1file );
        try {
            DroolsStreamUtils.streamOut( out,
                                         pkg );
        } finally {
            out.close();
        }
    }

    private KnowledgeAgent createKAgent(KnowledgeBase kbase) {
        KnowledgeAgentConfiguration aconf = KnowledgeAgentFactory.newKnowledgeAgentConfiguration();
        aconf.setProperty( "drools.agent.scanDirectories",
                           "true" );
        aconf.setProperty( "drools.agent.scanResources",
                           "true" );
        aconf.setProperty( "drools.agent.newInstance",
                           "true" );

        KnowledgeAgent kagent = KnowledgeAgentFactory.newKnowledgeAgent("test agent",
                                                                         kbase,
                                                                         aconf );

        assertEquals( "test agent",
                      kagent.getName() );

        return kagent;
    }

    private String createDefaultRule(String name) {
        return this.createDefaultRule( name,
                                       null );
    }

    private String createDefaultRule(String name,
                                     String packageName) {
        StringBuilder rule = new StringBuilder();
        if ( packageName == null ) {
            rule.append( "package org.drools.test\n" );
        } else {
            rule.append( "package " );
            rule.append( packageName );
            rule.append( "\n" );
        }
        rule.append( "global java.util.List list\n" );
        rule.append( "rule " );
        rule.append( name );
        rule.append( "\n" );
        rule.append( "when\n" );
        rule.append( "then\n" );
        rule.append( "list.add( drools.getRule().getName() );\n" );
        rule.append( "end\n" );

        return rule.toString();
    }

}
