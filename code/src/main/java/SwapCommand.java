import java.util.ArrayList;
import java.util.List;

public class SwapCommand implements Command {

    private Board board;

    public SwapCommand(Board board) {
        this.board = board;

    }

    @Override
    public void exec(){
        Position p1 = board.getSelector().getPos();
        Position p2 = new Position(p1.getX()+1, p1.getY());

        board.swap(p1,p2);
        List<Position> p = new ArrayList<>();

        p.add(p1);
        p.add(p2);

        board.notifyObserver(p);
    }
}
