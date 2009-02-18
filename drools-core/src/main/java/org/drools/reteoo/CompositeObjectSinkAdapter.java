package org.drools.reteoo;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

import org.drools.base.ValueType;
import org.drools.base.evaluators.Operator;
import org.drools.common.BaseNode;
import org.drools.common.InternalFactHandle;
import org.drools.common.InternalWorkingMemory;
import org.drools.common.RuleBasePartitionId;
import org.drools.rule.LiteralConstraint;
import org.drools.runtime.rule.Evaluator;
import org.drools.spi.AlphaNodeFieldConstraint;
import org.drools.spi.FieldValue;
import org.drools.spi.InternalReadAccessor;
import org.drools.spi.PropagationContext;
import org.drools.spi.ReadAccessor;
import org.drools.util.Iterator;
import org.drools.util.LinkedList;
import org.drools.util.LinkedListNode;
import org.drools.util.ObjectHashMap;
import org.drools.util.ObjectHashMap.ObjectEntry;

public class CompositeObjectSinkAdapter extends AbstractObjectSinkAdapter {

    //    /** You can override this property via a system property (eg -Ddrools.hashThreshold=4) */
    //    public static final String HASH_THRESHOLD_SYSTEM_PROPERTY = "drools.hashThreshold";
    //
    //    /** The threshold for when hashing kicks in */
    //    public static final int    THRESHOLD_TO_HASH              = Integer.parseInt( System.getProperty( HASH_THRESHOLD_SYSTEM_PROPERTY,
    //                                                                                                      "3" ) );

    private static final long serialVersionUID = 400L;
    ObjectSinkNodeList        otherSinks;
    ObjectSinkNodeList        hashableSinks;

    LinkedList                hashedFieldIndexes;

    ObjectHashMap             hashedSinkMap;

    private int               alphaNodeHashingThreshold;

    public CompositeObjectSinkAdapter() {
        this( null, 3 );
    }

    public CompositeObjectSinkAdapter(final RuleBasePartitionId partitionId, final int alphaNodeHashingThreshold) {
        super( partitionId );
        this.alphaNodeHashingThreshold = alphaNodeHashingThreshold;
    }

    public void readExternal(ObjectInput in) throws IOException,
                                            ClassNotFoundException {
        super.readExternal( in );
        otherSinks = (ObjectSinkNodeList) in.readObject();
        hashableSinks = (ObjectSinkNodeList) in.readObject();
        hashedFieldIndexes = (LinkedList) in.readObject();
        hashedSinkMap = (ObjectHashMap) in.readObject();
        alphaNodeHashingThreshold = in.readInt();
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal( out );
        out.writeObject( otherSinks );
        out.writeObject( hashableSinks );
        out.writeObject( hashedFieldIndexes );
        out.writeObject( hashedSinkMap );
        out.writeInt( alphaNodeHashingThreshold );
    }

    public ObjectSinkNodeList getOthers() {
        return this.otherSinks;        
    }
    
    public ObjectSinkNodeList getHashableSinks() {
        return this.hashableSinks;
    }
    
    public ObjectHashMap getHashedSinkMap() {
        return this.hashedSinkMap;
    }    

    public void addObjectSink(final ObjectSink sink) {
        if ( sink instanceof AlphaNode ) {
            final AlphaNode alphaNode = (AlphaNode) sink;
            final AlphaNodeFieldConstraint fieldConstraint = alphaNode.getConstraint();

            if ( fieldConstraint instanceof LiteralConstraint ) {
                final LiteralConstraint literalConstraint = (LiteralConstraint) fieldConstraint;
                final Evaluator evaluator = literalConstraint.getEvaluator();

                if ( evaluator.getOperator() == Operator.EQUAL && literalConstraint.getFieldExtractor().getValueType() != ValueType.OBJECT_TYPE ) {
                    final int index = literalConstraint.getFieldExtractor().getIndex();
                    final FieldIndex fieldIndex = registerFieldIndex( index,
                                                                      literalConstraint.getFieldExtractor() );

                    if ( fieldIndex.getCount() >= this.alphaNodeHashingThreshold && this.alphaNodeHashingThreshold != 0 ) {
                        if ( !fieldIndex.isHashed() ) {
                            hashSinks( fieldIndex );
                        }
                        final FieldValue value = literalConstraint.getField();
                        // no need to check, we know  the sink  does not exist
                        this.hashedSinkMap.put( new HashKey( index,
                                                             value,
                                                             fieldIndex.getFieldExtractor() ),
                                                sink,
                                                false );
                    } else {
                        if ( this.hashableSinks == null ) {
                            this.hashableSinks = new ObjectSinkNodeList();
                        }
                        this.hashableSinks.add( (ObjectSinkNode) sink );
                    }
                    return;
                }

            }
        }

        if ( this.otherSinks == null ) {
            this.otherSinks = new ObjectSinkNodeList();
        }

        this.otherSinks.add( (ObjectSinkNode) sink );
    }

