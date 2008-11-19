package org.drools.examples.conway;

import java.io.InputStreamReader;
import java.io.Reader;

import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
import org.drools.StatefulSession;
import org.drools.WorkingMemory;
import org.drools.agent.RuleAgent;
import org.drools.compiler.PackageBuilder;
import org.drools.event.AgendaGroupPoppedEvent;
import org.drools.event.DefaultAgendaEventListener;
import org.drools.examples.conway.patterns.ConwayPattern;
import org.drools.runtime.StatefulKnowledgeSession;

/**
 * A <code>CellGrid</code> represents a grid of <code>Cell</code> objects.
 * <p/>
 * 
 * @author <a href="mailto:brown_j@ociweb.com">Jeff Brown</a>
 * @see Cell
 */
public class CellGridImpl implements CellGrid {

    private final Cell[][]     cells;

    private final StatefulKnowledgeSession    session;

    private final ConwayRuleDelegate delegate;

    /**
     * Constructs a CellGrid
     * 
     * @param rows
     *            number of rows in the grid
     * @param columns
     *            number of columns in the grid
     */
    public CellGridImpl(final int rows,
                        final int columns,
                        final int executionControl) {

        this.cells = new Cell[rows][columns];

        if ( executionControl == AbstractRunConway.RULEFLOWGROUP ) {
            delegate = new RuleFlowDelegate();
        } else {
            delegate = new AgendaGroupDelegate();
        }
        
        this.session = delegate.getSession();

        this.session.insert( this );

        // populate the array of Cells and hook each
        // cell up with its neighbors...
        for ( int row = 0; row < rows; row++ ) {
            for ( int column = 0; column < columns; column++ ) {
                final Cell newCell = new Cell( column,
                                               row );
                this.cells[row][column] = newCell;
                this.session.insert( newCell );
            }
        }

        delegate.init();        
        //delegate.killAll();        
    }

    /* (non-Javadoc)
     * @see org.drools.examples.conway.CellGrid#getCellAt(int, int)
     */
    /* (non-Javadoc)
	 * @see org.drools.examples.conway.CellGrid#getCellAt(int, int)
	 */
    public Cell getCellAt(final int row,
                          final int column) {
        return this.cells[row][column];
    }

    /* (non-Javadoc)
     * @see org.drools.examples.conway.CellGrid#getNumberOfRows()
     */
    /* (non-Javadoc)
	 * @see org.drools.examples.conway.CellGrid#getNumberOfRows()
	 */
    public int getNumberOfRows() {
        return this.cells.length;
    }

    /* (non-Javadoc)
     * @see org.drools.examples.conway.CellGrid#getNumberOfColumns()
     */
    /* (non-Javadoc)
	 * @see org.drools.examples.conway.CellGrid#getNumberOfColumns()
	 */
    public int getNumberOfColumns() {
        return this.cells[0].length;
    }

    /* (non-Javadoc)
     * @see org.drools.examples.conway.CellGrid#nextGeneration()
     */
    /* (non-Javadoc)
	 * @see org.drools.examples.conway.CellGrid#nextGeneration()
	 */
    public boolean nextGeneration() {
        return delegate.nextGeneration();
    }

    /* (non-Javadoc)
     * @see org.drools.examples.conway.CellGrid#killAll()
     */
    /* (non-Javadoc)
	 * @see org.drools.examples.conway.CellGrid#killAll()
	 */
    public void killAll() {
        this.delegate.killAll();
    }

    /* (non-Javadoc)
     * @see org.drools.examples.conway.CellGrid#setPattern(org.drools.examples.conway.patterns.ConwayPattern)
     */
    /* (non-Javadoc)
	 * @see org.drools.examples.conway.CellGrid#setPattern(org.drools.examples.conway.patterns.ConwayPattern)
	 */
    public void setPattern(final ConwayPattern pattern) {
        final boolean[][] gridData = pattern.getPattern();
        int gridWidth = gridData[0].length;
        int gridHeight = gridData.length;

        int columnOffset = 0;
        int rowOffset = 0;

        if ( gridWidth > getNumberOfColumns() ) {
            gridWidth = getNumberOfColumns();
        } else {
            columnOffset = (getNumberOfColumns() - gridWidth) / 2;
        }

        if ( gridHeight > getNumberOfRows() ) {
            gridHeight = getNumberOfRows();
        } else {
            rowOffset = (getNumberOfRows() - gridHeight) / 2;
        }

        this.delegate.killAll();

        for ( int column = 0; column < gridWidth; column++ ) {
            for ( int row = 0; row < gridHeight; row++ ) {
                if ( gridData[row][column] ) {
                    final Cell cell = getCellAt( row + rowOffset,
                                                 column + columnOffset );
                    updateCell( cell, CellState.LIVE );
                }
            }
        }

        //this.delegate.setPattern();
    }
    
    public void updateCell(Cell cell, CellState state) {
        cell.setCellState( state );
        this.session.update( this.session.getFactHandle( cell ),
                             cell );                
    }

    /* (non-Javadoc)
     * @see org.drools.examples.conway.CellGrid#dispose()
     */
    /* (non-Javadoc)
	 * @see org.drools.examples.conway.CellGrid#dispose()
	 */
    public void dispose() {
        if ( this.session != null ) {
            this.session.dispose();
        }
    }

    /* (non-Javadoc)
	 * @see org.drools.examples.conway.CellGrid#toString()
	 */
    public String toString() {
        StringBuffer buf = new StringBuffer();

        for ( int i = 0; i < this.cells.length; i++ ) {
            for ( int j = 0; j < this.cells[i].length; j++ ) {
                Cell cell = this.cells[i][j];
                System.out.print( cell.getLiveNeighbors() + ((cell.getCellState() == CellState.DEAD) ? "D" : "L") + " " );
            }
            System.out.println( "" );
        }

        return buf.toString();
    }
}
