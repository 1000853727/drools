package org.drools.guvnor.client.modeldriven;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.drools.guvnor.client.modeldriven.DropDownData;
import org.drools.guvnor.client.modeldriven.SuggestionCompletionEngine;
import org.drools.guvnor.client.modeldriven.brl.ActionFieldValue;
import org.drools.guvnor.client.modeldriven.brl.FactPattern;
import org.drools.guvnor.client.modeldriven.brl.SingleFieldConstraint;
import org.drools.guvnor.server.rules.SuggestionCompletionLoader;

public class SuggestionCompletionEngineTest extends TestCase {

    public void testNestedImports() {
        String pkg = "package org.test\n import org.drools.guvnor.client.modeldriven.SuggestionCompletionEngineTest.NestedClass";

        SuggestionCompletionLoader loader = new SuggestionCompletionLoader();
        SuggestionCompletionEngine engine = loader.getSuggestionEngine( pkg, new ArrayList(), new ArrayList() );

        assertEquals( "String", engine.getFieldType( "SuggestionCompletionEngineTest$NestedClass", "name" ) );
    }

    public void testStringNonNumeric() {
        String pkg = "package org.test\n import org.drools.guvnor.client.modeldriven.Alert";

        SuggestionCompletionLoader loader = new SuggestionCompletionLoader();
        SuggestionCompletionEngine engine = loader.getSuggestionEngine( pkg, new ArrayList(), new ArrayList() );

        assertEquals( SuggestionCompletionEngine.TYPE_STRING, engine.getFieldType( "Alert", "message" ) );

    }

    public void testDataEnums() {
        String pkg = "package org.test\n import org.drools.guvnor.client.modeldriven.SuggestionCompletionEngineTest.NestedClass";

        SuggestionCompletionLoader loader = new SuggestionCompletionLoader();

        List enums = new ArrayList();

        enums.add( "'Person.age' : [42, 43] \n 'Person.sex' : ['M', 'F']");
        enums.add( "'Driver.sex' : ['M', 'F']" );


        SuggestionCompletionEngine engine = loader.getSuggestionEngine( pkg, new ArrayList(), new ArrayList() , enums);
        assertEquals( "String", engine.getFieldType( "SuggestionCompletionEngineTest$NestedClass", "name" ) );

        assertEquals(3, engine.dataEnumLists.size());
        String[] items = (String[]) engine.dataEnumLists.get( "Person.age" );
        assertEquals(2, items.length);
        assertEquals("42", items[0]);
        assertEquals("43", items[1]);

        items = engine.getEnums(new FactPattern("Person"), "age").fixedList;
        assertEquals(2, items.length);
        assertEquals("42", items[0]);
        assertEquals("43", items[1]);

        assertNull(engine.getEnums(new FactPattern("Nothing"), "age"));


        assertEquals(null, engine.getEnums(new FactPattern("Something"), "else"));

    }

