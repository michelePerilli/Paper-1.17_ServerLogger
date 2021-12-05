package it.pixel.serverhandbook.service.activity;

import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        List<String> messages = new ArrayList<>();
        messages.add(" ");
        messages.addAll(getAllActivities().stream().map(ActivityUtils::prepareActivityString).collect(Collectors.toList()));
        messages.add(" ");
        messages.forEach(player::sendMessage);

    }

}
