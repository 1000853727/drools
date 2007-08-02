/**
 * 
 */
package org.drools.clp;

import java.util.Map;

import org.drools.WorkingMemory;
import org.drools.clp.valuehandlers.FunctionCaller;
import org.drools.clp.valuehandlers.IndexedLocalVariableValue;
import org.drools.common.InternalWorkingMemory;
import org.drools.reteoo.ReteTuple;
import org.drools.rule.Declaration;
import org.drools.spi.PredicateExpression;
import org.drools.spi.Tuple;

public class CLPPredicate
    implements
    PredicateExpression,
    ExecutionEngine {
    private FunctionCaller function;
    private int            index;

    public CLPPredicate() {
    }

    public CLPPredicate(FunctionCaller function) {
        this.function = function;
    }

    public void addFunction(FunctionCaller function) {
        setFunction( function );
    }

    public void setFunction(FunctionCaller function) {
        this.function = function;
    }

    public FunctionCaller[] getFunctions() {
        return new FunctionCaller[]{this.function};
    }

    public VariableValueHandler createLocalVariable(String identifier) {
        return new IndexedLocalVariableValue( identifier,
                                              this.index++ );
    }

    public boolean evaluate(Object object,
                            Tuple tuple,
                            Declaration[] previousDeclarations,
                            Declaration[] localDeclarations,
                            WorkingMemory workingMemory) throws Exception {
        ExecutionContext context = new ExecutionContextImpl( (InternalWorkingMemory) workingMemory,
                                                         (ReteTuple) tuple,
                                                         object,
                                                         this.index - 1 );
        return this.function.getBooleanValue( context );
    }

    public void replaceTempTokens(Map variables) {
        this.function.replaceTempTokens( variables );
    }
}