package org.drools.decisiontable.parser.csv;

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

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.drools.decisiontable.parser.SheetListener;

public class CsvParserTest extends TestCase {

    public void testCsv() {
        final MockSheetListener listener = new MockSheetListener();
        final CsvLineParser lineParser = new CsvLineParser();
        final CsvParser parser = new CsvParser( listener,
                                          lineParser );

        parser.parseFile( getClass().getResourceAsStream( "/data/TestCsv.csv" ) );
        assertEquals( "A",
                      listener.getCell( 0,
                                        0 ) );
        assertEquals( "B",
                      listener.getCell( 0,
                                        1 ) );
        assertEquals( "",
                      listener.getCell( 2,
                                        0 ) );
        assertEquals( "C",
                      listener.getCell( 1,
                                        0 ) );
        assertEquals( "D",
                      listener.getCell( 1,
                                        1 ) );
        assertEquals( "E",
                      listener.getCell( 1,
                                        3 ) );

    }
    
    /**
     * Test the handling of merged cells.
     */
    public void testCellMergeHandling() {
        CsvParser parser = new CsvParser((SheetListener)null, null);
        assertEquals(SheetListener.NON_MERGED, parser.calcStartMerge( SheetListener.NON_MERGED, 1, "foo" ));
        assertEquals(42, parser.calcStartMerge( SheetListener.NON_MERGED, 42, "..." ));
        
        assertEquals(42, parser.calcStartMerge( 42, 43, "..." ));
        
        assertEquals(SheetListener.NON_MERGED, parser.calcStartMerge( 42, 44, "VanHalen" ));
        
        
        assertEquals("VanHalen", parser.calcCellText( SheetListener.NON_MERGED, "VanHalen" ));
        assertEquals("VanHalen", parser.calcCellText( 42, "VanHalen..." ));
        assertEquals("", parser.calcCellText( 42, "..." ));
        
        
    }

    static class MockSheetListener
        implements
        SheetListener {

        Map data = new HashMap();

        public String getCell(final int row,
                              final int col) {
            return (String) this.data.get( cellKey( row,
                                               col ) );
        }

        public void startSheet(final String name) {
        }

        public void finishSheet() {
        }

        public void newRow(final int rowNumber,
                           final int columns) {

        }

        public void newCell(final int row,
                            final int column,
                            final String value,
                            final int mergeCellStart) {

            this.data.put( cellKey( row,
                               column ),
                      value );
        }

        String cellKey(final int row,
                       final int column) {
            return "R" + row + "C" + column;
        }

    }
}