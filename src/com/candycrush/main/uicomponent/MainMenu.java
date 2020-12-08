package com.candycrush.main.uicomponent;

import com.candycrush.main.Game;
import com.candycrush.main.handler.ObjectHandlerInterface;

import java.awt.*;
import java.awt.event.MouseAdapter;

public class MainMenu extends MouseAdapter implements ObjectHandlerInterface {
    private static final int WIDTH = Game.WIDTH;
    private static final int HEIGHT = Game.HEIGHT;
    private static final float HUE_MIN = 0;
    private static final float HUE_MAX = 1;
    private float hue = HUE_MIN;
    private Color color1 = Color.white;
    private Color color2 = Color.black;
    private final float colorDelta = 0.01f;
    private final int RADIUS_MAX = WIDTH;
    private int radiusDelta = 0;

    public MainMenu() {
        System.out.println("Main Menu started!");

    }

    public void tick() {
        // Color changing gradient
        hue += colorDelta / 20;
        if (hue > HUE_MAX) {
            hue = HUE_MIN;
        }
        color1 = Color.getHSBColor(hue, 1, 1);
        color2 = Color.getHSBColor(hue + 16 * colorDelta, 1, 1);

        // Pattern
        radiusDelta += 10;
        if (radiusDelta > RADIUS_MAX) {
            radiusDelta = 0;
        }
    }

    public void render(Graphics2D graphic) {
        // Draw backdrop gradient
        float[] dist = {0f, 1f};
        Color[] colors = {color1, color2};
        RadialGradientPaint gradient = new RadialGradientPaint((float) WIDTH / 2, (float) HEIGHT / 2, WIDTH, dist, colors);

        graphic.setPaint(gradient);
        graphic.fill(new Rectangle(WIDTH,HEIGHT));

        /*// Draw pattern
        Color color = new Color(1f, 1f, 1f, 0.5f);
        graphic.setColor(color);
        for (int i = 1; i <= 5; i++) {
            int x = (WIDTH - (RADIUS_MAX - radiusDelta * i)) / 2 ;
            int y = (HEIGHT - (RADIUS_MAX - radiusDelta * i)) / 2;
            graphic.setStroke(new BasicStroke(100));
            graphic.drawOval(x,y, RADIUS_MAX - radiusDelta * i, RADIUS_MAX - radiusDelta * i);

        }*/

    }
}
