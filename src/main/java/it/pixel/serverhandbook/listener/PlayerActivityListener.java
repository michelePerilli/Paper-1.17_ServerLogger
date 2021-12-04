package it.pixel.serverhandbook.listener;

import it.pixel.serverhandbook.service.activity.ActivityCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.IOException;

/**
 * The type Player activity listener.
 */
public class PlayerActivityListener implements Listener {

    /**
     * On player join.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws IOException {
        Player player = event.getPlayer();
        ActivityCommand.trackActivity(player.getName(), true);
    }

    /**
     * On player quit.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) throws IOException {
        Player player = event.getPlayer();
        ActivityCommand.trackActivity(player.getName(), false);
    }

}
