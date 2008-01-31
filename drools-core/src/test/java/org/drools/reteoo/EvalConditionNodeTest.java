package org.drools.reteoo;

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

import org.drools.DroolsTestCase;
import org.drools.FactException;
import org.drools.RuleBaseFactory;
import org.drools.common.DefaultFactHandle;
import org.drools.common.PropagationContextImpl;
import org.drools.reteoo.EvalConditionNode.EvalMemory;
import org.drools.reteoo.builder.BuildContext;
import org.drools.spi.PropagationContext;
import org.drools.util.TupleHashTable;

public class EvalConditionNodeTest extends DroolsTestCase {
    private PropagationContext  context;
    private ReteooWorkingMemory workingMemory;
    private ReteooRuleBase ruleBase;
    private BuildContext buildContext;    

    public EvalConditionNodeTest(final String name) {
        super( name );
    }

    public void setUp() {
        this.ruleBase = ( ReteooRuleBase ) RuleBaseFactory.newRuleBase();
        this.buildContext = new BuildContext( ruleBase, ((ReteooRuleBase)ruleBase).getReteooBuilder().getIdGenerator() );
        
        this.context = new PropagationContextImpl( 0,
                                                   PropagationContext.ASSERTION,
                                                   null,
                                                   null );

        this.workingMemory = ( ReteooWorkingMemory ) this.ruleBase.newStatefulSession();
    }

    public void testAttach() throws Exception {
        final MockTupleSource source = new MockTupleSource( 12 );

        final EvalConditionNode node = new EvalConditionNode( 18,
                                                              source,
                                                              new MockEvalCondition( true ),
                                                              buildContext );

        assertEquals( 18,
                      node.getId() );

        assertEquals( 0,
                      source.getAttached() );

        node.attach();

        assertEquals( 1,
                      source.getAttached() );

    }

    public void testMemory() {
        final ReteooWorkingMemory workingMemory = new ReteooWorkingMemory( 1,
                                                                           (ReteooRuleBase) RuleBaseFactory.newRuleBase() );

        final MockTupleSource source = new MockTupleSource( 12 );

        final EvalConditionNode node = new EvalConditionNode( 18,
                                                              source,
                                                              new MockEvalCondition( true ),
                                                              buildContext );

        final EvalMemory memory = (EvalMemory) workingMemory.getNodeMemory( node );

        assertNotNull( memory.tupleMemory );
    }

    /**
     * If a eval allows an incoming Object, then the Object MUST be
     * propagated. This tests that the memory is updated
     * 
     * @throws FactException
     */
    public void testAssertedAllowed() throws FactException {
        final MockEvalCondition eval = new MockEvalCondition( true );

        // Create a test node that always returns false 
        final EvalConditionNode node = new EvalConditionNode( 1,
                                                              new MockTupleSource( 15 ),
                                                              eval,
                                                              buildContext );

        final MockTupleSink sink = new MockTupleSink();
        node.addTupleSink( sink );

        // Create the Tuple
        final DefaultFactHandle f0 = new DefaultFactHandle( 0,
                                                            "stilton" );
        final ReteTuple tuple0 = new ReteTuple( f0 );

        // Tuple should pass and propagate 
        node.assertTuple( tuple0,
                          this.context,
                          this.workingMemory );

        // Create the Tuple
        final DefaultFactHandle f1 = new DefaultFactHandle( 1,
                                                            "cheddar" );
        final ReteTuple tuple1 = new ReteTuple( f1 );

        // Tuple should pass and propagate 
        node.assertTuple( tuple1,
                          this.context,
                          this.workingMemory );

        // Check memory was populated
        final EvalMemory memory = (EvalMemory) this.workingMemory.getNodeMemory( node );

        assertEquals( 2,
                      memory.tupleMemory.size() );

        assertTrue( memory.tupleMemory.contains( tuple0 ) );
        assertTrue( memory.tupleMemory.contains( tuple1 ) );

        // make sure assertions were propagated
        assertEquals( 2,
                      sink.getAsserted().size() );
    }

