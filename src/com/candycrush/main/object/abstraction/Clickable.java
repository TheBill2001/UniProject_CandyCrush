package com.candycrush.main.object.abstraction;

import com.candycrush.main.object.interface_.ActionInterface;

import java.util.ArrayList;

public abstract class Clickable extends GameObject {
    protected ArrayList<ActionInterface> actions = new ArrayList<>();

    public Clickable(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public abstract void mousePressed ();

    public abstract void mouseReleased();

    public abstract void mouseHover();

    public abstract void mouseReset();

    public void addAction(ActionInterface actionInterface) {
        actions.add(actionInterface);
    }

    public void performAction() {
        for (ActionInterface action : actions) {
            action.perform();
        }
    }
}
