package org.drools.reteoo;

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

import java.io.Serializable;
import java.io.Externalizable;
import java.io.ObjectOutput;
import java.io.IOException;
import java.io.ObjectInput;

import org.drools.RuleBaseConfiguration;
import org.drools.common.BaseNode;
import org.drools.common.InternalFactHandle;
import org.drools.common.InternalWorkingMemory;
import org.drools.common.NodeMemory;
import org.drools.common.PropagationContextImpl;
import org.drools.reteoo.builder.BuildContext;
import org.drools.rule.Declaration;
import org.drools.rule.EntryPoint;
import org.drools.rule.EvalCondition;
import org.drools.spi.Constraint;
import org.drools.spi.ObjectType;
import org.drools.spi.PropagationContext;
import org.drools.util.FactEntry;
import org.drools.util.RightTupleList;
import org.drools.util.Iterator;
import org.drools.util.ObjectHashSet;
import org.drools.util.ObjectHashSet.ObjectEntry;

/**
 * <code>ObjectTypeNodes<code> are responsible for filtering and propagating the matching
 * fact assertions propagated from the <code>Rete</code> node using <code>ObjectType</code> interface.
 * <p>
 * The assert and retract methods do not attempt to filter as this is the role of the <code>Rete</code>
 * node which builds up a cache of matching <code>ObjectTypdeNodes</code>s for each asserted object, using
 * the <code>matches(Object object)</code> method. Incorrect propagation in these methods is not checked and
 * will result in <code>ClassCastExpcections</code> later on in the network.
 * <p>
 * Filters <code>Objects</code> coming from the <code>Rete</code> using a
 * <code>ObjectType</code> semantic module.
 *
 *
 * @see ObjectType
 * @see Rete
 *
 * @author <a href="mailto:mark.proctor@jboss.com">Mark Proctor</a>
 * @author <a href="mailto:bob@werken.com">Bob McWhirter</a>
 */
public class ObjectTypeNode extends ObjectSource
    implements
    ObjectSink,
    Externalizable,
    NodeMemory

