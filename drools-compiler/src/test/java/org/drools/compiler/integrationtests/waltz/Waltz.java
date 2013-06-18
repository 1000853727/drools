/*
 * Copyright 2005 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.compiler.integrationtests.waltz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.drools.compiler.CommonTestMethodBase;
import org.drools.core.PackageIntegrationException;
import org.drools.core.RuleBase;
import org.drools.core.RuleIntegrationException;
import org.drools.compiler.compiler.DrlParser;
import org.drools.compiler.compiler.DroolsError;
import org.drools.compiler.compiler.DroolsParserException;
import org.drools.compiler.compiler.PackageBuilder;
import org.drools.compiler.lang.descr.PackageDescr;
import org.drools.core.rule.InvalidPatternException;
import org.drools.core.rule.Package;

import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.builder.conf.LanguageLevelOption;
import org.kie.api.runtime.KieSession;

/**
 * This is a sample file to launch a rule package from a rule source file.
 */
public abstract class Waltz extends CommonTestMethodBase {

    protected abstract RuleBase getRuleBase() throws Exception;

    @Test(timeout = 20000 )
    public void testWaltz() {
        try {
            //load up the rulebase
            final KieBase kBase = readKnowledegBase();
            for ( int i = 0; i < 50; i++ ) {
                KieSession kSession = kBase.newKieSession();
    
    //            workingMemory.setGlobal( "sysout",
    //                                     System.out );
    
                //            DebugWorkingMemoryEventListener wmListener = new DebugWorkingMemoryEventListener();
                //            DebugAgendaEventListener agendaListener = new DebugAgendaEventListener();
                //            workingMemory.addEventListener( wmListener );
                //            workingMemory.addEventListener( agendaListener );
    
                //go !     
                this.loadLines( kSession,
                                "waltz50.dat" );
    
                //final Stage stage = new Stage( Stage.START );
                //workingMemory.assertObject( stage );
    
                final long start = System.currentTimeMillis();
    
                final Stage stage = new Stage( Stage.DUPLICATE );
                kSession.insert( stage );
                kSession.fireAllRules();
                kSession.dispose();
                final long end = System.currentTimeMillis();
                System.out.println( end - start );
            }
        } catch ( final Throwable t ) {
            t.printStackTrace();
            fail( t.getMessage() );
        }
    }

    /**
     * Please note that this is the "low level" rule assembly API.
     */
    private RuleBase readRule() throws Exception,
                               DroolsParserException,
            RuleIntegrationException,
                               PackageIntegrationException,
                               InvalidPatternException {
        //read in the source
        final Reader reader = new InputStreamReader( Waltz.class.getResourceAsStream( "waltz.drl" ) );
        final DrlParser parser = new DrlParser(LanguageLevelOption.DRL5);
        final PackageDescr packageDescr = parser.parse( reader );
        
        if( parser.hasErrors() ) {
            for( DroolsError error : parser.getErrors() ) {
                System.out.println( error );
            }
            assertFalse( parser.getErrors().toString(), parser.hasErrors() );
        }

        //pre build the package
        final PackageBuilder builder = new PackageBuilder();
        builder.addPackage( packageDescr );
        final Package pkg = builder.getPackage();

        //add the package to a rulebase        
        final RuleBase ruleBase = getRuleBase();
        ruleBase.addPackage( pkg );
        return ruleBase;
//        return SerializationHelper.serializeObject(ruleBase);
    }
    
    public KieBase readKnowledegBase() {
        KnowledgeBase kbase = loadKnowledgeBase( "waltz.drl");
        return ( KieBase ) kbase;
    }

    private void loadLines(final KieSession kSession,
                           final String filename) throws IOException {
        final BufferedReader reader = new BufferedReader( new InputStreamReader( Waltz.class.getResourceAsStream( filename ) ) );
        final Pattern pat = Pattern.compile( ".*make line \\^p1 ([0-9]*) \\^p2 ([0-9]*).*" );
        String line = reader.readLine();
        while ( line != null ) {
            final Matcher m = pat.matcher( line );
            if ( m.matches() ) {
                final Line l = new Line( Integer.parseInt( m.group( 1 ) ),
                                         Integer.parseInt( m.group( 2 ) ) );
                kSession.insert( l );
            }
            line = reader.readLine();
        }
        reader.close();
    }

}
