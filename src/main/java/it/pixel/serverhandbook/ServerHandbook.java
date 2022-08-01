package it.pixel.serverhandbook;

import it.pixel.serverhandbook.controller.CommandsController;
import it.pixel.serverhandbook.controller.TabController;
import it.pixel.serverhandbook.listener.PlayerActivityListener;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.stream.Stream;

import static it.pixel.serverhandbook.model.Commands.*;

/**
 * The type Server logger.
 */
public class ServerHandbook extends JavaPlugin {

    /**
     * On enable.
     */
    @SneakyThrows
    @Override
    public void onEnable() {

        Bukkit.getPluginManager().registerEvents(new PlayerActivityListener(), this);

        Stream.of(getCommand(COORDS.getCmd()), getCommand(ACTIVITY.getCmd()), getCommand(HERE.getCmd()))
                .filter(Objects::nonNull)
                .forEach(cmd -> {
                    cmd.setExecutor(new CommandsController());
                    cmd.setTabCompleter(new TabController());
                });
    }

}
