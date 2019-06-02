package crackattack.model.board;

public class Block implements GridElement {
    private Color color;

    public Block() {
        color = Color.randomColor();
    }

    public Block(Color color) {
        this.color = color;
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

}
