package org.drools.marshalling;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.drools.InitialFact;
import org.drools.base.ClassObjectType;
import org.drools.common.AgendaItem;
import org.drools.common.DefaultAgenda;
import org.drools.common.EqualityKey;
import org.drools.common.InternalFactHandle;
import org.drools.common.InternalRuleBase;
import org.drools.common.InternalWorkingMemory;
import org.drools.common.LogicalDependency;
import org.drools.common.NodeMemory;
import org.drools.common.ObjectStore;
import org.drools.common.RuleFlowGroupImpl;
import org.drools.common.WorkingMemoryAction;
import org.drools.process.core.context.swimlane.SwimlaneContext;
import org.drools.process.core.context.variable.VariableScope;
import org.drools.process.instance.WorkItemManager;
import org.drools.process.instance.context.swimlane.SwimlaneContextInstance;
import org.drools.process.instance.context.variable.VariableScopeInstance;
import org.drools.process.instance.timer.TimerInstance;
import org.drools.process.instance.timer.TimerManager;
import org.drools.reteoo.BetaNode;
import org.drools.reteoo.LeftTuple;
import org.drools.reteoo.LeftTupleSink;
import org.drools.reteoo.NodeTypeEnums;
import org.drools.reteoo.ObjectTypeNode;
import org.drools.reteoo.ReteooWorkingMemory;
import org.drools.reteoo.RightTuple;
import org.drools.reteoo.RuleTerminalNode;
import org.drools.reteoo.AccumulateNode.AccumulateContext;
import org.drools.reteoo.AccumulateNode.AccumulateMemory;
import org.drools.reteoo.CollectNode.CollectContext;
import org.drools.reteoo.CollectNode.CollectMemory;
import org.drools.rule.EntryPoint;
import org.drools.rule.Rule;
import org.drools.ruleflow.instance.RuleFlowProcessInstance;
import org.drools.runtime.process.NodeInstance;
import org.drools.runtime.process.WorkItem;
import org.drools.spi.ActivationGroup;
import org.drools.spi.AgendaGroup;
import org.drools.spi.PropagationContext;
import org.drools.spi.RuleFlowGroup;
import org.drools.util.ObjectHashMap;
import org.drools.util.ObjectHashSet;
import org.drools.workflow.instance.node.CompositeContextNodeInstance;
import org.drools.workflow.instance.node.ForEachNodeInstance;
import org.drools.workflow.instance.node.HumanTaskNodeInstance;
import org.drools.workflow.instance.node.JoinInstance;
import org.drools.workflow.instance.node.MilestoneNodeInstance;
import org.drools.workflow.instance.node.RuleSetNodeInstance;
import org.drools.workflow.instance.node.SubProcessNodeInstance;
import org.drools.workflow.instance.node.TimerNodeInstance;
import org.drools.workflow.instance.node.WorkItemNodeInstance;

public class OutputMarshaller {
    public static void writeSession(MarshallerWriteContext context) throws IOException {
        ReteooWorkingMemory wm = (ReteooWorkingMemory) context.wm;
        
        final boolean multithread = wm.isPartitionManagersActive(); 
        // is multi-thread active?
        if( multithread ) {
            context.writeBoolean( true );
            wm.stopPartitionManagers();
        } else {
            context.writeBoolean( false );
        }

        context.writeInt( wm.getFactHandleFactory().getId() );
        context.writeLong( wm.getFactHandleFactory().getRecency() );
//        context.out.println( "FactHandleFactory int:" + wm.getFactHandleFactory().getId() + " long:" + wm.getFactHandleFactory().getRecency() );

        InternalFactHandle handle = context.wm.getInitialFactHandle();
        context.writeInt( handle.getId() );
        context.writeLong( handle.getRecency() );
//        context.out.println( "InitialFact int:" + handle.getId() + " long:" + handle.getRecency() );

        context.writeLong( wm.getPropagationIdCounter() );
//        context.out.println( "PropagationCounter long:" + wm.getPropagationIdCounter() );

        writeAgenda( context );

        writeFactHandles( context );

        writeActionQueue( context );

        if ( wm.getTruthMaintenanceSystem() != null ) {
            context.writeBoolean( true );
            writeTruthMaintenanceSystem( context );
        } else {
            context.writeBoolean( false );
        }

        if ( context.marshalProcessInstances ) {
            writeProcessInstances( context );
        }

        if ( context.marshalWorkItems ) {
            writeWorkItems( context );
        }

        if ( context.marshalTimers ) {
            writeTimers( context );
        }
        
        if( multithread ) {
            wm.startPartitionManagers();
        }
    }

