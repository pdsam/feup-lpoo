import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class BoardController {
    private Screen context;
    private Board board;
    boolean shouldClose = false;

    public BoardController(Screen context, Board board) {
        this.context = context;
        this.board = board;
    }

    public void run() throws Exception{
        board.draw(context.newTextGraphics(), 0, 0);
        context.refresh();
        while (!shouldClose) {
            processInput();
            board.draw(context.newTextGraphics(), 0, 0);
            context.refresh();
        }
    }

    public void processInput() {
        KeyStroke kS = null;
        try {
            kS = context.readInput();

            switch (kS.getKeyType()) {
                case ArrowDown:
                    board.moveSelector(0);
                    break;
                case ArrowLeft:
                    board.moveSelector(1);
                    break;
                case ArrowUp:
                    board.moveSelector(2);
                    break;
                case ArrowRight:
                    board.moveSelector(3);
                    break;
                case Character:
                    if (kS.getCharacter() == ' ') {
                        new SwapCommand(board).exec();
                    } else if (kS.getCharacter() == 'q') {
                        shouldClose = true;
                    }
                    break;
                case EOF:
                    shouldClose = true;
                    break;
                default:break;
            }
        } catch (IOException e) {

        }
    }
}
