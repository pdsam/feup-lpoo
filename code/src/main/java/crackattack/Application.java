package crackattack;

import crackattack.MVC.ModelViewController;
import crackattack.MVC.TitleScreenMVC;
import crackattack.controller.Controller;
import crackattack.events.EventDispatcher;
import crackattack.events.EventHandler;
import crackattack.events.EventType;
import crackattack.view.LanternaViewFactory;
import crackattack.view.SwingViewFactory;
import crackattack.view.View;
import crackattack.view.ViewFactory;

import static java.lang.System.exit;

public class Application {

    ModelViewController mvc;
    Controller controller;
    View view;
    EventHandler eventHandler;
    ViewFactory viewFactory;

    EventDispatcher dispatcher = new EventDispatcher() {
        @Override
        public void dispatchEvent(EventType event) {
            eventHandler.handleEvent(event);
        }
    };

    public Application(ViewFactory view){
        this.viewFactory = view;
    }

    public static void main(String[] args) {
        if(args.length == 1 && args[0].equals("lanterna")){
           new Application(new LanternaViewFactory()).run();
        }
        else if(args.length == 1 && args[0].equals("swing")){
            new Application(new SwingViewFactory()).run();
        }
        else{
            exit(1);
        }


    }

    private void run() {
        //this.viewFactory = new SwingViewFactory();
        //this.viewFactory = new LanternaViewFactory();

        ModelViewController mvc = new TitleScreenMVC(dispatcher, this.viewFactory);
        switchMVC(mvc);

        while (!getView().shouldClose()) {
            EventType currentEvent;
            while ((currentEvent = getView().pollEvents()) != null) {
                dispatcher.dispatchEvent(currentEvent);
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

    public EventDispatcher getDispatcher() {
        return dispatcher;
    }
}
