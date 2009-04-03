package org.drools.impl;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.drools.definition.rule.Rule;

public class SerializedRule
    implements
    Rule,
    Externalizable {
    private String name;
    private String packageName;
    private Map<String, String> metaAttributes;
    
    public SerializedRule() {
        
    }
    
    public SerializedRule(Rule rule) {
        this.name = rule.getName();
        this.packageName = rule.getPackageName();
        Collection<String> identifiers = rule.listMetaAttributes();
        this.metaAttributes = new HashMap<String, String>(identifiers.size());
        for ( String identifier : identifiers ) {
            this.metaAttributes.put( identifier, rule.getMetaAttribute( identifier ) );
        }
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF( name );
        out.writeUTF( packageName );
        out.writeObject( this.metaAttributes );
    }

    public void readExternal(ObjectInput in) throws IOException,
                                            ClassNotFoundException {
        name = in.readUTF();
        packageName = in.readUTF();
        this.metaAttributes = ( Map<String, String> ) in.readObject();
    }

    public String getName() {
        return this.name;
    }

    public String getPackageName() {
        return this.packageName;
    }
    
    public String getMetaAttribute(String identifier) {
        return this.metaAttributes.get( identifier );
    }

    public Collection<String> listMetaAttributes() {
        return this.metaAttributes.keySet();
    }    

}
