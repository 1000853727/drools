package org.drools.clips;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import junit.framework.TestCase;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.drools.lang.descr.AndDescr;
import org.drools.lang.descr.AttributeDescr;
import org.drools.lang.descr.EvalDescr;
import org.drools.lang.descr.ExistsDescr;
import org.drools.lang.descr.FieldBindingDescr;
import org.drools.lang.descr.FieldConstraintDescr;
import org.drools.lang.descr.LiteralRestrictionDescr;
import org.drools.lang.descr.NotDescr;
import org.drools.lang.descr.OrDescr;
import org.drools.lang.descr.PatternDescr;
import org.drools.lang.descr.PredicateDescr;
import org.drools.lang.descr.RestrictionConnectiveDescr;
import org.drools.lang.descr.ReturnValueRestrictionDescr;
import org.drools.lang.descr.RuleDescr;
import org.drools.reteoo.builder.BuildContext;

public class LhsClpParserTest extends TestCase {

    private ClipsParser parser;
    
    //XFunctionRegistry registry;
    
    public void setUp() {
        //this.registry = new XFunctionRegistry( BuiltinFunctions.getInstance() );
    }
    
    protected void tearDown() throws Exception {
        super.tearDown();
        //this.parser = null;
    }

    public void testParseFunction() throws Exception {        
//        BuildContext context = new ExecutionBuildContext( new CLPPredicate(), this.registry );
//        FunctionCaller fc = ( FunctionCaller ) parse( "(< 1 2)" ).lisp_list( context, new LispForm2(context) );
//        
//        assertEquals( "<", fc.getName() );        
//        assertEquals( new LongValueHandler( 1 ), fc.getParameters()[0] );
//        assertEquals( new LongValueHandler( 2 ), fc.getParameters()[1] );
    }
    
    public void testPatternsRule() throws Exception {
        // the first pattern bellowshould generate a descriptor tree like that:
        //            Pattern[person]
        //                 |
        //                AND
        //               /   \
        //              FB    FC[person name]
        //                     |
        //                     OR
        //          +----------|------------+
        //         LR         LR          AND
        //                                /   \
        //                              RVR   PC
        
        // MARK: is it valid to add a predicate restriction as part of a field constraint? I mean, shouldn't
        // the predicate be out of the (name ...) scope? 
        RuleDescr rule = parse( "(defrule xxx ?b <- (person (name \"yyy\"&?bf|~\"zzz\"|~=(+ 2 3)&:(< 1 2)) ) ?c <- (hobby (type ?bf2&~iii) (rating fivestar) ) => )" ).defrule();

        assertEquals( "xxx",
                      rule.getName() );

        AndDescr lhs = rule.getLhs();
        List lhsList = lhs.getDescrs();
        assertEquals( 2,
                      lhsList.size() );

        // Parse the first pattern
        PatternDescr personPattern = (PatternDescr) lhsList.get( 0 );
        assertEquals( "$b",
                      personPattern.getIdentifier() );
        assertEquals( "person",
                      personPattern.getObjectType() );

        List colList = personPattern.getDescrs();
        assertEquals( 3,
                      colList.size() );
        
        // first, we have a field binding
        FieldBindingDescr fbd = (FieldBindingDescr) colList.get( 0 );
        assertEquals( "$bf",
                      fbd.getIdentifier() );
        assertEquals( "name",
                      fbd.getFieldName() );
        
        // then, we have a field constraint
        FieldConstraintDescr fieldConstraintDescr = (FieldConstraintDescr) colList.get( 1 );
        assertEquals( "name",
                      fieldConstraintDescr.getFieldName() );                        
        RestrictionConnectiveDescr root = (RestrictionConnectiveDescr) fieldConstraintDescr.getRestriction();
        assertEquals( 1,
                      root.getRestrictions().size() );
        RestrictionConnectiveDescr or = (RestrictionConnectiveDescr) root.getRestrictions().get( 0 );
        assertEquals( RestrictionConnectiveDescr.OR, 
                      or.getConnective() );
        
        List restrictionList = or.getRestrictions();

        assertEquals( 3,
                      restrictionList.size() );

        // first we have a literal restriction
        LiteralRestrictionDescr litDescr = (LiteralRestrictionDescr) restrictionList.get( 0 );
        assertEquals( "==",
                      litDescr.getEvaluator() );
        assertEquals( "yyy",
                      litDescr.getText() );

        // second, we have another literal restriction
        litDescr = (LiteralRestrictionDescr) restrictionList.get( 1 );
        assertEquals( "!=",
                      litDescr.getEvaluator() );
        assertEquals( "zzz",
                      litDescr.getText() );
        
        ReturnValueRestrictionDescr retDescr = (ReturnValueRestrictionDescr) restrictionList.get( 2 );
        assertEquals( "!=",
                      retDescr.getEvaluator() );
        
        LispForm lispForm = ( LispForm ) retDescr.getContent();
        assertEquals("(+ 2 3)", lispForm.toString() );                         

        // ----------------
        // this is how it would be compatible to our core engine 
        PredicateDescr predicateDescr = (PredicateDescr) colList.get( 2 );
        
        lispForm = ( LispForm ) predicateDescr.getContent();
        assertEquals("(< 1 2)", lispForm.toString() );                

        // -----------------
        // Parse the second column
        PatternDescr hobbyPattern = (PatternDescr) lhsList.get( 1 );
        assertEquals( "$c",
                      hobbyPattern.getIdentifier() );
        assertEquals( "hobby",
                      hobbyPattern.getObjectType() );

        colList = hobbyPattern.getDescrs();
        assertEquals( 3,
                      colList.size() );

        fbd = (FieldBindingDescr) colList.get( 0 );
        assertEquals( "$bf2",
                      fbd.getIdentifier() );
        assertEquals( "type",
                      fbd.getFieldName() );
        
        fieldConstraintDescr = (FieldConstraintDescr) colList.get( 1 );
        restrictionList = fieldConstraintDescr.getRestrictions();

        assertEquals( "type",
                      fieldConstraintDescr.getFieldName() );

        assertEquals( RestrictionConnectiveDescr.AND,
                      fieldConstraintDescr.getRestriction().getConnective() );

        litDescr = (LiteralRestrictionDescr) restrictionList.get( 0 );
        assertEquals( "!=",
                      litDescr.getEvaluator() );
        assertEquals( "iii",
                      litDescr.getText() );

        fieldConstraintDescr = (FieldConstraintDescr) colList.get( 2 );
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
        assertEquals( "$b",
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
        LispForm lispForm = ( LispForm ) evalDescr.getContent();
        assertEquals("(< 9.0 1.3)", lispForm.toString() );                          
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
    
    private ClipsParser parse(final String text) throws Exception {
        return new ClipsParser( new CommonTokenStream( new ClipsLexer( new ANTLRStringStream( text ) ) ) );
    }

    private ClipsParser parse(final String source,
                            final String text) throws Exception {
        this.parser =  new ClipsParser( new CommonTokenStream( new ClipsLexer( new ANTLRStringStream( text ) ) ) );
        this.parser.setSource( source );
        return this.parser;
    }

    private Reader getReader(final String name) throws Exception {
        final InputStream in = getClass().getResourceAsStream( name );

        return new InputStreamReader( in );
    }

    private ClipsParser parseResource(final String name) throws Exception {
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
