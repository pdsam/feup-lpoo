import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Board implements Drawable {

    private Position selectorPosition;
    private GridElement[][] gridElements;

    public Board() {
        this.selectorPosition = new Position(0,0);
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
                gridElements[i][j].draw(graphics, x+j, y+i);
            }
        }

        GridElement g1 = gridElements[selectorPosition.getY()][selectorPosition.getX()];
        GridElement g2 = gridElements[selectorPosition.getY()][selectorPosition.getX()+1];

        if (g1 != null) {
            graphics.setBackgroundColor(TextColor.Factory.fromString(g1.getColor().getColor()));
            graphics.enableModifiers(SGR.BOLD);
            graphics.putString(selectorPosition.getX(),selectorPosition.getY(),"S");
        }
        if (g2 != null) {
            graphics.setBackgroundColor(TextColor.Factory.fromString(g2.getColor().getColor()));
            graphics.enableModifiers(SGR.BOLD);
            graphics.putString(selectorPosition.getX()+1,selectorPosition.getY(),"S");
        }

    }

    public void moveSelector(int move) {
        Position nextPosition = new Position(0,0);
        switch (move) {
            case 0:
                nextPosition = new Position(selectorPosition.getX(),selectorPosition.getY()+1);
                break;
            case 1:
                nextPosition = new Position(selectorPosition.getX()-1,selectorPosition.getY());
                break;
            case 2:
                nextPosition = new Position(selectorPosition.getX(),selectorPosition.getY()-1);
                break;
            case 3:
                nextPosition = new Position(selectorPosition.getX()+1,selectorPosition.getY());
                break;
            default:break;
        }

        if (nextPosition.getY() >= 13 || nextPosition.getY() < 0) {
            return;
        }

        if (nextPosition.getX() >= 5 || nextPosition.getX() < 0) {
            return;
        }

        selectorPosition = nextPosition;
    }

    public void swap() {
        swap(new Position(selectorPosition.getX(), selectorPosition.getY()),
                new Position(selectorPosition.getX()+1, selectorPosition.getY()));
    }

    private void swap(Position p1, Position p2) {
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


}