package org.drools.reteoo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.drools.reteoo.TupleIterator.OnLeaf;

import junit.framework.TestCase;


public class TupleIterationTest extends TestCase {
    public void testRootTraversal() {        
        LeftTuple t0 = new LeftTuple();
        LeftTuple t1 = new LeftTuple(t0, null, true);
        LeftTuple t2 = new LeftTuple(t0, null, true);
        LeftTuple t3 = new LeftTuple(t0, null, true);
        
        LeftTuple t1_1 = new LeftTuple(t1, null, true);
        LeftTuple t1_2 = new LeftTuple(t1, null, true);
        
        LeftTuple t1_1_1 = new LeftTuple(t1_1, null, true);
        LeftTuple t1_1_1_1 = new LeftTuple(t1_1_1, null, true);
        LeftTuple t1_1_1_2 = new LeftTuple(t1_1_1, null, true);
        LeftTuple t1_1_1_3 = new LeftTuple(t1_1_1, null, true);
        
        LeftTuple t1_2_1 = new LeftTuple(t1_2, null, true);
        
        LeftTuple t1_2_2 = new LeftTuple(t1_2, null, true);
        LeftTuple t1_2_2_1 = new LeftTuple(t1_2_2, null, true);

        LeftTuple t1_2_3 = new LeftTuple(t1_2, null, true);        

        LeftTuple t2_1 = new LeftTuple(t2, null, true);
        LeftTuple t2_2 = new LeftTuple(t2, null, true);
        LeftTuple t2_3 = new LeftTuple(t2, null, true);
        
        LeftTuple t2_3_1 = new LeftTuple(t2_3, null, true);
        LeftTuple t2_3_1_1 = new LeftTuple(t2_3_1, null, true);
        
        LeftTuple t2_3_2 = new LeftTuple(t2_3, null, true);

        
        LeftTuple[] leafs = new LeftTuple[] {
t1_1_1_1,  t1_1_1_2, t1_1_1_3, t1_2_1, t1_2_2_1, t1_2_3, t2_1, t2_2, t2_3_1_1, t2_3_2, t3                  
        };

        final List<LeftTuple> foundLeafs = new ArrayList<LeftTuple>();
        
        TupleIterator iterator = new TupleIterator();
        OnLeaf onLeaf = new OnLeaf() {

            public void execute(LeftTuple leafLeftTuple) {
                foundLeafs.add( leafLeftTuple );
            }
            
        };
        
        iterator.traverse( t0, t0, onLeaf );
        
        assertEquals( leafs.length, foundLeafs.size() );
        assertEquals( Arrays.asList( leafs ), foundLeafs );        
    }
    
    public void testMidTraversal() {        
        LeftTuple tm2 = new LeftTuple();
        LeftTuple tm1 = new LeftTuple(tm2, null, true);
        LeftTuple tm1_1 = new LeftTuple(tm1, null, true); // this leaf will not be included
        
        LeftTuple t0 = new LeftTuple(tm1, null, true); // insert two nodes before our root traversal position
        
        
        LeftTuple t1 = new LeftTuple(t0, null, true);
        LeftTuple t2 = new LeftTuple(t0, null, true);
        LeftTuple t3 = new LeftTuple(t0, null, true);
        
        LeftTuple t1_1 = new LeftTuple(t1, null, true);
        LeftTuple t1_2 = new LeftTuple(t1, null, true);
        
        LeftTuple t1_1_1 = new LeftTuple(t1_1, null, true);
        LeftTuple t1_1_1_1 = new LeftTuple(t1_1_1, null, true);
        LeftTuple t1_1_1_2 = new LeftTuple(t1_1_1, null, true);
        LeftTuple t1_1_1_3 = new LeftTuple(t1_1_1, null, true);
        
        LeftTuple t1_2_1 = new LeftTuple(t1_2, null, true);
        
        LeftTuple t1_2_2 = new LeftTuple(t1_2, null, true);
        LeftTuple t1_2_2_1 = new LeftTuple(t1_2_2, null, true);

        LeftTuple t1_2_3 = new LeftTuple(t1_2, null, true);        

        LeftTuple t2_1 = new LeftTuple(t2, null, true);
        LeftTuple t2_2 = new LeftTuple(t2, null, true);
        LeftTuple t2_3 = new LeftTuple(t2, null, true);
        
        LeftTuple t2_3_1 = new LeftTuple(t2_3, null, true);
        LeftTuple t2_3_1_1 = new LeftTuple(t2_3_1, null, true);
        
        LeftTuple t2_3_2 = new LeftTuple(t2_3, null, true);

        
        LeftTuple[] leafs = new LeftTuple[] {
t1_1_1_1,  t1_1_1_2, t1_1_1_3, t1_2_1, t1_2_2_1, t1_2_3, t2_1, t2_2, t2_3_1_1, t2_3_2, t3                  
        };

        final List<LeftTuple> foundLeafs = new ArrayList<LeftTuple>();
        
        TupleIterator iterator = new TupleIterator();
        OnLeaf onLeaf = new OnLeaf() {

            public void execute(LeftTuple leafLeftTuple) {
                foundLeafs.add( leafLeftTuple );
            }
            
        };
        
        iterator.traverse( t0, t0, onLeaf );
        
        assertEquals( leafs.length, foundLeafs.size() );
        assertEquals( Arrays.asList( leafs ), foundLeafs );        
    }    
}
