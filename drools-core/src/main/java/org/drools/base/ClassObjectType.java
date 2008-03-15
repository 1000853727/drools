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

import org.drools.spi.ObjectType;

import java.io.ObjectInput;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.Externalizable;

/**
 * Java class semantics <code>ObjectType</code>.
 *
 * @author <a href="mailto:bob@werken.com">bob@werken.com </a>
 *
 * @version $Id: ClassObjectType.java,v 1.5 2005/02/04 02:13:36 mproctor Exp $
 */
public class ClassObjectType
    implements
    ObjectType, Externalizable {

    /**
     *
     */
    private static final long serialVersionUID = 400L;

    /** Java object class. */
    protected Class           objectTypeClass;

    protected ValueType       valueType;

    private boolean           isEvent;

    // ------------------------------------------------------------
    // Constructors
    // ------------------------------------------------------------

    public ClassObjectType() {
        this(null);
    }
    /**
     * Creates a new class object type with shadow disabled.
     *
     * @param objectTypeClass
     *            Java object class.
     */
    public ClassObjectType(final Class objectTypeClass) {
        this( objectTypeClass, false );
    }

    /**
     * Creates a new class object type
     *
     * @param objectTypeClass the class represented by this class object type
     * @param isEvent true if it is an event class, false otherwise
     */
    public ClassObjectType(final Class objectTypeClass, final boolean isEvent) {
        this.objectTypeClass = objectTypeClass;
        this.isEvent = isEvent;
        if (objectTypeClass != null)
            this.valueType = ValueType.determineValueType( objectTypeClass );
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        objectTypeClass = (Class)in.readObject();
        valueType       = (ValueType)in.readObject();
        if (valueType != null)
            valueType   = ValueType.determineValueType(valueType.getClassType());
        isEvent         = in.readBoolean();
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(objectTypeClass);
        out.writeObject(valueType);
        out.writeBoolean(isEvent);
    }

    // ------------------------------------------------------------
    // Instance methods
    // ------------------------------------------------------------

    /**
     * Return the Java object class.
     *
     * @return The Java object class.
     */
    public Class getClassType() {
        return this.objectTypeClass;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // org.drools.spi.ObjectType
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    /**
     * Determine if the passed <code>Class</code> matches to the object type
     * defined by this <code>objectType</code> instance.
     *
     * @param clazz
     *            The <code>Class</code> to test.
     *
     * @return <code>true</code> if the <code>Class</code> matches this
     *         object type, else <code>false</code>.
     */
    public boolean matchesClass(final Class clazz) {
        return getClassType().isAssignableFrom( clazz );
    }

    /**
     * Determine if the passed <code>Object</code> belongs to the object type
     * defined by this <code>objectType</code> instance.
     *
     * @param object
     *            The <code>Object</code> to test.
     *
     * @return <code>true</code> if the <code>Object</code> matches this
     *         object type, else <code>false</code>.
     */
    public boolean matches(final Object object) {
        return getClassType().isInstance( object );
    }

    public boolean isAssignableFrom(Object object) {
        return this.objectTypeClass.isAssignableFrom( (Class) object );
    }

    public boolean isAssignableFrom(ObjectType objectType) {
        if ( !(objectType instanceof ClassObjectType) ) {
            return false;
        } else {
            return this.objectTypeClass.isAssignableFrom( ((ClassObjectType) objectType).getClassType() );
        }
    }

    public ValueType getValueType() {
        return this.valueType;
    }

    public boolean isEvent() {
        return isEvent;
    }

    public void setEvent(boolean isEvent) {
        this.isEvent = isEvent;
    }

    public String toString() {
        return "[ClassObjectType "+( this.isEvent ? "event=" : "class=" )+ getClassType().getName() + "]";
    }

    /**
     * Determine if another object is equal to this.
     *
     * @param object
     *            The object to test.
     *
     * @return <code>true</code> if <code>object</code> is equal to this,
     *         otherwise <code>false</code>.
     */
    public boolean equals(final Object object) {
        if ( this == object ) {
            return true;
        }

        if ( object == null || object.getClass() != ClassObjectType.class ) {
            return false;
        }

        return this.objectTypeClass == ((ClassObjectType) object).objectTypeClass;
    }

    public int hashCode() {
        return this.objectTypeClass.hashCode();
    }

}