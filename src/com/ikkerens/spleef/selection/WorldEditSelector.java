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
    public boolean isValid( final Player player ) {
        final Selection sel = this.getSelection( player );
        return ( sel != null ) && sel.isValid();
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

    @Override
    public void clear( final Player player, final boolean restart ) {
        final Session session = (Session) player.getMetaData( "worldedit.session", null );

        if ( session != null )
            session.clearSelection();
    }

    private Selection getSelection( final Player player ) {
        final Session session = (Session) player.getMetaData( "worldedit.session", null );

        if ( ( session != null ) && session.getSelection().isValid() )
            return session.getSelection();
        else
            return null;
    }

}
