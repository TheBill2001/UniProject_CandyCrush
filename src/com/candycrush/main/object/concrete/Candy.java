package com.candycrush.main.object.concrete;

import com.candycrush.main.CandiesID;
import com.candycrush.main.handler.CandiesHandler;
import com.candycrush.main.object.abstraction.GameObject;

import java.awt.*;

public class Candy extends GameObject implements Comparable<Candy> {
    private final CandiesID id;
    private final CandiesHandler handler;
    private int destX;
    private int destY;
    private int animX;
    private int animY;
    private boolean moving = false;

    public Candy(int x, int y, CandiesID id, CandiesHandler handler) {
        super(x, y, 100, 100);
        this.id = id;
        destX = x;
        destY = y;
        animX = x;
        animY = y;
        this.handler = handler;
    }

    public void gotoXY(int x, int y) {
        this.x = x;
        this.y = y;
        destX = x;
        destY = y;
        moving = true;
    }

    public boolean isMoving() {
        return moving;
    }

    @Override
    public void tick() {
        if (animX != destX) {
            if (animX > destX)
                animX -= 1;
            else
                animX += 1;
        }
        if (animY != destY) {
            if (animY > destY)
                animY -= 1;
            else
                animY += 1;
        }
        if (animX == destX && animY == destY)
            moving = false;
    }

    @Override
    public void render(Graphics2D graphic) {
        int[][] gridData = handler.getGrid();
        if (gridData[(animY + 70) / 100][(animX - 350) / 100] != 2 && (animY + 70) / 100 != 0)
            graphic.drawImage(id.Texture, animX, animY, width, height, null);
    }


    @Override
    public int compareTo(Candy candy) {
        if (x < candy.x) {
            if (y > candy.y)
                return -1;
            else
                return 1;
        } else if (x > candy.x) {
            return 1;
        }
        return 0;
    }
}
