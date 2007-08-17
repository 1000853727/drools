package org.drools.decisiontable.parser.xls;

/*
 * Copyright 2005 JBoss Inc
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jxl.Cell;
import jxl.Range;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.drools.decisiontable.parser.DecisionTableParseException;
import org.drools.decisiontable.parser.DecisionTableParser;
import org.drools.decisiontable.parser.SheetListener;

/**
 * @author <a href="mailto:michael.neale@gmail.com"> Michael Neale </a>
 * Parse an excel spreadsheet, pusing cell info into the SheetListener interface.
 */
public class ExcelParser
    implements
    DecisionTableParser {

    public static final String DEFAULT_RULESHEET_NAME = "Decision Tables";
    private Map                _listners              = new HashMap();
    private boolean            _useFirstSheet;

    /**
     * Define a map of sheet name to listner handlers.
     * 
     * @param sheetListners
     *            map of String to SheetListener
     */
    public ExcelParser(final Map sheetListners) {
        this._listners = sheetListners;
    }

    public ExcelParser(final List sheetListners) {
        this._listners.put( ExcelParser.DEFAULT_RULESHEET_NAME,
                            sheetListners );
        this._useFirstSheet = true;
    }

    public ExcelParser(final SheetListener listener) {
        List listeners = new ArrayList();
        listeners.add( listener );
        this._listners.put( ExcelParser.DEFAULT_RULESHEET_NAME,
                            listeners );
        this._useFirstSheet = true;
    }

    public void parseFile(InputStream inStream) {
        try {
            Workbook workbook = Workbook.getWorkbook( inStream );

            if ( _useFirstSheet ) {
                Sheet sheet = workbook.getSheet( 0 );
                processSheet( sheet,
                              (List) _listners.get( DEFAULT_RULESHEET_NAME ) );
            } else {
                Set sheetNames = _listners.keySet();
                for ( Iterator iter = sheetNames.iterator(); iter.hasNext(); ) {
                    String sheetName = (String) iter.next();
                    Sheet sheet = workbook.getSheet( sheetName );
                    processSheet( sheet,
                                  (List) _listners.get( sheetName ) );

                }
            }
        } catch ( BiffException e ) {
            throw new DecisionTableParseException( "An error occured opening the workbook. ",
                                                   e );

        } catch ( IOException e ) {
            throw new DecisionTableParseException( "Failed to open Excel stream, " + "please check that the content is xls97 format.",
                                                   e );
        }

    }

    private void processSheet(Sheet sheet,
                              List listeners) {
        int maxRows = sheet.getRows();

        Range[] mergedRanges = sheet.getMergedCells();

        for ( int i = 0; i < maxRows; i++ ) {
            Cell[] row = sheet.getRow( i );
            newRow( listeners,
                    i,
                    row.length );
            for ( int cellNum = 0; cellNum < row.length; cellNum++ ) {
                Cell cell = row[cellNum];

                Range merged = getRangeIfMerged( cell,
                                                 mergedRanges );

                if ( merged != null ) {
                    Cell topLeft = merged.getTopLeft();
                    newCell( listeners,
                             i,
                             cellNum,
                             topLeft.getContents(),
                             topLeft.getColumn() );
                } else {
                    newCell( listeners,
                             i,
                             cellNum,
                             cell.getContents(),
                             SheetListener.NON_MERGED );
                }
            }
        }
        finishSheet( listeners );
    }

    Range getRangeIfMerged(Cell cell,
                           Range[] mergedRanges) {
        for ( int i = 0; i < mergedRanges.length; i++ ) {
            Range r = mergedRanges[i];
            Cell topLeft = r.getTopLeft();
            Cell bottomRight = r.getBottomRight();
            if ( cell.getRow() >= topLeft.getRow() && cell.getRow() <= bottomRight.getRow() && cell.getColumn() >= topLeft.getColumn() && cell.getColumn() <= bottomRight.getColumn() ) {
                return r;
            }
        }
        return null;
    }

    static String removeTrailingZero(String stringVal) {
        if ( stringVal.endsWith( ".0" ) ) {
            stringVal = stringVal.substring( 0,
                                             stringVal.length() - 2 );
        }
        return stringVal;
    }

    private void finishSheet(List listeners) {
        for ( Iterator it = listeners.iterator(); it.hasNext(); ) {
            SheetListener listener = (SheetListener) it.next();
            listener.finishSheet();
        }
    }

    private void newRow(List listeners,
                        int row,
                        int cols) {
        for ( Iterator it = listeners.iterator(); it.hasNext(); ) {
            SheetListener listener = (SheetListener) it.next();
            listener.newRow( row,
                             cols );
        }
    }

    public void newCell(List listeners,
                        int row,
                        int column,
                        String value,
                        int mergedColStart) {
        for ( Iterator it = listeners.iterator(); it.hasNext(); ) {
            SheetListener listener = (SheetListener) it.next();
            listener.newCell( row,
                              column,
                              value,
                              mergedColStart );
        }
    }

}