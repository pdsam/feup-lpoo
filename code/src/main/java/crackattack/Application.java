package crackattack;

import crackattack.MVC.BoardMVC;
import crackattack.MVC.ModelViewController;
import crackattack.MVC.TitleScreenMVC;
import crackattack.controller.Controller;
import crackattack.events.EventHandler;
import crackattack.events.EventType;
import crackattack.view.SwingViewFactory;
import crackattack.view.View;
import crackattack.view.ViewFactory;
import crackattack.view.LanternaViewFactory;

public class Application {

    ModelViewController mvc;
    Controller controller;
    View view;
    EventHandler eventHandler;
    ViewFactory viewFactory;

    public static void main(String[] args) {
        new Application().run();
    }

    private void run() {
        this.viewFactory = new SwingViewFactory();

        switchMVC(new TitleScreenMVC(viewFactory));

        while (!getView().shouldClose()) {
            EventType currentEvent;
            while ((currentEvent = getView().pollEvents()) != null) {
                eventHandler.handleEvent(currentEvent);
            }

            getController().executeCommands();

            getController().tick();

            getView().render();
        }

        System.out.println("closing");
        getView().close();
    }

    public void switchMVC(ModelViewController mvc) {
        this.mvc = mvc;
        this.controller = mvc.getController();
        this.view = mvc.getView();
        this.eventHandler = mvc.getEventHandler(this);
    }

    public View getView() {
        return mvc.getView();
    }

    public Controller getController() {
        return mvc.getController();
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }
}
