package crackattack.controller.board.commands;

import crackattack.model.board.Board;
import crackattack.model.board.Position;

public class MoveUpCommand implements Command {
    private Board board;

    public MoveUpCommand(Board board) {
        this.board = board;
    }


    @Override
    public void exec() {
        Position pos = board.getSelector().getPos();
        if(pos.getY() -1 <0)
            return;
        pos.setY(pos.getY()-1);


    }
}
