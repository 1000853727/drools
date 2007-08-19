package org.drools.clp;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import junit.framework.TestCase;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.TokenStream;
import org.drools.clp.valuehandlers.DoubleValueHandler;
import org.drools.clp.valuehandlers.FunctionCaller;
import org.drools.clp.valuehandlers.LongValueHandler;
import org.drools.lang.descr.AndDescr;
import org.drools.lang.descr.AttributeDescr;
import org.drools.lang.descr.EvalDescr;
import org.drools.lang.descr.ExistsDescr;
import org.drools.lang.descr.FieldConstraintDescr;
import org.drools.lang.descr.LiteralRestrictionDescr;
import org.drools.lang.descr.NotDescr;
import org.drools.lang.descr.OrDescr;
import org.drools.lang.descr.PatternDescr;
import org.drools.lang.descr.PredicateDescr;
import org.drools.lang.descr.RestrictionConnectiveDescr;
import org.drools.lang.descr.ReturnValueRestrictionDescr;
import org.drools.lang.descr.RuleDescr;
import org.drools.lang.descr.VariableRestrictionDescr;

public class LhsClpParserTest extends TestCase {

    private CLPParser parser;
    
    FunctionRegistry registry;
    
    public void setUp() {
        this.registry = new FunctionRegistry( BuiltinFunctions.getInstance() );
    }
    
    protected void tearDown() throws Exception {
        super.tearDown();
        this.parser = null;
    }

    public void testParseFunction() throws Exception {        
        BuildContext context = new ExecutionBuildContext( new CLPPredicate(), this.registry );
        FunctionCaller fc = ( FunctionCaller ) parse( "(< 1 2)" ).lisp_list( context, new LispForm(context) );
        
        assertEquals( "<", fc.getName() );        
        assertEquals( new LongValueHandler( 1 ), fc.getParameters()[0] );
        assertEquals( new LongValueHandler( 2 ), fc.getParameters()[1] );
    }
    
