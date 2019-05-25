package model.compositeBlocks;

import model.Board;
import model.Position;

public class BasicComposite extends CompositeBlock {
    public BasicComposite(Board board) {
        this.sizeX=board.getMaxX();
        this.sizeY = 1;
        this.numberUntillBreak = 1;
        this.topLeftCorner = new Position(0,board.getMaxY()-1);
        for(int i = 0; i < sizeX*sizeY;i++){
            this.content.add(new AgregatedBlock(this));
        }
    }

    @Override
    public void toBlocks() {
        Position temp = new Position(topLeftCorner.getX(),topLeftCorner.getY());
        for(int i = 0; i < sizeY; i++){

        }


    }


}
