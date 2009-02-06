package org.drools.integrationtests;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.drools.Message;
import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
import org.drools.WorkingMemory;
import org.drools.compiler.DroolsError;
import org.drools.compiler.PackageBuilder;
import org.drools.compiler.PackageBuilderErrors;
import org.drools.process.instance.ProcessInstance;
import org.drools.rule.Package;
import org.drools.runtime.ObjectFilter;
import org.drools.runtime.process.WorkItem;
import org.drools.runtime.process.WorkItemHandler;
import org.drools.runtime.process.WorkItemManager;

public class ProcessForEachTest extends TestCase {
    
    public void testOnEntryExit() {
        PackageBuilder builder = new PackageBuilder();
        Reader source = new StringReader(
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<process xmlns=\"http://drools.org/drools-5.0/process\"\n" +
            "         xmlns:xs=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
            "         xs:schemaLocation=\"http://drools.org/drools-5.0/process drools-processes-5.0.xsd\"\n" +
            "         type=\"RuleFlow\" name=\"ForEach\" id=\"org.drools.ForEach\" package-name=\"org.drools\" >\n" +
            "  <header>\n" +
            "    <globals>\n" +
            "      <global identifier=\"myList\" type=\"java.util.List\" />\n" +
            "    </globals>\n" +
            "    <variables>\n" +
            "      <variable name=\"collection\" >\n" +
            "        <type name=\"org.drools.process.core.datatype.impl.type.ObjectDataType\" className=\"java.util.List\" />\n" +
            "      </variable>\n" +
            "    </variables>\n" +
            "  </header>\n" +
            "\n" +
            "  <nodes>\n" +
            "    <forEach id=\"2\" name=\"ForEach\" variableName=\"item\" collectionExpression=\"collection\" >\n" +
            "      <nodes>\n" +
            "    <actionNode id=\"1\" name=\"Action\" >\n" +
            "        <action type=\"expression\" dialect=\"mvel\" >myList.add(item);</action>\n" +
            "    </actionNode>\n" +
            "      </nodes>\n" +
            "      <connections>\n" +
            "      </connections>\n" +
            "      <in-ports>\n" +
            "        <in-port type=\"DROOLS_DEFAULT\" nodeId=\"1\" nodeInType=\"DROOLS_DEFAULT\" />\n" +
            "      </in-ports>\n" +
            "      <out-ports>\n" +
            "        <out-port type=\"DROOLS_DEFAULT\" nodeId=\"1\" nodeOutType=\"DROOLS_DEFAULT\" />\n" +
            "      </out-ports>\n" +
            "    </forEach>\n" +
            "    <start id=\"1\" name=\"Start\" />\n" +
            "    <end id=\"3\" name=\"End\" />\n" +
            "  </nodes>\n" +
            "\n" +
            "  <connections>\n" +
            "    <connection from=\"1\" to=\"2\" />\n" +
            "    <connection from=\"2\" to=\"3\" />\n" +
            "  </connections>\n" +
            "</process>");
        builder.addRuleFlow(source);
        Package pkg = builder.getPackage();
        RuleBase ruleBase = RuleBaseFactory.newRuleBase();
        ruleBase.addPackage( pkg );
        WorkingMemory workingMemory = ruleBase.newStatefulSession();
        List<String> myList = new ArrayList<String>();
        workingMemory.setGlobal("myList", myList);
        List<String> collection = new ArrayList<String>();
        collection.add("one");
        collection.add("two");
        collection.add("three");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("collection", collection);
        ProcessInstance processInstance = ( ProcessInstance )
            workingMemory.startProcess("org.drools.ForEach", params);
        assertEquals(ProcessInstance.STATE_COMPLETED, processInstance.getState());
        assertEquals(3, myList.size());
    }
    
}
