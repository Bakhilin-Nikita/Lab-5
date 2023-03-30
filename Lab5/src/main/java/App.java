import manager.Controller;

import java.io.IOException;
import java.text.ParseException;

/**
 * App запускающий класс, содержащий метод {@link #main(String[])}
 */

public class App {

    /**
     * Главный  метод, который запускает {@link Controller#start()}
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException, ParseException {
        Controller controller = new Controller(args[0]);
        controller.start();
    }
}
