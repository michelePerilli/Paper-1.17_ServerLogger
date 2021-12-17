package it.pixel.serverhandbook.service.activity;

import it.pixel.serverhandbook.model.PlayerActivity;
import it.pixel.serverhandbook.service.BaseService;
import org.bukkit.ChatColor;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static it.pixel.files.FileManager.readFile;
import static it.pixel.files.FileManager.writeLine;
import static it.pixel.serverhandbook.service.TextManager.customText;

/**
 * The type Activity utils.
 */
public abstract class ActivityUtils extends BaseService {

    /**
     * The constant LOG_FILE.
     */
    public static final String ACTIVITY_FILE = "plugins/ServerHandbook/activity.dxl";

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
        writeLine(ACTIVITY_FILE, new PlayerActivity(false, getCurrentDate(), name, isJoin ? JOINED : LEFT));
    }


    /**
     * Gets all activities.
     *
     * @return the all activities
     * @throws IOException the io exception
     */
    protected static List<PlayerActivity> getAllActivities() throws IOException, ClassNotFoundException {
        return readFile(ACTIVITY_FILE).stream().map(x -> (PlayerActivity) x).filter(c -> !c.deleted()).collect(Collectors.toList());
    }


    /**
     * Gets all activities.
     *
     * @return the all activities
     * @throws IOException the io exception
     */
    protected static List<PlayerActivity> getAllActivitiesByPlayer(String playerName) throws IOException, ClassNotFoundException {
        return readFile(ACTIVITY_FILE).stream().map(x -> (PlayerActivity) x).filter(c -> !c.deleted() && c.playerName().contains(playerName)).collect(Collectors.toList());
    }

    /**
     * Prepare activity string string.
     *
     * @param activity the activity
     * @return the string
     */
    protected static String prepareActivityString(PlayerActivity activity) {
        String date = customText(activity.date(), ChatColor.RED);
        String name = customText(activity.playerName(), ChatColor.YELLOW);
        String act = customText(activity.activity(), ChatColor.YELLOW);
        return date + " " + name + " " + act;
    }
}
