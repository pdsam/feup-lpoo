package crackattack.view;

import crackattack.Constants;
import crackattack.events.EventType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public abstract class AbstractSwingView extends AbstractView {

    protected static JFrame frame;
    private static boolean open;
    private static KeyListener currentKeyListener;
    private static WindowAdapter currentWindowAdapter;

    public static final int HEIGHT = Constants.BOARD_ROWS * Constants.SWING_BOARD_CELL_HEIGHT;
    public static final int WIDTH = Constants.BOARD_COLUMNS * Constants.SWING_BOARD_CELL_WIDHT + 200;

    public AbstractSwingView() {
        super();
        if (frame == null) {
            open = true;

            frame = new JFrame("Crack Attack");
            frame.setResizable(false);
            frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));

            frame.addComponentListener(new ComponentAdapter() {
                public void componentResized(ComponentEvent evt) {
                    Dimension size = frame.getSize();
                    Dimension min = frame.getMinimumSize();
                    if (size.getWidth() < min.getWidth()) {
                        frame.setSize((int) min.getWidth(), (int) size.getHeight());
                    }
                    if (size.getHeight() < min.getHeight()) {
                        frame.setSize((int) size.getWidth(), (int) min.getHeight());
                    }
                }
            });
        } else {
            frame.getContentPane().removeAll();
            frame.removeKeyListener(currentKeyListener);
            frame.removeWindowListener(currentWindowAdapter);
        }

        currentKeyListener = new KeyListener() {
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
                        eventQueue.add(EventType.KEY_Q);
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
        };

        frame.addKeyListener(currentKeyListener);

        currentWindowAdapter = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("running handler");
                open = false;
                closing = true;
                eventQueue.add(EventType.CLOSE);
            }
        };

        frame.addWindowListener(currentWindowAdapter);
    }

    @Override
    public abstract void render();

    @Override
    public void notifyClosing() {
        System.out.println("being notified");
        if (open) {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
    }

    @Override
    public void close() {
        System.exit(0);
    }
}
