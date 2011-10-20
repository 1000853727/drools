package org.drools.rule.builder.dialect.asm;

import org.drools.WorkingMemory;
import org.drools.rule.Declaration;
import org.drools.rule.builder.RuleBuildContext;
import org.drools.spi.CompiledInvoker;
import org.drools.spi.PredicateExpression;
import org.drools.spi.Tuple;
import org.mvel2.asm.MethodVisitor;

import java.util.Map;

import static org.drools.rule.builder.dialect.asm.InvokerGenerator.createInvokerClassGenerator;
import static org.mvel2.asm.Opcodes.ACC_PUBLIC;
import static org.mvel2.asm.Opcodes.ACONST_NULL;
import static org.mvel2.asm.Opcodes.ARETURN;
import static org.mvel2.asm.Opcodes.INVOKESTATIC;
import static org.mvel2.asm.Opcodes.IRETURN;

public class ASMPredicateBuilder extends AbstractASMPredicateBuilder {

    protected byte[] createPredicateBytecode(final RuleBuildContext ruleContext, final Map<String, Object> vars) {
        final InvokerDataProvider data = new InvokerContext(vars);

        final ClassGenerator generator = createInvokerClassGenerator(data, ruleContext)
                .setInterfaces(PredicateExpression.class, CompiledInvoker.class);

        generator.addMethod(ACC_PUBLIC, "createContext", generator.methodDescr(Object.class), new ClassGenerator.MethodBody() {
            public void body(MethodVisitor mv) {
                mv.visitInsn(ACONST_NULL);
                mv.visitInsn(ARETURN);
            }
        }).addMethod(ACC_PUBLIC, "evaluate", generator.methodDescr(Boolean.TYPE, Object.class, Tuple.class, Declaration[].class, Declaration[].class, WorkingMemory.class, Object.class), new String[]{"java/lang/Exception"}, new GeneratorHelper.EvaluateMethod() {
            public void body(MethodVisitor mv) {
                final Declaration[] previousDeclarations = (Declaration[])vars.get("declarations");
                final String[] previousDeclarationTypes = (String[])vars.get("declarationTypes");
                final Declaration[] localDeclarations = (Declaration[])vars.get("localDeclarations");
                final String[] localDeclarationTypes = (String[])vars.get("localDeclarationTypes");
                final String[] globals = (String[])vars.get("globals");
                final String[] globalTypes = (String[])vars.get("globalTypes");

                objAstorePos = 7;
                int[] previousDeclarationsParamsPos = parseDeclarations(previousDeclarations, previousDeclarationTypes, 3, 2, 5, true);
                int[] localDeclarationsParamsPos = parseDeclarations(localDeclarations, localDeclarationTypes, 4, 2, 5, false);

                // @{ruleClassName}.@{methodName}(@foreach{previousDeclarations}, @foreach{localDeclarations}, @foreach{globals})
                StringBuilder predicateMethodDescr = new StringBuilder("(");
                for (int i = 0; i < previousDeclarations.length; i++) {
                    load(previousDeclarationsParamsPos[i]); // previousDeclarations[i]
                    predicateMethodDescr.append(typeDescr(previousDeclarationTypes[i]));
                }
                for (int i = 0; i < localDeclarations.length; i++) {
                    load(localDeclarationsParamsPos[i]); // localDeclarations[i]
                    predicateMethodDescr.append(typeDescr(localDeclarationTypes[i]));
                }

                // @foreach{type : globalTypes, identifier : globals} @{type} @{identifier} = ( @{type} ) workingMemory.getGlobal( "@{identifier}" );
                parseGlobals(globals, globalTypes, 5, predicateMethodDescr);

                predicateMethodDescr.append(")Z");
                mv.visitMethodInsn(INVOKESTATIC, data.getInternalRuleClassName(), data.getMethodName(), predicateMethodDescr.toString());
                mv.visitInsn(IRETURN);
            }
        });

        return generator.generateBytecode();
    }
}
