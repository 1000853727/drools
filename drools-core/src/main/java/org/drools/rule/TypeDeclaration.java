/*
 * Copyright 2008 JBoss Inc
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
 *
 * Created on Jan 23, 2008
 */

package org.drools.rule;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;

import org.drools.factmodel.ClassDefinition;
import org.drools.facttemplates.FactTemplate;
import org.drools.spi.AcceptsReadAccessor;
import org.drools.spi.InternalReadAccessor;

/**
 * The type declaration class stores all type's metadata
 * declared in source files.
 *  
 * @author etirelli
 */
public class TypeDeclaration
    implements
    Externalizable {

    public static final String ATTR_CLASS     = "class";
    public static final String ATTR_TEMPLATE  = "template";
    public static final String ATTR_DURATION  = "duration";
    public static final String ATTR_TIMESTAMP = "timestamp";

    public static enum Role {
        FACT, EVENT;

        public static final String ID = "role";

        public static Role parseRole(String role) {
            if ( "event".equalsIgnoreCase( role ) ) {
                return EVENT;
            } else if ( "fact".equalsIgnoreCase( role ) ) {
                return FACT;
            }
            return null;
        }
    }

    public static enum Format {
        POJO, TEMPLATE;

        public static final String ID = "format";

        public static Format parseFormat(String format) {
            if ( "pojo".equalsIgnoreCase( format ) ) {
                return POJO;
            } else if ( "template".equalsIgnoreCase( format ) ) {
                return TEMPLATE;
            }
            return null;
        }
    }

    private String               typeName;
    private Role                 role;
    private Format               format;
    private String               timestampAttribute;
    private String               durationAttribute;
    private InternalReadAccessor durationExtractor;
    private InternalReadAccessor timestampExtractor;
    private transient Class< ? > typeClass;
    private FactTemplate         typeTemplate;
    private ClassDefinition      typeClassDef;
    
    public TypeDeclaration() {
    }

    public TypeDeclaration(String typeName) {
        this.typeName = typeName;
        this.role = Role.FACT;
        this.format = Format.POJO;
        this.durationAttribute = null;
        this.timestampAttribute = null;
        //this.typeClass = null;
        this.typeTemplate = null;
    }

    public void readExternal(ObjectInput in) throws IOException,
                                            ClassNotFoundException {
        this.typeName = (String) in.readObject();
        this.role = (Role) in.readObject();
        this.format = (Format) in.readObject();
        this.durationAttribute = (String) in.readObject();
        this.timestampAttribute = (String) in.readObject();
        //this.typeClass = (Class< ? >) in.readObject();
        this.typeTemplate = (FactTemplate) in.readObject();
        this.typeClassDef = (ClassDefinition) in.readObject();
        this.durationExtractor = (InternalReadAccessor) in.readObject();
        this.timestampExtractor = (InternalReadAccessor) in.readObject();
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject( typeName );
        out.writeObject( role );
        out.writeObject( format );
        out.writeObject( durationAttribute );
        out.writeObject( timestampAttribute );
        //out.writeObject( typeClass );
        out.writeObject( typeTemplate );
        out.writeObject( typeClassDef );
        out.writeObject( durationExtractor );
        out.writeObject( timestampExtractor );
    }

    /**
     * @return the type
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * @return the category
     */
    public Role getRole() {
        return role;
    }

    /**
     * @param role the category to set
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * @return the format
     */
    public Format getFormat() {
        return format;
    }

    /**
     * @param format the format to set
     */
    public void setFormat(Format format) {
        this.format = format;
    }

    /**
     * @return the timestampAttribute
     */
    public String getTimestampAttribute() {
        return timestampAttribute;
    }

    /**
     * @param timestampAttribute the timestampAttribute to set
     */
    public void setTimestampAttribute(String timestampAttribute) {
        this.timestampAttribute = timestampAttribute;
    }

    /**
     * @return the durationAttribute
     */
    public String getDurationAttribute() {
        return durationAttribute;
    }

    /**
     * @param durationAttribute the durationAttribute to set
     */
    public void setDurationAttribute(String durationAttribute) {
        this.durationAttribute = durationAttribute;
    }

    /**
     * @return the typeClass
     */
    public Class< ? > getTypeClass() {
        return typeClass;
    }

    /**
     * @param typeClass the typeClass to set
     */
    public void setTypeClass(Class< ? > typeClass) {
        this.typeClass = typeClass;
        if ( this.typeClassDef != null ) {
            this.typeClassDef.setDefinedClass( this.typeClass );
        }
    }

    /**
     * @return the typeTemplate
     */
    public FactTemplate getTypeTemplate() {
        return typeTemplate;
    }

    /**
     * @param typeTemplate the typeTemplate to set
     */
    public void setTypeTemplate(FactTemplate typeTemplate) {
        this.typeTemplate = typeTemplate;
    }

    /**
     * Returns true if the given parameter matches this type declaration
     * 
     * @param clazz
     * @return
     */
    public boolean matches(Object clazz) {
        boolean matches = false;
        if ( clazz instanceof FactTemplate ) {
            matches = this.typeTemplate.equals( clazz );
        } else {
            matches = this.typeClass.isAssignableFrom( (Class< ? >) clazz );
        }
        return matches;
    }

    public boolean equals(Object obj) {
        if ( obj == this ) {
            return true;
        } else if ( obj instanceof TypeDeclaration ) {
            TypeDeclaration that = (TypeDeclaration) obj;
            return isObjectEqual( typeName,
                                  that.typeName ) && role == that.role && format == that.format && isObjectEqual( timestampAttribute,
                                                                                                                  that.timestampAttribute ) && isObjectEqual( durationAttribute,
                                                                                                                                                              that.durationAttribute ) && typeClass == that.typeClass
                   && isObjectEqual( typeTemplate,
                                     that.typeTemplate );
        }
        return false;
    }

    private static boolean isObjectEqual(Object a,
                                         Object b) {
        return a == b || a != null && a.equals( b );
    }

    public InternalReadAccessor getDurationExtractor() {
        return durationExtractor;
    }

    public void setDurationExtractor(InternalReadAccessor durationExtractor) {
        this.durationExtractor = durationExtractor;
    }

    /**
     * @return the typeClassDef
     */
    public ClassDefinition getTypeClassDef() {
        return typeClassDef;
    }

    /**
     * @param typeClassDef the typeClassDef to set
     */
    public void setTypeClassDef(ClassDefinition typeClassDef) {
        this.typeClassDef = typeClassDef;
    }

    public InternalReadAccessor getTimestampExtractor() {
        return timestampExtractor;
    }

    public void setTimestampExtractor(InternalReadAccessor timestampExtractor) {
        this.timestampExtractor = timestampExtractor;
    }

    public class DurationAccessorSetter implements AcceptsReadAccessor, Serializable {
        private static final long serialVersionUID = 1429300982505284833L;
        public void setReadAccessor(InternalReadAccessor readAccessor) {
            setDurationExtractor( readAccessor );
        }
    }
    
    public class TimestampAccessorSetter implements AcceptsReadAccessor, Serializable {
        private static final long serialVersionUID = 8656678871125722903L;
        public void setReadAccessor(InternalReadAccessor readAccessor) {
            setTimestampExtractor( readAccessor );
        }
    }
}
