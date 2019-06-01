package crackattack.view.board.lanterna;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import crackattack.Constants;
import crackattack.model.board.*;
import crackattack.view.AbstractLanternaView;

import java.io.IOException;

public class LanternaBoardView extends AbstractLanternaView {
    private BoardModel model;

    public final static int CELL_HEIGHT = Constants.LANTERNA_BOARD_CELL_HEIGHT;
    public final static int CELL_WIDTH = Constants.LANTERNA_BOARD_CELL_WIDTH;

    public LanternaBoardView(BoardModel model) throws IOException {
        super();

        this.model = model;
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
        NextLineTime time = model.getTimeToNextLine();

        for (int i = 0; i < Board.BOARD_HEIGHT; i++) {
            for (int j = 0; j < Board.BOARD_WIDTH; j++) {
                GridElement ge = board.getGridElement(new Position(j,i));
                drawGridElement(ge, j*CELL_WIDTH, i*CELL_HEIGHT);
            }
        }

        drawSelector(board);

        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.putString(Board.BOARD_WIDTH*CELL_WIDTH+3,3,"Your score: " + score.getScore());
        graphics.putString(Board.BOARD_WIDTH*CELL_WIDTH+3, 5, "                                                           ");
        graphics.putString(Board.BOARD_WIDTH*CELL_WIDTH+3,5,"Next line: " + time.getTime());

        try {
            context.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
