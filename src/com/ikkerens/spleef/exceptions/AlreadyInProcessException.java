package com.ikkerens.spleef.exceptions;

import com.ikkerens.spleef.commands.process.Process;

public class AlreadyInProcessException extends LogicalSpleefException {
    private static final long serialVersionUID = 791565695443624974L;

    private final Process     process;

    public AlreadyInProcessException( final Process process ) {
        this.process = process;
    }

    @Override
    public final String getMessage() {
        return String.format( "You cannot start a new process as you are already %s. You can either finish, or /spl cancel", this.process.getBusyMessage() );
    }
}