    public void removeObjectSink(final ObjectSink sink) {
        if ( sink instanceof AlphaNode ) {
            final AlphaNode alphaNode = (AlphaNode) sink;
            final AlphaNodeFieldConstraint fieldConstraint = alphaNode.getConstraint();

            if ( fieldConstraint instanceof LiteralConstraint ) {
                final LiteralConstraint literalConstraint = (LiteralConstraint) fieldConstraint;
                final Evaluator evaluator = literalConstraint.getEvaluator();
                final FieldValue value = literalConstraint.getField();

                if ( evaluator.getOperator() == Operator.EQUAL && literalConstraint.getFieldExtractor().getValueType() != ValueType.OBJECT_TYPE ) {
                    final int index = literalConstraint.getFieldExtractor().getIndex();
                    final FieldIndex fieldIndex = unregisterFieldIndex( index );

                    if ( fieldIndex.isHashed() ) {
                        HashKey hashKey = new HashKey( index,
                                                       value,
                                                       fieldIndex.getFieldExtractor() );
                        this.hashedSinkMap.remove( hashKey );
                        if ( fieldIndex.getCount() <= this.alphaNodeHashingThreshold - 1 ) {
                            // we have less than three so unhash
                            unHashSinks( fieldIndex );
                        }
                    } else {
                        this.hashableSinks.remove( (ObjectSinkNode) sink );
                    }

                    if ( this.hashableSinks != null && this.hashableSinks.isEmpty() ) {
                        this.hashableSinks = null;
                    }

                    return;
                }
            }
        }

        this.otherSinks.remove( (ObjectSinkNode) sink );

        if ( this.otherSinks.isEmpty() ) {
            this.otherSinks = null;
        }
    }

    public void hashSinks(final FieldIndex fieldIndex) {
        final int index = fieldIndex.getIndex();

        final List list = new ArrayList();

        if ( this.hashedSinkMap == null ) {
            this.hashedSinkMap = new ObjectHashMap();
        }

        for ( ObjectSinkNode sink = this.hashableSinks.getFirst(); sink != null; sink = sink.getNextObjectSinkNode() ) {
            final AlphaNode alphaNode = (AlphaNode) sink;
            final AlphaNodeFieldConstraint fieldConstraint = alphaNode.getConstraint();
            final LiteralConstraint literalConstraint = (LiteralConstraint) fieldConstraint;
            final Evaluator evaluator = literalConstraint.getEvaluator();
            if ( evaluator.getOperator() == Operator.EQUAL && index == literalConstraint.getFieldExtractor().getIndex() ) {
                final FieldValue value = literalConstraint.getField();
                list.add( sink );
                this.hashedSinkMap.put( new HashKey( index,
                                                     value,
                                                     fieldIndex.getFieldExtractor() ),
                                        sink );
            }
        }

        for ( final java.util.Iterator it = list.iterator(); it.hasNext(); ) {
            final ObjectSinkNode sink = (ObjectSinkNode) it.next();
            this.hashableSinks.remove( sink );
        }

        if ( this.hashableSinks.isEmpty() ) {
            this.hashableSinks = null;
        }

        fieldIndex.setHashed( true );
    }

