package org.drools.decisiontable.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.drools.template.model.SnippetBuilder;

/**
 * This utility will build up a list of constraints for a column.
 * For instance, the column has been spanned across multiple cells, and the cells below
 * contain the constraints.
 * @author Michael Neale
 *
 */
public class LhsBuilder implements SourceBuilder {

    private String column;
    private Map<Integer, String> constraints;
    private List<String> values;
    private boolean hasValues;
    private static Set<String> operators;

    static {
        operators = new HashSet<String>();
        operators.add( "==" );
        operators.add( "=" );
        operators.add( "!=" );
        operators.add( "<" );
        operators.add( ">" );
        operators.add( "<=" );
        operators.add( ">=" );
        operators.add( "contains" );
        operators.add( "matches" );
    }
    
    
    /**
     * @param colDefinition The initial column definition that is shared via merged cells.
     */
    public LhsBuilder(String colDefinition) {
        this.column = colDefinition == null ? "" : colDefinition;
        this.constraints = new HashMap<Integer, String>();
        this.values = new ArrayList<String>();
    }

    public void addTemplate(int column, String content) {
        Integer key = new Integer( column );
        content = content.trim();
        if ( constraints.containsKey( key ) ) {
            throw new IllegalArgumentException( "Internal error: Can't have a constraint added twice to one spreadsheet col." );
        }
        
        //we can wrap all values in quotes, it all works
        FieldType fieldType = calcFieldType( content );
        if (fieldType == FieldType.NORMAL_FIELD || !isMultipleConstraints()) {
            constraints.put( key,
                             content );            
        } else if (fieldType == FieldType.SINGLE_FIELD) {
            constraints.put( key, content + " == \"$param\"" );
        } else if (fieldType == FieldType.OPERATOR_FIELD) {
            constraints.put( key, content + " \"$param\"" );
        }
        
    }
    
    public void clearValues() {
        this.hasValues = false;
        this.values.clear();
    }

    public void addCellValue(int col,
                             String value) {
        this.hasValues = true;
        Integer key = new Integer( col );
        String content = (String) this.constraints.get( key );

        SnippetBuilder snip = new SnippetBuilder( content );
        String result = snip.build( value );
        this.values.add( result );

    }

    public String getResult() {
        StringBuffer buf = new StringBuffer();
        if ( !isMultipleConstraints() ) {
            for ( Iterator<String> iter = values.iterator(); iter.hasNext(); ) {
                String content = iter.next();
                buf.append( content );
                if (iter.hasNext()) {
                    buf.append( '\n' );
                }
            }
            return buf.toString();
        } else {
            buf.append( this.column );
            buf.append( '(' );
            for ( Iterator<String> iter = values.iterator(); iter.hasNext(); ) {
                buf.append( iter.next());
                if (iter.hasNext()) {
                    buf.append( ", " );
                }
                
            }
            buf.append( ')' );
            return buf.toString();
        }
    }

    /** Returns true if this is building up multiple constraints as in:
     * Foo(a ==b, c == d) etc...
     * If not, then it it really just like the "classic" style DTs.
     */
    boolean isMultipleConstraints() {
        if ( "".equals( column ) ) {
            return false;
        } else {
            if ( column.endsWith( ")" ) ) {
                return false;
            } else {
                return true;
            }
        }
    }

    /**
     * Work out the type of "field" that is being specified, 
     * as in :
     * age 
     * age <
     * age == $param
     * 
     * etc. as we treat them all differently.
     */
    public FieldType calcFieldType(String content) {
        if (content.indexOf( "$param" ) != -1 || content.indexOf( "$1" ) != -1 ) {
            return FieldType.NORMAL_FIELD;
        }
        for ( String op : operators ) {
            if (content.endsWith( op )) {
                return FieldType.OPERATOR_FIELD;
            }            
        }
        return FieldType.SINGLE_FIELD;
    }
    
    static class FieldType {
        public static final FieldType SINGLE_FIELD = new FieldType();
        public static final FieldType OPERATOR_FIELD = new FieldType();
        public static final FieldType NORMAL_FIELD = new FieldType();
    }

    public boolean hasValues() {
        return hasValues;
        
    }

}
