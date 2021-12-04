package it.pixel.serverhandbook.service.coords;

import it.pixel.serverhandbook.model.Coordinate;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

        List<String> descriptionParts = new ArrayList<>(Arrays.asList(arguments));
        descriptionParts.remove(0); // removing "add" from arguments

        String description = descriptionParts.stream().map(String::trim).collect(Collectors.joining(" "));
        Location coords = player.getLocation();

        String line = String.format("%s;%d;%d;%d;%s", player.getName(), coords.getBlockX(), coords.getBlockY(), coords.getBlockZ(), description);
        writeLine(COORDS_FILE, line);
    }


    /**
     * Read saved coords log
     *
     * @param player player
     * @throws IOException the io exception
     */
    public static void show(Player player) throws IOException {
        List<Coordinate> coords = getAllCoordsByPlayer(player);

        coords.forEach(c -> player.sendMessage(prepareCoordinateString(c)));
    }


}