    public void unHashSinks(final FieldIndex fieldIndex) {
        final int index = fieldIndex.getIndex();

        final List sinks = new ArrayList();

        //iterate twice as custom iterator is immutable
        final Iterator mapIt = this.hashedSinkMap.newIterator();
        for ( ObjectHashMap.ObjectEntry e = (ObjectHashMap.ObjectEntry) mapIt.next(); e != null; ) {

            sinks.add( e.getValue() );
            e = (ObjectHashMap.ObjectEntry) mapIt.next();
        }

        for ( final java.util.Iterator iter = sinks.iterator(); iter.hasNext(); ) {
            final AlphaNode sink = (AlphaNode) iter.next();
            final AlphaNode alphaNode = sink;
            final AlphaNodeFieldConstraint fieldConstraint = alphaNode.getConstraint();
            final LiteralConstraint literalConstraint = (LiteralConstraint) fieldConstraint;
            final Evaluator evaluator = literalConstraint.getEvaluator();
            if ( evaluator.getOperator() == Operator.EQUAL && index == literalConstraint.getFieldExtractor().getIndex() ) {
                final FieldValue value = literalConstraint.getField();
                if ( this.hashableSinks == null ) {
                    this.hashableSinks = new ObjectSinkNodeList();
                }
                this.hashableSinks.add( sink );
                this.hashedSinkMap.remove( new HashKey( index,
                                                        value,
                                                        fieldIndex.getFieldExtractor() ) );
            };
        }

        if ( this.hashedSinkMap.isEmpty() ) {
            this.hashedSinkMap = null;
        }

        fieldIndex.setHashed( false );
    }

    /**
     * Returns a FieldIndex which Keeps a count on how many times a particular field is used with an equality check in the sinks.
     * @param index
     * @param fieldExtractor
     * @return
     */
    private FieldIndex registerFieldIndex(final int index,
                                          final InternalReadAccessor fieldExtractor) {
        FieldIndex fieldIndex = null;

        // is linkedlist null, if so create and add
        if ( this.hashedFieldIndexes == null ) {
            this.hashedFieldIndexes = new LinkedList();
            fieldIndex = new FieldIndex( index,
                                         fieldExtractor );
            this.hashedFieldIndexes.add( fieldIndex );
        }

        // still null, so see if it already exists
        if ( fieldIndex == null ) {
            fieldIndex = findFieldIndex( index );
        }

        // doesn't exist so create it
        if ( fieldIndex == null ) {
            fieldIndex = new FieldIndex( index,
                                         fieldExtractor );
            this.hashedFieldIndexes.add( fieldIndex );
        }

        fieldIndex.increaseCounter();

        return fieldIndex;
    }

    private FieldIndex unregisterFieldIndex(final int index) {
        final FieldIndex fieldIndex = findFieldIndex( index );
        fieldIndex.decreaseCounter();

        // if the fieldcount is 0 then remove it from the linkedlist
        if ( fieldIndex.getCount() == 0 ) {
            this.hashedFieldIndexes.remove( fieldIndex );

            // if the linkedlist is empty then null it
            if ( this.hashedFieldIndexes.isEmpty() ) {
                this.hashedFieldIndexes = null;
            }
        }

        return fieldIndex;
    }

    private FieldIndex findFieldIndex(final int index) {
        for ( FieldIndex node = (FieldIndex) this.hashedFieldIndexes.getFirst(); node != null; node = (FieldIndex) node.getNext() ) {
            if ( node.getIndex() == index ) {
                return node;
            }
        }

        return null;
    }

    public void propagateAssertObject(final InternalFactHandle factHandle,
                                      final PropagationContext context,
                                      final InternalWorkingMemory workingMemory) {
        final Object object = factHandle.getObject();

        // Iterates t he FieldIndex collection, which tells you if particularly field is hashed or not
        // if the field is hashed then it builds the hashkey to return the correct sink for the current objects slot's
        // value, one object may have multiple fields indexed.
        if ( this.hashedFieldIndexes != null ) {
            // Iterate the FieldIndexes to see if any are hashed
            for ( FieldIndex fieldIndex = (FieldIndex) this.hashedFieldIndexes.getFirst(); fieldIndex != null; fieldIndex = (FieldIndex) fieldIndex.getNext() ) {
                if ( !fieldIndex.isHashed() ) {
                    continue;
                }
                // this field is hashed so set the existing hashKey and see if there is a sink for it
                final int index = fieldIndex.getIndex();
                final ReadAccessor extractor = fieldIndex.getFieldExtactor();
                HashKey hashKey = new HashKey( index,
                                               object,
                                               fieldIndex.getFieldExtractor() );
                final ObjectSink sink = (ObjectSink) this.hashedSinkMap.get( hashKey );
                if ( sink != null ) {
                    // The sink exists so propagate
                    doPropagateAssertObject( factHandle, context, workingMemory, sink );
                }
            }
        }

        // propagate unhashed
        if ( this.hashableSinks != null ) {
            for ( ObjectSinkNode sink = this.hashableSinks.getFirst(); sink != null; sink = sink.getNextObjectSinkNode() ) {
                doPropagateAssertObject( factHandle, context, workingMemory, sink );
            }
        }

        if ( this.otherSinks != null ) {
            // propagate others
            for ( ObjectSinkNode sink = this.otherSinks.getFirst(); sink != null; sink = sink.getNextObjectSinkNode() ) {
                doPropagateAssertObject( factHandle, context, workingMemory, sink );
            }
        }

    }

