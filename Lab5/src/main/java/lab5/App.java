package lab5;

import com.google.gson.Gson;
import lab5.exceptions.InvalidFieldY;
import lab5.exceptions.NullX;
import lab5.parserFromJson.ParserFromJson;
import lab5.parserFromJson.Root;
import lab5.parserToJson.ParserToJson;

import java.util.HashSet;

/**
 * Kernel Class of this program by ...
 */

public class App {

    public static void main(String[] args) throws NullX, InvalidFieldY {

        HashSet<LabWork> labs = new HashSet<>();

        //to Json
        ParserToJson inJson = new ParserToJson();
        LabWork lab1 = inJson.addToList(12, "Rim", 123, 12, "EASY", 11, 12.3, "Nikita", "GREEN", 224, "23-05-2004");
        LabWork lab2 = inJson.addToList(1122, "Rim21", 123, 12, "EASY", 11, 12.3, "Nikita", "GREEN", 224, "23-05-2004");
        LabWork lab3 = inJson.addToList(11222, "Ri342m21", 123423, 4312, "EASY", 11, 12.3, "Nikita", "GREEN", 224, "23-05-2004");
        labs.add(lab1);
        labs.add(lab2);
        labs.add(lab3);

        inJson.serialization(labs);

        Gson gson = new Gson();
        System.out.println(gson.toJson(labs));
        ParserFromJson parser = new ParserFromJson();
        Root root = parser.parse();
        parser.parse();

        HashSet<LabWork> labWorks = root.getLabs();

        for (LabWork lab : labWorks) {
            System.out.println("id:" + lab.getId());
            System.out.println("name:" + lab.getName());
            System.out.println("coordinates: [x=" + lab.getCoordinates().getX() +
                    ", y=" + lab.getCoordinates().getY() + "]");
            System.out.println("minimalPoint:" + lab.getMinimalPoint());
            System.out.println("tunedInWorks:" + lab.getTunedInWorks());
            System.out.println("difficulty:" + lab.getDifficulty());
            System.out.println("author: [\nname=" + lab.getAuthor().getName() +
                    ", \nbirthday=" + lab.getAuthor().getBirthday() +
                    "\nheight=" + lab.getAuthor().getHeight() +
                    "\neyeColor=" + lab.getAuthor().getEyeColor() + "\n]");
            System.out.println("---------------------");
        }

    }
}
