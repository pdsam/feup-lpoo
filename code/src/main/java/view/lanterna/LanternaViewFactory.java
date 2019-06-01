package view.lanterna;

import model.BoardModel;
import view.ViewFactory;
import view.View;

import java.io.IOException;

public class LanternaViewFactory implements ViewFactory {

    @Override
    public View createBoardView(BoardModel model) {
        try {
            return new LanternaBoardView(model);
        } catch (IOException e) {
            System.err.println("An problem occurred while creating window.");
            e.printStackTrace();
        }

        return null;
    }
}
