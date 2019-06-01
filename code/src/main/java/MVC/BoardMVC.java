package MVC;

import controller.BoardController;
import model.Board;
import model.BoardGeneratorNoAdjacency;
import model.BoardModel;
import model.BoardScore;
import view.ViewFactory;

public class BoardMVC extends ModelViewController{

    private BoardModel model;

    public BoardMVC(ViewFactory viewFactory) {
        Board b = new Board(new BoardGeneratorNoAdjacency());
        BoardScore score = new BoardScore();
        this.model = new BoardModel(b, score);

        this.view = viewFactory.createBoardView(this.model);
        this.controller = new BoardController(this.view, this.model);

    }
}
