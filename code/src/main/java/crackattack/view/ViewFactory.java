package crackattack.view;

import crackattack.model.board.BoardModel;

public interface ViewFactory {
    View createTitleScreenView();
    View createBoardView(BoardModel model);
}
