package com.candycrush.main.handler;

import com.candycrush.main.object.abstraction.GenericObject;
import com.candycrush.main.object.concrete.ObjectGroup;

import java.awt.*;
import java.util.ArrayList;


public class ObjectHandler {
    private static ObjectHandler objectHandler = null;
    private final ArrayList<GenericObject> objects = new ArrayList<>();
    private final ArrayList<GenericObject> objectsToRemove = new ArrayList<>();
    private final ArrayList<GenericObject> objectsToAdd = new ArrayList<>();

    public static ObjectHandler getInstance() {
        if (objectHandler == null) {
            objectHandler = new ObjectHandler();
        }
        return objectHandler;
    }

    public void tick() {
        for (GenericObject object : objects) {
            if (object.isEnable())
                object.tick();
        }

        objects.removeAll(objectsToRemove);
        objects.addAll(objectsToAdd);

        objectsToAdd.clear();
        objectsToRemove.clear();
    }

    public void render(Graphics2D graphic) {
        for (GenericObject object : objects) {
            if (object.isEnable())
                object.render(graphic);
        }
    }

    public void addObject(GenericObject object) {
        objectsToAdd.add(object);
    }

    public void removeObject(GenericObject object) {
        objectsToRemove.add(object);
    }

    public void addObjects(ObjectGroup group) {
        objectsToAdd.add(group);
    }
}
