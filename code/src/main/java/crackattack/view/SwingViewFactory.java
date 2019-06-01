package crackattack.view;

import crackattack.model.board.BoardModel;
import crackattack.view.ViewFactory;
import crackattack.view.View;
import crackattack.view.board.swing.SwingBoardView;
import crackattack.view.titleScreen.swing.SwingTitleScreenView;

public class SwingViewFactory implements ViewFactory {
    @Override
    public View createTitleScreenView() {
        return new SwingTitleScreenView();
    }

    @Override
    public View createBoardView(BoardModel model) {
        return new SwingBoardView(model);
    }
}
