package com.candycrush.main.object.concrete;

import java.util.Properties;

public class Level implements Comparable<Level> {
    private final int num;
    private int move;
    private final int moveD;
    private final int[] targets = new int[3];
    private int score = 0;
    private final boolean[][] empty = new boolean[9][9];

    public Level(Properties properties, int num) {
        this.num = num;
        this.move = Integer.parseInt(properties.getProperty("move"));
        this.moveD = this.move;
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

    public void reset() {
        move = moveD;
    }

    public int getNumber() {
        return num;
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

    public void addScore(int scoreToAdd) {
        score += scoreToAdd;
    }

    public void removeMove(int move) {
        this.move -= move;
    }

    @Override
    public int compareTo(Level level) {
        return num -level.getNumber();
    }
}
