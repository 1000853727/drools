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

package org.drools.rule.builder.dialect.clips;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.drools.base.mvel.DroolsMVELFactory;
import org.drools.base.mvel.MVELPredicateExpression;
import org.drools.clips.Appendable;
import org.drools.clips.FunctionHandlers;
import org.drools.clips.LispForm;
import org.drools.clips.StringBuilderAppendable;
import org.drools.compiler.Dialect;
import org.drools.compiler.DescrBuildError;
import org.drools.lang.descr.PredicateDescr;
import org.drools.rule.Declaration;
import org.drools.rule.MVELDialectRuntimeData;
import org.drools.rule.PredicateConstraint;
import org.drools.rule.builder.PredicateBuilder;
import org.drools.rule.builder.RuleBuildContext;
import org.drools.rule.builder.dialect.mvel.MVELPredicateBuilder;
import org.mvel.compiler.AbstractParser;
import org.mvel.compiler.ExpressionCompiler;
import org.mvel.MVEL;
import org.mvel.ParserContext;

/**
 * @author etirelli
 *
 */
public class ClipsPredicateBuilder extends MVELPredicateBuilder
    implements
    PredicateBuilder {

    public void build(final RuleBuildContext context,
                      final List[] usedIdentifiers,
                      final Declaration[] previousDeclarations,
                      final Declaration[] localDeclarations,
                      final PredicateConstraint predicate,
                      final PredicateDescr predicateDescr) {        
        super.build(context, usedIdentifiers, previousDeclarations, localDeclarations, predicate, predicateDescr);
    }

}
