package crackattack.controller.board.commands;

import crackattack.model.board.Board;
import crackattack.model.board.Position;

public class MoveRightCommand implements Command {
    private Board board;

    public MoveRightCommand(Board board) {
        this.board = board;
    }


    @Override
    public void exec() {

        Position pos = board.getSelector().getPos();
        if(pos.getX() +1 >= board.getMaxX() -1)
            return;
        pos.setX(pos.getX()+1);

    }
}
