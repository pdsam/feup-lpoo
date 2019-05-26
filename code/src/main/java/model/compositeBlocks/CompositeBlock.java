package model.compositeBlocks;

import model.Board;
import model.Color;
import model.GridElement;
import model.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class CompositeBlock {
    protected int numberUntillBreak;
    protected int sizeX;
    protected int sizeY;
    protected Position topLeftCorner;
    protected Board board;



    protected List<GridElement> content = new ArrayList<>();


    public Position getTopLeftCorner() {
        return topLeftCorner;
    }



    public int getNumberUntillBreak() {
        return numberUntillBreak;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public List<GridElement> getContent() {
        return content;
    }
}
