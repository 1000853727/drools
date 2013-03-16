package org.drools.core.beliefsystem;

import org.drools.core.common.InternalFactHandle;
import org.drools.core.common.LogicalDependency;
import org.drools.core.common.TruthMaintenanceSystem;
import org.drools.core.reteoo.ObjectTypeConf;
import org.drools.core.spi.Activation;
import org.drools.core.spi.PropagationContext;

public interface BeliefSystem {
    
    /**
     * TypeConf is already available, so we pass it, to avoid additional lookups
     * @param node
     * @param beliefSet
     * @param context
     * @param typeConf
     */
    public void insert(LogicalDependency node, 
                       BeliefSet beliefSet,
                       PropagationContext context,
                       ObjectTypeConf typeConf);
    
    /**
     * The typeConf has not yet been looked up, so we leave it to the implementation to decide if it needs it or not.
     * @param node
     * @param beliefSet
     * @param context
     */
    public void delete(LogicalDependency node, 
                       BeliefSet beliefSet,
                       PropagationContext context);
    
    public BeliefSet newBeliefSet(InternalFactHandle fh);
    
    public LogicalDependency newLogicalDependency(final Activation activation,
                                                  final BeliefSet beliefSet,
                                                  final Object object, 
                                                  final Object value);

    public void read(LogicalDependency node,
                     BeliefSet beliefSet,
                     PropagationContext context,
                     ObjectTypeConf typeConf);
    
    public TruthMaintenanceSystem getTruthMaintenanceSystem();
}
