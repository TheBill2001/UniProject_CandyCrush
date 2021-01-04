package com.candycrush.main.object.concrete;

import com.candycrush.main.CandiesID;
import com.candycrush.main.handler.CandiesHandler;
import com.candycrush.main.object.abstraction.GameObject;

import java.awt.*;

public class Candy extends GameObject implements Comparable<Candy> {
    private final CandiesID id;
    private int dX;
    private int dY;
    private boolean moving = false;
    private final CandiesHandler handler;

    public Candy(int x, int y, CandiesID id, CandiesHandler handler) {
        super(x, y, 100, 100);
        this.id = id;
        dX = x;
        dY = y;
        this.handler = handler;
    }

    public void gotoXY(int x, int y) {
        dX = x;
        dY = y;
        moving = true;
    }

    public boolean isMoving() {
        return moving;
    }

    @Override
    public void tick() {
        if (x != dX) {
            if (x > dX)
                x -= 10;
            else
                x += 10;
        }
        if (y != dY) {
            if (y > dY)
                y -= 10;
            else
                y += 10;
        }
        if (x == dX && y == dY)
            moving = false;
    }

    @Override
    public void render(Graphics2D graphic) {
        if (handler.getGrid()[(y+70)/100][(x - 350)/100] != 3 && (y+70)/100 != 0)
            graphic.drawImage(id.Texture,x,y,width,height,null);
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
