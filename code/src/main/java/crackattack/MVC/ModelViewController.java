package crackattack.MVC;

import crackattack.Application;
import crackattack.controller.Controller;
import crackattack.events.EventHandler;
import crackattack.view.View;

public abstract class ModelViewController {
    protected View view;
    protected Controller controller;

    public View getView() {
        return view;
    }

    public Controller getController() {
        return controller;
    }

    public abstract EventHandler getEventHandler(Application app);

}
