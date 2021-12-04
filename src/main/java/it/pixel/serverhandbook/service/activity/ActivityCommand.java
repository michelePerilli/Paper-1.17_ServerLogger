package it.pixel.serverhandbook.service.activity;

import it.pixel.serverhandbook.model.PlayerActivity;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.List;

/**
 * The type Logging service.
 */
public class ActivityCommand extends ActivityUtils {

    /**
     * Show server log list
     *
     * @param player player
     * @throws IOException the io exception
     */
    public static void show(Player player) throws IOException {

        List<PlayerActivity> coords = getAllActivities();

        coords.forEach(c -> player.sendMessage(prepareActivityString(c)));

    }

}
