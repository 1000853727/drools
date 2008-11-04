package org.drools.util;

import java.util.Collection;

import org.drools.common.ActivationGroupNode;
import org.drools.common.LogicalDependency;
import org.drools.common.RuleFlowGroupNode;
import org.drools.rule.GroupElement;
import org.drools.rule.Rule;
import org.drools.FactHandle;
import org.drools.spi.AgendaGroup;
import org.drools.spi.PropagationContext;
import org.drools.spi.Tuple;

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

public class BaseQueueable
    implements
    Queueable {
    private Queue queue;
    private int   index;

    public void enqueued(final Queue queue,
                         final int index) {
        this.queue = queue;
        this.index = index;
    }

    public void dequeue() {
        this.queue.dequeue( this.index );
    }

    public void addLogicalDependency(LogicalDependency node) {
        // TODO Auto-generated method stub
        
    }

    public ActivationGroupNode getActivationGroupNode() {
        // TODO Auto-generated method stub
        return null;
    }

    public long getActivationNumber() {
        // TODO Auto-generated method stub
        return 0;
    }

    public AgendaGroup getAgendaGroup() {
        // TODO Auto-generated method stub
        return null;
    }

    public LinkedList getLogicalDependencies() {
        // TODO Auto-generated method stub
        return null;
    }

    public PropagationContext getPropagationContext() {
        // TODO Auto-generated method stub
        return null;
    }

    public Rule getRule() {
        // TODO Auto-generated method stub
        return null;
    }

    public RuleFlowGroupNode getRuleFlowGroupNode() {
        // TODO Auto-generated method stub
        return null;
    }

    public int getSalience() {
        // TODO Auto-generated method stub
        return 0;
    }

    public GroupElement getSubRule() {
        // TODO Auto-generated method stub
        return null;
    }

    public Tuple getTuple() {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean isActivated() {
        // TODO Auto-generated method stub
        return false;
    }

    public void remove() {
        // TODO Auto-generated method stub
        
    }

    public void setActivated(boolean activated) {
        // TODO Auto-generated method stub
        
    }

    public void setActivationGroupNode(ActivationGroupNode activationGroupNode) {
        // TODO Auto-generated method stub
        
    }

    public void setLogicalDependencies(LinkedList justified) {
        // TODO Auto-generated method stub
        
    }

    public void setRuleFlowGroupNode(RuleFlowGroupNode ruleFlowGroupNode) {
        // TODO Auto-generated method stub
        
    }

    public Collection<FactHandle> getFactHandles() {
        // TODO Auto-generated method stub
        return null;
    }
}