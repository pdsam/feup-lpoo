import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class BarElement implements Drawable, GridElement{
    private Color color;
    private Bar bar;


    public BarElement(Bar bar) {
        this.bar = bar;
        this.color = Color.BAR;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void draw(TextGraphics graphics, int x, int y) {
        graphics.setBackgroundColor(TextColor.Factory.fromString(color.getColor()));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(x,y," ");
    }

    @Override
    public void deleted() {
        bar.notifyDestroyed();
    }
}
