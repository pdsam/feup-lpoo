package crackattack.MVC;

import crackattack.Application;
import crackattack.controller.titleScreen.TitleScreenController;
import crackattack.events.EventHandler;
import crackattack.events.TitleScreenEventHandler;
import crackattack.view.ViewFactory;

public class TitleScreenMVC extends ModelViewController {

    public TitleScreenMVC(ViewFactory viewFactory) {
        this.view = viewFactory.createTitleScreenView();
        this.controller = new TitleScreenController();
    }

    @Override
    public EventHandler getEventHandler(Application app) {
        return new TitleScreenEventHandler(app);
    }
}
