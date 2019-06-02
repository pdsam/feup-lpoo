package crackattack.view.titleScreen.swing;

import crackattack.Constants;
import crackattack.model.board.Color;
import crackattack.view.AbstractSwingView;

import javax.swing.*;
import java.awt.*;

public class TitleScreenComponent extends JComponent {

    private Color[][] colorsPanel;

    private static final int NUM_HOR_TILES = Constants.TITLE_SCREEN_HOR_TILE_NUM;
    private static final int NUM_VER_TILES = Constants.TITLE_SCREEN_VER_TILE_NUM;

    private int tileHeight;
    private int tileWidth;

    public TitleScreenComponent() {

        this.tileHeight = AbstractSwingView.HEIGHT / NUM_VER_TILES;
        this.tileWidth = AbstractSwingView.WIDTH / NUM_HOR_TILES;

        colorsPanel = new Color[NUM_VER_TILES][NUM_HOR_TILES];

        for (int y = 0; y < NUM_VER_TILES; y++) {
            for (int x = 0; x < NUM_HOR_TILES; x++) {
                colorsPanel[y][x] = Color.randomColor();
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(AbstractSwingView.WIDTH, AbstractSwingView.HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int currX = 0;
        int currY = 0;
        for (int y = 0; y < NUM_VER_TILES; y++) {
            for (int x = 0; x < NUM_HOR_TILES; x++) {
                g.setColor(new java.awt.Color(colorsPanel[y][x].getRGB()));
                g.fillRect(currX, currY, tileWidth, tileHeight);
                currX += tileWidth;
            }
            currX = 0;
            currY += tileHeight;
        }
    }
}
