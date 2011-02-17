package org.drools.lang;

import java.util.LinkedList;
import java.util.List;

import org.antlr.runtime.MismatchedTokenException;
import org.antlr.runtime.MissingTokenException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.UnwantedTokenException;
import org.drools.compiler.DroolsParserException;
import org.drools.lang.api.AnnotatedDescrBuilder;
import org.drools.lang.api.AnnotationDescrBuilder;
import org.drools.lang.api.DeclareDescrBuilder;
import org.drools.lang.api.DescrFactory;
import org.drools.lang.api.FieldDescrBuilder;
import org.drools.lang.api.GlobalDescrBuilder;
import org.drools.lang.api.ImportDescrBuilder;
import org.drools.lang.api.PackageDescrBuilder;
import org.drools.lang.descr.BaseDescr;
import org.drools.lang.descr.GlobalDescr;
import org.drools.lang.descr.ImportDescr;
import org.drools.lang.descr.PackageDescr;
import org.drools.lang.descr.TypeDeclarationDescr;

public class DRLXParser {

    private TokenStream           input;
    private RecognizerSharedState state;
    private ParserXHelper         helper;
    private DRLExpressions        exprParser;

    public DRLXParser(TokenStream input) {
        this.input = input;
        this.state = new RecognizerSharedState();
        this.helper = new ParserXHelper( DRLXTokens.tokenNames,
                                         input,
                                         state );
        this.exprParser = new DRLExpressions( input,
                                              state,
                                              helper );
    }

    /* ------------------------------------------------------------------------------------------------
     *                         GENERAL INTERFACING METHODS
     * ------------------------------------------------------------------------------------------------ */
    public String[] getTokenNames() {
        return DRLXTokens.tokenNames;
    }

    public ParserXHelper getHelper() {
        return helper;
    }

    public boolean hasErrors() {
        return helper.hasErrors();
    }

    public List<DroolsParserException> getErrors() {
        return helper.getErrors();
    }

    public List<String> getErrorMessages() {
        return helper.getErrorMessages();
    }

    public void enableEditorInterface() {
        helper.enableEditorInterface();
    }

    public void disableEditorInterface() {
        helper.disableEditorInterface();
    }

    public LinkedList<DroolsSentence> getEditorInterface() {
        return helper.getEditorInterface();
    }

    public void reportError( RecognitionException ex ) {
        if ( state.backtracking == 0 ) {
            helper.reportError( ex );
        }
    }

    /* ------------------------------------------------------------------------------------------------
     *                         GRAMMAR RULES
     * ------------------------------------------------------------------------------------------------ */

    /**
     * Entry point method of a DRL compilation unit
     * 
     * compilationUnit := package_statement? statement*
     *   
     * @return a PackageDescr with the content of the whole compilation unit
     * 
     * @throws RecognitionException
     */
    public final PackageDescr compilationUnit() throws RecognitionException {
        PackageDescrBuilder pkg = DescrFactory.newPackage();

        try {
            helper.builderContext.push( pkg );
            int next = input.LA( 1 );
            switch ( next ) {
                case DRLLexer.EOF :
                    return pkg.getDescr();
                case DRLLexer.ID :
                    // package declaration?
                    if ( helper.validateIdentifierKey( DroolsSoftKeywords.PACKAGE ) ) {
                        String pkgName = packageStatement();
                        pkg.name( pkgName );
                        if ( state.failed ) return pkg.getDescr();
                    }

                    // statements
                    int index;
                    do {
                        index = input.index();
                        statement();
                        if ( state.failed ) return pkg.getDescr();
                        // TODO: error handling and recovery
                    } while ( input.LA( 1 ) == DRLLexer.ID && index != input.index() );
                    break;
                default :
                    // TODO: error handling and recovery
                    break;
            }
        } catch ( RecognitionException e ) {
            helper.reportError( e );
        } catch ( Exception e ) {
            helper.reportError( e );
        } finally {
            helper.setEnd();
            helper.builderContext.pop();
        }
        return pkg.getDescr();
    }

