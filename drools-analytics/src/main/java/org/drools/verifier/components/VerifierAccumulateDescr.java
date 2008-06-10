package org.drools.verifier.components;


/**
 * 
 * @author Toni Rikkola
 */
public class VerifierAccumulateDescr extends VerifierComponent {

	private static int index = 0;

	private int inputPatternId;
	private String initCode;
	private String actionCode;
	private String reverseCode;
	private String resultCode;
	private String[] declarations;
	private String className;
	private boolean externalFunction = false;
	private String functionIdentifier;
	private String expression;

	public VerifierAccumulateDescr() {
		super(index++);
	}

	@Override
	public VerifierComponentType getComponentType() {
		return VerifierComponentType.ACCUMULATE;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String[] getDeclarations() {
		return declarations;
	}

	public void setDeclarations(String[] declarations) {
		this.declarations = declarations;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public boolean isExternalFunction() {
		return externalFunction;
	}

	public void setExternalFunction(boolean externalFunction) {
		this.externalFunction = externalFunction;
	}

	public String getFunctionIdentifier() {
		return functionIdentifier;
	}

	public void setFunctionIdentifier(String functionIdentifier) {
		this.functionIdentifier = functionIdentifier;
	}

	public String getInitCode() {
		return initCode;
	}

	public void setInitCode(String initCode) {
		this.initCode = initCode;
	}

	public int getInputPatternId() {
		return inputPatternId;
	}

	public void setInputPatternId(int inputPatternId) {
		this.inputPatternId = inputPatternId;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getReverseCode() {
		return reverseCode;
	}

	public void setReverseCode(String reverseCode) {
		this.reverseCode = reverseCode;
	}
}
