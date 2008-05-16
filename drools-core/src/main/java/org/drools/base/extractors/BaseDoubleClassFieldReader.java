package org.drools.base.extractors;

import java.lang.reflect.Method;

import org.drools.RuntimeDroolsException;
import org.drools.base.BaseClassFieldReader;
import org.drools.base.ValueType;
import org.drools.common.InternalWorkingMemory;

public abstract class BaseDoubleClassFieldReader extends BaseClassFieldReader {

    private static final long serialVersionUID = 400L;

    public BaseDoubleClassFieldReader(final Class clazz,
                                         final String fieldName) {
        super( clazz,
               fieldName );
    }

    /**
     * This constructor is not supposed to be used from outside the class hirarchy
     * 
     * @param index
     * @param fieldType
     * @param valueType
     */
    protected BaseDoubleClassFieldReader(final int index,
                                            final Class fieldType,
                                            final ValueType valueType) {
        super( index,
               fieldType,
               valueType );
    }

    public Object getValue(InternalWorkingMemory workingMemory, final Object object) {
        return new Double( getDoubleValue( workingMemory, object ) );
    }

    public boolean getBooleanValue(InternalWorkingMemory workingMemory, final Object object) {
        throw new RuntimeDroolsException( "Conversion to boolean not supported from double" );
    }

    public byte getByteValue(InternalWorkingMemory workingMemory, final Object object) {
        return (byte) getDoubleValue( workingMemory, object );

    }

    public char getCharValue(InternalWorkingMemory workingMemory, final Object object) {
        throw new RuntimeDroolsException( "Conversion to char not supported from double" );
    }

    public abstract double getDoubleValue(InternalWorkingMemory workingMemory, Object object);

    public float getFloatValue(InternalWorkingMemory workingMemory, final Object object) {
        return (float) getDoubleValue( workingMemory, object );
    }

    public int getIntValue(InternalWorkingMemory workingMemory, final Object object) {
        return (int) getDoubleValue( workingMemory, object );
    }

    public long getLongValue(InternalWorkingMemory workingMemory, final Object object) {
        return (long) getDoubleValue( workingMemory, object );
    }

    public short getShortValue(InternalWorkingMemory workingMemory, final Object object) {
        return (short) getDoubleValue( workingMemory, object );
    }

    public boolean isNullValue(InternalWorkingMemory workingMemory, final Object object) {
        return false;
    }

    public Method getNativeReadMethod() {
        try {
            return this.getClass().getDeclaredMethod( "getDoubleValue",
                                                      new Class[]{InternalWorkingMemory.class, Object.class} );
        } catch ( final Exception e ) {
            throw new RuntimeDroolsException( "This is a bug. Please report to development team: " + e.getMessage(),
                                              e );
        }
    }

    public int getHashCode(InternalWorkingMemory workingMemory, final Object object) {
        final long temp = Double.doubleToLongBits( getDoubleValue( workingMemory, object ) );
        return (int) (temp ^ (temp >>> 32));
    }

}
