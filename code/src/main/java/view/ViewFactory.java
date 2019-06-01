package view;

import model.BoardModel;
import view.View;

public interface ViewFactory {
    public View createBoardView(BoardModel model);
}
