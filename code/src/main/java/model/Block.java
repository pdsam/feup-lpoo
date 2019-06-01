package model;

public class Block implements GridElement {
    private Color color;

    public Block() {
        color = Color.randomColor();
    }


    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return color == block.color;
    }

    @Override
    public void deleted() {
    }

}
