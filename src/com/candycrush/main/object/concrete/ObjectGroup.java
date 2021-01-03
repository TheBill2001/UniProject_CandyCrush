package com.candycrush.main.object.concrete;

import com.candycrush.main.object.abstraction.GenericObject;
import com.candycrush.main.object.interface_.GroupInterface;

import java.awt.*;
import java.util.ArrayList;

public class ObjectGroup extends GenericObject implements GroupInterface<GenericObject> {
    private final ArrayList<GenericObject> objects = new ArrayList<>();

    @Override
    public void addObject(GenericObject object) {
        objects.add(object);
    }

    @Override
    public void removeObject(GenericObject object) {
        objects.remove(object);
    }

    @Override
    public ArrayList<GenericObject> getObjects() {
        return objects;
    }

    @Override
    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public void tick() {
        for (GenericObject object : objects) {
            object.setEnable(enable);
        }
    }

    @Override
    public void render(Graphics2D graphic) {
        for (GenericObject object : objects) {
            if (object.isEnable())
                object.render(graphic);
        }
    }
}
