package crackattack.MVC;

import crackattack.Application;
import crackattack.controller.Controller;
import crackattack.events.EventDispatcher;
import crackattack.events.EventHandler;
import crackattack.view.View;

public abstract class ModelViewController {
    protected EventDispatcher dispatcher;
    protected View view;
    protected Controller controller;

    public ModelViewController(EventDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public View getView() {
        return view;
    }

    public Controller getController() {
        return controller;
    }

    public abstract EventHandler getEventHandler(Application app);

}
