package com.candycrush.main;

import java.awt.image.BufferedImage;

public class SpriteSheet {
    private BufferedImage sprite;

    public SpriteSheet(BufferedImage ss) {
        this.sprite = ss;
    }

    public BufferedImage getImage(int column, int row, int width, int height) {
        BufferedImage image = sprite.getSubimage((row * 50) - 50, (column * 50) - 50, width, height);
        return image;
    }
}
