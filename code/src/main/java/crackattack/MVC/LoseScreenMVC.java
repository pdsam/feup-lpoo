package crackattack.MVC;

import crackattack.Application;
import crackattack.controller.UselessController;
import crackattack.events.EventDispatcher;
import crackattack.events.EventHandler;
import crackattack.events.LoseScreenEventHandler;
import crackattack.view.ViewFactory;

public class LoseScreenMVC extends ModelViewController {
    public LoseScreenMVC(EventDispatcher dispatcher, ViewFactory viewFactory, int finalScore) {
        super(dispatcher);
        this.view = viewFactory.createLoseScreenView(finalScore);
        this.controller = new UselessController(dispatcher);
    }

    @Override
    public EventHandler getEventHandler(Application app) {
        return new LoseScreenEventHandler(app);
    }
}
