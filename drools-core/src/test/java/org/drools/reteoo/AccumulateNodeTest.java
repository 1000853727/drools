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
package org.drools.reteoo;

import junit.framework.Assert;

import org.drools.DroolsTestCase;
import org.drools.RuleBaseConfiguration;
import org.drools.RuleBaseFactory;
import org.drools.base.ClassObjectType;
import org.drools.common.DefaultFactHandle;
import org.drools.common.EmptyBetaConstraints;
import org.drools.common.InternalFactHandle;
import org.drools.common.InternalWorkingMemory;
import org.drools.common.PropagationContextImpl;
import org.drools.reteoo.AccumulateNode.AccumulateMemory;
import org.drools.reteoo.builder.BuildContext;
import org.drools.rule.Accumulate;
import org.drools.rule.Declaration;
import org.drools.rule.Pattern;
import org.drools.rule.Rule;
import org.drools.spi.AlphaNodeFieldConstraint;
import org.drools.spi.MockConstraint;
import org.drools.spi.ObjectType;
import org.drools.spi.PropagationContext;

/**
 * A test case for AccumulateNode
 * 
 * @author etirelli
 */
public class AccumulateNodeTest extends DroolsTestCase {

    Rule                rule;
    PropagationContext  context;
    ReteooWorkingMemory workingMemory;
    MockObjectSource    objectSource;
    MockTupleSource     tupleSource;
    MockTupleSink       sink;
    BetaNode            node;
    BetaMemory          memory;
    MockConstraint      constraint = new MockConstraint();
    MockAccumulator     accumulator;
    Accumulate          accumulate;

    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        this.rule = new Rule( "test-rule" );
        this.context = new PropagationContextImpl( 0,
                                                   PropagationContext.ASSERTION,
                                                   null,
                                                   null );
        
        ReteooRuleBase ruleBase = (ReteooRuleBase) RuleBaseFactory.newRuleBase();
        BuildContext buildContext = new BuildContext( ruleBase,
                                                      ruleBase.getReteooBuilder().getIdGenerator() );
        
        this.workingMemory = (ReteooWorkingMemory) ruleBase.newStatefulSession();

        this.tupleSource = new MockTupleSource( 4 );
        this.objectSource = new MockObjectSource( 4 );
        this.sink = new MockTupleSink();

        this.accumulator = new MockAccumulator();

        final ObjectType srcObjType = new ClassObjectType( String.class );
        final Pattern sourcePattern = new Pattern( 0,
                                                srcObjType );
        this.accumulate = new Accumulate( sourcePattern,
                                          new Declaration[0],
                                          new Declaration[0],
                                          this.accumulator );
        
        

        this.node = new AccumulateNode( 15,
                                        this.tupleSource,
                                        this.objectSource,
                                        new AlphaNodeFieldConstraint[0],
                                        EmptyBetaConstraints.getInstance(),
                                        EmptyBetaConstraints.getInstance(),
                                        this.accumulate,
                                        false,
                                        buildContext );

        this.node.addTupleSink( this.sink );

        this.memory = ((AccumulateMemory) this.workingMemory.getNodeMemory( this.node )).betaMemory;

