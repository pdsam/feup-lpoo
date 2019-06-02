package crackattack.model.board;

public class BoardModel {
    private Board board;
    private BoardScore score;

    private NextLineTime timeToNextLine;

    public BoardModel(Board board, BoardScore score) {
        this.board = board;
        this.score = score;
        this.timeToNextLine = new NextLineTime();
    }

    public Board getBoard() {
        return board;
    }

    public BoardScore getScore() {
        return score;
    }

    public NextLineTime getTimeToNextLine() {
        return timeToNextLine;
    }
}
