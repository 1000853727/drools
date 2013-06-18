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

package org.drools.core.common;

import org.drools.core.conflict.PhreakConflictResolver;
import org.drools.core.conflict.SequentialConflictResolver;
import org.drools.core.impl.StatefulKnowledgeSessionImpl;
import org.drools.core.marshalling.impl.MarshallerReaderContext;
import org.drools.core.marshalling.impl.MarshallerWriteContext;
import org.drools.core.marshalling.impl.ProtobufMessages;
import org.drools.core.spi.Activation;
import org.drools.core.spi.PropagationContext;
import org.drools.core.util.BinaryHeapQueue;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * <code>AgendaGroup</code> implementation that uses a <code>PriorityQueue</code> to prioritise the evaluation of added
 * <code>ActivationQueue</code>s. The <code>AgendaGroup</code> also maintains a <code>Map</code> of <code>ActivationQueues</code>
 * for requested salience values.
 *
 * @see PriorityQueue
 * @see ActivationQueue
 */
public class AgendaGroupQueueImpl
        implements
        InternalAgendaGroup,
        InternalRuleFlowGroup {
    private static final long serialVersionUID = 510l;
    private          String             name;
    /**
     * Items in the agenda.
     */
    private          BinaryHeapQueue    priorityQueue;
    private volatile boolean            active;
    private          PropagationContext autoFocusActivator;
    private          long               activatedForRecency;
    private          long               clearedForRecency;

    private InternalWorkingMemory workingMemory;
    private boolean               autoDeactivate = true;
    private Map<Long, String>     nodeInstances  = new HashMap<Long, String>();

    /**
     * Construct an <code>AgendaGroup</code> with the given name.
     *
     * @param name The <AgendaGroup> name.
     */
    public AgendaGroupQueueImpl() {

    }

    public AgendaGroupQueueImpl(final String name,
                                final InternalRuleBase ruleBase) {
        this.name = name;
        if (ruleBase.getConfiguration().isPhreakEnabled()) {
            this.priorityQueue = new BinaryHeapQueue(new PhreakConflictResolver());
        }
        if (ruleBase.getConfiguration().isSequential()) {
            this.priorityQueue = new BinaryHeapQueue(new SequentialConflictResolver());
        } else {
            this.priorityQueue = new BinaryHeapQueue(ruleBase.getConfiguration().getConflictResolver());
        }

        this.clearedForRecency = -1;
    }

    public BinaryHeapQueue getBinaryHeapQueue() {
        return this.priorityQueue;
    }

    /* (non-Javadoc)
     * @see org.kie.spi.AgendaGroup#getName()
     */
    public String getName() {
        return this.name;
    }

    public void setWorkingMemory(InternalWorkingMemory workingMemory) {
        this.workingMemory = workingMemory;
    }

    public InternalWorkingMemory getWorkingMemory() {
        return workingMemory;
    }

    public void clear() {
        this.priorityQueue.clear();
//        this.active = false;
    }

    public Activation[] getAndClear() {
        return this.priorityQueue.getAndClear();
    }

    /* (non-Javadoc)
     * @see org.kie.spi.AgendaGroup#size()
     */
    public int size() {
        return this.priorityQueue.size();
    }

    public void add(final Activation activation) {
        this.priorityQueue.enqueue((Activation) activation);
    }

    public Activation remove() {
//        Activation act = this.priorityQueue.dequeue();
//        if ( this.priorityQueue.isEmpty() && autoDeactivate ) {
//            setActive( false );
//        }
        return (Activation) this.priorityQueue.dequeue();
    }

    public Activation peek() {
        return this.priorityQueue.peek();
    }

    public boolean isActive() {
        return this.active;
    }

    public void deactivateIfEmpty() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isAutoDeactivate() {
        return autoDeactivate;
    }

    public void setAutoDeactivate(boolean autoDeactivate) {
        this.autoDeactivate = autoDeactivate;
    }

    public void addNodeInstance(Long processInstanceId,
                                String nodeInstanceId) {
        nodeInstances.put( processInstanceId,
                           nodeInstanceId );
    }

    public void removeNodeInstance(Long processInstanceId,
                                   String nodeInstanceId) {
        nodeInstances.put( processInstanceId,
                           nodeInstanceId );
    }

    public void setActive(final boolean activate) {
        this.active = activate;
    }

    public PropagationContext getAutoFocusActivator() {
        return this.autoFocusActivator;
    }

    public void setAutoFocusActivator(PropagationContext autoFocusActivator) {
        this.autoFocusActivator = autoFocusActivator;
    }


    public boolean isEmpty() {
        return this.priorityQueue.isEmpty();
    }

    public Activation[] getActivations() {
        return (Activation[]) this.priorityQueue.toArray(new AgendaItem[this.priorityQueue.size()]);
    }

    @Override
    public Map<Long, String> getNodeInstances() {
        return nodeInstances;
    }

    public String toString() {
        return "AgendaGroup '" + this.name + "'";
    }

    public boolean equals(final Object object) {
        if ((object == null) || !(object instanceof AgendaGroupQueueImpl)) {
            return false;
        }

        if (((AgendaGroupQueueImpl) object).name.equals(this.name)) {
            return true;
        }

        return false;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public void setFocus() {
        throw new UnsupportedOperationException();
    }

    public void remove(final Activation activation) {
        this.priorityQueue.dequeue(activation.getQueueIndex());
    }

    public long getActivatedForRecency() {
        return this.activatedForRecency;
    }

    public void setActivatedForRecency(long recency) {
        this.activatedForRecency = recency;
    }

    public long getClearedForRecency() {
        return this.clearedForRecency;
    }

    public void setClearedForRecency(long recency) {
        this.clearedForRecency = recency;
    }

    public static class DeactivateCallback
            implements
            WorkingMemoryAction {

        private static final long     serialVersionUID = 510l;

        private InternalRuleFlowGroup ruleFlowGroup;

        public DeactivateCallback(InternalRuleFlowGroup ruleFlowGroup) {
            this.ruleFlowGroup = ruleFlowGroup;
        }

        public DeactivateCallback(MarshallerReaderContext context) throws IOException {
            this.ruleFlowGroup = (InternalRuleFlowGroup) context.wm.getAgenda().getRuleFlowGroup( context.readUTF() );
        }

        public DeactivateCallback(MarshallerReaderContext context,
                                  ProtobufMessages.ActionQueue.Action _action) {
            this.ruleFlowGroup = (InternalRuleFlowGroup) context.wm.getAgenda().getRuleFlowGroup( _action.getDeactivateCallback().getRuleflowGroup() );
        }

        public void write(MarshallerWriteContext context) throws IOException {
            context.writeShort( WorkingMemoryAction.DeactivateCallback );
            context.writeUTF( ruleFlowGroup.getName() );
        }

        public ProtobufMessages.ActionQueue.Action serialize(MarshallerWriteContext context) {
            return ProtobufMessages.ActionQueue.Action.newBuilder()
                                               .setType( ProtobufMessages.ActionQueue.ActionType.DEACTIVATE_CALLBACK )
                                               .setDeactivateCallback( ProtobufMessages.ActionQueue.DeactivateCallback.newBuilder()
                                                                                                   .setRuleflowGroup( ruleFlowGroup.getName() )
                                                                                                   .build() )
                                               .build();
        }

        public void readExternal(ObjectInput in) throws IOException,
                ClassNotFoundException {
            ruleFlowGroup = (InternalRuleFlowGroup) in.readObject();
        }

        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject( ruleFlowGroup );
        }

        public void execute(InternalWorkingMemory workingMemory) {
            // check whether ruleflow group is still empty first
            if ( this.ruleFlowGroup.isEmpty() ) {
                // deactivate ruleflow group
                this.ruleFlowGroup.setActive( false );
            }
        }
        public void execute(InternalKnowledgeRuntime kruntime) {
            execute(((StatefulKnowledgeSessionImpl) kruntime).getInternalWorkingMemory());
        }
    }

}
