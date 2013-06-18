package org.drools.compiler.integrationtests;

import org.drools.compiler.Cheese;
import org.drools.compiler.CommonTestMethodBase;
import org.junit.Test;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.builder.conf.PhreakOption;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;
import org.kie.api.io.ResourceType;

import java.util.ArrayList;
import java.util.List;

public class NamedConsequencesTest extends CommonTestMethodBase {

    @Test
    public void testNamedConsequences() {
        List<String> results = executeTestWithCondition("do[t1]");

        assertEquals( 2, results.size() );
        assertTrue( results.contains( "cheddar" ) );
        assertTrue( results.contains( "stilton" ) );
    }

    private List<String> executeTestWithCondition(String conditionElement) {
        String drl = "import org.drools.compiler.Cheese;\n " +
                "global java.util.List results;\n" +
                "\n" +
                "rule R1 when\n" +
                "    $a: Cheese ( type == \"stilton\" )\n" +
                "    " + conditionElement + "\n" +
                "    $b: Cheese ( type == \"cheddar\" )\n" +
                "then\n" +
                "    results.add( $b.getType() );\n" +
                "then[t1]\n" +
                "    results.add( $a.getType() );\n" +
                "end\n";

        return executeTestWithDRL(drl);
    }

    private List<String> executeTestWithDRL(String drl) {
        KnowledgeBase kbase = loadKnowledgeBaseFromString(drl);
        StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();

        List<String> results = new ArrayList<String>();
        ksession.setGlobal( "results", results );

        Cheese stilton = new Cheese( "stilton", 5 );
        Cheese cheddar = new Cheese( "cheddar", 7 );
        Cheese brie = new Cheese( "brie", 5 );

        ksession.insert( stilton );
        ksession.insert( cheddar );
        ksession.insert( brie );

        ksession.fireAllRules();
        return results;
    }

    @Test
    public void testNonCompilingBreakingConsequences() {
        String str = "import org.drools.compiler.Cheese;\n " +
                "global java.util.List results;\n" +
                "\n" +
                "rule R1 when\n" +
                "    $a: Cheese ( type == \"stilton\" )\n" +
                "    break[t1]\n" +
                "    $b: Cheese ( type == \"cheddar\" )\n" +
                "then\n" +
                "    results.add( $b.getType() );\n" +
                "then[t1]\n" +
                "    results.add( $a.getType() );\n" +
                "end\n";

        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add( ResourceFactory.newByteArrayResource(str.getBytes()), ResourceType.DRL );

        assertTrue(kbuilder.hasErrors());
    }

    @Test
    public void testNonCompilingDuplicatedNamedConsequence() {
        String str = "import org.drools.compiler.Cheese;\n " +
                "global java.util.List results;\n" +
                "\n" +
                "rule R1 when\n" +
                "    $a: Cheese ( type == \"stilton\" )\n" +
                "    do[t1]\n" +
                "    $b: Cheese ( type == \"cheddar\" )\n" +
                "then\n" +
                "    results.add( $b.getType() );\n" +
                "then[t1]\n" +
                "    results.add( $a.getType() );\n" +
                "then[t1]\n" +
                "    results.add( $a.getType() );\n" +
                "end\n";

        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add( ResourceFactory.newByteArrayResource(str.getBytes()), ResourceType.DRL );

        assertTrue(kbuilder.hasErrors());
    }

    @Test
    public void testOutOfScopeNamedConsequences() {
        String str = "import org.drools.compiler.Cheese;\n " +
                "global java.util.List results;\n" +
                "\n" +
                "rule R1 when\n" +
                "    $a: Cheese ( type == \"stilton\" )\n" +
                "    do[t1]\n" +
                "    $b: Cheese ( type == \"cheddar\" )\n" +
                "then\n" +
                "    results.add( $b.getType() );\n" +
                "then[t1]\n" +
                "    results.add( $b.getType() );\n" +
                "end\n";

        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add( ResourceFactory.newByteArrayResource(str.getBytes()), ResourceType.DRL );

        assertTrue(kbuilder.hasErrors());
    }

    @Test
    public void testAllowedIfDo() {
        List<String> results = executeTestWithCondition("if ( price < 10 ) do[t1]");

        assertEquals( 2, results.size() );
        assertTrue( results.contains( "cheddar" ) );
        assertTrue( results.contains( "stilton" ) );
    }

