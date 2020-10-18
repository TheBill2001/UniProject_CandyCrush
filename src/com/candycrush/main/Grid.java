package com.candycrush.main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Grid extends GameObject {
    private final List<BufferedImage> texture = new ArrayList<>();
    private static final int SPRITE_ROWS = 3;
    private static final int SPRITE_COLUMNS = 3;
    private static final int ROW_OFFSET = 325;
    private static final int COLUMN_OFFSET = 5;

    public Grid(int x, int y, ID id) {
        super(x, y, id);

        BufferedImageLoader loader = new BufferedImageLoader();
        BufferedImage sprite = loader.loadImage("/texture/grid.png");
        SpriteSheet ss = new SpriteSheet(sprite);
        divideSprite(ss);
    }

    private void divideSprite(SpriteSheet ss) {
        for (int i = 1; i<=SPRITE_COLUMNS; i++) {
            for (int j=1; j<=SPRITE_ROWS; j++) {
                texture.add(ss.getImage(i,j,50,50));
            }
        }
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.drawImage(texture.get(0),ROW_OFFSET,COLUMN_OFFSET, null);
    }
}
