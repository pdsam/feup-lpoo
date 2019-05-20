package controller.commands;

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
