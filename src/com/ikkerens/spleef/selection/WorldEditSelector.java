package com.ikkerens.spleef.selection;

import com.ikkerens.worldedit.model.Selection;
import com.ikkerens.worldedit.model.Session;
import com.mbserver.api.Server;
import com.mbserver.api.game.Player;

public class WorldEditSelector implements Selector {
    private static final String WE_KEY = "worldedit.session";

    public WorldEditSelector( final Server server ) {
        server.getLogger().info( "MBSpleef found MBWorldEdit, using WE for selections." );
    }

    @Override
    public void clear( final Player player, final boolean restart ) {
        final Session session = (Session) player.getMetaData( WE_KEY, null );

        if ( session != null )
            session.clearSelection();
    }

    @Override
    public com.ikkerens.spleef.selection.Selection getSelection( final Player player ) {
        final Session session = (Session) player.getMetaData( WE_KEY, null );

        if ( session != null ) {
            final Selection sel = session.getSelection();
            if ( sel.isValid() )
                return new com.ikkerens.spleef.selection.Selection( sel.getMinimumPosition(), sel.getMaximumPosition() );
        }

        return null;
    }
}
