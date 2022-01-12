package it.pixel.serverhandbook.service.coords;

import it.pixel.serverhandbook.model.Coordinate;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.List;

import static it.pixel.serverhandbook.model.Coordinate.getPlayerPosition;
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
     * @param player    player
     * @param arguments description
     * @throws IOException the io exception
     */
    public static void add(Player player, String[] arguments) throws Exception {
        if (arguments.length <= 1)
            return;

        String description = String.join(" ", getParameters(arguments));

        writeLine(getFileName(player), new Coordinate(getNextId(player), player.getName(), getPlayerDimension(player), getPlayerPosition(player), description, false));

        sendMessage(player, textInfo("Coordinate salvate con successo"));
    }


    /**
     * Read saved coords log
     *
     * @param player player
     * @throws IOException the io exception
     */
    public static void show(Player player) throws Exception {
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
     * @param player    the player
     * @param arguments the arguments
     * @throws IOException the io exception
     */
    public static void search(Player player, String[] arguments) throws Exception {
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
     * @param player    the player
     * @param arguments the arguments
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    public static void showTo(Player player, String[] arguments) throws Exception {
        List<String> parameters = getParameters(arguments);

        Player target = Bukkit.getPlayer(parameters.remove(0));


        String searchKey = String.join(" ", parameters);
        List<String> coordList = findAllCoordsByPlayerAndDescription(player, searchKey)
                .stream()
                .map(CoordsUtils::prepareCoordinateString)
                .toList();

        if (target == null || coordList.isEmpty()) return;

        String message = textName(player.getName()) + textInfo(" ti ha inviato delle coordinate");

        if (!target.getName().equals("all")) {
            sendMessage(target, message, coordList);
            sendMessage(player, textInfo("Hai condiviso con ") + textName(target.getName()) + textInfo(" delle coordinate"), coordList);
        } else {
            Bukkit.getOnlinePlayers().stream()
                    .filter(p -> !p.getName().equals(player.getName()))
                    .forEach(p -> sendMessage(p, message, coordList));

            sendMessage(player, textInfo("Hai condiviso con tutti delle coordinate"), coordList);
        }
    }

    // coming soon
    public static void del(Player player, String[] args) throws Exception {
        String searchKey = String.join(" ", getParameters(args));

        List<Coordinate> coords = findAllCoordsByPlayerAndDescription(player, searchKey);

        if (coords.isEmpty()) {
            sendMessage(player, textInfo("Nessun dato da eliminare."));
        } else {
            sendMessage(player, textInfo("Coming soon..."));
        }


    }

}
