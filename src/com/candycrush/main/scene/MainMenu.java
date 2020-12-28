package com.candycrush.main.scene;

import com.candycrush.main.Game;
import com.candycrush.main.handler.GameObject;

import java.awt.*;
import java.awt.event.MouseAdapter;

public class MainMenu extends GameObject {
    private static final int WIDTH = Game.WIDTH;
    private static final int HEIGHT = Game.HEIGHT;
    private static final float HUE_MIN = 0;
    private static final float HUE_MAX = 1;
    private final float colorDelta = 0.01f;
    private float hue = HUE_MIN;
    private Color color1 = Color.white;
    private Color color2 = Color.black;

    public MainMenu() {
        super(0,0,0,0);
        System.out.println("Main Menu started!");

    }

    @Override
    public void tick() {
        // Color changing gradient
        hue += colorDelta / 20;
        if (hue > HUE_MAX) {
            hue = HUE_MIN;
        }
        color1 = Color.getHSBColor(hue, 1, 1);
        color2 = Color.getHSBColor(hue + 16 * colorDelta, 1, 1);
    }

    @Override
    public void render(Graphics2D graphic) {
        // Draw backdrop gradient
        float[] dist = {0f, 1f};
        Color[] colors = {color1, color2};
        RadialGradientPaint gradient = new RadialGradientPaint((float) WIDTH / 2, (float) HEIGHT / 2, WIDTH, dist, colors);

        graphic.setPaint(gradient);
        graphic.fill(new Rectangle(WIDTH, HEIGHT));
    }
}