    public void testPatternsRule() throws Exception {
        // the first pattern bellowshould generate a descriptor tree like that:
        //
        //                     FC[person name]
        //                     |
        //                     OR
        //          +----------|------------+
        //         AND         LR          RVR
        //        /   \
        //       LR   VR
        
        // MARK: is it valid to add a predicate restriction as part of a field constraint? I mean, shouldn't
        // the predicate be out of the (name ...) scope? 
        RuleDescr rule = parse( "(defrule xxx ?b <- (person (name \"yyy\"&?bf|~\"zzz\"|~=(+ 2 3)&:(< 1 2)) ) ?c <- (hobby (type ?bf2&~iii) (rating fivestar) ) => )" ).defrule();

        assertEquals( "xxx",
                      rule.getName() );

        AndDescr lhs = rule.getLhs();
        List lhsList = lhs.getDescrs();
        assertEquals( 2,
                      lhsList.size() );

        // Parse the first column
        PatternDescr col = (PatternDescr) lhsList.get( 0 );
        assertEquals( "?b",
                      col.getIdentifier() );
        assertEquals( "person",
                      col.getObjectType() );

        List colList = col.getDescrs();
        assertEquals( 2,
                      colList.size() );
        FieldConstraintDescr fieldConstraintDescr = (FieldConstraintDescr) colList.get( 0 );
        assertEquals( "name",
                      fieldConstraintDescr.getFieldName() );
        // @todo the 7th one has no constraint, as its a predicate, have to figure out how to handle this
        assertEquals( RestrictionConnectiveDescr.OR, 
                      fieldConstraintDescr.getRestriction().getConnective() );
        
        List restrictionList = fieldConstraintDescr.getRestrictions();

        assertEquals( 3,
                      restrictionList.size() );

        RestrictionConnectiveDescr andRestr = (RestrictionConnectiveDescr) restrictionList.get( 0 );
        assertEquals( RestrictionConnectiveDescr.AND,
                      andRestr.getConnective() );
        assertEquals( 2, 
                      andRestr.getRestrictions().size() );
        
        LiteralRestrictionDescr litDescr = (LiteralRestrictionDescr) andRestr.getRestrictions().get( 0 );
        assertEquals( "==",
                      litDescr.getEvaluator() );
        assertEquals( "yyy",
                      litDescr.getText() );

        VariableRestrictionDescr varDescr = (VariableRestrictionDescr) restrictionList.get( 1 );
        assertEquals( "==",
                      varDescr.getEvaluator() );
        assertEquals( "?bf",
                      varDescr.getIdentifier() );

        litDescr = (LiteralRestrictionDescr) restrictionList.get( 1 );
        assertEquals( "!=",
                      litDescr.getEvaluator() );
        assertEquals( "zzz",
                      litDescr.getText() );

        ReturnValueRestrictionDescr retDescr = (ReturnValueRestrictionDescr) restrictionList.get( 2 );
        assertEquals( "!=",
                      retDescr.getEvaluator() );
        CLPReturnValue clprv = ( CLPReturnValue ) retDescr.getContent();
        FunctionCaller fc = clprv.getFunctions()[0];
        assertEquals( "+", fc.getName() );        
        assertEquals( new LongValueHandler( 2 ), fc.getParameters()[0] );
        assertEquals( new LongValueHandler( 3 ), fc.getParameters()[1] );       

        // ----------------
        // this is how it would be compatible to our core engine
        PredicateDescr predicateDescr = (PredicateDescr) colList.get( 1 );        
        CLPPredicate clpp = ( CLPPredicate ) predicateDescr.getContent();
        fc = clpp.getFunctions()[0];
        assertEquals( "<", fc.getName() );        
        assertEquals( new LongValueHandler( 1 ), fc.getParameters()[0] );
        assertEquals( new LongValueHandler( 2 ), fc.getParameters()[1] );        

        // -----------------
        // Parse the second column
        col = (PatternDescr) lhsList.get( 1 );
        assertEquals( "?c",
                      col.getIdentifier() );
        assertEquals( "hobby",
                      col.getObjectType() );

        colList = col.getDescrs();
        assertEquals( 2,
                      colList.size() );
        fieldConstraintDescr = (FieldConstraintDescr) colList.get( 0 );
        restrictionList = fieldConstraintDescr.getRestrictions();

        assertEquals( "type",
                      fieldConstraintDescr.getFieldName() );

        assertEquals( RestrictionConnectiveDescr.AND,
                      fieldConstraintDescr.getRestriction().getConnective() );

        varDescr = (VariableRestrictionDescr) restrictionList.get( 0 );
        assertEquals( "==",
                      varDescr.getEvaluator() );
        assertEquals( "?bf2",
                      varDescr.getIdentifier() );

        litDescr = (LiteralRestrictionDescr) restrictionList.get( 1 );
        assertEquals( "!=",
                      litDescr.getEvaluator() );
        assertEquals( "iii",
                      litDescr.getText() );

        fieldConstraintDescr = (FieldConstraintDescr) colList.get( 1 );
        restrictionList = fieldConstraintDescr.getRestrictions();

        assertEquals( "rating",
                      fieldConstraintDescr.getFieldName() );

        litDescr = (LiteralRestrictionDescr) restrictionList.get( 0 );
        assertEquals( "==",
                      litDescr.getEvaluator() );
        assertEquals( "fivestar",
                      litDescr.getText() );
    }

