package controller;

import model.Board;
import model.BoardObserver;
import model.BoardScore;
import model.Position;

import java.util.ArrayList;
import java.util.List;

public class BoardChangeObserver extends BoardObserver {

    private Board board;
    private BoardScore score;

    public BoardChangeObserver(Board board, BoardScore score) {
        this.board = board;
        this.score = score;
    }

    @Override
    public void update(List<Position> positions) {

        int addedScore = 0;
        int multiplier = 0;

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
            multiplier++;
            toCheckCombo.addAll(gravityChecker.updateGravity(toCheckGravity));

            toBreak = comboChecker.checkCombos(toCheckCombo);

            addedScore += toBreak.size()*multiplier;
        } while ((toCheckGravity = elementBreaker.breakElements(toBreak)) != null);

        this.score.increase(addedScore);
    }
}
