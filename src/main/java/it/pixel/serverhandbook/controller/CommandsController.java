package it.pixel.serverhandbook.controller;

import it.pixel.serverhandbook.service.activity.ActivityCommand;
import it.pixel.serverhandbook.service.coords.CoordsCommand;
import it.pixel.serverhandbook.service.here.HereCommand;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static it.pixel.serverhandbook.service.BaseService.initializeFiles;

/**
 * The type Commands controller.
 */
public class CommandsController implements CommandExecutor {

    @Getter
    public enum Commands {
        COORDS("coords"),
        ACTIVITY("activity"),
        HERE("here");

        private final String cmd;

        Commands(String cmd) {
            this.cmd = cmd;
        }
    }

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
        switch (Commands.valueOf(command.getName().toUpperCase())) {
            case COORDS -> {
                if (args.length < 1)
                    args = new String[]{CoordsCommand.CoordsParams.SHOW.getParam()};


                switch (CoordsCommand.CoordsParams.parseParam(args[0])) {
                    case ADD -> CoordsCommand.add(player, args);
                    case SHARE -> CoordsCommand.share(player, args);
                    case FIND -> CoordsCommand.search(player, args);
                    case DEL -> CoordsCommand.del(player, args);
                    case SHOW -> CoordsCommand.show(player);
                }
            }

            case ACTIVITY -> {
                if (args.length < 1)
                    args = new String[]{ActivityCommand.ActivityParams.SHOW.getParam()};


                switch (ActivityCommand.ActivityParams.parseParam(args[0])) {
                    case FIND -> ActivityCommand.find(player, args);
                    case REPORT -> ActivityCommand.stats(player);
                    case SHOW -> ActivityCommand.show(player);
                }
            }
            case HERE -> HereCommand.here(player, args);


            //*********************** END COMMANDs SECTION ***********************//
        }

        return true;
    }
}

