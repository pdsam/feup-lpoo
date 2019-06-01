package crackattack.model.board;

import crackattack.controller.board.BoardController;

import java.util.List;

public abstract class BoardObserver {
    protected Board board;
    protected BoardController controller;


    public abstract void update(List<Position> positions);
}
