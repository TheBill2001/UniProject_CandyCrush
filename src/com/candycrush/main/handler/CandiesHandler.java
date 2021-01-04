package com.candycrush.main.handler;

import com.candycrush.main.CandiesID;
import com.candycrush.main.object.concrete.Candy;
import com.candycrush.main.object.concrete.Level;
import com.candycrush.main.object.concrete.ObjectGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CandiesHandler {
    private static CandiesHandler candiesHandler = null;
    private final ObjectGroup candiesGroup = new ObjectGroup();
    private Level level = null;
    private int[][] grid = new int[10][9];
    private final ArrayList<Point> spawnPointCrd = new ArrayList<>();
    private final ArrayList<Candy> candies = new ArrayList<>();
    private Random random;

    static class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    public void setLevel(Level level) {
        this.level = level;

        if (level == null)
            return;

        for (int x = 0; x < 9; x++) {
            grid[0][x] = 3;
        }

        grid = new int[10][9];
        spawnPointCrd.clear();
        candies.clear();
        candiesGroup.getObjects().clear();
        random = new Random();

        for (int y = 1; y < 10; y++) {
            for (int x = 0; x < 9; x++) {
                if (level.getEmpty()[y-1][x])
                    grid[y][x] = 3;
                else
                    grid[y][x] = 0;
            }
        }

        // Find spawn points
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (grid[y+1][x] == 0) {
                    spawnPointCrd.add(new Point(x, 0));
                    break;
                }
            }
        }
    }

    public static CandiesHandler getInstance() {
        if (candiesHandler == null) {
            candiesHandler = new CandiesHandler();
        }
        return candiesHandler;
    }

    public int[][] getGrid() {
        return grid;
    }

    public ObjectGroup getCandies() {
        return candiesGroup;
    }

    private void spawnCandies() {
        CandiesID[] candiesID = CandiesID.values();
        for (Point point : spawnPointCrd) {
            if (grid[point.getY()][point.getX()] == 0) {
                Candy temp = new Candy(point.getX()*100+350, point.getY()*100-70, candiesID[random.nextInt(candiesID.length)], this);
                candies.add(temp);
                candiesGroup.addObject(temp);
                grid[point.getY()][point.getX()] = 2;
            }
        }
        Collections.sort(candies);
    }

    private void updateGrid() {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 9; x++) {
                if (grid[y][x] == 2)
                    grid[y][x] = 0;
            }
        }
        for (Candy candy : candies) {
            if (grid[(candy.getY() + 70) / 100][(candy.getX() - 350) / 100] == 0)
                grid[(candy.getY() + 70) / 100][(candy.getX() - 350) / 100] = 2;
        }
    }

    public void tick() {
        if (level == null)
            return;

        spawnCandies();
        for (Candy candy : candies) {
            // Falling
            int y = (candy.getY() + 70) / 100;
            int x = (candy.getX() - 350) / 100;
            while (y <= 8) {
                if (grid[y+1][x] == 0) {
                    y++;
                    continue;
                }
                if (grid[y+1][x] == 1) {
                    break;
                }
                if (grid[y+1][x] == 3) {
                    int temp = y;
                    while (temp <= 8 && grid[temp+1][x] == 3)
                        temp++;

                    if (grid[temp+1][x] == 0) {
                        y = temp;
                    }
                }
            }

            if (!candy.isMoving()) {
                candy.gotoXY(x * 100 + 350, y * 100 - 70);
                grid[y][x] = 1;
            }

            updateGrid();
        }

    }
}
