package org.drools.integrationtests;

import static org.drools.integrationtests.SerializationHelper.getSerialisedStatefulSession;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import junit.framework.TestCase;

import org.drools.Address;
import org.drools.Cell;
import org.drools.Cheese;
import org.drools.FactA;
import org.drools.FactB;
import org.drools.FactC;
import org.drools.Person;
import org.drools.Primitives;
import org.drools.RuleBase;
import org.drools.RuleBaseConfiguration;
import org.drools.RuleBaseFactory;
import org.drools.StatefulSession;
import org.drools.WorkingMemory;
import org.drools.base.ClassObjectType;
import org.drools.base.MapGlobalResolver;
import org.drools.common.BaseNode;
import org.drools.common.InternalFactHandle;
import org.drools.common.InternalRuleBase;
import org.drools.compiler.PackageBuilder;
import org.drools.compiler.PackageBuilderConfiguration;
import org.drools.marshalling.DefaultMarshaller;
import org.drools.marshalling.Marshaller;
import org.drools.marshalling.RuleBaseNodes;
import org.drools.reteoo.ObjectTypeNode;
import org.drools.reteoo.RuleTerminalNode;
import org.drools.rule.MapBackedClassLoader;
import org.drools.rule.Package;
import org.drools.rule.Rule;
import org.drools.spi.GlobalResolver;
import org.drools.util.DroolsStreamUtils;

public class MarshallingTest extends TestCase {

    public void testSerializable() throws Exception {

        final Reader reader = new InputStreamReader( getClass().getResourceAsStream( "test_Serializable.drl" ) );

        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( reader );
        final Package pkg = SerializationHelper.serializeObject( builder.getPackage() );

        assertEquals( 0,
                      builder.getErrors().getErrors().length );

        RuleBase ruleBase = getRuleBase( pkg );// RuleBaseFactory.newRuleBase();

        Map map = new HashMap();
        map.put( "x",
                 ruleBase );
        map = SerializationHelper.serializeObject( map );
        ruleBase = (RuleBase) map.get( "x" );
        final Rule[] rules = ruleBase.getPackages()[0].getRules();
        assertEquals( 4,
                      rules.length );

        assertEquals( "match Person 1",
                      rules[0].getName() );
        assertEquals( "match Person 2",
                      rules[1].getName() );
        assertEquals( "match Person 3",
                      rules[2].getName() );
        assertEquals( "match Integer",
                      rules[3].getName() );

        StatefulSession session = ruleBase.newStatefulSession();

        session.setGlobal( "list",
                           new ArrayList() );

        final Person bob = new Person( "bob" );
        session.insert( bob );

        session = SerializationHelper.getSerialisedStatefulSession( session );

        assertEquals( 1,
                      IteratorToList.convert( session.iterateObjects() ).size() );
        assertEquals( bob,
                      IteratorToList.convert( session.iterateObjects() ).get( 0 ) );

        assertEquals( 2,
                      session.getAgenda().agendaSize() );

        session.fireAllRules();

        final List list = (List) session.getGlobal( "list" );

        assertEquals( 3,
                      list.size() );
        // because of agenda-groups
        assertEquals( new Integer( 4 ),
                      list.get( 0 ) );

        assertEquals( 2,
                      IteratorToList.convert( session.iterateObjects() ).size() );
        assertTrue( IteratorToList.convert( session.iterateObjects() ).contains( bob ) );
        assertTrue( IteratorToList.convert( session.iterateObjects() ).contains( new Person( "help" ) ) );
    }

    public void testSerializeWorkingMemoryAndRuleBase1() throws Exception {
        // has the first newStatefulSession before the ruleBase is serialised
        final Reader reader = new InputStreamReader( getClass().getResourceAsStream( "test_Serializable.drl" ) );

        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( reader );
        final Package pkg = SerializationHelper.serializeObject( builder.getPackage() );

        assertEquals( 0,
                      builder.getErrors().getErrors().length );

        RuleBase ruleBase = getRuleBase();// RuleBaseFactory.newRuleBase();

        StatefulSession session = ruleBase.newStatefulSession();

        Map map = new HashMap();
        map.put( "x",
                 ruleBase );
        map = SerializationHelper.serializeObject( map );
        ruleBase = (RuleBase) map.get( "x" );

        ruleBase.addPackage( pkg );
        
        session = SerializationHelper.getSerialisedStatefulSession( session, ruleBase );  
        ruleBase = SerializationHelper.serializeObject( ruleBase );
              
        session.setGlobal( "list",
                           new ArrayList() );

        final Person bob = new Person( "bob" );
        session.insert( bob );

        final Rule[] rules = ruleBase.getPackages()[0].getRules();

        assertEquals( 4,
                      rules.length );

        assertEquals( "match Person 1",
                      rules[0].getName() );
        assertEquals( "match Person 2",
                      rules[1].getName() );
        assertEquals( "match Person 3",
                      rules[2].getName() );
        assertEquals( "match Integer",
                      rules[3].getName() );

        assertEquals( 1,
                      IteratorToList.convert( session.iterateObjects() ).size() );
        assertEquals( bob,
                      IteratorToList.convert( session.iterateObjects() ).get( 0 ) );

        assertEquals( 2,
                      session.getAgenda().agendaSize() );

        session = SerializationHelper.getSerialisedStatefulSession( session );
        session.fireAllRules();

        final List list = (List) session.getGlobal( "list" );

        assertEquals( 3,
                      list.size() );
        // because of agenda-groups
        assertEquals( new Integer( 4 ),
                      list.get( 0 ) );

        assertEquals( 2,
                      IteratorToList.convert( session.iterateObjects() ).size() );
        assertTrue( IteratorToList.convert( session.iterateObjects() ).contains( bob ) );
        assertTrue( IteratorToList.convert( session.iterateObjects() ).contains( new Person( "help" ) ) );

    }

    public void testSerializeWorkingMemoryAndRuleBase2() throws Exception {
        // has the first newStatefulSession after the ruleBase is serialised
        final Reader reader = new InputStreamReader( getClass().getResourceAsStream( "test_Serializable.drl" ) );

        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( reader );
        final Package pkg = SerializationHelper.serializeObject( builder.getPackage() );

        assertEquals( 0,
                      builder.getErrors().getErrors().length );

        RuleBase ruleBase = getRuleBase();// RuleBaseFactory.newRuleBase();

        // serialise a hashmap with the RuleBase as a key
        Map map = new HashMap();
        map.put( "x",
                 ruleBase );
        map = SerializationHelper.serializeObject( map );
        ruleBase = (RuleBase) map.get( "x" );

        StatefulSession session = ruleBase.newStatefulSession();

        // serialise the working memory before population
        session = SerializationHelper.getSerialisedStatefulSession( session, ruleBase );

        ruleBase.addPackage( pkg );

        session.setGlobal( "list",
                                 new ArrayList() );

        final Person bob = new Person( "bob" );
        session.insert( bob );

        final Rule[] rules = ruleBase.getPackages()[0].getRules();

        assertEquals( 4,
                      rules.length );

        assertEquals( "match Person 1",
                      rules[0].getName() );
        assertEquals( "match Person 2",
                      rules[1].getName() );
        assertEquals( "match Person 3",
                      rules[2].getName() );
        assertEquals( "match Integer",
                      rules[3].getName() );

        assertEquals( 1,
                      IteratorToList.convert( session.iterateObjects() ).size() );
        assertEquals( bob,
                      IteratorToList.convert( session.iterateObjects() ).get( 0 ) );

        assertEquals( 2,
                      session.getAgenda().agendaSize() );

        session = SerializationHelper.getSerialisedStatefulSession( session );
        session.fireAllRules();

        final List list = (List) session.getGlobal( "list" );

        assertEquals( 3,
                      list.size() );
        // because of agenda-groups
        assertEquals( new Integer( 4 ),
                      list.get( 0 ) );

        assertEquals( 2,
                      IteratorToList.convert( session.iterateObjects() ).size() );
        assertTrue( IteratorToList.convert( session.iterateObjects() ).contains( bob ) );
        assertTrue( IteratorToList.convert( session.iterateObjects() ).contains( new Person( "help" ) ) );
    }

