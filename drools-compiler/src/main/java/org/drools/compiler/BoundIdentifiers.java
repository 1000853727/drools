package org.drools.compiler;

import java.util.Map;

public class BoundIdentifiers {
    private Map<String, Class<?>> declarations;
    private Map<String, Class<?>> globals;
    private Class<?> thisClass;
    
    public BoundIdentifiers(Map<String, Class<?>> declarations,
                            Map<String, Class<?>> globals) {
        this.declarations = declarations;
        this.globals = globals;
    }
    
    public BoundIdentifiers(Map<String, Class<?>> declarations,
                            Map<String, Class<?>> globals,
                            Class<?> thisClass) {
        this.declarations = declarations;
        this.globals = globals;
        this.thisClass  = thisClass;
    }
    
    public Map<String, Class<?>> getDeclarations() {
        return declarations;
    }
    public Map<String, Class<?>> getGlobals() {
        return globals;
    }
    
    public Class<?> getThisClass() {
        return thisClass;
    }
    
    public String toString() {
        StringBuilder sbuilder = new StringBuilder();
        sbuilder.append( "thisClass: " + thisClass );
        sbuilder.append( "declarations:" + declarations + "\n" );
        sbuilder.append( "globals:" + globals + "\n" );
        
        return sbuilder.toString();
    }
    
}
