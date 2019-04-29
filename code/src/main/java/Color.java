import java.util.Random;

public enum Color {
    YELLOW('Y', "#FFFF00"), GREEN('G', "#00FF00"),
    BLUE('B', "#0000FF"), PURPLE('P', "#FF00FF"),
    ORANGE('O', "#FF5900"), GRAY('G', "#9E9E9E"),
    BAR('#', "#FFFFFF");

    private final char character;
    private final String color;

    Color(char c, String color) {
        this.character = c;
        this.color = color;
    }

    public static Color randomColor() {
        return values()[new Random().nextInt(6)];
    }

    public char getCharacter() {
        return character;
    }

    public String getColor() {
        return color;
    }
}