    public void testSerializeWorkingMemoryAndRuleBase3() throws Exception {
        // has the first newStatefulSession after the ruleBase is serialised
        final Reader reader = new InputStreamReader( getClass().getResourceAsStream( "test_Serializable.drl" ) );

        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( reader );
        final Package pkg = SerializationHelper.serializeObject( builder.getPackage() );

        assertEquals( 0,
                      builder.getErrors().getErrors().length );

        RuleBase ruleBase = getRuleBase();
        StatefulSession session = ruleBase.newStatefulSession();

        ruleBase.addPackage( pkg );

        session.setGlobal( "list",
                                 new ArrayList() );

        final Person bob = new Person( "bob" );
        session.insert( bob );

        // serialise a hashmap with the RuleBase as a key, after WM population
        Map map = new HashMap();
        map.put( "x",
                 ruleBase );
        map = SerializationHelper.serializeObject( map );
        ruleBase = (RuleBase) map.get( "x" );

        // now try serialising with a fully populated wm from a serialised rulebase
        session = SerializationHelper.getSerialisedStatefulSession( session, ruleBase );

        final Rule[] rules = ruleBase.getPackages()[0].getRules();

        assertEquals( 4,
                      rules.length );

        assertEquals( "match Person 1",
                      rules[0].getName() );
        assertEquals( "match Person 2",
                      rules[1].getName() );
        assertEquals( "match Person 3",
                      rules[2].getName() );
        assertEquals( "match Integer",
                      rules[3].getName() );

        assertEquals( 1,
                      IteratorToList.convert( session.iterateObjects() ).size() );
        assertEquals( bob,
                      IteratorToList.convert( session.iterateObjects() ).get( 0 ) );

        assertEquals( 2,
                      session.getAgenda().agendaSize() );

        session = SerializationHelper.getSerialisedStatefulSession( session, ruleBase );
        session.fireAllRules();

        final List list = (List) session.getGlobal( "list" );

        assertEquals( 3,
                      list.size() );
        // because of agenda-groups
        assertEquals( new Integer( 4 ),
                      list.get( 0 ) );

        assertEquals( 2,
                      IteratorToList.convert( session.iterateObjects() ).size() );
        assertTrue( IteratorToList.convert( session.iterateObjects() ).contains( bob ) );
        assertTrue( IteratorToList.convert( session.iterateObjects() ).contains( new Person( "help" ) ) );
    }

    public void testSerializeAdd() throws Exception {

        //Create a rulebase, a session, and test it
        RuleBase ruleBase = RuleBaseFactory.newRuleBase();
        PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_Dynamic1.drl" ) ) );
        Package pkg = SerializationHelper.serializeObject( builder.getPackage() );
        ruleBase.addPackage( pkg );
        ruleBase = SerializationHelper.serializeObject( ruleBase );

        StatefulSession session = ruleBase.newStatefulSession();
        List list = new ArrayList();
        session.setGlobal( "list",
                           list );

        InternalFactHandle stilton = (InternalFactHandle) session.insert( new Cheese( "stilton",
                                                                                      10 ) );
        InternalFactHandle brie = (InternalFactHandle) session.insert( new Cheese( "brie",
                                                                                   10 ) );
        session.fireAllRules();

        assertEquals( list.size(),
                      1 );
        assertEquals( "stilton",
                      list.get( 0 ) );

        // now recreate the rulebase, deserialize the session and test it
        session = SerializationHelper.getSerialisedStatefulSession( session, ruleBase );
        list = (List) session.getGlobal( "list" );

        assertNotNull( list );
        assertEquals( list.size(),
                      1 );
        assertEquals( "stilton",
                      list.get( 0 ) );

        builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_Dynamic3.drl" ) ) );
        pkg = SerializationHelper.serializeObject( builder.getPackage() );
        ruleBase.addPackage( pkg );

        InternalFactHandle stilton2 = (InternalFactHandle) session.insert( new Cheese( "stilton",
                                                                                       10 ) );
        InternalFactHandle brie2 = (InternalFactHandle) session.insert( new Cheese( "brie",
                                                                                    10 ) );
        InternalFactHandle bob = (InternalFactHandle) session.insert( new Person( "bob",
                                                                                  30 ) );
        session.fireAllRules();

        assertEquals( list.size(),
                      3 );
        assertEquals( bob.getObject(),
                      list.get( 1 ) );
        assertEquals( "stilton",
                      list.get( 2 ) );

        session.dispose();

    }

    public void testSerializationOfIndexedWM() throws Exception {
        final Reader reader = new InputStreamReader( getClass().getResourceAsStream( "test_Serializable2.drl" ) );

        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( reader );
        final Package pkg = builder.getPackage();

        assertEquals( builder.getErrors().toString(),
                      0,
                      builder.getErrors().getErrors().length );

        RuleBase ruleBase = getRuleBase( pkg );// RuleBaseFactory.newRuleBase();

        Map map = new HashMap();
        map.put( "x",
                 ruleBase );
        map = SerializationHelper.serializeObject( map );
        ruleBase = (RuleBase) map.get( "x" );
        final Rule[] rules = ruleBase.getPackages()[0].getRules();
        assertEquals( 3,
                      rules.length );

        StatefulSession session = ruleBase.newStatefulSession();

        session.setGlobal( "list",
                                 new ArrayList() );

        final Primitives p = new Primitives();
        p.setBytePrimitive( (byte) 1 );
        p.setShortPrimitive( (short) 2 );
        p.setIntPrimitive( (int) 3 );
        session.insert( p );

        session = SerializationHelper.getSerialisedStatefulSession( session, ruleBase );

        assertEquals( 1,
                      IteratorToList.convert( session.iterateObjects() ).size() );
        assertEquals( p,
                      IteratorToList.convert( session.iterateObjects() ).get( 0 ) );

        assertEquals( 3,
                      session.getAgenda().agendaSize() );

        session = SerializationHelper.getSerialisedStatefulSession( session, ruleBase );
        session.fireAllRules();

        final List list = (List) session.getGlobal( "list" );

        assertEquals( 3,
                      list.size() );
        // because of agenda-groups
        assertEquals( "1",
                      list.get( 0 ) );
        assertEquals( "2",
                      list.get( 1 ) );
        assertEquals( "3",
                      list.get( 2 ) );

    }

    /*
     *  Here I am inserting data points which are not used by any rule (e.g Person).
     *  Later adding rule (e.g. Rule: 'match Person') for those data points.
     *
     *  Result: Pkg/Rule addition is failing with ClassCastException
     */
    public void testSerializeAdd2() throws Exception {

        //Create a rulebase, a session, and test it
        RuleBase ruleBase = RuleBaseFactory.newRuleBase();
        PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_Dynamic1_0.drl" ) ) );
        Package pkg = SerializationHelper.serializeObject( builder.getPackage() );
        ruleBase.addPackage( pkg );

        List results = new ArrayList();
        StatefulSession session = ruleBase.newStatefulSession();
        session.setGlobal( "results",
                           results );

        InternalFactHandle stilton1 = (InternalFactHandle) session.insert( new Cheese( "stilton",
                                                                                       10 ) );
        session.insert( new Cheese( "brie",
                                    10 ) );
        InternalFactHandle bob = (InternalFactHandle) session.insert( new Person( "bob",
                                                                                  10 ) );

        // fire rules
        session.fireAllRules();
        // check the results are correct
        assertEquals( 1,
                      results.size() );
        assertEquals( stilton1.getObject(),
                      results.get( 0 ) );

        // serialize session and rulebase out
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Marshaller marshaller = new DefaultMarshaller();
        ruleBase.writeStatefulSession( session, baos, marshaller );
        GlobalResolver resolver = session.getGlobalResolver();
        byte[] serializedRulebase = DroolsStreamUtils.streamOut( ruleBase );
        session.dispose();

        // now deserialize the rulebase, deserialize the session and test it
        ruleBase = (RuleBase) DroolsStreamUtils.streamIn( serializedRulebase );
        session = ruleBase.readStatefulSession( new ByteArrayInputStream( baos.toByteArray() ), marshaller );
        session.setGlobalResolver( resolver );

        // dynamically add a new package
        builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_Dynamic3_0.drl" ) ) );
        pkg = SerializationHelper.serializeObject( builder.getPackage() );
        ruleBase.addPackage( pkg );

        InternalFactHandle stilton2 = (InternalFactHandle) session.insert( new Cheese( "stilton",
                                                                                       20 ) );
        session.insert( new Cheese( "brie",
                                    20 ) );
        InternalFactHandle mark = (InternalFactHandle) session.insert( new Person( "mark",
                                                                                   20 ) );
        session.fireAllRules();

        results = (List) session.getGlobal( "results" );
        assertEquals( 4,
                      results.size() );
        assertEquals( bob.getObject(),
                      results.get( 1 ) );
        assertEquals( stilton2.getObject(),
                      results.get( 2 ) );
        assertEquals( mark.getObject(),
                      results.get( 3 ) );

        serializedRulebase = null;

        session = SerializationHelper.getSerialisedStatefulSession( session, ruleBase );
        serializedRulebase = DroolsStreamUtils.streamOut( ruleBase );

        // dispose session
        session.dispose();

    }

