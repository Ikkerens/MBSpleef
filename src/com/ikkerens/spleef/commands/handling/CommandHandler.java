package com.ikkerens.spleef.commands.handling;

import com.mbserver.api.CommandSender;
import com.mbserver.api.game.Player;

public abstract class CommandHandler {
    public abstract void executeCommand( CommandSender sender, String[] args ) throws PlayerOnlyCommandException;

    public Player checkPlayer( final CommandSender sender ) throws PlayerOnlyCommandException {
        if ( !( sender instanceof Player ) )
            throw new PlayerOnlyCommandException();

        return (Player) sender;
    }
}
