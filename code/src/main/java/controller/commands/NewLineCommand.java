package controller.commands;

import model.Block;
import model.Board;
import model.Position;

import java.util.ArrayList;
import java.util.List;

public class NewLineCommand implements Command {
    private Board board;

    public NewLineCommand(Board board) {
        this.board = board;
    }

    @Override
    public void exec() {

        List<Position> toUpdate = new ArrayList<>();
        for(int i = 0; i < board.getMaxY()-1;i++){
            for(int j = 0; j < board.getMaxX(); j++){

                board.setGridElements(new Position(j,i),board.getGridElement(new Position(j,i+1)));

            }
        }

        for(int i = 0; i < board.getMaxX();i++){
            board.setGridElements(new Position(i,board.getMaxY()-1),new Block());
            toUpdate.add(new Position(i,board.getMaxY()-1));
            board.notifyObserver(toUpdate);
        }
        if (board.getSelector().getPos().getY() > 0t ) {
            board.getSelector().getPos().decrementY();
        }
    }
}
