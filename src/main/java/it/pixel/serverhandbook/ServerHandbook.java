package it.pixel.serverhandbook;

import it.pixel.serverhandbook.controller.CommandsController;
import it.pixel.serverhandbook.listener.PlayerActivityListener;
import it.pixel.serverhandbook.model.Coordinate;
import it.pixel.serverhandbook.model.PlayerActivity;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static it.pixel.serverhandbook.service.activity.ActivityUtils.ACTIVITY_FILE;
import static it.pixel.serverhandbook.service.coords.CoordsUtils.COORDS_FILE;

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

        initializeFiles();

        Bukkit.getPluginManager().registerEvents(new PlayerActivityListener(), this);

        List<PluginCommand> commandList = Arrays.asList(
                getCommand(CMD_COORDS),
                getCommand(CMD_ACTIVITY),
                getCommand(CMD_HERE)
        );

        commandList.stream().filter(Objects::nonNull).forEach(cmd -> cmd.setExecutor(new CommandsController()));

    }

    /**
     * Initialize files.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void initializeFiles() throws IOException {
        File folder = new File("plugins/ServerHandbook/");
        File activity = new File(ACTIVITY_FILE);
        File coordinate = new File(COORDS_FILE);

        folder.mkdir();
        if (!activity.exists()) {
            new ObjectOutputStream(new FileOutputStream(ACTIVITY_FILE, true)).writeObject(new PlayerActivity("", "", ""));
        }
        if (!coordinate.exists()) {
            new ObjectOutputStream(new FileOutputStream(COORDS_FILE, true)).writeObject(new Coordinate("header", 1, 1, 1, ""));
        }
    }
}
