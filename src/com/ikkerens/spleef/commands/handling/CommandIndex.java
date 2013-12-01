package com.ikkerens.spleef.commands.handling;

import java.util.ArrayList;

import com.ikkerens.spleef.commands.HelpCommand;
import com.ikkerens.spleef.commands.NotFoundCommand;
import com.mbserver.api.CommandSender;

public class CommandIndex {
    private static CommandIndex instance;

    private final CommandChild  root;

    private void registerCommands() {
        final CommandHandler help = new HelpCommand();
        this.register( "", help );
        this.register( "help", help );
        this.register( "help %", help );

        this.register( "% start", null );
        this.register( "% stop", null );

        this.register( "test", new NotFoundCommand() );
    }

    public static CommandIndex getInstance() {
        if ( instance == null )
            instance = new CommandIndex();

        return instance;
    }

    private CommandIndex() {
        this.root = new CommandChild();
        this.registerCommands();
    }

    public void resolve( final CommandSender player, final String[] args ) throws PlayerOnlyCommandException {
        CommandChild child = this.root;
        final ArrayList< String > paramRegister = new ArrayList< String >();

        // adds all urlparts to the first urlPart
        for ( final String urlPart : args )
            if ( !urlPart.equals( "" ) ) {
                child = child.getChild( paramRegister, urlPart );
                if ( child == null ) {
                    new NotFoundCommand().executeCommand( player, args );
                    return;
                }
            }

        final String[] params = new String[ paramRegister.size() ];
        paramRegister.toArray( params );

        child.getHandler().executeCommand( player, params );
    }

    private void register( final String url, final CommandHandler handler ) {
        final String[] urlParts = url.split( " " );

        CommandChild child = this.root;
        for ( final String urlPart : urlParts )
            if ( !urlPart.equals( "" ) )
                child = child.getChildOrRegister( urlPart );

        child.setAction( handler );
    }
}
