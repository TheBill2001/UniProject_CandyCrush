package com.candycrush.main;

import com.candycrush.main.handler.MouseHandler;
import com.candycrush.main.handler.ObjectHandler;
import com.candycrush.main.resourceloader.TextureLoader;
import com.candycrush.main.scene.MainMenu;
import com.candycrush.main.uicomponent.Button;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.io.Serial;

public class Game extends Canvas implements Runnable {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = WIDTH * 9 / 12;
    @Serial
    private static final long serialVersionUID = 8102020L;
    private static Thread thread;
    private static boolean running = false;
    private static State state = State.MAIN_MENU;
    private static final ObjectHandler OBJECT_HANDLER = ObjectHandler.getInstance();
    private static final TextureLoader TEXTURE_LOADER = TextureLoader.getInstance();
    private static final MouseHandler MOUSE_HANDLER = MouseHandler.getInstance();

    public Game() {
        new Window(WIDTH, HEIGHT, "Candy Crush - Student Game!", this);
        this.addMouseListener(MOUSE_HANDLER);
        //this.addMouseMotionListener(MOUSE_HANDLER);

        Button button = new Button();

        MainMenu mainMenu = new MainMenu();
        OBJECT_HANDLER.addObject(mainMenu);
        OBJECT_HANDLER.addObject(button);
        MOUSE_HANDLER.addObject(button);
    }

    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");
        new Game();
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60; // ticks per second
        double ns = 1000000000 / amountOfTicks; // nanoseconds per tick
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            // 1 delta means 1 tick has passed
            while (delta >= 1) {
                tick();
                delta--;
            }

            if (running)
                render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) { // 1000 milliseconds = 1 second
                timer += 1000;
//                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }

        stop();
    }

    private void tick() {
        OBJECT_HANDLER.tick();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        g.setRenderingHints(rh);
        g.setColor(Color.white);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        OBJECT_HANDLER.render(g);

        g.dispose();
        bs.show();
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        Game.state = state;
    }

}
