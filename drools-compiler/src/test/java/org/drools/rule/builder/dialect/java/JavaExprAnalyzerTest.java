/*
 * Copyright 2007 JBoss Inc
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
 *
 * Created on Jul 1, 2007
 */
package org.drools.rule.builder.dialect.java;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;

import org.antlr.runtime.RecognitionException;

/**
 * @author etirelli
 *
 */
public class JavaExprAnalyzerTest extends TestCase {

    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
    }

    /* (non-Javadoc)
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test method for {@link org.drools.rule.builder.dialect.java.JavaExprAnalyzer#analyzeBlock(java.lang.String, java.util.Set[])}.
     */
    public void testAnalyzeBlock() {
        JavaExprAnalyzer analyzer = new JavaExprAnalyzer();
        String codeBlock = "int x;\n"+
                           "Cheese cheese = new Cheese();\n"+
                           "for( Iterator it = list.iterator(); it.hasNext(); ) {\n" +
                           "    int shouldNotBeIncluded = 1;\n"+
                           "}\n" +
                           "{\n" +
                           "    String anotherNonTopLevelVar = \"test\";\n" +
                           "}\n" +
                           "double thisIsAGoodVar = 0;\n" +
                           "method();\n";
        try {
            JavaAnalysisResult analysis = analyzer.analyzeBlock( codeBlock, new Set[0] );
            List vars = analysis.getLocalVariables();
            
            assertEquals( 3, vars.size() );
            assertTrue( vars.contains( "x" ));
            assertTrue( vars.contains( "cheese" ));
            assertTrue( vars.contains( "thisIsAGoodVar" ));
            
        } catch ( RecognitionException e ) {
            e.printStackTrace();
            fail( "Not supposed to raise exception: "+e.getMessage());
        }
    }

}
