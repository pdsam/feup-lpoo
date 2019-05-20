package controller;

import controller.BoardController;
import model.Board;
import model.Position;

public abstract class BoardObserver {
    protected Board board;
    protected BoardController controler;


    public abstract void update(Position p);
}
