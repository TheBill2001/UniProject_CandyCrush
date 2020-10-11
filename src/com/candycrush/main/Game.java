package com.candycrush.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = 8102020L;

    public static final int WIDTH = 1280;
    public static final int HEIGHT = WIDTH * 9 / 12;

    private Thread thread;
    private boolean running = false;

    private Handler handler;

    public Game() {
        handler = new Handler();

        new Window(WIDTH, HEIGHT, "Candy Crush!", this);

        handler.addObject(new Grid(350, 30, ID.grid));
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

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.white);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        new Game();
    }

}