    public void testAssertedAllowedThenRetract() throws FactException {
        final MockEvalCondition eval = new MockEvalCondition( true );

        // Create a test node that always returns false 
        final EvalConditionNode node = new EvalConditionNode( 1,
                                                              new MockTupleSource( 15 ),
                                                              eval,
                                                              buildContext );

        final MockTupleSink sink = new MockTupleSink();
        node.addTupleSink( sink );

        // Create the Tuple
        final DefaultFactHandle f0 = new DefaultFactHandle( 0,
                                                            "stilton" );
        final ReteTuple tuple0 = new ReteTuple( f0 );

        // Tuple should pass and propagate 
        node.assertTuple( tuple0,
                          this.context,
                          this.workingMemory );

        // we create and retract two tuples, checking the linkedtuples is null for JBRULES-246 "NPE on retract()"        
        // Create the Tuple
        final DefaultFactHandle f1 = new DefaultFactHandle( 1,
                                                            "cheddar" );
        final ReteTuple tuple1 = new ReteTuple( f1 );

        // Tuple should pass and propagate 
        node.assertTuple( tuple1,
                          this.context,
                          this.workingMemory );

        // Check memory was populated
        final EvalMemory memory = (EvalMemory) this.workingMemory.getNodeMemory( node );

        assertEquals( 2,
                      memory.tupleMemory.size() );
        assertTrue( memory.tupleMemory.contains( tuple0 ) );
        assertTrue( memory.tupleMemory.contains( tuple1 ) );

        // make sure assertions were propagated
        assertEquals( 2,
                      sink.getAsserted().size() );

        // Now test that the fact is retracted correctly
        node.retractTuple( tuple0,
                           this.context,
                           this.workingMemory );

        // Now test that the fact is retracted correctly
        assertEquals( 1,
                      memory.tupleMemory.size() );

        assertTrue( memory.tupleMemory.contains( tuple1 ) );

        // make sure retractions were propagated
        assertEquals( 1,
                      sink.getRetracted().size() );

        // Now test that the fact is retracted correctly
        node.retractTuple( tuple1,
                           this.context,
                           this.workingMemory );

        // Now test that the fact is retracted correctly
        assertEquals( 0,
                      memory.tupleMemory.size() );

        // make sure retractions were propagated
        assertEquals( 2,
                      sink.getRetracted().size() );
    }

    public void testAssertedNotAllowed() throws FactException {
        final MockEvalCondition eval = new MockEvalCondition( false );

        // Create a test node that always returns false 
        final EvalConditionNode node = new EvalConditionNode( 1,
                                                              new MockTupleSource( 15 ),
                                                              eval,
                                                              buildContext );

        final MockTupleSink sink = new MockTupleSink();
        node.addTupleSink( sink );

        // Create the Tuple
        final DefaultFactHandle f0 = new DefaultFactHandle( 0,
                                                            "stilton" );
        final ReteTuple tuple0 = new ReteTuple( f0 );

        // Tuple should fail and not propagate
        node.assertTuple( tuple0,
                          this.context,
                          this.workingMemory );

        // Create the Tuple
        final DefaultFactHandle f1 = new DefaultFactHandle( 1,
                                                            "cheddar" );
        final ReteTuple tuple1 = new ReteTuple( f1 );

        // Tuple should fail and not propagate 
        node.assertTuple( tuple1,
                          this.context,
                          this.workingMemory );

        // Check memory was not populated
        final EvalMemory memory = (EvalMemory) this.workingMemory.getNodeMemory( node );

        assertEquals( 0,
                      memory.tupleMemory.size() );

        // test no propagations
        assertEquals( 0,
                      sink.getAsserted().size() );
        assertEquals( 0,
                      sink.getRetracted().size() );
    }

    public void testUpdateWithMemory() throws FactException {
        // If no child nodes have children then we need to re-process the left
        // and right memories
        // as a joinnode does not store the resulting tuples
        final ReteooWorkingMemory workingMemory = new ReteooWorkingMemory( 1,
                                                                           (ReteooRuleBase) RuleBaseFactory.newRuleBase() );

        // Creat the object source so we can detect the alphaNode telling it to
        // propate its contents
        //final MockTupleSource source = new MockTupleSource( 1 );

        /* Create a test node that always returns true */
        final EvalConditionNode node = new EvalConditionNode( 1,
                                                              new MockTupleSource( 15 ),
                                                              new MockEvalCondition( true ),
                                                              buildContext );

        // Add the first tuple sink and assert a tuple and object
        // The sink has no memory
        final MockTupleSink sink1 = new MockTupleSink( 2 );
        node.addTupleSink( sink1 );

        final DefaultFactHandle f0 = new DefaultFactHandle( 0,
                                                            "string0" );

        final ReteTuple tuple1 = new ReteTuple( f0 );

        node.assertTuple( tuple1,
                          this.context,
                          workingMemory );

        assertLength( 1,
                      sink1.getAsserted() );

        // Add the new sink, this should be updated from the re-processed
        // joinnode memory
        final MockTupleSink sink2 = new MockTupleSink( 3 );
        node.addTupleSink( sink2 );
        assertLength( 0,
                      sink2.getAsserted() );

        node.updateSink( sink2,
                         this.context,
                         workingMemory );

        assertLength( 1,
                      sink2.getAsserted() );
    }
}