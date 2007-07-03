package org.drools.ruleflow.core.impl;

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

import java.util.List;

import org.drools.ruleflow.core.Connection;
import org.drools.ruleflow.core.StartNode;

/**
 * Default implementation of a start node.
 * 
 * @author <a href="mailto:kris_verlaenen@hotmail.com">Kris Verlaenen</a>
 */
public class StartNodeImpl extends NodeImpl
    implements
    StartNode {

    private static final long serialVersionUID = 400L;

    public Connection getTo() {
        final List list = getOutgoingConnections();
        if ( list.size() > 0 ) {
            return (Connection) list.get( 0 );
        }
        return null;
    }

    protected void validateAddOutgoingConnection(final Connection connection) {
        super.validateAddOutgoingConnection( connection );
        if ( getOutgoingConnections().size() > 0 ) {
            throw new IllegalArgumentException( "A start node cannot have more than one outgoing connection" );
        }
    }

    protected void validateAddIncomingConnection(final Connection connection) {
        throw new UnsupportedOperationException( "A start node does not have an incoming connection" );
    }

    protected void validateRemoveIncomingConnection(final Connection connection) {
        throw new UnsupportedOperationException( "A start node does not have an incoming connection" );
    }

}
