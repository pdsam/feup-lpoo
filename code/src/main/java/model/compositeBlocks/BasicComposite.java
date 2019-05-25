package model.compositeBlocks;

import model.Board;

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


}
