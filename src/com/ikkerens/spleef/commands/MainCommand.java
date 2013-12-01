package com.ikkerens.spleef.commands;

import com.ikkerens.spleef.SpleefPlugin;
import com.ikkerens.spleef.commands.handling.CommandIndex;
import com.ikkerens.spleef.exceptions.LogicalSpleefException;
import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;

public class MainCommand implements CommandExecutor {
    private final CommandIndex index;

    public MainCommand( final SpleefPlugin plugin ) {
        this.index = new CommandIndex( plugin );
    }

    @Override
    public void execute( final String command, final CommandSender sender, final String[] args, final String label ) {
        if ( !sender.hasPermission( "ikkerens.spleef" ) ) {
            sender.sendMessage( "You do not have permission to execute /" + label );
            return;
        }

        try {
            this.index.resolve( sender, args );
        } catch ( final LogicalSpleefException e ) {
            sender.sendMessage( e.getMessage() );
            return;
        }
    }

}
