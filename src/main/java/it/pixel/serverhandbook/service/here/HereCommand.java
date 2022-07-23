package it.pixel.serverhandbook.service.here;

import it.pixel.serverhandbook.service.BaseService;
import it.pixel.serverhandbook.service.coords.CoordsUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static it.pixel.serverhandbook.service.TextManager.*;

/**
 * The type Here command.
 */
public interface HereCommand {

    /**
     * Show in all chat your current dimension
     *
     * @param player    player
     * @param arguments the arguments
     */
    static void here(Player player, String[] arguments) {

        String nome = textName(player.getName());
        String dimension = textColorByDimension(player.getWorld().getEnvironment(), BaseService.getDimensionName(player.getWorld().getEnvironment()));
        String coords;

        if (arguments.length > 0)
            coords = textColorByDimension(player.getWorld().getEnvironment(), String.join(" ", arguments));
        else {
            coords = textColorByDimension(player.getWorld().getEnvironment(), CoordsUtils.getCoordsAsString(player.getLocation()));
        }


        Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage(nome + textInfo(" → ") + dimension + textInfo(" • ") + coords));
    }
}
