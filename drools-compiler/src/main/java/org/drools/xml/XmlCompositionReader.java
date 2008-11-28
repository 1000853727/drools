package org.drools.xml;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import javax.xml.parsers.SAXParser;

import org.drools.compiler.KnowledgeComposition;
import org.drools.lang.descr.PackageDescr;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class XmlCompositionReader {
    private ExtensibleXmlParser parser;

    public XmlCompositionReader(final SemanticModules modules) {
        this( modules, null );
    }

    public XmlCompositionReader(final SemanticModules modules, final SAXParser parser) {
        if ( parser == null ) {
            this.parser = new ExtensibleXmlParser();
        } else {
            this.parser = new ExtensibleXmlParser( parser );
        }      
        this.parser.setSemanticModules( modules );
    }
    
    public ExtensibleXmlParser getParser() {
        return this.parser;
    }

    /**
     * Read a <code>RuleSet</code> from a <code>Reader</code>.
     *
     * @param reader
     *            The reader containing the rule-set.
     *
     * @return The rule-set.
     */
    public KnowledgeComposition read(final Reader reader) throws SAXException,
                                                 IOException {
        return (KnowledgeComposition) this.parser.read( reader );
    }

    /**
     * Read a <code>RuleSet</code> from an <code>InputStream</code>.
     *
     * @param inputStream
     *            The input-stream containing the rule-set.
     *
     * @return The rule-set.
     */
    public KnowledgeComposition read(final InputStream inputStream) throws SAXException,
                                                           IOException {
        return (KnowledgeComposition) this.parser.read( inputStream );
    }

    /**
     * Read a <code>RuleSet</code> from an <code>InputSource</code>.
     *
     * @param in
     *            The rule-set input-source.
     *
     * @return The rule-set.
     */
    public KnowledgeComposition read(final InputSource in) throws SAXException,
                                                  IOException {
        return (KnowledgeComposition) this.parser.read( in );
    }
}
