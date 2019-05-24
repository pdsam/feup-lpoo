package controller;

import controller.commands.NewLineCommand;
import model.Board;

public class LineTimerThread extends Thread {
    private boolean running;
    private Board board;
    private long currentTime;

    public LineTimerThread(String name, Board board) {
        super(name);
        this.running = false;
        this.board = board;
    }

    @Override
    public synchronized void start() {
        running = true;
        super.start();
    }

    @Override
    public void run() {
        long lastRoot = System.nanoTime() / 1000000;
        long currentTime;

        while (running) {
            long current = System.nanoTime() / 1000000;
            currentTime = current-lastRoot;

            if (currentTime >= 15000) {
                currentTime %= 15000;
                new NewLineCommand(board).exec();
                lastRoot = current;
            }
        }
    }

    public void termminate() {
        running = false;
    }
}
