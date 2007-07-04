package org.drools.reteoo;

import java.lang.reflect.Field;

import org.drools.common.InternalWorkingMemory;
import org.drools.reteoo.RuleTerminalNode.TerminalNodeMemory;
import org.drools.util.AbstractHashTable;
import org.drools.util.Entry;
import org.drools.util.FactHashTable;
import org.drools.util.FactHandleIndexHashTable;
import org.drools.util.Iterator;
import org.drools.util.ObjectHashMap;
import org.drools.util.ReflectiveVisitor;
import org.drools.util.FactHandleIndexHashTable.FieldIndexEntry;
import org.drools.util.ObjectHashMap.ObjectEntry;

public class MemoryVisitor extends ReflectiveVisitor {
    private InternalWorkingMemory workingMemory;
    private int                   indent = 0;

    /**
     * Constructor.
     */
    public MemoryVisitor(final InternalWorkingMemory workingMemory) {
        this.workingMemory = workingMemory;
    }

    /**
     * RuleBaseImpl visits its Rete.
     */
    public void visitReteooRuleBase(final ReteooRuleBase ruleBase) {
        visit( (ruleBase).getRete() );
    }

    /**
     * Rete visits each of its ObjectTypeNodes.
     */
    public void visitRete(final Rete rete) {
        final ObjectHashMap map = rete.getObjectTypeNodes();

        final Iterator it = map.newIterator();
        for ( ObjectEntry entry = (ObjectEntry) it.next(); entry != null; entry = (ObjectEntry) it.next() ) {
            visit( entry.getValue() );
        }
    }

    public void visitObjectTypeNode(final ObjectTypeNode node) {
        System.out.println( indent() + node );

        final FactHashTable memory = (FactHashTable) this.workingMemory.getNodeMemory( node );
        checkObjectHashTable( memory );

        this.indent++;
        try {
            final Field field = ObjectSource.class.getDeclaredField( "sink" );
            field.setAccessible( true );
            final ObjectSinkPropagator sink = (ObjectSinkPropagator) field.get( node );
            final ObjectSink[] sinks = sink.getSinks();
            for ( int i = 0, length = sinks.length; i < length; i++ ) {
                visit( sinks[i] );
            }
        } catch ( final Exception e ) {
            e.printStackTrace();
        }
        this.indent--;
    }

    public void visitAlphaNode(final AlphaNode node) {
        System.out.println( indent() + node );

        final FactHashTable memory = (FactHashTable) this.workingMemory.getNodeMemory( node );
        checkObjectHashTable( memory );

        this.indent++;
        try {
            final Field field = ObjectSource.class.getDeclaredField( "sink" );
            field.setAccessible( true );
            final ObjectSinkPropagator sink = (ObjectSinkPropagator) field.get( node );
            final ObjectSink[] sinks = sink.getSinks();
            for ( int i = 0, length = sinks.length; i < length; i++ ) {
                visit( sinks[i] );
            }
        } catch ( final Exception e ) {
            e.printStackTrace();
        }
        this.indent--;
    }

    public void visitLeftInputAdapterNode(final LeftInputAdapterNode node) {
        System.out.println( indent() + node );

        this.indent++;
        try {
            final Field field = TupleSource.class.getDeclaredField( "sink" );
            field.setAccessible( true );
            final TupleSinkPropagator sink = (TupleSinkPropagator) field.get( node );
            final TupleSink[] sinks = sink.getSinks();
            for ( int i = 0, length = sinks.length; i < length; i++ ) {
                visit( sinks[i] );
            }
        } catch ( final Exception e ) {
            e.printStackTrace();
        }
        this.indent--;
    }

    public void visitJoinNode(final JoinNode node) {
        System.out.println( indent() + node );

        try {
            final BetaMemory memory = (BetaMemory) this.workingMemory.getNodeMemory( node );
            checkObjectHashTable( memory.getFactHandleMemory() );
            checkTupleMemory( memory.getTupleMemory() );
        } catch ( final Exception e ) {
            e.printStackTrace();
        }

        this.indent++;
        try {
            final Field field = TupleSource.class.getDeclaredField( "sink" );
            field.setAccessible( true );
            final TupleSinkPropagator sink = (TupleSinkPropagator) field.get( node );
            final TupleSink[] sinks = sink.getSinks();
            for ( int i = 0, length = sinks.length; i < length; i++ ) {
                visit( sinks[i] );
            }
        } catch ( final Exception e ) {
            e.printStackTrace();
        }
        this.indent--;
    }

