package org.drools.base;

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

import org.drools.RuntimeDroolsException;
import org.drools.spi.FieldExtractor;
import org.drools.util.ClassUtils;
import org.drools.util.asm.ClassFieldInspector;

/**
 * This is the supertype for the ASM generated classes for accessing a field.
 * @author Alexander Bagerman
 */
abstract public class BaseClassFieldExtractor
    implements
    FieldExtractor {
    private final int       index;

    private final Class     fieldType;

    private final ValueType valueType;

    /**
     * This constructor is not supposed to be used from outside the class hirarchy
     * 
     * @param index
     * @param fieldType
     * @param valueType
     */
    protected BaseClassFieldExtractor(final int index,
                                      final Class fieldType,
                                      final ValueType valueType) {
        this.index = index;
        this.fieldType = fieldType;
        this.valueType = valueType;
    }

    /**
     * This is the constructor to be used
     * 
     * @param clazz
     * @param fieldName
     */
    public BaseClassFieldExtractor(final Class clazz,
                                   final String fieldName) {
        try {
            final ClassFieldInspector inspector = new ClassFieldInspector( clazz );
            this.index = ((Integer) inspector.getFieldNames().get( fieldName )).intValue();
            this.fieldType = (Class) inspector.getFieldTypes().get( fieldName );
            this.valueType = ValueType.determineValueType( this.fieldType );
        } catch ( final Exception e ) {
            throw new RuntimeDroolsException( e );
        }
    }

    public int getIndex() {
        return this.index;
    }

    public Class getExtractToClass() {
        return this.fieldType;
    }

    public String getExtractToClassName() {
        return ClassUtils.canonicalName( this.fieldType );
    }

    public ValueType getValueType() {
        return this.valueType;
    }
    
    public boolean isGlobal() {
        return false;
    }
    
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + this.fieldType.hashCode();
        result = PRIME * result + this.index;
        result = PRIME * result + this.valueType.hashCode();
        return result;
    }

    public boolean equals(final Object object) {
        if ( this == object ) {
            return true;
        }
        if ( !(object instanceof BaseClassFieldExtractor) ) {
            return false;
        }
        final BaseClassFieldExtractor other = (BaseClassFieldExtractor) object;
        return this.fieldType == other.fieldType && this.index == other.index && this.valueType.equals( other.valueType );
    }
}