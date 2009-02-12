/*
 * Copyright 2006 JBoss Inc
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

package org.drools.lang.dsl;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.drools.lang.dsl.DSLMappingEntry.Section;

/**
 * This is a default implementation of the DSL Mapping interface
 * capable of storing a list of DSLMappingEntries and managing it.
 * 
 * @author etirelli
 */
public class DefaultDSLMapping
    implements
    DSLMapping {

    private String                identifier;
    private String                description;
    private List<DSLMappingEntry> entries;

    public DefaultDSLMapping() {
        this( "" );
    }

    public DefaultDSLMapping(final String identifier) {
        this.identifier = identifier;
        this.entries = new LinkedList<DSLMappingEntry>();
    }

    /**
     * Add one entry to the list of the entries
     * @param entry
     */
    public void addEntry(final DSLMappingEntry entry) {
        this.entries.add( entry );
    }

    /**
     * Adds all entries in the given list to this DSL Mapping
     * @param entries
     */
    public void addEntries(final List<DSLMappingEntry> entries) {
        this.entries.addAll( entries );
    }

    /**
     * Returns an unmodifiable list of entries
     */
    public List<DSLMappingEntry> getEntries() {
        return Collections.unmodifiableList( this.entries );
    }

    /**
     * Returns the list of mappings for the given section 
     * @param section
     * @return
     */
    public List<DSLMappingEntry> getEntries(final Section section) {
        final List<DSLMappingEntry> list = new LinkedList<DSLMappingEntry>();
        for ( final Iterator<DSLMappingEntry> it = this.entries.iterator(); it.hasNext(); ) {
            final DSLMappingEntry entry = it.next();
            if ( entry.getSection().equals( section ) ) {
                list.add( entry );
            }
        }
        return list;
    }

    /**
     * Returns the identifier for this mapping
     */
    public String getIdentifier() {
        return this.identifier;
    }

    /**
     * @inheritDoc
     */
    public void removeEntry(final DSLMappingEntry entry) {
        this.entries.remove( entry );
    }

    /**
     * @inheritDoc
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @inheritDoc
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @inheritDoc
     */
    public void setIdentifier(final String identifier) {
        this.identifier = identifier;
    }

}
