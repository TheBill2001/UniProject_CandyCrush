package com.candycrush.main.handler;

import java.awt.*;
import java.util.LinkedList;

public class Handler {
    private LinkedList<ObjectHandlerInterface> handlers;

    public Handler() {
        handlers = new LinkedList<>();
    }

    public void tick() {
        for (ObjectHandlerInterface object : handlers) {
            object.tick();
        }
    }

    public void render(Graphics2D graphic) {
        for (ObjectHandlerInterface object : handlers) {
            object.render(graphic);
        }
    }

    public void addComponent(ObjectHandlerInterface object) {
        handlers.add(object);
    }
}
