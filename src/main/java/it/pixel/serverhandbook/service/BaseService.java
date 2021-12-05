package it.pixel.serverhandbook.service;

import org.bukkit.ChatColor;
import org.bukkit.World;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
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
     * Read file list.
     *
     * @param filename the filename
     * @return the list
     * @throws IOException the io exception
     */
    protected static List<String> readFile(String filename) throws IOException {
        BufferedReader br = getFileReader(filename);
        List<String> rows = new ArrayList<>();

        String row;
        while ((row = br.readLine()) != null) rows.add(row);
        br.close();

        return rows;
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
        }
        return "";
    }

    /**
     * Text color by dimension string.
     *
     * @param env the env
     * @param msg the msg
     * @return the string
     */
    protected static String textColorByDimension(World.Environment env, String msg) {
        switch (env) {
            case NETHER: {
                return customText(msg, ChatColor.DARK_RED, ChatColor.BOLD);
            }
            case THE_END: {
                return customText(msg, ChatColor.DARK_GRAY, ChatColor.BOLD);
            }
            case NORMAL: {
                return customText(msg, ChatColor.DARK_GREEN, ChatColor.BOLD);
            }
        }
        return msg;
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

    /**
     * Text name string.
     *
     * @param name the name
     * @return the string
     */
    protected static String textName(String name) {
        return customText(name, ChatColor.DARK_PURPLE, ChatColor.BOLD);
    }

    /**
     * Text coordinate string.
     *
     * @param coordinate the coordinate
     * @return the string
     */
    protected static String textCoordinate(String coordinate) {
        return customText(coordinate, ChatColor.DARK_AQUA, ChatColor.BOLD);
    }

    /**
     * Text info string.
     *
     * @param msg the msg
     * @return the string
     */
    protected static String textInfo(String msg) {
        return customText(msg, ChatColor.GRAY);
    }

    /**
     * Text descrizione string.
     *
     * @param msg the msg
     * @return the string
     */
    protected static String textDescription(String msg) {
        return customText(msg, ChatColor.GOLD);
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
