package org.drools.clp.functions;

import org.drools.clp.ExecutionContext;
import org.drools.clp.Function;
import org.drools.clp.LispData;
import org.drools.clp.LispList;
import org.drools.clp.ValueHandler;
import org.drools.clp.valuehandlers.ListValueHandler;
import org.mvel.PropertyAccessor;

public class ModifyFunction extends BaseFunction
    implements
    Function {
    private static final String name = "modify";

    public ModifyFunction() {
    }

    public ValueHandler execute(ValueHandler[] args,
                                ExecutionContext context) {
        Object object = args[0].getObject( context );
        for ( int i = 1, length = args.length; i < length; i++ ) {
            ValueHandler[] list = ((ListValueHandler) args[i]).getList();
            PropertyAccessor.set( object,
                                  list[0].getStringValue( context ),
                                  list[1].getObject( context ) );
        }
        return null;
    }   

    public LispList createList(int index) {
        return new LispData();
    }

    public String getName() {
        return name;
    }
}
