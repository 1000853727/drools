package org.drools.util;

import junit.framework.TestCase;

import org.drools.Cheese;
import org.drools.base.ClassFieldAccessorCache;
import org.drools.base.ClassFieldAccessorStore;
import org.drools.base.ClassFieldReader;
import org.drools.base.ValueType;
import org.drools.base.evaluators.EqualityEvaluatorsDefinition;
import org.drools.base.evaluators.Operator;
import org.drools.common.DefaultFactHandle;
import org.drools.common.InternalFactHandle;
import org.drools.reteoo.RightTuple;
import org.drools.util.AbstractHashTable.FieldIndex;
import org.drools.util.AbstractHashTable.SingleIndex;

public class FieldIndexEntryTest extends TestCase {
    EqualityEvaluatorsDefinition equals = new EqualityEvaluatorsDefinition();

    ClassFieldAccessorStore store = new ClassFieldAccessorStore();

    protected void setUp() throws Exception {
        store.setClassFieldAccessorCache( new ClassFieldAccessorCache( Thread.currentThread().getContextClassLoader() ) );
        store.setEagerWire( true );
    }

    public void testSingleEntry() {
        final ClassFieldReader extractor = store.getReader( Cheese.class,
                                                                  "type",
                                                                  getClass().getClassLoader() );

        final FieldIndex fieldIndex = new FieldIndex( extractor,
                                                      null,
                                                      equals.getEvaluator( ValueType.STRING_TYPE, Operator.EQUAL ) );
        final SingleIndex singleIndex = new SingleIndex( new FieldIndex[]{fieldIndex},
                                                         1 );

        final RightTupleList index = new RightTupleList( singleIndex,
                                                           "stilton".hashCode() );

        // Test initial construction
        assertNull( index.first );
        assertEquals( "stilton".hashCode(),
                      index.hashCode() );

        final Cheese stilton1 = new Cheese( "stilton",
                                            35 );
        final InternalFactHandle h1 = new DefaultFactHandle( 1,
                                                             stilton1 );

        // test add
        RightTuple h1RightTuple = new RightTuple( h1, null );
        index.add( h1RightTuple );

        final RightTuple entry1 = index.first;
        assertSame( h1,
                    entry1.getFactHandle() );
        assertNull( entry1.getNext() );
        assertSame( entry1,
                    index.get( h1 ) );

        // test get
        final RightTuple entry2 = index.get( new RightTuple( h1, null ) );
        assertSame( entry1,
                    entry2 );

        // test remove
        index.remove( h1RightTuple );
        assertNull( index.first );
    }

    public void testTwoEntries() {
        final ClassFieldReader extractor = store.getReader( Cheese.class,
                                                                  "type",
                                                                  getClass().getClassLoader() );
        final FieldIndex fieldIndex = new FieldIndex( extractor,
                                                      null,
                                                      equals.getEvaluator( ValueType.STRING_TYPE, Operator.EQUAL ) );
        final SingleIndex singleIndex = new SingleIndex( new FieldIndex[]{fieldIndex},
                                                         1 );

        final RightTupleList index = new RightTupleList( singleIndex,
                                                           "stilton".hashCode() );

        final Cheese stilton1 = new Cheese( "stilton",
                                            35 );
        final InternalFactHandle h1 = new DefaultFactHandle( 1,
                                                             stilton1 );
        final Cheese stilton2 = new Cheese( "stilton",
                                            59 );
        final InternalFactHandle h2 = new DefaultFactHandle( 2,
                                                             stilton2 );
        
        RightTuple h1RightTuple = new RightTuple( h1, null );
        RightTuple h2RightTuple = new RightTuple( h2, null );

        // test add
        index.add( h1RightTuple );
        index.add( h2RightTuple );
        assertEquals( h2,
                      index.first.getFactHandle() );
        assertEquals( h1,
                      ((RightTuple) index.first.getNext()).getFactHandle() );

        // test get
        assertEquals( h1,
                      index.get( h1 ).getFactHandle() );
        assertEquals( h2,
                      index.get( h2 ).getFactHandle() );

        // test removal for combinations
        // remove first
        index.remove( h2RightTuple );
        assertEquals( h1RightTuple.getFactHandle(),
                      index.first.getFactHandle() );

        // remove second
        index.add( h2RightTuple );
        index.remove( h1RightTuple );
        assertEquals( h2RightTuple.getFactHandle(),
                      index.first.getFactHandle() );

        // check index type does not change, as this fact is removed
        stilton1.setType( "cheddar" );
    }

    public void testThreeEntries() {
        final ClassFieldReader extractor = store.getReader( Cheese.class,
                                                                  "type",
                                                                  getClass().getClassLoader() );
        final FieldIndex fieldIndex = new FieldIndex( extractor,
                                                      null,
                                                      equals.getEvaluator( ValueType.STRING_TYPE, Operator.EQUAL ) );
        final SingleIndex singleIndex = new SingleIndex( new FieldIndex[]{fieldIndex},
                                                         1 );

        final RightTupleList index = new RightTupleList( singleIndex,
                                                           "stilton".hashCode() );

        final Cheese stilton1 = new Cheese( "stilton",
                                            35 );
        final InternalFactHandle h1 = new DefaultFactHandle( 1,
                                                             stilton1 );
        final Cheese stilton2 = new Cheese( "stilton",
                                            59 );
        final InternalFactHandle h2 = new DefaultFactHandle( 2,
                                                             stilton2 );
        final Cheese stilton3 = new Cheese( "stilton",
                                            59 );
        final InternalFactHandle h3 = new DefaultFactHandle( 3,
                                                             stilton3 );

        RightTuple h1RightTuple = new RightTuple( h1, null );
        RightTuple h2RightTuple = new RightTuple( h2, null );
        RightTuple h3RightTuple = new RightTuple( h3, null );
        
        // test add
        index.add( h1RightTuple );
        index.add( h2RightTuple );
        index.add( h3RightTuple );
        assertEquals( h3,
                      index.first.getFactHandle() );
        assertEquals( h2,
                      ((RightTuple) index.first.getNext()).getFactHandle() );
        assertEquals( h1,
                      ((RightTuple) index.first.getNext().getNext()).getFactHandle() );

        // test get
        assertEquals( h1,
                      index.get( h1 ).getFactHandle() );
        assertEquals( h2,
                      index.get( h2 ).getFactHandle() );
        assertEquals( h3,
                      index.get( h3 ).getFactHandle() );

        // test removal for combinations
        //remove first
        index.remove( h3RightTuple );
        assertEquals( h2,
                      index.first.getFactHandle() );
        assertEquals( h1,
                      ((RightTuple) index.first.getNext()).getFactHandle() );

        index.add( h3RightTuple );
        index.remove( h2RightTuple );
        assertEquals( h3,
                      index.first.getFactHandle() );
        assertEquals( h1,
                      ((RightTuple) index.first.getNext()).getFactHandle() );

        index.add( h2RightTuple );
        index.remove( h1RightTuple );
        assertEquals( h2,
                      index.first.getFactHandle() );
        assertEquals( h3,
                      ((RightTuple) index.first.getNext()).getFactHandle() );

        index.remove( index.first );
        // check index type does not change, as this fact is removed
        stilton2.setType( "cheddar" );
    }
}
