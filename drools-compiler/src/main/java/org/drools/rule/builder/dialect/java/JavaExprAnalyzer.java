package org.drools.rule.builder.dialect.java;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.drools.rule.builder.dialect.java.parser.JavaLexer;
import org.drools.rule.builder.dialect.java.parser.JavaLocalDeclarationDescr;
import org.drools.rule.builder.dialect.java.parser.JavaParser;

/**
 * Expression analyzer.
 * 
 * @author <a href="mailto:bob@eng.werken.com">bob mcwhirter </a>
 */
public class JavaExprAnalyzer {
    // ------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------

    /**
     * Construct.
     */
    public JavaExprAnalyzer() {
        // intentionally left blank.
    }

    // ------------------------------------------------------------
    // Instance methods
    // ------------------------------------------------------------

    /**
     * Analyze an expression.
     * 
     * @param expr
     *            The expression to analyze.
     * @param availDecls
     *            Total set of declarations available.
     * 
     * @return The <code>Set</code> of declarations used by the expression.
     * @throws RecognitionException 
     *             If an error occurs in the parser.
     */
    public JavaAnalysisResult analyzeExpression(final String expr,
                                            final Set[] availableIdentifiers) throws RecognitionException {
        final CharStream charStream = new ANTLRStringStream( expr );
        final JavaLexer lexer = new JavaLexer( charStream );
        final TokenStream tokenStream = new CommonTokenStream( lexer );
        final JavaParser parser = new JavaParser( tokenStream );

        parser.conditionalOrExpression();
        
        JavaAnalysisResult result = new JavaAnalysisResult();
        result.setIdentifiers( parser.getIdentifiers() );
        return analyze( result,
                        availableIdentifiers );
    }

    public JavaAnalysisResult analyzeBlock(final String expr,
                                       final Set[] availableIdentifiers) throws RecognitionException {
        final CharStream charStream = new ANTLRStringStream( "{" + expr + "}" );
        final JavaLexer lexer = new JavaLexer( charStream );
        final TokenStream tokenStream = new CommonTokenStream( lexer );
        final JavaParser parser = new JavaParser( tokenStream );

        parser.block();

        JavaAnalysisResult result = new JavaAnalysisResult();
        result.setIdentifiers( parser.getIdentifiers() );
        result.setLocalVariables( new HashMap() );
        for( Iterator it = parser.getLocalDeclarations().iterator(); it.hasNext(); ) {
            JavaLocalDeclarationDescr descr = (JavaLocalDeclarationDescr) it.next();
            for( Iterator identIt = descr.getIdentifiers().iterator(); identIt.hasNext(); ) {
                JavaLocalDeclarationDescr.IdentifierDescr ident = (JavaLocalDeclarationDescr.IdentifierDescr) identIt.next();
                result.addLocalVariable( ident.getIdentifier(), descr );
            }
        }
        result.setModifyBlocks( parser.getModifyBlocks() );

        return analyze( result,
                        availableIdentifiers );
    }

    /**
     * Analyze an expression.
     * 
     * @param availDecls
     *            Total set of declarations available.
     * @param ast
     *            The AST for the expression.
     * 
     * @return The <code>Set</code> of declarations used by the expression.
     * 
     * @throws RecognitionException
     *             If an error occurs in the parser.
     */
    private JavaAnalysisResult analyze(final JavaAnalysisResult result,
                                   final Set[] availableIdentifiers) throws RecognitionException {
        final List identifiers = result.getIdentifiers();
        final Set notBound = new HashSet( identifiers );
        final List[] used = new List[availableIdentifiers.length];
        for ( int i = 0, length = used.length; i < length; i++ ) {
            used[i] = new ArrayList();
        }

        for ( int i = 0, length = availableIdentifiers.length; i < length; i++ ) {
            final Set set = availableIdentifiers[i];
            for ( final Iterator it = set.iterator(); it.hasNext(); ) {
                final String eachDecl = (String) it.next();
                if ( identifiers.contains( eachDecl ) ) {
                    used[i].add( eachDecl );
                    notBound.remove( eachDecl );
                }
            }
        }
        result.setBoundIdentifiers( used );
        result.setNotBoundedIdentifiers( new ArrayList( notBound ) );

        return result;
    }
}