    /**
     * Parses a package statement and returns the name of the package
     * or null if none is defined.
     * 
     * packageStatement := PACKAGE qualifiedIdentifier SEMICOLON?  
     * 
     * @return the name of the package or null if none is defined
     */
    public String packageStatement() throws RecognitionException {
        String pkgName = null;

        try {
            helper.start( PackageDescrBuilder.class, null );

            match( input,
                   DRLLexer.ID,
                   DroolsSoftKeywords.PACKAGE,
                   null,
                   DroolsEditorType.KEYWORD );
            if ( state.failed ) return pkgName;

            pkgName = qualifiedIdentifier();
            helper.setParaphrasesValue( DroolsParaphraseTypes.PACKAGE,
                                        pkgName );
            if ( state.failed ) return pkgName;

            if ( input.LA( 1 ) == DRLLexer.SEMICOLON ) {
                match( input,
                       DRLLexer.SEMICOLON,
                       null,
                       null,
                       DroolsEditorType.SYMBOL );
                if ( state.failed ) return pkgName;
            }

        } catch ( RecognitionException re ) {
            reportError( re );
        } finally {
            helper.end( PackageDescrBuilder.class );
        }
        return pkgName;
    }

    /**
     * statement := importStatement
     *           |  globalStatement
     *           |  declare
     *           |  function
     *           |  rule
     *           |  query
     *           |  ruleAttribute
     *           ;
     *           
     * @throws RecognitionException
     */
    public BaseDescr statement() throws RecognitionException {
        BaseDescr descr = null;
        try {
            if ( helper.validateIdentifierKey( DroolsSoftKeywords.IMPORT ) ) {
                descr = importStatement();
                if ( state.failed ) return descr;
            } else if ( helper.validateIdentifierKey( DroolsSoftKeywords.GLOBAL ) ) {
                descr = globalStatement();
                if ( state.failed ) return descr;
            } else if ( helper.validateIdentifierKey( DroolsSoftKeywords.DECLARE ) ) {
                descr = declare();
                if ( state.failed ) return descr;
            }
        } catch ( RecognitionException e ) {
            helper.reportError( e );
        } catch ( Exception e ) {
            helper.reportError( e );
        }
        return descr;
    }

    /* ------------------------------------------------------------------------------------------------
     *                         IMPORT STATEMENT
     * ------------------------------------------------------------------------------------------------ */

    /**
     * importStatement := IMPORT FUNCTION? qualifiedIdentifier (DOT STAR)? SEMICOLON?
     * 
     * @return
     * @throws RecognitionException
     */
    public ImportDescr importStatement() throws RecognitionException {
        ImportDescrBuilder imp = null;
        try {
            imp = helper.start( ImportDescrBuilder.class, null );

            // import
            match( input,
                   DRLLexer.ID,
                   DroolsSoftKeywords.IMPORT,
                   null,
                   DroolsEditorType.KEYWORD );
            if ( state.failed ) return imp.getDescr();

            if ( helper.validateIdentifierKey( DroolsSoftKeywords.FUNCTION ) ) {
                // function
                match( input,
                       DRLLexer.ID,
                       DroolsSoftKeywords.FUNCTION,
                       null,
                       DroolsEditorType.KEYWORD );
                if ( state.failed ) return imp.getDescr();
            }

            // qualifiedIdentifier
            String target = qualifiedIdentifier();

            if ( input.LA( 1 ) == DRLLexer.DOT && input.LA( 2 ) == DRLLexer.STAR ) {
                // .*
                match( input,
                       DRLLexer.DOT,
                       null,
                       null,
                       DroolsEditorType.IDENTIFIER );
                if ( state.failed ) return imp.getDescr();
                match( input,
                       DRLLexer.STAR,
                       null,
                       null,
                       DroolsEditorType.IDENTIFIER );
                if ( state.failed ) return imp.getDescr();
                target += ".*";
            }
            imp.target( target );

            if ( input.LA( 1 ) == DRLLexer.SEMICOLON ) {
                match( input,
                       DRLLexer.SEMICOLON,
                       null,
                       null,
                       DroolsEditorType.SYMBOL );
                if ( state.failed ) return imp.getDescr();
            }

        } catch ( RecognitionException re ) {
            reportError( re );
        } finally {
            helper.end( ImportDescrBuilder.class );
        }
        return (imp != null) ? imp.getDescr() : null;
    }

