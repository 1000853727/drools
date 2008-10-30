package org.drools;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.drools.rule.Declaration;
import org.drools.rule.Query;
import org.drools.WorkingMemory;
import org.drools.spi.Tuple;

/**
 * Returned QueryResults instance for a requested named query. from here you can iterate the returned data, or
 * get a specific row. All the available Declarations used in the query can also be accessed.
 *
 */
public class QueryResults {
    private Query           query;

    private Map             declarations;

    protected List          results;
    protected WorkingMemory workingMemory;

    public QueryResults(final List results,
                        final Query query,
                        final WorkingMemory workingMemory) {
        this.results = results;
        this.query = query;
        this.workingMemory = workingMemory;
    }

    public QueryResult get(final int i) {
        if ( i > this.results.size() ) {
            throw new NoSuchElementException();
        }
        return new QueryResult( (Tuple) this.results.get( i ),
                                this.workingMemory,
                                this );
    }

    /**
     * Returns an Iterator for the results.
     * 
     * @return
     */
    public Iterator iterator() {
        return new QueryResultsIterator( this.results.iterator() );
    }

    /**
     * Return a map of Declarations where the key is the identifier and the value
     * is the Declaration.
     * 
     * @return
     *      The Map of Declarations.
     */
    public Map getDeclarations() {

        final Declaration[] declarations = this.query.getDeclarations();
        final Map map = new HashMap( declarations.length );
        for ( int i = 0, length = declarations.length; i < length; i++ ) {
            map.put( declarations[i].getIdentifier(),
                     declarations[i] );
        }
        this.declarations = map;

        return this.declarations;
    }

    /**
     * The results size
     * @return
     */
    public int size() {
        return this.results.size();
    }

    private class QueryResultsIterator
        implements
        Iterator {
        private Iterator iterator;

        public QueryResultsIterator(final Iterator iterator) {
            this.iterator = iterator;
        }

        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        public Object next() {
            return new QueryResult( (Tuple) this.iterator.next(),
                                    QueryResults.this.workingMemory,
                                    QueryResults.this );
        }

        public void remove() {
            this.iterator.remove();
        }

    }
}
