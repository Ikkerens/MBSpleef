package com.ikkerens.spleef.commands;

import com.ikkerens.spleef.commands.handling.CommandIndex;
import com.ikkerens.spleef.commands.handling.PlayerOnlyCommandException;
import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;

public class MainCommand implements CommandExecutor {

    @Override
    public void execute( final String command, final CommandSender sender, final String[] args, final String label ) {
        if ( !sender.hasPermission( "ikkerens.spleef" ) ) {
            sender.sendMessage( "You do not have permission to execute /" + label );
            return;
        }

        try {
            CommandIndex.getInstance().resolve( sender, args );
        } catch ( final PlayerOnlyCommandException e ) {
            sender.sendMessage( "That command can only be executed by a player!" );
            return;
        }
    }

}
