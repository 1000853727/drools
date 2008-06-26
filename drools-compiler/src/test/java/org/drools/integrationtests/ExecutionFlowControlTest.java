package org.drools.integrationtests;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.drools.Alarm;
import org.drools.Cheese;
import org.drools.FactHandle;
import org.drools.Message;
import org.drools.Person;
import org.drools.PersonInterface;
import org.drools.RuleBase;
import org.drools.RuleBaseConfiguration;
import org.drools.RuleBaseFactory;
import org.drools.StatefulSession;
import org.drools.WorkingMemory;
import org.drools.common.DefaultAgenda;
import org.drools.common.InternalWorkingMemoryActions;
import org.drools.common.RuleFlowGroupImpl;
import org.drools.compiler.DrlParser;
import org.drools.compiler.DroolsParserException;
import org.drools.compiler.PackageBuilder;
import org.drools.compiler.PackageBuilder.PackageMergeException;
import org.drools.event.ActivationCancelledEvent;
import org.drools.event.ActivationCreatedEvent;
import org.drools.event.AgendaEventListener;
import org.drools.event.DefaultAgendaEventListener;
import org.drools.lang.descr.PackageDescr;
import org.drools.process.instance.ProcessInstance;
import org.drools.rule.Package;
import org.drools.spi.Activation;
import org.drools.spi.ActivationGroup;
import org.drools.spi.AgendaGroup;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class ExecutionFlowControlTest extends TestCase {
    protected RuleBase getRuleBase() throws Exception {

        return RuleBaseFactory.newRuleBase( RuleBase.RETEOO,
                                            null );
    }

    protected RuleBase getRuleBase(final RuleBaseConfiguration config) throws Exception {

        return RuleBaseFactory.newRuleBase( RuleBase.RETEOO,
                                            config );
    }

    public void testRuleFlowConstraintDialects() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addRuleFlow( new InputStreamReader( getClass().getResourceAsStream( "test_ConstraintDialects.rfm" ) ) );

        System.err.print( builder.getErrors() );

        assertEquals( 0, builder.getErrors().getErrors().length );

        RuleBase ruleBase = RuleBaseFactory.newRuleBase();
        ruleBase.addPackage( builder.getPackage() );
        ruleBase    = SerializationHelper.serializeObject(ruleBase);

        StatefulSession session = ruleBase.newStatefulSession();
        List inList = new ArrayList();
        List outList = new ArrayList();
        session.setGlobal( "inList", inList );
        session.setGlobal( "outList", outList );

        inList.add( 1 );
        inList.add( 3 );
        inList.add( 6 );
        inList.add( 25 );

        FactHandle handle = session.insert( inList );
        session.startProcess( "ConstraintDialects" );
        assertEquals( 4, outList.size() );
        assertEquals( "MVELCodeConstraint was here", outList.get( 0 ));
        assertEquals( "JavaCodeConstraint was here", outList.get( 1 ));
        assertEquals( "MVELRuleConstraint was here", outList.get( 2 ));
        assertEquals( "JavaRuleConstraint was here", outList.get( 3 ));

        outList.clear();
        inList.remove( new Integer( 1 ) );
        session.update( handle, inList );
        session.startProcess( "ConstraintDialects" );
        assertEquals( 3, outList.size() );
        assertEquals( "JavaCodeConstraint was here", outList.get( 0 ));
        assertEquals( "MVELRuleConstraint was here", outList.get( 1 ));
        assertEquals( "JavaRuleConstraint was here", outList.get( 2 ));

        outList.clear();
        inList.remove( new Integer( 6 ) );
        session.update( handle, inList );
        session.startProcess( "ConstraintDialects" );
        assertEquals( 2, outList.size() );
        assertEquals( "JavaCodeConstraint was here", outList.get( 0 ));
        assertEquals( "JavaRuleConstraint was here", outList.get( 1 ));

        outList.clear();
        inList.remove( new Integer( 3 ) );
        session.update( handle, inList );
        session.startProcess( "ConstraintDialects" );
        assertEquals( 1, outList.size() );
        assertEquals( "JavaRuleConstraint was here", outList.get( 0 ));

        outList.clear();
        inList.remove( new Integer( 25 ) );
        session.update( handle, inList );
        try {
            session.startProcess( "ConstraintDialects" );
            fail("This should have thrown an exception" );
        } catch ( Exception e ) {
        }
    }

    public void testSalienceInteger() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_salienceIntegerRule.drl" ) ) );
        final Package pkg = builder.getPackage();

        RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        ruleBase    = SerializationHelper.serializeObject(ruleBase);
        final WorkingMemory workingMemory = ruleBase.newStatefulSession();

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        final PersonInterface person = new Person( "Edson",
                                                   "cheese" );
        workingMemory.insert( person );

        workingMemory.fireAllRules();

        Assert.assertEquals( "Two rules should have been fired",
                             2,
                             list.size() );
        Assert.assertEquals( "Rule 3 should have been fired first",
                             "Rule 3",
                             list.get( 0 ) );
        Assert.assertEquals( "Rule 2 should have been fired second",
                             "Rule 2",
                             list.get( 1 ) );
    }
     
    public void testSalienceExpression() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_salienceExpressionRule.drl" ) ) );
        final Package pkg = builder.getPackage();

        RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        ruleBase    = SerializationHelper.serializeObject(ruleBase);
        final WorkingMemory workingMemory = ruleBase.newStatefulSession();

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        final PersonInterface person10 = new Person( "bob",
                                                   "cheese",
                                                   10);
        workingMemory.insert( person10 );
        
        final PersonInterface person20 = new Person( "mic",
                                                     "cheese",
                                                     20);
          workingMemory.insert( person20 );        

        workingMemory.fireAllRules();

        Assert.assertEquals( "Two rules should have been fired",
                             2,
                             list.size() );
        Assert.assertEquals( "Rule 3 should have been fired first",
                             "Rule 3",
                             list.get( 0 ) );
        Assert.assertEquals( "Rule 2 should have been fired second",
                             "Rule 2",
                             list.get( 1 ) );
    }    

    public void testNoLoop() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "no-loop.drl" ) ) );
        final Package pkg = builder.getPackage();

        RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        ruleBase    = SerializationHelper.serializeObject(ruleBase);
        final WorkingMemory workingMemory = ruleBase.newStatefulSession();

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        final Cheese brie = new Cheese( "brie",
                                        12 );
        workingMemory.insert( brie );

        workingMemory.fireAllRules();

        Assert.assertEquals( "Should not loop  and thus size should be 1",
                             1,
                             list.size() );

    }

    public void testLockOnActive() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_LockOnActive.drl" ) ) );
        final Package pkg = builder.getPackage();

        RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        ruleBase    = SerializationHelper.serializeObject(ruleBase);
        final WorkingMemory workingMemory = ruleBase.newStatefulSession();

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        // AgendaGroup "group1" is not active, so should receive activation
        final Cheese brie12 = new Cheese( "brie",
                                          12 );
        workingMemory.insert( brie12 );
        final AgendaGroup group1 = workingMemory.getAgenda().getAgendaGroup( "group1" );
        assertEquals( 1,
                      group1.size() );

        workingMemory.setFocus( "group1" );
        // AgendaqGroup "group1" is now active, so should not receive activations
        final Cheese brie10 = new Cheese( "brie",
                                          10 );
        workingMemory.insert( brie10 );
        assertEquals( 1,
                      group1.size() );

        final Cheese cheddar20 = new Cheese( "cheddar",
                                             20 );
        workingMemory.insert( cheddar20 );
        final AgendaGroup group2 = workingMemory.getAgenda().getAgendaGroup( "group1" );
        assertEquals( 1,
                      group2.size() );

        final RuleFlowGroupImpl rfg = (RuleFlowGroupImpl) workingMemory.getAgenda().getRuleFlowGroup( "ruleflow2" );
        rfg.setActive( true );
        final Cheese cheddar17 = new Cheese( "cheddar",
                                             17 );
        workingMemory.insert( cheddar17 );
        assertEquals( 1,
                      group2.size() );
    }

    public void testLockOnActiveWithModify() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_LockOnActiveWithUpdate.drl" ) ) );
        final Package pkg = builder.getPackage();

        RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        ruleBase    = SerializationHelper.serializeObject(ruleBase);
        final WorkingMemory wm = ruleBase.newStatefulSession();

        final List list = new ArrayList();
        wm.setGlobal( "list",
                      list );

        final Cheese brie = new Cheese( "brie",
                                        13 );

        final Person bob = new Person( "bob" );
        bob.setCheese( brie );

        final Person mic = new Person( "mic" );
        mic.setCheese( brie );

        final Person mark = new Person( "mark" );
        mark.setCheese( brie );

        final FactHandle brieHandle = wm.insert( brie );
        wm.insert( bob );
        wm.insert( mic );
        wm.insert( mark );

        final DefaultAgenda agenda = (DefaultAgenda) wm.getAgenda();
        final AgendaGroup group1 = agenda.getAgendaGroup( "group1" );
        agenda.setFocus( group1 );
        assertEquals( 3,
                      group1.size() );
        agenda.fireNextItem( null );
        assertEquals( 2,
                      group1.size() );
        wm.update( brieHandle,
                         brie );
        assertEquals( 2,
                      group1.size() );

        AgendaGroup group2 = agenda.getAgendaGroup( "group2" );
        agenda.setFocus( group2 );

        RuleFlowGroupImpl rfg = (RuleFlowGroupImpl) wm.getAgenda().getRuleFlowGroup( "ruleflow2" );
        assertEquals( 3,
                      rfg.size() );

        agenda.activateRuleFlowGroup( "ruleflow2" );
        agenda.fireNextItem( null );
        assertEquals( 2,
                      rfg.size() );
        wm.update( brieHandle,
                         brie );
        assertEquals( 2,
                      group2.size() );
    }

    public void testAgendaGroups() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_AgendaGroups.drl" ) ) );
        final Package pkg = builder.getPackage();

        RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        ruleBase    = SerializationHelper.serializeObject(ruleBase);
        final WorkingMemory workingMemory = ruleBase.newStatefulSession();

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        final Cheese brie = new Cheese( "brie",
                                        12 );
        workingMemory.insert( brie );

        workingMemory.fireAllRules();

        assertEquals( 7,
                      list.size() );

        assertEquals( "group3",
                      list.get( 0 ) );
        assertEquals( "group4",
                      list.get( 1 ) );
        assertEquals( "group3",
                      list.get( 2 ) );
        assertEquals( "MAIN",
                      list.get( 3 ) );
        assertEquals( "group1",
                      list.get( 4 ) );
        assertEquals( "group1",
                      list.get( 5 ) );
        assertEquals( "MAIN",
                      list.get( 6 ) );

        workingMemory.setFocus( "group2" );
        workingMemory.fireAllRules();

        assertEquals( 8,
                      list.size() );
        assertEquals( "group2",
                      list.get( 7 ) );
    }

    public void testActivationGroups() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_ActivationGroups.drl" ) ) );
        final Package pkg = builder.getPackage();

        RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        ruleBase    = SerializationHelper.serializeObject(ruleBase);
        final WorkingMemory workingMemory = ruleBase.newStatefulSession();

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        final Cheese brie = new Cheese( "brie",
                                        12 );
        workingMemory.insert( brie );

        final ActivationGroup activationGroup0 = workingMemory.getAgenda().getActivationGroup( "activation-group-0" );
        assertEquals( 2,
                      activationGroup0.size() );

        final ActivationGroup activationGroup3 = workingMemory.getAgenda().getActivationGroup( "activation-group-3" );
        assertEquals( 1,
                      activationGroup3.size() );

        final AgendaGroup agendaGroup3 = workingMemory.getAgenda().getAgendaGroup( "agenda-group-3" );
        assertEquals( 1,
                      agendaGroup3.size() );

        final AgendaGroup agendaGroupMain = workingMemory.getAgenda().getAgendaGroup( "MAIN" );
        assertEquals( 3,
                      agendaGroupMain.size() );

        workingMemory.clearAgendaGroup( "agenda-group-3" );
        assertEquals( 0,
                      activationGroup3.size() );
        assertEquals( 0,
                      agendaGroup3.size() );

        workingMemory.fireAllRules();

        assertEquals( 0,
                      activationGroup0.size() );

        assertEquals( 2,
                      list.size() );
        assertEquals( "rule0",
                      list.get( 0 ) );
        assertEquals( "rule2",
                      list.get( 1 ) );

    }

    public void testDuration() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_Duration.drl" ) ) );
        final Package pkg = builder.getPackage();

        RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        ruleBase    = SerializationHelper.serializeObject(ruleBase);
        final WorkingMemory workingMemory = ruleBase.newStatefulSession();

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        final Cheese brie = new Cheese( "brie",
                                        12 );
        final FactHandle brieHandle = workingMemory.insert( brie );

        workingMemory.fireAllRules();

        // now check for update
        assertEquals( 0,
                      list.size() );

        // sleep for 300ms
        Thread.sleep( 300 );

        // now check for update
        assertEquals( 1,
                      list.size() );

    }

    public void testInsertRetractNoloop() throws Exception {
        // read in the source
        final Reader reader = new InputStreamReader( getClass().getResourceAsStream( "test_Insert_Retract_Noloop.drl" ) );
        RuleBase ruleBase = loadRuleBase( reader );

        ruleBase    = SerializationHelper.serializeObject(ruleBase);
        final WorkingMemory wm = ruleBase.newStatefulSession();
        wm.insert( new Cheese( "stilton",
                                     15 ) );

        wm.fireAllRules();
    }

    public void testDurationWithNoLoop() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_Duration_with_NoLoop.drl" ) ) );
        final Package pkg = builder.getPackage();

        RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        ruleBase    = SerializationHelper.serializeObject(ruleBase);
        final WorkingMemory workingMemory = ruleBase.newStatefulSession();

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        final Cheese brie = new Cheese( "brie",
                                        12 );
        final FactHandle brieHandle = workingMemory.insert( brie );

        workingMemory.fireAllRules();

        // now check for update
        assertEquals( 0,
                      list.size() );

        // sleep for 300ms
        Thread.sleep( 300 );

        // now check for update
        assertEquals( 1,
                      list.size() );
    }
    
    public void testDurationMemoryLeakonRepeatedUpdate() throws Exception {
        String str = "package org.drools.test\n" +
        "import org.drools.Alarm\n" +
        "global java.util.List list;" +
        "rule \"COMPTEUR\"\n" +
        "  duration 50\n" +
        "  when\n" +        
        "    $alarm : Alarm( number < 5 )\n" +
        "  then\n" +
        "    $alarm.incrementNumber();\n" +
        "    list.add( $alarm );\n" +
        "    update($alarm);\n" +
        "end\n";
        
        PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new StringReader( str ) );
        
        RuleBase ruleBase = RuleBaseFactory.newRuleBase();
        ruleBase.addPackage( builder.getPackage() );
        
        StatefulSession session = ruleBase.newStatefulSession();
        List list = new ArrayList();
        session.setGlobal( "list", list );
        session.insert( new Alarm() );
        
        session.fireAllRules();
        
        Thread.sleep( 1000 );
        
        assertEquals( 5, list.size() );
        assertEquals(0, session.getAgenda().getScheduledActivations().length );                        
    }    

    public void testFireRuleAfterDuration() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_FireRuleAfterDuration.drl" ) ) );
        final Package pkg = builder.getPackage();

        RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        ruleBase    = SerializationHelper.serializeObject(ruleBase);
        final WorkingMemory workingMemory = ruleBase.newStatefulSession();

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        final Cheese brie = new Cheese( "brie",
                                        12 );
        final FactHandle brieHandle = workingMemory.insert( brie );

        workingMemory.fireAllRules();

        // now check for update
        assertEquals( 0,
                      list.size() );

        // sleep for 300ms
        Thread.sleep( 300 );

        workingMemory.fireAllRules();

        // now check for update
        assertEquals( 2,
                      list.size() );

    }

    public void testUpdateNoLoop() throws Exception {
        // JBRULES-780, throws a NullPointer or infinite loop if there is an issue
        final Reader reader = new InputStreamReader( getClass().getResourceAsStream( "test_UpdateNoloop.drl" ) );
        RuleBase ruleBase = loadRuleBase( reader );

        ruleBase    = SerializationHelper.serializeObject(ruleBase);
        final WorkingMemory wm = ruleBase.newStatefulSession();
        wm.insert( new Cheese( "stilton",
                                     15 ) );

        wm.fireAllRules();
    }

    public void testUpdateActivationCreationNoLoop() throws Exception {
        // JBRULES-787, no-loop blocks all dependant tuples for update 
        final Reader reader = new InputStreamReader( getClass().getResourceAsStream( "test_UpdateActivationCreationNoLoop.drl" ) );
        RuleBase ruleBase = loadRuleBase( reader );

        ruleBase    = SerializationHelper.serializeObject(ruleBase);
        final InternalWorkingMemoryActions wm = (InternalWorkingMemoryActions) ruleBase.newStatefulSession();
        final List created = new ArrayList();
        final List cancelled = new ArrayList();
        final AgendaEventListener l = new DefaultAgendaEventListener() {
            public void activationCreated(ActivationCreatedEvent event,
                                          WorkingMemory workingMemory) {
                created.add( event );
            }

            public void activationCancelled(ActivationCancelledEvent event,
                                            WorkingMemory workingMemory) {
                cancelled.add( event );
            }

        };

        wm.addEventListener( l );

        final Cheese stilton = new Cheese( "stilton",
                                           15 );
        final FactHandle stiltonHandle = wm.insert( stilton );

        final Person p1 = new Person( "p1" );
        p1.setCheese( stilton );
        wm.insert( p1 );

        final Person p2 = new Person( "p2" );
        p2.setCheese( stilton );
        wm.insert( p2 );

        final Person p3 = new Person( "p3" );
        p3.setCheese( stilton );
        wm.insert( p3 );

        assertEquals( 3,
                      created.size() );
        assertEquals( 0,
                      cancelled.size() );

        final Activation item = ((ActivationCreatedEvent) created.get( 2 )).getActivation();

        // simulate a modify inside a consequence
        wm.update( stiltonHandle,
                         stilton,
                         item.getRule(),
                         item );

        // the two of the three tuples should re-activate
        assertEquals( 5,
                      created.size() );
        assertEquals( 3,
                      cancelled.size() );
    }

    public void testRuleFlowGroup() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "ruleflowgroup.drl" ) ) );
        final Package pkg = builder.getPackage();

        RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        ruleBase    = SerializationHelper.serializeObject(ruleBase);

        final WorkingMemory workingMemory = ruleBase.newStatefulSession();
        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        workingMemory.insert( "Test" );
        workingMemory.fireAllRules();
        assertEquals( 0,
                      list.size() );

        workingMemory.getAgenda().activateRuleFlowGroup( "Group1" );
        workingMemory.fireAllRules();

        assertEquals( 1,
                      list.size() );
    }

    public void testRuleFlow() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "ruleflow.drl" ) ) );
        builder.addRuleFlow( new InputStreamReader( getClass().getResourceAsStream( "ruleflow.rfm" ) ) );
        final Package pkg = builder.getPackage();
        RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        ruleBase    = SerializationHelper.serializeObject(ruleBase);

        final WorkingMemory workingMemory = ruleBase.newStatefulSession();
        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        workingMemory.fireAllRules();
        assertEquals( 0,
                      list.size() );

        final ProcessInstance processInstance = workingMemory.startProcess( "0" );
        assertEquals( ProcessInstance.STATE_ACTIVE,
                      processInstance.getState() );
        workingMemory.fireAllRules();
        assertEquals( 4,
                      list.size() );
        assertEquals( "Rule1",
                      list.get( 0 ) );
        assertEquals( "Rule3",
                      list.get( 1 ) );
        assertEquals( "Rule2",
                      list.get( 2 ) );
        assertEquals( "Rule4",
                      list.get( 3 ) );
        assertEquals( ProcessInstance.STATE_COMPLETED,
                      processInstance.getState() );
    }
    
    public void testRuleFlowClear() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_ruleflowClear.drl" ) ) );
        builder.addRuleFlow( new InputStreamReader( getClass().getResourceAsStream( "test_ruleflowClear.rfm" ) ) );
        final Package pkg = builder.getPackage();
        RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        ruleBase    = SerializationHelper.serializeObject(ruleBase);

        final WorkingMemory workingMemory = ruleBase.newStatefulSession();
        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );
        
        final List activations = new ArrayList();
        AgendaEventListener listener = new DefaultAgendaEventListener() {            
            public void activationCancelled(ActivationCancelledEvent event,
                                            WorkingMemory workingMemory) {
                activations.add( event.getActivation() );
            }
        };
        
        workingMemory.addEventListener( listener );
     
        assertEquals( 0 , workingMemory.getAgenda().getRuleFlowGroup( "flowgroup-1" ).size() );
        
        // We need to call fireAllRules here to get the InitialFact into the system, to the eval(true)'s kick in
        workingMemory.fireAllRules();
        
        // Now we have 4 in the RuleFlow, but not yet in the agenda
        assertEquals( 4 , workingMemory.getAgenda().getRuleFlowGroup( "flowgroup-1" ).size() );
        
        // Check they aren't in the Agenda
        assertEquals( 0, workingMemory.getAgenda().getAgendaGroup( "MAIN" ).size() );
        
        // Start the process, which shoudl populate the Agenda
        final ProcessInstance processInstance = workingMemory.startProcess( "ruleFlowClear" );
        assertEquals( 4, workingMemory.getAgenda().getAgendaGroup( "MAIN" ).size() );
        
        
        // Check we have 0 activation cancellation events
        assertEquals( 0, activations.size() );
        
        workingMemory.getAgenda().clearRuleFlowGroup( "flowgroup-1" );
        
        // Check the AgendaGroup and RuleFlowGroup  are now empty
        assertEquals( 0, workingMemory.getAgenda().getAgendaGroup( "MAIN" ).size() );
        assertEquals( 0 , workingMemory.getAgenda().getRuleFlowGroup( "flowgroup-1" ).size() );
        
        // Check we have four activation cancellation events
        assertEquals( 4, activations.size() );              
    }
    
    public void testRuleFlowInPackage() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "ruleflow.drl" ) ) );
        builder.addRuleFlow( new InputStreamReader( getClass().getResourceAsStream( "ruleflow.rfm" ) ) );
        final Package pkg = builder.getPackage();

        RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        ruleBase    = SerializationHelper.serializeObject(ruleBase);

        final WorkingMemory workingMemory = ruleBase.newStatefulSession();
        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        workingMemory.fireAllRules();
        assertEquals( 0,
                      list.size() );

        final ProcessInstance processInstance = workingMemory.startProcess( "0" );
        assertEquals( ProcessInstance.STATE_ACTIVE,
                      processInstance.getState() );
        workingMemory.fireAllRules();
        assertEquals( 4,
                      list.size() );
        assertEquals( "Rule1",
                      list.get( 0 ) );
        assertEquals( "Rule3",
                      list.get( 1 ) );
        assertEquals( "Rule2",
                      list.get( 2 ) );
        assertEquals( "Rule4",
                      list.get( 3 ) );
        assertEquals( ProcessInstance.STATE_COMPLETED,
                      processInstance.getState() );
        
    }
    
    public void testLoadingRuleFlowInPackage1() throws Exception {
    	// adding ruleflow before adding package
        final PackageBuilder builder = new PackageBuilder();
        builder.addRuleFlow( new InputStreamReader( getClass().getResourceAsStream( "ruleflow.rfm" ) ) );
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "ruleflow.drl" ) ) );
        builder.getPackage();
    }

    public void testLoadingRuleFlowInPackage2() throws Exception {
    	// only adding ruleflow
        final PackageBuilder builder = new PackageBuilder();
        builder.addRuleFlow( new InputStreamReader( getClass().getResourceAsStream( "ruleflow.rfm" ) ) );
        builder.getPackage();
    }

    public void testLoadingRuleFlowInPackage3() throws Exception {
    	// only adding ruleflow without any generated rules
        final PackageBuilder builder = new PackageBuilder();
        builder.addRuleFlow( new InputStreamReader( getClass().getResourceAsStream( "empty_ruleflow.rfm" ) ) );
        builder.getPackage();
    }

    public void FIXME_testLoadingRuleFlowInPackage4() throws Exception {
    	// adding ruleflows of different package
        final PackageBuilder builder = new PackageBuilder();
        builder.addRuleFlow( new InputStreamReader( getClass().getResourceAsStream( "empty_ruleflow.rfm" ) ) );
        try {
        	builder.addRuleFlow( new InputStreamReader( getClass().getResourceAsStream( "ruleflow.rfm" ) ) );
        	throw new Exception("An exception should have been thrown.");
        } catch (PackageMergeException e) {
        	// do nothing
        }
    }

    public void FIXME_testLoadingRuleFlowInPackage5() throws Exception {
    	// adding ruleflow of different package than rules
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "ruleflow.drl" ) ) );
        try {
        	builder.addRuleFlow( new InputStreamReader( getClass().getResourceAsStream( "empty_ruleflow.rfm" ) ) );
        	throw new Exception("An exception should have been thrown.");
        } catch (PackageMergeException e) {
        	// do nothing
        }
    }

    public void FIXME_testLoadingRuleFlowInPackage6() throws Exception {
    	// adding rules of different package than ruleflow
        final PackageBuilder builder = new PackageBuilder();
    	builder.addRuleFlow( new InputStreamReader( getClass().getResourceAsStream( "empty_ruleflow.rfm" ) ) );
        try {
            builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "ruleflow.drl" ) ) );
        	throw new Exception("An exception should have been thrown.");
        } catch (PackageMergeException e) {
        	// do nothing
        }
    }
    
    public void testRuleFlowActionDialects() throws Exception {
        final PackageBuilder builder = new PackageBuilder();
        builder.addRuleFlow( new InputStreamReader( getClass().getResourceAsStream( "test_ActionDialects.rfm" ) ) ); 
        
        RuleBase ruleBase = RuleBaseFactory.newRuleBase();
        ruleBase.addPackage( builder.getPackage() );
        ruleBase = SerializationHelper.serializeObject(ruleBase);

        StatefulSession session = ruleBase.newStatefulSession();
        List list = new ArrayList();
        session.setGlobal( "list", list );
        
        session.startProcess( "ActionDialects" );
        
        assertEquals( 2, list.size() );
        assertEquals( "mvel was here", list.get( 0 ) );
        assertEquals( "java was here", list.get( 1 ) );
    }
    
    public void testLoadingRuleFlowInPackage7() throws Exception {
    	// loading a ruleflow with errors
        final PackageBuilder builder = new PackageBuilder();
    	builder.addRuleFlow( new InputStreamReader( getClass().getResourceAsStream( "error_ruleflow.rfm" ) ) );
    	assertEquals(1, builder.getErrors().getErrors().length);
    }

    private RuleBase loadRuleBase(final Reader reader) throws IOException,
                                                      DroolsParserException,
                                                      Exception {
        final DrlParser parser = new DrlParser();
        final PackageDescr packageDescr = parser.parse( reader );
        if ( parser.hasErrors() ) {
            Assert.fail( "Error messages in parser, need to sort this our (or else collect error messages)" );
        }
        // pre build the package
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackage( packageDescr );
        final Package pkg = builder.getPackage();

        // add the package to a rulebase
        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        // load up the rulebase
        return ruleBase;
    }

    public void testDateEffective() throws Exception {
        // read in the source
        final Reader reader = new InputStreamReader( getClass().getResourceAsStream( "test_EffectiveDate.drl" ) );
        RuleBase ruleBase = loadRuleBase( reader );
        ruleBase    = SerializationHelper.serializeObject(ruleBase);

        final WorkingMemory workingMemory = ruleBase.newStatefulSession();

        final List list = new ArrayList();
        workingMemory.setGlobal( "list",
                                 list );

        // go !
        final Message message = new Message( "hola" );
        workingMemory.insert( message );
        workingMemory.fireAllRules();
        assertFalse( message.isFired() );

    }    
}
