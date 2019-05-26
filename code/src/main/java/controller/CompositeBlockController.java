package controller;

import model.Block;
import model.Board;
import model.Position;
import model.compositeBlocks.CompositeBlock;

import java.util.ArrayList;
import java.util.List;


public class CompositeBlockController {
    private Board board;

    public CompositeBlockController(Board board) {
        this.board = board;
    }

    public boolean addCompositeToBoard(CompositeBlock c){//dunno if works

        int sizeY = c.getSizeY();
        int sizeX = c.getSizeX();

        //checking if can insert
        for(int i = 0; i < sizeY; i ++){
            for(int j = 0; j < sizeX; j++){
                if(board.getGridElement(new Position(j,board.getMaxY()-1-i))!= null){
                    return false;
                }

            }



        }
        for(int i = 0; i < sizeY; i ++) {
            for (int j = 0; j < sizeX; j++) {
                board.setGridElements(new Position(j,board.getMaxY()-1-i),c.getContent().get(i*sizeX+j));

            }
        }

        return true;
    }


    public void toBlocks(CompositeBlock c) {
        Position temp = new Position(c.getTopLeftCorner().getX(),c.getTopLeftCorner().getY());
        for(int i = 0; i < c.getSizeY(); i++){
            for(int j = 0; j < c.getSizeX();i++){
                board.setGridElements(new Position(temp.getX()+j,temp.getX()+i),new Block());
            }
        }

        }

}
