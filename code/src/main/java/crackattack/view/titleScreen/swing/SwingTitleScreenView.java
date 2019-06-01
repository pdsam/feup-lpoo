package crackattack.view.titleScreen.swing;

import crackattack.view.AbstractSwingView;

import javax.swing.*;

public class SwingTitleScreenView extends AbstractSwingView {

    public SwingTitleScreenView() {
        super();

        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        TitleScreenComponent titleScreen = new TitleScreenComponent();

        frame.getContentPane().add(titleScreen);

        frame.pack();
        frame.setVisible(true);

    }

    @Override
    public void render() {

    }
}
