package com.candycrush.main.resourceloader;

import com.candycrush.main.object.concrete.Level;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

public class LevelLoader {
    private static final ArrayList<Level> levels = new ArrayList<>();
    private static LevelLoader levelLoader = null;

    private LevelLoader() {
        System.out.println("Start loading level configs!");
        loadLevelConfig();
        System.out.println("Loaded level configs!");
    }

    public static LevelLoader getInstance() {
        if (levelLoader == null) {
            levelLoader = new LevelLoader();
        }
        return levelLoader;
    }

    private void loadLevelConfig() {
        String path = "src/com/candycrush/main/res/level";
        Properties properties;
        File folder = new File(path);
        File[] fileList = folder.listFiles();

        if (fileList != null) {
            for (File file : fileList) {
                properties = new Properties();
                try {
                    properties.load(new FileInputStream(file));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                levels.add(new Level(properties, Integer.parseInt(file.getName().substring(0,file.getName().indexOf(".")))));
            }
        } else {
            System.out.println("Level folder not found!");
        }

        Collections.sort(levels);
        levels.get(0).setLock(false);
    }

    public ArrayList<Level> getLevels() {
        return levels;
    }

    public int getNumberOfLevel() {
        return levels.size();
    }
}
