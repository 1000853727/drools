package org.drools.verifier.dao;

import java.util.Collection;

import org.drools.verifier.report.components.VerifierMessageBase;
import org.drools.verifier.report.components.Gap;
import org.drools.verifier.report.components.MissingNumberPattern;
import org.drools.verifier.report.components.RangeCheckCause;
import org.drools.verifier.report.components.Severity;

/**
 *
 * @author Toni Rikkola
 */
public interface VerifierResult {

	public void add(Gap gap);

	public void remove(Gap gap);

	public void add(MissingNumberPattern missingNumberPattern);

	public VerifierData getVerifierData();

	public Collection<RangeCheckCause> getRangeCheckCauses();

	public Collection<RangeCheckCause> getRangeCheckCausesByFieldId(int id);

	public Collection<Gap> getGapsByFieldId(int fieldId);

	public void add(VerifierMessageBase note);

	/**
	 * Return all the items that have given severity value.
	 *
	 * @param severity
	 *            Severity level of item.
	 * @return Collection of items or an empty list if none was found.
	 */
	public Collection<VerifierMessageBase> getBySeverity(Severity severity);

}
