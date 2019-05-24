package model;

import java.util.Random;

public enum Color {
    YELLOW(" ", "#FFFF00"), GREEN(" ", "#00FF00"),
    BLUE(" ", "#0000FF"), PURPLE(" ", "#FF00FF"),
    ORANGE( " ", "#FF5900"), GRAY(" ", "#9E9E9E"),
    BAR("#", "#FFFFFF"), RED(" ","#FF0000");

    private final String character;
    private final String color;

    Color(String c, String color) {
        this.character = c;
        this.color = color;
    }

    public static Color randomColor() {
        return values()[new Random().nextInt(6)];
    }

    public String getCharacter() {
        return character;
    }

    public String getColor() {
        return color;
    }
}
