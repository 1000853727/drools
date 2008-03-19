package org.drools.base.field;

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

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;

import org.drools.RuntimeDroolsException;
import org.drools.spi.FieldValue;

public class ObjectFieldImpl
    implements
    FieldValue,
    Externalizable {

    private static final long serialVersionUID = 400L;

    private Object            value;

    // the isEnum attribute is used to support jdk 1.4 type safe enums, and so
    // has a different behavior of the other booleans in this class
    private boolean           isEnum;
    private String            enumName;
    private String            fieldName;

    private transient boolean isCollection;
    private transient boolean isNumber;
    private transient boolean isBoolean;
    private transient boolean isCharacter;
    private transient boolean isString;

    public ObjectFieldImpl() {
        this( null );
    }

    public ObjectFieldImpl(final Object value) {
        this.value = value;
        this.isEnum = value instanceof Enum;
        setBooleans();
    }

    public void readExternal(ObjectInput in) throws IOException,
                                            ClassNotFoundException {
        isEnum = in.readBoolean();
        enumName = (String) in.readObject();
        fieldName = (String) in.readObject();
        if ( !isEnum || enumName == null || fieldName == null ) {
            value = in.readObject();
        } else {
            resolveEnumValue();
        }
        setBooleans();
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeBoolean( isEnum );
        out.writeObject( enumName );
        out.writeObject( fieldName );
        if ( !isEnum || enumName == null || fieldName == null ) {
            out.writeObject( value );
        }
    }

    private void resolveEnumValue() {
        try {
            final Class<?> staticClass = Class.forName( enumName );
            value = staticClass.getField( fieldName ).get( null );
        } catch ( final Exception e ) {
            throw new RuntimeDroolsException("Error deserializing enum value "+enumName+"."+fieldName+" : "+e.getMessage());
        }
    }

    /**
     * @param value
     */
    private void setBooleans() {
        this.isCollection = value instanceof Collection;
        this.isNumber = value instanceof Number;
        this.isBoolean = value instanceof Boolean;
        this.isCharacter = value instanceof Character;
        this.isString = value instanceof String;
    }

    public Object getValue() {
        return this.value;
    }

    public String toString() {
        return this.value == null ? "null" : this.value.toString();
    }

    public boolean getBooleanValue() {
        if ( isBoolean ) {
            return ((Boolean) this.value).booleanValue();
        } else if ( isString ) {
            return Boolean.valueOf( (String) this.value ).booleanValue();
        }
        throw new RuntimeDroolsException( "Conversion to boolean not supported for type: " + this.value.getClass() );
    }

    public byte getByteValue() {
        if ( isNumber ) {
            return ((Number) this.value).byteValue();
        } else if ( isString ) {
            return Byte.valueOf( (String) this.value ).byteValue();
        }
        throw new RuntimeDroolsException( "Conversion to byte not supported for type: " + this.value.getClass() );
    }

    public char getCharValue() {
        if ( isCharacter ) {
            return ((Character) this.value).charValue();
        } else if ( isString && ((String) this.value).length() == 1 ) {
            return ((String) this.value).charAt( 0 );
        }
        throw new RuntimeDroolsException( "Conversion to char not supported for type: " + this.value.getClass() );
    }

    public double getDoubleValue() {
        if ( isNumber ) {
            return ((Number) this.value).doubleValue();
        } else if ( isString ) {
            return Double.valueOf( (String) this.value ).doubleValue();
        }
        throw new RuntimeDroolsException( "Conversion to double not supported for type: " + this.value.getClass() );
    }

    public float getFloatValue() {
        if ( isNumber ) {
            return ((Number) this.value).floatValue();
        } else if ( isString ) {
            return Float.valueOf( (String) this.value ).floatValue();
        }
        throw new RuntimeDroolsException( "Conversion to float not supported for type: " + this.value.getClass() );
    }

    public int getIntValue() {
        if ( isNumber ) {
            return ((Number) this.value).intValue();
        } else if ( isString ) {
            return Integer.valueOf( (String) this.value ).intValue();
        }
        throw new RuntimeDroolsException( "Conversion to int not supported for type: " + this.value.getClass() );
    }

    public long getLongValue() {
        if ( isNumber ) {
            return ((Number) this.value).longValue();
        } else if ( isString ) {
            return Long.valueOf( (String) this.value ).longValue();
        }
        throw new RuntimeDroolsException( "Conversion to long not supported for type: " + this.value.getClass() );
    }

    public short getShortValue() {
        if ( isNumber ) {
            return ((Number) this.value).shortValue();
        } else if ( isString ) {
            return Short.valueOf( (String) this.value ).shortValue();
        }
        throw new RuntimeDroolsException( "Conversion to short not supported for type: " + this.value.getClass() );
    }

    public boolean equals(final Object object) {
        if ( this == object ) {
            return true;
        }
        if ( object == null || !(object instanceof ObjectFieldImpl) ) {
            return false;
        }
        final ObjectFieldImpl other = (ObjectFieldImpl) object;

        return (((this.value == null) && (other.value == null)) || ((this.value != null) && (this.value.equals( other.value ))));
    }

    public int hashCode() {
        if ( this.value != null ) {
            return this.value.hashCode();
        } else {
            return 0;
        }
    }

    public boolean isNull() {
        return value == null;
    }

    public boolean isBooleanField() {
        return false;
    }

    public boolean isFloatNumberField() {
        return false;
    }

    public boolean isIntegerNumberField() {
        return false;
    }

    public boolean isObjectField() {
        return true;
    }

    public boolean isCollectionField() {
        return this.isCollection;
    }

    public boolean isStringField() {
        return this.isString;
    }

	public BigDecimal getBigDecimalValue() {
		if (this.value instanceof BigDecimal) return (BigDecimal) this.value;
		if (this.isNumber) {
			return new BigDecimal(((Number) value).doubleValue());
		} else if (this.isString) {
			return new BigDecimal((String) value);
		}
		if (this.value == null) return null;
        throw new RuntimeDroolsException( "Conversion to BigDecimal not supported for type: " + this.value.getClass() );
	}

	public BigInteger getBigIntegerValue() {
		if (this.value instanceof BigInteger) return (BigInteger) this.value;
		if (this.isNumber) {
			return BigInteger.valueOf(((Number) value).longValue());
		} else if (this.isString) {
			return new BigInteger((String) value);
		}
		if (this.value == null) return null;
        throw new RuntimeDroolsException( "Conversion to BigInteger not supported for type: " + this.value.getClass() );
	}

    public boolean isEnum() {
        return isEnum;
    }

    public void setEnum(boolean isEnum) {
        this.isEnum = isEnum;
    }

    public String getEnumName() {
        return enumName;
    }

    public void setEnumName(String enumName) {
        this.enumName = enumName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

}