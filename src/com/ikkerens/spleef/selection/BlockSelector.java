package com.ikkerens.spleef.selection;

import com.mbserver.api.events.BlockEvent;
import com.mbserver.api.events.EventHandler;
import com.mbserver.api.events.Listener;
import com.mbserver.api.game.Location;
import com.mbserver.api.game.Player;

// TODO
public class BlockSelector implements Selector, Listener {
    public static final String SELECTOR_KEY = "mbspleef.selector";

    @EventHandler
    public void onBlockAction( final BlockEvent event ) {
        if ( event.getPlayer().getMetaData( SELECTOR_KEY, null ) == null )
            return;
    }

    @Override
    public Location getMinimumPosition( final Player player ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Location getMaximumPosition( final Player player ) {
        // TODO Auto-generated method stub
        return null;
    }

    private static class PlayerSelection {
        private Location minimum;
        private Location maximum;
    }

}
