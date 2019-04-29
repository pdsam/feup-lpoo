public class Swap implements Command {

    private Board board;

    public Swap(Board board) {
        this.board = board;

    }

    @Override
    public void exec(){
            Position p1 = board.getSelector();
            GridElement g1 = board.getGridElement(p1);
            Position p2 = p1;
            p1.setX(p1.getX()+1);

            GridElement g2 = board.getGridElement(p2);

            if (g1 != null || g2 != null) {
                board.setGridElements(p1,g2);
                board.setGridElements(p2,g1);

        }

    }
}
