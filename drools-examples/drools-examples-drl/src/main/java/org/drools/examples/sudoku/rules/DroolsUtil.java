/*
 * JBoss, the OpenSource J2EE webOS
 * 
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package org.drools.examples.sudoku.rules;

import java.io.InputStreamReader;
import java.io.Reader;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseConfiguration;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.KnowledgeType;
import org.drools.io.ResourceFactory;

public class DroolsUtil {
    private static DroolsUtil INSTANCE;

    private DroolsUtil() {

    }

    public static DroolsUtil getInstance() {
        if ( INSTANCE == null ) {
            INSTANCE = new DroolsUtil();
        }

        return INSTANCE;
    }

    public KnowledgeBase readRuleBase(String drlFileName) throws Exception {
        //optionally read in the DSL (if you are using it).
        //Reader dsl = new InputStreamReader( DroolsTest.class.getResourceAsStream( "/mylang.dsl" ) );

        //Use package builder to build up a rule package.
        //An alternative lower level class called "DrlParser" can also be used...

        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

        //this will parse and compile in one step
        kbuilder.add( ResourceFactory.newClassPathResource( drlFileName,DroolsSudokuGridModel.class),
                             KnowledgeType.DRL );

        //Use the following instead of above if you are using a DSL:
        //builder.addPackageFromDrl( source, dsl );

        //add the package to a rulebase (deploy the rule package).
        KnowledgeBaseConfiguration kbaseconfiguration = KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
        kbaseconfiguration.setProperty( "drools.removeIdentities",
                                   "true" );

        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase( kbaseconfiguration );
        //        RuleBase ruleBase = RuleBaseFactory.newRuleBase( RuleBase.RETEOO,
        //                                                         conf );
        kbase.addKnowledgePackages( kbuilder.getKnowledgePackages() );
        return kbase;
    }
}
