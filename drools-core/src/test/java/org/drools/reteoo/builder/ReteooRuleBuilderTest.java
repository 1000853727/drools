/*
 * Copyright 2006 JBoss Inc
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

package org.drools.reteoo.builder;

import java.util.List;
import java.io.ObjectOutput;
import java.io.ObjectInput;
import java.io.IOException;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.drools.WorkingMemory;
import org.drools.base.ClassObjectType;
import org.drools.reteoo.ReteooBuilder;
import org.drools.reteoo.ReteooRuleBase;
import org.drools.reteoo.RuleTerminalNode;
import org.drools.reteoo.ReteooBuilder.IdGenerator;
import org.drools.rule.Package;
import org.drools.rule.Pattern;
import org.drools.rule.GroupElement;
import org.drools.rule.GroupElementFactory;
import org.drools.rule.Rule;
import org.drools.spi.Consequence;
import org.drools.spi.KnowledgeHelper;

/**
 * @author etirelli
 *
 */
public class ReteooRuleBuilderTest extends TestCase {
    private ReteooRuleBuilder builder;
    private ReteooRuleBase    rulebase;

    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();

        this.builder = new ReteooRuleBuilder();
        this.rulebase = new ReteooRuleBase( "default" );
    }

    /* (non-Javadoc)
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test method for {@link org.drools.reteoo.builder.ReteooRuleBuilder#addRule(org.drools.rule.Rule, org.drools.reteoo.ReteooRuleBase, java.util.Map, int)}.
     */
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
        };

        rule.setConsequence( consequence );

        final List terminals = this.builder.addRule( rule,
                                               this.rulebase,
                                               new ReteooBuilder.IdGenerator( 1 ) );

        Assert.assertEquals( "Rule must have a single terminal node",
                             1,
                             terminals.size() );

        final RuleTerminalNode terminal = (RuleTerminalNode) terminals.get( 0 );

    }


}
