package server7;


import server7.manager.Controller;
import server7.loggers.Logger;
import server7.loggers.StandardLogger;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * server.App запускающий класс, содержащий метод {@link #main(String[])}
 */

public class App {

    /**
     * Главный  метод, который запускает {@link Controller#start()}
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException, ParseException, ClassNotFoundException, NoSuchAlgorithmException, SQLException {

        String file;
        Logger logger = new StandardLogger();
        logger.write("Логгер запущен");

        try {
            file = args[0];
        } catch (IndexOutOfBoundsException e) {
            file = "notes.json";
        }

        Controller controller = new Controller(file);
        controller.start();
    }
}
