package it.pixel.serverhandbook.service.coords;

import it.pixel.serverhandbook.model.Coordinate;
import it.pixel.serverhandbook.service.BaseService;
import org.bukkit.entity.Player;

import java.io.IOException;
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
     * The constant PARAM_SEARCH.
     */
    public static final String PARAM_SEARCH = "search";

    /**
     * The constant PARAM_SHOW_TO.
     */
    public static final String PARAM_SHOW_TO = "showTo";

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
    protected static List<Coordinate> findAllCoordsByPlayer(Player player) throws IOException {

        return readFile(COORDS_FILE)
                .stream()
                .map(s -> new Coordinate(s.split(";")))
                .filter(c -> c.getPlayerName().equalsIgnoreCase(player.getName()))
                .collect(Collectors.toList());
    }

    /**
     * Find all coords by player and description list.
     *
     * @param player    the player
     * @param searchKey the search key
     * @return the list
     * @throws IOException the io exception
     */
    protected static List<Coordinate> findAllCoordsByPlayerAndDescription(Player player, String searchKey) throws IOException {

        return readFile(COORDS_FILE)
                .stream()
                .map(s -> new Coordinate(s.split(";")))
                .filter(c -> c.getPlayerName().equalsIgnoreCase(player.getName()) && c.getDescription().contains(searchKey))
                .collect(Collectors.toList());
    }

    /**
     * Prepare coordinate string string.
     *
     * @param c the c
     * @return the string
     */
    protected static String prepareCoordinateString(Coordinate c) {
        String coords = textCoordinate(getCoordsAsString(c.getX(), c.getY(), c.getZ()));
        String desc = textDescription(c.getDescription());
        return coords + ": " + desc;
    }
}
