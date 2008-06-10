package org.drools.verifier.components;

import org.drools.verifier.report.components.CauseType;

public class TextConsequence extends VerifierComponent implements Consequence {

	private static int index = 0;
	private String text;

	public TextConsequence() {
		super(index++);
	}

	public ConsequenceType getConsequenceType() {
		return ConsequenceType.TEXT;
	}

	@Override
	public VerifierComponentType getComponentType() {
		return VerifierComponentType.CONSEQUENCE;
	}

	public CauseType getCauseType() {
		return CauseType.CONSEQUENCE;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
