package com.candycrush.main;

import java.awt.*;

public class Grid extends GameObject {
    public Grid(int x, int y, ID id) {
        super(x, y, id);
    }

    public void tick() {

    }

    public void render(Graphics g) {
        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                if ((i+j) % 2 == 0) {
                    g.setColor(Color.BLUE);
                } else {
                    g.setColor(Color.CYAN);
                }
                g.fillRoundRect(350+i*100,30+j*100,100,100,30,30);
            }
        }
    }
}
