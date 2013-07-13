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

package org.drools.core.beliefsystem.simple;

import org.drools.core.common.LogicalDependency;
import org.drools.core.util.AbstractBaseLinkedListNode;
import org.drools.core.util.LinkedListEntry;
import org.drools.core.spi.Activation;

/**
 * LogicalDependency is a special node for LinkedLists that maintains
 * references for the Activation justifier and the justified FactHandle.
 */
public class SimpleLogicalDependency extends AbstractBaseLinkedListNode<LogicalDependency>
        implements
        LogicalDependency {
    private final Activation                         justifier;
    private final Object                             justified;
    private Object                                   object;
    private Object                                   value;

    private final LinkedListEntry<LogicalDependency> justifierEntry = new LinkedListEntry<LogicalDependency>( this );
    
    public SimpleLogicalDependency(final Activation justifier,
                                   final Object justified) {
        super();
        this.justifier = justifier;
        this.justified = justified;
    }

    public SimpleLogicalDependency(final Activation justifier,
                                   final Object justified,
                                   final Object object,
                                   final Object value) {
        super();
        this.justifier = justifier;
        this.justified = justified;
        this.object = object;
        this.value = value;
    }

    /* (non-Javadoc)
     * @see org.kie.common.LogicalDependency#getJustifierEntry()
     */
    public LinkedListEntry<LogicalDependency> getJustifierEntry() {
        return this.justifierEntry;
    }

    /* (non-Javadoc)
     * @see org.kie.common.LogicalDependency#getJustified()
     */
    public Object getJustified() {
        return this.justified;
    }

    /* (non-Javadoc)
     * @see org.kie.common.LogicalDependency#getJustifier()
     */
    public Activation getJustifier() {
        return this.justifier;
    }

    /* (non-Javadoc)
     * @see org.kie.common.LogicalDependency#getValue()
     */
    public Object getObject() {
        return this.object;
    }

    /* (non-Javadoc)
     * @see org.kie.common.LogicalDependency#getValue()
     */
    public Object getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }

    public boolean equals(final Object object) {
        if ( this == object ) {
            return true;
        }

        if ( !(object instanceof SimpleLogicalDependency) ) {
            return false;
        }

        final SimpleLogicalDependency other = (SimpleLogicalDependency) object;
        return (this.getJustifier() == other.getJustifier() && this.justified == other.justified);
    }

    public int hashCode() {
        return this.justifier.hashCode();
    }

    @Override
    public String toString() {
        return "SimpleLogicalDependency [justifier=" + justifier.getRule().getName() + ",\n justified=" + justified + ",\n object=" + object + ", value=" + value  + "]";
    }
    
    
}