    @Test
    public void testNotAllowedIfDo() {
        List<String> results = executeTestWithCondition("if ( price > 10 ) do[t1]");

        assertEquals( 1, results.size() );
        assertTrue( results.contains( "cheddar" ) );
    }

    @Test
    public void testAllowedIfBreak() {
        List<String> results = executeTestWithCondition("if ( price < 10 ) break[t1]");

        assertEquals( 1, results.size() );
        assertTrue( results.contains( "stilton" ) );
    }

    @Test
    public void testNotAllowedIfBreak() {
        List<String> results = executeTestWithCondition("if ( price > 10 ) break[t1]");

        assertEquals( 1, results.size() );
        assertTrue( results.contains( "cheddar" ) );
    }

    @Test
    public void testNamedConsequencesOutsideOR() {
        String str = "import org.drools.compiler.Cheese;\n " +
                "global java.util.List results;\n" +
                "\n" +
                "rule R1 when\n" +
                "    ( $a: Cheese ( type == \"stilton\" )\n" +
                "    or\n" +
                "    $a: Cheese ( type == \"gorgonzola\" ) )\n" +
                "    do[t1]\n" +
                "    $b: Cheese ( type == \"cheddar\" )\n" +
                "then\n" +
                "    results.add( $b.getType() );\n" +
                "then[t1]\n" +
                "    results.add( $a.getType() );\n" +
                "end\n";

        List<String> results = executeTestWithDRL(str);

        assertEquals( 2, results.size() );
        assertTrue( results.contains( "cheddar" ) );
        assertTrue( results.contains( "stilton" ) );
    }

    @Test
    public void testNamedConsequencesInsideOR1() {
        String str = "import org.drools.compiler.Cheese;\n " +
                "global java.util.List results;\n" +
                "\n" +
                "rule R1 when\n" +
                "    ( $a: Cheese ( type == \"stilton\" ) do[t1]\n" +
                "    or\n" +
                "    $b: Cheese ( type == \"gorgonzola\" ) )\n" +
                "    $c: Cheese ( type == \"cheddar\" )\n" +
                "then\n" +
                "    results.add( $c.getType() );\n" +
                "then[t1]\n" +
                "    results.add( $a.getType() );\n" +
                "end\n";

        List<String> results = executeTestWithDRL(str);

        assertEquals( 2, results.size() );
        assertTrue( results.contains( "cheddar" ) );
        assertTrue( results.contains( "stilton" ) );
    }

    @Test
    public void testNamedConsequencesInsideOR2() {
        String str = "import org.drools.compiler.Cheese;\n " +
                "global java.util.List results;\n" +
                "\n" +
                "rule R1 when\n" +
                "    ( $a: Cheese ( type == \"stilton\" )\n" +
                "    or\n" +
                "    $b: Cheese ( type == \"gorgonzola\" ) do[t1] )\n" +
                "    $c: Cheese ( type == \"cheddar\" )\n" +
                "then\n" +
                "    results.add( $c.getType() );\n" +
                "then[t1]\n" +
                "    results.add( $b.getType() );\n" +
                "end\n";

        List<String> results = executeTestWithDRL(str);

        assertEquals( 1, results.size() );
        assertTrue( results.contains( "cheddar" ) );
    }

    @Test
    public void testOutOfScopeNamedConsequencesWithOr1() {
        String str = "import org.drools.compiler.Cheese;\n " +
                "global java.util.List results;\n" +
                "\n" +
                "rule R1 when\n" +
                "    ( $a: Cheese ( type == \"stilton\" )\n" +
                "    or\n" +
                "    $b: Cheese ( type == \"gorgonzola\" ) do[t1] )\n" +
                "    $c: Cheese ( type == \"cheddar\" )\n" +
                "then\n" +
                "    results.add( $c.getType() );\n" +
                "then[t1]\n" +
                "    results.add( $a.getType() );\n" +
                "end\n";

        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add( ResourceFactory.newByteArrayResource(str.getBytes()), ResourceType.DRL );

        assertTrue(kbuilder.hasErrors());
    }

    @Test
    public void testOutOfScopeNamedConsequencesWithOr2() {
        String str = "import org.drools.compiler.Cheese;\n " +
                "global java.util.List results;\n" +
                "\n" +
                "rule R1 when\n" +
                "    ( $a: Cheese ( type == \"stilton\" )\n" +
                "    or\n" +
                "    $b: Cheese ( type == \"gorgonzola\" ) do[t1] )\n" +
                "    $c: Cheese ( type == \"cheddar\" )\n" +
                "then\n" +
                "    results.add( $c.getType() );\n" +
                "then[t1]\n" +
                "    results.add( $c.getType() );\n" +
                "end\n";

        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add( ResourceFactory.newByteArrayResource(str.getBytes()), ResourceType.DRL );

        assertTrue(kbuilder.hasErrors());
    }

