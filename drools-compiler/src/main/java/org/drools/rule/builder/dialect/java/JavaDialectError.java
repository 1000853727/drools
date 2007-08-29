package org.drools.rule.builder.dialect.java;

import org.drools.compiler.DroolsError;

public class JavaDialectError extends DroolsError {
    private String message;
    private static final int[] line = new int[0];

    public JavaDialectError(final String message) {
        super();
        this.message = message;
    }

    public int[] getErrorLines() {
        return line;
    }
    
    public String getMessage() {
        return this.message;
    }

    public String toString() {
        return "[JavaDialectError message='" + this.message + "']";
    }

}
