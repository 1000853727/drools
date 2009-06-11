package org.drools.process.command;

import java.util.List;

import org.drools.command.Setter;
import org.drools.reteoo.ReteooWorkingMemory;
import org.drools.runtime.rule.FactHandle;
import org.mvel2.MVEL;

public class ModifyCommand
    implements
    Command<Object> {

    /**
     * if this is true, modify can be any MVEL expressions. If false, it will only allow literal values.
     * (false should be use when taking input from an untrusted source, such as a web service).
     */
    public static boolean ALLOW_MODIFY_EXPRESSIONS = true;


    private FactHandle       handle;
    private List<Setter> setters;


    public ModifyCommand(FactHandle handle,
                         List<Setter> setters) {
        this.handle = handle;
        this.setters = setters;
    }

    public Object execute(ReteooWorkingMemory session) {
        Object object = session.getObject( this.handle );
        MVEL.eval( getMvelExpr(),
                   object );

        session.update( handle,
                        object );
        return object;
    }

    public FactHandle getFactHandle() {
        return this.handle;
    }

    public List<Setter> getSetters() {
        return this.setters;
    }

    private String getMvelExpr() {
        StringBuilder sbuilder = new StringBuilder();
        sbuilder.append( "with (this) {\n" );
        int i = 0;
        for ( Setter setter : this.setters ) {
            if ( i++ > 0 ) {
                sbuilder.append( "," );
            }
            if (ALLOW_MODIFY_EXPRESSIONS) {
                sbuilder.append( setter.getAccessor() + " = " + setter.getValue() + "\n" );
            } else {
                sbuilder.append( setter.getAccessor() + " = '" + setter.getValue().replace("\"", "") + "'\n" );
            }
        }
        sbuilder.append( "}" );
        return sbuilder.toString();
    }

    public String toString() {
        return "modify() " + getMvelExpr();
    }

    public static class SetterImpl
        implements
        Setter {
        private String accessor;
        private String value;

        public SetterImpl(String accessor,
                          String value) {
            this.accessor = accessor;
            this.value = value;
        }

        public String getAccessor() {
            return accessor;
        }

        public String getValue() {
            return value;
        }

    }
}
