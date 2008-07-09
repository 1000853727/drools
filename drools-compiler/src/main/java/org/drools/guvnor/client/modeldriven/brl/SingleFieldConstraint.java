package org.drools.guvnor.client.modeldriven.brl;


/**
 * This represents a contraint on a fact - involving a SINGLE FIELD.
 * 
 * Can also include optional "connective constraints" that extend the options for matches.
 * @author Michael Neale
 */
public class SingleFieldConstraint extends ISingleFieldConstraint implements FieldConstraint {

    public String                 fieldBinding;
    public String                 fieldName;
    public String                 operator;
    public String                 fieldType;
    public FieldConstraint  parent;

    public ConnectiveConstraint[] connectives;

    public SingleFieldConstraint(final String field, final String fieldType, final FieldConstraint parent) {
        this.fieldName = field;
        this.fieldType = fieldType;
        this.parent = parent;
    }

    public SingleFieldConstraint(final String field) {
        this.fieldName = field;
        this.fieldType = "";
        this.parent = null;
    }

    public SingleFieldConstraint() {
        this.fieldName = null;
        this.fieldType = "";
        this.parent = null;
    }

    /**
     * This adds a new connective.
     *
     */
    public void addNewConnective() {
        if ( this.connectives == null ) {
            this.connectives = new ConnectiveConstraint[]{new ConnectiveConstraint()};
        } else {
            final ConnectiveConstraint[] newList = new ConnectiveConstraint[this.connectives.length + 1];
            for ( int i = 0; i < this.connectives.length; i++ ) {
                newList[i] = this.connectives[i];
            }
            newList[this.connectives.length] = new ConnectiveConstraint();
            this.connectives = newList;
        }
    }

    /**
     * Returns true of there is a field binding.
     */
    public boolean isBound() {
        if ( this.fieldBinding != null && !"".equals( this.fieldBinding ) ) {
            return true;
        } else {
            return false;
        }
    }

}
