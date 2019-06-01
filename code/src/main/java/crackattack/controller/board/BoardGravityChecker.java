package crackattack.controller.board;

import crackattack.model.board.Board;
import crackattack.model.board.Position;

import java.util.ArrayList;
import java.util.List;

public class BoardGravityChecker {
    private Board board;

    public BoardGravityChecker(Board board) {
        this.board = board;
    }

    public ArrayList<Position> updateGravity(List<Position> toCheck) {
        ArrayList<Position> positions = new ArrayList<>();

        for (Position p: toCheck) {
            Position lastNull = null;
            int y = board.getMaxY() - 1;
            for (; y >= 0; y--) {
                Position temp = new Position(p.getX(), y);
                if (board.getGridElement(temp) == null) {
                    lastNull = temp;
                    break;
                }
            }

            y--;
            for (; y >= 0; y--) {
                Position temp = new Position(p.getX(), y);
                if (board.getGridElement(temp) != null) {
                    board.swap(temp, lastNull);
                    positions.add(new Position(lastNull.getX(), lastNull.getY()));
                    lastNull.decrementY();
                }
            }
        }

        return positions;
    }
}