    /*
     *  Here I am inserting data points(e.g. Cheese) with  'stilton' / 'brie' as type value.
     *  Initially I had only 1 rule ('like stilton') for data points(e.g. Cheese) having type as 'stilton'.
     *
     *  Later added new rule ('like brie')  for data points(e.g. Cheese) having type as 'brie'.
     *
     *  Result: new rule is not getting fired for new data points having type as 'brie'.
     *          Only for old data points having type as 'brie' the new rule got fired.
     */
    public void testSerializeAdd_newRuleNotFiredForNewData() throws Exception {
        //Create a rulebase, a session, and test it
        RuleBase ruleBase = RuleBaseFactory.newRuleBase();
        PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_Dynamic1_0.drl" ) ) );
        Package pkg = SerializationHelper.serializeObject( builder.getPackage() );
        ruleBase.addPackage( pkg );

        List results = new ArrayList();
        StatefulSession session = ruleBase.newStatefulSession();
        session.setGlobal( "results",
                           results );

        InternalFactHandle stilton1 = (InternalFactHandle) session.insert( new Cheese( "stilton",
                                                                                       10 ) );
        InternalFactHandle brie1 = (InternalFactHandle) session.insert( new Cheese( "brie",
                                                                                    10 ) );
        session.fireAllRules();

        GlobalResolver resolver = session.getGlobalResolver();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Marshaller marshaller = new DefaultMarshaller();        
        ruleBase.writeStatefulSession( session, baos, marshaller );
        byte[] serializedRulebase = DroolsStreamUtils.streamOut( ruleBase );

        session.dispose();

        assertEquals( 1,
                      results.size() );
        assertEquals( stilton1.getObject(),
                      results.get( 0 ) );

        // now recreate the rulebase, deserialize the session and test it
        ruleBase = (RuleBase) DroolsStreamUtils.streamIn( serializedRulebase );
        session = ruleBase.readStatefulSession( new ByteArrayInputStream( baos.toByteArray() ), marshaller );
        session.setGlobalResolver( resolver );
        results = (List) session.getGlobal( "results" );

        builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_Dynamic1_1.drl" ) ) );
        pkg = SerializationHelper.serializeObject( builder.getPackage() );
        ruleBase.addPackage( pkg );

        InternalFactHandle stilton2 = (InternalFactHandle) session.insert( new Cheese( "stilton",
                                                                                       20 ) );
        InternalFactHandle brie2 = (InternalFactHandle) session.insert( new Cheese( "brie",
                                                                                    20 ) );
        InternalFactHandle brie3 = (InternalFactHandle) session.insert( new Cheese( "brie",
                                                                                    30 ) );
        session.fireAllRules();
        assertEquals( 5,
                      results.size() );
        assertEquals( brie1.getObject(),
                      results.get( 1 ) );
        assertEquals( stilton2.getObject(),
                      results.get( 2 ) );
        assertEquals( brie2.getObject(),
                      results.get( 4 ) );
        assertEquals( brie3.getObject(),
                      results.get( 3 ) );

        serializedRulebase = null;

        session = SerializationHelper.getSerialisedStatefulSession( session, ruleBase );
        serializedRulebase = DroolsStreamUtils.streamOut( ruleBase );

        session.dispose();
    }

    /*
     *  Works Fine if both the scenarios mentioned above are skipped.
     */
    public void testSerializeAdd3() throws Exception {
        //Create a rulebase, a session, and test it
        RuleBase ruleBase = RuleBaseFactory.newRuleBase();
        PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_Dynamic1_0.drl" ) ) );
        Package pkg = SerializationHelper.serializeObject( builder.getPackage() );
        ruleBase.addPackage( pkg );

        List results = new ArrayList();
        StatefulSession session = ruleBase.newStatefulSession();
        session.setGlobal( "results",
                           results );

        InternalFactHandle stilton1 = (InternalFactHandle) session.insert( new Cheese( "stilton",
                                                                                       10 ) );
        InternalFactHandle brie1 = (InternalFactHandle) session.insert( new Cheese( "brie",
                                                                                    10 ) );
        session.fireAllRules();

        GlobalResolver resolver = session.getGlobalResolver();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Marshaller marshaller = new DefaultMarshaller();          
        ruleBase.writeStatefulSession( session, baos, marshaller );
        byte[] serializedRulebase = DroolsStreamUtils.streamOut( ruleBase );

        session.dispose();

        assertEquals( 1,
                      results.size() );
        assertEquals( stilton1.getObject(),
                      results.get( 0 ) );

        // now recreate the rulebase, deserialize the session and test it
        ruleBase = (RuleBase) DroolsStreamUtils.streamIn( serializedRulebase );
        session = ruleBase.readStatefulSession( new ByteArrayInputStream( baos.toByteArray() ), marshaller );
        session.setGlobalResolver( resolver );
        results = (List) session.getGlobal( "results" );

        builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_Dynamic3_0.drl" ) ) );
        pkg = SerializationHelper.serializeObject( builder.getPackage() );

        ruleBase.addPackage( pkg );

        InternalFactHandle stilton2 = (InternalFactHandle) session.insert( new Cheese( "stilton",
                                                                                       20 ) );
        InternalFactHandle brie2 = (InternalFactHandle) session.insert( new Cheese( "brie",
                                                                                    20 ) );
        InternalFactHandle bob1 = (InternalFactHandle) session.insert( new Person( "bob",
                                                                                   20 ) );
        InternalFactHandle bob2 = (InternalFactHandle) session.insert( new Person( "bob",
                                                                                   30 ) );
        session.fireAllRules();

        assertEquals( 4,
                      results.size() );
        assertEquals( stilton2.getObject(),
                      results.get( 1 ) );
        assertEquals( bob2.getObject(),
                      results.get( 2 ) );
        assertEquals( bob1.getObject(),
                      results.get( 3 ) );

        serializedRulebase = null;

        resolver = session.getGlobalResolver();
        baos = new ByteArrayOutputStream();
        marshaller = new DefaultMarshaller();
        ruleBase.writeStatefulSession( session, baos, marshaller );        
        serializedRulebase = DroolsStreamUtils.streamOut( ruleBase );

        session.dispose();

        // now recreate the rulebase, deserialize the session and test it
        ruleBase = (RuleBase) DroolsStreamUtils.streamIn( serializedRulebase ); 
        session = ruleBase.readStatefulSession( new ByteArrayInputStream( baos.toByteArray() ), marshaller );
        session.setGlobalResolver( resolver );
        results = (List) session.getGlobal( "results" );

        builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_Dynamic1_2.drl" ) ) );
        pkg = SerializationHelper.serializeObject( builder.getPackage() );
        ruleBase.addPackage( pkg );

        InternalFactHandle stilton3 = (InternalFactHandle) session.insert( new Cheese( "stilton",
                                                                                       40 ) );
        InternalFactHandle brie3 = (InternalFactHandle) session.insert( new Cheese( "brie",
                                                                                    40 ) );
        InternalFactHandle bob3 = (InternalFactHandle) session.insert( new Person( "bob",
                                                                                   40 ) );
        InternalFactHandle bob4 = (InternalFactHandle) session.insert( new Person( "bob",
                                                                                   40 ) );
        InternalFactHandle addr1 = (InternalFactHandle) session.insert( new Address( "bangalore" ) );
        InternalFactHandle addr2 = (InternalFactHandle) session.insert( new Address( "India" ) );

        session.fireAllRules();

        assertEquals( 9,
                      results.size() );
        assertEquals( stilton3.getObject(),
                      results.get( 4 ) );
        assertEquals( bob4.getObject(),
                      results.get( 5 ) );
        assertEquals( bob3.getObject(),
                      results.get( 6 ) );
        assertEquals( addr2.getObject(),
                      results.get( 7 ) );
        assertEquals( addr1.getObject(),
                      results.get( 8 ) );

        serializedRulebase = null;

        resolver = session.getGlobalResolver();
        baos = new ByteArrayOutputStream();
        marshaller = new DefaultMarshaller();
        ruleBase.writeStatefulSession( session, baos, marshaller );
        serializedRulebase = DroolsStreamUtils.streamOut( ruleBase );
        session.dispose();

        // now recreate the rulebase, deserialize the session and test it
        ruleBase = (RuleBase) DroolsStreamUtils.streamIn( serializedRulebase );
        session = ruleBase.readStatefulSession( new ByteArrayInputStream( baos.toByteArray() ), marshaller );
        session.setGlobalResolver( resolver );
        results = (List) session.getGlobal( "results" );

        InternalFactHandle stilton4 = (InternalFactHandle) session.insert( new Cheese( "stilton",
                                                                                       50 ) );
        InternalFactHandle brie4 = (InternalFactHandle) session.insert( new Cheese( "brie",
                                                                                    50 ) );
        InternalFactHandle bob5 = (InternalFactHandle) session.insert( new Person( "bob",
                                                                                   50 ) );
        InternalFactHandle bob6 = (InternalFactHandle) session.insert( new Person( "bob",
                                                                                   50 ) );
        InternalFactHandle addr3 = (InternalFactHandle) session.insert( new Address( "Tripura" ) );
        InternalFactHandle addr4 = (InternalFactHandle) session.insert( new Address( "Agartala" ) );

        session.fireAllRules();

        assertEquals( 14,
                      results.size() );
        assertEquals( stilton4.getObject(),
                      results.get( 9 ) );
        assertEquals( bob6.getObject(),
                      results.get( 10 ) );
        assertEquals( bob5.getObject(),
                      results.get( 11 ) );
        assertEquals( addr4.getObject(),
                      results.get( 12 ) );
        assertEquals( addr3.getObject(),
                      results.get( 13 ) );

        serializedRulebase = null;

        session = SerializationHelper.getSerialisedStatefulSession( session, ruleBase );
        serializedRulebase = DroolsStreamUtils.streamOut( ruleBase );

        session.dispose();
    }
    
