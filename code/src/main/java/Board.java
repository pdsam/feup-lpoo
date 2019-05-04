import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Board {



    private Selector selector;
    private GridElement[][] gridElements;



    private int maxX;
    private int maxY;


    public Board() {
        this.gridElements = new GridElement[13][6];
        this.maxX = 6;
        this.maxY = 13;
        this.selector = new Selector(new Position(0,0));

        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 6; j++) {
                gridElements[i][j] = new Block();
            }
        }
    }


    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public Selector getSelector() {
        return selector;
    }

    public void moveSelector(int move) {
        Position nextPosition = new Position(0,0);
        switch (move) {
            case 0:
                nextPosition = new Position(selector.getPos().getX(),selector.getPos().getY()+1);
                break;
            case 1:
                nextPosition = new Position(selector.getPos().getX()-1,selector.getPos().getY());
                break;
            case 2:
                nextPosition = new Position(selector.getPos().getX(),selector.getPos().getY()-1);
                break;
            case 3:
                nextPosition = new Position(selector.getPos().getX()+1,selector.getPos().getY());
                break;
            default:break;
        }

        if (nextPosition.getY() >= 13 || nextPosition.getY() < 0) {
            return;
        }

        if (nextPosition.getX() >= 5 || nextPosition.getX() < 0) {
            return;
        }

        selector.setPos(nextPosition);
    }

    public GridElement getGridElement(Position p){
        return gridElements[p.getY()][p.getX()];
    }

    public void setGridElements(Position p, GridElement element){
        gridElements[p.getY()][p.getX()] = element;
    }

}
