import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class Application {
    public static void main(String[] args) throws Exception {
        Terminal term = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(term);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();

        Board b = new Board();

        b.draw(screen.newTextGraphics(),0,0);
        screen.refresh();

    }


}
