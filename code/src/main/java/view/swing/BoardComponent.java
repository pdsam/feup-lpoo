package view.swing;

import model.Board;
import model.GridElement;
import model.Position;

import javax.swing.*;
import java.awt.*;

public class BoardComponent extends JComponent {

    private Board board;

    private final int CELL_HEIGHT = 30;
    private final int CELL_WIDTH = 30;

    public BoardComponent(Board board) {
        this.board = board;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Board.BOARD_WIDTH*CELL_WIDTH,Board.BOARD_HEIGHT*CELL_HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int x = 0; x < Board.BOARD_WIDTH; x++) {
            for (int y = 0; y < Board.BOARD_HEIGHT; y++) {
                GridElement ge = board.getGridElement(new Position(x,y));
                if (ge == null) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(new Color(ge.getColor().getRGB()));
                }
                g.fill3DRect(x*CELL_WIDTH,y*CELL_HEIGHT,CELL_WIDTH,CELL_HEIGHT, true);
            }
        }

        Position selectorPos = board.getSelector().getPos();
        g.setColor(Color.WHITE);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setStroke(new BasicStroke(5));
        g.drawRect(selectorPos.getX()*CELL_WIDTH, selectorPos.getY()*CELL_HEIGHT, CELL_WIDTH*2, CELL_HEIGHT);
    }
}
