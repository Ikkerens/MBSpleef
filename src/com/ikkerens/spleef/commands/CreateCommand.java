package com.ikkerens.spleef.commands;

import com.ikkerens.spleef.Permissions;
import com.ikkerens.spleef.commands.handling.CommandHandler;
import com.ikkerens.spleef.commands.process.CreateProcess;
import com.ikkerens.spleef.exceptions.LogicalSpleefException;
import com.mbserver.api.CommandSender;

public class CreateCommand extends CommandHandler {

    @Override
    public String getPermission() {
        return Permissions.CREATE;
    }

    @Override
    public void executeCommand( final CommandSender sender, final String[] args ) throws LogicalSpleefException {
        if ( args.length < 1 ) {
            sender.sendMessage( "Usage: /spl create <name>" );
            return;
        }

        new CreateProcess( this.getPlugin(), this.checkPlayer( sender ), args[ 0 ] ).advance();
    }

}
