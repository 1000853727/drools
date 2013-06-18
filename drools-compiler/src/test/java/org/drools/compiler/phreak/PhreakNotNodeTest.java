package org.drools.compiler.phreak;

import org.drools.core.RuleBaseConfiguration;
import org.drools.core.common.EmptyBetaConstraints;
import org.drools.core.common.InternalWorkingMemory;
import org.drools.core.phreak.PhreakNotNode;
import org.drools.core.reteoo.BetaMemory;
import org.drools.core.reteoo.JoinNode;
import org.drools.core.reteoo.LeftTupleSink;
import org.drools.core.reteoo.NodeTypeEnums;
import org.drools.core.reteoo.NotNode;
import org.drools.core.reteoo.ReteooRuleBase;
import org.drools.core.reteoo.SegmentMemory;
import org.drools.core.reteoo.builder.BuildContext;
import org.drools.core.rule.MVELDialectRuntimeData;
import org.drools.core.rule.Rule;
import org.junit.Test;

import java.beans.IntrospectionException;

import static org.drools.compiler.phreak.A.a;
import static org.drools.compiler.phreak.B.b;

public class PhreakNotNodeTest {

    BuildContext          buildContext;
    NotNode               notNode;
    JoinNode              sinkNode;
    InternalWorkingMemory wm;
    BetaMemory            bm;

    private void setupNotNode(String operator) {
        buildContext = createContext();

        notNode = (NotNode) BetaNodeBuilder.create( NodeTypeEnums.NotNode, buildContext )
                                             .setLeftType( A.class )
                                             .setBinding( "object", "$object" )
                                             .setRightType( B.class )
                                             .setConstraint( "object", operator, "$object" ).build();

        sinkNode = new JoinNode();
        sinkNode.setId( 1 );
        sinkNode.setConstraints( new EmptyBetaConstraints() );
        
        notNode.addTupleSink( sinkNode );

        wm = (InternalWorkingMemory) buildContext.getRuleBase().newStatefulSession( true );
        
        bm =(BetaMemory)  wm.getNodeMemory( notNode );
        
        BetaMemory bm1 =(BetaMemory)  wm.getNodeMemory( sinkNode );
        
        SegmentMemory smem = new SegmentMemory( notNode ) ;
        bm.setSegmentMemory( smem );
        
        SegmentMemory childSmem = new SegmentMemory( sinkNode ) ;
        bm1.setSegmentMemory( childSmem );       
        smem.add( childSmem );     

    }

    A a0 = a( 0 );
    A a1 = a( 1 );
    A a2 = a( 2 );
    A a3 = a( 3 );
    A a4 = a( 4 );

    B b0 = b( 0 );
    B b1 = b( 1 );
    B b2 = b( 2 );
    B b3 = b( 3 );
    B b4 = b( 4 );

    @Test
    public void test1() throws IntrospectionException {
        setupNotNode("!=");

        // @formatter:off
        test().left().insert( a0, a1, a2 )
        
              .result().insert( a2,
                                a1,
                                a0 )
                       .left(a2, a1, a0)
               .run().getActualResultLeftTuples().resetAll();
        
        test().left().delete( a2 )
              .right().insert( b1 )
                      
              .result().delete( a2, a0 )
                       .left( a1 )
                       .right( b1 )
         .run().getActualResultLeftTuples().resetAll();
        // @formatter:on
    }

    @Test
    public void test2() throws IntrospectionException {
        setupNotNode("<");

        // @formatter:off
        test().left().insert( a0, a1, a2 )

              .result().insert( a2, a1, a0 )
                       .left(a2, a1, a0)
              .run().getActualResultLeftTuples().resetAll();

        test().right().insert( b1 )

              .result().delete( a2 )
                       .left( a0, a1 )
                       .right( b1 )
              .run().getActualResultLeftTuples().resetAll();
        // @formatter:on
    }

    private Scenario test() {
        return test(notNode, sinkNode,
                    bm, wm);
    }

    private Scenario test(NotNode notNode,
                             LeftTupleSink sinkNode,
                             BetaMemory bm,
                             InternalWorkingMemory wm) {
        return new Scenario( PhreakNotNode.class, notNode, sinkNode, bm, wm ) ;
    }

    public BuildContext createContext() {
    
        RuleBaseConfiguration conf = new RuleBaseConfiguration();
    
        ReteooRuleBase rbase = new ReteooRuleBase( "ID",
                                                   conf );
        BuildContext buildContext = new BuildContext( rbase,
                                                      rbase.getReteooBuilder().getIdGenerator() );
    
        Rule rule = new Rule( "rule1", "org.pkg1", null );
        org.drools.core.rule.Package pkg = new org.drools.core.rule.Package( "org.pkg1" );
        pkg.getDialectRuntimeRegistry().setDialectData( "mvel", new MVELDialectRuntimeData() );
        pkg.addRule( rule );
        buildContext.setRule( rule );
    
        return buildContext;
    }


}
