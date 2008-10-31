package org.drools.verifier;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.drools.verifier.components.OperatorDescr;
import org.drools.verifier.components.VerifierComponent;

/**
 * Takes a list of Constraints and makes possibilities from them.
 * 
 * @author Toni Rikkola
 */
class Solver {

	private List<Set<VerifierComponent>> possibilityLists = new ArrayList<Set<VerifierComponent>>();
	private Solver subSolver = null;
	private boolean isChildExists = false;
	private boolean isChildForall = false;
	private boolean isChildNot = false;

	private OperatorDescr.Type type;

	protected Solver(OperatorDescr.Type type) {
		this.type = type;
	}

	public void addOperator(OperatorDescr.Type type) {
		if (subSolver != null) {
			subSolver.addOperator(type);
		} else {
			subSolver = new Solver(type);
		}
	}

	/**
	 * Add new descr.
	 * 
	 * @param descr
	 */
	public void add(VerifierComponent descr) {

		if (descr instanceof OperatorDescr) {
			throw new UnsupportedOperationException(
					"Operator descrs are not supported.");
		}

		if (subSolver != null) {
			subSolver.add(descr);
		} else {
			if (type == OperatorDescr.Type.AND) {
				if (possibilityLists.isEmpty()) {
					possibilityLists.add(new HashSet<VerifierComponent>());
				}
				for (Set<VerifierComponent> set : possibilityLists) {
					set.add(descr);
				}
			} else if (type == OperatorDescr.Type.OR) {
				Set<VerifierComponent> set = new HashSet<VerifierComponent>();
				set.add(descr);
				possibilityLists.add(set);
			}
		}
	}

	/**
	 * Ends subSolvers data collection.
	 * 
	 */
	protected void end() {
		if (subSolver != null && subSolver.subSolver == null) {
			if (type == OperatorDescr.Type.AND) {
				if (possibilityLists.isEmpty()) {
					possibilityLists.add(new HashSet<VerifierComponent>());
				}

				List<Set<VerifierComponent>> newPossibilities = new ArrayList<Set<VerifierComponent>>();

				List<Set<VerifierComponent>> sets = subSolver
						.getPossibilityLists();
				for (Set<VerifierComponent> possibilityList : possibilityLists) {

					for (Set<VerifierComponent> set : sets) {
						Set<VerifierComponent> newSet = new HashSet<VerifierComponent>();
						newSet.addAll(possibilityList);
						newSet.addAll(set);
						newPossibilities.add(newSet);
					}
				}
				possibilityLists = newPossibilities;

			} else if (type == OperatorDescr.Type.OR) {

				possibilityLists.addAll(subSolver.getPossibilityLists());

			}

			subSolver = null;

		} else if (subSolver != null && subSolver.subSolver != null) {

			subSolver.end();
		}

	}

	public void setChildForall(boolean b) {
		if (subSolver != null) {
			subSolver.setChildForall(b);
		} else {
			isChildForall = b;
		}
	}

	public void setChildExists(boolean b) {
		if (subSolver != null) {
			subSolver.setChildExists(b);
		} else {
			isChildExists = b;
		}
	}

	public void setChildNot(boolean b) {
		if (subSolver != null) {
			subSolver.setChildNot(b);
		} else {
			isChildNot = b;
		}
	}

	public boolean isForall() {
		if (subSolver != null) {
			return subSolver.isForall();
		} else {
			return isChildForall;
		}
	}

	public boolean isExists() {
		if (subSolver != null) {
			return subSolver.isExists();
		} else {
			return isChildExists;
		}
	}

	public boolean isChildNot() {
		if (subSolver != null) {
			return subSolver.isChildNot();
		} else {
			return isChildNot;
		}
	}

	public List<Set<VerifierComponent>> getPossibilityLists() {
		return possibilityLists;
	}
}
