package com.candycrush.main.object.abstraction;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

public abstract class Clickable extends GameObject {
    protected ArrayList<Action> actions = new ArrayList<>();

    public Clickable(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public abstract void mousePressed(MouseEvent e);

    public abstract void mouseReleased(MouseEvent e);

    public abstract void mouseHover(MouseEvent e);

    public abstract void mouseReset();

    public void addAction(Action action) {
        actions.add(action);
    }

    public void performAction() {
        for (Action action : actions) {
            action.perform();
        }
    }
}
