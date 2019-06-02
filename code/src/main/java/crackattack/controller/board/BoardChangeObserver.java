package crackattack.controller.board;

import crackattack.model.board.Board;
import crackattack.model.board.BoardObserver;
import crackattack.model.board.BoardScore;
import crackattack.model.board.Position;

import java.util.ArrayList;
import java.util.List;

public class BoardChangeObserver extends BoardObserver {

    private Board board;
    private BoardScore score;

    private BoardGravityChecker gravityChecker;
    private BoardComboChecker comboChecker;
    private BoardElementBreaker elementBreaker;

    public BoardChangeObserver(Board board, BoardScore score) {
        this(board, score, new BoardGravityChecker(board), new BoardComboChecker(board), new BoardElementBreaker(board));
    }

    public BoardChangeObserver(Board board, BoardScore score, BoardGravityChecker gravityChecker, BoardComboChecker comboChecker, BoardElementBreaker elementBreaker) {
        this.board = board;
        this.score = score;
        this.gravityChecker = gravityChecker;
        this.comboChecker = comboChecker;
        this.elementBreaker = elementBreaker;
    }

    @Override
    public void update(List<Position> positions) {

        int addedScore = 0;
        int multiplier = 0;


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
            toCheckCombo = new ArrayList<>();

            addedScore += toBreak.size()*multiplier;
        } while ((toCheckGravity = elementBreaker.breakElements(toBreak)) != null);

        this.score.increase(addedScore);
    }
}
