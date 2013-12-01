package com.ikkerens.spleef.commands.process;

import com.ikkerens.spleef.SpleefPlugin;
import com.ikkerens.spleef.exceptions.AlreadyInProcessException;
import com.mbserver.api.game.Player;

public class CreateProcess extends Process {

    public CreateProcess( final SpleefPlugin plugin, final Player player ) throws AlreadyInProcessException {
        super( plugin, player );
    }

    @Override
    public String getBusyMessage() {
        return "creating an arena";
    }

    @Override
    public boolean validate( final int step ) {
        switch ( step ) {
            case 0:
                return true;
            case 1:
                return this.getPlugin().getSelector().isValid( this.getPlayer() );
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override
    public void onStep( final int step ) {
        final Player player = this.getPlayer();
        switch ( step ) {
            case 1: {
                this.getPlugin().getSelector().clear( player, true );
                player.sendMessage( "Select the WHOLE arena area." );
            }
            default:
                throw new UnsupportedOperationException();
        }
    }
}
