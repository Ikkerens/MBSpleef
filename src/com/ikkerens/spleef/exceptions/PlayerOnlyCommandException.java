package com.ikkerens.spleef.exceptions;

public class PlayerOnlyCommandException extends LogicalSpleefException {
    private static final long serialVersionUID = 1L;

    @Override
    public String getMessage() {
        return "That command can only be executed by a player!";
    }

}