    public static void writeAgenda(MarshallerWriteContext context) throws IOException {
        InternalWorkingMemory wm = context.wm;
        DefaultAgenda agenda = (DefaultAgenda) wm.getAgenda();

        Map<String, ActivationGroup> activationGroups = agenda.getActivationGroupsMap();

        AgendaGroup[] agendaGroups = (AgendaGroup[]) agenda.getAgendaGroupsMap().values().toArray( new AgendaGroup[agenda.getAgendaGroupsMap().size()] );
        Arrays.sort( agendaGroups,
                     AgendaGroupSorter.instance );

        for ( AgendaGroup group : agendaGroups ) {
            context.writeShort( PersisterEnums.AGENDA_GROUP );
            context.writeUTF( group.getName() );
            context.writeBoolean( group.isActive() );
        }
        context.writeShort( PersisterEnums.END );

        LinkedList<AgendaGroup> focusStack = agenda.getStackList();
        for ( Iterator<AgendaGroup> it = focusStack.iterator(); it.hasNext(); ) {
            AgendaGroup group = it.next();
            context.writeShort( PersisterEnums.AGENDA_GROUP );
            context.writeUTF( group.getName() );
        }
        context.writeShort( PersisterEnums.END );

        RuleFlowGroupImpl[] ruleFlowGroups = (RuleFlowGroupImpl[]) agenda.getRuleFlowGroupsMap().values().toArray( new RuleFlowGroupImpl[agenda.getRuleFlowGroupsMap().size()] );
        Arrays.sort( ruleFlowGroups,
                     RuleFlowGroupSorter.instance );

        for ( RuleFlowGroupImpl group : ruleFlowGroups ) {
            context.writeShort( PersisterEnums.RULE_FLOW_GROUP );
            //group.write( context );
            context.writeUTF( group.getName() );
            context.writeBoolean( group.isActive() );
            context.writeBoolean( group.isAutoDeactivate() );
        }
        context.writeShort( PersisterEnums.END );
    }

    public static class AgendaGroupSorter
        implements
        Comparator<AgendaGroup> {
        public static final AgendaGroupSorter instance = new AgendaGroupSorter();

        public int compare(AgendaGroup group1,
                           AgendaGroup group2) {
            return group1.getName().compareTo( group2.getName() );
        }
    }

    public static class RuleFlowGroupSorter
        implements
        Comparator<RuleFlowGroup> {
        public static final RuleFlowGroupSorter instance = new RuleFlowGroupSorter();

        public int compare(RuleFlowGroup group1,
                           RuleFlowGroup group2) {
            return group1.getName().compareTo( group2.getName() );
        }
    }

    public static void writeActionQueue(MarshallerWriteContext context) throws IOException {
        ReteooWorkingMemory wm = (ReteooWorkingMemory) context.wm;

        WorkingMemoryAction[] queue = wm.getActionQueue().toArray( new WorkingMemoryAction[wm.getActionQueue().size()] );
        for ( int i = queue.length - 1; i >= 0; i-- ) {
            context.writeShort( PersisterEnums.WORKING_MEMORY_ACTION );
            queue[i].write( context );
        }
        context.writeShort( PersisterEnums.END );
    }

    public static void writeTruthMaintenanceSystem(MarshallerWriteContext context) throws IOException {
        ObjectOutputStream stream = context.stream;

        ObjectHashMap assertMap = context.wm.getTruthMaintenanceSystem().getAssertMap();

        EqualityKey[] keys = new EqualityKey[assertMap.size()];
        org.drools.util.Iterator it = assertMap.iterator();
        int i = 0;
        for ( org.drools.util.ObjectHashMap.ObjectEntry entry = (org.drools.util.ObjectHashMap.ObjectEntry) it.next(); entry != null; entry = (org.drools.util.ObjectHashMap.ObjectEntry) it.next() ) {
            EqualityKey key = (EqualityKey) entry.getKey();
            keys[i++] = key;
        }

        Arrays.sort( keys,
                     EqualityKeySorter.instance );

        // write the assert map of Equality keys
        for ( EqualityKey key : keys ) {
            stream.writeShort( PersisterEnums.EQUALITY_KEY );
            stream.writeInt( key.getStatus() );
            InternalFactHandle handle = key.getFactHandle();
            stream.writeInt( handle.getId() );
//            context.out.println( "EqualityKey int:" + key.getStatus() + " int:" + handle.getId() );
            if ( key.getOtherFactHandle() != null && !key.getOtherFactHandle().isEmpty() ) {
                for ( InternalFactHandle handle2 : key.getOtherFactHandle() ) {
                    stream.writeShort( PersisterEnums.FACT_HANDLE );
                    stream.writeInt( handle2.getId() );
//                    context.out.println( "OtherHandle int:" + handle2.getId() );
                }
            }
            stream.writeShort( PersisterEnums.END );
        }
        stream.writeShort( PersisterEnums.END );
    }

    public static class EqualityKeySorter
        implements
        Comparator<EqualityKey> {
        public static final EqualityKeySorter instance = new EqualityKeySorter();

        public int compare(EqualityKey key1,
                           EqualityKey key2) {
            return key1.getFactHandle().getId() - key2.getFactHandle().getId();
        }
    }

    public static void writeFactHandles(MarshallerWriteContext context) throws IOException {
        ObjectOutputStream stream = context.stream;
        InternalWorkingMemory wm = context.wm;
        PlaceholderResolverStrategyFactory resolverStrategyFactory = context.resolverStrategyFactory;

        writeInitialFactHandleRightTuples( context );

        stream.writeInt( wm.getObjectStore().size() );

        // Write out FactHandles
        for ( InternalFactHandle handle : orderFacts( wm.getObjectStore() ) ) {
            //stream.writeShort( PersisterEnums.FACT_HANDLE );
            //InternalFactHandle handle = (InternalFactHandle) it.next();
            writeFactHandle( context,
                             stream,
                             resolverStrategyFactory,
                             handle );

            writeRightTuples( handle,
                              context );
        }

        writeInitialFactHandleLeftTuples( context );

        writeLeftTuples( context );

        writePropagationContexts( context );

        writeActivations( context );
    }

