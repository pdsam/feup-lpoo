package model.compositeBlocks;

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
}