        // check memories are empty
        assertEquals( 0,
                      this.memory.getTupleMemory().size() );
        assertEquals( 0,
                      this.memory.getFactHandleMemory().size() );
    }

    /* (non-Javadoc)
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test method for {@link org.drools.reteoo.AccumulateNode#updateNewNode(InternalWorkingMemory, org.drools.spi.PropagationContext)}.
     */
    public void testUpdateSink() {
        this.node.updateSink( this.sink,
                              this.context,
                              this.workingMemory );
        Assert.assertEquals( "No tuple should be propagated",
                             0,
                             this.sink.getAsserted().size() );

        this.node.assertTuple( new ReteTuple( this.workingMemory.getFactHandleFactory().newFactHandle( "cheese" ) ),
                               this.context,
                               this.workingMemory );
        this.node.assertTuple( new ReteTuple( this.workingMemory.getFactHandleFactory().newFactHandle( "other cheese" ) ),
                               this.context,
                               this.workingMemory );

        Assert.assertEquals( "Two tuples should have been propagated",
                             2,
                             this.sink.getAsserted().size() );

        final MockTupleSink otherSink = new MockTupleSink();

        this.node.addTupleSink( otherSink );
        this.node.updateSink( otherSink,
                              this.context,
                              this.workingMemory );

        Assert.assertEquals( "Two tuples should have been propagated",
                             2,
                             otherSink.getAsserted().size() );
    }

    /**
     * Test method for {@link org.drools.reteoo.AccumulateNode#assertTuple(org.drools.reteoo.ReteTuple, org.drools.spi.PropagationContext, org.drools.reteoo.ReteooWorkingMemory)}.
     */
    public void testAssertTuple() {
        final DefaultFactHandle f0 = (DefaultFactHandle) this.workingMemory.getFactHandleFactory().newFactHandle( "cheese" );
        final ReteTuple tuple0 = new ReteTuple( f0 );

        // assert tuple, should add one to left memory
        this.node.assertTuple( tuple0,
                               this.context,
                               this.workingMemory );
        // check memories 
        assertEquals( 1,
                      this.memory.getTupleMemory().size() );
        assertEquals( 0,
                      this.memory.getFactHandleMemory().size() );
        Assert.assertTrue( "An empty matching objects list should be propagated",
                           this.accumulator.getMatchingObjects().isEmpty() );

        // assert tuple, should add left memory 
        final DefaultFactHandle f1 = (DefaultFactHandle) this.workingMemory.getFactHandleFactory().newFactHandle( "other cheese" );

        final ReteTuple tuple1 = new ReteTuple( f1 );
        this.node.assertTuple( tuple1,
                               this.context,
                               this.workingMemory );
        assertEquals( 2,
                      this.memory.getTupleMemory().size() );
        Assert.assertTrue( "An empty matching objects list should be propagated",
                           this.accumulator.getMatchingObjects().isEmpty() );

        final TupleMemory memory = this.memory.getTupleMemory();
        assertTrue( memory.contains( tuple0 ) );
        assertTrue( memory.contains( tuple1 ) );

        Assert.assertEquals( "Two tuples should have been propagated",
                             2,
                             this.sink.getAsserted().size() );
    }

    /**
     * Test method for {@link org.drools.reteoo.AccumulateNode#assertTuple(org.drools.reteoo.ReteTuple, org.drools.spi.PropagationContext, org.drools.reteoo.ReteooWorkingMemory)}.
     */
    public void testAssertTupleWithObjects() {
        final DefaultFactHandle f0 = (DefaultFactHandle) this.workingMemory.getFactHandleFactory().newFactHandle( "cheese" );
        final DefaultFactHandle f1 = (DefaultFactHandle) this.workingMemory.getFactHandleFactory().newFactHandle( "other cheese" );

        final ReteTuple tuple0 = new ReteTuple( f0 );

        this.node.assertObject( f0,
                                this.context,
                                this.workingMemory );
        this.node.assertObject( f1,
                                this.context,
                                this.workingMemory );

        // assert tuple, should add one to left memory
        this.node.assertTuple( tuple0,
                               this.context,
                               this.workingMemory );
        // check memories 
        assertEquals( 1,
                      this.memory.getTupleMemory().size() );
        assertEquals( 2,
                      this.memory.getFactHandleMemory().size() );
        Assert.assertEquals( "Wrong number of elements in matching objects list ",
                             2,
                             this.accumulator.getMatchingObjects().size() );

        // assert tuple, should add left memory 
        final ReteTuple tuple1 = new ReteTuple( f1 );
        this.node.assertTuple( tuple1,
                               this.context,
                               this.workingMemory );
        assertEquals( 2,
                      this.memory.getTupleMemory().size() );
        Assert.assertEquals( "Wrong number of elements in matching objects list ",
                             2,
                             this.accumulator.getMatchingObjects().size() );

        final TupleMemory memory = this.memory.getTupleMemory();
        assertTrue( memory.contains( tuple0 ) );
        assertTrue( memory.contains( tuple1 ) );

        Assert.assertEquals( "Two tuples should have been propagated",
                             2,
                             this.sink.getAsserted().size() );
    }

    /**
     * Test method for {@link org.drools.reteoo.AccumulateNode#retractTuple(org.drools.reteoo.ReteTuple, org.drools.spi.PropagationContext, org.drools.reteoo.ReteooWorkingMemory)}.
     */
    public void testRetractTuple() {
        final DefaultFactHandle f0 = (DefaultFactHandle) this.workingMemory.getFactHandleFactory().newFactHandle( "cheese" );

        final ReteTuple tuple0 = new ReteTuple( f0 );

        // assert tuple, should add one to left memory
        this.node.assertTuple( tuple0,
                               this.context,
                               this.workingMemory );
        // check memories 
        assertEquals( 1,
                      this.memory.getTupleMemory().size() );
        assertEquals( 0,
                      this.memory.getFactHandleMemory().size() );
        Assert.assertTrue( "An empty matching objects list should be propagated",
                           this.accumulator.getMatchingObjects().isEmpty() );

        this.node.retractTuple( tuple0,
                                this.context,
                                this.workingMemory );
        assertEquals( 0,
                      this.memory.getTupleMemory().size() );
        assertEquals( 1,
                      this.sink.getRetracted().size() );
        assertEquals( 1,
                      this.sink.getAsserted().size() );
    }

    /**
     * Test method for {@link org.drools.reteoo.AccumulateNode#assertObject(InternalFactHandle, org.drools.spi.PropagationContext, InternalWorkingMemory)}.
     */
    public void testAssertObject() {
        final DefaultFactHandle f0 = (DefaultFactHandle) this.workingMemory.getFactHandleFactory().newFactHandle( "cheese" );
        final DefaultFactHandle f1 = (DefaultFactHandle) this.workingMemory.getFactHandleFactory().newFactHandle( "other cheese" );

        final ReteTuple tuple0 = new ReteTuple( f0 );

        // assert tuple, should add one to left memory
        this.node.assertTuple( tuple0,
                               this.context,
                               this.workingMemory );

        // check memory 
        assertEquals( 1,
                      this.memory.getTupleMemory().size() );
        assertEquals( 1,
                      this.sink.getAsserted().size() );
        assertEquals( 0,
                      this.accumulator.getMatchingObjects().size() );

        this.node.assertObject( f0,
                                this.context,
                                this.workingMemory );
        assertEquals( 1,
                      this.memory.getFactHandleMemory().size() );
        assertEquals( 2,
                      this.sink.getAsserted().size() );
        assertEquals( 1,
                      this.accumulator.getMatchingObjects().size() );

        this.node.assertObject( f1,
                                this.context,
                                this.workingMemory );

        assertEquals( 2,
                      this.memory.getFactHandleMemory().size() );
        assertEquals( 3,
                      this.sink.getAsserted().size() );
        assertEquals( 2,
                      this.accumulator.getMatchingObjects().size() );

    }

    /**
     * Test method for {@link org.drools.reteoo.AccumulateNode#retractObject(InternalFactHandle, org.drools.spi.PropagationContext, InternalWorkingMemory)}.
     */
    public void testRetractObject() {
        final DefaultFactHandle f0 = (DefaultFactHandle) this.workingMemory.getFactHandleFactory().newFactHandle( "cheese" );
        final DefaultFactHandle f1 = (DefaultFactHandle) this.workingMemory.getFactHandleFactory().newFactHandle( "other cheese" );

        final ReteTuple tuple0 = new ReteTuple( f0 );

        this.node.assertObject( f0,
                                this.context,
                                this.workingMemory );
        this.node.assertObject( f1,
                                this.context,
                                this.workingMemory );
        assertEquals( 2,
                      this.memory.getFactHandleMemory().size() );

        // assert tuple, should add one to left memory
        this.node.assertTuple( tuple0,
                               this.context,
                               this.workingMemory );

        // check memory 
        assertEquals( 1,
                      this.memory.getTupleMemory().size() );
        assertEquals( 0,
                      this.sink.getRetracted().size() );
        assertEquals( 1,
                      this.sink.getAsserted().size() );
        assertEquals( 2,
                      this.accumulator.getMatchingObjects().size() );

        this.node.retractObject( f1,
                                 this.context,
                                 this.workingMemory );
        assertEquals( 1,
                      this.memory.getFactHandleMemory().size() );
        assertEquals( 1,
                      this.sink.getRetracted().size() );
        assertEquals( 2,
                      this.sink.getAsserted().size() );
        assertEquals( 1,
                      this.accumulator.getMatchingObjects().size() );

        this.node.retractObject( f0,
                                 this.context,
                                 this.workingMemory );
        assertEquals( 0,
                      this.memory.getFactHandleMemory().size() );
        assertEquals( 2,
                      this.sink.getRetracted().size() );
        assertEquals( 3,
                      this.sink.getAsserted().size() );
        assertEquals( 0,
                      this.accumulator.getMatchingObjects().size() );

    }

    public void testMemory() {
        ReteooRuleBase ruleBase = (ReteooRuleBase) RuleBaseFactory.newRuleBase();
        BuildContext buildContext = new BuildContext( ruleBase,
                                                      ruleBase.getReteooBuilder().getIdGenerator() );
        
        this.workingMemory = (ReteooWorkingMemory) ruleBase.newStatefulSession();

        final MockObjectSource objectSource = new MockObjectSource( 1 );
        final MockTupleSource tupleSource = new MockTupleSource( 1 );

        final AccumulateNode accumulateNode = new AccumulateNode( 2,
                                                                  tupleSource,
                                                                  objectSource,
                                                                  new AlphaNodeFieldConstraint[0],
                                                                  EmptyBetaConstraints.getInstance(),
                                                                  EmptyBetaConstraints.getInstance(),
                                                                  this.accumulate,
                                                                  false,
                                                                  buildContext  );

        final BetaMemory memory = ((AccumulateMemory) this.workingMemory.getNodeMemory( accumulateNode )).betaMemory;

        assertNotNull( memory );
    }

    /**
     * Test just tuple assertions
     * 
     * @throws AssertionException
     */
    public void testAssertTupleSequentialMode() throws Exception {
        RuleBaseConfiguration conf = new RuleBaseConfiguration();
        conf.setSequential( true );

        ReteooRuleBase ruleBase = (ReteooRuleBase) RuleBaseFactory.newRuleBase();
        BuildContext buildContext = new BuildContext( ruleBase,
                                                      ruleBase.getReteooBuilder().getIdGenerator() );
        buildContext.setTupleMemoryEnabled( false );
        // overide the original node, so we an set the BuildContext
        this.node = new AccumulateNode( 15,
                                        this.tupleSource,
                                        this.objectSource,
                                        new AlphaNodeFieldConstraint[0],
                                        EmptyBetaConstraints.getInstance(),
                                        EmptyBetaConstraints.getInstance(),
                                        this.accumulate,
                                        false,
                                        buildContext );

        this.node.addTupleSink( this.sink );        
        
        this.workingMemory = new ReteooWorkingMemory( 1,
                                                      (ReteooRuleBase) RuleBaseFactory.newRuleBase( conf ) );
        
        this.memory = ((AccumulateMemory) this.workingMemory.getNodeMemory( this.node )).betaMemory;

        final DefaultFactHandle f0 = (DefaultFactHandle) this.workingMemory.getFactHandleFactory().newFactHandle( "cheese" );
        final DefaultFactHandle f1 = (DefaultFactHandle) this.workingMemory.getFactHandleFactory().newFactHandle( "other cheese" );

        final ReteTuple tuple0 = new ReteTuple( f0 );

        this.node.assertObject( f0,
                                this.context,
                                this.workingMemory );
        this.node.assertObject( f1,
                                this.context,
                                this.workingMemory );

        // assert tuple, should not add to left memory, since we are in sequential mode
        this.node.assertTuple( tuple0,
                               this.context,
                               this.workingMemory );
        // check memories 
        assertNull( this.memory.getTupleMemory() );
        assertEquals( 2,
                      this.memory.getFactHandleMemory().size() );
        Assert.assertEquals( "Wrong number of elements in matching objects list ",
                             2,
                             this.accumulator.getMatchingObjects().size() );

        // assert tuple, should not add left memory 
        final ReteTuple tuple1 = new ReteTuple( f1 );
        this.node.assertTuple( tuple1,
                               this.context,
                               this.workingMemory );
        assertNull( this.memory.getTupleMemory() );
        Assert.assertEquals( "Wrong number of elements in matching objects list ",
                             2,
                             this.accumulator.getMatchingObjects().size() );

        Assert.assertEquals( "Two tuples should have been propagated",
                             2,
                             this.sink.getAsserted().size() );
    }

}