    private static void writeFactHandle(MarshallerWriteContext context,
                                        ObjectOutputStream stream,
                                        PlaceholderResolverStrategyFactory resolverStrategyFactory,
                                        InternalFactHandle handle) throws IOException {
        stream.writeInt( handle.getId() );
        stream.writeLong( handle.getRecency() );

//        context.out.println( "Object : int:" + handle.getId() + " long:" + handle.getRecency() );
//        context.out.println( handle.getObject() );

        Object object = handle.getObject();

        PlaceholderResolverStrategy strategy = resolverStrategyFactory.getStrategy( object );

        stream.writeInt( strategy.getIndex() );

        strategy.write( stream,
                        object );
    }

    public static InternalFactHandle[] orderFacts(ObjectStore objectStore) {
        // this method is just needed for testing purposes, to allow round tripping
        int size = objectStore.size();
        InternalFactHandle[] handles = new InternalFactHandle[size];
        int i = 0;
        for ( Iterator it = objectStore.iterateFactHandles(); it.hasNext(); ) {
            handles[i++] = (InternalFactHandle) it.next();
        }

        Arrays.sort( handles,
                     new HandleSorter() );

        return handles;
    }

    public static class HandleSorter
        implements
        Comparator<InternalFactHandle> {
        public int compare(InternalFactHandle h1,
                           InternalFactHandle h2) {
            return h1.getId() - h2.getId();
        }
    }

    public static void writeInitialFactHandleRightTuples(MarshallerWriteContext context) throws IOException {
        ObjectOutputStream stream = context.stream;
        InternalRuleBase ruleBase = context.ruleBase;

        ObjectTypeNode initialFactNode = ruleBase.getRete().getEntryPointNode( EntryPoint.DEFAULT ).getObjectTypeNodes().get( new ClassObjectType( InitialFact.class ) );

        // do we write the fact to the objecttypenode memory
        if ( initialFactNode != null ) {
            ObjectHashSet initialFactMemory = (ObjectHashSet) context.wm.getNodeMemory( initialFactNode );
            if ( initialFactMemory != null && !initialFactMemory.isEmpty() ) {
//                context.out.println( "InitialFactMemory true int:" + initialFactNode.getId() );
                stream.writeBoolean( true );
                stream.writeInt( initialFactNode.getId() );

//                context.out.println( "InitialFact RightTuples" );
                writeRightTuples( context.wm.getInitialFactHandle(),
                                  context );
            } else {
//                context.out.println( "InitialFactMemory false " );
                stream.writeBoolean( false );
            }
        } else {
//            context.out.println( "InitialFactMemory false " );
            stream.writeBoolean( false );
        }
    }

    public static void writeInitialFactHandleLeftTuples(MarshallerWriteContext context) throws IOException {
        ObjectOutputStream stream = context.stream;

//        context.out.println( "InitialFact LeftTuples Start" );
        InternalFactHandle handle = context.wm.getInitialFactHandle();
        for ( LeftTuple leftTuple = getLeftTuple( handle.getLeftTuple() ); leftTuple != null; leftTuple = (LeftTuple) leftTuple.getLeftParentPrevious() ) {
            stream.writeShort( PersisterEnums.LEFT_TUPLE );

            stream.writeInt( leftTuple.getLeftTupleSink().getId() );
//            context.out.println( "LeftTuple sinkId:" + leftTuple.getLeftTupleSink().getId() );
            writeLeftTuple( leftTuple,
                            context,
                            true );
        }
        stream.writeShort( PersisterEnums.END );
//        context.out.println( "InitialFact LeftTuples End" );
    }

    public static void writeRightTuples(InternalFactHandle handle,
                                        MarshallerWriteContext context) throws IOException {
        ObjectOutputStream stream = context.stream;
//        context.out.println( "RightTuples Start" );

        RightTuple rightTuple = handle.getRightTuple();
        for ( RightTuple tempRightTuple = rightTuple; tempRightTuple != null; tempRightTuple = (RightTuple) tempRightTuple.getHandleNext() ) {
            rightTuple = tempRightTuple;
        }
        for ( ; rightTuple != null; rightTuple = (RightTuple) rightTuple.getHandlePrevious() ) {
            stream.writeShort( PersisterEnums.RIGHT_TUPLE );
            writeRightTuple( rightTuple,
                             context );
        }
        stream.writeShort( PersisterEnums.END );
//        context.out.println( "RightTuples END" );
    }

    public static void writeRightTuple(RightTuple rightTuple,
                                       MarshallerWriteContext context) throws IOException {
        ObjectOutputStream stream = context.stream;
        InternalWorkingMemory wm = context.wm;
        stream.writeInt( rightTuple.getRightTupleSink().getId() );
//        context.out.println( "RightTuple sinkId:" + rightTuple.getRightTupleSink().getId() );
    }

    public static void writeLeftTuples(MarshallerWriteContext context) throws IOException {
        ObjectOutputStream stream = context.stream;
        InternalWorkingMemory wm = context.wm;

        // Write out LeftTuples
//        context.out.println( "LeftTuples Start" );
        for ( InternalFactHandle handle : orderFacts( wm.getObjectStore() ) ) {
            //InternalFactHandle handle = (InternalFactHandle) it.next();

            for ( LeftTuple leftTuple = getLeftTuple( handle.getLeftTuple() ); leftTuple != null; leftTuple = (LeftTuple) leftTuple.getLeftParentPrevious() ) {
                stream.writeShort( PersisterEnums.LEFT_TUPLE );

                stream.writeInt( leftTuple.getLeftTupleSink().getId() );
                stream.writeInt( handle.getId() );

//                context.out.println( "LeftTuple sinkId:" + leftTuple.getLeftTupleSink().getId() + " handleId:" + handle.getId() );
                writeLeftTuple( leftTuple,
                                context,
                                true );
            }
        }
        stream.writeShort( PersisterEnums.END );
//        context.out.println( "LeftTuples End" );
    }

