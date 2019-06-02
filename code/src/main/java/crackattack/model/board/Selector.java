package crackattack.model.board;

public class Selector {
    private Position pos;

    public Selector(Position pos) {
        this.pos = pos;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }
}