    public void testNestedCERule() throws Exception {
        RuleDescr rule = parse( "(defrule xxx ?b <- (person (name yyy)) (or (and (hobby1 (type qqq1)) (hobby2 (type ~qqq2))) (food (veg ~shroom) ) ) => )" ).defrule();

        assertEquals( "xxx",
                      rule.getName() );

        AndDescr lhs = rule.getLhs();
        List lhsList = lhs.getDescrs();
        assertEquals( 2,
                      lhsList.size() );

        // Parse the first column
        PatternDescr col = (PatternDescr) lhsList.get( 0 );
        assertEquals( "?b",
                      col.getIdentifier() );
        assertEquals( "person",
                      col.getObjectType() );
        FieldConstraintDescr fieldConstraintDescr = (FieldConstraintDescr) col.getDescrs().get( 0 );
        assertEquals( "name",
                      fieldConstraintDescr.getFieldName() ); //         
        LiteralRestrictionDescr litDescr = (LiteralRestrictionDescr) fieldConstraintDescr.getRestrictions().get( 0 );
        assertEquals( "==",
                      litDescr.getEvaluator() );
        assertEquals( "yyy",
                      litDescr.getText() );

        OrDescr orDescr = (OrDescr) lhsList.get( 1 );
        assertEquals( 2,
                      orDescr.getDescrs().size() );

        AndDescr andDescr = (AndDescr) orDescr.getDescrs().get( 0 );
        col = (PatternDescr) andDescr.getDescrs().get( 0 );
        assertEquals( "hobby1",
                      col.getObjectType() );
        fieldConstraintDescr = (FieldConstraintDescr) col.getDescrs().get( 0 );
        assertEquals( "type",
                      fieldConstraintDescr.getFieldName() ); //         
        litDescr = (LiteralRestrictionDescr) fieldConstraintDescr.getRestrictions().get( 0 );
        assertEquals( "==",
                      litDescr.getEvaluator() );
        assertEquals( "qqq1",
                      litDescr.getText() );

        col = (PatternDescr) andDescr.getDescrs().get( 1 );
        assertEquals( "hobby2",
                      col.getObjectType() );
        fieldConstraintDescr = (FieldConstraintDescr) col.getDescrs().get( 0 );
        assertEquals( "type",
                      fieldConstraintDescr.getFieldName() ); //         
        litDescr = (LiteralRestrictionDescr) fieldConstraintDescr.getRestrictions().get( 0 );
        assertEquals( "!=",
                      litDescr.getEvaluator() );
        assertEquals( "qqq2",
                      litDescr.getText() );

        col = (PatternDescr) orDescr.getDescrs().get( 1 );
        assertEquals( "food",
                      col.getObjectType() );
        fieldConstraintDescr = (FieldConstraintDescr) col.getDescrs().get( 0 );
        assertEquals( "veg",
                      fieldConstraintDescr.getFieldName() ); //         
        litDescr = (LiteralRestrictionDescr) fieldConstraintDescr.getRestrictions().get( 0 );
        assertEquals( "!=",
                      litDescr.getEvaluator() );
        assertEquals( "shroom",
                      litDescr.getText() );
    }

    public void testNotExistsRule() throws Exception {
        RuleDescr rule = parse( "(defrule xxx (or (hobby1 (type qqq1)) (not (and (exists (person (name ppp))) (person (name yyy))))) => )" ).defrule();

        assertEquals( "xxx",
                      rule.getName() );

        AndDescr lhs = rule.getLhs();
        List lhsList = lhs.getDescrs();
        assertEquals( 1,
                      lhsList.size() );

        OrDescr orDescr = (OrDescr) lhsList.get( 0 );
        assertEquals( 2,
                      orDescr.getDescrs().size() );

        PatternDescr col = (PatternDescr) orDescr.getDescrs().get( 0 );
        assertEquals( "hobby1",
                      col.getObjectType() );
        FieldConstraintDescr fieldConstraintDescr = (FieldConstraintDescr) col.getDescrs().get( 0 );
        assertEquals( "type",
                      fieldConstraintDescr.getFieldName() ); //         
        LiteralRestrictionDescr litDescr = (LiteralRestrictionDescr) fieldConstraintDescr.getRestrictions().get( 0 );
        assertEquals( "==",
                      litDescr.getEvaluator() );
        assertEquals( "qqq1",
                      litDescr.getText() );

        NotDescr notDescr = (NotDescr) orDescr.getDescrs().get( 1 );
        assertEquals( 1,
                      notDescr.getDescrs().size() );
        
        AndDescr andDescr = (AndDescr) notDescr.getDescrs().get( 0 );
        assertEquals( 2, andDescr.getDescrs().size() );
        ExistsDescr existsDescr = (ExistsDescr) andDescr.getDescrs().get( 0 );
        col = (PatternDescr) existsDescr.getDescrs().get( 0 );
        assertEquals( "person",
                      col.getObjectType() );
        fieldConstraintDescr = (FieldConstraintDescr) col.getDescrs().get( 0 );
        assertEquals( "name",
                      fieldConstraintDescr.getFieldName() ); //         
        litDescr = (LiteralRestrictionDescr) fieldConstraintDescr.getRestrictions().get( 0 );
        assertEquals( "==",
                      litDescr.getEvaluator() );
        assertEquals( "ppp",
                      litDescr.getText() );              
        
        col = (PatternDescr) andDescr.getDescrs().get( 1 );
        assertEquals( "person",
                      col.getObjectType() );
        fieldConstraintDescr = (FieldConstraintDescr) col.getDescrs().get( 0 );
        assertEquals( "name",
                      fieldConstraintDescr.getFieldName() ); //         
        litDescr = (LiteralRestrictionDescr) fieldConstraintDescr.getRestrictions().get( 0 );
        assertEquals( "==",
                      litDescr.getEvaluator() );
        assertEquals( "yyy",
                      litDescr.getText() );  
    }
    
