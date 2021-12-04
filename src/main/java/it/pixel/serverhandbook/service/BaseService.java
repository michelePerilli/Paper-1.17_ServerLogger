package it.pixel.serverhandbook.service;

import org.bukkit.ChatColor;
import org.bukkit.World;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.stream.Collectors;

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
     * Gets file reader.
     *
     * @param file the file
     * @return the file reader
     * @throws FileNotFoundException the file not found exception
     */
    protected static BufferedReader getFileReader(String file) throws FileNotFoundException {
        return new BufferedReader(new FileReader(file));
    }

    /**
     * Gets file writer.
     *
     * @param file the file
     * @return the file writer
     * @throws IOException the io exception
     */
    protected static PrintWriter getFileWriter(String file) throws IOException {
        return new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
    }

    /**
     * Save to log file the line
     *
     * @param file the path
     * @param line the line to log to file
     * @throws IOException the io exception
     */
    protected static void writeLine(String file, String line) throws IOException {
        PrintWriter writer = getFileWriter(file);
        writer.println(line);
        writer.close();
    }


    /**
     * Gets dimension name.
     *
     * @param env the env
     * @return the dimension name
     */
    protected static String getDimensionName(World.Environment env) {
        switch (env) {
            case NETHER: {
                return "Nether";
            }
            case THE_END: {
                return "End";
            }
            case NORMAL: {
                return "Overworld";
            }
            default: {
                throw new IllegalArgumentException(String.format("Unknown dim id %s", env));
            }
        }
    }


    /**
     * Grey text string.
     *
     * @param msg the msg
     * @return the string
     */
    protected static String greyText(String msg) {
        return customText(msg, ChatColor.GRAY);
    }

    /**
     * Custom text string.
     *
     * @param msg        the msg
     * @param chatColors the chat colors
     * @return the string
     */
    protected static String customText(String msg, ChatColor... chatColors) {
        return ChatColor.RESET.toString().concat(Arrays.stream(chatColors).map(ChatColor::toString).collect(Collectors.joining(""))).concat(msg);
    }

}
