package com.ikkerens.spleef.selection;

import com.mbserver.api.events.BlockEvent;
import com.mbserver.api.events.EventHandler;
import com.mbserver.api.events.Listener;
import com.mbserver.api.game.Location;
import com.mbserver.api.game.Player;

public class BlockEventSelector implements Selector, Listener {
    public static final String SELECTOR_KEY = "mbspleef.selector";

    @EventHandler
    public void onBlockAction( final BlockEvent event ) {
        if ( event.getPlayer().getMetaData( SELECTOR_KEY, null ) == null )
            return;

        final PlayerSelection selection = this.getRawSelection( event.getPlayer() );

        if ( selection.pos1 == null )
            selection.pos1 = event.getLocation();
        else if ( selection.pos2 == null )
            selection.pos2 = event.getLocation();
        else
            return;

        event.setCancelled( true );
    }

    @Override
    public void clear( final Player player, final boolean restart ) {
        if ( restart )
            player.setMetaData( SELECTOR_KEY, new PlayerSelection() );
        else
            player.removeMetaData( SELECTOR_KEY );
    }

    @Override
    public Selection getSelection( final Player player ) {
        final PlayerSelection raw = this.getRawSelection( player );

        if ( ( raw.pos1 != null ) && ( raw.pos2 != null ) )
            return new Selection( raw.pos1, raw.pos2 );

        return null;
    }

    private PlayerSelection getRawSelection( final Player player ) {
        PlayerSelection selection = player.getMetaData( SELECTOR_KEY, null );

        if ( selection == null ) {
            selection = new PlayerSelection();
            player.setMetaData( SELECTOR_KEY, selection );
        }

        return selection;
    }

    private static class PlayerSelection {
        private Location pos1, pos2;
    }

}
