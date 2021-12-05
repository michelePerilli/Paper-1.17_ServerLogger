package it.pixel.serverhandbook.service.coords;

import it.pixel.serverhandbook.model.Coordinate;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.List;

import static it.pixel.serverhandbook.model.Coordinate.createVectorFromLocation;
import static it.pixel.serverhandbook.service.FileManager.writeLine;
import static it.pixel.serverhandbook.service.TextManager.textInfo;
import static it.pixel.serverhandbook.service.TextManager.textName;

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

        String description = String.join(" ", getParameters(arguments));

        writeLine(COORDS_FILE, new Coordinate(false, player.getName(), getDimensionFormEnvironment(player.getWorld().getEnvironment()), createVectorFromLocation(player.getLocation()), description));

        sendMessage(player, textInfo("Coordinate salvate con successo"));
    }


    /**
     * Read saved coords log
     *
     * @param player player
     * @throws IOException the io exception
     */
    public static void show(Player player) throws IOException, ClassNotFoundException {
        List<Coordinate> coords = findAllCoordsByPlayer(player);

        if (coords.isEmpty()) {
            sendMessage(player, textInfo("Non hai ancora coordinate salvate. Usa /coords add <descrizione> per aggiungerne una."));
            return;
        }

        String message = textInfo("Le tue coordinate");
        sendMessage(player, message, coords.stream().map(CoordsUtils::prepareCoordinateString).toList());
    }


    /**
     * Search.
     *
     * @param arguments the arguments
     * @param player    the player
     * @throws IOException the io exception
     */
    public static void search(String[] arguments, Player player) throws IOException, ClassNotFoundException {
        String searchKey = String.join(" ", getParameters(arguments));

        List<Coordinate> coords = findAllCoordsByPlayerAndDescription(player, searchKey);

        if (coords.isEmpty()) {
            sendMessage(player, textInfo("Nessun risultato ottenuto."));
            return;
        }

        sendMessage(player, textInfo("Le tue coordinate che contengono '" + searchKey + "'"), coords.stream().map(CoordsUtils::prepareCoordinateString).toList());
    }


    /**
     * Show to.
     *
     * @param arguments the arguments
     * @param player    the player
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    public static void showTo(String[] arguments, Player player) throws IOException, ClassNotFoundException {
        List<String> parameters = getParameters(arguments);
        Player target = Bukkit.getPlayer(parameters.remove(0));


        String searchKey = String.join(" ", parameters);
        List<String> coordList = findAllCoordsByPlayerAndDescription(player, searchKey)
                .stream()
                .map(CoordsUtils::prepareCoordinateString)
                .toList();

        if (target == null || coordList.isEmpty()) return;

        String message = textName(player.getName()) + textInfo(" ti ha inviato delle coordinate");

        sendMessage(player, textInfo("Hai condiviso con ") + textName(target.getName()) + textInfo(" delle coordinate"), coordList);
        sendMessage(target, message, coordList);
    }
}
