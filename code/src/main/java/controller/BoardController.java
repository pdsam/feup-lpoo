package controller;

import controller.commands.*;
import model.Block;
import model.Board;
import model.Position;
import view.Callback;
import view.EventType;

import view.View;
import java.util.EnumMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class BoardController implements Controller{
    private Board board;
    private View boardView;

    private Map<EventType, Callback> callbackMap;

    public BoardController(View view, Board board) {
        this.boardView = view;
        this.board = board;

        this.board.attachObserver(new BoardChangeObserver(board));

        this.callbackMap = new EnumMap<>(EventType.class);

        callbackMap.put(EventType.UP_ARROW, this::moveUp);
        callbackMap.put(EventType.DOWN_ARROW, this::moveDown);
        callbackMap.put(EventType.LEFT_ARROW, this::moveLeft);
        callbackMap.put(EventType.RIGHT_ARROW, this::moveRight);
        callbackMap.put(EventType.SPACE, this::swap);
        callbackMap.put(EventType.CLOSE, this::close);
        callbackMap.put(EventType.ENTER, this::requestNewLine);
    }

    public void run() {

        LineTimerThread timer = new LineTimerThread("controller.LineTimerThread", board);
        timer.start();

        boardView.render();
        while (!boardView.shouldClose()) {
            EventType currentEvent;
            while ((currentEvent = boardView.pollEvents()) != null) {
                callbackMap.get(currentEvent).run();
            }
            boardView.render();
        }

        timer.termminate();
        try {
            timer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
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
        boardView.close();
    }

    public void requestNewLine() {
        new NewLineCommand(board).exec();
    }

}
