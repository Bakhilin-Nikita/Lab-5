package lab5;

import lab5.features.Coordinates;
import lab5.features.Person;
import lab5.features.enums.Difficulty;

/**
 * The LabWork is main object for console App
 */

public class LabWork {

    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates = new Coordinates(); //Поле не может быть null
    // private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int minimalPoint; //Значение поля должно быть больше 0
    private int tunedInWorks;
    private Difficulty difficulty; //Поле может быть null
    private Person author; //Поле не может быть null

    public LabWork(){}

    public LabWork(int id, String name, int minimalPoint, int tunedInWorks, Difficulty difficulty, Coordinates coordinates, Person author) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.minimalPoint = minimalPoint;
        this.author = author;
        this.tunedInWorks = tunedInWorks; // I do not understand what is it
        this.difficulty = difficulty;

        // set up automatically
        //this.creationDate = ZonedDateTime.now();
    }

    public void setDifficulty(Difficulty diff) {
        this.difficulty = diff;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
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

}
