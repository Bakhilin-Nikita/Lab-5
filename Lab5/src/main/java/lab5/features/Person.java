package lab5.features;

import lab5.features.enums.Color;

import java.time.LocalDate;


public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String birthday; //Поле не может быть null
    private float height; //Значение поля должно быть больше 0
    private Color eyeColor; //Поле не может быть null

    public Person(String name, Color eyeColor, double height, String birthday) {
        this.birthday = birthday;
        this.name = name;
        this.height = (float) height;
        this.eyeColor = eyeColor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBirthday(LocalDate birthday) {
        //this.birthday = birthday;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setEyeColor(Color eyeColor) {
        this.eyeColor = eyeColor;
    }

    public Color getEyeColor() {
        return eyeColor;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getHeight() {
        return height;
    }
}
