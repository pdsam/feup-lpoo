import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Board {

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

    public Position getSelector() {
        return selectorPosition;
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

    public GridElement getGridElement(Position p){
        return gridElements[p.getY()][p.getX()];
    }

    public void setGridElements(Position p, GridElement element){
        gridElements[p.getY()][p.getX()] = element;
    }

}
