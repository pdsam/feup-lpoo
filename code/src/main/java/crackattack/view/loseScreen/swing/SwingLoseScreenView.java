package crackattack.view.loseScreen.swing;

import crackattack.view.AbstractSwingView;

import javax.swing.*;
import java.awt.*;

public class SwingLoseScreenView extends AbstractSwingView {

    private final String youLostMessage = "You lost. Score: ";
    private final String tryGainMessage = "[Space] - Try again";
    private final String quitMessage = "[Q] - Quit";


    public SwingLoseScreenView(int finalScore) {

        LayoutManager layout = new GridBagLayout();

        frame.setLayout(layout);

        JLabel youLostLabel = new JLabel();
        youLostLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        youLostLabel.setText(youLostMessage + finalScore);

        JLabel tryAgainLabel = new JLabel();
        tryAgainLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        tryAgainLabel.setText(tryGainMessage);

        JLabel quitLabel = new JLabel();
        quitLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitLabel.setText(quitMessage);

        JPanel hPane = new JPanel();
        hPane.setLayout(new BoxLayout(hPane, BoxLayout.PAGE_AXIS));
        hPane.add(youLostLabel, BorderLayout.CENTER);
        hPane.add(tryAgainLabel, BorderLayout.CENTER);
        hPane.add(quitLabel, BorderLayout.CENTER);
        GridBagConstraints hPaneC = new GridBagConstraints();
        hPaneC.gridx = 0; hPaneC.gridy = 0;

        frame.getContentPane().add(hPane, hPaneC);

        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void render() {

    }
}
