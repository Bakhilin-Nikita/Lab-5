package lab5;

import lab5.command.show.Show;

import java.io.IOException;

/**
 * Kernel Class of this program by ...
 */

public class App {

    public static void main(String[] args) throws IOException {
        Controller controller = new Controller();
        controller.start();
    }
}
