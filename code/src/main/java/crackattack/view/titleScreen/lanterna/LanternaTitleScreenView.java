package crackattack.view.titleScreen.lanterna;

import com.googlecode.lanterna.TextColor;
import crackattack.Constants;
import crackattack.model.board.Color;
import crackattack.view.AbstractLanternaView;

import java.io.IOException;

public class LanternaTitleScreenView extends AbstractLanternaView {

    private Color[][] colorsPanel;

    private static final int NUM_HOR_TILES = Constants.TITLE_SCREEN_HOR_TILE_NUM;
    private static final int NUM_VER_TILES = Constants.TITLE_SCREEN_VER_TILE_NUM;

    private int tileWidth;
    private int tileHeight;

    private final String message = "Press Space";

    public LanternaTitleScreenView() throws IOException {
        super();

        this.tileWidth = WIDTH / NUM_HOR_TILES;
        this.tileHeight = HEIGHT / NUM_VER_TILES;

        colorsPanel = new Color[NUM_VER_TILES+1][NUM_HOR_TILES];
        for (int y = 0; y < NUM_VER_TILES+1; y++) {
            for (int x = 0; x < NUM_HOR_TILES; x++) {
                colorsPanel[y][x] = Color.randomColor();
            }
        }
    }

    private void paintTile(int x, int y) {
        graphics.setBackgroundColor(TextColor.Factory.fromString(colorsPanel[y][x].getColor()));
        for (int i = 0; i < tileHeight; i++) {
            for (int j = 0; j < tileWidth; j++) {
                graphics.putString(x*tileWidth+j, y*tileHeight+i, " ");
            }
        }
    }

    @Override
    public void render() {
        for (int y = 0; y < NUM_VER_TILES+1; y++) {
            for (int x = 0; x < NUM_HOR_TILES; x++) {
                paintTile(x,y);
            }
        }

        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(WIDTH/2 - message.length() /2, HEIGHT/2, message);

        try {
            context.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
