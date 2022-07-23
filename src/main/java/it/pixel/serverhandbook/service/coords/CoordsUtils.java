package it.pixel.serverhandbook.service.coords;

import it.pixel.serverhandbook.Vector3i;
import it.pixel.serverhandbook.model.Coordinate;
import it.pixel.serverhandbook.service.BaseService;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.List;

import static it.pixel.serverhandbook.service.BaseService.getDimensionName;
import static it.pixel.serverhandbook.service.BaseService.getEnvironmentFormDimension;
import static it.pixel.serverhandbook.service.FileManager.readFile;
import static it.pixel.serverhandbook.service.TextManager.*;

/**
 * The type Coords utils.
 */
public interface CoordsUtils {

    /**
     * The constant PARAM_ADD.
     */
    String PARAM_ADD = "add";

    /**
     * The constant PARAM_SHOW.
     */
    String PARAM_SHOW = "show";

    /**
     * The constant PARAM_SEARCH.
     */
    String PARAM_SEARCH = "find";

    /**
     * The constant PARAM_SHOW_TO.
     */
    String PARAM_SHARE = "share";

    /**
     * The constant PARAM_DEL.
     */
    String PARAM_DEL = "del";


    /**
     * Gets coords as string.
     *
     * @param xyz the xyz
     * @return the coords as string
     */
    static String getCoordsAsString(Vector3i xyz) {
        return String.format("%s %s %s", xyz.x(), xyz.y(), xyz.z());
    }

    /**
     * Gets coords as string.
     *
     * @param xyz the xyz
     * @return the coords as string
     */
    static String getCoordsAsString(Location xyz) {
        return String.format("%s %s %s", xyz.getBlockX(), xyz.getBlockY(), xyz.getBlockZ());
    }

    /**
     * Gets all coords by player.
     *
     * @param player the player
     * @return the all coords by player
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    static List<Coordinate> findAllCoordsByPlayer(Player player) throws Exception {

        return readFile(BaseService.getFileName(player))
                .stream()
                .map(Coordinate.class::cast)
                .filter(c -> !c.deleted())
                .toList();
    }


    /**
     * Gets next id.
     *
     * @param player the player
     * @return the next id
     * @throws Exception the exception
     */
    static long getNextId(Player player) throws Exception {
        return readFile(BaseService.getFileName(player))
                .stream()
                .map(Coordinate.class::cast)
                .filter(c -> !c.deleted())
                .toList().size();
    }


    /**
     * Find all coords by player and description list.
     *
     * @param player    the player
     * @param searchKey the search key
     * @return the list
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    static List<Coordinate> findAllCoordsByPlayerAndDescription(Player player, String searchKey) throws Exception {

        return readFile(BaseService.getFileName(player))
                .stream()
                .map(Coordinate.class::cast)
                .filter(c -> !c.deleted() && c.description().contains(searchKey))
                .toList();
    }

    static List<Coordinate> findAllCoordsByPlayerAndId(Player player, String id) throws Exception {

        return readFile(BaseService.getFileName(player))
                .stream()
                .map(Coordinate.class::cast)
                .filter(c -> !c.deleted() && Long.parseLong(id) == c.id())
                .toList();
    }


    /**
     * Prepare coordinate string string.
     *
     * @param c the c
     * @return the string
     */
    static String prepareCoordinateString(Coordinate c) {
        String dimension = textColorByDimension(getEnvironmentFormDimension(c.dimension()), getDimensionName(getEnvironmentFormDimension(c.dimension())));
        String coords = textColorByDimension(getEnvironmentFormDimension(c.dimension()), getCoordsAsString(c.xyz()));
        String desc = textDescription(c.description());
        return dimension + textInfo(" • ") + coords + textInfo(" → ") + desc;
    }
}
