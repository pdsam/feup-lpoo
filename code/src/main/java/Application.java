import MVC.BoardMVC;
import MVC.ModelViewController;
import controller.Controller;
import view.View;
import view.ViewFactory;
import view.lanterna.LanternaViewFactory;
import view.swing.SwingViewFactory;

public class Application {

    public static void main(String[] args) {

        ViewFactory viewFactory = new LanternaViewFactory();

        ModelViewController boardMVC = new BoardMVC(viewFactory);

        View view = boardMVC.getView();

        Controller controller = boardMVC.getController();

        while (!view.shouldClose()) {
            controller.tick();
        }

        view.close();
    }

}
