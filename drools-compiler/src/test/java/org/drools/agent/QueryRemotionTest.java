package org.drools.agent;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.KnowledgeBase;
import org.kie.KnowledgeBaseFactory;
import org.kie.agent.KnowledgeAgent;
import org.kie.io.ResourceFactory;
import org.kie.runtime.StatefulKnowledgeSession;
import org.kie.runtime.rule.QueryResults;

import static org.junit.Assert.*;

/**
 * Removes a query from kbase using kagent and incremental changeset build.
 */
public class QueryRemotionTest extends BaseKnowledgeAgentTest {

    @Test
    public void testRemoveQueryChangeSet() throws Exception {

        this.fileManager.write("rules.drl", this.createCommonQuery("all the Strings", new String[]{"$strings : String()"}));

        String xml = "";
        xml += "<change-set xmlns='http://drools.org/drools-5.0/change-set'";
        xml += "    xmlns:xs='http://www.w3.org/2001/XMLSchema-instance'";
        xml += "    xs:schemaLocation='http://drools.org/drools-5.0/change-set http://anonsvn.jboss.org/repos/labs/labs/jbossrules/trunk/drools-api/src/main/resources/change-set-1.0.0.xsd' >";
        xml += "    <add> ";
        xml += "        <resource source='http://localhost:" + this.getPort() + "/rules.drl' type='DRL' />";
        xml += "    </add> ";
        xml += "</change-set>";
        File fxml = fileManager.write( "changeset.xml",
                                       xml );

        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        KnowledgeAgent kagent = this.createKAgent(kbase, false);
        
        this.applyChangeSet(kagent, ResourceFactory.newUrlResource(fxml.toURI().toURL()));

        assertEquals(1, kbase.getKnowledgePackages().iterator().next().getRules().size());
        assertTrue(kbase.getKnowledgePackages().iterator().next().getRules().iterator().next().getName().equals("all the Strings"));

        StatefulKnowledgeSession ksession = createKnowledgeSession(kbase);
        ksession.insert("Some String");

        QueryResults queryResults = ksession.getQueryResults("all the Strings");

        assertTrue(queryResults.size() == 1);
        assertTrue(queryResults.iterator().next().get("$strings").equals("Some String"));


        this.fileManager.write("rules.drl", this.createCommonQuery("all the Strings 2", new String[]{"$strings : String()"}));

        this.scan(kagent);

        assertEquals(1, kbase.getKnowledgePackages().iterator().next().getRules().size());
        assertTrue(kbase.getKnowledgePackages().iterator().next().getRules().iterator().next().getName().equals("all the Strings 2"));

        queryResults = ksession.getQueryResults("all the Strings 2");

        assertTrue(queryResults.size() == 1);
        assertTrue(queryResults.iterator().next().get("$strings").equals("Some String"));


        ksession.dispose();
    }
    
}