    /*
     * I have tried both the scenarios
     * 1. Remove a rule from a pkg.
     * 2. Remove a pkg
     *
     * But both cases after inserting associated data points (i.e data points which are used to fire/activate the removed rule)
     * session.fireAllRules() is throwing NoClassDefFoundError
     *
     */
    public void testSerializeAddRemove_NoClassDefFoundError() throws Exception {

        //Create a rulebase, a session, and test it
        RuleBase ruleBase = RuleBaseFactory.newRuleBase( );
        PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_Dynamic1_0.drl" ) ) );
        Package pkg = SerializationHelper.serializeObject( builder.getPackage() );
        ruleBase.addPackage( pkg );

        List results = new ArrayList();
        StatefulSession session = ruleBase.newStatefulSession();
        session.setGlobal( "results",
                           results );

        InternalFactHandle stilton1 = (InternalFactHandle) session.insert( new Cheese( "stilton",
                                                                                       10 ) );
        InternalFactHandle brie1 = (InternalFactHandle) session.insert( new Cheese( "brie",
                                                                                    10 ) );
        session.fireAllRules();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Marshaller marshaller = new DefaultMarshaller();
        ruleBase.writeStatefulSession( session, baos, marshaller );
        byte[] serializedRulebase = DroolsStreamUtils.streamOut( ruleBase );

        session.dispose();

        assertEquals( 1,
                      results.size() );
        assertEquals( stilton1.getObject(),
                      results.get( 0 ) );

        // now recreate the rulebase, deserialize the session and test it
        ruleBase = (RuleBase) DroolsStreamUtils.streamIn( serializedRulebase );
        session = ruleBase.readStatefulSession( new ByteArrayInputStream( baos.toByteArray() ), marshaller );
        results.clear();
        session.setGlobal( "results", results );

        builder = new PackageBuilder();
        builder.addPackageFromDrl( new InputStreamReader( getClass().getResourceAsStream( "test_Dynamic3_0.drl" ) ) );
        pkg = SerializationHelper.serializeObject( builder.getPackage() );

        ruleBase.addPackage( pkg );

        InternalFactHandle stilton2 = (InternalFactHandle) session.insert( new Cheese( "stilton", 20 ) );
        InternalFactHandle brie2 = (InternalFactHandle) session.insert( new Cheese( "brie", 20 ) );
        InternalFactHandle bob1 = (InternalFactHandle) session.insert( new Person( "bob", 20 ) );
        InternalFactHandle bob2 = (InternalFactHandle) session.insert( new Person( "bob", 30 ) );
        session.fireAllRules();

        assertEquals( 3,
                      results.size() );
        assertEquals( stilton2.getObject(),
                      results.get( 0 ) );
        assertEquals( bob2.getObject(),
                      results.get( 1 ) );
        assertEquals( bob1.getObject(),
                      results.get( 2 ) );

        serializedRulebase = null;

        baos = new ByteArrayOutputStream();
        marshaller = new DefaultMarshaller();
        ruleBase.writeStatefulSession( session, baos, marshaller );
        serializedRulebase = DroolsStreamUtils.streamOut( ruleBase );

        session.dispose();

        // now recreate the rulebase, deserialize the session and test it
        ruleBase = (RuleBase) DroolsStreamUtils.streamIn( serializedRulebase );
        session = ruleBase.readStatefulSession( new ByteArrayInputStream( baos.toByteArray() ), marshaller );
        results.clear();
        session.setGlobal( "results", results );

        // CASE 1: remove rule
        ruleBase.removeRule("org.drools.test", "like stilton");

        InternalFactHandle stilton3 = (InternalFactHandle) session.insert( new Cheese( "stilton", 20 ) );
        InternalFactHandle brie3 = (InternalFactHandle) session.insert( new Cheese( "brie", 20 ) );
        InternalFactHandle bob3 = (InternalFactHandle) session.insert( new Person( "bob", 20 ) );
        InternalFactHandle bob4 = (InternalFactHandle) session.insert( new Person( "bob", 30 ) );
        session.fireAllRules();

        assertEquals( 2,
                      results.size() );
        assertEquals( bob4.getObject(),
                      results.get( 0 ) );
        assertEquals( bob3.getObject(),
                      results.get( 1 ) );


        // now recreate the rulebase, deserialize the session and test it
        ruleBase = (RuleBase) DroolsStreamUtils.streamIn( serializedRulebase );
        session = ruleBase.readStatefulSession( new ByteArrayInputStream( baos.toByteArray() ), marshaller );
        results.clear();
        session.setGlobal( "results", results );

        // CASE 2: remove pkg
        ruleBase.removePackage("org.drools.test");

        InternalFactHandle stilton4 = (InternalFactHandle) session.insert( new Cheese( "stilton", 20 ) );
        InternalFactHandle brie4 = (InternalFactHandle) session.insert( new Cheese( "brie", 20 ) );
        InternalFactHandle bob5 = (InternalFactHandle) session.insert( new Person( "bob", 20 ) );
        InternalFactHandle bob6 = (InternalFactHandle) session.insert( new Person( "bob", 30 ) );
        session.fireAllRules();

        assertEquals( 2,
                      results.size() );
        assertEquals( bob6.getObject(),
                      results.get( 0 ) );
        assertEquals( bob5.getObject(),
                      results.get( 1 ) );

        byte[] serializedSession = null;
        serializedRulebase = null;
        baos = new ByteArrayOutputStream();
        marshaller = new DefaultMarshaller();
        ruleBase.writeStatefulSession( session, baos,marshaller  );
        serializedRulebase = DroolsStreamUtils.streamOut( ruleBase );

        session.dispose();
        // Deserialize the rulebase and the session 
        ruleBase = (RuleBase) DroolsStreamUtils.streamIn( serializedRulebase );
        session = ruleBase.readStatefulSession( new ByteArrayInputStream( baos.toByteArray() ), marshaller );    //  throws java.lang.ClassNotFoundException Exception
        results.clear();
        session.setGlobal( "results", results );
             
        InternalFactHandle stilton5 = (InternalFactHandle) session.insert( new Cheese( "stilton", 30 ) );
        InternalFactHandle brie5 = (InternalFactHandle) session.insert( new Cheese( "brie", 30 ) );
        InternalFactHandle bob7 = (InternalFactHandle) session.insert( new Person( "bob", 30 ) );
        InternalFactHandle bob8 = (InternalFactHandle) session.insert( new Person( "bob", 40 ) );
        session.fireAllRules();
 
        assertEquals( 2,
                      results.size() );
        assertEquals( bob8.getObject(),
                      results.get( 0 ) );
        assertEquals( bob7.getObject(),
                      results.get( 1 ) );
       
        serializedSession = null;
        serializedRulebase = null;
       
        session = SerializationHelper.getSerialisedStatefulSession( session, ruleBase );
        serializedRulebase = DroolsStreamUtils.streamOut( ruleBase );
       
        session.dispose();        
       
    }    

