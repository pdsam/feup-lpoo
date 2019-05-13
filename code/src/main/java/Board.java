import java.util.ArrayList;
import java.util.List;

public class Board {
    private Selector selector;
    private GridElement[][] gridElements;

    private int maxX;
    private int maxY;
    private List<BoardObserver> observers = new ArrayList<BoardObserver>();


    public Board() {
        this.gridElements = new GridElement[13][6];
        this.maxX = 6;
        this.maxY = 13;
        this.selector = new Selector(new Position(0,0));

        for (int i = 7; i < 13; i++) {
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
        notifyObserver(p);

    }

    public void attachObserver(BoardObserver ob){
        this.observers.add(ob);
    }

    public void notifyObserver(Position p){
        for(BoardObserver ob : this.observers){
            ob.update(p);
        }
    }

}