    /* ------------------------------------------------------------------------------------------------
     *                         GLOBAL STATEMENT
     * ------------------------------------------------------------------------------------------------ */

    /**
     * globalStatement := GLOBAL type ID SEMICOLON?
     * 
     * @return
     * @throws RecognitionException
     */
    public GlobalDescr globalStatement() throws RecognitionException {
        GlobalDescrBuilder global = null;
        try {
            global = helper.start( GlobalDescrBuilder.class, null );

            // 'global'
            match( input,
                   DRLLexer.ID,
                   DroolsSoftKeywords.GLOBAL,
                   null,
                   DroolsEditorType.KEYWORD );
            if ( state.failed ) return global.getDescr();

            // type
            String type = type();
            global.type( type );
            if ( state.failed ) return global.getDescr();

            // identifier
            Token id = match( input,
                              DRLLexer.ID,
                              null,
                              null,
                              DroolsEditorType.IDENTIFIER_TYPE );
            global.identifier( id.getText() );
            helper.setParaphrasesValue( DroolsParaphraseTypes.GLOBAL,
                                        id.getText() );
            if ( state.failed ) return global.getDescr();

            if ( input.LA( 1 ) == DRLLexer.SEMICOLON ) {
                match( input,
                       DRLLexer.SEMICOLON,
                       null,
                       null,
                       DroolsEditorType.SYMBOL );
                if ( state.failed ) return global.getDescr();
            }

        } catch ( RecognitionException re ) {
            reportError( re );
        } finally {
            helper.end( GlobalDescrBuilder.class );
        }
        return (global != null) ? global.getDescr() : null;
    }

    /* ------------------------------------------------------------------------------------------------
     *                         DECLARE STATEMENT
     * ------------------------------------------------------------------------------------------------ */

    /**
     * declare := DECLARE type annotation* field* END SEMICOLON?
     * 
     * @return
     * @throws RecognitionException
     */
    public TypeDeclarationDescr declare() throws RecognitionException {
        DeclareDescrBuilder declare = null;
        try {
            declare = helper.start( DeclareDescrBuilder.class, null );

            // 'declare'
            match( input,
                   DRLLexer.ID,
                   DroolsSoftKeywords.DECLARE,
                   null,
                   DroolsEditorType.KEYWORD );
            if ( state.failed ) return declare.getDescr();

            // type
            String type = type();
            declare.type( type );
            if ( state.failed ) return declare.getDescr();

            while ( input.LA( 1 ) == DRLLexer.AT ) {
                // metadata*
                annotation( declare );
                if ( state.failed ) return declare.getDescr();
            }

            while ( input.LA( 1 ) == DRLLexer.ID && !helper.validateIdentifierKey( DroolsSoftKeywords.END ) ) {
                // field*
                field( declare );
                if ( state.failed ) return declare.getDescr();
            }

            match( input,
                   DRLLexer.ID,
                   DroolsSoftKeywords.END,
                   null,
                   DroolsEditorType.KEYWORD );
            if ( state.failed ) return declare.getDescr();

            if ( input.LA( 1 ) == DRLLexer.SEMICOLON ) {
                match( input,
                       DRLLexer.SEMICOLON,
                       null,
                       null,
                       DroolsEditorType.SYMBOL );
                if ( state.failed ) return declare.getDescr();
            }

        } catch ( RecognitionException re ) {
            reportError( re );
        } finally {
            helper.end( DeclareDescrBuilder.class );
        }
        return (declare != null) ? declare.getDescr() : null;
    }

