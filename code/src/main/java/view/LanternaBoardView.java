package view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import model.Board;
import model.Color;
import model.GridElement;
import model.Position;

import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

public class LanternaBoardView extends AbstractView {
    private Screen context;
    private TextGraphics graphics;
    private LanternaInputThread inputListener;

    private Board board;
    private boolean shouldClose = false;

    public LanternaBoardView(Screen screen, Board board) {
        this.eventQueue = new ConcurrentLinkedQueue<>();
        this.context = screen;
        this.inputListener = new LanternaInputThread(this.context, this.eventQueue);

        this.graphics = context.newTextGraphics();

        this.board = board;

        inputListener.start();
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
        } else {
            graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        }
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(selectorPosition.getX(),selectorPosition.getY(),"S");

        if (g2 != null) {
            Color color = g2.getColor();
            graphics.setBackgroundColor(TextColor.Factory.fromString(color.getColor()));
        } else {
            graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        }
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(selectorPosition.getX()+1,selectorPosition.getY(),"S");

        try {
            context.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean shouldClose() {
        return shouldClose;
    }

    @Override
    public void close() {
        inputListener.terminate();
        try {
            inputListener.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        shouldClose = true;
        try {
            context.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
