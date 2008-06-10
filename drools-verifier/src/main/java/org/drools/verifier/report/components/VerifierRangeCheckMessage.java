package org.drools.verifier.report.components;

import java.util.Collection;

public class VerifierRangeCheckMessage extends VerifierMessageBase {
	private static final long serialVersionUID = -2403507929285633672L;

	private Collection<RangeCheckCause> causes;

	public VerifierRangeCheckMessage(Severity severity, Cause faulty,
			String message, Collection<RangeCheckCause> causes) {
		super(severity, MessageType.RANGE_CHECK, faulty, message);

		this.causes = causes;
	}

	public Collection<RangeCheckCause> getCauses() {
		return causes;
	}

	public void setCauses(Collection<RangeCheckCause> reasons) {
		this.causes = reasons;
	}
}