    public void testCompletions() {

        final SuggestionCompletionEngine com = new SuggestionCompletionEngine();

        com.factTypes = new String[]{"Person", "Vehicle"};
        com.fieldsForType = new HashMap() {
            {
                put( "Person",
                     new String[]{"age", "name", "rank"} );
                put( "Vehicle",
                     new String[]{"type", "make"} );
            }
        };
        com.fieldTypes = new HashMap() {
            {
                put( "Person.age",
                     "Numeric" );
                put( "Person.rank",
                     "Comparable" );
                put( "Person.name",
                     "String" );
                put( "Vehicle.make",
                     "String" );
                put( "Vehicle.type",
                     "String" );
            }
        };
        com.globalTypes = new HashMap() {
            {
                put( "bar",
                     "Person" );
                put( "baz",
                     "Vehicle" );
            }
        };

        String[] c = com.getConditionalElements();
        assertEquals( "not",
                      c[0] );
        assertEquals( "exists",
                      c[1] );
        assertEquals( "or",
                      c[2] );

        c = com.getFactTypes();
        assertEquals( 2,
                      c.length );
        assertContains( "Person",
                        c );
        assertContains( "Vehicle",
                        c );

        c = com.getFieldCompletions( "Person" );
        assertEquals( "age",
                      c[0] );
        assertEquals( "name",
                      c[1] );

        c = com.getFieldCompletions( "Vehicle" );
        assertEquals( "type",
                      c[0] );
        assertEquals( "make",
                      c[1] );

        c = com.getOperatorCompletions( "Person",
                                        "name" );
        assertEquals( 4,
                      c.length );
        assertEquals( "==",
                      c[0] );
        assertEquals( "!=",
                      c[1] );
        assertEquals( "matches",
                      c[2] );

        c = com.getOperatorCompletions( "Person",
                                        "age" );
        assertEquals( 6,
                      c.length );
        assertEquals( c[0],
                      "==" );
        assertEquals( c[1],
                      "!=" );
        assertEquals( c[2],
                      "<" );
        assertEquals( c[3],
                      ">" );

        c = com.getOperatorCompletions( "Person",
                                        "rank" );
        assertEquals( 6,
                      c.length );
        assertEquals( c[0],
                      "==" );
        assertEquals( c[1],
                      "!=" );
        assertEquals( c[2],
                      "<" );
        assertEquals( c[3],
                      ">" );

        c = com.getConnectiveOperatorCompletions( "Vehicle",
                                                  "make" );
        assertEquals( 5,
                      c.length );
        assertEquals( "|| ==",
                      c[0] );

        c = com.getGlobalVariables();
        assertEquals( 2,
                      c.length );
        assertEquals( "baz",
                      c[0] );
        assertEquals( "bar",
                      c[1] );

        c = com.getFieldCompletionsForGlobalVariable( "bar" );
        assertEquals( 3,
                      c.length );
        assertEquals( "age",
                      c[0] );
        assertEquals( "name",
                      c[1] );

        c = com.getFieldCompletionsForGlobalVariable( "baz" );
        assertEquals( 2,
                      c.length );
        assertEquals( "type",
                      c[0] );
        assertEquals( "make",
                      c[1] );

        //check that it has default operators for general objects
        c = com.getOperatorCompletions( "Person",
                                        "wankle" );
        assertEquals( 2,
                      c.length );

        assertEquals("Numeric", com.getFieldType( "Person", "age" ));

    }

    public void testAdd() {
        final SuggestionCompletionEngine com = new SuggestionCompletionEngine();
        com.factTypes = new String[]{"Foo"};
        com.fieldsForType = new HashMap() {
            {
                put( "Foo",
                     new String[]{"a"} );
            }
        };

        assertEquals( 1,
                      com.getFactTypes().length );
        assertEquals( "Foo",
                      com.getFactTypes()[0] );

        assertEquals( 1,
                      com.getFieldCompletions( "Foo" ).length );
        assertEquals( "a",
                      com.getFieldCompletions( "Foo" )[0] );

    }

    public void testSmartEnums() {
    	final SuggestionCompletionEngine sce = new SuggestionCompletionEngine();
    	sce.dataEnumLists = new HashMap();
    	sce.dataEnumLists.put("Fact.type", new String[] {"sex", "colour"});
    	sce.dataEnumLists.put("Fact.value[type=sex]", new String[] {"M", "F"});
    	sce.dataEnumLists.put("Fact.value[type=colour]", new String[] {"RED", "WHITE", "BLUE"});

    	FactPattern pat = new FactPattern("Fact");
    	SingleFieldConstraint sfc = new SingleFieldConstraint("type");
    	sfc.value = "sex";
    	pat.addConstraint(sfc);
    	String[] result = sce.getEnums(pat, "value").fixedList;
    	assertEquals(2, result.length);
    	assertEquals("M", result[0]);
    	assertEquals("F", result[1]);


    	pat = new FactPattern("Fact");
    	sfc = new SingleFieldConstraint("type");
    	sfc.value = "colour";
    	pat.addConstraint(sfc);

    	result = sce.getEnums(pat, "value").fixedList;
    	assertEquals(3, result.length);
    	assertEquals("RED", result[0]);
    	assertEquals("WHITE", result[1]);
    	assertEquals("BLUE", result[2]);

    	result = sce.getEnums(pat, "type").fixedList;
    	assertEquals(2, result.length);
    	assertEquals("sex", result[0]);
    	assertEquals("colour", result[1]);


    	ActionFieldValue[] vals = new ActionFieldValue[2];
    	vals[0] = new ActionFieldValue("type", "sex", "blah");
    	vals[1] = new ActionFieldValue("value", null, "blah");
    	result = sce.getEnums("Fact", vals, "value").fixedList;
    	assertNotNull(result);
    	assertEquals(2, result.length);
    	assertEquals("M", result[0]);
    	assertEquals("F", result[1]);

    	assertNull(sce.getEnums("Nothing", vals, "value"));

    }

