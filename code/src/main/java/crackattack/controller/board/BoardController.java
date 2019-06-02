package crackattack.controller.board;

import crackattack.controller.AbstractController;
import crackattack.controller.board.commands.*;

import crackattack.events.EventDispatcher;
import crackattack.events.EventType;
import crackattack.model.board.Board;
import crackattack.model.board.BoardModel;
import crackattack.model.board.BoardScore;
import crackattack.model.board.NextLineTime;

public class BoardController extends AbstractController {
    private Board board;
    private BoardScore score;
    private NextLineTime time;

    private final int INITIAL_LINE_TIME = 14000;

    private long timeToNewLine;
    private long currentTime;
    private long last;
    public BoardController(EventDispatcher dispatcher, BoardModel model) {
        super(dispatcher);

        this.board = model.getBoard();
        this.score = model.getScore();
        this.time = model.getTimeToNextLine();

        this.board.attachObserver(new BoardChangeObserver(this.board, this.score));


        this.timeToNewLine = INITIAL_LINE_TIME;
        this.currentTime = INITIAL_LINE_TIME;
        this.last = System.currentTimeMillis();
    }

    @Override
    public void tick() {
        long current = System.currentTimeMillis();

        currentTime -= current - last;

        last = current;

        if (currentTime <= 0) {
            if(timeToNewLine > 4000)
                timeToNewLine -= 2000;

            currentTime += timeToNewLine;

            NewLineCommand nlCommand = new NewLineCommand(board);
            nlCommand.exec();

            if (nlCommand.lost()) {
                dispatcher.dispatchEvent(EventType.LOST);
            }
        }

        time.setTime(currentTime);
    }
}
