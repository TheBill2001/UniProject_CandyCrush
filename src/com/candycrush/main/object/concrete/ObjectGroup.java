package com.candycrush.main.object.concrete;

import com.candycrush.main.object.abstraction.GenericObject;

import java.awt.*;
import java.util.ArrayList;

public class ObjectGroup extends GenericObject {
    protected final ArrayList<GenericObject> objects = new ArrayList<>();

    public void addObject(GenericObject object) {
        objects.add(object);
    }

    public void removeObject(GenericObject object) {
        objects.remove(object);
    }

    public ArrayList<GenericObject> getObjects() {
        return objects;
    }

    @Override
    public void tick() {
        for (GenericObject object : objects) {
            object.tick();
        }
    }

    @Override
    public void render(Graphics2D graphic) {
        for (GenericObject object : objects) {
            object.render(graphic);
        }
    }
}
