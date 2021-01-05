package com.candycrush.main.object.abstraction;

import com.candycrush.main.object.interface_.ActionInterface;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

public abstract class Clickable extends GameObject {
    protected ArrayList<ActionInterface> actions = new ArrayList<>();

    public Clickable(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public abstract void mousePressed(MouseEvent e);

    public abstract void mouseReleased(MouseEvent e);

    public abstract void mouseHover(MouseEvent e);

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
