package it.pixel.serverhandbook.model;

import lombok.Getter;

/**
 * The type Player activity.
 */
public class PlayerActivity {
    /**
     * The Date.
     */
    @Getter
    private final String date;
    /**
     * The Player name.
     */
    @Getter
    private final String playerName;
    /**
     * The Activity.
     */
    @Getter
    private final String activity;

    /**
     * Instantiates a new Player activity.
     *
     * @param date       the date
     * @param playerName the player name
     * @param activity   the activity
     */
    public PlayerActivity(String date, String playerName, String activity) {
        this.date = date;
        this.playerName = playerName;
        this.activity = activity;
    }

    /**
     * Instantiates a new Player activity.
     *
     * @param data the data
     */
    public PlayerActivity(String... data) {
        this.date = data[0];
        this.playerName = data[1];
        this.activity = data[2];
    }

}
