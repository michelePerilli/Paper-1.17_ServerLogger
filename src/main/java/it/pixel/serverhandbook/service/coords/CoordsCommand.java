package it.pixel.serverhandbook.service.coords;

import it.pixel.serverhandbook.model.Coordinate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static it.pixel.serverhandbook.service.FileManager.writeLine;
import static it.pixel.serverhandbook.service.TextManager.customText;
import static it.pixel.serverhandbook.service.TextManager.textInfo;

/**
 * The type Coords service.
 */
public class CoordsCommand extends CoordsUtils {

    /**
     * Add new coords log
     *
     * @param arguments description
     * @param player    player
     * @throws IOException the io exception
     */
    public static void add(String[] arguments, Player player) throws IOException {
        if (arguments.length <= 1)
            return;

        String description = getParameters(arguments).stream().map(String::trim).collect(Collectors.joining(" "));
        Location coords = player.getLocation();

        writeLine(COORDS_FILE, new Coordinate(player.getName(), coords.getBlockX(), coords.getBlockY(), coords.getBlockZ(), description));
    }


    /**
     * Read saved coords log
     *
     * @param player player
     * @throws IOException the io exception
     */
    public static void show(Player player) throws IOException, ClassNotFoundException {
        List<Coordinate> coords = findAllCoordsByPlayer(player);

        coords.forEach(c -> player.sendMessage(prepareCoordinateString(c)));
    }


    /**
     * Search.
     *
     * @param arguments the arguments
     * @param player    the player
     * @throws IOException the io exception
     */
    public static void search(String[] arguments, Player player) throws IOException, ClassNotFoundException {
        String searchKey = getParameters(arguments).stream().map(String::trim).collect(Collectors.joining(" "));
        findAllCoordsByPlayerAndDescription(player, searchKey)
                .forEach(c -> player.sendMessage(prepareCoordinateString(c)));
    }

    /**
     * Show to.
     *
     * @param arguments the arguments
     * @param player    the player
     */
    public static void showTo(String[] arguments, Player player) throws IOException, ClassNotFoundException {
        List<String> parameters = getParameters(arguments);
        Player target = Bukkit.getPlayer(parameters.remove(0));

        if (target == null) return;

        String searchKey = parameters.stream().map(String::trim).collect(Collectors.joining(" "));

        String message = customText(customText(player.getName(), ChatColor.DARK_PURPLE) + textInfo(" ti ha inviato delle coordinate"), ChatColor.ITALIC);

        List<String> coordList = findAllCoordsByPlayerAndDescription(player, searchKey)
                .stream()
                .map(CoordsUtils::prepareCoordinateString)
                .collect(Collectors.toList());

        List<String> messages = new ArrayList<>();
        messages.add(" ");
        messages.add(message);
        messages.addAll(coordList);
        messages.add(customText(" "));
        messages.forEach(target::sendMessage);
    }
}
