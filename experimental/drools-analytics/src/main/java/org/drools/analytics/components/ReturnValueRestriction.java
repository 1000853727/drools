package org.drools.analytics.components;

import org.drools.analytics.result.Cause;

/**
 * 
 * @author Toni Rikkola
 */
public class ReturnValueRestriction extends Restriction implements Cause {

	private Object content;
	private String[] declarations;
	private String classMethodName;

	@Override
	public RestrictionType getRestrictionType() {
		return RestrictionType.RETURN_VALUE_RESTRICTION;
	}

	public String getClassMethodName() {
		return classMethodName;
	}

	public void setClassMethodName(String classMethodName) {
		this.classMethodName = classMethodName;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public String[] getDeclarations() {
		return declarations;
	}

	public void setDeclarations(String[] declarations) {
		this.declarations = declarations;
	}
}