    /**
     * field := ID COLON type (EQUALS_ASSIGN expression)? annotation* SEMICOLON
     */
    private void field( DeclareDescrBuilder declare ) {
        FieldDescrBuilder field = null;
        try {
            // ID
            Token id = match( input,
                              DRLLexer.ID,
                              null,
                              null,
                              DroolsEditorType.IDENTIFIER );
            if ( state.failed ) return;

            field = helper.start( FieldDescrBuilder.class,
                                  id.getText() );

            match( input,
                   DRLLexer.COLON,
                   null,
                   null,
                   DroolsEditorType.SYMBOL );
            if ( state.failed ) return;

            // type
            String type = type();
            if ( state.failed ) return;
            if ( state.backtracking == 0 ) field.type( type );

            if ( input.LA( 1 ) == DRLLexer.EQUALS_ASSIGN ) {
                // EQUALS_ASSIGN
                match( input,
                       DRLLexer.EQUALS_ASSIGN,
                       null,
                       null,
                       DroolsEditorType.SYMBOL );
                if ( state.failed ) return;

                int first = input.index();
                exprParser.conditionalExpression();
                if ( state.failed ) return;
                String value = input.toString( first,
                                               input.LT( -1 ).getTokenIndex() );
                if ( state.backtracking == 0 ) field.initialValue( value );
            }

            while ( input.LA( 1 ) == DRLLexer.AT ) {
                // annotation*
                annotation( field );
                if ( state.failed ) return;
            }

            if ( input.LA( 1 ) == DRLLexer.SEMICOLON ) {
                match( input,
                       DRLLexer.SEMICOLON,
                       null,
                       null,
                       DroolsEditorType.SYMBOL );
                if ( state.failed ) return;
            }

        } catch ( RecognitionException re ) {
            reportError( re );
        } finally {
            helper.end( FieldDescrBuilder.class );
        }
    }

    /* ------------------------------------------------------------------------------------------------
     *                         ANNOTATION
     * ------------------------------------------------------------------------------------------------ */

    /**
     * annotation := AT ID (elementValuePairs | parenChunk )?
     */
    private void annotation( AnnotatedDescrBuilder adb ) {
        try {
            // '@'
            Token at = match( input,
                              DRLLexer.AT,
                              null,
                              null,
                              DroolsEditorType.SYMBOL );
            if ( state.failed ) return;

            // identifier
            Token id = match( input,
                              DRLLexer.ID,
                              null,
                              null,
                              DroolsEditorType.IDENTIFIER );
            if ( state.failed ) return;

            AnnotationDescrBuilder annotation = null;
            if ( state.backtracking == 0 ) {
                annotation = adb.newAnnotation( id.getText() );
                helper.setStart( annotation,
                                 at );
                helper.builderContext.push( annotation );
            }

            try {
                if ( input.LA( 1 ) == DRLLexer.LEFT_PAREN ) {
                    if ( speculateElementValuePairs() ) {
                        elementValuePairs( annotation );
                        if ( state.failed ) return;
                    } else {
                        String value = parenChunk( annotation );
                        if ( state.failed ) return;
                        if ( state.backtracking == 0 ) {
                            annotation.value( value );
                        }
                    }
                }
            } finally {
                if ( state.backtracking == 0 ) {
                    helper.setEnd();
                    helper.builderContext.pop();
                }
            }

        } catch ( RecognitionException re ) {
            reportError( re );
        }
    }

    /**
     * Invokes elementValuePairs() rule with backtracking
     * to check if the next token sequence matches it or not.
     * 
     * @return true if the sequence of tokens will match the
     *         elementValuePairs() syntax. false otherwise.
     */
    private boolean speculateElementValuePairs() {
        state.backtracking++;
        int start = input.mark();
        try {
            elementValuePairs( null ); // can never throw exception
        } catch ( RecognitionException re ) {
            System.err.println( "impossible: " + re );
            re.printStackTrace();
        }
        boolean success = !state.failed;
        input.rewind( start );
        state.backtracking--;
        state.failed = false;
        return success;

    }

