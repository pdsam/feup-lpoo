package crackattack.events;

import crackattack.Application;
import crackattack.MVC.LoseScreenMVC;
import crackattack.controller.Controller;
import crackattack.controller.board.commands.*;
import crackattack.model.board.BoardModel;

public class BoardEventHandler extends EventHandler {

    private BoardModel model;

    public BoardEventHandler(Application app, BoardModel model) {
        super(app);
        this.model = model;
    }

    @Override
    protected void initiateCallbacks() {
        Controller controller = app.getController();
        callbackMap.put(EventType.UP_ARROW, ()->controller.addCommand(new MoveUpCommand(model.getBoard())));
        callbackMap.put(EventType.DOWN_ARROW, ()->controller.addCommand(new MoveDownCommand(model.getBoard())));
        callbackMap.put(EventType.LEFT_ARROW, ()->controller.addCommand(new MoveLeftCommand(model.getBoard())));
        callbackMap.put(EventType.RIGHT_ARROW, ()->controller.addCommand(new MoveRightCommand(model.getBoard())));
        callbackMap.put(EventType.SPACE, ()->controller.addCommand(new SwapCommand(model.getBoard())));
        callbackMap.put(EventType.ENTER, ()->controller.addCommand(new NewLineCommand(model.getBoard())));
        callbackMap.put(EventType.KEY_Q, ()->app.switchMVC(new LoseScreenMVC(app.getDispatcher(), app.getViewFactory(), model.getScore().getScore())));
        callbackMap.put(EventType.LOST, ()->app.switchMVC(new LoseScreenMVC(app.getDispatcher(), app.getViewFactory(), model.getScore().getScore())));
    }
}
