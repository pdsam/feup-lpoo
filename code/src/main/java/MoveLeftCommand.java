public class MoveLeftCommand implements Command {
    private Board board;

    public MoveLeftCommand(Board board) {
        this.board = board;

    }

    @Override
    public void exec() {
        Position pos = board.getSelector().getPos();
        if(pos.getX() -1 < 0)
            return;
        pos.setX(pos.getX()-1);

    }
}
