package com.candycrush.main.object.concrete;

import java.util.Properties;

public class Level implements Comparable<Level> {
    private final int num;
    private int move;
    private final int moveD;
    private final int target;
    private int score = 0;
    private final boolean[][] empty = new boolean[9][9];
    private boolean lock = true;
    private boolean win = false;
    private boolean loose = false;

    public Level(Properties properties, int num) {
        this.num = num;
        this.move = Integer.parseInt(properties.getProperty("move"));
        this.moveD = this.move;
        target = Integer.parseInt(properties.getProperty("target"));

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
        score = 0;
        win  = false;
        loose = false;
    }

    public int getNumber() {
        return num;
    }

    public boolean[][] getEmpty() {
        return empty;
    }

    public int getTarget() {
        return target;
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

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public boolean isWin() {
        return win;
    }

    public boolean isLoose() {
        return loose;
    }

    public void setLoose(boolean loose) {
        this.loose = loose;
    }

    @Override
    public int compareTo(Level level) {
        return num -level.getNumber();
    }
}
