package controller;

import controller.commands.*;
import model.*;
import view.Callback;
import view.EventType;

import view.View;
import java.util.EnumMap;
import java.util.Map;

public class BoardController implements Controller{
    private Board board;
    private View boardView;
    private BoardScore score;

    private Map<EventType, Callback> callbackMap;

    private long timeToNewLine;
    private long currentTime;
    private long lastRoot;
    public BoardController(View view, BoardModel model) {

        this.boardView = view;
        this.board = model.getBoard();
        this.score = model.getScore();

        this.board.attachObserver(new BoardChangeObserver(this.board, this.score));

        this.callbackMap = new EnumMap<>(EventType.class);

        callbackMap.put(EventType.UP_ARROW, this::moveUp);
        callbackMap.put(EventType.DOWN_ARROW, this::moveDown);
        callbackMap.put(EventType.LEFT_ARROW, this::moveLeft);
        callbackMap.put(EventType.RIGHT_ARROW, this::moveRight);
        callbackMap.put(EventType.SPACE, this::swap);
        callbackMap.put(EventType.CLOSE, this::close);
        callbackMap.put(EventType.ENTER, this::requestNewLine);

        this.timeToNewLine = 14000;
        this.currentTime = 0;
        this.lastRoot = System.currentTimeMillis();
    }

    @Override
    public void tick() {
        EventType currentEvent;
        while ((currentEvent = boardView.pollEvents()) != null) {
            callbackMap.get(currentEvent).run();
        }


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

        boardView.render();
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
