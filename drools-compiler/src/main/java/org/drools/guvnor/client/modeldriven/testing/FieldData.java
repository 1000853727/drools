package org.drools.guvnor.client.modeldriven.testing;

import org.drools.guvnor.client.modeldriven.brl.PortableObject;

public class FieldData implements PortableObject {

    /** the name of the field */
    public String name;

    /** The value of the field to be set to.
     * This will either be a literal value (which will be coerced by MVEL).
     * Or if it starts with an "=" then it is an EL that will be evaluated to yield a value.
     */
    public String value;


    public FieldData() {}
    public FieldData(String name, String value) {
        this.name = name;
        this.value = value;
    }



}
