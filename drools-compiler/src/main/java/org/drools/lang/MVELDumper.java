package org.drools.lang;

/*
 * Author Jayaram C S
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

import java.util.Iterator;

import org.drools.base.evaluators.Operator;
import org.drools.lang.descr.FieldBindingDescr;
import org.drools.lang.descr.FieldConstraintDescr;
import org.drools.lang.descr.LiteralRestrictionDescr;
import org.drools.lang.descr.PredicateDescr;
import org.drools.lang.descr.QualifiedIdentifierRestrictionDescr;
import org.drools.lang.descr.RestrictionConnectiveDescr;
import org.drools.lang.descr.ReturnValueRestrictionDescr;
import org.drools.lang.descr.VariableRestrictionDescr;
import org.drools.util.ReflectiveVisitor;

/**
 * 
 * @author <a href="mailto:jayaramcs@gmail.com">Author Jayaram C S</a>
 *
 */
public class MVELDumper extends ReflectiveVisitor {

    private StringBuffer        mvelDump;
    private boolean             isDateField;
    private static final String eol = System.getProperty( "line.separator" );
    private String              template;
    private String              fieldName;

    public String dump(FieldConstraintDescr fieldConstr) {
        return this.dump( fieldConstr, false );
    }

    public String dump(FieldConstraintDescr fieldConstr, boolean isDateField ) {
        mvelDump = new StringBuffer();
        this.isDateField = isDateField;
        this.visit( fieldConstr );
        return mvelDump.toString();
    }

    public void visitFieldConstraintDescr(final FieldConstraintDescr descr) {
        if ( !descr.getRestrictions().isEmpty() ) {
            this.fieldName = descr.getFieldName();
            mvelDump.append( processFieldConstraint( descr.getRestriction() ) );
        }
    }

    public void visitVariableRestrictionDescr(final VariableRestrictionDescr descr) {
        this.template = processRestriction( descr.getEvaluator(), descr.isNegated(), descr.getIdentifier() );
    }

    public void visitFieldBindingDescr(final FieldBindingDescr descr) {
        // do nothing
    }

    public void visitLiteralRestrictionDescr(final LiteralRestrictionDescr descr) {
        String text = descr.getText();
        if ( text == null || descr.getType() == LiteralRestrictionDescr.TYPE_NULL ) {
            text = "null";
        } else if( descr.getType() == LiteralRestrictionDescr.TYPE_NUMBER ) {
            try {
                Integer.parseInt( text );
            } catch ( final NumberFormatException e ) {
                text = "\"" + text + "\"";
            }
        } else if( descr.getType() == LiteralRestrictionDescr.TYPE_STRING ) {
            text = "\"" + text + "\"";
            if( this.isDateField ) {
                text = "org.drools.util.DateUtils.parseDate( "+text+" )";
            }
        }
        this.template = processRestriction( descr.getEvaluator(), descr.isNegated(), text );
    }

    public void visitQualifiedIdentifierRestrictionDescr(final QualifiedIdentifierRestrictionDescr descr) {
        this.template = processRestriction( descr.getEvaluator(), descr.isNegated(), descr.getText() );
    }

    public void visitRestrictionConnectiveDescr(final RestrictionConnectiveDescr descr) {
        this.template = "( " + this.processFieldConstraint( descr ) + " )";
    }

    public void visitPredicateDescr(final PredicateDescr descr) {
        this.template = "eval( " + descr.getContent() + " )";
    }

    public void visitReturnValueRestrictionDescr(final ReturnValueRestrictionDescr descr) {
        this.template = processRestriction( descr.getEvaluator(), descr.isNegated(),  "( "+descr.getContent().toString()+" )" );
    }

    private String processFieldConstraint(final RestrictionConnectiveDescr restriction) {
        String descrString = "";
        String connective = null;

        if ( restriction.getConnective() == RestrictionConnectiveDescr.OR ) {
            connective = " || ";
        } else {
            connective = " && ";
        }
        for ( final Iterator it = restriction.getRestrictions().iterator(); it.hasNext(); ) {
            final Object temp = it.next();
            visit( temp );
            descrString += this.template;
            if ( it.hasNext() ) {
                descrString += connective;
            }
        }
        return descrString;
    }

    private String processRestriction(String evaluator,
                                      boolean isNegated,
                                      String value) {
        Operator op = Operator.determineOperator( evaluator, isNegated );
        if( op == Operator.determineOperator( "memberOf", false ) ) {
            evaluator = "contains";
            return evaluatorPrefix( evaluator ) + 
                   value + " " + 
                   evaluator( evaluator ) + " " + 
                   this.fieldName + evaluatorSufix( evaluator );
        } else if(op == Operator.determineOperator( "memberOf", true )) {
            evaluator = "not contains";
            return evaluatorPrefix( evaluator ) + 
                   value + " " + 
                   evaluator( evaluator ) + " " + 
                   this.fieldName + evaluatorSufix( evaluator );
        } else if(op == Operator.determineOperator( "excludes", false ) ) {
            evaluator = "not contains";
            return evaluatorPrefix( evaluator ) + 
                   this.fieldName + " " + 
                   evaluator( evaluator ) + " " + 
                   value + evaluatorSufix( evaluator );
        } else if(op == Operator.determineOperator( "matches", false )) {
            evaluator = "~=";
            return evaluatorPrefix( evaluator ) + 
                   this.fieldName + " " + 
                   evaluator( evaluator ) + " " + 
                   value.replaceAll( "\\\\", "\\\\\\\\" ) + evaluatorSufix( evaluator );
        } else if(op == Operator.determineOperator( "matches", true )) {
            evaluator = "not ~=";
            return evaluatorPrefix( evaluator ) + 
                   this.fieldName + " " + 
                   evaluator( evaluator ) + " " + 
                   value.replaceAll( "\\\\", "\\\\\\\\" ) + evaluatorSufix( evaluator );
        }
        return evaluatorPrefix( evaluator ) + 
               this.fieldName + " " + 
               evaluator( evaluator ) + " " + 
               value + evaluatorSufix( evaluator );
    }

    private String evaluatorPrefix(String evaluator) {
        if ( evaluator.startsWith( "not" ) ) {
            return "!( ";
        }
        return "";
    }

    private String evaluator(String evaluator) {
        if ( evaluator.startsWith( "not" ) ) {
            return evaluator.substring( 4 );
        }
        return evaluator;
    }

    private String evaluatorSufix(String evaluator) {
        if ( evaluator.startsWith( "not" ) ) {
            return " )";
        }
        return "";
    }
}