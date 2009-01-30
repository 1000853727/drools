package org.drools.marshalling;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.drools.common.InternalRuleBase;
import org.drools.common.InternalWorkingMemory;
import org.drools.process.core.context.swimlane.SwimlaneContext;
import org.drools.process.core.context.variable.VariableScope;
import org.drools.process.instance.context.swimlane.SwimlaneContextInstance;
import org.drools.process.instance.context.variable.VariableScopeInstance;
import org.drools.process.instance.impl.ProcessInstanceImpl;
import org.drools.ruleflow.instance.RuleFlowProcessInstance;
import org.drools.runtime.process.NodeInstance;
import org.drools.runtime.process.NodeInstanceContainer;
import org.drools.runtime.process.ProcessInstance;
import org.drools.workflow.instance.WorkflowProcessInstance;
import org.drools.workflow.instance.impl.NodeInstanceImpl;
import org.drools.workflow.instance.impl.WorkflowProcessInstanceImpl;
import org.drools.workflow.instance.node.CompositeContextNodeInstance;
import org.drools.workflow.instance.node.ForEachNodeInstance;
import org.drools.workflow.instance.node.HumanTaskNodeInstance;
import org.drools.workflow.instance.node.JoinInstance;
import org.drools.workflow.instance.node.MilestoneNodeInstance;
import org.drools.workflow.instance.node.RuleSetNodeInstance;
import org.drools.workflow.instance.node.SubProcessNodeInstance;
import org.drools.workflow.instance.node.TimerNodeInstance;
import org.drools.workflow.instance.node.WorkItemNodeInstance;