    /**
     * This is a Hook method for subclasses to override. Please keep it protected unless you know
     * what you are doing.
     * 
     * @param factHandle
     * @param context
     * @param workingMemory
     * @param sink
     */
    protected void doPropagateAssertObject( InternalFactHandle factHandle, PropagationContext context,
                                            InternalWorkingMemory workingMemory, ObjectSink sink ) {
        sink.assertObject( factHandle,
                           context,
                           workingMemory );
    }

    public BaseNode getMatchingNode(BaseNode candidate) {
        if ( this.otherSinks != null ) {
            for ( ObjectSinkNode sink = this.otherSinks.getFirst(); sink != null; sink = sink.getNextObjectSinkNode() ) {
                if (candidate.equals( sink )) {
                    return (BaseNode)sink;
                }
            }
        }

        if ( this.hashableSinks != null ) {
            for ( ObjectSinkNode sink = this.hashableSinks.getFirst(); sink != null; sink = sink.getNextObjectSinkNode() ) {
                if (candidate.equals( sink )) {
                    return (BaseNode)sink;
                }
            }
        }

        if ( this.hashedSinkMap != null ) {
            final Iterator it = this.hashedSinkMap.newIterator();
            for ( ObjectEntry entry = (ObjectEntry) it.next(); entry != null; entry = (ObjectEntry) it.next() ) {
                final ObjectSink sink = (ObjectSink) entry.getValue();
                if (candidate.equals( sink )) {
                    return (BaseNode)sink;
                }
            }
        }
        return null;
    }

    public ObjectSink[] getSinks() {
        ObjectSink[]    sinks   = new ObjectSink[size()];
        int             at      = 0;

        if ( this.otherSinks != null ) {
            for ( ObjectSinkNode sink = this.otherSinks.getFirst(); sink != null; sink = sink.getNextObjectSinkNode() ) {
                sinks[at++] = sink;
            }
        }

        if ( this.hashableSinks != null ) {
            for ( ObjectSinkNode sink = this.hashableSinks.getFirst(); sink != null; sink = sink.getNextObjectSinkNode() ) {
                sinks[at++] = sink;
            }
        }

        if ( this.hashedSinkMap != null ) {
            final Iterator it = this.hashedSinkMap.newIterator();
            for ( ObjectEntry entry = (ObjectEntry) it.next(); entry != null; entry = (ObjectEntry) it.next() ) {
                sinks[at++] = (ObjectSink) entry.getValue();
            }
        }
        return sinks;
    }

    public int size() {
        return (this.otherSinks != null ? this.otherSinks.size() : 0) +
               (this.hashableSinks != null ? this.hashableSinks.size() : 0)+
               (this.hashedSinkMap != null ? this.hashedSinkMap.size() : 0);
    }

    public static class HashKey
        implements
        Externalizable {
        private static final long serialVersionUID = 400L;

        private static final byte OBJECT           = 1;
        private static final byte LONG             = 2;
        private static final byte DOUBLE           = 3;
        private static final byte BOOL             = 4;

        private int               index;

        private byte              type;
        private Object            ovalue;
        private long              lvalue;
        private boolean           bvalue;
        private double            dvalue;

        private boolean           isNull;

        private int               hashCode;

        public HashKey() {
        }

        public HashKey(final int index,
                       final FieldValue value,
                       final InternalReadAccessor extractor) {
            this.setValue( index,
                           extractor,
                           value );
        }

        public HashKey(final int index,
                       final Object value,
                       final InternalReadAccessor extractor) {
            this.setValue( index,
                           value,
                           extractor );
        }

        public void readExternal(ObjectInput in) throws IOException,
                                                ClassNotFoundException {
            index = in.readInt();
            type = in.readByte();
            ovalue = in.readObject();
            lvalue = in.readLong();
            bvalue = in.readBoolean();
            dvalue = in.readDouble();
            isNull = in.readBoolean();
            hashCode = in.readInt();
        }

        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeInt( index );
            out.writeByte( type );
            out.writeObject( ovalue );
            out.writeLong( lvalue );
            out.writeBoolean( bvalue );
            out.writeDouble( dvalue );
            out.writeBoolean( isNull );
            out.writeInt( hashCode );
        }