    @Test
    public void testNonCompilingIFAfterOR() {
        String str = "import org.drools.compiler.Cheese;\n " +
                "global java.util.List results;\n" +
                "\n" +
                "rule R1 when\n" +
                "    ( $a: Cheese ( type == \"stilton\" )\n" +
                "    or\n" +
                "    $a: Cheese ( type == \"gorgonzola\" ) )\n" +
                "    if ( price > 10 ) do[t1]\n" +
                "    $b: Cheese ( type == \"cheddar\" )\n" +
                "then\n" +
                "    results.add( $b.getType() );\n" +
                "then[t1]\n" +
                "    results.add( $a.getType() );\n" +
                "end\n";

        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add( ResourceFactory.newByteArrayResource(str.getBytes()), ResourceType.DRL );

        assertTrue(kbuilder.hasErrors());
    }

    @Test
    public void testIfElse1() {
        String str = "import org.drools.compiler.Cheese;\n " +
                "global java.util.List results;\n" +
                "\n" +
                "rule R1 when\n" +
                "    $a: Cheese ( type == \"stilton\" )\n" +
                "    if ( price > 10 ) do[t1] else do[t2]\n" +
                "    $b: Cheese ( type == \"cheddar\" )\n" +
                "then\n" +
                "    results.add( $b.getType() );\n" +
                "then[t1]\n" +
                "    results.add( $a.getType() );\n" +
                "then[t2]\n" +
                "    results.add( $a.getType().toUpperCase() );\n" +
                "end\n";

        List<String> results = executeTestWithDRL(str);

        assertEquals( 2, results.size() );
        assertTrue( results.contains( "cheddar" ) );
        assertTrue( results.contains( "STILTON" ) );
    }

    @Test
    public void testIfElse2() {
        String str = "import org.drools.compiler.Cheese;\n " +
                "global java.util.List results;\n" +
                "\n" +
                "rule R1 when\n" +
                "    $a: Cheese ( type == \"stilton\" )\n" +
                "    if ( price < 10 ) do[t1] else do[t2]\n" +
                "    $b: Cheese ( type == \"cheddar\" )\n" +
                "then\n" +
                "    results.add( $b.getType() );\n" +
                "then[t2]\n" +
                "    results.add( $a.getType() );\n" +
                "then[t1]\n" +
                "    results.add( $a.getType().toUpperCase() );\n" +
                "end\n";

        List<String> results = executeTestWithDRL(str);

        assertEquals( 2, results.size() );
        assertTrue( results.contains( "cheddar" ) );
        assertTrue( results.contains( "STILTON" ) );
    }

    @Test
    public void testIfElseBreak() {
        String str = "import org.drools.compiler.Cheese;\n " +
                "global java.util.List results;\n" +
                "\n" +
                "rule R1 when\n" +
                "    $a: Cheese ( type == \"stilton\" )\n" +
                "    if ( price > 10 ) do[t1] else break[t2]\n" +
                "    $b: Cheese ( type == \"cheddar\" )\n" +
                "then\n" +
                "    results.add( $b.getType() );\n" +
                "then[t1]\n" +
                "    results.add( $a.getType() );\n" +
                "then[t2]\n" +
                "    results.add( $a.getType().toUpperCase() );\n" +
                "end\n";

        List<String> results = executeTestWithDRL(str);

        assertEquals( 1, results.size() );
        assertTrue( results.contains( "STILTON" ) );
    }

    @Test
    public void testNestedIfElseBreak() {
        String str = "import org.drools.compiler.Cheese;\n " +
                "global java.util.List results;\n" +
                "\n" +
                "rule R1 when\n" +
                "    $a: Cheese ( type == \"stilton\" )\n" +
                "    if ( type.startsWith(\"a\") ) do[t0] else if ( price > 10 ) do[t1] else break[t2]\n" +
                "    $b: Cheese ( type == \"cheddar\" )\n" +
                "then\n" +
                "    results.add( $b.getType() );\n" +
                "then[t0]\n" +
                "    results.add( \"WRONG!\" );\n" +
                "then[t1]\n" +
                "    results.add( $a.getType() );\n" +
                "then[t2]\n" +
                "    results.add( $a.getType().toUpperCase() );\n" +
                "end\n";

        List<String> results = executeTestWithDRL(str);

        assertEquals( 1, results.size() );
        assertTrue( results.contains( "STILTON" ) );
    }

