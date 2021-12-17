package it.pixel.serverhandbook.controller;

import it.pixel.serverhandbook.model.Coordinate;
import it.pixel.serverhandbook.service.activity.ActivityCommand;
import it.pixel.serverhandbook.service.coords.CoordsCommand;
import it.pixel.serverhandbook.service.here.HereCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import static it.pixel.files.FileManager.readFile;
import static it.pixel.files.FileManager.writeLine;
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
            switch (command.getName()) {
                case CMD_COORDS -> {
                    if (args.length < 1)
                        args = new String[]{PARAM_SHOW};

                    switch (args[0]) {
                        case PARAM_ADD -> CoordsCommand.add(player, args);
                        case PARAM_SHARE -> CoordsCommand.showTo(player, args);
                        case PARAM_SEARCH -> CoordsCommand.search(player, args);
                        case PARAM_DEL -> CoordsCommand.del(player, args);
                        default -> CoordsCommand.show(player);
                    }
                }
                case CMD_ACTIVITY -> {
                    if (args.length < 1)
                        ActivityCommand.show(player);
                    else
                        ActivityCommand.find(player, args);
                }
                case CMD_HERE -> HereCommand.here(player, args);
                case "update" -> update();
                //*********************** END COMMANDs SECTION ***********************//
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private static void update() throws IOException, ClassNotFoundException {
        List<Coordinate> lista = readFile(FILES_PATH + "coords.dxl")
                .stream()
                .map(s -> (Coordinate) s)
                .toList();
        for (Coordinate c : lista) {
            writeLine(FILES_PATH + c.playerName() + ".dxl", c);
        }
    }


}

