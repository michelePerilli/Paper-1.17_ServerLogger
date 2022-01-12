package it.pixel.serverhandbook.controller;

import it.pixel.serverhandbook.service.activity.ActivityCommand;
import it.pixel.serverhandbook.service.book.BookCommand;
import it.pixel.serverhandbook.service.coords.CoordsCommand;
import it.pixel.serverhandbook.service.here.HereCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import static it.pixel.serverhandbook.ServerHandbook.*;
import static it.pixel.serverhandbook.service.TextManager.customText;
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
            initializeFiles(player.getName());
        } catch (IOException e) {
            e.printStackTrace();
            player.sendMessage(customText("Errore nell'inizializzazione dei file, contatta pixel", ChatColor.RED));
            return false;
        }

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
                case CMD_BOOK -> BookCommand.write(player, args);
                case "fix" -> {
                    if (args[0].equals("pixel"))
                        fix();
                }


                //*********************** END COMMANDs SECTION ***********************//
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    // TODO only debug purpose
    private static void fix() {

    }



}

