package org.drools.verifier.components;

import org.drools.verifier.report.components.Cause;

/**
 * 
 * @author Toni Rikkola
 */
public class VariableRestriction extends Restriction implements Cause {

	protected Variable variable;

	public Variable getVariable() {
		return variable;
	}

	public void setVariable(Variable variable) {
		this.variable = variable;
	}

	public RestrictionType getRestrictionType() {
		return Restriction.RestrictionType.VARIABLE;
	}

	@Override
	public String toString() {
		return "VariableRestriction from rule '" + ruleName + "' variable '"
				+ variable + "'";
	}
}