package com.ikkerens.spleef.commands.process;

import com.ikkerens.spleef.SpleefPlugin;
import com.ikkerens.spleef.exceptions.AlreadyInProcessException;
import com.ikkerens.spleef.exceptions.RequirementsNotMetException;
import com.ikkerens.spleef.selection.Selector;
import com.mbserver.api.game.Location;
import com.mbserver.api.game.Player;

public class CreateProcess extends Process {
    private Location fullPos1, fullPos2;
    private Location breakPos1, breakPos2;

    public CreateProcess( final SpleefPlugin plugin, final Player player ) throws AlreadyInProcessException {
        super( plugin, player );
    }

    @Override
    public String getBusyMessage() {
        return "creating an arena";
    }

    @Override
    public void validate( final int step ) throws RequirementsNotMetException {
        final Player player = this.getPlayer();
        final Selector sel = this.getPlugin().getSelector();

        switch ( step ) {
            case 0:
                break;
            case 1:
                if ( !sel.isValid( player ) )
                    throw new RequirementsNotMetException( "That is not a valid selection!" );

                break;
            case 2: {
                if ( !sel.isValid( player ) )
                    throw new RequirementsNotMetException( "That is not a valid selection!" );

                final Location p1 = sel.getMinimumPosition( player );
                final Location p2 = sel.getMaximumPosition( player );

                if ( p1.getBlockY() != p2.getBlockY() )
                    throw new RequirementsNotMetException( "The selected area is more than 1 block high!" );

                if ( ( p1.getX() < this.fullPos1.getX() ) || ( p2.getX() > this.fullPos2.getX() ) || ( p1.getZ() < this.fullPos1.getZ() ) || ( p2.getZ() > this.fullPos2.getZ() ) || ( p1.getY() < this.fullPos1.getY() ) || ( p1.getY() > this.fullPos2.getY() ) )
                    throw new RequirementsNotMetException( "The selected area is not within the whole arena selection!" );

                break;
            }
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override
    public void onStep( final int step ) {
        final Player player = this.getPlayer();
        final Selector sel = this.getPlugin().getSelector();

        switch ( step ) {
            case 1: {
                sel.clear( player, true );
                player.sendMessage( "Select the WHOLE arena area." );
                player.sendMessage( "This area needs to include both the breaking area and the waiting room." );
                player.sendMessage( "Once you are ready enter command: /spl done" );
                break;
            }
            case 2: {
                this.fullPos1 = sel.getMinimumPosition( player );
                this.fullPos2 = sel.getMaximumPosition( player );
                sel.clear( player, true );
                player.sendMessage( "Now select the breakable area. This area can only be 1 block high." );
                player.sendMessage( "Once you are ready enter command: /spl done" );
                break;
            }
            case 3: {
                this.breakPos1 = sel.getMinimumPosition( player );
                this.breakPos2 = sel.getMaximumPosition( player );
                sel.clear( player, false );
                player.sendMessage( "Please stand at the location you want to use as a waiting area." );
            }
            default:
                throw new UnsupportedOperationException();
        }
    }
}
