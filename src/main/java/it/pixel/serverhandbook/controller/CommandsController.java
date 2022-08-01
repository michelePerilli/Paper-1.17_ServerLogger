package it.pixel.serverhandbook.controller;

import it.pixel.serverhandbook.model.Commands;
import it.pixel.serverhandbook.service.activity.ActivityCommand;
import it.pixel.serverhandbook.service.coords.CoordsCommand;
import it.pixel.serverhandbook.service.here.HereCommand;
import lombok.SneakyThrows;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static it.pixel.serverhandbook.service.BaseService.initializeFiles;
import static it.pixel.serverhandbook.service.activity.ActivityUtils.PARAM_FIND;
import static it.pixel.serverhandbook.service.activity.ActivityUtils.PARAM_REPORT;
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
    @SneakyThrows
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        // End if is sent from console
        if (!(sender instanceof Player player)) return false;

        initializeFiles(player.getName());

        //************************* COMMANDs SECTION *************************//
        switch (Commands.valueOf(command.getName())) {
            case COORDS -> {
                if (args.length < 1)
                    args = new String[]{PARAM_SHOW};

                switch (args[0]) {
                    case PARAM_ADD -> CoordsCommand.add(player, args);
                    case PARAM_SHARE -> CoordsCommand.share(player, args);
                    case PARAM_SEARCH -> CoordsCommand.search(player, args);
                    case PARAM_DEL -> CoordsCommand.del(player, args);
                    default -> CoordsCommand.show(player);
                }
            }

            case ACTIVITY -> {
                if (args.length < 1)
                    args = new String[]{PARAM_SHOW};
                switch (args[0]) {
                    case PARAM_FIND -> ActivityCommand.find(player, args);
                    case PARAM_REPORT -> ActivityCommand.stats(player);
                    default -> ActivityCommand.show(player);
                }
            }
            case HERE -> HereCommand.here(player, args);


            //*********************** END COMMANDs SECTION ***********************//
        }

        return true;
    }
}