/* Author: mfossati, salaboy */
public abstract class AbstractProcessInstanceMarshaller implements
		ProcessInstanceMarshaller {

	// Output methods

	public void writeProcessInstance(MarshallerWriteContext context,
			ProcessInstance processInstance) throws IOException {

		WorkflowProcessInstanceImpl workFlow = (WorkflowProcessInstanceImpl) processInstance;
		ObjectOutputStream stream = context.stream;
		stream.writeLong(workFlow.getId());
		stream.writeUTF(workFlow.getProcessId());
		stream.writeInt(workFlow.getState());
		stream.writeLong(workFlow.getNodeInstanceCounter());
		
		VariableScopeInstance variableScopeInstance = (VariableScopeInstance) workFlow.getContextInstance( VariableScope.VARIABLE_SCOPE );
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

        SwimlaneContextInstance swimlaneContextInstance = (SwimlaneContextInstance) workFlow.getContextInstance( SwimlaneContext.SWIMLANE_SCOPE );
        
        if (swimlaneContextInstance!=null) {
			Map<String, String> swimlaneActors = swimlaneContextInstance
					.getSwimlaneActors();
			stream.writeInt(swimlaneActors.size());
			for (Map.Entry<String, String> entry : swimlaneActors.entrySet()) {
				stream.writeUTF(entry.getKey());
				stream.writeUTF(entry.getValue());
			}
		}
		List<NodeInstance> nodeInstances = new ArrayList<NodeInstance>( workFlow.getNodeInstances() );
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

	public void writeNodeInstance(MarshallerWriteContext context,
			NodeInstance nodeInstance) throws IOException {
		ObjectOutputStream stream = context.stream;
		stream.writeLong(nodeInstance.getId());
		stream.writeLong(nodeInstance.getNodeId());
		writeNodeInstanceContent(stream, nodeInstance, context);
	}

	protected void writeNodeInstanceContent(ObjectOutputStream stream,
			NodeInstance nodeInstance, MarshallerWriteContext context)
			throws IOException {
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

	// Input methods

	public ProcessInstance readProcessInstance(MarshallerReaderContext context)
			throws IOException {
		ObjectInputStream stream = context.stream;
        InternalRuleBase ruleBase = context.ruleBase;
        InternalWorkingMemory wm = context.wm;

        RuleFlowProcessInstance processInstance = new RuleFlowProcessInstance();
        processInstance.setId( stream.readLong() );
        String processId = stream.readUTF();
        processInstance.setProcessId( processId );
        if ( ruleBase != null ) {
            processInstance.setProcess( ruleBase.getProcess( processId ) );
        }
        processInstance.setState( stream.readInt() );
        long nodeInstanceCounter = stream.readLong();
        processInstance.setWorkingMemory( wm );

        int nbVariables = stream.readInt();
        if ( nbVariables > 0 ) {
            VariableScopeInstance variableScopeInstance = (VariableScopeInstance) processInstance.getContextInstance( VariableScope.VARIABLE_SCOPE );
            for ( int i = 0; i < nbVariables; i++ ) {
                String name = stream.readUTF();
                try {
                    Object value = stream.readObject();
                    variableScopeInstance.setVariable( name,
                                                       value );
                } catch ( ClassNotFoundException e ) {
                    throw new IllegalArgumentException( "Could not reload variable " + name );
                }
            }
        }

        int nbSwimlanes = stream.readInt();
        if ( nbSwimlanes > 0 ) {
            SwimlaneContextInstance swimlaneContextInstance = (SwimlaneContextInstance) processInstance.getContextInstance( SwimlaneContext.SWIMLANE_SCOPE );
            for ( int i = 0; i < nbSwimlanes; i++ ) {
                String name = stream.readUTF();
                String value = stream.readUTF();
                swimlaneContextInstance.setActorId( name,
                                                    value );
            }
        }

        while ( stream.readShort() == PersisterEnums.NODE_INSTANCE ) {
            readNodeInstance( context,
                              processInstance,
                              processInstance );
        }

        processInstance.internalSetNodeInstanceCounter( nodeInstanceCounter );
        if ( wm != null ) {
            processInstance.reconnect();
        }
        return processInstance;
	}

	public NodeInstance readNodeInstance(MarshallerReaderContext context,
			NodeInstanceContainer nodeInstanceContainer,
			WorkflowProcessInstance processInstance) throws IOException {
		ObjectInputStream stream = context.stream;
		long id = stream.readLong();
		long nodeId = stream.readLong();
		int nodeType = stream.readShort();
		NodeInstanceImpl nodeInstance = readNodeInstanceContent(nodeType,
				stream, context, processInstance);

		nodeInstance.setNodeId(nodeId);
		nodeInstance.setNodeInstanceContainer(nodeInstanceContainer);
		nodeInstance.setProcessInstance(processInstance);
		nodeInstance.setId(id);

		return nodeInstance;
	}

	protected NodeInstanceImpl readNodeInstanceContent(int nodeType,
			ObjectInputStream stream, MarshallerReaderContext context,
			WorkflowProcessInstance processInstance) throws IOException {
		NodeInstanceImpl nodeInstance = null;
		switch ( nodeType ) {
        case PersisterEnums.RULE_SET_NODE_INSTANCE :
            nodeInstance = new RuleSetNodeInstance();
            break;
        case PersisterEnums.HUMAN_TASK_NODE_INSTANCE :
            nodeInstance = new HumanTaskNodeInstance();
            ((HumanTaskNodeInstance) nodeInstance).internalSetWorkItemId( stream.readLong() );
            break;
        case PersisterEnums.WORK_ITEM_NODE_INSTANCE :
            nodeInstance = new WorkItemNodeInstance();
            ((WorkItemNodeInstance) nodeInstance).internalSetWorkItemId( stream.readLong() );
            break;
        case PersisterEnums.SUB_PROCESS_NODE_INSTANCE :
            nodeInstance = new SubProcessNodeInstance();
            ((SubProcessNodeInstance) nodeInstance).internalSetProcessInstanceId( stream.readLong() );
            break;
        case PersisterEnums.MILESTONE_NODE_INSTANCE :
            nodeInstance = new MilestoneNodeInstance();
            int nbTimerInstances = stream.readInt();
            if (nbTimerInstances > 0) {
            	List<Long> timerInstances = new ArrayList<Long>();
            	for (int i = 0; i < nbTimerInstances; i++) {
            		timerInstances.add(stream.readLong());
            	}
            	((MilestoneNodeInstance) nodeInstance).internalSetTimerInstances(timerInstances);
            }
            break;
        case PersisterEnums.TIMER_NODE_INSTANCE :
            nodeInstance = new TimerNodeInstance();
            ((TimerNodeInstance) nodeInstance).internalSetTimerId( stream.readLong() );
            break;
        case PersisterEnums.JOIN_NODE_INSTANCE :
            nodeInstance = new JoinInstance();
            int number = stream.readInt();
            if ( number > 0 ) {
                Map<Long, Integer> triggers = new HashMap<Long, Integer>();
                for ( int i = 0; i < number; i++ ) {
                    long l = stream.readLong();
                    int count = stream.readInt();
                    triggers.put( l,
                                  count );
                }
                ((JoinInstance) nodeInstance).internalSetTriggers( triggers );
            }
            break;
        case PersisterEnums.COMPOSITE_NODE_INSTANCE :
            nodeInstance = new CompositeContextNodeInstance();
            break;
        case PersisterEnums.FOR_EACH_NODE_INSTANCE :
            nodeInstance = new ForEachNodeInstance();
            break;
        default :
            throw new IllegalArgumentException( "Unknown node type: " + nodeType );
		}
		return nodeInstance;

	}
}
