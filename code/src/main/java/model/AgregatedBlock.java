package model;

public class AgregatedBlock implements GridElement {
    Color color;
    public AgregatedBlock() {
        this.color =Color.RED;
    }

    @Override
    public void deleted() {

    }

    @Override
    public Color getColor() {
        return null;
    }
}
