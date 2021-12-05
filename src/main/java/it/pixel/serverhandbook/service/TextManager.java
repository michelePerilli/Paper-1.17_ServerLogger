package it.pixel.serverhandbook.service;

import org.bukkit.ChatColor;
import org.bukkit.World;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TextManager {

    /**
     * Text color by dimension string.
     *
     * @param env the env
     * @param msg the msg
     * @return the string
     */
    public static String textColorByDimension(World.Environment env, String msg) {
        switch (env) {
            case NETHER -> {
                return customText(msg, ChatColor.DARK_RED, ChatColor.BOLD);
            }
            case THE_END -> {
                return customText(msg, ChatColor.DARK_GRAY, ChatColor.BOLD);
            }
            case NORMAL -> {
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
    public static String customText(String msg, ChatColor... chatColors) {
        return ChatColor.RESET.toString().concat(Arrays.stream(chatColors).map(ChatColor::toString).collect(Collectors.joining(""))).concat(msg);
    }

    /**
     * Text name string.
     *
     * @param name the name
     * @return the string
     */
    public static String textName(String name) {
        return customText(name, ChatColor.DARK_PURPLE, ChatColor.BOLD);
    }

    /**
     * Text coordinate string.
     *
     * @param coordinate the coordinate
     * @return the string
     */
    public static String textCoordinate(String coordinate) {
        return customText(coordinate, ChatColor.DARK_AQUA, ChatColor.BOLD);
    }

    /**
     * Text info string.
     *
     * @param msg the msg
     * @return the string
     */
    public static String textInfo(String msg) {
        return customText(msg, ChatColor.GRAY);
    }

    /**
     * Text descrizione string.
     *
     * @param msg the msg
     * @return the string
     */
    public static String textDescription(String msg) {
        return customText(msg, ChatColor.GOLD);
    }

}