    public void testSmarterLookupEnums() {
    	final SuggestionCompletionEngine sce = new SuggestionCompletionEngine();
    	sce.dataEnumLists = new HashMap();
    	sce.dataEnumLists.put("Fact.type", new String[] {"sex", "colour"});
    	sce.dataEnumLists.put("Fact.value[f1, f2]", "select something from database where x=@{f1} and y=@{f2}");



    	FactPattern fp = new FactPattern("Fact");
    	String[] drops = sce.getEnums(fp, "type").fixedList;
    	assertEquals(2, drops.length);
    	assertEquals("sex", drops[0]);
    	assertEquals("colour", drops[1]);


    	Map lookupFields = sce.loadDataEnumLookupFields();
    	assertEquals(1, lookupFields.size());
    	String[] flds = (String[]) lookupFields.get("Fact.value");
    	assertEquals(2, flds.length);
    	assertEquals("f1", flds[0]);
    	assertEquals("f2", flds[1]);


    	FactPattern pat = new FactPattern("Fact");
    	SingleFieldConstraint sfc = new SingleFieldConstraint("f1");
    	sfc.value = "f1val";
    	pat.addConstraint(sfc);
    	sfc = new SingleFieldConstraint("f2");
    	sfc.value = "f2val";
    	pat.addConstraint(sfc);


    	DropDownData dd = sce.getEnums(pat, "value");
    	assertNull(dd.fixedList);
    	assertNotNull(dd.queryExpression);
    	assertNotNull(dd.valuePairs);

    	assertEquals(2, dd.valuePairs.length);
    	assertEquals("select something from database where x=@{f1} and y=@{f2}", dd.queryExpression);
    	assertEquals("f1=f1val", dd.valuePairs[0]);
    	assertEquals("f2=f2val", dd.valuePairs[1]);


    	//and now for the RHS
    	ActionFieldValue[] vals = new ActionFieldValue[2];
    	vals[0] = new ActionFieldValue("f1", "f1val", "blah");
    	vals[1] = new ActionFieldValue("f2", "f2val", "blah");
    	dd = sce.getEnums("Fact", vals, "value");
    	assertNull(dd.fixedList);
    	assertNotNull(dd.queryExpression);
    	assertNotNull(dd.valuePairs);
    	assertEquals(2, dd.valuePairs.length);
    	assertEquals("select something from database where x=@{f1} and y=@{f2}", dd.queryExpression);
    	assertEquals("f1=f1val", dd.valuePairs[0]);
    	assertEquals("f2=f2val", dd.valuePairs[1]);


    }

    public void testSimpleEnums() {
    	final SuggestionCompletionEngine sce = new SuggestionCompletionEngine();
    	sce.dataEnumLists = new HashMap();
    	sce.dataEnumLists.put("Fact.type", new String[] {"sex", "colour"});
    	assertEquals(2, sce.getEnumValues("Fact", "type").length);
    	assertEquals("sex", sce.getEnumValues("Fact", "type")[0]);
    	assertEquals("colour", sce.getEnumValues("Fact", "type")[1]);

    }

    private void assertContains(final String string,
                                final String[] c) {

        for ( int i = 0; i < c.length; i++ ) {
            if ( string.equals( c[i] ) ) {
                return;
            }
        }
        fail( "String array did not contain: " + string );

    }

    public void testGlobalAndFacts() {
        final SuggestionCompletionEngine com = new SuggestionCompletionEngine();

        com.globalTypes = new HashMap() {
            {
                put( "y",
                     "Foo" );
            }
        };
        com.fieldsForType = new HashMap() {
            {
                put( "Foo",
                     new String[]{"a"} );
            }
        };

        assertFalse( com.isGlobalVariable( "x" ) );
        assertTrue( com.isGlobalVariable( "y" ) );
    }

    public void testDataDropDown() {
    	assertNull( DropDownData.create(null) );
    	assertNull( DropDownData.create(null, null));
    	assertTrue( DropDownData.create(new String[] {"hey"}) instanceof DropDownData);
    	assertTrue( DropDownData.create("abc", new String[] {"hey"}) instanceof DropDownData);


    }

    public static class NestedClass {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
