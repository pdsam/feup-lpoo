package model.compositeBlocks;

import model.Board;
import model.Position;

public class BasicComposite extends CompositeBlock {
    public BasicComposite(Board board) {
        this.board = board;
        this.sizeX=board.getMaxX();
        this.sizeY = 1;
        this.numberUntillBreak = 1;
        this.topLeftCorner = new Position(0,board.getMaxY()-1);
        for(int i = 0; i < sizeX*sizeY;i++){
            this.content.add(new AgregatedBlock(this));
        }
    }





}
