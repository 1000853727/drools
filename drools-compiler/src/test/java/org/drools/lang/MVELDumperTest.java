package org.drools.lang;

import junit.framework.TestCase;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.TokenStream;
import org.drools.base.evaluators.MatchesEvaluatorsDefinition;
import org.drools.base.evaluators.SetEvaluatorsDefinition;
import org.drools.lang.descr.FieldConstraintDescr;
import org.drools.lang.descr.PatternDescr;

public class MVELDumperTest extends TestCase {

    private MVELDumper dumper;
    
    protected void setUp() throws Exception {
        super.setUp();
        
        // configure operators
        new SetEvaluatorsDefinition();
        new MatchesEvaluatorsDefinition();
        
        dumper = new MVELDumper();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testDump() throws Exception {
        String input = "Cheese( price > 10 && < 20 || == $val || == 30 )";
        String expected = "( ( price > 10 && price < 20 ) || price == $val || price == 30 )" ;
        DRLParser parser = parse( input );
        PatternDescr pattern = (PatternDescr) parser.fact( null );
        
        FieldConstraintDescr fieldDescr = (FieldConstraintDescr) pattern.getConstraint().getDescrs().get( 0 );
        String result = dumper.dump( fieldDescr );
        
        assertEquals( expected, result );
    }
    
    public void testDumpMatches() throws Exception {
        String input = "Cheese( type.toString matches \"something\\swith\\tsingle escapes\" )";
        String expected = "type.toString ~= \"something\\\\swith\\\\tsingle escapes\"" ;
        DRLParser parser = parse( input );
        PatternDescr pattern = (PatternDescr) parser.fact( null );
        
        FieldConstraintDescr fieldDescr = (FieldConstraintDescr) pattern.getConstraint().getDescrs().get( 0 );
        String result = dumper.dump( fieldDescr );
        
        assertEquals( expected, result );
    }

    public void testDumpMatches2() throws Exception {
        String input = "Cheese( type.toString matches 'something\\swith\\tsingle escapes' )";
        String expected = "type.toString ~= \"something\\\\swith\\\\tsingle escapes\"" ;
        DRLParser parser = parse( input );
        PatternDescr pattern = (PatternDescr) parser.fact( null );
        
        FieldConstraintDescr fieldDescr = (FieldConstraintDescr) pattern.getConstraint().getDescrs().get( 0 );
        String result = dumper.dump( fieldDescr );
        
        assertEquals( expected, result );
    }

    public void testDumpMatches3() throws Exception {
        String input = "Map( this[\"content\"] matches \"hello ;=\" )";
        String expected = "this[\"content\"] ~= \"hello ;=\"" ;
        DRLParser parser = parse( input );
        PatternDescr pattern = (PatternDescr) parser.fact( null );
        
        FieldConstraintDescr fieldDescr = (FieldConstraintDescr) pattern.getConstraint().getDescrs().get( 0 );
        String result = dumper.dump( fieldDescr );
        
        assertEquals( expected, result );
    }

    public void testDumpWithDateAttr() throws Exception {
        String input = "Person( son.birthDate == \"01-jan-2000\" )";
        String expected = "son.birthDate == org.drools.util.DateUtils.parseDate( \"01-jan-2000\" )" ;
        DRLParser parser = parse( input );
        PatternDescr pattern = (PatternDescr) parser.fact( null );
        
        FieldConstraintDescr fieldDescr = (FieldConstraintDescr) pattern.getConstraint().getDescrs().get( 0 );
        String result = dumper.dump( fieldDescr, true );
        
        assertEquals( expected, result );
    }

    private DRLParser parse(final String text) throws Exception {
        return newParser( newTokenStream( newLexer( newCharStream( text ) ) ) );
    }

    private CharStream newCharStream(final String text) {
        return new ANTLRStringStream( text );
    }

    private DRLLexer newLexer(final CharStream charStream) {
        return new DRLLexer( charStream );
    }

    private TokenStream newTokenStream(final Lexer lexer) {
        return new CommonTokenStream( lexer );
    }

    private DRLParser newParser(final TokenStream tokenStream) {
        final DRLParser p = new DRLParser( tokenStream );
        //p.setParserDebug( true );
        return p;
    }
    
}
