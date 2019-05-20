package controller.commands;

import model.Board;
import model.Position;

public class MoveDownCommand implements Command {
    private Board board;

    public MoveDownCommand(Board board) {
        this.board = board;
    }


    @Override
    public void exec() {
        Position pos = board.getSelector().getPos();
        if(pos.getY() + 1 >= board.getMaxY())
            return;
        pos.setY(pos.getY()+1);

    }
}
