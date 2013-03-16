package org.drools.core.common;

import org.drools.core.FactException;
import org.drools.core.beliefsystem.BeliefSet;
import org.drools.core.util.LinkedList;
import org.drools.core.rule.Rule;
import org.drools.core.spi.Activation;
import org.drools.core.spi.PropagationContext;

public class TruthMaintenanceSystemHelper {

    public static void removeLogicalDependencies(final InternalFactHandle handle, final PropagationContext propagationContext ) throws FactException {
        final BeliefSet beliefSet = handle.getEqualityKey().getBeliefSet();
        if ( beliefSet != null && !beliefSet.isEmpty() ) {
            beliefSet.cancel(propagationContext);
        }
    }
    
    public static void clearLogicalDependencies(final InternalFactHandle handle, final PropagationContext propagationContext ) throws FactException {
        final BeliefSet beliefSet = handle.getEqualityKey().getBeliefSet();
        if ( beliefSet != null && !beliefSet.isEmpty() ) {
            beliefSet.clear(propagationContext);
        }
    }    
    
    
    public static void removeLogicalDependencies(final Activation activation,
                                                 final PropagationContext context,
                                                 final Rule rule) throws FactException {
        final LinkedList<LogicalDependency> list = activation.getLogicalDependencies();
        if ( list == null || list.isEmpty() ) {
            return;
        }

        for ( LogicalDependency node = list.getFirst(); node != null; node = node.getNext() ) {
            removeLogicalDependency( node, context );
        }
        activation.setLogicalDependencies( null );
    }

    public static void removeLogicalDependency(final LogicalDependency node,
                                               final PropagationContext context) {
        final BeliefSet beliefSet = ( BeliefSet ) node.getJustified();
        beliefSet.getBeliefSystem().delete( node, beliefSet, context );
    }
}
