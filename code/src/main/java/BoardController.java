public class BoardController {
    private Board board;
    private View boardView;

    public BoardController(View view, Board board) {
        this.boardView = view;
        this.board = board;
    }

    public void run() {
        boardView.render();
        while (!boardView.shouldClose()) {
            boardView.processInput();
            boardView.render();
        }
    }
}