    /**
     * In this case we are dealing with facts which are not on the systems classpath.
     *
     */
    public void testSerializabilityWithJarFacts() throws Exception {
        MapBackedClassLoader loader = new MapBackedClassLoader( this.getClass().getClassLoader() );

        JarInputStream jis = new JarInputStream( this.getClass().getResourceAsStream( "/billasurf.jar" ) );

        JarEntry entry = null;
        byte[] buf = new byte[1024];
        int len = 0;
        while ( (entry = jis.getNextJarEntry()) != null ) {
            if ( !entry.isDirectory() ) {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                while ( (len = jis.read( buf )) >= 0 ) {
                    out.write( buf,
                               0,
                               len );
                }
                loader.addResource( entry.getName(),
                                    out.toByteArray() );
            }
        }

        String drl = "package foo.bar \n" + "import com.billasurf.Board\n" + "rule 'MyGoodRule' \n dialect 'mvel' \n when Board() then System.err.println(42); \n end\n";

        PackageBuilder builder = new PackageBuilder( new PackageBuilderConfiguration( loader ) );
        builder.addPackageFromDrl( new StringReader( drl ) );
        assertFalse( builder.hasErrors() );

        Package p = builder.getPackage();
        byte[] ser = DroolsStreamUtils.streamOut( p );

        //now read it back
        Package p_ = (Package) DroolsStreamUtils.streamIn( ser,
                                                           loader );
        assertNotNull( p_ );
    }

    public void testEmptyRule() throws Exception {
        String rule = "package org.test;\n";
        rule += "global java.util.List list\n";
        rule += "rule \"Rule 1\"\n";
        rule += "when\n";
        rule += "then\n";
        rule += "    list.add( \"fired\" );\n";
        rule += "end";

        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new StringReader( rule ) );
        assertTrue( builder.getErrors().isEmpty() );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = RuleBaseFactory.newRuleBase();
        ruleBase.addPackage( pkg );

        // Make sure the rete node map is created correctly
        Map<Integer, BaseNode> nodes = RuleBaseNodes.getNodeMap( (InternalRuleBase) ruleBase );
        assertEquals( 2,
                      nodes.size() );
        assertEquals( "InitialFact",
                      ((ClassObjectType) ((ObjectTypeNode) nodes.get( 3 )).getObjectType()).getClassType().getSimpleName() );
        assertEquals( "Rule 1",
                      ((RuleTerminalNode) nodes.get( 5 )).getRule().getName() );

        StatefulSession session = ruleBase.newStatefulSession();

        List list = new ArrayList();
        session.setGlobal( "list",
                           list );

        session = getSerialisedStatefulSession( session );

        session.fireAllRules();

        session = getSerialisedStatefulSession( session );

