package com.ikkerens.spleef.commands.process;

import com.ikkerens.spleef.exceptions.AlreadyInProcessException;
import com.mbserver.api.game.Player;

public class CreateProcess extends Process {

    public CreateProcess( final Player player ) throws AlreadyInProcessException {
        super( player );
    }

    @Override
    public String getBusyMessage() {
        return "creating an arena";
    }

    @Override
    public boolean validate( final int step ) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void onStep( final int step ) {
        // TODO Auto-generated method stub

    }

}
