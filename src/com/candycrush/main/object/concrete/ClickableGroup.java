package com.candycrush.main.object.concrete;

import com.candycrush.main.object.abstraction.Clickable;

import java.awt.*;
import java.util.ArrayList;

public class ClickableGroup extends Clickable {
    private final ArrayList<Clickable> objects = new ArrayList<>();

    public ClickableGroup() {
        super(0,0,0,0);
    }

    public void addObject(Clickable object) {
        objects.add(object);
    }

    public void removeObject(Clickable object) {
        objects.remove(object);
    }

    public ArrayList<Clickable> getObjects() {
        return objects;
    }

    @Override
    public void tick() {
        for (Clickable object : objects) {
            object.tick();
        }
    }

    @Override
    public void render(Graphics2D graphic) {
        for (Clickable object : objects) {
            object.render(graphic);
        }
    }

    @Override
    public void mousePressed() {
        for (Clickable object : objects) {
            object.mousePressed();
        }
    }

    @Override
    public void mouseReleased() {
        for (Clickable object : objects) {
            object.mouseReleased();
        }
    }

    @Override
    public void mouseHover() {
        for (Clickable object : objects) {
            object.mouseHover();
        }
    }

    @Override
    public void mouseReset() {
        for (Clickable object : objects) {
            object.mouseReset();
        }
    }
}
