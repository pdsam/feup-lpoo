import crackattack.model.board.Board;
import crackattack.model.board.BoardModel;
import crackattack.model.board.BoardScore;
import crackattack.model.board.generators.TestBoardGenerator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestModelBoard {
    //@Before
    private Board board = new Board(new TestBoardGenerator());
    private BoardScore score = new BoardScore();
    private BoardModel boardModel = new BoardModel(board,score);

    @Test
    public void testScore(){
        score.setScore(0);
        score.increase(200);
        assertEquals(200,score.getScore());
    }


}
