import controller.BoardController;
import model.*;
import view.View;
import view.lanterna.LanternaBoardView;
import view.swing.SwingBoardView;

public class Application {

    public static void main(String[] args) throws Exception {
        Board b = new Board(new BoardGeneratorNoAdjacency());
        BoardScore score = new BoardScore();
        BoardModel model = new BoardModel(b, score);
        View v = new LanternaBoardView(model);
        //View v = new SwingBoardView(model);

        BoardController bC = new BoardController(v, model);

        bC.run();
    }

}
