package it.pixel.serverhandbook.service;

import org.bukkit.World;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The type Base service.
 */
public abstract class BaseService {


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

}
