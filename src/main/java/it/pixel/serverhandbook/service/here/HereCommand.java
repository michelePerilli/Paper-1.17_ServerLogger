package it.pixel.serverhandbook.service.here;

import it.pixel.serverhandbook.service.BaseService;
import it.pixel.serverhandbook.service.coords.CoordsUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * The type Here command.
 */
public class HereCommand extends BaseService {
    /**
     * Show in all chat your current position
     *
     * @param player player
     */
    public static void here(Player player) {
        Location data = player.getLocation();

        String nome = customText(player.getName(), ChatColor.DARK_PURPLE);
        String dimension = customText(getDimensionName(player.getWorld().getEnvironment()), ChatColor.GOLD);
        String coords = customText(CoordsUtils.getCoordsAsString(data.getBlockX(), data.getBlockY(), data.getBlockZ()), ChatColor.DARK_AQUA, ChatColor.BOLD);

        Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage(nome + greyText(" è qui: ") + dimension + "  " + coords));
    }
}
