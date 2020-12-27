package com.candycrush.main.handler;

import java.awt.*;

public interface GameObject {
    int getX();
    int getY();
    int getWidth();
    int getHeight();

    void tick();

    void render(Graphics2D graphic);
}
