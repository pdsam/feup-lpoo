package controller;

import controller.BoardController;
import controller.BoardObserver;
import model.Board;
import model.Position;

public class ComboBoardObserver extends BoardObserver {
    public ComboBoardObserver(Board board, BoardController controller) {
        this.controler = controller;
        this.board = board;
    }

    @Override
    public void update(Position p) {
        controler.comboChecker(p);
    }
}