    /**
     * elementValuePairs := LEFT_PAREN elementValuePair (COMMA elementValuePair)* RIGHT_PAREN
     * @param annotation
     */
    private void elementValuePairs( AnnotationDescrBuilder annotation ) throws RecognitionException {
        try {
            match( input,
                   DRLLexer.LEFT_PAREN,
                   null,
                   null,
                   DroolsEditorType.SYMBOL );
            if ( state.failed ) return;

            elementValuePair( annotation );
            if ( state.failed ) return;

            while ( input.LA( 1 ) == DRLLexer.COMMA ) {
                match( input,
                       DRLLexer.COMMA,
                       null,
                       null,
                       DroolsEditorType.SYMBOL );
                if ( state.failed ) return;

                elementValuePair( annotation );
                if ( state.failed ) return;
            }

            match( input,
                   DRLLexer.RIGHT_PAREN,
                   null,
                   null,
                   DroolsEditorType.SYMBOL );
            if ( state.failed ) return;

        } catch ( RecognitionException re ) {
            reportError( re );
        }
    }

    /**
     * elementValuePair := (ID EQUALS)? elementValue
     * @param annotation
     */
    private void elementValuePair( AnnotationDescrBuilder annotation ) {
        try {
            String key = null;
            if ( input.LA( 1 ) == DRLLexer.ID && input.LA( 2 ) == DRLLexer.EQUALS_ASSIGN ) {
                Token id = match( input,
                                  DRLLexer.ID,
                                  null,
                                  null,
                                  DroolsEditorType.IDENTIFIER );
                if ( state.failed ) return;
                key = id.getText();

                match( input,
                       DRLLexer.EQUALS_ASSIGN,
                       null,
                       null,
                       DroolsEditorType.SYMBOL );
                if ( state.failed ) return;
            }

            String value = safeStripDelimiters( elementValue(),
                                                "\"" );
            if ( state.failed ) return;

            if ( state.backtracking == 0 ) {
                annotation.keyValue( key != null ? key : "value",
                                     value );
            }

        } catch ( RecognitionException re ) {
            reportError( re );
        }
    }

    /**
     * elementValue := elementValueArrayInitializer | conditionalExpression 
     * @return
     */
    private String elementValue() {
        String value = "";
        try {
            int first = input.index();

            if ( input.LA( 1 ) == DRLLexer.LEFT_CURLY ) {
                elementValueArrayInitializer();
                if ( state.failed ) return value;
            } else {
                exprParser.conditionalExpression();
                if ( state.failed ) return value;
            }
            value = input.toString( first,
                                    input.LT( -1 ).getTokenIndex() );
        } catch ( RecognitionException re ) {
            reportError( re );
        }
        return value;
    }

    /**
     * elementValueArrayInitializer := LEFT_CURLY (elementValue (COMMA elementValue )*)? RIGHT_CURLY
     * @return
     */
    private String elementValueArrayInitializer() {
        String value = "";
        int first = input.index();
        try {
            match( input,
                   DRLLexer.LEFT_CURLY,
                   null,
                   null,
                   DroolsEditorType.SYMBOL );
            if ( state.failed ) return value;

            if ( input.LA( 1 ) != DRLLexer.RIGHT_CURLY ) {
                elementValue();
                if ( state.failed ) return value;

                while ( input.LA( 1 ) == DRLLexer.COMMA ) {
                    match( input,
                           DRLLexer.COMMA,
                           null,
                           null,
                           DroolsEditorType.SYMBOL );
                    if ( state.failed ) return value;

                    elementValue();
                    if ( state.failed ) return value;
                }
            }
            match( input,
                   DRLLexer.RIGHT_CURLY,
                   null,
                   null,
                   DroolsEditorType.SYMBOL );
            if ( state.failed ) return value;

        } catch ( RecognitionException re ) {
            reportError( re );
        } finally {
            value = input.toString( first,
                                    input.index() );
        }
        return value;
    }

