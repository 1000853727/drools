package org.drools.clp;

import org.drools.clp.valuehandlers.FunctionCaller;

public class FunctionDelegator
    implements
    Function {
    private Function function;
    private String   name;

    public FunctionDelegator(String name) {
        this.name = name;
    }

    public FunctionDelegator(Function function) {
        this.function = function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public Function getFunction() {
        return this.function;
    }

    public ValueHandler addParameterCallback(int index,
                                             FunctionCaller caller,
                                             ValueHandler valueHandler,
                                             BuildContext context) {
        if ( this.function == null ) {
            caller.addParameter( valueHandler );
        } else {
            valueHandler = this.function.addParameterCallback( 0,
                                                               caller,
                                                               valueHandler,
                                                               context );
        }
        return valueHandler;
    }

    public void initCallback(BuildContext context) {
        this.function.initCallback( context );
    }

    public ValueHandler execute(ValueHandler[] args,
                                ExecutionContext context) {
        if ( this.function == null ) {
            throw new RuntimeException( "Unable to find and bind Function '" + this.name + "'" );
        }
        return function.execute( args,
                                 context );
    }

    public String getName() {
        return this.function == null ? this.name : function.getName();
    }

    public LispList createList(int index) {
        return this.function.createList( index );
    }

    public String toString() {
        return "[FunctionDelegate " + this.function + "]";
    }

}
