package model;

import java.util.Random;

public enum Color {
    YELLOW(" ", "#FFFF00", 0xffff00), GREEN(" ", "#00FF00", 0x00ff00),
    BLUE(" ", "#0000FF", 0x0000ff), PURPLE(" ", "#FF00FF", 0xff00ff),
    ORANGE( " ", "#FF5900", 0xff5900), GRAY(" ", "#9E9E9E", 0x9e9e9e),
    BAR("#", "#FFFFFF", 0xffffff);

    private final String character;
    private final String color;
    private final int rgb;

    Color(String c, String color, int rgb) {
        this.character = c;
        this.color = color;
        this.rgb = rgb;
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

    public int getRGB() {
        return rgb;
    }

}
