package it.pixel.serverlogger;

import it.pixel.serverlogger.controller.CommandsController;
import it.pixel.serverlogger.listener.PlayerActivityListener;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class ServerLogger extends JavaPlugin {

    @Override
    public void onLoad() {
        super.onLoad();
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new PlayerActivityListener(), this);
        PluginCommand coordsCommand = getCommand("coords");
        PluginCommand logCommand = getCommand("log");

        if (coordsCommand != null) {
            coordsCommand.setExecutor(new CommandsController());
        }
        if (logCommand != null) {
            logCommand.setExecutor(new CommandsController());
        }
    }


}
