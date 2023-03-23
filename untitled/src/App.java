import java.util.Scanner;

public class App {
    public static void main(String args[]) {
        String cmd = "add_if_max 5";
        String[] arr = cmd.split(" ", 2);
        cmd = cmd.replaceAll("_", " ");
        cmd = WordUtils.capitalize(cmd).trim();
        cmd = cmd.replaceAll(" ", "");
    }
}
