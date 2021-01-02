package com.candycrush.main.handler;

import java.awt.*;
import java.util.ArrayList;

public class ObjectGroup extends GameObject{
    private final ArrayList<GameObject> objects = new ArrayList<>();

    public ObjectGroup() {
        super(0,0,0,0);
    }

    public void addComponent(GameObject object) {
        objects.add(object);
    }

    public void removeComponent(GameObject object) {
        objects.remove(object);
    }

    @Override
    public void setEnable(boolean enable) {
        for (GameObject object : objects) {
            object.setEnable(enable);
        }
    }

    @Override
    public void render(Graphics2D graphic) {
        for (GameObject object : objects) {
            if (object.isEnable())
                object.render(graphic);
        }
    }
}
