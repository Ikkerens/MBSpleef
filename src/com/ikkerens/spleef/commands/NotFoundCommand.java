package com.ikkerens.spleef.commands;

import com.ikkerens.spleef.Permissions;
import com.ikkerens.spleef.commands.handling.CommandHandler;
import com.mbserver.api.CommandSender;

public class NotFoundCommand extends CommandHandler {

    @Override
    public String getPermission() {
        return Permissions.PLAY;
    }

    @Override
    public void executeCommand( final CommandSender sender, final String[] args ) {
        sender.sendMessage( "Command not found!" );
    }

}
