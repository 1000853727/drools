/*
 * Copyright 2015 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/

package org.drools.core.phreak;

import org.drools.core.common.BetaConstraints;
import org.drools.core.common.InternalFactHandle;
import org.drools.core.common.InternalWorkingMemory;
import org.drools.core.common.InternalWorkingMemoryEntryPoint;
import org.drools.core.reteoo.LeftTuple;
import org.drools.core.reteoo.LeftTupleSinkNode;
import org.drools.core.reteoo.ReactiveFromNode;
import org.drools.core.reteoo.RightTuple;
import org.drools.core.rule.ContextEntry;
import org.drools.core.spi.PropagationContext;

import java.util.ArrayList;
import java.util.List;

import static org.drools.core.phreak.PhreakFromNode.deleteChildLeftTuple;
import static org.drools.core.phreak.PhreakFromNode.isAllowed;
import static org.drools.core.phreak.PhreakFromNode.propagate;

public abstract class ReactiveObject {
    private final List<LeftTuple> lts = new ArrayList<LeftTuple>();

    public void addLeftTuple(LeftTuple leftTuple) {
        lts.add(leftTuple);
    }

    protected void notifyModification() {
        notifyModification(this);
    }

    protected void notifyModification(Object object) {
        for (LeftTuple leftTuple : lts) {
            PropagationContext propagationContext = leftTuple.getPropagationContext();
            ReactiveFromNode node = (ReactiveFromNode)leftTuple.getSink();

            LeftTupleSinkNode sink = node.getSinkPropagator().getFirstLeftTupleSink();
            InternalWorkingMemory wm = getInternalWorkingMemory(propagationContext);

            wm.addPropagation(new ReactivePropagation(object, leftTuple, propagationContext, node, sink));
        }
    }

    private InternalWorkingMemory getInternalWorkingMemory(PropagationContext propagationContext) {
        InternalFactHandle fh = (InternalFactHandle) propagationContext.getFactHandleOrigin();
        return ((InternalWorkingMemoryEntryPoint) fh.getEntryPoint()).getInternalWorkingMemory();
    }

    class ReactivePropagation extends PropagationEntry.AbstractPropagationEntry {

        private final Object object;
        private final LeftTuple leftTuple;
        private final PropagationContext propagationContext;
        private final ReactiveFromNode node;
        private final LeftTupleSinkNode sink;

        ReactivePropagation( Object object, LeftTuple leftTuple, PropagationContext propagationContext, ReactiveFromNode node, LeftTupleSinkNode sink ) {
            this.object = object;
            this.leftTuple = leftTuple;
            this.propagationContext = propagationContext;
            this.node = node;
            this.sink = sink;
        }

        @Override
        public void execute( InternalWorkingMemory wm ) {
            ReactiveFromNode.ReactiveFromMemory mem = wm.getNodeMemory(node);
            InternalFactHandle factHandle = node.createFactHandle( leftTuple, propagationContext, wm, object );

            if ( isAllowed( factHandle, node.getAlphaConstraints(), wm, mem ) ) {
                ContextEntry[] context = mem.getBetaMemory().getContext();
                BetaConstraints betaConstraints = node.getBetaConstraints();
                betaConstraints.updateFromTuple( context,
                                                 wm,
                                                 leftTuple );

                propagate( sink,
                           leftTuple,
                           new RightTuple( factHandle ),
                           betaConstraints,
                           propagationContext,
                           context,
                           RuleNetworkEvaluator.useLeftMemory( node, leftTuple ),
                           mem.getStagedLeftTuples(),
                           null );
            } else {
                LeftTuple childLeftTuple = leftTuple.getFirstChild();
                while (childLeftTuple != null) {
                    LeftTuple next = childLeftTuple.getLeftParentNext();
                    if ( object == childLeftTuple.getHandle().getObject() ) {
                        deleteChildLeftTuple( propagationContext, mem.getStagedLeftTuples(), null, childLeftTuple );
                    }
                    childLeftTuple = next;
                }
            }

            mem.getBetaMemory().setNodeDirty(wm);
        }
    }
}
