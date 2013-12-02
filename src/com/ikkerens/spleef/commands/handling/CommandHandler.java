package com.ikkerens.spleef.commands.handling;

import com.ikkerens.spleef.SpleefPlugin;
import com.ikkerens.spleef.exceptions.LogicalSpleefException;
import com.ikkerens.spleef.exceptions.PlayerOnlyCommandException;
import com.mbserver.api.CommandSender;
import com.mbserver.api.game.Player;

public abstract class CommandHandler {
    private SpleefPlugin plugin;

    public final void injectPlugin( final SpleefPlugin plugin ) {
        this.plugin = plugin;
    }

    protected final SpleefPlugin getPlugin() {
        return this.plugin;
    }

    public final Player checkPlayer( final CommandSender sender ) throws PlayerOnlyCommandException {
        if ( !( sender instanceof Player ) )
            throw new PlayerOnlyCommandException();

        return (Player) sender;
    }

    public abstract String getPermission();

    public abstract void executeCommand( CommandSender sender, String[] args ) throws LogicalSpleefException;
}
