package org.drools.integrationtests;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.Cheese;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;

/**
 * This is a sample class to launch a rule.
 */
public class DateComparisonTest extends TestCase {

    public void testDateComparisonThan() throws Exception {
        List<String> results = new ArrayList<String>();
        // load up the knowledge base
        String str = "";
        str += "package org.drools;\n";
        str += "dialect \"mvel\"\n";
        str += "global java.util.List results;\n";
        str += "rule \"test date greater than\"\n";
        str += "     when\n";
        str += "         $c : Cheese(type == \"Yesterday\")\n";
        str += "         Cheese(type == \"Tomorrow\",  usedBy > ($c.usedBy))\n";
        str += "     then\n";
        str += "         results.add( \"test date greater than\" );\n";
        str += "end\n";

        str += "rule \"test date less than\"\n";
        str += "    when\n";
        str += "        $c : Cheese(type == \"Tomorrow\")\n";
        str += "        Cheese(type == \"Yesterday\", usedBy < ($c.usedBy));\n";
        str += "    then\n";
        str += "        results.add( \"test date less than\" );\n";
        str += "end\n";

        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add( ResourceFactory.newByteArrayResource( str.getBytes() ),
                      ResourceType.DRL );
        KnowledgeBuilderErrors errors = kbuilder.getErrors();
        if ( kbuilder.hasErrors() ) {
            fail( kbuilder.getErrors().toString() );
        }
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages( kbuilder.getKnowledgePackages() );
        
        StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
        ksession.setGlobal( "results",
                            results );
        KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger( ksession,
                                                                                     "test" );
        // go !
        Cheese yesterday = new Cheese( "Yesterday" );
        yesterday.setUsedBy( yesterday() );
        Cheese tomorrow = new Cheese( "Tomorrow" );
        tomorrow.setUsedBy( tomorrow() );
        ksession.insert( yesterday );
        ksession.insert( tomorrow );
        ksession.fireAllRules();
        logger.close();
        assertEquals( 2,
                      results.size() );
        assertTrue( results.contains( "test date greater than" ) );
        assertTrue( results.contains( "test date less than" ) );
    }

    private Date yesterday() {
        Calendar c = new GregorianCalendar();
        c.set( Calendar.DAY_OF_MONTH,
               c.get( Calendar.DAY_OF_MONTH ) - 1 );
        return c.getTime();
    }

    private Date tomorrow() {
        Calendar c = new GregorianCalendar();
        c.set( Calendar.DAY_OF_MONTH,
               c.get( Calendar.DAY_OF_MONTH ) + 1 );
        return c.getTime();
    }

}