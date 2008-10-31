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

package org.drools.spi;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Method;

import org.drools.RuntimeDroolsException;
import org.drools.base.ClassObjectType;
import org.drools.base.ValueType;
import org.drools.common.InternalWorkingMemory;

/**
 * This is a global variable extractor used to get a global variable value
 *
 * @author etirelli
 */
public class GlobalExtractor
    implements
    Externalizable,
    AcceptsClassObjectType,
    InternalReadAccessor {

    private static final long serialVersionUID = 400L;
    private String            identifier;
    private ValueType         valueType;
    private ObjectType        objectType;

    public GlobalExtractor() {

    }
    
    public GlobalExtractor(final String identifier,
                           final ObjectType objectType) {
        this.identifier = identifier;
        this.objectType = objectType;
        this.valueType = objectType.getValueType();
    }    

    public Object getValue(InternalWorkingMemory workingMemory, final Object object) {
        return workingMemory.getGlobal( identifier );
    }
    

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(identifier);
        out.writeObject( objectType );
    }


    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        identifier = in.readUTF();
        objectType = ( ObjectType) in.readObject();
        valueType = objectType.getValueType();
    }
    
    public void setClassObjectType(ClassObjectType objectType) {
        this.objectType = objectType;
    }


    public Class getExtractToClass() {
        if ( objectType instanceof ClassObjectType ) {
            return ((ClassObjectType)objectType).getClassType();
        } else {
            return valueType.getClassType();
        }
    }

    public String getExtractToClassName() {
        if ( objectType instanceof ClassObjectType ) {
            return ((ClassObjectType)objectType).getClassName();
        } else {
            return valueType.getClassType().getName();
        }                
    }

    public ValueType getValueType() {
        return valueType;
    }

    public boolean getBooleanValue(InternalWorkingMemory workingMemory, final Object object) {
        if( this.valueType.isBoolean() ) {
            return ((Boolean) workingMemory.getGlobal( identifier )).booleanValue();
        }
        throw new ClassCastException("Not possible to convert global '"+identifier+"' into a boolean.");
    }

    public byte getByteValue(InternalWorkingMemory workingMemory, final Object object) {
        if( this.valueType.isNumber() ) {
            return ((Number) workingMemory.getGlobal( identifier )).byteValue();
        }
        throw new ClassCastException("Not possible to convert global '"+identifier+"' into a byte.");
    }

    public char getCharValue(InternalWorkingMemory workingMemory, final Object object) {
        if( this.valueType.isChar() ) {
            return ((Character) workingMemory.getGlobal( identifier )).charValue();
        }
        throw new ClassCastException("Not possible to convert global '"+identifier+"' into a char.");
    }

    public double getDoubleValue(InternalWorkingMemory workingMemory, final Object object) {
        if( this.valueType.isNumber() ) {
            return ((Number) workingMemory.getGlobal( identifier )).doubleValue();
        }
        throw new ClassCastException("Not possible to convert global '"+identifier+"' into a double.");
    }

    public float getFloatValue(InternalWorkingMemory workingMemory, final Object object) {
        if( this.valueType.isNumber() ) {
            return ((Number) workingMemory.getGlobal( identifier )).floatValue();
        }
        throw new ClassCastException("Not possible to convert global '"+identifier+"' into a float.");
    }

    public int getIntValue(InternalWorkingMemory workingMemory, final Object object) {
        if( this.valueType.isNumber() ) {
            return ((Number) workingMemory.getGlobal( identifier )).intValue();
        }
        throw new ClassCastException("Not possible to convert global '"+identifier+"' into an int.");
    }

    public long getLongValue(InternalWorkingMemory workingMemory, final Object object) {
        if( this.valueType.isNumber() ) {
            return ((Number) workingMemory.getGlobal( identifier )).longValue();
        }
        throw new ClassCastException("Not possible to convert global '"+identifier+"' into a long.");
    }

    public short getShortValue(InternalWorkingMemory workingMemory, final Object object) {
        if( this.valueType.isNumber() ) {
            return ((Number) workingMemory.getGlobal( identifier )).shortValue();
        }
        throw new ClassCastException("Not possible to convert global '"+identifier+"' into a short.");
    }

    public Method getNativeReadMethod() {
        try {
            return this.getClass().getDeclaredMethod( "getValue",
                                                      new Class[]{InternalWorkingMemory.class, Object.class} );
        } catch ( final Exception e ) {
            throw new RuntimeDroolsException( "This is a bug. Please report to development team: " + e.getMessage(),
                                              e );
        }
    }

    public int getHashCode(InternalWorkingMemory workingMemory, final Object object) {
        final Object value = getValue( workingMemory, object );
        return (value != null) ? value.hashCode() : 0;
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
        result = prime * result + ((objectType == null) ? 0 : objectType.hashCode());        
        return result;
    }

    public boolean equals(Object obj) {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        GlobalExtractor other = (GlobalExtractor) obj;
        if ( objectType == null ) {
            if ( other.objectType != null ) return false;
        } else if ( !objectType.equals( other.objectType ) ) return false;
        if ( identifier == null ) {
            if ( other.identifier != null ) return false;
        } else if ( !identifier.equals( other.identifier ) ) return false;
        return true;
    }

    public boolean isNullValue( InternalWorkingMemory workingMemory, Object object ) {
        final Object value = getValue( workingMemory, object );
        return value == null;
    }

    public boolean isGlobal() {
        return true;
    }
    public boolean getBooleanValue(Object object) {
        throw new RuntimeDroolsException("Can't extract a value from a global without a working memory reference");
    }
    public byte getByteValue(Object object) {
        throw new RuntimeDroolsException("Can't extract a value from a global without a working memory reference");
    }
    public char getCharValue(Object object) {
        throw new RuntimeDroolsException("Can't extract a value from a global without a working memory reference");
    }
    public double getDoubleValue(Object object) {
        throw new RuntimeDroolsException("Can't extract a value from a global without a working memory reference");
    }
    public float getFloatValue(Object object) {
        throw new RuntimeDroolsException("Can't extract a value from a global without a working memory reference");
    }
    public int getHashCode(Object object) {
        throw new RuntimeDroolsException("Can't extract a value from a global without a working memory reference");
    }
    public int getIndex() {
        throw new RuntimeDroolsException("Can't extract a value from a global without a working memory reference");
    }
    public int getIntValue(Object object) {
        throw new RuntimeDroolsException("Can't extract a value from a global without a working memory reference");
    }
    public long getLongValue(Object object) {
        throw new RuntimeDroolsException("Can't extract a value from a global without a working memory reference");
    }
    public short getShortValue(Object object) {
        throw new RuntimeDroolsException("Can't extract a value from a global without a working memory reference");
    }
    public Object getValue(Object object) {
        throw new RuntimeDroolsException("Can't extract a value from a global without a working memory reference");
    }
    public boolean isNullValue(Object object) {
        throw new RuntimeDroolsException("Can't extract a value from a global without a working memory reference");
    }
}
