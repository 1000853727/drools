package org.drools.analytics.components;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.drools.analytics.result.Cause;

/**
 * Instance of this class represents a possible combination of
 * PatternPosibilities under one Rule. Each possibility returns true if all the
 * PatternPosibilities in the combination are true.
 * 
 * @author Toni Rikkola
 */
public class RulePossibility extends AnalyticsComponent implements
		Serializable, Possibility {
	private static final long serialVersionUID = 8871361928380977116L;

	private static int index = 0;

	private int ruleId;
	private Set<Cause> items = new HashSet<Cause>();

	public RulePossibility() {
		super(index++);
	}

	@Override
	public AnalyticsComponentType getComponentType() {
		return AnalyticsComponentType.RULE_POSSIBILITY;
	}

	public CauseType getCauseType() {
		return Cause.CauseType.POSSIBILITY;
	}

	public Set<Cause> getItems() {
		return items;
	}

	public int getAmountOfItems() {
		return items.size();
	}

	public int getRuleId() {
		return ruleId;
	}

	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}

	public void add(PatternPossibility patternPossibility) {
		items.add(patternPossibility);
	}

	@Override
	public String toString() {
		return "RulePossibility from rule: " + ruleName + ", amount of items:"
				+ items.size();
	}
}
