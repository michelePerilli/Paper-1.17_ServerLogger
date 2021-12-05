package it.pixel.serverhandbook.model;

import java.io.Serializable;

/**
 * The type Coordinate.
 */
public record Coordinate(String playerName,
                         int x,
                         int y,
                         int z,
                         String description) implements Serializable {

}
