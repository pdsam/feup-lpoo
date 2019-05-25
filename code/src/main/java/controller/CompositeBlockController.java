package controller;

import model.Board;
import model.Position;
import model.compositeBlocks.CompositeBlock;

import java.util.ArrayList;
import java.util.List;

public class CompositeBlockController {
    private Board board;
    //private List<CompositeBlock> compositeBlocks= new ArrayList<>();

    public CompositeBlockController(Board board) {
        this.board = board;
    }

    public boolean addComposite(CompositeBlock c){//dunno if works
        
        //compositeBlocks.add(c);
        for(int i = 0; i < board.getMaxY();i++){
            for(int j = 0; j < board.getMaxX();j++){
                if(board.getGridElement(new Position(j,i)) != null){
                    return false;
                }

            }
        }

        for(int i = 0; i < c.getSizeY();i++) {
            for (int j = 0; j < c.getSizeX(); j++) {
                board.setGridElements(new Position(j,i),c.getContent().get(i*c.getSizeY() + j));
            }
        }


        return true;
    }


}
