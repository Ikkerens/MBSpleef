package com.ikkerens.spleef.selection;

import com.mbserver.api.Constructors;
import com.mbserver.api.events.BlockEvent;
import com.mbserver.api.events.EventHandler;
import com.mbserver.api.events.Listener;
import com.mbserver.api.game.Location;
import com.mbserver.api.game.Player;

public class BlockSelector implements Selector, Listener {
    public static final String SELECTOR_KEY = "mbspleef.selector";

    @Override
    public boolean isValid( final Player player ) {
        final PlayerSelection sel = this.getSelection( player );
        return ( sel.minimum != null ) && ( sel.maximum != null );
    }

    @EventHandler
    public void onBlockAction( final BlockEvent event ) {
        if ( event.getPlayer().getMetaData( SELECTOR_KEY, null ) == null )
            return;

        final PlayerSelection selection = this.getSelection( event.getPlayer() );

        if ( selection.minimum == null )
            selection.minimum = event.getLocation();
        else if ( selection.maximum == null )
            selection.maximum = event.getLocation();
        else
            return;

        event.setCancelled( true );
    }

    @Override
    public Location getMinimumPosition( final Player player ) {
        return this.fixSelection( player ).minimum;
    }

    @Override
    public Location getMaximumPosition( final Player player ) {
        return this.fixSelection( player ).maximum;
    }

    private PlayerSelection fixSelection( final Player player ) {
        final PlayerSelection sel = this.getSelection( player );

        if ( !sel.fixed ) {
            final Location oMin = sel.minimum;
            final Location oMax = sel.maximum;

            sel.minimum = Constructors.newLocation( oMin.getWorld(), Math.min( oMin.getX(), oMax.getX() ), Math.min( oMin.getY(), oMax.getY() ), Math.min( oMin.getZ(), oMax.getZ() ) );
            sel.maximum = Constructors.newLocation( oMin.getWorld(), Math.max( oMin.getX(), oMax.getX() ), Math.max( oMin.getY(), oMax.getY() ), Math.max( oMin.getZ(), oMax.getZ() ) );
            sel.fixed = true;
        }

        return sel;
    }

    @Override
    public void clear( final Player player, final boolean restart ) {
        if ( restart )
            player.setMetaData( SELECTOR_KEY, new PlayerSelection() );
        else
            player.removeMetaData( SELECTOR_KEY );
    }

    private PlayerSelection getSelection( final Player player ) {
        PlayerSelection selection = player.getMetaData( SELECTOR_KEY, null );

        if ( selection == null ) {
            selection = new PlayerSelection();
            player.setMetaData( SELECTOR_KEY, selection );
        }

        return selection;
    }

    private static class PlayerSelection {
        private boolean  fixed;
        private Location minimum;
        private Location maximum;
    }

}
