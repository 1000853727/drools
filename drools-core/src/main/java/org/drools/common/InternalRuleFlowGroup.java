package org.drools.common;

import java.util.Map;

import org.drools.spi.Activation;
import org.drools.spi.RuleFlowGroup;

public interface InternalRuleFlowGroup extends RuleFlowGroup {

	void setWorkingMemory(InternalWorkingMemory workingMemory);
	
	InternalWorkingMemory getWorkingMemory();
	
    void addActivation(Activation activation);

    void removeActivation(final Activation activation);
    
    void clear();

    /**
     * Checks if this ruleflow group is active and should automatically deactivate.
     * If the queue is empty, it deactivates the group.
     */
    public void deactivateIfEmpty();

    /**
     * Activates or deactivates this <code>RuleFlowGroup</code>.
     * When activating, all activations of this <code>RuleFlowGroup</code> are added
     * to the agenda.
     * As long as the <code>RuleFlowGroup</code> remains active,
     * its activations are automatically added to the agenda. 
     * When deactivating, all activations of this <code>RuleFlowGroup</code> are removed
     * to the agenda.
     * As long as the <code>RuleFlowGroup</code> remains deactive,
     * its activations are not added to the agenda. 
     */
    void setActive(boolean active);

    boolean isActive();

    void addRuleFlowGroupListener(RuleFlowGroupListener listener);
    
    void removeRuleFlowGroupListener(RuleFlowGroupListener listener);
    
    void addNodeInstance(Long processInstanceId, String nodeInstanceId);

    void removeNodeInstance(Long processInstanceId, String nodeInstanceId);
    
    Map<Long, String> getNodeInstances();
    
}
