package com.candycrush.main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Grid extends GameObject {
    private final List<BufferedImage> texture = new ArrayList<>();
    private static final int SPRITE_ROWS = 3;
    private static final int SPRITE_COLUMNS = 3;
    private final int ROW_OFFSET;
    private final int COLUMN_OFFSET;
    private final int LEVEL_NUM;
    private LevelReader levelReader = new LevelReader();
    private boolean[][] grid;

    public Grid(int x, int y, ID id, int level) {
        super(x, y, id);
        this.ROW_OFFSET = x;
        this.COLUMN_OFFSET = y;
        this.LEVEL_NUM = level;

        BufferedImageLoader loader = new BufferedImageLoader();
        BufferedImage sprite = loader.loadImage("/texture/grid.png");
        SpriteSheet ss = new SpriteSheet(sprite);
        divideSprite(ss);

        grid = levelReader.getLevelGrid(LEVEL_NUM);
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
        for (int i = 0 ; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boolean even = i % 2 == j % 2;
                if (grid[i][j]) {

                } else {

                }
            }
        }

    }
}
