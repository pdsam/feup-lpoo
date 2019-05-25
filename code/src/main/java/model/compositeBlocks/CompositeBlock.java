package model.compositeBlocks;

import model.Board;
import model.Color;
import model.GridElement;

import java.util.ArrayList;
import java.util.List;

public abstract class CompositeBlock {
    int numberUntillBreak;
    int sizeX;
    int sizeY;
    List<GridElement> content = new ArrayList<>();

    public abstract void toBlocks();

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
