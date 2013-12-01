package com.ikkerens.spleef.selection;

import com.ikkerens.worldedit.model.Selection;
import com.ikkerens.worldedit.model.Session;
import com.mbserver.api.Server;
import com.mbserver.api.game.Location;
import com.mbserver.api.game.Player;

public class WorldEditSelector implements Selector {

    public WorldEditSelector( final Server server ) {
        server.getLogger().info( "MBSpleef found MBWorldEdit, using WE for selections." );
    }

    @Override
    public Location getMinimumPosition( final Player player ) {
        final Selection sel = this.getSelection( player );
        return sel.getMinimumPosition();
    }

    @Override
    public Location getMaximumPosition( final Player player ) {
        final Selection sel = this.getSelection( player );
        return sel.getMaximumPosition();
    }

    private Selection getSelection( final Player player ) {
        final Session session = (Session) player.getMetaData( "worldedit.session", null );

        if ( ( session != null ) && session.getSelection().isValid() )
            return session.getSelection();
        else
            return null;
    }

}
