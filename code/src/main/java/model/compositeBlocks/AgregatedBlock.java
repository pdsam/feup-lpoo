package model.compositeBlocks;

import model.Color;
import model.GridElement;

public class AgregatedBlock implements GridElement {
    Color color;
    protected CompositeBlock composite;


    public AgregatedBlock(CompositeBlock composite) {
        this.color =Color.RED;
        this.composite = composite;
    }

    @Override
    public void deleted() {

    }

    @Override
    public Color getColor() {
        return null;
    }
}
