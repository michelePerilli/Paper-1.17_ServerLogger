package it.pixel.serverlogger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractLogger {

    /**
     * Date format
     */
    private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";

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

    /**
     * get current date and time
     * @return current date
     */
    public String getCurrentDate(){
        return new SimpleDateFormat(DATE_FORMAT).format(new Date());
    }

}
