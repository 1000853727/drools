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

import java.util.List;

import org.drools.lang.dsl.DSLMappingEntry.Section;

/**
 * An interface that represents a DSL Mapping 
 * 
 * @author etirelli
 */
public interface DSLMapping {

    /**
     * Returns the string identifier for this mapping
     * @return
     */
    public String getIdentifier();

    /**
     * Sets the identifier for this mapping
     * @param identifier
     */
    public void setIdentifier(String identifier);

    /**
     * Returns a String description of this mapping
     * @return
     */
    public String getDescription();

    /**
     * Sets the description for this mapping
     * @param description
     */
    public void setDescription(String description);

    /**
     * Returns the list of entries in this mapping
     * @return
     */
    public List getEntries();

    /**
     * Add one entry to the list of the entries
     * @param entry
     */
    public void addEntry(DSLMappingEntry entry);

    /**
     * Adds all entries in the given list to this DSL Mapping
     * @param entries
     */
    public void addEntries(List entries);

    /**
     * Removes the given entry from the list of entries
     * @param entry
     */
    public void removeEntry(DSLMappingEntry entry);

    /**
     * Returns the list of mappings for the given section 
     * @param section
     * @return
     */
    public List getEntries(Section section);

}
