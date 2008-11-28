package org.drools.xml.composition;

import java.util.HashSet;

import org.drools.builder.DecisionTableConfiguration;
import org.drools.builder.DecisionTableInputType;
import org.drools.builder.ResourceConfiguration;
import org.drools.builder.impl.DecisionTableConfigurationImpl;
import org.drools.compiler.KnowledgeComposition;
import org.drools.compiler.KnowledgeResource;
import org.drools.util.StringUtils;
import org.drools.xml.BaseAbstractHandler;
import org.drools.xml.ExtensibleXmlParser;
import org.drools.xml.Handler;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class DecisionTableConfigurationHandler extends BaseAbstractHandler
    implements
    Handler {

    public DecisionTableConfigurationHandler() {
        if ( (this.validParents == null) && (this.validPeers == null) ) {
            this.validParents = new HashSet( 1 );
            this.validParents.add( KnowledgeResource.class );

            this.validPeers = new HashSet( 1 );
            this.validPeers.add( null );

            this.allowNesting = false;
        }
    }

    public Object start(String uri,
                        String localName,
                        Attributes attrs,
                        ExtensibleXmlParser parser) throws SAXException {
        parser.startElementBuilder( localName,
                                    attrs );

        String type = attrs.getValue( "input-type" );
        String worksheetName = attrs.getValue( "worksheet-name" );

        emptyAttributeCheck( localName,
                             "input-type",
                             type,
                             parser );

        DecisionTableConfiguration dtConf = new DecisionTableConfigurationImpl();
        dtConf.setInputType( DecisionTableInputType.valueOf( type ) );
        if ( !StringUtils.isEmpty( worksheetName ) ) {
            dtConf.setWorksheetName( worksheetName );
        }
        
        return dtConf;
    }

    public Object end(String uri,
                      String localName,
                      ExtensibleXmlParser parser) throws SAXException {
        final Element element = parser.endElementBuilder();
        final KnowledgeResource part = (KnowledgeResource) parser.getParent();
        ResourceConfiguration dtConf = (ResourceConfiguration) parser.getCurrent();
        part.setConfiguration( dtConf );
        
        return dtConf;
    }

    public Class< ? > generateNodeFor() {
        return ResourceConfiguration.class;
    }

}
