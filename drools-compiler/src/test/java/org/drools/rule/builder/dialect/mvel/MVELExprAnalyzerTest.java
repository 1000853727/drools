package org.drools.rule.builder.dialect.mvel;

import junit.framework.TestCase;

public class MVELExprAnalyzerTest extends TestCase {

    private MVELExprAnalyzer analyzer;

    protected void setUp() throws Exception {
        analyzer = new MVELExprAnalyzer();
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testDummy() {
        // Added this so test does prooduce warning in eclipse
    }

    public void testGetExpressionIdentifiers() {
//        try {
//            String expression = "order.id == 10";
//            List[] identifiers = analyzer.analyzeExpression( expression, new Set[0] );
//            
//            assertEquals( 1, identifiers.length );
//            assertEquals( 1, identifiers[0].size() );
//            assertEquals( "order", identifiers[0].get( 0 ));
//        } catch ( RecognitionException e ) {
//            e.printStackTrace();
//            fail( "Unexpected exception: "+e.getMessage());
//        }
    }

}
