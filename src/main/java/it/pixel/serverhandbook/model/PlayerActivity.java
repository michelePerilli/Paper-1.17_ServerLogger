package it.pixel.serverhandbook.model;

import java.io.Serializable;

/**
 * The type Player activity.
 */
public record PlayerActivity(boolean deleted,
                             String date,
                             String playerName,
                             String activity) implements Serializable {

}
