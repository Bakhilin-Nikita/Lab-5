package object.enums;

public enum Difficulty {
    VERY_EASY("очень легко"),
    EASY("легко"),
    VERY_HARD("очень тудно"),
    IMPOSSIBLE("невозможно"),
    HOPELESS("безнадёжно");

    String name;

    Difficulty(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