        public int getIndex() {
            return this.index;
        }

        public void setValue(final int index,
                             final Object value,
                             final InternalReadAccessor extractor) {
            this.index = index;
            final ValueType vtype = extractor.getValueType();

            isNull = extractor.isNullValue( null,
                                            value );

            if ( vtype.isBoolean() ) {
                this.type = BOOL;
                if ( !isNull ) {
                    this.bvalue = extractor.getBooleanValue( null,
                                                             value );
                    this.setHashCode( this.bvalue ? 1231 : 1237 );
                } else {
                    this.setHashCode( 0 );
                }
            } else if ( vtype.isIntegerNumber() || vtype.isChar() ) {
                this.type = LONG;
                if ( !isNull ) {
                    this.lvalue = extractor.getLongValue( null,
                                                          value );
                    this.setHashCode( (int) (this.lvalue ^ (this.lvalue >>> 32)) );
                } else {
                    this.setHashCode( 0 );
                }
            } else if ( vtype.isFloatNumber() ) {
                this.type = DOUBLE;
                if ( !isNull ) {
                    this.dvalue = extractor.getDoubleValue( null,
                                                            value );
                    final long temp = Double.doubleToLongBits( this.dvalue );
                    this.setHashCode( (int) (temp ^ (temp >>> 32)) );
                } else {
                    this.setHashCode( 0 );
                }
            } else {
                this.type = OBJECT;
                if ( !isNull ) {
                    this.ovalue = extractor.getValue( null,
                                                      value );
                    this.setHashCode( this.ovalue != null ? this.ovalue.hashCode() : 0 );
                } else {
                    this.setHashCode( 0 );
                }
            }
        }

        public void setValue(final int index,
                             final InternalReadAccessor extractor,
                             final FieldValue value) {
            this.index = index;

            this.isNull = value.isNull();
            final ValueType vtype = extractor.getValueType();

            if ( vtype.isBoolean() ) {
                this.type = BOOL;
                if ( !isNull ) {
                    this.bvalue = value.getBooleanValue();
                    this.setHashCode( this.bvalue ? 1231 : 1237 );
                } else {
                    this.setHashCode( 0 );
                }
            } else if ( vtype.isIntegerNumber() ) {
                this.type = LONG;
                if ( !isNull ) {
                    this.lvalue = value.getLongValue();
                    this.setHashCode( (int) (this.lvalue ^ (this.lvalue >>> 32)) );
                } else {
                    this.setHashCode( 0 );
                }
            } else if ( vtype.isFloatNumber() ) {
                this.type = DOUBLE;
                if ( !isNull ) {
                    this.dvalue = value.getDoubleValue();
                    final long temp = Double.doubleToLongBits( this.dvalue );
                    this.setHashCode( (int) (temp ^ (temp >>> 32)) );
                } else {
                    this.setHashCode( 0 );
                }
            } else {
                this.type = OBJECT;
                if ( !isNull ) {
                    this.ovalue = value.getValue();
                    this.setHashCode( this.ovalue != null ? this.ovalue.hashCode() : 0 );
                } else {
                    this.setHashCode( 0 );
                }
            }
        }

        private void setHashCode(final int hashSeed) {
            final int PRIME = 31;
            int result = 1;
            result = PRIME * result + hashSeed;
            result = PRIME * result + this.index;
            this.hashCode = result;
        }

        public boolean getBooleanValue() {
            switch ( this.type ) {
                case BOOL :
                    return this.bvalue;
                case OBJECT :
                    if ( this.ovalue == null ) {
                        return false;
                    } else if ( this.ovalue instanceof Boolean ) {
                        return ((Boolean) this.ovalue).booleanValue();
                    } else if ( this.ovalue instanceof String ) {
                        return Boolean.valueOf( (String) this.ovalue ).booleanValue();
                    } else {
                        throw new ClassCastException( "Can't convert " + this.ovalue.getClass() + " to a boolean value." );
                    }
                case LONG :
                    throw new ClassCastException( "Can't convert long to a boolean value." );
                case DOUBLE :
                    throw new ClassCastException( "Can't convert double to a boolean value." );

            }
            return false;
        }

