package lab5;

import lab5.parserFromJson.Root;
import lab5.parserFromJson.parserFromJson;

import java.util.HashSet;

/**
 * Kernel Class of this program by ...
 */

public class App {

    public static void main(String[] args) {

        parserFromJson parser = new parserFromJson();
        Root root = parser.parse();

        // there we are set our data from json to this collection
        HashSet<LabWork> labs = root.getLabs();

        for (LabWork lab : labs) {
            System.out.println("id:" + lab.getId());
            System.out.println("name:" + lab.getName());
            System.out.println("coordinates: [ x=" + lab.getCoordinates().getX() +
                    ", y=" + lab.getCoordinates().getY() + "]");
            System.out.println("minimalPoint:" + lab.getMinimalPoint());
            System.out.println("tunedInWorks:" + lab.getTunedInWorks());
            System.out.println("difficulty:" + lab.getDifficulty());
            System.out.println("author: [name=" + lab.getAuthor().getName() +
                    ", \nbirthday=" + lab.getAuthor().getBirthday() +
                    "\nheight=" + lab.getAuthor().getHeight() +
                    "\neyeColor=" + lab.getAuthor().getEyeColor());
        }
    }
}
