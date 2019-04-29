import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Block implements GridElement {
    private Color color;

    public Block() {
        color = Color.randomColor();
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
        return;
    }

    @Override
    public void draw(TextGraphics graphics, int x, int y) {
        graphics.setBackgroundColor(TextColor.Factory.fromString(color.getColor()));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(x,y," ");
    }
}
