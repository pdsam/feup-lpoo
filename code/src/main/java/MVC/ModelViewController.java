package MVC;

import controller.Controller;
import view.View;

public abstract class ModelViewController {
    protected View view;
    protected Controller controller;

    public View getView() {
        return view;
    }

    public Controller getController() {
        return controller;
    }
}
