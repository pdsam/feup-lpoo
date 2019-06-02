package crackattack.MVC;

import crackattack.Application;
import crackattack.events.BoardEventHandler;
import crackattack.controller.board.BoardController;
import crackattack.events.EventDispatcher;
import crackattack.events.EventHandler;
import crackattack.model.board.Board;
import crackattack.model.board.generators.BoardGeneratorNoAdjacency;
import crackattack.model.board.BoardModel;
import crackattack.model.board.BoardScore;
import crackattack.view.ViewFactory;

public class BoardMVC extends ModelViewController {

    private BoardModel model;

    public BoardMVC(EventDispatcher dispatcher, ViewFactory viewFactory) {
        super(dispatcher);
        Board b = new Board(new BoardGeneratorNoAdjacency());
        BoardScore score = new BoardScore();
        this.model = new BoardModel(b, score);

        this.view = viewFactory.createBoardView(this.model);
        this.controller = new BoardController(dispatcher, this.model);

    }

    @Override
    public EventHandler getEventHandler(Application app) {
        return new BoardEventHandler(app, model);
    }
}
