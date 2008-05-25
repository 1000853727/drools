grammar DRL; 

@parser::header {
	package org.drools.lang;
	import java.util.List;
	import java.util.ArrayList;
	import java.util.Iterator;
	import java.util.HashMap;	
	import java.util.StringTokenizer;
	import org.drools.lang.descr.*;
}

@parser::members {
	private PackageDescr packageDescr;
	private List errors = new ArrayList();
	private String source = "unknown";
	private int lineOffset = 0;
	private DescrFactory factory = new DescrFactory();
	private boolean parserDebug = false;
	private Location location = new Location( Location.LOCATION_UNKNOWN );
	
	// THE FOLLOWING LINES ARE DUMMY ATTRIBUTES TO WORK AROUND AN ANTLR BUG
	private BaseDescr from = null;
	private FieldConstraintDescr fc = null;
	private RestrictionConnectiveDescr and = null;
	private RestrictionConnectiveDescr or = null;
	private ConditionalElementDescr base = null;
	
	public void setParserDebug(boolean parserDebug) {
		this.parserDebug = parserDebug;
	}
	
	public void debug(String message) {
		if ( parserDebug ) 
			System.err.println( "drl parser: " + message );
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	public DescrFactory getFactory() {
		return factory;
	}	

	public String getSource() {
		return this.source;
	}
	
	public PackageDescr getPackageDescr() {
		return packageDescr;
	}
	
	private int offset(int line) {
		return line + lineOffset;
	}
	
	/**
	 * This will set the offset to record when reparsing. Normally is zero of course 
	 */
	public void setLineOffset(int i) {
	 	this.lineOffset = i;
	}
	
	private String getString(String token) {
		return safeSubstring( token, 1, token.length() -1 );
	}
	
	private String cleanupSpaces( String input ) {
                return input.replaceAll( "\\s", "" );
        }
	
	
	public void reportError(RecognitionException ex) {
	        // if we've already reported an error and have not matched a token
                // yet successfully, don't report any errors.
                if ( errorRecovery ) {
                        return;
                }
                errorRecovery = true;

		ex.line = offset(ex.line); //add the offset if there is one
		errors.add( ex ); 
	}
     	
     	/** return the raw RecognitionException errors */
     	public List getErrors() {
     		return errors;
     	}
     	
     	/** Return a list of pretty strings summarising the errors */
     	public List getErrorMessages() {
     		List messages = new ArrayList();
 		for ( Iterator errorIter = errors.iterator() ; errorIter.hasNext() ; ) {
     	     		messages.add( createErrorMessage( (RecognitionException) errorIter.next() ) );
     	     	}
     	     	return messages;
     	}
     	
     	/** return true if any parser errors were accumulated */
     	public boolean hasErrors() {
  		return ! errors.isEmpty();
     	}
     	
     	/** This will take a RecognitionException, and create a sensible error message out of it */
     	public String createErrorMessage(RecognitionException e)
        {
		StringBuffer message = new StringBuffer();		
                message.append( source + ":"+e.line+":"+e.charPositionInLine+" ");
                if ( e instanceof MismatchedTokenException ) {
                        MismatchedTokenException mte = (MismatchedTokenException)e;
                        if( mte.expecting >=0 && mte.expecting < tokenNames.length ) {
	                        message.append("mismatched token: "+
                                                           e.token+
                                                           "; expecting type "+
                                                           tokenNames[mte.expecting]);
                        } else {
	                        message.append("mismatched token: "+
                                                           e.token+
                                                           ";");
                        }
                }
                else if ( e instanceof MismatchedTreeNodeException ) {
                        MismatchedTreeNodeException mtne = (MismatchedTreeNodeException)e;
                        if( mtne.expecting >=0 && mtne.expecting < tokenNames.length ) {
	                        message.append("mismatched tree node: "+
                                                           mtne.toString() +
                                                           "; expecting type "+
                                                           tokenNames[mtne.expecting]);
                        } else {
	                        message.append("mismatched tree node: "+
                                                           mtne.toString() +
                                                           ";");
                        }
                }
                else if ( e instanceof NoViableAltException ) {
                        NoViableAltException nvae = (NoViableAltException)e;
			message.append( "Unexpected token '" + e.token.getText() + "'" );
                        /*
                        message.append("decision=<<"+nvae.grammarDecisionDescription+">>"+
                                                           " state "+nvae.stateNumber+
                                                           " (decision="+nvae.decisionNumber+
                                                           ") no viable alt; token="+
                                                           e.token);
                                                           */
                }
                else if ( e instanceof EarlyExitException ) {
                        EarlyExitException eee = (EarlyExitException)e;
                        message.append("required (...)+ loop (decision="+
                                                           eee.decisionNumber+
                                                           ") did not match anything; token="+
                                                           e.token);
                }
                else if ( e instanceof MismatchedSetException ) {
                        MismatchedSetException mse = (MismatchedSetException)e;
                        message.append("mismatched token '"+
                                                           e.token+
                                                           "' expecting set "+mse.expecting);
                }
                else if ( e instanceof MismatchedNotSetException ) {
                        MismatchedNotSetException mse = (MismatchedNotSetException)e;
                        message.append("mismatched token '"+
                                                           e.token+
                                                           "' expecting set "+mse.expecting);
                }
                else if ( e instanceof FailedPredicateException ) {
                        FailedPredicateException fpe = (FailedPredicateException)e;
                        message.append("rule "+fpe.ruleName+" failed predicate: {"+
                                                           fpe.predicateText+"}?");
                } else if (e instanceof GeneralParseException) {
			message.append(" " + e.getMessage());
		}
               	return message.toString();
        }   
        
        void checkTrailingSemicolon(String text, int line) {
        	if (text.trim().endsWith( ";" ) ) {
        		this.errors.add( new GeneralParseException( "Trailing semi-colon not allowed", offset(line) ) );
        	}
        }
        
        public Location getLocation() {
                return this.location;
        }
        
        private String safeSubstring( String text, int start, int end ) {
            	return text.substring( Math.min( start, text.length() ), Math.min( Math.max( start, end ), text.length() ) );
        }
      
}

@lexer::header {
	package org.drools.lang;
}

opt_semicolon
	: ';'?
	;

compilation_unit
	@init {
		// reset Location information
		this.location = new Location( Location.LOCATION_UNKNOWN );
	}
	:	prolog 
		statement*
		EOF
	;
	catch [ RecognitionException e ] {
		reportError( e );
	}
	
prolog
	@init {
		String packageName = "";
	}
	:	( pkgstmt=package_statement { packageName = $pkgstmt.packageName; } )?
		{ 
			this.packageDescr = factory.createPackage( packageName ); 
		}	
	;
	

statement
	:	a=rule_attribute { this.packageDescr.addAttribute( a ); }
	|	function_import_statement 
	|	import_statement 
	|	global 
	|	function 
	|       t=template { this.packageDescr.addFactTemplate( $t.template ); }
	|	r=rule { this.packageDescr.addRule( $r.rule ); }			
	|	q=query	{ this.packageDescr.addRule( $q.query ); }
	|       d=type_declaration { this.packageDescr.addTypeDeclaration( $d.declaration ); }
	;
	
package_statement returns [String packageName]
	@init{
		$packageName = null;
	}
	:	
		PACKAGE n=dotted_name opt_semicolon
		{
			$packageName = $n.text;
		}
	;

	

import_statement
        @init {
        	ImportDescr importDecl = null;
        }
	:	IMPORT 
	        {
	            importDecl = factory.createImport( );
	            importDecl.setStartCharacter( ((CommonToken)$IMPORT).getStartIndex() );
		    if (packageDescr != null) {
			packageDescr.addImport( importDecl );
		    }
	        }
	        import_name[importDecl] opt_semicolon
	;

function_import_statement
        @init {
        	FunctionImportDescr importDecl = null;
        }
	:	IMPORT FUNCTION 
	        {
	            importDecl = factory.createFunctionImport();
	            importDecl.setStartCharacter( ((CommonToken)$IMPORT).getStartIndex() );
		    if (packageDescr != null) {
			packageDescr.addFunctionImport( importDecl );
		    }
	        }
	        import_name[importDecl] opt_semicolon
	;

import_name[ImportDescr importDecl] returns [String name]
	@init {
		$name = null;
	}
	:	
		ID 
		{ 
		    $name=$ID.text; 
		    $importDecl.setTarget( name );
		    $importDecl.setEndCharacter( ((CommonToken)$ID).getStopIndex() );
		} 
		( DOT id=identifier 
		    { 
		        $name = $name + $DOT.text + $id.text; 
			$importDecl.setTarget( $name );
		        $importDecl.setEndCharacter( ((CommonToken)$id.start).getStopIndex() );
		    } 
		)* 
		( star='.*' 
		    { 
		        $name = $name + $star.text; 
			$importDecl.setTarget( $name );
		        $importDecl.setEndCharacter( ((CommonToken)$star).getStopIndex() );
		    }
		)?
	;


global
	@init {
	    GlobalDescr global = null;
	}
	:
		GLOBAL 
		{
		    global = factory.createGlobal();
	            global.setStartCharacter( ((CommonToken)$GLOBAL).getStartIndex() );
		    packageDescr.addGlobal( global );
		}
		type=dotted_name 
		{
		    global.setType( $type.text );
		}
		id=identifier opt_semicolon
		{
		    global.setIdentifier( $id.text );
		    global.setEndCharacter( ((CommonToken)$id.start).getStopIndex() );
		}
	;
	

function
	@init {
		FunctionDescr f = null;
		String type = null;
	}
	:
		FUNCTION retType=dotted_name? id=identifier
		{
			//System.err.println( "function :: " + n.getText() );
			type = retType != null ? $retType.text : null;
			f = factory.createFunction( $id.text, type );
			f.setLocation(offset($FUNCTION.line), $FUNCTION.pos);
	        	f.setStartCharacter( ((CommonToken)$FUNCTION).getStartIndex() );
			packageDescr.addFunction( f );
		} 
		LEFT_PAREN
			(	paramType=dotted_name? paramName=argument
				{
					type = paramType != null ? $paramType.text : null;
					f.addParameter( type, $paramName.name );
				}
				(	COMMA paramType=dotted_name? paramName=argument
					{
						type = paramType != null ? $paramType.text : null;
						f.addParameter( type, $paramName.name );
					}
				)*
			)?
		RIGHT_PAREN
		body=curly_chunk
		{
			//strip out '{','}'
			f.setText( safeSubstring( $body.text, 1, $body.text.length()-1 ) );
			f.setEndCharacter( ((CommonToken)$body.stop).getStopIndex() );
			f.setEndLocation(offset($body.stop.getLine()), $body.stop.getCharPositionInLine());
		}
	;

argument returns [String name]
	@init {
		$name = null;
	}
	:	id=identifier { $name=$id.text; } ( '[' ']' { $name += "[]";})*
	;
	
type_declaration returns [TypeDeclarationDescr declaration]
        @init {
                $declaration = factory.createTypeDeclaration();
        }
        :	DECLARE id=identifier 
                        {
                            $declaration.setTypeName( $id.text );
                        }
                        decl_metadata[$declaration]*
                        decl_field[$declaration]*
                END
        ;
        
decl_metadata[BaseDescr declaration]
        :	AT att=identifier val=paren_chunk
                {
                    if( $declaration instanceof TypeDeclarationDescr )
                        ((TypeDeclarationDescr)$declaration).addMetaAttribute( $att.text, getString( $val.text ).trim() );
                    else
                        ((TypeFieldDescr)$declaration).addMetaAttribute( $att.text, getString( $val.text ).trim() );
                }
        ;    
        
decl_field[TypeDeclarationDescr declaration]
@init {
        TypeFieldDescr field = null;
}
	:      id=identifier init=initialization? COLON type=qualified_id 
		{
		    field = new TypeFieldDescr( $id.text );
		    if( init != null )
		        field.setInitExpr( $init.expr );
		    field.setPattern( new PatternDescr($type.text) );
		    $declaration.addField( field );
		}
		decl_metadata[field]* 
	;   
	
initialization returns [String expr]
	:      EQUALS val=paren_chunk
	       {
	           $expr = getString( $val.text ).trim();
	       }
	;	

query returns [QueryDescr query]
	@init {
		$query = null;
		AndDescr lhs = null;
		List params = null;
		List types = null;		
 
	}
	:
		QUERY queryName=name
		{ 
			$query = factory.createQuery( $queryName.name ); 
			$query.setLocation( offset($QUERY.line), $QUERY.pos );
			$query.setStartCharacter( ((CommonToken)$QUERY).getStartIndex() );
			lhs = new AndDescr(); $query.setLhs( lhs ); 
			lhs.setLocation( offset($QUERY.line), $QUERY.pos );
                        location.setType( Location.LOCATION_RULE_HEADER );
		}
		( LEFT_PAREN
		        ( { params = new ArrayList(); types = new ArrayList();}
 
		            (paramType=qualified_id? paramName=ID { params.add( $paramName.text ); String type = (paramType != null) ? $paramType.text : "Object"; types.add( type ); } )
		            (COMMA paramType=qualified_id? paramName=ID { params.add( $paramName.text );  String type = (paramType != null) ? $paramType.text : "Object"; types.add( type );  } )*
 
		            {	$query.setParameters( (String[]) params.toArray( new String[params.size()] ) ); 
		            	$query.setParameterTypes( (String[]) types.toArray( new String[types.size()] ) ); 
		            }
		         )?
	          RIGHT_PAREN
	        )?		
	        {
                        location.setType( Location.LOCATION_LHS_BEGIN_OF_CONDITION );
	        }
		normal_lhs_block[lhs]
		END opt_semicolon
		{
			$query.setEndCharacter( ((CommonToken)$END).getStopIndex() );
		}
	;
	
template returns [FactTemplateDescr template]
	@init {
		$template = null;		
	}
	:
		TEMPLATE templateName=name opt_semicolon
		{
			$template = new FactTemplateDescr($templateName.name);
			$template.setLocation( offset($TEMPLATE.line), $TEMPLATE.pos );			
			$template.setStartCharacter( ((CommonToken)$TEMPLATE).getStartIndex() );
		}
		(
			slot=template_slot 
			{
				template.addFieldTemplate( $slot.field );
			}
		)+
		END opt_semicolon 
		{
			template.setEndCharacter( ((CommonToken)$END).getStopIndex() );
		}		
	;
	
template_slot returns [FieldTemplateDescr field]
	@init {
		$field = null;
	}
	:
	         {
			$field = factory.createFieldTemplate();
	         }
		 fieldType=qualified_id
		 {
		        $field.setClassType( $fieldType.text );
			$field.setStartCharacter( ((CommonToken)$fieldType.start).getStartIndex() );
			$field.setEndCharacter( ((CommonToken)$fieldType.stop).getStopIndex() );
		 }
		 
		 id=identifier opt_semicolon
		 {
		        $field.setName( $id.text );
			$field.setLocation( offset($id.start.getLine()), $id.start.getCharPositionInLine() );
			$field.setEndCharacter( ((CommonToken)$id.start).getStopIndex() );
		 } 
	;	
	
rule returns [RuleDescr rule]
	@init {
		$rule = null;
		AndDescr lhs = null;
	}
	:
		RULE ruleName=name 
		{ 
			location.setType( Location.LOCATION_RULE_HEADER );
			debug( "start rule: " + $ruleName.name );
			$rule = new RuleDescr( $ruleName.name, null ); 
			$rule.setLocation( offset($RULE.line), $RULE.pos );
			$rule.setStartCharacter( ((CommonToken)$RULE).getStartIndex() );
			lhs = new AndDescr(); 
			$rule.setLhs( lhs ); 
		}
		rule_attributes[$rule]?
		(	
			WHEN COLON?
			{ 
				this.location.setType( Location.LOCATION_LHS_BEGIN_OF_CONDITION );
				lhs.setLocation( offset($WHEN.line), $WHEN.pos );
				lhs.setStartCharacter( ((CommonToken)$WHEN).getStartIndex() );
			}
			normal_lhs_block[lhs]
		)?
		rhs_chunk[$rule]
	;
	


rule_attributes[RuleDescr rule]
	: 
	( ATTRIBUTES COLON )?
	attr=rule_attribute { $rule.addAttribute( $attr.attr ); }
	( ','? attr=rule_attribute { $rule.addAttribute( $attr.attr ); } )*
	;


	
rule_attribute returns [AttributeDescr attr]
	@init {
		$attr = null;
	}
	@after {
		$attr = $a.descr;
	}
	:	a=salience 
	|	a=no_loop  
	|	a=agenda_group  
	|	a=duration  
	|	a=activation_group 
	|	a=auto_focus 
	|	a=date_effective 
	|	a=date_expires 
	|	a=enabled 
	|	a=ruleflow_group 
	|	a=lock_on_active
	|	a=dialect 
	;
	
date_effective returns [AttributeDescr descr]
	@init {
		$descr = null;
	}	
	:
		DATE_EFFECTIVE STRING  
		{
			$descr = new AttributeDescr( "date-effective", getString( $STRING.text ) );
			$descr.setLocation( offset( $DATE_EFFECTIVE.line ), $DATE_EFFECTIVE.pos );
			$descr.setStartCharacter( ((CommonToken)$DATE_EFFECTIVE).getStartIndex() );
			$descr.setEndCharacter( ((CommonToken)$STRING).getStopIndex() );
		}

	;

date_expires returns [AttributeDescr descr]
	@init {
		$descr = null;
	}	
	:	DATE_EXPIRES STRING  
		{
			$descr = new AttributeDescr( "date-expires", getString( $STRING.text ) );
			$descr.setLocation( offset($DATE_EXPIRES.line), $DATE_EXPIRES.pos );
			$descr.setStartCharacter( ((CommonToken)$DATE_EXPIRES).getStartIndex() );
			$descr.setEndCharacter( ((CommonToken)$STRING).getStopIndex() );
		}
	;
	
enabled returns [AttributeDescr descr]
	@init {
		$descr = null;
	}
	:		ENABLED BOOL   
			{
				$descr = new AttributeDescr( "enabled", $BOOL.text );
				$descr.setLocation( offset($ENABLED.line), $ENABLED.pos );
				$descr.setStartCharacter( ((CommonToken)$ENABLED).getStartIndex() );
				$descr.setEndCharacter( ((CommonToken)$BOOL).getStopIndex() );
			}
	;	

salience returns [AttributeDescr descr]
	@init {
		$descr = null;
	}
	:	
		SALIENCE 
		{
			$descr = new AttributeDescr( "salience" );
			$descr.setLocation( offset($SALIENCE.line), $SALIENCE.pos );
			$descr.setStartCharacter( ((CommonToken)$SALIENCE).getStartIndex() );
		}
		( INT   
		{
			$descr.setValue( $INT.text );
			$descr.setEndCharacter( ((CommonToken)$INT).getStopIndex() );
		}
		| txt=paren_chunk
		{
			$descr.setValue( $txt.text );
			$descr.setEndCharacter( ((CommonToken)$txt.stop).getStopIndex() );
		}
		)
	;
	
no_loop returns [AttributeDescr descr]
	@init {
		$descr = null;
	}
	:	NO_LOOP   
		{
			$descr = new AttributeDescr( "no-loop", "true" );
			$descr.setLocation( offset($NO_LOOP.line), $NO_LOOP.pos );
			$descr.setStartCharacter( ((CommonToken)$NO_LOOP).getStartIndex() );
			$descr.setEndCharacter( ((CommonToken)$NO_LOOP).getStopIndex() );
		}
		( BOOL   
			{
				$descr.setValue( $BOOL.text );
				$descr.setEndCharacter( ((CommonToken)$BOOL).getStopIndex() );
			}
		)?
	;
	
auto_focus returns [AttributeDescr descr]
	@init {
		$descr = null;
	}
	:	AUTO_FOCUS   
		{
			$descr = new AttributeDescr( "auto-focus", "true" );
			$descr.setLocation( offset($AUTO_FOCUS.line), $AUTO_FOCUS.pos );
			$descr.setStartCharacter( ((CommonToken)$AUTO_FOCUS).getStartIndex() );
			$descr.setEndCharacter( ((CommonToken)$AUTO_FOCUS).getStopIndex() );
		}
		( BOOL   
			{
				$descr.setValue( $BOOL.text );
				$descr.setEndCharacter( ((CommonToken)$BOOL).getStopIndex() );
			}
		)?
	;	
	
activation_group returns [AttributeDescr descr]
	@init {
		$descr = null;
	}
	:	ACTIVATION_GROUP STRING   
		{
			$descr = new AttributeDescr( "activation-group", getString( $STRING.text ) );
			$descr.setLocation( offset($ACTIVATION_GROUP.line), $ACTIVATION_GROUP.pos );
			$descr.setStartCharacter( ((CommonToken)$ACTIVATION_GROUP).getStartIndex() );
			$descr.setEndCharacter( ((CommonToken)$STRING).getStopIndex() );
		}
	;

ruleflow_group returns [AttributeDescr descr]
	@init {
		$descr = null;
	}
	:	RULEFLOW_GROUP STRING   
		{
			$descr = new AttributeDescr( "ruleflow-group", getString( $STRING.text ) );
			$descr.setLocation( offset($RULEFLOW_GROUP.line), $RULEFLOW_GROUP.pos );
			$descr.setStartCharacter( ((CommonToken)$RULEFLOW_GROUP).getStartIndex() );
			$descr.setEndCharacter( ((CommonToken)$STRING).getStopIndex() );
		}
	;

agenda_group returns [AttributeDescr descr]
	@init {
		$descr = null;
	}
	:	AGENDA_GROUP STRING   
		{
			$descr = new AttributeDescr( "agenda-group", getString( $STRING.text ) );
			$descr.setLocation( offset($AGENDA_GROUP.line), $AGENDA_GROUP.pos );
			$descr.setStartCharacter( ((CommonToken)$AGENDA_GROUP).getStartIndex() );
			$descr.setEndCharacter( ((CommonToken)$STRING).getStopIndex() );
		}
	;

duration returns [AttributeDescr descr]
	@init {
		$descr = null;
	}
	:	DURATION INT 
		{
			$descr = new AttributeDescr( "duration", $INT.text );
			$descr.setLocation( offset($DURATION.line), $DURATION.pos );
			$descr.setStartCharacter( ((CommonToken)$DURATION).getStartIndex() );
			$descr.setEndCharacter( ((CommonToken)$INT).getStopIndex() );
		}
	;	
	
dialect returns [AttributeDescr descr]
	@init {
		$descr = null;
	}
	:	DIALECT STRING   
		{
			$descr = new AttributeDescr( "dialect", getString( $STRING.text ) );
			$descr.setLocation( offset($DIALECT.line), $DIALECT.pos );
			$descr.setStartCharacter( ((CommonToken)$DIALECT).getStartIndex() );
			$descr.setEndCharacter( ((CommonToken)$STRING).getStopIndex() );
		}
	;			
	
lock_on_active returns [AttributeDescr descr]
	@init {
		$descr = null;
	}
	:	LOCK_ON_ACTIVE   
		{
			$descr = new AttributeDescr( "lock-on-active", "true" );
			$descr.setLocation( offset($LOCK_ON_ACTIVE.line), $LOCK_ON_ACTIVE.pos );
			$descr.setStartCharacter( ((CommonToken)$LOCK_ON_ACTIVE).getStartIndex() );
			$descr.setEndCharacter( ((CommonToken)$LOCK_ON_ACTIVE).getStopIndex() );
		}
		( BOOL   
			{
				$descr.setValue( $BOOL.text );
				$descr.setEndCharacter( ((CommonToken)$BOOL).getStopIndex() );
			}
		)?
	;		

normal_lhs_block[AndDescr descr]
	@init {
		location.setType( Location.LOCATION_LHS_BEGIN_OF_CONDITION );
	}
	:
		(	d=lhs[$descr]
			{ if( $d.d != null) $descr.addDescr( $d.d ); }
		)*
	;

	
lhs[ConditionalElementDescr ce] returns [BaseDescr d]
	@init {
		$d=null;
	}
	:	l=lhs_or { $d = $l.d; } 
	;

	
lhs_or returns [BaseDescr d]
	@init{
		$d = null;
		OrDescr or = null;
	}
	:	LEFT_PAREN OR 
		{
			or = new OrDescr();
			$d = or;
			location.setType( Location.LOCATION_LHS_BEGIN_OF_CONDITION_AND_OR );
		}
		( lhsand=lhs_and 
		{
			or.addDescr( $lhsand.d );
		})+
		RIGHT_PAREN // PREFIX
	|	
	        left=lhs_and { $d = $left.d; }
		( (OR|DOUBLE_PIPE)
			{
				location.setType( Location.LOCATION_LHS_BEGIN_OF_CONDITION_AND_OR );
			}
			right=lhs_and
			{
				if ( or == null ) {
					or = new OrDescr();
					or.addDescr( $left.d );
					$d = or;
				}
				
				or.addDescr( $right.d );
			}
		)*
	;
	
lhs_and returns [BaseDescr d]
	@init{
		$d = null;
		AndDescr and = null;
	}
	:	LEFT_PAREN AND 
		{
			and = new AndDescr();
			$d = and;
			location.setType( Location.LOCATION_LHS_BEGIN_OF_CONDITION_AND_OR );
		}
		(lhsunary=lhs_unary
		{
			and.addDescr( $lhsunary.d );
		})+
		RIGHT_PAREN 
	|	
	        left=lhs_unary { $d = $left.d; }
		( (AND|DOUBLE_AMPER)
			{
				location.setType( Location.LOCATION_LHS_BEGIN_OF_CONDITION_AND_OR );
			}
			right=lhs_unary
			{
				if ( and == null ) {
					and = new AndDescr();
					and.addDescr( $left.d );
					$d = and;
				}
				
				and.addDescr( $right.d );
			}
		)* 
	;
	
lhs_unary returns [BaseDescr d]
	@init {
		$d = null;
	}
	:	(	( EXISTS ) => u=lhs_exist { $d = $u.d; }
		|	( NOT ) => u=lhs_not  { $d = $u.d; }
		|	( EVAL ) => u=lhs_eval  { $d = $u.d; }
		|	( FORALL ) => u=lhs_forall  { $d = $u.d; }
		|	( LEFT_PAREN ) => LEFT_PAREN u=lhs_or RIGHT_PAREN  { $d = $u.d; }
		|	ps=pattern_source  { $d = (BaseDescr) $ps.d; }
		) 
		opt_semicolon
	;
	
pattern_source returns [BaseDescr d]
	@init {
		$d = null;
	}
	:	
		u=lhs_pattern { $d = $u.d; } 
		(       OVER 
		(WINDOW COLON type=identifier param=paren_chunk
		        {
		                SlidingWindowDescr window = new SlidingWindowDescr( $type.text, getString( $param.text ).trim() );
		                ((PatternDescr)$d).addBehavior( window );
		        } 
		)+
		)?
		(
		        // THIS IS SIMPLY DUMB IMO, but it was the only way I could make it work... :( not sure if it is an 
		        // ANTLR bug or if I'm doing something wrong.
		        ( (FROM ENTRY_POINT) => FROM ep=entrypoint_statement { if( $d != null ) ((PatternDescr)$d).setSource((PatternSourceDescr) $ep.d); })
		        |
			FROM 
		        {
				location.setType(Location.LOCATION_LHS_FROM);
				location.setProperty(Location.LOCATION_FROM_CONTENT, "");
		        }
		        ( options { k=1; backtrack=true;} :
		          ( ac=accumulate_statement { if( $d != null ) ((PatternDescr)$d).setSource((PatternSourceDescr) $ac.d); })
		          | ( cs=collect_statement { if( $d != null ) ((PatternDescr)$d).setSource((PatternSourceDescr) $cs.d); }) 
		          //| ( ep=entrypoint_statement { if( $d != null ) ((PatternDescr)$d).setSource((PatternSourceDescr) $ep.d); })
		          | ( fm=from_statement { if( $d != null ) ((PatternDescr)$d).setSource((PatternSourceDescr) $fm.d); }) 
		        )
		)?
	;
	
lhs_exist returns [BaseDescr d]
	@init {
		$d = null;
	}
	:	EXISTS 
		{
			$d = new ExistsDescr( ); 
			$d.setLocation( offset($EXISTS.line), $EXISTS.pos );
			$d.setStartCharacter( ((CommonToken)$EXISTS).getStartIndex() );
			location.setType( Location.LOCATION_LHS_BEGIN_OF_CONDITION_EXISTS );
		}
	        ( ( LEFT_PAREN or=lhs_or 
	           	{ if ( $or.d != null ) ((ExistsDescr)$d).addDescr( $or.d ); }
	           RIGHT_PAREN 
	                { $d.setEndCharacter( ((CommonToken)$RIGHT_PAREN).getStopIndex() ); }
	        )    
	        | pattern=lhs_pattern
	                {
	                	if ( $pattern.d != null ) {
	                		((ExistsDescr)$d).addDescr( $pattern.d );
	                		$d.setEndCharacter( $pattern.d.getEndCharacter() );
	                	}
	                }
	        )
	;
	
lhs_not	returns [NotDescr d]
	@init {
		$d = null;
	}
	:	NOT 
		{
			$d = new NotDescr( ); 
			$d.setLocation( offset($NOT.line), $NOT.pos );
			$d.setStartCharacter( ((CommonToken)$NOT).getStartIndex() );
			location.setType( Location.LOCATION_LHS_BEGIN_OF_CONDITION_NOT );
		}
		( ( LEFT_PAREN or=lhs_or  
	           	{ if ( $or.d != null ) $d.addDescr( $or.d ); }
	           RIGHT_PAREN 
	                { $d.setEndCharacter( ((CommonToken)$RIGHT_PAREN).getStopIndex() ); }
		  )
		| 
		pattern=lhs_pattern
	                {
	                	if ( $pattern.d != null ) {
	                		$d.addDescr( $pattern.d );
	                		$d.setEndCharacter( $pattern.d.getEndCharacter() );
	                	}
	                }
		)
	;

lhs_eval returns [BaseDescr d]
	@init {
		$d = new EvalDescr( );
	}
	:
		EVAL 
		{
			location.setType( Location.LOCATION_LHS_INSIDE_EVAL );
		}
		c=paren_chunk
		{ 
			$d.setStartCharacter( ((CommonToken)$EVAL).getStartIndex() );
		        if( $c.text != null ) {
	  		    this.location.setType( Location.LOCATION_LHS_BEGIN_OF_CONDITION );
		            String body = safeSubstring( $c.text, 1, $c.text.length()-1 );
			    checkTrailingSemicolon( body, offset($EVAL.line) );
			    ((EvalDescr) $d).setContent( body );
			    location.setProperty(Location.LOCATION_EVAL_CONTENT, body);
			}
			if( $c.stop != null ) {
			    $d.setEndCharacter( ((CommonToken)$c.stop).getStopIndex() );
			}
		}
	;
	
lhs_forall returns [ForallDescr d]
	@init {
		$d = factory.createForall();
	}
	:	FORALL LEFT_PAREN base=lhs_pattern   
		{
			$d.setStartCharacter( ((CommonToken)$FORALL).getStartIndex() );
		        // adding the base pattern
		        $d.addDescr( $base.d );
			$d.setLocation( offset($FORALL.line), $FORALL.pos );
		}
		( pattern=lhs_pattern
		   {
		        // adding additional patterns
			$d.addDescr( $pattern.d );
		   }
		)*
		RIGHT_PAREN
		{
		        $d.setEndCharacter( ((CommonToken)$RIGHT_PAREN).getStopIndex() );
		}
	;

lhs_pattern returns [BaseDescr d]
	@init {
		$d=null;
	}
	@after {
		$d=$f.d;
	}
	:	f=fact_binding	
	|	f=fact[null]		
	;

from_statement returns [FromDescr d]
	@init {
		$d=factory.createFrom();
	}
	:
	ds=from_source[$d]
	{
		$d.setDataSource( $ds.ds );
	}
	;
	

accumulate_statement returns [AccumulateDescr d]
	@init {
		$d = factory.createAccumulate();
	} 
	:
	        ACCUMULATE 
		{ 
			$d.setLocation( offset($ACCUMULATE.line), $ACCUMULATE.pos );
			$d.setStartCharacter( ((CommonToken)$ACCUMULATE).getStartIndex() );
			location.setType( Location.LOCATION_LHS_FROM_ACCUMULATE );
		}	
		LEFT_PAREN inputCE=lhs_or COMMA? 
		{
		        $d.setInput( $inputCE.d );
		}
		( ( 
			INIT 
			{
				location.setType( Location.LOCATION_LHS_FROM_ACCUMULATE_INIT );
			}
			text=paren_chunk COMMA?
			{
				if( $text.text != null ) {
				        $d.setInitCode( safeSubstring( $text.text, 1, $text.text.length()-1 ) );
					location.setProperty(Location.LOCATION_PROPERTY_FROM_ACCUMULATE_INIT_CONTENT, $d.getInitCode());
					location.setType( Location.LOCATION_LHS_FROM_ACCUMULATE_ACTION );
				}
			}
			ACTION text=paren_chunk COMMA?
			{
				if( $text.text != null ) {
				        $d.setActionCode( safeSubstring( $text.text, 1, $text.text.length()-1 ) );
	       				location.setProperty(Location.LOCATION_PROPERTY_FROM_ACCUMULATE_ACTION_CONTENT, $d.getActionCode());
					location.setType( Location.LOCATION_LHS_FROM_ACCUMULATE_REVERSE );
				}
			}
			( REVERSE text=paren_chunk COMMA?
			{
				if( $text.text != null ) {
				        $d.setReverseCode( safeSubstring( $text.text, 1, $text.text.length()-1 ) );
	       				location.setProperty(Location.LOCATION_PROPERTY_FROM_ACCUMULATE_REVERSE_CONTENT, $d.getReverseCode());
					location.setType( Location.LOCATION_LHS_FROM_ACCUMULATE_RESULT );
				}
			}
			)?
			RESULT text=paren_chunk 
			{
				if( $text.text != null ) {
				        $d.setResultCode( safeSubstring( $text.text, 1, $text.text.length()-1 ) );
					location.setProperty(Location.LOCATION_PROPERTY_FROM_ACCUMULATE_RESULT_CONTENT, $d.getResultCode());
				}
			}
		) 
		|
		(
			id=ID text=paren_chunk
			{
				if( $id.text != null ) {
				        $d.setExternalFunction( true );
					$d.setFunctionIdentifier( $id.text );
				}
				if( $text.text != null ) {
				        $d.setExpression( safeSubstring( $text.text, 1, $text.text.length()-1 ) );
	       				location.setProperty(Location.LOCATION_PROPERTY_FROM_ACCUMULATE_EXPRESSION_CONTENT, $d.getExpression());
				}
			}
		)
		)
		RIGHT_PAREN
		{
			location.setType( Location.LOCATION_LHS_BEGIN_OF_CONDITION );
			d.setEndCharacter( ((CommonToken)$RIGHT_PAREN).getStopIndex() );
		} 
	; 
	
from_source[FromDescr from] returns [DeclarativeInvokerDescr ds]
	@init {
		$ds = null;
		AccessorDescr ad = null;
		FunctionCallDescr fc = null;
	}
	:	ident=identifier
		{
			ad = new AccessorDescr(ident.start.getText());	
			ad.setLocation( offset(ident.start.getLine()), ident.start.getCharPositionInLine() );
			ad.setStartCharacter( ((CommonToken)ident.start).getStartIndex() );
			ad.setEndCharacter( ((CommonToken)ident.start).getStopIndex() );
			$ds = ad;
			location.setProperty(Location.LOCATION_FROM_CONTENT, ident.start.getText());
		}
		(	/* WARNING: $o : O() from x(y) could be also $o : O() from x followed
			   by (y).  Resolve by always forcing (...) to be paren_chunk if
			   after a from.  Setting k=1 will force this to happen.  No backtracking
			   but you'll get a warning from ANTLR.  ANTLR resolves by choosing first
			   alternative to win, which is the paren_chunk case not the loop exit.
			*/
			options {k=1;}
		:	args=paren_chunk
		{
			if( $args.text != null ) {
				ad.setVariableName( null );
				fc = new FunctionCallDescr($ident.start.getText());
				fc.setLocation( offset($ident.start.getLine()), $ident.start.getCharPositionInLine() );			
				fc.setArguments($args.text);
				fc.setStartCharacter( ((CommonToken)$ident.start).getStartIndex() );
				fc.setEndCharacter( ((CommonToken)$ident.start).getStopIndex() );
				location.setProperty(Location.LOCATION_FROM_CONTENT, $args.text);
				$from.setEndCharacter( ((CommonToken)$args.stop).getStopIndex() );
			}
		}
		)?
		expression_chain[$from, ad]?
	;	
	finally {
		if( ad != null ) {
			if( fc != null ) {
				ad.addFirstInvoker( fc );
			}
			location.setProperty(Location.LOCATION_FROM_CONTENT, ad.toString() );
		}
	}
	
expression_chain[FromDescr from, AccessorDescr as] 
	@init {
  		FieldAccessDescr fa = null;
	    	MethodAccessDescr ma = null;	
	}
	:
	( DOT field=identifier  
	    {
	        fa = new FieldAccessDescr($field.start.getText());	
		fa.setLocation( offset($field.start.getLine()), $field.start.getCharPositionInLine() );
		fa.setStartCharacter( ((CommonToken)$field.start).getStartIndex() );
		fa.setEndCharacter( ((CommonToken)$field.start).getStopIndex() );
	    }
	  (
	    ( LEFT_SQUARE ) => sqarg=square_chunk
	      {
	          fa.setArgument( $sqarg.text );	
		  $from.setEndCharacter( ((CommonToken)$sqarg.stop).getStopIndex() );
	      }
	    |
	    ( LEFT_PAREN ) => paarg=paren_chunk
		{
	    	  ma = new MethodAccessDescr( $field.start.getText(), $paarg.text );	
		  ma.setLocation( offset($field.start.getLine()), $field.start.getCharPositionInLine() );
		  ma.setStartCharacter( ((CommonToken)$field.start).getStartIndex() );
		  $from.setEndCharacter( ((CommonToken)$paarg.stop).getStopIndex() );
		}
	  )?
	  expression_chain[from, as]?
	)  
	;	
	finally {
		// must be added to the start, since it is a recursive rule
		if( ma != null ) {
			$as.addFirstInvoker( ma );
		} else {
			$as.addFirstInvoker( fa );
		}
	}
	
		
 		
collect_statement returns [CollectDescr d]
	@init {
		$d = factory.createCollect();
	}
	:
	        COLLECT 
		{ 
			$d.setLocation( offset($COLLECT.line), $COLLECT.pos );
			$d.setStartCharacter( ((CommonToken)$COLLECT).getStartIndex() );
			location.setType( Location.LOCATION_LHS_FROM_COLLECT );
		}	
		LEFT_PAREN pattern=pattern_source RIGHT_PAREN
		{
		        $d.setInputPattern( (PatternDescr) $pattern.d );
			$d.setEndCharacter( ((CommonToken)$RIGHT_PAREN).getStopIndex() );
			location.setType( Location.LOCATION_LHS_BEGIN_OF_CONDITION );
		}
	; 		

entrypoint_statement returns [EntryPointDescr d]
	@init {
		$d = factory.createEntryPoint();
	}
	:
	        ENTRY_POINT 
		{ 
			$d.setLocation( offset($ENTRY_POINT.line), $ENTRY_POINT.pos );
			$d.setStartCharacter( ((CommonToken)$ENTRY_POINT).getStartIndex() );
			location.setType( Location.LOCATION_LHS_FROM_ENTRY_POINT );
		}	
		id=name
		{
		        $d.setEntryId( $id.name );
			$d.setEndCharacter( ((CommonToken)$id.stop).getStopIndex() );
			location.setType( Location.LOCATION_LHS_BEGIN_OF_CONDITION );
		}
	; 		

fact_binding returns [BaseDescr d]
	@init {
		$d=null;
		OrDescr or = null;
	}
 	:
 		ID COLON 
 		{
 		        // handling incomplete parsing
 		        $d = new PatternDescr( );
 		        ((PatternDescr) $d).setIdentifier( $ID.text );
 		}
		( fe=fact[$ID.text]
 		{
 		        // override previously instantiated pattern
 			$d=$fe.d;
 			if( $d != null ) {
   			    $d.setStartCharacter( ((CommonToken)$ID).getStartIndex() );
   			}
 		}
 		|
 		LEFT_PAREN left=fact[$ID.text]
 		{
 		        // override previously instantiated pattern
 			$d=$left.d;
 			if( $d != null ) {
   			    $d.setStartCharacter( ((CommonToken)$ID).getStartIndex() );
   			}
 		}
 		( (OR|DOUBLE_PIPE)
 			right=fact[$ID.text]
 			{
				if ( or == null ) {
					or = new OrDescr();
					or.addDescr( $left.d );
					$d = or;
				}
				or.addDescr( $right.d );
 			}
 		)*
 		RIGHT_PAREN
 		)
	;
 
fact[String ident] returns [BaseDescr d] 
	@init {
		$d=null;
		PatternDescr pattern = null;
	}
 	:	
 	        {
 			pattern = new PatternDescr( );
 			if( $ident != null ) {
 				pattern.setIdentifier( $ident );
 			}
 			$d = pattern; 
 	        }
 	        id=qualified_id
 		{ 
 			if( id != null ) {
	 		        pattern.setObjectType( $id.text );
 			        pattern.setEndCharacter( -1 );
				pattern.setStartCharacter( ((CommonToken)$id.start).getStartIndex() );
 			}
 		}
 		LEFT_PAREN 
 		{
		        location.setType( Location.LOCATION_LHS_INSIDE_CONDITION_START );
            		location.setProperty( Location.LOCATION_PROPERTY_CLASS_NAME, $id.text );
 				
 			pattern.setLocation( offset($LEFT_PAREN.line), $LEFT_PAREN.pos );
 			pattern.setLeftParentCharacter( ((CommonToken)$LEFT_PAREN).getStartIndex() );
 		} 
 		constraints[pattern]?
 		RIGHT_PAREN
		{
		        if( ")".equals( $RIGHT_PAREN.text ) ) {
				this.location.setType( Location.LOCATION_LHS_BEGIN_OF_CONDITION );
				pattern.setEndLocation( offset($RIGHT_PAREN.line), $RIGHT_PAREN.pos );	
				pattern.setEndCharacter( ((CommonToken)$RIGHT_PAREN).getStopIndex() );
		        	pattern.setRightParentCharacter( ((CommonToken)$RIGHT_PAREN).getStartIndex() );
		        }
 		}
 		EOF? // this is needed for partial parsing
 	;
	
	
constraints[PatternDescr pattern]
	:	(constraint[$pattern]|behaviors)
		( COMMA { location.setType( Location.LOCATION_LHS_INSIDE_CONDITION_START ); } 
		  (constraint[$pattern]|behaviors) 
		)* 
	;
	
constraint[PatternDescr pattern]
	@init {
		ConditionalElementDescr top = null;
		location.setType(Location.LOCATION_LHS_INSIDE_CONDITION_START);
		location.setProperty(Location.LOCATION_PROPERTY_PROPERTY_NAME, input.LT(1).getText() );
	}
	:
		{
			top = $pattern.getConstraint();
		}
		or_constr[top]
	;	
	
behaviors 
	:	LEFT_SQUARE behavior ( COMMA behavior )* RIGHT_SQUARE
	;	
	
behavior 
	:	ID (COLON ID)? paren_chunk?
	;
	
or_constr[ConditionalElementDescr base]
	@init {
		OrDescr or = new OrDescr();
	}
	:
		and_constr[or] 
		( DOUBLE_PIPE 
		{
			location.setType(Location.LOCATION_LHS_INSIDE_CONDITION_START);
		}
		and_constr[or] 
		)*
		{
		        if( or.getDescrs().size() == 1 ) {
		                $base.addOrMerge( (BaseDescr) or.getDescrs().get(0) );
		        } else if ( or.getDescrs().size() > 1 ) {
		        	$base.addDescr( or );
		        }
		}
	;
	
and_constr[ConditionalElementDescr base]
	@init {
		AndDescr and = new AndDescr();
	}
	:
		unary_constr[and] 
		( DOUBLE_AMPER 
		{
			location.setType(Location.LOCATION_LHS_INSIDE_CONDITION_START);
		}
		unary_constr[and] 
		)*
		{
		        if( and.getDescrs().size() == 1) {
		                $base.addOrMerge( (BaseDescr) and.getDescrs().get(0) );
		        } else if( and.getDescrs().size() > 1) {
		        	$base.addDescr( and );
		        }
		}
	;
	
unary_constr[ConditionalElementDescr base]
	:
		( field_constraint[$base] 
		| LEFT_PAREN or_constr[$base] RIGHT_PAREN
		| EVAL predicate[$base]
		)
	;	
		
field_constraint[ConditionalElementDescr base]
	@init {
		FieldBindingDescr fbd = null;
		FieldConstraintDescr fc = null;
		RestrictionConnectiveDescr top = null;
	}
	:
	        (
		ID COLON 
		    { 
			fbd = new FieldBindingDescr();
			fbd.setIdentifier( $ID.text );
			fbd.setLocation( offset($ID.line), $ID.pos );
			fbd.setStartCharacter( ((CommonToken)$ID).getStartIndex() );
			$base.addDescr( fbd );

		    }
		 
		f=accessor_path	
		{
		    // use $f.start to get token matched in identifier
		    // or use $f.text to get text.
		    if( $f.text != null ) {
			location.setType(Location.LOCATION_LHS_INSIDE_CONDITION_OPERATOR);
			location.setProperty(Location.LOCATION_PROPERTY_PROPERTY_NAME, $f.text);
		    
			if ( fbd != null ) {
			    fbd.setFieldName( $f.text );
			    // may have been overwritten
			    fbd.setStartCharacter( ((CommonToken)$ID).getStartIndex() );
			} 
			fc = new FieldConstraintDescr($f.text);
			fc.setLocation( offset($f.start.getLine()), $f.start.getCharPositionInLine() );
			fc.setStartCharacter( ((CommonToken)$f.start).getStartIndex() );
			top = fc.getRestriction();
			
			// it must be a field constraint, as it is not a binding
			if( $ID == null ) {
			    $base.addDescr( fc );
			}
		    }
		}
		(
			or_restr_connective[top]
			{
				// we must add now as we didn't before
				if( $ID != null) {
				    $base.addDescr( fc );
				}
			}
		|
			'->' predicate[$base] 
		)?
		) 
		|
		(
		f=accessor_path	
		{
		    // use $f.start to get token matched in identifier
		    // or use $f.text to get text.
		    if( $f.text != null ) {
			location.setType(Location.LOCATION_LHS_INSIDE_CONDITION_OPERATOR);
			location.setProperty(Location.LOCATION_PROPERTY_PROPERTY_NAME, $f.text);
		    
			fc = new FieldConstraintDescr($f.text);
			fc.setLocation( offset($f.start.getLine()), $f.start.getCharPositionInLine() );
			fc.setStartCharacter( ((CommonToken)$f.start).getStartIndex() );
			top = fc.getRestriction();
			
			$base.addDescr( fc );
		    }
		}
		or_restr_connective[top]
		)
	;
	catch[ NoViableAltException nvae ] {
	    if( input.LT(1) != null ) {
	                // in case of incomplete parsing, build as much as possible of the AST
	                // so that IDE requirements can be met
			fc = new FieldConstraintDescr( input.LT(1).getText() );
			fc.setLocation( offset(input.LT(1).getLine()), input.LT(1).getCharPositionInLine() );
			fc.setStartCharacter( ((CommonToken)input.LT(1)).getStartIndex() );
			$base.addDescr( fc );
	    }
	    throw nvae;
	}
	

or_restr_connective[ RestrictionConnectiveDescr base ]
	options { 
		backtrack=true;
	}
	@init {
		RestrictionConnectiveDescr or = new RestrictionConnectiveDescr(RestrictionConnectiveDescr.OR);
	}
	:
		and_restr_connective[or]
		( 
			options {backtrack=true;} : 
			DOUBLE_PIPE 
			{
				location.setType(Location.LOCATION_LHS_INSIDE_CONDITION_OPERATOR);
			}
			and_restr_connective[or] 
		)*
	;
	finally {
	        if( or.getRestrictions().size() == 1 ) {
	                $base.addOrMerge( (RestrictionDescr) or.getRestrictions().get( 0 ) );
	        } else if ( or.getRestrictions().size() > 1 ) {
	        	$base.addRestriction( or );
	        }
	}

and_restr_connective[ RestrictionConnectiveDescr base ]
	@init {
		RestrictionConnectiveDescr and = new RestrictionConnectiveDescr(RestrictionConnectiveDescr.AND);
	}
	:
		constraint_expression[and]
		(	options {backtrack=true;}
		:	t=DOUBLE_AMPER 
			{
				location.setType(Location.LOCATION_LHS_INSIDE_CONDITION_OPERATOR);
			}
			constraint_expression[and] 
		)*
	;
	finally {
	        if( and.getRestrictions().size() == 1) {
	                $base.addOrMerge( (RestrictionDescr) and.getRestrictions().get( 0 ) );
	        } else if ( and.getRestrictions().size() > 1 ) {
	        	$base.addRestriction( and );
	        }
	}
	
constraint_expression[RestrictionConnectiveDescr base]
        :	
		( compound_operator[$base]
		| simple_operator[$base] 
		| LEFT_PAREN 
		{
			location.setType(Location.LOCATION_LHS_INSIDE_CONDITION_OPERATOR);
		}
		or_restr_connective[$base]
		RIGHT_PAREN
 		)
	;	
	
simple_operator[RestrictionConnectiveDescr base]
	@init {
		String op = null;
		String paramText = null;
		boolean isNegated = false;
	}
	:
		(	t='=='
		|	t='>'
		|	t='>='
		|	t='<'
		|	t='<='
		|	t='!='
                |       t=CONTAINS
                |       n=NOT t=CONTAINS
                |       t=EXCLUDES
                |       t=MATCHES
                |       t=SOUNDSLIKE
                |       n=NOT t=MATCHES
                |       t=MEMBEROF
                |       n=NOT t=MEMBEROF
		|	TILDE t=ID param=square_chunk?
		|	n=NOT TILDE t=ID param=square_chunk?
		)
		{
  		    location.setType(Location.LOCATION_LHS_INSIDE_CONDITION_ARGUMENT);
                    location.setProperty(Location.LOCATION_PROPERTY_OPERATOR, $t.text);
 	            op = $t.text;
 	            isNegated = $n != null;
 	            if( param != null ) {
                        paramText = safeSubstring( $param.text, 1, $param.text.length()-1 );
 	            } 
		}
		rd=expression_value[$base, op, isNegated, paramText]
	;	
	finally {
		if ( $rd.rd == null && op != null ) {
		        $base.addRestriction( new LiteralRestrictionDescr(op, false, null) );
		}
	}
	
compound_operator[RestrictionConnectiveDescr base]
	@init {
		String op = null;
		RestrictionConnectiveDescr group = null;
	}
	:
		( IN 
			{
			  op = "==";
			  group = new RestrictionConnectiveDescr(RestrictionConnectiveDescr.OR);
			  $base.addRestriction( group );
  		    	  location.setType(Location.LOCATION_LHS_INSIDE_CONDITION_ARGUMENT);
                    	  location.setProperty(Location.LOCATION_PROPERTY_OPERATOR, "in");
			}
		| NOT IN 
			{
			  op = "!=";
			  group = new RestrictionConnectiveDescr(RestrictionConnectiveDescr.AND);
			  $base.addRestriction( group );
  		    	  location.setType(Location.LOCATION_LHS_INSIDE_CONDITION_ARGUMENT);
                    	  location.setProperty(Location.LOCATION_PROPERTY_OPERATOR, "in");
			}	
		)
		LEFT_PAREN rd=expression_value[group, op, false, null]
		( COMMA rd=expression_value[group, op, false, null]	)* 
		RIGHT_PAREN 
		{
			location.setType(Location.LOCATION_LHS_INSIDE_CONDITION_END);
		}
	;
	
expression_value[RestrictionConnectiveDescr base, String op, boolean isNegated, String paramText] returns [RestrictionDescr rd]
	@init {
		$rd = null;
	}
	:
		(	ap=accessor_path 
			{ 
			        if( $ap.text.indexOf( '.' ) > -1 || $ap.text.indexOf( '[' ) > -1) {
					$rd = new QualifiedIdentifierRestrictionDescr($op, $isNegated, $paramText, $ap.text);
				} else {
					$rd = new VariableRestrictionDescr($op, $isNegated, $paramText, $ap.text);
				}
			}						
		|	lc=literal_constraint 
			{ 
				$rd  = new LiteralRestrictionDescr($op, $isNegated, $paramText, $lc.text, $lc.type );
			}
		|	rvc=paren_chunk 
			{ 
				$rd = new ReturnValueRestrictionDescr($op, $isNegated, $paramText, safeSubstring( $rvc.text, 1, $rvc.text.length()-1) );							
			} 
		)	
		{
			if( $rd != null ) {
				$base.addRestriction( $rd );
			}
			location.setType(Location.LOCATION_LHS_INSIDE_CONDITION_END);
		}
	;	
	
literal_constraint returns [String text, int type]
	@init {
		$text = null;
	}
	:	(	t=STRING { $text = getString( $t.text ); $type = LiteralRestrictionDescr.TYPE_STRING; } 
		|	t=INT    { $text = $t.text; $type = LiteralRestrictionDescr.TYPE_NUMBER; }
		|	t=FLOAT	 { $text = $t.text; $type = LiteralRestrictionDescr.TYPE_NUMBER; }
		|	t=BOOL 	 { $text = $t.text; $type = LiteralRestrictionDescr.TYPE_BOOLEAN; }
		|	t=NULL   { $text = null; $type = LiteralRestrictionDescr.TYPE_NULL; }
		)
	;
	
predicate[ConditionalElementDescr base]
        @init {
		PredicateDescr d = null;
        }
	:
		text=paren_chunk
		{
		        if( $text.text != null ) {
				d = new PredicateDescr( );
			        d.setContent( safeSubstring( $text.text, 1, $text.text.length()-1 ) );
				d.setEndCharacter( ((CommonToken)$text.stop).getStopIndex() );
				$base.addDescr( d );
		        }
		}
	;


curly_chunk
	:
		LEFT_CURLY ( ~(LEFT_CURLY|RIGHT_CURLY) | curly_chunk )* RIGHT_CURLY
	;
	
paren_chunk
	:
		LEFT_PAREN ( ~(LEFT_PAREN|RIGHT_PAREN) | paren_chunk )* RIGHT_PAREN
	;

square_chunk
	:
		LEFT_SQUARE ( ~(LEFT_SQUARE|RIGHT_SQUARE) | square_chunk )* RIGHT_SQUARE
	;
	
qualified_id returns [ String text ]
	@init {
	        StringBuffer buf = new StringBuffer();
	}
	@after {
	        $text = buf != null ? buf.toString() : "";
	}
	: 	ID {buf.append($ID.text);} ( DOT identifier {buf.append("."+$identifier.text);} )* ( LEFT_SQUARE RIGHT_SQUARE {buf.append("[]");})*
	;
	
dotted_name returns [ String text ]
	@init {
	        StringBuffer buf = new StringBuffer();
	}
	@after {
	        $text = buf != null ? buf.toString() : "";
	}
	:	i=identifier {buf.append($i.text);} ( DOT i=identifier {buf.append("."+$i.text);} )* ( LEFT_SQUARE RIGHT_SQUARE {buf.append("[]");})*
	;
	
accessor_path returns [ String text ]
	@init {
	        StringBuffer buf = new StringBuffer();
	}
	@after {
	        $text = buf != null ? buf.toString() : "";
	}
	:	a=accessor_element {buf.append($a.text);} ( DOT a=accessor_element {buf.append("."+$a.text);} )* 
	;
	
accessor_element returns [ String text ]
	@init {
	        StringBuffer buf = new StringBuffer();
	}
	@after {
	        $text = buf != null ? buf.toString() : "";
	}
	:
		i=identifier {buf.append($i.text);} (s=square_chunk {buf.append($s.text);} )*
	;	
	
rhs_chunk[RuleDescr rule]
	:
		THEN { location.setType( Location.LOCATION_RHS ); }
		( ~END )*
                loc=END opt_semicolon
                {
                    // ignoring first line in the consequence
                    String buf = input.toString( $THEN, $loc );
                    // removing final END keyword
                    int idx=4;
                    while( idx < buf.length()-3 && (buf.charAt(idx) == ' ' || buf.charAt(idx) == '\t') ) {
                        idx++;
                    }
                    if( idx < buf.length()-3 && buf.charAt(idx) == '\r' ) idx++;
                    if( idx < buf.length()-3 && buf.charAt(idx) == '\n' ) idx++;
                    buf = safeSubstring( buf, idx, buf.length()-3 );
		    $rule.setConsequence( buf );
     		    $rule.setConsequenceLocation(offset($THEN.line), $THEN.pos);
 		    $rule.setEndCharacter( ((CommonToken)$loc).getStopIndex() );
 		    location.setProperty( Location.LOCATION_RHS_CONTENT, $rule.getConsequence() );
                }
	;

name returns [String name]
	: 	ID { $name = $ID.text; }
	| 	STRING { $name = getString( $STRING.text ); }
	;
	
identifier
	:       ID      
	|	PACKAGE
	|	FUNCTION
	|	GLOBAL
	|	IMPORT  
	|	EVENT
	|	RULE
	|	QUERY 
        |       TEMPLATE        
        |       ATTRIBUTES      
        |       ENABLED         
        |       SALIENCE 	
        |       DURATION 
        |	DIALECT	
        |       FROM	        
        |       INIT	        
        |       ACTION	        
        |       REVERSE	        
        |       RESULT	        
        |       WHEN            
        |       THEN	        
        |       END     
        |	IN        
	;
	
WS      :       (	' '
                |	'\t'
                |	'\f'
                |	EOL
                )+
                { $channel=HIDDEN; }
        ;

fragment
EOL 	:	     
   		(       ( '\r\n' )=> '\r\n'  // Evil DOS
                |       '\r'    // Macintosh
                |       '\n'    // Unix (the right way)
                )
        ;  
        
INT	
	:	('-')?('0'..'9')+
		;

FLOAT
	:	('-')?('0'..'9')+ '.' ('0'..'9')+
	;

STRING
    :  ('"' ( EscapeSequence | ~('\\'|'"') )* '"')
     | ('\'' ( EscapeSequence | ~('\\'|'\'') )* '\'')
    ;

fragment
HexDigit : ('0'..'9'|'a'..'f'|'A'..'F') ;

fragment
EscapeSequence
    :   '\\' ('b'|'B'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\'|'.'|'o'|
              'x'|'a'|'e'|'c'|'d'|'D'|'s'|'S'|'w'|'W'|'p'|'A'|
              'G'|'Z'|'z'|'Q'|'E'|'*'|'['|']'|'('|')'|'$'|'^'|
              '{'|'}'|'?'|'+'|'-'|'&'|'|')
    |   UnicodeEscape
    |   OctalEscape
    ;

fragment
OctalEscape
    :   '\\' ('0'..'3') ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7')
    ;

fragment
UnicodeEscape
    :   '\\' 'u' HexDigit HexDigit HexDigit HexDigit
    ;

BOOL
	:	('true'|'false') 
	;	

PACKAGE	:	'package';

IMPORT	:	'import';

FUNCTION :	'function';

EVENT :		'event';

GLOBAL	:	'global';
	
DECLARE	:	'declare';

RULE    :	'rule';

QUERY	:	'query';

TEMPLATE :	'template';

ATTRIBUTES :	'attributes';
	
DATE_EFFECTIVE 
	:	'date-effective';

DATE_EXPIRES 
	:	'date-expires';	
	
ENABLED :	'enabled';

SALIENCE 
	:	'salience';
	
NO_LOOP :	'no-loop';

AUTO_FOCUS 
	:	'auto-focus';
	
ACTIVATION_GROUP 
	:	'activation-group';
	
AGENDA_GROUP 
	:	'agenda-group';
	
DIALECT 
	:	'dialect';	
	
RULEFLOW_GROUP 
	:	'ruleflow-group';
	
DURATION 
	:	'duration';
	
LOCK_ON_ACTIVE
	:	'lock-on-active';	
	
FROM	:	'from';

ACCUMULATE 
	:	'accumulate';
	
INIT	:	'init';

ACTION	:	'action';

REVERSE	:	'reverse';

RESULT	:	'result';

COLLECT :	'collect';

ENTRY_POINT :	'entry-point';

OR	:	'or';

AND	:	'and';

CONTAINS 
       :       'contains';

EXCLUDES 
       :       'excludes';

MEMBEROF
       :       'memberOf';

MATCHES :      'matches';

SOUNDSLIKE :   'soundslike';

IN	:	'in';

NULL	:	'null';

EXISTS	:	'exists';

NOT	:	'not';

EVAL	:	'eval';

FORALL	:	'forall';							

WHEN    :	'when'; 

THEN	:    	'then';

END     :	'end';

OVER	:	'over';

WINDOW	:	'window';

ID	
	:	('a'..'z'|'A'..'Z'|'_'|'$'|'\u00c0'..'\u00ff')('a'..'z'|'A'..'Z'|'_'|'0'..'9'|'\u00c0'..'\u00ff')* 
	;
		
LEFT_PAREN
        :	'('
        ;

RIGHT_PAREN
        :	')'
        ;
        
LEFT_SQUARE
        :	'['
        ;

RIGHT_SQUARE
        :	']'
        ;        

LEFT_CURLY
        :	'{'
        ;

RIGHT_CURLY
        :	'}'
        ;
        
COMMA	:	','
	;
	
DOT	:	'.'
	;	
	
DOUBLE_AMPER
	:	'&&'
	;
	
DOUBLE_PIPE
	:	'||'
	;				
	
TILDE	:	'~';	

AT	:       '@';

EQUALS  :	'=';

COLON   :	':';
	
SH_STYLE_SINGLE_LINE_COMMENT	
	:	'#' ( options{greedy=false;} : .)* EOL /* ('\r')? '\n'  */
                { $channel=HIDDEN; setText("//"+getText().substring(1));}
	;
        
        
C_STYLE_SINGLE_LINE_COMMENT	
	:	'//' ( options{greedy=false;} : .)* EOL // ('\r')? '\n' 
                { $channel=HIDDEN; }
	;

MULTI_LINE_COMMENT
	:	'/*' (options{greedy=false;} : .)* '*/'
                { $channel=HIDDEN; }
	;

MISC 	:
		'!' | '$' | '%' | '^' | '*' | '_' | '-' | '+'  | '?' | '/' | '\'' | '\\' | '|' | '&'
	;


