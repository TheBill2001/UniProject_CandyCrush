package com.candycrush.main.handler;

public abstract class Clickable extends GameObject {
    public Clickable(int x, int y, int width, int height) {
        super(x,y,width,height);
    }

    public abstract void mousePressed ();

    public abstract void mouseReleased();

    public abstract void mouseHover();

    public abstract void mouseReset();
}
