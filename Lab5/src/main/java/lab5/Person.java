package lab5;

import java.time.ZonedDateTime;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.time.ZonedDateTime birthday; //Поле не может быть null
    private float height; //Значение поля должно быть больше 0
    private Color eyeColor; //Поле не может быть null

    public Person(String name, ZonedDateTime birthday, float height, Color eyeColor){
        this.name = name;
        this.birthday = birthday;
        this.height = height;
        this.eyeColor = eyeColor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBirthday(ZonedDateTime birthday) {
        this.birthday = birthday;
    }

    public ZonedDateTime getBirthday() {
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
