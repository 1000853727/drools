/*
 * Copyright 2012 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.workbench.models.datamodel.rule;

/**
 * This is for a connective constraint that adds more options to a field
 * constraint.
 */
public class ConnectiveConstraint extends BaseSingleFieldConstraint {

    private String factType;
    private String fieldName;
    private String fieldType;

    public ConnectiveConstraint() {
    }

    public ConnectiveConstraint( final String factType,
                                 final String fieldName,
                                 final String fieldType ) {
        this( factType,
              fieldName,
              fieldType,
              null,
              null );
    }

    public ConnectiveConstraint( final String factType,
                                 final String fieldName,
                                 final String fieldType,
                                 final String opr,
                                 final String val ) {
        this.factType = factType;
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.operator = opr;
        this.setValue( val );
    }

    public String getFactType() {
        return factType;
    }

    public void setFactType( String factType ) {
        this.factType = factType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName( String fieldName ) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType( String fieldType ) {
        this.fieldType = fieldType;
    }

}
