/*
 * Copyright 2010 JBoss Inc
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

package org.drools.core.reteoo.builder;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.drools.core.base.ClassObjectType;
import org.drools.core.reteoo.ReteooBuilder;
import org.drools.core.reteoo.ReteooRuleBase;
import org.drools.core.reteoo.RuleTerminalNode;
import org.drools.core.rule.GroupElement;
import org.drools.core.rule.GroupElementFactory;
import org.drools.core.rule.Pattern;
import org.drools.core.rule.Rule;
import org.drools.core.WorkingMemory;
import org.drools.core.spi.Consequence;
import org.drools.core.spi.KnowledgeHelper;

public class ReteooRuleBuilderTest {
    private ReteooRuleBuilder builder;
    private ReteooRuleBase    rulebase;

    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    @Before
    public void setUp() throws Exception {
        this.builder = new ReteooRuleBuilder();
        this.rulebase = new ReteooRuleBase( "default", null );
    }

    /**
     * Test method for {@link org.kie.reteoo.builder.ReteooRuleBuilder#addRule(org.kie.rule.Rule, org.kie.reteoo.ReteooRuleBase, java.util.Map, int)}.
     */
    @Test
    public void testAddRuleWithPatterns() {
        final Rule rule = new Rule( "only patterns" );
        final Pattern c1 = new Pattern( 0,
                                new ClassObjectType( String.class ) );
        final Pattern c2 = new Pattern( 1,
                                new ClassObjectType( String.class ) );
        final Pattern c3 = new Pattern( 2,
                                new ClassObjectType( String.class ) );

        final GroupElement lhsroot = GroupElementFactory.newAndInstance();
        lhsroot.addChild( c1 );
        lhsroot.addChild( c2 );
        lhsroot.addChild( c3 );

        rule.setLhs( lhsroot );

        final Consequence consequence = new Consequence() {
            public void evaluate(KnowledgeHelper knowledgeHelper,
                                 WorkingMemory workingMemory) throws Exception {
                System.out.println( "Consequence!" );
            }

            public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

            }

            public void writeExternal(ObjectOutput out) throws IOException {

            }
            
            public String getName() {
                return "default";
            }
        };

        rule.setConsequence( consequence );

        final List terminals = this.builder.addRule( rule,
                                               this.rulebase,
                                               new ReteooBuilder.IdGenerator( 1 ) );

        assertEquals( "Rule must have a single terminal node",
                             1,
                             terminals.size() );

        final RuleTerminalNode terminal = (RuleTerminalNode) terminals.get( 0 );

    }


}
