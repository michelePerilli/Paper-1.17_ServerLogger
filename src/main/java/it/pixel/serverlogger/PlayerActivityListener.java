package it.pixel.serverlogger;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerActivityListener extends AbstractLogger implements Listener {



    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        saveLog(player.getName(), true);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        saveLog(player.getName(), false);
    }

    private void saveLog(String name, Boolean isJoin) {
        String line = name + " " + getCurrentDate();

        if (isJoin) {
            line += " : Logged in";
        } else {
            line += " : Logged out";
        }

        log("server.log", line);
    }


}
