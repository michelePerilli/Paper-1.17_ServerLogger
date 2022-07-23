package it.pixel.serverhandbook.controller;

import it.pixel.serverhandbook.service.activity.ActivityCommand;
import it.pixel.serverhandbook.service.coords.CoordsCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.HumanEntity;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * The type Tab controller.
 */
public class TabController implements TabCompleter {
    /**
     * On tab complete list.
     *
     * @param commandSender the command sender
     * @param command       the command
     * @param s             the s
     * @param strings       the strings
     * @return the list
     */
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();

        switch (CommandsController.Commands.valueOf(command.getName().toUpperCase())) {
            case ACTIVITY -> {
                switch (strings.length) {
                    case 1 -> {
                        commands.add(ActivityCommand.ActivityParams.REPORT.getParam());
                        commands.add(ActivityCommand.ActivityParams.FIND.getParam());
                        commands.add(ActivityCommand.ActivityParams.SHOW.getParam());
                        StringUtil.copyPartialMatches(strings[0], commands, completions);
                    }
                    case 2 -> {
                        if (strings[0].equalsIgnoreCase(ActivityCommand.ActivityParams.FIND.getParam()))
                            commands.add("<player>");
                        StringUtil.copyPartialMatches(strings[1], commands, completions);
                    }
                    default -> {
                    }
                }
            }
            case COORDS -> {
                switch (strings.length) {
                    case 1 -> {
                        commands.add(CoordsCommand.CoordsParams.SHARE.getParam());
                        commands.add(CoordsCommand.CoordsParams.ADD.getParam());
                        commands.add(CoordsCommand.CoordsParams.FIND.getParam());
                        commands.add(CoordsCommand.CoordsParams.SHOW.getParam());
                        commands.add(CoordsCommand.CoordsParams.DEL.getParam());
                        StringUtil.copyPartialMatches(strings[0], commands, completions);
                    }
                    case 2 -> {
                        switch (CoordsCommand.CoordsParams.parseParam(strings[0])) {
                            case ADD -> commands.add("<descrizione>");
                            case FIND -> commands.add("<filtro>");
                            case DEL -> commands.add("<id>");
                            case SHARE -> {
                                commands.addAll(Bukkit.getOnlinePlayers().stream().map(HumanEntity::getName).toList());
                                commands.add("all");
                            }
                            case SHOW -> {
                            }
                        }
                        StringUtil.copyPartialMatches(strings[1], commands, completions);

                    }
                    case 3 -> {
                        if (strings[0].equals(CoordsCommand.CoordsParams.SHARE.getParam()))
                            commands.add("<filtro>");
                        StringUtil.copyPartialMatches(strings[2], commands, completions);
                    }
                    default -> {
                    }
                }
            }
            case HERE -> {
            }

        }

        Collections.sort(completions);
        return completions;
    }
}
