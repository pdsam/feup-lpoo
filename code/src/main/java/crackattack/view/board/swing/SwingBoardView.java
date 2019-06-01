package crackattack.view.board.swing;

import crackattack.model.board.BoardModel;
import crackattack.view.AbstractSwingView;

import javax.swing.*;
import java.awt.*;

public class SwingBoardView extends AbstractSwingView {

    private BoardModel model;

    private JLabel scoreLabel;
    private JLabel timeLabel;
    private BoardComponent board;


    public SwingBoardView(BoardModel model) {
        super();

        this.model = model;

        LayoutManager layout = new BorderLayout();
        frame.setLayout(layout);

        //Score label
        this.scoreLabel = new JLabel("Your Score: 0");
        this.scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.scoreLabel.setForeground(Color.BLACK);

        this.timeLabel = new JLabel();
        this.timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.timeLabel.setForeground(Color.BLACK);

        JPanel hPane = new JPanel();
        hPane.setLayout(new BoxLayout(hPane, BoxLayout.PAGE_AXIS));

        hPane.add(this.scoreLabel);
        hPane.add(this.timeLabel);

        hPane.setAlignmentY(Component.CENTER_ALIGNMENT);

        //Board display
        this.board = new BoardComponent(this.model.getBoard());

        frame.getContentPane().add(this.board, BorderLayout.EAST);
        frame.getContentPane().add(hPane, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);

    }

    @Override
    public void render() {
        this.scoreLabel.setText("Your score: " + model.getScore().getScore());
        this.timeLabel.setText("Next line: " + model.getTimeToNextLine().getTime());

        frame.repaint();
    }
}
