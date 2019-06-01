package crackattack.view.board.swing;

import crackattack.model.board.BoardModel;
import crackattack.view.AbstractSwingView;

import javax.swing.*;
import java.awt.*;

public class SwingBoardView extends AbstractSwingView {

    private BoardModel model;

    private JLabel scoreLabel;
    private BoardComponent board;


    public SwingBoardView(BoardModel model) {
        super();

        this.model = model;

        LayoutManager layout = new BorderLayout();
        frame.setLayout(layout);

        //Score label
        this.scoreLabel = new JLabel("Your Score: 0");
        this.scoreLabel.setForeground(Color.BLACK);

        frame.getContentPane().add(this.scoreLabel, BorderLayout.WEST);

        //Board display
        this.board = new BoardComponent(this.model.getBoard());

        frame.getContentPane().add(this.board, BorderLayout.EAST);

        frame.pack();
        frame.setVisible(true);

    }

    @Override
    public void render() {
        this.scoreLabel.setText("Your score: " + model.getScore().getScore());

        frame.repaint();
    }
}