    public static void writeLeftTuple(LeftTuple leftTuple,
                                      MarshallerWriteContext context,
                                      boolean recurse) throws IOException {
        ObjectOutputStream stream = context.stream;
        InternalRuleBase ruleBase = context.ruleBase;
        InternalWorkingMemory wm = context.wm;

        LeftTupleSink sink = leftTuple.getLeftTupleSink();

        switch ( sink.getType() ) {
            case NodeTypeEnums.JoinNode : {
//                context.out.println( "JoinNode" );
                for ( LeftTuple childLeftTuple = getLeftTuple( leftTuple.getBetaChildren() ); childLeftTuple != null; childLeftTuple = (LeftTuple) childLeftTuple.getLeftParentPrevious() ) {
                    stream.writeShort( PersisterEnums.RIGHT_TUPLE );
                    stream.writeInt( childLeftTuple.getLeftTupleSink().getId() );
                    stream.writeInt( childLeftTuple.getRightParent().getFactHandle().getId() );
//                    context.out.println( "RightTuple int:" + childLeftTuple.getLeftTupleSink().getId() + " int:" + childLeftTuple.getRightParent().getFactHandle().getId() );
                    writeLeftTuple( childLeftTuple,
                                    context,
                                    recurse );
                }
                stream.writeShort( PersisterEnums.END );
//                context.out.println( "JoinNode   ---   END" );
                break;
            }
            case NodeTypeEnums.EvalConditionNode : {
//                context.out.println( "EvalConditionNode" );
                for ( LeftTuple childLeftTuple = getLeftTuple( leftTuple.getBetaChildren() ); childLeftTuple != null; childLeftTuple = (LeftTuple) childLeftTuple.getLeftParentPrevious() ) {
                    stream.writeShort( PersisterEnums.LEFT_TUPLE );
                    stream.writeInt( childLeftTuple.getLeftTupleSink().getId() );
                    writeLeftTuple( childLeftTuple,
                                    context,
                                    recurse );
                }
                stream.writeShort( PersisterEnums.END );
                break;
            }
            case NodeTypeEnums.NotNode : {
                if ( leftTuple.getBlocker() == null ) {
                    // is not blocked so has children
                    stream.writeShort( PersisterEnums.LEFT_TUPLE_NOT_BLOCKED );

                    for ( LeftTuple childLeftTuple = getLeftTuple( leftTuple.getBetaChildren() ); childLeftTuple != null; childLeftTuple = (LeftTuple) leftTuple.getLeftParentPrevious() ) {
                        stream.writeShort( PersisterEnums.LEFT_TUPLE );
                        stream.writeInt( childLeftTuple.getLeftTupleSink().getId() );
                        writeLeftTuple( childLeftTuple,
                                        context,
                                        recurse );
                    }
                    stream.writeShort( PersisterEnums.END );

                } else {
                    stream.writeShort( PersisterEnums.LEFT_TUPLE_BLOCKED );
                    stream.writeInt( leftTuple.getBlocker().getFactHandle().getId() );
                }
                break;
            }
            case NodeTypeEnums.ExistsNode : {
                if ( leftTuple.getBlocker() == null ) {
                    // is blocked so has children
                    stream.writeShort( PersisterEnums.LEFT_TUPLE_NOT_BLOCKED );
                } else {
                    stream.writeShort( PersisterEnums.LEFT_TUPLE_BLOCKED );
                    stream.writeInt( leftTuple.getBlocker().getFactHandle().getId() );

                    for ( LeftTuple childLeftTuple = getLeftTuple( leftTuple.getBetaChildren() ); childLeftTuple != null; childLeftTuple = (LeftTuple) leftTuple.getLeftParentPrevious() ) {
                        stream.writeShort( PersisterEnums.LEFT_TUPLE );
                        stream.writeInt( childLeftTuple.getLeftTupleSink().getId() );
                        writeLeftTuple( childLeftTuple,
                                        context,
                                        recurse );
                    }
                    stream.writeShort( PersisterEnums.END );
                }
                break;
            }
            case NodeTypeEnums.AccumulateNode : {
//                context.out.println( "AccumulateNode" );
                // accumulate nodes generate new facts on-demand and need special procedures when serializing to persistent storage
                AccumulateMemory memory = (AccumulateMemory) context.wm.getNodeMemory( (BetaNode) sink );
                AccumulateContext accctx = (AccumulateContext) memory.betaMemory.getCreatedHandles().get( leftTuple );
                // first we serialize the generated fact handle
                writeFactHandle( context,
                                 stream,
                                 context.resolverStrategyFactory,
                                 accctx.result.getFactHandle() );
                // then we serialize the associated accumulation context
                stream.writeObject( accctx.context );
                // then we serialize the boolean propagated flag
                stream.writeBoolean( accctx.propagated );

                // then we serialize all the propagated tuples
                for ( LeftTuple childLeftTuple = getLeftTuple( leftTuple.getBetaChildren() ); childLeftTuple != null; childLeftTuple = (LeftTuple) childLeftTuple.getLeftParentPrevious() ) {
                    if( leftTuple.getLeftTupleSink().getId() == childLeftTuple.getLeftTupleSink().getId()) {
                        // this is a matching record, so, associate the right tuples
//                        context.out.println( "RightTuple(match) int:" + childLeftTuple.getLeftTupleSink().getId() + " int:" + childLeftTuple.getRightParent().getFactHandle().getId() );
                        stream.writeShort( PersisterEnums.RIGHT_TUPLE );
                        stream.writeInt( childLeftTuple.getRightParent().getFactHandle().getId() );
                    } else {
                        // this is a propagation record
//                        context.out.println( "RightTuple(propagation) int:" + childLeftTuple.getLeftTupleSink().getId() + " int:" + childLeftTuple.getRightParent().getFactHandle().getId() );
                        stream.writeShort( PersisterEnums.LEFT_TUPLE );
                        stream.writeInt( childLeftTuple.getLeftTupleSink().getId() );
                        writeLeftTuple( childLeftTuple,
                                        context,
                                        recurse );
                    }
                }
                stream.writeShort( PersisterEnums.END );
//                context.out.println( "AccumulateNode   ---   END" );
                break;
            }
            case NodeTypeEnums.CollectNode : {
//                context.out.println( "CollectNode" );
                // collect nodes generate new facts on-demand and need special procedures when serializing to persistent storage
                CollectMemory memory = (CollectMemory) context.wm.getNodeMemory( (BetaNode) sink );
                CollectContext colctx = (CollectContext) memory.betaMemory.getCreatedHandles().get( leftTuple );
                // first we serialize the generated fact handle
                writeFactHandle( context,
                                 stream,
                                 context.resolverStrategyFactory,
                                 colctx.resultTuple.getFactHandle() );
                // then we serialize the boolean propagated flag
                stream.writeBoolean( colctx.propagated );

                // then we serialize all the propagated tuples
                for ( LeftTuple childLeftTuple = getLeftTuple( leftTuple.getBetaChildren() ); childLeftTuple != null; childLeftTuple = (LeftTuple) childLeftTuple.getLeftParentPrevious() ) {
                    if( leftTuple.getLeftTupleSink().getId() == childLeftTuple.getLeftTupleSink().getId()) {
                        // this is a matching record, so, associate the right tuples
//                        context.out.println( "RightTuple(match) int:" + childLeftTuple.getLeftTupleSink().getId() + " int:" + childLeftTuple.getRightParent().getFactHandle().getId() );
                        stream.writeShort( PersisterEnums.RIGHT_TUPLE );
                        stream.writeInt( childLeftTuple.getRightParent().getFactHandle().getId() );
                    } else {
                        // this is a propagation record
//                        context.out.println( "RightTuple(propagation) int:" + childLeftTuple.getLeftTupleSink().getId() + " int:" + childLeftTuple.getRightParent().getFactHandle().getId() );
                        stream.writeShort( PersisterEnums.LEFT_TUPLE );
                        stream.writeInt( childLeftTuple.getLeftTupleSink().getId() );
                        writeLeftTuple( childLeftTuple,
                                        context,
                                        recurse );
                    }
                }
                stream.writeShort( PersisterEnums.END );
//                context.out.println( "CollectNode   ---   END" );
                break;
            }
            case NodeTypeEnums.RightInputAdaterNode : {
//                context.out.println( "RightInputAdapterNode" );
                // RIANs generate new fact handles on-demand to wrap tuples and need special procedures when serializing to persistent storage
                ObjectHashMap memory = (ObjectHashMap) context.wm.getNodeMemory( (NodeMemory) sink );
                InternalFactHandle ifh = (InternalFactHandle) memory.get( leftTuple );
                // first we serialize the generated fact handle ID
//                context.out.println( "FactHandle id:"+ifh.getId() );
                stream.writeInt( ifh.getId() );
                stream.writeLong( ifh.getRecency() );
                
                writeRightTuples( ifh, context );

                stream.writeShort( PersisterEnums.END );
//                context.out.println( "RightInputAdapterNode   ---   END" );
                break;
            }
            case NodeTypeEnums.RuleTerminalNode : {
//                context.out.println( "RuleTerminalNode" );
                int pos = context.terminalTupleMap.size();
                context.terminalTupleMap.put( leftTuple,
                                              pos );
                break;
            }
        }
    }

