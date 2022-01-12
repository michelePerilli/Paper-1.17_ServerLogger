package it.pixel.serverhandbook.controller;

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

import static it.pixel.serverhandbook.ServerHandbook.CMD_COORDS;
import static it.pixel.serverhandbook.service.coords.CoordsUtils.*;

public class TabController implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();

        if (command.getName().equals(CMD_COORDS)) {
            switch (strings.length) {
                case 1 -> {
                    commands.add(PARAM_SHARE);
                    commands.add(PARAM_ADD);
                    commands.add(PARAM_SEARCH);
                    commands.add(PARAM_SHOW);
                    StringUtil.copyPartialMatches(strings[0], commands, completions);
                }
                case 2 -> {
                    switch (strings[0]) {
                        case PARAM_ADD -> commands.add("<descrizione>");
                        case PARAM_SEARCH -> commands.add("<filtro>");
                        case PARAM_SHARE -> {
                            commands.addAll(Bukkit.getOnlinePlayers().stream().map(HumanEntity::getName).toList());
                            commands.add("all");
                        }
                    }
                    StringUtil.copyPartialMatches(strings[1], commands, completions);

                }
                case 3 -> {
                    if (strings[0].equals(PARAM_SHARE)) commands.add("<filtro>");
                    StringUtil.copyPartialMatches(strings[2], commands, completions);
                }
            }
        }

        Collections.sort(completions);
        return completions;
    }
}
