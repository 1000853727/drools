// $ANTLR 3.1.1 src/main/resources/org/drools/lang/DRL.g 2009-01-25 11:44:45

	package org.drools.lang;
	
	import java.util.List;
	import java.util.LinkedList;
	import org.drools.compiler.DroolsParserException;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;

public class DRLParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "VT_COMPILATION_UNIT", "VT_FUNCTION_IMPORT", "VT_FACT", "VT_CONSTRAINTS", "VT_LABEL", "VT_QUERY_ID", "VT_TEMPLATE_ID", "VT_TYPE_DECLARE_ID", "VT_RULE_ID", "VT_ENTRYPOINT_ID", "VT_SLOT_ID", "VT_SLOT", "VT_RULE_ATTRIBUTES", "VT_RHS_CHUNK", "VT_CURLY_CHUNK", "VT_SQUARE_CHUNK", "VT_PAREN_CHUNK", "VT_BEHAVIOR", "VT_AND_IMPLICIT", "VT_AND_PREFIX", "VT_OR_PREFIX", "VT_AND_INFIX", "VT_OR_INFIX", "VT_ACCUMULATE_INIT_CLAUSE", "VT_ACCUMULATE_ID_CLAUSE", "VT_FROM_SOURCE", "VT_EXPRESSION_CHAIN", "VT_PATTERN", "VT_FACT_BINDING", "VT_FACT_OR", "VT_BIND_FIELD", "VT_FIELD", "VT_ACCESSOR_PATH", "VT_ACCESSOR_ELEMENT", "VT_DATA_TYPE", "VT_PATTERN_TYPE", "VT_PACKAGE_ID", "VT_IMPORT_ID", "VT_GLOBAL_ID", "VT_FUNCTION_ID", "VT_PARAM_LIST", "VK_DATE_EFFECTIVE", "VK_DATE_EXPIRES", "VK_LOCK_ON_ACTIVE", "VK_NO_LOOP", "VK_AUTO_FOCUS", "VK_ACTIVATION_GROUP", "VK_AGENDA_GROUP", "VK_RULEFLOW_GROUP", "VK_DURATION", "VK_DIALECT", "VK_SALIENCE", "VK_ENABLED", "VK_ATTRIBUTES", "VK_RULE", "VK_EXTEND", "VK_IMPORT", "VK_PACKAGE", "VK_TEMPLATE", "VK_QUERY", "VK_DECLARE", "VK_FUNCTION", "VK_GLOBAL", "VK_EVAL", "VK_ENTRY_POINT", "VK_NOT", "VK_IN", "VK_OR", "VK_AND", "VK_EXISTS", "VK_FORALL", "VK_ACTION", "VK_REVERSE", "VK_RESULT", "VK_OPERATOR", "VK_END", "VK_INIT", "SEMICOLON", "ID", "DOT", "DOT_STAR", "STRING", "LEFT_PAREN", "COMMA", "RIGHT_PAREN", "AT", "COLON", "EQUALS", "WHEN", "BOOL", "INT", "DOUBLE_PIPE", "DOUBLE_AMPER", "FROM", "OVER", "ACCUMULATE", "COLLECT", "ARROW", "EQUAL", "GREATER", "GREATER_EQUAL", "LESS", "LESS_EQUAL", "NOT_EQUAL", "FLOAT", "NULL", "LEFT_SQUARE", "RIGHT_SQUARE", "THEN", "LEFT_CURLY", "RIGHT_CURLY", "MISC", "EOL", "WS", "EscapeSequence", "HexDigit", "UnicodeEscape", "OctalEscape", "GRAVE_ACCENT", "SH_STYLE_SINGLE_LINE_COMMENT", "C_STYLE_SINGLE_LINE_COMMENT", "MULTI_LINE_COMMENT"
    };
    public static final int COMMA=87;
    public static final int VT_PATTERN_TYPE=39;
    public static final int VT_ACCUMULATE_ID_CLAUSE=28;
    public static final int VK_DIALECT=54;
    public static final int VK_FUNCTION=65;
    public static final int HexDigit=119;
    public static final int VK_ATTRIBUTES=57;
    public static final int VT_EXPRESSION_CHAIN=30;
    public static final int MISC=115;
    public static final int VT_AND_PREFIX=23;
    public static final int VK_QUERY=63;
    public static final int THEN=112;
    public static final int VK_AUTO_FOCUS=49;
    public static final int DOT=83;
    public static final int VK_IMPORT=60;
    public static final int VT_SLOT=15;
    public static final int VT_PACKAGE_ID=40;
    public static final int LEFT_SQUARE=110;
    public static final int SH_STYLE_SINGLE_LINE_COMMENT=123;
    public static final int VT_DATA_TYPE=38;
    public static final int VT_FACT=6;
    public static final int LEFT_CURLY=113;
    public static final int AT=89;
    public static final int DOUBLE_AMPER=96;
    public static final int LEFT_PAREN=86;
    public static final int VT_QUERY_ID=9;
    public static final int VT_ACCESSOR_PATH=36;
    public static final int VT_LABEL=8;
    public static final int WHEN=92;
    public static final int VT_ENTRYPOINT_ID=13;
    public static final int WS=117;
    public static final int VT_FIELD=35;
    public static final int VK_SALIENCE=55;
    public static final int OVER=98;
    public static final int VK_AND=72;
    public static final int STRING=85;
    public static final int VT_ACCESSOR_ELEMENT=37;
    public static final int VT_ACCUMULATE_INIT_CLAUSE=27;
    public static final int VK_GLOBAL=66;
    public static final int VK_REVERSE=76;
    public static final int VT_BEHAVIOR=21;
    public static final int GRAVE_ACCENT=122;
    public static final int VK_DURATION=53;
    public static final int VT_SQUARE_CHUNK=19;
    public static final int VK_FORALL=74;
    public static final int VT_PAREN_CHUNK=20;
    public static final int VT_COMPILATION_UNIT=4;
    public static final int COLLECT=100;
    public static final int VK_ENABLED=56;
    public static final int VK_RESULT=77;
    public static final int EQUALS=91;
    public static final int UnicodeEscape=120;
    public static final int VK_PACKAGE=61;
    public static final int VT_RULE_ID=12;
    public static final int EQUAL=102;
    public static final int VK_NO_LOOP=48;
    public static final int SEMICOLON=81;
    public static final int VK_TEMPLATE=62;
    public static final int VT_AND_IMPLICIT=22;
    public static final int NULL=109;
    public static final int COLON=90;
    public static final int MULTI_LINE_COMMENT=125;
    public static final int VT_RULE_ATTRIBUTES=16;
    public static final int RIGHT_SQUARE=111;
    public static final int VK_AGENDA_GROUP=51;
    public static final int VT_FACT_OR=33;
    public static final int VK_NOT=69;
    public static final int VK_DATE_EXPIRES=46;
    public static final int ARROW=101;
    public static final int FLOAT=108;
    public static final int VK_EXTEND=59;
    public static final int VT_SLOT_ID=14;
    public static final int VT_CURLY_CHUNK=18;
    public static final int VT_OR_PREFIX=24;
    public static final int DOUBLE_PIPE=95;
    public static final int VK_END=79;
    public static final int LESS=105;
    public static final int VT_TYPE_DECLARE_ID=11;
    public static final int VT_PATTERN=31;
    public static final int VK_DATE_EFFECTIVE=45;
    public static final int EscapeSequence=118;
    public static final int VK_EXISTS=73;
    public static final int INT=94;
    public static final int VT_BIND_FIELD=34;
    public static final int VK_RULE=58;
    public static final int VK_EVAL=67;
    public static final int GREATER=103;
    public static final int VT_FACT_BINDING=32;
    public static final int FROM=97;
    public static final int ID=82;
    public static final int NOT_EQUAL=107;
    public static final int RIGHT_CURLY=114;
    public static final int VK_OPERATOR=78;
    public static final int BOOL=93;
    public static final int VT_AND_INFIX=25;
    public static final int VT_PARAM_LIST=44;
    public static final int VK_ENTRY_POINT=68;
    public static final int VT_FROM_SOURCE=29;
    public static final int VK_LOCK_ON_ACTIVE=47;
    public static final int VT_FUNCTION_IMPORT=5;
    public static final int VK_IN=70;
    public static final int VT_RHS_CHUNK=17;
    public static final int GREATER_EQUAL=104;
    public static final int VT_OR_INFIX=26;
    public static final int DOT_STAR=84;
    public static final int VK_OR=71;
    public static final int VT_GLOBAL_ID=42;
    public static final int LESS_EQUAL=106;
    public static final int ACCUMULATE=99;
    public static final int VK_RULEFLOW_GROUP=52;
    public static final int VT_FUNCTION_ID=43;
    public static final int EOF=-1;
    public static final int VT_CONSTRAINTS=7;
    public static final int EOL=116;
    public static final int VT_IMPORT_ID=41;
    public static final int VK_ACTIVATION_GROUP=50;
    public static final int VK_INIT=80;
    public static final int OctalEscape=121;
    public static final int VK_ACTION=75;
    public static final int RIGHT_PAREN=88;
    public static final int VT_TEMPLATE_ID=10;
    public static final int C_STYLE_SINGLE_LINE_COMMENT=124;
    public static final int VK_DECLARE=64;

    // delegates
    // delegators


        public DRLParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public DRLParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return DRLParser.tokenNames; }
    public String getGrammarFileName() { return "src/main/resources/org/drools/lang/DRL.g"; }


    	private Stack<Map<DroolsParaphraseTypes, String>> paraphrases = new Stack<Map<DroolsParaphraseTypes, String>>();
    	private List<DroolsParserException> errors = new ArrayList<DroolsParserException>();
    	private DroolsParserExceptionFactory errorMessageFactory = new DroolsParserExceptionFactory(tokenNames, paraphrases);
    	private String source = "unknown";
    	private boolean lookaheadTest = false;
    	private LinkedList<DroolsSentence> editorInterface = null;
    	private boolean isEditorInterfaceEnabled = false;

    	public LinkedList<DroolsSentence> getEditorInterface(){
    		return editorInterface;
    	}

    	public void enableEditorInterface(){
    		isEditorInterfaceEnabled = true;
    	}

    	public void disableEditorInterface(){
    		isEditorInterfaceEnabled = false;
    	}

    	private void beginSentence(DroolsSentenceType sentenceType){
    		if (isEditorInterfaceEnabled) {
    			if (null == editorInterface) {
    				editorInterface = new LinkedList<DroolsSentence>();
    			}
    			DroolsSentence sentence = new DroolsSentence();
    			sentence.setType(sentenceType);
    			editorInterface.add(sentence);
    		}
    	}

    	private DroolsSentence getActiveSentence(){
    		return editorInterface.getLast();
    	}

    	private void emit(List tokens, DroolsEditorType editorType){
    		if (isEditorInterfaceEnabled && tokens != null) {		
    			for (Object activeObject : tokens){
    				emit((Token) activeObject, editorType);
    			}
    		}
    	}

    	private void emit(Token token, DroolsEditorType editorType){
    		if (isEditorInterfaceEnabled && token != null) {
    			((DroolsToken)token).setEditorType(editorType);
    			getActiveSentence().addContent((DroolsToken) token);
    		}
    	}

    	private void emit(boolean forceEmit, int activeContext){
    		if (isEditorInterfaceEnabled) {
    				getActiveSentence().addContent(activeContext);
    		}
    	}
    	
    	private void emit(int activeContext){
    		if (isEditorInterfaceEnabled) {
    			emit(false, activeContext);
    		}
    	}

    	private DroolsToken getLastTokenOnList(LinkedList list){
    		DroolsToken lastToken = null;
    		for (Object object : list) {
    			if (object instanceof DroolsToken) {
    				lastToken = (DroolsToken) object;
    			}
    		}
    		return lastToken;
    	}

    	private int getLastIntegerValue(LinkedList list) {
    		int lastIntergerValue = -1;
    		for (Object object : list) {
    			if (object instanceof Integer) {
    				lastIntergerValue = (Integer) object;
    			}
    		}
    		return lastIntergerValue;
    	}

    	private String retrieveLT(int LTNumber) {
          		if (null == input)
    			return null;
    		if (null == input.LT(LTNumber))
    			return null;
    		if (null == input.LT(LTNumber).getText())
    			return null;
    	
    		return input.LT(LTNumber).getText();
    	}

    	private boolean validateLT(int LTNumber, String text) {
    		String text2Validate = retrieveLT( LTNumber );
    		return text2Validate == null ? false : text2Validate.equalsIgnoreCase(text);
    	}
    	
    	private boolean isPluggableEvaluator( int offset, boolean negated ) {
    		String text2Validate = retrieveLT( offset );
    	        return text2Validate == null ? false : DroolsSoftKeywords.isOperator( text2Validate, negated );
    	}
    	
    	private boolean isPluggableEvaluator( boolean negated ) {
    	        return isPluggableEvaluator( 1, negated );
    	}
    	
    	private boolean validateIdentifierKey(String text) {
    		return validateLT(1, text);
    	}
    	
    	void checkTrailingSemicolon(String text, Token token) {
    		if (text.trim().endsWith(";")) {
    			errors.add(errorMessageFactory
    					.createTrailingSemicolonException(((DroolsToken) token)
    							.getLine(), ((DroolsToken) token)
    							.getCharPositionInLine(), ((DroolsToken) token)
    							.getStopIndex()));
    		}
    	}
    	
    	private boolean validateNotWithBinding(){
    		if (input.LA(1) == ID && input.LA(2) == ID && input.LA(3) == COLON){
    			return true;
    		}
    		return false;
    	}

    	private boolean validateRestr() {
    		int lookahead = 2;
    		int countParen = 1;

    		while (true) {
    			if (input.LA(lookahead) == COMMA) {
    				break;
    			} else if (input.LA(lookahead) == LEFT_PAREN) {
    				countParen++;
    			} else if (input.LA(lookahead) == RIGHT_PAREN) {
    				countParen--;
    			} else if (input.LA(lookahead) == EOF) {
    				break;
    			}
    			if (countParen == 0){
    				break;
    			}
    			lookahead++;
    		}
    		
    		boolean returnValue = false;
    		int activeIndex = input.index();
    		lookaheadTest = true;
    		try {
    			input.seek(input.LT(2).getTokenIndex());
    			constraint_expression();
    			returnValue = true;
    		} catch (RecognitionException e) {
    		} finally{
    			input.seek(activeIndex);
    		}
    		lookaheadTest = false;

    		return returnValue;
    	}
    	
    	private String safeSubstring(String text, int start, int end) {
    		return text.substring(Math.min(start, text.length()), Math.min(Math
    				.max(start, end), text.length()));
    	}
    	
    	public void reportError(RecognitionException ex) {
    		// if we've already reported an error and have not matched a token
    		// yet successfully, don't report any errors.
    		if (state.errorRecovery) {
    			return;
    		}
    		state.errorRecovery = true;
    	
    		errors.add(errorMessageFactory.createDroolsException(ex));
    	}
    	
    	/** return the raw DroolsParserException errors */
    	public List<DroolsParserException> getErrors() {
    		return errors;
    	}
    	
    	/** Return a list of pretty strings summarising the errors */
    	public List<String> getErrorMessages() {
    		List<String> messages = new ArrayList<String>(errors.size());
    	
    		for (DroolsParserException activeException : errors) {
    			messages.add(activeException.getMessage());
    		}
    	
    		return messages;
    	}
    	
    	/** return true if any parser errors were accumulated */
    	public boolean hasErrors() {
    		return !errors.isEmpty();
    	}

    	/**
    	 * Method that adds a paraphrase type into paraphrases stack.
    	 * 
    	 * @param type
    	 *            paraphrase type
    	 */
    	private void pushParaphrases(DroolsParaphraseTypes type) {
    		Map<DroolsParaphraseTypes, String> activeMap = new HashMap<DroolsParaphraseTypes, String>();
    		activeMap.put(type, "");
    		paraphrases.push(activeMap);
    	}

    	/**
    	 * Method that sets paraphrase value for a type into paraphrases stack.
    	 * 
    	 * @param type
    	 *            paraphrase type
    	 * @param value
    	 *            paraphrase value
    	 */
    	private void setParaphrasesValue(DroolsParaphraseTypes type, String value) {
    		paraphrases.peek().put(type, value);
    	}

    	/**
    	 * Helper method that creates a string from a token list.
    	 * 
    	 * @param tokenList
    	 *            token list
    	 * @return string
    	 */
    	private String buildStringFromTokens(List<Token> tokenList) {
    		StringBuilder sb = new StringBuilder();
    		if (null != tokenList) {
    			for (Token activeToken : tokenList) {
    				if (null != activeToken) {
    					sb.append(activeToken.getText());
    				}
    			}
    		}
    		return sb.toString();
    	}
    	
    	/** Overrided this method to not output mesages */
    	public void emitErrorMessage(String msg) {
    	}


    public static class compilation_unit_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "compilation_unit"
    // src/main/resources/org/drools/lang/DRL.g:395:1: compilation_unit : ( package_statement )? ( statement )* EOF -> ^( VT_COMPILATION_UNIT ( package_statement )? ( statement )* ) ;
    public final DRLParser.compilation_unit_return compilation_unit() throws RecognitionException {
        DRLParser.compilation_unit_return retval = new DRLParser.compilation_unit_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EOF3=null;
        DRLParser.package_statement_return package_statement1 = null;

        DRLParser.statement_return statement2 = null;


        Object EOF3_tree=null;
        RewriteRuleTokenStream stream_EOF=new RewriteRuleTokenStream(adaptor,"token EOF");
        RewriteRuleSubtreeStream stream_package_statement=new RewriteRuleSubtreeStream(adaptor,"rule package_statement");
        RewriteRuleSubtreeStream stream_statement=new RewriteRuleSubtreeStream(adaptor,"rule statement");
        try {
            // src/main/resources/org/drools/lang/DRL.g:396:2: ( ( package_statement )? ( statement )* EOF -> ^( VT_COMPILATION_UNIT ( package_statement )? ( statement )* ) )
            // src/main/resources/org/drools/lang/DRL.g:396:4: ( package_statement )? ( statement )* EOF
            {
            // src/main/resources/org/drools/lang/DRL.g:396:4: ( package_statement )?
            int alt1=2;
            alt1 = dfa1.predict(input);
            switch (alt1) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:396:4: package_statement
                    {
                    pushFollow(FOLLOW_package_statement_in_compilation_unit384);
                    package_statement1=package_statement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_package_statement.add(package_statement1.getTree());

                    }
                    break;

            }

            // src/main/resources/org/drools/lang/DRL.g:397:3: ( statement )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==ID) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // src/main/resources/org/drools/lang/DRL.g:397:3: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_compilation_unit389);
            	    statement2=statement();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_statement.add(statement2.getTree());

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            EOF3=(Token)match(input,EOF,FOLLOW_EOF_in_compilation_unit394); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_EOF.add(EOF3);



            // AST REWRITE
            // elements: package_statement, statement
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 399:3: -> ^( VT_COMPILATION_UNIT ( package_statement )? ( statement )* )
            {
                // src/main/resources/org/drools/lang/DRL.g:399:6: ^( VT_COMPILATION_UNIT ( package_statement )? ( statement )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(VT_COMPILATION_UNIT, "VT_COMPILATION_UNIT"), root_1);

                // src/main/resources/org/drools/lang/DRL.g:399:28: ( package_statement )?
                if ( stream_package_statement.hasNext() ) {
                    adaptor.addChild(root_1, stream_package_statement.nextTree());

                }
                stream_package_statement.reset();
                // src/main/resources/org/drools/lang/DRL.g:399:47: ( statement )*
                while ( stream_statement.hasNext() ) {
                    adaptor.addChild(root_1, stream_statement.nextTree());

                }
                stream_statement.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch ( RecognitionException e ) {

            		reportError( e );
            	
        }
        catch ( RewriteEmptyStreamException e ) {

            	
        }
        finally {

            	if (isEditorInterfaceEnabled && retval.tree == null) {
            		retval.tree = root_0;
            		root_0 = (Object) adaptor.nil();
            		Object root_1 = (Object) adaptor.nil();
            		root_1 = (Object) adaptor.becomeRoot(adaptor.create(
            				VT_COMPILATION_UNIT, "VT_COMPILATION_UNIT"), root_1);
            		if (stream_package_statement.hasNext()) {
            			adaptor.addChild(root_1, stream_package_statement.nextTree());
            		}
            		while (stream_statement.hasNext()) {
            			adaptor.addChild(root_1, stream_statement.nextTree());
            		}
            		adaptor.addChild(root_0, root_1);
            		retval.stop = input.LT(-1);
            		retval.tree = (Object) adaptor.rulePostProcessing(root_0);
            		adaptor.setTokenBoundaries(retval.tree, retval.start,
            				retval.stop);
            	}
            	if (isEditorInterfaceEnabled && hasErrors()) {
            		Tree rootNode = (Tree) adaptor.becomeRoot(adaptor.create(
            				VT_COMPILATION_UNIT, "VT_COMPILATION_UNIT"), adaptor.nil());
            		for (int i = 0; i < ((Tree)retval.tree).getChildCount(); i++) {
            			Tree childNode = (Tree) ((Tree)retval.tree).getChild(i);
            			if (!(childNode instanceof CommonErrorNode)) {
            				rootNode.addChild(childNode);
            			}
            		}
            		retval.tree = rootNode; 
            	}

        }
        return retval;
    }
    // $ANTLR end "compilation_unit"

    public static class package_statement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "package_statement"
    // src/main/resources/org/drools/lang/DRL.g:438:1: package_statement : package_key package_id ( SEMICOLON )? -> ^( package_key package_id ) ;
    public final DRLParser.package_statement_return package_statement() throws RecognitionException {
        DRLParser.package_statement_return retval = new DRLParser.package_statement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token SEMICOLON6=null;
        DRLParser.package_key_return package_key4 = null;

        DRLParser.package_id_return package_id5 = null;


        Object SEMICOLON6_tree=null;
        RewriteRuleTokenStream stream_SEMICOLON=new RewriteRuleTokenStream(adaptor,"token SEMICOLON");
        RewriteRuleSubtreeStream stream_package_key=new RewriteRuleSubtreeStream(adaptor,"rule package_key");
        RewriteRuleSubtreeStream stream_package_id=new RewriteRuleSubtreeStream(adaptor,"rule package_id");
         pushParaphrases(DroolsParaphraseTypes.PACKAGE); if ( state.backtracking==0 ) beginSentence(DroolsSentenceType.PACKAGE); 
        try {
            // src/main/resources/org/drools/lang/DRL.g:441:2: ( package_key package_id ( SEMICOLON )? -> ^( package_key package_id ) )
            // src/main/resources/org/drools/lang/DRL.g:441:4: package_key package_id ( SEMICOLON )?
            {
            pushFollow(FOLLOW_package_key_in_package_statement449);
            package_key4=package_key();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_package_key.add(package_key4.getTree());
            pushFollow(FOLLOW_package_id_in_package_statement453);
            package_id5=package_id();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_package_id.add(package_id5.getTree());
            // src/main/resources/org/drools/lang/DRL.g:442:14: ( SEMICOLON )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==SEMICOLON) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:442:14: SEMICOLON
                    {
                    SEMICOLON6=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_package_statement455); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMICOLON.add(SEMICOLON6);


                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              	emit(SEMICOLON6, DroolsEditorType.SYMBOL);	
            }


            // AST REWRITE
            // elements: package_key, package_id
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 444:3: -> ^( package_key package_id )
            {
                // src/main/resources/org/drools/lang/DRL.g:444:6: ^( package_key package_id )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_package_key.nextNode(), root_1);

                adaptor.addChild(root_1, stream_package_id.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
               paraphrases.pop(); 
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "package_statement"

    public static class package_id_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "package_id"
    // src/main/resources/org/drools/lang/DRL.g:447:1: package_id : id+= ID (id+= DOT id+= ID )* -> ^( VT_PACKAGE_ID ( ID )+ ) ;
    public final DRLParser.package_id_return package_id() throws RecognitionException {
        DRLParser.package_id_return retval = new DRLParser.package_id_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;
        List list_id=null;

        Object id_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_DOT=new RewriteRuleTokenStream(adaptor,"token DOT");

        try {
            // src/main/resources/org/drools/lang/DRL.g:448:2: (id+= ID (id+= DOT id+= ID )* -> ^( VT_PACKAGE_ID ( ID )+ ) )
            // src/main/resources/org/drools/lang/DRL.g:448:4: id+= ID (id+= DOT id+= ID )*
            {
            id=(Token)match(input,ID,FOLLOW_ID_in_package_id482); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id);

            if (list_id==null) list_id=new ArrayList();
            list_id.add(id);

            // src/main/resources/org/drools/lang/DRL.g:448:11: (id+= DOT id+= ID )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==DOT) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // src/main/resources/org/drools/lang/DRL.g:448:13: id+= DOT id+= ID
            	    {
            	    id=(Token)match(input,DOT,FOLLOW_DOT_in_package_id488); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_DOT.add(id);

            	    if (list_id==null) list_id=new ArrayList();
            	    list_id.add(id);

            	    id=(Token)match(input,ID,FOLLOW_ID_in_package_id492); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_ID.add(id);

            	    if (list_id==null) list_id=new ArrayList();
            	    list_id.add(id);


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            if ( state.backtracking==0 ) {
              	emit(list_id, DroolsEditorType.IDENTIFIER);
              		setParaphrasesValue(DroolsParaphraseTypes.PACKAGE, buildStringFromTokens(list_id));	
            }


            // AST REWRITE
            // elements: ID
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 451:3: -> ^( VT_PACKAGE_ID ( ID )+ )
            {
                // src/main/resources/org/drools/lang/DRL.g:451:6: ^( VT_PACKAGE_ID ( ID )+ )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(VT_PACKAGE_ID, "VT_PACKAGE_ID"), root_1);

                if ( !(stream_ID.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_ID.hasNext() ) {
                    adaptor.addChild(root_1, stream_ID.nextNode());

                }
                stream_ID.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "package_id"

    public static class statement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "statement"
    // src/main/resources/org/drools/lang/DRL.g:454:1: statement options {k=2; } : ( rule_attribute | {...}? => function_import_statement | import_statement | global | function | {...}? => template | {...}? => type_declaration | rule | query );
    public final DRLParser.statement_return statement() throws RecognitionException {
        DRLParser.statement_return retval = new DRLParser.statement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        DRLParser.rule_attribute_return rule_attribute7 = null;

        DRLParser.function_import_statement_return function_import_statement8 = null;

        DRLParser.import_statement_return import_statement9 = null;

        DRLParser.global_return global10 = null;

        DRLParser.function_return function11 = null;

        DRLParser.template_return template12 = null;

        DRLParser.type_declaration_return type_declaration13 = null;

        DRLParser.rule_return rule14 = null;

        DRLParser.query_return query15 = null;



        try {
            // src/main/resources/org/drools/lang/DRL.g:457:3: ( rule_attribute | {...}? => function_import_statement | import_statement | global | function | {...}? => template | {...}? => type_declaration | rule | query )
            int alt5=9;
            alt5 = dfa5.predict(input);
            switch (alt5) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:457:5: rule_attribute
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_rule_attribute_in_statement530);
                    rule_attribute7=rule_attribute();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, rule_attribute7.getTree());

                    }
                    break;
                case 2 :
                    // src/main/resources/org/drools/lang/DRL.g:458:3: {...}? => function_import_statement
                    {
                    root_0 = (Object)adaptor.nil();

                    if ( !(((validateLT(1, "import") && validateLT(2, "function") ))) ) {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        throw new FailedPredicateException(input, "statement", "(validateLT(1, \"import\") && validateLT(2, \"function\") )");
                    }
                    pushFollow(FOLLOW_function_import_statement_in_statement537);
                    function_import_statement8=function_import_statement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, function_import_statement8.getTree());

                    }
                    break;
                case 3 :
                    // src/main/resources/org/drools/lang/DRL.g:459:4: import_statement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_import_statement_in_statement543);
                    import_statement9=import_statement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, import_statement9.getTree());

                    }
                    break;
                case 4 :
                    // src/main/resources/org/drools/lang/DRL.g:460:4: global
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_global_in_statement549);
                    global10=global();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, global10.getTree());

                    }
                    break;
                case 5 :
                    // src/main/resources/org/drools/lang/DRL.g:461:4: function
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_function_in_statement555);
                    function11=function();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, function11.getTree());

                    }
                    break;
                case 6 :
                    // src/main/resources/org/drools/lang/DRL.g:462:4: {...}? => template
                    {
                    root_0 = (Object)adaptor.nil();

                    if ( !(((validateLT(1, DroolsSoftKeywords.TEMPLATE)))) ) {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        throw new FailedPredicateException(input, "statement", "(validateLT(1, DroolsSoftKeywords.TEMPLATE))");
                    }
                    pushFollow(FOLLOW_template_in_statement563);
                    template12=template();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, template12.getTree());

                    }
                    break;
                case 7 :
                    // src/main/resources/org/drools/lang/DRL.g:463:4: {...}? => type_declaration
                    {
                    root_0 = (Object)adaptor.nil();

                    if ( !(((validateLT(1, DroolsSoftKeywords.DECLARE)))) ) {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        throw new FailedPredicateException(input, "statement", "(validateLT(1, DroolsSoftKeywords.DECLARE))");
                    }
                    pushFollow(FOLLOW_type_declaration_in_statement571);
                    type_declaration13=type_declaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, type_declaration13.getTree());

                    }
                    break;
                case 8 :
                    // src/main/resources/org/drools/lang/DRL.g:464:4: rule
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_rule_in_statement576);
                    rule14=rule();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, rule14.getTree());

                    }
                    break;
                case 9 :
                    // src/main/resources/org/drools/lang/DRL.g:465:4: query
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_query_in_statement581);
                    query15=query();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, query15.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "statement"

    public static class import_statement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "import_statement"
    // src/main/resources/org/drools/lang/DRL.g:468:1: import_statement : import_key import_name[DroolsParaphraseTypes.IMPORT] ( SEMICOLON )? -> ^( import_key import_name ) ;
    public final DRLParser.import_statement_return import_statement() throws RecognitionException {
        DRLParser.import_statement_return retval = new DRLParser.import_statement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token SEMICOLON18=null;
        DRLParser.import_key_return import_key16 = null;

        DRLParser.import_name_return import_name17 = null;


        Object SEMICOLON18_tree=null;
        RewriteRuleTokenStream stream_SEMICOLON=new RewriteRuleTokenStream(adaptor,"token SEMICOLON");
        RewriteRuleSubtreeStream stream_import_key=new RewriteRuleSubtreeStream(adaptor,"rule import_key");
        RewriteRuleSubtreeStream stream_import_name=new RewriteRuleSubtreeStream(adaptor,"rule import_name");
         pushParaphrases(DroolsParaphraseTypes.IMPORT); if ( state.backtracking==0 ) beginSentence(DroolsSentenceType.IMPORT_STATEMENT);  
        try {
            // src/main/resources/org/drools/lang/DRL.g:471:2: ( import_key import_name[DroolsParaphraseTypes.IMPORT] ( SEMICOLON )? -> ^( import_key import_name ) )
            // src/main/resources/org/drools/lang/DRL.g:471:4: import_key import_name[DroolsParaphraseTypes.IMPORT] ( SEMICOLON )?
            {
            pushFollow(FOLLOW_import_key_in_import_statement603);
            import_key16=import_key();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_import_key.add(import_key16.getTree());
            pushFollow(FOLLOW_import_name_in_import_statement605);
            import_name17=import_name(DroolsParaphraseTypes.IMPORT);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_import_name.add(import_name17.getTree());
            // src/main/resources/org/drools/lang/DRL.g:471:57: ( SEMICOLON )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==SEMICOLON) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:471:57: SEMICOLON
                    {
                    SEMICOLON18=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_import_statement608); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMICOLON.add(SEMICOLON18);


                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              	emit(SEMICOLON18, DroolsEditorType.SYMBOL);	
            }


            // AST REWRITE
            // elements: import_key, import_name
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 473:3: -> ^( import_key import_name )
            {
                // src/main/resources/org/drools/lang/DRL.g:473:6: ^( import_key import_name )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_import_key.nextNode(), root_1);

                adaptor.addChild(root_1, stream_import_name.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
               paraphrases.pop(); 
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "import_statement"

    public static class function_import_statement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "function_import_statement"
    // src/main/resources/org/drools/lang/DRL.g:476:1: function_import_statement : imp= import_key function_key import_name[DroolsParaphraseTypes.FUNCTION_IMPORT] ( SEMICOLON )? -> ^( VT_FUNCTION_IMPORT[$imp.start] function_key import_name ) ;
    public final DRLParser.function_import_statement_return function_import_statement() throws RecognitionException {
        DRLParser.function_import_statement_return retval = new DRLParser.function_import_statement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token SEMICOLON21=null;
        DRLParser.import_key_return imp = null;

        DRLParser.function_key_return function_key19 = null;

        DRLParser.import_name_return import_name20 = null;


        Object SEMICOLON21_tree=null;
        RewriteRuleTokenStream stream_SEMICOLON=new RewriteRuleTokenStream(adaptor,"token SEMICOLON");
        RewriteRuleSubtreeStream stream_import_key=new RewriteRuleSubtreeStream(adaptor,"rule import_key");
        RewriteRuleSubtreeStream stream_function_key=new RewriteRuleSubtreeStream(adaptor,"rule function_key");
        RewriteRuleSubtreeStream stream_import_name=new RewriteRuleSubtreeStream(adaptor,"rule import_name");
         pushParaphrases(DroolsParaphraseTypes.FUNCTION_IMPORT); if ( state.backtracking==0 ) beginSentence(DroolsSentenceType.FUNCTION_IMPORT_STATEMENT); 
        try {
            // src/main/resources/org/drools/lang/DRL.g:479:2: (imp= import_key function_key import_name[DroolsParaphraseTypes.FUNCTION_IMPORT] ( SEMICOLON )? -> ^( VT_FUNCTION_IMPORT[$imp.start] function_key import_name ) )
            // src/main/resources/org/drools/lang/DRL.g:479:4: imp= import_key function_key import_name[DroolsParaphraseTypes.FUNCTION_IMPORT] ( SEMICOLON )?
            {
            pushFollow(FOLLOW_import_key_in_function_import_statement646);
            imp=import_key();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_import_key.add(imp.getTree());
            pushFollow(FOLLOW_function_key_in_function_import_statement648);
            function_key19=function_key();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_function_key.add(function_key19.getTree());
            pushFollow(FOLLOW_import_name_in_function_import_statement650);
            import_name20=import_name(DroolsParaphraseTypes.FUNCTION_IMPORT);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_import_name.add(import_name20.getTree());
            // src/main/resources/org/drools/lang/DRL.g:479:83: ( SEMICOLON )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==SEMICOLON) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:479:83: SEMICOLON
                    {
                    SEMICOLON21=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_function_import_statement653); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMICOLON.add(SEMICOLON21);


                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              	emit(SEMICOLON21, DroolsEditorType.SYMBOL);	
            }


            // AST REWRITE
            // elements: function_key, import_name
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 481:3: -> ^( VT_FUNCTION_IMPORT[$imp.start] function_key import_name )
            {
                // src/main/resources/org/drools/lang/DRL.g:481:6: ^( VT_FUNCTION_IMPORT[$imp.start] function_key import_name )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(VT_FUNCTION_IMPORT, (imp!=null?((Token)imp.start):null)), root_1);

                adaptor.addChild(root_1, stream_function_key.nextTree());
                adaptor.addChild(root_1, stream_import_name.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
               paraphrases.pop(); 
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "function_import_statement"

    public static class import_name_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "import_name"
    // src/main/resources/org/drools/lang/DRL.g:484:1: import_name[DroolsParaphraseTypes importType] : id+= ID (id+= DOT id+= ID )* (id+= DOT_STAR )? -> ^( VT_IMPORT_ID ( ID )+ ( DOT_STAR )? ) ;
    public final DRLParser.import_name_return import_name(DroolsParaphraseTypes importType) throws RecognitionException {
        DRLParser.import_name_return retval = new DRLParser.import_name_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;
        List list_id=null;

        Object id_tree=null;
        RewriteRuleTokenStream stream_DOT_STAR=new RewriteRuleTokenStream(adaptor,"token DOT_STAR");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_DOT=new RewriteRuleTokenStream(adaptor,"token DOT");

        try {
            // src/main/resources/org/drools/lang/DRL.g:485:2: (id+= ID (id+= DOT id+= ID )* (id+= DOT_STAR )? -> ^( VT_IMPORT_ID ( ID )+ ( DOT_STAR )? ) )
            // src/main/resources/org/drools/lang/DRL.g:485:4: id+= ID (id+= DOT id+= ID )* (id+= DOT_STAR )?
            {
            id=(Token)match(input,ID,FOLLOW_ID_in_import_name687); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id);

            if (list_id==null) list_id=new ArrayList();
            list_id.add(id);

            // src/main/resources/org/drools/lang/DRL.g:485:11: (id+= DOT id+= ID )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==DOT) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // src/main/resources/org/drools/lang/DRL.g:485:13: id+= DOT id+= ID
            	    {
            	    id=(Token)match(input,DOT,FOLLOW_DOT_in_import_name693); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_DOT.add(id);

            	    if (list_id==null) list_id=new ArrayList();
            	    list_id.add(id);

            	    id=(Token)match(input,ID,FOLLOW_ID_in_import_name697); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_ID.add(id);

            	    if (list_id==null) list_id=new ArrayList();
            	    list_id.add(id);


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            // src/main/resources/org/drools/lang/DRL.g:485:33: (id+= DOT_STAR )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==DOT_STAR) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:485:33: id+= DOT_STAR
                    {
                    id=(Token)match(input,DOT_STAR,FOLLOW_DOT_STAR_in_import_name704); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_DOT_STAR.add(id);

                    if (list_id==null) list_id=new ArrayList();
                    list_id.add(id);


                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              	emit(list_id, DroolsEditorType.IDENTIFIER);
              		setParaphrasesValue(importType, buildStringFromTokens(list_id));	
            }


            // AST REWRITE
            // elements: DOT_STAR, ID
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 488:3: -> ^( VT_IMPORT_ID ( ID )+ ( DOT_STAR )? )
            {
                // src/main/resources/org/drools/lang/DRL.g:488:6: ^( VT_IMPORT_ID ( ID )+ ( DOT_STAR )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(VT_IMPORT_ID, "VT_IMPORT_ID"), root_1);

                if ( !(stream_ID.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_ID.hasNext() ) {
                    adaptor.addChild(root_1, stream_ID.nextNode());

                }
                stream_ID.reset();
                // src/main/resources/org/drools/lang/DRL.g:488:25: ( DOT_STAR )?
                if ( stream_DOT_STAR.hasNext() ) {
                    adaptor.addChild(root_1, stream_DOT_STAR.nextNode());

                }
                stream_DOT_STAR.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "import_name"

    public static class global_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "global"
    // src/main/resources/org/drools/lang/DRL.g:491:1: global : global_key data_type global_id ( SEMICOLON )? -> ^( global_key data_type global_id ) ;
    public final DRLParser.global_return global() throws RecognitionException {
        DRLParser.global_return retval = new DRLParser.global_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token SEMICOLON25=null;
        DRLParser.global_key_return global_key22 = null;

        DRLParser.data_type_return data_type23 = null;

        DRLParser.global_id_return global_id24 = null;


        Object SEMICOLON25_tree=null;
        RewriteRuleTokenStream stream_SEMICOLON=new RewriteRuleTokenStream(adaptor,"token SEMICOLON");
        RewriteRuleSubtreeStream stream_data_type=new RewriteRuleSubtreeStream(adaptor,"rule data_type");
        RewriteRuleSubtreeStream stream_global_id=new RewriteRuleSubtreeStream(adaptor,"rule global_id");
        RewriteRuleSubtreeStream stream_global_key=new RewriteRuleSubtreeStream(adaptor,"rule global_key");
         pushParaphrases(DroolsParaphraseTypes.GLOBAL);  if ( state.backtracking==0 ) beginSentence(DroolsSentenceType.GLOBAL); 
        try {
            // src/main/resources/org/drools/lang/DRL.g:494:2: ( global_key data_type global_id ( SEMICOLON )? -> ^( global_key data_type global_id ) )
            // src/main/resources/org/drools/lang/DRL.g:494:4: global_key data_type global_id ( SEMICOLON )?
            {
            pushFollow(FOLLOW_global_key_in_global744);
            global_key22=global_key();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_global_key.add(global_key22.getTree());
            pushFollow(FOLLOW_data_type_in_global746);
            data_type23=data_type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_data_type.add(data_type23.getTree());
            pushFollow(FOLLOW_global_id_in_global748);
            global_id24=global_id();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_global_id.add(global_id24.getTree());
            // src/main/resources/org/drools/lang/DRL.g:494:35: ( SEMICOLON )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==SEMICOLON) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:494:35: SEMICOLON
                    {
                    SEMICOLON25=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_global750); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMICOLON.add(SEMICOLON25);


                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              	emit(SEMICOLON25, DroolsEditorType.SYMBOL);	
            }


            // AST REWRITE
            // elements: global_id, global_key, data_type
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 496:3: -> ^( global_key data_type global_id )
            {
                // src/main/resources/org/drools/lang/DRL.g:496:6: ^( global_key data_type global_id )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_global_key.nextNode(), root_1);

                adaptor.addChild(root_1, stream_data_type.nextTree());
                adaptor.addChild(root_1, stream_global_id.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
               paraphrases.pop(); 
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "global"

    public static class global_id_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "global_id"
    // src/main/resources/org/drools/lang/DRL.g:499:1: global_id : id= ID -> VT_GLOBAL_ID[$id] ;
    public final DRLParser.global_id_return global_id() throws RecognitionException {
        DRLParser.global_id_return retval = new DRLParser.global_id_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;

        Object id_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // src/main/resources/org/drools/lang/DRL.g:500:2: (id= ID -> VT_GLOBAL_ID[$id] )
            // src/main/resources/org/drools/lang/DRL.g:500:4: id= ID
            {
            id=(Token)match(input,ID,FOLLOW_ID_in_global_id779); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id);

            if ( state.backtracking==0 ) {
              	emit(id, DroolsEditorType.IDENTIFIER);
              		setParaphrasesValue(DroolsParaphraseTypes.GLOBAL, (id!=null?id.getText():null));	
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 503:3: -> VT_GLOBAL_ID[$id]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VT_GLOBAL_ID, id));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "global_id"

    public static class function_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "function"
    // src/main/resources/org/drools/lang/DRL.g:506:1: function : function_key ( data_type )? function_id parameters curly_chunk -> ^( function_key ( data_type )? function_id parameters curly_chunk ) ;
    public final DRLParser.function_return function() throws RecognitionException {
        DRLParser.function_return retval = new DRLParser.function_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        DRLParser.function_key_return function_key26 = null;

        DRLParser.data_type_return data_type27 = null;

        DRLParser.function_id_return function_id28 = null;

        DRLParser.parameters_return parameters29 = null;

        DRLParser.curly_chunk_return curly_chunk30 = null;


        RewriteRuleSubtreeStream stream_parameters=new RewriteRuleSubtreeStream(adaptor,"rule parameters");
        RewriteRuleSubtreeStream stream_function_key=new RewriteRuleSubtreeStream(adaptor,"rule function_key");
        RewriteRuleSubtreeStream stream_curly_chunk=new RewriteRuleSubtreeStream(adaptor,"rule curly_chunk");
        RewriteRuleSubtreeStream stream_data_type=new RewriteRuleSubtreeStream(adaptor,"rule data_type");
        RewriteRuleSubtreeStream stream_function_id=new RewriteRuleSubtreeStream(adaptor,"rule function_id");
         pushParaphrases(DroolsParaphraseTypes.FUNCTION); if ( state.backtracking==0 ) beginSentence(DroolsSentenceType.FUNCTION);  
        try {
            // src/main/resources/org/drools/lang/DRL.g:509:2: ( function_key ( data_type )? function_id parameters curly_chunk -> ^( function_key ( data_type )? function_id parameters curly_chunk ) )
            // src/main/resources/org/drools/lang/DRL.g:509:4: function_key ( data_type )? function_id parameters curly_chunk
            {
            pushFollow(FOLLOW_function_key_in_function811);
            function_key26=function_key();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_function_key.add(function_key26.getTree());
            // src/main/resources/org/drools/lang/DRL.g:509:17: ( data_type )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==ID) ) {
                int LA11_1 = input.LA(2);

                if ( ((LA11_1>=ID && LA11_1<=DOT)||LA11_1==LEFT_SQUARE) ) {
                    alt11=1;
                }
            }
            switch (alt11) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:509:17: data_type
                    {
                    pushFollow(FOLLOW_data_type_in_function813);
                    data_type27=data_type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_data_type.add(data_type27.getTree());

                    }
                    break;

            }

            pushFollow(FOLLOW_function_id_in_function816);
            function_id28=function_id();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_function_id.add(function_id28.getTree());
            pushFollow(FOLLOW_parameters_in_function818);
            parameters29=parameters();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_parameters.add(parameters29.getTree());
            pushFollow(FOLLOW_curly_chunk_in_function820);
            curly_chunk30=curly_chunk();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_curly_chunk.add(curly_chunk30.getTree());


            // AST REWRITE
            // elements: parameters, data_type, function_key, function_id, curly_chunk
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 510:3: -> ^( function_key ( data_type )? function_id parameters curly_chunk )
            {
                // src/main/resources/org/drools/lang/DRL.g:510:6: ^( function_key ( data_type )? function_id parameters curly_chunk )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_function_key.nextNode(), root_1);

                // src/main/resources/org/drools/lang/DRL.g:510:21: ( data_type )?
                if ( stream_data_type.hasNext() ) {
                    adaptor.addChild(root_1, stream_data_type.nextTree());

                }
                stream_data_type.reset();
                adaptor.addChild(root_1, stream_function_id.nextTree());
                adaptor.addChild(root_1, stream_parameters.nextTree());
                adaptor.addChild(root_1, stream_curly_chunk.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
               paraphrases.pop(); 
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "function"

    public static class function_id_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "function_id"
    // src/main/resources/org/drools/lang/DRL.g:513:1: function_id : id= ID -> VT_FUNCTION_ID[$id] ;
    public final DRLParser.function_id_return function_id() throws RecognitionException {
        DRLParser.function_id_return retval = new DRLParser.function_id_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;

        Object id_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // src/main/resources/org/drools/lang/DRL.g:514:2: (id= ID -> VT_FUNCTION_ID[$id] )
            // src/main/resources/org/drools/lang/DRL.g:514:4: id= ID
            {
            id=(Token)match(input,ID,FOLLOW_ID_in_function_id850); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id);

            if ( state.backtracking==0 ) {
              	emit(id, DroolsEditorType.IDENTIFIER);
              		setParaphrasesValue(DroolsParaphraseTypes.FUNCTION, (id!=null?id.getText():null));	
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 517:3: -> VT_FUNCTION_ID[$id]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VT_FUNCTION_ID, id));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "function_id"

    public static class query_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "query"
    // src/main/resources/org/drools/lang/DRL.g:520:1: query : query_key query_id ( parameters )? normal_lhs_block end= end_key ( SEMICOLON )? -> ^( query_key query_id ( parameters )? normal_lhs_block end_key ) ;
    public final DRLParser.query_return query() throws RecognitionException {
        DRLParser.query_return retval = new DRLParser.query_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token SEMICOLON35=null;
        DRLParser.end_key_return end = null;

        DRLParser.query_key_return query_key31 = null;

        DRLParser.query_id_return query_id32 = null;

        DRLParser.parameters_return parameters33 = null;

        DRLParser.normal_lhs_block_return normal_lhs_block34 = null;


        Object SEMICOLON35_tree=null;
        RewriteRuleTokenStream stream_SEMICOLON=new RewriteRuleTokenStream(adaptor,"token SEMICOLON");
        RewriteRuleSubtreeStream stream_parameters=new RewriteRuleSubtreeStream(adaptor,"rule parameters");
        RewriteRuleSubtreeStream stream_query_key=new RewriteRuleSubtreeStream(adaptor,"rule query_key");
        RewriteRuleSubtreeStream stream_normal_lhs_block=new RewriteRuleSubtreeStream(adaptor,"rule normal_lhs_block");
        RewriteRuleSubtreeStream stream_query_id=new RewriteRuleSubtreeStream(adaptor,"rule query_id");
        RewriteRuleSubtreeStream stream_end_key=new RewriteRuleSubtreeStream(adaptor,"rule end_key");
         pushParaphrases(DroolsParaphraseTypes.QUERY); if ( state.backtracking==0 ) beginSentence(DroolsSentenceType.QUERY); 
        try {
            // src/main/resources/org/drools/lang/DRL.g:523:2: ( query_key query_id ( parameters )? normal_lhs_block end= end_key ( SEMICOLON )? -> ^( query_key query_id ( parameters )? normal_lhs_block end_key ) )
            // src/main/resources/org/drools/lang/DRL.g:523:4: query_key query_id ( parameters )? normal_lhs_block end= end_key ( SEMICOLON )?
            {
            pushFollow(FOLLOW_query_key_in_query882);
            query_key31=query_key();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_query_key.add(query_key31.getTree());
            pushFollow(FOLLOW_query_id_in_query884);
            query_id32=query_id();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_query_id.add(query_id32.getTree());
            if ( state.backtracking==0 ) {
              	emit(Location.LOCATION_RULE_HEADER);	
            }
            // src/main/resources/org/drools/lang/DRL.g:525:3: ( parameters )?
            int alt12=2;
            alt12 = dfa12.predict(input);
            switch (alt12) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:525:3: parameters
                    {
                    pushFollow(FOLLOW_parameters_in_query892);
                    parameters33=parameters();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_parameters.add(parameters33.getTree());

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              	emit(Location.LOCATION_LHS_BEGIN_OF_CONDITION);	
            }
            pushFollow(FOLLOW_normal_lhs_block_in_query901);
            normal_lhs_block34=normal_lhs_block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_normal_lhs_block.add(normal_lhs_block34.getTree());
            pushFollow(FOLLOW_end_key_in_query908);
            end=end_key();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_end_key.add(end.getTree());
            // src/main/resources/org/drools/lang/DRL.g:528:15: ( SEMICOLON )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==SEMICOLON) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:528:15: SEMICOLON
                    {
                    SEMICOLON35=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_query910); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMICOLON.add(SEMICOLON35);


                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              	emit(SEMICOLON35, DroolsEditorType.SYMBOL);	
            }


            // AST REWRITE
            // elements: end_key, parameters, query_key, query_id, normal_lhs_block
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 530:3: -> ^( query_key query_id ( parameters )? normal_lhs_block end_key )
            {
                // src/main/resources/org/drools/lang/DRL.g:530:6: ^( query_key query_id ( parameters )? normal_lhs_block end_key )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_query_key.nextNode(), root_1);

                adaptor.addChild(root_1, stream_query_id.nextTree());
                // src/main/resources/org/drools/lang/DRL.g:530:27: ( parameters )?
                if ( stream_parameters.hasNext() ) {
                    adaptor.addChild(root_1, stream_parameters.nextTree());

                }
                stream_parameters.reset();
                adaptor.addChild(root_1, stream_normal_lhs_block.nextTree());
                adaptor.addChild(root_1, stream_end_key.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
               paraphrases.pop(); 
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "query"

    public static class query_id_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "query_id"
    // src/main/resources/org/drools/lang/DRL.g:533:1: query_id : (id= ID -> VT_QUERY_ID[$id] | id= STRING -> VT_QUERY_ID[$id] );
    public final DRLParser.query_id_return query_id() throws RecognitionException {
        DRLParser.query_id_return retval = new DRLParser.query_id_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;

        Object id_tree=null;
        RewriteRuleTokenStream stream_STRING=new RewriteRuleTokenStream(adaptor,"token STRING");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // src/main/resources/org/drools/lang/DRL.g:534:2: (id= ID -> VT_QUERY_ID[$id] | id= STRING -> VT_QUERY_ID[$id] )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==ID) ) {
                alt14=1;
            }
            else if ( (LA14_0==STRING) ) {
                alt14=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }
            switch (alt14) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:534:5: id= ID
                    {
                    id=(Token)match(input,ID,FOLLOW_ID_in_query_id945); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ID.add(id);

                    if ( state.backtracking==0 ) {
                      	emit(id, DroolsEditorType.IDENTIFIER);
                      		setParaphrasesValue(DroolsParaphraseTypes.QUERY, (id!=null?id.getText():null));	
                    }


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 536:65: -> VT_QUERY_ID[$id]
                    {
                        adaptor.addChild(root_0, (Object)adaptor.create(VT_QUERY_ID, id));

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // src/main/resources/org/drools/lang/DRL.g:537:5: id= STRING
                    {
                    id=(Token)match(input,STRING,FOLLOW_STRING_in_query_id961); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_STRING.add(id);

                    if ( state.backtracking==0 ) {
                      	emit(id, DroolsEditorType.IDENTIFIER);
                      		setParaphrasesValue(DroolsParaphraseTypes.QUERY, (id!=null?id.getText():null));	
                    }


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 539:65: -> VT_QUERY_ID[$id]
                    {
                        adaptor.addChild(root_0, (Object)adaptor.create(VT_QUERY_ID, id));

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "query_id"

    public static class parameters_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "parameters"
    // src/main/resources/org/drools/lang/DRL.g:542:1: parameters : LEFT_PAREN ( param_definition ( COMMA param_definition )* )? RIGHT_PAREN -> ^( VT_PARAM_LIST ( param_definition )* RIGHT_PAREN ) ;
    public final DRLParser.parameters_return parameters() throws RecognitionException {
        DRLParser.parameters_return retval = new DRLParser.parameters_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LEFT_PAREN36=null;
        Token COMMA38=null;
        Token RIGHT_PAREN40=null;
        DRLParser.param_definition_return param_definition37 = null;

        DRLParser.param_definition_return param_definition39 = null;


        Object LEFT_PAREN36_tree=null;
        Object COMMA38_tree=null;
        Object RIGHT_PAREN40_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_LEFT_PAREN=new RewriteRuleTokenStream(adaptor,"token LEFT_PAREN");
        RewriteRuleTokenStream stream_RIGHT_PAREN=new RewriteRuleTokenStream(adaptor,"token RIGHT_PAREN");
        RewriteRuleSubtreeStream stream_param_definition=new RewriteRuleSubtreeStream(adaptor,"rule param_definition");
        try {
            // src/main/resources/org/drools/lang/DRL.g:543:2: ( LEFT_PAREN ( param_definition ( COMMA param_definition )* )? RIGHT_PAREN -> ^( VT_PARAM_LIST ( param_definition )* RIGHT_PAREN ) )
            // src/main/resources/org/drools/lang/DRL.g:543:4: LEFT_PAREN ( param_definition ( COMMA param_definition )* )? RIGHT_PAREN
            {
            LEFT_PAREN36=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_parameters980); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LEFT_PAREN.add(LEFT_PAREN36);

            if ( state.backtracking==0 ) {
              	emit(LEFT_PAREN36, DroolsEditorType.SYMBOL);	
            }
            // src/main/resources/org/drools/lang/DRL.g:544:4: ( param_definition ( COMMA param_definition )* )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==ID) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:544:6: param_definition ( COMMA param_definition )*
                    {
                    pushFollow(FOLLOW_param_definition_in_parameters989);
                    param_definition37=param_definition();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_param_definition.add(param_definition37.getTree());
                    // src/main/resources/org/drools/lang/DRL.g:544:23: ( COMMA param_definition )*
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( (LA15_0==COMMA) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // src/main/resources/org/drools/lang/DRL.g:544:24: COMMA param_definition
                    	    {
                    	    COMMA38=(Token)match(input,COMMA,FOLLOW_COMMA_in_parameters992); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_COMMA.add(COMMA38);

                    	    if ( state.backtracking==0 ) {
                    	      	emit(COMMA38, DroolsEditorType.SYMBOL);	
                    	    }
                    	    pushFollow(FOLLOW_param_definition_in_parameters996);
                    	    param_definition39=param_definition();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_param_definition.add(param_definition39.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop15;
                        }
                    } while (true);


                    }
                    break;

            }

            RIGHT_PAREN40=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_parameters1005); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RIGHT_PAREN.add(RIGHT_PAREN40);

            if ( state.backtracking==0 ) {
              	emit(RIGHT_PAREN40, DroolsEditorType.SYMBOL);	
            }


            // AST REWRITE
            // elements: param_definition, RIGHT_PAREN
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 546:3: -> ^( VT_PARAM_LIST ( param_definition )* RIGHT_PAREN )
            {
                // src/main/resources/org/drools/lang/DRL.g:546:6: ^( VT_PARAM_LIST ( param_definition )* RIGHT_PAREN )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(VT_PARAM_LIST, "VT_PARAM_LIST"), root_1);

                // src/main/resources/org/drools/lang/DRL.g:546:22: ( param_definition )*
                while ( stream_param_definition.hasNext() ) {
                    adaptor.addChild(root_1, stream_param_definition.nextTree());

                }
                stream_param_definition.reset();
                adaptor.addChild(root_1, stream_RIGHT_PAREN.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "parameters"

    public static class param_definition_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "param_definition"
    // src/main/resources/org/drools/lang/DRL.g:549:1: param_definition : ( data_type )? argument ;
    public final DRLParser.param_definition_return param_definition() throws RecognitionException {
        DRLParser.param_definition_return retval = new DRLParser.param_definition_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        DRLParser.data_type_return data_type41 = null;

        DRLParser.argument_return argument42 = null;



        try {
            // src/main/resources/org/drools/lang/DRL.g:550:2: ( ( data_type )? argument )
            // src/main/resources/org/drools/lang/DRL.g:550:4: ( data_type )? argument
            {
            root_0 = (Object)adaptor.nil();

            // src/main/resources/org/drools/lang/DRL.g:550:4: ( data_type )?
            int alt17=2;
            alt17 = dfa17.predict(input);
            switch (alt17) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:550:4: data_type
                    {
                    pushFollow(FOLLOW_data_type_in_param_definition1031);
                    data_type41=data_type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, data_type41.getTree());

                    }
                    break;

            }

            pushFollow(FOLLOW_argument_in_param_definition1034);
            argument42=argument();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, argument42.getTree());

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "param_definition"

    public static class argument_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "argument"
    // src/main/resources/org/drools/lang/DRL.g:553:1: argument : ID ( dimension_definition )* ;
    public final DRLParser.argument_return argument() throws RecognitionException {
        DRLParser.argument_return retval = new DRLParser.argument_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID43=null;
        DRLParser.dimension_definition_return dimension_definition44 = null;


        Object ID43_tree=null;

        try {
            // src/main/resources/org/drools/lang/DRL.g:554:2: ( ID ( dimension_definition )* )
            // src/main/resources/org/drools/lang/DRL.g:554:4: ID ( dimension_definition )*
            {
            root_0 = (Object)adaptor.nil();

            ID43=(Token)match(input,ID,FOLLOW_ID_in_argument1045); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ID43_tree = (Object)adaptor.create(ID43);
            adaptor.addChild(root_0, ID43_tree);
            }
            if ( state.backtracking==0 ) {
              	emit(ID43, DroolsEditorType.IDENTIFIER);	
            }
            // src/main/resources/org/drools/lang/DRL.g:555:3: ( dimension_definition )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==LEFT_SQUARE) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // src/main/resources/org/drools/lang/DRL.g:555:3: dimension_definition
            	    {
            	    pushFollow(FOLLOW_dimension_definition_in_argument1051);
            	    dimension_definition44=dimension_definition();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, dimension_definition44.getTree());

            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "argument"

    public static class type_declaration_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "type_declaration"
    // src/main/resources/org/drools/lang/DRL.g:558:1: type_declaration : declare_key type_declare_id ( decl_metadata )* ( decl_field )* end_key -> ^( declare_key type_declare_id ( decl_metadata )* ( decl_field )* end_key ) ;
    public final DRLParser.type_declaration_return type_declaration() throws RecognitionException {
        DRLParser.type_declaration_return retval = new DRLParser.type_declaration_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        DRLParser.declare_key_return declare_key45 = null;

        DRLParser.type_declare_id_return type_declare_id46 = null;

        DRLParser.decl_metadata_return decl_metadata47 = null;

        DRLParser.decl_field_return decl_field48 = null;

        DRLParser.end_key_return end_key49 = null;


        RewriteRuleSubtreeStream stream_decl_field=new RewriteRuleSubtreeStream(adaptor,"rule decl_field");
        RewriteRuleSubtreeStream stream_declare_key=new RewriteRuleSubtreeStream(adaptor,"rule declare_key");
        RewriteRuleSubtreeStream stream_decl_metadata=new RewriteRuleSubtreeStream(adaptor,"rule decl_metadata");
        RewriteRuleSubtreeStream stream_end_key=new RewriteRuleSubtreeStream(adaptor,"rule end_key");
        RewriteRuleSubtreeStream stream_type_declare_id=new RewriteRuleSubtreeStream(adaptor,"rule type_declare_id");
         pushParaphrases(DroolsParaphraseTypes.TYPE_DECLARE); if ( state.backtracking==0 ) beginSentence(DroolsSentenceType.TYPE_DECLARATION); 
        try {
            // src/main/resources/org/drools/lang/DRL.g:561:2: ( declare_key type_declare_id ( decl_metadata )* ( decl_field )* end_key -> ^( declare_key type_declare_id ( decl_metadata )* ( decl_field )* end_key ) )
            // src/main/resources/org/drools/lang/DRL.g:561:4: declare_key type_declare_id ( decl_metadata )* ( decl_field )* end_key
            {
            pushFollow(FOLLOW_declare_key_in_type_declaration1074);
            declare_key45=declare_key();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_declare_key.add(declare_key45.getTree());
            pushFollow(FOLLOW_type_declare_id_in_type_declaration1077);
            type_declare_id46=type_declare_id();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_type_declare_id.add(type_declare_id46.getTree());
            // src/main/resources/org/drools/lang/DRL.g:562:3: ( decl_metadata )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==AT) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // src/main/resources/org/drools/lang/DRL.g:562:3: decl_metadata
            	    {
            	    pushFollow(FOLLOW_decl_metadata_in_type_declaration1081);
            	    decl_metadata47=decl_metadata();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_decl_metadata.add(decl_metadata47.getTree());

            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);

            // src/main/resources/org/drools/lang/DRL.g:563:3: ( decl_field )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==ID) ) {
                    int LA20_1 = input.LA(2);

                    if ( ((LA20_1>=COLON && LA20_1<=EQUALS)) ) {
                        alt20=1;
                    }


                }


                switch (alt20) {
            	case 1 :
            	    // src/main/resources/org/drools/lang/DRL.g:563:3: decl_field
            	    {
            	    pushFollow(FOLLOW_decl_field_in_type_declaration1086);
            	    decl_field48=decl_field();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_decl_field.add(decl_field48.getTree());

            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);

            pushFollow(FOLLOW_end_key_in_type_declaration1091);
            end_key49=end_key();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_end_key.add(end_key49.getTree());


            // AST REWRITE
            // elements: decl_metadata, decl_field, declare_key, end_key, type_declare_id
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 565:3: -> ^( declare_key type_declare_id ( decl_metadata )* ( decl_field )* end_key )
            {
                // src/main/resources/org/drools/lang/DRL.g:565:6: ^( declare_key type_declare_id ( decl_metadata )* ( decl_field )* end_key )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_declare_key.nextNode(), root_1);

                adaptor.addChild(root_1, stream_type_declare_id.nextTree());
                // src/main/resources/org/drools/lang/DRL.g:565:36: ( decl_metadata )*
                while ( stream_decl_metadata.hasNext() ) {
                    adaptor.addChild(root_1, stream_decl_metadata.nextTree());

                }
                stream_decl_metadata.reset();
                // src/main/resources/org/drools/lang/DRL.g:565:51: ( decl_field )*
                while ( stream_decl_field.hasNext() ) {
                    adaptor.addChild(root_1, stream_decl_field.nextTree());

                }
                stream_decl_field.reset();
                adaptor.addChild(root_1, stream_end_key.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
               paraphrases.pop(); 
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "type_declaration"

    public static class type_declare_id_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "type_declare_id"
    // src/main/resources/org/drools/lang/DRL.g:568:1: type_declare_id : id= ID -> VT_TYPE_DECLARE_ID[$id] ;
    public final DRLParser.type_declare_id_return type_declare_id() throws RecognitionException {
        DRLParser.type_declare_id_return retval = new DRLParser.type_declare_id_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;

        Object id_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // src/main/resources/org/drools/lang/DRL.g:569:2: (id= ID -> VT_TYPE_DECLARE_ID[$id] )
            // src/main/resources/org/drools/lang/DRL.g:569:5: id= ID
            {
            id=(Token)match(input,ID,FOLLOW_ID_in_type_declare_id1123); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id);

            if ( state.backtracking==0 ) {
              	emit(id, DroolsEditorType.IDENTIFIER);
              		setParaphrasesValue(DroolsParaphraseTypes.TYPE_DECLARE, (id!=null?id.getText():null));	
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 571:72: -> VT_TYPE_DECLARE_ID[$id]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VT_TYPE_DECLARE_ID, id));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "type_declare_id"

    public static class decl_metadata_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "decl_metadata"
    // src/main/resources/org/drools/lang/DRL.g:574:1: decl_metadata : AT ID paren_chunk -> ^( AT ID paren_chunk ) ;
    public final DRLParser.decl_metadata_return decl_metadata() throws RecognitionException {
        DRLParser.decl_metadata_return retval = new DRLParser.decl_metadata_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token AT50=null;
        Token ID51=null;
        DRLParser.paren_chunk_return paren_chunk52 = null;


        Object AT50_tree=null;
        Object ID51_tree=null;
        RewriteRuleTokenStream stream_AT=new RewriteRuleTokenStream(adaptor,"token AT");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_paren_chunk=new RewriteRuleSubtreeStream(adaptor,"rule paren_chunk");
        try {
            // src/main/resources/org/drools/lang/DRL.g:575:2: ( AT ID paren_chunk -> ^( AT ID paren_chunk ) )
            // src/main/resources/org/drools/lang/DRL.g:575:4: AT ID paren_chunk
            {
            AT50=(Token)match(input,AT,FOLLOW_AT_in_decl_metadata1142); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_AT.add(AT50);

            if ( state.backtracking==0 ) {
              	emit(AT50, DroolsEditorType.SYMBOL);	
            }
            ID51=(Token)match(input,ID,FOLLOW_ID_in_decl_metadata1150); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(ID51);

            if ( state.backtracking==0 ) {
              	emit(ID51, DroolsEditorType.IDENTIFIER);	
            }
            pushFollow(FOLLOW_paren_chunk_in_decl_metadata1157);
            paren_chunk52=paren_chunk();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_paren_chunk.add(paren_chunk52.getTree());


            // AST REWRITE
            // elements: paren_chunk, ID, AT
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 580:3: -> ^( AT ID paren_chunk )
            {
                // src/main/resources/org/drools/lang/DRL.g:580:6: ^( AT ID paren_chunk )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_AT.nextNode(), root_1);

                adaptor.addChild(root_1, stream_ID.nextNode());
                adaptor.addChild(root_1, stream_paren_chunk.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "decl_metadata"

    public static class decl_field_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "decl_field"
    // src/main/resources/org/drools/lang/DRL.g:583:1: decl_field : ID ( decl_field_initialization )? COLON data_type ( decl_metadata )* -> ^( ID ( decl_field_initialization )? data_type ( decl_metadata )* ) ;
    public final DRLParser.decl_field_return decl_field() throws RecognitionException {
        DRLParser.decl_field_return retval = new DRLParser.decl_field_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID53=null;
        Token COLON55=null;
        DRLParser.decl_field_initialization_return decl_field_initialization54 = null;

        DRLParser.data_type_return data_type56 = null;

        DRLParser.decl_metadata_return decl_metadata57 = null;


        Object ID53_tree=null;
        Object COLON55_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_decl_metadata=new RewriteRuleSubtreeStream(adaptor,"rule decl_metadata");
        RewriteRuleSubtreeStream stream_decl_field_initialization=new RewriteRuleSubtreeStream(adaptor,"rule decl_field_initialization");
        RewriteRuleSubtreeStream stream_data_type=new RewriteRuleSubtreeStream(adaptor,"rule data_type");
        try {
            // src/main/resources/org/drools/lang/DRL.g:584:2: ( ID ( decl_field_initialization )? COLON data_type ( decl_metadata )* -> ^( ID ( decl_field_initialization )? data_type ( decl_metadata )* ) )
            // src/main/resources/org/drools/lang/DRL.g:584:4: ID ( decl_field_initialization )? COLON data_type ( decl_metadata )*
            {
            ID53=(Token)match(input,ID,FOLLOW_ID_in_decl_field1180); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(ID53);

            if ( state.backtracking==0 ) {
              	emit(ID53, DroolsEditorType.IDENTIFIER);	
            }
            // src/main/resources/org/drools/lang/DRL.g:585:3: ( decl_field_initialization )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==EQUALS) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:585:3: decl_field_initialization
                    {
                    pushFollow(FOLLOW_decl_field_initialization_in_decl_field1186);
                    decl_field_initialization54=decl_field_initialization();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_decl_field_initialization.add(decl_field_initialization54.getTree());

                    }
                    break;

            }

            COLON55=(Token)match(input,COLON,FOLLOW_COLON_in_decl_field1192); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_COLON.add(COLON55);

            if ( state.backtracking==0 ) {
              	emit(COLON55, DroolsEditorType.SYMBOL);	
            }
            pushFollow(FOLLOW_data_type_in_decl_field1198);
            data_type56=data_type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_data_type.add(data_type56.getTree());
            // src/main/resources/org/drools/lang/DRL.g:588:3: ( decl_metadata )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==AT) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // src/main/resources/org/drools/lang/DRL.g:588:3: decl_metadata
            	    {
            	    pushFollow(FOLLOW_decl_metadata_in_decl_field1202);
            	    decl_metadata57=decl_metadata();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_decl_metadata.add(decl_metadata57.getTree());

            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);



            // AST REWRITE
            // elements: decl_metadata, ID, data_type, decl_field_initialization
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 589:3: -> ^( ID ( decl_field_initialization )? data_type ( decl_metadata )* )
            {
                // src/main/resources/org/drools/lang/DRL.g:589:6: ^( ID ( decl_field_initialization )? data_type ( decl_metadata )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_ID.nextNode(), root_1);

                // src/main/resources/org/drools/lang/DRL.g:589:11: ( decl_field_initialization )?
                if ( stream_decl_field_initialization.hasNext() ) {
                    adaptor.addChild(root_1, stream_decl_field_initialization.nextTree());

                }
                stream_decl_field_initialization.reset();
                adaptor.addChild(root_1, stream_data_type.nextTree());
                // src/main/resources/org/drools/lang/DRL.g:589:48: ( decl_metadata )*
                while ( stream_decl_metadata.hasNext() ) {
                    adaptor.addChild(root_1, stream_decl_metadata.nextTree());

                }
                stream_decl_metadata.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "decl_field"

    public static class decl_field_initialization_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "decl_field_initialization"
    // src/main/resources/org/drools/lang/DRL.g:592:1: decl_field_initialization : EQUALS paren_chunk -> ^( EQUALS paren_chunk ) ;
    public final DRLParser.decl_field_initialization_return decl_field_initialization() throws RecognitionException {
        DRLParser.decl_field_initialization_return retval = new DRLParser.decl_field_initialization_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EQUALS58=null;
        DRLParser.paren_chunk_return paren_chunk59 = null;


        Object EQUALS58_tree=null;
        RewriteRuleTokenStream stream_EQUALS=new RewriteRuleTokenStream(adaptor,"token EQUALS");
        RewriteRuleSubtreeStream stream_paren_chunk=new RewriteRuleSubtreeStream(adaptor,"rule paren_chunk");
        try {
            // src/main/resources/org/drools/lang/DRL.g:593:2: ( EQUALS paren_chunk -> ^( EQUALS paren_chunk ) )
            // src/main/resources/org/drools/lang/DRL.g:593:4: EQUALS paren_chunk
            {
            EQUALS58=(Token)match(input,EQUALS,FOLLOW_EQUALS_in_decl_field_initialization1230); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_EQUALS.add(EQUALS58);

            if ( state.backtracking==0 ) {
              	emit(EQUALS58, DroolsEditorType.SYMBOL);	
            }
            pushFollow(FOLLOW_paren_chunk_in_decl_field_initialization1236);
            paren_chunk59=paren_chunk();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_paren_chunk.add(paren_chunk59.getTree());


            // AST REWRITE
            // elements: paren_chunk, EQUALS
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 595:2: -> ^( EQUALS paren_chunk )
            {
                // src/main/resources/org/drools/lang/DRL.g:595:5: ^( EQUALS paren_chunk )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_EQUALS.nextNode(), root_1);

                adaptor.addChild(root_1, stream_paren_chunk.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "decl_field_initialization"

    public static class template_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "template"
    // src/main/resources/org/drools/lang/DRL.g:598:1: template : template_key template_id (semi1= SEMICOLON )? ( template_slot )+ end= end_key (semi2= SEMICOLON )? -> ^( template_key template_id ( template_slot )+ end_key ) ;
    public final DRLParser.template_return template() throws RecognitionException {
        DRLParser.template_return retval = new DRLParser.template_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token semi1=null;
        Token semi2=null;
        DRLParser.end_key_return end = null;

        DRLParser.template_key_return template_key60 = null;

        DRLParser.template_id_return template_id61 = null;

        DRLParser.template_slot_return template_slot62 = null;


        Object semi1_tree=null;
        Object semi2_tree=null;
        RewriteRuleTokenStream stream_SEMICOLON=new RewriteRuleTokenStream(adaptor,"token SEMICOLON");
        RewriteRuleSubtreeStream stream_template_id=new RewriteRuleSubtreeStream(adaptor,"rule template_id");
        RewriteRuleSubtreeStream stream_template_slot=new RewriteRuleSubtreeStream(adaptor,"rule template_slot");
        RewriteRuleSubtreeStream stream_template_key=new RewriteRuleSubtreeStream(adaptor,"rule template_key");
        RewriteRuleSubtreeStream stream_end_key=new RewriteRuleSubtreeStream(adaptor,"rule end_key");
         pushParaphrases(DroolsParaphraseTypes.TEMPLATE); 
        try {
            // src/main/resources/org/drools/lang/DRL.g:601:2: ( template_key template_id (semi1= SEMICOLON )? ( template_slot )+ end= end_key (semi2= SEMICOLON )? -> ^( template_key template_id ( template_slot )+ end_key ) )
            // src/main/resources/org/drools/lang/DRL.g:602:2: template_key template_id (semi1= SEMICOLON )? ( template_slot )+ end= end_key (semi2= SEMICOLON )?
            {
            if ( state.backtracking==0 ) {
              	beginSentence(DroolsSentenceType.TEMPLATE);	
            }
            pushFollow(FOLLOW_template_key_in_template1273);
            template_key60=template_key();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_template_key.add(template_key60.getTree());
            pushFollow(FOLLOW_template_id_in_template1275);
            template_id61=template_id();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_template_id.add(template_id61.getTree());
            // src/main/resources/org/drools/lang/DRL.g:604:8: (semi1= SEMICOLON )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==SEMICOLON) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:604:8: semi1= SEMICOLON
                    {
                    semi1=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_template1282); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMICOLON.add(semi1);


                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              	emit(semi1, DroolsEditorType.SYMBOL);	
            }
            // src/main/resources/org/drools/lang/DRL.g:606:3: ( template_slot )+
            int cnt24=0;
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==ID) ) {
                    int LA24_1 = input.LA(2);

                    if ( (LA24_1==DOT||LA24_1==LEFT_SQUARE) ) {
                        alt24=1;
                    }
                    else if ( (LA24_1==ID) ) {
                        int LA24_3 = input.LA(3);

                        if ( (LA24_3==ID) ) {
                            int LA24_5 = input.LA(4);

                            if ( (!((((validateIdentifierKey(DroolsSoftKeywords.END)))))) ) {
                                alt24=1;
                            }


                        }
                        else if ( (LA24_3==SEMICOLON) ) {
                            alt24=1;
                        }


                    }


                }


                switch (alt24) {
            	case 1 :
            	    // src/main/resources/org/drools/lang/DRL.g:606:3: template_slot
            	    {
            	    pushFollow(FOLLOW_template_slot_in_template1290);
            	    template_slot62=template_slot();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_template_slot.add(template_slot62.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt24 >= 1 ) break loop24;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(24, input);
                        throw eee;
                }
                cnt24++;
            } while (true);

            pushFollow(FOLLOW_end_key_in_template1297);
            end=end_key();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_end_key.add(end.getTree());
            // src/main/resources/org/drools/lang/DRL.g:607:20: (semi2= SEMICOLON )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==SEMICOLON) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:607:20: semi2= SEMICOLON
                    {
                    semi2=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_template1301); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMICOLON.add(semi2);


                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              	emit(semi2, DroolsEditorType.SYMBOL);	
            }


            // AST REWRITE
            // elements: template_key, end_key, template_id, template_slot
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 609:3: -> ^( template_key template_id ( template_slot )+ end_key )
            {
                // src/main/resources/org/drools/lang/DRL.g:609:6: ^( template_key template_id ( template_slot )+ end_key )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_template_key.nextNode(), root_1);

                adaptor.addChild(root_1, stream_template_id.nextTree());
                if ( !(stream_template_slot.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_template_slot.hasNext() ) {
                    adaptor.addChild(root_1, stream_template_slot.nextTree());

                }
                stream_template_slot.reset();
                adaptor.addChild(root_1, stream_end_key.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
               paraphrases.pop(); 
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "template"

    public static class template_id_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "template_id"
    // src/main/resources/org/drools/lang/DRL.g:612:1: template_id : (id= ID -> VT_TEMPLATE_ID[$id] | id= STRING -> VT_TEMPLATE_ID[$id] );
    public final DRLParser.template_id_return template_id() throws RecognitionException {
        DRLParser.template_id_return retval = new DRLParser.template_id_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;

        Object id_tree=null;
        RewriteRuleTokenStream stream_STRING=new RewriteRuleTokenStream(adaptor,"token STRING");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // src/main/resources/org/drools/lang/DRL.g:613:2: (id= ID -> VT_TEMPLATE_ID[$id] | id= STRING -> VT_TEMPLATE_ID[$id] )
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==ID) ) {
                alt26=1;
            }
            else if ( (LA26_0==STRING) ) {
                alt26=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;
            }
            switch (alt26) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:613:5: id= ID
                    {
                    id=(Token)match(input,ID,FOLLOW_ID_in_template_id1334); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ID.add(id);

                    if ( state.backtracking==0 ) {
                      	emit(id, DroolsEditorType.IDENTIFIER);
                      		setParaphrasesValue(DroolsParaphraseTypes.TEMPLATE, (id!=null?id.getText():null));	
                    }


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 615:68: -> VT_TEMPLATE_ID[$id]
                    {
                        adaptor.addChild(root_0, (Object)adaptor.create(VT_TEMPLATE_ID, id));

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // src/main/resources/org/drools/lang/DRL.g:616:5: id= STRING
                    {
                    id=(Token)match(input,STRING,FOLLOW_STRING_in_template_id1350); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_STRING.add(id);

                    if ( state.backtracking==0 ) {
                      	emit(id, DroolsEditorType.IDENTIFIER);
                      		setParaphrasesValue(DroolsParaphraseTypes.TEMPLATE, (id!=null?id.getText():null));	
                    }


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 618:68: -> VT_TEMPLATE_ID[$id]
                    {
                        adaptor.addChild(root_0, (Object)adaptor.create(VT_TEMPLATE_ID, id));

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "template_id"

    public static class template_slot_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "template_slot"
    // src/main/resources/org/drools/lang/DRL.g:621:1: template_slot : data_type slot_id ( SEMICOLON )? -> ^( VT_SLOT data_type slot_id ) ;
    public final DRLParser.template_slot_return template_slot() throws RecognitionException {
        DRLParser.template_slot_return retval = new DRLParser.template_slot_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token SEMICOLON65=null;
        DRLParser.data_type_return data_type63 = null;

        DRLParser.slot_id_return slot_id64 = null;


        Object SEMICOLON65_tree=null;
        RewriteRuleTokenStream stream_SEMICOLON=new RewriteRuleTokenStream(adaptor,"token SEMICOLON");
        RewriteRuleSubtreeStream stream_slot_id=new RewriteRuleSubtreeStream(adaptor,"rule slot_id");
        RewriteRuleSubtreeStream stream_data_type=new RewriteRuleSubtreeStream(adaptor,"rule data_type");
        try {
            // src/main/resources/org/drools/lang/DRL.g:622:2: ( data_type slot_id ( SEMICOLON )? -> ^( VT_SLOT data_type slot_id ) )
            // src/main/resources/org/drools/lang/DRL.g:622:5: data_type slot_id ( SEMICOLON )?
            {
            pushFollow(FOLLOW_data_type_in_template_slot1370);
            data_type63=data_type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_data_type.add(data_type63.getTree());
            pushFollow(FOLLOW_slot_id_in_template_slot1372);
            slot_id64=slot_id();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_slot_id.add(slot_id64.getTree());
            // src/main/resources/org/drools/lang/DRL.g:622:23: ( SEMICOLON )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==SEMICOLON) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:622:23: SEMICOLON
                    {
                    SEMICOLON65=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_template_slot1374); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMICOLON.add(SEMICOLON65);


                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              	emit(SEMICOLON65, DroolsEditorType.SYMBOL);	
            }


            // AST REWRITE
            // elements: data_type, slot_id
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 624:3: -> ^( VT_SLOT data_type slot_id )
            {
                // src/main/resources/org/drools/lang/DRL.g:624:6: ^( VT_SLOT data_type slot_id )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(VT_SLOT, "VT_SLOT"), root_1);

                adaptor.addChild(root_1, stream_data_type.nextTree());
                adaptor.addChild(root_1, stream_slot_id.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "template_slot"

    public static class slot_id_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "slot_id"
    // src/main/resources/org/drools/lang/DRL.g:627:1: slot_id : id= ID -> VT_SLOT_ID[$id] ;
    public final DRLParser.slot_id_return slot_id() throws RecognitionException {
        DRLParser.slot_id_return retval = new DRLParser.slot_id_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;

        Object id_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // src/main/resources/org/drools/lang/DRL.g:628:2: (id= ID -> VT_SLOT_ID[$id] )
            // src/main/resources/org/drools/lang/DRL.g:628:4: id= ID
            {
            id=(Token)match(input,ID,FOLLOW_ID_in_slot_id1403); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id);

            if ( state.backtracking==0 ) {
              	emit(id, DroolsEditorType.IDENTIFIER);	
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 630:3: -> VT_SLOT_ID[$id]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VT_SLOT_ID, id));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "slot_id"

    public static class rule_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rule"
    // src/main/resources/org/drools/lang/DRL.g:633:1: rule : rule_key rule_id ( extend_key rule_id )? ( decl_metadata )* ( rule_attributes )? ( when_part )? rhs_chunk -> ^( rule_key rule_id ( ^( extend_key rule_id ) )? ( decl_metadata )* ( rule_attributes )? ( when_part )? rhs_chunk ) ;
    public final DRLParser.rule_return rule() throws RecognitionException {
        DRLParser.rule_return retval = new DRLParser.rule_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        DRLParser.rule_key_return rule_key66 = null;

        DRLParser.rule_id_return rule_id67 = null;

        DRLParser.extend_key_return extend_key68 = null;

        DRLParser.rule_id_return rule_id69 = null;

        DRLParser.decl_metadata_return decl_metadata70 = null;

        DRLParser.rule_attributes_return rule_attributes71 = null;

        DRLParser.when_part_return when_part72 = null;

        DRLParser.rhs_chunk_return rhs_chunk73 = null;


        RewriteRuleSubtreeStream stream_rule_key=new RewriteRuleSubtreeStream(adaptor,"rule rule_key");
        RewriteRuleSubtreeStream stream_rule_id=new RewriteRuleSubtreeStream(adaptor,"rule rule_id");
        RewriteRuleSubtreeStream stream_when_part=new RewriteRuleSubtreeStream(adaptor,"rule when_part");
        RewriteRuleSubtreeStream stream_rule_attributes=new RewriteRuleSubtreeStream(adaptor,"rule rule_attributes");
        RewriteRuleSubtreeStream stream_rhs_chunk=new RewriteRuleSubtreeStream(adaptor,"rule rhs_chunk");
        RewriteRuleSubtreeStream stream_decl_metadata=new RewriteRuleSubtreeStream(adaptor,"rule decl_metadata");
        RewriteRuleSubtreeStream stream_extend_key=new RewriteRuleSubtreeStream(adaptor,"rule extend_key");
         boolean isFailed = true; pushParaphrases(DroolsParaphraseTypes.RULE); 
        try {
            // src/main/resources/org/drools/lang/DRL.g:636:2: ( rule_key rule_id ( extend_key rule_id )? ( decl_metadata )* ( rule_attributes )? ( when_part )? rhs_chunk -> ^( rule_key rule_id ( ^( extend_key rule_id ) )? ( decl_metadata )* ( rule_attributes )? ( when_part )? rhs_chunk ) )
            // src/main/resources/org/drools/lang/DRL.g:637:2: rule_key rule_id ( extend_key rule_id )? ( decl_metadata )* ( rule_attributes )? ( when_part )? rhs_chunk
            {
            if ( state.backtracking==0 ) {
              	beginSentence(DroolsSentenceType.RULE);	
            }
            pushFollow(FOLLOW_rule_key_in_rule1440);
            rule_key66=rule_key();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_rule_key.add(rule_key66.getTree());
            pushFollow(FOLLOW_rule_id_in_rule1442);
            rule_id67=rule_id();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_rule_id.add(rule_id67.getTree());
            if ( state.backtracking==0 ) {
              	emit(Location.LOCATION_RULE_HEADER);	
            }
            // src/main/resources/org/drools/lang/DRL.g:640:3: ( extend_key rule_id )?
            int alt28=2;
            alt28 = dfa28.predict(input);
            switch (alt28) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:640:4: extend_key rule_id
                    {
                    pushFollow(FOLLOW_extend_key_in_rule1451);
                    extend_key68=extend_key();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_extend_key.add(extend_key68.getTree());
                    pushFollow(FOLLOW_rule_id_in_rule1453);
                    rule_id69=rule_id();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rule_id.add(rule_id69.getTree());

                    }
                    break;

            }

            // src/main/resources/org/drools/lang/DRL.g:640:25: ( decl_metadata )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( (LA29_0==AT) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // src/main/resources/org/drools/lang/DRL.g:640:25: decl_metadata
            	    {
            	    pushFollow(FOLLOW_decl_metadata_in_rule1457);
            	    decl_metadata70=decl_metadata();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_decl_metadata.add(decl_metadata70.getTree());

            	    }
            	    break;

            	default :
            	    break loop29;
                }
            } while (true);

            // src/main/resources/org/drools/lang/DRL.g:640:40: ( rule_attributes )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==ID) && ((((validateIdentifierKey(DroolsSoftKeywords.ACTIVATION) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.GROUP)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateIdentifierKey(DroolsSoftKeywords.NO) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.LOOP)))||((validateIdentifierKey(DroolsSoftKeywords.DURATION)))||((validateIdentifierKey(DroolsSoftKeywords.DATE) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.EFFECTIVE)))||((validateIdentifierKey(DroolsSoftKeywords.AGENDA) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.GROUP)))||((validateIdentifierKey(DroolsSoftKeywords.DATE) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.EXPIRES)))||((validateIdentifierKey(DroolsSoftKeywords.LOCK) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.ON) && validateLT(4, "-") && validateLT(5, DroolsSoftKeywords.ACTIVE)))||((validateIdentifierKey(DroolsSoftKeywords.SALIENCE)))||((validateIdentifierKey(DroolsSoftKeywords.ENABLED)))||((validateIdentifierKey(DroolsSoftKeywords.RULEFLOW) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.GROUP)))||((validateIdentifierKey(DroolsSoftKeywords.ATTRIBUTES)))||((validateIdentifierKey(DroolsSoftKeywords.AUTO) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.FOCUS)))))) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:640:40: rule_attributes
                    {
                    pushFollow(FOLLOW_rule_attributes_in_rule1460);
                    rule_attributes71=rule_attributes();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rule_attributes.add(rule_attributes71.getTree());

                    }
                    break;

            }

            // src/main/resources/org/drools/lang/DRL.g:640:57: ( when_part )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==WHEN) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:640:57: when_part
                    {
                    pushFollow(FOLLOW_when_part_in_rule1463);
                    when_part72=when_part();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_when_part.add(when_part72.getTree());

                    }
                    break;

            }

            pushFollow(FOLLOW_rhs_chunk_in_rule1466);
            rhs_chunk73=rhs_chunk();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_rhs_chunk.add(rhs_chunk73.getTree());


            // AST REWRITE
            // elements: rule_attributes, decl_metadata, rule_id, rule_id, rhs_chunk, when_part, rule_key, extend_key
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 641:3: -> ^( rule_key rule_id ( ^( extend_key rule_id ) )? ( decl_metadata )* ( rule_attributes )? ( when_part )? rhs_chunk )
            {
                // src/main/resources/org/drools/lang/DRL.g:641:6: ^( rule_key rule_id ( ^( extend_key rule_id ) )? ( decl_metadata )* ( rule_attributes )? ( when_part )? rhs_chunk )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_rule_key.nextNode(), root_1);

                adaptor.addChild(root_1, stream_rule_id.nextTree());
                // src/main/resources/org/drools/lang/DRL.g:641:25: ( ^( extend_key rule_id ) )?
                if ( stream_rule_id.hasNext()||stream_extend_key.hasNext() ) {
                    // src/main/resources/org/drools/lang/DRL.g:641:25: ^( extend_key rule_id )
                    {
                    Object root_2 = (Object)adaptor.nil();
                    root_2 = (Object)adaptor.becomeRoot(stream_extend_key.nextNode(), root_2);

                    adaptor.addChild(root_2, stream_rule_id.nextTree());

                    adaptor.addChild(root_1, root_2);
                    }

                }
                stream_rule_id.reset();
                stream_extend_key.reset();
                // src/main/resources/org/drools/lang/DRL.g:641:48: ( decl_metadata )*
                while ( stream_decl_metadata.hasNext() ) {
                    adaptor.addChild(root_1, stream_decl_metadata.nextTree());

                }
                stream_decl_metadata.reset();
                // src/main/resources/org/drools/lang/DRL.g:641:63: ( rule_attributes )?
                if ( stream_rule_attributes.hasNext() ) {
                    adaptor.addChild(root_1, stream_rule_attributes.nextTree());

                }
                stream_rule_attributes.reset();
                // src/main/resources/org/drools/lang/DRL.g:641:80: ( when_part )?
                if ( stream_when_part.hasNext() ) {
                    adaptor.addChild(root_1, stream_when_part.nextTree());

                }
                stream_when_part.reset();
                adaptor.addChild(root_1, stream_rhs_chunk.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
               paraphrases.pop(); isFailed = false; 
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {

            	if (isEditorInterfaceEnabled && isFailed) {
            		if (input.LA(6) == EOF && input.LA(1) == ID && input.LA(2) == MISC && input.LA(3) == ID && 
            			input.LA(5) == MISC && input.LA(6) == ID && 
            			validateLT(1, DroolsSoftKeywords.LOCK) && validateLT(3, DroolsSoftKeywords.ON) &&
            			validateLT(5, DroolsSoftKeywords.ACTIVE)){
            			emit(input.LT(1), DroolsEditorType.KEYWORD);
            			emit(input.LT(2), DroolsEditorType.KEYWORD);
            			emit(input.LT(3), DroolsEditorType.KEYWORD);
            			emit(input.LT(4), DroolsEditorType.KEYWORD);
            			emit(input.LT(5), DroolsEditorType.KEYWORD);
            			emit(Location.LOCATION_RULE_HEADER_KEYWORD);
            			input.consume();
            			input.consume();
            			input.consume();
            			input.consume();
            			input.consume();
            		} else if (input.LA(4) == EOF && input.LA(1) == ID && input.LA(2) == MISC && input.LA(3) == ID && 
            			(	(validateLT(1, DroolsSoftKeywords.ACTIVATION) && validateLT(3, DroolsSoftKeywords.GROUP)) ||
            				(validateLT(1, DroolsSoftKeywords.DATE) && validateLT(3, DroolsSoftKeywords.EXPIRES)) ||
            				(validateLT(1, DroolsSoftKeywords.NO) && validateLT(3, DroolsSoftKeywords.LOOP)) ||
            				(validateLT(1, DroolsSoftKeywords.DATE) && validateLT(3, DroolsSoftKeywords.EFFECTIVE)) ||
            				(validateLT(1, DroolsSoftKeywords.AUTO) && validateLT(3, DroolsSoftKeywords.FOCUS)) ||
            				(validateLT(1, DroolsSoftKeywords.ACTIVATION) && validateLT(3, DroolsSoftKeywords.GROUP)) ||
            				(validateLT(1, DroolsSoftKeywords.RULEFLOW) && validateLT(3, DroolsSoftKeywords.GROUP)) ||
            				(validateLT(1, DroolsSoftKeywords.AGENDA) && validateLT(3, DroolsSoftKeywords.GROUP))	)){
            			emit(input.LT(1), DroolsEditorType.KEYWORD);
            			emit(input.LT(2), DroolsEditorType.KEYWORD);
            			emit(input.LT(3), DroolsEditorType.KEYWORD);
            			emit(Location.LOCATION_RULE_HEADER_KEYWORD);
            			input.consume();
            			input.consume();
            			input.consume();
            		} else if (input.LA(2) == EOF && input.LA(1) == ID && 
            				(validateLT(1, DroolsSoftKeywords.DIALECT) || validateLT(1, DroolsSoftKeywords.ENABLED) ||
            				 validateLT(1, DroolsSoftKeywords.SALIENCE) || validateLT(1, DroolsSoftKeywords.DURATION))){
            			emit(input.LT(1), DroolsEditorType.KEYWORD);
            			emit(Location.LOCATION_RULE_HEADER_KEYWORD);
            			input.consume();
            		}
            	}

        }
        return retval;
    }
    // $ANTLR end "rule"

    public static class when_part_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "when_part"
    // src/main/resources/org/drools/lang/DRL.g:686:1: when_part : WHEN ( COLON )? normal_lhs_block -> WHEN normal_lhs_block ;
    public final DRLParser.when_part_return when_part() throws RecognitionException {
        DRLParser.when_part_return retval = new DRLParser.when_part_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token WHEN74=null;
        Token COLON75=null;
        DRLParser.normal_lhs_block_return normal_lhs_block76 = null;


        Object WHEN74_tree=null;
        Object COLON75_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_WHEN=new RewriteRuleTokenStream(adaptor,"token WHEN");
        RewriteRuleSubtreeStream stream_normal_lhs_block=new RewriteRuleSubtreeStream(adaptor,"rule normal_lhs_block");
        try {
            // src/main/resources/org/drools/lang/DRL.g:687:2: ( WHEN ( COLON )? normal_lhs_block -> WHEN normal_lhs_block )
            // src/main/resources/org/drools/lang/DRL.g:687:5: WHEN ( COLON )? normal_lhs_block
            {
            WHEN74=(Token)match(input,WHEN,FOLLOW_WHEN_in_when_part1510); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_WHEN.add(WHEN74);

            if ( state.backtracking==0 ) {
              	emit(WHEN74, DroolsEditorType.KEYWORD);	
            }
            // src/main/resources/org/drools/lang/DRL.g:688:3: ( COLON )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==COLON) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:688:3: COLON
                    {
                    COLON75=(Token)match(input,COLON,FOLLOW_COLON_in_when_part1516); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COLON.add(COLON75);


                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              	emit(COLON75, DroolsEditorType.SYMBOL);	
            }
            if ( state.backtracking==0 ) {
              	emit(Location.LOCATION_LHS_BEGIN_OF_CONDITION);	
            }
            pushFollow(FOLLOW_normal_lhs_block_in_when_part1526);
            normal_lhs_block76=normal_lhs_block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_normal_lhs_block.add(normal_lhs_block76.getTree());


            // AST REWRITE
            // elements: WHEN, normal_lhs_block
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 691:2: -> WHEN normal_lhs_block
            {
                adaptor.addChild(root_0, stream_WHEN.nextNode());
                adaptor.addChild(root_0, stream_normal_lhs_block.nextTree());

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "when_part"

    public static class rule_id_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rule_id"
    // src/main/resources/org/drools/lang/DRL.g:694:1: rule_id : (id= ID -> VT_RULE_ID[$id] | id= STRING -> VT_RULE_ID[$id] );
    public final DRLParser.rule_id_return rule_id() throws RecognitionException {
        DRLParser.rule_id_return retval = new DRLParser.rule_id_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;

        Object id_tree=null;
        RewriteRuleTokenStream stream_STRING=new RewriteRuleTokenStream(adaptor,"token STRING");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // src/main/resources/org/drools/lang/DRL.g:695:2: (id= ID -> VT_RULE_ID[$id] | id= STRING -> VT_RULE_ID[$id] )
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==ID) ) {
                alt33=1;
            }
            else if ( (LA33_0==STRING) ) {
                alt33=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;
            }
            switch (alt33) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:695:5: id= ID
                    {
                    id=(Token)match(input,ID,FOLLOW_ID_in_rule_id1547); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ID.add(id);

                    if ( state.backtracking==0 ) {
                      	emit(id, DroolsEditorType.IDENTIFIER);
                      		setParaphrasesValue(DroolsParaphraseTypes.RULE, (id!=null?id.getText():null));	
                    }


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 697:64: -> VT_RULE_ID[$id]
                    {
                        adaptor.addChild(root_0, (Object)adaptor.create(VT_RULE_ID, id));

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // src/main/resources/org/drools/lang/DRL.g:698:5: id= STRING
                    {
                    id=(Token)match(input,STRING,FOLLOW_STRING_in_rule_id1563); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_STRING.add(id);

                    if ( state.backtracking==0 ) {
                      	emit(id, DroolsEditorType.IDENTIFIER);
                      		setParaphrasesValue(DroolsParaphraseTypes.RULE, (id!=null?id.getText():null));	
                    }


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 700:64: -> VT_RULE_ID[$id]
                    {
                        adaptor.addChild(root_0, (Object)adaptor.create(VT_RULE_ID, id));

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rule_id"

    public static class rule_attributes_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rule_attributes"
    // src/main/resources/org/drools/lang/DRL.g:703:1: rule_attributes : ( attributes_key COLON )? rule_attribute ( ( COMMA )? attr= rule_attribute )* -> ^( VT_RULE_ATTRIBUTES ( attributes_key )? ( rule_attribute )+ ) ;
    public final DRLParser.rule_attributes_return rule_attributes() throws RecognitionException {
        DRLParser.rule_attributes_return retval = new DRLParser.rule_attributes_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COLON78=null;
        Token COMMA80=null;
        DRLParser.rule_attribute_return attr = null;

        DRLParser.attributes_key_return attributes_key77 = null;

        DRLParser.rule_attribute_return rule_attribute79 = null;


        Object COLON78_tree=null;
        Object COMMA80_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleSubtreeStream stream_attributes_key=new RewriteRuleSubtreeStream(adaptor,"rule attributes_key");
        RewriteRuleSubtreeStream stream_rule_attribute=new RewriteRuleSubtreeStream(adaptor,"rule rule_attribute");
        try {
            // src/main/resources/org/drools/lang/DRL.g:704:2: ( ( attributes_key COLON )? rule_attribute ( ( COMMA )? attr= rule_attribute )* -> ^( VT_RULE_ATTRIBUTES ( attributes_key )? ( rule_attribute )+ ) )
            // src/main/resources/org/drools/lang/DRL.g:704:4: ( attributes_key COLON )? rule_attribute ( ( COMMA )? attr= rule_attribute )*
            {
            // src/main/resources/org/drools/lang/DRL.g:704:4: ( attributes_key COLON )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==ID) && ((((validateIdentifierKey(DroolsSoftKeywords.ACTIVATION) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.GROUP)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateIdentifierKey(DroolsSoftKeywords.NO) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.LOOP)))||((validateIdentifierKey(DroolsSoftKeywords.DURATION)))||((validateIdentifierKey(DroolsSoftKeywords.DATE) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.EFFECTIVE)))||((validateIdentifierKey(DroolsSoftKeywords.AGENDA) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.GROUP)))||((validateIdentifierKey(DroolsSoftKeywords.DATE) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.EXPIRES)))||((validateIdentifierKey(DroolsSoftKeywords.LOCK) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.ON) && validateLT(4, "-") && validateLT(5, DroolsSoftKeywords.ACTIVE)))||((validateIdentifierKey(DroolsSoftKeywords.SALIENCE)))||((validateIdentifierKey(DroolsSoftKeywords.ENABLED)))||((validateIdentifierKey(DroolsSoftKeywords.RULEFLOW) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.GROUP)))||((validateIdentifierKey(DroolsSoftKeywords.ATTRIBUTES)))||((validateIdentifierKey(DroolsSoftKeywords.AUTO) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.FOCUS)))))) {
                int LA34_1 = input.LA(2);

                if ( (LA34_1==COLON) && (((validateIdentifierKey(DroolsSoftKeywords.ATTRIBUTES))))) {
                    alt34=1;
                }
            }
            switch (alt34) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:704:6: attributes_key COLON
                    {
                    pushFollow(FOLLOW_attributes_key_in_rule_attributes1584);
                    attributes_key77=attributes_key();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_attributes_key.add(attributes_key77.getTree());
                    COLON78=(Token)match(input,COLON,FOLLOW_COLON_in_rule_attributes1586); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COLON.add(COLON78);

                    if ( state.backtracking==0 ) {
                      	emit(COLON78, DroolsEditorType.SYMBOL);	
                    }

                    }
                    break;

            }

            pushFollow(FOLLOW_rule_attribute_in_rule_attributes1596);
            rule_attribute79=rule_attribute();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_rule_attribute.add(rule_attribute79.getTree());
            // src/main/resources/org/drools/lang/DRL.g:705:18: ( ( COMMA )? attr= rule_attribute )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( (LA36_0==ID||LA36_0==COMMA) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // src/main/resources/org/drools/lang/DRL.g:705:20: ( COMMA )? attr= rule_attribute
            	    {
            	    // src/main/resources/org/drools/lang/DRL.g:705:20: ( COMMA )?
            	    int alt35=2;
            	    int LA35_0 = input.LA(1);

            	    if ( (LA35_0==COMMA) ) {
            	        alt35=1;
            	    }
            	    switch (alt35) {
            	        case 1 :
            	            // src/main/resources/org/drools/lang/DRL.g:705:20: COMMA
            	            {
            	            COMMA80=(Token)match(input,COMMA,FOLLOW_COMMA_in_rule_attributes1600); if (state.failed) return retval; 
            	            if ( state.backtracking==0 ) stream_COMMA.add(COMMA80);


            	            }
            	            break;

            	    }

            	    if ( state.backtracking==0 ) {
            	      	emit(COMMA80, DroolsEditorType.SYMBOL);	
            	    }
            	    pushFollow(FOLLOW_rule_attribute_in_rule_attributes1607);
            	    attr=rule_attribute();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_rule_attribute.add(attr.getTree());

            	    }
            	    break;

            	default :
            	    break loop36;
                }
            } while (true);



            // AST REWRITE
            // elements: attributes_key, rule_attribute
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 706:3: -> ^( VT_RULE_ATTRIBUTES ( attributes_key )? ( rule_attribute )+ )
            {
                // src/main/resources/org/drools/lang/DRL.g:706:6: ^( VT_RULE_ATTRIBUTES ( attributes_key )? ( rule_attribute )+ )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(VT_RULE_ATTRIBUTES, "VT_RULE_ATTRIBUTES"), root_1);

                // src/main/resources/org/drools/lang/DRL.g:706:27: ( attributes_key )?
                if ( stream_attributes_key.hasNext() ) {
                    adaptor.addChild(root_1, stream_attributes_key.nextTree());

                }
                stream_attributes_key.reset();
                if ( !(stream_rule_attribute.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_rule_attribute.hasNext() ) {
                    adaptor.addChild(root_1, stream_rule_attribute.nextTree());

                }
                stream_rule_attribute.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rule_attributes"

    public static class rule_attribute_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rule_attribute"
    // src/main/resources/org/drools/lang/DRL.g:709:1: rule_attribute : ( salience | no_loop | agenda_group | duration | activation_group | auto_focus | date_effective | date_expires | enabled | ruleflow_group | lock_on_active | dialect );
    public final DRLParser.rule_attribute_return rule_attribute() throws RecognitionException {
        DRLParser.rule_attribute_return retval = new DRLParser.rule_attribute_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        DRLParser.salience_return salience81 = null;

        DRLParser.no_loop_return no_loop82 = null;

        DRLParser.agenda_group_return agenda_group83 = null;

        DRLParser.duration_return duration84 = null;

        DRLParser.activation_group_return activation_group85 = null;

        DRLParser.auto_focus_return auto_focus86 = null;

        DRLParser.date_effective_return date_effective87 = null;

        DRLParser.date_expires_return date_expires88 = null;

        DRLParser.enabled_return enabled89 = null;

        DRLParser.ruleflow_group_return ruleflow_group90 = null;

        DRLParser.lock_on_active_return lock_on_active91 = null;

        DRLParser.dialect_return dialect92 = null;



         boolean isFailed = true; pushParaphrases(DroolsParaphraseTypes.RULE_ATTRIBUTE); 
        try {
            // src/main/resources/org/drools/lang/DRL.g:712:2: ( salience | no_loop | agenda_group | duration | activation_group | auto_focus | date_effective | date_expires | enabled | ruleflow_group | lock_on_active | dialect )
            int alt37=12;
            alt37 = dfa37.predict(input);
            switch (alt37) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:712:4: salience
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_salience_in_rule_attribute1646);
                    salience81=salience();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, salience81.getTree());

                    }
                    break;
                case 2 :
                    // src/main/resources/org/drools/lang/DRL.g:713:4: no_loop
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_no_loop_in_rule_attribute1652);
                    no_loop82=no_loop();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, no_loop82.getTree());

                    }
                    break;
                case 3 :
                    // src/main/resources/org/drools/lang/DRL.g:714:4: agenda_group
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_agenda_group_in_rule_attribute1657);
                    agenda_group83=agenda_group();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, agenda_group83.getTree());

                    }
                    break;
                case 4 :
                    // src/main/resources/org/drools/lang/DRL.g:715:4: duration
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_duration_in_rule_attribute1664);
                    duration84=duration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, duration84.getTree());

                    }
                    break;
                case 5 :
                    // src/main/resources/org/drools/lang/DRL.g:716:4: activation_group
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_activation_group_in_rule_attribute1671);
                    activation_group85=activation_group();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, activation_group85.getTree());

                    }
                    break;
                case 6 :
                    // src/main/resources/org/drools/lang/DRL.g:717:4: auto_focus
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_auto_focus_in_rule_attribute1677);
                    auto_focus86=auto_focus();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, auto_focus86.getTree());

                    }
                    break;
                case 7 :
                    // src/main/resources/org/drools/lang/DRL.g:718:4: date_effective
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_date_effective_in_rule_attribute1683);
                    date_effective87=date_effective();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, date_effective87.getTree());

                    }
                    break;
                case 8 :
                    // src/main/resources/org/drools/lang/DRL.g:719:4: date_expires
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_date_expires_in_rule_attribute1689);
                    date_expires88=date_expires();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, date_expires88.getTree());

                    }
                    break;
                case 9 :
                    // src/main/resources/org/drools/lang/DRL.g:720:4: enabled
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_enabled_in_rule_attribute1695);
                    enabled89=enabled();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, enabled89.getTree());

                    }
                    break;
                case 10 :
                    // src/main/resources/org/drools/lang/DRL.g:721:4: ruleflow_group
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_ruleflow_group_in_rule_attribute1701);
                    ruleflow_group90=ruleflow_group();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ruleflow_group90.getTree());

                    }
                    break;
                case 11 :
                    // src/main/resources/org/drools/lang/DRL.g:722:4: lock_on_active
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_lock_on_active_in_rule_attribute1707);
                    lock_on_active91=lock_on_active();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, lock_on_active91.getTree());

                    }
                    break;
                case 12 :
                    // src/main/resources/org/drools/lang/DRL.g:723:4: dialect
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_dialect_in_rule_attribute1712);
                    dialect92=dialect();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, dialect92.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
               paraphrases.pop(); isFailed = false; if (!(retval.tree instanceof CommonErrorNode)) emit(Location.LOCATION_RULE_HEADER); 
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {

            	if (isEditorInterfaceEnabled && isFailed) {
            		if (input.LA(2) == EOF && input.LA(1) == ID){
            			emit(input.LT(1), DroolsEditorType.IDENTIFIER);
            			input.consume();
            		}
            	}

        }
        return retval;
    }
    // $ANTLR end "rule_attribute"

    public static class date_effective_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "date_effective"
    // src/main/resources/org/drools/lang/DRL.g:733:1: date_effective : date_effective_key STRING ;
    public final DRLParser.date_effective_return date_effective() throws RecognitionException {
        DRLParser.date_effective_return retval = new DRLParser.date_effective_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token STRING94=null;
        DRLParser.date_effective_key_return date_effective_key93 = null;


        Object STRING94_tree=null;

        try {
            // src/main/resources/org/drools/lang/DRL.g:734:2: ( date_effective_key STRING )
            // src/main/resources/org/drools/lang/DRL.g:734:4: date_effective_key STRING
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_date_effective_key_in_date_effective1727);
            date_effective_key93=date_effective_key();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(date_effective_key93.getTree(), root_0);
            if ( state.backtracking==0 ) {
              	emit(Location.LOCATION_RULE_HEADER_KEYWORD);	
            }
            STRING94=(Token)match(input,STRING,FOLLOW_STRING_in_date_effective1732); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            STRING94_tree = (Object)adaptor.create(STRING94);
            adaptor.addChild(root_0, STRING94_tree);
            }
            if ( state.backtracking==0 ) {
              	emit(STRING94, DroolsEditorType.STRING_CONST );	
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "date_effective"

    public static class date_expires_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "date_expires"
    // src/main/resources/org/drools/lang/DRL.g:738:1: date_expires : date_expires_key STRING ;
    public final DRLParser.date_expires_return date_expires() throws RecognitionException {
        DRLParser.date_expires_return retval = new DRLParser.date_expires_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token STRING96=null;
        DRLParser.date_expires_key_return date_expires_key95 = null;


        Object STRING96_tree=null;

        try {
            // src/main/resources/org/drools/lang/DRL.g:739:2: ( date_expires_key STRING )
            // src/main/resources/org/drools/lang/DRL.g:739:4: date_expires_key STRING
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_date_expires_key_in_date_expires1746);
            date_expires_key95=date_expires_key();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(date_expires_key95.getTree(), root_0);
            if ( state.backtracking==0 ) {
              	emit(Location.LOCATION_RULE_HEADER_KEYWORD);	
            }
            STRING96=(Token)match(input,STRING,FOLLOW_STRING_in_date_expires1751); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            STRING96_tree = (Object)adaptor.create(STRING96);
            adaptor.addChild(root_0, STRING96_tree);
            }
            if ( state.backtracking==0 ) {
              	emit(STRING96, DroolsEditorType.STRING_CONST );	
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "date_expires"

    public static class enabled_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "enabled"
    // src/main/resources/org/drools/lang/DRL.g:743:1: enabled : enabled_key ( BOOL | paren_chunk ) ;
    public final DRLParser.enabled_return enabled() throws RecognitionException {
        DRLParser.enabled_return retval = new DRLParser.enabled_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token BOOL98=null;
        DRLParser.enabled_key_return enabled_key97 = null;

        DRLParser.paren_chunk_return paren_chunk99 = null;


        Object BOOL98_tree=null;

        try {
            // src/main/resources/org/drools/lang/DRL.g:744:2: ( enabled_key ( BOOL | paren_chunk ) )
            // src/main/resources/org/drools/lang/DRL.g:744:4: enabled_key ( BOOL | paren_chunk )
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_enabled_key_in_enabled1766);
            enabled_key97=enabled_key();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(enabled_key97.getTree(), root_0);
            if ( state.backtracking==0 ) {
              	emit(Location.LOCATION_RULE_HEADER_KEYWORD);	
            }
            // src/main/resources/org/drools/lang/DRL.g:745:6: ( BOOL | paren_chunk )
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==BOOL) ) {
                alt38=1;
            }
            else if ( (LA38_0==LEFT_PAREN) ) {
                alt38=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;
            }
            switch (alt38) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:745:8: BOOL
                    {
                    BOOL98=(Token)match(input,BOOL,FOLLOW_BOOL_in_enabled1779); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    BOOL98_tree = (Object)adaptor.create(BOOL98);
                    adaptor.addChild(root_0, BOOL98_tree);
                    }
                    if ( state.backtracking==0 ) {
                      	emit(BOOL98, DroolsEditorType.BOOLEAN_CONST );	
                    }

                    }
                    break;
                case 2 :
                    // src/main/resources/org/drools/lang/DRL.g:746:8: paren_chunk
                    {
                    pushFollow(FOLLOW_paren_chunk_in_enabled1790);
                    paren_chunk99=paren_chunk();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, paren_chunk99.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "enabled"

    public static class salience_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "salience"
    // src/main/resources/org/drools/lang/DRL.g:750:1: salience : salience_key ( INT | paren_chunk ) ;
    public final DRLParser.salience_return salience() throws RecognitionException {
        DRLParser.salience_return retval = new DRLParser.salience_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token INT101=null;
        DRLParser.salience_key_return salience_key100 = null;

        DRLParser.paren_chunk_return paren_chunk102 = null;


        Object INT101_tree=null;

        try {
            // src/main/resources/org/drools/lang/DRL.g:751:2: ( salience_key ( INT | paren_chunk ) )
            // src/main/resources/org/drools/lang/DRL.g:751:4: salience_key ( INT | paren_chunk )
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_salience_key_in_salience1810);
            salience_key100=salience_key();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(salience_key100.getTree(), root_0);
            if ( state.backtracking==0 ) {
              	emit(Location.LOCATION_RULE_HEADER_KEYWORD);	
            }
            // src/main/resources/org/drools/lang/DRL.g:752:3: ( INT | paren_chunk )
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==INT) ) {
                alt39=1;
            }
            else if ( (LA39_0==LEFT_PAREN) ) {
                alt39=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 39, 0, input);

                throw nvae;
            }
            switch (alt39) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:752:5: INT
                    {
                    INT101=(Token)match(input,INT,FOLLOW_INT_in_salience1819); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    INT101_tree = (Object)adaptor.create(INT101);
                    adaptor.addChild(root_0, INT101_tree);
                    }
                    if ( state.backtracking==0 ) {
                      	emit(INT101, DroolsEditorType.NUMERIC_CONST );	
                    }

                    }
                    break;
                case 2 :
                    // src/main/resources/org/drools/lang/DRL.g:753:5: paren_chunk
                    {
                    pushFollow(FOLLOW_paren_chunk_in_salience1828);
                    paren_chunk102=paren_chunk();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, paren_chunk102.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "salience"

    public static class no_loop_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "no_loop"
    // src/main/resources/org/drools/lang/DRL.g:757:1: no_loop : no_loop_key ( BOOL )? ;
    public final DRLParser.no_loop_return no_loop() throws RecognitionException {
        DRLParser.no_loop_return retval = new DRLParser.no_loop_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token BOOL104=null;
        DRLParser.no_loop_key_return no_loop_key103 = null;


        Object BOOL104_tree=null;

        try {
            // src/main/resources/org/drools/lang/DRL.g:758:2: ( no_loop_key ( BOOL )? )
            // src/main/resources/org/drools/lang/DRL.g:758:4: no_loop_key ( BOOL )?
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_no_loop_key_in_no_loop1843);
            no_loop_key103=no_loop_key();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(no_loop_key103.getTree(), root_0);
            if ( state.backtracking==0 ) {
              	emit(Location.LOCATION_RULE_HEADER_KEYWORD);	
            }
            // src/main/resources/org/drools/lang/DRL.g:758:66: ( BOOL )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==BOOL) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:758:66: BOOL
                    {
                    BOOL104=(Token)match(input,BOOL,FOLLOW_BOOL_in_no_loop1848); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    BOOL104_tree = (Object)adaptor.create(BOOL104);
                    adaptor.addChild(root_0, BOOL104_tree);
                    }

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              	emit(BOOL104, DroolsEditorType.BOOLEAN_CONST );	
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "no_loop"

    public static class auto_focus_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "auto_focus"
    // src/main/resources/org/drools/lang/DRL.g:762:1: auto_focus : auto_focus_key ( BOOL )? ;
    public final DRLParser.auto_focus_return auto_focus() throws RecognitionException {
        DRLParser.auto_focus_return retval = new DRLParser.auto_focus_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token BOOL106=null;
        DRLParser.auto_focus_key_return auto_focus_key105 = null;


        Object BOOL106_tree=null;

        try {
            // src/main/resources/org/drools/lang/DRL.g:763:2: ( auto_focus_key ( BOOL )? )
            // src/main/resources/org/drools/lang/DRL.g:763:4: auto_focus_key ( BOOL )?
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_auto_focus_key_in_auto_focus1863);
            auto_focus_key105=auto_focus_key();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(auto_focus_key105.getTree(), root_0);
            if ( state.backtracking==0 ) {
              	emit(Location.LOCATION_RULE_HEADER_KEYWORD);	
            }
            // src/main/resources/org/drools/lang/DRL.g:763:69: ( BOOL )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==BOOL) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:763:69: BOOL
                    {
                    BOOL106=(Token)match(input,BOOL,FOLLOW_BOOL_in_auto_focus1868); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    BOOL106_tree = (Object)adaptor.create(BOOL106);
                    adaptor.addChild(root_0, BOOL106_tree);
                    }

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              	emit(BOOL106, DroolsEditorType.BOOLEAN_CONST );	
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "auto_focus"

    public static class activation_group_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "activation_group"
    // src/main/resources/org/drools/lang/DRL.g:767:1: activation_group : activation_group_key STRING ;
    public final DRLParser.activation_group_return activation_group() throws RecognitionException {
        DRLParser.activation_group_return retval = new DRLParser.activation_group_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token STRING108=null;
        DRLParser.activation_group_key_return activation_group_key107 = null;


        Object STRING108_tree=null;

        try {
            // src/main/resources/org/drools/lang/DRL.g:768:2: ( activation_group_key STRING )
            // src/main/resources/org/drools/lang/DRL.g:768:4: activation_group_key STRING
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_activation_group_key_in_activation_group1885);
            activation_group_key107=activation_group_key();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(activation_group_key107.getTree(), root_0);
            if ( state.backtracking==0 ) {
              	emit(Location.LOCATION_RULE_HEADER_KEYWORD);	
            }
            STRING108=(Token)match(input,STRING,FOLLOW_STRING_in_activation_group1890); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            STRING108_tree = (Object)adaptor.create(STRING108);
            adaptor.addChild(root_0, STRING108_tree);
            }
            if ( state.backtracking==0 ) {
              	emit(STRING108, DroolsEditorType.STRING_CONST );	
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "activation_group"

    public static class ruleflow_group_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ruleflow_group"
    // src/main/resources/org/drools/lang/DRL.g:772:1: ruleflow_group : ruleflow_group_key STRING ;
    public final DRLParser.ruleflow_group_return ruleflow_group() throws RecognitionException {
        DRLParser.ruleflow_group_return retval = new DRLParser.ruleflow_group_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token STRING110=null;
        DRLParser.ruleflow_group_key_return ruleflow_group_key109 = null;


        Object STRING110_tree=null;

        try {
            // src/main/resources/org/drools/lang/DRL.g:773:2: ( ruleflow_group_key STRING )
            // src/main/resources/org/drools/lang/DRL.g:773:4: ruleflow_group_key STRING
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_ruleflow_group_key_in_ruleflow_group1904);
            ruleflow_group_key109=ruleflow_group_key();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(ruleflow_group_key109.getTree(), root_0);
            if ( state.backtracking==0 ) {
              	emit(Location.LOCATION_RULE_HEADER_KEYWORD);	
            }
            STRING110=(Token)match(input,STRING,FOLLOW_STRING_in_ruleflow_group1909); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            STRING110_tree = (Object)adaptor.create(STRING110);
            adaptor.addChild(root_0, STRING110_tree);
            }
            if ( state.backtracking==0 ) {
              	emit(STRING110, DroolsEditorType.STRING_CONST );	
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "ruleflow_group"

    public static class agenda_group_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "agenda_group"
    // src/main/resources/org/drools/lang/DRL.g:777:1: agenda_group : agenda_group_key STRING ;
    public final DRLParser.agenda_group_return agenda_group() throws RecognitionException {
        DRLParser.agenda_group_return retval = new DRLParser.agenda_group_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token STRING112=null;
        DRLParser.agenda_group_key_return agenda_group_key111 = null;


        Object STRING112_tree=null;

        try {
            // src/main/resources/org/drools/lang/DRL.g:778:2: ( agenda_group_key STRING )
            // src/main/resources/org/drools/lang/DRL.g:778:4: agenda_group_key STRING
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_agenda_group_key_in_agenda_group1923);
            agenda_group_key111=agenda_group_key();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(agenda_group_key111.getTree(), root_0);
            if ( state.backtracking==0 ) {
              	emit(Location.LOCATION_RULE_HEADER_KEYWORD);	
            }
            STRING112=(Token)match(input,STRING,FOLLOW_STRING_in_agenda_group1928); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            STRING112_tree = (Object)adaptor.create(STRING112);
            adaptor.addChild(root_0, STRING112_tree);
            }
            if ( state.backtracking==0 ) {
              	emit(STRING112, DroolsEditorType.STRING_CONST );	
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "agenda_group"

    public static class duration_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "duration"
    // src/main/resources/org/drools/lang/DRL.g:782:1: duration : duration_key ( INT | paren_chunk ) ;
    public final DRLParser.duration_return duration() throws RecognitionException {
        DRLParser.duration_return retval = new DRLParser.duration_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token INT114=null;
        DRLParser.duration_key_return duration_key113 = null;

        DRLParser.paren_chunk_return paren_chunk115 = null;


        Object INT114_tree=null;

        try {
            // src/main/resources/org/drools/lang/DRL.g:783:2: ( duration_key ( INT | paren_chunk ) )
            // src/main/resources/org/drools/lang/DRL.g:783:4: duration_key ( INT | paren_chunk )
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_duration_key_in_duration1942);
            duration_key113=duration_key();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(duration_key113.getTree(), root_0);
            if ( state.backtracking==0 ) {
              	emit(Location.LOCATION_RULE_HEADER_KEYWORD);	
            }
            // src/main/resources/org/drools/lang/DRL.g:784:6: ( INT | paren_chunk )
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==INT) ) {
                alt42=1;
            }
            else if ( (LA42_0==LEFT_PAREN) ) {
                alt42=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 42, 0, input);

                throw nvae;
            }
            switch (alt42) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:784:8: INT
                    {
                    INT114=(Token)match(input,INT,FOLLOW_INT_in_duration1955); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    INT114_tree = (Object)adaptor.create(INT114);
                    adaptor.addChild(root_0, INT114_tree);
                    }
                    if ( state.backtracking==0 ) {
                      	emit(INT114, DroolsEditorType.NUMERIC_CONST );	
                    }

                    }
                    break;
                case 2 :
                    // src/main/resources/org/drools/lang/DRL.g:785:8: paren_chunk
                    {
                    pushFollow(FOLLOW_paren_chunk_in_duration1966);
                    paren_chunk115=paren_chunk();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, paren_chunk115.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "duration"

    public static class dialect_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "dialect"
    // src/main/resources/org/drools/lang/DRL.g:789:1: dialect : dialect_key STRING ;
    public final DRLParser.dialect_return dialect() throws RecognitionException {
        DRLParser.dialect_return retval = new DRLParser.dialect_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token STRING117=null;
        DRLParser.dialect_key_return dialect_key116 = null;


        Object STRING117_tree=null;

        try {
            // src/main/resources/org/drools/lang/DRL.g:790:2: ( dialect_key STRING )
            // src/main/resources/org/drools/lang/DRL.g:790:4: dialect_key STRING
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_dialect_key_in_dialect1986);
            dialect_key116=dialect_key();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(dialect_key116.getTree(), root_0);
            if ( state.backtracking==0 ) {
              	emit(Location.LOCATION_RULE_HEADER_KEYWORD);	
            }
            STRING117=(Token)match(input,STRING,FOLLOW_STRING_in_dialect1991); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            STRING117_tree = (Object)adaptor.create(STRING117);
            adaptor.addChild(root_0, STRING117_tree);
            }
            if ( state.backtracking==0 ) {
              	emit(STRING117, DroolsEditorType.STRING_CONST );	
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "dialect"

    public static class lock_on_active_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "lock_on_active"
    // src/main/resources/org/drools/lang/DRL.g:794:1: lock_on_active : lock_on_active_key ( BOOL )? ;
    public final DRLParser.lock_on_active_return lock_on_active() throws RecognitionException {
        DRLParser.lock_on_active_return retval = new DRLParser.lock_on_active_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token BOOL119=null;
        DRLParser.lock_on_active_key_return lock_on_active_key118 = null;


        Object BOOL119_tree=null;

        try {
            // src/main/resources/org/drools/lang/DRL.g:795:2: ( lock_on_active_key ( BOOL )? )
            // src/main/resources/org/drools/lang/DRL.g:795:4: lock_on_active_key ( BOOL )?
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_lock_on_active_key_in_lock_on_active2009);
            lock_on_active_key118=lock_on_active_key();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(lock_on_active_key118.getTree(), root_0);
            if ( state.backtracking==0 ) {
              	emit(Location.LOCATION_RULE_HEADER_KEYWORD);	
            }
            // src/main/resources/org/drools/lang/DRL.g:795:73: ( BOOL )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==BOOL) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:795:73: BOOL
                    {
                    BOOL119=(Token)match(input,BOOL,FOLLOW_BOOL_in_lock_on_active2014); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    BOOL119_tree = (Object)adaptor.create(BOOL119);
                    adaptor.addChild(root_0, BOOL119_tree);
                    }

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              	emit(BOOL119, DroolsEditorType.BOOLEAN_CONST );	
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "lock_on_active"

    public static class normal_lhs_block_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "normal_lhs_block"
    // src/main/resources/org/drools/lang/DRL.g:799:1: normal_lhs_block : ( lhs )* -> ^( VT_AND_IMPLICIT ( lhs )* ) ;
    public final DRLParser.normal_lhs_block_return normal_lhs_block() throws RecognitionException {
        DRLParser.normal_lhs_block_return retval = new DRLParser.normal_lhs_block_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        DRLParser.lhs_return lhs120 = null;


        RewriteRuleSubtreeStream stream_lhs=new RewriteRuleSubtreeStream(adaptor,"rule lhs");
        try {
            // src/main/resources/org/drools/lang/DRL.g:800:2: ( ( lhs )* -> ^( VT_AND_IMPLICIT ( lhs )* ) )
            // src/main/resources/org/drools/lang/DRL.g:800:4: ( lhs )*
            {
            // src/main/resources/org/drools/lang/DRL.g:800:4: ( lhs )*
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( (LA44_0==ID) ) {
                    int LA44_1 = input.LA(2);

                    if ( (!((((validateIdentifierKey(DroolsSoftKeywords.END)))))) ) {
                        alt44=1;
                    }


                }
                else if ( (LA44_0==LEFT_PAREN) ) {
                    alt44=1;
                }


                switch (alt44) {
            	case 1 :
            	    // src/main/resources/org/drools/lang/DRL.g:800:4: lhs
            	    {
            	    pushFollow(FOLLOW_lhs_in_normal_lhs_block2029);
            	    lhs120=lhs();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_lhs.add(lhs120.getTree());

            	    }
            	    break;

            	default :
            	    break loop44;
                }
            } while (true);



            // AST REWRITE
            // elements: lhs
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 801:2: -> ^( VT_AND_IMPLICIT ( lhs )* )
            {
                // src/main/resources/org/drools/lang/DRL.g:801:5: ^( VT_AND_IMPLICIT ( lhs )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(VT_AND_IMPLICIT, "VT_AND_IMPLICIT"), root_1);

                // src/main/resources/org/drools/lang/DRL.g:801:23: ( lhs )*
                while ( stream_lhs.hasNext() ) {
                    adaptor.addChild(root_1, stream_lhs.nextTree());

                }
                stream_lhs.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "normal_lhs_block"

    public static class lhs_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "lhs"
    // src/main/resources/org/drools/lang/DRL.g:804:1: lhs : lhs_or ;
    public final DRLParser.lhs_return lhs() throws RecognitionException {
        DRLParser.lhs_return retval = new DRLParser.lhs_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        DRLParser.lhs_or_return lhs_or121 = null;



        try {
            // src/main/resources/org/drools/lang/DRL.g:804:5: ( lhs_or )
            // src/main/resources/org/drools/lang/DRL.g:804:7: lhs_or
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_lhs_or_in_lhs2050);
            lhs_or121=lhs_or();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, lhs_or121.getTree());

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "lhs"

    public static class lhs_or_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "lhs_or"
    // src/main/resources/org/drools/lang/DRL.g:807:1: lhs_or : ( ( LEFT_PAREN or_key )=> LEFT_PAREN or= or_key ( lhs_and )+ RIGHT_PAREN -> ^( VT_OR_PREFIX[$or.start] ( lhs_and )+ RIGHT_PAREN ) | ( lhs_and -> lhs_and ) ( ( or_key | DOUBLE_PIPE )=> (value= or_key | pipe= DOUBLE_PIPE ) lhs_and -> ^( VT_OR_INFIX[orToken] $lhs_or lhs_and ) )* );
    public final DRLParser.lhs_or_return lhs_or() throws RecognitionException {
        DRLParser.lhs_or_return retval = new DRLParser.lhs_or_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token pipe=null;
        Token LEFT_PAREN122=null;
        Token RIGHT_PAREN124=null;
        DRLParser.or_key_return or = null;

        DRLParser.or_key_return value = null;

        DRLParser.lhs_and_return lhs_and123 = null;

        DRLParser.lhs_and_return lhs_and125 = null;

        DRLParser.lhs_and_return lhs_and126 = null;


        Object pipe_tree=null;
        Object LEFT_PAREN122_tree=null;
        Object RIGHT_PAREN124_tree=null;
        RewriteRuleTokenStream stream_DOUBLE_PIPE=new RewriteRuleTokenStream(adaptor,"token DOUBLE_PIPE");
        RewriteRuleTokenStream stream_LEFT_PAREN=new RewriteRuleTokenStream(adaptor,"token LEFT_PAREN");
        RewriteRuleTokenStream stream_RIGHT_PAREN=new RewriteRuleTokenStream(adaptor,"token RIGHT_PAREN");
        RewriteRuleSubtreeStream stream_or_key=new RewriteRuleSubtreeStream(adaptor,"rule or_key");
        RewriteRuleSubtreeStream stream_lhs_and=new RewriteRuleSubtreeStream(adaptor,"rule lhs_and");

        	Token orToken = null;

        try {
            // src/main/resources/org/drools/lang/DRL.g:810:3: ( ( LEFT_PAREN or_key )=> LEFT_PAREN or= or_key ( lhs_and )+ RIGHT_PAREN -> ^( VT_OR_PREFIX[$or.start] ( lhs_and )+ RIGHT_PAREN ) | ( lhs_and -> lhs_and ) ( ( or_key | DOUBLE_PIPE )=> (value= or_key | pipe= DOUBLE_PIPE ) lhs_and -> ^( VT_OR_INFIX[orToken] $lhs_or lhs_and ) )* )
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==LEFT_PAREN) ) {
                int LA48_1 = input.LA(2);

                if ( (synpred1_DRL()) ) {
                    alt48=1;
                }
                else if ( (true) ) {
                    alt48=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 48, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA48_0==ID) ) {
                alt48=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 48, 0, input);

                throw nvae;
            }
            switch (alt48) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:810:5: ( LEFT_PAREN or_key )=> LEFT_PAREN or= or_key ( lhs_and )+ RIGHT_PAREN
                    {
                    LEFT_PAREN122=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_lhs_or2074); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LEFT_PAREN.add(LEFT_PAREN122);

                    if ( state.backtracking==0 ) {
                      	emit(LEFT_PAREN122, DroolsEditorType.SYMBOL);	
                    }
                    pushFollow(FOLLOW_or_key_in_lhs_or2084);
                    or=or_key();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_or_key.add(or.getTree());
                    if ( state.backtracking==0 ) {
                      	emit(Location.LOCATION_LHS_BEGIN_OF_CONDITION_AND_OR);	
                    }
                    // src/main/resources/org/drools/lang/DRL.g:814:4: ( lhs_and )+
                    int cnt45=0;
                    loop45:
                    do {
                        int alt45=2;
                        int LA45_0 = input.LA(1);

                        if ( (LA45_0==ID||LA45_0==LEFT_PAREN) ) {
                            alt45=1;
                        }


                        switch (alt45) {
                    	case 1 :
                    	    // src/main/resources/org/drools/lang/DRL.g:814:4: lhs_and
                    	    {
                    	    pushFollow(FOLLOW_lhs_and_in_lhs_or2092);
                    	    lhs_and123=lhs_and();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_lhs_and.add(lhs_and123.getTree());

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt45 >= 1 ) break loop45;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(45, input);
                                throw eee;
                        }
                        cnt45++;
                    } while (true);

                    RIGHT_PAREN124=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_lhs_or2098); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RIGHT_PAREN.add(RIGHT_PAREN124);

                    if ( state.backtracking==0 ) {
                      	emit(RIGHT_PAREN124, DroolsEditorType.SYMBOL);	
                    }


                    // AST REWRITE
                    // elements: RIGHT_PAREN, lhs_and
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 816:3: -> ^( VT_OR_PREFIX[$or.start] ( lhs_and )+ RIGHT_PAREN )
                    {
                        // src/main/resources/org/drools/lang/DRL.g:816:6: ^( VT_OR_PREFIX[$or.start] ( lhs_and )+ RIGHT_PAREN )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(VT_OR_PREFIX, (or!=null?((Token)or.start):null)), root_1);

                        if ( !(stream_lhs_and.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_lhs_and.hasNext() ) {
                            adaptor.addChild(root_1, stream_lhs_and.nextTree());

                        }
                        stream_lhs_and.reset();
                        adaptor.addChild(root_1, stream_RIGHT_PAREN.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // src/main/resources/org/drools/lang/DRL.g:817:4: ( lhs_and -> lhs_and ) ( ( or_key | DOUBLE_PIPE )=> (value= or_key | pipe= DOUBLE_PIPE ) lhs_and -> ^( VT_OR_INFIX[orToken] $lhs_or lhs_and ) )*
                    {
                    // src/main/resources/org/drools/lang/DRL.g:817:4: ( lhs_and -> lhs_and )
                    // src/main/resources/org/drools/lang/DRL.g:817:5: lhs_and
                    {
                    pushFollow(FOLLOW_lhs_and_in_lhs_or2121);
                    lhs_and125=lhs_and();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_lhs_and.add(lhs_and125.getTree());


                    // AST REWRITE
                    // elements: lhs_and
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 817:13: -> lhs_and
                    {
                        adaptor.addChild(root_0, stream_lhs_and.nextTree());

                    }

                    retval.tree = root_0;}
                    }

                    // src/main/resources/org/drools/lang/DRL.g:818:3: ( ( or_key | DOUBLE_PIPE )=> (value= or_key | pipe= DOUBLE_PIPE ) lhs_and -> ^( VT_OR_INFIX[orToken] $lhs_or lhs_and ) )*
                    loop47:
                    do {
                        int alt47=2;
                        int LA47_0 = input.LA(1);

                        if ( (LA47_0==ID) ) {
                            int LA47_1 = input.LA(2);

                            if ( ((synpred2_DRL()&&((validateIdentifierKey(DroolsSoftKeywords.OR))))) ) {
                                alt47=1;
                            }


                        }
                        else if ( (LA47_0==DOUBLE_PIPE) ) {
                            int LA47_3 = input.LA(2);

                            if ( (synpred2_DRL()) ) {
                                alt47=1;
                            }


                        }


                        switch (alt47) {
                    	case 1 :
                    	    // src/main/resources/org/drools/lang/DRL.g:818:5: ( or_key | DOUBLE_PIPE )=> (value= or_key | pipe= DOUBLE_PIPE ) lhs_and
                    	    {
                    	    // src/main/resources/org/drools/lang/DRL.g:818:28: (value= or_key | pipe= DOUBLE_PIPE )
                    	    int alt46=2;
                    	    int LA46_0 = input.LA(1);

                    	    if ( (LA46_0==ID) && (((validateIdentifierKey(DroolsSoftKeywords.OR))))) {
                    	        alt46=1;
                    	    }
                    	    else if ( (LA46_0==DOUBLE_PIPE) ) {
                    	        alt46=2;
                    	    }
                    	    else {
                    	        if (state.backtracking>0) {state.failed=true; return retval;}
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("", 46, 0, input);

                    	        throw nvae;
                    	    }
                    	    switch (alt46) {
                    	        case 1 :
                    	            // src/main/resources/org/drools/lang/DRL.g:818:29: value= or_key
                    	            {
                    	            pushFollow(FOLLOW_or_key_in_lhs_or2143);
                    	            value=or_key();

                    	            state._fsp--;
                    	            if (state.failed) return retval;
                    	            if ( state.backtracking==0 ) stream_or_key.add(value.getTree());
                    	            if ( state.backtracking==0 ) {
                    	              orToken = (value!=null?((Token)value.start):null);
                    	            }

                    	            }
                    	            break;
                    	        case 2 :
                    	            // src/main/resources/org/drools/lang/DRL.g:818:69: pipe= DOUBLE_PIPE
                    	            {
                    	            pipe=(Token)match(input,DOUBLE_PIPE,FOLLOW_DOUBLE_PIPE_in_lhs_or2150); if (state.failed) return retval; 
                    	            if ( state.backtracking==0 ) stream_DOUBLE_PIPE.add(pipe);

                    	            if ( state.backtracking==0 ) {
                    	              orToken = pipe; emit(pipe, DroolsEditorType.SYMBOL);
                    	            }

                    	            }
                    	            break;

                    	    }

                    	    if ( state.backtracking==0 ) {
                    	      	emit(Location.LOCATION_LHS_BEGIN_OF_CONDITION_AND_OR);	
                    	    }
                    	    pushFollow(FOLLOW_lhs_and_in_lhs_or2161);
                    	    lhs_and126=lhs_and();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_lhs_and.add(lhs_and126.getTree());


                    	    // AST REWRITE
                    	    // elements: lhs_and, lhs_or
                    	    // token labels: 
                    	    // rule labels: retval
                    	    // token list labels: 
                    	    // rule list labels: 
                    	    if ( state.backtracking==0 ) {
                    	    retval.tree = root_0;
                    	    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    	    root_0 = (Object)adaptor.nil();
                    	    // 821:3: -> ^( VT_OR_INFIX[orToken] $lhs_or lhs_and )
                    	    {
                    	        // src/main/resources/org/drools/lang/DRL.g:821:6: ^( VT_OR_INFIX[orToken] $lhs_or lhs_and )
                    	        {
                    	        Object root_1 = (Object)adaptor.nil();
                    	        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(VT_OR_INFIX, orToken), root_1);

                    	        adaptor.addChild(root_1, stream_retval.nextTree());
                    	        adaptor.addChild(root_1, stream_lhs_and.nextTree());

                    	        adaptor.addChild(root_0, root_1);
                    	        }

                    	    }

                    	    retval.tree = root_0;}
                    	    }
                    	    break;

                    	default :
                    	    break loop47;
                        }
                    } while (true);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "lhs_or"

    public static class lhs_and_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "lhs_and"
    // src/main/resources/org/drools/lang/DRL.g:824:1: lhs_and : ( ( LEFT_PAREN and_key )=> LEFT_PAREN and= and_key ( lhs_unary )+ RIGHT_PAREN -> ^( VT_AND_PREFIX[$and.start] ( lhs_unary )+ RIGHT_PAREN ) | ( lhs_unary -> lhs_unary ) ( ( and_key | DOUBLE_AMPER )=> (value= and_key | amper= DOUBLE_AMPER ) lhs_unary -> ^( VT_AND_INFIX[andToken] $lhs_and lhs_unary ) )* );
    public final DRLParser.lhs_and_return lhs_and() throws RecognitionException {
        DRLParser.lhs_and_return retval = new DRLParser.lhs_and_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token amper=null;
        Token LEFT_PAREN127=null;
        Token RIGHT_PAREN129=null;
        DRLParser.and_key_return and = null;

        DRLParser.and_key_return value = null;

        DRLParser.lhs_unary_return lhs_unary128 = null;

        DRLParser.lhs_unary_return lhs_unary130 = null;

        DRLParser.lhs_unary_return lhs_unary131 = null;


        Object amper_tree=null;
        Object LEFT_PAREN127_tree=null;
        Object RIGHT_PAREN129_tree=null;
        RewriteRuleTokenStream stream_DOUBLE_AMPER=new RewriteRuleTokenStream(adaptor,"token DOUBLE_AMPER");
        RewriteRuleTokenStream stream_LEFT_PAREN=new RewriteRuleTokenStream(adaptor,"token LEFT_PAREN");
        RewriteRuleTokenStream stream_RIGHT_PAREN=new RewriteRuleTokenStream(adaptor,"token RIGHT_PAREN");
        RewriteRuleSubtreeStream stream_and_key=new RewriteRuleSubtreeStream(adaptor,"rule and_key");
        RewriteRuleSubtreeStream stream_lhs_unary=new RewriteRuleSubtreeStream(adaptor,"rule lhs_unary");

        	Token andToken = null;

        try {
            // src/main/resources/org/drools/lang/DRL.g:827:3: ( ( LEFT_PAREN and_key )=> LEFT_PAREN and= and_key ( lhs_unary )+ RIGHT_PAREN -> ^( VT_AND_PREFIX[$and.start] ( lhs_unary )+ RIGHT_PAREN ) | ( lhs_unary -> lhs_unary ) ( ( and_key | DOUBLE_AMPER )=> (value= and_key | amper= DOUBLE_AMPER ) lhs_unary -> ^( VT_AND_INFIX[andToken] $lhs_and lhs_unary ) )* )
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==LEFT_PAREN) ) {
                int LA52_1 = input.LA(2);

                if ( (synpred3_DRL()) ) {
                    alt52=1;
                }
                else if ( (true) ) {
                    alt52=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 52, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA52_0==ID) ) {
                alt52=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 52, 0, input);

                throw nvae;
            }
            switch (alt52) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:827:5: ( LEFT_PAREN and_key )=> LEFT_PAREN and= and_key ( lhs_unary )+ RIGHT_PAREN
                    {
                    LEFT_PAREN127=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_lhs_and2202); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LEFT_PAREN.add(LEFT_PAREN127);

                    if ( state.backtracking==0 ) {
                      	emit(LEFT_PAREN127, DroolsEditorType.SYMBOL);	
                    }
                    pushFollow(FOLLOW_and_key_in_lhs_and2212);
                    and=and_key();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_and_key.add(and.getTree());
                    if ( state.backtracking==0 ) {
                      	emit(Location.LOCATION_LHS_BEGIN_OF_CONDITION_AND_OR);	
                    }
                    // src/main/resources/org/drools/lang/DRL.g:831:4: ( lhs_unary )+
                    int cnt49=0;
                    loop49:
                    do {
                        int alt49=2;
                        int LA49_0 = input.LA(1);

                        if ( (LA49_0==ID||LA49_0==LEFT_PAREN) ) {
                            alt49=1;
                        }


                        switch (alt49) {
                    	case 1 :
                    	    // src/main/resources/org/drools/lang/DRL.g:831:4: lhs_unary
                    	    {
                    	    pushFollow(FOLLOW_lhs_unary_in_lhs_and2220);
                    	    lhs_unary128=lhs_unary();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_lhs_unary.add(lhs_unary128.getTree());

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt49 >= 1 ) break loop49;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(49, input);
                                throw eee;
                        }
                        cnt49++;
                    } while (true);

                    RIGHT_PAREN129=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_lhs_and2226); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RIGHT_PAREN.add(RIGHT_PAREN129);

                    if ( state.backtracking==0 ) {
                      	emit(RIGHT_PAREN129, DroolsEditorType.SYMBOL);	
                    }


                    // AST REWRITE
                    // elements: RIGHT_PAREN, lhs_unary
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 833:3: -> ^( VT_AND_PREFIX[$and.start] ( lhs_unary )+ RIGHT_PAREN )
                    {
                        // src/main/resources/org/drools/lang/DRL.g:833:6: ^( VT_AND_PREFIX[$and.start] ( lhs_unary )+ RIGHT_PAREN )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(VT_AND_PREFIX, (and!=null?((Token)and.start):null)), root_1);

                        if ( !(stream_lhs_unary.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_lhs_unary.hasNext() ) {
                            adaptor.addChild(root_1, stream_lhs_unary.nextTree());

                        }
                        stream_lhs_unary.reset();
                        adaptor.addChild(root_1, stream_RIGHT_PAREN.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // src/main/resources/org/drools/lang/DRL.g:834:4: ( lhs_unary -> lhs_unary ) ( ( and_key | DOUBLE_AMPER )=> (value= and_key | amper= DOUBLE_AMPER ) lhs_unary -> ^( VT_AND_INFIX[andToken] $lhs_and lhs_unary ) )*
                    {
                    // src/main/resources/org/drools/lang/DRL.g:834:4: ( lhs_unary -> lhs_unary )
                    // src/main/resources/org/drools/lang/DRL.g:834:5: lhs_unary
                    {
                    pushFollow(FOLLOW_lhs_unary_in_lhs_and2250);
                    lhs_unary130=lhs_unary();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_lhs_unary.add(lhs_unary130.getTree());


                    // AST REWRITE
                    // elements: lhs_unary
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 834:15: -> lhs_unary
                    {
                        adaptor.addChild(root_0, stream_lhs_unary.nextTree());

                    }

                    retval.tree = root_0;}
                    }

                    // src/main/resources/org/drools/lang/DRL.g:835:3: ( ( and_key | DOUBLE_AMPER )=> (value= and_key | amper= DOUBLE_AMPER ) lhs_unary -> ^( VT_AND_INFIX[andToken] $lhs_and lhs_unary ) )*
                    loop51:
                    do {
                        int alt51=2;
                        int LA51_0 = input.LA(1);

                        if ( (LA51_0==ID) ) {
                            int LA51_2 = input.LA(2);

                            if ( ((synpred4_DRL()&&((validateIdentifierKey(DroolsSoftKeywords.AND))))) ) {
                                alt51=1;
                            }


                        }
                        else if ( (LA51_0==DOUBLE_AMPER) ) {
                            int LA51_3 = input.LA(2);

                            if ( (synpred4_DRL()) ) {
                                alt51=1;
                            }


                        }


                        switch (alt51) {
                    	case 1 :
                    	    // src/main/resources/org/drools/lang/DRL.g:835:5: ( and_key | DOUBLE_AMPER )=> (value= and_key | amper= DOUBLE_AMPER ) lhs_unary
                    	    {
                    	    // src/main/resources/org/drools/lang/DRL.g:835:30: (value= and_key | amper= DOUBLE_AMPER )
                    	    int alt50=2;
                    	    int LA50_0 = input.LA(1);

                    	    if ( (LA50_0==ID) && (((validateIdentifierKey(DroolsSoftKeywords.AND))))) {
                    	        alt50=1;
                    	    }
                    	    else if ( (LA50_0==DOUBLE_AMPER) ) {
                    	        alt50=2;
                    	    }
                    	    else {
                    	        if (state.backtracking>0) {state.failed=true; return retval;}
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("", 50, 0, input);

                    	        throw nvae;
                    	    }
                    	    switch (alt50) {
                    	        case 1 :
                    	            // src/main/resources/org/drools/lang/DRL.g:835:31: value= and_key
                    	            {
                    	            pushFollow(FOLLOW_and_key_in_lhs_and2272);
                    	            value=and_key();

                    	            state._fsp--;
                    	            if (state.failed) return retval;
                    	            if ( state.backtracking==0 ) stream_and_key.add(value.getTree());
                    	            if ( state.backtracking==0 ) {
                    	              andToken = (value!=null?((Token)value.start):null);
                    	            }

                    	            }
                    	            break;
                    	        case 2 :
                    	            // src/main/resources/org/drools/lang/DRL.g:835:73: amper= DOUBLE_AMPER
                    	            {
                    	            amper=(Token)match(input,DOUBLE_AMPER,FOLLOW_DOUBLE_AMPER_in_lhs_and2279); if (state.failed) return retval; 
                    	            if ( state.backtracking==0 ) stream_DOUBLE_AMPER.add(amper);

                    	            if ( state.backtracking==0 ) {
                    	              andToken = amper; emit(amper, DroolsEditorType.SYMBOL);
                    	            }

                    	            }
                    	            break;

                    	    }

                    	    if ( state.backtracking==0 ) {
                    	      	emit(Location.LOCATION_LHS_BEGIN_OF_CONDITION_AND_OR);	
                    	    }
                    	    pushFollow(FOLLOW_lhs_unary_in_lhs_and2290);
                    	    lhs_unary131=lhs_unary();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_lhs_unary.add(lhs_unary131.getTree());


                    	    // AST REWRITE
                    	    // elements: lhs_unary, lhs_and
                    	    // token labels: 
                    	    // rule labels: retval
                    	    // token list labels: 
                    	    // rule list labels: 
                    	    if ( state.backtracking==0 ) {
                    	    retval.tree = root_0;
                    	    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    	    root_0 = (Object)adaptor.nil();
                    	    // 838:3: -> ^( VT_AND_INFIX[andToken] $lhs_and lhs_unary )
                    	    {
                    	        // src/main/resources/org/drools/lang/DRL.g:838:6: ^( VT_AND_INFIX[andToken] $lhs_and lhs_unary )
                    	        {
                    	        Object root_1 = (Object)adaptor.nil();
                    	        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(VT_AND_INFIX, andToken), root_1);

                    	        adaptor.addChild(root_1, stream_retval.nextTree());
                    	        adaptor.addChild(root_1, stream_lhs_unary.nextTree());

                    	        adaptor.addChild(root_0, root_1);
                    	        }

                    	    }

                    	    retval.tree = root_0;}
                    	    }
                    	    break;

                    	default :
                    	    break loop51;
                        }
                    } while (true);


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "lhs_and"

    public static class lhs_unary_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "lhs_unary"
    // src/main/resources/org/drools/lang/DRL.g:841:1: lhs_unary : ( lhs_exist | {...}? => lhs_not_binding | lhs_not | lhs_eval | lhs_forall | LEFT_PAREN lhs_or RIGHT_PAREN | pattern_source ) ( ( SEMICOLON )=> SEMICOLON )? ;
    public final DRLParser.lhs_unary_return lhs_unary() throws RecognitionException {
        DRLParser.lhs_unary_return retval = new DRLParser.lhs_unary_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LEFT_PAREN137=null;
        Token RIGHT_PAREN139=null;
        Token SEMICOLON141=null;
        DRLParser.lhs_exist_return lhs_exist132 = null;

        DRLParser.lhs_not_binding_return lhs_not_binding133 = null;

        DRLParser.lhs_not_return lhs_not134 = null;

        DRLParser.lhs_eval_return lhs_eval135 = null;

        DRLParser.lhs_forall_return lhs_forall136 = null;

        DRLParser.lhs_or_return lhs_or138 = null;

        DRLParser.pattern_source_return pattern_source140 = null;


        Object LEFT_PAREN137_tree=null;
        Object RIGHT_PAREN139_tree=null;
        Object SEMICOLON141_tree=null;

        try {
            // src/main/resources/org/drools/lang/DRL.g:842:2: ( ( lhs_exist | {...}? => lhs_not_binding | lhs_not | lhs_eval | lhs_forall | LEFT_PAREN lhs_or RIGHT_PAREN | pattern_source ) ( ( SEMICOLON )=> SEMICOLON )? )
            // src/main/resources/org/drools/lang/DRL.g:842:4: ( lhs_exist | {...}? => lhs_not_binding | lhs_not | lhs_eval | lhs_forall | LEFT_PAREN lhs_or RIGHT_PAREN | pattern_source ) ( ( SEMICOLON )=> SEMICOLON )?
            {
            root_0 = (Object)adaptor.nil();

            // src/main/resources/org/drools/lang/DRL.g:842:4: ( lhs_exist | {...}? => lhs_not_binding | lhs_not | lhs_eval | lhs_forall | LEFT_PAREN lhs_or RIGHT_PAREN | pattern_source )
            int alt53=7;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==ID) ) {
                int LA53_1 = input.LA(2);

                if ( (((validateIdentifierKey(DroolsSoftKeywords.EXISTS)))) ) {
                    alt53=1;
                }
                else if ( (((validateNotWithBinding())&&((validateIdentifierKey(DroolsSoftKeywords.NOT))))) ) {
                    alt53=2;
                }
                else if ( (((validateIdentifierKey(DroolsSoftKeywords.NOT)))) ) {
                    alt53=3;
                }
                else if ( (((validateIdentifierKey(DroolsSoftKeywords.EVAL)))) ) {
                    alt53=4;
                }
                else if ( (((validateIdentifierKey(DroolsSoftKeywords.FORALL)))) ) {
                    alt53=5;
                }
                else if ( (true) ) {
                    alt53=7;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 53, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA53_0==LEFT_PAREN) ) {
                alt53=6;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 53, 0, input);

                throw nvae;
            }
            switch (alt53) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:842:6: lhs_exist
                    {
                    pushFollow(FOLLOW_lhs_exist_in_lhs_unary2321);
                    lhs_exist132=lhs_exist();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, lhs_exist132.getTree());

                    }
                    break;
                case 2 :
                    // src/main/resources/org/drools/lang/DRL.g:843:4: {...}? => lhs_not_binding
                    {
                    if ( !((validateNotWithBinding())) ) {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        throw new FailedPredicateException(input, "lhs_unary", "validateNotWithBinding()");
                    }
                    pushFollow(FOLLOW_lhs_not_binding_in_lhs_unary2329);
                    lhs_not_binding133=lhs_not_binding();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, lhs_not_binding133.getTree());

                    }
                    break;
                case 3 :
                    // src/main/resources/org/drools/lang/DRL.g:844:5: lhs_not
                    {
                    pushFollow(FOLLOW_lhs_not_in_lhs_unary2335);
                    lhs_not134=lhs_not();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, lhs_not134.getTree());

                    }
                    break;
                case 4 :
                    // src/main/resources/org/drools/lang/DRL.g:845:5: lhs_eval
                    {
                    pushFollow(FOLLOW_lhs_eval_in_lhs_unary2341);
                    lhs_eval135=lhs_eval();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, lhs_eval135.getTree());

                    }
                    break;
                case 5 :
                    // src/main/resources/org/drools/lang/DRL.g:846:5: lhs_forall
                    {
                    pushFollow(FOLLOW_lhs_forall_in_lhs_unary2347);
                    lhs_forall136=lhs_forall();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, lhs_forall136.getTree());

                    }
                    break;
                case 6 :
                    // src/main/resources/org/drools/lang/DRL.g:847:5: LEFT_PAREN lhs_or RIGHT_PAREN
                    {
                    LEFT_PAREN137=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_lhs_unary2353); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      	emit(LEFT_PAREN137, DroolsEditorType.SYMBOL); emit(Location.LOCATION_LHS_BEGIN_OF_CONDITION );	
                    }
                    pushFollow(FOLLOW_lhs_or_in_lhs_unary2364);
                    lhs_or138=lhs_or();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, lhs_or138.getTree());
                    RIGHT_PAREN139=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_lhs_unary2370); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RIGHT_PAREN139_tree = (Object)adaptor.create(RIGHT_PAREN139);
                    adaptor.addChild(root_0, RIGHT_PAREN139_tree);
                    }
                    if ( state.backtracking==0 ) {
                      	emit(RIGHT_PAREN139, DroolsEditorType.SYMBOL);	
                    }

                    }
                    break;
                case 7 :
                    // src/main/resources/org/drools/lang/DRL.g:850:5: pattern_source
                    {
                    pushFollow(FOLLOW_pattern_source_in_lhs_unary2378);
                    pattern_source140=pattern_source();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, pattern_source140.getTree());

                    }
                    break;

            }

            // src/main/resources/org/drools/lang/DRL.g:852:3: ( ( SEMICOLON )=> SEMICOLON )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==SEMICOLON) ) {
                int LA54_1 = input.LA(2);

                if ( (synpred5_DRL()) ) {
                    alt54=1;
                }
            }
            switch (alt54) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:852:4: ( SEMICOLON )=> SEMICOLON
                    {
                    SEMICOLON141=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_lhs_unary2392); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      	emit(SEMICOLON141, DroolsEditorType.SYMBOL);	
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "lhs_unary"

    public static class lhs_exist_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "lhs_exist"
    // src/main/resources/org/drools/lang/DRL.g:855:1: lhs_exist : exists_key ( ( LEFT_PAREN ( or_key | and_key ) )=> lhs_or | LEFT_PAREN lhs_or RIGHT_PAREN | lhs_pattern ) -> ^( exists_key ( lhs_or )? ( lhs_pattern )? ( RIGHT_PAREN )? ) ;
    public final DRLParser.lhs_exist_return lhs_exist() throws RecognitionException {
        DRLParser.lhs_exist_return retval = new DRLParser.lhs_exist_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LEFT_PAREN144=null;
        Token RIGHT_PAREN146=null;
        DRLParser.exists_key_return exists_key142 = null;

        DRLParser.lhs_or_return lhs_or143 = null;

        DRLParser.lhs_or_return lhs_or145 = null;

        DRLParser.lhs_pattern_return lhs_pattern147 = null;


        Object LEFT_PAREN144_tree=null;
        Object RIGHT_PAREN146_tree=null;
        RewriteRuleTokenStream stream_LEFT_PAREN=new RewriteRuleTokenStream(adaptor,"token LEFT_PAREN");
        RewriteRuleTokenStream stream_RIGHT_PAREN=new RewriteRuleTokenStream(adaptor,"token RIGHT_PAREN");
        RewriteRuleSubtreeStream stream_lhs_or=new RewriteRuleSubtreeStream(adaptor,"rule lhs_or");
        RewriteRuleSubtreeStream stream_exists_key=new RewriteRuleSubtreeStream(adaptor,"rule exists_key");
        RewriteRuleSubtreeStream stream_lhs_pattern=new RewriteRuleSubtreeStream(adaptor,"rule lhs_pattern");
        try {
            // src/main/resources/org/drools/lang/DRL.g:856:2: ( exists_key ( ( LEFT_PAREN ( or_key | and_key ) )=> lhs_or | LEFT_PAREN lhs_or RIGHT_PAREN | lhs_pattern ) -> ^( exists_key ( lhs_or )? ( lhs_pattern )? ( RIGHT_PAREN )? ) )
            // src/main/resources/org/drools/lang/DRL.g:856:4: exists_key ( ( LEFT_PAREN ( or_key | and_key ) )=> lhs_or | LEFT_PAREN lhs_or RIGHT_PAREN | lhs_pattern )
            {
            pushFollow(FOLLOW_exists_key_in_lhs_exist2408);
            exists_key142=exists_key();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_exists_key.add(exists_key142.getTree());
            if ( state.backtracking==0 ) {
              	emit(Location.LOCATION_LHS_BEGIN_OF_CONDITION_EXISTS);	
            }
            // src/main/resources/org/drools/lang/DRL.g:858:10: ( ( LEFT_PAREN ( or_key | and_key ) )=> lhs_or | LEFT_PAREN lhs_or RIGHT_PAREN | lhs_pattern )
            int alt55=3;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==LEFT_PAREN) ) {
                int LA55_1 = input.LA(2);

                if ( (synpred6_DRL()) ) {
                    alt55=1;
                }
                else if ( (true) ) {
                    alt55=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 55, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA55_0==ID) ) {
                int LA55_2 = input.LA(2);

                if ( (((synpred6_DRL()&&((validateIdentifierKey(DroolsSoftKeywords.EVAL))))||(synpred6_DRL()&&((validateIdentifierKey(DroolsSoftKeywords.NOT))))||synpred6_DRL()||(synpred6_DRL()&&((validateIdentifierKey(DroolsSoftKeywords.EXISTS))))||((synpred6_DRL()&&(validateNotWithBinding()))&&((validateIdentifierKey(DroolsSoftKeywords.NOT))))||(synpred6_DRL()&&((validateIdentifierKey(DroolsSoftKeywords.FORALL)))))) ) {
                    alt55=1;
                }
                else if ( (true) ) {
                    alt55=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 55, 2, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 55, 0, input);

                throw nvae;
            }
            switch (alt55) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:858:12: ( LEFT_PAREN ( or_key | and_key ) )=> lhs_or
                    {
                    pushFollow(FOLLOW_lhs_or_in_lhs_exist2435);
                    lhs_or143=lhs_or();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_lhs_or.add(lhs_or143.getTree());

                    }
                    break;
                case 2 :
                    // src/main/resources/org/drools/lang/DRL.g:859:5: LEFT_PAREN lhs_or RIGHT_PAREN
                    {
                    LEFT_PAREN144=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_lhs_exist2442); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LEFT_PAREN.add(LEFT_PAREN144);

                    if ( state.backtracking==0 ) {
                      	emit(LEFT_PAREN144, DroolsEditorType.SYMBOL);	
                    }
                    pushFollow(FOLLOW_lhs_or_in_lhs_exist2450);
                    lhs_or145=lhs_or();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_lhs_or.add(lhs_or145.getTree());
                    RIGHT_PAREN146=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_lhs_exist2457); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RIGHT_PAREN.add(RIGHT_PAREN146);

                    if ( state.backtracking==0 ) {
                      	emit(RIGHT_PAREN146, DroolsEditorType.SYMBOL);	
                    }

                    }
                    break;
                case 3 :
                    // src/main/resources/org/drools/lang/DRL.g:862:12: lhs_pattern
                    {
                    pushFollow(FOLLOW_lhs_pattern_in_lhs_exist2472);
                    lhs_pattern147=lhs_pattern();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_lhs_pattern.add(lhs_pattern147.getTree());

                    }
                    break;

            }



            // AST REWRITE
            // elements: RIGHT_PAREN, exists_key, lhs_or, lhs_pattern
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 864:10: -> ^( exists_key ( lhs_or )? ( lhs_pattern )? ( RIGHT_PAREN )? )
            {
                // src/main/resources/org/drools/lang/DRL.g:864:13: ^( exists_key ( lhs_or )? ( lhs_pattern )? ( RIGHT_PAREN )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_exists_key.nextNode(), root_1);

                // src/main/resources/org/drools/lang/DRL.g:864:26: ( lhs_or )?
                if ( stream_lhs_or.hasNext() ) {
                    adaptor.addChild(root_1, stream_lhs_or.nextTree());

                }
                stream_lhs_or.reset();
                // src/main/resources/org/drools/lang/DRL.g:864:34: ( lhs_pattern )?
                if ( stream_lhs_pattern.hasNext() ) {
                    adaptor.addChild(root_1, stream_lhs_pattern.nextTree());

                }
                stream_lhs_pattern.reset();
                // src/main/resources/org/drools/lang/DRL.g:864:47: ( RIGHT_PAREN )?
                if ( stream_RIGHT_PAREN.hasNext() ) {
                    adaptor.addChild(root_1, stream_RIGHT_PAREN.nextNode());

                }
                stream_RIGHT_PAREN.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "lhs_exist"

    public static class lhs_not_binding_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "lhs_not_binding"
    // src/main/resources/org/drools/lang/DRL.g:867:1: lhs_not_binding : not_key fact_binding -> ^( not_key ^( VT_PATTERN fact_binding ) ) ;
    public final DRLParser.lhs_not_binding_return lhs_not_binding() throws RecognitionException {
        DRLParser.lhs_not_binding_return retval = new DRLParser.lhs_not_binding_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        DRLParser.not_key_return not_key148 = null;

        DRLParser.fact_binding_return fact_binding149 = null;


        RewriteRuleSubtreeStream stream_fact_binding=new RewriteRuleSubtreeStream(adaptor,"rule fact_binding");
        RewriteRuleSubtreeStream stream_not_key=new RewriteRuleSubtreeStream(adaptor,"rule not_key");
        try {
            // src/main/resources/org/drools/lang/DRL.g:868:2: ( not_key fact_binding -> ^( not_key ^( VT_PATTERN fact_binding ) ) )
            // src/main/resources/org/drools/lang/DRL.g:868:4: not_key fact_binding
            {
            pushFollow(FOLLOW_not_key_in_lhs_not_binding2518);
            not_key148=not_key();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_not_key.add(not_key148.getTree());
            pushFollow(FOLLOW_fact_binding_in_lhs_not_binding2520);
            fact_binding149=fact_binding();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_fact_binding.add(fact_binding149.getTree());


            // AST REWRITE
            // elements: not_key, fact_binding
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 869:2: -> ^( not_key ^( VT_PATTERN fact_binding ) )
            {
                // src/main/resources/org/drools/lang/DRL.g:869:5: ^( not_key ^( VT_PATTERN fact_binding ) )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_not_key.nextNode(), root_1);

                // src/main/resources/org/drools/lang/DRL.g:869:15: ^( VT_PATTERN fact_binding )
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(VT_PATTERN, "VT_PATTERN"), root_2);

                adaptor.addChild(root_2, stream_fact_binding.nextTree());

                adaptor.addChild(root_1, root_2);
                }

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "lhs_not_binding"

    public static class lhs_not_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "lhs_not"
    // src/main/resources/org/drools/lang/DRL.g:872:1: lhs_not : not_key ( ( LEFT_PAREN ( or_key | and_key ) )=> lhs_or | LEFT_PAREN lhs_or RIGHT_PAREN | lhs_pattern ) -> ^( not_key ( lhs_or )? ( lhs_pattern )? ( RIGHT_PAREN )? ) ;
    public final DRLParser.lhs_not_return lhs_not() throws RecognitionException {
        DRLParser.lhs_not_return retval = new DRLParser.lhs_not_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LEFT_PAREN152=null;
        Token RIGHT_PAREN154=null;
        DRLParser.not_key_return not_key150 = null;

        DRLParser.lhs_or_return lhs_or151 = null;

        DRLParser.lhs_or_return lhs_or153 = null;

        DRLParser.lhs_pattern_return lhs_pattern155 = null;


        Object LEFT_PAREN152_tree=null;
        Object RIGHT_PAREN154_tree=null;
        RewriteRuleTokenStream stream_LEFT_PAREN=new RewriteRuleTokenStream(adaptor,"token LEFT_PAREN");
        RewriteRuleTokenStream stream_RIGHT_PAREN=new RewriteRuleTokenStream(adaptor,"token RIGHT_PAREN");
        RewriteRuleSubtreeStream stream_lhs_or=new RewriteRuleSubtreeStream(adaptor,"rule lhs_or");
        RewriteRuleSubtreeStream stream_not_key=new RewriteRuleSubtreeStream(adaptor,"rule not_key");
        RewriteRuleSubtreeStream stream_lhs_pattern=new RewriteRuleSubtreeStream(adaptor,"rule lhs_pattern");
        try {
            // src/main/resources/org/drools/lang/DRL.g:872:9: ( not_key ( ( LEFT_PAREN ( or_key | and_key ) )=> lhs_or | LEFT_PAREN lhs_or RIGHT_PAREN | lhs_pattern ) -> ^( not_key ( lhs_or )? ( lhs_pattern )? ( RIGHT_PAREN )? ) )
            // src/main/resources/org/drools/lang/DRL.g:872:11: not_key ( ( LEFT_PAREN ( or_key | and_key ) )=> lhs_or | LEFT_PAREN lhs_or RIGHT_PAREN | lhs_pattern )
            {
            pushFollow(FOLLOW_not_key_in_lhs_not2543);
            not_key150=not_key();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_not_key.add(not_key150.getTree());
            if ( state.backtracking==0 ) {
              	emit(Location.LOCATION_LHS_BEGIN_OF_CONDITION_NOT);	
            }
            // src/main/resources/org/drools/lang/DRL.g:874:3: ( ( LEFT_PAREN ( or_key | and_key ) )=> lhs_or | LEFT_PAREN lhs_or RIGHT_PAREN | lhs_pattern )
            int alt56=3;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==LEFT_PAREN) ) {
                int LA56_1 = input.LA(2);

                if ( (synpred7_DRL()) ) {
                    alt56=1;
                }
                else if ( (true) ) {
                    alt56=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 56, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA56_0==ID) ) {
                int LA56_2 = input.LA(2);

                if ( (synpred7_DRL()) ) {
                    alt56=1;
                }
                else if ( (true) ) {
                    alt56=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 56, 2, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 56, 0, input);

                throw nvae;
            }
            switch (alt56) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:874:5: ( LEFT_PAREN ( or_key | and_key ) )=> lhs_or
                    {
                    if ( state.backtracking==0 ) {
                      	emit(Location.LOCATION_LHS_BEGIN_OF_CONDITION );	
                    }
                    pushFollow(FOLLOW_lhs_or_in_lhs_not2565);
                    lhs_or151=lhs_or();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_lhs_or.add(lhs_or151.getTree());

                    }
                    break;
                case 2 :
                    // src/main/resources/org/drools/lang/DRL.g:875:5: LEFT_PAREN lhs_or RIGHT_PAREN
                    {
                    LEFT_PAREN152=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_lhs_not2572); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LEFT_PAREN.add(LEFT_PAREN152);

                    if ( state.backtracking==0 ) {
                      	emit(LEFT_PAREN152, DroolsEditorType.SYMBOL); emit(Location.LOCATION_LHS_BEGIN_OF_CONDITION );	
                    }
                    pushFollow(FOLLOW_lhs_or_in_lhs_not2581);
                    lhs_or153=lhs_or();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_lhs_or.add(lhs_or153.getTree());
                    RIGHT_PAREN154=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_lhs_not2587); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RIGHT_PAREN.add(RIGHT_PAREN154);

                    if ( state.backtracking==0 ) {
                      	emit(RIGHT_PAREN154, DroolsEditorType.SYMBOL);	
                    }

                    }
                    break;
                case 3 :
                    // src/main/resources/org/drools/lang/DRL.g:878:6: lhs_pattern
                    {
                    pushFollow(FOLLOW_lhs_pattern_in_lhs_not2597);
                    lhs_pattern155=lhs_pattern();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_lhs_pattern.add(lhs_pattern155.getTree());

                    }
                    break;

            }



            // AST REWRITE
            // elements: RIGHT_PAREN, lhs_or, lhs_pattern, not_key
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 879:10: -> ^( not_key ( lhs_or )? ( lhs_pattern )? ( RIGHT_PAREN )? )
            {
                // src/main/resources/org/drools/lang/DRL.g:879:13: ^( not_key ( lhs_or )? ( lhs_pattern )? ( RIGHT_PAREN )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_not_key.nextNode(), root_1);

                // src/main/resources/org/drools/lang/DRL.g:879:23: ( lhs_or )?
                if ( stream_lhs_or.hasNext() ) {
                    adaptor.addChild(root_1, stream_lhs_or.nextTree());

                }
                stream_lhs_or.reset();
                // src/main/resources/org/drools/lang/DRL.g:879:31: ( lhs_pattern )?
                if ( stream_lhs_pattern.hasNext() ) {
                    adaptor.addChild(root_1, stream_lhs_pattern.nextTree());

                }
                stream_lhs_pattern.reset();
                // src/main/resources/org/drools/lang/DRL.g:879:44: ( RIGHT_PAREN )?
                if ( stream_RIGHT_PAREN.hasNext() ) {
                    adaptor.addChild(root_1, stream_RIGHT_PAREN.nextNode());

                }
                stream_RIGHT_PAREN.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "lhs_not"

    public static class lhs_eval_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "lhs_eval"
    // src/main/resources/org/drools/lang/DRL.g:882:1: lhs_eval : ev= eval_key pc= paren_chunk -> ^( eval_key paren_chunk ) ;
    public final DRLParser.lhs_eval_return lhs_eval() throws RecognitionException {
        DRLParser.lhs_eval_return retval = new DRLParser.lhs_eval_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        DRLParser.eval_key_return ev = null;

        DRLParser.paren_chunk_return pc = null;


        RewriteRuleSubtreeStream stream_eval_key=new RewriteRuleSubtreeStream(adaptor,"rule eval_key");
        RewriteRuleSubtreeStream stream_paren_chunk=new RewriteRuleSubtreeStream(adaptor,"rule paren_chunk");
        try {
            // src/main/resources/org/drools/lang/DRL.g:883:2: (ev= eval_key pc= paren_chunk -> ^( eval_key paren_chunk ) )
            // src/main/resources/org/drools/lang/DRL.g:883:4: ev= eval_key pc= paren_chunk
            {
            pushFollow(FOLLOW_eval_key_in_lhs_eval2636);
            ev=eval_key();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_eval_key.add(ev.getTree());
            if ( state.backtracking==0 ) {
              	emit(Location.LOCATION_LHS_INSIDE_EVAL);	
            }
            pushFollow(FOLLOW_paren_chunk_in_lhs_eval2645);
            pc=paren_chunk();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_paren_chunk.add(pc.getTree());
            if ( state.backtracking==0 ) {
              	if (((DroolsTree) (pc!=null?((Object)pc.tree):null)).getText() != null){
              			emit(Location.LOCATION_LHS_BEGIN_OF_CONDITION);	            		
              		}
              	
            }
            if ( state.backtracking==0 ) {
              	String body = safeSubstring( (pc!=null?input.toString(pc.start,pc.stop):null), 1, (pc!=null?input.toString(pc.start,pc.stop):null).length()-1 );
              		checkTrailingSemicolon( body, (ev!=null?((Token)ev.start):null) );	
            }


            // AST REWRITE
            // elements: paren_chunk, eval_key
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 892:3: -> ^( eval_key paren_chunk )
            {
                // src/main/resources/org/drools/lang/DRL.g:892:6: ^( eval_key paren_chunk )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_eval_key.nextNode(), root_1);

                adaptor.addChild(root_1, stream_paren_chunk.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "lhs_eval"

    public static class lhs_forall_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "lhs_forall"
    // src/main/resources/org/drools/lang/DRL.g:895:1: lhs_forall : forall_key LEFT_PAREN ( lhs_pattern )+ RIGHT_PAREN -> ^( forall_key ( lhs_pattern )+ RIGHT_PAREN ) ;
    public final DRLParser.lhs_forall_return lhs_forall() throws RecognitionException {
        DRLParser.lhs_forall_return retval = new DRLParser.lhs_forall_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LEFT_PAREN157=null;
        Token RIGHT_PAREN159=null;
        DRLParser.forall_key_return forall_key156 = null;

        DRLParser.lhs_pattern_return lhs_pattern158 = null;


        Object LEFT_PAREN157_tree=null;
        Object RIGHT_PAREN159_tree=null;
        RewriteRuleTokenStream stream_LEFT_PAREN=new RewriteRuleTokenStream(adaptor,"token LEFT_PAREN");
        RewriteRuleTokenStream stream_RIGHT_PAREN=new RewriteRuleTokenStream(adaptor,"token RIGHT_PAREN");
        RewriteRuleSubtreeStream stream_forall_key=new RewriteRuleSubtreeStream(adaptor,"rule forall_key");
        RewriteRuleSubtreeStream stream_lhs_pattern=new RewriteRuleSubtreeStream(adaptor,"rule lhs_pattern");
        try {
            // src/main/resources/org/drools/lang/DRL.g:896:2: ( forall_key LEFT_PAREN ( lhs_pattern )+ RIGHT_PAREN -> ^( forall_key ( lhs_pattern )+ RIGHT_PAREN ) )
            // src/main/resources/org/drools/lang/DRL.g:896:4: forall_key LEFT_PAREN ( lhs_pattern )+ RIGHT_PAREN
            {
            pushFollow(FOLLOW_forall_key_in_lhs_forall2672);
            forall_key156=forall_key();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_forall_key.add(forall_key156.getTree());
            LEFT_PAREN157=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_lhs_forall2677); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LEFT_PAREN.add(LEFT_PAREN157);

            if ( state.backtracking==0 ) {
              	emit(LEFT_PAREN157, DroolsEditorType.SYMBOL);	
            }
            // src/main/resources/org/drools/lang/DRL.g:898:4: ( lhs_pattern )+
            int cnt57=0;
            loop57:
            do {
                int alt57=2;
                int LA57_0 = input.LA(1);

                if ( (LA57_0==ID) ) {
                    alt57=1;
                }


                switch (alt57) {
            	case 1 :
            	    // src/main/resources/org/drools/lang/DRL.g:898:4: lhs_pattern
            	    {
            	    pushFollow(FOLLOW_lhs_pattern_in_lhs_forall2685);
            	    lhs_pattern158=lhs_pattern();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_lhs_pattern.add(lhs_pattern158.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt57 >= 1 ) break loop57;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(57, input);
                        throw eee;
                }
                cnt57++;
            } while (true);

            RIGHT_PAREN159=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_lhs_forall2691); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RIGHT_PAREN.add(RIGHT_PAREN159);

            if ( state.backtracking==0 ) {
              	emit(RIGHT_PAREN159, DroolsEditorType.SYMBOL);	
            }


            // AST REWRITE
            // elements: forall_key, lhs_pattern, RIGHT_PAREN
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 900:3: -> ^( forall_key ( lhs_pattern )+ RIGHT_PAREN )
            {
                // src/main/resources/org/drools/lang/DRL.g:900:6: ^( forall_key ( lhs_pattern )+ RIGHT_PAREN )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_forall_key.nextNode(), root_1);

                if ( !(stream_lhs_pattern.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_lhs_pattern.hasNext() ) {
                    adaptor.addChild(root_1, stream_lhs_pattern.nextTree());

                }
                stream_lhs_pattern.reset();
                adaptor.addChild(root_1, stream_RIGHT_PAREN.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "lhs_forall"

    public static class pattern_source_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "pattern_source"
    // src/main/resources/org/drools/lang/DRL.g:903:1: pattern_source : lhs_pattern ( over_clause )? ( FROM ( accumulate_statement | collect_statement | entrypoint_statement | from_source ) )? ;
    public final DRLParser.pattern_source_return pattern_source() throws RecognitionException {
        DRLParser.pattern_source_return retval = new DRLParser.pattern_source_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token FROM162=null;
        DRLParser.lhs_pattern_return lhs_pattern160 = null;

        DRLParser.over_clause_return over_clause161 = null;

        DRLParser.accumulate_statement_return accumulate_statement163 = null;

        DRLParser.collect_statement_return collect_statement164 = null;

        DRLParser.entrypoint_statement_return entrypoint_statement165 = null;

        DRLParser.from_source_return from_source166 = null;


        Object FROM162_tree=null;

         boolean isFailed = true;	
        try {
            // src/main/resources/org/drools/lang/DRL.g:906:2: ( lhs_pattern ( over_clause )? ( FROM ( accumulate_statement | collect_statement | entrypoint_statement | from_source ) )? )
            // src/main/resources/org/drools/lang/DRL.g:906:4: lhs_pattern ( over_clause )? ( FROM ( accumulate_statement | collect_statement | entrypoint_statement | from_source ) )?
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_lhs_pattern_in_pattern_source2727);
            lhs_pattern160=lhs_pattern();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, lhs_pattern160.getTree());
            // src/main/resources/org/drools/lang/DRL.g:907:3: ( over_clause )?
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==OVER) ) {
                alt58=1;
            }
            switch (alt58) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:907:3: over_clause
                    {
                    pushFollow(FOLLOW_over_clause_in_pattern_source2731);
                    over_clause161=over_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, over_clause161.getTree());

                    }
                    break;

            }

            // src/main/resources/org/drools/lang/DRL.g:908:3: ( FROM ( accumulate_statement | collect_statement | entrypoint_statement | from_source ) )?
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==FROM) ) {
                alt60=1;
            }
            switch (alt60) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:909:4: FROM ( accumulate_statement | collect_statement | entrypoint_statement | from_source )
                    {
                    FROM162=(Token)match(input,FROM,FOLLOW_FROM_in_pattern_source2741); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FROM162_tree = (Object)adaptor.create(FROM162);
                    root_0 = (Object)adaptor.becomeRoot(FROM162_tree, root_0);
                    }
                    if ( state.backtracking==0 ) {
                      	emit(FROM162, DroolsEditorType.KEYWORD);
                      			emit(Location.LOCATION_LHS_FROM);	
                    }
                    // src/main/resources/org/drools/lang/DRL.g:912:11: ( accumulate_statement | collect_statement | entrypoint_statement | from_source )
                    int alt59=4;
                    switch ( input.LA(1) ) {
                    case ACCUMULATE:
                        {
                        alt59=1;
                        }
                        break;
                    case COLLECT:
                        {
                        alt59=2;
                        }
                        break;
                    case ID:
                        {
                        int LA59_3 = input.LA(2);

                        if ( (LA59_3==MISC) && (((validateIdentifierKey(DroolsSoftKeywords.ENTRY) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.POINT))))) {
                            alt59=3;
                        }
                        else if ( ((LA59_3>=SEMICOLON && LA59_3<=DOT)||(LA59_3>=LEFT_PAREN && LA59_3<=RIGHT_PAREN)||(LA59_3>=DOUBLE_PIPE && LA59_3<=DOUBLE_AMPER)||LA59_3==THEN) ) {
                            alt59=4;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 59, 3, input);

                            throw nvae;
                        }
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 59, 0, input);

                        throw nvae;
                    }

                    switch (alt59) {
                        case 1 :
                            // src/main/resources/org/drools/lang/DRL.g:912:14: accumulate_statement
                            {
                            pushFollow(FOLLOW_accumulate_statement_in_pattern_source2761);
                            accumulate_statement163=accumulate_statement();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, accumulate_statement163.getTree());

                            }
                            break;
                        case 2 :
                            // src/main/resources/org/drools/lang/DRL.g:913:15: collect_statement
                            {
                            pushFollow(FOLLOW_collect_statement_in_pattern_source2777);
                            collect_statement164=collect_statement();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, collect_statement164.getTree());

                            }
                            break;
                        case 3 :
                            // src/main/resources/org/drools/lang/DRL.g:914:15: entrypoint_statement
                            {
                            pushFollow(FOLLOW_entrypoint_statement_in_pattern_source2794);
                            entrypoint_statement165=entrypoint_statement();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, entrypoint_statement165.getTree());

                            }
                            break;
                        case 4 :
                            // src/main/resources/org/drools/lang/DRL.g:915:15: from_source
                            {
                            pushFollow(FOLLOW_from_source_in_pattern_source2810);
                            from_source166=from_source();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, from_source166.getTree());

                            }
                            break;

                    }


                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
               isFailed = false;	
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {

            	if (isEditorInterfaceEnabled && input.LA(3) == EOF && input.LA(1) == ACCUMULATE) {
            			emit(input.LT(1), DroolsEditorType.KEYWORD);
            			emit(input.LT(2), DroolsEditorType.SYMBOL);
            			input.consume();
            			emit(true, Location.LOCATION_LHS_FROM_ACCUMULATE);
            	} else if (isEditorInterfaceEnabled && input.LA(3) == EOF && input.LA(1) == COLLECT) {
            			emit(input.LT(1), DroolsEditorType.KEYWORD);
            			emit(input.LT(2), DroolsEditorType.SYMBOL);
            			input.consume();
            			emit(true, Location.LOCATION_LHS_FROM_COLLECT);
            	}

        }
        return retval;
    }
    // $ANTLR end "pattern_source"

    public static class over_clause_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "over_clause"
    // src/main/resources/org/drools/lang/DRL.g:933:1: over_clause : OVER over_elements ( COMMA over_elements )* ;
    public final DRLParser.over_clause_return over_clause() throws RecognitionException {
        DRLParser.over_clause_return retval = new DRLParser.over_clause_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token OVER167=null;
        Token COMMA169=null;
        DRLParser.over_elements_return over_elements168 = null;

        DRLParser.over_elements_return over_elements170 = null;


        Object OVER167_tree=null;
        Object COMMA169_tree=null;

        try {
            // src/main/resources/org/drools/lang/DRL.g:934:2: ( OVER over_elements ( COMMA over_elements )* )
            // src/main/resources/org/drools/lang/DRL.g:934:4: OVER over_elements ( COMMA over_elements )*
            {
            root_0 = (Object)adaptor.nil();

            OVER167=(Token)match(input,OVER,FOLLOW_OVER_in_over_clause2842); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            OVER167_tree = (Object)adaptor.create(OVER167);
            root_0 = (Object)adaptor.becomeRoot(OVER167_tree, root_0);
            }
            if ( state.backtracking==0 ) {
              	emit(OVER167, DroolsEditorType.KEYWORD);	
            }
            pushFollow(FOLLOW_over_elements_in_over_clause2847);
            over_elements168=over_elements();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, over_elements168.getTree());
            // src/main/resources/org/drools/lang/DRL.g:935:4: ( COMMA over_elements )*
            loop61:
            do {
                int alt61=2;
                int LA61_0 = input.LA(1);

                if ( (LA61_0==COMMA) ) {
                    int LA61_2 = input.LA(2);

                    if ( (LA61_2==ID) ) {
                        int LA61_3 = input.LA(3);

                        if ( (LA61_3==COLON) ) {
                            alt61=1;
                        }


                    }


                }


                switch (alt61) {
            	case 1 :
            	    // src/main/resources/org/drools/lang/DRL.g:935:5: COMMA over_elements
            	    {
            	    COMMA169=(Token)match(input,COMMA,FOLLOW_COMMA_in_over_clause2854); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	      	emit(COMMA169, DroolsEditorType.SYMBOL);	
            	    }
            	    pushFollow(FOLLOW_over_elements_in_over_clause2859);
            	    over_elements170=over_elements();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, over_elements170.getTree());

            	    }
            	    break;

            	default :
            	    break loop61;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "over_clause"

    public static class over_elements_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "over_elements"
    // src/main/resources/org/drools/lang/DRL.g:938:1: over_elements : id1= ID COLON id2= ID paren_chunk -> ^( VT_BEHAVIOR $id1 $id2 paren_chunk ) ;
    public final DRLParser.over_elements_return over_elements() throws RecognitionException {
        DRLParser.over_elements_return retval = new DRLParser.over_elements_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id1=null;
        Token id2=null;
        Token COLON171=null;
        DRLParser.paren_chunk_return paren_chunk172 = null;


        Object id1_tree=null;
        Object id2_tree=null;
        Object COLON171_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_paren_chunk=new RewriteRuleSubtreeStream(adaptor,"rule paren_chunk");
        try {
            // src/main/resources/org/drools/lang/DRL.g:939:2: (id1= ID COLON id2= ID paren_chunk -> ^( VT_BEHAVIOR $id1 $id2 paren_chunk ) )
            // src/main/resources/org/drools/lang/DRL.g:939:4: id1= ID COLON id2= ID paren_chunk
            {
            id1=(Token)match(input,ID,FOLLOW_ID_in_over_elements2874); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id1);

            if ( state.backtracking==0 ) {
              	emit(id1, DroolsEditorType.IDENTIFIER);	
            }
            COLON171=(Token)match(input,COLON,FOLLOW_COLON_in_over_elements2881); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_COLON.add(COLON171);

            if ( state.backtracking==0 ) {
              	emit(COLON171, DroolsEditorType.SYMBOL);	
            }
            id2=(Token)match(input,ID,FOLLOW_ID_in_over_elements2890); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id2);

            if ( state.backtracking==0 ) {
              	emit(id2, DroolsEditorType.IDENTIFIER);	
            }
            pushFollow(FOLLOW_paren_chunk_in_over_elements2897);
            paren_chunk172=paren_chunk();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_paren_chunk.add(paren_chunk172.getTree());


            // AST REWRITE
            // elements: id1, paren_chunk, id2
            // token labels: id1, id2
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleTokenStream stream_id1=new RewriteRuleTokenStream(adaptor,"token id1",id1);
            RewriteRuleTokenStream stream_id2=new RewriteRuleTokenStream(adaptor,"token id2",id2);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 943:2: -> ^( VT_BEHAVIOR $id1 $id2 paren_chunk )
            {
                // src/main/resources/org/drools/lang/DRL.g:943:5: ^( VT_BEHAVIOR $id1 $id2 paren_chunk )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(VT_BEHAVIOR, "VT_BEHAVIOR"), root_1);

                adaptor.addChild(root_1, stream_id1.nextNode());
                adaptor.addChild(root_1, stream_id2.nextNode());
                adaptor.addChild(root_1, stream_paren_chunk.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "over_elements"

    public static class accumulate_statement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "accumulate_statement"
    // src/main/resources/org/drools/lang/DRL.g:946:1: accumulate_statement : ACCUMULATE LEFT_PAREN lhs_or ( COMMA )? ( accumulate_init_clause | accumulate_id_clause ) RIGHT_PAREN -> ^( ACCUMULATE lhs_or ( accumulate_init_clause )? ( accumulate_id_clause )? RIGHT_PAREN ) ;
    public final DRLParser.accumulate_statement_return accumulate_statement() throws RecognitionException {
        DRLParser.accumulate_statement_return retval = new DRLParser.accumulate_statement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ACCUMULATE173=null;
        Token LEFT_PAREN174=null;
        Token COMMA176=null;
        Token RIGHT_PAREN179=null;
        DRLParser.lhs_or_return lhs_or175 = null;

        DRLParser.accumulate_init_clause_return accumulate_init_clause177 = null;

        DRLParser.accumulate_id_clause_return accumulate_id_clause178 = null;


        Object ACCUMULATE173_tree=null;
        Object LEFT_PAREN174_tree=null;
        Object COMMA176_tree=null;
        Object RIGHT_PAREN179_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleTokenStream stream_ACCUMULATE=new RewriteRuleTokenStream(adaptor,"token ACCUMULATE");
        RewriteRuleTokenStream stream_LEFT_PAREN=new RewriteRuleTokenStream(adaptor,"token LEFT_PAREN");
        RewriteRuleTokenStream stream_RIGHT_PAREN=new RewriteRuleTokenStream(adaptor,"token RIGHT_PAREN");
        RewriteRuleSubtreeStream stream_lhs_or=new RewriteRuleSubtreeStream(adaptor,"rule lhs_or");
        RewriteRuleSubtreeStream stream_accumulate_init_clause=new RewriteRuleSubtreeStream(adaptor,"rule accumulate_init_clause");
        RewriteRuleSubtreeStream stream_accumulate_id_clause=new RewriteRuleSubtreeStream(adaptor,"rule accumulate_id_clause");
        try {
            // src/main/resources/org/drools/lang/DRL.g:947:2: ( ACCUMULATE LEFT_PAREN lhs_or ( COMMA )? ( accumulate_init_clause | accumulate_id_clause ) RIGHT_PAREN -> ^( ACCUMULATE lhs_or ( accumulate_init_clause )? ( accumulate_id_clause )? RIGHT_PAREN ) )
            // src/main/resources/org/drools/lang/DRL.g:947:4: ACCUMULATE LEFT_PAREN lhs_or ( COMMA )? ( accumulate_init_clause | accumulate_id_clause ) RIGHT_PAREN
            {
            ACCUMULATE173=(Token)match(input,ACCUMULATE,FOLLOW_ACCUMULATE_in_accumulate_statement2923); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ACCUMULATE.add(ACCUMULATE173);

            if ( state.backtracking==0 ) {
              	emit(ACCUMULATE173, DroolsEditorType.KEYWORD);	
            }
            if ( state.backtracking==0 ) {
              	emit(Location.LOCATION_LHS_FROM_ACCUMULATE);	
            }
            LEFT_PAREN174=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_accumulate_statement2932); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LEFT_PAREN.add(LEFT_PAREN174);

            if ( state.backtracking==0 ) {
              	emit(LEFT_PAREN174, DroolsEditorType.SYMBOL);	
            }
            pushFollow(FOLLOW_lhs_or_in_accumulate_statement2940);
            lhs_or175=lhs_or();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_lhs_or.add(lhs_or175.getTree());
            // src/main/resources/org/drools/lang/DRL.g:951:3: ( COMMA )?
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==COMMA) ) {
                alt62=1;
            }
            switch (alt62) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:951:3: COMMA
                    {
                    COMMA176=(Token)match(input,COMMA,FOLLOW_COMMA_in_accumulate_statement2945); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(COMMA176);


                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              	emit(COMMA176, DroolsEditorType.SYMBOL);	
            }
            // src/main/resources/org/drools/lang/DRL.g:952:3: ( accumulate_init_clause | accumulate_id_clause )
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==ID) ) {
                int LA63_1 = input.LA(2);

                if ( (((validateIdentifierKey(DroolsSoftKeywords.INIT)))) ) {
                    alt63=1;
                }
                else if ( (true) ) {
                    alt63=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 63, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 63, 0, input);

                throw nvae;
            }
            switch (alt63) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:952:5: accumulate_init_clause
                    {
                    pushFollow(FOLLOW_accumulate_init_clause_in_accumulate_statement2955);
                    accumulate_init_clause177=accumulate_init_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_accumulate_init_clause.add(accumulate_init_clause177.getTree());

                    }
                    break;
                case 2 :
                    // src/main/resources/org/drools/lang/DRL.g:953:5: accumulate_id_clause
                    {
                    pushFollow(FOLLOW_accumulate_id_clause_in_accumulate_statement2961);
                    accumulate_id_clause178=accumulate_id_clause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_accumulate_id_clause.add(accumulate_id_clause178.getTree());

                    }
                    break;

            }

            RIGHT_PAREN179=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_accumulate_statement2969); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RIGHT_PAREN.add(RIGHT_PAREN179);

            if ( state.backtracking==0 ) {
              	emit(RIGHT_PAREN179, DroolsEditorType.SYMBOL);	
            }
            if ( state.backtracking==0 ) {
              	emit(Location.LOCATION_LHS_BEGIN_OF_CONDITION);	
            }


            // AST REWRITE
            // elements: lhs_or, ACCUMULATE, RIGHT_PAREN, accumulate_init_clause, accumulate_id_clause
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 957:3: -> ^( ACCUMULATE lhs_or ( accumulate_init_clause )? ( accumulate_id_clause )? RIGHT_PAREN )
            {
                // src/main/resources/org/drools/lang/DRL.g:957:6: ^( ACCUMULATE lhs_or ( accumulate_init_clause )? ( accumulate_id_clause )? RIGHT_PAREN )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_ACCUMULATE.nextNode(), root_1);

                adaptor.addChild(root_1, stream_lhs_or.nextTree());
                // src/main/resources/org/drools/lang/DRL.g:957:26: ( accumulate_init_clause )?
                if ( stream_accumulate_init_clause.hasNext() ) {
                    adaptor.addChild(root_1, stream_accumulate_init_clause.nextTree());

                }
                stream_accumulate_init_clause.reset();
                // src/main/resources/org/drools/lang/DRL.g:957:50: ( accumulate_id_clause )?
                if ( stream_accumulate_id_clause.hasNext() ) {
                    adaptor.addChild(root_1, stream_accumulate_id_clause.nextTree());

                }
                stream_accumulate_id_clause.reset();
                adaptor.addChild(root_1, stream_RIGHT_PAREN.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "accumulate_statement"

    public static class accumulate_init_clause_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "accumulate_init_clause"
    // src/main/resources/org/drools/lang/DRL.g:961:1: accumulate_init_clause : init_key pc1= accumulate_paren_chunk[Location.LOCATION_LHS_FROM_ACCUMULATE_INIT_INSIDE] (cm1= COMMA )? action_key pc2= accumulate_paren_chunk[Location.LOCATION_LHS_FROM_ACCUMULATE_ACTION_INSIDE] (cm2= COMMA )? ( reverse_key pc3= accumulate_paren_chunk[Location.LOCATION_LHS_FROM_ACCUMULATE_REVERSE_INSIDE] (cm3= COMMA )? )? res1= result_key pc4= accumulate_paren_chunk[Location.LOCATION_LHS_FROM_ACCUMULATE_RESULT_INSIDE] -> ^( VT_ACCUMULATE_INIT_CLAUSE ^( init_key $pc1) ^( action_key $pc2) ( ^( reverse_key $pc3) )? ^( result_key $pc4) ) ;
    public final DRLParser.accumulate_init_clause_return accumulate_init_clause() throws RecognitionException {
        DRLParser.accumulate_init_clause_return retval = new DRLParser.accumulate_init_clause_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token cm1=null;
        Token cm2=null;
        Token cm3=null;
        DRLParser.accumulate_paren_chunk_return pc1 = null;

        DRLParser.accumulate_paren_chunk_return pc2 = null;

        DRLParser.accumulate_paren_chunk_return pc3 = null;

        DRLParser.result_key_return res1 = null;

        DRLParser.accumulate_paren_chunk_return pc4 = null;

        DRLParser.init_key_return init_key180 = null;

        DRLParser.action_key_return action_key181 = null;

        DRLParser.reverse_key_return reverse_key182 = null;


        Object cm1_tree=null;
        Object cm2_tree=null;
        Object cm3_tree=null;
        RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
        RewriteRuleSubtreeStream stream_accumulate_paren_chunk=new RewriteRuleSubtreeStream(adaptor,"rule accumulate_paren_chunk");
        RewriteRuleSubtreeStream stream_reverse_key=new RewriteRuleSubtreeStream(adaptor,"rule reverse_key");
        RewriteRuleSubtreeStream stream_result_key=new RewriteRuleSubtreeStream(adaptor,"rule result_key");
        RewriteRuleSubtreeStream stream_init_key=new RewriteRuleSubtreeStream(adaptor,"rule init_key");
        RewriteRuleSubtreeStream stream_action_key=new RewriteRuleSubtreeStream(adaptor,"rule action_key");
         boolean isFailed = true;	
        try {
            // src/main/resources/org/drools/lang/DRL.g:964:2: ( init_key pc1= accumulate_paren_chunk[Location.LOCATION_LHS_FROM_ACCUMULATE_INIT_INSIDE] (cm1= COMMA )? action_key pc2= accumulate_paren_chunk[Location.LOCATION_LHS_FROM_ACCUMULATE_ACTION_INSIDE] (cm2= COMMA )? ( reverse_key pc3= accumulate_paren_chunk[Location.LOCATION_LHS_FROM_ACCUMULATE_REVERSE_INSIDE] (cm3= COMMA )? )? res1= result_key pc4= accumulate_paren_chunk[Location.LOCATION_LHS_FROM_ACCUMULATE_RESULT_INSIDE] -> ^( VT_ACCUMULATE_INIT_CLAUSE ^( init_key $pc1) ^( action_key $pc2) ( ^( reverse_key $pc3) )? ^( result_key $pc4) ) )
            // src/main/resources/org/drools/lang/DRL.g:964:4: init_key pc1= accumulate_paren_chunk[Location.LOCATION_LHS_FROM_ACCUMULATE_INIT_INSIDE] (cm1= COMMA )? action_key pc2= accumulate_paren_chunk[Location.LOCATION_LHS_FROM_ACCUMULATE_ACTION_INSIDE] (cm2= COMMA )? ( reverse_key pc3= accumulate_paren_chunk[Location.LOCATION_LHS_FROM_ACCUMULATE_REVERSE_INSIDE] (cm3= COMMA )? )? res1= result_key pc4= accumulate_paren_chunk[Location.LOCATION_LHS_FROM_ACCUMULATE_RESULT_INSIDE]
            {
            pushFollow(FOLLOW_init_key_in_accumulate_init_clause3015);
            init_key180=init_key();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_init_key.add(init_key180.getTree());
            if ( state.backtracking==0 ) {
              	emit(Location.LOCATION_LHS_FROM_ACCUMULATE_INIT);	
            }
            pushFollow(FOLLOW_accumulate_paren_chunk_in_accumulate_init_clause3025);
            pc1=accumulate_paren_chunk(Location.LOCATION_LHS_FROM_ACCUMULATE_INIT_INSIDE);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_accumulate_paren_chunk.add(pc1.getTree());
            // src/main/resources/org/drools/lang/DRL.g:966:84: (cm1= COMMA )?
            int alt64=2;
            int LA64_0 = input.LA(1);

            if ( (LA64_0==COMMA) ) {
                alt64=1;
            }
            switch (alt64) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:966:84: cm1= COMMA
                    {
                    cm1=(Token)match(input,COMMA,FOLLOW_COMMA_in_accumulate_init_clause3030); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(cm1);


                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              	emit(cm1, DroolsEditorType.SYMBOL);	
            }
            if ( state.backtracking==0 ) {
              	if (pc1 != null && ((DroolsTree) pc1.getTree()).getText() != null) emit(Location.LOCATION_LHS_FROM_ACCUMULATE_ACTION);	
            }
            pushFollow(FOLLOW_action_key_in_accumulate_init_clause3041);
            action_key181=action_key();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_action_key.add(action_key181.getTree());
            pushFollow(FOLLOW_accumulate_paren_chunk_in_accumulate_init_clause3045);
            pc2=accumulate_paren_chunk(Location.LOCATION_LHS_FROM_ACCUMULATE_ACTION_INSIDE);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_accumulate_paren_chunk.add(pc2.getTree());
            // src/main/resources/org/drools/lang/DRL.g:968:97: (cm2= COMMA )?
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==COMMA) ) {
                alt65=1;
            }
            switch (alt65) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:968:97: cm2= COMMA
                    {
                    cm2=(Token)match(input,COMMA,FOLLOW_COMMA_in_accumulate_init_clause3050); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_COMMA.add(cm2);


                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              	emit(cm2, DroolsEditorType.SYMBOL);	
            }
            if ( state.backtracking==0 ) {
              	if (pc1 != null && ((DroolsTree) pc1.getTree()).getText() != null && pc2 != null && ((DroolsTree) pc2.getTree()).getText() != null ) emit(Location.LOCATION_LHS_FROM_ACCUMULATE_REVERSE);	
            }
            // src/main/resources/org/drools/lang/DRL.g:970:2: ( reverse_key pc3= accumulate_paren_chunk[Location.LOCATION_LHS_FROM_ACCUMULATE_REVERSE_INSIDE] (cm3= COMMA )? )?
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==ID) ) {
                int LA67_1 = input.LA(2);

                if ( (((validateIdentifierKey(DroolsSoftKeywords.REVERSE)))) ) {
                    alt67=1;
                }
            }
            switch (alt67) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:970:4: reverse_key pc3= accumulate_paren_chunk[Location.LOCATION_LHS_FROM_ACCUMULATE_REVERSE_INSIDE] (cm3= COMMA )?
                    {
                    pushFollow(FOLLOW_reverse_key_in_accumulate_init_clause3062);
                    reverse_key182=reverse_key();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_reverse_key.add(reverse_key182.getTree());
                    pushFollow(FOLLOW_accumulate_paren_chunk_in_accumulate_init_clause3066);
                    pc3=accumulate_paren_chunk(Location.LOCATION_LHS_FROM_ACCUMULATE_REVERSE_INSIDE);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_accumulate_paren_chunk.add(pc3.getTree());
                    // src/main/resources/org/drools/lang/DRL.g:970:100: (cm3= COMMA )?
                    int alt66=2;
                    int LA66_0 = input.LA(1);

                    if ( (LA66_0==COMMA) ) {
                        alt66=1;
                    }
                    switch (alt66) {
                        case 1 :
                            // src/main/resources/org/drools/lang/DRL.g:970:100: cm3= COMMA
                            {
                            cm3=(Token)match(input,COMMA,FOLLOW_COMMA_in_accumulate_init_clause3071); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_COMMA.add(cm3);


                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {
                      	emit(cm3, DroolsEditorType.SYMBOL);	
                    }

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              	if ((pc1 != null && ((DroolsTree) pc1.tree).getText() != null) &&
                          			(pc2 != null && ((DroolsTree) pc2.tree).getText() != null) &&
                          			(pc3 != null && ((DroolsTree) pc3.tree).getText() != null)) {
              			emit(Location.LOCATION_LHS_FROM_ACCUMULATE_RESULT);
              		}	
              	
            }
            pushFollow(FOLLOW_result_key_in_accumulate_init_clause3087);
            res1=result_key();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_result_key.add(res1.getTree());
            if ( state.backtracking==0 ) {
              	emit((res1!=null?((Token)res1.start):null), DroolsEditorType.KEYWORD);	
            }
            pushFollow(FOLLOW_accumulate_paren_chunk_in_accumulate_init_clause3093);
            pc4=accumulate_paren_chunk(Location.LOCATION_LHS_FROM_ACCUMULATE_RESULT_INSIDE);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_accumulate_paren_chunk.add(pc4.getTree());


            // AST REWRITE
            // elements: reverse_key, action_key, pc2, pc4, result_key, pc1, init_key, pc3
            // token labels: 
            // rule labels: pc2, pc3, pc4, pc1, retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_pc2=new RewriteRuleSubtreeStream(adaptor,"token pc2",pc2!=null?pc2.tree:null);
            RewriteRuleSubtreeStream stream_pc3=new RewriteRuleSubtreeStream(adaptor,"token pc3",pc3!=null?pc3.tree:null);
            RewriteRuleSubtreeStream stream_pc4=new RewriteRuleSubtreeStream(adaptor,"token pc4",pc4!=null?pc4.tree:null);
            RewriteRuleSubtreeStream stream_pc1=new RewriteRuleSubtreeStream(adaptor,"token pc1",pc1!=null?pc1.tree:null);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 979:2: -> ^( VT_ACCUMULATE_INIT_CLAUSE ^( init_key $pc1) ^( action_key $pc2) ( ^( reverse_key $pc3) )? ^( result_key $pc4) )
            {
                // src/main/resources/org/drools/lang/DRL.g:979:5: ^( VT_ACCUMULATE_INIT_CLAUSE ^( init_key $pc1) ^( action_key $pc2) ( ^( reverse_key $pc3) )? ^( result_key $pc4) )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(VT_ACCUMULATE_INIT_CLAUSE, "VT_ACCUMULATE_INIT_CLAUSE"), root_1);

                // src/main/resources/org/drools/lang/DRL.g:979:33: ^( init_key $pc1)
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot(stream_init_key.nextNode(), root_2);

                adaptor.addChild(root_2, stream_pc1.nextTree());

                adaptor.addChild(root_1, root_2);
                }
                // src/main/resources/org/drools/lang/DRL.g:979:50: ^( action_key $pc2)
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot(stream_action_key.nextNode(), root_2);

                adaptor.addChild(root_2, stream_pc2.nextTree());

                adaptor.addChild(root_1, root_2);
                }
                // src/main/resources/org/drools/lang/DRL.g:979:69: ( ^( reverse_key $pc3) )?
                if ( stream_reverse_key.hasNext()||stream_pc3.hasNext() ) {
                    // src/main/resources/org/drools/lang/DRL.g:979:69: ^( reverse_key $pc3)
                    {
                    Object root_2 = (Object)adaptor.nil();
                    root_2 = (Object)adaptor.becomeRoot(stream_reverse_key.nextNode(), root_2);

                    adaptor.addChild(root_2, stream_pc3.nextTree());

                    adaptor.addChild(root_1, root_2);
                    }

                }
                stream_reverse_key.reset();
                stream_pc3.reset();
                // src/main/resources/org/drools/lang/DRL.g:979:90: ^( result_key $pc4)
                {
                Object root_2 = (Object)adaptor.nil();
                root_2 = (Object)adaptor.becomeRoot(stream_result_key.nextNode(), root_2);

                adaptor.addChild(root_2, stream_pc4.nextTree());

                adaptor.addChild(root_1, root_2);
                }

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
               isFailed = false;	
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
             
            	if (isEditorInterfaceEnabled && isFailed && input.LA(1) == ID && validateLT(1, DroolsSoftKeywords.RESULT)) {
            		emit(input.LT(1), DroolsEditorType.KEYWORD);
            		input.consume();
            		if (input.LA(1) == LEFT_PAREN){
            			input.consume();
            			emit(Location.LOCATION_LHS_FROM_ACCUMULATE_RESULT_INSIDE);
            		}
            	}

        }
        return retval;
    }
    // $ANTLR end "accumulate_init_clause"

    public static class accumulate_paren_chunk_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "accumulate_paren_chunk"
    // src/main/resources/org/drools/lang/DRL.g:992:1: accumulate_paren_chunk[int locationType] : pc= accumulate_paren_chunk_data[false,$locationType] -> VT_PAREN_CHUNK[$pc.start,text] ;
    public final DRLParser.accumulate_paren_chunk_return accumulate_paren_chunk(int locationType) throws RecognitionException {
        DRLParser.accumulate_paren_chunk_return retval = new DRLParser.accumulate_paren_chunk_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        DRLParser.accumulate_paren_chunk_data_return pc = null;


        RewriteRuleSubtreeStream stream_accumulate_paren_chunk_data=new RewriteRuleSubtreeStream(adaptor,"rule accumulate_paren_chunk_data");

        	String text = "";

        try {
            // src/main/resources/org/drools/lang/DRL.g:995:3: (pc= accumulate_paren_chunk_data[false,$locationType] -> VT_PAREN_CHUNK[$pc.start,text] )
            // src/main/resources/org/drools/lang/DRL.g:995:5: pc= accumulate_paren_chunk_data[false,$locationType]
            {
            pushFollow(FOLLOW_accumulate_paren_chunk_data_in_accumulate_paren_chunk3151);
            pc=accumulate_paren_chunk_data(false, locationType);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_accumulate_paren_chunk_data.add(pc.getTree());
            if ( state.backtracking==0 ) {
              text = (pc!=null?input.toString(pc.start,pc.stop):null);
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 996:2: -> VT_PAREN_CHUNK[$pc.start,text]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VT_PAREN_CHUNK, (pc!=null?((Token)pc.start):null), text));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "accumulate_paren_chunk"

    public static class accumulate_paren_chunk_data_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "accumulate_paren_chunk_data"
    // src/main/resources/org/drools/lang/DRL.g:999:1: accumulate_paren_chunk_data[boolean isRecursive, int locationType] : lp1= LEFT_PAREN (any=~ ( LEFT_PAREN | RIGHT_PAREN ) | accumulate_paren_chunk_data[true,-1] )* rp1= RIGHT_PAREN ;
    public final DRLParser.accumulate_paren_chunk_data_return accumulate_paren_chunk_data(boolean isRecursive, int locationType) throws RecognitionException {
        DRLParser.accumulate_paren_chunk_data_return retval = new DRLParser.accumulate_paren_chunk_data_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token lp1=null;
        Token any=null;
        Token rp1=null;
        DRLParser.accumulate_paren_chunk_data_return accumulate_paren_chunk_data183 = null;


        Object lp1_tree=null;
        Object any_tree=null;
        Object rp1_tree=null;

        try {
            // src/main/resources/org/drools/lang/DRL.g:1000:2: (lp1= LEFT_PAREN (any=~ ( LEFT_PAREN | RIGHT_PAREN ) | accumulate_paren_chunk_data[true,-1] )* rp1= RIGHT_PAREN )
            // src/main/resources/org/drools/lang/DRL.g:1000:4: lp1= LEFT_PAREN (any=~ ( LEFT_PAREN | RIGHT_PAREN ) | accumulate_paren_chunk_data[true,-1] )* rp1= RIGHT_PAREN
            {
            root_0 = (Object)adaptor.nil();

            lp1=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_accumulate_paren_chunk_data3175); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            lp1_tree = (Object)adaptor.create(lp1);
            adaptor.addChild(root_0, lp1_tree);
            }
            if ( state.backtracking==0 ) {
              	if (!isRecursive) {
              				emit(lp1, DroolsEditorType.SYMBOL);
              				emit(locationType);
              			} else {
              				emit(lp1, DroolsEditorType.CODE_CHUNK);
              			}	
              		
            }
            // src/main/resources/org/drools/lang/DRL.g:1008:4: (any=~ ( LEFT_PAREN | RIGHT_PAREN ) | accumulate_paren_chunk_data[true,-1] )*
            loop68:
            do {
                int alt68=3;
                int LA68_0 = input.LA(1);

                if ( ((LA68_0>=VT_COMPILATION_UNIT && LA68_0<=STRING)||LA68_0==COMMA||(LA68_0>=AT && LA68_0<=MULTI_LINE_COMMENT)) ) {
                    alt68=1;
                }
                else if ( (LA68_0==LEFT_PAREN) ) {
                    alt68=2;
                }


                switch (alt68) {
            	case 1 :
            	    // src/main/resources/org/drools/lang/DRL.g:1008:5: any=~ ( LEFT_PAREN | RIGHT_PAREN )
            	    {
            	    any=(Token)input.LT(1);
            	    if ( (input.LA(1)>=VT_COMPILATION_UNIT && input.LA(1)<=STRING)||input.LA(1)==COMMA||(input.LA(1)>=AT && input.LA(1)<=MULTI_LINE_COMMENT) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(any));
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    if ( state.backtracking==0 ) {
            	       emit(any, DroolsEditorType.CODE_CHUNK); 
            	    }

            	    }
            	    break;
            	case 2 :
            	    // src/main/resources/org/drools/lang/DRL.g:1008:87: accumulate_paren_chunk_data[true,-1]
            	    {
            	    pushFollow(FOLLOW_accumulate_paren_chunk_data_in_accumulate_paren_chunk_data3203);
            	    accumulate_paren_chunk_data183=accumulate_paren_chunk_data(true, -1);

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, accumulate_paren_chunk_data183.getTree());

            	    }
            	    break;

            	default :
            	    break loop68;
                }
            } while (true);

            rp1=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_accumulate_paren_chunk_data3214); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            rp1_tree = (Object)adaptor.create(rp1);
            adaptor.addChild(root_0, rp1_tree);
            }
            if ( state.backtracking==0 ) {
              	if (!isRecursive) {
              				emit(rp1, DroolsEditorType.SYMBOL);
              			} else {
              				emit(rp1, DroolsEditorType.CODE_CHUNK);
              			}	
              		
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "accumulate_paren_chunk_data"

    public static class accumulate_id_clause_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "accumulate_id_clause"
    // src/main/resources/org/drools/lang/DRL.g:1018:1: accumulate_id_clause : ID paren_chunk -> ^( VT_ACCUMULATE_ID_CLAUSE ID paren_chunk ) ;
    public final DRLParser.accumulate_id_clause_return accumulate_id_clause() throws RecognitionException {
        DRLParser.accumulate_id_clause_return retval = new DRLParser.accumulate_id_clause_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID184=null;
        DRLParser.paren_chunk_return paren_chunk185 = null;


        Object ID184_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_paren_chunk=new RewriteRuleSubtreeStream(adaptor,"rule paren_chunk");
        try {
            // src/main/resources/org/drools/lang/DRL.g:1019:2: ( ID paren_chunk -> ^( VT_ACCUMULATE_ID_CLAUSE ID paren_chunk ) )
            // src/main/resources/org/drools/lang/DRL.g:1019:4: ID paren_chunk
            {
            ID184=(Token)match(input,ID,FOLLOW_ID_in_accumulate_id_clause3230); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(ID184);

            if ( state.backtracking==0 ) {
              	emit(ID184, DroolsEditorType.IDENTIFIER);	
            }
            pushFollow(FOLLOW_paren_chunk_in_accumulate_id_clause3236);
            paren_chunk185=paren_chunk();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_paren_chunk.add(paren_chunk185.getTree());


            // AST REWRITE
            // elements: paren_chunk, ID
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1021:2: -> ^( VT_ACCUMULATE_ID_CLAUSE ID paren_chunk )
            {
                // src/main/resources/org/drools/lang/DRL.g:1021:5: ^( VT_ACCUMULATE_ID_CLAUSE ID paren_chunk )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(VT_ACCUMULATE_ID_CLAUSE, "VT_ACCUMULATE_ID_CLAUSE"), root_1);

                adaptor.addChild(root_1, stream_ID.nextNode());
                adaptor.addChild(root_1, stream_paren_chunk.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "accumulate_id_clause"

    public static class collect_statement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "collect_statement"
    // src/main/resources/org/drools/lang/DRL.g:1024:1: collect_statement : COLLECT LEFT_PAREN pattern_source RIGHT_PAREN -> ^( COLLECT pattern_source RIGHT_PAREN ) ;
    public final DRLParser.collect_statement_return collect_statement() throws RecognitionException {
        DRLParser.collect_statement_return retval = new DRLParser.collect_statement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COLLECT186=null;
        Token LEFT_PAREN187=null;
        Token RIGHT_PAREN189=null;
        DRLParser.pattern_source_return pattern_source188 = null;


        Object COLLECT186_tree=null;
        Object LEFT_PAREN187_tree=null;
        Object RIGHT_PAREN189_tree=null;
        RewriteRuleTokenStream stream_LEFT_PAREN=new RewriteRuleTokenStream(adaptor,"token LEFT_PAREN");
        RewriteRuleTokenStream stream_RIGHT_PAREN=new RewriteRuleTokenStream(adaptor,"token RIGHT_PAREN");
        RewriteRuleTokenStream stream_COLLECT=new RewriteRuleTokenStream(adaptor,"token COLLECT");
        RewriteRuleSubtreeStream stream_pattern_source=new RewriteRuleSubtreeStream(adaptor,"rule pattern_source");
        try {
            // src/main/resources/org/drools/lang/DRL.g:1025:2: ( COLLECT LEFT_PAREN pattern_source RIGHT_PAREN -> ^( COLLECT pattern_source RIGHT_PAREN ) )
            // src/main/resources/org/drools/lang/DRL.g:1025:4: COLLECT LEFT_PAREN pattern_source RIGHT_PAREN
            {
            COLLECT186=(Token)match(input,COLLECT,FOLLOW_COLLECT_in_collect_statement3258); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_COLLECT.add(COLLECT186);

            if ( state.backtracking==0 ) {
              	emit(COLLECT186, DroolsEditorType.KEYWORD);	
            }
            if ( state.backtracking==0 ) {
              	emit(Location.LOCATION_LHS_FROM_COLLECT);	
            }
            LEFT_PAREN187=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_collect_statement3267); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LEFT_PAREN.add(LEFT_PAREN187);

            if ( state.backtracking==0 ) {
              	emit(LEFT_PAREN187, DroolsEditorType.SYMBOL);	
            }
            pushFollow(FOLLOW_pattern_source_in_collect_statement3274);
            pattern_source188=pattern_source();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_pattern_source.add(pattern_source188.getTree());
            RIGHT_PAREN189=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_collect_statement3279); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RIGHT_PAREN.add(RIGHT_PAREN189);

            if ( state.backtracking==0 ) {
              	emit(RIGHT_PAREN189, DroolsEditorType.SYMBOL);	
            }
            if ( state.backtracking==0 ) {
              	emit(Location.LOCATION_LHS_BEGIN_OF_CONDITION);	
            }


            // AST REWRITE
            // elements: RIGHT_PAREN, pattern_source, COLLECT
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1031:2: -> ^( COLLECT pattern_source RIGHT_PAREN )
            {
                // src/main/resources/org/drools/lang/DRL.g:1031:5: ^( COLLECT pattern_source RIGHT_PAREN )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_COLLECT.nextNode(), root_1);

                adaptor.addChild(root_1, stream_pattern_source.nextTree());
                adaptor.addChild(root_1, stream_RIGHT_PAREN.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "collect_statement"

    public static class entrypoint_statement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "entrypoint_statement"
    // src/main/resources/org/drools/lang/DRL.g:1034:1: entrypoint_statement : entry_point_key entrypoint_id -> ^( entry_point_key entrypoint_id ) ;
    public final DRLParser.entrypoint_statement_return entrypoint_statement() throws RecognitionException {
        DRLParser.entrypoint_statement_return retval = new DRLParser.entrypoint_statement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        DRLParser.entry_point_key_return entry_point_key190 = null;

        DRLParser.entrypoint_id_return entrypoint_id191 = null;


        RewriteRuleSubtreeStream stream_entrypoint_id=new RewriteRuleSubtreeStream(adaptor,"rule entrypoint_id");
        RewriteRuleSubtreeStream stream_entry_point_key=new RewriteRuleSubtreeStream(adaptor,"rule entry_point_key");
        try {
            // src/main/resources/org/drools/lang/DRL.g:1035:2: ( entry_point_key entrypoint_id -> ^( entry_point_key entrypoint_id ) )
            // src/main/resources/org/drools/lang/DRL.g:1035:4: entry_point_key entrypoint_id
            {
            pushFollow(FOLLOW_entry_point_key_in_entrypoint_statement3306);
            entry_point_key190=entry_point_key();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_entry_point_key.add(entry_point_key190.getTree());
            if ( state.backtracking==0 ) {
              	emit(Location.LOCATION_LHS_FROM_COLLECT);	
            }
            pushFollow(FOLLOW_entrypoint_id_in_entrypoint_statement3314);
            entrypoint_id191=entrypoint_id();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_entrypoint_id.add(entrypoint_id191.getTree());
            if ( state.backtracking==0 ) {
              	emit(Location.LOCATION_LHS_BEGIN_OF_CONDITION);	
            }


            // AST REWRITE
            // elements: entrypoint_id, entry_point_key
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1039:2: -> ^( entry_point_key entrypoint_id )
            {
                // src/main/resources/org/drools/lang/DRL.g:1039:5: ^( entry_point_key entrypoint_id )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot(stream_entry_point_key.nextNode(), root_1);

                adaptor.addChild(root_1, stream_entrypoint_id.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "entrypoint_statement"

    public static class entrypoint_id_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "entrypoint_id"
    // src/main/resources/org/drools/lang/DRL.g:1042:1: entrypoint_id : (value= ID -> VT_ENTRYPOINT_ID[$value] | value= STRING -> VT_ENTRYPOINT_ID[$value] );
    public final DRLParser.entrypoint_id_return entrypoint_id() throws RecognitionException {
        DRLParser.entrypoint_id_return retval = new DRLParser.entrypoint_id_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token value=null;

        Object value_tree=null;
        RewriteRuleTokenStream stream_STRING=new RewriteRuleTokenStream(adaptor,"token STRING");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // src/main/resources/org/drools/lang/DRL.g:1043:2: (value= ID -> VT_ENTRYPOINT_ID[$value] | value= STRING -> VT_ENTRYPOINT_ID[$value] )
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( (LA69_0==ID) ) {
                alt69=1;
            }
            else if ( (LA69_0==STRING) ) {
                alt69=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 69, 0, input);

                throw nvae;
            }
            switch (alt69) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:1043:5: value= ID
                    {
                    value=(Token)match(input,ID,FOLLOW_ID_in_entrypoint_id3340); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ID.add(value);

                    if ( state.backtracking==0 ) {
                      	emit(value, DroolsEditorType.IDENTIFIER);	
                    }


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 1044:3: -> VT_ENTRYPOINT_ID[$value]
                    {
                        adaptor.addChild(root_0, (Object)adaptor.create(VT_ENTRYPOINT_ID, value));

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // src/main/resources/org/drools/lang/DRL.g:1045:5: value= STRING
                    {
                    value=(Token)match(input,STRING,FOLLOW_STRING_in_entrypoint_id3357); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_STRING.add(value);

                    if ( state.backtracking==0 ) {
                      	emit(value, DroolsEditorType.IDENTIFIER);	
                    }


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 1046:3: -> VT_ENTRYPOINT_ID[$value]
                    {
                        adaptor.addChild(root_0, (Object)adaptor.create(VT_ENTRYPOINT_ID, value));

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "entrypoint_id"

    public static class from_source_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "from_source"
    // src/main/resources/org/drools/lang/DRL.g:1049:1: from_source : ID ( ( LEFT_PAREN )=>args= paren_chunk )? ( expression_chain )? -> ^( VT_FROM_SOURCE ID ( paren_chunk )? ( expression_chain )? ) ;
    public final DRLParser.from_source_return from_source() throws RecognitionException {
        DRLParser.from_source_return retval = new DRLParser.from_source_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID192=null;
        DRLParser.paren_chunk_return args = null;

        DRLParser.expression_chain_return expression_chain193 = null;


        Object ID192_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_expression_chain=new RewriteRuleSubtreeStream(adaptor,"rule expression_chain");
        RewriteRuleSubtreeStream stream_paren_chunk=new RewriteRuleSubtreeStream(adaptor,"rule paren_chunk");
        try {
            // src/main/resources/org/drools/lang/DRL.g:1050:2: ( ID ( ( LEFT_PAREN )=>args= paren_chunk )? ( expression_chain )? -> ^( VT_FROM_SOURCE ID ( paren_chunk )? ( expression_chain )? ) )
            // src/main/resources/org/drools/lang/DRL.g:1050:4: ID ( ( LEFT_PAREN )=>args= paren_chunk )? ( expression_chain )?
            {
            ID192=(Token)match(input,ID,FOLLOW_ID_in_from_source3377); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(ID192);

            if ( state.backtracking==0 ) {
              	emit(ID192, DroolsEditorType.IDENTIFIER);	
            }
            // src/main/resources/org/drools/lang/DRL.g:1051:3: ( ( LEFT_PAREN )=>args= paren_chunk )?
            int alt70=2;
            alt70 = dfa70.predict(input);
            switch (alt70) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:1051:5: ( LEFT_PAREN )=>args= paren_chunk
                    {
                    pushFollow(FOLLOW_paren_chunk_in_from_source3392);
                    args=paren_chunk();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_paren_chunk.add(args.getTree());

                    }
                    break;

            }

            // src/main/resources/org/drools/lang/DRL.g:1052:3: ( expression_chain )?
            int alt71=2;
            int LA71_0 = input.LA(1);

            if ( (LA71_0==DOT) ) {
                alt71=1;
            }
            switch (alt71) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:1052:3: expression_chain
                    {
                    pushFollow(FOLLOW_expression_chain_in_from_source3399);
                    expression_chain193=expression_chain();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression_chain.add(expression_chain193.getTree());

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              	if ( input.LA(1) == EOF && input.get(input.index() - 1).getType() == WS) {
              			emit(Location.LOCATION_LHS_BEGIN_OF_CONDITION);
              		} else if ( input.LA(1) != EOF ) {
              			emit(Location.LOCATION_LHS_BEGIN_OF_CONDITION);
              		}	
            }


            // AST REWRITE
            // elements: ID, paren_chunk, expression_chain
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1058:2: -> ^( VT_FROM_SOURCE ID ( paren_chunk )? ( expression_chain )? )
            {
                // src/main/resources/org/drools/lang/DRL.g:1058:5: ^( VT_FROM_SOURCE ID ( paren_chunk )? ( expression_chain )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(VT_FROM_SOURCE, "VT_FROM_SOURCE"), root_1);

                adaptor.addChild(root_1, stream_ID.nextNode());
                // src/main/resources/org/drools/lang/DRL.g:1058:25: ( paren_chunk )?
                if ( stream_paren_chunk.hasNext() ) {
                    adaptor.addChild(root_1, stream_paren_chunk.nextTree());

                }
                stream_paren_chunk.reset();
                // src/main/resources/org/drools/lang/DRL.g:1058:38: ( expression_chain )?
                if ( stream_expression_chain.hasNext() ) {
                    adaptor.addChild(root_1, stream_expression_chain.nextTree());

                }
                stream_expression_chain.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "from_source"

    public static class expression_chain_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "expression_chain"
    // src/main/resources/org/drools/lang/DRL.g:1061:1: expression_chain : DOT ID ({...}? paren_chunk | square_chunk )? ( expression_chain )? -> ^( VT_EXPRESSION_CHAIN[$DOT] ID ( square_chunk )? ( paren_chunk )? ( expression_chain )? ) ;
    public final DRLParser.expression_chain_return expression_chain() throws RecognitionException {
        DRLParser.expression_chain_return retval = new DRLParser.expression_chain_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token DOT194=null;
        Token ID195=null;
        DRLParser.paren_chunk_return paren_chunk196 = null;

        DRLParser.square_chunk_return square_chunk197 = null;

        DRLParser.expression_chain_return expression_chain198 = null;


        Object DOT194_tree=null;
        Object ID195_tree=null;
        RewriteRuleTokenStream stream_DOT=new RewriteRuleTokenStream(adaptor,"token DOT");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_square_chunk=new RewriteRuleSubtreeStream(adaptor,"rule square_chunk");
        RewriteRuleSubtreeStream stream_expression_chain=new RewriteRuleSubtreeStream(adaptor,"rule expression_chain");
        RewriteRuleSubtreeStream stream_paren_chunk=new RewriteRuleSubtreeStream(adaptor,"rule paren_chunk");
        try {
            // src/main/resources/org/drools/lang/DRL.g:1062:2: ( DOT ID ({...}? paren_chunk | square_chunk )? ( expression_chain )? -> ^( VT_EXPRESSION_CHAIN[$DOT] ID ( square_chunk )? ( paren_chunk )? ( expression_chain )? ) )
            // src/main/resources/org/drools/lang/DRL.g:1063:3: DOT ID ({...}? paren_chunk | square_chunk )? ( expression_chain )?
            {
            DOT194=(Token)match(input,DOT,FOLLOW_DOT_in_expression_chain3432); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_DOT.add(DOT194);

            if ( state.backtracking==0 ) {
              	emit(DOT194, DroolsEditorType.IDENTIFIER);	
            }
            ID195=(Token)match(input,ID,FOLLOW_ID_in_expression_chain3439); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(ID195);

            if ( state.backtracking==0 ) {
              	emit(ID195, DroolsEditorType.IDENTIFIER);	
            }
            // src/main/resources/org/drools/lang/DRL.g:1065:4: ({...}? paren_chunk | square_chunk )?
            int alt72=3;
            alt72 = dfa72.predict(input);
            switch (alt72) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:1066:6: {...}? paren_chunk
                    {
                    if ( !((input.LA(1) == LEFT_PAREN)) ) {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        throw new FailedPredicateException(input, "expression_chain", "input.LA(1) == LEFT_PAREN");
                    }
                    pushFollow(FOLLOW_paren_chunk_in_expression_chain3455);
                    paren_chunk196=paren_chunk();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_paren_chunk.add(paren_chunk196.getTree());

                    }
                    break;
                case 2 :
                    // src/main/resources/org/drools/lang/DRL.g:1068:6: square_chunk
                    {
                    pushFollow(FOLLOW_square_chunk_in_expression_chain3469);
                    square_chunk197=square_chunk();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_square_chunk.add(square_chunk197.getTree());

                    }
                    break;

            }

            // src/main/resources/org/drools/lang/DRL.g:1070:4: ( expression_chain )?
            int alt73=2;
            int LA73_0 = input.LA(1);

            if ( (LA73_0==DOT) ) {
                alt73=1;
            }
            switch (alt73) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:1070:4: expression_chain
                    {
                    pushFollow(FOLLOW_expression_chain_in_expression_chain3480);
                    expression_chain198=expression_chain();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression_chain.add(expression_chain198.getTree());

                    }
                    break;

            }



            // AST REWRITE
            // elements: ID, expression_chain, square_chunk, paren_chunk
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1071:4: -> ^( VT_EXPRESSION_CHAIN[$DOT] ID ( square_chunk )? ( paren_chunk )? ( expression_chain )? )
            {
                // src/main/resources/org/drools/lang/DRL.g:1071:7: ^( VT_EXPRESSION_CHAIN[$DOT] ID ( square_chunk )? ( paren_chunk )? ( expression_chain )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(VT_EXPRESSION_CHAIN, DOT194), root_1);

                adaptor.addChild(root_1, stream_ID.nextNode());
                // src/main/resources/org/drools/lang/DRL.g:1071:38: ( square_chunk )?
                if ( stream_square_chunk.hasNext() ) {
                    adaptor.addChild(root_1, stream_square_chunk.nextTree());

                }
                stream_square_chunk.reset();
                // src/main/resources/org/drools/lang/DRL.g:1071:52: ( paren_chunk )?
                if ( stream_paren_chunk.hasNext() ) {
                    adaptor.addChild(root_1, stream_paren_chunk.nextTree());

                }
                stream_paren_chunk.reset();
                // src/main/resources/org/drools/lang/DRL.g:1071:65: ( expression_chain )?
                if ( stream_expression_chain.hasNext() ) {
                    adaptor.addChild(root_1, stream_expression_chain.nextTree());

                }
                stream_expression_chain.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "expression_chain"

    public static class lhs_pattern_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "lhs_pattern"
    // src/main/resources/org/drools/lang/DRL.g:1074:1: lhs_pattern : ( fact_binding -> ^( VT_PATTERN fact_binding ) | fact -> ^( VT_PATTERN fact ) );
    public final DRLParser.lhs_pattern_return lhs_pattern() throws RecognitionException {
        DRLParser.lhs_pattern_return retval = new DRLParser.lhs_pattern_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        DRLParser.fact_binding_return fact_binding199 = null;

        DRLParser.fact_return fact200 = null;


        RewriteRuleSubtreeStream stream_fact_binding=new RewriteRuleSubtreeStream(adaptor,"rule fact_binding");
        RewriteRuleSubtreeStream stream_fact=new RewriteRuleSubtreeStream(adaptor,"rule fact");
        try {
            // src/main/resources/org/drools/lang/DRL.g:1075:2: ( fact_binding -> ^( VT_PATTERN fact_binding ) | fact -> ^( VT_PATTERN fact ) )
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( (LA74_0==ID) ) {
                int LA74_1 = input.LA(2);

                if ( (LA74_1==DOT||LA74_1==LEFT_PAREN||LA74_1==LEFT_SQUARE) ) {
                    alt74=2;
                }
                else if ( (LA74_1==COLON) ) {
                    alt74=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 74, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 74, 0, input);

                throw nvae;
            }
            switch (alt74) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:1075:4: fact_binding
                    {
                    pushFollow(FOLLOW_fact_binding_in_lhs_pattern3513);
                    fact_binding199=fact_binding();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_fact_binding.add(fact_binding199.getTree());


                    // AST REWRITE
                    // elements: fact_binding
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 1075:17: -> ^( VT_PATTERN fact_binding )
                    {
                        // src/main/resources/org/drools/lang/DRL.g:1075:20: ^( VT_PATTERN fact_binding )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(VT_PATTERN, "VT_PATTERN"), root_1);

                        adaptor.addChild(root_1, stream_fact_binding.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // src/main/resources/org/drools/lang/DRL.g:1076:4: fact
                    {
                    pushFollow(FOLLOW_fact_in_lhs_pattern3526);
                    fact200=fact();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_fact.add(fact200.getTree());


                    // AST REWRITE
                    // elements: fact
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 1076:9: -> ^( VT_PATTERN fact )
                    {
                        // src/main/resources/org/drools/lang/DRL.g:1076:12: ^( VT_PATTERN fact )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(VT_PATTERN, "VT_PATTERN"), root_1);

                        adaptor.addChild(root_1, stream_fact.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "lhs_pattern"

    public static class fact_binding_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "fact_binding"
    // src/main/resources/org/drools/lang/DRL.g:1079:1: fact_binding : label ( fact | LEFT_PAREN fact_binding_expression RIGHT_PAREN ) -> ^( VT_FACT_BINDING label ( fact )? ( fact_binding_expression )? ( RIGHT_PAREN )? ) ;
    public final DRLParser.fact_binding_return fact_binding() throws RecognitionException {
        DRLParser.fact_binding_return retval = new DRLParser.fact_binding_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LEFT_PAREN203=null;
        Token RIGHT_PAREN205=null;
        DRLParser.label_return label201 = null;

        DRLParser.fact_return fact202 = null;

        DRLParser.fact_binding_expression_return fact_binding_expression204 = null;


        Object LEFT_PAREN203_tree=null;
        Object RIGHT_PAREN205_tree=null;
        RewriteRuleTokenStream stream_LEFT_PAREN=new RewriteRuleTokenStream(adaptor,"token LEFT_PAREN");
        RewriteRuleTokenStream stream_RIGHT_PAREN=new RewriteRuleTokenStream(adaptor,"token RIGHT_PAREN");
        RewriteRuleSubtreeStream stream_label=new RewriteRuleSubtreeStream(adaptor,"rule label");
        RewriteRuleSubtreeStream stream_fact_binding_expression=new RewriteRuleSubtreeStream(adaptor,"rule fact_binding_expression");
        RewriteRuleSubtreeStream stream_fact=new RewriteRuleSubtreeStream(adaptor,"rule fact");
        try {
            // src/main/resources/org/drools/lang/DRL.g:1080:3: ( label ( fact | LEFT_PAREN fact_binding_expression RIGHT_PAREN ) -> ^( VT_FACT_BINDING label ( fact )? ( fact_binding_expression )? ( RIGHT_PAREN )? ) )
            // src/main/resources/org/drools/lang/DRL.g:1080:5: label ( fact | LEFT_PAREN fact_binding_expression RIGHT_PAREN )
            {
            pushFollow(FOLLOW_label_in_fact_binding3546);
            label201=label();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_label.add(label201.getTree());
            // src/main/resources/org/drools/lang/DRL.g:1081:3: ( fact | LEFT_PAREN fact_binding_expression RIGHT_PAREN )
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( (LA75_0==ID) ) {
                alt75=1;
            }
            else if ( (LA75_0==LEFT_PAREN) ) {
                alt75=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 75, 0, input);

                throw nvae;
            }
            switch (alt75) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:1081:5: fact
                    {
                    pushFollow(FOLLOW_fact_in_fact_binding3552);
                    fact202=fact();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_fact.add(fact202.getTree());

                    }
                    break;
                case 2 :
                    // src/main/resources/org/drools/lang/DRL.g:1082:6: LEFT_PAREN fact_binding_expression RIGHT_PAREN
                    {
                    LEFT_PAREN203=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_fact_binding3559); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_LEFT_PAREN.add(LEFT_PAREN203);

                    if ( state.backtracking==0 ) {
                      	emit(LEFT_PAREN203, DroolsEditorType.SYMBOL);	
                    }
                    pushFollow(FOLLOW_fact_binding_expression_in_fact_binding3567);
                    fact_binding_expression204=fact_binding_expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_fact_binding_expression.add(fact_binding_expression204.getTree());
                    RIGHT_PAREN205=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_fact_binding3575); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RIGHT_PAREN.add(RIGHT_PAREN205);

                    if ( state.backtracking==0 ) {
                      	emit(RIGHT_PAREN205, DroolsEditorType.SYMBOL);	
                    }

                    }
                    break;

            }



            // AST REWRITE
            // elements: fact, RIGHT_PAREN, label, fact_binding_expression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1086:3: -> ^( VT_FACT_BINDING label ( fact )? ( fact_binding_expression )? ( RIGHT_PAREN )? )
            {
                // src/main/resources/org/drools/lang/DRL.g:1086:6: ^( VT_FACT_BINDING label ( fact )? ( fact_binding_expression )? ( RIGHT_PAREN )? )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(VT_FACT_BINDING, "VT_FACT_BINDING"), root_1);

                adaptor.addChild(root_1, stream_label.nextTree());
                // src/main/resources/org/drools/lang/DRL.g:1086:30: ( fact )?
                if ( stream_fact.hasNext() ) {
                    adaptor.addChild(root_1, stream_fact.nextTree());

                }
                stream_fact.reset();
                // src/main/resources/org/drools/lang/DRL.g:1086:36: ( fact_binding_expression )?
                if ( stream_fact_binding_expression.hasNext() ) {
                    adaptor.addChild(root_1, stream_fact_binding_expression.nextTree());

                }
                stream_fact_binding_expression.reset();
                // src/main/resources/org/drools/lang/DRL.g:1086:61: ( RIGHT_PAREN )?
                if ( stream_RIGHT_PAREN.hasNext() ) {
                    adaptor.addChild(root_1, stream_RIGHT_PAREN.nextNode());

                }
                stream_RIGHT_PAREN.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "fact_binding"

    public static class fact_binding_expression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "fact_binding_expression"
    // src/main/resources/org/drools/lang/DRL.g:1089:1: fact_binding_expression : ( fact -> fact ) ( (value= or_key | pipe= DOUBLE_PIPE ) fact -> ^( VT_FACT_OR[orToken] $fact_binding_expression fact ) )* ;
    public final DRLParser.fact_binding_expression_return fact_binding_expression() throws RecognitionException {
        DRLParser.fact_binding_expression_return retval = new DRLParser.fact_binding_expression_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token pipe=null;
        DRLParser.or_key_return value = null;

        DRLParser.fact_return fact206 = null;

        DRLParser.fact_return fact207 = null;


        Object pipe_tree=null;
        RewriteRuleTokenStream stream_DOUBLE_PIPE=new RewriteRuleTokenStream(adaptor,"token DOUBLE_PIPE");
        RewriteRuleSubtreeStream stream_or_key=new RewriteRuleSubtreeStream(adaptor,"rule or_key");
        RewriteRuleSubtreeStream stream_fact=new RewriteRuleSubtreeStream(adaptor,"rule fact");

        	Token orToken = null;

        try {
            // src/main/resources/org/drools/lang/DRL.g:1092:3: ( ( fact -> fact ) ( (value= or_key | pipe= DOUBLE_PIPE ) fact -> ^( VT_FACT_OR[orToken] $fact_binding_expression fact ) )* )
            // src/main/resources/org/drools/lang/DRL.g:1092:5: ( fact -> fact ) ( (value= or_key | pipe= DOUBLE_PIPE ) fact -> ^( VT_FACT_OR[orToken] $fact_binding_expression fact ) )*
            {
            // src/main/resources/org/drools/lang/DRL.g:1092:5: ( fact -> fact )
            // src/main/resources/org/drools/lang/DRL.g:1092:6: fact
            {
            pushFollow(FOLLOW_fact_in_fact_binding_expression3616);
            fact206=fact();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_fact.add(fact206.getTree());


            // AST REWRITE
            // elements: fact
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1092:11: -> fact
            {
                adaptor.addChild(root_0, stream_fact.nextTree());

            }

            retval.tree = root_0;}
            }

            // src/main/resources/org/drools/lang/DRL.g:1092:20: ( (value= or_key | pipe= DOUBLE_PIPE ) fact -> ^( VT_FACT_OR[orToken] $fact_binding_expression fact ) )*
            loop77:
            do {
                int alt77=2;
                int LA77_0 = input.LA(1);

                if ( (LA77_0==ID) && (((validateIdentifierKey(DroolsSoftKeywords.OR))))) {
                    alt77=1;
                }
                else if ( (LA77_0==DOUBLE_PIPE) ) {
                    alt77=1;
                }


                switch (alt77) {
            	case 1 :
            	    // src/main/resources/org/drools/lang/DRL.g:1092:22: (value= or_key | pipe= DOUBLE_PIPE ) fact
            	    {
            	    // src/main/resources/org/drools/lang/DRL.g:1092:22: (value= or_key | pipe= DOUBLE_PIPE )
            	    int alt76=2;
            	    int LA76_0 = input.LA(1);

            	    if ( (LA76_0==ID) && (((validateIdentifierKey(DroolsSoftKeywords.OR))))) {
            	        alt76=1;
            	    }
            	    else if ( (LA76_0==DOUBLE_PIPE) ) {
            	        alt76=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 76, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt76) {
            	        case 1 :
            	            // src/main/resources/org/drools/lang/DRL.g:1092:23: value= or_key
            	            {
            	            pushFollow(FOLLOW_or_key_in_fact_binding_expression3628);
            	            value=or_key();

            	            state._fsp--;
            	            if (state.failed) return retval;
            	            if ( state.backtracking==0 ) stream_or_key.add(value.getTree());
            	            if ( state.backtracking==0 ) {
            	              orToken = (value!=null?((Token)value.start):null);
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // src/main/resources/org/drools/lang/DRL.g:1092:62: pipe= DOUBLE_PIPE
            	            {
            	            pipe=(Token)match(input,DOUBLE_PIPE,FOLLOW_DOUBLE_PIPE_in_fact_binding_expression3634); if (state.failed) return retval; 
            	            if ( state.backtracking==0 ) stream_DOUBLE_PIPE.add(pipe);

            	            if ( state.backtracking==0 ) {
            	              orToken = pipe;
            	            }

            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_fact_in_fact_binding_expression3639);
            	    fact207=fact();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_fact.add(fact207.getTree());


            	    // AST REWRITE
            	    // elements: fact_binding_expression, fact
            	    // token labels: 
            	    // rule labels: retval
            	    // token list labels: 
            	    // rule list labels: 
            	    if ( state.backtracking==0 ) {
            	    retval.tree = root_0;
            	    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            	    root_0 = (Object)adaptor.nil();
            	    // 1093:3: -> ^( VT_FACT_OR[orToken] $fact_binding_expression fact )
            	    {
            	        // src/main/resources/org/drools/lang/DRL.g:1093:6: ^( VT_FACT_OR[orToken] $fact_binding_expression fact )
            	        {
            	        Object root_1 = (Object)adaptor.nil();
            	        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(VT_FACT_OR, orToken), root_1);

            	        adaptor.addChild(root_1, stream_retval.nextTree());
            	        adaptor.addChild(root_1, stream_fact.nextTree());

            	        adaptor.addChild(root_0, root_1);
            	        }

            	    }

            	    retval.tree = root_0;}
            	    }
            	    break;

            	default :
            	    break loop77;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "fact_binding_expression"

    public static class fact_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "fact"
    // src/main/resources/org/drools/lang/DRL.g:1096:1: fact : pattern_type LEFT_PAREN ( constraints )? RIGHT_PAREN -> ^( VT_FACT pattern_type ( constraints )? RIGHT_PAREN ) ;
    public final DRLParser.fact_return fact() throws RecognitionException {
        DRLParser.fact_return retval = new DRLParser.fact_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LEFT_PAREN209=null;
        Token RIGHT_PAREN211=null;
        DRLParser.pattern_type_return pattern_type208 = null;

        DRLParser.constraints_return constraints210 = null;


        Object LEFT_PAREN209_tree=null;
        Object RIGHT_PAREN211_tree=null;
        RewriteRuleTokenStream stream_LEFT_PAREN=new RewriteRuleTokenStream(adaptor,"token LEFT_PAREN");
        RewriteRuleTokenStream stream_RIGHT_PAREN=new RewriteRuleTokenStream(adaptor,"token RIGHT_PAREN");
        RewriteRuleSubtreeStream stream_pattern_type=new RewriteRuleSubtreeStream(adaptor,"rule pattern_type");
        RewriteRuleSubtreeStream stream_constraints=new RewriteRuleSubtreeStream(adaptor,"rule constraints");
         boolean isFailedOnConstraints = true; pushParaphrases(DroolsParaphraseTypes.PATTERN); 
        try {
            // src/main/resources/org/drools/lang/DRL.g:1099:2: ( pattern_type LEFT_PAREN ( constraints )? RIGHT_PAREN -> ^( VT_FACT pattern_type ( constraints )? RIGHT_PAREN ) )
            // src/main/resources/org/drools/lang/DRL.g:1099:4: pattern_type LEFT_PAREN ( constraints )? RIGHT_PAREN
            {
            pushFollow(FOLLOW_pattern_type_in_fact3679);
            pattern_type208=pattern_type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_pattern_type.add(pattern_type208.getTree());
            LEFT_PAREN209=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_fact3684); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LEFT_PAREN.add(LEFT_PAREN209);

            if ( state.backtracking==0 ) {
              	emit(LEFT_PAREN209, DroolsEditorType.SYMBOL);	
            }
            if ( state.backtracking==0 ) {
              	emit(Location.LOCATION_LHS_INSIDE_CONDITION_START);	
            }
            // src/main/resources/org/drools/lang/DRL.g:1102:4: ( constraints )?
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( (LA78_0==ID||LA78_0==LEFT_PAREN) ) {
                alt78=1;
            }
            switch (alt78) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:1102:4: constraints
                    {
                    pushFollow(FOLLOW_constraints_in_fact3695);
                    constraints210=constraints();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_constraints.add(constraints210.getTree());

                    }
                    break;

            }

            RIGHT_PAREN211=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_fact3701); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RIGHT_PAREN.add(RIGHT_PAREN211);

            if ( state.backtracking==0 ) {
              	isFailedOnConstraints = false;	
            }
            if ( state.backtracking==0 ) {
              	if ((RIGHT_PAREN211!=null?RIGHT_PAREN211.getText():null).equals(")") ){ //WORKAROUND FOR ANTLR BUG!
              			emit(RIGHT_PAREN211, DroolsEditorType.SYMBOL);
              			emit(Location.LOCATION_LHS_BEGIN_OF_CONDITION);
              		}	
            }


            // AST REWRITE
            // elements: constraints, RIGHT_PAREN, pattern_type
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1108:2: -> ^( VT_FACT pattern_type ( constraints )? RIGHT_PAREN )
            {
                // src/main/resources/org/drools/lang/DRL.g:1108:5: ^( VT_FACT pattern_type ( constraints )? RIGHT_PAREN )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(VT_FACT, "VT_FACT"), root_1);

                adaptor.addChild(root_1, stream_pattern_type.nextTree());
                // src/main/resources/org/drools/lang/DRL.g:1108:28: ( constraints )?
                if ( stream_constraints.hasNext() ) {
                    adaptor.addChild(root_1, stream_constraints.nextTree());

                }
                stream_constraints.reset();
                adaptor.addChild(root_1, stream_RIGHT_PAREN.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
               paraphrases.pop();	
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {

            	if (isEditorInterfaceEnabled && isFailedOnConstraints && input.LA(1) == EOF && input.get(input.index() - 1).getType() == WS){
            		if (!(getActiveSentence().getContent().getLast() instanceof Integer) && input.LA(-1) != COLON) {
            			emit(Location.LOCATION_LHS_INSIDE_CONDITION_OPERATOR);
            		}
            	}

        }
        return retval;
    }
    // $ANTLR end "fact"

    public static class constraints_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "constraints"
    // src/main/resources/org/drools/lang/DRL.g:1118:1: constraints : constraint ( COMMA constraint )* ;
    public final DRLParser.constraints_return constraints() throws RecognitionException {
        DRLParser.constraints_return retval = new DRLParser.constraints_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token COMMA213=null;
        DRLParser.constraint_return constraint212 = null;

        DRLParser.constraint_return constraint214 = null;


        Object COMMA213_tree=null;

        try {
            // src/main/resources/org/drools/lang/DRL.g:1119:2: ( constraint ( COMMA constraint )* )
            // src/main/resources/org/drools/lang/DRL.g:1119:4: constraint ( COMMA constraint )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_constraint_in_constraints3735);
            constraint212=constraint();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, constraint212.getTree());
            // src/main/resources/org/drools/lang/DRL.g:1119:15: ( COMMA constraint )*
            loop79:
            do {
                int alt79=2;
                int LA79_0 = input.LA(1);

                if ( (LA79_0==COMMA) ) {
                    alt79=1;
                }


                switch (alt79) {
            	case 1 :
            	    // src/main/resources/org/drools/lang/DRL.g:1119:17: COMMA constraint
            	    {
            	    COMMA213=(Token)match(input,COMMA,FOLLOW_COMMA_in_constraints3739); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	      	emit(COMMA213, DroolsEditorType.SYMBOL);
            	      		emit(Location.LOCATION_LHS_INSIDE_CONDITION_START);	
            	    }
            	    pushFollow(FOLLOW_constraint_in_constraints3746);
            	    constraint214=constraint();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, constraint214.getTree());

            	    }
            	    break;

            	default :
            	    break loop79;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "constraints"

    public static class constraint_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "constraint"
    // src/main/resources/org/drools/lang/DRL.g:1124:1: constraint : or_constr ;
    public final DRLParser.constraint_return constraint() throws RecognitionException {
        DRLParser.constraint_return retval = new DRLParser.constraint_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        DRLParser.or_constr_return or_constr215 = null;



        try {
            // src/main/resources/org/drools/lang/DRL.g:1125:2: ( or_constr )
            // src/main/resources/org/drools/lang/DRL.g:1125:4: or_constr
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_or_constr_in_constraint3760);
            or_constr215=or_constr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, or_constr215.getTree());

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "constraint"

    public static class or_constr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "or_constr"
    // src/main/resources/org/drools/lang/DRL.g:1128:1: or_constr : and_constr ( DOUBLE_PIPE and_constr )* ;
    public final DRLParser.or_constr_return or_constr() throws RecognitionException {
        DRLParser.or_constr_return retval = new DRLParser.or_constr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token DOUBLE_PIPE217=null;
        DRLParser.and_constr_return and_constr216 = null;

        DRLParser.and_constr_return and_constr218 = null;


        Object DOUBLE_PIPE217_tree=null;

        try {
            // src/main/resources/org/drools/lang/DRL.g:1129:2: ( and_constr ( DOUBLE_PIPE and_constr )* )
            // src/main/resources/org/drools/lang/DRL.g:1129:4: and_constr ( DOUBLE_PIPE and_constr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_and_constr_in_or_constr3771);
            and_constr216=and_constr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, and_constr216.getTree());
            // src/main/resources/org/drools/lang/DRL.g:1129:15: ( DOUBLE_PIPE and_constr )*
            loop80:
            do {
                int alt80=2;
                int LA80_0 = input.LA(1);

                if ( (LA80_0==DOUBLE_PIPE) ) {
                    alt80=1;
                }


                switch (alt80) {
            	case 1 :
            	    // src/main/resources/org/drools/lang/DRL.g:1129:17: DOUBLE_PIPE and_constr
            	    {
            	    DOUBLE_PIPE217=(Token)match(input,DOUBLE_PIPE,FOLLOW_DOUBLE_PIPE_in_or_constr3775); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    DOUBLE_PIPE217_tree = (Object)adaptor.create(DOUBLE_PIPE217);
            	    root_0 = (Object)adaptor.becomeRoot(DOUBLE_PIPE217_tree, root_0);
            	    }
            	    if ( state.backtracking==0 ) {
            	      	emit(DOUBLE_PIPE217, DroolsEditorType.SYMBOL);	
            	    }
            	    pushFollow(FOLLOW_and_constr_in_or_constr3782);
            	    and_constr218=and_constr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, and_constr218.getTree());

            	    }
            	    break;

            	default :
            	    break loop80;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "or_constr"

    public static class and_constr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "and_constr"
    // src/main/resources/org/drools/lang/DRL.g:1133:1: and_constr : unary_constr ( DOUBLE_AMPER unary_constr )* ;
    public final DRLParser.and_constr_return and_constr() throws RecognitionException {
        DRLParser.and_constr_return retval = new DRLParser.and_constr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token DOUBLE_AMPER220=null;
        DRLParser.unary_constr_return unary_constr219 = null;

        DRLParser.unary_constr_return unary_constr221 = null;


        Object DOUBLE_AMPER220_tree=null;

        try {
            // src/main/resources/org/drools/lang/DRL.g:1134:2: ( unary_constr ( DOUBLE_AMPER unary_constr )* )
            // src/main/resources/org/drools/lang/DRL.g:1134:4: unary_constr ( DOUBLE_AMPER unary_constr )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_unary_constr_in_and_constr3797);
            unary_constr219=unary_constr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, unary_constr219.getTree());
            // src/main/resources/org/drools/lang/DRL.g:1134:17: ( DOUBLE_AMPER unary_constr )*
            loop81:
            do {
                int alt81=2;
                int LA81_0 = input.LA(1);

                if ( (LA81_0==DOUBLE_AMPER) ) {
                    alt81=1;
                }


                switch (alt81) {
            	case 1 :
            	    // src/main/resources/org/drools/lang/DRL.g:1134:19: DOUBLE_AMPER unary_constr
            	    {
            	    DOUBLE_AMPER220=(Token)match(input,DOUBLE_AMPER,FOLLOW_DOUBLE_AMPER_in_and_constr3801); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    DOUBLE_AMPER220_tree = (Object)adaptor.create(DOUBLE_AMPER220);
            	    root_0 = (Object)adaptor.becomeRoot(DOUBLE_AMPER220_tree, root_0);
            	    }
            	    if ( state.backtracking==0 ) {
            	      	emit(DOUBLE_AMPER220, DroolsEditorType.SYMBOL);;	
            	    }
            	    pushFollow(FOLLOW_unary_constr_in_and_constr3808);
            	    unary_constr221=unary_constr();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, unary_constr221.getTree());

            	    }
            	    break;

            	default :
            	    break loop81;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "and_constr"

    public static class unary_constr_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "unary_constr"
    // src/main/resources/org/drools/lang/DRL.g:1138:1: unary_constr options {k=2; } : ( eval_key paren_chunk | field_constraint | LEFT_PAREN or_constr RIGHT_PAREN );
    public final DRLParser.unary_constr_return unary_constr() throws RecognitionException {
        DRLParser.unary_constr_return retval = new DRLParser.unary_constr_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LEFT_PAREN225=null;
        Token RIGHT_PAREN227=null;
        DRLParser.eval_key_return eval_key222 = null;

        DRLParser.paren_chunk_return paren_chunk223 = null;

        DRLParser.field_constraint_return field_constraint224 = null;

        DRLParser.or_constr_return or_constr226 = null;


        Object LEFT_PAREN225_tree=null;
        Object RIGHT_PAREN227_tree=null;

         boolean isFailed = true;	
        try {
            // src/main/resources/org/drools/lang/DRL.g:1142:2: ( eval_key paren_chunk | field_constraint | LEFT_PAREN or_constr RIGHT_PAREN )
            int alt82=3;
            alt82 = dfa82.predict(input);
            switch (alt82) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:1142:4: eval_key paren_chunk
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_eval_key_in_unary_constr3841);
                    eval_key222=eval_key();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(eval_key222.getTree(), root_0);
                    pushFollow(FOLLOW_paren_chunk_in_unary_constr3844);
                    paren_chunk223=paren_chunk();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, paren_chunk223.getTree());

                    }
                    break;
                case 2 :
                    // src/main/resources/org/drools/lang/DRL.g:1143:4: field_constraint
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_field_constraint_in_unary_constr3849);
                    field_constraint224=field_constraint();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, field_constraint224.getTree());

                    }
                    break;
                case 3 :
                    // src/main/resources/org/drools/lang/DRL.g:1144:5: LEFT_PAREN or_constr RIGHT_PAREN
                    {
                    root_0 = (Object)adaptor.nil();

                    LEFT_PAREN225=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_unary_constr3855); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      	emit(LEFT_PAREN225, DroolsEditorType.SYMBOL);	
                    }
                    pushFollow(FOLLOW_or_constr_in_unary_constr3865);
                    or_constr226=or_constr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, or_constr226.getTree());
                    RIGHT_PAREN227=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_unary_constr3870); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RIGHT_PAREN227_tree = (Object)adaptor.create(RIGHT_PAREN227);
                    adaptor.addChild(root_0, RIGHT_PAREN227_tree);
                    }
                    if ( state.backtracking==0 ) {
                      	emit(RIGHT_PAREN227, DroolsEditorType.SYMBOL);	
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
               isFailed = false;	
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
             
            	if (isEditorInterfaceEnabled && isFailed && input.LA(2) == EOF && input.LA(1) == ID) {
            		emit(input.LT(1), DroolsEditorType.IDENTIFIER);
            		input.consume();
            		if (input.get(input.index() - 1).getType() == WS)
            			emit(Location.LOCATION_LHS_INSIDE_CONDITION_OPERATOR);
            	}

        }
        return retval;
    }
    // $ANTLR end "unary_constr"

    public static class field_constraint_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "field_constraint"
    // src/main/resources/org/drools/lang/DRL.g:1157:1: field_constraint : ( label accessor_path ( or_restr_connective | arw= ARROW paren_chunk )? -> {isArrow}? ^( VT_BIND_FIELD label ^( VT_FIELD accessor_path ) ) ( ^( VK_EVAL[$arw] paren_chunk ) )? -> ^( VT_BIND_FIELD label ^( VT_FIELD accessor_path ( or_restr_connective )? ) ) | accessor_path or_restr_connective -> ^( VT_FIELD accessor_path or_restr_connective ) );
    public final DRLParser.field_constraint_return field_constraint() throws RecognitionException {
        DRLParser.field_constraint_return retval = new DRLParser.field_constraint_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token arw=null;
        DRLParser.label_return label228 = null;

        DRLParser.accessor_path_return accessor_path229 = null;

        DRLParser.or_restr_connective_return or_restr_connective230 = null;

        DRLParser.paren_chunk_return paren_chunk231 = null;

        DRLParser.accessor_path_return accessor_path232 = null;

        DRLParser.or_restr_connective_return or_restr_connective233 = null;


        Object arw_tree=null;
        RewriteRuleTokenStream stream_ARROW=new RewriteRuleTokenStream(adaptor,"token ARROW");
        RewriteRuleSubtreeStream stream_accessor_path=new RewriteRuleSubtreeStream(adaptor,"rule accessor_path");
        RewriteRuleSubtreeStream stream_label=new RewriteRuleSubtreeStream(adaptor,"rule label");
        RewriteRuleSubtreeStream stream_paren_chunk=new RewriteRuleSubtreeStream(adaptor,"rule paren_chunk");
        RewriteRuleSubtreeStream stream_or_restr_connective=new RewriteRuleSubtreeStream(adaptor,"rule or_restr_connective");

        	boolean isArrow = false;

        try {
            // src/main/resources/org/drools/lang/DRL.g:1160:3: ( label accessor_path ( or_restr_connective | arw= ARROW paren_chunk )? -> {isArrow}? ^( VT_BIND_FIELD label ^( VT_FIELD accessor_path ) ) ( ^( VK_EVAL[$arw] paren_chunk ) )? -> ^( VT_BIND_FIELD label ^( VT_FIELD accessor_path ( or_restr_connective )? ) ) | accessor_path or_restr_connective -> ^( VT_FIELD accessor_path or_restr_connective ) )
            int alt84=2;
            int LA84_0 = input.LA(1);

            if ( (LA84_0==ID) ) {
                int LA84_1 = input.LA(2);

                if ( ((LA84_1>=ID && LA84_1<=DOT)||LA84_1==LEFT_PAREN||(LA84_1>=EQUAL && LA84_1<=NOT_EQUAL)||LA84_1==LEFT_SQUARE) ) {
                    alt84=2;
                }
                else if ( (LA84_1==COLON) ) {
                    alt84=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 84, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 84, 0, input);

                throw nvae;
            }
            switch (alt84) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:1160:5: label accessor_path ( or_restr_connective | arw= ARROW paren_chunk )?
                    {
                    pushFollow(FOLLOW_label_in_field_constraint3890);
                    label228=label();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_label.add(label228.getTree());
                    pushFollow(FOLLOW_accessor_path_in_field_constraint3892);
                    accessor_path229=accessor_path();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_accessor_path.add(accessor_path229.getTree());
                    // src/main/resources/org/drools/lang/DRL.g:1161:3: ( or_restr_connective | arw= ARROW paren_chunk )?
                    int alt83=3;
                    int LA83_0 = input.LA(1);

                    if ( (LA83_0==ID) && ((((validateIdentifierKey(DroolsSoftKeywords.NOT)))||((validateIdentifierKey(DroolsSoftKeywords.IN)))||((isPluggableEvaluator(false)))))) {
                        alt83=1;
                    }
                    else if ( (LA83_0==LEFT_PAREN||(LA83_0>=EQUAL && LA83_0<=NOT_EQUAL)) ) {
                        alt83=1;
                    }
                    else if ( (LA83_0==ARROW) ) {
                        alt83=2;
                    }
                    switch (alt83) {
                        case 1 :
                            // src/main/resources/org/drools/lang/DRL.g:1161:5: or_restr_connective
                            {
                            pushFollow(FOLLOW_or_restr_connective_in_field_constraint3899);
                            or_restr_connective230=or_restr_connective();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_or_restr_connective.add(or_restr_connective230.getTree());

                            }
                            break;
                        case 2 :
                            // src/main/resources/org/drools/lang/DRL.g:1161:27: arw= ARROW paren_chunk
                            {
                            arw=(Token)match(input,ARROW,FOLLOW_ARROW_in_field_constraint3905); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_ARROW.add(arw);

                            if ( state.backtracking==0 ) {
                              	emit(arw, DroolsEditorType.SYMBOL);	
                            }
                            pushFollow(FOLLOW_paren_chunk_in_field_constraint3909);
                            paren_chunk231=paren_chunk();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_paren_chunk.add(paren_chunk231.getTree());
                            if ( state.backtracking==0 ) {
                              isArrow = true;
                            }

                            }
                            break;

                    }



                    // AST REWRITE
                    // elements: label, or_restr_connective, accessor_path, label, paren_chunk, accessor_path
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 1162:3: -> {isArrow}? ^( VT_BIND_FIELD label ^( VT_FIELD accessor_path ) ) ( ^( VK_EVAL[$arw] paren_chunk ) )?
                    if (isArrow) {
                        // src/main/resources/org/drools/lang/DRL.g:1162:17: ^( VT_BIND_FIELD label ^( VT_FIELD accessor_path ) )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(VT_BIND_FIELD, "VT_BIND_FIELD"), root_1);

                        adaptor.addChild(root_1, stream_label.nextTree());
                        // src/main/resources/org/drools/lang/DRL.g:1162:39: ^( VT_FIELD accessor_path )
                        {
                        Object root_2 = (Object)adaptor.nil();
                        root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(VT_FIELD, "VT_FIELD"), root_2);

                        adaptor.addChild(root_2, stream_accessor_path.nextTree());

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                        }
                        // src/main/resources/org/drools/lang/DRL.g:1162:66: ( ^( VK_EVAL[$arw] paren_chunk ) )?
                        if ( stream_paren_chunk.hasNext() ) {
                            // src/main/resources/org/drools/lang/DRL.g:1162:66: ^( VK_EVAL[$arw] paren_chunk )
                            {
                            Object root_1 = (Object)adaptor.nil();
                            root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(VK_EVAL, arw), root_1);

                            adaptor.addChild(root_1, stream_paren_chunk.nextTree());

                            adaptor.addChild(root_0, root_1);
                            }

                        }
                        stream_paren_chunk.reset();

                    }
                    else // 1163:3: -> ^( VT_BIND_FIELD label ^( VT_FIELD accessor_path ( or_restr_connective )? ) )
                    {
                        // src/main/resources/org/drools/lang/DRL.g:1163:6: ^( VT_BIND_FIELD label ^( VT_FIELD accessor_path ( or_restr_connective )? ) )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(VT_BIND_FIELD, "VT_BIND_FIELD"), root_1);

                        adaptor.addChild(root_1, stream_label.nextTree());
                        // src/main/resources/org/drools/lang/DRL.g:1163:28: ^( VT_FIELD accessor_path ( or_restr_connective )? )
                        {
                        Object root_2 = (Object)adaptor.nil();
                        root_2 = (Object)adaptor.becomeRoot((Object)adaptor.create(VT_FIELD, "VT_FIELD"), root_2);

                        adaptor.addChild(root_2, stream_accessor_path.nextTree());
                        // src/main/resources/org/drools/lang/DRL.g:1163:53: ( or_restr_connective )?
                        if ( stream_or_restr_connective.hasNext() ) {
                            adaptor.addChild(root_2, stream_or_restr_connective.nextTree());

                        }
                        stream_or_restr_connective.reset();

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // src/main/resources/org/drools/lang/DRL.g:1164:4: accessor_path or_restr_connective
                    {
                    pushFollow(FOLLOW_accessor_path_in_field_constraint3963);
                    accessor_path232=accessor_path();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_accessor_path.add(accessor_path232.getTree());
                    pushFollow(FOLLOW_or_restr_connective_in_field_constraint3965);
                    or_restr_connective233=or_restr_connective();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_or_restr_connective.add(or_restr_connective233.getTree());


                    // AST REWRITE
                    // elements: accessor_path, or_restr_connective
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (Object)adaptor.nil();
                    // 1165:3: -> ^( VT_FIELD accessor_path or_restr_connective )
                    {
                        // src/main/resources/org/drools/lang/DRL.g:1165:6: ^( VT_FIELD accessor_path or_restr_connective )
                        {
                        Object root_1 = (Object)adaptor.nil();
                        root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(VT_FIELD, "VT_FIELD"), root_1);

                        adaptor.addChild(root_1, stream_accessor_path.nextTree());
                        adaptor.addChild(root_1, stream_or_restr_connective.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "field_constraint"

    public static class label_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "label"
    // src/main/resources/org/drools/lang/DRL.g:1168:1: label : value= ID COLON -> VT_LABEL[$value] ;
    public final DRLParser.label_return label() throws RecognitionException {
        DRLParser.label_return retval = new DRLParser.label_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token value=null;
        Token COLON234=null;

        Object value_tree=null;
        Object COLON234_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // src/main/resources/org/drools/lang/DRL.g:1169:2: (value= ID COLON -> VT_LABEL[$value] )
            // src/main/resources/org/drools/lang/DRL.g:1169:4: value= ID COLON
            {
            value=(Token)match(input,ID,FOLLOW_ID_in_label3990); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(value);

            if ( state.backtracking==0 ) {
              	emit(value, DroolsEditorType.IDENTIFIER_VARIABLE);	
            }
            COLON234=(Token)match(input,COLON,FOLLOW_COLON_in_label3997); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_COLON.add(COLON234);

            if ( state.backtracking==0 ) {
              	emit(COLON234, DroolsEditorType.SYMBOL);	
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1171:3: -> VT_LABEL[$value]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VT_LABEL, value));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "label"

    public static class or_restr_connective_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "or_restr_connective"
    // src/main/resources/org/drools/lang/DRL.g:1174:1: or_restr_connective : and_restr_connective ({...}? => DOUBLE_PIPE and_restr_connective )* ;
    public final DRLParser.or_restr_connective_return or_restr_connective() throws RecognitionException {
        DRLParser.or_restr_connective_return retval = new DRLParser.or_restr_connective_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token DOUBLE_PIPE236=null;
        DRLParser.and_restr_connective_return and_restr_connective235 = null;

        DRLParser.and_restr_connective_return and_restr_connective237 = null;


        Object DOUBLE_PIPE236_tree=null;

        try {
            // src/main/resources/org/drools/lang/DRL.g:1175:2: ( and_restr_connective ({...}? => DOUBLE_PIPE and_restr_connective )* )
            // src/main/resources/org/drools/lang/DRL.g:1175:4: and_restr_connective ({...}? => DOUBLE_PIPE and_restr_connective )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_and_restr_connective_in_or_restr_connective4018);
            and_restr_connective235=and_restr_connective();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, and_restr_connective235.getTree());
            // src/main/resources/org/drools/lang/DRL.g:1175:25: ({...}? => DOUBLE_PIPE and_restr_connective )*
            loop85:
            do {
                int alt85=2;
                int LA85_0 = input.LA(1);

                if ( (LA85_0==DOUBLE_PIPE) ) {
                    int LA85_2 = input.LA(2);

                    if ( (((validateRestr()))) ) {
                        alt85=1;
                    }


                }


                switch (alt85) {
            	case 1 :
            	    // src/main/resources/org/drools/lang/DRL.g:1175:26: {...}? => DOUBLE_PIPE and_restr_connective
            	    {
            	    if ( !(((validateRestr()))) ) {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        throw new FailedPredicateException(input, "or_restr_connective", "(validateRestr())");
            	    }
            	    DOUBLE_PIPE236=(Token)match(input,DOUBLE_PIPE,FOLLOW_DOUBLE_PIPE_in_or_restr_connective4024); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    DOUBLE_PIPE236_tree = (Object)adaptor.create(DOUBLE_PIPE236);
            	    root_0 = (Object)adaptor.becomeRoot(DOUBLE_PIPE236_tree, root_0);
            	    }
            	    if ( state.backtracking==0 ) {
            	      	emit(DOUBLE_PIPE236, DroolsEditorType.SYMBOL);	
            	    }
            	    pushFollow(FOLLOW_and_restr_connective_in_or_restr_connective4032);
            	    and_restr_connective237=and_restr_connective();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, and_restr_connective237.getTree());

            	    }
            	    break;

            	default :
            	    break loop85;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch ( RecognitionException re ) {

            	if (!lookaheadTest){
                    reportError(re);
                    recover(input,re);
                	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
            	} else {
            		throw re;
            	}

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "or_restr_connective"

    public static class and_restr_connective_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "and_restr_connective"
    // src/main/resources/org/drools/lang/DRL.g:1188:1: and_restr_connective : constraint_expression ({...}? => DOUBLE_AMPER constraint_expression )* ;
    public final DRLParser.and_restr_connective_return and_restr_connective() throws RecognitionException {
        DRLParser.and_restr_connective_return retval = new DRLParser.and_restr_connective_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token DOUBLE_AMPER239=null;
        DRLParser.constraint_expression_return constraint_expression238 = null;

        DRLParser.constraint_expression_return constraint_expression240 = null;


        Object DOUBLE_AMPER239_tree=null;

        try {
            // src/main/resources/org/drools/lang/DRL.g:1189:2: ( constraint_expression ({...}? => DOUBLE_AMPER constraint_expression )* )
            // src/main/resources/org/drools/lang/DRL.g:1189:4: constraint_expression ({...}? => DOUBLE_AMPER constraint_expression )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_constraint_expression_in_and_restr_connective4053);
            constraint_expression238=constraint_expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, constraint_expression238.getTree());
            // src/main/resources/org/drools/lang/DRL.g:1189:26: ({...}? => DOUBLE_AMPER constraint_expression )*
            loop86:
            do {
                int alt86=2;
                int LA86_0 = input.LA(1);

                if ( (LA86_0==DOUBLE_AMPER) ) {
                    int LA86_2 = input.LA(2);

                    if ( (((validateRestr()))) ) {
                        alt86=1;
                    }


                }


                switch (alt86) {
            	case 1 :
            	    // src/main/resources/org/drools/lang/DRL.g:1189:27: {...}? => DOUBLE_AMPER constraint_expression
            	    {
            	    if ( !(((validateRestr()))) ) {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        throw new FailedPredicateException(input, "and_restr_connective", "(validateRestr())");
            	    }
            	    DOUBLE_AMPER239=(Token)match(input,DOUBLE_AMPER,FOLLOW_DOUBLE_AMPER_in_and_restr_connective4059); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    DOUBLE_AMPER239_tree = (Object)adaptor.create(DOUBLE_AMPER239);
            	    root_0 = (Object)adaptor.becomeRoot(DOUBLE_AMPER239_tree, root_0);
            	    }
            	    if ( state.backtracking==0 ) {
            	      	emit(DOUBLE_AMPER239, DroolsEditorType.SYMBOL);	
            	    }
            	    pushFollow(FOLLOW_constraint_expression_in_and_restr_connective4066);
            	    constraint_expression240=constraint_expression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, constraint_expression240.getTree());

            	    }
            	    break;

            	default :
            	    break loop86;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch ( RecognitionException re ) {

            	if (!lookaheadTest){
                    reportError(re);
                    recover(input,re);
                	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
            	} else {
            		throw re;
            	}

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "and_restr_connective"

    public static class constraint_expression_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "constraint_expression"
    // src/main/resources/org/drools/lang/DRL.g:1202:1: constraint_expression options {k=3; } : ( compound_operator | simple_operator | LEFT_PAREN or_restr_connective RIGHT_PAREN );
    public final DRLParser.constraint_expression_return constraint_expression() throws RecognitionException {
        DRLParser.constraint_expression_return retval = new DRLParser.constraint_expression_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LEFT_PAREN243=null;
        Token RIGHT_PAREN245=null;
        DRLParser.compound_operator_return compound_operator241 = null;

        DRLParser.simple_operator_return simple_operator242 = null;

        DRLParser.or_restr_connective_return or_restr_connective244 = null;


        Object LEFT_PAREN243_tree=null;
        Object RIGHT_PAREN245_tree=null;

        try {
            // src/main/resources/org/drools/lang/DRL.g:1205:3: ( compound_operator | simple_operator | LEFT_PAREN or_restr_connective RIGHT_PAREN )
            int alt87=3;
            alt87 = dfa87.predict(input);
            switch (alt87) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:1205:5: compound_operator
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_compound_operator_in_constraint_expression4094);
                    compound_operator241=compound_operator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, compound_operator241.getTree());

                    }
                    break;
                case 2 :
                    // src/main/resources/org/drools/lang/DRL.g:1206:4: simple_operator
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_simple_operator_in_constraint_expression4099);
                    simple_operator242=simple_operator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, simple_operator242.getTree());

                    }
                    break;
                case 3 :
                    // src/main/resources/org/drools/lang/DRL.g:1207:4: LEFT_PAREN or_restr_connective RIGHT_PAREN
                    {
                    root_0 = (Object)adaptor.nil();

                    LEFT_PAREN243=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_constraint_expression4104); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      	emit(LEFT_PAREN243, DroolsEditorType.SYMBOL);	
                    }
                    pushFollow(FOLLOW_or_restr_connective_in_constraint_expression4113);
                    or_restr_connective244=or_restr_connective();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, or_restr_connective244.getTree());
                    RIGHT_PAREN245=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_constraint_expression4118); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RIGHT_PAREN245_tree = (Object)adaptor.create(RIGHT_PAREN245);
                    adaptor.addChild(root_0, RIGHT_PAREN245_tree);
                    }
                    if ( state.backtracking==0 ) {
                      	emit(RIGHT_PAREN245, DroolsEditorType.SYMBOL);	
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch ( RecognitionException re ) {

            	if (!lookaheadTest){
                    reportError(re);
                    recover(input,re);
                	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
            	} else {
            		throw re;
            	}

        }
        finally {

            	if (isEditorInterfaceEnabled && input.LA(2) == EOF && input.LA(1) == ID) {
            		emit(true, Location.LOCATION_LHS_INSIDE_CONDITION_OPERATOR);
            		emit(input.LT(1), DroolsEditorType.KEYWORD);
            		input.consume();
            		emit(true, Location.LOCATION_LHS_INSIDE_CONDITION_ARGUMENT);
            	} else if (isEditorInterfaceEnabled && input.LA(3) == EOF && input.LA(1) == ID && 
            				input.LA(2) == ID && validateLT(1, DroolsSoftKeywords.NOT)) {
            		emit(true, Location.LOCATION_LHS_INSIDE_CONDITION_OPERATOR);
            		emit(input.LT(1), DroolsEditorType.KEYWORD);
            		emit(input.LT(2), DroolsEditorType.KEYWORD);
            		input.consume();
            		input.consume();
            		emit(true, Location.LOCATION_LHS_INSIDE_CONDITION_ARGUMENT);
            	} else if (isEditorInterfaceEnabled && input.LA(3) == EOF  && input.LA(1) == ID && validateLT(1, DroolsSoftKeywords.IN)) {
            		emit(true, Location.LOCATION_LHS_INSIDE_CONDITION_OPERATOR);
            		emit(input.LT(1), DroolsEditorType.KEYWORD);
            		emit(input.LT(2), DroolsEditorType.SYMBOL);
            		input.consume();
            		input.consume();
            		emit(true, Location.LOCATION_LHS_INSIDE_CONDITION_ARGUMENT);
            	} else if (isEditorInterfaceEnabled && input.LA(3) == EOF && input.LA(1) == ID) {
            		emit(true, Location.LOCATION_LHS_INSIDE_CONDITION_OPERATOR);
            		emit(input.LT(1), DroolsEditorType.KEYWORD);
            		emit(input.LT(2), DroolsEditorType.IDENTIFIER);
            		input.consume();
            		input.consume();
            		if (input.get(input.index() - 1).getType() == WS){
            			emit(true, Location.LOCATION_LHS_INSIDE_CONDITION_END);
            		}
            	}

        }
        return retval;
    }
    // $ANTLR end "constraint_expression"

    public static class simple_operator_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "simple_operator"
    // src/main/resources/org/drools/lang/DRL.g:1253:1: simple_operator : ( EQUAL | GREATER | GREATER_EQUAL | LESS | LESS_EQUAL | NOT_EQUAL | ( not_key )? ( operator_key ( square_chunk )? ) ) expression_value ;
    public final DRLParser.simple_operator_return simple_operator() throws RecognitionException {
        DRLParser.simple_operator_return retval = new DRLParser.simple_operator_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EQUAL246=null;
        Token GREATER247=null;
        Token GREATER_EQUAL248=null;
        Token LESS249=null;
        Token LESS_EQUAL250=null;
        Token NOT_EQUAL251=null;
        DRLParser.not_key_return not_key252 = null;

        DRLParser.operator_key_return operator_key253 = null;

        DRLParser.square_chunk_return square_chunk254 = null;

        DRLParser.expression_value_return expression_value255 = null;


        Object EQUAL246_tree=null;
        Object GREATER247_tree=null;
        Object GREATER_EQUAL248_tree=null;
        Object LESS249_tree=null;
        Object LESS_EQUAL250_tree=null;
        Object NOT_EQUAL251_tree=null;

        if ( state.backtracking==0 ) emit(Location.LOCATION_LHS_INSIDE_CONDITION_OPERATOR);
        try {
            // src/main/resources/org/drools/lang/DRL.g:1255:2: ( ( EQUAL | GREATER | GREATER_EQUAL | LESS | LESS_EQUAL | NOT_EQUAL | ( not_key )? ( operator_key ( square_chunk )? ) ) expression_value )
            // src/main/resources/org/drools/lang/DRL.g:1256:2: ( EQUAL | GREATER | GREATER_EQUAL | LESS | LESS_EQUAL | NOT_EQUAL | ( not_key )? ( operator_key ( square_chunk )? ) ) expression_value
            {
            root_0 = (Object)adaptor.nil();

            // src/main/resources/org/drools/lang/DRL.g:1256:2: ( EQUAL | GREATER | GREATER_EQUAL | LESS | LESS_EQUAL | NOT_EQUAL | ( not_key )? ( operator_key ( square_chunk )? ) )
            int alt90=7;
            int LA90_0 = input.LA(1);

            if ( (LA90_0==EQUAL) ) {
                alt90=1;
            }
            else if ( (LA90_0==GREATER) ) {
                alt90=2;
            }
            else if ( (LA90_0==GREATER_EQUAL) ) {
                alt90=3;
            }
            else if ( (LA90_0==LESS) ) {
                alt90=4;
            }
            else if ( (LA90_0==LESS_EQUAL) ) {
                alt90=5;
            }
            else if ( (LA90_0==NOT_EQUAL) ) {
                alt90=6;
            }
            else if ( (LA90_0==ID) && ((((validateIdentifierKey(DroolsSoftKeywords.NOT)))||((isPluggableEvaluator(false)))))) {
                alt90=7;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 90, 0, input);

                throw nvae;
            }
            switch (alt90) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:1257:3: EQUAL
                    {
                    EQUAL246=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_simple_operator4153); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    EQUAL246_tree = (Object)adaptor.create(EQUAL246);
                    root_0 = (Object)adaptor.becomeRoot(EQUAL246_tree, root_0);
                    }
                    if ( state.backtracking==0 ) {
                      	emit(EQUAL246, DroolsEditorType.SYMBOL);	
                    }

                    }
                    break;
                case 2 :
                    // src/main/resources/org/drools/lang/DRL.g:1258:4: GREATER
                    {
                    GREATER247=(Token)match(input,GREATER,FOLLOW_GREATER_in_simple_operator4161); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    GREATER247_tree = (Object)adaptor.create(GREATER247);
                    root_0 = (Object)adaptor.becomeRoot(GREATER247_tree, root_0);
                    }
                    if ( state.backtracking==0 ) {
                      	emit(GREATER247, DroolsEditorType.SYMBOL);	
                    }

                    }
                    break;
                case 3 :
                    // src/main/resources/org/drools/lang/DRL.g:1259:4: GREATER_EQUAL
                    {
                    GREATER_EQUAL248=(Token)match(input,GREATER_EQUAL,FOLLOW_GREATER_EQUAL_in_simple_operator4169); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    GREATER_EQUAL248_tree = (Object)adaptor.create(GREATER_EQUAL248);
                    root_0 = (Object)adaptor.becomeRoot(GREATER_EQUAL248_tree, root_0);
                    }
                    if ( state.backtracking==0 ) {
                      	emit(GREATER_EQUAL248, DroolsEditorType.SYMBOL);	
                    }

                    }
                    break;
                case 4 :
                    // src/main/resources/org/drools/lang/DRL.g:1260:4: LESS
                    {
                    LESS249=(Token)match(input,LESS,FOLLOW_LESS_in_simple_operator4177); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LESS249_tree = (Object)adaptor.create(LESS249);
                    root_0 = (Object)adaptor.becomeRoot(LESS249_tree, root_0);
                    }
                    if ( state.backtracking==0 ) {
                      	emit(LESS249, DroolsEditorType.SYMBOL);	
                    }

                    }
                    break;
                case 5 :
                    // src/main/resources/org/drools/lang/DRL.g:1261:4: LESS_EQUAL
                    {
                    LESS_EQUAL250=(Token)match(input,LESS_EQUAL,FOLLOW_LESS_EQUAL_in_simple_operator4185); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LESS_EQUAL250_tree = (Object)adaptor.create(LESS_EQUAL250);
                    root_0 = (Object)adaptor.becomeRoot(LESS_EQUAL250_tree, root_0);
                    }
                    if ( state.backtracking==0 ) {
                      	emit(LESS_EQUAL250, DroolsEditorType.SYMBOL);	
                    }

                    }
                    break;
                case 6 :
                    // src/main/resources/org/drools/lang/DRL.g:1262:4: NOT_EQUAL
                    {
                    NOT_EQUAL251=(Token)match(input,NOT_EQUAL,FOLLOW_NOT_EQUAL_in_simple_operator4193); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NOT_EQUAL251_tree = (Object)adaptor.create(NOT_EQUAL251);
                    root_0 = (Object)adaptor.becomeRoot(NOT_EQUAL251_tree, root_0);
                    }
                    if ( state.backtracking==0 ) {
                      	emit(NOT_EQUAL251, DroolsEditorType.SYMBOL);	
                    }

                    }
                    break;
                case 7 :
                    // src/main/resources/org/drools/lang/DRL.g:1263:4: ( not_key )? ( operator_key ( square_chunk )? )
                    {
                    // src/main/resources/org/drools/lang/DRL.g:1263:4: ( not_key )?
                    int alt88=2;
                    int LA88_0 = input.LA(1);

                    if ( (LA88_0==ID) && ((((validateIdentifierKey(DroolsSoftKeywords.NOT)))||((isPluggableEvaluator(false)))))) {
                        int LA88_1 = input.LA(2);

                        if ( (((validateIdentifierKey(DroolsSoftKeywords.NOT)))) ) {
                            alt88=1;
                        }
                    }
                    switch (alt88) {
                        case 1 :
                            // src/main/resources/org/drools/lang/DRL.g:1263:4: not_key
                            {
                            pushFollow(FOLLOW_not_key_in_simple_operator4201);
                            not_key252=not_key();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, not_key252.getTree());

                            }
                            break;

                    }

                    // src/main/resources/org/drools/lang/DRL.g:1264:3: ( operator_key ( square_chunk )? )
                    // src/main/resources/org/drools/lang/DRL.g:1264:5: operator_key ( square_chunk )?
                    {
                    pushFollow(FOLLOW_operator_key_in_simple_operator4208);
                    operator_key253=operator_key();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(operator_key253.getTree(), root_0);
                    // src/main/resources/org/drools/lang/DRL.g:1264:19: ( square_chunk )?
                    int alt89=2;
                    int LA89_0 = input.LA(1);

                    if ( (LA89_0==LEFT_SQUARE) ) {
                        alt89=1;
                    }
                    switch (alt89) {
                        case 1 :
                            // src/main/resources/org/drools/lang/DRL.g:1264:19: square_chunk
                            {
                            pushFollow(FOLLOW_square_chunk_in_simple_operator4211);
                            square_chunk254=square_chunk();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, square_chunk254.getTree());

                            }
                            break;

                    }


                    }


                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              	emit(Location.LOCATION_LHS_INSIDE_CONDITION_ARGUMENT);	
            }
            pushFollow(FOLLOW_expression_value_in_simple_operator4223);
            expression_value255=expression_value();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expression_value255.getTree());

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "simple_operator"

    public static class compound_operator_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "compound_operator"
    // src/main/resources/org/drools/lang/DRL.g:1271:1: compound_operator : ( in_key | not_key in_key ) LEFT_PAREN expression_value ( COMMA expression_value )* RIGHT_PAREN ;
    public final DRLParser.compound_operator_return compound_operator() throws RecognitionException {
        DRLParser.compound_operator_return retval = new DRLParser.compound_operator_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LEFT_PAREN259=null;
        Token COMMA261=null;
        Token RIGHT_PAREN263=null;
        DRLParser.in_key_return in_key256 = null;

        DRLParser.not_key_return not_key257 = null;

        DRLParser.in_key_return in_key258 = null;

        DRLParser.expression_value_return expression_value260 = null;

        DRLParser.expression_value_return expression_value262 = null;


        Object LEFT_PAREN259_tree=null;
        Object COMMA261_tree=null;
        Object RIGHT_PAREN263_tree=null;

         if ( state.backtracking==0 ) emit(Location.LOCATION_LHS_INSIDE_CONDITION_OPERATOR); 
        try {
            // src/main/resources/org/drools/lang/DRL.g:1273:2: ( ( in_key | not_key in_key ) LEFT_PAREN expression_value ( COMMA expression_value )* RIGHT_PAREN )
            // src/main/resources/org/drools/lang/DRL.g:1274:2: ( in_key | not_key in_key ) LEFT_PAREN expression_value ( COMMA expression_value )* RIGHT_PAREN
            {
            root_0 = (Object)adaptor.nil();

            // src/main/resources/org/drools/lang/DRL.g:1274:2: ( in_key | not_key in_key )
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( (LA91_0==ID) && ((((validateIdentifierKey(DroolsSoftKeywords.NOT)))||((validateIdentifierKey(DroolsSoftKeywords.IN)))))) {
                int LA91_1 = input.LA(2);

                if ( (LA91_1==LEFT_PAREN) && (((validateIdentifierKey(DroolsSoftKeywords.IN))))) {
                    alt91=1;
                }
                else if ( (LA91_1==ID) && (((validateIdentifierKey(DroolsSoftKeywords.NOT))))) {
                    alt91=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 91, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 91, 0, input);

                throw nvae;
            }
            switch (alt91) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:1274:4: in_key
                    {
                    pushFollow(FOLLOW_in_key_in_compound_operator4245);
                    in_key256=in_key();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(in_key256.getTree(), root_0);

                    }
                    break;
                case 2 :
                    // src/main/resources/org/drools/lang/DRL.g:1274:14: not_key in_key
                    {
                    pushFollow(FOLLOW_not_key_in_compound_operator4250);
                    not_key257=not_key();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, not_key257.getTree());
                    pushFollow(FOLLOW_in_key_in_compound_operator4252);
                    in_key258=in_key();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) root_0 = (Object)adaptor.becomeRoot(in_key258.getTree(), root_0);

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              	emit(Location.LOCATION_LHS_INSIDE_CONDITION_ARGUMENT);	
            }
            LEFT_PAREN259=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_compound_operator4263); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
              	emit(LEFT_PAREN259, DroolsEditorType.SYMBOL);	
            }
            pushFollow(FOLLOW_expression_value_in_compound_operator4271);
            expression_value260=expression_value();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expression_value260.getTree());
            // src/main/resources/org/drools/lang/DRL.g:1277:21: ( COMMA expression_value )*
            loop92:
            do {
                int alt92=2;
                int LA92_0 = input.LA(1);

                if ( (LA92_0==COMMA) ) {
                    alt92=1;
                }


                switch (alt92) {
            	case 1 :
            	    // src/main/resources/org/drools/lang/DRL.g:1277:23: COMMA expression_value
            	    {
            	    COMMA261=(Token)match(input,COMMA,FOLLOW_COMMA_in_compound_operator4275); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	      	emit(COMMA261, DroolsEditorType.SYMBOL);	
            	    }
            	    pushFollow(FOLLOW_expression_value_in_compound_operator4280);
            	    expression_value262=expression_value();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, expression_value262.getTree());

            	    }
            	    break;

            	default :
            	    break loop92;
                }
            } while (true);

            RIGHT_PAREN263=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_compound_operator4288); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RIGHT_PAREN263_tree = (Object)adaptor.create(RIGHT_PAREN263);
            adaptor.addChild(root_0, RIGHT_PAREN263_tree);
            }
            if ( state.backtracking==0 ) {
              	emit(RIGHT_PAREN263, DroolsEditorType.SYMBOL);	
            }
            if ( state.backtracking==0 ) {
              	emit(Location.LOCATION_LHS_INSIDE_CONDITION_END);	
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
             
            	if (isEditorInterfaceEnabled && input.LA(2) == EOF && input.LA(1) == DOUBLE_PIPE) {
            		emit(input.LT(1), DroolsEditorType.SYMBOL);
            		input.consume();
            		emit(true, Location.LOCATION_LHS_INSIDE_CONDITION_OPERATOR);
            	}	
        }
        return retval;
    }
    // $ANTLR end "compound_operator"

    public static class operator_key_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "operator_key"
    // src/main/resources/org/drools/lang/DRL.g:1288:1: operator_key : {...}? =>id= ID -> VK_OPERATOR[$id] ;
    public final DRLParser.operator_key_return operator_key() throws RecognitionException {
        DRLParser.operator_key_return retval = new DRLParser.operator_key_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;

        Object id_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // src/main/resources/org/drools/lang/DRL.g:1289:2: ({...}? =>id= ID -> VK_OPERATOR[$id] )
            // src/main/resources/org/drools/lang/DRL.g:1289:9: {...}? =>id= ID
            {
            if ( !(((isPluggableEvaluator(false)))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "operator_key", "(isPluggableEvaluator(false))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_operator_key4319); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id);

            if ( state.backtracking==0 ) {
               emit(id, DroolsEditorType.IDENTIFIER); 
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1291:9: -> VK_OPERATOR[$id]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VK_OPERATOR, id));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "operator_key"

    public static class neg_operator_key_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "neg_operator_key"
    // src/main/resources/org/drools/lang/DRL.g:1294:1: neg_operator_key : {...}? =>id= ID -> VK_OPERATOR[$id] ;
    public final DRLParser.neg_operator_key_return neg_operator_key() throws RecognitionException {
        DRLParser.neg_operator_key_return retval = new DRLParser.neg_operator_key_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;

        Object id_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // src/main/resources/org/drools/lang/DRL.g:1295:2: ({...}? =>id= ID -> VK_OPERATOR[$id] )
            // src/main/resources/org/drools/lang/DRL.g:1295:9: {...}? =>id= ID
            {
            if ( !(((isPluggableEvaluator(true)))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "neg_operator_key", "(isPluggableEvaluator(true))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_neg_operator_key4364); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id);

            if ( state.backtracking==0 ) {
               emit(id, DroolsEditorType.IDENTIFIER); 
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1297:9: -> VK_OPERATOR[$id]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VK_OPERATOR, id));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "neg_operator_key"

    public static class expression_value_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "expression_value"
    // src/main/resources/org/drools/lang/DRL.g:1300:1: expression_value : ( accessor_path | literal_constraint | paren_chunk ) ;
    public final DRLParser.expression_value_return expression_value() throws RecognitionException {
        DRLParser.expression_value_return retval = new DRLParser.expression_value_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        DRLParser.accessor_path_return accessor_path264 = null;

        DRLParser.literal_constraint_return literal_constraint265 = null;

        DRLParser.paren_chunk_return paren_chunk266 = null;



        try {
            // src/main/resources/org/drools/lang/DRL.g:1301:2: ( ( accessor_path | literal_constraint | paren_chunk ) )
            // src/main/resources/org/drools/lang/DRL.g:1301:4: ( accessor_path | literal_constraint | paren_chunk )
            {
            root_0 = (Object)adaptor.nil();

            // src/main/resources/org/drools/lang/DRL.g:1301:4: ( accessor_path | literal_constraint | paren_chunk )
            int alt93=3;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt93=1;
                }
                break;
            case STRING:
            case BOOL:
            case INT:
            case FLOAT:
            case NULL:
                {
                alt93=2;
                }
                break;
            case LEFT_PAREN:
                {
                alt93=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 93, 0, input);

                throw nvae;
            }

            switch (alt93) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:1301:5: accessor_path
                    {
                    pushFollow(FOLLOW_accessor_path_in_expression_value4401);
                    accessor_path264=accessor_path();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, accessor_path264.getTree());

                    }
                    break;
                case 2 :
                    // src/main/resources/org/drools/lang/DRL.g:1302:4: literal_constraint
                    {
                    pushFollow(FOLLOW_literal_constraint_in_expression_value4406);
                    literal_constraint265=literal_constraint();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, literal_constraint265.getTree());

                    }
                    break;
                case 3 :
                    // src/main/resources/org/drools/lang/DRL.g:1303:4: paren_chunk
                    {
                    pushFollow(FOLLOW_paren_chunk_in_expression_value4412);
                    paren_chunk266=paren_chunk();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, paren_chunk266.getTree());

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              	if (isEditorInterfaceEnabled && !(input.LA(1) == EOF && input.get(input.index() - 1).getType() != WS))
              			emit(Location.LOCATION_LHS_INSIDE_CONDITION_END);	
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
             
            	if (isEditorInterfaceEnabled && input.LA(2) == EOF) {
            		if (input.LA(1) == DOUBLE_PIPE) {
            			emit(input.LT(1), DroolsEditorType.SYMBOL);
            			input.consume();
            			emit(true, Location.LOCATION_LHS_INSIDE_CONDITION_OPERATOR);
            		}
            	}

        }
        return retval;
    }
    // $ANTLR end "expression_value"

    public static class literal_constraint_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "literal_constraint"
    // src/main/resources/org/drools/lang/DRL.g:1317:1: literal_constraint : ( STRING | INT | FLOAT | BOOL | NULL );
    public final DRLParser.literal_constraint_return literal_constraint() throws RecognitionException {
        DRLParser.literal_constraint_return retval = new DRLParser.literal_constraint_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token STRING267=null;
        Token INT268=null;
        Token FLOAT269=null;
        Token BOOL270=null;
        Token NULL271=null;

        Object STRING267_tree=null;
        Object INT268_tree=null;
        Object FLOAT269_tree=null;
        Object BOOL270_tree=null;
        Object NULL271_tree=null;

        try {
            // src/main/resources/org/drools/lang/DRL.g:1318:2: ( STRING | INT | FLOAT | BOOL | NULL )
            int alt94=5;
            switch ( input.LA(1) ) {
            case STRING:
                {
                alt94=1;
                }
                break;
            case INT:
                {
                alt94=2;
                }
                break;
            case FLOAT:
                {
                alt94=3;
                }
                break;
            case BOOL:
                {
                alt94=4;
                }
                break;
            case NULL:
                {
                alt94=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 94, 0, input);

                throw nvae;
            }

            switch (alt94) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:1318:4: STRING
                    {
                    root_0 = (Object)adaptor.nil();

                    STRING267=(Token)match(input,STRING,FOLLOW_STRING_in_literal_constraint4431); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STRING267_tree = (Object)adaptor.create(STRING267);
                    adaptor.addChild(root_0, STRING267_tree);
                    }
                    if ( state.backtracking==0 ) {
                      	emit(STRING267, DroolsEditorType.STRING_CONST);	
                    }

                    }
                    break;
                case 2 :
                    // src/main/resources/org/drools/lang/DRL.g:1319:4: INT
                    {
                    root_0 = (Object)adaptor.nil();

                    INT268=(Token)match(input,INT,FOLLOW_INT_in_literal_constraint4438); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    INT268_tree = (Object)adaptor.create(INT268);
                    adaptor.addChild(root_0, INT268_tree);
                    }
                    if ( state.backtracking==0 ) {
                      	emit(INT268, DroolsEditorType.NUMERIC_CONST);	
                    }

                    }
                    break;
                case 3 :
                    // src/main/resources/org/drools/lang/DRL.g:1320:4: FLOAT
                    {
                    root_0 = (Object)adaptor.nil();

                    FLOAT269=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_literal_constraint4445); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FLOAT269_tree = (Object)adaptor.create(FLOAT269);
                    adaptor.addChild(root_0, FLOAT269_tree);
                    }
                    if ( state.backtracking==0 ) {
                      	emit(FLOAT269, DroolsEditorType.NUMERIC_CONST);	
                    }

                    }
                    break;
                case 4 :
                    // src/main/resources/org/drools/lang/DRL.g:1321:4: BOOL
                    {
                    root_0 = (Object)adaptor.nil();

                    BOOL270=(Token)match(input,BOOL,FOLLOW_BOOL_in_literal_constraint4452); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    BOOL270_tree = (Object)adaptor.create(BOOL270);
                    adaptor.addChild(root_0, BOOL270_tree);
                    }
                    if ( state.backtracking==0 ) {
                      	emit(BOOL270, DroolsEditorType.BOOLEAN_CONST);	
                    }

                    }
                    break;
                case 5 :
                    // src/main/resources/org/drools/lang/DRL.g:1322:4: NULL
                    {
                    root_0 = (Object)adaptor.nil();

                    NULL271=(Token)match(input,NULL,FOLLOW_NULL_in_literal_constraint4459); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NULL271_tree = (Object)adaptor.create(NULL271);
                    adaptor.addChild(root_0, NULL271_tree);
                    }
                    if ( state.backtracking==0 ) {
                      	emit(NULL271, DroolsEditorType.NULL_CONST);	
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "literal_constraint"

    public static class pattern_type_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "pattern_type"
    // src/main/resources/org/drools/lang/DRL.g:1325:1: pattern_type : id+= ID (id+= DOT id+= ID )* ( dimension_definition )* -> ^( VT_PATTERN_TYPE ( ID )+ ( dimension_definition )* ) ;
    public final DRLParser.pattern_type_return pattern_type() throws RecognitionException {
        DRLParser.pattern_type_return retval = new DRLParser.pattern_type_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;
        List list_id=null;
        DRLParser.dimension_definition_return dimension_definition272 = null;


        Object id_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_DOT=new RewriteRuleTokenStream(adaptor,"token DOT");
        RewriteRuleSubtreeStream stream_dimension_definition=new RewriteRuleSubtreeStream(adaptor,"rule dimension_definition");
        try {
            // src/main/resources/org/drools/lang/DRL.g:1326:2: (id+= ID (id+= DOT id+= ID )* ( dimension_definition )* -> ^( VT_PATTERN_TYPE ( ID )+ ( dimension_definition )* ) )
            // src/main/resources/org/drools/lang/DRL.g:1326:4: id+= ID (id+= DOT id+= ID )* ( dimension_definition )*
            {
            id=(Token)match(input,ID,FOLLOW_ID_in_pattern_type4474); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id);

            if (list_id==null) list_id=new ArrayList();
            list_id.add(id);

            // src/main/resources/org/drools/lang/DRL.g:1326:11: (id+= DOT id+= ID )*
            loop95:
            do {
                int alt95=2;
                int LA95_0 = input.LA(1);

                if ( (LA95_0==DOT) ) {
                    alt95=1;
                }


                switch (alt95) {
            	case 1 :
            	    // src/main/resources/org/drools/lang/DRL.g:1326:13: id+= DOT id+= ID
            	    {
            	    id=(Token)match(input,DOT,FOLLOW_DOT_in_pattern_type4480); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_DOT.add(id);

            	    if (list_id==null) list_id=new ArrayList();
            	    list_id.add(id);

            	    id=(Token)match(input,ID,FOLLOW_ID_in_pattern_type4484); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_ID.add(id);

            	    if (list_id==null) list_id=new ArrayList();
            	    list_id.add(id);


            	    }
            	    break;

            	default :
            	    break loop95;
                }
            } while (true);

            if ( state.backtracking==0 ) {
              	emit(list_id, DroolsEditorType.IDENTIFIER);
              		setParaphrasesValue(DroolsParaphraseTypes.PATTERN, buildStringFromTokens(list_id));	
            }
            // src/main/resources/org/drools/lang/DRL.g:1329:6: ( dimension_definition )*
            loop96:
            do {
                int alt96=2;
                int LA96_0 = input.LA(1);

                if ( (LA96_0==LEFT_SQUARE) ) {
                    alt96=1;
                }


                switch (alt96) {
            	case 1 :
            	    // src/main/resources/org/drools/lang/DRL.g:1329:6: dimension_definition
            	    {
            	    pushFollow(FOLLOW_dimension_definition_in_pattern_type4499);
            	    dimension_definition272=dimension_definition();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_dimension_definition.add(dimension_definition272.getTree());

            	    }
            	    break;

            	default :
            	    break loop96;
                }
            } while (true);



            // AST REWRITE
            // elements: ID, dimension_definition
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1330:3: -> ^( VT_PATTERN_TYPE ( ID )+ ( dimension_definition )* )
            {
                // src/main/resources/org/drools/lang/DRL.g:1330:6: ^( VT_PATTERN_TYPE ( ID )+ ( dimension_definition )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(VT_PATTERN_TYPE, "VT_PATTERN_TYPE"), root_1);

                if ( !(stream_ID.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_ID.hasNext() ) {
                    adaptor.addChild(root_1, stream_ID.nextNode());

                }
                stream_ID.reset();
                // src/main/resources/org/drools/lang/DRL.g:1330:28: ( dimension_definition )*
                while ( stream_dimension_definition.hasNext() ) {
                    adaptor.addChild(root_1, stream_dimension_definition.nextTree());

                }
                stream_dimension_definition.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "pattern_type"

    public static class data_type_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "data_type"
    // src/main/resources/org/drools/lang/DRL.g:1333:1: data_type : id+= ID (id+= DOT id+= ID )* ( dimension_definition )* -> ^( VT_DATA_TYPE ( ID )+ ( dimension_definition )* ) ;
    public final DRLParser.data_type_return data_type() throws RecognitionException {
        DRLParser.data_type_return retval = new DRLParser.data_type_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;
        List list_id=null;
        DRLParser.dimension_definition_return dimension_definition273 = null;


        Object id_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_DOT=new RewriteRuleTokenStream(adaptor,"token DOT");
        RewriteRuleSubtreeStream stream_dimension_definition=new RewriteRuleSubtreeStream(adaptor,"rule dimension_definition");
        try {
            // src/main/resources/org/drools/lang/DRL.g:1334:2: (id+= ID (id+= DOT id+= ID )* ( dimension_definition )* -> ^( VT_DATA_TYPE ( ID )+ ( dimension_definition )* ) )
            // src/main/resources/org/drools/lang/DRL.g:1334:4: id+= ID (id+= DOT id+= ID )* ( dimension_definition )*
            {
            id=(Token)match(input,ID,FOLLOW_ID_in_data_type4527); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id);

            if (list_id==null) list_id=new ArrayList();
            list_id.add(id);

            // src/main/resources/org/drools/lang/DRL.g:1334:11: (id+= DOT id+= ID )*
            loop97:
            do {
                int alt97=2;
                int LA97_0 = input.LA(1);

                if ( (LA97_0==DOT) ) {
                    alt97=1;
                }


                switch (alt97) {
            	case 1 :
            	    // src/main/resources/org/drools/lang/DRL.g:1334:13: id+= DOT id+= ID
            	    {
            	    id=(Token)match(input,DOT,FOLLOW_DOT_in_data_type4533); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_DOT.add(id);

            	    if (list_id==null) list_id=new ArrayList();
            	    list_id.add(id);

            	    id=(Token)match(input,ID,FOLLOW_ID_in_data_type4537); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_ID.add(id);

            	    if (list_id==null) list_id=new ArrayList();
            	    list_id.add(id);


            	    }
            	    break;

            	default :
            	    break loop97;
                }
            } while (true);

            // src/main/resources/org/drools/lang/DRL.g:1334:31: ( dimension_definition )*
            loop98:
            do {
                int alt98=2;
                int LA98_0 = input.LA(1);

                if ( (LA98_0==LEFT_SQUARE) ) {
                    alt98=1;
                }


                switch (alt98) {
            	case 1 :
            	    // src/main/resources/org/drools/lang/DRL.g:1334:31: dimension_definition
            	    {
            	    pushFollow(FOLLOW_dimension_definition_in_data_type4542);
            	    dimension_definition273=dimension_definition();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_dimension_definition.add(dimension_definition273.getTree());

            	    }
            	    break;

            	default :
            	    break loop98;
                }
            } while (true);

            if ( state.backtracking==0 ) {
              	emit(list_id, DroolsEditorType.IDENTIFIER);	
            }


            // AST REWRITE
            // elements: ID, dimension_definition
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1336:3: -> ^( VT_DATA_TYPE ( ID )+ ( dimension_definition )* )
            {
                // src/main/resources/org/drools/lang/DRL.g:1336:6: ^( VT_DATA_TYPE ( ID )+ ( dimension_definition )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(VT_DATA_TYPE, "VT_DATA_TYPE"), root_1);

                if ( !(stream_ID.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_ID.hasNext() ) {
                    adaptor.addChild(root_1, stream_ID.nextNode());

                }
                stream_ID.reset();
                // src/main/resources/org/drools/lang/DRL.g:1336:25: ( dimension_definition )*
                while ( stream_dimension_definition.hasNext() ) {
                    adaptor.addChild(root_1, stream_dimension_definition.nextTree());

                }
                stream_dimension_definition.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "data_type"

    public static class dimension_definition_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "dimension_definition"
    // src/main/resources/org/drools/lang/DRL.g:1339:1: dimension_definition : LEFT_SQUARE RIGHT_SQUARE ;
    public final DRLParser.dimension_definition_return dimension_definition() throws RecognitionException {
        DRLParser.dimension_definition_return retval = new DRLParser.dimension_definition_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LEFT_SQUARE274=null;
        Token RIGHT_SQUARE275=null;

        Object LEFT_SQUARE274_tree=null;
        Object RIGHT_SQUARE275_tree=null;

        try {
            // src/main/resources/org/drools/lang/DRL.g:1340:2: ( LEFT_SQUARE RIGHT_SQUARE )
            // src/main/resources/org/drools/lang/DRL.g:1340:4: LEFT_SQUARE RIGHT_SQUARE
            {
            root_0 = (Object)adaptor.nil();

            LEFT_SQUARE274=(Token)match(input,LEFT_SQUARE,FOLLOW_LEFT_SQUARE_in_dimension_definition4571); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            LEFT_SQUARE274_tree = (Object)adaptor.create(LEFT_SQUARE274);
            adaptor.addChild(root_0, LEFT_SQUARE274_tree);
            }
            if ( state.backtracking==0 ) {
              	emit(LEFT_SQUARE274, DroolsEditorType.SYMBOL);	
            }
            RIGHT_SQUARE275=(Token)match(input,RIGHT_SQUARE,FOLLOW_RIGHT_SQUARE_in_dimension_definition4578); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            RIGHT_SQUARE275_tree = (Object)adaptor.create(RIGHT_SQUARE275);
            adaptor.addChild(root_0, RIGHT_SQUARE275_tree);
            }
            if ( state.backtracking==0 ) {
              	emit(RIGHT_SQUARE275, DroolsEditorType.SYMBOL);	
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "dimension_definition"

    public static class accessor_path_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "accessor_path"
    // src/main/resources/org/drools/lang/DRL.g:1344:1: accessor_path : accessor_element ( DOT accessor_element )* -> ^( VT_ACCESSOR_PATH ( accessor_element )+ ) ;
    public final DRLParser.accessor_path_return accessor_path() throws RecognitionException {
        DRLParser.accessor_path_return retval = new DRLParser.accessor_path_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token DOT277=null;
        DRLParser.accessor_element_return accessor_element276 = null;

        DRLParser.accessor_element_return accessor_element278 = null;


        Object DOT277_tree=null;
        RewriteRuleTokenStream stream_DOT=new RewriteRuleTokenStream(adaptor,"token DOT");
        RewriteRuleSubtreeStream stream_accessor_element=new RewriteRuleSubtreeStream(adaptor,"rule accessor_element");
        try {
            // src/main/resources/org/drools/lang/DRL.g:1345:2: ( accessor_element ( DOT accessor_element )* -> ^( VT_ACCESSOR_PATH ( accessor_element )+ ) )
            // src/main/resources/org/drools/lang/DRL.g:1345:4: accessor_element ( DOT accessor_element )*
            {
            pushFollow(FOLLOW_accessor_element_in_accessor_path4592);
            accessor_element276=accessor_element();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_accessor_element.add(accessor_element276.getTree());
            // src/main/resources/org/drools/lang/DRL.g:1345:21: ( DOT accessor_element )*
            loop99:
            do {
                int alt99=2;
                int LA99_0 = input.LA(1);

                if ( (LA99_0==DOT) ) {
                    alt99=1;
                }


                switch (alt99) {
            	case 1 :
            	    // src/main/resources/org/drools/lang/DRL.g:1345:23: DOT accessor_element
            	    {
            	    DOT277=(Token)match(input,DOT,FOLLOW_DOT_in_accessor_path4596); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_DOT.add(DOT277);

            	    if ( state.backtracking==0 ) {
            	      	emit(DOT277, DroolsEditorType.IDENTIFIER);	
            	    }
            	    pushFollow(FOLLOW_accessor_element_in_accessor_path4600);
            	    accessor_element278=accessor_element();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_accessor_element.add(accessor_element278.getTree());

            	    }
            	    break;

            	default :
            	    break loop99;
                }
            } while (true);



            // AST REWRITE
            // elements: accessor_element
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1346:2: -> ^( VT_ACCESSOR_PATH ( accessor_element )+ )
            {
                // src/main/resources/org/drools/lang/DRL.g:1346:5: ^( VT_ACCESSOR_PATH ( accessor_element )+ )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(VT_ACCESSOR_PATH, "VT_ACCESSOR_PATH"), root_1);

                if ( !(stream_accessor_element.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_accessor_element.hasNext() ) {
                    adaptor.addChild(root_1, stream_accessor_element.nextTree());

                }
                stream_accessor_element.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "accessor_path"

    public static class accessor_element_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "accessor_element"
    // src/main/resources/org/drools/lang/DRL.g:1349:1: accessor_element : ID ( square_chunk )* -> ^( VT_ACCESSOR_ELEMENT ID ( square_chunk )* ) ;
    public final DRLParser.accessor_element_return accessor_element() throws RecognitionException {
        DRLParser.accessor_element_return retval = new DRLParser.accessor_element_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ID279=null;
        DRLParser.square_chunk_return square_chunk280 = null;


        Object ID279_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_square_chunk=new RewriteRuleSubtreeStream(adaptor,"rule square_chunk");
        try {
            // src/main/resources/org/drools/lang/DRL.g:1350:2: ( ID ( square_chunk )* -> ^( VT_ACCESSOR_ELEMENT ID ( square_chunk )* ) )
            // src/main/resources/org/drools/lang/DRL.g:1350:4: ID ( square_chunk )*
            {
            ID279=(Token)match(input,ID,FOLLOW_ID_in_accessor_element4624); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(ID279);

            if ( state.backtracking==0 ) {
              	emit(ID279, DroolsEditorType.IDENTIFIER);	
            }
            // src/main/resources/org/drools/lang/DRL.g:1351:3: ( square_chunk )*
            loop100:
            do {
                int alt100=2;
                int LA100_0 = input.LA(1);

                if ( (LA100_0==LEFT_SQUARE) ) {
                    alt100=1;
                }


                switch (alt100) {
            	case 1 :
            	    // src/main/resources/org/drools/lang/DRL.g:1351:3: square_chunk
            	    {
            	    pushFollow(FOLLOW_square_chunk_in_accessor_element4630);
            	    square_chunk280=square_chunk();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_square_chunk.add(square_chunk280.getTree());

            	    }
            	    break;

            	default :
            	    break loop100;
                }
            } while (true);



            // AST REWRITE
            // elements: square_chunk, ID
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1352:2: -> ^( VT_ACCESSOR_ELEMENT ID ( square_chunk )* )
            {
                // src/main/resources/org/drools/lang/DRL.g:1352:5: ^( VT_ACCESSOR_ELEMENT ID ( square_chunk )* )
                {
                Object root_1 = (Object)adaptor.nil();
                root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(VT_ACCESSOR_ELEMENT, "VT_ACCESSOR_ELEMENT"), root_1);

                adaptor.addChild(root_1, stream_ID.nextNode());
                // src/main/resources/org/drools/lang/DRL.g:1352:30: ( square_chunk )*
                while ( stream_square_chunk.hasNext() ) {
                    adaptor.addChild(root_1, stream_square_chunk.nextTree());

                }
                stream_square_chunk.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "accessor_element"

    public static class rhs_chunk_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rhs_chunk"
    // src/main/resources/org/drools/lang/DRL.g:1355:1: rhs_chunk : rc= rhs_chunk_data -> VT_RHS_CHUNK[$rc.start,text] ;
    public final DRLParser.rhs_chunk_return rhs_chunk() throws RecognitionException {
        DRLParser.rhs_chunk_return retval = new DRLParser.rhs_chunk_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        DRLParser.rhs_chunk_data_return rc = null;


        RewriteRuleSubtreeStream stream_rhs_chunk_data=new RewriteRuleSubtreeStream(adaptor,"rule rhs_chunk_data");

        	String text = "";

        try {
            // src/main/resources/org/drools/lang/DRL.g:1358:3: (rc= rhs_chunk_data -> VT_RHS_CHUNK[$rc.start,text] )
            // src/main/resources/org/drools/lang/DRL.g:1358:5: rc= rhs_chunk_data
            {
            pushFollow(FOLLOW_rhs_chunk_data_in_rhs_chunk4659);
            rc=rhs_chunk_data();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_rhs_chunk_data.add(rc.getTree());
            if ( state.backtracking==0 ) {
              text = (rc!=null?input.toString(rc.start,rc.stop):null);
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1359:2: -> VT_RHS_CHUNK[$rc.start,text]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VT_RHS_CHUNK, (rc!=null?((Token)rc.start):null), text));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rhs_chunk"

    public static class rhs_chunk_data_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rhs_chunk_data"
    // src/main/resources/org/drools/lang/DRL.g:1362:1: rhs_chunk_data : THEN ( not_end_key )* end_key ( SEMICOLON )? ;
    public final DRLParser.rhs_chunk_data_return rhs_chunk_data() throws RecognitionException {
        DRLParser.rhs_chunk_data_return retval = new DRLParser.rhs_chunk_data_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token THEN281=null;
        Token SEMICOLON284=null;
        DRLParser.not_end_key_return not_end_key282 = null;

        DRLParser.end_key_return end_key283 = null;


        Object THEN281_tree=null;
        Object SEMICOLON284_tree=null;

        try {
            // src/main/resources/org/drools/lang/DRL.g:1363:2: ( THEN ( not_end_key )* end_key ( SEMICOLON )? )
            // src/main/resources/org/drools/lang/DRL.g:1363:4: THEN ( not_end_key )* end_key ( SEMICOLON )?
            {
            root_0 = (Object)adaptor.nil();

            THEN281=(Token)match(input,THEN,FOLLOW_THEN_in_rhs_chunk_data4678); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            THEN281_tree = (Object)adaptor.create(THEN281);
            adaptor.addChild(root_0, THEN281_tree);
            }
            if ( state.backtracking==0 ) {
              	if ((THEN281!=null?THEN281.getText():null).equalsIgnoreCase("then")){
              			emit(THEN281, DroolsEditorType.KEYWORD);
              			emit(Location.LOCATION_RHS);
              		}	
            }
            // src/main/resources/org/drools/lang/DRL.g:1368:4: ( not_end_key )*
            loop101:
            do {
                int alt101=2;
                int LA101_0 = input.LA(1);

                if ( (LA101_0==ID) && (((!(validateIdentifierKey(DroolsSoftKeywords.END)))||((validateIdentifierKey(DroolsSoftKeywords.END)))))) {
                    int LA101_1 = input.LA(2);

                    if ( ((!(validateIdentifierKey(DroolsSoftKeywords.END)))) ) {
                        alt101=1;
                    }


                }
                else if ( ((LA101_0>=VT_COMPILATION_UNIT && LA101_0<=SEMICOLON)||(LA101_0>=DOT && LA101_0<=MULTI_LINE_COMMENT)) && ((!(validateIdentifierKey(DroolsSoftKeywords.END))))) {
                    alt101=1;
                }


                switch (alt101) {
            	case 1 :
            	    // src/main/resources/org/drools/lang/DRL.g:1368:4: not_end_key
            	    {
            	    pushFollow(FOLLOW_not_end_key_in_rhs_chunk_data4687);
            	    not_end_key282=not_end_key();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, not_end_key282.getTree());

            	    }
            	    break;

            	default :
            	    break loop101;
                }
            } while (true);

            pushFollow(FOLLOW_end_key_in_rhs_chunk_data4693);
            end_key283=end_key();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, end_key283.getTree());
            // src/main/resources/org/drools/lang/DRL.g:1370:3: ( SEMICOLON )?
            int alt102=2;
            int LA102_0 = input.LA(1);

            if ( (LA102_0==SEMICOLON) ) {
                alt102=1;
            }
            switch (alt102) {
                case 1 :
                    // src/main/resources/org/drools/lang/DRL.g:1370:3: SEMICOLON
                    {
                    SEMICOLON284=(Token)match(input,SEMICOLON,FOLLOW_SEMICOLON_in_rhs_chunk_data4698); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    SEMICOLON284_tree = (Object)adaptor.create(SEMICOLON284);
                    adaptor.addChild(root_0, SEMICOLON284_tree);
                    }

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              	emit(SEMICOLON284, DroolsEditorType.KEYWORD);	
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rhs_chunk_data"

    public static class curly_chunk_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "curly_chunk"
    // src/main/resources/org/drools/lang/DRL.g:1373:1: curly_chunk : cc= curly_chunk_data[false] -> VT_CURLY_CHUNK[$cc.start,text] ;
    public final DRLParser.curly_chunk_return curly_chunk() throws RecognitionException {
        DRLParser.curly_chunk_return retval = new DRLParser.curly_chunk_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        DRLParser.curly_chunk_data_return cc = null;


        RewriteRuleSubtreeStream stream_curly_chunk_data=new RewriteRuleSubtreeStream(adaptor,"rule curly_chunk_data");

        	String text = "";

        try {
            // src/main/resources/org/drools/lang/DRL.g:1376:3: (cc= curly_chunk_data[false] -> VT_CURLY_CHUNK[$cc.start,text] )
            // src/main/resources/org/drools/lang/DRL.g:1376:5: cc= curly_chunk_data[false]
            {
            pushFollow(FOLLOW_curly_chunk_data_in_curly_chunk4717);
            cc=curly_chunk_data(false);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_curly_chunk_data.add(cc.getTree());
            if ( state.backtracking==0 ) {
              text = (cc!=null?input.toString(cc.start,cc.stop):null);
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1377:2: -> VT_CURLY_CHUNK[$cc.start,text]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VT_CURLY_CHUNK, (cc!=null?((Token)cc.start):null), text));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "curly_chunk"

    public static class curly_chunk_data_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "curly_chunk_data"
    // src/main/resources/org/drools/lang/DRL.g:1380:1: curly_chunk_data[boolean isRecursive] : lc1= LEFT_CURLY (any=~ ( LEFT_CURLY | RIGHT_CURLY ) | curly_chunk_data[true] )* rc1= RIGHT_CURLY ;
    public final DRLParser.curly_chunk_data_return curly_chunk_data(boolean isRecursive) throws RecognitionException {
        DRLParser.curly_chunk_data_return retval = new DRLParser.curly_chunk_data_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token lc1=null;
        Token any=null;
        Token rc1=null;
        DRLParser.curly_chunk_data_return curly_chunk_data285 = null;


        Object lc1_tree=null;
        Object any_tree=null;
        Object rc1_tree=null;

        try {
            // src/main/resources/org/drools/lang/DRL.g:1381:2: (lc1= LEFT_CURLY (any=~ ( LEFT_CURLY | RIGHT_CURLY ) | curly_chunk_data[true] )* rc1= RIGHT_CURLY )
            // src/main/resources/org/drools/lang/DRL.g:1381:4: lc1= LEFT_CURLY (any=~ ( LEFT_CURLY | RIGHT_CURLY ) | curly_chunk_data[true] )* rc1= RIGHT_CURLY
            {
            root_0 = (Object)adaptor.nil();

            lc1=(Token)match(input,LEFT_CURLY,FOLLOW_LEFT_CURLY_in_curly_chunk_data4740); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            lc1_tree = (Object)adaptor.create(lc1);
            adaptor.addChild(root_0, lc1_tree);
            }
            if ( state.backtracking==0 ) {
              	if (!isRecursive) {
              				emit(lc1, DroolsEditorType.SYMBOL);
              			} else {
              				emit(lc1, DroolsEditorType.CODE_CHUNK);
              			}	
              		
            }
            // src/main/resources/org/drools/lang/DRL.g:1388:4: (any=~ ( LEFT_CURLY | RIGHT_CURLY ) | curly_chunk_data[true] )*
            loop103:
            do {
                int alt103=3;
                int LA103_0 = input.LA(1);

                if ( ((LA103_0>=VT_COMPILATION_UNIT && LA103_0<=THEN)||(LA103_0>=MISC && LA103_0<=MULTI_LINE_COMMENT)) ) {
                    alt103=1;
                }
                else if ( (LA103_0==LEFT_CURLY) ) {
                    alt103=2;
                }


                switch (alt103) {
            	case 1 :
            	    // src/main/resources/org/drools/lang/DRL.g:1388:5: any=~ ( LEFT_CURLY | RIGHT_CURLY )
            	    {
            	    any=(Token)input.LT(1);
            	    if ( (input.LA(1)>=VT_COMPILATION_UNIT && input.LA(1)<=THEN)||(input.LA(1)>=MISC && input.LA(1)<=MULTI_LINE_COMMENT) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(any));
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    if ( state.backtracking==0 ) {
            	       emit(any, DroolsEditorType.CODE_CHUNK); 
            	    }

            	    }
            	    break;
            	case 2 :
            	    // src/main/resources/org/drools/lang/DRL.g:1388:87: curly_chunk_data[true]
            	    {
            	    pushFollow(FOLLOW_curly_chunk_data_in_curly_chunk_data4768);
            	    curly_chunk_data285=curly_chunk_data(true);

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, curly_chunk_data285.getTree());

            	    }
            	    break;

            	default :
            	    break loop103;
                }
            } while (true);

            rc1=(Token)match(input,RIGHT_CURLY,FOLLOW_RIGHT_CURLY_in_curly_chunk_data4779); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            rc1_tree = (Object)adaptor.create(rc1);
            adaptor.addChild(root_0, rc1_tree);
            }
            if ( state.backtracking==0 ) {
              	if (!isRecursive) {
              				emit(rc1, DroolsEditorType.SYMBOL);
              			} else {
              				emit(rc1, DroolsEditorType.CODE_CHUNK);
              			}	
              		
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "curly_chunk_data"

    public static class paren_chunk_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "paren_chunk"
    // src/main/resources/org/drools/lang/DRL.g:1398:1: paren_chunk : pc= paren_chunk_data[false] -> VT_PAREN_CHUNK[$pc.start,text] ;
    public final DRLParser.paren_chunk_return paren_chunk() throws RecognitionException {
        DRLParser.paren_chunk_return retval = new DRLParser.paren_chunk_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        DRLParser.paren_chunk_data_return pc = null;


        RewriteRuleSubtreeStream stream_paren_chunk_data=new RewriteRuleSubtreeStream(adaptor,"rule paren_chunk_data");

        	String text = "";

        try {
            // src/main/resources/org/drools/lang/DRL.g:1401:3: (pc= paren_chunk_data[false] -> VT_PAREN_CHUNK[$pc.start,text] )
            // src/main/resources/org/drools/lang/DRL.g:1401:5: pc= paren_chunk_data[false]
            {
            pushFollow(FOLLOW_paren_chunk_data_in_paren_chunk4800);
            pc=paren_chunk_data(false);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_paren_chunk_data.add(pc.getTree());
            if ( state.backtracking==0 ) {
              text = (pc!=null?input.toString(pc.start,pc.stop):null);
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1402:2: -> VT_PAREN_CHUNK[$pc.start,text]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VT_PAREN_CHUNK, (pc!=null?((Token)pc.start):null), text));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "paren_chunk"

    public static class paren_chunk_data_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "paren_chunk_data"
    // src/main/resources/org/drools/lang/DRL.g:1405:1: paren_chunk_data[boolean isRecursive] : lp1= LEFT_PAREN (any=~ ( LEFT_PAREN | RIGHT_PAREN ) | paren_chunk_data[true] )* rp1= RIGHT_PAREN ;
    public final DRLParser.paren_chunk_data_return paren_chunk_data(boolean isRecursive) throws RecognitionException {
        DRLParser.paren_chunk_data_return retval = new DRLParser.paren_chunk_data_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token lp1=null;
        Token any=null;
        Token rp1=null;
        DRLParser.paren_chunk_data_return paren_chunk_data286 = null;


        Object lp1_tree=null;
        Object any_tree=null;
        Object rp1_tree=null;

        try {
            // src/main/resources/org/drools/lang/DRL.g:1406:2: (lp1= LEFT_PAREN (any=~ ( LEFT_PAREN | RIGHT_PAREN ) | paren_chunk_data[true] )* rp1= RIGHT_PAREN )
            // src/main/resources/org/drools/lang/DRL.g:1406:4: lp1= LEFT_PAREN (any=~ ( LEFT_PAREN | RIGHT_PAREN ) | paren_chunk_data[true] )* rp1= RIGHT_PAREN
            {
            root_0 = (Object)adaptor.nil();

            lp1=(Token)match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_paren_chunk_data4824); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            lp1_tree = (Object)adaptor.create(lp1);
            adaptor.addChild(root_0, lp1_tree);
            }
            if ( state.backtracking==0 ) {
              	if (!isRecursive) {
              				emit(lp1, DroolsEditorType.SYMBOL);
              			} else {
              				emit(lp1, DroolsEditorType.CODE_CHUNK);
              			}	
              		
            }
            // src/main/resources/org/drools/lang/DRL.g:1413:4: (any=~ ( LEFT_PAREN | RIGHT_PAREN ) | paren_chunk_data[true] )*
            loop104:
            do {
                int alt104=3;
                int LA104_0 = input.LA(1);

                if ( ((LA104_0>=VT_COMPILATION_UNIT && LA104_0<=STRING)||LA104_0==COMMA||(LA104_0>=AT && LA104_0<=MULTI_LINE_COMMENT)) ) {
                    alt104=1;
                }
                else if ( (LA104_0==LEFT_PAREN) ) {
                    alt104=2;
                }


                switch (alt104) {
            	case 1 :
            	    // src/main/resources/org/drools/lang/DRL.g:1413:5: any=~ ( LEFT_PAREN | RIGHT_PAREN )
            	    {
            	    any=(Token)input.LT(1);
            	    if ( (input.LA(1)>=VT_COMPILATION_UNIT && input.LA(1)<=STRING)||input.LA(1)==COMMA||(input.LA(1)>=AT && input.LA(1)<=MULTI_LINE_COMMENT) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(any));
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    if ( state.backtracking==0 ) {
            	       emit(any, DroolsEditorType.CODE_CHUNK); 
            	    }

            	    }
            	    break;
            	case 2 :
            	    // src/main/resources/org/drools/lang/DRL.g:1413:87: paren_chunk_data[true]
            	    {
            	    pushFollow(FOLLOW_paren_chunk_data_in_paren_chunk_data4852);
            	    paren_chunk_data286=paren_chunk_data(true);

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, paren_chunk_data286.getTree());

            	    }
            	    break;

            	default :
            	    break loop104;
                }
            } while (true);

            rp1=(Token)match(input,RIGHT_PAREN,FOLLOW_RIGHT_PAREN_in_paren_chunk_data4863); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            rp1_tree = (Object)adaptor.create(rp1);
            adaptor.addChild(root_0, rp1_tree);
            }
            if ( state.backtracking==0 ) {
              	if (!isRecursive) {
              				emit(rp1, DroolsEditorType.SYMBOL);
              			} else {
              				emit(rp1, DroolsEditorType.CODE_CHUNK);
              			}	
              		
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "paren_chunk_data"

    public static class square_chunk_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "square_chunk"
    // src/main/resources/org/drools/lang/DRL.g:1423:1: square_chunk : sc= square_chunk_data[false] -> VT_SQUARE_CHUNK[$sc.start,text] ;
    public final DRLParser.square_chunk_return square_chunk() throws RecognitionException {
        DRLParser.square_chunk_return retval = new DRLParser.square_chunk_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        DRLParser.square_chunk_data_return sc = null;


        RewriteRuleSubtreeStream stream_square_chunk_data=new RewriteRuleSubtreeStream(adaptor,"rule square_chunk_data");

        	String text = "";

        try {
            // src/main/resources/org/drools/lang/DRL.g:1426:3: (sc= square_chunk_data[false] -> VT_SQUARE_CHUNK[$sc.start,text] )
            // src/main/resources/org/drools/lang/DRL.g:1426:5: sc= square_chunk_data[false]
            {
            pushFollow(FOLLOW_square_chunk_data_in_square_chunk4884);
            sc=square_chunk_data(false);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_square_chunk_data.add(sc.getTree());
            if ( state.backtracking==0 ) {
              text = (sc!=null?input.toString(sc.start,sc.stop):null);
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1427:2: -> VT_SQUARE_CHUNK[$sc.start,text]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VT_SQUARE_CHUNK, (sc!=null?((Token)sc.start):null), text));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "square_chunk"

    public static class square_chunk_data_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "square_chunk_data"
    // src/main/resources/org/drools/lang/DRL.g:1430:1: square_chunk_data[boolean isRecursive] : ls1= LEFT_SQUARE (any=~ ( LEFT_SQUARE | RIGHT_SQUARE ) | square_chunk_data[true] )* rs1= RIGHT_SQUARE ;
    public final DRLParser.square_chunk_data_return square_chunk_data(boolean isRecursive) throws RecognitionException {
        DRLParser.square_chunk_data_return retval = new DRLParser.square_chunk_data_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token ls1=null;
        Token any=null;
        Token rs1=null;
        DRLParser.square_chunk_data_return square_chunk_data287 = null;


        Object ls1_tree=null;
        Object any_tree=null;
        Object rs1_tree=null;

        try {
            // src/main/resources/org/drools/lang/DRL.g:1431:2: (ls1= LEFT_SQUARE (any=~ ( LEFT_SQUARE | RIGHT_SQUARE ) | square_chunk_data[true] )* rs1= RIGHT_SQUARE )
            // src/main/resources/org/drools/lang/DRL.g:1431:4: ls1= LEFT_SQUARE (any=~ ( LEFT_SQUARE | RIGHT_SQUARE ) | square_chunk_data[true] )* rs1= RIGHT_SQUARE
            {
            root_0 = (Object)adaptor.nil();

            ls1=(Token)match(input,LEFT_SQUARE,FOLLOW_LEFT_SQUARE_in_square_chunk_data4907); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ls1_tree = (Object)adaptor.create(ls1);
            adaptor.addChild(root_0, ls1_tree);
            }
            if ( state.backtracking==0 ) {
              	if (!isRecursive) {
              				emit(ls1, DroolsEditorType.SYMBOL);
              			} else {
              				emit(ls1, DroolsEditorType.CODE_CHUNK);
              			}	
              		
            }
            // src/main/resources/org/drools/lang/DRL.g:1438:4: (any=~ ( LEFT_SQUARE | RIGHT_SQUARE ) | square_chunk_data[true] )*
            loop105:
            do {
                int alt105=3;
                int LA105_0 = input.LA(1);

                if ( ((LA105_0>=VT_COMPILATION_UNIT && LA105_0<=NULL)||(LA105_0>=THEN && LA105_0<=MULTI_LINE_COMMENT)) ) {
                    alt105=1;
                }
                else if ( (LA105_0==LEFT_SQUARE) ) {
                    alt105=2;
                }


                switch (alt105) {
            	case 1 :
            	    // src/main/resources/org/drools/lang/DRL.g:1438:5: any=~ ( LEFT_SQUARE | RIGHT_SQUARE )
            	    {
            	    any=(Token)input.LT(1);
            	    if ( (input.LA(1)>=VT_COMPILATION_UNIT && input.LA(1)<=NULL)||(input.LA(1)>=THEN && input.LA(1)<=MULTI_LINE_COMMENT) ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(any));
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    if ( state.backtracking==0 ) {
            	       emit(any, DroolsEditorType.CODE_CHUNK); 
            	    }

            	    }
            	    break;
            	case 2 :
            	    // src/main/resources/org/drools/lang/DRL.g:1438:88: square_chunk_data[true]
            	    {
            	    pushFollow(FOLLOW_square_chunk_data_in_square_chunk_data4934);
            	    square_chunk_data287=square_chunk_data(true);

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, square_chunk_data287.getTree());

            	    }
            	    break;

            	default :
            	    break loop105;
                }
            } while (true);

            rs1=(Token)match(input,RIGHT_SQUARE,FOLLOW_RIGHT_SQUARE_in_square_chunk_data4945); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            rs1_tree = (Object)adaptor.create(rs1);
            adaptor.addChild(root_0, rs1_tree);
            }
            if ( state.backtracking==0 ) {
              	if (!isRecursive) {
              				emit(rs1, DroolsEditorType.SYMBOL);
              			} else {
              				emit(rs1, DroolsEditorType.CODE_CHUNK);
              			}	
              		
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "square_chunk_data"

    public static class lock_on_active_key_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "lock_on_active_key"
    // src/main/resources/org/drools/lang/DRL.g:1448:1: lock_on_active_key : {...}? =>id1= ID mis1= MISC id2= ID mis2= MISC id3= ID -> VK_LOCK_ON_ACTIVE[$start, text] ;
    public final DRLParser.lock_on_active_key_return lock_on_active_key() throws RecognitionException {
        DRLParser.lock_on_active_key_return retval = new DRLParser.lock_on_active_key_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id1=null;
        Token mis1=null;
        Token id2=null;
        Token mis2=null;
        Token id3=null;

        Object id1_tree=null;
        Object mis1_tree=null;
        Object id2_tree=null;
        Object mis2_tree=null;
        Object id3_tree=null;
        RewriteRuleTokenStream stream_MISC=new RewriteRuleTokenStream(adaptor,"token MISC");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");


        	String text = "";

        try {
            // src/main/resources/org/drools/lang/DRL.g:1451:3: ({...}? =>id1= ID mis1= MISC id2= ID mis2= MISC id3= ID -> VK_LOCK_ON_ACTIVE[$start, text] )
            // src/main/resources/org/drools/lang/DRL.g:1451:5: {...}? =>id1= ID mis1= MISC id2= ID mis2= MISC id3= ID
            {
            if ( !(((validateIdentifierKey(DroolsSoftKeywords.LOCK) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.ON) && validateLT(4, "-") && validateLT(5, DroolsSoftKeywords.ACTIVE)))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "lock_on_active_key", "(validateIdentifierKey(DroolsSoftKeywords.LOCK) && validateLT(2, \"-\") && validateLT(3, DroolsSoftKeywords.ON) && validateLT(4, \"-\") && validateLT(5, DroolsSoftKeywords.ACTIVE))");
            }
            id1=(Token)match(input,ID,FOLLOW_ID_in_lock_on_active_key4969); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id1);

            mis1=(Token)match(input,MISC,FOLLOW_MISC_in_lock_on_active_key4973); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_MISC.add(mis1);

            id2=(Token)match(input,ID,FOLLOW_ID_in_lock_on_active_key4977); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id2);

            mis2=(Token)match(input,MISC,FOLLOW_MISC_in_lock_on_active_key4981); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_MISC.add(mis2);

            id3=(Token)match(input,ID,FOLLOW_ID_in_lock_on_active_key4985); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id3);

            if ( state.backtracking==0 ) {
              text = input.toString(retval.start,input.LT(-1));
            }
            if ( state.backtracking==0 ) {
              	emit(id1, DroolsEditorType.KEYWORD);
              		emit(mis1, DroolsEditorType.KEYWORD);
              		emit(id2, DroolsEditorType.KEYWORD);
              		emit(mis2, DroolsEditorType.KEYWORD);
              		emit(id3, DroolsEditorType.KEYWORD);	
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1457:3: -> VK_LOCK_ON_ACTIVE[$start, text]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VK_LOCK_ON_ACTIVE, ((Token)retval.start), text));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "lock_on_active_key"

    public static class date_effective_key_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "date_effective_key"
    // src/main/resources/org/drools/lang/DRL.g:1460:1: date_effective_key : {...}? =>id1= ID mis1= MISC id2= ID -> VK_DATE_EFFECTIVE[$start, text] ;
    public final DRLParser.date_effective_key_return date_effective_key() throws RecognitionException {
        DRLParser.date_effective_key_return retval = new DRLParser.date_effective_key_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id1=null;
        Token mis1=null;
        Token id2=null;

        Object id1_tree=null;
        Object mis1_tree=null;
        Object id2_tree=null;
        RewriteRuleTokenStream stream_MISC=new RewriteRuleTokenStream(adaptor,"token MISC");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");


        	String text = "";

        try {
            // src/main/resources/org/drools/lang/DRL.g:1463:3: ({...}? =>id1= ID mis1= MISC id2= ID -> VK_DATE_EFFECTIVE[$start, text] )
            // src/main/resources/org/drools/lang/DRL.g:1463:5: {...}? =>id1= ID mis1= MISC id2= ID
            {
            if ( !(((validateIdentifierKey(DroolsSoftKeywords.DATE) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.EFFECTIVE)))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "date_effective_key", "(validateIdentifierKey(DroolsSoftKeywords.DATE) && validateLT(2, \"-\") && validateLT(3, DroolsSoftKeywords.EFFECTIVE))");
            }
            id1=(Token)match(input,ID,FOLLOW_ID_in_date_effective_key5017); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id1);

            mis1=(Token)match(input,MISC,FOLLOW_MISC_in_date_effective_key5021); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_MISC.add(mis1);

            id2=(Token)match(input,ID,FOLLOW_ID_in_date_effective_key5025); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id2);

            if ( state.backtracking==0 ) {
              text = input.toString(retval.start,input.LT(-1));
            }
            if ( state.backtracking==0 ) {
              	emit(id1, DroolsEditorType.KEYWORD);
              		emit(mis1, DroolsEditorType.KEYWORD);
              		emit(id2, DroolsEditorType.KEYWORD);	
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1467:3: -> VK_DATE_EFFECTIVE[$start, text]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VK_DATE_EFFECTIVE, ((Token)retval.start), text));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "date_effective_key"

    public static class date_expires_key_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "date_expires_key"
    // src/main/resources/org/drools/lang/DRL.g:1470:1: date_expires_key : {...}? =>id1= ID mis1= MISC id2= ID -> VK_DATE_EXPIRES[$start, text] ;
    public final DRLParser.date_expires_key_return date_expires_key() throws RecognitionException {
        DRLParser.date_expires_key_return retval = new DRLParser.date_expires_key_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id1=null;
        Token mis1=null;
        Token id2=null;

        Object id1_tree=null;
        Object mis1_tree=null;
        Object id2_tree=null;
        RewriteRuleTokenStream stream_MISC=new RewriteRuleTokenStream(adaptor,"token MISC");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");


        	String text = "";

        try {
            // src/main/resources/org/drools/lang/DRL.g:1473:3: ({...}? =>id1= ID mis1= MISC id2= ID -> VK_DATE_EXPIRES[$start, text] )
            // src/main/resources/org/drools/lang/DRL.g:1473:5: {...}? =>id1= ID mis1= MISC id2= ID
            {
            if ( !(((validateIdentifierKey(DroolsSoftKeywords.DATE) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.EXPIRES)))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "date_expires_key", "(validateIdentifierKey(DroolsSoftKeywords.DATE) && validateLT(2, \"-\") && validateLT(3, DroolsSoftKeywords.EXPIRES))");
            }
            id1=(Token)match(input,ID,FOLLOW_ID_in_date_expires_key5057); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id1);

            mis1=(Token)match(input,MISC,FOLLOW_MISC_in_date_expires_key5061); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_MISC.add(mis1);

            id2=(Token)match(input,ID,FOLLOW_ID_in_date_expires_key5065); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id2);

            if ( state.backtracking==0 ) {
              text = input.toString(retval.start,input.LT(-1));
            }
            if ( state.backtracking==0 ) {
              	emit(id1, DroolsEditorType.KEYWORD);
              		emit(mis1, DroolsEditorType.KEYWORD);
              		emit(id2, DroolsEditorType.KEYWORD);	
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1477:3: -> VK_DATE_EXPIRES[$start, text]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VK_DATE_EXPIRES, ((Token)retval.start), text));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "date_expires_key"

    public static class no_loop_key_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "no_loop_key"
    // src/main/resources/org/drools/lang/DRL.g:1480:1: no_loop_key : {...}? =>id1= ID mis1= MISC id2= ID -> VK_NO_LOOP[$start, text] ;
    public final DRLParser.no_loop_key_return no_loop_key() throws RecognitionException {
        DRLParser.no_loop_key_return retval = new DRLParser.no_loop_key_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id1=null;
        Token mis1=null;
        Token id2=null;

        Object id1_tree=null;
        Object mis1_tree=null;
        Object id2_tree=null;
        RewriteRuleTokenStream stream_MISC=new RewriteRuleTokenStream(adaptor,"token MISC");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");


        	String text = "";

        try {
            // src/main/resources/org/drools/lang/DRL.g:1483:3: ({...}? =>id1= ID mis1= MISC id2= ID -> VK_NO_LOOP[$start, text] )
            // src/main/resources/org/drools/lang/DRL.g:1483:5: {...}? =>id1= ID mis1= MISC id2= ID
            {
            if ( !(((validateIdentifierKey(DroolsSoftKeywords.NO) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.LOOP)))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "no_loop_key", "(validateIdentifierKey(DroolsSoftKeywords.NO) && validateLT(2, \"-\") && validateLT(3, DroolsSoftKeywords.LOOP))");
            }
            id1=(Token)match(input,ID,FOLLOW_ID_in_no_loop_key5097); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id1);

            mis1=(Token)match(input,MISC,FOLLOW_MISC_in_no_loop_key5101); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_MISC.add(mis1);

            id2=(Token)match(input,ID,FOLLOW_ID_in_no_loop_key5105); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id2);

            if ( state.backtracking==0 ) {
              text = input.toString(retval.start,input.LT(-1));
            }
            if ( state.backtracking==0 ) {
              	emit(id1, DroolsEditorType.KEYWORD);
              		emit(mis1, DroolsEditorType.KEYWORD);
              		emit(id2, DroolsEditorType.KEYWORD);	
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1487:3: -> VK_NO_LOOP[$start, text]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VK_NO_LOOP, ((Token)retval.start), text));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "no_loop_key"

    public static class auto_focus_key_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "auto_focus_key"
    // src/main/resources/org/drools/lang/DRL.g:1490:1: auto_focus_key : {...}? =>id1= ID mis1= MISC id2= ID -> VK_AUTO_FOCUS[$start, text] ;
    public final DRLParser.auto_focus_key_return auto_focus_key() throws RecognitionException {
        DRLParser.auto_focus_key_return retval = new DRLParser.auto_focus_key_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id1=null;
        Token mis1=null;
        Token id2=null;

        Object id1_tree=null;
        Object mis1_tree=null;
        Object id2_tree=null;
        RewriteRuleTokenStream stream_MISC=new RewriteRuleTokenStream(adaptor,"token MISC");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");


        	String text = "";

        try {
            // src/main/resources/org/drools/lang/DRL.g:1493:3: ({...}? =>id1= ID mis1= MISC id2= ID -> VK_AUTO_FOCUS[$start, text] )
            // src/main/resources/org/drools/lang/DRL.g:1493:5: {...}? =>id1= ID mis1= MISC id2= ID
            {
            if ( !(((validateIdentifierKey(DroolsSoftKeywords.AUTO) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.FOCUS)))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "auto_focus_key", "(validateIdentifierKey(DroolsSoftKeywords.AUTO) && validateLT(2, \"-\") && validateLT(3, DroolsSoftKeywords.FOCUS))");
            }
            id1=(Token)match(input,ID,FOLLOW_ID_in_auto_focus_key5137); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id1);

            mis1=(Token)match(input,MISC,FOLLOW_MISC_in_auto_focus_key5141); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_MISC.add(mis1);

            id2=(Token)match(input,ID,FOLLOW_ID_in_auto_focus_key5145); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id2);

            if ( state.backtracking==0 ) {
              text = input.toString(retval.start,input.LT(-1));
            }
            if ( state.backtracking==0 ) {
              	emit(id1, DroolsEditorType.KEYWORD);
              		emit(mis1, DroolsEditorType.KEYWORD);
              		emit(id2, DroolsEditorType.KEYWORD);	
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1497:3: -> VK_AUTO_FOCUS[$start, text]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VK_AUTO_FOCUS, ((Token)retval.start), text));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "auto_focus_key"

    public static class activation_group_key_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "activation_group_key"
    // src/main/resources/org/drools/lang/DRL.g:1500:1: activation_group_key : {...}? =>id1= ID mis1= MISC id2= ID -> VK_ACTIVATION_GROUP[$start, text] ;
    public final DRLParser.activation_group_key_return activation_group_key() throws RecognitionException {
        DRLParser.activation_group_key_return retval = new DRLParser.activation_group_key_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id1=null;
        Token mis1=null;
        Token id2=null;

        Object id1_tree=null;
        Object mis1_tree=null;
        Object id2_tree=null;
        RewriteRuleTokenStream stream_MISC=new RewriteRuleTokenStream(adaptor,"token MISC");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");


        	String text = "";

        try {
            // src/main/resources/org/drools/lang/DRL.g:1503:3: ({...}? =>id1= ID mis1= MISC id2= ID -> VK_ACTIVATION_GROUP[$start, text] )
            // src/main/resources/org/drools/lang/DRL.g:1503:5: {...}? =>id1= ID mis1= MISC id2= ID
            {
            if ( !(((validateIdentifierKey(DroolsSoftKeywords.ACTIVATION) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.GROUP)))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "activation_group_key", "(validateIdentifierKey(DroolsSoftKeywords.ACTIVATION) && validateLT(2, \"-\") && validateLT(3, DroolsSoftKeywords.GROUP))");
            }
            id1=(Token)match(input,ID,FOLLOW_ID_in_activation_group_key5177); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id1);

            mis1=(Token)match(input,MISC,FOLLOW_MISC_in_activation_group_key5181); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_MISC.add(mis1);

            id2=(Token)match(input,ID,FOLLOW_ID_in_activation_group_key5185); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id2);

            if ( state.backtracking==0 ) {
              text = input.toString(retval.start,input.LT(-1));
            }
            if ( state.backtracking==0 ) {
              	emit(id1, DroolsEditorType.KEYWORD);
              		emit(mis1, DroolsEditorType.KEYWORD);
              		emit(id2, DroolsEditorType.KEYWORD);	
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1507:3: -> VK_ACTIVATION_GROUP[$start, text]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VK_ACTIVATION_GROUP, ((Token)retval.start), text));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "activation_group_key"

    public static class agenda_group_key_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "agenda_group_key"
    // src/main/resources/org/drools/lang/DRL.g:1510:1: agenda_group_key : {...}? =>id1= ID mis1= MISC id2= ID -> VK_AGENDA_GROUP[$start, text] ;
    public final DRLParser.agenda_group_key_return agenda_group_key() throws RecognitionException {
        DRLParser.agenda_group_key_return retval = new DRLParser.agenda_group_key_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id1=null;
        Token mis1=null;
        Token id2=null;

        Object id1_tree=null;
        Object mis1_tree=null;
        Object id2_tree=null;
        RewriteRuleTokenStream stream_MISC=new RewriteRuleTokenStream(adaptor,"token MISC");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");


        	String text = "";

        try {
            // src/main/resources/org/drools/lang/DRL.g:1513:3: ({...}? =>id1= ID mis1= MISC id2= ID -> VK_AGENDA_GROUP[$start, text] )
            // src/main/resources/org/drools/lang/DRL.g:1513:5: {...}? =>id1= ID mis1= MISC id2= ID
            {
            if ( !(((validateIdentifierKey(DroolsSoftKeywords.AGENDA) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.GROUP)))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "agenda_group_key", "(validateIdentifierKey(DroolsSoftKeywords.AGENDA) && validateLT(2, \"-\") && validateLT(3, DroolsSoftKeywords.GROUP))");
            }
            id1=(Token)match(input,ID,FOLLOW_ID_in_agenda_group_key5217); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id1);

            mis1=(Token)match(input,MISC,FOLLOW_MISC_in_agenda_group_key5221); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_MISC.add(mis1);

            id2=(Token)match(input,ID,FOLLOW_ID_in_agenda_group_key5225); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id2);

            if ( state.backtracking==0 ) {
              text = input.toString(retval.start,input.LT(-1));
            }
            if ( state.backtracking==0 ) {
              	emit(id1, DroolsEditorType.KEYWORD);
              		emit(mis1, DroolsEditorType.KEYWORD);
              		emit(id2, DroolsEditorType.KEYWORD);	
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1517:3: -> VK_AGENDA_GROUP[$start, text]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VK_AGENDA_GROUP, ((Token)retval.start), text));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "agenda_group_key"

    public static class ruleflow_group_key_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ruleflow_group_key"
    // src/main/resources/org/drools/lang/DRL.g:1520:1: ruleflow_group_key : {...}? =>id1= ID mis1= MISC id2= ID -> VK_RULEFLOW_GROUP[$start, text] ;
    public final DRLParser.ruleflow_group_key_return ruleflow_group_key() throws RecognitionException {
        DRLParser.ruleflow_group_key_return retval = new DRLParser.ruleflow_group_key_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id1=null;
        Token mis1=null;
        Token id2=null;

        Object id1_tree=null;
        Object mis1_tree=null;
        Object id2_tree=null;
        RewriteRuleTokenStream stream_MISC=new RewriteRuleTokenStream(adaptor,"token MISC");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");


        	String text = "";

        try {
            // src/main/resources/org/drools/lang/DRL.g:1523:3: ({...}? =>id1= ID mis1= MISC id2= ID -> VK_RULEFLOW_GROUP[$start, text] )
            // src/main/resources/org/drools/lang/DRL.g:1523:5: {...}? =>id1= ID mis1= MISC id2= ID
            {
            if ( !(((validateIdentifierKey(DroolsSoftKeywords.RULEFLOW) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.GROUP)))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "ruleflow_group_key", "(validateIdentifierKey(DroolsSoftKeywords.RULEFLOW) && validateLT(2, \"-\") && validateLT(3, DroolsSoftKeywords.GROUP))");
            }
            id1=(Token)match(input,ID,FOLLOW_ID_in_ruleflow_group_key5257); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id1);

            mis1=(Token)match(input,MISC,FOLLOW_MISC_in_ruleflow_group_key5261); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_MISC.add(mis1);

            id2=(Token)match(input,ID,FOLLOW_ID_in_ruleflow_group_key5265); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id2);

            if ( state.backtracking==0 ) {
              text = input.toString(retval.start,input.LT(-1));
            }
            if ( state.backtracking==0 ) {
              	emit(id1, DroolsEditorType.KEYWORD);
              		emit(mis1, DroolsEditorType.KEYWORD);
              		emit(id2, DroolsEditorType.KEYWORD);	
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1527:3: -> VK_RULEFLOW_GROUP[$start, text]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VK_RULEFLOW_GROUP, ((Token)retval.start), text));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "ruleflow_group_key"

    public static class entry_point_key_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "entry_point_key"
    // src/main/resources/org/drools/lang/DRL.g:1530:1: entry_point_key : {...}? =>id1= ID mis1= MISC id2= ID -> VK_ENTRY_POINT[$start, text] ;
    public final DRLParser.entry_point_key_return entry_point_key() throws RecognitionException {
        DRLParser.entry_point_key_return retval = new DRLParser.entry_point_key_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id1=null;
        Token mis1=null;
        Token id2=null;

        Object id1_tree=null;
        Object mis1_tree=null;
        Object id2_tree=null;
        RewriteRuleTokenStream stream_MISC=new RewriteRuleTokenStream(adaptor,"token MISC");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");


        	String text = "";

        try {
            // src/main/resources/org/drools/lang/DRL.g:1533:3: ({...}? =>id1= ID mis1= MISC id2= ID -> VK_ENTRY_POINT[$start, text] )
            // src/main/resources/org/drools/lang/DRL.g:1533:5: {...}? =>id1= ID mis1= MISC id2= ID
            {
            if ( !(((validateIdentifierKey(DroolsSoftKeywords.ENTRY) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.POINT)))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "entry_point_key", "(validateIdentifierKey(DroolsSoftKeywords.ENTRY) && validateLT(2, \"-\") && validateLT(3, DroolsSoftKeywords.POINT))");
            }
            id1=(Token)match(input,ID,FOLLOW_ID_in_entry_point_key5297); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id1);

            mis1=(Token)match(input,MISC,FOLLOW_MISC_in_entry_point_key5301); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_MISC.add(mis1);

            id2=(Token)match(input,ID,FOLLOW_ID_in_entry_point_key5305); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id2);

            if ( state.backtracking==0 ) {
              text = input.toString(retval.start,input.LT(-1));
            }
            if ( state.backtracking==0 ) {
              	emit(id1, DroolsEditorType.KEYWORD);
              		emit(mis1, DroolsEditorType.KEYWORD);
              		emit(id2, DroolsEditorType.KEYWORD);	
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1537:3: -> VK_ENTRY_POINT[$start, text]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VK_ENTRY_POINT, ((Token)retval.start), text));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "entry_point_key"

    public static class duration_key_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "duration_key"
    // src/main/resources/org/drools/lang/DRL.g:1540:1: duration_key : {...}? =>id= ID -> VK_DURATION[$id] ;
    public final DRLParser.duration_key_return duration_key() throws RecognitionException {
        DRLParser.duration_key_return retval = new DRLParser.duration_key_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;

        Object id_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // src/main/resources/org/drools/lang/DRL.g:1541:2: ({...}? =>id= ID -> VK_DURATION[$id] )
            // src/main/resources/org/drools/lang/DRL.g:1541:4: {...}? =>id= ID
            {
            if ( !(((validateIdentifierKey(DroolsSoftKeywords.DURATION)))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "duration_key", "(validateIdentifierKey(DroolsSoftKeywords.DURATION))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_duration_key5334); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id);

            if ( state.backtracking==0 ) {
              	emit(id, DroolsEditorType.KEYWORD);	
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1543:3: -> VK_DURATION[$id]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VK_DURATION, id));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "duration_key"

    public static class package_key_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "package_key"
    // src/main/resources/org/drools/lang/DRL.g:1546:1: package_key : {...}? =>id= ID -> VK_PACKAGE[$id] ;
    public final DRLParser.package_key_return package_key() throws RecognitionException {
        DRLParser.package_key_return retval = new DRLParser.package_key_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;

        Object id_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // src/main/resources/org/drools/lang/DRL.g:1547:2: ({...}? =>id= ID -> VK_PACKAGE[$id] )
            // src/main/resources/org/drools/lang/DRL.g:1547:4: {...}? =>id= ID
            {
            if ( !(((validateIdentifierKey(DroolsSoftKeywords.PACKAGE)))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "package_key", "(validateIdentifierKey(DroolsSoftKeywords.PACKAGE))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_package_key5361); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id);

            if ( state.backtracking==0 ) {
              	emit(id, DroolsEditorType.KEYWORD);	
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1549:3: -> VK_PACKAGE[$id]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VK_PACKAGE, id));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "package_key"

    public static class import_key_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "import_key"
    // src/main/resources/org/drools/lang/DRL.g:1552:1: import_key : {...}? =>id= ID -> VK_IMPORT[$id] ;
    public final DRLParser.import_key_return import_key() throws RecognitionException {
        DRLParser.import_key_return retval = new DRLParser.import_key_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;

        Object id_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // src/main/resources/org/drools/lang/DRL.g:1553:2: ({...}? =>id= ID -> VK_IMPORT[$id] )
            // src/main/resources/org/drools/lang/DRL.g:1553:4: {...}? =>id= ID
            {
            if ( !(((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "import_key", "(validateIdentifierKey(DroolsSoftKeywords.IMPORT))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_import_key5388); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id);

            if ( state.backtracking==0 ) {
              	emit(id, DroolsEditorType.KEYWORD);	
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1555:3: -> VK_IMPORT[$id]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VK_IMPORT, id));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "import_key"

    public static class dialect_key_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "dialect_key"
    // src/main/resources/org/drools/lang/DRL.g:1558:1: dialect_key : {...}? =>id= ID -> VK_DIALECT[$id] ;
    public final DRLParser.dialect_key_return dialect_key() throws RecognitionException {
        DRLParser.dialect_key_return retval = new DRLParser.dialect_key_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;

        Object id_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // src/main/resources/org/drools/lang/DRL.g:1559:2: ({...}? =>id= ID -> VK_DIALECT[$id] )
            // src/main/resources/org/drools/lang/DRL.g:1559:4: {...}? =>id= ID
            {
            if ( !(((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "dialect_key", "(validateIdentifierKey(DroolsSoftKeywords.DIALECT))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_dialect_key5415); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id);

            if ( state.backtracking==0 ) {
              	emit(id, DroolsEditorType.KEYWORD);	
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1561:3: -> VK_DIALECT[$id]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VK_DIALECT, id));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "dialect_key"

    public static class salience_key_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "salience_key"
    // src/main/resources/org/drools/lang/DRL.g:1564:1: salience_key : {...}? =>id= ID -> VK_SALIENCE[$id] ;
    public final DRLParser.salience_key_return salience_key() throws RecognitionException {
        DRLParser.salience_key_return retval = new DRLParser.salience_key_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;

        Object id_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // src/main/resources/org/drools/lang/DRL.g:1565:2: ({...}? =>id= ID -> VK_SALIENCE[$id] )
            // src/main/resources/org/drools/lang/DRL.g:1565:4: {...}? =>id= ID
            {
            if ( !(((validateIdentifierKey(DroolsSoftKeywords.SALIENCE)))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "salience_key", "(validateIdentifierKey(DroolsSoftKeywords.SALIENCE))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_salience_key5442); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id);

            if ( state.backtracking==0 ) {
              	emit(id, DroolsEditorType.KEYWORD);	
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1567:3: -> VK_SALIENCE[$id]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VK_SALIENCE, id));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "salience_key"

    public static class enabled_key_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "enabled_key"
    // src/main/resources/org/drools/lang/DRL.g:1570:1: enabled_key : {...}? =>id= ID -> VK_ENABLED[$id] ;
    public final DRLParser.enabled_key_return enabled_key() throws RecognitionException {
        DRLParser.enabled_key_return retval = new DRLParser.enabled_key_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;

        Object id_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // src/main/resources/org/drools/lang/DRL.g:1571:2: ({...}? =>id= ID -> VK_ENABLED[$id] )
            // src/main/resources/org/drools/lang/DRL.g:1571:4: {...}? =>id= ID
            {
            if ( !(((validateIdentifierKey(DroolsSoftKeywords.ENABLED)))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "enabled_key", "(validateIdentifierKey(DroolsSoftKeywords.ENABLED))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_enabled_key5469); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id);

            if ( state.backtracking==0 ) {
              	emit(id, DroolsEditorType.KEYWORD);	
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1573:3: -> VK_ENABLED[$id]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VK_ENABLED, id));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "enabled_key"

    public static class attributes_key_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "attributes_key"
    // src/main/resources/org/drools/lang/DRL.g:1576:1: attributes_key : {...}? =>id= ID -> VK_ATTRIBUTES[$id] ;
    public final DRLParser.attributes_key_return attributes_key() throws RecognitionException {
        DRLParser.attributes_key_return retval = new DRLParser.attributes_key_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;

        Object id_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // src/main/resources/org/drools/lang/DRL.g:1577:2: ({...}? =>id= ID -> VK_ATTRIBUTES[$id] )
            // src/main/resources/org/drools/lang/DRL.g:1577:4: {...}? =>id= ID
            {
            if ( !(((validateIdentifierKey(DroolsSoftKeywords.ATTRIBUTES)))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "attributes_key", "(validateIdentifierKey(DroolsSoftKeywords.ATTRIBUTES))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_attributes_key5496); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id);

            if ( state.backtracking==0 ) {
              	emit(id, DroolsEditorType.KEYWORD);	
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1579:3: -> VK_ATTRIBUTES[$id]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VK_ATTRIBUTES, id));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "attributes_key"

    public static class rule_key_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rule_key"
    // src/main/resources/org/drools/lang/DRL.g:1582:1: rule_key : {...}? =>id= ID -> VK_RULE[$id] ;
    public final DRLParser.rule_key_return rule_key() throws RecognitionException {
        DRLParser.rule_key_return retval = new DRLParser.rule_key_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;

        Object id_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // src/main/resources/org/drools/lang/DRL.g:1583:2: ({...}? =>id= ID -> VK_RULE[$id] )
            // src/main/resources/org/drools/lang/DRL.g:1583:4: {...}? =>id= ID
            {
            if ( !(((validateIdentifierKey(DroolsSoftKeywords.RULE)))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "rule_key", "(validateIdentifierKey(DroolsSoftKeywords.RULE))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_rule_key5523); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id);

            if ( state.backtracking==0 ) {
              	emit(id, DroolsEditorType.KEYWORD);	
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1585:3: -> VK_RULE[$id]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VK_RULE, id));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rule_key"

    public static class extend_key_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "extend_key"
    // src/main/resources/org/drools/lang/DRL.g:1588:1: extend_key : {...}? =>id= ID -> VK_EXTEND[$id] ;
    public final DRLParser.extend_key_return extend_key() throws RecognitionException {
        DRLParser.extend_key_return retval = new DRLParser.extend_key_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;

        Object id_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // src/main/resources/org/drools/lang/DRL.g:1589:2: ({...}? =>id= ID -> VK_EXTEND[$id] )
            // src/main/resources/org/drools/lang/DRL.g:1589:4: {...}? =>id= ID
            {
            if ( !(((validateIdentifierKey(DroolsSoftKeywords.EXTEND)))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "extend_key", "(validateIdentifierKey(DroolsSoftKeywords.EXTEND))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_extend_key5550); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id);

            if ( state.backtracking==0 ) {
              	emit(id, DroolsEditorType.KEYWORD);	
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1591:3: -> VK_EXTEND[$id]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VK_EXTEND, id));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "extend_key"

    public static class template_key_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "template_key"
    // src/main/resources/org/drools/lang/DRL.g:1594:1: template_key : {...}? =>id= ID -> VK_TEMPLATE[$id] ;
    public final DRLParser.template_key_return template_key() throws RecognitionException {
        DRLParser.template_key_return retval = new DRLParser.template_key_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;

        Object id_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // src/main/resources/org/drools/lang/DRL.g:1595:2: ({...}? =>id= ID -> VK_TEMPLATE[$id] )
            // src/main/resources/org/drools/lang/DRL.g:1595:4: {...}? =>id= ID
            {
            if ( !(((validateIdentifierKey(DroolsSoftKeywords.TEMPLATE)))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "template_key", "(validateIdentifierKey(DroolsSoftKeywords.TEMPLATE))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_template_key5577); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id);

            if ( state.backtracking==0 ) {
              	emit(id, DroolsEditorType.KEYWORD);	
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1597:3: -> VK_TEMPLATE[$id]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VK_TEMPLATE, id));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "template_key"

    public static class query_key_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "query_key"
    // src/main/resources/org/drools/lang/DRL.g:1600:1: query_key : {...}? =>id= ID -> VK_QUERY[$id] ;
    public final DRLParser.query_key_return query_key() throws RecognitionException {
        DRLParser.query_key_return retval = new DRLParser.query_key_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;

        Object id_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // src/main/resources/org/drools/lang/DRL.g:1601:2: ({...}? =>id= ID -> VK_QUERY[$id] )
            // src/main/resources/org/drools/lang/DRL.g:1601:4: {...}? =>id= ID
            {
            if ( !(((validateIdentifierKey(DroolsSoftKeywords.QUERY)))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "query_key", "(validateIdentifierKey(DroolsSoftKeywords.QUERY))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_query_key5604); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id);

            if ( state.backtracking==0 ) {
              	emit(id, DroolsEditorType.KEYWORD);	
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1603:3: -> VK_QUERY[$id]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VK_QUERY, id));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "query_key"

    public static class declare_key_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "declare_key"
    // src/main/resources/org/drools/lang/DRL.g:1606:1: declare_key : {...}? =>id= ID -> VK_DECLARE[$id] ;
    public final DRLParser.declare_key_return declare_key() throws RecognitionException {
        DRLParser.declare_key_return retval = new DRLParser.declare_key_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;

        Object id_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // src/main/resources/org/drools/lang/DRL.g:1607:2: ({...}? =>id= ID -> VK_DECLARE[$id] )
            // src/main/resources/org/drools/lang/DRL.g:1607:4: {...}? =>id= ID
            {
            if ( !(((validateIdentifierKey(DroolsSoftKeywords.DECLARE)))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "declare_key", "(validateIdentifierKey(DroolsSoftKeywords.DECLARE))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_declare_key5631); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id);

            if ( state.backtracking==0 ) {
              	emit(id, DroolsEditorType.KEYWORD);	
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1609:3: -> VK_DECLARE[$id]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VK_DECLARE, id));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "declare_key"

    public static class function_key_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "function_key"
    // src/main/resources/org/drools/lang/DRL.g:1612:1: function_key : {...}? =>id= ID -> VK_FUNCTION[$id] ;
    public final DRLParser.function_key_return function_key() throws RecognitionException {
        DRLParser.function_key_return retval = new DRLParser.function_key_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;

        Object id_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // src/main/resources/org/drools/lang/DRL.g:1613:2: ({...}? =>id= ID -> VK_FUNCTION[$id] )
            // src/main/resources/org/drools/lang/DRL.g:1613:4: {...}? =>id= ID
            {
            if ( !(((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "function_key", "(validateIdentifierKey(DroolsSoftKeywords.FUNCTION))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_function_key5658); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id);

            if ( state.backtracking==0 ) {
              	emit(id, DroolsEditorType.KEYWORD);	
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1615:3: -> VK_FUNCTION[$id]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VK_FUNCTION, id));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "function_key"

    public static class global_key_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "global_key"
    // src/main/resources/org/drools/lang/DRL.g:1618:1: global_key : {...}? =>id= ID -> VK_GLOBAL[$id] ;
    public final DRLParser.global_key_return global_key() throws RecognitionException {
        DRLParser.global_key_return retval = new DRLParser.global_key_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;

        Object id_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // src/main/resources/org/drools/lang/DRL.g:1619:2: ({...}? =>id= ID -> VK_GLOBAL[$id] )
            // src/main/resources/org/drools/lang/DRL.g:1619:4: {...}? =>id= ID
            {
            if ( !(((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "global_key", "(validateIdentifierKey(DroolsSoftKeywords.GLOBAL))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_global_key5685); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id);

            if ( state.backtracking==0 ) {
              	emit(id, DroolsEditorType.KEYWORD);	
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1621:3: -> VK_GLOBAL[$id]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VK_GLOBAL, id));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "global_key"

    public static class eval_key_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "eval_key"
    // src/main/resources/org/drools/lang/DRL.g:1624:1: eval_key : {...}? =>id= ID -> VK_EVAL[$id] ;
    public final DRLParser.eval_key_return eval_key() throws RecognitionException {
        DRLParser.eval_key_return retval = new DRLParser.eval_key_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;

        Object id_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // src/main/resources/org/drools/lang/DRL.g:1625:2: ({...}? =>id= ID -> VK_EVAL[$id] )
            // src/main/resources/org/drools/lang/DRL.g:1625:4: {...}? =>id= ID
            {
            if ( !(((validateIdentifierKey(DroolsSoftKeywords.EVAL)))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "eval_key", "(validateIdentifierKey(DroolsSoftKeywords.EVAL))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_eval_key5712); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id);

            if ( state.backtracking==0 ) {
              	emit(id, DroolsEditorType.KEYWORD);	
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1627:3: -> VK_EVAL[$id]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VK_EVAL, id));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "eval_key"

    public static class not_key_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "not_key"
    // src/main/resources/org/drools/lang/DRL.g:1630:1: not_key : {...}? =>id= ID -> VK_NOT[$id] ;
    public final DRLParser.not_key_return not_key() throws RecognitionException {
        DRLParser.not_key_return retval = new DRLParser.not_key_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;

        Object id_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // src/main/resources/org/drools/lang/DRL.g:1631:2: ({...}? =>id= ID -> VK_NOT[$id] )
            // src/main/resources/org/drools/lang/DRL.g:1631:4: {...}? =>id= ID
            {
            if ( !(((validateIdentifierKey(DroolsSoftKeywords.NOT)))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "not_key", "(validateIdentifierKey(DroolsSoftKeywords.NOT))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_not_key5739); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id);

            if ( state.backtracking==0 ) {
              	emit(id, DroolsEditorType.KEYWORD);	
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1633:3: -> VK_NOT[$id]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VK_NOT, id));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "not_key"

    public static class in_key_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "in_key"
    // src/main/resources/org/drools/lang/DRL.g:1636:1: in_key : {...}? =>id= ID -> VK_IN[$id] ;
    public final DRLParser.in_key_return in_key() throws RecognitionException {
        DRLParser.in_key_return retval = new DRLParser.in_key_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;

        Object id_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // src/main/resources/org/drools/lang/DRL.g:1637:2: ({...}? =>id= ID -> VK_IN[$id] )
            // src/main/resources/org/drools/lang/DRL.g:1637:4: {...}? =>id= ID
            {
            if ( !(((validateIdentifierKey(DroolsSoftKeywords.IN)))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "in_key", "(validateIdentifierKey(DroolsSoftKeywords.IN))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_in_key5766); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id);

            if ( state.backtracking==0 ) {
              	emit(id, DroolsEditorType.KEYWORD);	
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1639:3: -> VK_IN[$id]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VK_IN, id));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "in_key"

    public static class or_key_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "or_key"
    // src/main/resources/org/drools/lang/DRL.g:1642:1: or_key : {...}? =>id= ID -> VK_OR[$id] ;
    public final DRLParser.or_key_return or_key() throws RecognitionException {
        DRLParser.or_key_return retval = new DRLParser.or_key_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;

        Object id_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // src/main/resources/org/drools/lang/DRL.g:1643:2: ({...}? =>id= ID -> VK_OR[$id] )
            // src/main/resources/org/drools/lang/DRL.g:1643:4: {...}? =>id= ID
            {
            if ( !(((validateIdentifierKey(DroolsSoftKeywords.OR)))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "or_key", "(validateIdentifierKey(DroolsSoftKeywords.OR))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_or_key5793); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id);

            if ( state.backtracking==0 ) {
              	emit(id, DroolsEditorType.KEYWORD);	
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1645:3: -> VK_OR[$id]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VK_OR, id));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "or_key"

    public static class and_key_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "and_key"
    // src/main/resources/org/drools/lang/DRL.g:1648:1: and_key : {...}? =>id= ID -> VK_AND[$id] ;
    public final DRLParser.and_key_return and_key() throws RecognitionException {
        DRLParser.and_key_return retval = new DRLParser.and_key_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;

        Object id_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // src/main/resources/org/drools/lang/DRL.g:1649:2: ({...}? =>id= ID -> VK_AND[$id] )
            // src/main/resources/org/drools/lang/DRL.g:1649:4: {...}? =>id= ID
            {
            if ( !(((validateIdentifierKey(DroolsSoftKeywords.AND)))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "and_key", "(validateIdentifierKey(DroolsSoftKeywords.AND))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_and_key5820); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id);

            if ( state.backtracking==0 ) {
              	emit(id, DroolsEditorType.KEYWORD);	
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1651:3: -> VK_AND[$id]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VK_AND, id));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "and_key"

    public static class exists_key_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "exists_key"
    // src/main/resources/org/drools/lang/DRL.g:1654:1: exists_key : {...}? =>id= ID -> VK_EXISTS[$id] ;
    public final DRLParser.exists_key_return exists_key() throws RecognitionException {
        DRLParser.exists_key_return retval = new DRLParser.exists_key_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;

        Object id_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // src/main/resources/org/drools/lang/DRL.g:1655:2: ({...}? =>id= ID -> VK_EXISTS[$id] )
            // src/main/resources/org/drools/lang/DRL.g:1655:4: {...}? =>id= ID
            {
            if ( !(((validateIdentifierKey(DroolsSoftKeywords.EXISTS)))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "exists_key", "(validateIdentifierKey(DroolsSoftKeywords.EXISTS))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_exists_key5847); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id);

            if ( state.backtracking==0 ) {
              	emit(id, DroolsEditorType.KEYWORD);	
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1657:3: -> VK_EXISTS[$id]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VK_EXISTS, id));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "exists_key"

    public static class forall_key_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "forall_key"
    // src/main/resources/org/drools/lang/DRL.g:1660:1: forall_key : {...}? =>id= ID -> VK_FORALL[$id] ;
    public final DRLParser.forall_key_return forall_key() throws RecognitionException {
        DRLParser.forall_key_return retval = new DRLParser.forall_key_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;

        Object id_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // src/main/resources/org/drools/lang/DRL.g:1661:2: ({...}? =>id= ID -> VK_FORALL[$id] )
            // src/main/resources/org/drools/lang/DRL.g:1661:4: {...}? =>id= ID
            {
            if ( !(((validateIdentifierKey(DroolsSoftKeywords.FORALL)))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "forall_key", "(validateIdentifierKey(DroolsSoftKeywords.FORALL))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_forall_key5874); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id);

            if ( state.backtracking==0 ) {
              	emit(id, DroolsEditorType.KEYWORD);	
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1663:3: -> VK_FORALL[$id]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VK_FORALL, id));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "forall_key"

    public static class action_key_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "action_key"
    // src/main/resources/org/drools/lang/DRL.g:1666:1: action_key : {...}? =>id= ID -> VK_ACTION[$id] ;
    public final DRLParser.action_key_return action_key() throws RecognitionException {
        DRLParser.action_key_return retval = new DRLParser.action_key_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;

        Object id_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // src/main/resources/org/drools/lang/DRL.g:1667:2: ({...}? =>id= ID -> VK_ACTION[$id] )
            // src/main/resources/org/drools/lang/DRL.g:1667:4: {...}? =>id= ID
            {
            if ( !(((validateIdentifierKey(DroolsSoftKeywords.ACTION)))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "action_key", "(validateIdentifierKey(DroolsSoftKeywords.ACTION))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_action_key5901); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id);

            if ( state.backtracking==0 ) {
              	emit(id, DroolsEditorType.KEYWORD);	
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1669:3: -> VK_ACTION[$id]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VK_ACTION, id));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "action_key"

    public static class reverse_key_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "reverse_key"
    // src/main/resources/org/drools/lang/DRL.g:1672:1: reverse_key : {...}? =>id= ID -> VK_REVERSE[$id] ;
    public final DRLParser.reverse_key_return reverse_key() throws RecognitionException {
        DRLParser.reverse_key_return retval = new DRLParser.reverse_key_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;

        Object id_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // src/main/resources/org/drools/lang/DRL.g:1673:2: ({...}? =>id= ID -> VK_REVERSE[$id] )
            // src/main/resources/org/drools/lang/DRL.g:1673:4: {...}? =>id= ID
            {
            if ( !(((validateIdentifierKey(DroolsSoftKeywords.REVERSE)))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "reverse_key", "(validateIdentifierKey(DroolsSoftKeywords.REVERSE))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_reverse_key5928); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id);

            if ( state.backtracking==0 ) {
              	emit(id, DroolsEditorType.KEYWORD);	
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1675:3: -> VK_REVERSE[$id]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VK_REVERSE, id));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "reverse_key"

    public static class result_key_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "result_key"
    // src/main/resources/org/drools/lang/DRL.g:1678:1: result_key : {...}? =>id= ID -> VK_RESULT[$id] ;
    public final DRLParser.result_key_return result_key() throws RecognitionException {
        DRLParser.result_key_return retval = new DRLParser.result_key_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;

        Object id_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // src/main/resources/org/drools/lang/DRL.g:1679:2: ({...}? =>id= ID -> VK_RESULT[$id] )
            // src/main/resources/org/drools/lang/DRL.g:1679:4: {...}? =>id= ID
            {
            if ( !(((validateIdentifierKey(DroolsSoftKeywords.RESULT)))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "result_key", "(validateIdentifierKey(DroolsSoftKeywords.RESULT))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_result_key5955); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id);

            if ( state.backtracking==0 ) {
              	emit(id, DroolsEditorType.KEYWORD);	
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1681:3: -> VK_RESULT[$id]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VK_RESULT, id));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "result_key"

    public static class end_key_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "end_key"
    // src/main/resources/org/drools/lang/DRL.g:1684:1: end_key : {...}? =>id= ID -> VK_END[$id] ;
    public final DRLParser.end_key_return end_key() throws RecognitionException {
        DRLParser.end_key_return retval = new DRLParser.end_key_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;

        Object id_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // src/main/resources/org/drools/lang/DRL.g:1685:2: ({...}? =>id= ID -> VK_END[$id] )
            // src/main/resources/org/drools/lang/DRL.g:1685:4: {...}? =>id= ID
            {
            if ( !(((validateIdentifierKey(DroolsSoftKeywords.END)))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "end_key", "(validateIdentifierKey(DroolsSoftKeywords.END))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_end_key5982); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id);

            if ( state.backtracking==0 ) {
              	emit(id, DroolsEditorType.KEYWORD);	
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1687:3: -> VK_END[$id]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VK_END, id));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "end_key"

    public static class not_end_key_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "not_end_key"
    // src/main/resources/org/drools/lang/DRL.g:1690:1: not_end_key : {...}? =>any= . ;
    public final DRLParser.not_end_key_return not_end_key() throws RecognitionException {
        DRLParser.not_end_key_return retval = new DRLParser.not_end_key_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token any=null;

        Object any_tree=null;

        try {
            // src/main/resources/org/drools/lang/DRL.g:1691:2: ({...}? =>any= . )
            // src/main/resources/org/drools/lang/DRL.g:1691:4: {...}? =>any= .
            {
            root_0 = (Object)adaptor.nil();

            if ( !((!(validateIdentifierKey(DroolsSoftKeywords.END)))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "not_end_key", "!(validateIdentifierKey(DroolsSoftKeywords.END))");
            }
            any=(Token)input.LT(1);
            matchAny(input); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            any_tree = (Object)adaptor.create(any);
            adaptor.addChild(root_0, any_tree);
            }
            if ( state.backtracking==0 ) {
              	emit(any, DroolsEditorType.CODE_CHUNK);	
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "not_end_key"

    public static class init_key_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "init_key"
    // src/main/resources/org/drools/lang/DRL.g:1695:1: init_key : {...}? =>id= ID -> VK_INIT[$id] ;
    public final DRLParser.init_key_return init_key() throws RecognitionException {
        DRLParser.init_key_return retval = new DRLParser.init_key_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token id=null;

        Object id_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // src/main/resources/org/drools/lang/DRL.g:1696:2: ({...}? =>id= ID -> VK_INIT[$id] )
            // src/main/resources/org/drools/lang/DRL.g:1696:4: {...}? =>id= ID
            {
            if ( !(((validateIdentifierKey(DroolsSoftKeywords.INIT)))) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "init_key", "(validateIdentifierKey(DroolsSoftKeywords.INIT))");
            }
            id=(Token)match(input,ID,FOLLOW_ID_in_init_key6029); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(id);

            if ( state.backtracking==0 ) {
              	emit(id, DroolsEditorType.KEYWORD);	
            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (Object)adaptor.nil();
            // 1698:3: -> VK_INIT[$id]
            {
                adaptor.addChild(root_0, (Object)adaptor.create(VK_INIT, id));

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "init_key"

    // $ANTLR start synpred1_DRL
    public final void synpred1_DRL_fragment() throws RecognitionException {   
        // src/main/resources/org/drools/lang/DRL.g:810:5: ( LEFT_PAREN or_key )
        // src/main/resources/org/drools/lang/DRL.g:810:6: LEFT_PAREN or_key
        {
        match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_synpred1_DRL2065); if (state.failed) return ;
        pushFollow(FOLLOW_or_key_in_synpred1_DRL2067);
        or_key();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred1_DRL

    // $ANTLR start synpred2_DRL
    public final void synpred2_DRL_fragment() throws RecognitionException {   
        // src/main/resources/org/drools/lang/DRL.g:818:5: ( or_key | DOUBLE_PIPE )
        int alt106=2;
        int LA106_0 = input.LA(1);

        if ( (LA106_0==ID) && (((validateIdentifierKey(DroolsSoftKeywords.OR))))) {
            alt106=1;
        }
        else if ( (LA106_0==DOUBLE_PIPE) ) {
            alt106=2;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            NoViableAltException nvae =
                new NoViableAltException("", 106, 0, input);

            throw nvae;
        }
        switch (alt106) {
            case 1 :
                // src/main/resources/org/drools/lang/DRL.g:818:6: or_key
                {
                pushFollow(FOLLOW_or_key_in_synpred2_DRL2134);
                or_key();

                state._fsp--;
                if (state.failed) return ;

                }
                break;
            case 2 :
                // src/main/resources/org/drools/lang/DRL.g:818:13: DOUBLE_PIPE
                {
                match(input,DOUBLE_PIPE,FOLLOW_DOUBLE_PIPE_in_synpred2_DRL2136); if (state.failed) return ;

                }
                break;

        }}
    // $ANTLR end synpred2_DRL

    // $ANTLR start synpred3_DRL
    public final void synpred3_DRL_fragment() throws RecognitionException {   
        // src/main/resources/org/drools/lang/DRL.g:827:5: ( LEFT_PAREN and_key )
        // src/main/resources/org/drools/lang/DRL.g:827:6: LEFT_PAREN and_key
        {
        match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_synpred3_DRL2193); if (state.failed) return ;
        pushFollow(FOLLOW_and_key_in_synpred3_DRL2195);
        and_key();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred3_DRL

    // $ANTLR start synpred4_DRL
    public final void synpred4_DRL_fragment() throws RecognitionException {   
        // src/main/resources/org/drools/lang/DRL.g:835:5: ( and_key | DOUBLE_AMPER )
        int alt107=2;
        int LA107_0 = input.LA(1);

        if ( (LA107_0==ID) && (((validateIdentifierKey(DroolsSoftKeywords.AND))))) {
            alt107=1;
        }
        else if ( (LA107_0==DOUBLE_AMPER) ) {
            alt107=2;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            NoViableAltException nvae =
                new NoViableAltException("", 107, 0, input);

            throw nvae;
        }
        switch (alt107) {
            case 1 :
                // src/main/resources/org/drools/lang/DRL.g:835:6: and_key
                {
                pushFollow(FOLLOW_and_key_in_synpred4_DRL2263);
                and_key();

                state._fsp--;
                if (state.failed) return ;

                }
                break;
            case 2 :
                // src/main/resources/org/drools/lang/DRL.g:835:14: DOUBLE_AMPER
                {
                match(input,DOUBLE_AMPER,FOLLOW_DOUBLE_AMPER_in_synpred4_DRL2265); if (state.failed) return ;

                }
                break;

        }}
    // $ANTLR end synpred4_DRL

    // $ANTLR start synpred5_DRL
    public final void synpred5_DRL_fragment() throws RecognitionException {   
        // src/main/resources/org/drools/lang/DRL.g:852:4: ( SEMICOLON )
        // src/main/resources/org/drools/lang/DRL.g:852:5: SEMICOLON
        {
        match(input,SEMICOLON,FOLLOW_SEMICOLON_in_synpred5_DRL2388); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred5_DRL

    // $ANTLR start synpred6_DRL
    public final void synpred6_DRL_fragment() throws RecognitionException {   
        // src/main/resources/org/drools/lang/DRL.g:858:12: ( LEFT_PAREN ( or_key | and_key ) )
        // src/main/resources/org/drools/lang/DRL.g:858:13: LEFT_PAREN ( or_key | and_key )
        {
        match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_synpred6_DRL2425); if (state.failed) return ;
        // src/main/resources/org/drools/lang/DRL.g:858:24: ( or_key | and_key )
        int alt108=2;
        int LA108_0 = input.LA(1);

        if ( (LA108_0==ID) && ((((validateIdentifierKey(DroolsSoftKeywords.AND)))||((validateIdentifierKey(DroolsSoftKeywords.OR)))))) {
            int LA108_1 = input.LA(2);

            if ( (((validateIdentifierKey(DroolsSoftKeywords.OR)))) ) {
                alt108=1;
            }
            else if ( (((validateIdentifierKey(DroolsSoftKeywords.AND)))) ) {
                alt108=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 108, 1, input);

                throw nvae;
            }
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            NoViableAltException nvae =
                new NoViableAltException("", 108, 0, input);

            throw nvae;
        }
        switch (alt108) {
            case 1 :
                // src/main/resources/org/drools/lang/DRL.g:858:25: or_key
                {
                pushFollow(FOLLOW_or_key_in_synpred6_DRL2428);
                or_key();

                state._fsp--;
                if (state.failed) return ;

                }
                break;
            case 2 :
                // src/main/resources/org/drools/lang/DRL.g:858:32: and_key
                {
                pushFollow(FOLLOW_and_key_in_synpred6_DRL2430);
                and_key();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        }
    }
    // $ANTLR end synpred6_DRL

    // $ANTLR start synpred7_DRL
    public final void synpred7_DRL_fragment() throws RecognitionException {   
        // src/main/resources/org/drools/lang/DRL.g:874:5: ( LEFT_PAREN ( or_key | and_key ) )
        // src/main/resources/org/drools/lang/DRL.g:874:6: LEFT_PAREN ( or_key | and_key )
        {
        match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_synpred7_DRL2553); if (state.failed) return ;
        // src/main/resources/org/drools/lang/DRL.g:874:17: ( or_key | and_key )
        int alt109=2;
        int LA109_0 = input.LA(1);

        if ( (LA109_0==ID) && ((((validateIdentifierKey(DroolsSoftKeywords.AND)))||((validateIdentifierKey(DroolsSoftKeywords.OR)))))) {
            int LA109_1 = input.LA(2);

            if ( (((validateIdentifierKey(DroolsSoftKeywords.OR)))) ) {
                alt109=1;
            }
            else if ( (((validateIdentifierKey(DroolsSoftKeywords.AND)))) ) {
                alt109=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 109, 1, input);

                throw nvae;
            }
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            NoViableAltException nvae =
                new NoViableAltException("", 109, 0, input);

            throw nvae;
        }
        switch (alt109) {
            case 1 :
                // src/main/resources/org/drools/lang/DRL.g:874:18: or_key
                {
                pushFollow(FOLLOW_or_key_in_synpred7_DRL2556);
                or_key();

                state._fsp--;
                if (state.failed) return ;

                }
                break;
            case 2 :
                // src/main/resources/org/drools/lang/DRL.g:874:25: and_key
                {
                pushFollow(FOLLOW_and_key_in_synpred7_DRL2558);
                and_key();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        }
    }
    // $ANTLR end synpred7_DRL

    // $ANTLR start synpred8_DRL
    public final void synpred8_DRL_fragment() throws RecognitionException {   
        // src/main/resources/org/drools/lang/DRL.g:1051:5: ( LEFT_PAREN )
        // src/main/resources/org/drools/lang/DRL.g:1051:6: LEFT_PAREN
        {
        match(input,LEFT_PAREN,FOLLOW_LEFT_PAREN_in_synpred8_DRL3386); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred8_DRL

    // Delegated rules

    public final boolean synpred3_DRL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred3_DRL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred2_DRL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_DRL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred5_DRL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred5_DRL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred7_DRL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred7_DRL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred6_DRL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred6_DRL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred1_DRL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_DRL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred4_DRL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred4_DRL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred8_DRL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred8_DRL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA1 dfa1 = new DFA1(this);
    protected DFA5 dfa5 = new DFA5(this);
    protected DFA12 dfa12 = new DFA12(this);
    protected DFA17 dfa17 = new DFA17(this);
    protected DFA28 dfa28 = new DFA28(this);
    protected DFA37 dfa37 = new DFA37(this);
    protected DFA70 dfa70 = new DFA70(this);
    protected DFA72 dfa72 = new DFA72(this);
    protected DFA82 dfa82 = new DFA82(this);
    protected DFA87 dfa87 = new DFA87(this);
    static final String DFA1_eotS =
        "\12\uffff";
    static final String DFA1_eofS =
        "\1\2\11\uffff";
    static final String DFA1_minS =
        "\2\122\2\uffff\1\0\5\uffff";
    static final String DFA1_maxS =
        "\1\122\1\163\2\uffff\1\0\5\uffff";
    static final String DFA1_acceptS =
        "\2\uffff\2\2\1\uffff\4\2\1\1";
    static final String DFA1_specialS =
        "\1\uffff\1\0\2\uffff\1\1\5\uffff}>";
    static final String[] DFA1_transitionS = {
            "\1\1",
            "\1\4\2\uffff\1\7\1\6\6\uffff\1\10\1\5\24\uffff\1\3",
            "",
            "",
            "\1\uffff",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA1_eot = DFA.unpackEncodedString(DFA1_eotS);
    static final short[] DFA1_eof = DFA.unpackEncodedString(DFA1_eofS);
    static final char[] DFA1_min = DFA.unpackEncodedStringToUnsignedChars(DFA1_minS);
    static final char[] DFA1_max = DFA.unpackEncodedStringToUnsignedChars(DFA1_maxS);
    static final short[] DFA1_accept = DFA.unpackEncodedString(DFA1_acceptS);
    static final short[] DFA1_special = DFA.unpackEncodedString(DFA1_specialS);
    static final short[][] DFA1_transition;

    static {
        int numStates = DFA1_transitionS.length;
        DFA1_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA1_transition[i] = DFA.unpackEncodedString(DFA1_transitionS[i]);
        }
    }

    class DFA1 extends DFA {

        public DFA1(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 1;
            this.eot = DFA1_eot;
            this.eof = DFA1_eof;
            this.min = DFA1_min;
            this.max = DFA1_max;
            this.accept = DFA1_accept;
            this.special = DFA1_special;
            this.transition = DFA1_transition;
        }
        public String getDescription() {
            return "396:4: ( package_statement )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA1_1 = input.LA(1);

                         
                        int index1_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA1_1==MISC) && ((((validateIdentifierKey(DroolsSoftKeywords.AGENDA) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.GROUP)))||((validateIdentifierKey(DroolsSoftKeywords.DATE) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.EXPIRES)))||((validateIdentifierKey(DroolsSoftKeywords.ACTIVATION) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.GROUP)))||((validateIdentifierKey(DroolsSoftKeywords.LOCK) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.ON) && validateLT(4, "-") && validateLT(5, DroolsSoftKeywords.ACTIVE)))||((validateIdentifierKey(DroolsSoftKeywords.NO) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.LOOP)))||((validateIdentifierKey(DroolsSoftKeywords.RULEFLOW) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.GROUP)))||((validateIdentifierKey(DroolsSoftKeywords.AUTO) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.FOCUS)))||((validateIdentifierKey(DroolsSoftKeywords.DATE) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.EFFECTIVE)))))) {s = 3;}

                        else if ( (LA1_1==ID) ) {s = 4;}

                        else if ( (LA1_1==INT) && ((((validateIdentifierKey(DroolsSoftKeywords.SALIENCE)))||((validateIdentifierKey(DroolsSoftKeywords.DURATION)))))) {s = 5;}

                        else if ( (LA1_1==LEFT_PAREN) && ((((validateIdentifierKey(DroolsSoftKeywords.SALIENCE)))||((validateIdentifierKey(DroolsSoftKeywords.ENABLED)))||((validateIdentifierKey(DroolsSoftKeywords.DURATION)))))) {s = 6;}

                        else if ( (LA1_1==STRING) ) {s = 7;}

                        else if ( (LA1_1==BOOL) && (((validateIdentifierKey(DroolsSoftKeywords.ENABLED))))) {s = 8;}

                         
                        input.seek(index1_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA1_4 = input.LA(1);

                         
                        int index1_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (((validateIdentifierKey(DroolsSoftKeywords.PACKAGE)))) ) {s = 9;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index1_4);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 1, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA5_eotS =
        "\20\uffff";
    static final String DFA5_eofS =
        "\20\uffff";
    static final String DFA5_minS =
        "\2\122\3\uffff\2\0\11\uffff";
    static final String DFA5_maxS =
        "\1\122\1\163\3\uffff\2\0\11\uffff";
    static final String DFA5_acceptS =
        "\2\uffff\3\1\2\uffff\1\1\1\6\1\10\1\11\1\2\1\3\1\4\1\5\1\7";
    static final String DFA5_specialS =
        "\1\uffff\1\0\3\uffff\1\1\1\2\11\uffff}>";
    static final String[] DFA5_transitionS = {
            "\1\1",
            "\1\6\2\uffff\1\5\1\4\6\uffff\1\7\1\3\24\uffff\1\2",
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
            return "454:1: statement options {k=2; } : ( rule_attribute | {...}? => function_import_statement | import_statement | global | function | {...}? => template | {...}? => type_declaration | rule | query );";
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
                        if ( (LA5_1==MISC) && ((((validateIdentifierKey(DroolsSoftKeywords.AGENDA) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.GROUP)))||((validateIdentifierKey(DroolsSoftKeywords.DATE) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.EXPIRES)))||((validateIdentifierKey(DroolsSoftKeywords.ACTIVATION) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.GROUP)))||((validateIdentifierKey(DroolsSoftKeywords.LOCK) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.ON) && validateLT(4, "-") && validateLT(5, DroolsSoftKeywords.ACTIVE)))||((validateIdentifierKey(DroolsSoftKeywords.NO) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.LOOP)))||((validateIdentifierKey(DroolsSoftKeywords.RULEFLOW) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.GROUP)))||((validateIdentifierKey(DroolsSoftKeywords.AUTO) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.FOCUS)))||((validateIdentifierKey(DroolsSoftKeywords.DATE) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.EFFECTIVE)))))) {s = 2;}

                        else if ( (LA5_1==INT) && ((((validateIdentifierKey(DroolsSoftKeywords.SALIENCE)))||((validateIdentifierKey(DroolsSoftKeywords.DURATION)))))) {s = 3;}

                        else if ( (LA5_1==LEFT_PAREN) && ((((validateIdentifierKey(DroolsSoftKeywords.SALIENCE)))||((validateIdentifierKey(DroolsSoftKeywords.ENABLED)))||((validateIdentifierKey(DroolsSoftKeywords.DURATION)))))) {s = 4;}

                        else if ( (LA5_1==STRING) && ((!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))))) {s = 5;}

                        else if ( (LA5_1==ID) && ((!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!((((((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE)))))))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE)))))))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE)))))))||!((((((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE)))))))||!((((((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!((((((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||!((((((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||!((((((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE)))))))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE)))))))||!((((((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!((((((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!((((((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||!((((((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!((((((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE)))))))||!((((((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!((((((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE)))))))||!((((((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||!((((((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!((((((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!((((((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!((((((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE)))))))||!((((((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!((((((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE)))))))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||!((((((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!((((((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||!((((((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE)))))))||!((((((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE)))))))||!((((((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE)))))))||!((((((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!((((((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE)))))))||!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))))) {s = 6;}

                        else if ( (LA5_1==BOOL) && (((validateIdentifierKey(DroolsSoftKeywords.ENABLED))))) {s = 7;}

                         
                        input.seek(index5_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA5_5 = input.LA(1);

                         
                        int index5_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))) ) {s = 7;}

                        else if ( (((validateLT(1, DroolsSoftKeywords.TEMPLATE)))) ) {s = 8;}

                        else if ( (!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))) ) {s = 9;}

                        else if ( (((validateIdentifierKey(DroolsSoftKeywords.QUERY)))) ) {s = 10;}

                         
                        input.seek(index5_5);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA5_6 = input.LA(1);

                         
                        int index5_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( ((((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))) ) {s = 11;}

                        else if ( (((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))) ) {s = 12;}

                        else if ( (((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))) ) {s = 13;}

                        else if ( (((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))) ) {s = 14;}

                        else if ( (((validateLT(1, DroolsSoftKeywords.TEMPLATE)))) ) {s = 8;}

                        else if ( ((((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))) ) {s = 15;}

                        else if ( (!(((((validateIdentifierKey(DroolsSoftKeywords.QUERY)))||((validateIdentifierKey(DroolsSoftKeywords.GLOBAL)))||((validateIdentifierKey(DroolsSoftKeywords.IMPORT)))||(((validateLT(1, "import") && validateLT(2, "function") ))&&((validateIdentifierKey(DroolsSoftKeywords.IMPORT))))||(((validateLT(1, DroolsSoftKeywords.DECLARE)))&&((validateIdentifierKey(DroolsSoftKeywords.DECLARE))))||((validateIdentifierKey(DroolsSoftKeywords.FUNCTION)))||((validateLT(1, DroolsSoftKeywords.TEMPLATE))))))) ) {s = 9;}

                        else if ( (((validateIdentifierKey(DroolsSoftKeywords.QUERY)))) ) {s = 10;}

                         
                        input.seek(index5_6);
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
    static final String DFA12_eotS =
        "\16\uffff";
    static final String DFA12_eofS =
        "\16\uffff";
    static final String DFA12_minS =
        "\2\122\1\uffff\1\122\1\uffff\1\157\3\122\1\157\1\122\1\126\1\157"+
        "\1\122";
    static final String DFA12_maxS =
        "\1\126\1\130\1\uffff\1\156\1\uffff\1\157\1\156\1\122\1\156\1\157"+
        "\2\156\1\157\1\156";
    static final String DFA12_acceptS =
        "\2\uffff\1\2\1\uffff\1\1\11\uffff";
    static final String DFA12_specialS =
        "\16\uffff}>";
    static final String[] DFA12_transitionS = {
            "\1\2\3\uffff\1\1",
            "\1\3\3\uffff\1\2\1\uffff\1\4",
            "",
            "\1\6\1\7\2\uffff\1\2\2\4\1\uffff\1\2\23\uffff\1\5",
            "",
            "\1\10",
            "\2\2\2\uffff\1\2\2\4\1\uffff\1\2\23\uffff\1\11",
            "\1\12",
            "\1\4\3\uffff\1\2\2\4\25\uffff\1\5",
            "\1\13",
            "\1\4\1\7\2\uffff\1\2\27\uffff\1\14",
            "\1\2\2\4\25\uffff\1\11",
            "\1\15",
            "\1\4\3\uffff\1\2\27\uffff\1\14"
    };

    static final short[] DFA12_eot = DFA.unpackEncodedString(DFA12_eotS);
    static final short[] DFA12_eof = DFA.unpackEncodedString(DFA12_eofS);
    static final char[] DFA12_min = DFA.unpackEncodedStringToUnsignedChars(DFA12_minS);
    static final char[] DFA12_max = DFA.unpackEncodedStringToUnsignedChars(DFA12_maxS);
    static final short[] DFA12_accept = DFA.unpackEncodedString(DFA12_acceptS);
    static final short[] DFA12_special = DFA.unpackEncodedString(DFA12_specialS);
    static final short[][] DFA12_transition;

    static {
        int numStates = DFA12_transitionS.length;
        DFA12_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA12_transition[i] = DFA.unpackEncodedString(DFA12_transitionS[i]);
        }
    }

    class DFA12 extends DFA {

        public DFA12(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 12;
            this.eot = DFA12_eot;
            this.eof = DFA12_eof;
            this.min = DFA12_min;
            this.max = DFA12_max;
            this.accept = DFA12_accept;
            this.special = DFA12_special;
            this.transition = DFA12_transition;
        }
        public String getDescription() {
            return "525:3: ( parameters )?";
        }
    }
    static final String DFA17_eotS =
        "\6\uffff";
    static final String DFA17_eofS =
        "\6\uffff";
    static final String DFA17_minS =
        "\2\122\1\157\2\uffff\1\122";
    static final String DFA17_maxS =
        "\1\122\1\156\1\157\2\uffff\1\156";
    static final String DFA17_acceptS =
        "\3\uffff\1\2\1\1\1\uffff";
    static final String DFA17_specialS =
        "\6\uffff}>";
    static final String[] DFA17_transitionS = {
            "\1\1",
            "\2\4\3\uffff\2\3\25\uffff\1\2",
            "\1\5",
            "",
            "",
            "\1\4\4\uffff\2\3\25\uffff\1\2"
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
            return "550:4: ( data_type )?";
        }
    }
    static final String DFA28_eotS =
        "\12\uffff";
    static final String DFA28_eofS =
        "\12\uffff";
    static final String DFA28_minS =
        "\2\122\3\uffff\1\0\4\uffff";
    static final String DFA28_maxS =
        "\1\160\1\163\3\uffff\1\0\4\uffff";
    static final String DFA28_acceptS =
        "\2\uffff\2\2\1\1\1\uffff\4\2";
    static final String DFA28_specialS =
        "\1\1\1\2\3\uffff\1\0\4\uffff}>";
    static final String[] DFA28_transitionS = {
            "\1\1\6\uffff\1\2\2\uffff\1\2\23\uffff\1\2",
            "\1\4\2\uffff\1\5\1\7\3\uffff\1\11\2\uffff\1\6\1\10\24\uffff"+
            "\1\3",
            "",
            "",
            "",
            "\1\uffff",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA28_eot = DFA.unpackEncodedString(DFA28_eotS);
    static final short[] DFA28_eof = DFA.unpackEncodedString(DFA28_eofS);
    static final char[] DFA28_min = DFA.unpackEncodedStringToUnsignedChars(DFA28_minS);
    static final char[] DFA28_max = DFA.unpackEncodedStringToUnsignedChars(DFA28_maxS);
    static final short[] DFA28_accept = DFA.unpackEncodedString(DFA28_acceptS);
    static final short[] DFA28_special = DFA.unpackEncodedString(DFA28_specialS);
    static final short[][] DFA28_transition;

    static {
        int numStates = DFA28_transitionS.length;
        DFA28_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA28_transition[i] = DFA.unpackEncodedString(DFA28_transitionS[i]);
        }
    }

    class DFA28 extends DFA {

        public DFA28(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 28;
            this.eot = DFA28_eot;
            this.eof = DFA28_eof;
            this.min = DFA28_min;
            this.max = DFA28_max;
            this.accept = DFA28_accept;
            this.special = DFA28_special;
            this.transition = DFA28_transition;
        }
        public String getDescription() {
            return "640:3: ( extend_key rule_id )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA28_5 = input.LA(1);

                         
                        int index28_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (((validateIdentifierKey(DroolsSoftKeywords.EXTEND)))) ) {s = 4;}

                        else if ( (((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))) ) {s = 9;}

                         
                        input.seek(index28_5);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA28_0 = input.LA(1);

                         
                        int index28_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA28_0==ID) && ((((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateIdentifierKey(DroolsSoftKeywords.ACTIVATION) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.GROUP)))||((validateIdentifierKey(DroolsSoftKeywords.EXTEND)))||((validateIdentifierKey(DroolsSoftKeywords.NO) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.LOOP)))||((validateIdentifierKey(DroolsSoftKeywords.DURATION)))||((validateIdentifierKey(DroolsSoftKeywords.DATE) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.EFFECTIVE)))||((validateIdentifierKey(DroolsSoftKeywords.AGENDA) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.GROUP)))||((validateIdentifierKey(DroolsSoftKeywords.DATE) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.EXPIRES)))||((validateIdentifierKey(DroolsSoftKeywords.LOCK) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.ON) && validateLT(4, "-") && validateLT(5, DroolsSoftKeywords.ACTIVE)))||((validateIdentifierKey(DroolsSoftKeywords.SALIENCE)))||((validateIdentifierKey(DroolsSoftKeywords.RULEFLOW) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.GROUP)))||((validateIdentifierKey(DroolsSoftKeywords.ENABLED)))||((validateIdentifierKey(DroolsSoftKeywords.ATTRIBUTES)))||((validateIdentifierKey(DroolsSoftKeywords.AUTO) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.FOCUS)))))) {s = 1;}

                        else if ( (LA28_0==AT||LA28_0==WHEN||LA28_0==THEN) ) {s = 2;}

                         
                        input.seek(index28_0);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA28_1 = input.LA(1);

                         
                        int index28_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA28_1==MISC) && ((((validateIdentifierKey(DroolsSoftKeywords.AGENDA) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.GROUP)))||((validateIdentifierKey(DroolsSoftKeywords.DATE) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.EXPIRES)))||((validateIdentifierKey(DroolsSoftKeywords.ACTIVATION) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.GROUP)))||((validateIdentifierKey(DroolsSoftKeywords.LOCK) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.ON) && validateLT(4, "-") && validateLT(5, DroolsSoftKeywords.ACTIVE)))||((validateIdentifierKey(DroolsSoftKeywords.NO) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.LOOP)))||((validateIdentifierKey(DroolsSoftKeywords.RULEFLOW) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.GROUP)))||((validateIdentifierKey(DroolsSoftKeywords.AUTO) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.FOCUS)))||((validateIdentifierKey(DroolsSoftKeywords.DATE) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.EFFECTIVE)))))) {s = 3;}

                        else if ( (LA28_1==ID) && (((validateIdentifierKey(DroolsSoftKeywords.EXTEND))))) {s = 4;}

                        else if ( (LA28_1==STRING) && ((((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateIdentifierKey(DroolsSoftKeywords.EXTEND)))))) {s = 5;}

                        else if ( (LA28_1==BOOL) && (((validateIdentifierKey(DroolsSoftKeywords.ENABLED))))) {s = 6;}

                        else if ( (LA28_1==LEFT_PAREN) && ((((validateIdentifierKey(DroolsSoftKeywords.SALIENCE)))||((validateIdentifierKey(DroolsSoftKeywords.ENABLED)))||((validateIdentifierKey(DroolsSoftKeywords.DURATION)))))) {s = 7;}

                        else if ( (LA28_1==INT) && ((((validateIdentifierKey(DroolsSoftKeywords.SALIENCE)))||((validateIdentifierKey(DroolsSoftKeywords.DURATION)))))) {s = 8;}

                        else if ( (LA28_1==COLON) && (((validateIdentifierKey(DroolsSoftKeywords.ATTRIBUTES))))) {s = 9;}

                         
                        input.seek(index28_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 28, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA37_eotS =
        "\16\uffff";
    static final String DFA37_eofS =
        "\16\uffff";
    static final String DFA37_minS =
        "\1\122\1\0\14\uffff";
    static final String DFA37_maxS =
        "\1\122\1\0\14\uffff";
    static final String DFA37_acceptS =
        "\2\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14";
    static final String DFA37_specialS =
        "\1\0\1\1\14\uffff}>";
    static final String[] DFA37_transitionS = {
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
            ""
    };

    static final short[] DFA37_eot = DFA.unpackEncodedString(DFA37_eotS);
    static final short[] DFA37_eof = DFA.unpackEncodedString(DFA37_eofS);
    static final char[] DFA37_min = DFA.unpackEncodedStringToUnsignedChars(DFA37_minS);
    static final char[] DFA37_max = DFA.unpackEncodedStringToUnsignedChars(DFA37_maxS);
    static final short[] DFA37_accept = DFA.unpackEncodedString(DFA37_acceptS);
    static final short[] DFA37_special = DFA.unpackEncodedString(DFA37_specialS);
    static final short[][] DFA37_transition;

    static {
        int numStates = DFA37_transitionS.length;
        DFA37_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA37_transition[i] = DFA.unpackEncodedString(DFA37_transitionS[i]);
        }
    }

    class DFA37 extends DFA {

        public DFA37(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 37;
            this.eot = DFA37_eot;
            this.eof = DFA37_eof;
            this.min = DFA37_min;
            this.max = DFA37_max;
            this.accept = DFA37_accept;
            this.special = DFA37_special;
            this.transition = DFA37_transition;
        }
        public String getDescription() {
            return "709:1: rule_attribute : ( salience | no_loop | agenda_group | duration | activation_group | auto_focus | date_effective | date_expires | enabled | ruleflow_group | lock_on_active | dialect );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA37_0 = input.LA(1);

                         
                        int index37_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA37_0==ID) && ((((validateIdentifierKey(DroolsSoftKeywords.AGENDA) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.GROUP)))||((validateIdentifierKey(DroolsSoftKeywords.DATE) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.EXPIRES)))||((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))||((validateIdentifierKey(DroolsSoftKeywords.ACTIVATION) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.GROUP)))||((validateIdentifierKey(DroolsSoftKeywords.LOCK) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.ON) && validateLT(4, "-") && validateLT(5, DroolsSoftKeywords.ACTIVE)))||((validateIdentifierKey(DroolsSoftKeywords.SALIENCE)))||((validateIdentifierKey(DroolsSoftKeywords.NO) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.LOOP)))||((validateIdentifierKey(DroolsSoftKeywords.RULEFLOW) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.GROUP)))||((validateIdentifierKey(DroolsSoftKeywords.ENABLED)))||((validateIdentifierKey(DroolsSoftKeywords.DURATION)))||((validateIdentifierKey(DroolsSoftKeywords.AUTO) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.FOCUS)))||((validateIdentifierKey(DroolsSoftKeywords.DATE) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.EFFECTIVE)))))) {s = 1;}

                         
                        input.seek(index37_0);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA37_1 = input.LA(1);

                         
                        int index37_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (((validateIdentifierKey(DroolsSoftKeywords.SALIENCE)))) ) {s = 2;}

                        else if ( (((validateIdentifierKey(DroolsSoftKeywords.NO) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.LOOP)))) ) {s = 3;}

                        else if ( (((validateIdentifierKey(DroolsSoftKeywords.AGENDA) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.GROUP)))) ) {s = 4;}

                        else if ( (((validateIdentifierKey(DroolsSoftKeywords.DURATION)))) ) {s = 5;}

                        else if ( (((validateIdentifierKey(DroolsSoftKeywords.ACTIVATION) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.GROUP)))) ) {s = 6;}

                        else if ( (((validateIdentifierKey(DroolsSoftKeywords.AUTO) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.FOCUS)))) ) {s = 7;}

                        else if ( (((validateIdentifierKey(DroolsSoftKeywords.DATE) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.EFFECTIVE)))) ) {s = 8;}

                        else if ( (((validateIdentifierKey(DroolsSoftKeywords.DATE) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.EXPIRES)))) ) {s = 9;}

                        else if ( (((validateIdentifierKey(DroolsSoftKeywords.ENABLED)))) ) {s = 10;}

                        else if ( (((validateIdentifierKey(DroolsSoftKeywords.RULEFLOW) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.GROUP)))) ) {s = 11;}

                        else if ( (((validateIdentifierKey(DroolsSoftKeywords.LOCK) && validateLT(2, "-") && validateLT(3, DroolsSoftKeywords.ON) && validateLT(4, "-") && validateLT(5, DroolsSoftKeywords.ACTIVE)))) ) {s = 12;}

                        else if ( (((validateIdentifierKey(DroolsSoftKeywords.DIALECT)))) ) {s = 13;}

                         
                        input.seek(index37_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 37, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA70_eotS =
        "\13\uffff";
    static final String DFA70_eofS =
        "\13\uffff";
    static final String DFA70_minS =
        "\1\121\1\0\11\uffff";
    static final String DFA70_maxS =
        "\1\160\1\0\11\uffff";
    static final String DFA70_acceptS =
        "\2\uffff\1\2\7\uffff\1\1";
    static final String DFA70_specialS =
        "\1\uffff\1\0\11\uffff}>";
    static final String[] DFA70_transitionS = {
            "\3\2\2\uffff\1\1\2\2\6\uffff\2\2\17\uffff\1\2",
            "\1\uffff",
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
            return "1051:3: ( ( LEFT_PAREN )=>args= paren_chunk )?";
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
                        if ( (synpred8_DRL()) ) {s = 10;}

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
        "\14\uffff";
    static final String DFA72_eofS =
        "\14\uffff";
    static final String DFA72_minS =
        "\1\121\1\0\12\uffff";
    static final String DFA72_maxS =
        "\1\160\1\0\12\uffff";
    static final String DFA72_acceptS =
        "\2\uffff\1\2\1\3\7\uffff\1\1";
    static final String DFA72_specialS =
        "\1\uffff\1\0\12\uffff}>";
    static final String[] DFA72_transitionS = {
            "\3\3\2\uffff\1\1\2\3\6\uffff\2\3\15\uffff\1\2\1\uffff\1\3",
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
            return "1065:4: ({...}? paren_chunk | square_chunk )?";
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
                        if ( ((input.LA(1) == LEFT_PAREN)) ) {s = 11;}

                        else if ( (true) ) {s = 3;}

                         
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
    static final String DFA82_eotS =
        "\17\uffff";
    static final String DFA82_eofS =
        "\17\uffff";
    static final String DFA82_minS =
        "\2\122\13\uffff\1\0\1\uffff";
    static final String DFA82_maxS =
        "\1\126\1\156\13\uffff\1\0\1\uffff";
    static final String DFA82_acceptS =
        "\2\uffff\1\3\1\2\12\uffff\1\1";
    static final String DFA82_specialS =
        "\15\uffff\1\0\1\uffff}>";
    static final String[] DFA82_transitionS = {
            "\1\1\3\uffff\1\2",
            "\2\3\2\uffff\1\15\3\uffff\1\3\13\uffff\6\3\2\uffff\1\3",
            "",
            "",
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
            ""
    };

    static final short[] DFA82_eot = DFA.unpackEncodedString(DFA82_eotS);
    static final short[] DFA82_eof = DFA.unpackEncodedString(DFA82_eofS);
    static final char[] DFA82_min = DFA.unpackEncodedStringToUnsignedChars(DFA82_minS);
    static final char[] DFA82_max = DFA.unpackEncodedStringToUnsignedChars(DFA82_maxS);
    static final short[] DFA82_accept = DFA.unpackEncodedString(DFA82_acceptS);
    static final short[] DFA82_special = DFA.unpackEncodedString(DFA82_specialS);
    static final short[][] DFA82_transition;

    static {
        int numStates = DFA82_transitionS.length;
        DFA82_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA82_transition[i] = DFA.unpackEncodedString(DFA82_transitionS[i]);
        }
    }

    class DFA82 extends DFA {

        public DFA82(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 82;
            this.eot = DFA82_eot;
            this.eof = DFA82_eof;
            this.min = DFA82_min;
            this.max = DFA82_max;
            this.accept = DFA82_accept;
            this.special = DFA82_special;
            this.transition = DFA82_transition;
        }
        public String getDescription() {
            return "1138:1: unary_constr options {k=2; } : ( eval_key paren_chunk | field_constraint | LEFT_PAREN or_constr RIGHT_PAREN );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA82_13 = input.LA(1);

                         
                        int index82_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (((validateIdentifierKey(DroolsSoftKeywords.EVAL)))) ) {s = 14;}

                        else if ( (true) ) {s = 3;}

                         
                        input.seek(index82_13);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 82, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA87_eotS =
        "\50\uffff";
    static final String DFA87_eofS =
        "\50\uffff";
    static final String DFA87_minS =
        "\2\122\10\uffff\1\122\5\uffff\1\4\6\uffff\1\0\6\uffff\7\0\3\uffff";
    static final String DFA87_maxS =
        "\1\153\1\156\10\uffff\1\156\5\uffff\1\175\6\uffff\1\0\6\uffff\7"+
        "\0\3\uffff";
    static final String DFA87_acceptS =
        "\2\uffff\1\2\5\uffff\1\3\1\2\7\uffff\2\2\5\uffff\1\2\14\uffff\1"+
        "\2\1\uffff\1\1";
    static final String DFA87_specialS =
        "\1\0\1\1\10\uffff\1\2\5\uffff\1\3\6\uffff\1\4\6\uffff\1\5\1\6\1"+
        "\7\1\10\1\11\1\12\1\13\3\uffff}>";
    static final String[] DFA87_transitionS = {
            "\1\1\3\uffff\1\10\17\uffff\6\2",
            "\1\12\2\uffff\1\11\1\20\6\uffff\2\11\15\uffff\3\11",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\30\1\22\1\uffff\1\30\1\27\2\22\4\uffff\2\30\2\22\13\uffff"+
            "\2\30\1\21",
            "",
            "",
            "",
            "",
            "",
            "\116\45\1\36\2\45\1\37\1\44\6\45\1\42\1\40\15\45\1\41\1\43"+
            "\20\45",
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
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            ""
    };

    static final short[] DFA87_eot = DFA.unpackEncodedString(DFA87_eotS);
    static final short[] DFA87_eof = DFA.unpackEncodedString(DFA87_eofS);
    static final char[] DFA87_min = DFA.unpackEncodedStringToUnsignedChars(DFA87_minS);
    static final char[] DFA87_max = DFA.unpackEncodedStringToUnsignedChars(DFA87_maxS);
    static final short[] DFA87_accept = DFA.unpackEncodedString(DFA87_acceptS);
    static final short[] DFA87_special = DFA.unpackEncodedString(DFA87_specialS);
    static final short[][] DFA87_transition;

    static {
        int numStates = DFA87_transitionS.length;
        DFA87_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA87_transition[i] = DFA.unpackEncodedString(DFA87_transitionS[i]);
        }
    }

    class DFA87 extends DFA {

        public DFA87(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 87;
            this.eot = DFA87_eot;
            this.eof = DFA87_eof;
            this.min = DFA87_min;
            this.max = DFA87_max;
            this.accept = DFA87_accept;
            this.special = DFA87_special;
            this.transition = DFA87_transition;
        }
        public String getDescription() {
            return "1202:1: constraint_expression options {k=3; } : ( compound_operator | simple_operator | LEFT_PAREN or_restr_connective RIGHT_PAREN );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA87_0 = input.LA(1);

                         
                        int index87_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA87_0==ID) && ((((validateIdentifierKey(DroolsSoftKeywords.IN)))||((validateIdentifierKey(DroolsSoftKeywords.NOT)))||((isPluggableEvaluator(false)))))) {s = 1;}

                        else if ( ((LA87_0>=EQUAL && LA87_0<=NOT_EQUAL)) ) {s = 2;}

                        else if ( (LA87_0==LEFT_PAREN) ) {s = 8;}

                         
                        input.seek(index87_0);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA87_1 = input.LA(1);

                         
                        int index87_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA87_1==STRING||(LA87_1>=BOOL && LA87_1<=INT)||(LA87_1>=FLOAT && LA87_1<=LEFT_SQUARE)) && (((isPluggableEvaluator(false))))) {s = 9;}

                        else if ( (LA87_1==ID) && ((((validateIdentifierKey(DroolsSoftKeywords.NOT)))||((isPluggableEvaluator(false)))))) {s = 10;}

                        else if ( (LA87_1==LEFT_PAREN) && ((((validateIdentifierKey(DroolsSoftKeywords.IN)))||((isPluggableEvaluator(false)))))) {s = 16;}

                         
                        input.seek(index87_1);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA87_10 = input.LA(1);

                         
                        int index87_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA87_10==LEFT_SQUARE) && ((((validateIdentifierKey(DroolsSoftKeywords.NOT)))||((isPluggableEvaluator(false)))))) {s = 17;}

                        else if ( (LA87_10==DOT||(LA87_10>=COMMA && LA87_10<=RIGHT_PAREN)||(LA87_10>=DOUBLE_PIPE && LA87_10<=DOUBLE_AMPER)) && (((isPluggableEvaluator(false))))) {s = 18;}

                        else if ( (LA87_10==LEFT_PAREN) && (((validateIdentifierKey(DroolsSoftKeywords.NOT))))) {s = 23;}

                        else if ( (LA87_10==ID||LA87_10==STRING||(LA87_10>=BOOL && LA87_10<=INT)||(LA87_10>=FLOAT && LA87_10<=NULL)) && (((validateIdentifierKey(DroolsSoftKeywords.NOT))))) {s = 24;}

                         
                        input.seek(index87_10);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA87_16 = input.LA(1);

                         
                        int index87_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA87_16==ID) && ((((validateIdentifierKey(DroolsSoftKeywords.IN)))||((isPluggableEvaluator(false)))))) {s = 30;}

                        else if ( (LA87_16==STRING) && ((((validateIdentifierKey(DroolsSoftKeywords.IN)))||((isPluggableEvaluator(false)))))) {s = 31;}

                        else if ( (LA87_16==INT) && ((((validateIdentifierKey(DroolsSoftKeywords.IN)))||((isPluggableEvaluator(false)))))) {s = 32;}

                        else if ( (LA87_16==FLOAT) && ((((validateIdentifierKey(DroolsSoftKeywords.IN)))||((isPluggableEvaluator(false)))))) {s = 33;}

                        else if ( (LA87_16==BOOL) && ((((validateIdentifierKey(DroolsSoftKeywords.IN)))||((isPluggableEvaluator(false)))))) {s = 34;}

                        else if ( (LA87_16==NULL) && ((((validateIdentifierKey(DroolsSoftKeywords.IN)))||((isPluggableEvaluator(false)))))) {s = 35;}

                        else if ( (LA87_16==LEFT_PAREN) && ((((validateIdentifierKey(DroolsSoftKeywords.IN)))||((isPluggableEvaluator(false)))))) {s = 36;}

                        else if ( ((LA87_16>=VT_COMPILATION_UNIT && LA87_16<=SEMICOLON)||(LA87_16>=DOT && LA87_16<=DOT_STAR)||(LA87_16>=COMMA && LA87_16<=WHEN)||(LA87_16>=DOUBLE_PIPE && LA87_16<=NOT_EQUAL)||(LA87_16>=LEFT_SQUARE && LA87_16<=MULTI_LINE_COMMENT)) && (((isPluggableEvaluator(false))))) {s = 37;}

                         
                        input.seek(index87_16);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA87_23 = input.LA(1);

                         
                        int index87_23 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (((validateIdentifierKey(DroolsSoftKeywords.NOT)))) ) {s = 39;}

                        else if ( (((validateIdentifierKey(DroolsSoftKeywords.NOT)))) ) {s = 37;}

                         
                        input.seek(index87_23);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA87_30 = input.LA(1);

                         
                        int index87_30 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (((validateIdentifierKey(DroolsSoftKeywords.IN)))) ) {s = 39;}

                        else if ( (((isPluggableEvaluator(false)))) ) {s = 37;}

                         
                        input.seek(index87_30);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA87_31 = input.LA(1);

                         
                        int index87_31 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (((validateIdentifierKey(DroolsSoftKeywords.IN)))) ) {s = 39;}

                        else if ( (((isPluggableEvaluator(false)))) ) {s = 37;}

                         
                        input.seek(index87_31);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA87_32 = input.LA(1);

                         
                        int index87_32 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (((validateIdentifierKey(DroolsSoftKeywords.IN)))) ) {s = 39;}

                        else if ( (((isPluggableEvaluator(false)))) ) {s = 37;}

                         
                        input.seek(index87_32);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA87_33 = input.LA(1);

                         
                        int index87_33 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (((validateIdentifierKey(DroolsSoftKeywords.IN)))) ) {s = 39;}

                        else if ( (((isPluggableEvaluator(false)))) ) {s = 37;}

                         
                        input.seek(index87_33);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA87_34 = input.LA(1);

                         
                        int index87_34 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (((validateIdentifierKey(DroolsSoftKeywords.IN)))) ) {s = 39;}

                        else if ( (((isPluggableEvaluator(false)))) ) {s = 37;}

                         
                        input.seek(index87_34);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA87_35 = input.LA(1);

                         
                        int index87_35 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (((validateIdentifierKey(DroolsSoftKeywords.IN)))) ) {s = 39;}

                        else if ( (((isPluggableEvaluator(false)))) ) {s = 37;}

                         
                        input.seek(index87_35);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA87_36 = input.LA(1);

                         
                        int index87_36 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (((validateIdentifierKey(DroolsSoftKeywords.IN)))) ) {s = 39;}

                        else if ( (((isPluggableEvaluator(false)))) ) {s = 37;}

                         
                        input.seek(index87_36);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 87, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_package_statement_in_compilation_unit384 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_statement_in_compilation_unit389 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_EOF_in_compilation_unit394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_package_key_in_package_statement449 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_package_id_in_package_statement453 = new BitSet(new long[]{0x0000000000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_SEMICOLON_in_package_statement455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_package_id482 = new BitSet(new long[]{0x0000000000000002L,0x0000000000080000L});
    public static final BitSet FOLLOW_DOT_in_package_id488 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_ID_in_package_id492 = new BitSet(new long[]{0x0000000000000002L,0x0000000000080000L});
    public static final BitSet FOLLOW_rule_attribute_in_statement530 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_function_import_statement_in_statement537 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_import_statement_in_statement543 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_global_in_statement549 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_function_in_statement555 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_template_in_statement563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_declaration_in_statement571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule_in_statement576 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_query_in_statement581 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_import_key_in_import_statement603 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_import_name_in_import_statement605 = new BitSet(new long[]{0x0000000000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_SEMICOLON_in_import_statement608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_import_key_in_function_import_statement646 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_function_key_in_function_import_statement648 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_import_name_in_function_import_statement650 = new BitSet(new long[]{0x0000000000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_SEMICOLON_in_function_import_statement653 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_import_name687 = new BitSet(new long[]{0x0000000000000002L,0x0000000000180000L});
    public static final BitSet FOLLOW_DOT_in_import_name693 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_ID_in_import_name697 = new BitSet(new long[]{0x0000000000000002L,0x0000000000180000L});
    public static final BitSet FOLLOW_DOT_STAR_in_import_name704 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_global_key_in_global744 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_data_type_in_global746 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_global_id_in_global748 = new BitSet(new long[]{0x0000000000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_SEMICOLON_in_global750 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_global_id779 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_function_key_in_function811 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_data_type_in_function813 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_function_id_in_function816 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_parameters_in_function818 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000000L});
    public static final BitSet FOLLOW_curly_chunk_in_function820 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_function_id850 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_query_key_in_query882 = new BitSet(new long[]{0x0000000000000000L,0x0000000000240000L});
    public static final BitSet FOLLOW_query_id_in_query884 = new BitSet(new long[]{0x0000000000000000L,0x0000000000440000L});
    public static final BitSet FOLLOW_parameters_in_query892 = new BitSet(new long[]{0x0000000000000000L,0x0000000000440000L});
    public static final BitSet FOLLOW_normal_lhs_block_in_query901 = new BitSet(new long[]{0x0000000000000000L,0x0000000000440000L});
    public static final BitSet FOLLOW_end_key_in_query908 = new BitSet(new long[]{0x0000000000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_SEMICOLON_in_query910 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_query_id945 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_query_id961 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_parameters980 = new BitSet(new long[]{0x0000000000000000L,0x0000000001040000L});
    public static final BitSet FOLLOW_param_definition_in_parameters989 = new BitSet(new long[]{0x0000000000000000L,0x0000000001800000L});
    public static final BitSet FOLLOW_COMMA_in_parameters992 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_param_definition_in_parameters996 = new BitSet(new long[]{0x0000000000000000L,0x0000000001800000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_parameters1005 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_data_type_in_param_definition1031 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_argument_in_param_definition1034 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_argument1045 = new BitSet(new long[]{0x0000000000000002L,0x0000400000000000L});
    public static final BitSet FOLLOW_dimension_definition_in_argument1051 = new BitSet(new long[]{0x0000000000000002L,0x0000400000000000L});
    public static final BitSet FOLLOW_declare_key_in_type_declaration1074 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_type_declare_id_in_type_declaration1077 = new BitSet(new long[]{0x0000000000000000L,0x0000000002440000L});
    public static final BitSet FOLLOW_decl_metadata_in_type_declaration1081 = new BitSet(new long[]{0x0000000000000000L,0x0000000002440000L});
    public static final BitSet FOLLOW_decl_field_in_type_declaration1086 = new BitSet(new long[]{0x0000000000000000L,0x0000000000440000L});
    public static final BitSet FOLLOW_end_key_in_type_declaration1091 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_type_declare_id1123 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AT_in_decl_metadata1142 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_ID_in_decl_metadata1150 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_paren_chunk_in_decl_metadata1157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_decl_field1180 = new BitSet(new long[]{0x0000000000000000L,0x000000000C000000L});
    public static final BitSet FOLLOW_decl_field_initialization_in_decl_field1186 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_COLON_in_decl_field1192 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_data_type_in_decl_field1198 = new BitSet(new long[]{0x0000000000000002L,0x0000000002000000L});
    public static final BitSet FOLLOW_decl_metadata_in_decl_field1202 = new BitSet(new long[]{0x0000000000000002L,0x0000000002000000L});
    public static final BitSet FOLLOW_EQUALS_in_decl_field_initialization1230 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_paren_chunk_in_decl_field_initialization1236 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_template_key_in_template1273 = new BitSet(new long[]{0x0000000000000000L,0x0000000000240000L});
    public static final BitSet FOLLOW_template_id_in_template1275 = new BitSet(new long[]{0x0000000000000000L,0x0000000000060000L});
    public static final BitSet FOLLOW_SEMICOLON_in_template1282 = new BitSet(new long[]{0x0000000000000000L,0x0000000000060000L});
    public static final BitSet FOLLOW_template_slot_in_template1290 = new BitSet(new long[]{0x0000000000000000L,0x0000000000460000L});
    public static final BitSet FOLLOW_end_key_in_template1297 = new BitSet(new long[]{0x0000000000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_SEMICOLON_in_template1301 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_template_id1334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_template_id1350 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_data_type_in_template_slot1370 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_slot_id_in_template_slot1372 = new BitSet(new long[]{0x0000000000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_SEMICOLON_in_template_slot1374 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_slot_id1403 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule_key_in_rule1440 = new BitSet(new long[]{0x0000000000000000L,0x0000000000240000L});
    public static final BitSet FOLLOW_rule_id_in_rule1442 = new BitSet(new long[]{0x0000000000000000L,0x0001000012040000L});
    public static final BitSet FOLLOW_extend_key_in_rule1451 = new BitSet(new long[]{0x0000000000000000L,0x0000000000240000L});
    public static final BitSet FOLLOW_rule_id_in_rule1453 = new BitSet(new long[]{0x0000000000000000L,0x0001000012040000L});
    public static final BitSet FOLLOW_decl_metadata_in_rule1457 = new BitSet(new long[]{0x0000000000000000L,0x0001000012040000L});
    public static final BitSet FOLLOW_rule_attributes_in_rule1460 = new BitSet(new long[]{0x0000000000000000L,0x0001000012040000L});
    public static final BitSet FOLLOW_when_part_in_rule1463 = new BitSet(new long[]{0x0000000000000000L,0x0001000012040000L});
    public static final BitSet FOLLOW_rhs_chunk_in_rule1466 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WHEN_in_when_part1510 = new BitSet(new long[]{0x0000000000000000L,0x0000000004440000L});
    public static final BitSet FOLLOW_COLON_in_when_part1516 = new BitSet(new long[]{0x0000000000000000L,0x0000000000440000L});
    public static final BitSet FOLLOW_normal_lhs_block_in_when_part1526 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_rule_id1547 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_rule_id1563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_attributes_key_in_rule_attributes1584 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_COLON_in_rule_attributes1586 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_rule_attribute_in_rule_attributes1596 = new BitSet(new long[]{0x0000000000000002L,0x0000000000840000L});
    public static final BitSet FOLLOW_COMMA_in_rule_attributes1600 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_rule_attribute_in_rule_attributes1607 = new BitSet(new long[]{0x0000000000000002L,0x0000000000840000L});
    public static final BitSet FOLLOW_salience_in_rule_attribute1646 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_no_loop_in_rule_attribute1652 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_agenda_group_in_rule_attribute1657 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_duration_in_rule_attribute1664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_activation_group_in_rule_attribute1671 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_auto_focus_in_rule_attribute1677 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_date_effective_in_rule_attribute1683 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_date_expires_in_rule_attribute1689 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enabled_in_rule_attribute1695 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleflow_group_in_rule_attribute1701 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lock_on_active_in_rule_attribute1707 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dialect_in_rule_attribute1712 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_date_effective_key_in_date_effective1727 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_STRING_in_date_effective1732 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_date_expires_key_in_date_expires1746 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_STRING_in_date_expires1751 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enabled_key_in_enabled1766 = new BitSet(new long[]{0x0000000000000000L,0x0000000020400000L});
    public static final BitSet FOLLOW_BOOL_in_enabled1779 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_paren_chunk_in_enabled1790 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_salience_key_in_salience1810 = new BitSet(new long[]{0x0000000000000000L,0x0000000040400000L});
    public static final BitSet FOLLOW_INT_in_salience1819 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_paren_chunk_in_salience1828 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_no_loop_key_in_no_loop1843 = new BitSet(new long[]{0x0000000000000002L,0x0000000020000000L});
    public static final BitSet FOLLOW_BOOL_in_no_loop1848 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_auto_focus_key_in_auto_focus1863 = new BitSet(new long[]{0x0000000000000002L,0x0000000020000000L});
    public static final BitSet FOLLOW_BOOL_in_auto_focus1868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_activation_group_key_in_activation_group1885 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_STRING_in_activation_group1890 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleflow_group_key_in_ruleflow_group1904 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_STRING_in_ruleflow_group1909 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_agenda_group_key_in_agenda_group1923 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_STRING_in_agenda_group1928 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_duration_key_in_duration1942 = new BitSet(new long[]{0x0000000000000000L,0x0000000040400000L});
    public static final BitSet FOLLOW_INT_in_duration1955 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_paren_chunk_in_duration1966 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dialect_key_in_dialect1986 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_STRING_in_dialect1991 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lock_on_active_key_in_lock_on_active2009 = new BitSet(new long[]{0x0000000000000002L,0x0000000020000000L});
    public static final BitSet FOLLOW_BOOL_in_lock_on_active2014 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lhs_in_normal_lhs_block2029 = new BitSet(new long[]{0x0000000000000002L,0x0000000000440000L});
    public static final BitSet FOLLOW_lhs_or_in_lhs2050 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_lhs_or2074 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_or_key_in_lhs_or2084 = new BitSet(new long[]{0x0000000000000000L,0x0000000000440000L});
    public static final BitSet FOLLOW_lhs_and_in_lhs_or2092 = new BitSet(new long[]{0x0000000000000000L,0x0000000001440000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_lhs_or2098 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lhs_and_in_lhs_or2121 = new BitSet(new long[]{0x0000000000000002L,0x0000000080040000L});
    public static final BitSet FOLLOW_or_key_in_lhs_or2143 = new BitSet(new long[]{0x0000000000000000L,0x0000000000440000L});
    public static final BitSet FOLLOW_DOUBLE_PIPE_in_lhs_or2150 = new BitSet(new long[]{0x0000000000000000L,0x0000000000440000L});
    public static final BitSet FOLLOW_lhs_and_in_lhs_or2161 = new BitSet(new long[]{0x0000000000000002L,0x0000000080040000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_lhs_and2202 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_and_key_in_lhs_and2212 = new BitSet(new long[]{0x0000000000000000L,0x0000000000440000L});
    public static final BitSet FOLLOW_lhs_unary_in_lhs_and2220 = new BitSet(new long[]{0x0000000000000000L,0x0000000001440000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_lhs_and2226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lhs_unary_in_lhs_and2250 = new BitSet(new long[]{0x0000000000000002L,0x0000000100040000L});
    public static final BitSet FOLLOW_and_key_in_lhs_and2272 = new BitSet(new long[]{0x0000000000000000L,0x0000000000440000L});
    public static final BitSet FOLLOW_DOUBLE_AMPER_in_lhs_and2279 = new BitSet(new long[]{0x0000000000000000L,0x0000000000440000L});
    public static final BitSet FOLLOW_lhs_unary_in_lhs_and2290 = new BitSet(new long[]{0x0000000000000002L,0x0000000100040000L});
    public static final BitSet FOLLOW_lhs_exist_in_lhs_unary2321 = new BitSet(new long[]{0x0000000000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_lhs_not_binding_in_lhs_unary2329 = new BitSet(new long[]{0x0000000000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_lhs_not_in_lhs_unary2335 = new BitSet(new long[]{0x0000000000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_lhs_eval_in_lhs_unary2341 = new BitSet(new long[]{0x0000000000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_lhs_forall_in_lhs_unary2347 = new BitSet(new long[]{0x0000000000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_lhs_unary2353 = new BitSet(new long[]{0x0000000000000000L,0x0000000000440000L});
    public static final BitSet FOLLOW_lhs_or_in_lhs_unary2364 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_lhs_unary2370 = new BitSet(new long[]{0x0000000000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_pattern_source_in_lhs_unary2378 = new BitSet(new long[]{0x0000000000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_SEMICOLON_in_lhs_unary2392 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exists_key_in_lhs_exist2408 = new BitSet(new long[]{0x0000000000000000L,0x0000000000440000L});
    public static final BitSet FOLLOW_lhs_or_in_lhs_exist2435 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_lhs_exist2442 = new BitSet(new long[]{0x0000000000000000L,0x0000000000440000L});
    public static final BitSet FOLLOW_lhs_or_in_lhs_exist2450 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_lhs_exist2457 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lhs_pattern_in_lhs_exist2472 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_not_key_in_lhs_not_binding2518 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_fact_binding_in_lhs_not_binding2520 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_not_key_in_lhs_not2543 = new BitSet(new long[]{0x0000000000000000L,0x0000000000440000L});
    public static final BitSet FOLLOW_lhs_or_in_lhs_not2565 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_lhs_not2572 = new BitSet(new long[]{0x0000000000000000L,0x0000000000440000L});
    public static final BitSet FOLLOW_lhs_or_in_lhs_not2581 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_lhs_not2587 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lhs_pattern_in_lhs_not2597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_eval_key_in_lhs_eval2636 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_paren_chunk_in_lhs_eval2645 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forall_key_in_lhs_forall2672 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_lhs_forall2677 = new BitSet(new long[]{0x0000000000000000L,0x0000000000440000L});
    public static final BitSet FOLLOW_lhs_pattern_in_lhs_forall2685 = new BitSet(new long[]{0x0000000000000000L,0x0000000001440000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_lhs_forall2691 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lhs_pattern_in_pattern_source2727 = new BitSet(new long[]{0x0000000000000002L,0x0000000600000000L});
    public static final BitSet FOLLOW_over_clause_in_pattern_source2731 = new BitSet(new long[]{0x0000000000000002L,0x0000000200000000L});
    public static final BitSet FOLLOW_FROM_in_pattern_source2741 = new BitSet(new long[]{0x0000000000000000L,0x0000001800040000L});
    public static final BitSet FOLLOW_accumulate_statement_in_pattern_source2761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_collect_statement_in_pattern_source2777 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entrypoint_statement_in_pattern_source2794 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_from_source_in_pattern_source2810 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OVER_in_over_clause2842 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_over_elements_in_over_clause2847 = new BitSet(new long[]{0x0000000000000002L,0x0000000000800000L});
    public static final BitSet FOLLOW_COMMA_in_over_clause2854 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_over_elements_in_over_clause2859 = new BitSet(new long[]{0x0000000000000002L,0x0000000000800000L});
    public static final BitSet FOLLOW_ID_in_over_elements2874 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_COLON_in_over_elements2881 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_ID_in_over_elements2890 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_paren_chunk_in_over_elements2897 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ACCUMULATE_in_accumulate_statement2923 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_accumulate_statement2932 = new BitSet(new long[]{0x0000000000000000L,0x0000000000440000L});
    public static final BitSet FOLLOW_lhs_or_in_accumulate_statement2940 = new BitSet(new long[]{0x0000000000000000L,0x0000000000840000L});
    public static final BitSet FOLLOW_COMMA_in_accumulate_statement2945 = new BitSet(new long[]{0x0000000000000000L,0x0000000000840000L});
    public static final BitSet FOLLOW_accumulate_init_clause_in_accumulate_statement2955 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_accumulate_id_clause_in_accumulate_statement2961 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_accumulate_statement2969 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_init_key_in_accumulate_init_clause3015 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_accumulate_paren_chunk_in_accumulate_init_clause3025 = new BitSet(new long[]{0x0000000000000000L,0x0000000000840000L});
    public static final BitSet FOLLOW_COMMA_in_accumulate_init_clause3030 = new BitSet(new long[]{0x0000000000000000L,0x0000000000840000L});
    public static final BitSet FOLLOW_action_key_in_accumulate_init_clause3041 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_accumulate_paren_chunk_in_accumulate_init_clause3045 = new BitSet(new long[]{0x0000000000000000L,0x0000000000840000L});
    public static final BitSet FOLLOW_COMMA_in_accumulate_init_clause3050 = new BitSet(new long[]{0x0000000000000000L,0x0000000000840000L});
    public static final BitSet FOLLOW_reverse_key_in_accumulate_init_clause3062 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_accumulate_paren_chunk_in_accumulate_init_clause3066 = new BitSet(new long[]{0x0000000000000000L,0x0000000000840000L});
    public static final BitSet FOLLOW_COMMA_in_accumulate_init_clause3071 = new BitSet(new long[]{0x0000000000000000L,0x0000000000840000L});
    public static final BitSet FOLLOW_result_key_in_accumulate_init_clause3087 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_accumulate_paren_chunk_in_accumulate_init_clause3093 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_accumulate_paren_chunk_data_in_accumulate_paren_chunk3151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_accumulate_paren_chunk_data3175 = new BitSet(new long[]{0xFFFFFFFFFFFFFFF0L,0x3FFFFFFFFFFFFFFFL});
    public static final BitSet FOLLOW_set_in_accumulate_paren_chunk_data3187 = new BitSet(new long[]{0xFFFFFFFFFFFFFFF0L,0x3FFFFFFFFFFFFFFFL});
    public static final BitSet FOLLOW_accumulate_paren_chunk_data_in_accumulate_paren_chunk_data3203 = new BitSet(new long[]{0xFFFFFFFFFFFFFFF0L,0x3FFFFFFFFFFFFFFFL});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_accumulate_paren_chunk_data3214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_accumulate_id_clause3230 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_paren_chunk_in_accumulate_id_clause3236 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLLECT_in_collect_statement3258 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_collect_statement3267 = new BitSet(new long[]{0x0000000000000000L,0x0000000000440000L});
    public static final BitSet FOLLOW_pattern_source_in_collect_statement3274 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_collect_statement3279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_entry_point_key_in_entrypoint_statement3306 = new BitSet(new long[]{0x0000000000000000L,0x0000000000240000L});
    public static final BitSet FOLLOW_entrypoint_id_in_entrypoint_statement3314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_entrypoint_id3340 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_entrypoint_id3357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_from_source3377 = new BitSet(new long[]{0x0000000000000002L,0x0000000000480000L});
    public static final BitSet FOLLOW_paren_chunk_in_from_source3392 = new BitSet(new long[]{0x0000000000000002L,0x0000000000080000L});
    public static final BitSet FOLLOW_expression_chain_in_from_source3399 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_expression_chain3432 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_ID_in_expression_chain3439 = new BitSet(new long[]{0x0000000000000002L,0x0000400000480000L});
    public static final BitSet FOLLOW_paren_chunk_in_expression_chain3455 = new BitSet(new long[]{0x0000000000000002L,0x0000000000080000L});
    public static final BitSet FOLLOW_square_chunk_in_expression_chain3469 = new BitSet(new long[]{0x0000000000000002L,0x0000000000080000L});
    public static final BitSet FOLLOW_expression_chain_in_expression_chain3480 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fact_binding_in_lhs_pattern3513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fact_in_lhs_pattern3526 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_label_in_fact_binding3546 = new BitSet(new long[]{0x0000000000000000L,0x0000000000440000L});
    public static final BitSet FOLLOW_fact_in_fact_binding3552 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_fact_binding3559 = new BitSet(new long[]{0x0000000000000000L,0x0000000000440000L});
    public static final BitSet FOLLOW_fact_binding_expression_in_fact_binding3567 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_fact_binding3575 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fact_in_fact_binding_expression3616 = new BitSet(new long[]{0x0000000000000002L,0x0000000080040000L});
    public static final BitSet FOLLOW_or_key_in_fact_binding_expression3628 = new BitSet(new long[]{0x0000000000000000L,0x0000000000440000L});
    public static final BitSet FOLLOW_DOUBLE_PIPE_in_fact_binding_expression3634 = new BitSet(new long[]{0x0000000000000000L,0x0000000000440000L});
    public static final BitSet FOLLOW_fact_in_fact_binding_expression3639 = new BitSet(new long[]{0x0000000000000002L,0x0000000080040000L});
    public static final BitSet FOLLOW_pattern_type_in_fact3679 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_fact3684 = new BitSet(new long[]{0x0000000000000000L,0x0000000001440000L});
    public static final BitSet FOLLOW_constraints_in_fact3695 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_fact3701 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constraint_in_constraints3735 = new BitSet(new long[]{0x0000000000000002L,0x0000000000800000L});
    public static final BitSet FOLLOW_COMMA_in_constraints3739 = new BitSet(new long[]{0x0000000000000000L,0x0000000000440000L});
    public static final BitSet FOLLOW_constraint_in_constraints3746 = new BitSet(new long[]{0x0000000000000002L,0x0000000000800000L});
    public static final BitSet FOLLOW_or_constr_in_constraint3760 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_and_constr_in_or_constr3771 = new BitSet(new long[]{0x0000000000000002L,0x0000000080000000L});
    public static final BitSet FOLLOW_DOUBLE_PIPE_in_or_constr3775 = new BitSet(new long[]{0x0000000000000000L,0x0000000000440000L});
    public static final BitSet FOLLOW_and_constr_in_or_constr3782 = new BitSet(new long[]{0x0000000000000002L,0x0000000080000000L});
    public static final BitSet FOLLOW_unary_constr_in_and_constr3797 = new BitSet(new long[]{0x0000000000000002L,0x0000000100000000L});
    public static final BitSet FOLLOW_DOUBLE_AMPER_in_and_constr3801 = new BitSet(new long[]{0x0000000000000000L,0x0000000000440000L});
    public static final BitSet FOLLOW_unary_constr_in_and_constr3808 = new BitSet(new long[]{0x0000000000000002L,0x0000000100000000L});
    public static final BitSet FOLLOW_eval_key_in_unary_constr3841 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_paren_chunk_in_unary_constr3844 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_field_constraint_in_unary_constr3849 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_unary_constr3855 = new BitSet(new long[]{0x0000000000000000L,0x0000000000440000L});
    public static final BitSet FOLLOW_or_constr_in_unary_constr3865 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_unary_constr3870 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_label_in_field_constraint3890 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_accessor_path_in_field_constraint3892 = new BitSet(new long[]{0x0000000000000002L,0x00000FE000440000L});
    public static final BitSet FOLLOW_or_restr_connective_in_field_constraint3899 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ARROW_in_field_constraint3905 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_paren_chunk_in_field_constraint3909 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_accessor_path_in_field_constraint3963 = new BitSet(new long[]{0x0000000000000000L,0x00000FC000440000L});
    public static final BitSet FOLLOW_or_restr_connective_in_field_constraint3965 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_label3990 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_COLON_in_label3997 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_and_restr_connective_in_or_restr_connective4018 = new BitSet(new long[]{0x0000000000000002L,0x0000000080000000L});
    public static final BitSet FOLLOW_DOUBLE_PIPE_in_or_restr_connective4024 = new BitSet(new long[]{0x0000000000000000L,0x00000FC000440000L});
    public static final BitSet FOLLOW_and_restr_connective_in_or_restr_connective4032 = new BitSet(new long[]{0x0000000000000002L,0x0000000080000000L});
    public static final BitSet FOLLOW_constraint_expression_in_and_restr_connective4053 = new BitSet(new long[]{0x0000000000000002L,0x0000000100000000L});
    public static final BitSet FOLLOW_DOUBLE_AMPER_in_and_restr_connective4059 = new BitSet(new long[]{0x0000000000000000L,0x00000FC000440000L});
    public static final BitSet FOLLOW_constraint_expression_in_and_restr_connective4066 = new BitSet(new long[]{0x0000000000000002L,0x0000000100000000L});
    public static final BitSet FOLLOW_compound_operator_in_constraint_expression4094 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simple_operator_in_constraint_expression4099 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_constraint_expression4104 = new BitSet(new long[]{0x0000000000000000L,0x00000FC000440000L});
    public static final BitSet FOLLOW_or_restr_connective_in_constraint_expression4113 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_constraint_expression4118 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUAL_in_simple_operator4153 = new BitSet(new long[]{0x0000000000000000L,0x0000300060640000L});
    public static final BitSet FOLLOW_GREATER_in_simple_operator4161 = new BitSet(new long[]{0x0000000000000000L,0x0000300060640000L});
    public static final BitSet FOLLOW_GREATER_EQUAL_in_simple_operator4169 = new BitSet(new long[]{0x0000000000000000L,0x0000300060640000L});
    public static final BitSet FOLLOW_LESS_in_simple_operator4177 = new BitSet(new long[]{0x0000000000000000L,0x0000300060640000L});
    public static final BitSet FOLLOW_LESS_EQUAL_in_simple_operator4185 = new BitSet(new long[]{0x0000000000000000L,0x0000300060640000L});
    public static final BitSet FOLLOW_NOT_EQUAL_in_simple_operator4193 = new BitSet(new long[]{0x0000000000000000L,0x0000300060640000L});
    public static final BitSet FOLLOW_not_key_in_simple_operator4201 = new BitSet(new long[]{0x0000000000000000L,0x00000FC000040000L});
    public static final BitSet FOLLOW_operator_key_in_simple_operator4208 = new BitSet(new long[]{0x0000000000000000L,0x0000700060640000L});
    public static final BitSet FOLLOW_square_chunk_in_simple_operator4211 = new BitSet(new long[]{0x0000000000000000L,0x0000300060640000L});
    public static final BitSet FOLLOW_expression_value_in_simple_operator4223 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_in_key_in_compound_operator4245 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_not_key_in_compound_operator4250 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_in_key_in_compound_operator4252 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_compound_operator4263 = new BitSet(new long[]{0x0000000000000000L,0x0000300060640000L});
    public static final BitSet FOLLOW_expression_value_in_compound_operator4271 = new BitSet(new long[]{0x0000000000000000L,0x0000000001800000L});
    public static final BitSet FOLLOW_COMMA_in_compound_operator4275 = new BitSet(new long[]{0x0000000000000000L,0x0000300060640000L});
    public static final BitSet FOLLOW_expression_value_in_compound_operator4280 = new BitSet(new long[]{0x0000000000000000L,0x0000000001800000L});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_compound_operator4288 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_operator_key4319 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_neg_operator_key4364 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_accessor_path_in_expression_value4401 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literal_constraint_in_expression_value4406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_paren_chunk_in_expression_value4412 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_literal_constraint4431 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_literal_constraint4438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_literal_constraint4445 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOL_in_literal_constraint4452 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NULL_in_literal_constraint4459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_pattern_type4474 = new BitSet(new long[]{0x0000000000000002L,0x0000400000080000L});
    public static final BitSet FOLLOW_DOT_in_pattern_type4480 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_ID_in_pattern_type4484 = new BitSet(new long[]{0x0000000000000002L,0x0000400000080000L});
    public static final BitSet FOLLOW_dimension_definition_in_pattern_type4499 = new BitSet(new long[]{0x0000000000000002L,0x0000400000000000L});
    public static final BitSet FOLLOW_ID_in_data_type4527 = new BitSet(new long[]{0x0000000000000002L,0x0000400000080000L});
    public static final BitSet FOLLOW_DOT_in_data_type4533 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_ID_in_data_type4537 = new BitSet(new long[]{0x0000000000000002L,0x0000400000080000L});
    public static final BitSet FOLLOW_dimension_definition_in_data_type4542 = new BitSet(new long[]{0x0000000000000002L,0x0000400000000000L});
    public static final BitSet FOLLOW_LEFT_SQUARE_in_dimension_definition4571 = new BitSet(new long[]{0x0000000000000000L,0x0000800000000000L});
    public static final BitSet FOLLOW_RIGHT_SQUARE_in_dimension_definition4578 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_accessor_element_in_accessor_path4592 = new BitSet(new long[]{0x0000000000000002L,0x0000000000080000L});
    public static final BitSet FOLLOW_DOT_in_accessor_path4596 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_accessor_element_in_accessor_path4600 = new BitSet(new long[]{0x0000000000000002L,0x0000000000080000L});
    public static final BitSet FOLLOW_ID_in_accessor_element4624 = new BitSet(new long[]{0x0000000000000002L,0x0000400000000000L});
    public static final BitSet FOLLOW_square_chunk_in_accessor_element4630 = new BitSet(new long[]{0x0000000000000002L,0x0000400000000000L});
    public static final BitSet FOLLOW_rhs_chunk_data_in_rhs_chunk4659 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_THEN_in_rhs_chunk_data4678 = new BitSet(new long[]{0xFFFFFFFFFFFFFFF0L,0x3FFFFFFFFFFFFFFFL});
    public static final BitSet FOLLOW_not_end_key_in_rhs_chunk_data4687 = new BitSet(new long[]{0xFFFFFFFFFFFFFFF0L,0x3FFFFFFFFFFFFFFFL});
    public static final BitSet FOLLOW_end_key_in_rhs_chunk_data4693 = new BitSet(new long[]{0x0000000000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_SEMICOLON_in_rhs_chunk_data4698 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_curly_chunk_data_in_curly_chunk4717 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_CURLY_in_curly_chunk_data4740 = new BitSet(new long[]{0xFFFFFFFFFFFFFFF0L,0x3FFFFFFFFFFFFFFFL});
    public static final BitSet FOLLOW_set_in_curly_chunk_data4752 = new BitSet(new long[]{0xFFFFFFFFFFFFFFF0L,0x3FFFFFFFFFFFFFFFL});
    public static final BitSet FOLLOW_curly_chunk_data_in_curly_chunk_data4768 = new BitSet(new long[]{0xFFFFFFFFFFFFFFF0L,0x3FFFFFFFFFFFFFFFL});
    public static final BitSet FOLLOW_RIGHT_CURLY_in_curly_chunk_data4779 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_paren_chunk_data_in_paren_chunk4800 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_paren_chunk_data4824 = new BitSet(new long[]{0xFFFFFFFFFFFFFFF0L,0x3FFFFFFFFFFFFFFFL});
    public static final BitSet FOLLOW_set_in_paren_chunk_data4836 = new BitSet(new long[]{0xFFFFFFFFFFFFFFF0L,0x3FFFFFFFFFFFFFFFL});
    public static final BitSet FOLLOW_paren_chunk_data_in_paren_chunk_data4852 = new BitSet(new long[]{0xFFFFFFFFFFFFFFF0L,0x3FFFFFFFFFFFFFFFL});
    public static final BitSet FOLLOW_RIGHT_PAREN_in_paren_chunk_data4863 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_square_chunk_data_in_square_chunk4884 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_SQUARE_in_square_chunk_data4907 = new BitSet(new long[]{0xFFFFFFFFFFFFFFF0L,0x3FFFFFFFFFFFFFFFL});
    public static final BitSet FOLLOW_set_in_square_chunk_data4919 = new BitSet(new long[]{0xFFFFFFFFFFFFFFF0L,0x3FFFFFFFFFFFFFFFL});
    public static final BitSet FOLLOW_square_chunk_data_in_square_chunk_data4934 = new BitSet(new long[]{0xFFFFFFFFFFFFFFF0L,0x3FFFFFFFFFFFFFFFL});
    public static final BitSet FOLLOW_RIGHT_SQUARE_in_square_chunk_data4945 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_lock_on_active_key4969 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_MISC_in_lock_on_active_key4973 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_ID_in_lock_on_active_key4977 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_MISC_in_lock_on_active_key4981 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_ID_in_lock_on_active_key4985 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_date_effective_key5017 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_MISC_in_date_effective_key5021 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_ID_in_date_effective_key5025 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_date_expires_key5057 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_MISC_in_date_expires_key5061 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_ID_in_date_expires_key5065 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_no_loop_key5097 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_MISC_in_no_loop_key5101 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_ID_in_no_loop_key5105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_auto_focus_key5137 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_MISC_in_auto_focus_key5141 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_ID_in_auto_focus_key5145 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_activation_group_key5177 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_MISC_in_activation_group_key5181 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_ID_in_activation_group_key5185 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_agenda_group_key5217 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_MISC_in_agenda_group_key5221 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_ID_in_agenda_group_key5225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_ruleflow_group_key5257 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_MISC_in_ruleflow_group_key5261 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_ID_in_ruleflow_group_key5265 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_entry_point_key5297 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_MISC_in_entry_point_key5301 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_ID_in_entry_point_key5305 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_duration_key5334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_package_key5361 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_import_key5388 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_dialect_key5415 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_salience_key5442 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_enabled_key5469 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_attributes_key5496 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_rule_key5523 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_extend_key5550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_template_key5577 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_query_key5604 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_declare_key5631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_function_key5658 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_global_key5685 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_eval_key5712 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_not_key5739 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_in_key5766 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_or_key5793 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_and_key5820 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_exists_key5847 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_forall_key5874 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_action_key5901 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_reverse_key5928 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_result_key5955 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_end_key5982 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_init_key6029 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_synpred1_DRL2065 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_or_key_in_synpred1_DRL2067 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_or_key_in_synpred2_DRL2134 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLE_PIPE_in_synpred2_DRL2136 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_synpred3_DRL2193 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_and_key_in_synpred3_DRL2195 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_and_key_in_synpred4_DRL2263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLE_AMPER_in_synpred4_DRL2265 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMICOLON_in_synpred5_DRL2388 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_synpred6_DRL2425 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_or_key_in_synpred6_DRL2428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_and_key_in_synpred6_DRL2430 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_synpred7_DRL2553 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_or_key_in_synpred7_DRL2556 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_and_key_in_synpred7_DRL2558 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFT_PAREN_in_synpred8_DRL3386 = new BitSet(new long[]{0x0000000000000002L});

}