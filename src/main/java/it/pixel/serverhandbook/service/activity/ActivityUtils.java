package it.pixel.serverhandbook.service.activity;

import it.pixel.serverhandbook.model.PlayerActivity;
import it.pixel.serverhandbook.service.BaseService;
import org.bukkit.ChatColor;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Activity utils.
 */
public abstract class ActivityUtils extends BaseService {

    /**
     * The constant LOG_FILE.
     */
    protected static final String ACTIVITY_FILE = "server.log";

    /**
     * The constant JOINED.
     */
    protected static final String JOINED = "joined the game";
    /**
     * The constant LEFT.
     */
    protected static final String LEFT = "left the game";

    /**
     * Add server log record
     *
     * @param name   player name
     * @param isJoin joined or left
     * @throws IOException the io exception
     */
    public static void trackActivity(String name, Boolean isJoin) throws IOException {
        writeLine(ACTIVITY_FILE, String.format("%s;%s;%s", getCurrentDate(), name, isJoin ? JOINED : LEFT));
    }


    /**
     * Gets all activities.
     *
     * @return the all activities
     * @throws IOException the io exception
     */
    protected static List<PlayerActivity> getAllActivities() throws IOException {

        List<String> rows = readFile(ACTIVITY_FILE);

        return rows
                .stream()
                .map(s -> new PlayerActivity(s.split(";")))
                .collect(Collectors.toList());
    }

    /**
     * Prepare activity string string.
     *
     * @param activity the activity
     * @return the string
     */
    protected static String prepareActivityString(PlayerActivity activity) {
        String date = customText(activity.getDate(), ChatColor.RED);
        String name = customText(activity.getPlayerName(), ChatColor.YELLOW);
        String act = customText(activity.getActivity(), ChatColor.YELLOW);
        return date + " " + name + " " + act;
    }
}
