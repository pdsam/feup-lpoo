package model;

import controller.BoardController;
import model.Board;
import model.Position;

import java.util.List;

public abstract class BoardObserver {
    protected Board board;
    protected BoardController controller;


    public abstract void update(List<Position> positions);
}
