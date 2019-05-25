import controller.BoardController;
import model.Board;
import model.BoardModel;
import model.BoardScore;
import model.TestBoardGenerator;
import view.View;
import view.swing.SwingBoardView;

public class Application {

    public static void main(String[] args) throws Exception {
        Board b = new Board(new TestBoardGenerator());
        BoardScore score = new BoardScore();
        BoardModel model = new BoardModel(b, score);
        //View v = new LanternaBoardView(model);
        View v = new SwingBoardView(model);

        BoardController bC = new BoardController(v, model);

        bC.run();
    }

}
