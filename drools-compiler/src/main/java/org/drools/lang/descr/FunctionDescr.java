package org.drools.lang.descr;

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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.drools.rule.Dialectable;
import org.drools.rule.Namespaceable;

public class FunctionDescr extends BaseDescr
    implements
    Dialectable,
    Namespaceable {
    private static final long serialVersionUID = 400L;

    private String            namespace;
    private final String      name;
    private final String      returnType;
    private String            dialect;

    private List<String>      parameterTypes   = Collections.emptyList();
    private List<String>      parameterNames   = Collections.emptyList();

    private Object            content;

    private int               offset;

    private String            className;

    public FunctionDescr(final String name,
                         final String returnType) {
        this.name = name;
        this.returnType = returnType;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public String getName() {
        return this.name;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public String getDialect() {
        return this.dialect;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(final String className) {
        this.className = className;
    }

    public List<String> getParameterNames() {
        return this.parameterNames;
    }

    public List<String> getParameterTypes() {
        return this.parameterTypes;
    }

    public void addParameter(final String type,
                             final String name) {
        if ( this.parameterTypes == Collections.EMPTY_LIST ) {
            this.parameterTypes = new ArrayList<String>();
        }
        this.parameterTypes.add( type );

        if ( this.parameterNames == Collections.EMPTY_LIST ) {
            this.parameterNames = new ArrayList<String>();
        }
        this.parameterNames.add( name );
    }

    public String getReturnType() {
        return this.returnType;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public Object getContent() {
        return this.content;
    }

    public int getOffset() {
        return this.offset;
    }

    public void setOffset(final int offset) {
        this.offset = offset;
    }

}