        public long getLongValue() {
            switch ( this.type ) {
                case BOOL :
                    return this.bvalue ? 1 : 0;
                case OBJECT :
                    if ( this.ovalue == null ) {
                        return 0;
                    } else if ( this.ovalue instanceof Number ) {
                        return ((Number) this.ovalue).longValue();
                    } else if ( this.ovalue instanceof String ) {
                        return Long.parseLong( (String) this.ovalue );
                    } else {
                        throw new ClassCastException( "Can't convert " + this.ovalue.getClass() + " to a long value." );
                    }
                case LONG :
                    return this.lvalue;
                case DOUBLE :
                    return (long) this.dvalue;

            }
            return 0;
        }

        public double getDoubleValue() {
            switch ( this.type ) {
                case BOOL :
                    return this.bvalue ? 1 : 0;
                case OBJECT :
                    if ( this.ovalue == null ) {
                        return 0;
                    } else if ( this.ovalue instanceof Number ) {
                        return ((Number) this.ovalue).doubleValue();
                    } else if ( this.ovalue instanceof String ) {
                        return Double.parseDouble( (String) this.ovalue );
                    } else {
                        throw new ClassCastException( "Can't convert " + this.ovalue.getClass() + " to a double value." );
                    }
                case LONG :
                    return this.lvalue;
                case DOUBLE :
                    return this.dvalue;
            }
            return 0;
        }

        public Object getObjectValue() {
            switch ( this.type ) {
                case BOOL :
                    return this.bvalue ? Boolean.TRUE : Boolean.FALSE;
                case OBJECT :
                    return this.ovalue;
                case LONG :
                    return new Long( this.lvalue );
                case DOUBLE :
                    return new Double( this.dvalue );
            }
            return null;
        }

        public int hashCode() {
            return this.hashCode;
        }

        public boolean equals(final Object object) {
            final HashKey other = (HashKey) object;

            if ( this.isNull ) {
                return (other.isNull );
            }

            switch ( this.type ) {
                case BOOL :
                    return (this.index == other.index) && (this.bvalue == other.getBooleanValue());
                case LONG :
                    return (this.index == other.index) && (this.lvalue == other.getLongValue());
                case DOUBLE :
                    return (this.index == other.index) && (this.dvalue == other.getDoubleValue());
                case OBJECT :
                    final Object otherValue = other.getObjectValue();
                    if ( (this.ovalue != null) && (this.ovalue instanceof Number) && (otherValue instanceof Number) ) {
                        return (this.index == other.index) && (((Number) this.ovalue).doubleValue() == ((Number) otherValue).doubleValue());
                    }
                    return (this.index == other.index) && (this.ovalue == null ? otherValue == null : this.ovalue.equals( otherValue ));
            }
            return false;
        }

    }

    public static class FieldIndex
        implements
        LinkedListNode {
        private static final long serialVersionUID = 400L;
        private int               index;
        private InternalReadAccessor    fieldExtactor;

        private int               count;

        private boolean           hashed;

        private LinkedListNode    previous;
        private LinkedListNode    next;

        public FieldIndex() {

        }

        public FieldIndex(final int index,
                          final InternalReadAccessor fieldExtractor) {
            this.index = index;
            this.fieldExtactor = fieldExtractor;
        }

        public void readExternal(ObjectInput in) throws IOException,
                                                ClassNotFoundException {
            index = in.readInt();
            fieldExtactor = (InternalReadAccessor) in.readObject();
            count = in.readInt();
            hashed = in.readBoolean();
            previous = (LinkedListNode) in.readObject();
            next = (LinkedListNode) in.readObject();
        }

        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeInt( index );
            out.writeObject( fieldExtactor );
            out.writeInt( count );
            out.writeBoolean( hashed );
            out.writeObject( previous );
            out.writeObject( next );
        }

        public InternalReadAccessor getFieldExtractor() {
            return this.fieldExtactor;
        }

        public int getIndex() {
            return this.index;
        }

        public int getCount() {
            return this.count;
        }

        public ReadAccessor getFieldExtactor() {
            return this.fieldExtactor;
        }

        public boolean isHashed() {
            return this.hashed;
        }

        public void setHashed(final boolean hashed) {
            this.hashed = hashed;
        }

        public void increaseCounter() {
            this.count++;
        }

        public void decreaseCounter() {
            this.count--;
        }

        public LinkedListNode getNext() {
            return this.next;
        }

        public LinkedListNode getPrevious() {
            return this.previous;
        }

        public void setNext(final LinkedListNode next) {
            this.next = next;

        }

        public void setPrevious(final LinkedListNode previous) {
            this.previous = previous;
        }
    }
}
