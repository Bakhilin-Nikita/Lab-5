package lab5.command.addLab;

// для команды add {element}

import lab5.LabWork;
import lab5.command.Command;
import lab5.command.Primitives;
import lab5.features.Coordinates;
import lab5.features.Person;
import lab5.features.enums.Color;
import lab5.features.enums.Difficulty;
import lab5.parser.parserFromJson.ParserFromJson;
import lab5.parser.parserFromJson.Root;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * receiver
 */

public class AddLab implements Command {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private Primitives primitives = new Primitives();
    private LabWork lab;

    @Override
    public void execute() throws IOException {
        if (primitives.enterDigit() == 1) {
            String name = primitives.nameOfLab();
            int mP = primitives.minimalPoint();
            int tW = primitives.tunedInWorks();
            String diff = primitives.difficulty();
            Coordinates coordinates = addCoordinates();
            Person person = addPerson();
            int id = getId();
            LabWork lab = new LabWork( id,name, mP, tW, Difficulty.valueOf(diff), new Coordinates(coordinates.getX(), coordinates.getY()), new Person(person.getName(), person.getEyeColor(), person.getHeight(), person.getBirthday()));
            this.lab = lab;
        }
    }

    public LabWork getLab() {
        return lab;
    }

    public Coordinates addCoordinates() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter x:");
        int x = Integer.parseInt(reader.readLine());
        System.out.println("Enter y:");
        double y = Double.parseDouble(reader.readLine());

        return new Coordinates(x, y);
    }

    public Person addPerson() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter name: ");
        String name = reader.readLine();
        System.out.println("Enter height: ");
        float height = Float.parseFloat(reader.readLine());
        System.out.println("Enter birthday: ");
        String birthday = reader.readLine();
        System.out.println("Enter color of your eyes: ");
        String color = reader.readLine();
        return new Person(name, Color.valueOf(color), height, birthday);
    }

    public int getId() {
        ParserFromJson parser = new ParserFromJson();
        Root root = parser.parse();
        HashSet<LabWork> labs = root.getLabs();
        Iterator<LabWork> iterator = labs.iterator();
        int labId = 0;
        while (iterator.hasNext()) {
            labId = iterator.next().getId();
            if (!iterator.hasNext())
                break;
        }

       return labId+1;
    }
}
