package crackattack.view;

import crackattack.model.board.BoardModel;
import crackattack.view.board.swing.SwingBoardView;
import crackattack.view.loseScreen.swing.SwingLoseScreenView;
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

    @Override
    public View createLoseScreenView(int finalScore) {
        return new SwingLoseScreenView(finalScore);
    }
}
