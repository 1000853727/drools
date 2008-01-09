package org.drools.process.core.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.drools.process.core.ParameterDefinition;
import org.drools.process.core.WorkDefinition;

/**
 * 
 * @author <a href="mailto:kris_verlaenen@hotmail.com">Kris Verlaenen</a>
 */
public class WorkDefinitionImpl implements WorkDefinition, Serializable {
    
    private static final long serialVersionUID = 400L;
    
    private String name;
    private Map<String, ParameterDefinition> parameters = new HashMap<String, ParameterDefinition>();
    private Map<String, ParameterDefinition> results = new HashMap<String, ParameterDefinition>();

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Set<ParameterDefinition> getParameters() {
    	return new HashSet<ParameterDefinition>(parameters.values());        
    }
    
    public void setParameters(Set<ParameterDefinition> parameters) {
        this.parameters.clear();
        Iterator<ParameterDefinition> iterator = parameters.iterator();
        while (iterator.hasNext()) {
        	addParameter(iterator.next());
        }        
    }
    
    public void addParameter(ParameterDefinition parameter) {
    	parameters.put(parameter.getName(), parameter);
    }
    
    public void removeParameter(String name) {
        parameters.remove(name);
    }
    
    public String[] getParameterNames() {
        return parameters.keySet().toArray(new String[parameters.size()]);
    }
    
    public ParameterDefinition getParameter(String name) {
        return parameters.get(name);
    }
    
    public Set<ParameterDefinition> getResults() {
    	return new HashSet<ParameterDefinition>(results.values());
    }
    
    public void setResults(Set<ParameterDefinition> results) {
    	this.results.clear();
        Iterator<ParameterDefinition> it = results.iterator();
        while (it.hasNext()) {
        	addResult(it.next());
        }   
    }
    
    public void addResult(ParameterDefinition result) {
        results.put(result.getName(), result);
    }
    
    public void removeResult(String name) {
        results.remove(name);
    }
    
    public String[] getResultNames() {
        return results.keySet().toArray(new String[results.size()]);
    }
    
    public ParameterDefinition getResult(String name) {
        return results.get(name);
    }
    
    public String toString() {
        return name;
    }
}
