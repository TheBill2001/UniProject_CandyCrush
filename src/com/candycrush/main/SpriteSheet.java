package com.candycrush.main;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class SpriteSheet {
    private final BufferedImage sprite;
    private final int spriteColumn;
    private final int spriteRow;
    private final int cellHeight;
    private final int cellWidth;

    public SpriteSheet(BufferedImage ss, int spriteRow, int spriteColumn, int cellWidth, int cellHeight) {
        this.sprite = ss;
        this.spriteRow = spriteRow;
        this.spriteColumn = spriteColumn;
        this.cellHeight = cellHeight;
        this.cellWidth = cellWidth;
    }

    public BufferedImage getImage(int column, int row, int width, int height) {
        return sprite.getSubimage((row * width) - width, (column * height) - height, width, height);
    }

    public List<BufferedImage> divideSprite() {
        List<BufferedImage> texture = new ArrayList<>();
        for (int i = 1; i <= spriteRow; i++) {
            for (int j = 1; j <= spriteColumn; j++) {
                texture.add(getImage(i, j, cellWidth, cellHeight));
            }
        }
        return texture;
    }

}
