package com.ikkerens.spleef;

import com.ikkerens.spleef.commands.MainCommand;
import com.ikkerens.spleef.selection.BlockSelector;
import com.ikkerens.spleef.selection.Selector;
import com.ikkerens.spleef.selection.WorldEditSelector;
import com.ikkerens.worldedit.WorldEditPlugin;
import com.mbserver.api.MBServerPlugin;
import com.mbserver.api.Manifest;
import com.mbserver.api.events.EventHandler;
import com.mbserver.api.events.Listener;
import com.mbserver.api.events.ServerStartedEvent;

@Manifest( name = "MBSpleef", authors = "Ikkerens" )
public class SpleefPlugin extends MBServerPlugin implements Listener {
    private Selector selector;

    @Override
    public void onEnable() {
        this.getPluginManager().registerEventHandler( this );
        this.getPluginManager().registerCommand( "spleef", new String[] { "spl" }, new MainCommand( this ) );
    }

    public Selector getSelector() {
        return this.selector;
    }

    @EventHandler
    public void onServerStart( final ServerStartedEvent e ) {
        // See if worldedit is loaded.
        final MBServerPlugin we = this.getPluginManager().getPlugin( "MBWorldEdit" );
        if ( we instanceof WorldEditPlugin )
            this.selector = new WorldEditSelector( this.getServer() );
        else
            this.selector = new BlockSelector();
    }
}
