package org.drools.xml;

public class XmlRuleFlowProcessDumper extends XmlWorkflowProcessDumper {
    
    public static final XmlRuleFlowProcessDumper INSTANCE = new XmlRuleFlowProcessDumper();
    
    private XmlRuleFlowProcessDumper() {
        super(
            "RuleFlow", 
            "http://drools.org/drools-4.0/process",
            "drools-processes-4.0.xsd", 
            new ProcessSemanticModule()
        );
    }
    
}
