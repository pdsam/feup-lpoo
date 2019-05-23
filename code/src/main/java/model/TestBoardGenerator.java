package model;

public class TestBoardGenerator implements BoardContentGenerator {
    @Override
    public void fillBoard(GridElement[][] board) {
        for (int i = 7; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(j == 0){
                    continue;
                }
                board[i][j] = new Block();
            }
        }
    }
}
