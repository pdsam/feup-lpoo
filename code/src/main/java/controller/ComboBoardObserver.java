package controller;

import model.BoardObserver;
import model.Board;
import model.Position;

public class ComboBoardObserver extends BoardObserver {
    public ComboBoardObserver(Board board, BoardController controller) {
        this.controller = controller;
        this.board = board;
    }

    @Override
    public void update(Position p) {
        controller.comboChecker(p);
    }
}
