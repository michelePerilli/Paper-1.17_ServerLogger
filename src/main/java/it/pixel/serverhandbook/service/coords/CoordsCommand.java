package it.pixel.serverhandbook.service.coords;

import it.pixel.serverhandbook.model.Coordinate;
import it.pixel.serverhandbook.service.BaseService;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static it.pixel.serverhandbook.model.Coordinate.getPlayerPosition;
import static it.pixel.serverhandbook.service.BaseService.*;
import static it.pixel.serverhandbook.service.FileManager.writeLine;
import static it.pixel.serverhandbook.service.TextManager.*;
import static it.pixel.serverhandbook.service.coords.CoordsUtils.*;

/**
 * The type Coords service.
 */
public interface CoordsCommand {

    /**
     * Add new coords log
     *
     * @param player    player
     * @param arguments description
     * @throws IOException the io exception
     */
    static void add(Player player, String[] arguments) throws Exception {
        if (arguments.length <= 1)
            return;

        String description = String.join(" ", getParameters(arguments));

        Coordinate coord = new Coordinate(
                getNextId(player),
                player.getName(),
                getPlayerDimension(player),
                getPlayerPosition(player),
                description,
                false);
        writeLine(BaseService.getFileName(player), coord);

        sendMessage(player, Arrays.asList(textInfo("Coordinate salvate con successo"), "", prepareCoordinateString(coord)));

    }


    /**
     * Read saved coords log
     *
     * @param player player
     * @throws IOException the io exception
     */
    static void show(Player player) throws Exception {

        List<Coordinate> coords = CoordsUtils.findAllUndeletedCoordsByPlayer(player);

        if (coords.isEmpty()) {
            sendMessage(player, List.of(
                    textInfo("Non hai ancora coordinate salvate."),
                    textInfo("Usa ") + textWhite("/coords add <descrizione>") + textInfo(" per aggiungerne una")));
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
    static void search(Player player, String[] arguments) throws Exception {
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
    static void share(Player player, String[] arguments) throws Exception {

        List<String> parameters = getParameters(arguments);

        String targetName = parameters.remove(0);

        String searchKey = String.join(" ", parameters);
        List<String> coordList = findAllCoordsByPlayerAndDescription(player, searchKey)
                .stream()
                .map(CoordsUtils::prepareCoordinateString)
                .toList();


        String message = textName(player.getName()) + textInfo(" ti ha inviato delle coordinate");

        if (!targetName.equals("all")) {
            Player target = Bukkit.getPlayer(targetName);
            if (target == null || coordList.isEmpty()) return;
            sendMessage(target, message, coordList);
            sendMessage(player, textInfo("Hai condiviso con ") + textName(target.getName()) + textInfo(" delle coordinate"), coordList);
        } else {
            Bukkit.getOnlinePlayers().stream()
                    .filter(p -> !p.getName().equals(player.getName()))
                    .forEach(p -> sendMessage(p, message, coordList));

            sendMessage(player, textInfo("Hai condiviso con tutti delle coordinate"), coordList);
        }
    }


    static void del(Player player, String[] args) throws Exception {
        if (args.length > 2) {
            player.sendMessage(textInfo("Che stai a fa?"));
            return;
        }
        String searchKey = String.join(" ", getParameters(args));

        List<Coordinate> coords = findAllCoordsByPlayerAndId(player, Long.valueOf(searchKey));
        if (coords.isEmpty()) {
            sendMessage(player, textInfo("Nessun dato da eliminare."));
        } else {
            deleteCoordsByIdAndPlayer(player, Long.valueOf(searchKey));
            sendMessage(player, List.of(
                    textInfo("Coordinata eliminata.")/*,
                    coming soon..
                    textInfo("Usa ") + textWhite("/coords restore " + searchKey) + textInfo(" per annullare."),
                    textInfo("Usa ") + textWhite("/coords clear") + textInfo(" per eliminare definitivamente.")*/));
        }


    }

}
