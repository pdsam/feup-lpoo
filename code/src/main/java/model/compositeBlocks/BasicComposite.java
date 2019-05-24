package model.compositeBlocks;

import model.AgregatedBlock;
import model.Board;
import model.Color;

public class BasicComposite extends CompositeBlock {
    public BasicComposite(Board board) {
        this.sizeX=board.getMaxX();
        this.sizeY = 1;
        this.numberUntillBreak = 1;
        for(int i = 0; i < sizeX*sizeY;i++){
            this.content.add(new AgregatedBlock());
        }
    }

    @Override
    public void toBlocks() {

    }

    @Override
    public void deleted() {

    }

    @Override
    public Color getColor() {
        return null;
    }
}