    /* ------------------------------------------------------------------------------------------------
     *                         UTILITY RULES
     * ------------------------------------------------------------------------------------------------ */

    /**
     * Matches a type name
     * 
     * type := ID typeArguments? ( DOT ID typeArguments? )* (LEFT_SQUARE RIGHT_SQUARE)*
     * 
     * @return
     * @throws RecognitionException
     */
    public String type() throws RecognitionException {
        String type = "";
        try {
            int first = input.index(), last = first;
            match( input,
                   DRLLexer.ID,
                   null,
                   new int[]{DRLLexer.DOT, DRLLexer.LESS},
                   DroolsEditorType.IDENTIFIER );
            if ( state.failed ) return type;

            if ( input.LA( 1 ) == DRLLexer.LESS ) {
                typeArguments();
                if ( state.failed ) return type;
            }

            while ( input.LA( 1 ) == DRLLexer.DOT && input.LA( 2 ) == DRLLexer.ID ) {
                match( input,
                       DRLLexer.DOT,
                       null,
                       new int[]{DRLLexer.ID},
                       DroolsEditorType.IDENTIFIER );
                if ( state.failed ) return type;
                match( input,
                       DRLLexer.ID,
                       null,
                       new int[]{DRLLexer.DOT},
                       DroolsEditorType.IDENTIFIER );
                if ( state.failed ) return type;

                if ( input.LA( 1 ) == DRLLexer.LESS ) {
                    typeArguments();
                    if ( state.failed ) return type;
                }
            }

            while ( input.LA( 1 ) == DRLLexer.LEFT_SQUARE && input.LA( 2 ) == DRLLexer.RIGHT_SQUARE ) {
                match( input,
                               DRLLexer.LEFT_SQUARE,
                               null,
                               new int[]{DRLLexer.RIGHT_SQUARE},
                               DroolsEditorType.IDENTIFIER );
                if ( state.failed ) return type;
                match( input,
                               DRLLexer.RIGHT_SQUARE,
                               null,
                               null,
                               DroolsEditorType.IDENTIFIER );
                if ( state.failed ) return type;
            }
            last = input.LT( -1 ).getTokenIndex();
            type = input.toString( first,
                                   last );
        } catch ( RecognitionException re ) {
            reportError( re );
        }
        return type;
    }

    /**
     * Matches type arguments
     * 
     * typeArguments := LESS typeArgument (COMMA typeArgument)* GREATER
     * 
     * @return
     * @throws RecognitionException
     */
    public String typeArguments() throws RecognitionException {
        String typeArguments = "";
        try {
            int first = input.index();
            Token token = match( input,
                                 DRLLexer.LESS,
                                 null,
                                 new int[]{DRLLexer.QUESTION, DRLLexer.ID},
                                 DroolsEditorType.SYMBOL );
            if ( state.failed ) return typeArguments;

            typeArgument();
            if ( state.failed ) return typeArguments;

            while ( input.LA( 1 ) == DRLLexer.COMMA ) {
                token = match( input,
                               DRLLexer.COMMA,
                               null,
                               new int[]{DRLLexer.QUESTION, DRLLexer.ID},
                               DroolsEditorType.IDENTIFIER );
                if ( state.failed ) return typeArguments;

                typeArgument();
                if ( state.failed ) return typeArguments;
            }

            token = match( input,
                           DRLLexer.GREATER,
                           null,
                           null,
                           DroolsEditorType.SYMBOL );
            if ( state.failed ) return typeArguments;
            typeArguments = input.toString( first,
                                            token.getTokenIndex() );

        } catch ( RecognitionException re ) {
            reportError( re );
        }
        return typeArguments;
    }

