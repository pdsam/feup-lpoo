import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import controller.BoardController;
import model.Board;
import model.TestBoardGenerator;
import view.LanternaBoardView;
import view.View;

public class Application {

    public static void main(String[] args) throws Exception {
        Terminal term = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(term);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();

        Board b = new Board(new TestBoardGenerator());
        View v = new LanternaBoardView(screen, b);
        BoardController bC = new BoardController(v, b);

        bC.run();
    }

}
