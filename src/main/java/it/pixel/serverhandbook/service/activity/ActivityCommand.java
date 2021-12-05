package it.pixel.serverhandbook.service.activity;

import org.bukkit.entity.Player;

import java.io.IOException;

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
    public static void show(Player player) throws IOException, ClassNotFoundException {
        sendMessage(player, getAllActivities().stream().map(ActivityUtils::prepareActivityString).toList());
    }

}