    public static LeftTuple getLeftTuple(LeftTuple leftTuple) {
        for ( LeftTuple tempLeftTuple = leftTuple; tempLeftTuple != null; tempLeftTuple = (LeftTuple) tempLeftTuple.getLeftParentNext() ) {
            leftTuple = tempLeftTuple;
        }
        return leftTuple;
    }

    public static void writeActivations(MarshallerWriteContext context) throws IOException {
        ObjectOutputStream stream = context.stream;

        Entry<LeftTuple, Integer>[] entries = context.terminalTupleMap.entrySet().toArray( new Entry[context.terminalTupleMap.size()] );
        Arrays.sort( entries,
                     TupleSorter.instance );

        //Map<LeftTuple, Integer> tuples = context.terminalTupleMap;
        if ( entries.length != 0 ) {
            for ( Entry<LeftTuple, Integer> entry : entries ) {
                if (entry.getKey().getActivation() != null) {
					LeftTuple leftTuple = entry.getKey();
					stream.writeShort(PersisterEnums.ACTIVATION);
					writeActivation(context, leftTuple, (AgendaItem) leftTuple
							.getActivation(), (RuleTerminalNode) leftTuple
							.getLeftTupleSink());
				}
            }
        }
        stream.writeShort( PersisterEnums.END );
    }

