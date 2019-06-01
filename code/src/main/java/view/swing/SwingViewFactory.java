package view.swing;

import model.BoardModel;
import view.ViewFactory;
import view.View;

public class SwingViewFactory implements ViewFactory {
    @Override
    public View createBoardView(BoardModel model) {
        return new SwingBoardView(model);
    }
}
