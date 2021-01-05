package com.candycrush.main.object.concrete;

import java.util.Properties;

public class Level implements Comparable<Level> {
    private String name;
    private int move = 0;
    private int[] targets = new int[3];
    private int score = 0;
    private final boolean[][] empty = new boolean[9][9];

    public Level(Properties properties) {
        setProperties(properties);
    }

    public void setProperties(Properties properties) {
        this.name = properties.getProperty("name");
        this.move = Integer.parseInt(properties.getProperty("move"));
        String[] temp = properties.getProperty("target").split(",");
        for (int i = 0; i < 3; i++) {
            targets[i] = Integer.parseInt(temp[i]);
        }
        setEmpty(properties.getProperty("empty"));
    }

    private void setEmpty(String string) {
        if (string.isEmpty())
            return;

        String[] emptyCell = string.split(",");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                empty[i][j] = false;
            }
        }

        for (String str : emptyCell) {
            int num = Integer.parseInt(str);
            empty[num / 9][num % 9] = true;
        }
    }

    public String getName() {
        return name;
    }

    public boolean[][] getEmpty() {
        return empty;
    }

    public int getTarget() {
        return targets[targets.length-1];
    }

    public int getMove() {
        return move;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(Level level) {
        return Integer.parseInt(name) - Integer.parseInt(level.getName());
    }
}
