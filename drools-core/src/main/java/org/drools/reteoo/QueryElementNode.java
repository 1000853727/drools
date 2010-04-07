package org.drools.reteoo;

import org.drools.QueryResults;
import org.drools.RuleBaseConfiguration;
import org.drools.base.DefaultQueryResultsCollector;
import org.drools.base.DroolsQuery;
import org.drools.base.QueryResultCollector;
import org.drools.common.BaseNode;
import org.drools.common.BetaConstraints;
import org.drools.common.EmptyBetaConstraints;
import org.drools.common.InternalFactHandle;
import org.drools.common.InternalWorkingMemory;
import org.drools.common.NodeMemory;
import org.drools.common.PropagationContextImpl;
import org.drools.core.util.Entry;
import org.drools.reteoo.builder.BuildContext;
import org.drools.rule.Declaration;
import org.drools.rule.EntryPoint;
import org.drools.rule.QueryElement;
import org.drools.rule.Variable;
import org.drools.spi.AlphaNodeFieldConstraint;
import org.drools.spi.DataProvider;
import org.drools.spi.PropagationContext;

public class QueryElementNode extends LeftTupleSource
    implements
    LeftTupleSinkNode {

    private LeftTupleSource   tupleSource;

    private LeftTupleSinkNode previousTupleSinkNode;
    private LeftTupleSinkNode nextTupleSinkNode;

    private QueryElement      queryElement;

    private boolean           tupleMemoryEnabled;

    public QueryElementNode(final int id,
                            final LeftTupleSource tupleSource,
                            final QueryElement queryElement,
                            final boolean tupleMemoryEnabled,
                            final BuildContext context) {
        super( id,
               context.getPartitionId(),
               context.getRuleBase().getConfiguration().isMultithreadEvaluation() );
        this.tupleSource = tupleSource;
        this.queryElement = queryElement;
        this.tupleMemoryEnabled = tupleMemoryEnabled;
    }

    public void updateSink(LeftTupleSink sink,
                           PropagationContext context,
                           InternalWorkingMemory workingMemory) {
        // do nothing as we have no left memory
    }

    protected void doRemove(RuleRemovalContext context,
                            ReteooBuilder builder,
                            BaseNode node,
                            InternalWorkingMemory[] workingMemories) {
        context.visitTupleSource( this );
        if ( !node.isInUse() ) {
            removeTupleSink( (LeftTupleSink) node );
        }

        if ( !context.alreadyVisited( this.tupleSource ) ) {
            this.tupleSource.remove( context,
                                     builder,
                                     this,
                                     workingMemories );
        }
    }

    public void attach() {
        this.tupleSource.addTupleSink( this );
    }

    public void attach(InternalWorkingMemory[] workingMemories) {
        attach();

        for ( int i = 0, length = workingMemories.length; i < length; i++ ) {
            final InternalWorkingMemory workingMemory = workingMemories[i];
            final PropagationContext propagationContext = new PropagationContextImpl( workingMemory.getNextPropagationIdCounter(),
                                                                                      PropagationContext.RULE_ADDITION,
                                                                                      null,
                                                                                      null,
                                                                                      null );
            this.tupleSource.updateSink( this,
                                         propagationContext,
                                         workingMemory );
        }
    }

    public void networkUpdated() {
        this.tupleSource.networkUpdated();
    }

    public void assertLeftTuple(LeftTuple leftTuple,
                                PropagationContext context,
                                InternalWorkingMemory workingMemory) {
        Object[] arguments = this.queryElement.getArguments();
        Object[] inputArgs = new Object[arguments.length];

        System.arraycopy( arguments,
                          0,
                          inputArgs,
                          0,
                          inputArgs.length );

        int[] declIndexes = this.queryElement.getDeclIndexes();

        for ( int i = 0, length = declIndexes.length; i > length; i++ ) {
            Declaration declr = (Declaration) arguments[declIndexes[i]];
            inputArgs[i] = declr.getValue( workingMemory,
                                           leftTuple.get( declr ).getObject() );
        }

        UnificationNodeQueryResultsCollector collector = new UnificationNodeQueryResultsCollector( leftTuple,
                                                                                                   this.queryElement.getVariables(),
                                                                                                   this.sink );
        
        DroolsQuery queryObject = new DroolsQuery( this.queryElement.getQueryName(),
                                                   inputArgs,
                                                   collector );
        collector.setDroolsQuery( queryObject );

        InternalFactHandle handle = workingMemory.getFactHandleFactory().newFactHandle( queryObject,
                                                                                        workingMemory.getObjectTypeConfigurationRegistry().getObjectTypeConf( EntryPoint.DEFAULT,
                                                                                                                                                              queryObject ),
                                                                                        workingMemory );

        workingMemory.insert( handle,
                              queryObject,
                              null,
                              null,
                              workingMemory.getObjectTypeConfigurationRegistry().getObjectTypeConf( workingMemory.getEntryPoint(),
                                                                                                    queryObject ) );

        workingMemory.getFactHandleFactory().destroyFactHandle( handle );

    }

    public static class UnificationNodeQueryResultsCollector
        implements
        QueryResultCollector {

        private LeftTuple                 leftTuple;
        protected LeftTupleSinkPropagator sink;

        private DroolsQuery               query;
        private int[]                     variables;

        public UnificationNodeQueryResultsCollector(LeftTuple leftTuple,
                                                    int[] variables,
                                                    LeftTupleSinkPropagator sink) {
            this.leftTuple = leftTuple;
            this.variables = variables;
            this.sink = sink;
        }

        public void setDroolsQuery(DroolsQuery query) {
            this.query = query;
        }

        public void add(LeftTuple resultLeftTuple,
                        PropagationContext context,
                        InternalWorkingMemory workingMemory) {

            Object[] args = query.getArguments();
            Object[] objects = new Object[this.variables.length];

            for ( int i = 0, length = this.variables.length; i < length; i++ ) {
                objects[i] = ((Variable) args[ this.variables[i]] ).getValue();
            }

            final InternalFactHandle handle = workingMemory.getFactHandleFactory().newFactHandle( objects,
                                                                                                  null, // set this to null, otherwise it uses the driver fact's entrypoint
                                                                                                  workingMemory );

            RightTuple rightTuple = new RightTuple( handle,
                                                    null );

            this.sink.propagateAssertLeftTuple( this.leftTuple,
                                                rightTuple,
                                                null,
                                                null,
                                                context,
                                                workingMemory,
                                                false );
        }

    }

    public short getType() {
        return NodeTypeEnums.UnificationNode;
    }

    public boolean isLeftTupleMemoryEnabled() {
        return false;
    }

    public void retractLeftTuple(LeftTuple leftTuple,
                                 PropagationContext context,
                                 InternalWorkingMemory workingMemory) {
    }

    public void modifyLeftTuple(LeftTuple leftTuple,
                                PropagationContext context,
                                InternalWorkingMemory workingMemory) {
    }

    public void modifyLeftTuple(InternalFactHandle factHandle,
                                ModifyPreviousTuples modifyPreviousTuples,
                                PropagationContext context,
                                InternalWorkingMemory workingMemory) {
    }

    public void setLeftTupleMemoryEnabled(boolean tupleMemoryEnabled) {
        this.tupleMemoryEnabled = tupleMemoryEnabled;
    }

    /**
     * Returns the next node
     * @return
     *      The next TupleSinkNode
     */
    public LeftTupleSinkNode getNextLeftTupleSinkNode() {
        return this.nextTupleSinkNode;
    }

    /**
     * Sets the next node
     * @param next
     *      The next TupleSinkNode
     */
    public void setNextLeftTupleSinkNode(final LeftTupleSinkNode next) {
        this.nextTupleSinkNode = next;
    }

    /**
     * Returns the previous node
     * @return
     *      The previous TupleSinkNode
     */
    public LeftTupleSinkNode getPreviousLeftTupleSinkNode() {
        return this.previousTupleSinkNode;
    }

    /**
     * Sets the previous node
     * @param previous
     *      The previous TupleSinkNode
     */
    public void setPreviousLeftTupleSinkNode(final LeftTupleSinkNode previous) {
        this.previousTupleSinkNode = previous;
    }

}
