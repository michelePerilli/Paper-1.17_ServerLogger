package it.pixel.serverlogger.service;

import it.pixel.serverlogger.utils.DateUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LoggingService extends BaseService {

    /**
     * Add server log record
     * @param name player name
     * @param isJoin joined or left
     */
    public static void add(String name, Boolean isJoin) {
        String line = String.format("%s%s %s%s%s", ChatColor.RED, DateUtil.getCurrentDate(), ChatColor.YELLOW, name, isJoin ? " joined the game": " left the game");

        log("server.log", line);
    }

    /**
     * Show server log list
     *
     * @param player player
     */
    public static void show(Player player) {
        try {
            File file = new File("server.log");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String row;
            while ((row = br.readLine()) != null) {
                player.sendMessage(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
