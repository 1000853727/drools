package org.drools.decisiontable;

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
import java.util.List;
import java.util.Map;

import javax.rules.RuleRuntime;
import javax.rules.RuleServiceProvider;
import javax.rules.RuleServiceProviderManager;
import javax.rules.StatefulRuleSession;
import javax.rules.admin.LocalRuleExecutionSetProvider;
import javax.rules.admin.RuleAdministrator;
import javax.rules.admin.RuleExecutionSet;

import junit.framework.TestCase;

import org.drools.jsr94.rules.Constants;
import org.drools.jsr94.rules.ExampleRuleEngineFacade;
import org.drools.jsr94.rules.RuleServiceProviderImpl;

public class SpreadsheetIntegrationTest extends TestCase {

    public void testExecute() throws Exception {
        Map properties = new HashMap();
        properties.put( Constants.RES_SOURCE,
                        Constants.RES_SOURCE_TYPE_DECISION_TABLE );
        
        RuleServiceProviderManager.registerRuleServiceProvider( ExampleRuleEngineFacade.RULE_SERVICE_PROVIDER,
                                                                RuleServiceProviderImpl.class );

        RuleServiceProvider ruleServiceProvider = RuleServiceProviderManager.getRuleServiceProvider( ExampleRuleEngineFacade.RULE_SERVICE_PROVIDER );
        RuleAdministrator ruleAdministrator = ruleServiceProvider.getRuleAdministrator();
        LocalRuleExecutionSetProvider ruleSetProvider = ruleAdministrator.getLocalRuleExecutionSetProvider( null );

        RuleExecutionSet ruleExecutionSet = ruleSetProvider.createRuleExecutionSet( SpreadsheetIntegrationTest.class.getResourceAsStream( "IntegrationExampleTest.xls" ),
                                                                                    properties );

        ruleAdministrator.registerRuleExecutionSet( "IntegrationExampleTest.xls",
                                                    ruleExecutionSet,
                                                    properties );

        properties.clear();
        final List list = new ArrayList();
        properties.put( "list",
                        list );
        
        RuleRuntime ruleRuntime = ruleServiceProvider.getRuleRuntime();
        StatefulRuleSession session = (StatefulRuleSession) ruleRuntime.createRuleSession( "IntegrationExampleTest.xls",
                                                                                           properties,
                                                                                           RuleRuntime.STATEFUL_SESSION_TYPE );

        //ASSERT AND FIRE
        session.addObject( new Cheese( "stilton",
                                       42 ) );
        session.addObject( new Person( "michael",
                                       "stilton",
                                       42 ) );

        session.executeRules();
        assertEquals( 1,
                      list.size() );
    }

}