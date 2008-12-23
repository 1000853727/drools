package org.drools.integrationtests;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.drools.Person;
import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
import org.drools.WorkingMemory;
import org.drools.compiler.PackageBuilder;
import org.drools.process.core.context.variable.VariableScope;
import org.drools.process.instance.ProcessInstance;
import org.drools.process.instance.context.variable.VariableScopeInstance;
import org.drools.rule.Package;
import org.drools.ruleflow.instance.RuleFlowProcessInstance;

public class ProcessSplitTest extends TestCase {
    
    public void testSplitWithProcessInstanceConstraint() {
        PackageBuilder builder = new PackageBuilder();
        Reader source = new StringReader(
            "<process xmlns=\"http://drools.org/drools-5.0/process\"" +
            "         xmlns:xs=\"http://www.w3.org/2001/XMLSchema-instance\"" +
            "         xs:schemaLocation=\"http://drools.org/drools-5.0/process drools-processes-5.0.xsd\"" +
            "         type=\"RuleFlow\" name=\"ruleflow\" id=\"org.drools.process-split\" package-name=\"org.drools\" >" +
            "" +
            "  <header>" +
            "    <imports>" +
            "      <import name=\"org.drools.Person\" />" +
            "      <import name=\"org.drools.integrationtests.ProcessSplitTest.ProcessUtils\" />" +
            "    </imports>" +
            "    <globals>" +
            "      <global identifier=\"list\" type=\"java.util.List\" />" +
            "    </globals>" +
            "    <variables>\n" +
            "      <variable name=\"name\" >\n" +
            "        <type name=\"org.drools.process.core.datatype.impl.type.StringDataType\" />\n" +
            "      </variable>\n" +
            "    </variables>\n" +
            "  </header>" +
            "" +
            "  <nodes>" +
            "    <actionNode id=\"2\" name=\"Action\" >" +
            "        <action type=\"expression\" dialect=\"mvel\" >insert(context.getProcessInstance());</action>" +
            "    </actionNode>" +
            "    <split id=\"4\" name=\"Split\" type=\"2\" >" +
            "      <constraints>" +
            "        <constraint toNodeId=\"8\" toType=\"DROOLS_DEFAULT\" priority=\"2\" type=\"rule\" dialect=\"mvel\" >eval(true)</constraint>" +
            "        <constraint toNodeId=\"6\" toType=\"DROOLS_DEFAULT\" name=\"constraint\" priority=\"1\" type=\"rule\" dialect=\"mvel\" >processInstance: org.drools.ruleflow.instance.RuleFlowProcessInstance()" +
            "Person( name == (ProcessUtils.getValue(processInstance, \"name\")) )</constraint>" +
            "      </constraints>" +
            "    </split>" +
            "    <end id=\"8\" name=\"End\" />" +
            "    <actionNode id=\"6\" name=\"Action\" >" +
            "        <action type=\"expression\" dialect=\"mvel\" >list.add(context.getProcessInstance().getId());</action>" +
            "    </actionNode>" +
            "    <start id=\"1\" name=\"Start\" />" +
            "    <end id=\"3\" name=\"End\" />" +
            "  </nodes>" +
            "  <connections>" +
            "    <connection from=\"1\" to=\"2\" />" +
            "    <connection from=\"2\" to=\"4\" />" +
            "    <connection from=\"4\" to=\"8\" />" +
            "    <connection from=\"4\" to=\"6\" />" +
            "    <connection from=\"6\" to=\"3\" />" +
            "  </connections>" +
            "" +
            "</process>");
        builder.addRuleFlow(source);
        Package pkg = builder.getPackage();
        RuleBase ruleBase = RuleBaseFactory.newRuleBase();
        ruleBase.addPackage( pkg );
        WorkingMemory workingMemory = ruleBase.newStatefulSession();
        List<Long> list = new ArrayList<Long>();
        workingMemory.setGlobal("list", list);

        Person john = new Person("John Doe", 20);
        Person jane = new Person("Jane Doe", 20);
        Person julie = new Person("Julie Doe", 20);
        workingMemory.insert(john);
        workingMemory.insert(jane);
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", john.getName());
        ProcessInstance processInstance1 = ( ProcessInstance )
            workingMemory.startProcess("org.drools.process-split", params);
        
        params = new HashMap<String, Object>();
        params.put("name", jane.getName());
        ProcessInstance processInstance2 = ( ProcessInstance )
            workingMemory.startProcess("org.drools.process-split", params);
        
        params = new HashMap<String, Object>();
        params.put("name", julie.getName());
        ProcessInstance processInstance3 = ( ProcessInstance )
            workingMemory.startProcess("org.drools.process-split", params);
        
        assertEquals(ProcessInstance.STATE_COMPLETED, processInstance1.getState());
        assertEquals(ProcessInstance.STATE_COMPLETED, processInstance2.getState());
        assertEquals(ProcessInstance.STATE_COMPLETED, processInstance3.getState());
        assertEquals(2, list.size());
    }
    
    public static class ProcessUtils {
    	
    	public static Object getValue(RuleFlowProcessInstance processInstance, String name) {
    		VariableScopeInstance scope = (VariableScopeInstance)
    			processInstance.getContextInstance(VariableScope.VARIABLE_SCOPE);
    		return scope.getVariable(name);
    	}
    	
    }

}
