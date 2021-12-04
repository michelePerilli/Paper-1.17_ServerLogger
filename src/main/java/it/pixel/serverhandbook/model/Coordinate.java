package it.pixel.serverhandbook.model;

import lombok.Getter;

/**
 * The type Coordinate.
 */
public class Coordinate {
    /**
     * The Player name.
     */
    @Getter
    private final String playerName;
    /**
     * The X.
     */
    @Getter
    private final String x;
    /**
     * The Y.
     */
    @Getter
    private final String y;
    /**
     * The Z.
     */
    @Getter
    private final String z;
    /**
     * The Description.
     */
    @Getter
    private final String description;

    /**
     * Instantiates a new Coordinate.
     *
     * @param playerName  the player name
     * @param x           the x
     * @param y           the y
     * @param z           the z
     * @param description the description
     */
    public Coordinate(String playerName, String x, String y, String z, String description) {
        this.playerName = playerName;
        this.x = x;
        this.y = y;
        this.z = z;
        this.description = description;
    }

    /**
     * Instantiates a new Coordinate.
     *
     * @param data the data
     */
    public Coordinate(String... data) {
        this.playerName = data[0];
        this.x = data[1];
        this.y = data[2];
        this.z = data[3];
        this.description = data[4];
    }

}
