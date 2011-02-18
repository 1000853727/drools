// $ANTLR 3.3 Nov 30, 2010 12:45:30 /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g 2011-02-16 16:44:38

    package org.drools.lang;

    import java.util.LinkedList;
    import org.drools.compiler.DroolsParserException;
    import org.drools.lang.ParserHelper;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class DRLExpressions extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "EOL", "WS", "Exponent", "FloatTypeSuffix", "FLOAT", "HexDigit", "IntegerTypeSuffix", "HEX", "DECIMAL", "EscapeSequence", "STRING", "TimePeriod", "UnicodeEscape", "OctalEscape", "BOOL", "NULL", "AT", "PLUS_ASSIGN", "MINUS_ASSIGN", "MULT_ASSIGN", "DIV_ASSIGN", "AND_ASSIGN", "OR_ASSIGN", "XOR_ASSIGN", "MOD_ASSIGN", "DECR", "INCR", "ARROW", "SEMICOLON", "COLON", "EQUALS", "NOT_EQUALS", "GREATER_EQUALS", "LESS_EQUALS", "GREATER", "LESS", "EQUALS_ASSIGN", "LEFT_PAREN", "RIGHT_PAREN", "LEFT_SQUARE", "RIGHT_SQUARE", "LEFT_CURLY", "RIGHT_CURLY", "COMMA", "DOT", "DOUBLE_AMPER", "DOUBLE_PIPE", "QUESTION", "NEGATION", "TILDE", "PIPE", "AMPER", "XOR", "MOD", "STAR", "MINUS", "PLUS", "SH_STYLE_SINGLE_LINE_COMMENT", "C_STYLE_SINGLE_LINE_COMMENT", "MULTI_LINE_COMMENT", "IdentifierStart", "IdentifierPart", "ID", "DIV", "MISC"
    };
    public static final int EOF=-1;
    public static final int EOL=4;
    public static final int WS=5;
    public static final int Exponent=6;
    public static final int FloatTypeSuffix=7;
    public static final int FLOAT=8;
    public static final int HexDigit=9;
    public static final int IntegerTypeSuffix=10;
    public static final int HEX=11;
    public static final int DECIMAL=12;
    public static final int EscapeSequence=13;
    public static final int STRING=14;
    public static final int TimePeriod=15;
    public static final int UnicodeEscape=16;
    public static final int OctalEscape=17;
    public static final int BOOL=18;
    public static final int NULL=19;
    public static final int AT=20;
    public static final int PLUS_ASSIGN=21;
    public static final int MINUS_ASSIGN=22;
    public static final int MULT_ASSIGN=23;
    public static final int DIV_ASSIGN=24;
    public static final int AND_ASSIGN=25;
    public static final int OR_ASSIGN=26;
    public static final int XOR_ASSIGN=27;
    public static final int MOD_ASSIGN=28;
    public static final int DECR=29;
    public static final int INCR=30;
    public static final int ARROW=31;
    public static final int SEMICOLON=32;
    public static final int COLON=33;
    public static final int EQUALS=34;
    public static final int NOT_EQUALS=35;
    public static final int GREATER_EQUALS=36;
    public static final int LESS_EQUALS=37;
    public static final int GREATER=38;
    public static final int LESS=39;
    public static final int EQUALS_ASSIGN=40;
    public static final int LEFT_PAREN=41;
    public static final int RIGHT_PAREN=42;
    public static final int LEFT_SQUARE=43;
    public static final int RIGHT_SQUARE=44;
    public static final int LEFT_CURLY=45;
    public static final int RIGHT_CURLY=46;
    public static final int COMMA=47;
    public static final int DOT=48;
    public static final int DOUBLE_AMPER=49;
    public static final int DOUBLE_PIPE=50;
    public static final int QUESTION=51;
    public static final int NEGATION=52;
    public static final int TILDE=53;
    public static final int PIPE=54;
    public static final int AMPER=55;
    public static final int XOR=56;
    public static final int MOD=57;
    public static final int STAR=58;
    public static final int MINUS=59;
    public static final int PLUS=60;
    public static final int SH_STYLE_SINGLE_LINE_COMMENT=61;
    public static final int C_STYLE_SINGLE_LINE_COMMENT=62;
    public static final int MULTI_LINE_COMMENT=63;
    public static final int IdentifierStart=64;
    public static final int IdentifierPart=65;
    public static final int ID=66;
    public static final int DIV=67;
    public static final int MISC=68;

    // delegates
    // delegators


        public DRLExpressions(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public DRLExpressions(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return DRLExpressions.tokenNames; }
    public String getGrammarFileName() { return "/home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g"; }


        private ParserHelper helper;
                                                        
        public DRLExpressions(TokenStream input,
                              RecognizerSharedState state,
                              ParserHelper helper ) {
            this( input,
                  state );
            this.helper = helper;
        }

        public ParserHelper getHelper()                           { return helper; }
        public boolean hasErrors()                                { return helper.hasErrors(); }
        public List<DroolsParserException> getErrors()            { return helper.getErrors(); }
        public List<String> getErrorMessages()                    { return helper.getErrorMessages(); }
        public void enableEditorInterface()                       {        helper.enableEditorInterface(); }
        public void disableEditorInterface()                      {        helper.disableEditorInterface(); }
        public LinkedList<DroolsSentence> getEditorInterface()    { return helper.getEditorInterface(); }
        public void reportError(RecognitionException ex)          {        helper.reportError( ex ); }
        public void emitErrorMessage(String msg)                  {}
        
        private boolean buildConstraint;
        public void setBuildConstraint( boolean build ) { this.buildConstraint = build; }
        public boolean isBuildConstraint() { return this.buildConstraint; }




    // $ANTLR start "literal"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:54:1: literal : ( STRING | DECIMAL | HEX | FLOAT | BOOL | NULL );
    public final void literal() throws RecognitionException {
        Token STRING1=null;
        Token DECIMAL2=null;
        Token HEX3=null;
        Token FLOAT4=null;
        Token BOOL5=null;
        Token NULL6=null;

        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:55:2: ( STRING | DECIMAL | HEX | FLOAT | BOOL | NULL )
            int alt1=6;
            switch ( input.LA(1) ) {
            case STRING:
                {
                alt1=1;
                }
                break;
            case DECIMAL:
                {
                alt1=2;
                }
                break;
            case HEX:
                {
                alt1=3;
                }
                break;
            case FLOAT:
                {
                alt1=4;
                }
                break;
            case BOOL:
                {
                alt1=5;
                }
                break;
            case NULL:
                {
                alt1=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }

            switch (alt1) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:55:4: STRING
                    {
                    STRING1=(Token)match(input,STRING,FOLLOW_STRING_in_literal74); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                          helper.emit(STRING1, DroolsEditorType.STRING_CONST);
                    }

                    }
                    break;
                case 2 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:56:4: DECIMAL
                    {
                    DECIMAL2=(Token)match(input,DECIMAL,FOLLOW_DECIMAL_in_literal86); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                          helper.emit(DECIMAL2, DroolsEditorType.NUMERIC_CONST);
                    }

                    }
                    break;
                case 3 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:57:4: HEX
                    {
                    HEX3=(Token)match(input,HEX,FOLLOW_HEX_in_literal95); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                          helper.emit(HEX3, DroolsEditorType.NUMERIC_CONST);
                    }

                    }
                    break;
                case 4 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:58:4: FLOAT
                    {
                    FLOAT4=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_literal108); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                          helper.emit(FLOAT4, DroolsEditorType.NUMERIC_CONST);
                    }

                    }
                    break;
                case 5 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:59:4: BOOL
                    {
                    BOOL5=(Token)match(input,BOOL,FOLLOW_BOOL_in_literal119); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                          helper.emit(BOOL5, DroolsEditorType.BOOLEAN_CONST);
                    }

                    }
                    break;
                case 6 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:60:4: NULL
                    {
                    NULL6=(Token)match(input,NULL,FOLLOW_NULL_in_literal133); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                          helper.emit(NULL6, DroolsEditorType.NULL_CONST);
                    }

                    }
                    break;

            }
        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "literal"


    // $ANTLR start "typeList"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:63:1: typeList : type ( COMMA type )* ;
    public final void typeList() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:64:2: ( type ( COMMA type )* )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:64:4: type ( COMMA type )*
            {
            pushFollow(FOLLOW_type_in_typeList153);
            type();

            state._fsp--;
            if (state.failed) return ;
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:64:9: ( COMMA type )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==COMMA) ) {
                    alt2=1;
                }


                switch (alt2) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:64:10: COMMA type
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_typeList156); if (state.failed) return ;
                    pushFollow(FOLLOW_type_in_typeList158);
                    type();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

                default :
                    break loop2;
                }
            } while (true);


            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "typeList"


    // $ANTLR start "type"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:67:1: type : ( ( primitiveType )=> ( primitiveType ( ( LEFT_SQUARE RIGHT_SQUARE )=> LEFT_SQUARE RIGHT_SQUARE )* ) | ( ID ( ( typeArguments )=> typeArguments )? ( DOT ID ( ( typeArguments )=> typeArguments )? )* ( ( LEFT_SQUARE RIGHT_SQUARE )=> LEFT_SQUARE RIGHT_SQUARE )* ) );
    public final void type() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:68:2: ( ( primitiveType )=> ( primitiveType ( ( LEFT_SQUARE RIGHT_SQUARE )=> LEFT_SQUARE RIGHT_SQUARE )* ) | ( ID ( ( typeArguments )=> typeArguments )? ( DOT ID ( ( typeArguments )=> typeArguments )? )* ( ( LEFT_SQUARE RIGHT_SQUARE )=> LEFT_SQUARE RIGHT_SQUARE )* ) )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==ID) ) {
                int LA8_1 = input.LA(2);

                if ( (((synpred1_DRLExpressions()&&((helper.validateIdentifierKey(DroolsSoftKeywords.SHORT))))||(synpred1_DRLExpressions()&&((helper.validateIdentifierKey(DroolsSoftKeywords.CHAR))))||(synpred1_DRLExpressions()&&((helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF))))||(synpred1_DRLExpressions()&&((helper.validateIdentifierKey(DroolsSoftKeywords.LONG))))||(synpred1_DRLExpressions()&&((helper.validateIdentifierKey(DroolsSoftKeywords.INT))))||(synpred1_DRLExpressions()&&((helper.validateIdentifierKey(DroolsSoftKeywords.DOUBLE))))||(synpred1_DRLExpressions()&&((helper.validateIdentifierKey(DroolsSoftKeywords.BYTE))))||(synpred1_DRLExpressions()&&((helper.validateIdentifierKey(DroolsSoftKeywords.FLOAT)))))) ) {
                    alt8=1;
                }
                else if ( (true) ) {
                    alt8=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 8, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:68:5: ( primitiveType )=> ( primitiveType ( ( LEFT_SQUARE RIGHT_SQUARE )=> LEFT_SQUARE RIGHT_SQUARE )* )
                    {
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:68:24: ( primitiveType ( ( LEFT_SQUARE RIGHT_SQUARE )=> LEFT_SQUARE RIGHT_SQUARE )* )
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:68:26: primitiveType ( ( LEFT_SQUARE RIGHT_SQUARE )=> LEFT_SQUARE RIGHT_SQUARE )*
                    {
                    pushFollow(FOLLOW_primitiveType_in_type181);
                    primitiveType();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:68:40: ( ( LEFT_SQUARE RIGHT_SQUARE )=> LEFT_SQUARE RIGHT_SQUARE )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==LEFT_SQUARE) ) {
                            int LA3_2 = input.LA(2);

                            if ( (LA3_2==RIGHT_SQUARE) ) {
                                int LA3_3 = input.LA(3);

                                if ( (synpred2_DRLExpressions()) ) {
                                    alt3=1;
                                }


                            }


                        }


                        switch (alt3) {
                        case 1 :
                            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:68:41: ( LEFT_SQUARE RIGHT_SQUARE )=> LEFT_SQUARE RIGHT_SQUARE
                            {
                            match(input,LEFT_SQUARE,FOLLOW_LEFT_SQUARE_in_type191); if (state.failed) return ;
                            match(input,RIGHT_SQUARE,FOLLOW_RIGHT_SQUARE_in_type193); if (state.failed) return ;

                            }
                            break;

                        default :
                            break loop3;
                        }
                    } while (true);


                    }


                    }
                    break;
                case 2 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:69:4: ( ID ( ( typeArguments )=> typeArguments )? ( DOT ID ( ( typeArguments )=> typeArguments )? )* ( ( LEFT_SQUARE RIGHT_SQUARE )=> LEFT_SQUARE RIGHT_SQUARE )* )
                    {
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:69:4: ( ID ( ( typeArguments )=> typeArguments )? ( DOT ID ( ( typeArguments )=> typeArguments )? )* ( ( LEFT_SQUARE RIGHT_SQUARE )=> LEFT_SQUARE RIGHT_SQUARE )* )
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:69:6: ID ( ( typeArguments )=> typeArguments )? ( DOT ID ( ( typeArguments )=> typeArguments )? )* ( ( LEFT_SQUARE RIGHT_SQUARE )=> LEFT_SQUARE RIGHT_SQUARE )*
                    {
                    match(input,ID,FOLLOW_ID_in_type204); if (state.failed) return ;
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:69:9: ( ( typeArguments )=> typeArguments )?
                    int alt4=2;
                    alt4 = dfa4.predict(input);
                    switch (alt4) {
                        case 1 :
                            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:69:10: ( typeArguments )=> typeArguments
                            {
                            pushFollow(FOLLOW_typeArguments_in_type211);
                            typeArguments();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:69:43: ( DOT ID ( ( typeArguments )=> typeArguments )? )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==DOT) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                        case 1 :
                            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:69:44: DOT ID ( ( typeArguments )=> typeArguments )?
                            {
                            match(input,DOT,FOLLOW_DOT_in_type216); if (state.failed) return ;
                            match(input,ID,FOLLOW_ID_in_type218); if (state.failed) return ;
                            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:69:51: ( ( typeArguments )=> typeArguments )?
                            int alt5=2;
                            alt5 = dfa5.predict(input);
                            switch (alt5) {
                                case 1 :
                                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:69:52: ( typeArguments )=> typeArguments
                                    {
                                    pushFollow(FOLLOW_typeArguments_in_type225);
                                    typeArguments();

                                    state._fsp--;
                                    if (state.failed) return ;

                                    }
                                    break;

                            }


                            }
                            break;

                        default :
                            break loop6;
                        }
                    } while (true);

                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:69:88: ( ( LEFT_SQUARE RIGHT_SQUARE )=> LEFT_SQUARE RIGHT_SQUARE )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==LEFT_SQUARE) ) {
                            int LA7_2 = input.LA(2);

                            if ( (LA7_2==RIGHT_SQUARE) ) {
                                int LA7_3 = input.LA(3);

                                if ( (synpred5_DRLExpressions()) ) {
                                    alt7=1;
                                }


                            }


                        }


                        switch (alt7) {
                        case 1 :
                            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:69:89: ( LEFT_SQUARE RIGHT_SQUARE )=> LEFT_SQUARE RIGHT_SQUARE
                            {
                            match(input,LEFT_SQUARE,FOLLOW_LEFT_SQUARE_in_type240); if (state.failed) return ;
                            match(input,RIGHT_SQUARE,FOLLOW_RIGHT_SQUARE_in_type242); if (state.failed) return ;

                            }
                            break;

                        default :
                            break loop7;
                        }
                    } while (true);


                    }


                    }
                    break;

            }
        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "type"


    // $ANTLR start "typeArguments"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:72:1: typeArguments : LESS typeArgument ( COMMA typeArgument )* GREATER ;
    public final void typeArguments() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:73:2: ( LESS typeArgument ( COMMA typeArgument )* GREATER )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:73:4: LESS typeArgument ( COMMA typeArgument )* GREATER
            {
            match(input,LESS,FOLLOW_LESS_in_typeArguments257); if (state.failed) return ;
            pushFollow(FOLLOW_typeArgument_in_typeArguments259);
            typeArgument();

            state._fsp--;
            if (state.failed) return ;
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:73:22: ( COMMA typeArgument )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==COMMA) ) {
                    alt9=1;
                }


                switch (alt9) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:73:23: COMMA typeArgument
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_typeArguments262); if (state.failed) return ;
                    pushFollow(FOLLOW_typeArgument_in_typeArguments264);
                    typeArgument();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

                default :
                    break loop9;
                }
            } while (true);

            match(input,GREATER,FOLLOW_GREATER_in_typeArguments268); if (state.failed) return ;

            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "typeArguments"


    // $ANTLR start "typeArgument"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:76:1: typeArgument : ( type | QUESTION ( ( extends_key | super_key ) type )? );
    public final void typeArgument() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:77:2: ( type | QUESTION ( ( extends_key | super_key ) type )? )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==ID) ) {
                alt12=1;
            }
            else if ( (LA12_0==QUESTION) ) {
                alt12=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:77:4: type
                    {
                    pushFollow(FOLLOW_type_in_typeArgument280);
                    type();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:78:4: QUESTION ( ( extends_key | super_key ) type )?
                    {
                    match(input,QUESTION,FOLLOW_QUESTION_in_typeArgument285); if (state.failed) return ;
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:78:13: ( ( extends_key | super_key ) type )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==ID) && ((((helper.validateIdentifierKey(DroolsSoftKeywords.SUPER)))||((helper.validateIdentifierKey(DroolsSoftKeywords.EXTENDS)))))) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:78:14: ( extends_key | super_key ) type
                            {
                            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:78:14: ( extends_key | super_key )
                            int alt10=2;
                            int LA10_0 = input.LA(1);

                            if ( (LA10_0==ID) && ((((helper.validateIdentifierKey(DroolsSoftKeywords.SUPER)))||((helper.validateIdentifierKey(DroolsSoftKeywords.EXTENDS)))))) {
                                int LA10_1 = input.LA(2);

                                if ( (((helper.validateIdentifierKey(DroolsSoftKeywords.EXTENDS)))) ) {
                                    alt10=1;
                                }
                                else if ( (((helper.validateIdentifierKey(DroolsSoftKeywords.SUPER)))) ) {
                                    alt10=2;
                                }
                                else {
                                    if (state.backtracking>0) {state.failed=true; return ;}
                                    NoViableAltException nvae =
                                        new NoViableAltException("", 10, 1, input);

                                    throw nvae;
                                }
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return ;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 10, 0, input);

                                throw nvae;
                            }
                            switch (alt10) {
                                case 1 :
                                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:78:15: extends_key
                                    {
                                    pushFollow(FOLLOW_extends_key_in_typeArgument289);
                                    extends_key();

                                    state._fsp--;
                                    if (state.failed) return ;

                                    }
                                    break;
                                case 2 :
                                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:78:29: super_key
                                    {
                                    pushFollow(FOLLOW_super_key_in_typeArgument293);
                                    super_key();

                                    state._fsp--;
                                    if (state.failed) return ;

                                    }
                                    break;

                            }

                            pushFollow(FOLLOW_type_in_typeArgument296);
                            type();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;

            }
        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "typeArgument"


    // $ANTLR start "dummy"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:86:1: dummy : expression ( AT | DOUBLE_PIPE | DOUBLE_AMPER | PIPE | XOR | AMPER | SEMICOLON ) ;
    public final void dummy() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:87:2: ( expression ( AT | DOUBLE_PIPE | DOUBLE_AMPER | PIPE | XOR | AMPER | SEMICOLON ) )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:87:4: expression ( AT | DOUBLE_PIPE | DOUBLE_AMPER | PIPE | XOR | AMPER | SEMICOLON )
            {
            pushFollow(FOLLOW_expression_in_dummy314);
            expression();

            state._fsp--;
            if (state.failed) return ;
            if ( input.LA(1)==AT||input.LA(1)==SEMICOLON||(input.LA(1)>=DOUBLE_AMPER && input.LA(1)<=DOUBLE_PIPE)||(input.LA(1)>=PIPE && input.LA(1)<=XOR) ) {
                input.consume();
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "dummy"


    // $ANTLR start "expression"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:91:1: expression : conditionalExpression ( ( assignmentOperator )=> assignmentOperator expression )? ;
    public final void expression() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:92:2: ( conditionalExpression ( ( assignmentOperator )=> assignmentOperator expression )? )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:92:4: conditionalExpression ( ( assignmentOperator )=> assignmentOperator expression )?
            {
            pushFollow(FOLLOW_conditionalExpression_in_expression356);
            conditionalExpression();

            state._fsp--;
            if (state.failed) return ;
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:92:26: ( ( assignmentOperator )=> assignmentOperator expression )?
            int alt13=2;
            alt13 = dfa13.predict(input);
            switch (alt13) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:92:27: ( assignmentOperator )=> assignmentOperator expression
                    {
                    pushFollow(FOLLOW_assignmentOperator_in_expression365);
                    assignmentOperator();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_expression_in_expression367);
                    expression();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "expression"


    // $ANTLR start "conditionalExpression"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:95:1: conditionalExpression : conditionalOrExpression ( QUESTION expression COLON expression )? ;
    public final void conditionalExpression() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:96:3: ( conditionalOrExpression ( QUESTION expression COLON expression )? )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:96:5: conditionalOrExpression ( QUESTION expression COLON expression )?
            {
            pushFollow(FOLLOW_conditionalOrExpression_in_conditionalExpression382);
            conditionalOrExpression();

            state._fsp--;
            if (state.failed) return ;
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:96:29: ( QUESTION expression COLON expression )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==QUESTION) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:96:31: QUESTION expression COLON expression
                    {
                    match(input,QUESTION,FOLLOW_QUESTION_in_conditionalExpression386); if (state.failed) return ;
                    pushFollow(FOLLOW_expression_in_conditionalExpression388);
                    expression();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,COLON,FOLLOW_COLON_in_conditionalExpression390); if (state.failed) return ;
                    pushFollow(FOLLOW_expression_in_conditionalExpression392);
                    expression();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "conditionalExpression"


    // $ANTLR start "conditionalOrExpression"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:99:1: conditionalOrExpression : conditionalAndExpression ( DOUBLE_PIPE ( ( operator )=> operator shiftExpression | conditionalAndExpression ) )* ;
    public final void conditionalOrExpression() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:100:3: ( conditionalAndExpression ( DOUBLE_PIPE ( ( operator )=> operator shiftExpression | conditionalAndExpression ) )* )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:100:5: conditionalAndExpression ( DOUBLE_PIPE ( ( operator )=> operator shiftExpression | conditionalAndExpression ) )*
            {
            pushFollow(FOLLOW_conditionalAndExpression_in_conditionalOrExpression408);
            conditionalAndExpression();

            state._fsp--;
            if (state.failed) return ;
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:100:30: ( DOUBLE_PIPE ( ( operator )=> operator shiftExpression | conditionalAndExpression ) )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==DOUBLE_PIPE) ) {
                    int LA16_2 = input.LA(2);

                    if ( (LA16_2==FLOAT||(LA16_2>=HEX && LA16_2<=DECIMAL)||LA16_2==STRING||(LA16_2>=BOOL && LA16_2<=NULL)||(LA16_2>=DECR && LA16_2<=INCR)||(LA16_2>=EQUALS && LA16_2<=LESS)||LA16_2==LEFT_PAREN||LA16_2==LEFT_SQUARE||(LA16_2>=NEGATION && LA16_2<=TILDE)||(LA16_2>=MINUS && LA16_2<=PLUS)||LA16_2==ID) ) {
                        alt16=1;
                    }


                }


                switch (alt16) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:100:32: DOUBLE_PIPE ( ( operator )=> operator shiftExpression | conditionalAndExpression )
                    {
                    match(input,DOUBLE_PIPE,FOLLOW_DOUBLE_PIPE_in_conditionalOrExpression412); if (state.failed) return ;
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:101:6: ( ( operator )=> operator shiftExpression | conditionalAndExpression )
                    int alt15=2;
                    alt15 = dfa15.predict(input);
                    switch (alt15) {
                        case 1 :
                            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:101:8: ( operator )=> operator shiftExpression
                            {
                            pushFollow(FOLLOW_operator_in_conditionalOrExpression427);
                            operator();

                            state._fsp--;
                            if (state.failed) return ;
                            pushFollow(FOLLOW_shiftExpression_in_conditionalOrExpression429);
                            shiftExpression();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;
                        case 2 :
                            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:102:8: conditionalAndExpression
                            {
                            pushFollow(FOLLOW_conditionalAndExpression_in_conditionalOrExpression438);
                            conditionalAndExpression();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;

                default :
                    break loop16;
                }
            } while (true);


            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "conditionalOrExpression"


    // $ANTLR start "conditionalAndExpression"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:105:1: conditionalAndExpression : inclusiveOrExpression ( DOUBLE_AMPER ( ( operator )=> operator shiftExpression | inclusiveOrExpression ) )* ;
    public final void conditionalAndExpression() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:106:3: ( inclusiveOrExpression ( DOUBLE_AMPER ( ( operator )=> operator shiftExpression | inclusiveOrExpression ) )* )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:106:5: inclusiveOrExpression ( DOUBLE_AMPER ( ( operator )=> operator shiftExpression | inclusiveOrExpression ) )*
            {
            pushFollow(FOLLOW_inclusiveOrExpression_in_conditionalAndExpression456);
            inclusiveOrExpression();

            state._fsp--;
            if (state.failed) return ;
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:106:27: ( DOUBLE_AMPER ( ( operator )=> operator shiftExpression | inclusiveOrExpression ) )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==DOUBLE_AMPER) ) {
                    int LA18_2 = input.LA(2);

                    if ( (LA18_2==FLOAT||(LA18_2>=HEX && LA18_2<=DECIMAL)||LA18_2==STRING||(LA18_2>=BOOL && LA18_2<=NULL)||(LA18_2>=DECR && LA18_2<=INCR)||(LA18_2>=EQUALS && LA18_2<=LESS)||LA18_2==LEFT_PAREN||LA18_2==LEFT_SQUARE||(LA18_2>=NEGATION && LA18_2<=TILDE)||(LA18_2>=MINUS && LA18_2<=PLUS)||LA18_2==ID) ) {
                        alt18=1;
                    }


                }


                switch (alt18) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:106:29: DOUBLE_AMPER ( ( operator )=> operator shiftExpression | inclusiveOrExpression )
                    {
                    match(input,DOUBLE_AMPER,FOLLOW_DOUBLE_AMPER_in_conditionalAndExpression460); if (state.failed) return ;
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:107:5: ( ( operator )=> operator shiftExpression | inclusiveOrExpression )
                    int alt17=2;
                    alt17 = dfa17.predict(input);
                    switch (alt17) {
                        case 1 :
                            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:107:7: ( operator )=> operator shiftExpression
                            {
                            pushFollow(FOLLOW_operator_in_conditionalAndExpression474);
                            operator();

                            state._fsp--;
                            if (state.failed) return ;
                            pushFollow(FOLLOW_shiftExpression_in_conditionalAndExpression476);
                            shiftExpression();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;
                        case 2 :
                            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:108:7: inclusiveOrExpression
                            {
                            pushFollow(FOLLOW_inclusiveOrExpression_in_conditionalAndExpression484);
                            inclusiveOrExpression();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;

                default :
                    break loop18;
                }
            } while (true);


            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "conditionalAndExpression"


    // $ANTLR start "inclusiveOrExpression"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:111:1: inclusiveOrExpression : exclusiveOrExpression ( PIPE ( ( operator )=> operator shiftExpression | exclusiveOrExpression ) )* ;
    public final void inclusiveOrExpression() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:112:3: ( exclusiveOrExpression ( PIPE ( ( operator )=> operator shiftExpression | exclusiveOrExpression ) )* )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:112:5: exclusiveOrExpression ( PIPE ( ( operator )=> operator shiftExpression | exclusiveOrExpression ) )*
            {
            pushFollow(FOLLOW_exclusiveOrExpression_in_inclusiveOrExpression501);
            exclusiveOrExpression();

            state._fsp--;
            if (state.failed) return ;
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:112:27: ( PIPE ( ( operator )=> operator shiftExpression | exclusiveOrExpression ) )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==PIPE) ) {
                    int LA20_2 = input.LA(2);

                    if ( (LA20_2==FLOAT||(LA20_2>=HEX && LA20_2<=DECIMAL)||LA20_2==STRING||(LA20_2>=BOOL && LA20_2<=NULL)||(LA20_2>=DECR && LA20_2<=INCR)||(LA20_2>=EQUALS && LA20_2<=LESS)||LA20_2==LEFT_PAREN||LA20_2==LEFT_SQUARE||(LA20_2>=NEGATION && LA20_2<=TILDE)||(LA20_2>=MINUS && LA20_2<=PLUS)||LA20_2==ID) ) {
                        alt20=1;
                    }


                }


                switch (alt20) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:112:29: PIPE ( ( operator )=> operator shiftExpression | exclusiveOrExpression )
                    {
                    match(input,PIPE,FOLLOW_PIPE_in_inclusiveOrExpression505); if (state.failed) return ;
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:113:5: ( ( operator )=> operator shiftExpression | exclusiveOrExpression )
                    int alt19=2;
                    alt19 = dfa19.predict(input);
                    switch (alt19) {
                        case 1 :
                            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:113:7: ( operator )=> operator shiftExpression
                            {
                            pushFollow(FOLLOW_operator_in_inclusiveOrExpression518);
                            operator();

                            state._fsp--;
                            if (state.failed) return ;
                            pushFollow(FOLLOW_shiftExpression_in_inclusiveOrExpression520);
                            shiftExpression();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;
                        case 2 :
                            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:114:7: exclusiveOrExpression
                            {
                            pushFollow(FOLLOW_exclusiveOrExpression_in_inclusiveOrExpression528);
                            exclusiveOrExpression();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;

                default :
                    break loop20;
                }
            } while (true);


            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "inclusiveOrExpression"


    // $ANTLR start "exclusiveOrExpression"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:117:1: exclusiveOrExpression : andExpression ( XOR ( ( operator )=> operator shiftExpression | andExpression ) )* ;
    public final void exclusiveOrExpression() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:118:3: ( andExpression ( XOR ( ( operator )=> operator shiftExpression | andExpression ) )* )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:118:5: andExpression ( XOR ( ( operator )=> operator shiftExpression | andExpression ) )*
            {
            pushFollow(FOLLOW_andExpression_in_exclusiveOrExpression545);
            andExpression();

            state._fsp--;
            if (state.failed) return ;
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:118:19: ( XOR ( ( operator )=> operator shiftExpression | andExpression ) )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==XOR) ) {
                    int LA22_2 = input.LA(2);

                    if ( (LA22_2==FLOAT||(LA22_2>=HEX && LA22_2<=DECIMAL)||LA22_2==STRING||(LA22_2>=BOOL && LA22_2<=NULL)||(LA22_2>=DECR && LA22_2<=INCR)||(LA22_2>=EQUALS && LA22_2<=LESS)||LA22_2==LEFT_PAREN||LA22_2==LEFT_SQUARE||(LA22_2>=NEGATION && LA22_2<=TILDE)||(LA22_2>=MINUS && LA22_2<=PLUS)||LA22_2==ID) ) {
                        alt22=1;
                    }


                }


                switch (alt22) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:118:21: XOR ( ( operator )=> operator shiftExpression | andExpression )
                    {
                    match(input,XOR,FOLLOW_XOR_in_exclusiveOrExpression549); if (state.failed) return ;
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:119:5: ( ( operator )=> operator shiftExpression | andExpression )
                    int alt21=2;
                    alt21 = dfa21.predict(input);
                    switch (alt21) {
                        case 1 :
                            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:119:7: ( operator )=> operator shiftExpression
                            {
                            pushFollow(FOLLOW_operator_in_exclusiveOrExpression563);
                            operator();

                            state._fsp--;
                            if (state.failed) return ;
                            pushFollow(FOLLOW_shiftExpression_in_exclusiveOrExpression565);
                            shiftExpression();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;
                        case 2 :
                            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:120:7: andExpression
                            {
                            pushFollow(FOLLOW_andExpression_in_exclusiveOrExpression573);
                            andExpression();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;

                default :
                    break loop22;
                }
            } while (true);


            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "exclusiveOrExpression"


    // $ANTLR start "andExpression"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:123:1: andExpression : equalityExpression ( AMPER ( ( operator )=> operator shiftExpression | equalityExpression ) )* ;
    public final void andExpression() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:124:3: ( equalityExpression ( AMPER ( ( operator )=> operator shiftExpression | equalityExpression ) )* )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:124:5: equalityExpression ( AMPER ( ( operator )=> operator shiftExpression | equalityExpression ) )*
            {
            pushFollow(FOLLOW_equalityExpression_in_andExpression591);
            equalityExpression();

            state._fsp--;
            if (state.failed) return ;
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:124:24: ( AMPER ( ( operator )=> operator shiftExpression | equalityExpression ) )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==AMPER) ) {
                    int LA24_2 = input.LA(2);

                    if ( (LA24_2==FLOAT||(LA24_2>=HEX && LA24_2<=DECIMAL)||LA24_2==STRING||(LA24_2>=BOOL && LA24_2<=NULL)||(LA24_2>=DECR && LA24_2<=INCR)||(LA24_2>=EQUALS && LA24_2<=LESS)||LA24_2==LEFT_PAREN||LA24_2==LEFT_SQUARE||(LA24_2>=NEGATION && LA24_2<=TILDE)||(LA24_2>=MINUS && LA24_2<=PLUS)||LA24_2==ID) ) {
                        alt24=1;
                    }


                }


                switch (alt24) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:124:26: AMPER ( ( operator )=> operator shiftExpression | equalityExpression )
                    {
                    match(input,AMPER,FOLLOW_AMPER_in_andExpression595); if (state.failed) return ;
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:125:5: ( ( operator )=> operator shiftExpression | equalityExpression )
                    int alt23=2;
                    alt23 = dfa23.predict(input);
                    switch (alt23) {
                        case 1 :
                            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:125:7: ( operator )=> operator shiftExpression
                            {
                            pushFollow(FOLLOW_operator_in_andExpression609);
                            operator();

                            state._fsp--;
                            if (state.failed) return ;
                            pushFollow(FOLLOW_shiftExpression_in_andExpression611);
                            shiftExpression();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;
                        case 2 :
                            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:126:7: equalityExpression
                            {
                            pushFollow(FOLLOW_equalityExpression_in_andExpression619);
                            equalityExpression();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;

                default :
                    break loop24;
                }
            } while (true);


            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "andExpression"


    // $ANTLR start "equalityExpression"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:129:1: equalityExpression : instanceOfExpression ( ( EQUALS | NOT_EQUALS ) instanceOfExpression )* ;
    public final void equalityExpression() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:130:3: ( instanceOfExpression ( ( EQUALS | NOT_EQUALS ) instanceOfExpression )* )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:130:5: instanceOfExpression ( ( EQUALS | NOT_EQUALS ) instanceOfExpression )*
            {
            pushFollow(FOLLOW_instanceOfExpression_in_equalityExpression637);
            instanceOfExpression();

            state._fsp--;
            if (state.failed) return ;
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:130:26: ( ( EQUALS | NOT_EQUALS ) instanceOfExpression )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( ((LA25_0>=EQUALS && LA25_0<=NOT_EQUALS)) ) {
                    alt25=1;
                }


                switch (alt25) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:130:28: ( EQUALS | NOT_EQUALS ) instanceOfExpression
                    {
                    if ( (input.LA(1)>=EQUALS && input.LA(1)<=NOT_EQUALS) ) {
                        input.consume();
                        state.errorRecovery=false;state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }

                    pushFollow(FOLLOW_instanceOfExpression_in_equalityExpression651);
                    instanceOfExpression();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

                default :
                    break loop25;
                }
            } while (true);


            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "equalityExpression"


    // $ANTLR start "instanceOfExpression"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:133:1: instanceOfExpression : inExpression ( instanceof_key type )? ;
    public final void instanceOfExpression() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:134:3: ( inExpression ( instanceof_key type )? )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:134:5: inExpression ( instanceof_key type )?
            {
            pushFollow(FOLLOW_inExpression_in_instanceOfExpression667);
            inExpression();

            state._fsp--;
            if (state.failed) return ;
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:134:18: ( instanceof_key type )?
            int alt26=2;
            alt26 = dfa26.predict(input);
            switch (alt26) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:134:19: instanceof_key type
                    {
                    pushFollow(FOLLOW_instanceof_key_in_instanceOfExpression670);
                    instanceof_key();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_type_in_instanceOfExpression672);
                    type();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "instanceOfExpression"


    // $ANTLR start "inExpression"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:137:1: inExpression : relationalExpression ( ( not_key )? in_key LEFT_PAREN expression ( COMMA expression )* RIGHT_PAREN )? ;
    public final void inExpression() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:138:3: ( relationalExpression ( ( not_key )? in_key LEFT_PAREN expression ( COMMA expression )* RIGHT_PAREN )? )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:138:5: relationalExpression ( ( not_key )? in_key LEFT_PAREN expression ( COMMA expression )* RIGHT_PAREN )?
            {
            pushFollow(FOLLOW_relationalExpression_in_inExpression687);
            relationalExpression();

            state._fsp--;
            if (state.failed) return ;
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:138:26: ( ( not_key )? in_key LEFT_PAREN expression ( COMMA expression )* RIGHT_PAREN )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==ID) ) {
                int LA29_1 = input.LA(2);

                if ( ((((helper.validateIdentifierKey(DroolsSoftKeywords.NOT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.IN))))) ) {
                    alt29=1;
                }
            }
            switch (alt29) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:138:27: ( not_key )? in_key LEFT_PAREN expression ( COMMA expression )* RIGHT_PAREN
                    {
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:138:27: ( not_key )?
                    int alt27=2;
                    int LA27_0 = input.LA(1);

                    if ( (LA27_0==ID) && ((((helper.validateIdentifierKey(DroolsSoftKeywords.NOT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.IN)))))) {
                        int LA27_1 = input.LA(2);

                        if ( (LA27_1==ID) && (((helper.validateIdentifierKey(DroolsSoftKeywords.NOT))))) {
                            alt27=1;
                        }
                    }
                    switch (alt27) {
                        case 1 :
                            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:138:27: not_key
                            {
                            pushFollow(FOLLOW_not_key_in_inExpression690);
                            not_key();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    pushFollow(FOLLOW_in_key_in_inExpression693);
                    in_key();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_inExpression695); if (state.failed) return ;
                    pushFollow(FOLLOW_expression_in_inExpression697);
                    expression();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:138:65: ( COMMA expression )*
                    loop28:
                    do {
                        int alt28=2;
                        int LA28_0 = input.LA(1);

                        if ( (LA28_0==COMMA) ) {
                            alt28=1;
                        }


                        switch (alt28) {
                        case 1 :
                            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:138:66: COMMA expression
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_inExpression700); if (state.failed) return ;
                            pushFollow(FOLLOW_expression_in_inExpression702);
                            expression();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                        default :
                            break loop28;
                        }
                    } while (true);

                    match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_inExpression706); if (state.failed) return ;

                    }
                    break;

            }


            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "inExpression"


    // $ANTLR start "relationalExpression"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:141:1: relationalExpression : shiftExpression ( ( relationalOp )=> relationalOp shiftExpression )* ;
    public final void relationalExpression() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:142:3: ( shiftExpression ( ( relationalOp )=> relationalOp shiftExpression )* )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:142:5: shiftExpression ( ( relationalOp )=> relationalOp shiftExpression )*
            {
            pushFollow(FOLLOW_shiftExpression_in_relationalExpression722);
            shiftExpression();

            state._fsp--;
            if (state.failed) return ;
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:142:21: ( ( relationalOp )=> relationalOp shiftExpression )*
            loop30:
            do {
                int alt30=2;
                alt30 = dfa30.predict(input);
                switch (alt30) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:142:23: ( relationalOp )=> relationalOp shiftExpression
                    {
                    pushFollow(FOLLOW_relationalOp_in_relationalExpression731);
                    relationalOp();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_shiftExpression_in_relationalExpression733);
                    shiftExpression();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

                default :
                    break loop30;
                }
            } while (true);


            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "relationalExpression"


    // $ANTLR start "operator"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:145:1: operator : ( EQUALS | NOT_EQUALS | relationalOp ) ;
    public final void operator() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:146:3: ( ( EQUALS | NOT_EQUALS | relationalOp ) )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:146:5: ( EQUALS | NOT_EQUALS | relationalOp )
            {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:146:5: ( EQUALS | NOT_EQUALS | relationalOp )
            int alt31=3;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==EQUALS) ) {
                alt31=1;
            }
            else if ( (LA31_0==NOT_EQUALS) ) {
                alt31=2;
            }
            else if ( ((LA31_0>=GREATER_EQUALS && LA31_0<=LESS)) ) {
                alt31=3;
            }
            else if ( (LA31_0==ID) && ((((helper.validateIdentifierKey(DroolsSoftKeywords.NOT)))||((helper.isPluggableEvaluator(false)))))) {
                alt31=3;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;
            }
            switch (alt31) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:146:7: EQUALS
                    {
                    match(input,EQUALS,FOLLOW_EQUALS_in_operator752); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:147:7: NOT_EQUALS
                    {
                    match(input,NOT_EQUALS,FOLLOW_NOT_EQUALS_in_operator760); if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:148:7: relationalOp
                    {
                    pushFollow(FOLLOW_relationalOp_in_operator768);
                    relationalOp();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "operator"


    // $ANTLR start "relationalOp"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:152:1: relationalOp : ( LESS_EQUALS | GREATER_EQUALS | LESS | GREATER | not_key neg_operator_key | operator_key ) ;
    public final void relationalOp() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:153:3: ( ( LESS_EQUALS | GREATER_EQUALS | LESS | GREATER | not_key neg_operator_key | operator_key ) )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:153:5: ( LESS_EQUALS | GREATER_EQUALS | LESS | GREATER | not_key neg_operator_key | operator_key )
            {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:153:5: ( LESS_EQUALS | GREATER_EQUALS | LESS | GREATER | not_key neg_operator_key | operator_key )
            int alt32=6;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==LESS_EQUALS) ) {
                alt32=1;
            }
            else if ( (LA32_0==GREATER_EQUALS) ) {
                alt32=2;
            }
            else if ( (LA32_0==LESS) ) {
                alt32=3;
            }
            else if ( (LA32_0==GREATER) ) {
                alt32=4;
            }
            else if ( (LA32_0==ID) && ((((helper.validateIdentifierKey(DroolsSoftKeywords.NOT)))||((helper.isPluggableEvaluator(false)))))) {
                int LA32_5 = input.LA(2);

                if ( (((helper.validateIdentifierKey(DroolsSoftKeywords.NOT)))) ) {
                    alt32=5;
                }
                else if ( (((helper.isPluggableEvaluator(false)))) ) {
                    alt32=6;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 32, 5, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;
            }
            switch (alt32) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:153:7: LESS_EQUALS
                    {
                    match(input,LESS_EQUALS,FOLLOW_LESS_EQUALS_in_relationalOp794); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:154:7: GREATER_EQUALS
                    {
                    match(input,GREATER_EQUALS,FOLLOW_GREATER_EQUALS_in_relationalOp802); if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:155:7: LESS
                    {
                    match(input,LESS,FOLLOW_LESS_in_relationalOp811); if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:156:7: GREATER
                    {
                    match(input,GREATER,FOLLOW_GREATER_in_relationalOp820); if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:157:7: not_key neg_operator_key
                    {
                    pushFollow(FOLLOW_not_key_in_relationalOp828);
                    not_key();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_neg_operator_key_in_relationalOp830);
                    neg_operator_key();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 6 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:158:7: operator_key
                    {
                    pushFollow(FOLLOW_operator_key_in_relationalOp838);
                    operator_key();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "relationalOp"


    // $ANTLR start "shiftExpression"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:162:1: shiftExpression : additiveExpression ( ( shiftOp )=> shiftOp additiveExpression )* ;
    public final void shiftExpression() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:163:3: ( additiveExpression ( ( shiftOp )=> shiftOp additiveExpression )* )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:163:5: additiveExpression ( ( shiftOp )=> shiftOp additiveExpression )*
            {
            pushFollow(FOLLOW_additiveExpression_in_shiftExpression856);
            additiveExpression();

            state._fsp--;
            if (state.failed) return ;
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:163:24: ( ( shiftOp )=> shiftOp additiveExpression )*
            loop33:
            do {
                int alt33=2;
                alt33 = dfa33.predict(input);
                switch (alt33) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:163:26: ( shiftOp )=> shiftOp additiveExpression
                    {
                    pushFollow(FOLLOW_shiftOp_in_shiftExpression864);
                    shiftOp();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_additiveExpression_in_shiftExpression866);
                    additiveExpression();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

                default :
                    break loop33;
                }
            } while (true);


            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "shiftExpression"


    // $ANTLR start "shiftOp"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:166:1: shiftOp : ( LESS LESS | GREATER GREATER GREATER | GREATER GREATER ) ;
    public final void shiftOp() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:167:2: ( ( LESS LESS | GREATER GREATER GREATER | GREATER GREATER ) )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:167:4: ( LESS LESS | GREATER GREATER GREATER | GREATER GREATER )
            {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:167:4: ( LESS LESS | GREATER GREATER GREATER | GREATER GREATER )
            int alt34=3;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==LESS) ) {
                alt34=1;
            }
            else if ( (LA34_0==GREATER) ) {
                int LA34_2 = input.LA(2);

                if ( (LA34_2==GREATER) ) {
                    int LA34_3 = input.LA(3);

                    if ( (LA34_3==GREATER) ) {
                        alt34=2;
                    }
                    else if ( (LA34_3==EOF||LA34_3==FLOAT||(LA34_3>=HEX && LA34_3<=DECIMAL)||LA34_3==STRING||(LA34_3>=BOOL && LA34_3<=NULL)||(LA34_3>=DECR && LA34_3<=INCR)||LA34_3==LESS||LA34_3==LEFT_PAREN||LA34_3==LEFT_SQUARE||(LA34_3>=NEGATION && LA34_3<=TILDE)||(LA34_3>=MINUS && LA34_3<=PLUS)||LA34_3==ID) ) {
                        alt34=3;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 34, 3, input);

                        throw nvae;
                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 34, 2, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 34, 0, input);

                throw nvae;
            }
            switch (alt34) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:167:5: LESS LESS
                    {
                    match(input,LESS,FOLLOW_LESS_in_shiftOp881); if (state.failed) return ;
                    match(input,LESS,FOLLOW_LESS_in_shiftOp883); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:167:17: GREATER GREATER GREATER
                    {
                    match(input,GREATER,FOLLOW_GREATER_in_shiftOp887); if (state.failed) return ;
                    match(input,GREATER,FOLLOW_GREATER_in_shiftOp889); if (state.failed) return ;
                    match(input,GREATER,FOLLOW_GREATER_in_shiftOp891); if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:167:43: GREATER GREATER
                    {
                    match(input,GREATER,FOLLOW_GREATER_in_shiftOp895); if (state.failed) return ;
                    match(input,GREATER,FOLLOW_GREATER_in_shiftOp897); if (state.failed) return ;

                    }
                    break;

            }


            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "shiftOp"


    // $ANTLR start "additiveExpression"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:170:1: additiveExpression : multiplicativeExpression ( ( PLUS | MINUS )=> ( PLUS | MINUS ) multiplicativeExpression )* ;
    public final void additiveExpression() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:171:3: ( multiplicativeExpression ( ( PLUS | MINUS )=> ( PLUS | MINUS ) multiplicativeExpression )* )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:171:7: multiplicativeExpression ( ( PLUS | MINUS )=> ( PLUS | MINUS ) multiplicativeExpression )*
            {
            pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression913);
            multiplicativeExpression();

            state._fsp--;
            if (state.failed) return ;
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:171:32: ( ( PLUS | MINUS )=> ( PLUS | MINUS ) multiplicativeExpression )*
            loop35:
            do {
                int alt35=2;
                alt35 = dfa35.predict(input);
                switch (alt35) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:171:34: ( PLUS | MINUS )=> ( PLUS | MINUS ) multiplicativeExpression
                    {
                    if ( (input.LA(1)>=MINUS && input.LA(1)<=PLUS) ) {
                        input.consume();
                        state.errorRecovery=false;state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }

                    pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression932);
                    multiplicativeExpression();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

                default :
                    break loop35;
                }
            } while (true);


            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "additiveExpression"


    // $ANTLR start "multiplicativeExpression"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:174:1: multiplicativeExpression : unaryExpression ( ( STAR | DIV | MOD ) unaryExpression )* ;
    public final void multiplicativeExpression() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:175:3: ( unaryExpression ( ( STAR | DIV | MOD ) unaryExpression )* )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:175:7: unaryExpression ( ( STAR | DIV | MOD ) unaryExpression )*
            {
            pushFollow(FOLLOW_unaryExpression_in_multiplicativeExpression949);
            unaryExpression();

            state._fsp--;
            if (state.failed) return ;
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:175:23: ( ( STAR | DIV | MOD ) unaryExpression )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( ((LA36_0>=MOD && LA36_0<=STAR)||LA36_0==DIV) ) {
                    alt36=1;
                }


                switch (alt36) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:175:25: ( STAR | DIV | MOD ) unaryExpression
                    {
                    if ( (input.LA(1)>=MOD && input.LA(1)<=STAR)||input.LA(1)==DIV ) {
                        input.consume();
                        state.errorRecovery=false;state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }

                    pushFollow(FOLLOW_unaryExpression_in_multiplicativeExpression967);
                    unaryExpression();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

                default :
                    break loop36;
                }
            } while (true);


            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "multiplicativeExpression"


    // $ANTLR start "unaryExpression"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:178:1: unaryExpression : ( PLUS unaryExpression | MINUS unaryExpression | INCR primary | DECR primary | unaryExpressionNotPlusMinus );
    public final void unaryExpression() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:179:5: ( PLUS unaryExpression | MINUS unaryExpression | INCR primary | DECR primary | unaryExpressionNotPlusMinus )
            int alt37=5;
            switch ( input.LA(1) ) {
            case PLUS:
                {
                alt37=1;
                }
                break;
            case MINUS:
                {
                alt37=2;
                }
                break;
            case INCR:
                {
                alt37=3;
                }
                break;
            case DECR:
                {
                alt37=4;
                }
                break;
            case FLOAT:
            case HEX:
            case DECIMAL:
            case STRING:
            case BOOL:
            case NULL:
            case LESS:
            case LEFT_PAREN:
            case LEFT_SQUARE:
            case NEGATION:
            case TILDE:
            case ID:
                {
                alt37=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;
            }

            switch (alt37) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:179:9: PLUS unaryExpression
                    {
                    match(input,PLUS,FOLLOW_PLUS_in_unaryExpression987); if (state.failed) return ;
                    pushFollow(FOLLOW_unaryExpression_in_unaryExpression989);
                    unaryExpression();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:180:7: MINUS unaryExpression
                    {
                    match(input,MINUS,FOLLOW_MINUS_in_unaryExpression997); if (state.failed) return ;
                    pushFollow(FOLLOW_unaryExpression_in_unaryExpression999);
                    unaryExpression();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:181:9: INCR primary
                    {
                    match(input,INCR,FOLLOW_INCR_in_unaryExpression1009); if (state.failed) return ;
                    pushFollow(FOLLOW_primary_in_unaryExpression1011);
                    primary();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:182:9: DECR primary
                    {
                    match(input,DECR,FOLLOW_DECR_in_unaryExpression1021); if (state.failed) return ;
                    pushFollow(FOLLOW_primary_in_unaryExpression1023);
                    primary();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:183:9: unaryExpressionNotPlusMinus
                    {
                    pushFollow(FOLLOW_unaryExpressionNotPlusMinus_in_unaryExpression1033);
                    unaryExpressionNotPlusMinus();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "unaryExpression"


    // $ANTLR start "unaryExpressionNotPlusMinus"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:186:1: unaryExpressionNotPlusMinus : ( TILDE unaryExpression | NEGATION unaryExpression | ( castExpression )=> castExpression | primary ( ( selector )=> selector )* ( ( INCR | DECR )=> ( INCR | DECR ) )? );
    public final void unaryExpressionNotPlusMinus() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:187:5: ( TILDE unaryExpression | NEGATION unaryExpression | ( castExpression )=> castExpression | primary ( ( selector )=> selector )* ( ( INCR | DECR )=> ( INCR | DECR ) )? )
            int alt40=4;
            alt40 = dfa40.predict(input);
            switch (alt40) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:187:9: TILDE unaryExpression
                    {
                    match(input,TILDE,FOLLOW_TILDE_in_unaryExpressionNotPlusMinus1052); if (state.failed) return ;
                    pushFollow(FOLLOW_unaryExpression_in_unaryExpressionNotPlusMinus1054);
                    unaryExpression();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:188:8: NEGATION unaryExpression
                    {
                    match(input,NEGATION,FOLLOW_NEGATION_in_unaryExpressionNotPlusMinus1063); if (state.failed) return ;
                    pushFollow(FOLLOW_unaryExpression_in_unaryExpressionNotPlusMinus1065);
                    unaryExpression();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:189:9: ( castExpression )=> castExpression
                    {
                    pushFollow(FOLLOW_castExpression_in_unaryExpressionNotPlusMinus1079);
                    castExpression();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:190:9: primary ( ( selector )=> selector )* ( ( INCR | DECR )=> ( INCR | DECR ) )?
                    {
                    pushFollow(FOLLOW_primary_in_unaryExpressionNotPlusMinus1089);
                    primary();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:190:17: ( ( selector )=> selector )*
                    loop38:
                    do {
                        int alt38=2;
                        alt38 = dfa38.predict(input);
                        switch (alt38) {
                        case 1 :
                            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:190:18: ( selector )=> selector
                            {
                            pushFollow(FOLLOW_selector_in_unaryExpressionNotPlusMinus1096);
                            selector();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                        default :
                            break loop38;
                        }
                    } while (true);

                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:190:41: ( ( INCR | DECR )=> ( INCR | DECR ) )?
                    int alt39=2;
                    alt39 = dfa39.predict(input);
                    switch (alt39) {
                        case 1 :
                            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:190:42: ( INCR | DECR )=> ( INCR | DECR )
                            {
                            if ( (input.LA(1)>=DECR && input.LA(1)<=INCR) ) {
                                input.consume();
                                state.errorRecovery=false;state.failed=false;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return ;}
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                throw mse;
                            }


                            }
                            break;

                    }


                    }
                    break;

            }
        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "unaryExpressionNotPlusMinus"


    // $ANTLR start "castExpression"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:193:1: castExpression : ( ( LEFT_PAREN primitiveType )=> LEFT_PAREN primitiveType RIGHT_PAREN unaryExpression | ( LEFT_PAREN type )=> LEFT_PAREN type RIGHT_PAREN unaryExpressionNotPlusMinus );
    public final void castExpression() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:194:5: ( ( LEFT_PAREN primitiveType )=> LEFT_PAREN primitiveType RIGHT_PAREN unaryExpression | ( LEFT_PAREN type )=> LEFT_PAREN type RIGHT_PAREN unaryExpressionNotPlusMinus )
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==LEFT_PAREN) ) {
                int LA41_1 = input.LA(2);

                if ( (synpred18_DRLExpressions()) ) {
                    alt41=1;
                }
                else if ( (synpred19_DRLExpressions()) ) {
                    alt41=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 41, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 41, 0, input);

                throw nvae;
            }
            switch (alt41) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:194:8: ( LEFT_PAREN primitiveType )=> LEFT_PAREN primitiveType RIGHT_PAREN unaryExpression
                    {
                    match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_castExpression1144); if (state.failed) return ;
                    pushFollow(FOLLOW_primitiveType_in_castExpression1146);
                    primitiveType();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_castExpression1148); if (state.failed) return ;
                    pushFollow(FOLLOW_unaryExpression_in_castExpression1150);
                    unaryExpression();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:195:8: ( LEFT_PAREN type )=> LEFT_PAREN type RIGHT_PAREN unaryExpressionNotPlusMinus
                    {
                    match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_castExpression1167); if (state.failed) return ;
                    pushFollow(FOLLOW_type_in_castExpression1169);
                    type();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_castExpression1171); if (state.failed) return ;
                    pushFollow(FOLLOW_unaryExpressionNotPlusMinus_in_castExpression1173);
                    unaryExpressionNotPlusMinus();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "castExpression"


    // $ANTLR start "primitiveType"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:198:1: primitiveType : ( boolean_key | char_key | byte_key | short_key | int_key | long_key | float_key | double_key );
    public final void primitiveType() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:199:5: ( boolean_key | char_key | byte_key | short_key | int_key | long_key | float_key | double_key )
            int alt42=8;
            alt42 = dfa42.predict(input);
            switch (alt42) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:199:7: boolean_key
                    {
                    pushFollow(FOLLOW_boolean_key_in_primitiveType1194);
                    boolean_key();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:200:7: char_key
                    {
                    pushFollow(FOLLOW_char_key_in_primitiveType1202);
                    char_key();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:201:7: byte_key
                    {
                    pushFollow(FOLLOW_byte_key_in_primitiveType1210);
                    byte_key();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:202:7: short_key
                    {
                    pushFollow(FOLLOW_short_key_in_primitiveType1218);
                    short_key();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:203:7: int_key
                    {
                    pushFollow(FOLLOW_int_key_in_primitiveType1226);
                    int_key();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 6 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:204:7: long_key
                    {
                    pushFollow(FOLLOW_long_key_in_primitiveType1234);
                    long_key();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 7 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:205:7: float_key
                    {
                    pushFollow(FOLLOW_float_key_in_primitiveType1242);
                    float_key();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 8 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:206:7: double_key
                    {
                    pushFollow(FOLLOW_double_key_in_primitiveType1250);
                    double_key();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "primitiveType"


    // $ANTLR start "primary"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:209:1: primary : ( ( parExpression )=> parExpression | ( nonWildcardTypeArguments )=> nonWildcardTypeArguments ( explicitGenericInvocationSuffix | this_key arguments ) | ( literal )=> literal | ( super_key )=> super_key superSuffix | ( new_key )=> new_key creator | ( primitiveType )=> primitiveType ( LEFT_SQUARE RIGHT_SQUARE )* DOT class_key | ( inlineMapExpression )=> inlineMapExpression | ( inlineListExpression )=> inlineListExpression | ( ID )=> ID ( ( DOT ID )=> DOT ID )* ( ( identifierSuffix )=> identifierSuffix )? );
    public final void primary() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:210:5: ( ( parExpression )=> parExpression | ( nonWildcardTypeArguments )=> nonWildcardTypeArguments ( explicitGenericInvocationSuffix | this_key arguments ) | ( literal )=> literal | ( super_key )=> super_key superSuffix | ( new_key )=> new_key creator | ( primitiveType )=> primitiveType ( LEFT_SQUARE RIGHT_SQUARE )* DOT class_key | ( inlineMapExpression )=> inlineMapExpression | ( inlineListExpression )=> inlineListExpression | ( ID )=> ID ( ( DOT ID )=> DOT ID )* ( ( identifierSuffix )=> identifierSuffix )? )
            int alt47=9;
            alt47 = dfa47.predict(input);
            switch (alt47) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:210:7: ( parExpression )=> parExpression
                    {
                    pushFollow(FOLLOW_parExpression_in_primary1272);
                    parExpression();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:211:9: ( nonWildcardTypeArguments )=> nonWildcardTypeArguments ( explicitGenericInvocationSuffix | this_key arguments )
                    {
                    pushFollow(FOLLOW_nonWildcardTypeArguments_in_primary1287);
                    nonWildcardTypeArguments();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:211:63: ( explicitGenericInvocationSuffix | this_key arguments )
                    int alt43=2;
                    int LA43_0 = input.LA(1);

                    if ( (LA43_0==ID) ) {
                        int LA43_1 = input.LA(2);

                        if ( (!((((helper.validateIdentifierKey(DroolsSoftKeywords.THIS)))))) ) {
                            alt43=1;
                        }
                        else if ( (((helper.validateIdentifierKey(DroolsSoftKeywords.THIS)))) ) {
                            alt43=2;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return ;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 43, 1, input);

                            throw nvae;
                        }
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 43, 0, input);

                        throw nvae;
                    }
                    switch (alt43) {
                        case 1 :
                            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:211:64: explicitGenericInvocationSuffix
                            {
                            pushFollow(FOLLOW_explicitGenericInvocationSuffix_in_primary1290);
                            explicitGenericInvocationSuffix();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;
                        case 2 :
                            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:211:98: this_key arguments
                            {
                            pushFollow(FOLLOW_this_key_in_primary1294);
                            this_key();

                            state._fsp--;
                            if (state.failed) return ;
                            pushFollow(FOLLOW_arguments_in_primary1296);
                            arguments();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:212:9: ( literal )=> literal
                    {
                    pushFollow(FOLLOW_literal_in_primary1312);
                    literal();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:214:9: ( super_key )=> super_key superSuffix
                    {
                    pushFollow(FOLLOW_super_key_in_primary1332);
                    super_key();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_superSuffix_in_primary1334);
                    superSuffix();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:215:9: ( new_key )=> new_key creator
                    {
                    pushFollow(FOLLOW_new_key_in_primary1349);
                    new_key();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_creator_in_primary1351);
                    creator();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 6 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:216:9: ( primitiveType )=> primitiveType ( LEFT_SQUARE RIGHT_SQUARE )* DOT class_key
                    {
                    pushFollow(FOLLOW_primitiveType_in_primary1366);
                    primitiveType();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:216:41: ( LEFT_SQUARE RIGHT_SQUARE )*
                    loop44:
                    do {
                        int alt44=2;
                        int LA44_0 = input.LA(1);

                        if ( (LA44_0==LEFT_SQUARE) ) {
                            alt44=1;
                        }


                        switch (alt44) {
                        case 1 :
                            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:216:42: LEFT_SQUARE RIGHT_SQUARE
                            {
                            match(input,LEFT_SQUARE,FOLLOW_LEFT_SQUARE_in_primary1369); if (state.failed) return ;
                            match(input,RIGHT_SQUARE,FOLLOW_RIGHT_SQUARE_in_primary1371); if (state.failed) return ;

                            }
                            break;

                        default :
                            break loop44;
                        }
                    } while (true);

                    match(input,DOT,FOLLOW_DOT_in_primary1375); if (state.failed) return ;
                    pushFollow(FOLLOW_class_key_in_primary1377);
                    class_key();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 7 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:218:9: ( inlineMapExpression )=> inlineMapExpression
                    {
                    pushFollow(FOLLOW_inlineMapExpression_in_primary1397);
                    inlineMapExpression();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 8 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:219:9: ( inlineListExpression )=> inlineListExpression
                    {
                    pushFollow(FOLLOW_inlineListExpression_in_primary1412);
                    inlineListExpression();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 9 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:220:9: ( ID )=> ID ( ( DOT ID )=> DOT ID )* ( ( identifierSuffix )=> identifierSuffix )?
                    {
                    match(input,ID,FOLLOW_ID_in_primary1426); if (state.failed) return ;
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:220:18: ( ( DOT ID )=> DOT ID )*
                    loop45:
                    do {
                        int alt45=2;
                        int LA45_0 = input.LA(1);

                        if ( (LA45_0==DOT) ) {
                            int LA45_2 = input.LA(2);

                            if ( (LA45_2==ID) ) {
                                int LA45_3 = input.LA(3);

                                if ( (synpred29_DRLExpressions()) ) {
                                    alt45=1;
                                }


                            }


                        }


                        switch (alt45) {
                        case 1 :
                            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:220:19: ( DOT ID )=> DOT ID
                            {
                            match(input,DOT,FOLLOW_DOT_in_primary1435); if (state.failed) return ;
                            match(input,ID,FOLLOW_ID_in_primary1437); if (state.failed) return ;

                            }
                            break;

                        default :
                            break loop45;
                        }
                    } while (true);

                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:220:38: ( ( identifierSuffix )=> identifierSuffix )?
                    int alt46=2;
                    alt46 = dfa46.predict(input);
                    switch (alt46) {
                        case 1 :
                            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:220:39: ( identifierSuffix )=> identifierSuffix
                            {
                            pushFollow(FOLLOW_identifierSuffix_in_primary1446);
                            identifierSuffix();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;

            }
        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "primary"


    // $ANTLR start "inlineListExpression"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:223:1: inlineListExpression : LEFT_SQUARE ( expressionList )? RIGHT_SQUARE ;
    public final void inlineListExpression() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:224:5: ( LEFT_SQUARE ( expressionList )? RIGHT_SQUARE )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:224:9: LEFT_SQUARE ( expressionList )? RIGHT_SQUARE
            {
            match(input,LEFT_SQUARE,FOLLOW_LEFT_SQUARE_in_inlineListExpression1467); if (state.failed) return ;
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:224:21: ( expressionList )?
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==FLOAT||(LA48_0>=HEX && LA48_0<=DECIMAL)||LA48_0==STRING||(LA48_0>=BOOL && LA48_0<=NULL)||(LA48_0>=DECR && LA48_0<=INCR)||LA48_0==LESS||LA48_0==LEFT_PAREN||LA48_0==LEFT_SQUARE||(LA48_0>=NEGATION && LA48_0<=TILDE)||(LA48_0>=MINUS && LA48_0<=PLUS)||LA48_0==ID) ) {
                alt48=1;
            }
            switch (alt48) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:224:21: expressionList
                    {
                    pushFollow(FOLLOW_expressionList_in_inlineListExpression1469);
                    expressionList();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            match(input,RIGHT_SQUARE,FOLLOW_RIGHT_SQUARE_in_inlineListExpression1472); if (state.failed) return ;

            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "inlineListExpression"


    // $ANTLR start "inlineMapExpression"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:227:1: inlineMapExpression : LEFT_SQUARE ( mapExpressionList )+ RIGHT_SQUARE ;
    public final void inlineMapExpression() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:228:5: ( LEFT_SQUARE ( mapExpressionList )+ RIGHT_SQUARE )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:228:7: LEFT_SQUARE ( mapExpressionList )+ RIGHT_SQUARE
            {
            match(input,LEFT_SQUARE,FOLLOW_LEFT_SQUARE_in_inlineMapExpression1494); if (state.failed) return ;
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:228:19: ( mapExpressionList )+
            int cnt49=0;
            loop49:
            do {
                int alt49=2;
                int LA49_0 = input.LA(1);

                if ( (LA49_0==FLOAT||(LA49_0>=HEX && LA49_0<=DECIMAL)||LA49_0==STRING||(LA49_0>=BOOL && LA49_0<=NULL)||(LA49_0>=DECR && LA49_0<=INCR)||LA49_0==LESS||LA49_0==LEFT_PAREN||LA49_0==LEFT_SQUARE||(LA49_0>=NEGATION && LA49_0<=TILDE)||(LA49_0>=MINUS && LA49_0<=PLUS)||LA49_0==ID) ) {
                    alt49=1;
                }


                switch (alt49) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:228:19: mapExpressionList
                    {
                    pushFollow(FOLLOW_mapExpressionList_in_inlineMapExpression1496);
                    mapExpressionList();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

                default :
                    if ( cnt49 >= 1 ) break loop49;
                    if (state.backtracking>0) {state.failed=true; return ;}
                        EarlyExitException eee =
                            new EarlyExitException(49, input);
                        throw eee;
                }
                cnt49++;
            } while (true);

            match(input,RIGHT_SQUARE,FOLLOW_RIGHT_SQUARE_in_inlineMapExpression1499); if (state.failed) return ;

            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "inlineMapExpression"


    // $ANTLR start "mapExpressionList"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:231:1: mapExpressionList : mapEntry ( COMMA mapEntry )* ;
    public final void mapExpressionList() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:232:5: ( mapEntry ( COMMA mapEntry )* )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:232:7: mapEntry ( COMMA mapEntry )*
            {
            pushFollow(FOLLOW_mapEntry_in_mapExpressionList1516);
            mapEntry();

            state._fsp--;
            if (state.failed) return ;
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:232:16: ( COMMA mapEntry )*
            loop50:
            do {
                int alt50=2;
                int LA50_0 = input.LA(1);

                if ( (LA50_0==COMMA) ) {
                    alt50=1;
                }


                switch (alt50) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:232:17: COMMA mapEntry
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_mapExpressionList1519); if (state.failed) return ;
                    pushFollow(FOLLOW_mapEntry_in_mapExpressionList1521);
                    mapEntry();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

                default :
                    break loop50;
                }
            } while (true);


            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "mapExpressionList"


    // $ANTLR start "mapEntry"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:235:1: mapEntry : expression COLON expression ;
    public final void mapEntry() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:236:5: ( expression COLON expression )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:236:7: expression COLON expression
            {
            pushFollow(FOLLOW_expression_in_mapEntry1544);
            expression();

            state._fsp--;
            if (state.failed) return ;
            match(input,COLON,FOLLOW_COLON_in_mapEntry1546); if (state.failed) return ;
            pushFollow(FOLLOW_expression_in_mapEntry1548);
            expression();

            state._fsp--;
            if (state.failed) return ;

            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "mapEntry"


    // $ANTLR start "parExpression"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:239:1: parExpression : LEFT_PAREN expression RIGHT_PAREN ;
    public final void parExpression() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:240:2: ( LEFT_PAREN expression RIGHT_PAREN )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:240:4: LEFT_PAREN expression RIGHT_PAREN
            {
            match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_parExpression1562); if (state.failed) return ;
            pushFollow(FOLLOW_expression_in_parExpression1564);
            expression();

            state._fsp--;
            if (state.failed) return ;
            match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_parExpression1566); if (state.failed) return ;

            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "parExpression"


    // $ANTLR start "identifierSuffix"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:243:1: identifierSuffix : ( ( LEFT_SQUARE RIGHT_SQUARE )=> ( LEFT_SQUARE RIGHT_SQUARE )+ DOT class_key | ( ( LEFT_SQUARE )=> LEFT_SQUARE expression RIGHT_SQUARE )+ | arguments );
    public final void identifierSuffix() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:244:5: ( ( LEFT_SQUARE RIGHT_SQUARE )=> ( LEFT_SQUARE RIGHT_SQUARE )+ DOT class_key | ( ( LEFT_SQUARE )=> LEFT_SQUARE expression RIGHT_SQUARE )+ | arguments )
            int alt53=3;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==LEFT_SQUARE) ) {
                int LA53_1 = input.LA(2);

                if ( (LA53_1==RIGHT_SQUARE) && (synpred31_DRLExpressions())) {
                    alt53=1;
                }
                else if ( (LA53_1==FLOAT||(LA53_1>=HEX && LA53_1<=DECIMAL)||LA53_1==STRING||(LA53_1>=BOOL && LA53_1<=NULL)||(LA53_1>=DECR && LA53_1<=INCR)||LA53_1==LESS||LA53_1==LEFT_PAREN||LA53_1==LEFT_SQUARE||(LA53_1>=NEGATION && LA53_1<=TILDE)||(LA53_1>=MINUS && LA53_1<=PLUS)||LA53_1==ID) ) {
                    alt53=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 53, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA53_0==LEFT_PAREN) ) {
                alt53=3;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 53, 0, input);

                throw nvae;
            }
            switch (alt53) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:244:7: ( LEFT_SQUARE RIGHT_SQUARE )=> ( LEFT_SQUARE RIGHT_SQUARE )+ DOT class_key
                    {
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:244:35: ( LEFT_SQUARE RIGHT_SQUARE )+
                    int cnt51=0;
                    loop51:
                    do {
                        int alt51=2;
                        int LA51_0 = input.LA(1);

                        if ( (LA51_0==LEFT_SQUARE) ) {
                            alt51=1;
                        }


                        switch (alt51) {
                        case 1 :
                            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:244:36: LEFT_SQUARE RIGHT_SQUARE
                            {
                            match(input,LEFT_SQUARE,FOLLOW_LEFT_SQUARE_in_identifierSuffix1588); if (state.failed) return ;
                            match(input,RIGHT_SQUARE,FOLLOW_RIGHT_SQUARE_in_identifierSuffix1590); if (state.failed) return ;

                            }
                            break;

                        default :
                            if ( cnt51 >= 1 ) break loop51;
                            if (state.backtracking>0) {state.failed=true; return ;}
                                EarlyExitException eee =
                                    new EarlyExitException(51, input);
                                throw eee;
                        }
                        cnt51++;
                    } while (true);

                    match(input,DOT,FOLLOW_DOT_in_identifierSuffix1594); if (state.failed) return ;
                    pushFollow(FOLLOW_class_key_in_identifierSuffix1596);
                    class_key();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:245:7: ( ( LEFT_SQUARE )=> LEFT_SQUARE expression RIGHT_SQUARE )+
                    {
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:245:7: ( ( LEFT_SQUARE )=> LEFT_SQUARE expression RIGHT_SQUARE )+
                    int cnt52=0;
                    loop52:
                    do {
                        int alt52=2;
                        alt52 = dfa52.predict(input);
                        switch (alt52) {
                        case 1 :
                            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:245:8: ( LEFT_SQUARE )=> LEFT_SQUARE expression RIGHT_SQUARE
                            {
                            match(input,LEFT_SQUARE,FOLLOW_LEFT_SQUARE_in_identifierSuffix1611); if (state.failed) return ;
                            pushFollow(FOLLOW_expression_in_identifierSuffix1613);
                            expression();

                            state._fsp--;
                            if (state.failed) return ;
                            match(input,RIGHT_SQUARE,FOLLOW_RIGHT_SQUARE_in_identifierSuffix1615); if (state.failed) return ;

                            }
                            break;

                        default :
                            if ( cnt52 >= 1 ) break loop52;
                            if (state.backtracking>0) {state.failed=true; return ;}
                                EarlyExitException eee =
                                    new EarlyExitException(52, input);
                                throw eee;
                        }
                        cnt52++;
                    } while (true);


                    }
                    break;
                case 3 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:246:9: arguments
                    {
                    pushFollow(FOLLOW_arguments_in_identifierSuffix1628);
                    arguments();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "identifierSuffix"


    // $ANTLR start "creator"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:254:1: creator : ( nonWildcardTypeArguments )? createdName ( arrayCreatorRest | classCreatorRest ) ;
    public final void creator() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:255:2: ( ( nonWildcardTypeArguments )? createdName ( arrayCreatorRest | classCreatorRest ) )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:255:4: ( nonWildcardTypeArguments )? createdName ( arrayCreatorRest | classCreatorRest )
            {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:255:4: ( nonWildcardTypeArguments )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==LESS) ) {
                alt54=1;
            }
            switch (alt54) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:255:4: nonWildcardTypeArguments
                    {
                    pushFollow(FOLLOW_nonWildcardTypeArguments_in_creator1646);
                    nonWildcardTypeArguments();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            pushFollow(FOLLOW_createdName_in_creator1649);
            createdName();

            state._fsp--;
            if (state.failed) return ;
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:256:9: ( arrayCreatorRest | classCreatorRest )
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==LEFT_SQUARE) ) {
                alt55=1;
            }
            else if ( (LA55_0==LEFT_PAREN) ) {
                alt55=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 55, 0, input);

                throw nvae;
            }
            switch (alt55) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:256:10: arrayCreatorRest
                    {
                    pushFollow(FOLLOW_arrayCreatorRest_in_creator1660);
                    arrayCreatorRest();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:256:29: classCreatorRest
                    {
                    pushFollow(FOLLOW_classCreatorRest_in_creator1664);
                    classCreatorRest();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "creator"


    // $ANTLR start "createdName"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:259:1: createdName : ( ID ( typeArguments )? ( DOT ID ( typeArguments )? )* | primitiveType );
    public final void createdName() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:260:2: ( ID ( typeArguments )? ( DOT ID ( typeArguments )? )* | primitiveType )
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==ID) && ((!(((((helper.validateIdentifierKey(DroolsSoftKeywords.SHORT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.LONG)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF)))||((helper.validateIdentifierKey(DroolsSoftKeywords.BYTE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.FLOAT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.DOUBLE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.CHAR))))))||!(((((helper.validateIdentifierKey(DroolsSoftKeywords.SHORT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.LONG)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF)))||((helper.validateIdentifierKey(DroolsSoftKeywords.BYTE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.FLOAT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.DOUBLE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.CHAR))))))||!(((((helper.validateIdentifierKey(DroolsSoftKeywords.SHORT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.LONG)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF)))||((helper.validateIdentifierKey(DroolsSoftKeywords.BYTE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.FLOAT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.DOUBLE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.CHAR))))))||!(((((helper.validateIdentifierKey(DroolsSoftKeywords.SHORT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.LONG)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF)))||((helper.validateIdentifierKey(DroolsSoftKeywords.BYTE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.FLOAT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.DOUBLE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.CHAR))))))||!(((((helper.validateIdentifierKey(DroolsSoftKeywords.SHORT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.LONG)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF)))||((helper.validateIdentifierKey(DroolsSoftKeywords.BYTE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.FLOAT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.DOUBLE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.CHAR))))))||((helper.validateIdentifierKey(DroolsSoftKeywords.INT)))||!(((((helper.validateIdentifierKey(DroolsSoftKeywords.SHORT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.LONG)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF)))||((helper.validateIdentifierKey(DroolsSoftKeywords.BYTE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.FLOAT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.DOUBLE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.CHAR))))))||!(((((helper.validateIdentifierKey(DroolsSoftKeywords.SHORT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.LONG)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF)))||((helper.validateIdentifierKey(DroolsSoftKeywords.BYTE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.FLOAT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.DOUBLE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.CHAR))))))||!(((((helper.validateIdentifierKey(DroolsSoftKeywords.SHORT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.LONG)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF)))||((helper.validateIdentifierKey(DroolsSoftKeywords.BYTE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.FLOAT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.DOUBLE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.CHAR))))))||((helper.validateIdentifierKey(DroolsSoftKeywords.SHORT)))||!(((((helper.validateIdentifierKey(DroolsSoftKeywords.SHORT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.LONG)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF)))||((helper.validateIdentifierKey(DroolsSoftKeywords.BYTE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.FLOAT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.DOUBLE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.CHAR))))))||((helper.validateIdentifierKey(DroolsSoftKeywords.BYTE)))||!(((((helper.validateIdentifierKey(DroolsSoftKeywords.SHORT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.LONG)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF)))||((helper.validateIdentifierKey(DroolsSoftKeywords.BYTE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.FLOAT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.DOUBLE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.CHAR))))))||!(((((helper.validateIdentifierKey(DroolsSoftKeywords.SHORT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.LONG)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF)))||((helper.validateIdentifierKey(DroolsSoftKeywords.BYTE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.FLOAT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.DOUBLE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.CHAR))))))||!(((((helper.validateIdentifierKey(DroolsSoftKeywords.SHORT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.LONG)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF)))||((helper.validateIdentifierKey(DroolsSoftKeywords.BYTE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.FLOAT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.DOUBLE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.CHAR))))))||((helper.validateIdentifierKey(DroolsSoftKeywords.CHAR)))||!(((((helper.validateIdentifierKey(DroolsSoftKeywords.SHORT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.LONG)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF)))||((helper.validateIdentifierKey(DroolsSoftKeywords.BYTE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.FLOAT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.DOUBLE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.CHAR))))))||!(((((helper.validateIdentifierKey(DroolsSoftKeywords.SHORT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.LONG)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF)))||((helper.validateIdentifierKey(DroolsSoftKeywords.BYTE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.FLOAT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.DOUBLE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.CHAR))))))||!(((((helper.validateIdentifierKey(DroolsSoftKeywords.SHORT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.LONG)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF)))||((helper.validateIdentifierKey(DroolsSoftKeywords.BYTE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.FLOAT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.DOUBLE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.CHAR))))))||!(((((helper.validateIdentifierKey(DroolsSoftKeywords.SHORT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.LONG)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF)))||((helper.validateIdentifierKey(DroolsSoftKeywords.BYTE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.FLOAT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.DOUBLE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.CHAR))))))||!(((((helper.validateIdentifierKey(DroolsSoftKeywords.SHORT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.LONG)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF)))||((helper.validateIdentifierKey(DroolsSoftKeywords.BYTE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.FLOAT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.DOUBLE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.CHAR))))))||!(((((helper.validateIdentifierKey(DroolsSoftKeywords.SHORT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.LONG)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF)))||((helper.validateIdentifierKey(DroolsSoftKeywords.BYTE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.FLOAT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.DOUBLE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.CHAR))))))||!(((((helper.validateIdentifierKey(DroolsSoftKeywords.SHORT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.LONG)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF)))||((helper.validateIdentifierKey(DroolsSoftKeywords.BYTE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.FLOAT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.DOUBLE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.CHAR))))))||!(((((helper.validateIdentifierKey(DroolsSoftKeywords.SHORT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.LONG)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF)))||((helper.validateIdentifierKey(DroolsSoftKeywords.BYTE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.FLOAT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.DOUBLE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.CHAR))))))||((helper.validateIdentifierKey(DroolsSoftKeywords.DOUBLE)))||!(((((helper.validateIdentifierKey(DroolsSoftKeywords.SHORT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.LONG)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF)))||((helper.validateIdentifierKey(DroolsSoftKeywords.BYTE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.FLOAT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.DOUBLE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.CHAR))))))||!(((((helper.validateIdentifierKey(DroolsSoftKeywords.SHORT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.LONG)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF)))||((helper.validateIdentifierKey(DroolsSoftKeywords.BYTE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.FLOAT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.DOUBLE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.CHAR))))))||!(((((helper.validateIdentifierKey(DroolsSoftKeywords.SHORT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.LONG)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF)))||((helper.validateIdentifierKey(DroolsSoftKeywords.BYTE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.FLOAT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.DOUBLE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.CHAR))))))||!(((((helper.validateIdentifierKey(DroolsSoftKeywords.SHORT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.LONG)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF)))||((helper.validateIdentifierKey(DroolsSoftKeywords.BYTE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.FLOAT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.DOUBLE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.CHAR))))))||((helper.validateIdentifierKey(DroolsSoftKeywords.LONG)))||!(((((helper.validateIdentifierKey(DroolsSoftKeywords.SHORT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.LONG)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF)))||((helper.validateIdentifierKey(DroolsSoftKeywords.BYTE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.FLOAT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.DOUBLE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.CHAR))))))||((helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF)))||((helper.validateIdentifierKey(DroolsSoftKeywords.FLOAT)))||!(((((helper.validateIdentifierKey(DroolsSoftKeywords.SHORT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.LONG)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF)))||((helper.validateIdentifierKey(DroolsSoftKeywords.BYTE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.FLOAT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.DOUBLE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.CHAR))))))||!(((((helper.validateIdentifierKey(DroolsSoftKeywords.SHORT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.LONG)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF)))||((helper.validateIdentifierKey(DroolsSoftKeywords.BYTE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.FLOAT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.DOUBLE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.CHAR))))))||!(((((helper.validateIdentifierKey(DroolsSoftKeywords.SHORT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.LONG)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF)))||((helper.validateIdentifierKey(DroolsSoftKeywords.BYTE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.FLOAT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.DOUBLE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.CHAR))))))||!(((((helper.validateIdentifierKey(DroolsSoftKeywords.SHORT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.LONG)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF)))||((helper.validateIdentifierKey(DroolsSoftKeywords.BYTE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.FLOAT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.DOUBLE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.CHAR))))))))) {
                int LA59_1 = input.LA(2);

                if ( (!(((((helper.validateIdentifierKey(DroolsSoftKeywords.SHORT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.LONG)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF)))||((helper.validateIdentifierKey(DroolsSoftKeywords.BYTE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.FLOAT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.DOUBLE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.CHAR))))))) ) {
                    alt59=1;
                }
                else if ( ((((helper.validateIdentifierKey(DroolsSoftKeywords.SHORT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.LONG)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF)))||((helper.validateIdentifierKey(DroolsSoftKeywords.BYTE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.FLOAT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.DOUBLE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.CHAR))))) ) {
                    alt59=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 59, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 59, 0, input);

                throw nvae;
            }
            switch (alt59) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:260:4: ID ( typeArguments )? ( DOT ID ( typeArguments )? )*
                    {
                    match(input,ID,FOLLOW_ID_in_createdName1676); if (state.failed) return ;
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:260:7: ( typeArguments )?
                    int alt56=2;
                    int LA56_0 = input.LA(1);

                    if ( (LA56_0==LESS) ) {
                        alt56=1;
                    }
                    switch (alt56) {
                        case 1 :
                            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:260:7: typeArguments
                            {
                            pushFollow(FOLLOW_typeArguments_in_createdName1678);
                            typeArguments();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:261:9: ( DOT ID ( typeArguments )? )*
                    loop58:
                    do {
                        int alt58=2;
                        int LA58_0 = input.LA(1);

                        if ( (LA58_0==DOT) ) {
                            alt58=1;
                        }


                        switch (alt58) {
                        case 1 :
                            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:261:11: DOT ID ( typeArguments )?
                            {
                            match(input,DOT,FOLLOW_DOT_in_createdName1691); if (state.failed) return ;
                            match(input,ID,FOLLOW_ID_in_createdName1693); if (state.failed) return ;
                            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:261:18: ( typeArguments )?
                            int alt57=2;
                            int LA57_0 = input.LA(1);

                            if ( (LA57_0==LESS) ) {
                                alt57=1;
                            }
                            switch (alt57) {
                                case 1 :
                                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:261:18: typeArguments
                                    {
                                    pushFollow(FOLLOW_typeArguments_in_createdName1695);
                                    typeArguments();

                                    state._fsp--;
                                    if (state.failed) return ;

                                    }
                                    break;

                            }


                            }
                            break;

                        default :
                            break loop58;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:262:11: primitiveType
                    {
                    pushFollow(FOLLOW_primitiveType_in_createdName1710);
                    primitiveType();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "createdName"


    // $ANTLR start "innerCreator"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:265:1: innerCreator : {...}? => ID classCreatorRest ;
    public final void innerCreator() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:266:2: ({...}? => ID classCreatorRest )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:266:4: {...}? => ID classCreatorRest
            {
            if ( !((!(helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF)))) ) {
                if (state.backtracking>0) {state.failed=true; return ;}
                throw new FailedPredicateException(input, "innerCreator", "!(helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF))");
            }
            match(input,ID,FOLLOW_ID_in_innerCreator1725); if (state.failed) return ;
            pushFollow(FOLLOW_classCreatorRest_in_innerCreator1727);
            classCreatorRest();

            state._fsp--;
            if (state.failed) return ;

            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "innerCreator"


    // $ANTLR start "arrayCreatorRest"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:269:1: arrayCreatorRest : LEFT_SQUARE ( RIGHT_SQUARE ( LEFT_SQUARE RIGHT_SQUARE )* arrayInitializer | expression RIGHT_SQUARE ({...}? => LEFT_SQUARE expression RIGHT_SQUARE )* ( ( LEFT_SQUARE RIGHT_SQUARE )=> LEFT_SQUARE RIGHT_SQUARE )* ) ;
    public final void arrayCreatorRest() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:270:2: ( LEFT_SQUARE ( RIGHT_SQUARE ( LEFT_SQUARE RIGHT_SQUARE )* arrayInitializer | expression RIGHT_SQUARE ({...}? => LEFT_SQUARE expression RIGHT_SQUARE )* ( ( LEFT_SQUARE RIGHT_SQUARE )=> LEFT_SQUARE RIGHT_SQUARE )* ) )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:270:6: LEFT_SQUARE ( RIGHT_SQUARE ( LEFT_SQUARE RIGHT_SQUARE )* arrayInitializer | expression RIGHT_SQUARE ({...}? => LEFT_SQUARE expression RIGHT_SQUARE )* ( ( LEFT_SQUARE RIGHT_SQUARE )=> LEFT_SQUARE RIGHT_SQUARE )* )
            {
            match(input,LEFT_SQUARE,FOLLOW_LEFT_SQUARE_in_arrayCreatorRest1740); if (state.failed) return ;
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:271:2: ( RIGHT_SQUARE ( LEFT_SQUARE RIGHT_SQUARE )* arrayInitializer | expression RIGHT_SQUARE ({...}? => LEFT_SQUARE expression RIGHT_SQUARE )* ( ( LEFT_SQUARE RIGHT_SQUARE )=> LEFT_SQUARE RIGHT_SQUARE )* )
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==RIGHT_SQUARE) ) {
                alt63=1;
            }
            else if ( (LA63_0==FLOAT||(LA63_0>=HEX && LA63_0<=DECIMAL)||LA63_0==STRING||(LA63_0>=BOOL && LA63_0<=NULL)||(LA63_0>=DECR && LA63_0<=INCR)||LA63_0==LESS||LA63_0==LEFT_PAREN||LA63_0==LEFT_SQUARE||(LA63_0>=NEGATION && LA63_0<=TILDE)||(LA63_0>=MINUS && LA63_0<=PLUS)||LA63_0==ID) ) {
                alt63=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 63, 0, input);

                throw nvae;
            }
            switch (alt63) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:271:6: RIGHT_SQUARE ( LEFT_SQUARE RIGHT_SQUARE )* arrayInitializer
                    {
                    match(input,RIGHT_SQUARE,FOLLOW_RIGHT_SQUARE_in_arrayCreatorRest1748); if (state.failed) return ;
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:271:19: ( LEFT_SQUARE RIGHT_SQUARE )*
                    loop60:
                    do {
                        int alt60=2;
                        int LA60_0 = input.LA(1);

                        if ( (LA60_0==LEFT_SQUARE) ) {
                            alt60=1;
                        }


                        switch (alt60) {
                        case 1 :
                            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:271:20: LEFT_SQUARE RIGHT_SQUARE
                            {
                            match(input,LEFT_SQUARE,FOLLOW_LEFT_SQUARE_in_arrayCreatorRest1751); if (state.failed) return ;
                            match(input,RIGHT_SQUARE,FOLLOW_RIGHT_SQUARE_in_arrayCreatorRest1753); if (state.failed) return ;

                            }
                            break;

                        default :
                            break loop60;
                        }
                    } while (true);

                    pushFollow(FOLLOW_arrayInitializer_in_arrayCreatorRest1757);
                    arrayInitializer();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:272:13: expression RIGHT_SQUARE ({...}? => LEFT_SQUARE expression RIGHT_SQUARE )* ( ( LEFT_SQUARE RIGHT_SQUARE )=> LEFT_SQUARE RIGHT_SQUARE )*
                    {
                    pushFollow(FOLLOW_expression_in_arrayCreatorRest1771);
                    expression();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,RIGHT_SQUARE,FOLLOW_RIGHT_SQUARE_in_arrayCreatorRest1773); if (state.failed) return ;
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:272:37: ({...}? => LEFT_SQUARE expression RIGHT_SQUARE )*
                    loop61:
                    do {
                        int alt61=2;
                        alt61 = dfa61.predict(input);
                        switch (alt61) {
                        case 1 :
                            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:272:38: {...}? => LEFT_SQUARE expression RIGHT_SQUARE
                            {
                            if ( !((!helper.validateLT(2,"]"))) ) {
                                if (state.backtracking>0) {state.failed=true; return ;}
                                throw new FailedPredicateException(input, "arrayCreatorRest", "!helper.validateLT(2,\"]\")");
                            }
                            match(input,LEFT_SQUARE,FOLLOW_LEFT_SQUARE_in_arrayCreatorRest1778); if (state.failed) return ;
                            pushFollow(FOLLOW_expression_in_arrayCreatorRest1780);
                            expression();

                            state._fsp--;
                            if (state.failed) return ;
                            match(input,RIGHT_SQUARE,FOLLOW_RIGHT_SQUARE_in_arrayCreatorRest1782); if (state.failed) return ;

                            }
                            break;

                        default :
                            break loop61;
                        }
                    } while (true);

                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:272:106: ( ( LEFT_SQUARE RIGHT_SQUARE )=> LEFT_SQUARE RIGHT_SQUARE )*
                    loop62:
                    do {
                        int alt62=2;
                        int LA62_0 = input.LA(1);

                        if ( (LA62_0==LEFT_SQUARE) ) {
                            int LA62_2 = input.LA(2);

                            if ( (LA62_2==RIGHT_SQUARE) ) {
                                int LA62_3 = input.LA(3);

                                if ( (synpred33_DRLExpressions()) ) {
                                    alt62=1;
                                }


                            }


                        }


                        switch (alt62) {
                        case 1 :
                            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:272:107: ( LEFT_SQUARE RIGHT_SQUARE )=> LEFT_SQUARE RIGHT_SQUARE
                            {
                            match(input,LEFT_SQUARE,FOLLOW_LEFT_SQUARE_in_arrayCreatorRest1794); if (state.failed) return ;
                            match(input,RIGHT_SQUARE,FOLLOW_RIGHT_SQUARE_in_arrayCreatorRest1796); if (state.failed) return ;

                            }
                            break;

                        default :
                            break loop62;
                        }
                    } while (true);


                    }
                    break;

            }


            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "arrayCreatorRest"


    // $ANTLR start "variableInitializer"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:276:1: variableInitializer : ( arrayInitializer | expression );
    public final void variableInitializer() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:277:2: ( arrayInitializer | expression )
            int alt64=2;
            int LA64_0 = input.LA(1);

            if ( (LA64_0==LEFT_CURLY) ) {
                alt64=1;
            }
            else if ( (LA64_0==FLOAT||(LA64_0>=HEX && LA64_0<=DECIMAL)||LA64_0==STRING||(LA64_0>=BOOL && LA64_0<=NULL)||(LA64_0>=DECR && LA64_0<=INCR)||LA64_0==LESS||LA64_0==LEFT_PAREN||LA64_0==LEFT_SQUARE||(LA64_0>=NEGATION && LA64_0<=TILDE)||(LA64_0>=MINUS && LA64_0<=PLUS)||LA64_0==ID) ) {
                alt64=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 64, 0, input);

                throw nvae;
            }
            switch (alt64) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:277:4: arrayInitializer
                    {
                    pushFollow(FOLLOW_arrayInitializer_in_variableInitializer1819);
                    arrayInitializer();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:278:10: expression
                    {
                    pushFollow(FOLLOW_expression_in_variableInitializer1830);
                    expression();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "variableInitializer"


    // $ANTLR start "arrayInitializer"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:281:1: arrayInitializer : LEFT_CURLY ( variableInitializer ( COMMA variableInitializer )* ( COMMA )? )? RIGHT_CURLY ;
    public final void arrayInitializer() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:282:2: ( LEFT_CURLY ( variableInitializer ( COMMA variableInitializer )* ( COMMA )? )? RIGHT_CURLY )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:282:4: LEFT_CURLY ( variableInitializer ( COMMA variableInitializer )* ( COMMA )? )? RIGHT_CURLY
            {
            match(input,LEFT_CURLY,FOLLOW_LEFT_CURLY_in_arrayInitializer1842); if (state.failed) return ;
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:282:15: ( variableInitializer ( COMMA variableInitializer )* ( COMMA )? )?
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==FLOAT||(LA67_0>=HEX && LA67_0<=DECIMAL)||LA67_0==STRING||(LA67_0>=BOOL && LA67_0<=NULL)||(LA67_0>=DECR && LA67_0<=INCR)||LA67_0==LESS||LA67_0==LEFT_PAREN||LA67_0==LEFT_SQUARE||LA67_0==LEFT_CURLY||(LA67_0>=NEGATION && LA67_0<=TILDE)||(LA67_0>=MINUS && LA67_0<=PLUS)||LA67_0==ID) ) {
                alt67=1;
            }
            switch (alt67) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:282:16: variableInitializer ( COMMA variableInitializer )* ( COMMA )?
                    {
                    pushFollow(FOLLOW_variableInitializer_in_arrayInitializer1845);
                    variableInitializer();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:282:36: ( COMMA variableInitializer )*
                    loop65:
                    do {
                        int alt65=2;
                        int LA65_0 = input.LA(1);

                        if ( (LA65_0==COMMA) ) {
                            int LA65_1 = input.LA(2);

                            if ( (LA65_1==FLOAT||(LA65_1>=HEX && LA65_1<=DECIMAL)||LA65_1==STRING||(LA65_1>=BOOL && LA65_1<=NULL)||(LA65_1>=DECR && LA65_1<=INCR)||LA65_1==LESS||LA65_1==LEFT_PAREN||LA65_1==LEFT_SQUARE||LA65_1==LEFT_CURLY||(LA65_1>=NEGATION && LA65_1<=TILDE)||(LA65_1>=MINUS && LA65_1<=PLUS)||LA65_1==ID) ) {
                                alt65=1;
                            }


                        }


                        switch (alt65) {
                        case 1 :
                            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:282:37: COMMA variableInitializer
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_arrayInitializer1848); if (state.failed) return ;
                            pushFollow(FOLLOW_variableInitializer_in_arrayInitializer1850);
                            variableInitializer();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                        default :
                            break loop65;
                        }
                    } while (true);

                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:282:65: ( COMMA )?
                    int alt66=2;
                    int LA66_0 = input.LA(1);

                    if ( (LA66_0==COMMA) ) {
                        alt66=1;
                    }
                    switch (alt66) {
                        case 1 :
                            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:282:66: COMMA
                            {
                            match(input,COMMA,FOLLOW_COMMA_in_arrayInitializer1855); if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;

            }

            match(input,RIGHT_CURLY,FOLLOW_RIGHT_CURLY_in_arrayInitializer1862); if (state.failed) return ;

            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "arrayInitializer"


    // $ANTLR start "classCreatorRest"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:285:1: classCreatorRest : arguments ;
    public final void classCreatorRest() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:286:2: ( arguments )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:286:4: arguments
            {
            pushFollow(FOLLOW_arguments_in_classCreatorRest1873);
            arguments();

            state._fsp--;
            if (state.failed) return ;

            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "classCreatorRest"


    // $ANTLR start "explicitGenericInvocation"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:289:1: explicitGenericInvocation : nonWildcardTypeArguments arguments ;
    public final void explicitGenericInvocation() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:290:2: ( nonWildcardTypeArguments arguments )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:290:4: nonWildcardTypeArguments arguments
            {
            pushFollow(FOLLOW_nonWildcardTypeArguments_in_explicitGenericInvocation1886);
            nonWildcardTypeArguments();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_arguments_in_explicitGenericInvocation1888);
            arguments();

            state._fsp--;
            if (state.failed) return ;

            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "explicitGenericInvocation"


    // $ANTLR start "nonWildcardTypeArguments"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:293:1: nonWildcardTypeArguments : LESS typeList GREATER ;
    public final void nonWildcardTypeArguments() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:294:2: ( LESS typeList GREATER )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:294:4: LESS typeList GREATER
            {
            match(input,LESS,FOLLOW_LESS_in_nonWildcardTypeArguments1900); if (state.failed) return ;
            pushFollow(FOLLOW_typeList_in_nonWildcardTypeArguments1902);
            typeList();

            state._fsp--;
            if (state.failed) return ;
            match(input,GREATER,FOLLOW_GREATER_in_nonWildcardTypeArguments1904); if (state.failed) return ;

            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "nonWildcardTypeArguments"


    // $ANTLR start "explicitGenericInvocationSuffix"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:297:1: explicitGenericInvocationSuffix : ( super_key superSuffix | ID arguments );
    public final void explicitGenericInvocationSuffix() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:298:2: ( super_key superSuffix | ID arguments )
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( (LA68_0==ID) ) {
                int LA68_1 = input.LA(2);

                if ( (((helper.validateIdentifierKey(DroolsSoftKeywords.SUPER)))) ) {
                    alt68=1;
                }
                else if ( (true) ) {
                    alt68=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 68, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 68, 0, input);

                throw nvae;
            }
            switch (alt68) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:298:4: super_key superSuffix
                    {
                    pushFollow(FOLLOW_super_key_in_explicitGenericInvocationSuffix1916);
                    super_key();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_superSuffix_in_explicitGenericInvocationSuffix1918);
                    superSuffix();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:299:7: ID arguments
                    {
                    match(input,ID,FOLLOW_ID_in_explicitGenericInvocationSuffix1926); if (state.failed) return ;
                    pushFollow(FOLLOW_arguments_in_explicitGenericInvocationSuffix1928);
                    arguments();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "explicitGenericInvocationSuffix"


    // $ANTLR start "selector"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:302:1: selector : ( ( DOT super_key )=> DOT super_key superSuffix | ( DOT new_key )=> DOT new_key ( nonWildcardTypeArguments )? innerCreator | ( DOT ID )=> DOT ID ( ( LEFT_PAREN )=> arguments )? | ( LEFT_SQUARE )=> LEFT_SQUARE expression RIGHT_SQUARE );
    public final void selector() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:303:2: ( ( DOT super_key )=> DOT super_key superSuffix | ( DOT new_key )=> DOT new_key ( nonWildcardTypeArguments )? innerCreator | ( DOT ID )=> DOT ID ( ( LEFT_PAREN )=> arguments )? | ( LEFT_SQUARE )=> LEFT_SQUARE expression RIGHT_SQUARE )
            int alt71=4;
            int LA71_0 = input.LA(1);

            if ( (LA71_0==DOT) ) {
                int LA71_1 = input.LA(2);

                if ( (synpred34_DRLExpressions()) ) {
                    alt71=1;
                }
                else if ( (synpred35_DRLExpressions()) ) {
                    alt71=2;
                }
                else if ( (synpred36_DRLExpressions()) ) {
                    alt71=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 71, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA71_0==LEFT_SQUARE) && (synpred38_DRLExpressions())) {
                alt71=4;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 71, 0, input);

                throw nvae;
            }
            switch (alt71) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:303:6: ( DOT super_key )=> DOT super_key superSuffix
                    {
                    match(input,DOT,FOLLOW_DOT_in_selector1947); if (state.failed) return ;
                    pushFollow(FOLLOW_super_key_in_selector1949);
                    super_key();

                    state._fsp--;
                    if (state.failed) return ;
                    pushFollow(FOLLOW_superSuffix_in_selector1951);
                    superSuffix();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:304:6: ( DOT new_key )=> DOT new_key ( nonWildcardTypeArguments )? innerCreator
                    {
                    match(input,DOT,FOLLOW_DOT_in_selector1964); if (state.failed) return ;
                    pushFollow(FOLLOW_new_key_in_selector1966);
                    new_key();

                    state._fsp--;
                    if (state.failed) return ;
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:304:33: ( nonWildcardTypeArguments )?
                    int alt69=2;
                    int LA69_0 = input.LA(1);

                    if ( (LA69_0==LESS) ) {
                        alt69=1;
                    }
                    switch (alt69) {
                        case 1 :
                            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:304:34: nonWildcardTypeArguments
                            {
                            pushFollow(FOLLOW_nonWildcardTypeArguments_in_selector1969);
                            nonWildcardTypeArguments();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    pushFollow(FOLLOW_innerCreator_in_selector1973);
                    innerCreator();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:305:6: ( DOT ID )=> DOT ID ( ( LEFT_PAREN )=> arguments )?
                    {
                    match(input,DOT,FOLLOW_DOT_in_selector1986); if (state.failed) return ;
                    match(input,ID,FOLLOW_ID_in_selector1988); if (state.failed) return ;
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:305:23: ( ( LEFT_PAREN )=> arguments )?
                    int alt70=2;
                    alt70 = dfa70.predict(input);
                    switch (alt70) {
                        case 1 :
                            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:305:24: ( LEFT_PAREN )=> arguments
                            {
                            pushFollow(FOLLOW_arguments_in_selector1997);
                            arguments();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;
                case 4 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:307:6: ( LEFT_SQUARE )=> LEFT_SQUARE expression RIGHT_SQUARE
                    {
                    match(input,LEFT_SQUARE,FOLLOW_LEFT_SQUARE_in_selector2012); if (state.failed) return ;
                    pushFollow(FOLLOW_expression_in_selector2014);
                    expression();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,RIGHT_SQUARE,FOLLOW_RIGHT_SQUARE_in_selector2016); if (state.failed) return ;

                    }
                    break;

            }
        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "selector"


    // $ANTLR start "superSuffix"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:310:1: superSuffix : ( arguments | DOT ID ( ( LEFT_PAREN )=> arguments )? );
    public final void superSuffix() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:311:2: ( arguments | DOT ID ( ( LEFT_PAREN )=> arguments )? )
            int alt73=2;
            int LA73_0 = input.LA(1);

            if ( (LA73_0==LEFT_PAREN) ) {
                alt73=1;
            }
            else if ( (LA73_0==DOT) ) {
                alt73=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 73, 0, input);

                throw nvae;
            }
            switch (alt73) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:311:4: arguments
                    {
                    pushFollow(FOLLOW_arguments_in_superSuffix2028);
                    arguments();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:312:7: DOT ID ( ( LEFT_PAREN )=> arguments )?
                    {
                    match(input,DOT,FOLLOW_DOT_in_superSuffix2036); if (state.failed) return ;
                    match(input,ID,FOLLOW_ID_in_superSuffix2038); if (state.failed) return ;
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:312:14: ( ( LEFT_PAREN )=> arguments )?
                    int alt72=2;
                    alt72 = dfa72.predict(input);
                    switch (alt72) {
                        case 1 :
                            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:312:15: ( LEFT_PAREN )=> arguments
                            {
                            pushFollow(FOLLOW_arguments_in_superSuffix2047);
                            arguments();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;

            }
        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "superSuffix"


    // $ANTLR start "arguments"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:315:1: arguments : LEFT_PAREN ( expressionList )? RIGHT_PAREN ;
    public final void arguments() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:316:2: ( LEFT_PAREN ( expressionList )? RIGHT_PAREN )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:316:4: LEFT_PAREN ( expressionList )? RIGHT_PAREN
            {
            match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_arguments2067); if (state.failed) return ;
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:316:15: ( expressionList )?
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( (LA74_0==FLOAT||(LA74_0>=HEX && LA74_0<=DECIMAL)||LA74_0==STRING||(LA74_0>=BOOL && LA74_0<=NULL)||(LA74_0>=DECR && LA74_0<=INCR)||LA74_0==LESS||LA74_0==LEFT_PAREN||LA74_0==LEFT_SQUARE||(LA74_0>=NEGATION && LA74_0<=TILDE)||(LA74_0>=MINUS && LA74_0<=PLUS)||LA74_0==ID) ) {
                alt74=1;
            }
            switch (alt74) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:316:15: expressionList
                    {
                    pushFollow(FOLLOW_expressionList_in_arguments2069);
                    expressionList();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_arguments2072); if (state.failed) return ;

            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "arguments"


    // $ANTLR start "expressionList"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:319:1: expressionList : expression ( COMMA expression )* ;
    public final void expressionList() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:320:3: ( expression ( COMMA expression )* )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:320:7: expression ( COMMA expression )*
            {
            pushFollow(FOLLOW_expression_in_expressionList2086);
            expression();

            state._fsp--;
            if (state.failed) return ;
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:320:18: ( COMMA expression )*
            loop75:
            do {
                int alt75=2;
                int LA75_0 = input.LA(1);

                if ( (LA75_0==COMMA) ) {
                    alt75=1;
                }


                switch (alt75) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:320:19: COMMA expression
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_expressionList2089); if (state.failed) return ;
                    pushFollow(FOLLOW_expression_in_expressionList2091);
                    expression();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

                default :
                    break loop75;
                }
            } while (true);


            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "expressionList"


    // $ANTLR start "assignmentOperator"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:323:1: assignmentOperator : ( EQUALS_ASSIGN | PLUS_ASSIGN | MINUS_ASSIGN | MULT_ASSIGN | DIV_ASSIGN | AND_ASSIGN | OR_ASSIGN | XOR_ASSIGN | MOD_ASSIGN | LESS LESS EQUALS_ASSIGN | ( GREATER GREATER GREATER )=> GREATER GREATER GREATER EQUALS_ASSIGN | ( GREATER GREATER )=> GREATER GREATER EQUALS_ASSIGN );
    public final void assignmentOperator() throws RecognitionException {
        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:324:2: ( EQUALS_ASSIGN | PLUS_ASSIGN | MINUS_ASSIGN | MULT_ASSIGN | DIV_ASSIGN | AND_ASSIGN | OR_ASSIGN | XOR_ASSIGN | MOD_ASSIGN | LESS LESS EQUALS_ASSIGN | ( GREATER GREATER GREATER )=> GREATER GREATER GREATER EQUALS_ASSIGN | ( GREATER GREATER )=> GREATER GREATER EQUALS_ASSIGN )
            int alt76=12;
            alt76 = dfa76.predict(input);
            switch (alt76) {
                case 1 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:324:6: EQUALS_ASSIGN
                    {
                    match(input,EQUALS_ASSIGN,FOLLOW_EQUALS_ASSIGN_in_assignmentOperator2107); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:325:7: PLUS_ASSIGN
                    {
                    match(input,PLUS_ASSIGN,FOLLOW_PLUS_ASSIGN_in_assignmentOperator2115); if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:326:7: MINUS_ASSIGN
                    {
                    match(input,MINUS_ASSIGN,FOLLOW_MINUS_ASSIGN_in_assignmentOperator2123); if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:327:7: MULT_ASSIGN
                    {
                    match(input,MULT_ASSIGN,FOLLOW_MULT_ASSIGN_in_assignmentOperator2131); if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:328:7: DIV_ASSIGN
                    {
                    match(input,DIV_ASSIGN,FOLLOW_DIV_ASSIGN_in_assignmentOperator2139); if (state.failed) return ;

                    }
                    break;
                case 6 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:329:7: AND_ASSIGN
                    {
                    match(input,AND_ASSIGN,FOLLOW_AND_ASSIGN_in_assignmentOperator2147); if (state.failed) return ;

                    }
                    break;
                case 7 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:330:7: OR_ASSIGN
                    {
                    match(input,OR_ASSIGN,FOLLOW_OR_ASSIGN_in_assignmentOperator2155); if (state.failed) return ;

                    }
                    break;
                case 8 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:331:7: XOR_ASSIGN
                    {
                    match(input,XOR_ASSIGN,FOLLOW_XOR_ASSIGN_in_assignmentOperator2163); if (state.failed) return ;

                    }
                    break;
                case 9 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:332:7: MOD_ASSIGN
                    {
                    match(input,MOD_ASSIGN,FOLLOW_MOD_ASSIGN_in_assignmentOperator2171); if (state.failed) return ;

                    }
                    break;
                case 10 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:333:7: LESS LESS EQUALS_ASSIGN
                    {
                    match(input,LESS,FOLLOW_LESS_in_assignmentOperator2179); if (state.failed) return ;
                    match(input,LESS,FOLLOW_LESS_in_assignmentOperator2181); if (state.failed) return ;
                    match(input,EQUALS_ASSIGN,FOLLOW_EQUALS_ASSIGN_in_assignmentOperator2183); if (state.failed) return ;

                    }
                    break;
                case 11 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:334:7: ( GREATER GREATER GREATER )=> GREATER GREATER GREATER EQUALS_ASSIGN
                    {
                    match(input,GREATER,FOLLOW_GREATER_in_assignmentOperator2200); if (state.failed) return ;
                    match(input,GREATER,FOLLOW_GREATER_in_assignmentOperator2202); if (state.failed) return ;
                    match(input,GREATER,FOLLOW_GREATER_in_assignmentOperator2204); if (state.failed) return ;
                    match(input,EQUALS_ASSIGN,FOLLOW_EQUALS_ASSIGN_in_assignmentOperator2206); if (state.failed) return ;

                    }
                    break;
                case 12 :
                    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:335:7: ( GREATER GREATER )=> GREATER GREATER EQUALS_ASSIGN
                    {
                    match(input,GREATER,FOLLOW_GREATER_in_assignmentOperator2221); if (state.failed) return ;
                    match(input,GREATER,FOLLOW_GREATER_in_assignmentOperator2223); if (state.failed) return ;
                    match(input,EQUALS_ASSIGN,FOLLOW_EQUALS_ASSIGN_in_assignmentOperator2225); if (state.failed) return ;

                    }
                    break;

            }
        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "assignmentOperator"


    // $ANTLR start "extends_key"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:341:1: extends_key : {...}? =>id= ID ;
    public final void extends_key() throws RecognitionException {
        Token id=null;

        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:342:2: ({...}? =>id= ID )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:342:9: {...}? =>id= ID
            {
            if ( !(((helper.validateIdentifierKey(DroolsSoftKeywords.EXTENDS)))) ) {
                if (state.backtracking>0) {state.failed=true; return ;}
                throw new FailedPredicateException(input, "extends_key", "(helper.validateIdentifierKey(DroolsSoftKeywords.EXTENDS))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_extends_key2249); if (state.failed) return ;

            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "extends_key"


    // $ANTLR start "super_key"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:345:1: super_key : {...}? =>id= ID ;
    public final void super_key() throws RecognitionException {
        Token id=null;

        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:346:2: ({...}? =>id= ID )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:346:9: {...}? =>id= ID
            {
            if ( !(((helper.validateIdentifierKey(DroolsSoftKeywords.SUPER)))) ) {
                if (state.backtracking>0) {state.failed=true; return ;}
                throw new FailedPredicateException(input, "super_key", "(helper.validateIdentifierKey(DroolsSoftKeywords.SUPER))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_super_key2270); if (state.failed) return ;

            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "super_key"


    // $ANTLR start "instanceof_key"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:349:1: instanceof_key : {...}? =>id= ID ;
    public final void instanceof_key() throws RecognitionException {
        Token id=null;

        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:350:2: ({...}? =>id= ID )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:350:9: {...}? =>id= ID
            {
            if ( !(((helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF)))) ) {
                if (state.backtracking>0) {state.failed=true; return ;}
                throw new FailedPredicateException(input, "instanceof_key", "(helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_instanceof_key2291); if (state.failed) return ;

            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "instanceof_key"


    // $ANTLR start "boolean_key"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:353:1: boolean_key : {...}? =>id= ID ;
    public final void boolean_key() throws RecognitionException {
        Token id=null;

        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:354:2: ({...}? =>id= ID )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:354:9: {...}? =>id= ID
            {
            if ( !(((helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF)))) ) {
                if (state.backtracking>0) {state.failed=true; return ;}
                throw new FailedPredicateException(input, "boolean_key", "(helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_boolean_key2312); if (state.failed) return ;

            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "boolean_key"


    // $ANTLR start "char_key"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:357:1: char_key : {...}? =>id= ID ;
    public final void char_key() throws RecognitionException {
        Token id=null;

        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:358:2: ({...}? =>id= ID )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:358:9: {...}? =>id= ID
            {
            if ( !(((helper.validateIdentifierKey(DroolsSoftKeywords.CHAR)))) ) {
                if (state.backtracking>0) {state.failed=true; return ;}
                throw new FailedPredicateException(input, "char_key", "(helper.validateIdentifierKey(DroolsSoftKeywords.CHAR))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_char_key2333); if (state.failed) return ;

            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "char_key"


    // $ANTLR start "byte_key"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:361:1: byte_key : {...}? =>id= ID ;
    public final void byte_key() throws RecognitionException {
        Token id=null;

        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:362:2: ({...}? =>id= ID )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:362:9: {...}? =>id= ID
            {
            if ( !(((helper.validateIdentifierKey(DroolsSoftKeywords.BYTE)))) ) {
                if (state.backtracking>0) {state.failed=true; return ;}
                throw new FailedPredicateException(input, "byte_key", "(helper.validateIdentifierKey(DroolsSoftKeywords.BYTE))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_byte_key2354); if (state.failed) return ;

            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "byte_key"


    // $ANTLR start "short_key"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:365:1: short_key : {...}? =>id= ID ;
    public final void short_key() throws RecognitionException {
        Token id=null;

        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:366:2: ({...}? =>id= ID )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:366:9: {...}? =>id= ID
            {
            if ( !(((helper.validateIdentifierKey(DroolsSoftKeywords.SHORT)))) ) {
                if (state.backtracking>0) {state.failed=true; return ;}
                throw new FailedPredicateException(input, "short_key", "(helper.validateIdentifierKey(DroolsSoftKeywords.SHORT))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_short_key2375); if (state.failed) return ;

            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "short_key"


    // $ANTLR start "int_key"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:369:1: int_key : {...}? =>id= ID ;
    public final void int_key() throws RecognitionException {
        Token id=null;

        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:370:2: ({...}? =>id= ID )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:370:9: {...}? =>id= ID
            {
            if ( !(((helper.validateIdentifierKey(DroolsSoftKeywords.INT)))) ) {
                if (state.backtracking>0) {state.failed=true; return ;}
                throw new FailedPredicateException(input, "int_key", "(helper.validateIdentifierKey(DroolsSoftKeywords.INT))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_int_key2396); if (state.failed) return ;

            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "int_key"


    // $ANTLR start "float_key"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:373:1: float_key : {...}? =>id= ID ;
    public final void float_key() throws RecognitionException {
        Token id=null;

        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:374:2: ({...}? =>id= ID )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:374:9: {...}? =>id= ID
            {
            if ( !(((helper.validateIdentifierKey(DroolsSoftKeywords.FLOAT)))) ) {
                if (state.backtracking>0) {state.failed=true; return ;}
                throw new FailedPredicateException(input, "float_key", "(helper.validateIdentifierKey(DroolsSoftKeywords.FLOAT))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_float_key2417); if (state.failed) return ;

            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "float_key"


    // $ANTLR start "long_key"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:377:1: long_key : {...}? =>id= ID ;
    public final void long_key() throws RecognitionException {
        Token id=null;

        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:378:2: ({...}? =>id= ID )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:378:9: {...}? =>id= ID
            {
            if ( !(((helper.validateIdentifierKey(DroolsSoftKeywords.LONG)))) ) {
                if (state.backtracking>0) {state.failed=true; return ;}
                throw new FailedPredicateException(input, "long_key", "(helper.validateIdentifierKey(DroolsSoftKeywords.LONG))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_long_key2438); if (state.failed) return ;

            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "long_key"


    // $ANTLR start "double_key"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:381:1: double_key : {...}? =>id= ID ;
    public final void double_key() throws RecognitionException {
        Token id=null;

        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:382:2: ({...}? =>id= ID )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:382:9: {...}? =>id= ID
            {
            if ( !(((helper.validateIdentifierKey(DroolsSoftKeywords.DOUBLE)))) ) {
                if (state.backtracking>0) {state.failed=true; return ;}
                throw new FailedPredicateException(input, "double_key", "(helper.validateIdentifierKey(DroolsSoftKeywords.DOUBLE))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_double_key2459); if (state.failed) return ;

            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "double_key"


    // $ANTLR start "void_key"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:385:1: void_key : {...}? =>id= ID ;
    public final void void_key() throws RecognitionException {
        Token id=null;

        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:386:2: ({...}? =>id= ID )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:386:9: {...}? =>id= ID
            {
            if ( !(((helper.validateIdentifierKey(DroolsSoftKeywords.VOID)))) ) {
                if (state.backtracking>0) {state.failed=true; return ;}
                throw new FailedPredicateException(input, "void_key", "(helper.validateIdentifierKey(DroolsSoftKeywords.VOID))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_void_key2480); if (state.failed) return ;

            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "void_key"


    // $ANTLR start "this_key"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:389:1: this_key : {...}? =>id= ID ;
    public final void this_key() throws RecognitionException {
        Token id=null;

        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:390:2: ({...}? =>id= ID )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:390:9: {...}? =>id= ID
            {
            if ( !(((helper.validateIdentifierKey(DroolsSoftKeywords.THIS)))) ) {
                if (state.backtracking>0) {state.failed=true; return ;}
                throw new FailedPredicateException(input, "this_key", "(helper.validateIdentifierKey(DroolsSoftKeywords.THIS))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_this_key2501); if (state.failed) return ;

            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "this_key"


    // $ANTLR start "class_key"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:393:1: class_key : {...}? =>id= ID ;
    public final void class_key() throws RecognitionException {
        Token id=null;

        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:394:2: ({...}? =>id= ID )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:394:9: {...}? =>id= ID
            {
            if ( !(((helper.validateIdentifierKey(DroolsSoftKeywords.CLASS)))) ) {
                if (state.backtracking>0) {state.failed=true; return ;}
                throw new FailedPredicateException(input, "class_key", "(helper.validateIdentifierKey(DroolsSoftKeywords.CLASS))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_class_key2522); if (state.failed) return ;

            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "class_key"


    // $ANTLR start "new_key"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:397:1: new_key : {...}? =>id= ID ;
    public final void new_key() throws RecognitionException {
        Token id=null;

        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:398:2: ({...}? =>id= ID )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:398:9: {...}? =>id= ID
            {
            if ( !(((helper.validateIdentifierKey(DroolsSoftKeywords.NEW)))) ) {
                if (state.backtracking>0) {state.failed=true; return ;}
                throw new FailedPredicateException(input, "new_key", "(helper.validateIdentifierKey(DroolsSoftKeywords.NEW))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_new_key2543); if (state.failed) return ;

            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "new_key"


    // $ANTLR start "not_key"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:401:1: not_key : {...}? =>id= ID ;
    public final void not_key() throws RecognitionException {
        Token id=null;

        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:402:2: ({...}? =>id= ID )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:402:9: {...}? =>id= ID
            {
            if ( !(((helper.validateIdentifierKey(DroolsSoftKeywords.NOT)))) ) {
                if (state.backtracking>0) {state.failed=true; return ;}
                throw new FailedPredicateException(input, "not_key", "(helper.validateIdentifierKey(DroolsSoftKeywords.NOT))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_not_key2564); if (state.failed) return ;

            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "not_key"


    // $ANTLR start "in_key"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:405:1: in_key : {...}? =>id= ID ;
    public final void in_key() throws RecognitionException {
        Token id=null;

        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:406:3: ({...}? =>id= ID )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:406:10: {...}? =>id= ID
            {
            if ( !(((helper.validateIdentifierKey(DroolsSoftKeywords.IN)))) ) {
                if (state.backtracking>0) {state.failed=true; return ;}
                throw new FailedPredicateException(input, "in_key", "(helper.validateIdentifierKey(DroolsSoftKeywords.IN))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_in_key2586); if (state.failed) return ;

            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "in_key"


    // $ANTLR start "operator_key"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:409:1: operator_key : {...}? =>id= ID ;
    public final void operator_key() throws RecognitionException {
        Token id=null;

        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:410:3: ({...}? =>id= ID )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:410:10: {...}? =>id= ID
            {
            if ( !(((helper.isPluggableEvaluator(false)))) ) {
                if (state.backtracking>0) {state.failed=true; return ;}
                throw new FailedPredicateException(input, "operator_key", "(helper.isPluggableEvaluator(false))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_operator_key2609); if (state.failed) return ;

            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "operator_key"


    // $ANTLR start "neg_operator_key"
    // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:413:1: neg_operator_key : {...}? =>id= ID ;
    public final void neg_operator_key() throws RecognitionException {
        Token id=null;

        try {
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:414:3: ({...}? =>id= ID )
            // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:414:10: {...}? =>id= ID
            {
            if ( !(((helper.isPluggableEvaluator(true)))) ) {
                if (state.backtracking>0) {state.failed=true; return ;}
                throw new FailedPredicateException(input, "neg_operator_key", "(helper.isPluggableEvaluator(true))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_neg_operator_key2632); if (state.failed) return ;

            }

        }

        catch (RecognitionException re) {
            throw re;
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "neg_operator_key"

    // $ANTLR start synpred1_DRLExpressions
    public final void synpred1_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:68:5: ( primitiveType )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:68:6: primitiveType
        {
        pushFollow(FOLLOW_primitiveType_in_synpred1_DRLExpressions174);
        primitiveType();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred1_DRLExpressions

    // $ANTLR start synpred2_DRLExpressions
    public final void synpred2_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:68:41: ( LEFT_SQUARE RIGHT_SQUARE )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:68:42: LEFT_SQUARE RIGHT_SQUARE
        {
        match(input,LEFT_SQUARE,FOLLOW_LEFT_SQUARE_in_synpred2_DRLExpressions185); if (state.failed) return ;
        match(input,RIGHT_SQUARE,FOLLOW_RIGHT_SQUARE_in_synpred2_DRLExpressions187); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred2_DRLExpressions

    // $ANTLR start synpred3_DRLExpressions
    public final void synpred3_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:69:10: ( typeArguments )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:69:11: typeArguments
        {
        pushFollow(FOLLOW_typeArguments_in_synpred3_DRLExpressions208);
        typeArguments();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred3_DRLExpressions

    // $ANTLR start synpred4_DRLExpressions
    public final void synpred4_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:69:52: ( typeArguments )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:69:53: typeArguments
        {
        pushFollow(FOLLOW_typeArguments_in_synpred4_DRLExpressions222);
        typeArguments();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred4_DRLExpressions

    // $ANTLR start synpred5_DRLExpressions
    public final void synpred5_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:69:89: ( LEFT_SQUARE RIGHT_SQUARE )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:69:90: LEFT_SQUARE RIGHT_SQUARE
        {
        match(input,LEFT_SQUARE,FOLLOW_LEFT_SQUARE_in_synpred5_DRLExpressions234); if (state.failed) return ;
        match(input,RIGHT_SQUARE,FOLLOW_RIGHT_SQUARE_in_synpred5_DRLExpressions236); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred5_DRLExpressions

    // $ANTLR start synpred6_DRLExpressions
    public final void synpred6_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:92:27: ( assignmentOperator )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:92:28: assignmentOperator
        {
        pushFollow(FOLLOW_assignmentOperator_in_synpred6_DRLExpressions360);
        assignmentOperator();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred6_DRLExpressions

    // $ANTLR start synpred7_DRLExpressions
    public final void synpred7_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:101:8: ( operator )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:101:9: operator
        {
        pushFollow(FOLLOW_operator_in_synpred7_DRLExpressions423);
        operator();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred7_DRLExpressions

    // $ANTLR start synpred8_DRLExpressions
    public final void synpred8_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:107:7: ( operator )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:107:8: operator
        {
        pushFollow(FOLLOW_operator_in_synpred8_DRLExpressions470);
        operator();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred8_DRLExpressions

    // $ANTLR start synpred9_DRLExpressions
    public final void synpred9_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:113:7: ( operator )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:113:8: operator
        {
        pushFollow(FOLLOW_operator_in_synpred9_DRLExpressions514);
        operator();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred9_DRLExpressions

    // $ANTLR start synpred10_DRLExpressions
    public final void synpred10_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:119:7: ( operator )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:119:8: operator
        {
        pushFollow(FOLLOW_operator_in_synpred10_DRLExpressions559);
        operator();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred10_DRLExpressions

    // $ANTLR start synpred11_DRLExpressions
    public final void synpred11_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:125:7: ( operator )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:125:8: operator
        {
        pushFollow(FOLLOW_operator_in_synpred11_DRLExpressions605);
        operator();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred11_DRLExpressions

    // $ANTLR start synpred12_DRLExpressions
    public final void synpred12_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:142:23: ( relationalOp )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:142:24: relationalOp
        {
        pushFollow(FOLLOW_relationalOp_in_synpred12_DRLExpressions727);
        relationalOp();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred12_DRLExpressions

    // $ANTLR start synpred13_DRLExpressions
    public final void synpred13_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:163:26: ( shiftOp )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:163:27: shiftOp
        {
        pushFollow(FOLLOW_shiftOp_in_synpred13_DRLExpressions861);
        shiftOp();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred13_DRLExpressions

    // $ANTLR start synpred14_DRLExpressions
    public final void synpred14_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:171:34: ( PLUS | MINUS )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:
        {
        if ( (input.LA(1)>=MINUS && input.LA(1)<=PLUS) ) {
            input.consume();
            state.errorRecovery=false;state.failed=false;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            MismatchedSetException mse = new MismatchedSetException(null,input);
            throw mse;
        }


        }
    }
    // $ANTLR end synpred14_DRLExpressions

    // $ANTLR start synpred15_DRLExpressions
    public final void synpred15_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:189:9: ( castExpression )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:189:10: castExpression
        {
        pushFollow(FOLLOW_castExpression_in_synpred15_DRLExpressions1076);
        castExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred15_DRLExpressions

    // $ANTLR start synpred16_DRLExpressions
    public final void synpred16_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:190:18: ( selector )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:190:19: selector
        {
        pushFollow(FOLLOW_selector_in_synpred16_DRLExpressions1093);
        selector();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred16_DRLExpressions

    // $ANTLR start synpred17_DRLExpressions
    public final void synpred17_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:190:42: ( INCR | DECR )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:
        {
        if ( (input.LA(1)>=DECR && input.LA(1)<=INCR) ) {
            input.consume();
            state.errorRecovery=false;state.failed=false;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            MismatchedSetException mse = new MismatchedSetException(null,input);
            throw mse;
        }


        }
    }
    // $ANTLR end synpred17_DRLExpressions

    // $ANTLR start synpred18_DRLExpressions
    public final void synpred18_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:194:8: ( LEFT_PAREN primitiveType )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:194:9: LEFT_PAREN primitiveType
        {
        match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_synpred18_DRLExpressions1137); if (state.failed) return ;
        pushFollow(FOLLOW_primitiveType_in_synpred18_DRLExpressions1139);
        primitiveType();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred18_DRLExpressions

    // $ANTLR start synpred19_DRLExpressions
    public final void synpred19_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:195:8: ( LEFT_PAREN type )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:195:9: LEFT_PAREN type
        {
        match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_synpred19_DRLExpressions1160); if (state.failed) return ;
        pushFollow(FOLLOW_type_in_synpred19_DRLExpressions1162);
        type();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred19_DRLExpressions

    // $ANTLR start synpred20_DRLExpressions
    public final void synpred20_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:210:7: ( parExpression )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:210:8: parExpression
        {
        pushFollow(FOLLOW_parExpression_in_synpred20_DRLExpressions1268);
        parExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred20_DRLExpressions

    // $ANTLR start synpred21_DRLExpressions
    public final void synpred21_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:211:9: ( nonWildcardTypeArguments )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:211:10: nonWildcardTypeArguments
        {
        pushFollow(FOLLOW_nonWildcardTypeArguments_in_synpred21_DRLExpressions1283);
        nonWildcardTypeArguments();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred21_DRLExpressions

    // $ANTLR start synpred22_DRLExpressions
    public final void synpred22_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:212:9: ( literal )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:212:10: literal
        {
        pushFollow(FOLLOW_literal_in_synpred22_DRLExpressions1308);
        literal();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred22_DRLExpressions

    // $ANTLR start synpred23_DRLExpressions
    public final void synpred23_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:214:9: ( super_key )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:214:10: super_key
        {
        pushFollow(FOLLOW_super_key_in_synpred23_DRLExpressions1328);
        super_key();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred23_DRLExpressions

    // $ANTLR start synpred24_DRLExpressions
    public final void synpred24_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:215:9: ( new_key )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:215:10: new_key
        {
        pushFollow(FOLLOW_new_key_in_synpred24_DRLExpressions1345);
        new_key();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred24_DRLExpressions

    // $ANTLR start synpred25_DRLExpressions
    public final void synpred25_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:216:9: ( primitiveType )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:216:10: primitiveType
        {
        pushFollow(FOLLOW_primitiveType_in_synpred25_DRLExpressions1362);
        primitiveType();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred25_DRLExpressions

    // $ANTLR start synpred26_DRLExpressions
    public final void synpred26_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:218:9: ( inlineMapExpression )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:218:10: inlineMapExpression
        {
        pushFollow(FOLLOW_inlineMapExpression_in_synpred26_DRLExpressions1393);
        inlineMapExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred26_DRLExpressions

    // $ANTLR start synpred27_DRLExpressions
    public final void synpred27_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:219:9: ( inlineListExpression )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:219:10: inlineListExpression
        {
        pushFollow(FOLLOW_inlineListExpression_in_synpred27_DRLExpressions1408);
        inlineListExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred27_DRLExpressions

    // $ANTLR start synpred28_DRLExpressions
    public final void synpred28_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:220:9: ( ID )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:220:10: ID
        {
        match(input,ID,FOLLOW_ID_in_synpred28_DRLExpressions1423); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred28_DRLExpressions

    // $ANTLR start synpred29_DRLExpressions
    public final void synpred29_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:220:19: ( DOT ID )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:220:20: DOT ID
        {
        match(input,DOT,FOLLOW_DOT_in_synpred29_DRLExpressions1430); if (state.failed) return ;
        match(input,ID,FOLLOW_ID_in_synpred29_DRLExpressions1432); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred29_DRLExpressions

    // $ANTLR start synpred30_DRLExpressions
    public final void synpred30_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:220:39: ( identifierSuffix )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:220:40: identifierSuffix
        {
        pushFollow(FOLLOW_identifierSuffix_in_synpred30_DRLExpressions1443);
        identifierSuffix();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred30_DRLExpressions

    // $ANTLR start synpred31_DRLExpressions
    public final void synpred31_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:244:7: ( LEFT_SQUARE RIGHT_SQUARE )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:244:8: LEFT_SQUARE RIGHT_SQUARE
        {
        match(input,LEFT_SQUARE,FOLLOW_LEFT_SQUARE_in_synpred31_DRLExpressions1582); if (state.failed) return ;
        match(input,RIGHT_SQUARE,FOLLOW_RIGHT_SQUARE_in_synpred31_DRLExpressions1584); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred31_DRLExpressions

    // $ANTLR start synpred32_DRLExpressions
    public final void synpred32_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:245:8: ( LEFT_SQUARE )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:245:9: LEFT_SQUARE
        {
        match(input,LEFT_SQUARE,FOLLOW_LEFT_SQUARE_in_synpred32_DRLExpressions1606); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred32_DRLExpressions

    // $ANTLR start synpred33_DRLExpressions
    public final void synpred33_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:272:107: ( LEFT_SQUARE RIGHT_SQUARE )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:272:108: LEFT_SQUARE RIGHT_SQUARE
        {
        match(input,LEFT_SQUARE,FOLLOW_LEFT_SQUARE_in_synpred33_DRLExpressions1788); if (state.failed) return ;
        match(input,RIGHT_SQUARE,FOLLOW_RIGHT_SQUARE_in_synpred33_DRLExpressions1790); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred33_DRLExpressions

    // $ANTLR start synpred34_DRLExpressions
    public final void synpred34_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:303:6: ( DOT super_key )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:303:7: DOT super_key
        {
        match(input,DOT,FOLLOW_DOT_in_synpred34_DRLExpressions1942); if (state.failed) return ;
        pushFollow(FOLLOW_super_key_in_synpred34_DRLExpressions1944);
        super_key();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred34_DRLExpressions

    // $ANTLR start synpred35_DRLExpressions
    public final void synpred35_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:304:6: ( DOT new_key )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:304:7: DOT new_key
        {
        match(input,DOT,FOLLOW_DOT_in_synpred35_DRLExpressions1959); if (state.failed) return ;
        pushFollow(FOLLOW_new_key_in_synpred35_DRLExpressions1961);
        new_key();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred35_DRLExpressions

    // $ANTLR start synpred36_DRLExpressions
    public final void synpred36_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:305:6: ( DOT ID )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:305:7: DOT ID
        {
        match(input,DOT,FOLLOW_DOT_in_synpred36_DRLExpressions1981); if (state.failed) return ;
        match(input,ID,FOLLOW_ID_in_synpred36_DRLExpressions1983); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred36_DRLExpressions

    // $ANTLR start synpred37_DRLExpressions
    public final void synpred37_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:305:24: ( LEFT_PAREN )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:305:25: LEFT_PAREN
        {
        match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_synpred37_DRLExpressions1992); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred37_DRLExpressions

    // $ANTLR start synpred38_DRLExpressions
    public final void synpred38_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:307:6: ( LEFT_SQUARE )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:307:7: LEFT_SQUARE
        {
        match(input,LEFT_SQUARE,FOLLOW_LEFT_SQUARE_in_synpred38_DRLExpressions2009); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred38_DRLExpressions

    // $ANTLR start synpred39_DRLExpressions
    public final void synpred39_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:312:15: ( LEFT_PAREN )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:312:16: LEFT_PAREN
        {
        match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_synpred39_DRLExpressions2042); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred39_DRLExpressions

    // $ANTLR start synpred40_DRLExpressions
    public final void synpred40_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:334:7: ( GREATER GREATER GREATER )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:334:8: GREATER GREATER GREATER
        {
        match(input,GREATER,FOLLOW_GREATER_in_synpred40_DRLExpressions2192); if (state.failed) return ;
        match(input,GREATER,FOLLOW_GREATER_in_synpred40_DRLExpressions2194); if (state.failed) return ;
        match(input,GREATER,FOLLOW_GREATER_in_synpred40_DRLExpressions2196); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred40_DRLExpressions

    // $ANTLR start synpred41_DRLExpressions
    public final void synpred41_DRLExpressions_fragment() throws RecognitionException {
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:335:7: ( GREATER GREATER )
        // /home/etirelli/workspace/jboss/drools/drools-compiler/src/main/resources/org/drools/lang/DRLExpressions.g:335:8: GREATER GREATER
        {
        match(input,GREATER,FOLLOW_GREATER_in_synpred41_DRLExpressions2215); if (state.failed) return ;
        match(input,GREATER,FOLLOW_GREATER_in_synpred41_DRLExpressions2217); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred41_DRLExpressions

    // Delegated rules

    public final boolean synpred13_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred13_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred11_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred11_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred17_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred17_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred24_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred24_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred3_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred3_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred23_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred23_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred6_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred6_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred2_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred20_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred20_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred30_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred30_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred36_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred36_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred12_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred12_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred26_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred26_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred10_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred10_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred33_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred33_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred22_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred22_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred27_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred27_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred4_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred4_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred7_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred7_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred21_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred21_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred15_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred15_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred8_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred8_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred18_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred18_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred16_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred16_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred35_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred35_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred1_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred19_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred19_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred28_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred28_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred32_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred32_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred29_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred29_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred25_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred25_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred9_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred9_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred40_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred40_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred34_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred34_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred41_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred41_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred5_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred5_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred37_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred37_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred14_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred14_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred38_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred38_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred31_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred31_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred39_DRLExpressions() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred39_DRLExpressions_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA4 dfa4 = new DFA4(this);
    protected DFA5 dfa5 = new DFA5(this);
    protected DFA13 dfa13 = new DFA13(this);
    protected DFA15 dfa15 = new DFA15(this);
    protected DFA17 dfa17 = new DFA17(this);
    protected DFA19 dfa19 = new DFA19(this);
    protected DFA21 dfa21 = new DFA21(this);
    protected DFA23 dfa23 = new DFA23(this);
    protected DFA26 dfa26 = new DFA26(this);
    protected DFA30 dfa30 = new DFA30(this);
    protected DFA33 dfa33 = new DFA33(this);
    protected DFA35 dfa35 = new DFA35(this);
    protected DFA40 dfa40 = new DFA40(this);
    protected DFA38 dfa38 = new DFA38(this);
    protected DFA39 dfa39 = new DFA39(this);
    protected DFA42 dfa42 = new DFA42(this);
    protected DFA47 dfa47 = new DFA47(this);
    protected DFA46 dfa46 = new DFA46(this);
    protected DFA52 dfa52 = new DFA52(this);
    protected DFA61 dfa61 = new DFA61(this);
    protected DFA70 dfa70 = new DFA70(this);
    protected DFA72 dfa72 = new DFA72(this);
    protected DFA76 dfa76 = new DFA76(this);
    static final String DFA4_eotS =
        "\53\uffff";
    static final String DFA4_eofS =
        "\1\2\52\uffff";
    static final String DFA4_minS =
        "\1\10\1\0\51\uffff";
    static final String DFA4_maxS =
        "\1\102\1\0\51\uffff";
    static final String DFA4_acceptS =
        "\2\uffff\1\2\47\uffff\1\1";
    static final String DFA4_specialS =
        "\1\uffff\1\0\51\uffff}>";
    static final String[] DFA4_transitionS = {
            "\1\2\2\uffff\2\2\1\uffff\1\2\3\uffff\15\2\1\uffff\4\2\2\uffff"+
            "\1\2\1\1\5\2\1\uffff\13\2\2\uffff\2\2\5\uffff\1\2",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA4_eot = DFA.unpackEncodedString(DFA4_eotS);
    static final short[] DFA4_eof = DFA.unpackEncodedString(DFA4_eofS);
    static final char[] DFA4_min = DFA.unpackEncodedStringToUnsignedChars(DFA4_minS);
    static final char[] DFA4_max = DFA.unpackEncodedStringToUnsignedChars(DFA4_maxS);
    static final short[] DFA4_accept = DFA.unpackEncodedString(DFA4_acceptS);
    static final short[] DFA4_special = DFA.unpackEncodedString(DFA4_specialS);
    static final short[][] DFA4_transition;

    static {
        int numStates = DFA4_transitionS.length;
        DFA4_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA4_transition[i] = DFA.unpackEncodedString(DFA4_transitionS[i]);
        }
    }

    class DFA4 extends DFA {

        public DFA4(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 4;
            this.eot = DFA4_eot;
            this.eof = DFA4_eof;
            this.min = DFA4_min;
            this.max = DFA4_max;
            this.accept = DFA4_accept;
            this.special = DFA4_special;
            this.transition = DFA4_transition;
        }
        public String getDescription() {
            return "69:9: ( ( typeArguments )=> typeArguments )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
            int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA4_1 = input.LA(1);

                         
                        int index4_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred3_DRLExpressions()) ) {s = 42;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index4_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 4, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA5_eotS =
        "\53\uffff";
    static final String DFA5_eofS =
        "\1\2\52\uffff";
    static final String DFA5_minS =
        "\1\10\1\0\51\uffff";
    static final String DFA5_maxS =
        "\1\102\1\0\51\uffff";
    static final String DFA5_acceptS =
        "\2\uffff\1\2\47\uffff\1\1";
    static final String DFA5_specialS =
        "\1\uffff\1\0\51\uffff}>";
    static final String[] DFA5_transitionS = {
            "\1\2\2\uffff\2\2\1\uffff\1\2\3\uffff\15\2\1\uffff\4\2\2\uffff"+
            "\1\2\1\1\5\2\1\uffff\13\2\2\uffff\2\2\5\uffff\1\2",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA5_eot = DFA.unpackEncodedString(DFA5_eotS);
    static final short[] DFA5_eof = DFA.unpackEncodedString(DFA5_eofS);
    static final char[] DFA5_min = DFA.unpackEncodedStringToUnsignedChars(DFA5_minS);
    static final char[] DFA5_max = DFA.unpackEncodedStringToUnsignedChars(DFA5_maxS);
    static final short[] DFA5_accept = DFA.unpackEncodedString(DFA5_acceptS);
    static final short[] DFA5_special = DFA.unpackEncodedString(DFA5_specialS);
    static final short[][] DFA5_transition;

    static {
        int numStates = DFA5_transitionS.length;
        DFA5_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA5_transition[i] = DFA.unpackEncodedString(DFA5_transitionS[i]);
        }
    }

    class DFA5 extends DFA {

        public DFA5(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 5;
            this.eot = DFA5_eot;
            this.eof = DFA5_eof;
            this.min = DFA5_min;
            this.max = DFA5_max;
            this.accept = DFA5_accept;
            this.special = DFA5_special;
            this.transition = DFA5_transition;
        }
        public String getDescription() {
            return "69:51: ( ( typeArguments )=> typeArguments )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
            int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA5_1 = input.LA(1);

                         
                        int index5_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_DRLExpressions()) ) {s = 42;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index5_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 5, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA13_eotS =
        "\16\uffff";
    static final String DFA13_eofS =
        "\16\uffff";
    static final String DFA13_minS =
        "\1\10\13\0\2\uffff";
    static final String DFA13_maxS =
        "\1\102\13\0\2\uffff";
    static final String DFA13_acceptS =
        "\14\uffff\1\2\1\1";
    static final String DFA13_specialS =
        "\1\uffff\1\11\1\7\1\6\1\5\1\4\1\2\1\0\1\3\1\1\1\12\1\10\2\uffff}>";
    static final String[] DFA13_transitionS = {
            "\1\14\2\uffff\2\14\1\uffff\1\14\3\uffff\3\14\1\2\1\3\1\4\1\5"+
            "\1\6\1\7\1\10\1\11\2\14\1\uffff\2\14\4\uffff\1\13\1\12\1\1\4"+
            "\14\1\uffff\2\14\1\uffff\2\14\1\uffff\5\14\2\uffff\2\14\5\uffff"+
            "\1\14",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            ""
    };

    static final short[] DFA13_eot = DFA.unpackEncodedString(DFA13_eotS);
    static final short[] DFA13_eof = DFA.unpackEncodedString(DFA13_eofS);
    static final char[] DFA13_min = DFA.unpackEncodedStringToUnsignedChars(DFA13_minS);
    static final char[] DFA13_max = DFA.unpackEncodedStringToUnsignedChars(DFA13_maxS);
    static final short[] DFA13_accept = DFA.unpackEncodedString(DFA13_acceptS);
    static final short[] DFA13_special = DFA.unpackEncodedString(DFA13_specialS);
    static final short[][] DFA13_transition;

    static {
        int numStates = DFA13_transitionS.length;
        DFA13_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA13_transition[i] = DFA.unpackEncodedString(DFA13_transitionS[i]);
        }
    }

    class DFA13 extends DFA {

        public DFA13(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 13;
            this.eot = DFA13_eot;
            this.eof = DFA13_eof;
            this.min = DFA13_min;
            this.max = DFA13_max;
            this.accept = DFA13_accept;
            this.special = DFA13_special;
            this.transition = DFA13_transition;
        }
        public String getDescription() {
            return "92:26: ( ( assignmentOperator )=> assignmentOperator expression )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
            int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA13_7 = input.LA(1);

                         
                        int index13_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred6_DRLExpressions()) ) {s = 13;}

                        else if ( (true) ) {s = 12;}

                         
                        input.seek(index13_7);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA13_9 = input.LA(1);

                         
                        int index13_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred6_DRLExpressions()) ) {s = 13;}

                        else if ( (true) ) {s = 12;}

                         
                        input.seek(index13_9);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA13_6 = input.LA(1);

                         
                        int index13_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred6_DRLExpressions()) ) {s = 13;}

                        else if ( (true) ) {s = 12;}

                         
                        input.seek(index13_6);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA13_8 = input.LA(1);

                         
                        int index13_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred6_DRLExpressions()) ) {s = 13;}

                        else if ( (true) ) {s = 12;}

                         
                        input.seek(index13_8);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA13_5 = input.LA(1);

                         
                        int index13_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred6_DRLExpressions()) ) {s = 13;}

                        else if ( (true) ) {s = 12;}

                         
                        input.seek(index13_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA13_4 = input.LA(1);

                         
                        int index13_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred6_DRLExpressions()) ) {s = 13;}

                        else if ( (true) ) {s = 12;}

                         
                        input.seek(index13_4);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA13_3 = input.LA(1);

                         
                        int index13_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred6_DRLExpressions()) ) {s = 13;}

                        else if ( (true) ) {s = 12;}

                         
                        input.seek(index13_3);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA13_2 = input.LA(1);

                         
                        int index13_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred6_DRLExpressions()) ) {s = 13;}

                        else if ( (true) ) {s = 12;}

                         
                        input.seek(index13_2);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA13_11 = input.LA(1);

                         
                        int index13_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred6_DRLExpressions()) ) {s = 13;}

                        else if ( (true) ) {s = 12;}

                         
                        input.seek(index13_11);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA13_1 = input.LA(1);

                         
                        int index13_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred6_DRLExpressions()) ) {s = 13;}

                        else if ( (true) ) {s = 12;}

                         
                        input.seek(index13_1);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA13_10 = input.LA(1);

                         
                        int index13_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred6_DRLExpressions()) ) {s = 13;}

                        else if ( (true) ) {s = 12;}

                         
                        input.seek(index13_10);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 13, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA15_eotS =
        "\26\uffff";
    static final String DFA15_eofS =
        "\26\uffff";
    static final String DFA15_minS =
        "\1\10\4\uffff\1\0\1\uffff\1\0\16\uffff";
    static final String DFA15_maxS =
        "\1\102\4\uffff\1\0\1\uffff\1\0\16\uffff";
    static final String DFA15_acceptS =
        "\1\uffff\4\1\1\uffff\1\1\1\uffff\1\2\15\uffff";
    static final String DFA15_specialS =
        "\1\0\4\uffff\1\1\1\uffff\1\2\16\uffff}>";
    static final String[] DFA15_transitionS = {
            "\1\10\2\uffff\2\10\1\uffff\1\10\3\uffff\2\10\11\uffff\2\10\3"+
            "\uffff\1\1\1\2\1\4\1\3\1\6\1\5\1\uffff\1\10\1\uffff\1\10\10"+
            "\uffff\2\10\5\uffff\2\10\5\uffff\1\7",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA15_eot = DFA.unpackEncodedString(DFA15_eotS);
    static final short[] DFA15_eof = DFA.unpackEncodedString(DFA15_eofS);
    static final char[] DFA15_min = DFA.unpackEncodedStringToUnsignedChars(DFA15_minS);
    static final char[] DFA15_max = DFA.unpackEncodedStringToUnsignedChars(DFA15_maxS);
    static final short[] DFA15_accept = DFA.unpackEncodedString(DFA15_acceptS);
    static final short[] DFA15_special = DFA.unpackEncodedString(DFA15_specialS);
    static final short[][] DFA15_transition;

    static {
        int numStates = DFA15_transitionS.length;
        DFA15_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA15_transition[i] = DFA.unpackEncodedString(DFA15_transitionS[i]);
        }
    }

    class DFA15 extends DFA {

        public DFA15(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 15;
            this.eot = DFA15_eot;
            this.eof = DFA15_eof;
            this.min = DFA15_min;
            this.max = DFA15_max;
            this.accept = DFA15_accept;
            this.special = DFA15_special;
            this.transition = DFA15_transition;
        }
        public String getDescription() {
            return "101:6: ( ( operator )=> operator shiftExpression | conditionalAndExpression )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
            int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA15_0 = input.LA(1);

                         
                        int index15_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA15_0==EQUALS) && (synpred7_DRLExpressions())) {s = 1;}

                        else if ( (LA15_0==NOT_EQUALS) && (synpred7_DRLExpressions())) {s = 2;}

                        else if ( (LA15_0==LESS_EQUALS) && (synpred7_DRLExpressions())) {s = 3;}

                        else if ( (LA15_0==GREATER_EQUALS) && (synpred7_DRLExpressions())) {s = 4;}

                        else if ( (LA15_0==LESS) ) {s = 5;}

                        else if ( (LA15_0==GREATER) && (synpred7_DRLExpressions())) {s = 6;}

                        else if ( (LA15_0==ID) ) {s = 7;}

                        else if ( (LA15_0==FLOAT||(LA15_0>=HEX && LA15_0<=DECIMAL)||LA15_0==STRING||(LA15_0>=BOOL && LA15_0<=NULL)||(LA15_0>=DECR && LA15_0<=INCR)||LA15_0==LEFT_PAREN||LA15_0==LEFT_SQUARE||(LA15_0>=NEGATION && LA15_0<=TILDE)||(LA15_0>=MINUS && LA15_0<=PLUS)) ) {s = 8;}

                         
                        input.seek(index15_0);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA15_5 = input.LA(1);

                         
                        int index15_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred7_DRLExpressions()) ) {s = 6;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index15_5);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA15_7 = input.LA(1);

                         
                        int index15_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (((synpred7_DRLExpressions()&&((helper.validateIdentifierKey(DroolsSoftKeywords.NOT))))||(synpred7_DRLExpressions()&&((helper.isPluggableEvaluator(false)))))) ) {s = 6;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index15_7);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 15, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA17_eotS =
        "\26\uffff";
    static final String DFA17_eofS =
        "\26\uffff";
    static final String DFA17_minS =
        "\1\10\4\uffff\1\0\1\uffff\1\0\16\uffff";
    static final String DFA17_maxS =
        "\1\102\4\uffff\1\0\1\uffff\1\0\16\uffff";
    static final String DFA17_acceptS =
        "\1\uffff\4\1\1\uffff\1\1\1\uffff\1\2\15\uffff";
    static final String DFA17_specialS =
        "\1\0\4\uffff\1\1\1\uffff\1\2\16\uffff}>";
    static final String[] DFA17_transitionS = {
            "\1\10\2\uffff\2\10\1\uffff\1\10\3\uffff\2\10\11\uffff\2\10\3"+
            "\uffff\1\1\1\2\1\4\1\3\1\6\1\5\1\uffff\1\10\1\uffff\1\10\10"+
            "\uffff\2\10\5\uffff\2\10\5\uffff\1\7",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA17_eot = DFA.unpackEncodedString(DFA17_eotS);
    static final short[] DFA17_eof = DFA.unpackEncodedString(DFA17_eofS);
    static final char[] DFA17_min = DFA.unpackEncodedStringToUnsignedChars(DFA17_minS);
    static final char[] DFA17_max = DFA.unpackEncodedStringToUnsignedChars(DFA17_maxS);
    static final short[] DFA17_accept = DFA.unpackEncodedString(DFA17_acceptS);
    static final short[] DFA17_special = DFA.unpackEncodedString(DFA17_specialS);
    static final short[][] DFA17_transition;

    static {
        int numStates = DFA17_transitionS.length;
        DFA17_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA17_transition[i] = DFA.unpackEncodedString(DFA17_transitionS[i]);
        }
    }

    class DFA17 extends DFA {

        public DFA17(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 17;
            this.eot = DFA17_eot;
            this.eof = DFA17_eof;
            this.min = DFA17_min;
            this.max = DFA17_max;
            this.accept = DFA17_accept;
            this.special = DFA17_special;
            this.transition = DFA17_transition;
        }
        public String getDescription() {
            return "107:5: ( ( operator )=> operator shiftExpression | inclusiveOrExpression )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
            int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA17_0 = input.LA(1);

                         
                        int index17_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA17_0==EQUALS) && (synpred8_DRLExpressions())) {s = 1;}

                        else if ( (LA17_0==NOT_EQUALS) && (synpred8_DRLExpressions())) {s = 2;}

                        else if ( (LA17_0==LESS_EQUALS) && (synpred8_DRLExpressions())) {s = 3;}

                        else if ( (LA17_0==GREATER_EQUALS) && (synpred8_DRLExpressions())) {s = 4;}

                        else if ( (LA17_0==LESS) ) {s = 5;}

                        else if ( (LA17_0==GREATER) && (synpred8_DRLExpressions())) {s = 6;}

                        else if ( (LA17_0==ID) ) {s = 7;}

                        else if ( (LA17_0==FLOAT||(LA17_0>=HEX && LA17_0<=DECIMAL)||LA17_0==STRING||(LA17_0>=BOOL && LA17_0<=NULL)||(LA17_0>=DECR && LA17_0<=INCR)||LA17_0==LEFT_PAREN||LA17_0==LEFT_SQUARE||(LA17_0>=NEGATION && LA17_0<=TILDE)||(LA17_0>=MINUS && LA17_0<=PLUS)) ) {s = 8;}

                         
                        input.seek(index17_0);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA17_5 = input.LA(1);

                         
                        int index17_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred8_DRLExpressions()) ) {s = 6;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index17_5);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA17_7 = input.LA(1);

                         
                        int index17_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (((synpred8_DRLExpressions()&&((helper.isPluggableEvaluator(false))))||(synpred8_DRLExpressions()&&((helper.validateIdentifierKey(DroolsSoftKeywords.NOT)))))) ) {s = 6;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index17_7);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 17, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA19_eotS =
        "\26\uffff";
    static final String DFA19_eofS =
        "\26\uffff";
    static final String DFA19_minS =
        "\1\10\4\uffff\1\0\1\uffff\1\0\16\uffff";
    static final String DFA19_maxS =
        "\1\102\4\uffff\1\0\1\uffff\1\0\16\uffff";
    static final String DFA19_acceptS =
        "\1\uffff\4\1\1\uffff\1\1\1\uffff\1\2\15\uffff";
    static final String DFA19_specialS =
        "\1\0\4\uffff\1\1\1\uffff\1\2\16\uffff}>";
    static final String[] DFA19_transitionS = {
            "\1\10\2\uffff\2\10\1\uffff\1\10\3\uffff\2\10\11\uffff\2\10\3"+
            "\uffff\1\1\1\2\1\4\1\3\1\6\1\5\1\uffff\1\10\1\uffff\1\10\10"+
            "\uffff\2\10\5\uffff\2\10\5\uffff\1\7",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA19_eot = DFA.unpackEncodedString(DFA19_eotS);
    static final short[] DFA19_eof = DFA.unpackEncodedString(DFA19_eofS);
    static final char[] DFA19_min = DFA.unpackEncodedStringToUnsignedChars(DFA19_minS);
    static final char[] DFA19_max = DFA.unpackEncodedStringToUnsignedChars(DFA19_maxS);
    static final short[] DFA19_accept = DFA.unpackEncodedString(DFA19_acceptS);
    static final short[] DFA19_special = DFA.unpackEncodedString(DFA19_specialS);
    static final short[][] DFA19_transition;

    static {
        int numStates = DFA19_transitionS.length;
        DFA19_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA19_transition[i] = DFA.unpackEncodedString(DFA19_transitionS[i]);
        }
    }

    class DFA19 extends DFA {

        public DFA19(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 19;
            this.eot = DFA19_eot;
            this.eof = DFA19_eof;
            this.min = DFA19_min;
            this.max = DFA19_max;
            this.accept = DFA19_accept;
            this.special = DFA19_special;
            this.transition = DFA19_transition;
        }
        public String getDescription() {
            return "113:5: ( ( operator )=> operator shiftExpression | exclusiveOrExpression )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
            int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA19_0 = input.LA(1);

                         
                        int index19_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA19_0==EQUALS) && (synpred9_DRLExpressions())) {s = 1;}

                        else if ( (LA19_0==NOT_EQUALS) && (synpred9_DRLExpressions())) {s = 2;}

                        else if ( (LA19_0==LESS_EQUALS) && (synpred9_DRLExpressions())) {s = 3;}

                        else if ( (LA19_0==GREATER_EQUALS) && (synpred9_DRLExpressions())) {s = 4;}

                        else if ( (LA19_0==LESS) ) {s = 5;}

                        else if ( (LA19_0==GREATER) && (synpred9_DRLExpressions())) {s = 6;}

                        else if ( (LA19_0==ID) ) {s = 7;}

                        else if ( (LA19_0==FLOAT||(LA19_0>=HEX && LA19_0<=DECIMAL)||LA19_0==STRING||(LA19_0>=BOOL && LA19_0<=NULL)||(LA19_0>=DECR && LA19_0<=INCR)||LA19_0==LEFT_PAREN||LA19_0==LEFT_SQUARE||(LA19_0>=NEGATION && LA19_0<=TILDE)||(LA19_0>=MINUS && LA19_0<=PLUS)) ) {s = 8;}

                         
                        input.seek(index19_0);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA19_5 = input.LA(1);

                         
                        int index19_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred9_DRLExpressions()) ) {s = 6;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index19_5);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA19_7 = input.LA(1);

                         
                        int index19_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (((synpred9_DRLExpressions()&&((helper.validateIdentifierKey(DroolsSoftKeywords.NOT))))||(synpred9_DRLExpressions()&&((helper.isPluggableEvaluator(false)))))) ) {s = 6;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index19_7);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 19, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA21_eotS =
        "\26\uffff";
    static final String DFA21_eofS =
        "\26\uffff";
    static final String DFA21_minS =
        "\1\10\4\uffff\1\0\1\uffff\1\0\16\uffff";
    static final String DFA21_maxS =
        "\1\102\4\uffff\1\0\1\uffff\1\0\16\uffff";
    static final String DFA21_acceptS =
        "\1\uffff\4\1\1\uffff\1\1\1\uffff\1\2\15\uffff";
    static final String DFA21_specialS =
        "\1\0\4\uffff\1\1\1\uffff\1\2\16\uffff}>";
    static final String[] DFA21_transitionS = {
            "\1\10\2\uffff\2\10\1\uffff\1\10\3\uffff\2\10\11\uffff\2\10\3"+
            "\uffff\1\1\1\2\1\4\1\3\1\6\1\5\1\uffff\1\10\1\uffff\1\10\10"+
            "\uffff\2\10\5\uffff\2\10\5\uffff\1\7",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA21_eot = DFA.unpackEncodedString(DFA21_eotS);
    static final short[] DFA21_eof = DFA.unpackEncodedString(DFA21_eofS);
    static final char[] DFA21_min = DFA.unpackEncodedStringToUnsignedChars(DFA21_minS);
    static final char[] DFA21_max = DFA.unpackEncodedStringToUnsignedChars(DFA21_maxS);
    static final short[] DFA21_accept = DFA.unpackEncodedString(DFA21_acceptS);
    static final short[] DFA21_special = DFA.unpackEncodedString(DFA21_specialS);
    static final short[][] DFA21_transition;

    static {
        int numStates = DFA21_transitionS.length;
        DFA21_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA21_transition[i] = DFA.unpackEncodedString(DFA21_transitionS[i]);
        }
    }

    class DFA21 extends DFA {

        public DFA21(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 21;
            this.eot = DFA21_eot;
            this.eof = DFA21_eof;
            this.min = DFA21_min;
            this.max = DFA21_max;
            this.accept = DFA21_accept;
            this.special = DFA21_special;
            this.transition = DFA21_transition;
        }
        public String getDescription() {
            return "119:5: ( ( operator )=> operator shiftExpression | andExpression )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
            int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA21_0 = input.LA(1);

                         
                        int index21_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA21_0==EQUALS) && (synpred10_DRLExpressions())) {s = 1;}

                        else if ( (LA21_0==NOT_EQUALS) && (synpred10_DRLExpressions())) {s = 2;}

                        else if ( (LA21_0==LESS_EQUALS) && (synpred10_DRLExpressions())) {s = 3;}

                        else if ( (LA21_0==GREATER_EQUALS) && (synpred10_DRLExpressions())) {s = 4;}

                        else if ( (LA21_0==LESS) ) {s = 5;}

                        else if ( (LA21_0==GREATER) && (synpred10_DRLExpressions())) {s = 6;}

                        else if ( (LA21_0==ID) ) {s = 7;}

                        else if ( (LA21_0==FLOAT||(LA21_0>=HEX && LA21_0<=DECIMAL)||LA21_0==STRING||(LA21_0>=BOOL && LA21_0<=NULL)||(LA21_0>=DECR && LA21_0<=INCR)||LA21_0==LEFT_PAREN||LA21_0==LEFT_SQUARE||(LA21_0>=NEGATION && LA21_0<=TILDE)||(LA21_0>=MINUS && LA21_0<=PLUS)) ) {s = 8;}

                         
                        input.seek(index21_0);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA21_5 = input.LA(1);

                         
                        int index21_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred10_DRLExpressions()) ) {s = 6;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index21_5);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA21_7 = input.LA(1);

                         
                        int index21_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (((synpred10_DRLExpressions()&&((helper.validateIdentifierKey(DroolsSoftKeywords.NOT))))||(synpred10_DRLExpressions()&&((helper.isPluggableEvaluator(false)))))) ) {s = 6;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index21_7);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 21, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA23_eotS =
        "\26\uffff";
    static final String DFA23_eofS =
        "\26\uffff";
    static final String DFA23_minS =
        "\1\10\4\uffff\1\0\1\uffff\1\0\16\uffff";
    static final String DFA23_maxS =
        "\1\102\4\uffff\1\0\1\uffff\1\0\16\uffff";
    static final String DFA23_acceptS =
        "\1\uffff\4\1\1\uffff\1\1\1\uffff\1\2\15\uffff";
    static final String DFA23_specialS =
        "\1\0\4\uffff\1\1\1\uffff\1\2\16\uffff}>";
    static final String[] DFA23_transitionS = {
            "\1\10\2\uffff\2\10\1\uffff\1\10\3\uffff\2\10\11\uffff\2\10\3"+
            "\uffff\1\1\1\2\1\4\1\3\1\6\1\5\1\uffff\1\10\1\uffff\1\10\10"+
            "\uffff\2\10\5\uffff\2\10\5\uffff\1\7",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA23_eot = DFA.unpackEncodedString(DFA23_eotS);
    static final short[] DFA23_eof = DFA.unpackEncodedString(DFA23_eofS);
    static final char[] DFA23_min = DFA.unpackEncodedStringToUnsignedChars(DFA23_minS);
    static final char[] DFA23_max = DFA.unpackEncodedStringToUnsignedChars(DFA23_maxS);
    static final short[] DFA23_accept = DFA.unpackEncodedString(DFA23_acceptS);
    static final short[] DFA23_special = DFA.unpackEncodedString(DFA23_specialS);
    static final short[][] DFA23_transition;

    static {
        int numStates = DFA23_transitionS.length;
        DFA23_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA23_transition[i] = DFA.unpackEncodedString(DFA23_transitionS[i]);
        }
    }

    class DFA23 extends DFA {

        public DFA23(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 23;
            this.eot = DFA23_eot;
            this.eof = DFA23_eof;
            this.min = DFA23_min;
            this.max = DFA23_max;
            this.accept = DFA23_accept;
            this.special = DFA23_special;
            this.transition = DFA23_transition;
        }
        public String getDescription() {
            return "125:5: ( ( operator )=> operator shiftExpression | equalityExpression )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
            int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA23_0 = input.LA(1);

                         
                        int index23_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA23_0==EQUALS) && (synpred11_DRLExpressions())) {s = 1;}

                        else if ( (LA23_0==NOT_EQUALS) && (synpred11_DRLExpressions())) {s = 2;}

                        else if ( (LA23_0==LESS_EQUALS) && (synpred11_DRLExpressions())) {s = 3;}

                        else if ( (LA23_0==GREATER_EQUALS) && (synpred11_DRLExpressions())) {s = 4;}

                        else if ( (LA23_0==LESS) ) {s = 5;}

                        else if ( (LA23_0==GREATER) && (synpred11_DRLExpressions())) {s = 6;}

                        else if ( (LA23_0==ID) ) {s = 7;}

                        else if ( (LA23_0==FLOAT||(LA23_0>=HEX && LA23_0<=DECIMAL)||LA23_0==STRING||(LA23_0>=BOOL && LA23_0<=NULL)||(LA23_0>=DECR && LA23_0<=INCR)||LA23_0==LEFT_PAREN||LA23_0==LEFT_SQUARE||(LA23_0>=NEGATION && LA23_0<=TILDE)||(LA23_0>=MINUS && LA23_0<=PLUS)) ) {s = 8;}

                         
                        input.seek(index23_0);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA23_5 = input.LA(1);

                         
                        int index23_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred11_DRLExpressions()) ) {s = 6;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index23_5);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA23_7 = input.LA(1);

                         
                        int index23_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (((synpred11_DRLExpressions()&&((helper.isPluggableEvaluator(false))))||(synpred11_DRLExpressions()&&((helper.validateIdentifierKey(DroolsSoftKeywords.NOT)))))) ) {s = 6;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index23_7);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 23, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA26_eotS =
        "\51\uffff";
    static final String DFA26_eofS =
        "\51\uffff";
    static final String DFA26_minS =
        "\1\10\1\0\47\uffff";
    static final String DFA26_maxS =
        "\1\102\1\0\47\uffff";
    static final String DFA26_acceptS =
        "\2\uffff\1\2\45\uffff\1\1";
    static final String DFA26_specialS =
        "\1\uffff\1\0\47\uffff}>";
    static final String[] DFA26_transitionS = {
            "\1\2\2\uffff\2\2\1\uffff\1\2\3\uffff\15\2\1\uffff\4\2\2\uffff"+
            "\7\2\1\uffff\2\2\1\uffff\10\2\2\uffff\2\2\5\uffff\1\1",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA26_eot = DFA.unpackEncodedString(DFA26_eotS);
    static final short[] DFA26_eof = DFA.unpackEncodedString(DFA26_eofS);
    static final char[] DFA26_min = DFA.unpackEncodedStringToUnsignedChars(DFA26_minS);
    static final char[] DFA26_max = DFA.unpackEncodedStringToUnsignedChars(DFA26_maxS);
    static final short[] DFA26_accept = DFA.unpackEncodedString(DFA26_acceptS);
    static final short[] DFA26_special = DFA.unpackEncodedString(DFA26_specialS);
    static final short[][] DFA26_transition;

    static {
        int numStates = DFA26_transitionS.length;
        DFA26_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA26_transition[i] = DFA.unpackEncodedString(DFA26_transitionS[i]);
        }
    }

    class DFA26 extends DFA {

        public DFA26(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 26;
            this.eot = DFA26_eot;
            this.eof = DFA26_eof;
            this.min = DFA26_min;
            this.max = DFA26_max;
            this.accept = DFA26_accept;
            this.special = DFA26_special;
            this.transition = DFA26_transition;
        }
        public String getDescription() {
            return "134:18: ( instanceof_key type )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
            int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA26_1 = input.LA(1);

                         
                        int index26_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (((helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF)))) ) {s = 40;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index26_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 26, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA30_eotS =
        "\52\uffff";
    static final String DFA30_eofS =
        "\52\uffff";
    static final String DFA30_minS =
        "\1\10\1\0\20\uffff\2\0\26\uffff";
    static final String DFA30_maxS =
        "\1\102\1\0\20\uffff\2\0\26\uffff";
    static final String DFA30_acceptS =
        "\2\uffff\1\2\45\uffff\2\1";
    static final String DFA30_specialS =
        "\1\0\1\1\20\uffff\1\2\1\3\26\uffff}>";
    static final String[] DFA30_transitionS = {
            "\1\2\2\uffff\2\2\1\uffff\1\2\3\uffff\15\2\1\uffff\4\2\1\51\1"+
            "\50\1\23\1\22\5\2\1\uffff\2\2\1\uffff\10\2\2\uffff\2\2\5\uffff"+
            "\1\1",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA30_eot = DFA.unpackEncodedString(DFA30_eotS);
    static final short[] DFA30_eof = DFA.unpackEncodedString(DFA30_eofS);
    static final char[] DFA30_min = DFA.unpackEncodedStringToUnsignedChars(DFA30_minS);
    static final char[] DFA30_max = DFA.unpackEncodedStringToUnsignedChars(DFA30_maxS);
    static final short[] DFA30_accept = DFA.unpackEncodedString(DFA30_acceptS);
    static final short[] DFA30_special = DFA.unpackEncodedString(DFA30_specialS);
    static final short[][] DFA30_transition;

    static {
        int numStates = DFA30_transitionS.length;
        DFA30_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA30_transition[i] = DFA.unpackEncodedString(DFA30_transitionS[i]);
        }
    }

    class DFA30 extends DFA {

        public DFA30(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 30;
            this.eot = DFA30_eot;
            this.eof = DFA30_eof;
            this.min = DFA30_min;
            this.max = DFA30_max;
            this.accept = DFA30_accept;
            this.special = DFA30_special;
            this.transition = DFA30_transition;
        }
        public String getDescription() {
            return "()* loopback of 142:21: ( ( relationalOp )=> relationalOp shiftExpression )*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
            int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA30_0 = input.LA(1);

                         
                        int index30_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA30_0==ID) ) {s = 1;}

                        else if ( (LA30_0==FLOAT||(LA30_0>=HEX && LA30_0<=DECIMAL)||LA30_0==STRING||(LA30_0>=BOOL && LA30_0<=INCR)||(LA30_0>=SEMICOLON && LA30_0<=NOT_EQUALS)||(LA30_0>=EQUALS_ASSIGN && LA30_0<=RIGHT_SQUARE)||(LA30_0>=RIGHT_CURLY && LA30_0<=COMMA)||(LA30_0>=DOUBLE_AMPER && LA30_0<=XOR)||(LA30_0>=MINUS && LA30_0<=PLUS)) ) {s = 2;}

                        else if ( (LA30_0==LESS) ) {s = 18;}

                        else if ( (LA30_0==GREATER) ) {s = 19;}

                        else if ( (LA30_0==LESS_EQUALS) && (synpred12_DRLExpressions())) {s = 40;}

                        else if ( (LA30_0==GREATER_EQUALS) && (synpred12_DRLExpressions())) {s = 41;}

                         
                        input.seek(index30_0);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA30_1 = input.LA(1);

                         
                        int index30_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (((synpred12_DRLExpressions()&&((helper.isPluggableEvaluator(false))))||(synpred12_DRLExpressions()&&((helper.validateIdentifierKey(DroolsSoftKeywords.NOT)))))) ) {s = 41;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index30_1);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA30_18 = input.LA(1);

                         
                        int index30_18 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_DRLExpressions()) ) {s = 41;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index30_18);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA30_19 = input.LA(1);

                         
                        int index30_19 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred12_DRLExpressions()) ) {s = 41;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index30_19);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 30, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA33_eotS =
        "\53\uffff";
    static final String DFA33_eofS =
        "\53\uffff";
    static final String DFA33_minS =
        "\1\10\12\uffff\2\0\36\uffff";
    static final String DFA33_maxS =
        "\1\102\12\uffff\2\0\36\uffff";
    static final String DFA33_acceptS =
        "\1\uffff\1\2\50\uffff\1\1";
    static final String DFA33_specialS =
        "\13\uffff\1\0\1\1\36\uffff}>";
    static final String[] DFA33_transitionS = {
            "\1\1\2\uffff\2\1\1\uffff\1\1\3\uffff\15\1\1\uffff\6\1\1\14\1"+
            "\13\5\1\1\uffff\2\1\1\uffff\10\1\2\uffff\2\1\5\uffff\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA33_eot = DFA.unpackEncodedString(DFA33_eotS);
    static final short[] DFA33_eof = DFA.unpackEncodedString(DFA33_eofS);
    static final char[] DFA33_min = DFA.unpackEncodedStringToUnsignedChars(DFA33_minS);
    static final char[] DFA33_max = DFA.unpackEncodedStringToUnsignedChars(DFA33_maxS);
    static final short[] DFA33_accept = DFA.unpackEncodedString(DFA33_acceptS);
    static final short[] DFA33_special = DFA.unpackEncodedString(DFA33_specialS);
    static final short[][] DFA33_transition;

    static {
        int numStates = DFA33_transitionS.length;
        DFA33_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA33_transition[i] = DFA.unpackEncodedString(DFA33_transitionS[i]);
        }
    }

    class DFA33 extends DFA {

        public DFA33(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 33;
            this.eot = DFA33_eot;
            this.eof = DFA33_eof;
            this.min = DFA33_min;
            this.max = DFA33_max;
            this.accept = DFA33_accept;
            this.special = DFA33_special;
            this.transition = DFA33_transition;
        }
        public String getDescription() {
            return "()* loopback of 163:24: ( ( shiftOp )=> shiftOp additiveExpression )*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
            int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA33_11 = input.LA(1);

                         
                        int index33_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_DRLExpressions()) ) {s = 42;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index33_11);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA33_12 = input.LA(1);

                         
                        int index33_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_DRLExpressions()) ) {s = 42;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index33_12);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 33, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA35_eotS =
        "\53\uffff";
    static final String DFA35_eofS =
        "\53\uffff";
    static final String DFA35_minS =
        "\1\10\21\uffff\2\0\27\uffff";
    static final String DFA35_maxS =
        "\1\102\21\uffff\2\0\27\uffff";
    static final String DFA35_acceptS =
        "\1\uffff\1\2\50\uffff\1\1";
    static final String DFA35_specialS =
        "\22\uffff\1\0\1\1\27\uffff}>";
    static final String[] DFA35_transitionS = {
            "\1\1\2\uffff\2\1\1\uffff\1\1\3\uffff\15\1\1\uffff\15\1\1\uffff"+
            "\2\1\1\uffff\10\1\2\uffff\1\23\1\22\5\uffff\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA35_eot = DFA.unpackEncodedString(DFA35_eotS);
    static final short[] DFA35_eof = DFA.unpackEncodedString(DFA35_eofS);
    static final char[] DFA35_min = DFA.unpackEncodedStringToUnsignedChars(DFA35_minS);
    static final char[] DFA35_max = DFA.unpackEncodedStringToUnsignedChars(DFA35_maxS);
    static final short[] DFA35_accept = DFA.unpackEncodedString(DFA35_acceptS);
    static final short[] DFA35_special = DFA.unpackEncodedString(DFA35_specialS);
    static final short[][] DFA35_transition;

    static {
        int numStates = DFA35_transitionS.length;
        DFA35_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA35_transition[i] = DFA.unpackEncodedString(DFA35_transitionS[i]);
        }
    }

    class DFA35 extends DFA {

        public DFA35(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 35;
            this.eot = DFA35_eot;
            this.eof = DFA35_eof;
            this.min = DFA35_min;
            this.max = DFA35_max;
            this.accept = DFA35_accept;
            this.special = DFA35_special;
            this.transition = DFA35_transition;
        }
        public String getDescription() {
            return "()* loopback of 171:32: ( ( PLUS | MINUS )=> ( PLUS | MINUS ) multiplicativeExpression )*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
            int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA35_18 = input.LA(1);

                         
                        int index35_18 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred14_DRLExpressions()) ) {s = 42;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index35_18);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA35_19 = input.LA(1);

                         
                        int index35_19 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred14_DRLExpressions()) ) {s = 42;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index35_19);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 35, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA40_eotS =
        "\16\uffff";
    static final String DFA40_eofS =
        "\16\uffff";
    static final String DFA40_minS =
        "\1\10\2\uffff\1\0\12\uffff";
    static final String DFA40_maxS =
        "\1\102\2\uffff\1\0\12\uffff";
    static final String DFA40_acceptS =
        "\1\uffff\1\1\1\2\1\uffff\1\4\10\uffff\1\3";
    static final String DFA40_specialS =
        "\3\uffff\1\0\12\uffff}>";
    static final String[] DFA40_transitionS = {
            "\1\4\2\uffff\2\4\1\uffff\1\4\3\uffff\2\4\23\uffff\1\4\1\uffff"+
            "\1\3\1\uffff\1\4\10\uffff\1\2\1\1\14\uffff\1\4",
            "",
            "",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA40_eot = DFA.unpackEncodedString(DFA40_eotS);
    static final short[] DFA40_eof = DFA.unpackEncodedString(DFA40_eofS);
    static final char[] DFA40_min = DFA.unpackEncodedStringToUnsignedChars(DFA40_minS);
    static final char[] DFA40_max = DFA.unpackEncodedStringToUnsignedChars(DFA40_maxS);
    static final short[] DFA40_accept = DFA.unpackEncodedString(DFA40_acceptS);
    static final short[] DFA40_special = DFA.unpackEncodedString(DFA40_specialS);
    static final short[][] DFA40_transition;

    static {
        int numStates = DFA40_transitionS.length;
        DFA40_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA40_transition[i] = DFA.unpackEncodedString(DFA40_transitionS[i]);
        }
    }

    class DFA40 extends DFA {

        public DFA40(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 40;
            this.eot = DFA40_eot;
            this.eof = DFA40_eof;
            this.min = DFA40_min;
            this.max = DFA40_max;
            this.accept = DFA40_accept;
            this.special = DFA40_special;
            this.transition = DFA40_transition;
        }
        public String getDescription() {
            return "186:1: unaryExpressionNotPlusMinus : ( TILDE unaryExpression | NEGATION unaryExpression | ( castExpression )=> castExpression | primary ( ( selector )=> selector )* ( ( INCR | DECR )=> ( INCR | DECR ) )? );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
            int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA40_3 = input.LA(1);

                         
                        int index40_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred15_DRLExpressions()) ) {s = 13;}

                        else if ( (true) ) {s = 4;}

                         
                        input.seek(index40_3);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 40, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA38_eotS =
        "\55\uffff";
    static final String DFA38_eofS =
        "\1\1\54\uffff";
    static final String DFA38_minS =
        "\1\10\40\uffff\1\0\13\uffff";
    static final String DFA38_maxS =
        "\1\103\40\uffff\1\0\13\uffff";
    static final String DFA38_acceptS =
        "\1\uffff\1\2\52\uffff\1\1";
    static final String DFA38_specialS =
        "\1\0\40\uffff\1\1\13\uffff}>";
    static final String[] DFA38_transitionS = {
            "\1\1\2\uffff\2\1\1\uffff\1\1\3\uffff\15\1\1\uffff\13\1\1\41"+
            "\1\1\1\uffff\2\1\1\54\14\1\5\uffff\2\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA38_eot = DFA.unpackEncodedString(DFA38_eotS);
    static final short[] DFA38_eof = DFA.unpackEncodedString(DFA38_eofS);
    static final char[] DFA38_min = DFA.unpackEncodedStringToUnsignedChars(DFA38_minS);
    static final char[] DFA38_max = DFA.unpackEncodedStringToUnsignedChars(DFA38_maxS);
    static final short[] DFA38_accept = DFA.unpackEncodedString(DFA38_acceptS);
    static final short[] DFA38_special = DFA.unpackEncodedString(DFA38_specialS);
    static final short[][] DFA38_transition;

    static {
        int numStates = DFA38_transitionS.length;
        DFA38_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA38_transition[i] = DFA.unpackEncodedString(DFA38_transitionS[i]);
        }
    }

    class DFA38 extends DFA {

        public DFA38(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 38;
            this.eot = DFA38_eot;
            this.eof = DFA38_eof;
            this.min = DFA38_min;
            this.max = DFA38_max;
            this.accept = DFA38_accept;
            this.special = DFA38_special;
            this.transition = DFA38_transition;
        }
        public String getDescription() {
            return "()* loopback of 190:17: ( ( selector )=> selector )*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
            int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA38_0 = input.LA(1);

                         
                        int index38_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA38_0==EOF||LA38_0==FLOAT||(LA38_0>=HEX && LA38_0<=DECIMAL)||LA38_0==STRING||(LA38_0>=BOOL && LA38_0<=INCR)||(LA38_0>=SEMICOLON && LA38_0<=RIGHT_PAREN)||LA38_0==RIGHT_SQUARE||(LA38_0>=RIGHT_CURLY && LA38_0<=COMMA)||(LA38_0>=DOUBLE_AMPER && LA38_0<=PLUS)||(LA38_0>=ID && LA38_0<=DIV)) ) {s = 1;}

                        else if ( (LA38_0==LEFT_SQUARE) ) {s = 33;}

                        else if ( (LA38_0==DOT) && (synpred16_DRLExpressions())) {s = 44;}

                         
                        input.seek(index38_0);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA38_33 = input.LA(1);

                         
                        int index38_33 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred16_DRLExpressions()) ) {s = 44;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index38_33);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 38, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA39_eotS =
        "\55\uffff";
    static final String DFA39_eofS =
        "\1\2\54\uffff";
    static final String DFA39_minS =
        "\1\10\1\0\24\uffff\1\0\26\uffff";
    static final String DFA39_maxS =
        "\1\103\1\0\24\uffff\1\0\26\uffff";
    static final String DFA39_acceptS =
        "\2\uffff\1\2\51\uffff\1\1";
    static final String DFA39_specialS =
        "\1\uffff\1\0\24\uffff\1\1\26\uffff}>";
    static final String[] DFA39_transitionS = {
            "\1\2\2\uffff\2\2\1\uffff\1\2\3\uffff\13\2\1\26\1\1\1\uffff\15"+
            "\2\1\uffff\2\2\1\uffff\14\2\5\uffff\2\2",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA39_eot = DFA.unpackEncodedString(DFA39_eotS);
    static final short[] DFA39_eof = DFA.unpackEncodedString(DFA39_eofS);
    static final char[] DFA39_min = DFA.unpackEncodedStringToUnsignedChars(DFA39_minS);
    static final char[] DFA39_max = DFA.unpackEncodedStringToUnsignedChars(DFA39_maxS);
    static final short[] DFA39_accept = DFA.unpackEncodedString(DFA39_acceptS);
    static final short[] DFA39_special = DFA.unpackEncodedString(DFA39_specialS);
    static final short[][] DFA39_transition;

    static {
        int numStates = DFA39_transitionS.length;
        DFA39_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA39_transition[i] = DFA.unpackEncodedString(DFA39_transitionS[i]);
        }
    }

    class DFA39 extends DFA {

        public DFA39(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 39;
            this.eot = DFA39_eot;
            this.eof = DFA39_eof;
            this.min = DFA39_min;
            this.max = DFA39_max;
            this.accept = DFA39_accept;
            this.special = DFA39_special;
            this.transition = DFA39_transition;
        }
        public String getDescription() {
            return "190:41: ( ( INCR | DECR )=> ( INCR | DECR ) )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
            int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA39_1 = input.LA(1);

                         
                        int index39_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred17_DRLExpressions()) ) {s = 44;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index39_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA39_22 = input.LA(1);

                         
                        int index39_22 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred17_DRLExpressions()) ) {s = 44;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index39_22);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 39, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA42_eotS =
        "\12\uffff";
    static final String DFA42_eofS =
        "\12\uffff";
    static final String DFA42_minS =
        "\1\102\1\0\10\uffff";
    static final String DFA42_maxS =
        "\1\102\1\0\10\uffff";
    static final String DFA42_acceptS =
        "\2\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10";
    static final String DFA42_specialS =
        "\1\1\1\0\10\uffff}>";
    static final String[] DFA42_transitionS = {
            "\1\1",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA42_eot = DFA.unpackEncodedString(DFA42_eotS);
    static final short[] DFA42_eof = DFA.unpackEncodedString(DFA42_eofS);
    static final char[] DFA42_min = DFA.unpackEncodedStringToUnsignedChars(DFA42_minS);
    static final char[] DFA42_max = DFA.unpackEncodedStringToUnsignedChars(DFA42_maxS);
    static final short[] DFA42_accept = DFA.unpackEncodedString(DFA42_acceptS);
    static final short[] DFA42_special = DFA.unpackEncodedString(DFA42_specialS);
    static final short[][] DFA42_transition;

    static {
        int numStates = DFA42_transitionS.length;
        DFA42_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA42_transition[i] = DFA.unpackEncodedString(DFA42_transitionS[i]);
        }
    }

    class DFA42 extends DFA {

        public DFA42(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 42;
            this.eot = DFA42_eot;
            this.eof = DFA42_eof;
            this.min = DFA42_min;
            this.max = DFA42_max;
            this.accept = DFA42_accept;
            this.special = DFA42_special;
            this.transition = DFA42_transition;
        }
        public String getDescription() {
            return "198:1: primitiveType : ( boolean_key | char_key | byte_key | short_key | int_key | long_key | float_key | double_key );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
            int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA42_1 = input.LA(1);

                         
                        int index42_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (((helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF)))) ) {s = 2;}

                        else if ( (((helper.validateIdentifierKey(DroolsSoftKeywords.CHAR)))) ) {s = 3;}

                        else if ( (((helper.validateIdentifierKey(DroolsSoftKeywords.BYTE)))) ) {s = 4;}

                        else if ( (((helper.validateIdentifierKey(DroolsSoftKeywords.SHORT)))) ) {s = 5;}

                        else if ( (((helper.validateIdentifierKey(DroolsSoftKeywords.INT)))) ) {s = 6;}

                        else if ( (((helper.validateIdentifierKey(DroolsSoftKeywords.LONG)))) ) {s = 7;}

                        else if ( (((helper.validateIdentifierKey(DroolsSoftKeywords.FLOAT)))) ) {s = 8;}

                        else if ( (((helper.validateIdentifierKey(DroolsSoftKeywords.DOUBLE)))) ) {s = 9;}

                         
                        input.seek(index42_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA42_0 = input.LA(1);

                         
                        int index42_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA42_0==ID) && ((((helper.validateIdentifierKey(DroolsSoftKeywords.SHORT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.LONG)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF)))||((helper.validateIdentifierKey(DroolsSoftKeywords.BYTE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.FLOAT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.DOUBLE)))||((helper.validateIdentifierKey(DroolsSoftKeywords.INT)))||((helper.validateIdentifierKey(DroolsSoftKeywords.CHAR)))))) {s = 1;}

                         
                        input.seek(index42_0);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 42, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA47_eotS =
        "\21\uffff";
    static final String DFA47_eofS =
        "\21\uffff";
    static final String DFA47_minS =
        "\1\10\10\uffff\2\0\6\uffff";
    static final String DFA47_maxS =
        "\1\102\10\uffff\2\0\6\uffff";
    static final String DFA47_acceptS =
        "\1\uffff\1\1\1\2\6\3\2\uffff\1\4\1\5\1\6\1\11\1\7\1\10";
    static final String DFA47_specialS =
        "\1\0\10\uffff\1\1\1\2\6\uffff}>";
    static final String[] DFA47_transitionS = {
            "\1\6\2\uffff\1\5\1\4\1\uffff\1\3\3\uffff\1\7\1\10\23\uffff\1"+
            "\2\1\uffff\1\1\1\uffff\1\12\26\uffff\1\11",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA47_eot = DFA.unpackEncodedString(DFA47_eotS);
    static final short[] DFA47_eof = DFA.unpackEncodedString(DFA47_eofS);
    static final char[] DFA47_min = DFA.unpackEncodedStringToUnsignedChars(DFA47_minS);
    static final char[] DFA47_max = DFA.unpackEncodedStringToUnsignedChars(DFA47_maxS);
    static final short[] DFA47_accept = DFA.unpackEncodedString(DFA47_acceptS);
    static final short[] DFA47_special = DFA.unpackEncodedString(DFA47_specialS);
    static final short[][] DFA47_transition;

    static {
        int numStates = DFA47_transitionS.length;
        DFA47_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA47_transition[i] = DFA.unpackEncodedString(DFA47_transitionS[i]);
        }
    }

    class DFA47 extends DFA {

        public DFA47(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 47;
            this.eot = DFA47_eot;
            this.eof = DFA47_eof;
            this.min = DFA47_min;
            this.max = DFA47_max;
            this.accept = DFA47_accept;
            this.special = DFA47_special;
            this.transition = DFA47_transition;
        }
        public String getDescription() {
            return "209:1: primary : ( ( parExpression )=> parExpression | ( nonWildcardTypeArguments )=> nonWildcardTypeArguments ( explicitGenericInvocationSuffix | this_key arguments ) | ( literal )=> literal | ( super_key )=> super_key superSuffix | ( new_key )=> new_key creator | ( primitiveType )=> primitiveType ( LEFT_SQUARE RIGHT_SQUARE )* DOT class_key | ( inlineMapExpression )=> inlineMapExpression | ( inlineListExpression )=> inlineListExpression | ( ID )=> ID ( ( DOT ID )=> DOT ID )* ( ( identifierSuffix )=> identifierSuffix )? );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
            int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA47_0 = input.LA(1);

                         
                        int index47_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA47_0==LEFT_PAREN) && (synpred20_DRLExpressions())) {s = 1;}

                        else if ( (LA47_0==LESS) && (synpred21_DRLExpressions())) {s = 2;}

                        else if ( (LA47_0==STRING) && (synpred22_DRLExpressions())) {s = 3;}

                        else if ( (LA47_0==DECIMAL) && (synpred22_DRLExpressions())) {s = 4;}

                        else if ( (LA47_0==HEX) && (synpred22_DRLExpressions())) {s = 5;}

                        else if ( (LA47_0==FLOAT) && (synpred22_DRLExpressions())) {s = 6;}

                        else if ( (LA47_0==BOOL) && (synpred22_DRLExpressions())) {s = 7;}

                        else if ( (LA47_0==NULL) && (synpred22_DRLExpressions())) {s = 8;}

                        else if ( (LA47_0==ID) ) {s = 9;}

                        else if ( (LA47_0==LEFT_SQUARE) ) {s = 10;}

                         
                        input.seek(index47_0);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA47_9 = input.LA(1);

                         
                        int index47_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((synpred23_DRLExpressions()&&((helper.validateIdentifierKey(DroolsSoftKeywords.SUPER))))) ) {s = 11;}

                        else if ( ((synpred24_DRLExpressions()&&((helper.validateIdentifierKey(DroolsSoftKeywords.NEW))))) ) {s = 12;}

                        else if ( (((synpred25_DRLExpressions()&&((helper.validateIdentifierKey(DroolsSoftKeywords.INSTANCEOF))))||(synpred25_DRLExpressions()&&((helper.validateIdentifierKey(DroolsSoftKeywords.FLOAT))))||(synpred25_DRLExpressions()&&((helper.validateIdentifierKey(DroolsSoftKeywords.BYTE))))||(synpred25_DRLExpressions()&&((helper.validateIdentifierKey(DroolsSoftKeywords.SHORT))))||(synpred25_DRLExpressions()&&((helper.validateIdentifierKey(DroolsSoftKeywords.INT))))||(synpred25_DRLExpressions()&&((helper.validateIdentifierKey(DroolsSoftKeywords.CHAR))))||(synpred25_DRLExpressions()&&((helper.validateIdentifierKey(DroolsSoftKeywords.DOUBLE))))||(synpred25_DRLExpressions()&&((helper.validateIdentifierKey(DroolsSoftKeywords.LONG)))))) ) {s = 13;}

                        else if ( (synpred28_DRLExpressions()) ) {s = 14;}

                         
                        input.seek(index47_9);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA47_10 = input.LA(1);

                         
                        int index47_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred26_DRLExpressions()) ) {s = 15;}

                        else if ( (synpred27_DRLExpressions()) ) {s = 16;}

                         
                        input.seek(index47_10);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 47, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA46_eotS =
        "\56\uffff";
    static final String DFA46_eofS =
        "\1\3\55\uffff";
    static final String DFA46_minS =
        "\1\10\2\0\53\uffff";
    static final String DFA46_maxS =
        "\1\103\2\0\53\uffff";
    static final String DFA46_acceptS =
        "\3\uffff\1\2\51\uffff\1\1";
    static final String DFA46_specialS =
        "\1\uffff\1\0\1\1\53\uffff}>";
    static final String[] DFA46_transitionS = {
            "\1\3\2\uffff\2\3\1\uffff\1\3\3\uffff\15\3\1\uffff\11\3\1\2\1"+
            "\3\1\1\1\3\1\uffff\17\3\5\uffff\2\3",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA46_eot = DFA.unpackEncodedString(DFA46_eotS);
    static final short[] DFA46_eof = DFA.unpackEncodedString(DFA46_eofS);
    static final char[] DFA46_min = DFA.unpackEncodedStringToUnsignedChars(DFA46_minS);
    static final char[] DFA46_max = DFA.unpackEncodedStringToUnsignedChars(DFA46_maxS);
    static final short[] DFA46_accept = DFA.unpackEncodedString(DFA46_acceptS);
    static final short[] DFA46_special = DFA.unpackEncodedString(DFA46_specialS);
    static final short[][] DFA46_transition;

    static {
        int numStates = DFA46_transitionS.length;
        DFA46_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA46_transition[i] = DFA.unpackEncodedString(DFA46_transitionS[i]);
        }
    }

    class DFA46 extends DFA {

        public DFA46(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 46;
            this.eot = DFA46_eot;
            this.eof = DFA46_eof;
            this.min = DFA46_min;
            this.max = DFA46_max;
            this.accept = DFA46_accept;
            this.special = DFA46_special;
            this.transition = DFA46_transition;
        }
        public String getDescription() {
            return "220:38: ( ( identifierSuffix )=> identifierSuffix )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
            int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA46_1 = input.LA(1);

                         
                        int index46_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_DRLExpressions()) ) {s = 45;}

                        else if ( (true) ) {s = 3;}

                         
                        input.seek(index46_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA46_2 = input.LA(1);

                         
                        int index46_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_DRLExpressions()) ) {s = 45;}

                        else if ( (true) ) {s = 3;}

                         
                        input.seek(index46_2);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 46, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA52_eotS =
        "\56\uffff";
    static final String DFA52_eofS =
        "\1\1\55\uffff";
    static final String DFA52_minS =
        "\1\10\40\uffff\1\0\14\uffff";
    static final String DFA52_maxS =
        "\1\103\40\uffff\1\0\14\uffff";
    static final String DFA52_acceptS =
        "\1\uffff\1\2\53\uffff\1\1";
    static final String DFA52_specialS =
        "\41\uffff\1\0\14\uffff}>";
    static final String[] DFA52_transitionS = {
            "\1\1\2\uffff\2\1\1\uffff\1\1\3\uffff\15\1\1\uffff\13\1\1\41"+
            "\1\1\1\uffff\17\1\5\uffff\2\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA52_eot = DFA.unpackEncodedString(DFA52_eotS);
    static final short[] DFA52_eof = DFA.unpackEncodedString(DFA52_eofS);
    static final char[] DFA52_min = DFA.unpackEncodedStringToUnsignedChars(DFA52_minS);
    static final char[] DFA52_max = DFA.unpackEncodedStringToUnsignedChars(DFA52_maxS);
    static final short[] DFA52_accept = DFA.unpackEncodedString(DFA52_acceptS);
    static final short[] DFA52_special = DFA.unpackEncodedString(DFA52_specialS);
    static final short[][] DFA52_transition;

    static {
        int numStates = DFA52_transitionS.length;
        DFA52_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA52_transition[i] = DFA.unpackEncodedString(DFA52_transitionS[i]);
        }
    }

    class DFA52 extends DFA {

        public DFA52(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 52;
            this.eot = DFA52_eot;
            this.eof = DFA52_eof;
            this.min = DFA52_min;
            this.max = DFA52_max;
            this.accept = DFA52_accept;
            this.special = DFA52_special;
            this.transition = DFA52_transition;
        }
        public String getDescription() {
            return "()+ loopback of 245:7: ( ( LEFT_SQUARE )=> LEFT_SQUARE expression RIGHT_SQUARE )+";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
            int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA52_33 = input.LA(1);

                         
                        int index52_33 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred32_DRLExpressions()) ) {s = 45;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index52_33);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 52, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA61_eotS =
        "\56\uffff";
    static final String DFA61_eofS =
        "\1\2\55\uffff";
    static final String DFA61_minS =
        "\1\10\1\0\54\uffff";
    static final String DFA61_maxS =
        "\1\103\1\0\54\uffff";
    static final String DFA61_acceptS =
        "\2\uffff\1\2\52\uffff\1\1";
    static final String DFA61_specialS =
        "\1\uffff\1\0\54\uffff}>";
    static final String[] DFA61_transitionS = {
            "\1\2\2\uffff\2\2\1\uffff\1\2\3\uffff\15\2\1\uffff\13\2\1\1\1"+
            "\2\1\uffff\17\2\5\uffff\2\2",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA61_eot = DFA.unpackEncodedString(DFA61_eotS);
    static final short[] DFA61_eof = DFA.unpackEncodedString(DFA61_eofS);
    static final char[] DFA61_min = DFA.unpackEncodedStringToUnsignedChars(DFA61_minS);
    static final char[] DFA61_max = DFA.unpackEncodedStringToUnsignedChars(DFA61_maxS);
    static final short[] DFA61_accept = DFA.unpackEncodedString(DFA61_acceptS);
    static final short[] DFA61_special = DFA.unpackEncodedString(DFA61_specialS);
    static final short[][] DFA61_transition;

    static {
        int numStates = DFA61_transitionS.length;
        DFA61_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA61_transition[i] = DFA.unpackEncodedString(DFA61_transitionS[i]);
        }
    }

    class DFA61 extends DFA {

        public DFA61(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 61;
            this.eot = DFA61_eot;
            this.eof = DFA61_eof;
            this.min = DFA61_min;
            this.max = DFA61_max;
            this.accept = DFA61_accept;
            this.special = DFA61_special;
            this.transition = DFA61_transition;
        }
        public String getDescription() {
            return "()* loopback of 272:37: ({...}? => LEFT_SQUARE expression RIGHT_SQUARE )*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
            int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA61_1 = input.LA(1);

                         
                        int index61_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((!helper.validateLT(2,"]"))) ) {s = 45;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index61_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 61, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA70_eotS =
        "\56\uffff";
    static final String DFA70_eofS =
        "\1\2\55\uffff";
    static final String DFA70_minS =
        "\1\10\1\0\54\uffff";
    static final String DFA70_maxS =
        "\1\103\1\0\54\uffff";
    static final String DFA70_acceptS =
        "\2\uffff\1\2\52\uffff\1\1";
    static final String DFA70_specialS =
        "\1\uffff\1\0\54\uffff}>";
    static final String[] DFA70_transitionS = {
            "\1\2\2\uffff\2\2\1\uffff\1\2\3\uffff\15\2\1\uffff\11\2\1\1\3"+
            "\2\1\uffff\17\2\5\uffff\2\2",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA70_eot = DFA.unpackEncodedString(DFA70_eotS);
    static final short[] DFA70_eof = DFA.unpackEncodedString(DFA70_eofS);
    static final char[] DFA70_min = DFA.unpackEncodedStringToUnsignedChars(DFA70_minS);
    static final char[] DFA70_max = DFA.unpackEncodedStringToUnsignedChars(DFA70_maxS);
    static final short[] DFA70_accept = DFA.unpackEncodedString(DFA70_acceptS);
    static final short[] DFA70_special = DFA.unpackEncodedString(DFA70_specialS);
    static final short[][] DFA70_transition;

    static {
        int numStates = DFA70_transitionS.length;
        DFA70_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA70_transition[i] = DFA.unpackEncodedString(DFA70_transitionS[i]);
        }
    }

    class DFA70 extends DFA {

        public DFA70(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 70;
            this.eot = DFA70_eot;
            this.eof = DFA70_eof;
            this.min = DFA70_min;
            this.max = DFA70_max;
            this.accept = DFA70_accept;
            this.special = DFA70_special;
            this.transition = DFA70_transition;
        }
        public String getDescription() {
            return "305:23: ( ( LEFT_PAREN )=> arguments )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
            int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA70_1 = input.LA(1);

                         
                        int index70_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred37_DRLExpressions()) ) {s = 45;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index70_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 70, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA72_eotS =
        "\56\uffff";
    static final String DFA72_eofS =
        "\1\2\55\uffff";
    static final String DFA72_minS =
        "\1\10\1\0\54\uffff";
    static final String DFA72_maxS =
        "\1\103\1\0\54\uffff";
    static final String DFA72_acceptS =
        "\2\uffff\1\2\52\uffff\1\1";
    static final String DFA72_specialS =
        "\1\uffff\1\0\54\uffff}>";
    static final String[] DFA72_transitionS = {
            "\1\2\2\uffff\2\2\1\uffff\1\2\3\uffff\15\2\1\uffff\11\2\1\1\3"+
            "\2\1\uffff\17\2\5\uffff\2\2",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA72_eot = DFA.unpackEncodedString(DFA72_eotS);
    static final short[] DFA72_eof = DFA.unpackEncodedString(DFA72_eofS);
    static final char[] DFA72_min = DFA.unpackEncodedStringToUnsignedChars(DFA72_minS);
    static final char[] DFA72_max = DFA.unpackEncodedStringToUnsignedChars(DFA72_maxS);
    static final short[] DFA72_accept = DFA.unpackEncodedString(DFA72_acceptS);
    static final short[] DFA72_special = DFA.unpackEncodedString(DFA72_specialS);
    static final short[][] DFA72_transition;

    static {
        int numStates = DFA72_transitionS.length;
        DFA72_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA72_transition[i] = DFA.unpackEncodedString(DFA72_transitionS[i]);
        }
    }

    class DFA72 extends DFA {

        public DFA72(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 72;
            this.eot = DFA72_eot;
            this.eof = DFA72_eof;
            this.min = DFA72_min;
            this.max = DFA72_max;
            this.accept = DFA72_accept;
            this.special = DFA72_special;
            this.transition = DFA72_transition;
        }
        public String getDescription() {
            return "312:14: ( ( LEFT_PAREN )=> arguments )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
            int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA72_1 = input.LA(1);

                         
                        int index72_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred39_DRLExpressions()) ) {s = 45;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index72_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 72, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA76_eotS =
        "\17\uffff";
    static final String DFA76_eofS =
        "\17\uffff";
    static final String DFA76_minS =
        "\1\25\12\uffff\2\46\2\uffff";
    static final String DFA76_maxS =
        "\1\50\12\uffff\1\46\1\50\2\uffff";
    static final String DFA76_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\2\uffff\1\13"+
        "\1\14";
    static final String DFA76_specialS =
        "\14\uffff\1\0\2\uffff}>";
    static final String[] DFA76_transitionS = {
            "\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\11\uffff\1\13\1\12\1\1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\14",
            "\1\15\1\uffff\1\16",
            "",
            ""
    };

    static final short[] DFA76_eot = DFA.unpackEncodedString(DFA76_eotS);
    static final short[] DFA76_eof = DFA.unpackEncodedString(DFA76_eofS);
    static final char[] DFA76_min = DFA.unpackEncodedStringToUnsignedChars(DFA76_minS);
    static final char[] DFA76_max = DFA.unpackEncodedStringToUnsignedChars(DFA76_maxS);
    static final short[] DFA76_accept = DFA.unpackEncodedString(DFA76_acceptS);
    static final short[] DFA76_special = DFA.unpackEncodedString(DFA76_specialS);
    static final short[][] DFA76_transition;

    static {
        int numStates = DFA76_transitionS.length;
        DFA76_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA76_transition[i] = DFA.unpackEncodedString(DFA76_transitionS[i]);
        }
    }

    class DFA76 extends DFA {

        public DFA76(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 76;
            this.eot = DFA76_eot;
            this.eof = DFA76_eof;
            this.min = DFA76_min;
            this.max = DFA76_max;
            this.accept = DFA76_accept;
            this.special = DFA76_special;
            this.transition = DFA76_transition;
        }
        public String getDescription() {
            return "323:1: assignmentOperator : ( EQUALS_ASSIGN | PLUS_ASSIGN | MINUS_ASSIGN | MULT_ASSIGN | DIV_ASSIGN | AND_ASSIGN | OR_ASSIGN | XOR_ASSIGN | MOD_ASSIGN | LESS LESS EQUALS_ASSIGN | ( GREATER GREATER GREATER )=> GREATER GREATER GREATER EQUALS_ASSIGN | ( GREATER GREATER )=> GREATER GREATER EQUALS_ASSIGN );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
            int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA76_12 = input.LA(1);

                         
                        int index76_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA76_12==GREATER) && (synpred40_DRLExpressions())) {s = 13;}

                        else if ( (LA76_12==EQUALS_ASSIGN) && (synpred41_DRLExpressions())) {s = 14;}

                         
                        input.seek(index76_12);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 76, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_STRING_in_literal74 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECIMAL_in_literal86 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HEX_in_literal95 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_literal108 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOL_in_literal119 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NULL_in_literal133 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_typeList153 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_typeList156 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_typeList158 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_primitiveType_in_type181 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_LEFT_SQUARE_in_type191 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_RIGHT_SQUARE_in_type193 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_ID_in_type204 = new BitSet(new long[]{0x0001088000000002L});
    public static final BitSet FOLLOW_typeArguments_in_type211 = new BitSet(new long[]{0x0001080000000002L});
    public static final BitSet FOLLOW_DOT_in_type216 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_type218 = new BitSet(new long[]{0x0001088000000002L});
    public static final BitSet FOLLOW_typeArguments_in_type225 = new BitSet(new long[]{0x0001080000000002L});
    public static final BitSet FOLLOW_LEFT_SQUARE_in_type240 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_RIGHT_SQUARE_in_type242 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_LESS_in_typeArguments257 = new BitSet(new long[]{0x0008000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_typeArgument_in_typeArguments259 = new BitSet(new long[]{0x0000804000000000L});
    public static final BitSet FOLLOW_COMMA_in_typeArguments262 = new BitSet(new long[]{0x0008000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_typeArgument_in_typeArguments264 = new BitSet(new long[]{0x0000804000000000L});
    public static final BitSet FOLLOW_GREATER_in_typeArguments268 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_typeArgument280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUESTION_in_typeArgument285 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000004L});
    public static final BitSet FOLLOW_extends_key_in_typeArgument289 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_super_key_in_typeArgument293 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_typeArgument296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_dummy314 = new BitSet(new long[]{0x01C6000100100000L});
    public static final BitSet FOLLOW_set_in_dummy316 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionalExpression_in_expression356 = new BitSet(new long[]{0x000001C01FE00002L});
    public static final BitSet FOLLOW_assignmentOperator_in_expression365 = new BitSet(new long[]{0x18300A80600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expression367 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionalOrExpression_in_conditionalExpression382 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_QUESTION_in_conditionalExpression386 = new BitSet(new long[]{0x18300A80600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_conditionalExpression388 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_COLON_in_conditionalExpression390 = new BitSet(new long[]{0x18300A80600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_conditionalExpression392 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionalAndExpression_in_conditionalOrExpression408 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_DOUBLE_PIPE_in_conditionalOrExpression412 = new BitSet(new long[]{0x18300AFC600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_operator_in_conditionalOrExpression427 = new BitSet(new long[]{0x18300A80600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_shiftExpression_in_conditionalOrExpression429 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_conditionalAndExpression_in_conditionalOrExpression438 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_inclusiveOrExpression_in_conditionalAndExpression456 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_DOUBLE_AMPER_in_conditionalAndExpression460 = new BitSet(new long[]{0x18300AFC600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_operator_in_conditionalAndExpression474 = new BitSet(new long[]{0x18300A80600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_shiftExpression_in_conditionalAndExpression476 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_inclusiveOrExpression_in_conditionalAndExpression484 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_exclusiveOrExpression_in_inclusiveOrExpression501 = new BitSet(new long[]{0x0040000000000002L});
    public static final BitSet FOLLOW_PIPE_in_inclusiveOrExpression505 = new BitSet(new long[]{0x18300AFC600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_operator_in_inclusiveOrExpression518 = new BitSet(new long[]{0x18300A80600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_shiftExpression_in_inclusiveOrExpression520 = new BitSet(new long[]{0x0040000000000002L});
    public static final BitSet FOLLOW_exclusiveOrExpression_in_inclusiveOrExpression528 = new BitSet(new long[]{0x0040000000000002L});
    public static final BitSet FOLLOW_andExpression_in_exclusiveOrExpression545 = new BitSet(new long[]{0x0100000000000002L});
    public static final BitSet FOLLOW_XOR_in_exclusiveOrExpression549 = new BitSet(new long[]{0x18300AFC600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_operator_in_exclusiveOrExpression563 = new BitSet(new long[]{0x18300A80600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_shiftExpression_in_exclusiveOrExpression565 = new BitSet(new long[]{0x0100000000000002L});
    public static final BitSet FOLLOW_andExpression_in_exclusiveOrExpression573 = new BitSet(new long[]{0x0100000000000002L});
    public static final BitSet FOLLOW_equalityExpression_in_andExpression591 = new BitSet(new long[]{0x0080000000000002L});
    public static final BitSet FOLLOW_AMPER_in_andExpression595 = new BitSet(new long[]{0x18300AFC600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_operator_in_andExpression609 = new BitSet(new long[]{0x18300A80600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_shiftExpression_in_andExpression611 = new BitSet(new long[]{0x0080000000000002L});
    public static final BitSet FOLLOW_equalityExpression_in_andExpression619 = new BitSet(new long[]{0x0080000000000002L});
    public static final BitSet FOLLOW_instanceOfExpression_in_equalityExpression637 = new BitSet(new long[]{0x0000000C00000002L});
    public static final BitSet FOLLOW_set_in_equalityExpression641 = new BitSet(new long[]{0x18300A80600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_instanceOfExpression_in_equalityExpression651 = new BitSet(new long[]{0x0000000C00000002L});
    public static final BitSet FOLLOW_inExpression_in_instanceOfExpression667 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000004L});
    public static final BitSet FOLLOW_instanceof_key_in_instanceOfExpression670 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_instanceOfExpression672 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_relationalExpression_in_inExpression687 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000004L});
    public static final BitSet FOLLOW_not_key_in_inExpression690 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_in_key_in_inExpression693 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_inExpression695 = new BitSet(new long[]{0x18300A80600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_inExpression697 = new BitSet(new long[]{0x0000840000000000L});
    public static final BitSet FOLLOW_COMMA_in_inExpression700 = new BitSet(new long[]{0x18300A80600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_inExpression702 = new BitSet(new long[]{0x0000840000000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_inExpression706 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_shiftExpression_in_relationalExpression722 = new BitSet(new long[]{0x000000FC00000002L,0x0000000000000004L});
    public static final BitSet FOLLOW_relationalOp_in_relationalExpression731 = new BitSet(new long[]{0x18300A80600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_shiftExpression_in_relationalExpression733 = new BitSet(new long[]{0x000000FC00000002L,0x0000000000000004L});
    public static final BitSet FOLLOW_EQUALS_in_operator752 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_EQUALS_in_operator760 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_relationalOp_in_operator768 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESS_EQUALS_in_relationalOp794 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GREATER_EQUALS_in_relationalOp802 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESS_in_relationalOp811 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GREATER_in_relationalOp820 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_not_key_in_relationalOp828 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_neg_operator_key_in_relationalOp830 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_operator_key_in_relationalOp838 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_additiveExpression_in_shiftExpression856 = new BitSet(new long[]{0x000000C000000002L});
    public static final BitSet FOLLOW_shiftOp_in_shiftExpression864 = new BitSet(new long[]{0x18300A80600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_additiveExpression_in_shiftExpression866 = new BitSet(new long[]{0x000000C000000002L});
    public static final BitSet FOLLOW_LESS_in_shiftOp881 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_LESS_in_shiftOp883 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GREATER_in_shiftOp887 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_GREATER_in_shiftOp889 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_GREATER_in_shiftOp891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GREATER_in_shiftOp895 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_GREATER_in_shiftOp897 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression913 = new BitSet(new long[]{0x1800000000000002L});
    public static final BitSet FOLLOW_set_in_additiveExpression924 = new BitSet(new long[]{0x18300A80600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression932 = new BitSet(new long[]{0x1800000000000002L});
    public static final BitSet FOLLOW_unaryExpression_in_multiplicativeExpression949 = new BitSet(new long[]{0x0600000000000002L,0x0000000000000008L});
    public static final BitSet FOLLOW_set_in_multiplicativeExpression953 = new BitSet(new long[]{0x18300A80600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_unaryExpression_in_multiplicativeExpression967 = new BitSet(new long[]{0x0600000000000002L,0x0000000000000008L});
    public static final BitSet FOLLOW_PLUS_in_unaryExpression987 = new BitSet(new long[]{0x18300A80600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_unaryExpression_in_unaryExpression989 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_unaryExpression997 = new BitSet(new long[]{0x18300A80600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_unaryExpression_in_unaryExpression999 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INCR_in_unaryExpression1009 = new BitSet(new long[]{0x18300A80600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_primary_in_unaryExpression1011 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECR_in_unaryExpression1021 = new BitSet(new long[]{0x18300A80600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_primary_in_unaryExpression1023 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unaryExpressionNotPlusMinus_in_unaryExpression1033 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TILDE_in_unaryExpressionNotPlusMinus1052 = new BitSet(new long[]{0x18300A80600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_unaryExpression_in_unaryExpressionNotPlusMinus1054 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEGATION_in_unaryExpressionNotPlusMinus1063 = new BitSet(new long[]{0x18300A80600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_unaryExpression_in_unaryExpressionNotPlusMinus1065 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_castExpression_in_unaryExpressionNotPlusMinus1079 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primary_in_unaryExpressionNotPlusMinus1089 = new BitSet(new long[]{0x0001080060000002L});
    public static final BitSet FOLLOW_selector_in_unaryExpressionNotPlusMinus1096 = new BitSet(new long[]{0x0001080060000002L});
    public static final BitSet FOLLOW_set_in_unaryExpressionNotPlusMinus1108 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_castExpression1144 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_primitiveType_in_castExpression1146 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_castExpression1148 = new BitSet(new long[]{0x18300A80600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_unaryExpression_in_castExpression1150 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_castExpression1167 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_castExpression1169 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_castExpression1171 = new BitSet(new long[]{0x18300A80600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_unaryExpressionNotPlusMinus_in_castExpression1173 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_boolean_key_in_primitiveType1194 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_char_key_in_primitiveType1202 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_byte_key_in_primitiveType1210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_short_key_in_primitiveType1218 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_int_key_in_primitiveType1226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_long_key_in_primitiveType1234 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_float_key_in_primitiveType1242 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_double_key_in_primitiveType1250 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parExpression_in_primary1272 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nonWildcardTypeArguments_in_primary1287 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_explicitGenericInvocationSuffix_in_primary1290 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_this_key_in_primary1294 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_arguments_in_primary1296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literal_in_primary1312 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_super_key_in_primary1332 = new BitSet(new long[]{0x0001020000000000L});
    public static final BitSet FOLLOW_superSuffix_in_primary1334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_new_key_in_primary1349 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_creator_in_primary1351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primitiveType_in_primary1366 = new BitSet(new long[]{0x0001080000000000L});
    public static final BitSet FOLLOW_LEFT_SQUARE_in_primary1369 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_RIGHT_SQUARE_in_primary1371 = new BitSet(new long[]{0x0001080000000000L});
    public static final BitSet FOLLOW_DOT_in_primary1375 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_class_key_in_primary1377 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_inlineMapExpression_in_primary1397 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_inlineListExpression_in_primary1412 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_primary1426 = new BitSet(new long[]{0x00010A0000000002L});
    public static final BitSet FOLLOW_DOT_in_primary1435 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_primary1437 = new BitSet(new long[]{0x00010A0000000002L});
    public static final BitSet FOLLOW_identifierSuffix_in_primary1446 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_SQUARE_in_inlineListExpression1467 = new BitSet(new long[]{0x18301A80600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_expressionList_in_inlineListExpression1469 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_RIGHT_SQUARE_in_inlineListExpression1472 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_SQUARE_in_inlineMapExpression1494 = new BitSet(new long[]{0x18300A80600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_mapExpressionList_in_inlineMapExpression1496 = new BitSet(new long[]{0x18301A80600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_RIGHT_SQUARE_in_inlineMapExpression1499 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_mapEntry_in_mapExpressionList1516 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_mapExpressionList1519 = new BitSet(new long[]{0x18300A80600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_mapEntry_in_mapExpressionList1521 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_expression_in_mapEntry1544 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_COLON_in_mapEntry1546 = new BitSet(new long[]{0x18300A80600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_mapEntry1548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_parExpression1562 = new BitSet(new long[]{0x18300A80600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_parExpression1564 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_parExpression1566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_SQUARE_in_identifierSuffix1588 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_RIGHT_SQUARE_in_identifierSuffix1590 = new BitSet(new long[]{0x0001080000000000L});
    public static final BitSet FOLLOW_DOT_in_identifierSuffix1594 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_class_key_in_identifierSuffix1596 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_SQUARE_in_identifierSuffix1611 = new BitSet(new long[]{0x18300A80600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_identifierSuffix1613 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_RIGHT_SQUARE_in_identifierSuffix1615 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_arguments_in_identifierSuffix1628 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nonWildcardTypeArguments_in_creator1646 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_createdName_in_creator1649 = new BitSet(new long[]{0x00000A0000000000L});
    public static final BitSet FOLLOW_arrayCreatorRest_in_creator1660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_classCreatorRest_in_creator1664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_createdName1676 = new BitSet(new long[]{0x0001008000000002L});
    public static final BitSet FOLLOW_typeArguments_in_createdName1678 = new BitSet(new long[]{0x0001000000000002L});
    public static final BitSet FOLLOW_DOT_in_createdName1691 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_createdName1693 = new BitSet(new long[]{0x0001008000000002L});
    public static final BitSet FOLLOW_typeArguments_in_createdName1695 = new BitSet(new long[]{0x0001000000000002L});
    public static final BitSet FOLLOW_primitiveType_in_createdName1710 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_innerCreator1725 = new BitSet(new long[]{0x00000A0000000000L});
    public static final BitSet FOLLOW_classCreatorRest_in_innerCreator1727 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_SQUARE_in_arrayCreatorRest1740 = new BitSet(new long[]{0x18301A80600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_RIGHT_SQUARE_in_arrayCreatorRest1748 = new BitSet(new long[]{0x0000280000000000L});
    public static final BitSet FOLLOW_LEFT_SQUARE_in_arrayCreatorRest1751 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_RIGHT_SQUARE_in_arrayCreatorRest1753 = new BitSet(new long[]{0x0000280000000000L});
    public static final BitSet FOLLOW_arrayInitializer_in_arrayCreatorRest1757 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_arrayCreatorRest1771 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_RIGHT_SQUARE_in_arrayCreatorRest1773 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_LEFT_SQUARE_in_arrayCreatorRest1778 = new BitSet(new long[]{0x18300A80600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_arrayCreatorRest1780 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_RIGHT_SQUARE_in_arrayCreatorRest1782 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_LEFT_SQUARE_in_arrayCreatorRest1794 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_RIGHT_SQUARE_in_arrayCreatorRest1796 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_arrayInitializer_in_variableInitializer1819 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_variableInitializer1830 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_CURLY_in_arrayInitializer1842 = new BitSet(new long[]{0x18306A80600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_variableInitializer_in_arrayInitializer1845 = new BitSet(new long[]{0x0000C00000000000L});
    public static final BitSet FOLLOW_COMMA_in_arrayInitializer1848 = new BitSet(new long[]{0x18302A80600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_variableInitializer_in_arrayInitializer1850 = new BitSet(new long[]{0x0000C00000000000L});
    public static final BitSet FOLLOW_COMMA_in_arrayInitializer1855 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_RIGHT_CURLY_in_arrayInitializer1862 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arguments_in_classCreatorRest1873 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nonWildcardTypeArguments_in_explicitGenericInvocation1886 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_arguments_in_explicitGenericInvocation1888 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESS_in_nonWildcardTypeArguments1900 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_typeList_in_nonWildcardTypeArguments1902 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_GREATER_in_nonWildcardTypeArguments1904 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_super_key_in_explicitGenericInvocationSuffix1916 = new BitSet(new long[]{0x0001020000000000L});
    public static final BitSet FOLLOW_superSuffix_in_explicitGenericInvocationSuffix1918 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_explicitGenericInvocationSuffix1926 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_arguments_in_explicitGenericInvocationSuffix1928 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_selector1947 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_super_key_in_selector1949 = new BitSet(new long[]{0x0001020000000000L});
    public static final BitSet FOLLOW_superSuffix_in_selector1951 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_selector1964 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_new_key_in_selector1966 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_nonWildcardTypeArguments_in_selector1969 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_innerCreator_in_selector1973 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_selector1986 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_selector1988 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_arguments_in_selector1997 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_SQUARE_in_selector2012 = new BitSet(new long[]{0x18300A80600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_selector2014 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_RIGHT_SQUARE_in_selector2016 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arguments_in_superSuffix2028 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_superSuffix2036 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_superSuffix2038 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_arguments_in_superSuffix2047 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_arguments2067 = new BitSet(new long[]{0x18300E80600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_expressionList_in_arguments2069 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_arguments2072 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_expressionList2086 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_COMMA_in_expressionList2089 = new BitSet(new long[]{0x18300A80600C5900L,0x0000000000000004L});
    public static final BitSet FOLLOW_expression_in_expressionList2091 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_EQUALS_ASSIGN_in_assignmentOperator2107 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUS_ASSIGN_in_assignmentOperator2115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_ASSIGN_in_assignmentOperator2123 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MULT_ASSIGN_in_assignmentOperator2131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIV_ASSIGN_in_assignmentOperator2139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AND_ASSIGN_in_assignmentOperator2147 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OR_ASSIGN_in_assignmentOperator2155 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_XOR_ASSIGN_in_assignmentOperator2163 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MOD_ASSIGN_in_assignmentOperator2171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESS_in_assignmentOperator2179 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_LESS_in_assignmentOperator2181 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_EQUALS_ASSIGN_in_assignmentOperator2183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GREATER_in_assignmentOperator2200 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_GREATER_in_assignmentOperator2202 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_GREATER_in_assignmentOperator2204 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_EQUALS_ASSIGN_in_assignmentOperator2206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GREATER_in_assignmentOperator2221 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_GREATER_in_assignmentOperator2223 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_EQUALS_ASSIGN_in_assignmentOperator2225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_extends_key2249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_super_key2270 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_instanceof_key2291 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_boolean_key2312 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_char_key2333 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_byte_key2354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_short_key2375 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_int_key2396 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_float_key2417 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_long_key2438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_double_key2459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_void_key2480 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_this_key2501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_class_key2522 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_new_key2543 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_not_key2564 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_in_key2586 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_operator_key2609 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_neg_operator_key2632 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primitiveType_in_synpred1_DRLExpressions174 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_SQUARE_in_synpred2_DRLExpressions185 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_RIGHT_SQUARE_in_synpred2_DRLExpressions187 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeArguments_in_synpred3_DRLExpressions208 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeArguments_in_synpred4_DRLExpressions222 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_SQUARE_in_synpred5_DRLExpressions234 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_RIGHT_SQUARE_in_synpred5_DRLExpressions236 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignmentOperator_in_synpred6_DRLExpressions360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_operator_in_synpred7_DRLExpressions423 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_operator_in_synpred8_DRLExpressions470 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_operator_in_synpred9_DRLExpressions514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_operator_in_synpred10_DRLExpressions559 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_operator_in_synpred11_DRLExpressions605 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_relationalOp_in_synpred12_DRLExpressions727 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_shiftOp_in_synpred13_DRLExpressions861 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_synpred14_DRLExpressions917 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_castExpression_in_synpred15_DRLExpressions1076 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_selector_in_synpred16_DRLExpressions1093 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_synpred17_DRLExpressions1101 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_synpred18_DRLExpressions1137 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_primitiveType_in_synpred18_DRLExpressions1139 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_synpred19_DRLExpressions1160 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_synpred19_DRLExpressions1162 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parExpression_in_synpred20_DRLExpressions1268 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nonWildcardTypeArguments_in_synpred21_DRLExpressions1283 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literal_in_synpred22_DRLExpressions1308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_super_key_in_synpred23_DRLExpressions1328 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_new_key_in_synpred24_DRLExpressions1345 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primitiveType_in_synpred25_DRLExpressions1362 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_inlineMapExpression_in_synpred26_DRLExpressions1393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_inlineListExpression_in_synpred27_DRLExpressions1408 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_synpred28_DRLExpressions1423 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_synpred29_DRLExpressions1430 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_synpred29_DRLExpressions1432 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_identifierSuffix_in_synpred30_DRLExpressions1443 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_SQUARE_in_synpred31_DRLExpressions1582 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_RIGHT_SQUARE_in_synpred31_DRLExpressions1584 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_SQUARE_in_synpred32_DRLExpressions1606 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_SQUARE_in_synpred33_DRLExpressions1788 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_RIGHT_SQUARE_in_synpred33_DRLExpressions1790 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_synpred34_DRLExpressions1942 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_super_key_in_synpred34_DRLExpressions1944 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_synpred35_DRLExpressions1959 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_new_key_in_synpred35_DRLExpressions1961 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_synpred36_DRLExpressions1981 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_synpred36_DRLExpressions1983 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_synpred37_DRLExpressions1992 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_SQUARE_in_synpred38_DRLExpressions2009 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_synpred39_DRLExpressions2042 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GREATER_in_synpred40_DRLExpressions2192 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_GREATER_in_synpred40_DRLExpressions2194 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_GREATER_in_synpred40_DRLExpressions2196 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GREATER_in_synpred41_DRLExpressions2215 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_GREATER_in_synpred41_DRLExpressions2217 = new BitSet(new long[]{0x0000000000000002L});

}
