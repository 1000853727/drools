package org.drools.template.model;

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

/*
 * @author: salaboy
 */
import junit.framework.TestCase;

public class QueriesRenderTest1 extends TestCase {

    public void testQueriesRender() {
        final Queries queries = new Queries();

        DRLOutput out = new DRLOutput();
        queries.renderDRL( out );

        assertEquals( "",
                      out.toString() );

        queries.setQueriesListing( "query myQuery(String value) Person() end" );
        out = new DRLOutput();
        queries.renderDRL( out );
        final String s = out.toString();
        assertEquals( "query myQuery(String value) Person() end\n",
                      s );
    }

}
