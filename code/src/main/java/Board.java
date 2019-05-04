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

    public GridElement getGridElement(Position p){
        return gridElements[p.getY()][p.getX()];
    }

    public void setGridElements(Position p, GridElement element){
        gridElements[p.getY()][p.getX()] = element;
    }

}
