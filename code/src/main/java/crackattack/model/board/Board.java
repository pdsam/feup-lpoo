package crackattack.model.board;

import crackattack.Constants;
import crackattack.model.board.generators.BoardContentGenerator;

import java.util.ArrayList;
import java.util.List;

public class Board implements BoardTemplate {

    public static final int BOARD_WIDTH = Constants.BOARD_COLUMNS;
    public static final int BOARD_HEIGHT = Constants.BOARD_ROWS;

    private Selector selector;
    private GridElement[][] gridElements;

    private List<BoardObserver> observers = new ArrayList<>();

    public Board(BoardContentGenerator generator) {
        this.gridElements = new GridElement[BOARD_HEIGHT][BOARD_WIDTH];
        generator.fillBoard(this.gridElements);

        this.selector = new Selector(new Position(0,0));
    }

    public int getMaxX() {
        return  BOARD_WIDTH;
    }

    public int getMaxY() {
        return BOARD_HEIGHT;
    }

    public Selector getSelector() {
        return selector;
    }

    public GridElement getGridElement(Position p){
        return gridElements[p.getY()][p.getX()];
    }



    public void swap(Position p1, Position p2){
        GridElement g1 = this.getGridElement(p1);
        GridElement g2 = this.getGridElement(p2);

        if (g1 != null || g2 != null) {
            this.setGridElements(p1,g2);
            this.setGridElements(p2,g1);
        }
    }

    public synchronized void setGridElements(Position p, GridElement element){
        gridElements[p.getY()][p.getX()] = element;

    }

    public void attachObserver(BoardObserver ob){
        this.observers.add(ob);
    }

    public void notifyObserver(List<Position> p){
        for(BoardObserver ob : this.observers){
            ob.update(p);
        }
    }



}
