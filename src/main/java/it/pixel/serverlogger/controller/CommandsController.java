package it.pixel.serverlogger.controller;

import it.pixel.serverlogger.service.CoordsService;
import it.pixel.serverlogger.service.LoggingService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandsController implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        // End if is sent from console
        if (!(sender instanceof Player)) return true;
        // Player init
        Player player = (Player) sender;

        // Check for wrong args len
        if (args.length < 1) return false;

        //************************* COMMANDs SECTION *************************//
        if (command.getName().equalsIgnoreCase("coords")) {

            //********* /coords add <description> *********//
            if (args[0].equalsIgnoreCase("add"))
                CoordsService.add(args, player);

            if (args[0].equalsIgnoreCase("show"))
                CoordsService.show(player);

        }

        if (command.getName().equalsIgnoreCase("log")) {

            //********* /coords add <description> *********//
            if (args[0].equalsIgnoreCase("show"))
                LoggingService.show(player);

        }
        //*********************** END COMMANDs SECTION ***********************//
        return true;
    }
}
