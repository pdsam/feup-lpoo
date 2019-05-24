package controller;

import model.Board;
import model.Position;

import java.util.ArrayList;
import java.util.List;

public class BoardElementBreaker {

    private Board board;

    public BoardElementBreaker(Board board) {
        this.board = board;
    }

    public ArrayList<Position> breakElements(List<Position> p){
        if (p.size() == 0) {
            return null;
        }

        for(Position tmp : p){
            board.setGridElements(tmp, null);
        }

        return new ArrayList<>(p);
    }
}
