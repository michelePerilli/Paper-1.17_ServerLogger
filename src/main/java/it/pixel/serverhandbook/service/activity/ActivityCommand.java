package it.pixel.serverhandbook.service.activity;

import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.Arrays;

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
    public static void show(Player player) throws Exception {
        sendMessage(player, getAllActivities().stream().map(ActivityUtils::prepareActivityString).toList());
    }


    /**
     * Show server log list
     *
     * @param player player
     * @throws IOException the io exception
     */
    public static void find(Player player, String[] args) throws Exception {
        String searchKey = String.join(" ", Arrays.asList(args));
        sendMessage(player, getAllActivitiesByPlayer(searchKey).stream().map(ActivityUtils::prepareActivityString).toList());
    }

}
