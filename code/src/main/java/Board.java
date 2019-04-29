import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.HashMap;
import java.util.Map;

public class Board implements Drawable {

    private GridElement[][] gridElements;
    private Position selector;

    public Position getSelector() {
        return selector;
    }

    public Board() {
        this.gridElements = new GridElement[13][6];

        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 6; j++) {
                gridElements[i][j] = new Block();
            }
        }
    }

    @Override
    public void draw(TextGraphics graphics, int x, int y) {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 6; j++) {
                gridElements[i][j].draw(graphics, x+i, y+i);
            }
        }
    }


    public GridElement getGridElement(Position p){
        return gridElements[p.getY()][p.getX()];


    }

    public void setGridElements(Position p, GridElement element){
        gridElements[p.getY()][p.getX()] = element;
    }


    /*
    public void swap(Position p1, Position p2) {
        GridElement g1 = gridElements[p1.getY()][p1.getX()];
        GridElement g2 = gridElements[p2.getY()][p2.getX()];

        if (g1 != null || g2 != null) {
            if (g1 == null) {
                gridElements[p2.getY()][p2.getX()] = null;
                gridElements[p1.getY()][p1.getX()] = g2;
            } else if (g2 == null) {
                gridElements[p1.getY()][p1.getX()] = null;
                gridElements[p2.getY()][p2.getX()] = g1;
            } else {
                gridElements[p2.getY()][p2.getX()] = null;
                gridElements[p1.getY()][p1.getX()] = null;
                gridElements[p2.getY()][p2.getX()] = g1;
                gridElements[p1.getY()][p1.getX()] = g2;
            }
        }
    }
*/

}
