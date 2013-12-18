/*
 * Copyright 2013 JBoss Inc
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
package org.drools.workbench.models.commons.backend.rule;

import java.util.HashSet;
import java.util.Set;

import org.drools.workbench.models.datamodel.rule.FieldConstraint;
import org.drools.workbench.models.datamodel.rule.InterpolationVariable;

/**
 * DRL generation context object
 */
public class GeneratorContext {

    private Set<String> varsInScope = new HashSet<String>();
    private FieldConstraint fieldConstraint;
    private GeneratorContext parent;
    private int depth;
    private int offset;
    private boolean hasOutput;
    private boolean hasNonTemplateOutput;
    private int childCount;

    GeneratorContext() {
    }

    GeneratorContext( GeneratorContext parent,
                      int depth,
                      int offset ) {
        this.parent = parent;
        this.depth = depth;
        this.offset = offset;
    }

    public FieldConstraint getFieldConstraint() {
        return fieldConstraint;
    }

    public void setFieldConstraint( FieldConstraint fieldConstraint ) {
        this.fieldConstraint = fieldConstraint;
        this.varsInScope.clear();
        Set<InterpolationVariable> vars = new HashSet<InterpolationVariable>();
        GeneratorContextRuleModelVisitor visitor = new GeneratorContextRuleModelVisitor( vars );
        visitor.visit( fieldConstraint );
        for ( InterpolationVariable var : vars ) {
            varsInScope.add( var.getVarName() );
        }
        hasNonTemplateOutput = visitor.hasNonTemplateOutput();
    }

    public GeneratorContext getParent() {
        return parent;
    }

    public int getDepth() {
        return depth;
    }

    public boolean isHasOutput() {
        return hasOutput;
    }

    public void setHasOutput( boolean hasOutput ) {
        this.hasOutput = hasOutput;
    }

    public Set<String> getVarsInScope() {
        return this.varsInScope;
    }

    public int getOffset() {
        return offset;
    }

    public boolean hasNonTemplateOutput() {
        return hasNonTemplateOutput;
    }

}
