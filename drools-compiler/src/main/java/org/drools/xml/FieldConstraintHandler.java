package org.drools.xml;

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

import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;

import org.drools.lang.descr.AndDescr;
import org.drools.lang.descr.ConditionalElementDescr;
import org.drools.lang.descr.FieldBindingDescr;
import org.drools.lang.descr.FieldConstraintDescr;
import org.drools.lang.descr.OrDescr;
import org.drools.lang.descr.PatternDescr;
import org.drools.lang.descr.PredicateDescr;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author mproctor
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
class FieldConstraintHandler extends BaseAbstractHandler
    implements
    Handler {
    FieldConstraintHandler(final XmlPackageReader xmlPackageReader) {
        this.xmlPackageReader = xmlPackageReader;

        if ( (this.validParents == null) && (this.validPeers == null) ) {
            this.validParents = new HashSet();
            this.validParents.add( PatternDescr.class );
            this.validParents.add( AndDescr.class );
            this.validParents.add( OrDescr.class );

            this.validPeers = new HashSet();
            this.validPeers.add( null );
            this.validPeers.add( FieldConstraintDescr.class );
            this.validPeers.add( PredicateDescr.class );
            this.validPeers.add( FieldBindingDescr.class );

            this.validPeers.add( AndDescr.class );
            this.validPeers.add( OrDescr.class );

            this.allowNesting = false;
        }
    }

    public Object start(final String uri,
                        final String localName,
                        final Attributes attrs) throws SAXException {
        this.xmlPackageReader.startConfiguration( localName,
                                                  attrs );

        final String fieldName = attrs.getValue( "field-name" );

        if ( fieldName == null || fieldName.trim().equals( "" ) ) {
            throw new SAXParseException( "<field-constraint> requires a 'field-name' attribute",
                                         this.xmlPackageReader.getLocator() );
        }

        final FieldConstraintDescr fieldConstraint = new FieldConstraintDescr( fieldName );

        return fieldConstraint;
    }

    public Object end(final String uri,
                      final String localName) throws SAXException {

        final Configuration config = this.xmlPackageReader.endConfiguration();

        final FieldConstraintDescr fieldConstraintDescr = (FieldConstraintDescr) this.xmlPackageReader.getCurrent();

        final LinkedList parents = this.xmlPackageReader.getParents();
        final ListIterator it = parents.listIterator( parents.size() );
        it.previous();
        final Object parent = it.previous();

        if ( parent instanceof PatternDescr ) {
            final PatternDescr patternDescr = (PatternDescr) parent;
            patternDescr.addConstraint( fieldConstraintDescr );
        } else if ( parent instanceof ConditionalElementDescr ) {
            final ConditionalElementDescr ceDescr = (ConditionalElementDescr) parent;
            final FieldConstraintDescr field = (FieldConstraintDescr) this.xmlPackageReader.getCurrent();
            ceDescr.addOrMerge( field );
        }

        return null;
    }

    public Class generateNodeFor() {
        return FieldConstraintDescr.class;
    }
}