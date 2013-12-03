package com.ikkerens.spleef.commands.process;

import com.ikkerens.spleef.SpleefPlugin;
import com.ikkerens.spleef.exceptions.AlreadyInProcessException;
import com.ikkerens.spleef.exceptions.RequirementsNotMetException;
import com.ikkerens.spleef.selection.Selection;
import com.ikkerens.spleef.selection.Selector;
import com.mbserver.api.game.Location;
import com.mbserver.api.game.Player;

public class CreateProcess extends Process {
    private final String name;
    private Selection    full, breakable;
    private Location     wait;

    public CreateProcess( final SpleefPlugin plugin, final Player player, final String name ) throws AlreadyInProcessException {
        super( plugin, player );
        this.name = name;
    }

    @Override
    public String getBusyMessage() {
        return "creating an arena";
    }

    @Override
    public void validate( final int step ) throws RequirementsNotMetException {
        final Player player = this.getPlayer();
        final Selector selector = this.getPlugin().getSelector();

        switch ( step ) {
            case 0:
                break;
            case 1:
                if ( selector.getSelection( player ) == null )
                    throw new RequirementsNotMetException( "That is not a valid selection!" );

                break;
            case 2: {
                final Selection sel = selector.getSelection( player );
                if ( sel == null )
                    throw new RequirementsNotMetException( "That is not a valid selection!" );

                if ( sel.getMinimumLocation().getBlockY() != sel.getMaximumLocation().getBlockY() )
                    throw new RequirementsNotMetException( "The selected area is more than 1 block high!" );

                if ( !this.full.contains( sel.getMinimumLocation() ) || !this.full.contains( sel.getMaximumLocation() ) )
                    throw new RequirementsNotMetException( "The selected area is not within the whole arena selection!" );

                break;
            }
            case 3: {
                final Location playerLoc = player.getLocation();

                if ( !this.full.contains( playerLoc ) )
                    throw new RequirementsNotMetException( "Your position is not within the whole arena selection!" );

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
                this.full = sel.getSelection( player );
                sel.clear( player, true );
                player.sendMessage( "Now select the breakable area. This area can only be 1 block high." );
                player.sendMessage( "Once you are ready enter command: /spl done" );
                break;
            }
            case 3: {
                this.breakable = sel.getSelection( player );
                sel.clear( player, false );
                player.sendMessage( "Please stand at the location you want to use as a waiting area." );
                player.sendMessage( "Once you are ready enter command: /spl done" );
                break;
            }
            case 4: {
                this.wait = player.getLocation();
                this.finish();
                player.sendMessage( "Your arena has been created!" );
            }
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override
    public void finish() {
        super.finish();
    }
}
