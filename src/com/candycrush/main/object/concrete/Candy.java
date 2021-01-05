package com.candycrush.main.object.concrete;

import com.candycrush.main.CandiesID;
import com.candycrush.main.handler.LevelHandler;
import com.candycrush.main.object.abstraction.GameObject;

import java.awt.*;

public class Candy extends GameObject {
    private final CandiesID id;
    private final LevelHandler handler;
    private int animX;
    private int animY;

    public Candy(int x, int y, CandiesID id, LevelHandler handler) {
        super(x, y, 100, 100);
        this.id = id;
        animX = x;
        animY = y;
        this.handler = handler;
    }

    public void gotoXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isMoving() {
        return !(animX == x && animY == y);
    }

    public CandiesID getId() {
        return id;
    }

    public static void swap(Candy c1, Candy c2) {
        int x1 = c1.getX();
        int y1 = c1.getY();
        int x2 = c2.getX();
        int y2 = c2.getY();

        c1.gotoXY(x2,y2);
        c2.gotoXY(x1,y1);
    }

    @Override
    public void tick() {
        if (animX != x) {
            if (animX > x)
                animX -= 10;
            else
                animX += 10;
        }
        if (animY != y) {
            if (animY > y)
                animY -= 10;
            else
                animY += 10;
        }
    }

    @Override
    public void render(Graphics2D graphic) {
        int[][] gridData = handler.getGrid();
        if (gridData[(animY + 70) / 100][(animX - 350) / 100] != 2 && (animY + 70) / 100 != 0)
            graphic.drawImage(id.Texture, animX, animY, width, height, null);
    }
}
