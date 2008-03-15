package org.drools.ruleflow.instance;

import java.io.Externalizable;
import java.io.ObjectOutput;
import java.io.ObjectInput;
import java.io.IOException;

import org.drools.process.instance.ProcessInstance;
import org.drools.process.instance.ProcessInstanceFactory;

public class RuleFlowProcessInstanceFactory implements ProcessInstanceFactory, Externalizable {

    private static final long serialVersionUID = 400L;

    public ProcessInstance createProcessInstance() {
        return new RuleFlowProcessInstance();
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    }

    public void writeExternal(ObjectOutput out) throws IOException {
    }


}
