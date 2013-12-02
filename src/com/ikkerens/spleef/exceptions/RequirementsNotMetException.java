package com.ikkerens.spleef.exceptions;

public class RequirementsNotMetException extends LogicalSpleefException {
    private static final long serialVersionUID = 4353936471030766026L;

    private final String      message;

    public RequirementsNotMetException( final String message ) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