    public static class TupleSorter
        implements
        Comparator<Entry<LeftTuple, Integer>> {
        public static final TupleSorter instance = new TupleSorter();

        public int compare(Entry<LeftTuple, Integer> e1,
                           Entry<LeftTuple, Integer> e2) {
            return e1.getValue() - e2.getValue();
        }
    }

    public static void writeActivation(MarshallerWriteContext context,
                                       LeftTuple leftTuple,
                                       AgendaItem agendaItem,
                                       RuleTerminalNode ruleTerminalNode) throws IOException {
        ObjectOutputStream stream = context.stream;

        stream.writeLong( agendaItem.getActivationNumber() );

        stream.writeInt( context.terminalTupleMap.get( leftTuple ) );

        stream.writeInt( agendaItem.getSalience() );

        Rule rule = agendaItem.getRule();
        stream.writeUTF( rule.getPackage() );
        stream.writeUTF( rule.getName() );

//        context.out.println( "Rule " + rule.getPackage() + "." + rule.getName() );

//        context.out.println( "AgendaItem long:" + agendaItem.getPropagationContext().getPropagationNumber() );
        stream.writeLong( agendaItem.getPropagationContext().getPropagationNumber() );

        if ( agendaItem.getActivationGroupNode() != null ) {
            stream.writeBoolean( true );
//            context.out.println( "ActivationGroup bool:" + true );
            stream.writeUTF( agendaItem.getActivationGroupNode().getActivationGroup().getName() );
//            context.out.println( "ActivationGroup string:" + agendaItem.getActivationGroupNode().getActivationGroup().getName() );
        } else {
            stream.writeBoolean( false );
//            context.out.println( "ActivationGroup bool:" + false );
        }

        stream.writeBoolean( agendaItem.isActivated() );
//        context.out.println( "AgendaItem bool:" + agendaItem.isActivated() );

        org.drools.util.LinkedList list = agendaItem.getLogicalDependencies();
        if ( list != null && !list.isEmpty() ) {
            for ( LogicalDependency node = (LogicalDependency) list.getFirst(); node != null; node = (LogicalDependency) node.getNext() ) {
                stream.writeShort( PersisterEnums.LOGICAL_DEPENDENCY );
                stream.writeInt( ((InternalFactHandle) node.getFactHandle()).getId() );
//                context.out.println( "Logical Depenency : int " + ((InternalFactHandle) node.getFactHandle()).getId() );
            }
        }
        stream.writeShort( PersisterEnums.END );
    }

    public static void writePropagationContexts(MarshallerWriteContext context) throws IOException {
        ObjectOutputStream stream = context.stream;

        Entry<LeftTuple, Integer>[] entries = context.terminalTupleMap.entrySet().toArray( new Entry[context.terminalTupleMap.size()] );
        Arrays.sort( entries,
                     TupleSorter.instance );

        //Map<LeftTuple, Integer> tuples = context.terminalTupleMap;
        if ( entries.length != 0 ) {
            Map<Long, PropagationContext> pcMap = new HashMap<Long, PropagationContext>();
            for ( Entry<LeftTuple, Integer> entry : entries ) {
                LeftTuple leftTuple = entry.getKey();
                if (leftTuple.getActivation() != null) {
					PropagationContext pc = leftTuple.getActivation()
							.getPropagationContext();
					if (!pcMap.containsKey(pc.getPropagationNumber())) {
						stream.writeShort(PersisterEnums.PROPAGATION_CONTEXT);
						writePropagationContext(context, pc);
						pcMap.put(pc.getPropagationNumber(), pc);
					}
				}
            }
        }

        stream.writeShort( PersisterEnums.END );
    }

    public static void writePropagationContext(MarshallerWriteContext context,
                                               PropagationContext pc) throws IOException {
        ObjectOutputStream stream = context.stream;
        Map<LeftTuple, Integer> tuples = context.terminalTupleMap;

        stream.writeInt( pc.getType() );

        Rule ruleOrigin = pc.getRuleOrigin();
        if ( ruleOrigin != null ) {
            stream.writeBoolean( true );
            stream.writeUTF( ruleOrigin.getPackage() );
            stream.writeUTF( ruleOrigin.getName() );
        } else {
            stream.writeBoolean( false );
        }

        LeftTuple tupleOrigin = pc.getLeftTupleOrigin();
        if ( tupleOrigin != null && tuples.containsKey( tupleOrigin )) {
            stream.writeBoolean( true );
            stream.writeInt( tuples.get( tupleOrigin ) );
        } else {
            stream.writeBoolean( false );
        }

        stream.writeLong( pc.getPropagationNumber() );
        if ( pc.getFactHandleOrigin() != null ) {
            stream.writeInt( ((InternalFactHandle)pc.getFactHandleOrigin()).getId() );
        } else {
            stream.writeInt( -1 );
        }

        stream.writeInt( pc.getActiveActivations() );
        stream.writeInt( pc.getDormantActivations() );

        stream.writeUTF( pc.getEntryPoint().getEntryPointId() );
    }

    public static void writeProcessInstances(MarshallerWriteContext context) throws IOException {
        ObjectOutputStream stream = context.stream;
        List<org.drools.runtime.process.ProcessInstance> processInstances = new ArrayList<org.drools.runtime.process.ProcessInstance>( context.wm.getProcessInstances() );
        Collections.sort( processInstances,
                          new Comparator<org.drools.runtime.process.ProcessInstance>() {
                              public int compare(org.drools.runtime.process.ProcessInstance o1,
                            		  org.drools.runtime.process.ProcessInstance o2) {
                                  return (int) (o1.getId() - o2.getId());
                              }
                          } );
        for ( org.drools.runtime.process.ProcessInstance processInstance : processInstances ) {
            stream.writeShort( PersisterEnums.PROCESS_INSTANCE );
            writeProcessInstance( context,
                                  (RuleFlowProcessInstance) processInstance );
        }
        stream.writeShort( PersisterEnums.END );
    }

