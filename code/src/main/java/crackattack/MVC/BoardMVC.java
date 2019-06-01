package crackattack.MVC;

import crackattack.Application;
import crackattack.events.BoardEventHandler;
import crackattack.controller.board.BoardController;
import crackattack.events.EventHandler;
import crackattack.model.board.Board;
import crackattack.model.board.BoardGeneratorNoAdjacency;
import crackattack.model.board.BoardModel;
import crackattack.model.board.BoardScore;
import crackattack.view.ViewFactory;

public class BoardMVC extends ModelViewController {

    private BoardModel model;

    public BoardMVC(ViewFactory viewFactory) {
        Board b = new Board(new BoardGeneratorNoAdjacency());
        BoardScore score = new BoardScore();
        this.model = new BoardModel(b, score);

        this.view = viewFactory.createBoardView(this.model);
        this.controller = new BoardController(this.view, this.model);

    }

    @Override
    public EventHandler getEventHandler(Application app) {
        return new BoardEventHandler(app, model);
    }
}
