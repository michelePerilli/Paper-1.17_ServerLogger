package it.pixel.serverhandbook.model;

import it.pixel.serverhandbook.Vector3i;
import it.pixel.serverhandbook.service.BaseService;
import org.bukkit.entity.Player;

import java.io.Serializable;

/**
 * The type Coordinate.
 */
public record Coordinate(boolean deleted,
                         String playerName,
                         BaseService.Dimension dimension,
                         Vector3i xyz,
                         String description) implements Serializable {

    /**
     * Gets player position.
     *
     * @param player the player
     * @return the player position
     */
    public static Vector3i getPlayerPosition(Player player) {
        return new Vector3i(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());
    }

}


