package com.candycrush.main.handler;

import java.awt.*;
import java.util.ArrayList;


public class ObjectHandler {
    private static ObjectHandler objectHandler = null;
    private static final ArrayList<GameObject> objects = new ArrayList<>();

    public static ObjectHandler getInstance() {
        if (objectHandler == null) {
            objectHandler = new ObjectHandler();
        }
        return objectHandler;
    }

    public void tick() {
        for (GameObject object : objects) {
            object.tick();
        }
    }

    public void render(Graphics2D graphic) {
        for (GameObject object : objects) {
            object.render(graphic);
        }
    }

    public void addObject(GameObject object) {
        objects.add(object);
    }
}
