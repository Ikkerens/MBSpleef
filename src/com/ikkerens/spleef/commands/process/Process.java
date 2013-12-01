package com.ikkerens.spleef.commands.process;

import com.ikkerens.spleef.SpleefPlugin;
import com.ikkerens.spleef.exceptions.AlreadyInProcessException;
import com.mbserver.api.game.Player;

public abstract class Process {
    public static final String KEY = "mbspleef.process";

    private final SpleefPlugin plugin;
    private final Player       player;
    private int                step;

    public Process( final SpleefPlugin plugin, final Player player ) throws AlreadyInProcessException {
        this.plugin = plugin;
        this.player = player;
        this.step = 0;

        // Register
        final Process process = player.getMetaData( KEY, null );
        if ( process != null )
            throw new AlreadyInProcessException( process );

        player.setMetaData( KEY, this );
    }

    public final SpleefPlugin getPlugin() {
        return this.plugin;
    }

    public final Player getPlayer() {
        return this.player;
    }

    public final void advance() {
        if ( this.validate( this.step ) )
            this.onStep( ++this.step );
    }

    public final void cancel() {
        if ( this.player.getMetaData( KEY, null ) == this )
            this.player.removeMetaData( KEY );
    }

    public void finish() {
        this.cancel();
    }

    public abstract String getBusyMessage();

    public abstract boolean validate( int step );

    public abstract void onStep( int step );
}