        assertEquals( 1,
                      ((List) session.getGlobal( "list" )).size() );
        assertEquals( "fired",
                      ((List) session.getGlobal( "list" )).get( 0 ) );
    }

    public void testDynamicEmptyRule() throws Exception {
        String rule1 = "package org.test;\n";
        rule1 += "global java.util.List list\n";
        rule1 += "rule \"Rule 1\"\n";
        rule1 += "when\n";
        rule1 += "then\n";
        rule1 += "    list.add( \"fired1\" );\n";
        rule1 += "end";

        String rule2 = "package org.test;\n";
        rule2 += "global java.util.List list\n";
        rule2 += "rule \"Rule 2\"\n";
        rule2 += "when\n";
        rule2 += "then\n";
        rule2 += "    list.add( \"fired2\" );\n";
        rule2 += "end";

        PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new StringReader( rule1 ) );
        Package pkg = builder.getPackage();

        final RuleBase ruleBase = RuleBaseFactory.newRuleBase();
        ruleBase.addPackage( pkg );

        // Make sure the rete node map is created correctly
        Map<Integer, BaseNode> nodes = RuleBaseNodes.getNodeMap( (InternalRuleBase) ruleBase );
        assertEquals( 2,
                      nodes.size() );
        assertEquals( "InitialFact",
                      ((ClassObjectType) ((ObjectTypeNode) nodes.get( 3 )).getObjectType()).getClassType().getSimpleName() );
        assertEquals( "Rule 1",
                      ((RuleTerminalNode) nodes.get( 5 )).getRule().getName() );

        StatefulSession session = ruleBase.newStatefulSession();

        List list = new ArrayList();
        session.setGlobal( "list",
                           list );
        StatefulSession session1 = getSerialisedStatefulSession( session );
        session1.fireAllRules();

        assertEquals( 1,
                      ((List) session1.getGlobal( "list" )).size() );

        WorkingMemory session2 = getSerialisedStatefulSession( session1,
                                                               true );

        session.dispose();
        session1.dispose();

        builder = new PackageBuilder();
        builder.addPackageFromDrl( new StringReader( rule2 ) );
        pkg = builder.getPackage();

        ruleBase.addPackage( pkg );

        assertEquals( 2,
                      ((List) session2.getGlobal( "list" )).size() );
        assertEquals( "fired1",
                      ((List) session2.getGlobal( "list" )).get( 0 ) );
        assertEquals( "fired2",
                      ((List) session2.getGlobal( "list" )).get( 1 ) );
    }

    public void testSinglePattern() throws Exception {
        String rule = "package org.test;\n";
        rule += "import org.drools.Person\n";
        rule += "global java.util.List list\n";
        rule += "rule \"Rule 1\"\n";
        rule += "when\n";
        rule += "    $p : Person( ) \n";
        rule += "then\n";
        rule += "    list.add( $p );\n";
        rule += "end";

        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new StringReader( rule ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = RuleBaseFactory.newRuleBase();
        ruleBase.addPackage( pkg );

        // Make sure the rete node map is created correctly
        Map<Integer, BaseNode> nodes = RuleBaseNodes.getNodeMap( (InternalRuleBase) ruleBase );
        assertEquals( 2,
                      nodes.size() );
        assertEquals( "Person",
                      ((ClassObjectType) ((ObjectTypeNode) nodes.get( 3 )).getObjectType()).getClassType().getSimpleName() );
        assertEquals( "Rule 1",
                      ((RuleTerminalNode) nodes.get( 5 )).getRule().getName() );

        StatefulSession session = ruleBase.newStatefulSession();

        List list = new ArrayList();
        session.setGlobal( "list",
                           list );

        Person p = new Person( "bobba fet",
                               32 );
        session.insert( p );

        session = getSerialisedStatefulSession( session );
        session.fireAllRules();

        assertEquals( 1,
                      ((List) session.getGlobal( "list" )).size() );
        assertEquals( p,
                      ((List) session.getGlobal( "list" )).get( 0 ) );
    }

    public void testSingleRuleSingleJoinNodePattern() throws Exception {
        String rule = "package org.test;\n";
        rule += "import org.drools.Person\n";
        rule += "import org.drools.Cheese\n";
        rule += "global java.util.List list\n";
        rule += "rule \"Rule 1\"\n";
        rule += "when\n";
        rule += "    $c : Cheese( ) \n";
        rule += "    $p : Person( cheese == $c ) \n";
        rule += "then\n";
        rule += "    list.add( $p );\n";
        rule += "end";

        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new StringReader( rule ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = RuleBaseFactory.newRuleBase();
        ruleBase.addPackage( pkg );

        // Make sure the rete node map is created correctly
        Map<Integer, BaseNode> nodes = RuleBaseNodes.getNodeMap( (InternalRuleBase) ruleBase );
        assertEquals( 4,
                      nodes.size() );
        assertEquals( "Cheese",
                      ((ClassObjectType) ((ObjectTypeNode) nodes.get( 3 )).getObjectType()).getClassType().getSimpleName() );
        assertEquals( "Person",
                      ((ClassObjectType) ((ObjectTypeNode) nodes.get( 6 )).getObjectType()).getClassType().getSimpleName() );
        assertEquals( "JoinNode",
                      nodes.get( 7 ).getClass().getSimpleName() );
        assertEquals( "Rule 1",
                      ((RuleTerminalNode) nodes.get( 8 )).getRule().getName() );

        StatefulSession session = ruleBase.newStatefulSession();

        List list = new ArrayList();
        session.setGlobal( "list",
                           list );

        Cheese stilton = new Cheese( "stilton",
                                     25 );
        Cheese brie = new Cheese( "brie",
                                  49 );
        Person bobba = new Person( "bobba fet",
                                   32 );
        bobba.setCheese( stilton );

        Person vadar = new Person( "darth vadar",
                                   32 );

        session.insert( stilton );
        session.insert( bobba );
        session.insert( vadar );
        session.insert( brie );

        session = getSerialisedStatefulSession( session );

        session.fireAllRules();

        assertEquals( 1,
                      ((List) session.getGlobal( "list" )).size() );
        assertEquals( bobba,
                      ((List) session.getGlobal( "list" )).get( 0 ) );

        Person c3po = new Person( "c3p0",
                                  32 );
        c3po.setCheese( stilton );
        session.insert( c3po );

        session = getSerialisedStatefulSession( session );

        session.fireAllRules();

        assertEquals( 2,
                      ((List) session.getGlobal( "list" )).size() );
        assertEquals( c3po,
                      ((List) session.getGlobal( "list" )).get( 1 ) );

        Person r2d2 = new Person( "r2d2",
                                  32 );
        r2d2.setCheese( brie );
        session.insert( r2d2 );

        System.out.println( "\n\njointpattern" );
        session = getSerialisedStatefulSession( session );

        session.fireAllRules();

        assertEquals( 3,
                      ((List) session.getGlobal( "list" )).size() );
        assertEquals( r2d2,
                      ((List) session.getGlobal( "list" )).get( 2 ) );
    }

    public void testMultiRuleMultiJoinNodePatternsWithHalt() throws Exception {
        String rule1 = "package org.test;\n";
        rule1 += "import org.drools.Person\n";
        rule1 += "import org.drools.Cheese\n";
        rule1 += "global java.util.List list\n";
        rule1 += "rule \"Rule 1\"\n";
        rule1 += "when\n";
        rule1 += "    $c : Cheese( ) \n";
        rule1 += "    $p : Person( cheese == $c ) \n";
        rule1 += "then\n";
        rule1 += "    list.add( $p );\n";
        rule1 += "end";

        String rule2 = "package org.test;\n";
        rule2 += "import org.drools.Person\n";
        rule2 += "import org.drools.Cheese\n";
        rule2 += "import org.drools.Cell\n";
        rule2 += "global java.util.List list\n";
        rule2 += "rule \"Rule 2\"\n";
        rule2 += "when\n";
        rule2 += "    $c : Cheese( ) \n";
        rule2 += "    $p : Person( cheese == $c ) \n";
        rule2 += "    Cell( value == $p.age ) \n";
        rule2 += "then\n";
        rule2 += "    list.add( $p );\n";
        rule2 += "end";

        String rule3 = "package org.test;\n";
        rule3 += "import org.drools.FactA\n";
        rule3 += "import org.drools.FactB\n";
        rule3 += "import org.drools.FactC\n";
        rule3 += "import org.drools.Person\n";
        rule3 += "global java.util.List list\n";
        rule3 += "rule \"Rule 3\"\n";
        rule3 += "when\n";
        rule3 += "    $a : FactA( field2 > 10 ) \n";
        rule3 += "    $b : FactB( f2 >= $a.field2 ) \n";
        rule3 += "    $p : Person( name == \"darth vadar\" ) \n";
        rule3 += "    $c : FactC( f2 >= $b.f2 ) \n";
        rule3 += "then\n";
        rule3 += "    list.add( $c );\n";
        rule3 += "    drools.halt();\n";
        rule3 += "end";

        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new StringReader( rule1 ) );
        builder.addPackageFromDrl( new StringReader( rule2 ) );
        builder.addPackageFromDrl( new StringReader( rule3 ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = RuleBaseFactory.newRuleBase();
        ruleBase.addPackage( pkg );

        StatefulSession session = ruleBase.newStatefulSession();

        List list = new ArrayList();
        session.setGlobal( "list",
                           list );

        Cheese stilton = new Cheese( "stilton",
                                     25 );
        Cheese brie = new Cheese( "brie",
                                  49 );
        Person bobba = new Person( "bobba fet",
                                   30 );
        bobba.setCheese( stilton );
        Person vadar = new Person( "darth vadar",
                                   38 );
        Person c3po = new Person( "c3p0",
                                  17 );
        c3po.setCheese( stilton );
        Person r2d2 = new Person( "r2d2",
                                  58 );
        r2d2.setCheese( brie );

        session.insert( stilton );
        session.insert( bobba );
        session.insert( vadar );
        session.insert( brie );
        session.insert( c3po );
        session.insert( r2d2 );

        session = getSerialisedStatefulSession( session );
        session.fireAllRules();

        assertEquals( 3,
                      ((List) session.getGlobal( "list" )).size() );
        assertEquals( bobba,
                      ((List) session.getGlobal( "list" )).get( 2 ) );
        assertEquals( c3po,
                      ((List) session.getGlobal( "list" )).get( 1 ) );
        assertEquals( r2d2,
                      ((List) session.getGlobal( "list" )).get( 0 ) );

        session = getSerialisedStatefulSession( session );

        session.insert( new Cell( 30 ) );
        session.insert( new Cell( 58 ) );

        session = getSerialisedStatefulSession( session );

        session.fireAllRules();

        assertEquals( 5,
                      ((List) session.getGlobal( "list" )).size() );
        assertEquals( r2d2,
                      ((List) session.getGlobal( "list" )).get( 3 ) );
        assertEquals( bobba,
                      ((List) session.getGlobal( "list" )).get( 4 ) );

        session = getSerialisedStatefulSession( session );

        session.insert( new FactA( 15 ) );
        session.insert( new FactB( 20 ) );
        session.insert( new FactC( 27 ) );
        session.insert( new FactC( 52 ) );

        session = getSerialisedStatefulSession( session );

        session.fireAllRules();

        assertEquals( 6,
                      ((List) session.getGlobal( "list" )).size() );
        assertEquals( new FactC( 52 ),
                      ((List) session.getGlobal( "list" )).get( 5 ) );

        session = getSerialisedStatefulSession( session );

        session.fireAllRules();

        assertEquals( 7,
                      ((List) session.getGlobal( "list" )).size() );
        assertEquals( new FactC( 27 ),
                      ((List) session.getGlobal( "list" )).get( 6 ) );
    }

    public void testNot() throws Exception {
        String header = "package org.drools.test;\n";
        header += "import java.util.List;\n";
        header += "import org.drools.Person\n";
        header += "import org.drools.Cheese\n";
        header += "global java.util.List list;\n";

        String rule1 = "rule \"not rule test\"\n";
        rule1 += "salience 10\n";
        rule1 += "when\n";
        rule1 += "    Person()\n";
        rule1 += "    not Cheese( price >= 5 )\n";
        rule1 += "then\n";
        rule1 += "    list.add( new Integer( 5 ) );\n";
        rule1 += "end\n";     

        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new StringReader( header + rule1 ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = RuleBaseFactory.newRuleBase();
        ruleBase.addPackage( pkg );

        StatefulSession session = ruleBase.newStatefulSession();
        List list = new ArrayList();
        session.setGlobal( "list",
                           list );

        // add a person, no cheese
        session = getSerialisedStatefulSession( session );
        Person bobba = new Person( "bobba fet",
                                   50 );
        session.insert( bobba );
        session = getSerialisedStatefulSession( session );
        session.fireAllRules();
        assertEquals( 1,
                      list.size() );

        // add another person, no cheese
        session = getSerialisedStatefulSession( session );
        Person darth = new Person( "darth vadar",
                                   200 );
        session.insert( darth );
        session = getSerialisedStatefulSession( session );
        session.fireAllRules();
        assertEquals( 2,
                      list.size() );

        // add cheese 
        session = getSerialisedStatefulSession( session );
        Cheese stilton = new Cheese( "stilton",
                                     5 );
        session.insert( stilton );
        session = getSerialisedStatefulSession( session );
        session.fireAllRules();
        assertEquals( 2,
                      list.size() );

        // remove cheese
        session = getSerialisedStatefulSession( session );
        session.retract( session.getFactHandle( stilton ) );
        session = getSerialisedStatefulSession( session );
        session.fireAllRules();
        assertEquals( 4,
                      list.size() );

        // put 2 cheeses back in
        session = getSerialisedStatefulSession( session );
        session.insert( stilton );
        session = getSerialisedStatefulSession( session );
        Cheese brie = new Cheese( "brie",
                                  18 );
        session.insert( brie );
        session.fireAllRules();
        assertEquals( 4,
                      list.size() );

        // now remove a cheese, should be no change
        session.retract( session.getFactHandle( stilton ) );
        session = getSerialisedStatefulSession( session );
        session.fireAllRules();
        assertEquals( 4,
                      list.size() );
        
        // now remove a person, should be no change
        session.retract( session.getFactHandle( bobba ) );
        session = getSerialisedStatefulSession( session );
        session.fireAllRules();
        assertEquals( 4,
                      list.size() );     
        
        //removal remaining cheese, should increase by one, as one person left
        session.retract( session.getFactHandle( brie ) );
        session = getSerialisedStatefulSession( session );
        session.fireAllRules();
        assertEquals( 5,
                      list.size() );        
    }
    
    public void testExists() throws Exception {
        String header = "package org.drools.test;\n";
        header += "import java.util.List;\n";
        header += "import org.drools.Person\n";
        header += "import org.drools.Cheese\n";
        header += "global java.util.List list;\n";

        String rule1 = "rule \"not rule test\"\n";
        rule1 += "salience 10\n";
        rule1 += "when\n";
        rule1 += "    Person()\n";
        rule1 += "    exists Cheese( price >= 5 )\n";
        rule1 += "then\n";
        rule1 += "    list.add( new Integer( 5 ) );\n";
        rule1 += "end\n";     

        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new StringReader( header + rule1 ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = RuleBaseFactory.newRuleBase();
        ruleBase.addPackage( pkg );

        StatefulSession session = ruleBase.newStatefulSession();
        List list = new ArrayList();
        session.setGlobal( "list",
                           list );

        // add a person, no cheese
        session = getSerialisedStatefulSession( session );
        Person bobba = new Person( "bobba fet",
                                   50 );
        session.insert( bobba );
        session = getSerialisedStatefulSession( session );
        session.fireAllRules();
        assertEquals( 0,
                      list.size() );

        // add another person, no cheese
        session = getSerialisedStatefulSession( session );
        Person darth = new Person( "darth vadar",
                                   200 );
        session.insert( darth );
        session = getSerialisedStatefulSession( session );
        session.fireAllRules();
        assertEquals( 0,
                      list.size() );

        // add cheese 
        session = getSerialisedStatefulSession( session );
        Cheese stilton = new Cheese( "stilton",
                                     5 );
        session.insert( stilton );
        session = getSerialisedStatefulSession( session );
        session.fireAllRules();
        assertEquals( 2,
                      list.size() );

        // remove cheese
        session = getSerialisedStatefulSession( session );
        session.retract( session.getFactHandle( stilton ) );
        session = getSerialisedStatefulSession( session );
        session.fireAllRules();
        assertEquals( 2,
                      list.size() );

        // put 2 cheeses back in
        session = getSerialisedStatefulSession( session );
        session.insert( stilton );
        session = getSerialisedStatefulSession( session );
        Cheese brie = new Cheese( "brie",
                                  18 );
        session.insert( brie );
        session.fireAllRules();
        assertEquals( 4,
                      list.size() );

        // now remove a cheese, should be no change
        session.retract( session.getFactHandle( stilton ) );
        session = getSerialisedStatefulSession( session );
        session.fireAllRules();
        assertEquals( 4,
                      list.size() );
        
        // now remove a person, should be no change
        session.retract( session.getFactHandle( bobba ) );
        session = getSerialisedStatefulSession( session );
        session.fireAllRules();
        assertEquals( 4,
                      list.size() );     
        
        //removal remaining cheese, no
        session.retract( session.getFactHandle( brie ) );
        session = getSerialisedStatefulSession( session );
        session.fireAllRules();
        assertEquals( 4,
                      list.size() );    
        
        // put one cheese back in, with one person should increase by one
        session = getSerialisedStatefulSession( session );
        session.insert( stilton );
        session.fireAllRules();
        assertEquals( 5,
                      list.size() );        
    }    

    public void testTruthMaintenance() throws Exception {
        String header = "package org.drools.test;\n";
        header += "import java.util.List;\n";
        header += "import org.drools.Person\n";
        header += "import org.drools.Cheese\n";
        header += "global Cheese cheese;\n";
        header += "global Person person;\n";
        header += "global java.util.List list;\n";

        String rule1 = "rule \"not person then cheese\"\n";
        rule1 += "when \n";
        rule1 += "    not Person() \n";
        rule1 += "then \n";
        rule1 += "    if (list.size() < 3) { \n";
        rule1 += "        list.add(new Integer(0)); \n";
        rule1 += "        insertLogical( cheese ); \n" + "    }\n";
        rule1 += "    drools.halt();\n" + "end\n";

        String rule2 = "rule \"if cheese then person\"\n";
        rule2 += "when\n";
        rule2 += "    Cheese()\n";
        rule2 += "then\n";
        rule2 += "    if (list.size() < 3) {\n";
        rule2 += "        list.add(new Integer(0));\n";
        rule2 += "        insertLogical( person );\n";
        rule2 += "    }\n" + "    drools.halt();\n";
        rule2 += "end\n";

        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new StringReader( header + rule1 ) );
        builder.addPackageFromDrl( new StringReader( header + rule2 ) );
        final Package pkg = builder.getPackage();

        final RuleBase ruleBase = RuleBaseFactory.newRuleBase();
        ruleBase.addPackage( pkg );

        StatefulSession session = ruleBase.newStatefulSession();

        final List list = new ArrayList();

        final Person person = new Person( "person" );
        final Cheese cheese = new Cheese( "cheese",
                                          0 );
        session.setGlobal( "cheese",
                           cheese );
        session.setGlobal( "person",
                           person );
        session.setGlobal( "list",
                           list );
        session.fireAllRules();
        assertEquals( 1,
                      list.size() );

        session = getSerialisedStatefulSession( session );
        session.fireAllRules();
        assertEquals( 2,
                      list.size() );

        session = getSerialisedStatefulSession( session );
        session.fireAllRules();
        assertEquals( 3,
                      list.size() );

        // should not grow any further
        session = getSerialisedStatefulSession( session );
        session.fireAllRules();
        assertEquals( 3,
                      list.size() );
    }

    public void testActivationGroups() throws Exception {
        String rule1 = "package org.test;\n";
        rule1 += "import org.drools.Cheese\n";
        rule1 += "global java.util.List list\n";
        rule1 += "rule \"Rule 1\"\n";
        rule1 += "    activation-group \"activation-group-1\"\n";
        rule1 += "when\n";
        rule1 += "    $c : Cheese( ) \n";
        rule1 += "then\n";
        rule1 += "    list.add( \"rule1\" );\n";
        rule1 += "    drools.halt();\n";
        rule1 += "end";

        String rule2 = "package org.test;\n";
        rule2 += "import org.drools.Cheese\n";
        rule2 += "global java.util.List list\n";
        rule2 += "rule \"Rule 2\"\n";
        rule2 += "    salience 10\n";
        rule2 += "    activation-group \"activation-group-1\"\n";
        rule2 += "when\n";
        rule2 += "    $c : Cheese( ) \n";
        rule2 += "then\n";
        rule2 += "    list.add( \"rule2\" );\n";
        rule2 += "    drools.halt();\n";
        rule2 += "end";

        String rule3 = "package org.test;\n";
        rule3 += "import org.drools.Cheese\n";
        rule3 += "global java.util.List list\n";
        rule3 += "rule \"Rule 3\"\n";
        rule3 += "    activation-group \"activation-group-1\"\n";
        rule3 += "when\n";
        rule3 += "    $c : Cheese( ) \n";
        rule3 += "then\n";
        rule3 += "    list.add( \"rule3\" );\n";
        rule3 += "    drools.halt();\n";
        rule3 += "end";

        String rule4 = "package org.test;\n";
        rule4 += "import org.drools.Cheese\n";
        rule4 += "global java.util.List list\n";
        rule4 += "rule \"Rule 4\"\n";
        rule4 += "    activation-group \"activation-group-2\"\n";
        rule4 += "when\n";
        rule4 += "    $c : Cheese( ) \n";
        rule4 += "then\n";
        rule4 += "    list.add( \"rule4\" );\n";
        rule4 += "    drools.halt();\n";
        rule4 += "end";

        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new StringReader( rule1 ) );
        builder.addPackageFromDrl( new StringReader( rule2 ) );
        builder.addPackageFromDrl( new StringReader( rule3 ) );
        builder.addPackageFromDrl( new StringReader( rule4 ) );

        final Package pkg = builder.getPackage();

        RuleBase ruleBase = RuleBaseFactory.newRuleBase();
        ruleBase.addPackage( pkg );
        ruleBase = SerializationHelper.serializeObject( ruleBase );
        StatefulSession session = ruleBase.newStatefulSession();
        session = getSerialisedStatefulSession( session );

        final List list = new ArrayList();
        session.setGlobal( "list",
                           list );

        final Cheese brie = new Cheese( "brie",
                                        12 );
        session.insert( brie );

        session = getSerialisedStatefulSession( session );
        session.fireAllRules();

        session = getSerialisedStatefulSession( session );
        session.fireAllRules();

        assertEquals( 2,
                      list.size() );
        assertEquals( "rule2",
                      list.get( 0 ) );
        assertEquals( "rule4",
                      list.get( 1 ) );
    }

    public void testAgendaGroups() throws Exception {
        String rule1 = "package org.test;\n";
        rule1 += "import org.drools.Cheese\n";
        rule1 += "global java.util.List list\n";
        rule1 += "rule \"Rule 1\"\n";
        rule1 += "    agenda-group \"agenda-group-1\"\n";
        rule1 += "when\n";
        rule1 += "    $c : Cheese( ) \n";
        rule1 += "then\n";
        rule1 += "    list.add( \"rule1\" );\n";
        rule1 += "    drools.halt();\n";
        rule1 += "end";

        String rule2 = "package org.test;\n";
        rule2 += "import org.drools.Cheese\n";
        rule2 += "global java.util.List list\n";
        rule2 += "rule \"Rule 2\"\n";
        rule2 += "    salience 10\n";
        rule2 += "    agenda-group \"agenda-group-1\"\n";
        rule2 += "when\n";
        rule2 += "    $c : Cheese( ) \n";
        rule2 += "then\n";
        rule2 += "    list.add( \"rule2\" );\n";
        rule2 += "    drools.halt();\n";
        rule2 += "end";

        String rule3 = "package org.test;\n";
        rule3 += "import org.drools.Cheese\n";
        rule3 += "global java.util.List list\n";
        rule3 += "rule \"Rule 3\"\n";
        rule3 += "    salience 10\n";
        rule3 += "    agenda-group \"agenda-group-2\"\n";
        rule3 += "    activation-group \"activation-group-2\"\n";
        rule3 += "when\n";
        rule3 += "    $c : Cheese( ) \n";
        rule3 += "then\n";
        rule3 += "    list.add( \"rule3\" );\n";
        rule3 += "    drools.halt();\n";
        rule3 += "end";

        String rule4 = "package org.test;\n";
        rule4 += "import org.drools.Cheese\n";
        rule4 += "global java.util.List list\n";
        rule4 += "rule \"Rule 4\"\n";
        rule4 += "    agenda-group \"agenda-group-2\"\n";
        rule4 += "    activation-group \"activation-group-2\"\n";
        rule4 += "when\n";
        rule4 += "    $c : Cheese( ) \n";
        rule4 += "then\n";
        rule4 += "    list.add( \"rule4\" );\n";
        rule4 += "    drools.halt();\n";
        rule4 += "end";

        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new StringReader( rule1 ) );
        builder.addPackageFromDrl( new StringReader( rule2 ) );
        builder.addPackageFromDrl( new StringReader( rule3 ) );
        builder.addPackageFromDrl( new StringReader( rule4 ) );

        final Package pkg = builder.getPackage();

        RuleBase ruleBase = RuleBaseFactory.newRuleBase();
        ruleBase.addPackage( pkg );
        ruleBase = SerializationHelper.serializeObject( ruleBase );
        StatefulSession session = ruleBase.newStatefulSession();

        session = getSerialisedStatefulSession( session );

        final List list = new ArrayList();
        session.setGlobal( "list",
                           list );

        final Cheese brie = new Cheese( "brie",
                                        12 );
        session.insert( brie );

        session = getSerialisedStatefulSession( session );
        session.setFocus( "agenda-group-1" );
        session = getSerialisedStatefulSession( session );
        session.fireAllRules();
        assertEquals( "rule2",
                      list.get( 0 ) );

        session = getSerialisedStatefulSession( session );
        session.setFocus( "agenda-group-2" );
        session = getSerialisedStatefulSession( session );
        session.fireAllRules();
        assertEquals( "rule3",
                      list.get( 1 ) );

        session = getSerialisedStatefulSession( session );
        session.fireAllRules();
        assertEquals( "rule1",
                      list.get( 2 ) );
    }

    public void testRuleFlowGroups() throws Exception {
        String rule1 = "package org.test;\n";
        rule1 += "import org.drools.Cheese\n";
        rule1 += "global java.util.List list\n";
        rule1 += "rule \"Rule 1\"\n";
        rule1 += "    ruleflow-group \"ruleflow-group-1\"\n";
        rule1 += "when\n";
        rule1 += "    $c : Cheese( ) \n";
        rule1 += "then\n";
        rule1 += "    list.add( \"rule1\" );\n";
        rule1 += "    drools.halt();\n";
        rule1 += "end";

        String rule2 = "package org.test;\n";
        rule2 += "import org.drools.Cheese\n";
        rule2 += "global java.util.List list\n";
        rule2 += "rule \"Rule 2\"\n";
        rule2 += "    salience 10\n";
        rule2 += "    ruleflow-group \"ruleflow-group-1\"\n";
        rule2 += "when\n";
        rule2 += "    $c : Cheese( ) \n";
        rule2 += "then\n";
        rule2 += "    list.add( \"rule2\" );\n";
        rule2 += "    drools.halt();\n";
        rule2 += "end";

        String rule3 = "package org.test;\n";
        rule3 += "import org.drools.Cheese\n";
        rule3 += "global java.util.List list\n";
        rule3 += "rule \"Rule 3\"\n";
        rule3 += "    salience 10\n";
        rule3 += "    ruleflow-group \"ruleflow-group-2\"\n";
        rule3 += "    activation-group \"activation-group-2\"\n";
        rule3 += "when\n";
        rule3 += "    $c : Cheese( ) \n";
        rule3 += "then\n";
        rule3 += "    list.add( \"rule3\" );\n";
        rule3 += "    drools.halt();\n";
        rule3 += "end";

        String rule4 = "package org.test;\n";
        rule4 += "import org.drools.Cheese\n";
        rule4 += "global java.util.List list\n";
        rule4 += "rule \"Rule 4\"\n";
        rule4 += "    ruleflow-group \"ruleflow-group-2\"\n";
        rule4 += "    activation-group \"activation-group-2\"\n";
        rule4 += "when\n";
        rule4 += "    $c : Cheese( ) \n";
        rule4 += "then\n";
        rule4 += "    list.add( \"rule4\" );\n";
        rule4 += "    drools.halt();\n";
        rule4 += "end";

        final PackageBuilder builder = new PackageBuilder();
        builder.addPackageFromDrl( new StringReader( rule1 ) );
        builder.addPackageFromDrl( new StringReader( rule2 ) );
        builder.addPackageFromDrl( new StringReader( rule3 ) );
        builder.addPackageFromDrl( new StringReader( rule4 ) );

        final Package pkg = builder.getPackage();

        RuleBase ruleBase = RuleBaseFactory.newRuleBase();
        ruleBase.addPackage( pkg );
        ruleBase = SerializationHelper.serializeObject( ruleBase );
        StatefulSession session = ruleBase.newStatefulSession();

        session = getSerialisedStatefulSession( session );

        final List list = new ArrayList();
        session.setGlobal( "list",
                           list );

        final Cheese brie = new Cheese( "brie",
                                        12 );
        session.insert( brie );

        session = getSerialisedStatefulSession( session );
        session.getAgenda().activateRuleFlowGroup( "ruleflow-group-1" );
        session = getSerialisedStatefulSession( session );
        session.fireAllRules();
        assertEquals( "rule2",
                      list.get( 0 ) );

        session = getSerialisedStatefulSession( session );
        session.getAgenda().activateRuleFlowGroup( "ruleflow-group-2" );
        session = getSerialisedStatefulSession( session );
        session.fireAllRules();
        assertEquals( "rule3",
                      list.get( 1 ) );

        session = getSerialisedStatefulSession( session );
        session.fireAllRules();
        assertEquals( "rule1",
                      list.get( 2 ) );
    }
    
    
    protected RuleBase getRuleBase() throws Exception {

        return RuleBaseFactory.newRuleBase( RuleBase.RETEOO,
                                            null );
    }

    protected RuleBase getRuleBase(Package pkg) throws Exception {
        RuleBase ruleBase = getRuleBase();

        ruleBase.addPackage( pkg );
        return SerializationHelper.serializeObject( ruleBase );
    }

    protected RuleBase getRuleBase(final RuleBaseConfiguration config) throws Exception {

        return RuleBaseFactory.newRuleBase( RuleBase.RETEOO,
                                            config );
    }

}
