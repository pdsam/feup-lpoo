package crackattack.view.titleScreen.swing;

import crackattack.view.AbstractSwingView;

import javax.swing.*;
import java.awt.*;

public class SwingTitleScreenView extends AbstractSwingView {

    private final String message = "Press Space";

    public SwingTitleScreenView() {
        super();

        frame.setLayout(new OverlayLayout(frame.getContentPane()));

        JLabel titleLabel = new JLabel();
        titleLabel.setOpaque(true);
        titleLabel.setFont(titleLabel.getFont().deriveFont(16F));
        titleLabel.setBackground(Color.WHITE);
        titleLabel.setText(message);
        titleLabel.setAlignmentX(0.5F);
        titleLabel.setAlignmentY(0.5F);
        frame.getContentPane().add(titleLabel);

        TitleScreenComponent titleScreen = new TitleScreenComponent();
        titleScreen.setOpaque(false);
        frame.getContentPane().add(titleScreen);

        frame.pack();
        frame.setVisible(true);

    }

    @Override
    public void render() {}
}
