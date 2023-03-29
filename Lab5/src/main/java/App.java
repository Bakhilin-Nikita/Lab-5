import manager.Controller;

import java.io.IOException;

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
    public static void main(String[] args) throws IOException {
        Controller controller = new Controller();
        controller.start();
    }
}
