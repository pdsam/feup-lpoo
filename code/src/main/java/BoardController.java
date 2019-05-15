import java.util.ArrayList;
import java.util.List;

public class BoardController {
    private Board board;
    private View boardView;


    public BoardController(View view, Board board) {
        this.boardView = view;
        this.board = board;
        this.board.attachObserver(new PhysicsBoardObserver(board,this));
    }

    public void run() {
        boardView.render();
        while (!boardView.shouldClose()) {
            boardView.processInput();
            boardView.render();
        }
    }



    private boolean inBoundaries(Position p){
        if(p.getY() <0 || p.getY() >= board.getMaxY()  || p.getX() < 0 || p.getX()>=board.getMaxX()  )
            return false;

        return true;
    }




    public void blockGravityUpdate(Position p){//que função jabarda
        if(!inBoundaries(p))
            return;

        if(board.getGridElement(p) != null) {
            if(p.getY() == board.getMaxY() -1)
                return;

            Position bottom = new Position(p.getX(),board.getMaxY()-1);

            while(board.getGridElement(bottom )!=null ){

                bottom.decrementY();


            }

            board.swap(p,bottom);
            blockGravityUpdate(new Position(p.getX(),p.getY()-1));

        }
        else{
            if(p.getY() != 0){
                Position aux = new Position(p.getX(),p.getY()-1);
                if(board.getGridElement(aux)!= null)
                    board.swap(p, aux);
                blockGravityUpdate(aux);

            }

        }

    }


    public void comboChecker(Position p){

    }

}
