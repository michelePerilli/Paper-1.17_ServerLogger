package it.pixel.serverhandbook.service.book;

import it.pixel.serverhandbook.model.Book;
import it.pixel.serverhandbook.service.BaseService;
import org.bukkit.entity.Player;

import java.io.IOException;

import static it.pixel.serverhandbook.service.FileManager.writeLine;
import static it.pixel.serverhandbook.service.TextManager.textInfo;

/**
 * The type Book command.
 */
public class BookCommand extends BaseService {

    /**
     * Write.
     *
     * @param player the player
     * @param args   the args
     * @throws IOException the io exception
     */
    public static void write(Player player, String[] args) throws IOException {
        if (args.length <= 1)
            return;

        String note = String.join(" ", getParameters(args));

        writeLine("notes.dxl", new Book(player.getName(), note));

        sendMessage(player, textInfo("Nota salvata con successo"));
    }

}
