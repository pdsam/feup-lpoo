public class SwapCommand implements Command {

    private Board board;

    public SwapCommand(Board board) {
        this.board = board;

    }

    @Override
    public void exec(){
        Position p1 = board.getSelector().getPos();
        Position p2 = new Position(p1.getX()+1, p1.getY());

        board.swap(p1,p2);

        board.notifyObserver(p1);
        board.notifyObserver(p2);

    }
}
