package it.pixel.serverhandbook.controller;

import it.pixel.serverhandbook.service.activity.ActivityCommand;
import it.pixel.serverhandbook.service.coords.CoordsCommand;
import it.pixel.serverhandbook.service.here.HereCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static com.sun.tools.javac.util.StringUtils.toLowerCase;
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
        if (!(sender instanceof Player player)) return false;

        try {
            //************************* COMMANDs SECTION *************************//
            switch (toLowerCase(command.getName())) {
                case CMD_COORDS -> {
                    if (args.length < 1)
                        args = new String[]{PARAM_SHOW};

                    switch (toLowerCase(args[0])) {
                        case PARAM_ADD -> CoordsCommand.add(player, args);
                        case PARAM_SHOW_TO -> CoordsCommand.showTo(player, args);
                        case PARAM_SEARCH -> CoordsCommand.search(player, args);
                        default -> CoordsCommand.show(player);
                    }
                }
                case CMD_ACTIVITY -> ActivityCommand.show(player);
                case CMD_HERE -> HereCommand.here(player, args);

                //*********************** END COMMANDs SECTION ***********************//
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}

