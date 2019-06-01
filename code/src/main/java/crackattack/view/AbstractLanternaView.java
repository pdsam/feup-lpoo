package crackattack.view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import crackattack.Constants;

import java.io.IOException;

public abstract class AbstractLanternaView extends AbstractView {
    private static Terminal term;
    protected Screen context;
    protected TextGraphics graphics;
    private static LanternaInputThread inputListener;

    public static final int WIDTH = 80;
    public static final int HEIGHT = Constants.BOARD_ROWS * Constants.LANTERNA_BOARD_CELL_HEIGHT;

    public AbstractLanternaView() throws IOException {
        super();

        if (term == null) {
            term = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(WIDTH, HEIGHT)).createTerminal();
        }

        Screen screen = new TerminalScreen(term);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();

        context = screen;

        graphics = context.newTextGraphics();

        if (inputListener != null) {
            inputListener.terminate();
        }

        inputListener = new LanternaInputThread(this.context, this.eventQueue);
        inputListener.start();
    }

    @Override
    public abstract void render();

    @Override
    public void notifyClosing() {
        inputListener.terminate();
        try {
            inputListener.join();
        } catch (InterruptedException e) {
        }

        try {
            context.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        closing = true;
    }

    @Override
    public void close() {
        return;
    }
}