    /**
     * Matches a type argument
     * 
     * typeArguments := QUESTION (( EXTENDS | SUPER ) type )? 
     *               |  type
     *               ;
     * 
     * @return
     * @throws RecognitionException
     */
    public String typeArgument() throws RecognitionException {
        String typeArgument = "";
        try {
            int first = input.index(), last = first;
            int next = input.LA( 1 );
            switch ( next ) {
                case DRLLexer.QUESTION :
                    match( input,
                           DRLLexer.QUESTION,
                           null,
                           null,
                           DroolsEditorType.SYMBOL );
                    if ( state.failed ) return typeArgument;

                    if ( helper.validateIdentifierKey( DroolsSoftKeywords.EXTENDS ) ) {
                        match( input,
                               DRLLexer.ID,
                               DroolsSoftKeywords.EXTENDS,
                               null,
                               DroolsEditorType.SYMBOL );
                        if ( state.failed ) return typeArgument;

                        type();
                        if ( state.failed ) return typeArgument;
                    } else if ( helper.validateIdentifierKey( DroolsSoftKeywords.SUPER ) ) {
                        match( input,
                               DRLLexer.ID,
                               DroolsSoftKeywords.SUPER,
                               null,
                               DroolsEditorType.SYMBOL );
                        if ( state.failed ) return typeArgument;
                        type();
                        if ( state.failed ) return typeArgument;
                    }
                    break;
                case DRLLexer.ID :
                    type();
                    if ( state.failed ) return typeArgument;
                    break;
                default :
                    // TODO: raise error
            }
            last = input.LT( -1 ).getTokenIndex();
            typeArgument = input.toString( first,
                                           last );
        } catch ( RecognitionException re ) {
            reportError( re );
        }
        return typeArgument;
    }

    /**
     * Matches a qualified identifier
     * 
     * qualifiedIdentifier := ID ( DOT ID )*
     * 
     * @return
     * @throws RecognitionException
     */
    public String qualifiedIdentifier() throws RecognitionException {
        String qi = "";
        try {
            Token first = match( input,
                                 DRLLexer.ID,
                                 null,
                                 new int[]{DRLLexer.DOT},
                                 DroolsEditorType.IDENTIFIER );
            if ( state.failed ) return qi;

            Token last = first;
            while ( input.LA( 1 ) == DRLLexer.DOT && input.LA( 2 ) == DRLLexer.ID ) {
                last = match( input,
                               DRLLexer.DOT,
                               null,
                               new int[]{DRLLexer.ID},
                               DroolsEditorType.IDENTIFIER );
                if ( state.failed ) return qi;
                last = match( input,
                               DRLLexer.ID,
                               null,
                               new int[]{DRLLexer.DOT},
                               DroolsEditorType.IDENTIFIER );
                if ( state.failed ) return qi;
            }
            qi = input.toString( first,
                                 last );
        } catch ( RecognitionException re ) {
            reportError( re );
        }
        return qi;
    }

    private String parenChunk( AnnotationDescrBuilder annotation ) {
        String chunk = "";
        int first = -1, last = first;
        try {
            match( input,
                   DRLLexer.LEFT_PAREN,
                   null,
                   null,
                   DroolsEditorType.SYMBOL );
            if ( state.failed ) return chunk;
            int nests = 0;
            first = input.index();

            while ( input.LA( 1 ) != DRLLexer.RIGHT_PAREN || nests > 0 ) {
                switch ( input.LA( 1 ) ) {
                    case DRLLexer.RIGHT_PAREN :
                        nests--;
                        break;
                    case DRLLexer.LEFT_PAREN :
                        nests++;
                        break;
                }
                input.consume();
            }
            last = input.LT( -1 ).getTokenIndex();

            match( input,
                   DRLLexer.RIGHT_PAREN,
                   null,
                   null,
                   DroolsEditorType.SYMBOL );
            if ( state.failed ) return chunk;

        } catch ( RecognitionException re ) {
            reportError( re );
        } finally {
            if ( last >= first ) {
                chunk = input.toString( first,
                                        last );
            }
        }
        return chunk;
    }

