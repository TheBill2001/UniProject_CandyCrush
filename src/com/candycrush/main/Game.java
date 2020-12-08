package com.candycrush.main;

import com.candycrush.main.handler.Handler;
import com.candycrush.main.uicomponent.MainMenu;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = 8102020L;

    public static final int WIDTH = 1280;
    public static final int HEIGHT = WIDTH * 9 / 12;

    private static Thread thread;
    private static boolean running = false;
    private static State state = State.MAIN_MENU;
    private static Handler handler;

    public Game() {
        handler = new Handler();
        new Window(WIDTH, HEIGHT, "Candy Crush - Student Game!", this);

        MainMenu mainMenu = new MainMenu();
        handler.addComponent(mainMenu);
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
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }

        stop();
    }

    private void tick() {
        handler.tick();
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

        handler.render(g);

        g.dispose();
        bs.show();
    }

    public void setState(State state) {
        Game.state = state;
    }

    public State getState() {
        return state;
    }

    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");
        new Game();
    }

}