    public static void writeProcessInstance(MarshallerWriteContext context,
                                            RuleFlowProcessInstance processInstance) throws IOException {
        ObjectOutputStream stream = context.stream;
        stream.writeLong( processInstance.getId() );
        stream.writeUTF( processInstance.getProcessId() );
        stream.writeInt( processInstance.getState() );
        stream.writeLong( processInstance.getNodeInstanceCounter() );

        VariableScopeInstance variableScopeInstance = (VariableScopeInstance) processInstance.getContextInstance( VariableScope.VARIABLE_SCOPE );
        Map<String, Object> variables = variableScopeInstance.getVariables();
        List<String> keys = new ArrayList<String>( variables.keySet() );
        Collections.sort( keys,
                          new Comparator<String>() {
                              public int compare(String o1,
                                                 String o2) {
                                  return o1.compareTo( o2 );
                              }
                          } );
        stream.writeInt( keys.size() );
        for ( String key : keys ) {
            stream.writeUTF( key );
            stream.writeObject( variables.get( key ) );
        }

        SwimlaneContextInstance swimlaneContextInstance = (SwimlaneContextInstance) processInstance.getContextInstance( SwimlaneContext.SWIMLANE_SCOPE );
        Map<String, String> swimlaneActors = swimlaneContextInstance.getSwimlaneActors();
        stream.writeInt( swimlaneActors.size() );
        for ( Map.Entry<String, String> entry : swimlaneActors.entrySet() ) {
            stream.writeUTF( entry.getKey() );
            stream.writeUTF( entry.getValue() );
        }

        List<NodeInstance> nodeInstances = new ArrayList<NodeInstance>( processInstance.getNodeInstances() );
        Collections.sort( nodeInstances,
                          new Comparator<NodeInstance>() {
                              public int compare(NodeInstance o1,
                                                 NodeInstance o2) {
                                  return (int) (o1.getId() - o2.getId());
                              }
                          } );
        for ( NodeInstance nodeInstance : nodeInstances ) {
            stream.writeShort( PersisterEnums.NODE_INSTANCE );
            writeNodeInstance( context,
                               nodeInstance );
        }
        stream.writeShort( PersisterEnums.END );
    }

    public static void writeNodeInstance(MarshallerWriteContext context,
                                         NodeInstance nodeInstance) throws IOException {
        ObjectOutputStream stream = context.stream;
        stream.writeLong( nodeInstance.getId() );
        stream.writeLong( nodeInstance.getNodeId() );
        if ( nodeInstance instanceof RuleSetNodeInstance ) {
            stream.writeShort( PersisterEnums.RULE_SET_NODE_INSTANCE );
        } else if ( nodeInstance instanceof HumanTaskNodeInstance ) {
            stream.writeShort( PersisterEnums.HUMAN_TASK_NODE_INSTANCE );
            stream.writeLong( ((HumanTaskNodeInstance) nodeInstance).getWorkItem().getId() );
        } else if ( nodeInstance instanceof WorkItemNodeInstance ) {
            stream.writeShort( PersisterEnums.WORK_ITEM_NODE_INSTANCE );
            stream.writeLong( ((WorkItemNodeInstance) nodeInstance).getWorkItem().getId() );
        } else if ( nodeInstance instanceof SubProcessNodeInstance ) {
            stream.writeShort( PersisterEnums.SUB_PROCESS_NODE_INSTANCE );
            stream.writeLong( ((SubProcessNodeInstance) nodeInstance).getProcessInstanceId() );
        } else if ( nodeInstance instanceof MilestoneNodeInstance ) {
            stream.writeShort( PersisterEnums.MILESTONE_NODE_INSTANCE );
            List<Long> timerInstances = 
            	((MilestoneNodeInstance) nodeInstance).getTimerInstances();
            if (timerInstances != null) {
            	stream.writeInt(timerInstances.size());
            	for (Long id: timerInstances) {
            		stream.writeLong(id);
            	}
            } else {
            	stream.writeInt(0);
            }
        } else if ( nodeInstance instanceof TimerNodeInstance ) {
            stream.writeShort( PersisterEnums.TIMER_NODE_INSTANCE );
            stream.writeLong( ((TimerNodeInstance) nodeInstance).getTimerId() );
        } else if ( nodeInstance instanceof JoinInstance ) {
            stream.writeShort( PersisterEnums.JOIN_NODE_INSTANCE );
            Map<Long, Integer> triggers = ((JoinInstance) nodeInstance).getTriggers();
            stream.writeInt( triggers.size() );
            List<Long> keys = new ArrayList<Long>( triggers.keySet() );
            Collections.sort( keys,
                              new Comparator<Long>() {
                                  public int compare(Long o1,
                                                     Long o2) {
                                      return o1.compareTo( o2 );
                                  }
                              } );
            for ( Long key : keys ) {
                stream.writeLong( key );
                stream.writeInt( triggers.get( key ) );
            }
        } else if ( nodeInstance instanceof CompositeContextNodeInstance ) {
            stream.writeShort( PersisterEnums.COMPOSITE_NODE_INSTANCE );
            CompositeContextNodeInstance compositeNodeInstance = (CompositeContextNodeInstance) nodeInstance;
            VariableScopeInstance variableScopeInstance = (VariableScopeInstance) compositeNodeInstance.getContextInstance( VariableScope.VARIABLE_SCOPE );
            Map<String, Object> variables = variableScopeInstance.getVariables();
            List<String> keys = new ArrayList<String>( variables.keySet() );
            Collections.sort( keys,
                              new Comparator<String>() {
                                  public int compare(String o1,
                                                     String o2) {
                                      return o1.compareTo( o2 );
                                  }
                              } );
            stream.writeInt( keys.size() );
            for ( String key : keys ) {
                stream.writeUTF( key );
                stream.writeObject( variables.get( key ) );
            }
            List<NodeInstance> nodeInstances = new ArrayList<NodeInstance>( compositeNodeInstance.getNodeInstances() );
            Collections.sort( nodeInstances,
                              new Comparator<NodeInstance>() {
                                  public int compare(NodeInstance o1,
                                                     NodeInstance o2) {
                                      return (int) (o1.getId() - o2.getId());
                                  }
                              } );
            for ( NodeInstance subNodeInstance : nodeInstances ) {
                stream.writeShort( PersisterEnums.NODE_INSTANCE );
                writeNodeInstance( context,
                                   subNodeInstance );
            }
            stream.writeShort( PersisterEnums.END );
        } else if ( nodeInstance instanceof ForEachNodeInstance ) {
            stream.writeShort( PersisterEnums.FOR_EACH_NODE_INSTANCE );
            ForEachNodeInstance forEachNodeInstance = (ForEachNodeInstance) nodeInstance;
            List<NodeInstance> nodeInstances = new ArrayList<NodeInstance>( forEachNodeInstance.getNodeInstances() );
            Collections.sort( nodeInstances,
                              new Comparator<NodeInstance>() {
                                  public int compare(NodeInstance o1,
                                                     NodeInstance o2) {
                                      return (int) (o1.getId() - o2.getId());
                                  }
                              } );
            for ( NodeInstance subNodeInstance : nodeInstances ) {
                if ( subNodeInstance instanceof CompositeContextNodeInstance ) {
                    stream.writeShort( PersisterEnums.NODE_INSTANCE );
                    writeNodeInstance( context,
                                       subNodeInstance );
                }
            }
            stream.writeShort( PersisterEnums.END );
        } else {
            // TODO ForEachNodeInstance
            // TODO timer manager
            throw new IllegalArgumentException( "Unknown node instance type: " + nodeInstance );
        }
    }

