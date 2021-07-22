package it.pixel.serverlogger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;

public class ServerLogger extends JavaPlugin {



    @Override
    public void onLoad() {
        super.onLoad();
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new PlayerActivityListener(), this);
        getCommand("coords").setExecutor(new LoggerCommands());
    }


}
