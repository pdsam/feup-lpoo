package controller.commands;

import model.Block;
import model.Board;
import model.Position;

import java.util.ArrayList;
import java.util.List;

public class NewLineCommand implements Command {
    public static boolean lost = false;
    private Board board;

    public NewLineCommand(Board board) {
        this.board = board;
    }

    @Override
    public void exec() {
        for (int i = 0; i < board.getMaxX(); i++) {
            if (board.getGridElement(new Position(i, 0)) != null) {
                lost = true;
                return;
            }
        }

        List<Position> toUpdate = new ArrayList<>();
        for(int i = 0; i < board.getMaxY()-1;i++){
            for(int j = 0; j < board.getMaxX(); j++){
                board.setGridElements(new Position(j,i),board.getGridElement(new Position(j,i+1)));
            }
        }

        for(int i = 0; i < board.getMaxX();i++){
            Position p = new Position(i, board.getMaxY() - 1);
            board.setGridElements(p,new Block());
            toUpdate.add(p);
        }
        board.notifyObserver(toUpdate);

        if (board.getSelector().getPos().getY() > 0) {
            board.getSelector().getPos().decrementY();
        }
    }
}
