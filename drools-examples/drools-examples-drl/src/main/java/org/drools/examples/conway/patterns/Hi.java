package org.drools.examples.conway.patterns;

/**
 * The Hi pattern <p/>
 * 
 * @see ConwayPattern
 * @see org.drools.examples.conway.CellGridImpl
 * 
 * @author <a href="mailto:brown_j@ociweb.com">Jeff Brown</a>
 */
public class Hi
    implements
    ConwayPattern {

    private final boolean[][] grid = {{true, false, false, false, true, false, false, true, true, true, true, true, true, true}, {true, false, false, false, true, false, false, false, false, false, true, false, false, false},
            {true, false, false, false, true, false, false, false, false, false, true, false, false, false}, {true, false, false, false, true, false, false, false, false, false, true, false, false, false},
            {true, true, true, true, true, false, false, false, false, false, true, false, false, false}, {true, false, false, false, true, false, false, false, false, false, true, false, false, false},
            {true, false, false, false, true, false, false, false, false, false, true, false, false, false}, {true, false, false, false, true, false, false, false, false, false, true, false, false, false},
            {true, false, false, false, true, false, false, true, true, true, true, true, true, true}};

    /**
     * This method should return a 2 dimensional array of boolean that represent
     * a conway grid, with <code>true</code> values in the positions where
     * cells are alive
     * 
     * @return array representing a conway grid
     */
    public boolean[][] getPattern() {
        return this.grid;
    }

    /**
     * @return the name of this pattern
     */
    public String getPatternName() {
        return "Hi";
    }

    public String toString() {
        return getPatternName();
    }
}
