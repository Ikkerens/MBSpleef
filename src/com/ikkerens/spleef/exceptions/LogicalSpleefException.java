package com.ikkerens.spleef.exceptions;

public abstract class LogicalSpleefException extends Exception {
    private static final long serialVersionUID = 9044766664303557408L;

    @Override
    public abstract String getMessage();
}
