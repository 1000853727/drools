package org.drools.compiler.rule.builder.dialect.asm;

import org.drools.compiler.compiler.AnalysisResult;
import org.drools.compiler.compiler.BoundIdentifiers;
import org.drools.compiler.lang.descr.ReturnValueRestrictionDescr;
import org.drools.compiler.rule.builder.ReturnValueBuilder;
import org.drools.core.rule.Declaration;
import org.drools.core.rule.ReturnValueRestriction;
import org.drools.compiler.rule.builder.RuleBuildContext;

import java.util.Map;

import static org.drools.compiler.rule.builder.dialect.java.JavaRuleBuilderHelper.createVariableContext;
import static org.drools.compiler.rule.builder.dialect.java.JavaRuleBuilderHelper.generateMethodTemplate;
import static org.drools.compiler.rule.builder.dialect.java.JavaRuleBuilderHelper.registerInvokerBytecode;

public abstract class AbstractASMReturnValueBuilder implements ReturnValueBuilder {

    public void build(final RuleBuildContext context,
                      final BoundIdentifiers usedIdentifiers,
                      final Declaration[] previousDeclarations,
                      final Declaration[] localDeclarations,
                      final ReturnValueRestriction returnValueRestriction,
                      final ReturnValueRestrictionDescr returnValueRestrictionDescr,
                      final AnalysisResult analysis) {
        final String className = "returnValue" + context.getNextId();
        returnValueRestrictionDescr.setClassMethodName( className );

        final Map vars = createVariableContext(className,
                                               (String) returnValueRestrictionDescr.getContent(),
                                               context,
                                               previousDeclarations,
                                               localDeclarations,
                                               usedIdentifiers.getGlobals());

        generateMethodTemplate("returnValueMethod", context, vars);

        byte[] bytecode = createReturnValueBytecode(context, vars, false);
        registerInvokerBytecode(context, vars, bytecode, returnValueRestriction);
    }

    protected abstract byte[] createReturnValueBytecode(RuleBuildContext context, Map vars, boolean readLocalsFromTuple);
}
