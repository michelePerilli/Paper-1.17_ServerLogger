package it.pixel.serverhandbook.model;

import it.pixel.serverhandbook.service.BaseService;
import it.pixel.vectors.Vector3i;
import org.bukkit.Location;

import java.io.Serializable;

/**
 * The type Coordinate.
 */
public record Coordinate(boolean deleted,
                         String playerName,
                         BaseService.Dimension dimension,
                         Vector3i xyz,
                         String description) implements Serializable {

    public static Vector3i createVectorFromLocation(Location location) {
        return new Vector3i(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

}


