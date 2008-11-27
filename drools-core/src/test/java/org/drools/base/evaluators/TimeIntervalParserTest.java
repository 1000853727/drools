/*
 * Copyright 2008 Red Hat
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
 *
 */
package org.drools.base.evaluators;

import junit.framework.TestCase;

/**
 * @author admin
 *
 */
public class TimeIntervalParserTest extends TestCase {

    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
    }

    /**
     * Test method for {@link org.drools.base.evaluators.TimeIntervalParser#parse(java.lang.String)}.
     */
    public void testParse() {
        String input = "2d10h49m10s789ms";
        long expected = 211750789;
        Long[] result = new TimeIntervalParser().parse( input );
        assertEquals( 1, result.length );
        assertEquals( expected, result[0].longValue() );
    }

    /**
     * Test method for {@link org.drools.base.evaluators.TimeIntervalParser#parse(java.lang.String)}.
     */
    public void testParse2() {
        String input = "10h49m789ms";
        long expected = 10 * 3600000 + 49 * 60000 + 789;
        Long[] result = new TimeIntervalParser().parse( input );
        assertEquals( 1, result.length );
        assertEquals( expected, result[0].longValue() );
    }

    /**
     * Test method for {@link org.drools.base.evaluators.TimeIntervalParser#parse(java.lang.String)}.
     */
    public void testParse3() {
        // ms are optional
        String input = " 10h49m789 , 12h ";
        long expected1 = 10 * 3600000 + 49 * 60000 + 789;
        long expected2 = 12 * 3600000;
        Long[] result = new TimeIntervalParser().parse( input );
        assertEquals( 2, result.length );
        assertEquals( expected1, result[0].longValue() );
        assertEquals( expected2, result[1].longValue() );
    }

    /**
     * Test method for {@link org.drools.base.evaluators.TimeIntervalParser#parse(java.lang.String)}.
     */
    public void testParse4() {
        // raw ms without the unit declared
        String input = " 15957, 3500000 ";
        long expected1 = 15957;
        long expected2 = 3500000;
        Long[] result = new TimeIntervalParser().parse( input );
        assertEquals( 2, result.length );
        assertEquals( expected1, result[0].longValue() );
        assertEquals( expected2, result[1].longValue() );
    }

    /**
     * Test method for {@link org.drools.base.evaluators.TimeIntervalParser#parse(java.lang.String)}.
     */
    public void testParse5() {
        // empty input
        String input = "";
        Long[] result = new TimeIntervalParser().parse( input );
        assertEquals( 0, result.length );
    }
    
    /**
     * Test method for {@link org.drools.base.evaluators.TimeIntervalParser#parse(java.lang.String)}.
     */
    public void testParse6() {
        // null input
        String input = null;
        Long[] result = new TimeIntervalParser().parse( input );
        assertEquals( 0, result.length );
    }
    
    /**
     * Test method for {@link org.drools.base.evaluators.TimeIntervalParser#parse(java.lang.String)}.
     */
    public void testParse7() {
        // empty input
        String input = "  ";
        Long[] result = new TimeIntervalParser().parse( input );
        assertEquals( 0, result.length );
    }
    
    /**
     * Test method for {@link org.drools.base.evaluators.TimeIntervalParser#parse(java.lang.String)}.
     */
    public void testParse8() {
        // raw ms without the unit declared
        String input = " -15957, 3500000 ";
        long expected1 = -15957;
        long expected2 = 3500000;
        Long[] result = new TimeIntervalParser().parse( input );
        assertEquals( 2, result.length );
        assertEquals( expected1, result[0].longValue() );
        assertEquals( expected2, result[1].longValue() );
    }

    /**
     * Test method for {@link org.drools.base.evaluators.TimeIntervalParser#parse(java.lang.String)}.
     */
    public void testParse9() {
        // ms are optional
        String input = " -10h49m789 , -8h ";
        long expected1 = -( 10 * 3600000 + 49 * 60000 + 789 );
        long expected2 = -( 8 * 3600000 );
        Long[] result = new TimeIntervalParser().parse( input );
        assertEquals( 2, result.length );
        assertEquals( expected1, result[0].longValue() );
        assertEquals( expected2, result[1].longValue() );
    }

}
