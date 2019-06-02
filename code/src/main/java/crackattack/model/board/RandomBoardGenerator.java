package crackattack.model.board;

public class RandomBoardGenerator implements BoardContentGenerator {
    @Override
    public void fillBoard(GridElement[][] board) {
        for (int i = board.length/2; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Block();
            }
        }
    }
}
