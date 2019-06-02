package crackattack.controller.board;

import crackattack.model.board.Board;
import crackattack.model.board.Color;
import crackattack.model.board.Position;

import java.util.ArrayList;
import java.util.List;

public class BoardComboChecker {
    private Board board;

    public BoardComboChecker(Board board) {
        this.board = board;
    }

    private boolean inBoundaries(Position p){
        return p.getY() >= 0 && p.getY() < board.getMaxY() && p.getX() >= 0 && p.getX() < board.getMaxX();
    }

    public ArrayList<Position> checkCombos(List<Position> toCheck) {
        ArrayList<Position> allPositions = new ArrayList<>();

        for (Position p :toCheck) {
            if(board.getGridElement(p)== null)
                continue;

            Color origin = board.getGridElement(p).getColor();

            //vertical
            List<Position> verticalPositions = new ArrayList<>();
            int offset = 1;
            while(true) {
                Position temp = new Position(p.getX(), p.getY() + offset);
                if (inBoundaries(temp) && board.getGridElement(temp)!= null) {
                    if (origin == board.getGridElement(temp).getColor()) {
                        verticalPositions.add(temp);
                        offset++;
                    } else
                        break;
                } else
                    break;
            }

            offset = 1;
            while(true) {
                Position temp = new Position(p.getX(), p.getY() - offset);
                if (inBoundaries(temp) && board.getGridElement(temp)!= null) {
                    if (origin == board.getGridElement(temp).getColor()) {
                        verticalPositions.add(temp);
                        offset++;
                    } else
                        break;
                } else
                    break;
            }
            offset = 1;

            //horizontal
            List<Position> horizontalPositions = new ArrayList<>();
            while(true) {
                Position temp = new Position(p.getX() + offset, p.getY());
                if (inBoundaries(temp) && board.getGridElement(temp)!= null) {
                    if (origin == board.getGridElement(temp).getColor()) {
                        horizontalPositions.add(temp);
                        offset++;
                    } else
                        break;
                } else
                    break;
            }

            offset = 1;
            while(true) {
                Position temp = new Position(p.getX() - offset, p.getY());
                if (inBoundaries(temp)&& board.getGridElement(temp)!= null) {
                    if (origin == board.getGridElement(temp).getColor()) {
                        horizontalPositions.add(temp);
                        offset++;
                    } else
                        break;
                } else
                    break;
            }

            if (verticalPositions.size() >= 2 || horizontalPositions.size() >= 2) {
                allPositions.add(p);
            }

            if (verticalPositions.size() >= 2) {
                allPositions.addAll(verticalPositions);
            }

            if (horizontalPositions.size() >= 2) {
                allPositions.addAll(horizontalPositions);
            }
        }
        return allPositions;
    }
}
