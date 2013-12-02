package com.ikkerens.spleef;

public abstract class Permissions {
    private Permissions() {
        throw new UnsupportedOperationException();
    }

    private static final String PREFIX    = "ikkerens.spleef.";

    // Gameplay permissions
    public static final String  PLAY      = PREFIX + "play";

    // Admin permissions
    /**
     * Used to determine wether the user can destroy blocks within the arena.
     */
    public static final String  DESTROY   = PREFIX + "admin.destroy";

    /**
     * Used to determine wether the user can create new arenas.
     */
    public static final String  CREATE    = PREFIX + "admin.create";

    /**
     * Used to determine wether the user can configure existing arenas.
     */
    public static final String  CONFIGURE = PREFIX + "admin.configure";

    /**
     * Used to determine wether the user can start/stop arenas.
     */
    public static final String  CONTROL   = PREFIX + "admin.control";
}
