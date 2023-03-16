package object;

import object.Coordinates;
import object.Person;
import object.enums.Difficulty;

import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * The object.LabWork is main object for console App
 */

public class LabWork {

    private  int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates = new Coordinates(); //Поле не может быть null
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int minimalPoint; //Значение поля должно быть больше 0
    private int tunedInWorks;
    private Difficulty difficulty; //Поле может быть null
    private Person author; //Поле не может быть null



    public LabWork(int id, String name, int minimalPoint, int tunedInWorks, Difficulty difficulty, Coordinates coordinates, Person author) {
        this.id = id;
        this.name = Objects.requireNonNull(name);
        this.coordinates = Objects.requireNonNull(coordinates);
        this.minimalPoint = minimalPoint;
        this.author = Objects.requireNonNull(author);
        this.tunedInWorks = tunedInWorks; // I do not understand what is it
        this.difficulty = Objects.requireNonNull(difficulty);
        this.creationDate = ZonedDateTime.now();
    }

    public LabWork(String name, int minimalPoint, int tunedInWorks, Difficulty difficulty, Coordinates coordinates, Person author) {
        //this.id = createID();
        this.name = Objects.requireNonNull(name);
        this.coordinates = Objects.requireNonNull(coordinates);
        this.minimalPoint = minimalPoint;
        this.author = Objects.requireNonNull(author);
        this.tunedInWorks = tunedInWorks; // I do not understand what is it
        this.difficulty = Objects.requireNonNull(difficulty);
        this.creationDate = ZonedDateTime.now();
    }

    public ZonedDateTime getCreationDate() {
        return this.creationDate;
       // return null;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setDifficulty(Difficulty diff) {
        this.difficulty = diff;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public long getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public Person getAuthor() {
        return this.author;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public void setMinimalPoint(int minimalPoint) {
        this.minimalPoint = minimalPoint;
    }

    public int getMinimalPoint() {
        return this.minimalPoint;
    }

    public void setTunedInWorks(int tunedInWorks) {
        this.tunedInWorks = tunedInWorks;
    }

    public int getTunedInWorks() {
        return this.tunedInWorks;
    }

    @Override
    public String toString() {
        return "id:" + id + "\n" +
                "name:" + name + "\n" +
                "coordinates:" + "\n" +
                "[x=" + getCoordinates().getX() + "\n" +
                "y=" + getCoordinates().getY() + "]" + "\n" +
                "minimalPoint=" + minimalPoint + "\n" +
                "tunedInWorks=" + tunedInWorks + "\n" +
                "difficulty=" + difficulty + "\n" +
                "author:" + "\n" +
                "[name=" + author.getName() + "\n" +
                "birthday=" + author.getBirthday() + "\n" +
                "height=" + author.getHeight() + "\n" +
                "eyeColor=" + author.getEyeColor() + "]\n" +
                "---------------------";
    }
}

