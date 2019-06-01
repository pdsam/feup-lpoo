package crackattack.events;

import crackattack.Application;
import crackattack.MVC.BoardMVC;

public class LoseScreenEventHandler extends EventHandler{
    public LoseScreenEventHandler(Application app) {
        super(app);
    }

    @Override
    protected void initiateCallbacks() {
         callbackMap.put(EventType.KEY_Q, ()->app.getView().notifyClosing());
         callbackMap.put(EventType.SPACE, ()->app.switchMVC(new BoardMVC(app.getDispatcher(), app.getViewFactory())));
    }
}
