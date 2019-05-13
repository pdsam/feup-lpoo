public abstract class BoardObserver {
    protected Board board;
    protected BoardController controler;


    public abstract void update(Position p);
}
