package crackattack.view;

import crackattack.model.board.BoardModel;
import crackattack.view.ViewFactory;
import crackattack.view.View;
import crackattack.view.board.lanterna.LanternaBoardView;
import crackattack.view.loseScreen.lanterna.LanternaLoseScreenView;
import crackattack.view.titleScreen.lanterna.LanternaTitleScreenView;

import java.io.IOException;

public class LanternaViewFactory implements ViewFactory {

    @Override
    public View createTitleScreenView() {
        try {
            return new LanternaTitleScreenView();
        } catch (IOException e) {
            System.err.println("An problem occurred while creating window.");
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }

    @Override
    public View createBoardView(BoardModel model) {
        try {
            return new LanternaBoardView(model);
        } catch (IOException e) {
            System.err.println("An problem occurred while creating window.");
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }

    @Override
    public View createLoseScreenView(int finalScore) {
        try {
            return new LanternaLoseScreenView(finalScore);
        } catch (IOException e) {
            System.err.println("An problem occurred while creating window.");
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }
}
