package it.pixel.serverhandbook.controller;

import it.pixel.serverhandbook.service.coords.CoordsCommand;
import it.pixel.serverhandbook.service.here.HereCommand;
import it.pixel.serverhandbook.service.activity.ActivityCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static it.pixel.serverhandbook.ServerHandbook.*;
import static it.pixel.serverhandbook.service.coords.CoordsUtils.*;

/**
 * The type Commands controller.
 */
public class CommandsController implements CommandExecutor {

    /**
     * On command boolean.
     *
     * @param sender  the sender
     * @param command the command
     * @param label   the label
     * @param args    the args
     * @return the boolean
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        // End if is sent from console
        if (!(sender instanceof Player)) return true;
        // Player init
        Player player = (Player) sender;

        try {
            //************************* COMMANDs SECTION *************************//
            if (command.getName().equalsIgnoreCase(CMD_COORDS)) {

                // Check for wrong args len
                if (args.length < 1) return false;

                //********* /coords add <description> *********//
                if (args[0].equalsIgnoreCase(PARAM_ADD))
                    CoordsCommand.add(args, player);

                if (args[0].equalsIgnoreCase(PARAM_SHOW))
                    CoordsCommand.show(player);

            }

            if (command.getName().equalsIgnoreCase(CMD_ACTIVITY))
                ActivityCommand.show(player);


            if (command.getName().equalsIgnoreCase(CMD_HERE))
                HereCommand.here(player);
            //*********************** END COMMANDs SECTION ***********************//
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
