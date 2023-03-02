package org.example.lab5.parserToJson;

import org.example.lab5.LabWork;
import org.example.lab5.entity.Coordinates;
import org.example.lab5.entity.Person;
import org.example.lab5.entity.enums.Color;
import org.example.lab5.entity.enums.Difficulty;
import org.example.lab5.exceptions.InvalidFieldY;
import org.example.lab5.exceptions.NullX;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class ParserToJson {

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

    // fileName = "notes.json"
    /*public void serialization(Set<LabWork> labs, String fileName) {
        Gson gson = new Gson();
        String result = gson.toJson(labs);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(result);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

//        try (FileOutputStream out = new FileOutputStream(fileName, true);
//
//             BufferedOutputStream bos = new BufferedOutputStream(out)) {
//            // перевод строки в байты
//            byte[] buffer = result.getBytes();
//            bos.write(buffer, 0, buffer.length);
//
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
    }*/
}
