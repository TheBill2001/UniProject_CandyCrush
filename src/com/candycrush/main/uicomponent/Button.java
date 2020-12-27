package com.candycrush.main.uicomponent;

import com.candycrush.main.handler.Clickable;

import java.awt.*;

public class Button implements Clickable {
    private Color color = Color.BLACK;

    @Override
    public void mouseClicked() {
        System.out.println("Ding!");
        color = Color.BLUE;
    }

    @Override
    public int getX() {
        return 100;
    }

    @Override
    public int getY() {
        return 100;
    }

    @Override
    public int getWidth() {
        return 100;
    }

    @Override
    public int getHeight() {
        return 100;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics2D graphic) {
        graphic.setColor(color);
        graphic.fillRect(100,100,100,100);
    }
}
