import java.util.ArrayList;
import java.util.List;

public class BoardController {
    private Board board;
    private View boardView;


    public BoardController(View view, Board board) {
        this.boardView = view;
        this.board = board;
    }

    public void run() {
        boardView.render();
        while (!boardView.shouldClose()) {
            boardView.processInput();
            boardView.render();
        }
    }



    public void blockGravityUpdate(Position p){
        /*for(int i = 1;i < board.getMaxY() -1 - p.getY();i++){
            if(board.getGridElement(new Position(p.getX(),p.getY() +1) ) != null)
                board.setGridElements(new Position(p.getX(),p.getY() +i -1), board.getGridElement(p));
            board.setGridElements(p, null);
                return;
        }

        */
    }


    public void comboChecker(Position p){

    }

}
