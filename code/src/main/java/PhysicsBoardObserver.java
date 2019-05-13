public class PhysicsBoardObserver extends BoardObserver {

    public PhysicsBoardObserver(Board board, BoardController controller) {
        this.controler = controller;
        this.board = board;
        this.board.attachObserver(this);
    }

    @Override
    public void update(Position p) {
        if(board.getGridElement(new Position(p.getX(),p.getY()+1)) == null){
            controler.blockGravityUpdate(p);
        }


    }
}
