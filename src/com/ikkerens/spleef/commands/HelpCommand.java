package com.ikkerens.spleef.commands;

import com.ikkerens.spleef.commands.handling.CommandHandler;
import com.ikkerens.spleef.commands.handling.PlayerOnlyCommandException;
import com.mbserver.api.CommandSender;

public class HelpCommand extends CommandHandler {

    @Override
    public void executeCommand( final CommandSender sender, final String[] args ) throws PlayerOnlyCommandException {
        final int page = args.length > 0 ? Integer.parseInt( args[ 0 ] ) : 1;
        switch ( page ) {
            case 1:
                sender.sendMessage( "/spl create - Creates a new arena." );
                sender.sendMessage( "/spl *arena* start - Starts the game for an arena." );
                sender.sendMessage( "/spl *arena* stop - Stops the game for an arena." );
                break;
            default:
                sender.sendMessage( "Page out of bounds!" );
        }
    }

}
