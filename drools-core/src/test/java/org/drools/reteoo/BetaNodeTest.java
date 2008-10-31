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

package org.drools.reteoo;

import junit.framework.TestCase;

import org.drools.RuleBaseFactory;
import org.drools.common.EmptyBetaConstraints;
import org.drools.reteoo.builder.BuildContext;
import org.drools.rule.Behavior;

/**
 * @author etirelli
 *
 */
public class BetaNodeTest extends TestCase {

    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
    }

    /* (non-Javadoc)
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test method for {@link org.drools.reteoo.BetaNode#equals(java.lang.Object)}.
     */
    public void testEqualsObject() {
        final LeftTupleSource ts = new MockTupleSource( 1 );
        final ObjectSource os = new MockObjectSource( 2 );

        ReteooRuleBase ruleBase = ( ReteooRuleBase ) RuleBaseFactory.newRuleBase();
        BuildContext buildContext = new BuildContext( ruleBase, ruleBase.getReteooBuilder().getIdGenerator() );        
        
        final BetaNode j1 = new JoinNode( 1,
                                          ts,
                                          os,
                                          EmptyBetaConstraints.getInstance(),
                                          Behavior.EMPTY_BEHAVIOR_LIST,
                                          buildContext );
        final BetaNode j2 = new JoinNode( 2,
                                          ts,
                                          os,
                                          EmptyBetaConstraints.getInstance(),
                                          Behavior.EMPTY_BEHAVIOR_LIST,
                                          buildContext );
        final BetaNode n1 = new NotNode( 3,
                                         ts,
                                         os,
                                         EmptyBetaConstraints.getInstance(),
                                         Behavior.EMPTY_BEHAVIOR_LIST,
                                         buildContext );
        final BetaNode n2 = new NotNode( 4,
                                         ts,
                                         os,
                                         EmptyBetaConstraints.getInstance(),
                                         Behavior.EMPTY_BEHAVIOR_LIST,
                                         buildContext );

        assertEquals( j1,
                      j1 );
        assertEquals( j2,
                      j2 );
        assertEquals( j1,
                      j2 );
        assertEquals( n1,
                      n1 );
        assertEquals( n2,
                      n2 );
        assertEquals( n1,
                      n2 );

        assertFalse( j1.equals( n1 ) );
        assertFalse( j1.equals( n2 ) );
        assertFalse( n1.equals( j1 ) );
        assertFalse( n1.equals( j2 ) );
    }

}
