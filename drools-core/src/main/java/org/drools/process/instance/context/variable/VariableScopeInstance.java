package org.drools.process.instance.context.variable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.drools.common.EventSupport;
import org.drools.process.core.context.variable.Variable;
import org.drools.process.core.context.variable.VariableScope;
import org.drools.process.instance.ContextInstanceContainer;
import org.drools.process.instance.context.AbstractContextInstance;

/**
 * 
 * @author <a href="mailto:kris_verlaenen@hotmail.com">Kris Verlaenen</a>
 */
public class VariableScopeInstance extends AbstractContextInstance {

    private static final long serialVersionUID = 400L;
    
    private Map<String, Object> variables = new HashMap<String, Object>();

    public String getContextType() {
        return VariableScope.VARIABLE_SCOPE;
    }

    public Object getVariable(String name) {
        return variables.get(name);
    }

    public Map<String, Object> getVariables() {
        return Collections.unmodifiableMap(variables);
    }

    public void setVariable(String name, Object value) {
        if (name == null) {
            throw new IllegalArgumentException(
                "The name of a variable may not be null!");
        }
        if(getProcessInstance() != null){
            ((EventSupport) getProcessInstance().getWorkingMemory()).getRuleFlowEventSupport().fireBeforeVariableChange(getProcessInstance(),name, variables.get(name), getProcessInstance().getWorkingMemory());
        }
        variables.put(name, value);
        if(getProcessInstance() != null){
            ((EventSupport) getProcessInstance().getWorkingMemory()).getRuleFlowEventSupport().fireAfterVariableChange(getProcessInstance(),name, value, getProcessInstance().getWorkingMemory());
        }
    }
    
    public VariableScope getVariableScope() {
    	return (VariableScope) getContext();
    }
    
    public void setContextInstanceContainer(ContextInstanceContainer contextInstanceContainer) {
    	super.setContextInstanceContainer(contextInstanceContainer);
    	for (Variable variable : getVariableScope().getVariables()) {
            setVariable(variable.getName(), variable.getValue());
        }
    }

}
