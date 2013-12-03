package com.ikkerens.spleef.commands.handling;

import java.util.ArrayList;

import com.ikkerens.spleef.SpleefPlugin;
import com.ikkerens.spleef.commands.AdvanceCommand;
import com.ikkerens.spleef.commands.CancelCommand;
import com.ikkerens.spleef.commands.CreateCommand;
import com.ikkerens.spleef.commands.HelpCommand;
import com.ikkerens.spleef.commands.NotFoundCommand;
import com.ikkerens.spleef.exceptions.LogicalSpleefException;
import com.mbserver.api.CommandSender;

public class CommandIndex {
    private final SpleefPlugin plugin;
    private final CommandChild root;

    private void registerCommands() {
        final CommandHandler help = new HelpCommand();
        this.register( "", help );
        this.register( "help", help );
        this.register( "help %", help );

        final CommandHandler create = new CreateCommand();
        this.register( "create", create );
        this.register( "create %", create );

        final CommandHandler advance = new AdvanceCommand();
        this.register( "done", advance );
        this.register( "advance", advance );
        this.register( "cancel", new CancelCommand() );

        this.register( "% start", new HelpCommand() );
        this.register( "% stop", new HelpCommand() );

        this.register( "test", new NotFoundCommand() );
    }

    public CommandIndex( final SpleefPlugin plugin ) {
        this.plugin = plugin;
        this.root = new CommandChild();
        this.registerCommands();
    }

    public void resolve( final CommandSender sender, final String[] args ) throws LogicalSpleefException {
        CommandChild child = this.root;
        final ArrayList< String > paramRegister = new ArrayList< String >();

        // adds all urlparts to the first urlPart
        for ( final String urlPart : args )
            if ( !urlPart.equals( "" ) ) {
                child = child.getChild( paramRegister, urlPart );
                if ( child == null ) {
                    new NotFoundCommand().executeCommand( sender, args );
                    return;
                }
            }

        final String[] params = new String[ paramRegister.size() ];
        paramRegister.toArray( params );

        final CommandHandler handler = child.getHandler();
        if ( !sender.hasPermission( handler.getPermission() ) )
            handler.executeCommand( sender, params );
    }

    private void register( final String url, final CommandHandler handler ) {
        final String[] urlParts = url.split( " " );

        CommandChild child = this.root;
        for ( final String urlPart : urlParts )
            if ( !urlPart.equals( "" ) )
                child = child.getChildOrRegister( urlPart );

        handler.injectPlugin( this.plugin );
        child.setAction( handler );
    }
}
