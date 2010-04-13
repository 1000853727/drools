package org.drools.compiler;

import org.drools.Cheese;
import org.drools.DroolsTestCase;
import org.drools.Person;
import org.drools.QueryResults;
import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
import org.drools.StatefulSession;
import org.drools.lang.descr.AndDescr;
import org.drools.lang.descr.FieldBindingDescr;
import org.drools.lang.descr.FieldConstraintDescr;
import org.drools.lang.descr.LiteralDescr;
import org.drools.lang.descr.PackageDescr;
import org.drools.lang.descr.PatternDescr;
import org.drools.lang.descr.QueryDescr;
import org.drools.lang.descr.RuleDescr;
import org.drools.lang.descr.VariableDescr;
import org.drools.lang.descr.VariableRestrictionDescr;

public class QueryBuilderTest extends DroolsTestCase {
    public void testRuleWithQuery() throws Exception {
        final PackageBuilder builder = new PackageBuilder();

        final PackageDescr packageDescr = new PackageDescr( "p1" );

        QueryDescr queryDescr = new QueryDescr( "query1" );
        queryDescr.setParameters( new String[]{"$name", "$age", "$likes"} );
        queryDescr.setParameterTypes( new String[]{"String", "int", "String"} );
        packageDescr.addRule( queryDescr );
        AndDescr lhs = new AndDescr();
        queryDescr.setLhs( lhs );
        PatternDescr pattern = new PatternDescr( Person.class.getName() );
        lhs.addDescr( pattern );
        FieldConstraintDescr literalDescr = new FieldConstraintDescr( "name" );
        literalDescr.addRestriction( new VariableRestrictionDescr( "==",
                                                                   "$name" ) );
        pattern.addConstraint( literalDescr );
        
        literalDescr = new FieldConstraintDescr( "age" );
        literalDescr.addRestriction( new VariableRestrictionDescr( "==",
                                                                   "$age" ) );
        pattern.addConstraint( literalDescr );
        
        literalDescr = new FieldConstraintDescr( "likes" );
        literalDescr.addRestriction( new VariableRestrictionDescr( "==",
                                                                   "$likes" ) );
        pattern.addConstraint( literalDescr );        
        
        RuleDescr ruleDescr = new RuleDescr( "rule-1" );
        packageDescr.addRule( ruleDescr );
        lhs = new AndDescr();
        ruleDescr.setLhs( lhs );
        
        pattern = new PatternDescr( Cheese.class.getName()  );
        lhs.addDescr( pattern );       
        pattern.addConstraint( new FieldBindingDescr( "type", "$type" ) );
        
        pattern = new PatternDescr( "query1" );
        lhs.addDescr( pattern );
        pattern.addConstraint( new LiteralDescr( "bobba",
                                                 LiteralDescr.TYPE_STRING ) );
        pattern.addConstraint( new VariableDescr( "$age" ) );
        pattern.addConstraint( new VariableDescr( "$type" ) );
        ruleDescr.setConsequence( "System.out.println(\"age: \" + $age);" );
        
        builder.addPackage( packageDescr );
        assertLength( 0,
                      builder.getErrors().getErrors() );

        RuleBase rbase = RuleBaseFactory.newRuleBase();
        rbase.addPackage( builder.getPackage() );
        StatefulSession session = rbase.newStatefulSession();
        
        session.insert( new Person( "bobba", "stilton", 90 ) );
        session.insert( new Person( "bobba", "brie", 80 ) );
        session.insert( new Person( "bobba", "brie", 75 ) );
        session.insert( new Person( "darth", "brie", 100 ) );
        session.insert( new Person( "luke", "brie", 25 ) );
        session.insert( new Cheese( "brie", 25 ) );
        session.fireAllRules();
    }

    public void testQuery() throws Exception {
        final PackageBuilder builder = new PackageBuilder();

        final PackageDescr packageDescr = new PackageDescr( "p1" );
        final QueryDescr queryDescr = new QueryDescr( "query1" );
        queryDescr.setParameters( new String[]{"$type"} );
        queryDescr.setParameterTypes( new String[]{"String"} );

        packageDescr.addRule( queryDescr );

        final AndDescr lhs = new AndDescr();
        queryDescr.setLhs( lhs );

        final PatternDescr pattern = new PatternDescr( Cheese.class.getName(),
                                                       "stilton" );
        lhs.addDescr( pattern );

        final FieldConstraintDescr literalDescr = new FieldConstraintDescr( "type" );
        literalDescr.addRestriction( new VariableRestrictionDescr( "==",
                                                                   "$type" ) );

        pattern.addConstraint( literalDescr );

        builder.addPackage( packageDescr );

        assertLength( 0,
                      builder.getErrors().getErrors() );

        RuleBase ruleBase = RuleBaseFactory.newRuleBase();
        ruleBase.addPackage( builder.getPackage() );

        StatefulSession session = ruleBase.newStatefulSession();

        session.insert( new Cheese( "stilton",
                                    15 ) );

        QueryResults results = session.getQueryResults( "query1",
                                                        new Object[]{"stilton"} );
        assertEquals( 1,
                      results.size() );
        Object object = results.get( 0 ).get( 0 );
        assertEquals( new Cheese( "stilton",
                                  15 ),
                      object );

        results = session.getQueryResults( "query1",
                                           new Object[]{"cheddar"} );
    }
}