{
    // ------------------------------------------------------------
    // Instance members
    // ------------------------------------------------------------

    /**
     *
     */
    private static final long serialVersionUID = 400L;

    /** The <code>ObjectType</code> semantic module. */
    private ObjectType        objectType;

    private boolean           skipOnModify     = false;

    private boolean           objectMemoryEnabled;

    public ObjectTypeNode() {

    }

    /**
     * Construct given a semantic <code>ObjectType</code> and the provided
     * unique id. All <code>ObjectTypdeNode</code> have node memory.
     *
     * @param id
     *          The unique id for the node.
     * @param objectType
     *           The semantic object-type differentiator.
     */
    public ObjectTypeNode(final int id,
                          final EntryPointNode source,
                          final ObjectType objectType,
                          final BuildContext context) {
        super( id,
               source,
               context.getRuleBase().getConfiguration().getAlphaNodeHashingThreshold() );
        this.objectType = objectType;
        setObjectMemoryEnabled( context.isObjectTypeNodeMemoryEnabled() );
    }

    public void readExternal(ObjectInput in) throws IOException,
                                            ClassNotFoundException {
        super.readExternal( in );
        objectType = (ObjectType) in.readObject();
        skipOnModify = in.readBoolean();
        objectMemoryEnabled = in.readBoolean();
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal( out );
        out.writeObject( objectType );
        out.writeBoolean( skipOnModify );
        out.writeBoolean( objectMemoryEnabled );
    }

    /**
     * Retrieve the semantic <code>ObjectType</code> differentiator.
     *
     * @return
     *      The semantic <code>ObjectType</code> differentiator.
     */
    public ObjectType getObjectType() {
        return this.objectType;
    }

    /**
     * Tests the provided object to see if this <code>ObjectTypeNode</code> can receive the object
     * for assertion and retraction propagations.
     *
     * @param object
     * @return
     *      boolean value indicating whether the <code>ObjectTypeNode</code> can receive the object.
     */
    public boolean matches(final Object object) {
        return this.objectType.matches( object );
    }

    public boolean isAssignableFrom(final Object object) {
        return this.objectType.isAssignableFrom( object );
    }

    /**
     * Propagate the <code>FactHandleimpl</code> through the <code>Rete</code> network. All
     * <code>FactHandleImpl</code> should be remembered in the node memory, so that later runtime rule attachmnents
     * can have the matched facts propagated to them.
     *
     * @param factHandle
     *            The fact handle.
     * @param object
     *            The object to assert.
     * @param workingMemory
     *            The working memory session.
     */
    public void assertObject(final InternalFactHandle factHandle,
                             final PropagationContext context,
                             final InternalWorkingMemory workingMemory) {
        if ( context.getType() == PropagationContext.MODIFICATION && this.skipOnModify && context.getDormantActivations() == 0 ) {
            // we do this after the shadowproxy update, just so that its up to date for the future
            return;
        }

        if ( this.objectMemoryEnabled ) {
            final ObjectHashSet memory = (ObjectHashSet) workingMemory.getNodeMemory( this );
            memory.add( factHandle,
                        false );
        }

        this.sink.propagateAssertObject( factHandle,
                                         context,
                                         workingMemory );
    }

    /**
     * Retract the <code>FactHandleimpl</code> from the <code>Rete</code> network. Also remove the 
     * <code>FactHandleImpl</code> from the node memory.
     * 
     * @param rightTuple
     *            The fact handle.
     * @param object
     *            The object to assert.
     * @param workingMemory
     *            The working memory session.
     */
    public void retractObject(final InternalFactHandle factHandle,
                              final PropagationContext context,
                              final InternalWorkingMemory workingMemory) {

        if ( context.getType() == PropagationContext.MODIFICATION && this.skipOnModify && context.getDormantActivations() == 0 ) {
            return;
        }

        if ( this.objectMemoryEnabled ) {
            final ObjectHashSet memory = (ObjectHashSet) workingMemory.getNodeMemory( this );
            memory.remove( factHandle );
        }

        for ( RightTuple rightTuple = factHandle.getRightTuple(); rightTuple != null; rightTuple = (RightTuple) rightTuple.getHandleNext() ) {
            rightTuple.getRightTupleSink().retractRightTuple( rightTuple,
                                                              context,
                                                              workingMemory );
        }
        factHandle.setRightTuple( null );

        for ( LeftTuple leftTuple = factHandle.getLeftTuple(); leftTuple != null; leftTuple = (LeftTuple) leftTuple.getLeftParentNext() ) {
            leftTuple.getLeftTupleSink().retractLeftTuple( leftTuple,
                                                           context,
                                                           workingMemory );
        }
        factHandle.setLeftTuple( null );
    }

    public void updateSink(final ObjectSink sink,
                           final PropagationContext context,
                           final InternalWorkingMemory workingMemory) {
        final ObjectHashSet memory = (ObjectHashSet) workingMemory.getNodeMemory( this );
        Iterator it = memory.iterator();
        for ( ObjectEntry entry = (ObjectEntry) it.next(); entry != null; entry = (ObjectEntry) it.next() ) {
            sink.assertObject( (InternalFactHandle) entry.getValue(),
                               context,
                               workingMemory );
        }
    }

    /**
     * Rete needs to know that this ObjectTypeNode has been added
     */
    public void attach() {
        this.source.addObjectSink( this );
    }

    public void attach(final InternalWorkingMemory[] workingMemories) {
        attach();

        // we need to call updateSink on Rete, because someone
        // might have already added facts matching this ObjectTypeNode
        // to working memories
        for ( int i = 0, length = workingMemories.length; i < length; i++ ) {
            final InternalWorkingMemory workingMemory = workingMemories[i];
            final PropagationContextImpl propagationContext = new PropagationContextImpl( workingMemory.getNextPropagationIdCounter(),
                                                                                          PropagationContext.RULE_ADDITION,
                                                                                          null,
                                                                                          null,
                                                                                          null );
            propagationContext.setEntryPoint( ((EntryPointNode) this.source).getEntryPoint() );
            this.source.updateSink( this,
                                    propagationContext,
                                    workingMemory );
        }
    }

    public void networkUpdated() {
        this.skipOnModify = canSkipOnModify( this.sink.getSinks() );
    }

    /**
     * OTN needs to override remove to avoid releasing the node ID, since OTN are
     * never removed from the rulebase in the current implementation
     *
     * @inheritDoc
     *
     * @see org.drools.common.BaseNode#remove(org.drools.reteoo.RuleRemovalContext, org.drools.reteoo.ReteooBuilder, org.drools.common.BaseNode, org.drools.common.InternalWorkingMemory[])
     */
    public void remove(RuleRemovalContext context,
                       ReteooBuilder builder,
                       BaseNode node,
                       InternalWorkingMemory[] workingMemories) {
        doRemove( context,
                  builder,
                  node,
                  workingMemories );
    }

    protected void doRemove(final RuleRemovalContext context,
                            final ReteooBuilder builder,
                            final BaseNode node,
                            final InternalWorkingMemory[] workingMemories) {
        if ( !node.isInUse() ) {
            removeObjectSink( (ObjectSink) node );
        }
    }

    /**
     * Creates memory for the node using PrimitiveLongMap as its optimised for storage and reteivals of Longs.
     * However PrimitiveLongMap is not ideal for spase data. So it should be monitored incase its more optimal
     * to switch back to a standard HashMap.
     */
    public Object createMemory(final RuleBaseConfiguration config) {
        return new ObjectHashSet();
    }

    public boolean isObjectMemoryEnabled() {
        return this.objectMemoryEnabled;
    }

    public void setObjectMemoryEnabled(boolean objectMemoryEnabled) {
        this.objectMemoryEnabled = objectMemoryEnabled;
    }

    public String toString() {
        return "[ObjectTypeNode(" + this.id + ")::" + ((EntryPointNode) this.source).getEntryPoint() + " objectType=" + this.objectType + "]";
    }

    /**
     * Uses he hashCode() of the underlying ObjectType implementation.
     */
    public int hashCode() {
        return this.objectType.hashCode() ^ this.source.hashCode();
    }

    public boolean equals(final Object object) {
        if ( this == object ) {
            return true;
        }

        if ( object == null || !(object instanceof ObjectTypeNode) ) {
            return false;
        }

        final ObjectTypeNode other = (ObjectTypeNode) object;

        return this.objectType.equals( other.objectType ) && this.source.equals( other.source );
    }

    /**
     * @inheritDoc
     */
    protected void addObjectSink(final ObjectSink objectSink) {
        super.addObjectSink( objectSink );
    }

    /**
     * @inheritDoc
     */
    protected void removeObjectSink(final ObjectSink objectSink) {
        super.removeObjectSink( objectSink );
    }

    /**
     * Checks if a modify action on this object type may
     * be skipped because no constraint is applied to it
     *
     * @param sinks
     * @return
     */
    private boolean canSkipOnModify(final Sink[] sinks) {
        // If we have no alpha or beta node with constraints on this ObjectType, we can just skip modifies
        boolean hasConstraints = false;
        for ( int i = 0; i < sinks.length && !hasConstraints; i++ ) {
            if ( sinks[i] instanceof AlphaNode || sinks[i] instanceof AccumulateNode || sinks[i] instanceof CollectNode || sinks[i] instanceof FromNode ) {
                hasConstraints = true;
            } else if ( sinks[i] instanceof BetaNode && ((BetaNode) sinks[i]).getConstraints().length > 0 ) {
                hasConstraints = this.usesDeclaration( ((BetaNode) sinks[i]).getConstraints() );
            } else if ( sinks[i] instanceof EvalConditionNode ) {
                hasConstraints = this.usesDeclaration( ((EvalConditionNode) sinks[i]).getCondition() );
            }
            if ( !hasConstraints && sinks[i] instanceof ObjectSource ) {
                hasConstraints = !this.canSkipOnModify( ((ObjectSource) sinks[i]).getSinkPropagator().getSinks() );
            } else if ( !hasConstraints && sinks[i] instanceof LeftTupleSource ) {
                hasConstraints = !this.canSkipOnModify( ((LeftTupleSource) sinks[i]).getSinkPropagator().getSinks() );
            }
        }

        // Can only skip if we have no constraints
        return !hasConstraints;
    }

    private boolean usesDeclaration(final Constraint[] constraints) {
        boolean usesDecl = false;
        for ( int i = 0; !usesDecl && i < constraints.length; i++ ) {
            usesDecl = this.usesDeclaration( constraints[i] );
        }
        return usesDecl;
    }

    private boolean usesDeclaration(final Constraint constraint) {
        boolean usesDecl = false;
        final Declaration[] declarations = constraint.getRequiredDeclarations();
        for ( int j = 0; !usesDecl && j < declarations.length; j++ ) {
            usesDecl = (declarations[j].getPattern().getObjectType() == this.objectType);
        }
        return usesDecl;
    }

    private boolean usesDeclaration(final EvalCondition condition) {
        boolean usesDecl = false;
        final Declaration[] declarations = condition.getRequiredDeclarations();
        for ( int j = 0; !usesDecl && j < declarations.length; j++ ) {
            usesDecl = (declarations[j].getPattern().getObjectType() == this.objectType);
        }
        return usesDecl;
    }

    /**
     * @return the entryPoint
     */
    public EntryPoint getEntryPoint() {
        return ((EntryPointNode) this.source).getEntryPoint();
    }
}
