package com.candycrush.main.object.concrete;

import com.candycrush.main.object.abstraction.Clickable;
import com.candycrush.main.object.abstraction.GenericObject;
import com.candycrush.main.object.interface_.GroupInterface;


import java.awt.*;
import java.util.ArrayList;

public class ClickableGroup extends GenericObject implements GroupInterface<Clickable> {
    private final ArrayList<Clickable> objects = new ArrayList<>();

    @Override
    public void addObject(Clickable object) {
        objects.add(object);
    }

    @Override
    public void removeObject(Clickable object) {
        objects.remove(object);
    }

    @Override
    public ArrayList<Clickable> getObjects() {
        return objects;
    }

    @Override
    public void tick() {
        for (Clickable object : objects) {
            object.setEnable(enable);
        }
    }

    @Override
    public void render(Graphics2D graphic) {
        for (Clickable object : objects) {
            if (object.isEnable())
                object.render(graphic);
        }
    }
}
