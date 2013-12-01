package com.ikkerens.spleef.selection;

import com.mbserver.api.game.Location;
import com.mbserver.api.game.Player;

public interface Selector {
    Location getMinimumPosition( final Player player );

    Location getMaximumPosition( final Player player );
}
