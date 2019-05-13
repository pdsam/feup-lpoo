public class ComboBoardObserver extends BoardObserver {
    public ComboBoardObserver(Board board, BoardController controller) {
        this.controler = controller;
        this.board = board;
        this.board.attachObserver(this);
    }

    @Override
    public void update(Position p) {

    }
}
