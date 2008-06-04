package org.drools.lang.dsl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.drools.lang.dsl.DSLMappingEntry.MetaData;
import org.drools.lang.dsl.DSLMappingEntry.Section;
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


public abstract class AbstractDSLMappingEntry {

	protected Section section;
	protected MetaData metadata;
	protected String key;
	protected String value;
	protected Map variables = Collections.EMPTY_MAP;
	protected Pattern keyPattern;
	protected String valuePattern;

	public AbstractDSLMappingEntry() {
		super();
	}

	/**
	 * @inheritDoc
	 */
	public Section getSection() {
	    return this.section;
	}

	/**
	 * @inheritDoc
	 */
	public DSLMappingEntry.MetaData getMetaData() {
	    return this.metadata;
	}

	/**
	 * @inheritDoc
	 */
	public String getMappingKey() {
	    return this.key;
	}

	/**
	 * @inheritDoc
	 */
	public String getMappingValue() {
	    return this.value;
	}

	/**
	 * @param section the section to set
	 */
	public void setSection(final Section section) {
	    this.section = section;
	}

	/**
	 * @param metadata the metadata to set
	 */
	public void setMetaData(final MetaData metadata) {
	    this.metadata = metadata;
	}

	/**
	 * @return the keyPattern
	 */
	public Pattern getKeyPattern() {
	    return this.keyPattern;
	}

	/**
	 * @return the valuePattern
	 */
	public String getValuePattern() {
	    return this.valuePattern;
	}

	/**
	 * @return the variables
	 */
	public Map getVariables() {
	    return this.variables;
	}

	public String toPatternString() {
	    return this.section + "[" + this.metadata + "]" + this.keyPattern.pattern() + "=" + this.valuePattern;
	}

	public String toString() {
	    return this.section + "[" + this.metadata + "]" + this.key + "=" + this.value;
	}

	public int hashCode() {
	    final int PRIME = 31;
	    int result = 1;
	    result = PRIME * result + ((this.key == null) ? 0 : this.key.hashCode());
	    result = PRIME * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
	    result = PRIME * result + ((this.section == null) ? 0 : this.section.hashCode());
	    result = PRIME * result + ((this.value == null) ? 0 : this.value.hashCode());
	    return result;
	}

	public boolean equals(final Object obj) {
	    if ( this == obj ) {
	        return true;
	    }
	    if ( obj == null ) {
	        return false;
	    }
	    if ( getClass() != obj.getClass() ) {
	        return false;
	    }
	    final AbstractDSLMappingEntry other = (AbstractDSLMappingEntry) obj;
	    if ( this.key == null ) {
	        if ( other.key != null ) {
	            return false;
	        }
	    } else if ( !this.key.equals( other.key ) ) {
	        return false;
	    }
	    if ( this.metadata == null ) {
	        if ( other.metadata != null ) {
	            return false;
	        }
	    } else if ( !this.metadata.equals( other.metadata ) ) {
	        return false;
	    }
	    if ( this.section == null ) {
	        if ( other.section != null ) {
	            return false;
	        }
	    } else if ( !this.section.equals( other.section ) ) {
	        return false;
	    }
	    if ( this.value == null ) {
	        if ( other.value != null ) {
	            return false;
	        }
	    } else if ( !this.value.equals( other.value ) ) {
	        return false;
	    }
	    return true;
	}

	public List getErrors() {
	    // TODO Need to implement validation here
	    return Collections.EMPTY_LIST;
	}

}