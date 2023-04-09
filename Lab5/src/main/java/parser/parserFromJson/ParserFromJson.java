package parser.parserFromJson;

import object.Coordinates;
import object.LabWork;
import object.Person;
import object.enums.Color;
import object.enums.Difficulty;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import parser.Root;

import java.io.*;
import java.util.HashSet;
import java.util.Map;

/**
 * Метод парсит данные из json файла в коллекцию {@link Root#labWorkSet}
 * Ключевой метод для работы с коллекцией.
 */

public class ParserFromJson {



    /**
     * Метод обращается к файлу notes.json, использует его в качестве базы данных объектов.
     * @return
     * @throws IOException
     */
    public Root parse(String fileName) throws IOException {
        Root root = new Root();
        JSONParser parser = new JSONParser();
        File file = new File(fileName);
        if (file.exists())
            try (InputStreamReader reader = new InputStreamReader(new FileInputStream(file))) {

                JSONArray labsJsonArray = (JSONArray) parser.parse(reader);

                HashSet<LabWork> labWorks = new HashSet<>();
                for (Object lab : labsJsonArray) {
                    JSONObject labJsonObject = (JSONObject) lab;

                    // at the first parsing primitive type of json file
                    long id = (Long) labJsonObject.get("id");
                    long minimalPoint = (Long) labJsonObject.get("minimalPoint");
                    long tunedInWorks = (Long) labJsonObject.get("tunedInWorks");
                    String name = (String) labJsonObject.get("name");
                    String creationDate = (String) labJsonObject.get("creationDateString");
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
                    String dataBirthday = (String) personJsonObject.get("birthday");

                    LabWork labWork = new LabWork((int) id, name, (int) minimalPoint, (int) tunedInWorks, Difficulty.valueOf(difficulty), new Coordinates((int) x, y), new Person(nameAuthor, Color.valueOf(color), height, dataBirthday), creationDate);

                    labWorks.add(labWork);
                }

                root.setLabWorkSet(labWorks);

                root.setValid(true);

                return root;
            } catch (ParseException | NullPointerException e) {
                System.out.println("Невалидный файл json!");
                root.setValid(false);
            } catch(IllegalArgumentException | ClassCastException e) {
                System.out.println("Проблема с парсингом из файла -->");
                System.out.println(e.getMessage());
                root.setValid(false);
            }
        return root;
    }


    /**
     * Метод проверяет есть ли в файле обьекты коллекции.
     * @return boolean
     */
    public boolean checkOnEmpty(String fileName) {

        try {
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));

            try {
                if (br.readLine() == null)
                    return false;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
