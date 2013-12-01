package com.ikkerens.spleef.commands;

import com.ikkerens.spleef.commands.handling.CommandHandler;
import com.ikkerens.spleef.commands.process.CreateProcess;
import com.ikkerens.spleef.exceptions.LogicalSpleefException;
import com.mbserver.api.CommandSender;

public class CreateCommand extends CommandHandler {

    @Override
    public void executeCommand( final CommandSender sender, final String[] args ) throws LogicalSpleefException {
        new CreateProcess( this.checkPlayer( sender ) ).advance();
    }

}
