package lab5.parserFromJson;

import lab5.LabWork;
import lab5.features.Coordinates;
import lab5.features.Person;
import lab5.features.enums.Color;
import lab5.features.enums.Difficulty;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

public class parserFromJson {
    public Root parse() {
        Root root = new Root();
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader("data.json")) {

            JSONObject rootJsonObject = (JSONObject) parser.parse(reader);
            JSONArray labsJsonArray = (JSONArray) rootJsonObject.get("labs");

            HashSet<LabWork> labWorks = new HashSet<>();

            for (Object lab : labsJsonArray) {
                JSONObject labJsonObject = (JSONObject) lab;

                // at the first parsing primitive type of json file
                long id = (Long) labJsonObject.get("id");
                String name = (String) labJsonObject.get("name");
                long minimalPoint = (Long) labJsonObject.get("minimalPoint");
                long tunedInWorks = (Long) labJsonObject.get("tunedInWorks");

                // ENUM parsing
                String difficulty = (String) labJsonObject.get("difficulty");

                // at the second parsing object type of json file
                //Coordinates type
                JSONObject coordinatesJsonObject = (JSONObject) labJsonObject.get("coordinates");
                long x = (Long) coordinatesJsonObject.get("x");
                double y = (Double) coordinatesJsonObject.get("y");

                // Person type
                JSONObject personJsonObject = (JSONObject) labJsonObject.get("author");
                String nameAuthor = (String) personJsonObject.get("name");
                String color = (String) personJsonObject.get("eyeColor");
                double height = (Double) personJsonObject.get("height");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                String dataBirthday = (String) personJsonObject.get("birthday");
                //convert string to LocalDate
                LocalDate birthday = LocalDate.parse(dataBirthday, formatter);

                LabWork labWork = new LabWork((int) id,
                        name, new Coordinates((int) x, y), (int) minimalPoint,
                        (int) tunedInWorks, new Person(nameAuthor, birthday,
                        Color.valueOf(color), height), Difficulty.valueOf(difficulty));

                labWorks.add(labWork);
            }

            root.setLabs(labWorks);
            return root;
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
