package org.drools.testing.core.model;

/**
 * 
 * @author Matt
 *
 * (c) Matt Shaw
 */
public class RuleFilter {

	private String name;
	private boolean fire;
	
	public RuleFilter () {}
	
	public boolean isFire() {
		return fire;
	}
	public void setFire(boolean fire) {
		this.fire = fire;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
