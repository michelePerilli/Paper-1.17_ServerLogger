package it.pixel.serverhandbook.service.coords;

import it.pixel.serverhandbook.model.Coordinate;
import it.pixel.serverhandbook.service.BaseService;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Coords utils.
 */
public abstract class CoordsUtils extends BaseService {

    /**
     * The constant COORDS_FILE.
     */
    protected static final String COORDS_FILE = "coords.log";

    /**
     * The constant PARAM_ADD.
     */
    public static final String PARAM_ADD = "add";
    /**
     * The constant PARAM_SHOW.
     */
    public static final String PARAM_SHOW = "show";


    /**
     * Gets coords as string.
     *
     * @param x the x
     * @param y the y
     * @param z the z
     * @return the coords as string
     */
    public static String getCoordsAsString(String x, String y, String z) {
        return String.format("%s %s %s", x, y, z);
    }

    /**
     * Gets coords as string.
     *
     * @param x the x
     * @param y the y
     * @param z the z
     * @return the coords as string
     */
    public static String getCoordsAsString(int x, int y, int z) {
        return String.format("%s %s %s", x, y, z);
    }

    /**
     * Gets all coords by player.
     *
     * @param player the player
     * @return the all coords by player
     * @throws IOException the io exception
     */
    protected static List<Coordinate> getAllCoordsByPlayer(Player player) throws IOException {
        BufferedReader br = getFileReader(COORDS_FILE);
        List<String> rows = new ArrayList<>();

        String row;
        while ((row = br.readLine()) != null) rows.add(row);

        return rows
                .stream()
                .map(s -> new Coordinate(s.split(";")))
                .filter(c -> c.getPlayerName().equalsIgnoreCase(player.getName()))
                .collect(Collectors.toList());
    }

    /**
     * Prepare coordinate string string.
     *
     * @param c the c
     * @return the string
     */
    protected static String prepareCoordinateString(Coordinate c) {
        String coords = customText(getCoordsAsString(c.getX(), c.getY(), c.getZ()), ChatColor.DARK_AQUA, ChatColor.BOLD);
        String desc = customText(c.getDescription(), ChatColor.GREEN);
        return coords + ": " + desc;
    }
}
