import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class LanternaBoardView implements View{
    private Screen context;
    private TextGraphics graphics;

    private Board board;
    private boolean shouldClose = false;

    public LanternaBoardView(Screen screen, Board board) {
        this.context = screen;
        this.graphics = context.newTextGraphics();
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

        Position selectorPosition = board.getSelector().getPos();
        GridElement g1 = board.getGridElement(new Position(selectorPosition.getX(), selectorPosition.getY()));
        GridElement g2 = board.getGridElement(new Position(selectorPosition.getX() + 1, selectorPosition.getY()));

        if (g1 != null) {
            Color color = g1.getColor();
            graphics.setBackgroundColor(TextColor.Factory.fromString(color.getColor()));
            graphics.enableModifiers(SGR.BOLD);
            graphics.putString(selectorPosition.getX(),selectorPosition.getY(),"S");
        }
        else{
            graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
            graphics.enableModifiers(SGR.BOLD);
            graphics.putString(selectorPosition.getX(),selectorPosition.getY(),"S");

        }
        if (g2 != null) {
            Color color = g2.getColor();
            graphics.setBackgroundColor(TextColor.Factory.fromString(color.getColor()));
            graphics.enableModifiers(SGR.BOLD);
            graphics.putString(selectorPosition.getX()+1,selectorPosition.getY(),"S");
        }
        else{
            graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
            graphics.enableModifiers(SGR.BOLD);
            graphics.putString(selectorPosition.getX()+1,selectorPosition.getY(),"S");

        }

        try {
            context.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processInput() {
        KeyStroke kS;
        try {
            kS = context.readInput();

            switch (kS.getKeyType()) {
                case ArrowDown:
                    new MoveDownCommand(board).exec();
                    break;
                case ArrowLeft:
                    new MoveLeftCommand(board).exec();
                    break;
                case ArrowUp:
                    new MoveUpCommand(board).exec();
                    break;
                case ArrowRight:
                    new MoveRightCommand(board).exec();
                    break;
                case Character:
                    if (kS.getCharacter() == ' ') {
                        new SwapCommand(board).exec();
                    } else if (kS.getCharacter() == 'q') {
                        shouldClose = true;
                        context.close();
                    }
                    break;
                case EOF:
                    shouldClose = true;
                    context.close();
                    break;
                default:break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean shouldClose() {
        return shouldClose;
    }
}