    @Test
    public void testIfWithModify() {
        String str = "import org.drools.compiler.Cheese;\n " +
                "global java.util.List results;\n" +
                "\n" +
                "rule R1 when\n" +
                "    $a: Cheese ( type == \"stilton\" )\n" +
                "    if ( price > 10 ) break[t1]\n" +
                "    $b: Cheese ( type == \"cheddar\" )\n" +
                "then\n" +
                "    modify( $a ) { setPrice(15) };\n" +
                "then[t1]\n" +
                "    results.add( $a.getType() );\n" +
                "end\n";

        List<String> results = executeTestWithDRL(str);

        assertEquals( 1, results.size() );
        assertTrue( results.contains( "stilton" ) );
    }

    @Test(expected=RuntimeException.class)
    public void testEndlessIfWithModify() {
        String str = "import org.drools.compiler.Cheese;\n " +
                "global java.util.List results;\n" +
                "\n" +
                "rule R1 when\n" +
                "    $a: Cheese ( type == \"stilton\" )\n" +
                "    if ( price > 10 ) do[t1]\n" +
                "    $b: Cheese ( type == \"cheddar\" )\n" +
                "then\n" +
                "    modify( $a ) { setPrice(15) };\n" +
                "    results.add( \"modify\" );\n" +
                "    if (results.size() > 10) throw new RuntimeException();\n" +
                "then[t1]\n" +
                "    results.add( $a.getType() );\n" +
                "    if (results.size() > 10) throw new RuntimeException();\n" +
                "end\n";

        List<String> results = executeTestWithDRL(str);
    }

    @Test
    public void testIfWithModify2() {
        String str = "import org.drools.compiler.Cheese;\n " +
                "global java.util.List results;\n" +
                "\n" +
                "rule R1 when\n" +
                "    $a: Cheese ( type == \"stilton\" )\n" +
                "    if ( price < 10 ) break[t1]\n" +
                "    $b: Cheese ( type == \"cheddar\" )\n" +
                "then\n" +
                "    results.add( $a.getType() );\n" +
                "then[t1]\n" +
                "    modify( $a ) { setPrice(15) };\n" +
                "end\n";

        List<String> results = executeTestWithDRL(str);

        assertEquals( 1, results.size() );
        assertTrue( results.contains( "stilton" ) );
    }

    @Test
    public void testIfWithModify3() {
        String str = "import org.drools.compiler.Cheese;\n " +
                "global java.util.List results;\n" +
                "\n" +
                "rule R1 when\n" +
                "    $a: Cheese ( type == \"stilton\", price < 10 )\n" +
                "    $b: Cheese ( type == \"cheddar\" )\n" +
                "then\n" +
                "    modify( $a ) { setPrice(15) };\n" +
                "end\n" +
                "rule R2 when\n" +
                "    $a: Cheese ( type == \"stilton\" )\n" +
                "    if ( price > 10 ) break[t1]\n" +
                "    $b: Cheese ( type == \"cheddar\" )\n" +
                "then\n" +
                "then[t1]\n" +
                "    results.add( $a.getType() );\n" +
                "end\n";

        List<String> results = executeTestWithDRL(str);

        assertEquals( 1, results.size() );
        assertTrue( results.contains( "stilton" ) );
    }

    @Test
    public void testIfElseWithModify() {
        String str = "import org.drools.compiler.Cheese;\n " +
                "global java.util.List results;\n" +
                "\n" +
                "rule R1 when\n" +
                "    $a: Cheese ( type == \"stilton\" )\n" +
                "    if ( price < 10 ) do[t1] else break[t2]\n" +
                "    $b: Cheese ( type == \"cheddar\" )\n" +
                "then\n" +
                "    modify( $a ) { setPrice(15) };\n" +
                "then[t1]\n" +
                "    results.add( $a.getType() );\n" +
                "then[t2]\n" +
                "    results.add( $a.getType().toUpperCase() );\n" +
                "end\n";

//        Cheese stilton = new Cheese( "stilton", 5 );
//        Cheese cheddar = new Cheese( "cheddar", 7 );
//        Cheese brie = new Cheese( "brie", 5 );

        List<String> results = executeTestWithDRL(str);

        if ( CommonTestMethodBase.phreak == PhreakOption.ENABLED) {
            assertEquals( 2, results.size() );
        } else {
            assertEquals( 1, results.size() );
        }

        assertTrue( results.contains( "STILTON" ) );
    }

