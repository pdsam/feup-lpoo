package crackattack.model.board;

public class BoardModel {
    private Board board;
    private BoardScore score;

    public BoardModel(Board board, BoardScore score) {
        this.board = board;
        this.score = score;
    }

    public Board getBoard() {
        return board;
    }

    public BoardScore getScore() {
        return score;
    }
}
