package it.pixel.serverlogger.utils;

import org.bukkit.ChatColor;

public class MessageUtil {

    public static String whiteText(String msg) {
        return String.format("%s%s", ChatColor.WHITE, msg);
    }

    public static String goldText(String msg) {
        return String.format("%s%s", ChatColor.GOLD, msg);
    }

    public static String greyText(String msg) {
        return String.format("%s%s", ChatColor.GRAY, msg);
    }
}
