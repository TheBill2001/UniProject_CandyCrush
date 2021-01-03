package com.candycrush.main.object.concrete;

import com.candycrush.main.object.abstraction.GenericObject;

import java.awt.*;
import java.util.ArrayList;

public class ObjectGroup extends GenericObject {
    protected final ArrayList<GenericObject> objects = new ArrayList<>();

    public void addObject(GenericObject object) {
        objects.add(object);
    }

    public void addObjects(ObjectGroup group) {
        objects.addAll(group.getObjects());
    }

    public void removeObject(GenericObject object) {
        objects.remove(object);
    }

    public void removeObjects(ObjectGroup group) {
        objects.removeAll(group.getObjects());
    }

    public ArrayList<GenericObject> getObjects() {
        return objects;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

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
