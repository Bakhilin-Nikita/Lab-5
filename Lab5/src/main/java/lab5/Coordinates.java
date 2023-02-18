package lab5;

import lab5.exceptions.InvalidFieldY;
import lab5.exceptions.NullX;

public class Coordinates {
    private Integer x; //Поле не может быть null
    private Double y; //Значение поля должно быть больше -184, Поле не может быть null

    public Coordinates(Integer x, Double y)
            throws NullX, InvalidFieldY {
        if (x == null) {
            throw new NullX("Field X can not be NULL!");
        } else {
            this.x = x;
        }
        if (y == null || y < -184) {
            throw new InvalidFieldY("Field Y must be > -184 and con not be NULL");
        } else {
            this.y = y;
        }
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getX() {
        return x;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getY() {
        return y;
    }
}
