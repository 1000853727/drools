package org.drools.util.debug;

import java.util.Stack;

import org.drools.common.NetworkNode;
import org.drools.reteoo.RuleTerminalNode;
import org.drools.reteoo.RuleTerminalNode.TerminalNodeMemory;
import org.drools.rule.Rule;

public class RuleTerminalNodeVisitor extends AbstractNetworkNodeVisitor {
    
    public static final RuleTerminalNodeVisitor INSTANCE = new RuleTerminalNodeVisitor();
    
    protected RuleTerminalNodeVisitor() {
    }

    @Override
    protected void doVisit(NetworkNode node,
                           Stack<NetworkNode> nodeStack,
                           StatefulKnowledgeSessionInfo info) {
        RuleTerminalNode rtn = (RuleTerminalNode) node;
        Rule rule = rtn.getRule();
        // first thing, associate all nodes belonging to this rule
        for( NetworkNode snode : nodeStack ) {
            info.assign( snode, rule );
        }

        final TerminalNodeMemory memory = (TerminalNodeMemory) info.getSession().getNodeMemory( rtn );
        final DefaultNodeInfo ni = (DefaultNodeInfo) info.getNodeInfo( node );
        ni.setMemoryEnabled( true );
        ni.setTupleMemorySize( memory.getTupleMemory().size() );

    }

}
