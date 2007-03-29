package org.drools.testing.plugin.exception;

public class LoadTestSuiteException extends DroolsPluginException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4614603272979789124L;
	private Throwable cause = null;

    public LoadTestSuiteException() {
        super();
    }

    public LoadTestSuiteException(String message) {
        super(message);
    }

    public LoadTestSuiteException(String message, Throwable cause) {
        super(message);
        this.cause = cause;
    }

    public Throwable getCause() {
        return cause;
    }

    public void printStackTrace() {
        super.printStackTrace();
        if (cause != null) {
            System.err.println("Caused by:");
            cause.printStackTrace();
        }
    }

    public void printStackTrace(java.io.PrintStream ps) {
        super.printStackTrace(ps);
        if (cause != null) {
            ps.println("Caused by:");
            cause.printStackTrace(ps);
        }
    }

    public void printStackTrace(java.io.PrintWriter pw) {
        super.printStackTrace(pw);
        if (cause != null) {
            pw.println("Caused by:");
            cause.printStackTrace(pw);
        }
    }

    public String toString()
    {
        String exceptionString = super.toString();
        if (cause != null) {
            exceptionString += " Caused By: \n";
            exceptionString += cause.toString();
        }
        return exceptionString;
    }
}
