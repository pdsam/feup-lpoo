package crackattack.controller.board;

import crackattack.controller.AbstractController;
import crackattack.controller.board.commands.*;

import crackattack.model.board.Board;
import crackattack.model.board.BoardModel;
import crackattack.model.board.BoardScore;
import crackattack.view.View;

public class BoardController extends AbstractController {
    private Board board;
    private View boardView;
    private BoardScore score;

    private long timeToNewLine;
    private long currentTime;
    private long lastRoot;
    public BoardController(View view, BoardModel model) {
        super();

        this.boardView = view;
        this.board = model.getBoard();
        this.score = model.getScore();

        this.board.attachObserver(new BoardChangeObserver(this.board, this.score));


        this.timeToNewLine = 14000;
        this.currentTime = 0;
        this.lastRoot = System.currentTimeMillis();
    }

    @Override
    public void tick() {
        long current = System.currentTimeMillis();
        currentTime = current - lastRoot;

        if (currentTime >= timeToNewLine) {
            currentTime -= timeToNewLine;
            if(timeToNewLine > 4000)
                timeToNewLine -= 2000;
            new NewLineCommand(board).exec();
            lastRoot = current;
        }

        if (NewLineCommand.lost) {
            boardView.notifyClosing();
            return;
        }

    }

    public void moveUp() {
        new MoveUpCommand(board).exec();
    }

    public void moveDown() {
        new MoveDownCommand(board).exec();
    }

    public void moveLeft() {
        new MoveLeftCommand(board).exec();
    }

    public void moveRight() {
        new MoveRightCommand(board).exec();
    }

    public void swap() {
        new SwapCommand(board).exec();
    }

    public void close() {
        boardView.notifyClosing();
    }

    public void requestNewLine() {
        new NewLineCommand(board).exec();
    }

}
