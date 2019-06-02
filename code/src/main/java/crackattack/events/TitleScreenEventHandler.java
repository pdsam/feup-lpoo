package crackattack.events;

import crackattack.Application;
import crackattack.MVC.BoardMVC;

public class TitleScreenEventHandler extends EventHandler {

    public TitleScreenEventHandler(Application app) {
        super(app);
    }

    @Override
    protected void initiateCallbacks() {
        callbackMap.put(EventType.SPACE, ()->app.switchMVC(new BoardMVC(app.getDispatcher(), app.getViewFactory())));
    }
}
