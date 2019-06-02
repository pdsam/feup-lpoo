package crackattack.MVC;

import crackattack.Application;
import crackattack.controller.UselessController;
import crackattack.events.EventDispatcher;
import crackattack.events.EventHandler;
import crackattack.events.TitleScreenEventHandler;
import crackattack.view.ViewFactory;

public class TitleScreenMVC extends ModelViewController {

    public TitleScreenMVC(EventDispatcher dispatcher, ViewFactory viewFactory) {
        super(dispatcher);
        this.view = viewFactory.createTitleScreenView();
        this.controller = new UselessController(dispatcher);
    }

    @Override
    public EventHandler getEventHandler(Application app) {
        return new TitleScreenEventHandler(app);
    }
}
