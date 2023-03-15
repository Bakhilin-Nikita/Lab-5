package parser.parserToJson;

import com.google.gson.Gson;
import exceptions.InvalidFieldY;
import exceptions.NullX;
import object.Coordinates;
import object.LabWork;
import object.Person;
import object.enums.Color;
import object.enums.Difficulty;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

public class ParserToJson {

    public LabWork addToList(int id, String name, int mP, int tIW, String difficulty, int x, double y, String nameAuthor, String color, float height, String bd) throws NullX, InvalidFieldY {
        LabWork lab = new LabWork(name, mP, tIW, Difficulty.valueOf(difficulty), new Coordinates(x, y), new Person(nameAuthor, Color.valueOf(color), height, bd));
        return lab;
    }

    public LabWork createLabWork(String name,
                                 int mP,
                                 int tIW,
                                 String difficulty,
                                 int x,
                                 double y,
                                 String nameAuthor,
                                 String color,
                                 float height,
                                 String bd)
            throws NullX, InvalidFieldY {
        LabWork lab = new LabWork(name, mP, tIW, Difficulty.valueOf(difficulty), new Coordinates(x, y), new Person(nameAuthor, Color.valueOf(color), height, bd));
        return lab;
    }

    public boolean serialization(HashSet<LabWork> labs) {
        Gson gson = new Gson();

        String result = gson.toJson(labs);


        try (FileOutputStream out = new FileOutputStream("notes.json");
             BufferedOutputStream bos = new BufferedOutputStream(out)) {
            // перевод строки в байты
            byte[] buffer = result.getBytes();
            bos.write(buffer, 0, buffer.length);

            return true;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return false;
    }
}
