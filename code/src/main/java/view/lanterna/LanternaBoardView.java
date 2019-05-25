package view.lanterna;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import model.*;
import model.Color;
import view.AbstractView;

import java.io.IOException;

public class LanternaBoardView extends AbstractView {
    private Screen context;
    private TextGraphics graphics;
    private LanternaInputThread inputListener;

    private BoardModel model;

    private final static int CELL_HEIGHT = 2;
    private final static int CELL_WIDTH = 3;

    public LanternaBoardView(BoardModel model) throws IOException {
        super();

        Terminal term = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(80, Board.BOARD_HEIGHT*CELL_HEIGHT)).createTerminal();
        Screen screen = new TerminalScreen(term);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();

        this.context = screen;
        this.inputListener = new LanternaInputThread(this.context, this.eventQueue);

        this.graphics = context.newTextGraphics();

        this.model = model;

        inputListener.start();
    }

    private void drawCellContents(int x, int y, String content) {
        for (int i = 0; i < CELL_HEIGHT; i++) {
            for (int j = 0; j < CELL_WIDTH; j++) {
                graphics.putString(x+j,y+i, content);
            }
        }
    }

    private void drawGridElement(GridElement element, int x, int y) {

        if (element != null) {
            Color color = element.getColor();
            graphics.setBackgroundColor(TextColor.Factory.fromString(color.getColor()));
            graphics.enableModifiers(SGR.BOLD);
        } else {
            graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
            graphics.enableModifiers(SGR.BOLD);
        }

        drawCellContents(x, y, " ");

    }

    private void drawSelector(Board board) {
        Position selectorPosition = board.getSelector().getPos();
        GridElement g1 = board.getGridElement(new Position(selectorPosition.getX(), selectorPosition.getY()));
        GridElement g2 = board.getGridElement(new Position(selectorPosition.getX() + 1, selectorPosition.getY()));

        setupSelectorBackground(g1);
        drawCellContents(selectorPosition.getX()*CELL_WIDTH,selectorPosition.getY()*CELL_HEIGHT,"S");

        setupSelectorBackground(g2);
        drawCellContents((selectorPosition.getX()+1)*CELL_WIDTH,selectorPosition.getY()*CELL_HEIGHT,"S");
    }

    private void setupSelectorBackground(GridElement g1) {
        if (g1 != null) {
            Color color = g1.getColor();
            graphics.setBackgroundColor(TextColor.Factory.fromString(color.getColor()));
        } else {
            graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        }
        graphics.enableModifiers(SGR.BOLD);
    }

    @Override
    public void render() {
        Board board = model.getBoard();
        BoardScore score = model.getScore();

        for (int i = 0; i < Board.BOARD_HEIGHT; i++) {
            for (int j = 0; j < Board.BOARD_WIDTH; j++) {
                GridElement ge = board.getGridElement(new Position(j,i));
                drawGridElement(ge, j*CELL_WIDTH, i*CELL_HEIGHT);
            }
        }

        drawSelector(board);

        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.putString(Board.BOARD_WIDTH*CELL_WIDTH+3,3,"Your score: " + score.getScore());

        try {
            context.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notifyClosing() {
        inputListener.terminate();
        try {
            inputListener.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            context.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        return;
    }
}
