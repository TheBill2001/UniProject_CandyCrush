package com.candycrush.main.handler;

import java.awt.*;
import java.util.ArrayList;


public class ObjectHandler {
    private static ObjectHandler objectHandler = null;
    private final ArrayList<GameObject> objects = new ArrayList<>();
    private final ArrayList<GameObject> objectsToRemove = new ArrayList<>();
    private final ArrayList<GameObject> objectsToAdd = new ArrayList<>();

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

        objects.removeAll(objectsToRemove);
        objects.addAll(objectsToAdd);

        objectsToAdd.clear();
        objectsToRemove.clear();
    }

    public void render(Graphics2D graphic) {
        for (GameObject object : objects) {
            object.render(graphic);
        }
    }

    public void addObject(GameObject object) {
        objectsToAdd.add(object);
    }

    public void removeObject(GameObject object) {
        objectsToRemove.add(object);
    }
}
