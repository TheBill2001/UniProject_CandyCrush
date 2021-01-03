package com.candycrush.main.object.abstraction;

import java.awt.*;

public abstract class GenericObject {
    protected boolean enable = true;

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isEnable() {
        return enable;
    }

    public void tick() {}

    public void render(Graphics2D graphic) {}
}
