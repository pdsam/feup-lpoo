package model;


import java.util.ArrayList;

public class BoardGeneratorNoAdjacency implements BoardContentGenerator {


    @Override
    public void fillBoard(GridElement[][] board) {
        Color lastColor = Color.YELLOW;
        ArrayList<Color> lastLine = new ArrayList<>();
        for (int i = board.length / 2; i < board.length /2 +1; i++) {
            for (int j = 0; j < board[i].length; j++) {
                Block temp = new Block();
                while (temp.getColor().equals(lastColor)) {
                    temp = new Block();
                }
                board[i][j] = temp;
                lastColor = temp.getColor();
                lastLine.add(lastColor);
            }

        }
        for (int i = board.length / 2 + 1; i < board.length; i++) {
            ArrayList<Color> aux = new ArrayList<>();
            for (int j = 0; j < board[i].length; j++) {
                Block temp = new Block();

                while (temp.getColor().equals(lastColor) || lastLine.get(j).equals(temp.getColor())) {
                    temp = new Block();
                }

                board[i][j] = temp;
                lastColor = temp.getColor();
                aux.add(temp.getColor());

            }
            lastLine = aux;
        }
    }
}