package controller;

import model.BoardObserver;
import model.Board;
import model.Position;

public class PhysicsBoardObserver extends BoardObserver {

    public PhysicsBoardObserver(Board board, BoardController controller) {
        this.controller = controller;
        this.board = board;
    }

    @Override
    public void update(Position p) {
        if(p.getY() < board.getMaxY()) {
            //if (board.getGridElement(new model.Position(p.getX(), p.getY() + 1)) == null)
                controller.blockGravityUpdate(p);

        }

    }
}
