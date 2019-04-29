public class SwapCommand implements Command {

    private Board board;

    public SwapCommand(Board board) {
        this.board = board;

    }

    @Override
    public void exec(){
        Position p1 = board.getSelector();
        Position p2 = new Position(p1.getX()+1, p1.getY());

        GridElement g1 = board.getGridElement(p1);
        GridElement g2 = board.getGridElement(p2);

        if (g1 != null || g2 != null) {
            board.setGridElements(p1,g2);
            board.setGridElements(p2,g1);
        }

    }
}
