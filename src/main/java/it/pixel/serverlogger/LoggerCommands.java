package it.pixel.serverlogger;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class LoggerCommands extends AbstractLogger implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        // End if is sent from console
        if (!(sender instanceof Player)) return true;
        // Player init
        Player player = (Player) sender;

        // Check for wrong args len
        if (args.length < 1) {
            player.sendMessage(ChatColor.DARK_GREEN + "/coords <description>");
            return false;
        }

        System.err.println(Arrays.toString(args));

        //************************* COMMANDs SECTION *************************//
        if (command.getName().equalsIgnoreCase("coords")) {

            //********* /coords add <description> *********//
            if (args[0].equalsIgnoreCase("add"))
                add(args, player);

            if (args[0].equalsIgnoreCase("show"))
                show(player);

        }
        //*********************** END COMMANDs SECTION ***********************//
        return true;
    }


    /**
     * Add new coords log
     *
     * @param arguments description
     * @param player    player
     */
    private void add(String[] arguments, Player player) {
        System.err.println("im in add");

        StringBuilder description = new StringBuilder();
        for (int i = 1; i < arguments.length; i++) {
            description.append(arguments[i]).append(" ");
        }
        Location coords = player.getLocation();
        String line = String.format("%s;%d;%d;%d;%s", player.getName(), coords.getBlockX(), coords.getBlockY(), coords.getBlockZ(), description).trim();
        System.err.println("close to end");
        log("coords.log", line);
    }

    /**
     * Read saved coords log
     *
     * @param player player
     */
    private void show(Player player) {

        try {

            File file = new File("coords.log");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String row;
            while ((row = br.readLine()) != null) {
                String[] data = row.split(";");
                if ( data[0].equals(player.getName())) {
                    String coords = String.format("%s%s%s %s %s", ChatColor.GOLD, ChatColor.BOLD, data[1], data[2], data[3]);
                    String desc = String.format("%s%s", ChatColor.GREEN, data[4]);
                    player.sendMessage(coords + ": " + desc);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}




