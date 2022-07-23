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

    String FILES_PATH = "plugins/ServerHandbook/";


    enum Dimension implements Serializable {
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
        return switch (dimension) {
            case OVERWORLD -> NORMAL;
            case NETHER -> NETHER;
            case END -> THE_END;
        };
    }

    /**
     * Get environment form dimension world . environment.
     *
     * @param player the player
     * @return the world . environment
     */
    static Dimension getPlayerDimension(Player player) {
        return switch (player.getWorld().getEnvironment()) {
            case NORMAL -> Dimension.OVERWORLD;
            case NETHER -> Dimension.NETHER;
            case THE_END -> Dimension.END;
            case CUSTOM -> null;
        };
    }

    /**
     * Gets dimension name.
     *
     * @param env the env
     * @return the dimension name
     */
    static String getDimensionName(World.Environment env) {
        return switch (env) {
            case NETHER -> "Nether";
            case THE_END -> "End";
            case NORMAL -> "Overworld";
            case CUSTOM -> "";
        };
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
        File workspace = new File(FILES_PATH);

        if (!(!workspace.exists() && workspace.mkdir())) {
            // Logger.getAnonymousLogger().warning("Errore creazione cartella workspace")
        }

        if (!new File(FILES_PATH + playerName + ".dxl").createNewFile()) {
            // Logger.getAnonymousLogger().warning("Errore creazione file utente")
        }

        if (!new File(ACTIVITY_FILE).createNewFile()) {
            // Logger.getAnonymousLogger().warning("Errore creazione file attività")
        }

    }
}
