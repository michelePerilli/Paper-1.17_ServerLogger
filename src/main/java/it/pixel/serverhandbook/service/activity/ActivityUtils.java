package it.pixel.serverhandbook.service.activity;

import it.pixel.serverhandbook.model.PlayerActivity;
import it.pixel.serverhandbook.service.BaseService;
import org.bukkit.ChatColor;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static it.pixel.serverhandbook.service.FileManager.readFile;
import static it.pixel.serverhandbook.service.FileManager.writeLine;
import static it.pixel.serverhandbook.service.TextManager.*;

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
        initializeFiles(name);
        writeLine(ACTIVITY_FILE, new PlayerActivity(false, getCurrentDate(), name, isJoin ? JOINED : LEFT));
    }


    /**
     * Gets all activities.
     *
     * @return the all activities
     * @throws IOException the io exception
     */
    protected static List<PlayerActivity> getAllActivities() throws Exception {
        return readFile(ACTIVITY_FILE).stream().map(PlayerActivity.class::cast).filter(c -> !c.deleted()).toList();
    }


    /**
     * Gets all activities.
     *
     * @return the all activities
     * @throws IOException the io exception
     */
    protected static List<PlayerActivity> getAllActivitiesByPlayer(String playerName) throws Exception {
        return readFile(ACTIVITY_FILE).stream().map(PlayerActivity.class::cast).filter(c -> !c.deleted() && c.playerName().contains(playerName)).toList();
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

    protected static String prepareActivityReportString(String playerName, Long time) {
        return textName(playerName) + textInfo(" → ") + textInfo(toTime(time));
    }


    protected static Long simpleStringToDate(String myDate) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Date date = null;
        try {
            date = sdf.parse(myDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    protected static String toTime(Long milliseconds) {
        int seconds = (int) (milliseconds / 1000) % 60;
        int minutes = (int) ((milliseconds / (1000 * 60)) % 60);
        int hours = (int) ((milliseconds / (1000 * 60 * 60)) % 24);

        return String.format("%02d h, %02d min, %02d sec",
                hours,
                minutes, seconds
        );
    }
}
