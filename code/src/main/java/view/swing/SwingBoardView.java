package view.swing;

import model.BoardModel;
import view.AbstractView;
import view.EventType;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SwingBoardView extends AbstractView {

    private BoardModel model;

    private JFrame frame;
    private JLabel scoreLabel;
    private BoardComponent board;

    private boolean open = true;

    public SwingBoardView(BoardModel model) {
        this.model = model;

        this.frame = new JFrame("Crack Attack");
        LayoutManager layout = new BorderLayout();
        this.frame.setLayout(layout);
        this.frame.setSize(new Dimension(300, 300));

        //Score label
        this.scoreLabel = new JLabel("Your Score: 0");
        this.scoreLabel.setForeground(Color.BLACK);
        //GridBagConstraints scoreConstraints = new GridBagConstraints();
        //scoreConstraints.gridx = 0; scoreConstraints.gridy = 0;

        this.frame.getContentPane().add(this.scoreLabel, BorderLayout.WEST);

        //Board display
        this.board = new BoardComponent(this.model.getBoard());
        //GridBagConstraints boardconstraints = new GridBagConstraints();
        //boardconstraints.gridx = 2; boardconstraints.gridy = 0;
        //boardconstraints.gridheight = 3;

        this.frame.getContentPane().add(this.board, BorderLayout.EAST);

        this.frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                return;
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_DOWN:
                        eventQueue.add(EventType.DOWN_ARROW);
                        break;
                    case KeyEvent.VK_LEFT:
                        eventQueue.add(EventType.LEFT_ARROW);
                        break;
                    case KeyEvent.VK_UP:
                        eventQueue.add(EventType.UP_ARROW);
                        break;
                    case KeyEvent.VK_RIGHT:
                        eventQueue.add(EventType.RIGHT_ARROW);
                        break;
                    case KeyEvent.VK_SPACE:
                        eventQueue.add(EventType.SPACE);
                        break;
                    case KeyEvent.VK_Q:
                        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                        eventQueue.add(EventType.CLOSE);
                        break;
                    case KeyEvent.VK_ENTER:
                        eventQueue.add(EventType.ENTER);
                        break;
                    default:
                        break;

                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                return;
            }
        });

        this.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("running handler");
                open = false;
                eventQueue.add(EventType.CLOSE);
            }
        });

        this.frame.pack();
        this.frame.setVisible(true);

    }

    @Override
    public void render() {
        this.scoreLabel.setText("Your score: " + model.getScore().getScore());

        this.frame.repaint();
    }

    @Override
    public void notifyClosing() {
        if (open) {
            this.frame.dispatchEvent(new WindowEvent(this.frame, WindowEvent.WINDOW_CLOSING));
        }
    }

    @Override
    public void close() {
        System.exit(0);
    }
}
