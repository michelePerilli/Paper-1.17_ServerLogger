package it.pixel.serverhandbook.service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    /**
     * The type Append object output stream.
     */
    protected static class AppendObjectOutputStream extends ObjectOutputStream {

        /**
         * Instantiates a new Append object output stream.
         *
         * @param file the file
         * @throws IOException the io exception
         */
        public AppendObjectOutputStream(OutputStream file) throws IOException {
            super(file);
        }


        @Override
        protected void writeStreamHeader() throws IOException {
            // do not write a header, but reset:
            // this line added after another question
            // showed a problem with the original
            reset();
        }
    }

    /**
     * Gets file reader.
     *
     * @param file the file
     * @return the file reader
     * @throws FileNotFoundException the file not found exception
     */
    protected static ObjectInputStream getFileReader(String file) throws IOException {
        return new ObjectInputStream(new FileInputStream(file));
    }

    /**
     * Gets file writer.
     *
     * @param file the file
     * @return the file writer
     * @throws IOException the io exception
     */
    public static AppendObjectOutputStream getFileWriter(String file) throws IOException {
        return new AppendObjectOutputStream(new FileOutputStream(file, true));
    }

    /**
     * Save to log file the line
     *
     * @param file the path
     * @param obj  the line to log to file
     * @throws IOException the io exception
     */
    public static void writeLine(String file, Object obj) throws IOException {
        ObjectOutputStream writer = getFileWriter(file);
        writer.writeObject(obj);
        writer.close();
    }

    /**
     * Read file list.
     *
     * @param filename the filename
     * @return the list
     * @throws IOException the io exception
     */
    public static List<Object> readFile(String filename) throws ClassNotFoundException, IOException {
        ObjectInputStream reader = getFileReader(filename);
        List<Object> objects = new ArrayList<>();
        boolean go = true;
        while (go) {
            try {
                objects.add(reader.readObject());
            } catch (EOFException e) {
                go = false;
            }
        }

        return objects;
    }

}
