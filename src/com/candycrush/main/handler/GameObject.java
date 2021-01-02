package com.candycrush.main.handler;

import java.awt.*;

public class GameObject {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean enable = true;

    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void gotoXY(int x, int y, int tick) {

    }

    public void tick() {

    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isEnable() {
        return enable;
    }

    public void render(Graphics2D graphic) {

    }
}
