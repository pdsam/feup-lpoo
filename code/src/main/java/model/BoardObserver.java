package model;

import controller.BoardController;
import model.Board;
import model.Position;

public abstract class BoardObserver {
    protected Board board;
    protected BoardController controller;


    public abstract void update(Position p);
}
