/**
 * 
 */
package org.drools.rule.builder.dialect.java.parser;


/**
 * A helper class used during java code parsing to identify
 * and handle exitPoints calls
 * 
 * @author etirelli
 */
public class JavaInterfacePointsDescr implements JavaBlockDescr {
    
    private int start;
    private int end;
    private String id;
    private BlockType type;
    
    public JavaInterfacePointsDescr( String id ) {
        this.id = id;
    }
    
    /* (non-Javadoc)
     * @see org.drools.rule.builder.dialect.java.parser.JavaBlockDescr#getStart()
     */
    public int getStart() {
        return start;
    }
    public void setStart(int start) {
        this.start = start;
    }
    /* (non-Javadoc)
     * @see org.drools.rule.builder.dialect.java.parser.JavaBlockDescr#getEnd()
     */
    public int getEnd() {
        return end;
    }
    public void setEnd(int end) {
        this.end = end;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    
    public String toString() {
        return "ExitPoints( type="+type+" start="+start+" end="+end+" id="+id+" )";
    }

    public BlockType getType() {
        return type;
    }

    public void setType(BlockType type) {
        this.type = type;
    }

}
