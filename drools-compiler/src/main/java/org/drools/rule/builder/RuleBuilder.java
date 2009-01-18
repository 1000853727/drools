package org.drools.rule.builder;

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

import java.util.Calendar;

import org.drools.RuntimeDroolsException;
import org.drools.base.EnabledBoolean;
import org.drools.base.SalienceInteger;
import org.drools.lang.descr.AttributeDescr;
import org.drools.lang.descr.QueryDescr;
import org.drools.lang.descr.RuleDescr;
import org.drools.rule.GroupElement;
import org.drools.rule.Pattern;
import org.drools.rule.Rule;
import org.drools.spi.Salience;
import org.drools.time.TimeUtils;
import org.drools.util.DateUtils;

/**
 * This builds the rule structure from an AST.
 * Generates semantic code where necessary if semantics are used.
 * This is an internal API.
 */
public class RuleBuilder {

    // Constructor
    public RuleBuilder() {
    }

    /**
     * Build the give rule into the 
     * @param pkg
     * @param ruleDescr
     * @return
     */
    public void build(final RuleBuildContext context) {
        RuleDescr ruleDescr = context.getRuleDescr();

        //Query and get object instead of using String
        if ( null != ruleDescr.getParentName() && null != context.getPkg().getRule( ruleDescr.getParentName() ) ) {
            context.getRule().setParent( context.getPkg().getRule( ruleDescr.getParentName() ) );
        }
        // add all the rule's meta attributes
        context.getRule().getMetaAttributes().putAll( ruleDescr.getMetaAttributes() );

        final RuleConditionBuilder builder = (RuleConditionBuilder) context.getDialect().getBuilder( ruleDescr.getLhs().getClass() );
        if ( builder != null ) {
            Pattern prefixPattern = null;
            if ( context.getRuleDescr() instanceof QueryDescr ) {
                prefixPattern = context.getDialect().getQueryBuilder().build( context,
                                                                              (QueryDescr) context.getRuleDescr() );
            }
            final GroupElement ce = (GroupElement) builder.build( context,
                                                                  ruleDescr.getLhs(),
                                                                  prefixPattern );

            context.getRule().setLhs( ce );
        } else {
            throw new RuntimeDroolsException( "BUG: builder not found for descriptor class " + ruleDescr.getLhs().getClass() );
        }

        // build all the rule's attributes
        // must be after building LHS because some attributes require bindings from the LHS
        buildAttributes( context );

        // Build the consequence and generate it's invoker/s
        // generate the main rule from the previously generated s.
        if ( !(ruleDescr instanceof QueryDescr) ) {
            // do not build the consequence if we have a query

            context.getDialect().getConsequenceBuilder().build( context );
        }

    }

    public void buildAttributes(final RuleBuildContext context) {
        final Rule rule = context.getRule();
        final RuleDescr ruleDescr = context.getRuleDescr();

        for ( final AttributeDescr attributeDescr : ruleDescr.getAttributes().values() ) {
            final String name = attributeDescr.getName();
            if ( name.equals( "no-loop" ) ) {
                rule.setNoLoop( getBooleanValue( attributeDescr,
                                                 true ) );
            } else if ( name.equals( "auto-focus" ) ) {
                rule.setAutoFocus( getBooleanValue( attributeDescr,
                                                    true ) );
            } else if ( name.equals( "agenda-group" ) ) {
                rule.setAgendaGroup( attributeDescr.getValue() );
            } else if ( name.equals( "activation-group" ) ) {
                rule.setActivationGroup( attributeDescr.getValue() );
            } else if ( name.equals( "ruleflow-group" ) ) {
                rule.setRuleFlowGroup( attributeDescr.getValue() );
            } else if ( name.equals( "lock-on-active" ) ) {
                rule.setLockOnActive( getBooleanValue( attributeDescr,
                                                       true ) );
            } else if ( name.equals( "duration" ) ) {
                String duration = attributeDescr.getValue();
                if( duration.indexOf( '(' ) >=0 ) {
                    duration = duration.substring( duration.indexOf( '(' )+1, duration.lastIndexOf( ')' ) );
                }
                rule.setDuration( TimeUtils.parseTimeString( duration ) );
            } else if ( name.equals( "date-effective" ) ) {
                final Calendar cal = Calendar.getInstance();
                cal.setTime( DateUtils.parseDate( attributeDescr.getValue() ) );
                rule.setDateEffective( cal );
            } else if ( name.equals( "date-expires" ) ) {
                final Calendar cal = Calendar.getInstance();
                cal.setTime( DateUtils.parseDate( attributeDescr.getValue() ) );
                rule.setDateExpires( cal );
            }
        }

        buildSalience( context );

        buildEnabled( context );

        //        buildDuration( context );
    }

    private boolean getBooleanValue(final AttributeDescr attributeDescr,
                                    final boolean defaultValue) {
        return (attributeDescr.getValue() == null || "".equals( attributeDescr.getValue().trim() )) ? defaultValue : Boolean.valueOf( attributeDescr.getValue() ).booleanValue();
    }

    //    private void buildDuration(final RuleBuildContext context) {
    //        String durationText = context.getRuleDescr().getDuration();
    //        try {
    //            // First see if its an Integer
    //            if ( durationText != null && !durationText.equals( "" )) {
    //                Duration duration = new DurationInteger( Integer.parseInt( durationText ) );
    //                context.getRule().setDuration( duration );
    //            }
    //        } catch (Exception e) {
    //            // It wasn't an integer, so build as an expression
    //            context.getDialect().getDurationBuilder().build( context );    
    //        }
    //    }

    private void buildEnabled(final RuleBuildContext context) {
        String enabledText = context.getRuleDescr().getEnabled();
        if ( enabledText != null ) {
            if ( "true".equalsIgnoreCase( enabledText.trim() ) || "false".equalsIgnoreCase( enabledText.trim() ) ) {
                if ( Boolean.parseBoolean( enabledText ) ) {
                    context.getRule().setEnabled( EnabledBoolean.ENABLED_TRUE );
                } else {
                    context.getRule().setEnabled( EnabledBoolean.ENABLED_FALSE );
                }
            } else {
                context.getDialect().getEnabledBuilder().build( context );
            }
        }
    }

    private void buildSalience(final RuleBuildContext context) {
        String salienceText = context.getRuleDescr().getSalience();
        if ( salienceText != null && !salienceText.equals( "" ) ) {
            try {
                // First check if it is an Integer
                Salience salience = new SalienceInteger( Integer.parseInt( salienceText ) );
                context.getRule().setSalience( salience );
            } catch ( Exception e ) {
                // It wasn't an integer, so build as an expression
                context.getDialect().getSalienceBuilder().build( context );
            }
        }
    }

}