package com.ikkerens.spleef.commands.handling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.ikkerens.spleef.commands.NotFoundCommand;

public class CommandChild {
    private final Map< String, CommandChild > children;
    private CommandHandler                    action;

    public CommandChild() {
        this.children = new HashMap< String, CommandChild >();
        this.action = new NotFoundCommand();
    }

    public void addChild( final String childName, final CommandChild subRoute ) {
        this.children.put( childName, subRoute );
    }

    public CommandChild getChildOrRegister( final String routeName ) {
        if ( !this.children.containsKey( routeName ) )
            this.children.put( routeName, new CommandChild() );

        return this.getChild( null, routeName );
    }

    public CommandChild getChild( final ArrayList< String > paramRegister, final String routeName ) {
        CommandChild returnV = this.children.get( routeName );

        if ( returnV == null ) {
            returnV = this.children.get( "%" );
            if ( returnV != null )
                paramRegister.add( routeName );
        }

        return returnV;
    }

    public void setAction( final CommandHandler action ) {
        this.action = action;
    }

    public CommandHandler getHandler() {
        return this.action;
    }
}
