package it.pixel.serverlogger.service;

import java.io.*;

public abstract class BaseService {

    /**
     * Save to log file the line
     * @param file the path
     * @param line the line to log to file
     */
    static void log(String file, String line) {
        try {
            File f = new File(file);
            FileWriter fr = new FileWriter(f, true);
            BufferedWriter br = new BufferedWriter(fr);
            PrintWriter writer = new PrintWriter(br);
            writer.println(line);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
