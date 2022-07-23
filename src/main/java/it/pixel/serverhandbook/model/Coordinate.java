package it.pixel.serverhandbook.model;

import it.pixel.serverhandbook.Vector3i;
import it.pixel.serverhandbook.service.BaseService;
import lombok.Data;
import org.bukkit.entity.Player;

import java.io.Serializable;


@Data
/**
 * The type Coordinate.
 */
public final class Coordinate implements Serializable {

    private long id;
    private String playerName;
    private BaseService.Dimension dimension;
    private Vector3i xyz;
    private String description;
    private boolean deleted;

    public Coordinate(long id,
                      String playerName,
                      BaseService.Dimension dimension,
                      Vector3i xyz,
                      String description,
                      boolean deleted) {
        this.id = id;
        this.playerName = playerName;
        this.dimension = dimension;
        this.xyz = xyz;
        this.description = description;
        this.deleted = deleted;
    }

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


