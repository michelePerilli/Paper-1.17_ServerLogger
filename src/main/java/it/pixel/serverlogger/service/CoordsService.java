package it.pixel.serverlogger.service;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static it.pixel.serverlogger.utils.MessageUtil.*;

public class CoordsService extends BaseService {

    /**
     * Add new coords log
     *
     * @param arguments description
     * @param player    player
     */
    public static void add(String[] arguments, Player player) {

        StringBuilder description = new StringBuilder();
        for (int i = 1; i < arguments.length; i++) {
            description.append(arguments[i]).append(" ");
        }
        Location coords = player.getLocation();
        String line = String.format("%s;%d;%d;%d;%s", player.getName(), coords.getBlockX(), coords.getBlockY(), coords.getBlockZ(), description).trim();
        if (arguments.length > 1)
            log("coords.log", line);
    }


    /**
     * Read saved coords log
     *
     * @param player player
     */
    public static void show(Player player) {

        try {
            File file = new File("coords.log");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String row;
            while ((row = br.readLine()) != null) {
                String[] data = row.split(";");
                if (data[0].equals(player.getName())) {
                    String coords = String.format("%s%s%s %s %s", ChatColor.GOLD, ChatColor.BOLD, data[1], data[2], data[3]);
                    String desc = String.format("%s%s", ChatColor.GREEN, data[4]);
                    player.sendMessage(coords + ": " + desc);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Show in all chat your current position
     *
     * @param player player
     */
    public static void here(Player player) {
        Location data = player.getLocation();
        String coords = String.format("%s%s%s %s %s", ChatColor.DARK_AQUA, ChatColor.BOLD, data.getBlockX(), data.getBlockY(), data.getBlockZ());
        String nome = String.format("%s%s", ChatColor.DARK_PURPLE, player.getName());
        String dimension = goldText(getDimensionName(player.getWorld().getEnvironment()));

        Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage(nome + greyText(" è qui: ") + dimension + whiteText("  ") + coords));
    }



    private static String getDimensionName(World.Environment env) {
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


}
