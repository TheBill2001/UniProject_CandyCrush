package com.candycrush.main;

import java.awt.*;

public class Grid extends GameObject {
    public Grid(int x, int y, ID id) {
        super(x, y, id);
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(x-1, y-1, 902, 902);

        g.setColor(Color.WHITE);
        int tempX = x;
        int tempY = y;
        int line = 2;
        int tile = 100;
        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                g.fillRect(tempX + line, tempY + line, tile - 2 * line, tile - 2 * line);
                tempX += tile;
            }
            tempX = x;
            tempY += tile;
        }
    }
}
