package org.drools.process.core.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.drools.process.core.ParameterDefinition;
import org.drools.process.core.Work;

/**
 * 
 * @author <a href="mailto:kris_verlaenen@hotmail.com">Kris Verlaenen</a>
 */
public class WorkImpl implements Work, Serializable {
    
    private static final long serialVersionUID = 400L;
    
    private String name;
    private Map<String, Object> parameters = new HashMap<String, Object>();
    private Map<String, ParameterDefinition> parameterDefinitions = new HashMap<String, ParameterDefinition>();
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setParameter(String name, Object value) {
    	if (name == null) {
    		throw new NullPointerException("Parameter name is null");
    	}
		parameters.put(name, value);
    }
    
    public void setParameters(Map<String, Object> parameters) {
        if (parameters == null) {
            throw new NullPointerException();
        }
        this.parameters = new HashMap<String, Object>(parameters);
    }
    
    public Object getParameter(String name) {
        if (name == null) {
            throw new NullPointerException("Parameter name is null");
        }
        return parameters.get(name);
    }
    
    public Map<String, Object> getParameters() {
        return Collections.unmodifiableMap(parameters);
    }
    
    public String toString() {
        return "Work " + name;
    }

    public void setParameterDefinitions(Set<ParameterDefinition> parameterDefinitions) {
        this.parameterDefinitions.clear();
        for (ParameterDefinition parameterDefinition: parameterDefinitions) {
            addParameterDefinition(parameterDefinition);
        }        
    }

    public void addParameterDefinition(ParameterDefinition parameterDefinition) {
        this.parameterDefinitions.put(parameterDefinition.getName(), parameterDefinition);
    }

    public Set<ParameterDefinition> getParameterDefinitions() {
        return new HashSet<ParameterDefinition>(parameterDefinitions.values());        
    }

    public String[] getParameterNames() {
        return parameterDefinitions.keySet().toArray(new String[parameterDefinitions.size()]);
    }

    public ParameterDefinition getParameterDefinition(String name) {
        return parameterDefinitions.get(name);
    }
}
