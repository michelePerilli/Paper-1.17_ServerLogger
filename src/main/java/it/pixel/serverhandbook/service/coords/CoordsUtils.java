package it.pixel.serverhandbook.service.coords;

import it.pixel.serverhandbook.Vector3i;
import it.pixel.serverhandbook.model.Coordinate;
import it.pixel.serverhandbook.service.BaseService;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static it.pixel.serverhandbook.service.BaseService.getDimensionName;
import static it.pixel.serverhandbook.service.BaseService.getEnvironmentFormDimension;
import static it.pixel.serverhandbook.service.FileManager.readFile;
import static it.pixel.serverhandbook.service.FileManager.writeFile;
import static it.pixel.serverhandbook.service.TextManager.*;

/**
 * The type Coords utils.
 */
public interface CoordsUtils {


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
     * @throws Exception the exception
     */
    static List<Coordinate> findAllUndeletedCoordsByPlayer(Player player) throws Exception {

        return readFile(BaseService.getFileName(player))
                .stream()
                .map(Coordinate.class::cast)
                .filter(c -> !c.deleted())
                .toList();
    }

    /**
     * Find all coords by player list.
     *
     * @param player the player
     * @return the list
     * @throws Exception the exception
     */
    static List<Coordinate> findAllCoordsByPlayer(Player player) throws Exception {

        return readFile(BaseService.getFileName(player))
                .stream()
                .map(Coordinate.class::cast)
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
                .toList().size();
    }


    /**
     * Find all coords by player and description list.
     *
     * @param player    the player
     * @param searchKey the search key
     * @return the list
     * @throws Exception the exception
     */
    static List<Coordinate> findAllCoordsByPlayerAndDescription(Player player, String searchKey) throws Exception {

        return readFile(BaseService.getFileName(player))
                .stream()
                .map(Coordinate.class::cast)
                .filter(c -> !c.deleted())
                .filter(c -> c.description().toLowerCase().contains(searchKey.toLowerCase()))
                .toList();
    }

    /**
     * Find all coords by player and id list.
     *
     * @param player the player
     * @param id     the id
     * @return the list
     * @throws Exception the exception
     */
    static List<Coordinate> findAllCoordsByPlayerAndId(Player player, Long id) throws Exception {

        return readFile(BaseService.getFileName(player))
                .stream()
                .map(Coordinate.class::cast)
                .filter(c -> !c.deleted())
                .filter(c -> id.equals(c.id()))
                .toList();
    }


    /**
     * Delete coords by id and player.
     *
     * @param player the player
     * @param id     the id
     * @throws Exception the exception
     */
    static void deleteCoordsByIdAndPlayer(Player player, Long id) throws Exception {
        List<Coordinate> allCoordsByPlayer = new ArrayList<>(findAllCoordsByPlayer(player));
        if (!new File(BaseService.getFileName(player)).delete()) {
            Logger.getAnonymousLogger().warning("Errore eliminazione file utente");

        }

        for (int i = 0; i < allCoordsByPlayer.size(); i++) {
            if (id.equals(allCoordsByPlayer.get(i).id()) && !allCoordsByPlayer.get(i).deleted()) {
                allCoordsByPlayer.set(i, new Coordinate(
                        allCoordsByPlayer.get(i).id(), allCoordsByPlayer.get(i)
                        .playerName(), allCoordsByPlayer.get(i).dimension(), allCoordsByPlayer.get(i).xyz(),
                        allCoordsByPlayer.get(i).description(), true));
            }
        }


        writeFile(BaseService.getFileName(player), allCoordsByPlayer);
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
        return textWhite("[" + c.id() + "] ") + dimension + textInfo(" • ") + coords + textInfo(" → ") + desc;
    }
}