    public void visitNotNode(final NotNode node) {
        System.out.println( indent() + node );
        try {
            final BetaMemory memory = (BetaMemory) this.workingMemory.getNodeMemory( node );
            checkObjectHashTable( memory.getFactHandleMemory() );
            checkTupleMemory( memory.getTupleMemory() );
        } catch ( final Exception e ) {
            e.printStackTrace();
        }

        this.indent++;
        try {
            final Field field = TupleSource.class.getDeclaredField( "sink" );
            field.setAccessible( true );
            final TupleSinkPropagator sink = (TupleSinkPropagator) field.get( node );
            final TupleSink[] sinks = sink.getSinks();
            for ( int i = 0, length = sinks.length; i < length; i++ ) {
                visit( sinks[i] );
            }
        } catch ( final Exception e ) {
            e.printStackTrace();
        }
        this.indent--;
    }

    public void visitTerminalNode(final RuleTerminalNode node) {
        System.out.println( indent() + node );
        final TerminalNodeMemory memory = (TerminalNodeMemory) this.workingMemory.getNodeMemory( node );
        checkTupleMemory( memory.getTupleMemory() );
    }

    //    private void checkObjectHashMap(final ObjectHashMap map) {
    //        final Entry[] entries = map.getTable();
    //        int count = 0;
    //        for ( int i = 0, length = entries.length; i < length; i++ ) {
    //            if ( entries[i] != null ) {
    //                count++;
    //            }
    //        }
    //
    //        System.out.println( "ObjectHashMap: " + indent() + map.size() + ":" + count );
    //        if ( map.size() != count ) {
    //            System.out.println( indent() + "error" );
    //        }
    //    }

    private void checkObjectHashTable(final FactHandleMemory memory) {
        if ( memory instanceof FactHashTable ) {
            checkFactHashTable( (FactHashTable) memory );
        } else if ( memory instanceof FactHandleIndexHashTable ) {
            checkFieldIndexHashTable( (FactHandleIndexHashTable) memory );
        } else {
            throw new RuntimeException( memory.getClass() + " should not be here" );
        }
    }

    private void checkFactHashTable(final FactHashTable memory) {
        final Entry[] entries = memory.getTable();
        int count = 0;
        for ( int i = 0, length = entries.length; i < length; i++ ) {
            if ( entries[i] != null ) {
                Entry entry = entries[i];
                while ( entry != null ) {
                    count++;
                    entry = entry.getNext();
                }
            }
        }

        System.out.println( indent() + "FactHashTable: " + memory.size() + ":" + count );
        if ( memory.size() != count ) {
            System.out.println( indent() + "error" );
        }
    }

    private void checkFieldIndexHashTable(final FactHandleIndexHashTable memory) {
        final Entry[] entries = memory.getTable();
        int factCount = 0;
        int bucketCount = 0;
        for ( int i = 0, length = entries.length; i < length; i++ ) {
            if ( entries[i] != null ) {
                FieldIndexEntry fieldIndexEntry = (FieldIndexEntry) entries[i];
                while ( fieldIndexEntry != null ) {
                    if ( fieldIndexEntry.getFirst() != null ) {
                        Entry entry = fieldIndexEntry.getFirst();
                        while ( entry != null ) {
                            entry = entry.getNext();
                            factCount++;
                        }
                    } else {
                        System.out.println( "error : fieldIndexHashTable cannot have empty FieldIndexEntry objects" );
                    }
                    fieldIndexEntry = (FieldIndexEntry) fieldIndexEntry.getNext();
                    bucketCount++;
                }
            }
        }

        try {
            final Field field = AbstractHashTable.class.getDeclaredField( "size" );
            field.setAccessible( true );
            System.out.println( indent() + "FieldIndexBuckets: " + ((Integer) field.get( memory )).intValue() + ":" + bucketCount );
            if ( ((Integer) field.get( memory )).intValue() != bucketCount ) {
                System.out.println( indent() + "error" );
            }
        } catch ( final Exception e ) {
            e.printStackTrace();
        }

        System.out.println( indent() + "FieldIndexFacts: " + memory.size() + ":" + factCount );
        if ( memory.size() != factCount ) {
            System.out.println( indent() + "error" );
        }
    }

    private void checkTupleMemory(final TupleMemory memory) {
        final Entry[] entries = memory.getTable();
        int count = 0;
        for ( int i = 0, length = entries.length; i < length; i++ ) {
            if ( entries[i] != null ) {
                Entry entry = entries[i];
                while ( entry != null ) {
                    count++;
                    entry = entry.getNext();
                }
            }
        }

        System.out.println( indent() + "TupleMemory: " + memory.size() + ":" + count );
        if ( memory.size() != count ) {
            System.out.println( indent() + "error" );
        }
    }

    private String indent() {
        final StringBuffer buffer = new StringBuffer();
        for ( int i = 0; i < this.indent; i++ ) {
            buffer.append( "  " );
        }
        return buffer.toString();
    }
}
