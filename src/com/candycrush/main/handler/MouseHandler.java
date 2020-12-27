package com.candycrush.main.handler;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MouseHandler extends MouseAdapter {
    private static MouseHandler handler = null;
    private static ArrayList<Clickable> objects = new ArrayList<>();

    public static MouseHandler getInstance() {
        if (handler == null) {
            handler = new MouseHandler();
        }
        return handler;
    }

    public void addObject(Clickable object) {
        objects.add(object);
    }

    private boolean mouseOver(int mouseX, int mouseY, Clickable object) {
        int x = object.getX();
        int y = object.getY();
        int dx = x + object.getWidth();
        int dy = y + object.getHeight();
        return mouseX > x && mouseX < dx && mouseY > y && mouseY < dy;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        System.out.println("Pressed!");
        System.out.println(mouseX);
        System.out.println(mouseY);

        for (Clickable object : objects) {
            if (mouseOver(mouseX, mouseY, object))
                object.mouseClicked();
        }
    }
}
