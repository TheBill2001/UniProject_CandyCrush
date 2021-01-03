package com.candycrush.main.object.concrete;

import com.candycrush.main.object.abstraction.Clickable;
import com.candycrush.main.object.abstraction.GenericObject;

import java.awt.*;
import java.util.ArrayList;

public class ClickableGroup extends GenericObject {
    private final ArrayList<Clickable> objects = new ArrayList<>();

    public void addObject(Clickable object) {
        objects.add(object);
    }

    public void addObjects(ClickableGroup group) {
        objects.addAll(group.getObjects());
    }

    public void removeObject(Clickable object) {
        objects.remove(object);
    }

    public void removeObjects(ClickableGroup group) {
        objects.removeAll(group.getObjects());
    }

    public ArrayList<Clickable> getObjects() {
        return objects;
    }

    @Override
    public void render(Graphics2D graphic) {
        for (Clickable object : objects) {
            if (object.isEnable())
                object.render(graphic);
        }
    }
}
