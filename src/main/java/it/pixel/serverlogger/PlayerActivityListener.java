package it.pixel.serverlogger;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayerActivityListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws IOException {
        Player player = event.getPlayer();
        log(player.getName(), true);

    }

    @EventHandler
    public void onPlayerJoin(PlayerQuitEvent event) throws IOException {
        Player player = event.getPlayer();
        log(player.getName(), false);

    }


    private void log(String name, Boolean isJoin) throws IOException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String line = name + " " + formatter.format(date);

        if(isJoin){
            line += " " + ": Logged in";
        } else {
            line += " " + ": Logged out";
        }

        File file = new File("log");
        FileWriter fr = new FileWriter(file, true);
        BufferedWriter br = new BufferedWriter(fr);
        PrintWriter writer = new PrintWriter(br);
        writer.println(line);
        writer.close();
    }

}
