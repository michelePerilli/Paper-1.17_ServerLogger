package it.pixel.serverhandbook.service;

import org.bukkit.World;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

import static it.pixel.serverhandbook.service.activity.ActivityUtils.ACTIVITY_FILE;
import static org.bukkit.World.Environment.*;

/**
 * The type Base service.
 */
public interface BaseService {

    public static final String FILES_PATH = "plugins/ServerHandbook/";


    public enum Dimension implements Serializable {
        OVERWORLD,
        NETHER,
        END
    }

    static String getFileName(Player player) {
        return FILES_PATH + player.getName() + ".dxl";
    }

    /**
     * Date format
     */
    String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";

    /**
     * get current date and time
     *
     * @return current date
     */
    static String getCurrentDate() {
        SimpleDateFormat date = new SimpleDateFormat(DATE_FORMAT);
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Rome");
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(new Date());
        date.setTimeZone(timeZone);

        return date.format(calendar.getTime());
    }


    /**
     * Get environment form dimension world . environment.
     *
     * @param dimension the dimension
     * @return the world . environment
     */
    static World.Environment getEnvironmentFormDimension(Dimension dimension) {
        switch (dimension) {
            case OVERWORLD -> {
                return NORMAL;
            }
            case NETHER -> {
                return NETHER;
            }
            case END -> {
                return THE_END;
            }
        }
        return CUSTOM;
    }

    /**
     * Get environment form dimension world . environment.
     *
     * @param player the player
     * @return the world . environment
     */
    static Dimension getPlayerDimension(Player player) {
        switch (player.getWorld().getEnvironment()) {
            case NORMAL -> {
                return Dimension.OVERWORLD;
            }
            case NETHER -> {
                return Dimension.NETHER;
            }
            case THE_END -> {
                return Dimension.END;
            }
        }
        return null;
    }

    /**
     * Gets dimension name.
     *
     * @param env the env
     * @return the dimension name
     */
    static String getDimensionName(World.Environment env) {
        switch (env) {
            case NETHER -> {
                return "Nether";
            }
            case THE_END -> {
                return "End";
            }
            case NORMAL -> {
                return "Overworld";
            }
        }
        return "";
    }


    /**
     * Get parameters list.
     *
     * @param arguments the arguments
     * @return the list
     */
    static List<String> getParameters(String[] arguments) {
        List<String> params = new ArrayList<>(Arrays.asList(arguments));
        params.remove(0);
        return params;
    }


    /**
     * Send message.
     *
     * @param target the target
     * @param header the header
     */
    static void sendMessage(Player target, String header) {
        target.sendMessage(" ");
        target.sendMessage(header);
        target.sendMessage(" ");
    }

    /**
     * Send message.
     *
     * @param target the target
     * @param rows   the rows
     */
    static void sendMessage(Player target, String header, List<String> rows) {
        target.sendMessage(" ");
        target.sendMessage(header);
        rows.forEach(target::sendMessage);
        target.sendMessage(" ");
    }


    /**
     * Send message.
     *
     * @param target the target
     * @param rows   the rows
     */
    static void sendMessage(Player target, List<String> rows) {
        target.sendMessage(" ");
        rows.forEach(target::sendMessage);
        target.sendMessage(" ");
    }


    /**
     * Initialize files.
     *
     * @throws IOException the io exception
     */
    static void initializeFiles(String playerName) throws IOException {
        File workspace = new File("plugins/ServerHandbook/");
        if (!workspace.exists()) {
            workspace.mkdir();
        }

        new File("plugins/ServerHandbook/" + playerName + ".dxl").createNewFile();
        new File(ACTIVITY_FILE).createNewFile();
    }
}
