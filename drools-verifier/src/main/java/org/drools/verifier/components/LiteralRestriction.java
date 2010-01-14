package org.drools.verifier.components;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.zip.DataFormatException;

import org.drools.verifier.report.components.Cause;

/**
 * 
 * @author Toni Rikkola
 */
public class LiteralRestriction extends Restriction
    implements
    Cause {

    private String  valueType;

    private boolean booleanValue;

    private int     intValue;

    private double  doubleValue;

    private String  stringValue;

    private Date    dateValue;

    public RestrictionType getRestrictionType() {
        return Restriction.RestrictionType.LITERAL;
    }

    /**
     * Compares two LiteralRestrictions by value.
     * 
     * @param restriction
     *            Restriction that this object is compared to.
     * @return a negative integer, zero, or a positive integer as this object is
     *         less than, equal to, or greater than the specified object.
     * @throws DataFormatException
     *             If data was not supported.
     */
    public int compareValues(LiteralRestriction restriction) throws DataFormatException {
        if ( !restriction.getValueType().equals( valueType ) ) {
            throw new DataFormatException( "Value types did not match. Value type " + restriction.getValueType() + " was compared to " + valueType );
        }

        if ( valueType == Field.DATE ) {
            return dateValue.compareTo( restriction.getDateValue() );
        } else if ( valueType == Field.DOUBLE ) {
            if ( doubleValue > restriction.getDoubleValue() ) {
                return 1;
            } else if ( doubleValue < restriction.getDoubleValue() ) {
                return -1;
            } else {
                return 0;
            }
        } else if ( valueType == Field.INT ) {
            if ( intValue > restriction.getIntValue() ) {
                return 1;
            } else if ( intValue < restriction.getIntValue() ) {
                return -1;
            } else {
                return 0;
            }
        } else if ( valueType == Field.STRING ) {
            return stringValue.compareTo( restriction.getValueAsString() );
        } else if ( valueType == Field.UNKNOWN ) {
            return 0;
        }

        throw new DataFormatException( "Value types did not match. Value type " + restriction.getValueType() + " was compared to " + valueType );
    }

    public Object getValueAsObject() {
        if ( valueType == Field.BOOLEAN ) {
            return Boolean.valueOf( booleanValue );
        } else if ( valueType == Field.DATE ) {
            return dateValue;
        } else if ( valueType == Field.DOUBLE ) {
            return Double.valueOf( doubleValue );
        } else if ( valueType == Field.INT ) {
            return Integer.valueOf( intValue );
        }
        return stringValue;
    }

    public String getValueAsString() {
        return stringValue;
    }

    public double getDoubleValue() {
        return doubleValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public String getValueType() {
        return valueType;
    }

    public Date getDateValue() {
        return dateValue;
    }

    public void setValue(String value) {

        if ( value == null ) {
            stringValue = null;
            valueType = Field.UNKNOWN;
            return;
        }

        stringValue = value;
        valueType = Field.STRING;

        if ( "true".equals( value ) || "false".equals( value ) ) {
            booleanValue = value.equals( "true" );
            valueType = Field.BOOLEAN;
            stringValue = value;
        }

        try {
            intValue = Integer.parseInt( value );
            valueType = Field.INT;
            stringValue = value;
            return;
        } catch ( NumberFormatException e ) {
            // Not int.
        }

        try {
            doubleValue = Double.parseDouble( value );
            valueType = Field.DOUBLE;
            stringValue = value;
            return;
        } catch ( NumberFormatException e ) {
            // Not double.
        }

        try {
            // TODO: Get this from config.
            String fmt = System.getProperty( "drools.dateformat" );
            if ( fmt == null ) {
                fmt = "dd-MMM-yyyy";
            }

            dateValue = new SimpleDateFormat( fmt,
                                              Locale.ENGLISH ).parse( value );
            valueType = Field.DATE;
            stringValue = value;
            return;
        } catch ( Exception e ) {
            // Not a date.
        }

    }

    public boolean getBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    @Override
    public String toString() {
        return "LiteralRestriction from rule [" + getRuleName() + "] value '" + operator.getOperatorString() + " " + stringValue + "'";
    }
}
