package com.candycrush.main.uicomponent;

import com.candycrush.main.handler.Clickable;
import com.candycrush.main.handler.SpriteHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Button extends Clickable {
    private String string;
    private Color color;
    private int size;
    private ArrayList<BufferedImage> textures;
    private int state = 0;

    public Button(String string, Color color,int size, int x, int y, int width, int height, BufferedImage spriteSheet) {
        super(x, y, width, height);
        this.string = string;
        this.color = color;
        this.size = size;
        this.textures = SpriteHandler.divideSprite(spriteSheet,1,3);
    }

    public void action() {

    }

    @Override
    public void mousePressed() {
        state = 2;
    }

    @Override
    public void mouseReleased() {
        state = 0;
        action();
    }

    @Override
    public void mouseHover() {
        state = 1;
    }

    @Override
    public void mouseReset() {
        state = 0;
    }

    @Override
    public void render(Graphics2D graphic) {
        graphic.drawImage(textures.get(state),x,y,width,height,null);
        graphic.setColor(color);
        Font font = new Font("TimesRoman", Font.BOLD, size);
        FontMetrics fontMetrics = graphic.getFontMetrics(font);
        int strX = x + (width - fontMetrics.stringWidth(string)) / 2;
        int strY = y + (height - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent();
        graphic.setFont(font);
        graphic.drawString(string,strX,strY);
    }
}
