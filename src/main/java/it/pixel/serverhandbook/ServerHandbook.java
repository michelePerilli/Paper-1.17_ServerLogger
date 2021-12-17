package it.pixel.serverhandbook;

import it.pixel.serverhandbook.controller.CommandsController;
import it.pixel.serverhandbook.controller.TabController;
import it.pixel.serverhandbook.listener.PlayerActivityListener;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * The type Server logger.
 */
public class ServerHandbook extends JavaPlugin {

    /**
     * The constant CMD_COORDS.
     */
    public static final String CMD_COORDS = "coords";
    /**
     * The constant CMD_ACTIVITY.
     */
    public static final String CMD_ACTIVITY = "activity";
    /**
     * The constant CMD_HERE.
     */
    public static final String CMD_HERE = "here";

    /**
     * On load.
     */
    @Override
    public void onLoad() {
        super.onLoad();
    }

    /**
     * On enable.
     */
    @SneakyThrows
    @Override
    public void onEnable() {

        Bukkit.getPluginManager().registerEvents(new PlayerActivityListener(), this);

        List<PluginCommand> commandList = Arrays.asList(
                getCommand(CMD_COORDS),
                getCommand(CMD_ACTIVITY),
                getCommand(CMD_HERE)
        );

        commandList.stream().filter(Objects::nonNull).forEach(cmd -> {
            cmd.setExecutor(new CommandsController());
            cmd.setTabCompleter(new TabController());
        });
    }

}
