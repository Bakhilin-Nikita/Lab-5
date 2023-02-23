package lab5;

import lab5.command.addLab.AddLab;
import lab5.parser.parserFromJson.ParserFromJson;
import lab5.parser.parserFromJson.Root;
import lab5.parser.parserToJson.ParserToJson;

import java.io.IOException;
import java.util.HashSet;

/**
 * Kernel Class of this program by ...
 */

public class App {

    public static void main(String[] args) throws IOException {

        HashSet<LabWork> labWorks = new HashSet<>();
        ParserToJson inJson = new ParserToJson();
        ParserFromJson parser = new ParserFromJson();
        if (parser.checkOnEmpty()) {
            Root root = parser.parse();
            labWorks = root.getLabs();
            inJson.serialization(labWorks);
        }

        AddLab newLab = new AddLab();
        newLab.execute();
        labWorks.add(newLab.getLab());

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


        //
        inJson.serialization(labWorks);
    }
}
