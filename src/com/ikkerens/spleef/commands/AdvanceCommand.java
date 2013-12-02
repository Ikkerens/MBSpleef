package com.ikkerens.spleef.commands;

import com.ikkerens.spleef.Permissions;
import com.ikkerens.spleef.commands.handling.CommandHandler;
import com.ikkerens.spleef.commands.process.Process;
import com.ikkerens.spleef.exceptions.LogicalSpleefException;
import com.mbserver.api.CommandSender;
import com.mbserver.api.game.Player;

public class AdvanceCommand extends CommandHandler {

    @Override
    public String getPermission() {
        return Permissions.CREATE;
    }

    @Override
    public void executeCommand( final CommandSender sender, final String[] args ) throws LogicalSpleefException {
        final Player player = this.checkPlayer( sender );
        final Process process = player.getMetaData( Process.KEY, null );

        if ( process != null )
            process.advance();
        else
            player.sendMessage( "There is no process to advance!" );
    }

}
