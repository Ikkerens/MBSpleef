package com.ikkerens.spleef.selection;

import com.mbserver.api.Constructors;
import com.mbserver.api.game.Location;

public class Selection {
    private final Location min, max;

    public Selection( final Location pos1, final Location pos2 ) {
        this.min = Constructors.newLocation( pos1.getWorld(), Math.min( pos1.getX(), pos2.getX() ), Math.min( pos1.getY(), pos2.getY() ), Math.min( pos1.getZ(), pos2.getZ() ) );
        this.max = Constructors.newLocation( pos1.getWorld(), Math.max( pos1.getX(), pos2.getX() ), Math.max( pos1.getY(), pos2.getY() ), Math.max( pos1.getZ(), pos2.getZ() ) );
    }

    public Location getMinimumLocation() {
        return this.min;
    }

    public Location getMaximumLocation() {
        return this.max;
    }

    public boolean contains( final Location pos ) {
        return ( ( pos.getX() >= this.min.getX() ) && ( pos.getX() <= this.max.getX() ) &&
                ( pos.getY() >= this.min.getY() ) && ( pos.getY() <= this.max.getY() ) ) &&
                ( pos.getZ() >= this.min.getZ() ) && ( pos.getZ() <= this.max.getZ() );
    }
}
