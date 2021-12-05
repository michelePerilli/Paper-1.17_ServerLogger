package it.pixel.serverhandbook.service.here;

import it.pixel.serverhandbook.service.BaseService;
import it.pixel.serverhandbook.service.coords.CoordsUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import static it.pixel.serverhandbook.service.TextManager.*;

/**
 * The type Here command.
 */
public class HereCommand extends BaseService {

    /**
     * Show in all chat your current dimension
     *
     * @param player player
     */
    public static void here(Player player) {
        Location data = player.getLocation();

        String nome = textName(player.getName());
        String dimension = textColorByDimension(player.getWorld().getEnvironment(), getDimensionName(player.getWorld().getEnvironment()));
        String coords = textColorByDimension(player.getWorld().getEnvironment(), CoordsUtils.getCoordsAsString(data.getBlockX(), data.getBlockY(), data.getBlockZ()));

        Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage(nome + textInfo(" → ") + dimension + textInfo(" • ") + coords));
    }
}