    public void testTestRule() throws Exception {
        RuleDescr rule = parse( "(defrule xxx (test (< 9.0 1.3) ) => )" ).defrule();

        assertEquals( "xxx",
                      rule.getName() );

        AndDescr lhs = rule.getLhs();
        List lhsList = lhs.getDescrs();
        assertEquals( 1,
                      lhsList.size() );

        EvalDescr evalDescr = (EvalDescr) lhsList.get( 0 );
        
        CLPEval clpe = ( CLPEval ) evalDescr.getContent();
        FunctionCaller f = clpe.getFunctions()[0];
        assertEquals( "<", f.getName() );        
        assertEquals( new DoubleValueHandler( 9.0 ), f.getParameters()[0] );
        assertEquals( new DoubleValueHandler( 1.3 ), f.getParameters()[1] );          
    }

    public void testRuleHeader() throws Exception {
        RuleDescr rule = parse( "(defrule MAIN::name \"docs\"(declare (salience -100) ) => )" ).defrule();
        
        List attributes = rule.getAttributes();
        AttributeDescr module = ( AttributeDescr ) attributes.get( 0 );
        assertEquals( "agenda-group", module.getName() );
        assertEquals( "MAIN", module.getValue() );
        
        assertEquals("name", rule.getName() );
        
        AttributeDescr dialect = ( AttributeDescr ) attributes.get( 1 );
        assertEquals( "dialect", dialect.getName() );
        assertEquals( "clips", dialect.getValue() );        
        
        AttributeDescr salience = ( AttributeDescr ) attributes.get( 2 );
        assertEquals( "salience", salience.getName() );
        assertEquals( "-100", salience.getValue() );
        
        
    }
    
    private CLPParser parse(final String text) throws Exception {
        this.parser = newParser( newTokenStream( newLexer( newCharStream( text ) ) ) );
        return this.parser;
    }

    private CLPParser parse(final String source,
                            final String text) throws Exception {
        this.parser = newParser( newTokenStream( newLexer( newCharStream( text ) ) ) );
        this.parser.setSource( source );
        return this.parser;
    }

    private Reader getReader(final String name) throws Exception {
        final InputStream in = getClass().getResourceAsStream( name );

        return new InputStreamReader( in );
    }

    private CLPParser parseResource(final String name) throws Exception {

        //        System.err.println( getClass().getResource( name ) );
        Reader reader = getReader( name );

        final StringBuffer text = new StringBuffer();

        final char[] buf = new char[1024];
        int len = 0;

        while ( (len = reader.read( buf )) >= 0 ) {
            text.append( buf,
                         0,
                         len );
        }

        return parse( name,
                      text.toString() );
    }

    private CharStream newCharStream(final String text) {
        return new ANTLRStringStream( text );
    }

    private CLPLexer newLexer(final CharStream charStream) {
        return new CLPLexer( charStream );
    }

    private TokenStream newTokenStream(final Lexer lexer) {
        return new CommonTokenStream( lexer );
    }

    private CLPParser newParser(final TokenStream tokenStream) {
        final CLPParser p = new CLPParser( tokenStream );
        p.setFunctionRegistry( new FunctionRegistry( BuiltinFunctions.getInstance() )  );
        //p.setParserDebug( true );
        return p;
    }

    private void assertEqualsIgnoreWhitespace(final String expected,
                                              final String actual) {
        final String cleanExpected = expected.replaceAll( "\\s+",
                                                          "" );
        final String cleanActual = actual.replaceAll( "\\s+",
                                                      "" );

        assertEquals( cleanExpected,
                      cleanActual );
    }

}
