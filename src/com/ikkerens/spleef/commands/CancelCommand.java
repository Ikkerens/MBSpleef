package com.ikkerens.spleef.commands;

import com.ikkerens.spleef.commands.handling.CommandHandler;
import com.ikkerens.spleef.commands.process.Process;
import com.ikkerens.spleef.exceptions.PlayerOnlyCommandException;
import com.mbserver.api.CommandSender;
import com.mbserver.api.game.Player;

public class CancelCommand extends CommandHandler {

    @Override
    public void executeCommand( final CommandSender sender, final String[] args ) throws PlayerOnlyCommandException {
        final Player player = this.checkPlayer( sender );
        final Process process = player.getMetaData( Process.KEY, null );

        if ( process != null )
            process.cancel();
        else
            player.sendMessage( "There is no process to cancel!" );
    }

}
