import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class LanternaBoardView implements View{
    private TextGraphics graphics;
    private Board board;

    public LanternaBoardView(TextGraphics graphics, Board board) {
        this.graphics = graphics;
        this.board = board;
    }

    @Override
    public void render() {

        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 6; j++) {
                GridElement ge = board.getGridElement(new Position(j,i));

                if (ge != null) {
                    Color color = ge.getColor();
                    graphics.setBackgroundColor(TextColor.Factory.fromString(color.getColor()));
                    graphics.enableModifiers(SGR.BOLD);
                    graphics.putString(j,i, color.getCharacter());
                } else {
                    graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
                    graphics.enableModifiers(SGR.BOLD);
                    graphics.putString(j,i, " ");
                }
            }
        }

        Position selectorPosition = board.getSelector();
        GridElement g1 = board.getGridElement(new Position(selectorPosition.getX(), selectorPosition.getY()));
        GridElement g2 = board.getGridElement(new Position(selectorPosition.getX() + 1, selectorPosition.getY()));

        if (g1 != null) {
            Color color = g1.getColor();
            graphics.setBackgroundColor(TextColor.Factory.fromString(color.getColor()));
            graphics.enableModifiers(SGR.BOLD);
            graphics.putString(selectorPosition.getX(),selectorPosition.getY(),"S");
        }
        if (g2 != null) {
            Color color = g2.getColor();
            graphics.setBackgroundColor(TextColor.Factory.fromString(color.getColor()));
            graphics.enableModifiers(SGR.BOLD);
            graphics.putString(selectorPosition.getX()+1,selectorPosition.getY(),"S");
        }
    }
}
