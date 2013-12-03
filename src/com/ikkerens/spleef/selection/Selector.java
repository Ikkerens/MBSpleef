package com.ikkerens.spleef.selection;

import com.mbserver.api.game.Player;

public interface Selector {
    Selection getSelection( final Player player );

    void clear( final Player player, final boolean restart );
}
