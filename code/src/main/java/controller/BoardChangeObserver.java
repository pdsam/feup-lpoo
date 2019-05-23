package controller;

import model.Board;
import model.BoardObserver;
import model.Position;

import java.util.ArrayList;
import java.util.List;

public class BoardChangeObserver extends BoardObserver {

    private Board board;

    public BoardChangeObserver(Board board) {
        this.board = board;
    }

    @Override
    public void update(List<Position> positions) {

        BoardGravityChecker gravityChecker = new BoardGravityChecker(board);
        BoardComboChecker comboChecker = new BoardComboChecker(board);
        BoardElementBreaker elementBreaker = new BoardElementBreaker(board);

        List<Position> toCheckGravity = new ArrayList<>();
        List<Position> toCheckCombo = new ArrayList<>();
        for (Position p: positions) {
            if (board.getGridElement(p) == null) {
                toCheckGravity.add(p);
                continue;
            }
            if (p.getY() < board.getMaxY() - 1) {
                if (board.getGridElement(new Position(p.getX(), p.getY()+1)) == null) {
                    toCheckGravity.add(p);
                } else {
                    toCheckCombo.add(p);
                }
            } else if (p.getY() == board.getMaxY() - 1) {
                toCheckCombo.add(p);
            }
        }

        List<Position> toBreak;

        do {
            toCheckCombo.addAll(gravityChecker.updateGravity(toCheckGravity));

            toBreak = comboChecker.checkCombos(toCheckCombo);
        } while ((toCheckGravity = elementBreaker.breakElements(toBreak)) != null);
    }
}
