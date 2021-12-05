package it.pixel.serverhandbook.service;

import org.bukkit.World;
import org.bukkit.entity.Player;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.bukkit.World.Environment.*;

/**
 * The type Base service.
 */
public abstract class BaseService {

    public enum Dimension implements Serializable {
        OVERWORLD,
        NETHER,
        END
    }

    /**
     * Date format
     */
    private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";

    /**
     * get current date and time
     *
     * @return current date
     */
    public static String getCurrentDate() {
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
    protected static World.Environment getEnvironmentFormDimension(Dimension dimension) {
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
     * @param environment the environment
     * @return the world . environment
     */
    protected static Dimension getDimensionFormEnvironment(World.Environment environment) {
        switch (environment) {
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
    protected static String getDimensionName(World.Environment env) {
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
    protected static List<String> getParameters(String[] arguments) {
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
    protected static void sendMessage(Player target, String header) {
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
    protected static void sendMessage(Player target, String header, List<String> rows) {
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
    protected static void sendMessage(Player target, List<String> rows) {
        target.sendMessage(" ");
        rows.forEach(target::sendMessage);
        target.sendMessage(" ");
    }
}