    public static void writeWorkItems(MarshallerWriteContext context) throws IOException {
        ObjectOutputStream stream = context.stream;

        List<WorkItem> workItems = new ArrayList<WorkItem>(
    		((WorkItemManager) context.wm.getWorkItemManager()).getWorkItems() );
        Collections.sort( workItems,
                          new Comparator<WorkItem>() {
                              public int compare(WorkItem o1,
                                                 WorkItem o2) {
                                  return (int) (o2.getId() - o1.getId());
                              }
                          } );
        for ( WorkItem workItem : workItems ) {
            stream.writeShort( PersisterEnums.WORK_ITEM );
            writeWorkItem( context,
                           workItem );
        }
        stream.writeShort( PersisterEnums.END );
    }

    public static void writeWorkItem(MarshallerWriteContext context,
                                     WorkItem workItem) throws IOException {
        ObjectOutputStream stream = context.stream;
        stream.writeLong( workItem.getId() );
        stream.writeLong( workItem.getProcessInstanceId() );
        stream.writeUTF( workItem.getName() );
        stream.writeInt( workItem.getState() );
        Map<String, Object> parameters = workItem.getParameters();
        stream.writeInt( parameters.size() );
        for ( Map.Entry<String, Object> entry : parameters.entrySet() ) {
            stream.writeUTF( entry.getKey() );
            stream.writeObject( entry.getValue() );
        }
    }

    public static void writeTimers(MarshallerWriteContext context) throws IOException {
        ObjectOutputStream stream = context.stream;

        TimerManager timerManager = context.wm.getTimerManager();
        long timerId = timerManager.internalGetTimerId();
        stream.writeLong( timerId );
        
        // need to think on how to fix this
        // stream.writeObject( timerManager.getTimerService() );
        
        List<TimerInstance> timers = new ArrayList<TimerInstance>( timerManager.getTimers() );
        Collections.sort( timers,
                          new Comparator<TimerInstance>() {
                              public int compare(TimerInstance o1,
                                                 TimerInstance o2) {
                                  return (int) (o2.getId() - o1.getId());
                              }
                          } );
        for ( TimerInstance timer : timers ) {
            stream.writeShort( PersisterEnums.TIMER );
            writeTimer( context,
                        timer );
        }
        stream.writeShort( PersisterEnums.END );
    }

    public static void writeTimer(MarshallerWriteContext context,
                                  TimerInstance timer) throws IOException {
        ObjectOutputStream stream = context.stream;
        stream.writeLong( timer.getId() );
        stream.writeLong( timer.getTimerId() );
        stream.writeLong( timer.getDelay() );
        stream.writeLong( timer.getPeriod() );
        stream.writeLong( timer.getProcessInstanceId() );
        stream.writeLong( timer.getActivated().getTime() );
        Date lastTriggered = timer.getLastTriggered();
        if ( lastTriggered != null ) {
            stream.writeBoolean( true );
            stream.writeLong( timer.getLastTriggered().getTime() );
        } else {
            stream.writeBoolean( false );
        }
    }

}
