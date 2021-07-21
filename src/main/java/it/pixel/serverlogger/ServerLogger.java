package it.pixel.serverlogger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class ServerLogger extends JavaPlugin {


    @Override
    public void onLoad() {
        super.onLoad();
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "ServerLogger disabled");
    }

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "ServerLogger enabled");
        Bukkit.getPluginManager().registerEvents(new PlayerActivityListener(), this);
    }
}
