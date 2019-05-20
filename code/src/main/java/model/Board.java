package model;

import controller.BoardObserver;

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
                if(j == 0){
                    continue;
                }
                gridElements[i][j] = new Block();
            }
        }
    }

    public int getMaxX() {
        return  maxX;
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


    //isto é do controler ou daqui?
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
            for(Position temp: p){
                ob.update(temp);
            }
        }
    }



}