    /* ------------------------------------------------------------------------------------------------
      *                         GENERAL UTILITY METHODS
      * ------------------------------------------------------------------------------------------------ */
    /** 
     *  Match current input symbol against ttype and optionally
     *  check the text of the token against text.  Attempt
     *  single token insertion or deletion error recovery.  If
     *  that fails, throw MismatchedTokenException.
     */
    private Token match( TokenStream input,
                         int ttype,
                         String text,
                         int[] follow,
                         DroolsEditorType etype ) throws RecognitionException {
        Token matchedSymbol = null;
        matchedSymbol = input.LT( 1 );
        if ( input.LA( 1 ) == ttype && (text == null || text.equals( matchedSymbol.getText() )) ) {
            input.consume();
            state.errorRecovery = false;
            state.failed = false;
            helper.emit( matchedSymbol,
                         etype );
            return matchedSymbol;
        }
        if ( state.backtracking > 0 ) {
            state.failed = true;
            return matchedSymbol;
        }
        matchedSymbol = recoverFromMismatchedToken( input,
                                                    ttype,
                                                    text,
                                                    follow );
        helper.emit( matchedSymbol,
                     etype );
        return matchedSymbol;
    }

    /** Attempt to recover from a single missing or extra token.
    *
    *  EXTRA TOKEN
    *
    *  LA(1) is not what we are looking for.  If LA(2) has the right token,
    *  however, then assume LA(1) is some extra spurious token.  Delete it
    *  and LA(2) as if we were doing a normal match(), which advances the
    *  input.
    *
    *  MISSING TOKEN
    *
    *  If current token is consistent with what could come after
    *  ttype then it is ok to "insert" the missing token, else throw
    *  exception For example, Input "i=(3;" is clearly missing the
    *  ')'.  When the parser returns from the nested call to expr, it
    *  will have call chain:
    *
    *    stat -> expr -> atom
    *
    *  and it will be trying to match the ')' at this point in the
    *  derivation:
    *
    *       => ID '=' '(' INT ')' ('+' atom)* ';'
    *                          ^
    *  match() will see that ';' doesn't match ')' and report a
    *  mismatched token error.  To recover, it sees that LA(1)==';'
    *  is in the set of tokens that can follow the ')' token
    *  reference in rule atom.  It can assume that you forgot the ')'.
    */
    protected Token recoverFromMismatchedToken( TokenStream input,
                                                int ttype,
                                                String text,
                                                int[] follow )
                                                              throws RecognitionException {
        RecognitionException e = null;
        // if next token is what we are looking for then "delete" this token
        if ( mismatchIsUnwantedToken( input,
                                      ttype,
                                      text ) ) {
            e = new UnwantedTokenException( ttype,
                                            input );
            input.consume(); // simply delete extra token
            reportError( e ); // report after consuming so AW sees the token in the exception
            // we want to return the token we're actually matching
            Token matchedSymbol = input.LT( 1 );
            input.consume(); // move past ttype token as if all were ok
            return matchedSymbol;
        }
        // can't recover with single token deletion, try insertion
        if ( mismatchIsMissingToken( input,
                                     follow ) ) {
            e = new MissingTokenException( ttype,
                                           input,
                                           null );
            reportError( e ); // report after inserting so AW sees the token in the exception
            return null;
        }
        // even that didn't work; must throw the exception
        e = new MismatchedTokenException( ttype,
                                          input );
        throw e;
    }

    public boolean mismatchIsUnwantedToken( TokenStream input,
                                            int ttype,
                                            String text ) {
        return (input.LA( 2 ) == ttype && (text == null || text.equals( input.LT( 2 ).getText() )));
    }

    public boolean mismatchIsMissingToken( TokenStream input,
                                           int[] follow ) {
        if ( follow == null ) {
            // we have no information about the follow; we can only consume
            // a single token and hope for the best
            return false;
        }
        // TODO: implement this error recovery strategy
        return false;
    }

    private String safeStripDelimiters( String value,
                                        String delimiter ) {
        if ( value != null ) {
            value = value.trim();
            if ( value.length() > 1 && value.startsWith( delimiter ) && value.endsWith( delimiter ) ) {
                value = value.substring( 1,
                                         value.length() - 1 );
            }
        }
        return value;
    }

}