package it.pixel.serverhandbook.service.activity;

import it.pixel.serverhandbook.model.PlayerActivity;
import org.bukkit.ChatColor;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static it.pixel.serverhandbook.service.BaseService.*;
import static it.pixel.serverhandbook.service.FileManager.readFile;
import static it.pixel.serverhandbook.service.FileManager.writeLine;
import static it.pixel.serverhandbook.service.TextManager.*;

/**
 * The type Activity utils.
 */
public interface ActivityUtils {

    /**
     * The constant LOG_FILE.
     */
    String ACTIVITY_FILE = "plugins/ServerHandbook/activity.dxl";

    /**
     * The constant JOINED.
     */
    String JOINED = "joined the game";

    /**
     * The constant LEFT.
     */
    String LEFT = "left the game";

    /**
     * Add server log record
     *
     * @param name   player name
     * @param isJoin joined or left
     * @throws IOException the io exception
     */
    static void trackActivity(String name, Boolean isJoin) throws IOException {
        initializeFiles(name);
        writeLine(ACTIVITY_FILE, new PlayerActivity(false, getCurrentDate(), name, isJoin ? JOINED : LEFT));
    }


    /**
     * Gets all activities.
     *
     * @return the all activities
     * @throws IOException the io exception
     */
    static List<PlayerActivity> getAllActivities() throws Exception {
        return readFile(ACTIVITY_FILE).stream().map(PlayerActivity.class::cast).filter(c -> !c.deleted()).toList();
    }


    /**
     * Gets all activities.
     *
     * @return the all activities
     * @throws IOException the io exception
     */
    static List<PlayerActivity> getAllActivitiesByPlayer(String playerName) throws Exception {
        return readFile(ACTIVITY_FILE).stream().map(PlayerActivity.class::cast).filter(c -> !c.deleted() && c.playerName().contains(playerName)).toList();
    }

    /**
     * Prepare activity string string.
     *
     * @param activity the activity
     * @return the string
     */
    static String prepareActivityString(PlayerActivity activity) {
        String date = customText(activity.date(), ChatColor.RED);
        String name = customText(activity.playerName(), ChatColor.YELLOW);
        String act = customText(activity.activity(), ChatColor.YELLOW);
        return date + " " + name + " " + act;
    }

    static String prepareActivityReportString(String playerName, Long time) {
        return textInfo(toTime(time)) + textInfo(" → ") + textName(playerName);
    }

    static String prepareActivityReportStringGold(String playerName, Long time) {
        return textInfo(toTime(time)) + textInfo(" → ") + textNameGold(playerName);
    }


    static Long simpleStringToDate(String myDate) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Date date = null;
        try {
            date = sdf.parse(myDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    static String toTime(Long milliseconds) {
        int seconds = (int) (milliseconds / 1000) % 60;
        int minutes = (int) ((milliseconds / (1000 * 60)) % 60);
        int hours = (int) ((milliseconds / (1000 * 60 * 60)) % 24);
        int days = (int) (milliseconds / (1000 * 60 * 60 * 24));

        return String.format("%3d d, %02d h, %02d min, %02d sec",
                days,
                hours,
                minutes,
                seconds
        );
    }
}
