package it.pixel.serverhandbook.service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public interface FileManager {

    /**
     * The type Append object output stream.
     */
    class AppendObjectOutputStream extends ObjectOutputStream {

        /**
         * Instantiates a new Append object output stream.
         *
         * @param file the file
         * @throws IOException the io exception
         */
        public AppendObjectOutputStream(OutputStream file) throws IOException {
            super(file);
        }


        /**
         * Write stream header.
         *
         * @throws IOException the io exception
         */
        @Override
        protected void writeStreamHeader() throws IOException {
            // do not write a header
            reset();
        }
    }

    /**
     * Is first time boolean.
     *
     * @param file the file
     * @return the boolean
     */
    private static boolean isFirstTime(String file) {
        return new File(file).length() == 0;
    }

    /**
     * Gets file reader.
     *
     * @param file the file
     * @return the file reader
     * @throws IOException the io exception
     */
    private static ObjectInputStream getFileReader(String file) throws IOException {
        return new ObjectInputStream(new FileInputStream(file));
    }


    /**
     * Gets file writer.
     *
     * @param file the file
     * @return the file writer
     * @throws IOException the io exception
     */
    private static ObjectOutputStream getFileWriter(String file) throws IOException {
        if (isFirstTime(file)) {
            return new ObjectOutputStream(new FileOutputStream(file, true));
        } else {
            return new AppendObjectOutputStream(new FileOutputStream(file, true));
        }
    }

    /**
     * Save to log file the line
     *
     * @param file the path
     * @param obj  the line to log to file
     * @throws IOException the io exception
     */
    static void writeLine(String file, Object obj) throws IOException {
        ObjectOutputStream writer = getFileWriter(file);
        writer.writeObject(obj);
        writer.close();
    }

    /**
     * Read file list.
     *
     * @param filename the filename
     * @return the list
     * @throws ClassNotFoundException the class not found exception
     * @throws IOException            the io exception
     */
    static List<Object> readFile(String filename) throws Exception {
        if (!isFirstTime(filename)) {
            ObjectInputStream reader = getFileReader(filename);
            List<Object> objects = new ArrayList<>();
            boolean go = true;
            while (go) {
                try {
                    objects.add(reader.readObject());
                } catch (Exception e) {
                    go = false;
                }
            }
            reader.close();
            return objects;
        } else {
            return new ArrayList<>();
        }
    }

}
