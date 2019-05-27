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

    private boolean running;

    public BoardController(View view, BoardModel model) {

        this.running = true;

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
    }

    public void run() {
        long timeToNewLine = 14000;

        long lastRoot = System.currentTimeMillis();
        long currentTime;

        boardView.render();

        while (running && !NewLineCommand.lost) {
            EventType currentEvent;
            while ((currentEvent = boardView.pollEvents()) != null) {
                callbackMap.get(currentEvent).run();
            }


            long current = System.currentTimeMillis();
            currentTime = current-lastRoot;

            if (currentTime >= timeToNewLine) {
                currentTime %= timeToNewLine;
                if(timeToNewLine > 4000)
                    timeToNewLine -= 2000;
                new NewLineCommand(board).exec();
                lastRoot = current;
            }

            boardView.render();
        }
        System.out.println("loop end");

        System.out.println("Controller signing out.");
        boardView.close();
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
        running = false;
        boardView.notifyClosing();
    }

    public void requestNewLine() {
        new NewLineCommand(board).exec();
    }

}
