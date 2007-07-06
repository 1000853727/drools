package org.drools.ruleflow.instance.impl;

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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.drools.ruleflow.core.Connection;
import org.drools.ruleflow.core.Join;
import org.drools.ruleflow.core.Node;
import org.drools.ruleflow.instance.RuleFlowNodeInstance;

/**
 * Runtime counterpart of a join node.
 * 
 * @author <a href="mailto:kris_verlaenen@hotmail.com">Kris Verlaenen</a>
 */
public class RuleFlowJoinInstanceImpl extends RuleFlowNodeInstanceImpl
    implements
    RuleFlowNodeInstance {

    private final Map triggers = new HashMap();

    protected Join getJoinNode() {
        return (Join) getNode();
    }

    public void trigger(final RuleFlowNodeInstance from) {
        final Join join = getJoinNode();
        switch ( join.getType() ) {
            case Join.TYPE_XOR :
                triggerCompleted();
                break;
            case Join.TYPE_AND :
                final Node node = getProcessInstance().getRuleFlowProcess().getNode( from.getNodeId() );
                final Integer count = (Integer) this.triggers.get( node );
                if ( count == null ) {
                    this.triggers.put( node,
                                       new Integer( 1 ) );
                } else {
                    this.triggers.put( node,
                                       new Integer( count.intValue() + 1 ) );
                }
                checkActivation();
                break;
            default :
                throw new IllegalArgumentException( "Illegal join type " + join.getType() );
        }
    }

    private void checkActivation() {
        // check whether all parent nodes have been triggered 
        for ( final Iterator it = getJoinNode().getIncomingConnections().iterator(); it.hasNext(); ) {
            final Connection connection = (Connection) it.next();
            if ( this.triggers.get( connection.getFrom() ) == null ) {
                return;
            }
        }
        // if true, decrease trigger count for all parents and trigger children
        for ( final Iterator it = getJoinNode().getIncomingConnections().iterator(); it.hasNext(); ) {
            final Connection connection = (Connection) it.next();
            final Integer count = (Integer) this.triggers.get( connection.getFrom() );
            if ( count.intValue() == 1 ) {
                this.triggers.remove( connection.getFrom() );
            } else {
                this.triggers.put( connection.getFrom(),
                                   new Integer( count.intValue() - 1 ) );
            }
        }
        triggerCompleted();
    }

    public void triggerCompleted() {
        getProcessInstance().getNodeInstance( getJoinNode().getTo().getTo() ).trigger( this );
    }
    
}
