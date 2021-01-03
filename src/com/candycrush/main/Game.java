package com.candycrush.main;

import com.candycrush.main.handler.MouseHandler;
import com.candycrush.main.handler.ObjectHandler;
import com.candycrush.main.object.concrete.ClickableGroup;
import com.candycrush.main.object.concrete.Level;
import com.candycrush.main.object.concrete.ObjectGroup;
import com.candycrush.main.object.uicomponent.Background;
import com.candycrush.main.object.uicomponent.Button;
import com.candycrush.main.object.uicomponent.PlainImage;
import com.candycrush.main.object.uicomponent.Window;
import com.candycrush.main.resourceloader.LevelLoader;
import com.candycrush.main.resourceloader.TextureLoader;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.Serial;
import java.util.ArrayList;

public class Game extends Canvas implements Runnable {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = WIDTH * 9 / 12;
    private static int fps = 0;
    @Serial
    private static final long serialVersionUID = 8102020L;
    private static Thread thread;
    private static boolean running = false;
    private static final ObjectHandler OBJECT_HANDLER = ObjectHandler.getInstance();
    private static final TextureLoader TEXTURE_LOADER = TextureLoader.getInstance();
    private static final MouseHandler MOUSE_HANDLER = MouseHandler.getInstance();
    private static final LevelLoader LEVEL_LOADER = LevelLoader.getInstance();
    private final Window window = new Window(WIDTH, HEIGHT, "Candy Crush - Student Game!", this);

    public Game() {
        this.addMouseListener(MOUSE_HANDLER);
        this.addMouseMotionListener(MOUSE_HANDLER);

        ObjectGroup mainMenuGroup = new ObjectGroup();
        ObjectGroup levelSelectorGroup = new ObjectGroup();
        ClickableGroup levelButtons = new ClickableGroup();
        ObjectGroup gameSceneGroup = new ObjectGroup();

        Background background = new Background();
        OBJECT_HANDLER.addObject(background);

        // Main menu
        PlainImage mainLogo = new PlainImage(TEXTURE_LOADER.getTexture("logo_main.png"), (WIDTH-400)/2,50, 400, 400);
        Button exitButton = new Button("Exit",Color.WHITE,45, (WIDTH-250)/2, 625, 250, 75, TEXTURE_LOADER.getTexture("button_pink_long.png"));
        Button playButton = new Button("Play", Color.WHITE, 50,(WIDTH-250)/2, 500, 250, 100, TEXTURE_LOADER.getTexture("button_yellow_long.png"));

        exitButton.addAction(() -> running = false);
        playButton.addAction(() -> {
            mainMenuGroup.setEnable(false);
            levelSelectorGroup.setEnable(true);
        });

        mainMenuGroup.addObject(mainLogo);
        mainMenuGroup.addObject(playButton);
        mainMenuGroup.addObject(exitButton);

        OBJECT_HANDLER.addObject(mainMenuGroup);
        MOUSE_HANDLER.addObject(playButton);
        MOUSE_HANDLER.addObject(exitButton);

        // Levels selector
        PlainImage levelPanel = new PlainImage(TEXTURE_LOADER.getTexture("big_panel.png"), 0, 0, WIDTH, HEIGHT);
        Button backToMenu = new Button((WIDTH/2)-50,820,100,100,TEXTURE_LOADER.getTexture("back_to_menu.png")) ;
        Button levelPageLeft = new Button((WIDTH/2)-170, 820, 100, 100, TEXTURE_LOADER.getTexture("arrow_pink_left.png"));
        Button levelPageRight = new Button((WIDTH/2)+70, 820, 100, 100, TEXTURE_LOADER.getTexture("arrow_pink_right.png"));

        backToMenu.addAction(() -> {
            levelSelectorGroup.setEnable(false);
            mainMenuGroup.setEnable(true);
        });

        levelSelectorGroup.addObject(levelPanel);
        levelSelectorGroup.addObject(backToMenu);
        levelSelectorGroup.addObject(levelPageLeft);
        levelSelectorGroup.addObject(levelPageRight);

        int count = 0;
        ArrayList<Level> levels = LEVEL_LOADER.getLevels();
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 6; j++) {
                if (count < LEVEL_LOADER.getNumberOfLevel()) {
                    Button temp = new Button(levels.get(count++).getName(), Color.WHITE, 30, j*(WIDTH / 7)-50, i*(HEIGHT / 6) - 50, 100, 100, TEXTURE_LOADER.getTexture("square_button.png"));
                    temp.addAction(() -> {
                        levelSelectorGroup.setEnable(false);
                        gameSceneGroup.setEnable(true);
                    });
                    levelButtons.addObject(temp);
                    levelSelectorGroup.addObject(temp);
                }
            }
        }

        levelSelectorGroup.setEnable(false);

        OBJECT_HANDLER.addObject(levelSelectorGroup);
        MOUSE_HANDLER.addObjects(levelButtons);
        MOUSE_HANDLER.addObject(backToMenu);
        MOUSE_HANDLER.addObject(levelPageLeft);
        MOUSE_HANDLER.addObject(levelPageRight);
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
            thread.join(10);
            window.dispose();
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
                fps = frames;
                frames = 0;
            }
        }

        stop();
    }

    private void tick() {
        MOUSE_HANDLER.tick();
        OBJECT_HANDLER.tick();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setColor(Color.white);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        OBJECT_HANDLER.render(g);
        g.setFont(new Font("TimesRoman", Font.BOLD, 10));
        g.setColor(Color.BLACK);
        g.drawString("FPS: " + fps, 10, 10);

        g.dispose();
        bs.show();
    }
}