    @Test(expected=RuntimeException.class)
    public void testEndlessIfElseWithModify() {
        String str = "import org.drools.compiler.Cheese;\n " +
                "global java.util.List results;\n" +
                "\n" +
                "rule R1 when\n" +
                "    $a: Cheese ( type == \"stilton\" )\n" +
                "    if ( price < 10 ) do[t1] else do[t2]\n" +
                "    $b: Cheese ( type == \"cheddar\" )\n" +
                "then\n" +
                "    modify( $a ) { setPrice(15) };\n" +
                "    results.add( \"modify\" );\n" +
                "    if (results.size() > 10) throw new RuntimeException();\n" +
                "then[t1]\n" +
                "    results.add( $a.getType() );\n" +
                "then[t2]\n" +
                "    results.add( $a.getType().toUpperCase() );\n" +
                "    if (results.size() > 10) throw new RuntimeException();\n" +
                "end\n";

        List<String> results = executeTestWithDRL(str);
    }

    @Test
    public void testNamedConsequenceAfterNotPattern() {
        // DROOLS-5
        String str = "import org.drools.compiler.Cheese;\n " +
                "global java.util.List results;\n" +
                "\n" +
                "rule R1 when\n" +
                "    $a: Cheese ( type == \"stilton\" )\n" +
                "    not Cheese ( type == \"brie\" )\n" +
                "    do[t1]\n" +
                "    $b: Cheese ( type == \"cheddar\" )\n" +
                "then\n" +
                "    results.add( $b.getType() );\n" +
                "then[t1]\n" +
                "    results.add( $a.getType() );\n" +
                "end\n";

        KnowledgeBase kbase = loadKnowledgeBaseFromString(str);
        StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();

        List<String> results = new ArrayList<String>();
        ksession.setGlobal( "results", results );

        ksession.insert( new Cheese( "stilton", 5 ) );
        ksession.insert( new Cheese("cheddar", 7 ) );

        ksession.fireAllRules();

        assertTrue(results.contains("stilton"));
        assertTrue(results.contains("cheddar"));
    }

    @Test
    public void testMultipleIfElseInARow() {
        // DROOLS-26
        String str =
                "global java.util.List results;" +
                        "declare UnBlocker end \n" +
                        "\n" +
                        "declare Car\n" +
                        "  colour\t: String \n" +
                        "  price \t: int\n" +
                        "  horsepower \t: int\n" +
                        "  abs \t\t: boolean\n" +
                        "end\n" +
                        "\n" +
                        "rule \"Init\" \n" +
                        "when \n" +
                        "then \n" +
                        "  insert( \n" +
                        "\tnew Car( \"red\", 1200, 170, true ) \n" +
                        "  ); \n" +
                        "end\n" +
                        "\n" +
                        "rule \"Car\" \n" +
                        "when \n" +
                        "  $car: Car( abs == true ) \n" +
                        "  if ( colour == \"red\" ) do[red] " +
                        "  else if ( colour != \"red\" ) do[notRed]\n" +
                        "  if ( price < 1000 ) do[cheap] " +
                        "  else do[notCheap]\n" +
                        " UnBlocker() \n" +
                        "then\n" +
                        "  results.add( \"Found a Car\" ); \n" +
                        "then[red]\n" +
                        "  results.add( \"Car is red\" ); " +
                        "  insert( new UnBlocker() ); \n" +
                        "then[notRed]\n" +
                        "  results.add( \"Car is NOT red\" ); \n" +
                        "then[cheap]\n" +
                        "  results.add( \"Car is cheap\" ); \n" +
                        "then[notCheap]\n" +
                        "  results.add( \"Car is NOT cheap\" ); \n" +
                        "end";

        KnowledgeBase kbase = loadKnowledgeBaseFromString(str);
        StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();

        List<String> results = new ArrayList<String>();
        ksession.setGlobal("results", results);
        ksession.fireAllRules();

        assertEquals(3, results.size());
        assertTrue(results.contains("Found a Car"));
        assertTrue(results.contains("Car is red"));
        assertTrue(results.contains("Car is NOT cheap"));
    }
}
