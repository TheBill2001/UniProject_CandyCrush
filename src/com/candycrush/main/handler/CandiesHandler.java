package com.candycrush.main.handler;

import com.candycrush.main.CandiesID;
import com.candycrush.main.object.concrete.Candy;
import com.candycrush.main.object.concrete.Level;
import com.candycrush.main.object.concrete.ObjectGroup;
import com.candycrush.main.resourceloader.TextureLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CandiesHandler {
    private static CandiesHandler candiesHandler = null;
    private final ObjectGroup candiesGroup = new ObjectGroup();
    private final ArrayList<Candy> candies = new ArrayList<>();
    private Level level = null;
    private int[][] grid = new int[10][9];
    private Random random;

    private int selX = 0;
    private int selY = 0;
    private int oldSelX = 0;
    private int oldSelY = 0;

    public static CandiesHandler getInstance() {
        if (candiesHandler == null) {
            candiesHandler = new CandiesHandler();
        }
        return candiesHandler;
    }

    public void setLevel(Level level) {
        this.level = level;

        if (level == null)
            return;

        for (int x = 0; x < 9; x++) {
            grid[0][x] = 3;
        }

        grid = new int[10][9];
        candies.clear();
        candiesGroup.clear();
        random = new Random();

        for (int y = 1; y < 10; y++) {
            for (int x = 0; x < 9; x++) {
                if (level.getEmpty()[y - 1][x])
                    grid[y][x] = 2;
                else
                    grid[y][x] = 0;
            }
        }
    }

    public void selected(int x, int y) {
        if (grid[(y+70)/100][(x-350)/100] != 2) {
            selX = ((x-350) / 100) * 100 + 350;
            selY = ((y-30) / 100) * 100 +30;
        }
    }

    public int[][] getGrid() {
        return grid;
    }

    public ObjectGroup getCandies() {
        return candiesGroup;
    }

    private void updateCandiesGroup() {
        candiesGroup.clear();
        for (Candy candy : candies) {
            candiesGroup.addObject(candy);
        }
    }

    private void spawnCandies() {
        CandiesID[] candiesID = CandiesID.values();
        for (int x = 0; x < 9; x++) {
            if (grid[0][x] == 0) {
                Candy temp = new Candy(x * 100 + 350, -70, candiesID[random.nextInt(candiesID.length)], this);
                candies.add(temp);
                grid[0][x] = 1;
            }
        }
        //Collections.sort(candies);
    }

    private void updateGrid() {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 9; x++) {
                if (grid[y][x] == 1)
                    grid[y][x] = 0;
            }
        }

        for (Candy candy : candies) {
            if (grid[(candy.getY() + 70) / 100][(candy.getX() - 350) / 100] == 0)
                grid[(candy.getY() + 70) / 100][(candy.getX() - 350) / 100] = 1;
        }
    }

    private Candy findCandy(int x, int y) {
        for (Candy candy : candies) {
            if (candy.getX() == x && candy.getY() == y)
                return candy;
        }
        return null;
    }

    private ArrayList<Candy> checkVerticalLeft(Candy candy) {
        ArrayList<Candy> matches = new ArrayList<>();
        Candy c1 = findCandy(candy.getX()-100, candy.getY());
        if (c1 != null && c1.getId() == candy.getId()) {
            matches.addAll(checkVerticalLeft(c1));
            matches.add(c1);
        }

        return matches;
    }

    private ArrayList<Candy> checkVerticalRight(Candy candy) {
        ArrayList<Candy> matches = new ArrayList<>();
        Candy c1 = findCandy(candy.getX()+100, candy.getY());
        if (c1 != null && c1.getId() == candy.getId()) {
            matches.addAll(checkVerticalRight(c1));
            matches.add(c1);
        }

        return matches;
    }

    private ArrayList<Candy> checkVertical(Candy candy) {
        ArrayList<Candy> matches = new ArrayList<>();
        matches.addAll(checkVerticalRight(candy));
        matches.addAll(checkVerticalLeft(candy));
        matches.add(candy);
        return matches;
    }

    private ArrayList<Candy> checkHorizontalUp(Candy candy) {
        ArrayList<Candy> matches = new ArrayList<>();
        Candy c1 = findCandy(candy.getX(), candy.getY()+100);
        if (c1 != null && c1.getId() == candy.getId()) {
            matches.addAll(checkHorizontalUp(c1));
            matches.add(c1);
        }

        return matches;
    }

    private ArrayList<Candy> checkHorizontalDown(Candy candy) {
        ArrayList<Candy> matches = new ArrayList<>();
        Candy c1 = findCandy(candy.getX(), candy.getY()-100);
        if (c1 != null && c1.getId() == candy.getId()) {
            matches.addAll(checkHorizontalDown(c1));
            matches.add(c1);
        }

        return matches;
    }

    private ArrayList<Candy> checkHorizontal(Candy candy) {
        ArrayList<Candy> matches = new ArrayList<>();
        matches.addAll(checkHorizontalUp(candy));
        matches.addAll(checkHorizontalDown(candy));
        matches.add(candy);
        return matches;
    }

    private boolean check(Candy candy) {
        if (checkHorizontal(candy).size() == 3) {
            candies.removeAll(checkHorizontal(candy));
        } else if (checkVertical(candy).size() == 3) {
            candies.removeAll(checkVertical(candy));
        } else
            return false;
        return true;
    }

    public void tick() {
        if (level == null)
            return;

        spawnCandies();
        for (int i = 0; i < candies.size(); i++) {
            Candy candy = candies.get(i);
            int y = (candy.getY() + 70) / 100;
            int x = (candy.getX() - 350) / 100;
            while (y <= 8) {
                if (grid[y + 1][x] == 0) {
                    y++;
                    continue;
                }
                if (grid[y + 1][x] == 1) {
                    break;
                }
                if (grid[y + 1][x] == 2) {
                    int temp = y;
                    while (temp <= 8 && grid[temp + 1][x] == 2)
                        temp++;

                    if (grid[temp + 1][x] == 0) {
                        y = temp;
                    } else
                        break;
                }
            }

            if (!candy.isMoving()) {
                candy.gotoXY(x * 100 + 350, y * 100 - 70);
            }

            if (check(candy))
                i = 0;

            updateGrid();
        }

        if (oldSelX != selX || oldSelY != selY)  {
            Candy c1 = findCandy(oldSelX,oldSelY);
            Candy c2 = findCandy(selX,selY);

            if (c1 != null && c2 != null) {
                if ((Math.abs(oldSelX - selX) == 100 && Math.abs(oldSelY - selY) == 0) || (Math.abs(oldSelX - selX) == 0 && Math.abs(oldSelY - selY) == 100)) {
                    c1.gotoXY(selX, selY);
                    c2.gotoXY(oldSelX, oldSelY);

                    if (!check(c1) && !check(c2)) {
                        c1.gotoXY(oldSelX, oldSelY);
                        c2.gotoXY(selX, selY);
                    }
                }
            }

            oldSelX = selX;
            oldSelY = selY;
        }

        updateCandiesGroup();
    }

    public void render(Graphics2D graphic) {
        if (level == null || selY == 0 || selX == 0)
            return ;

        BufferedImage selector = SpriteHandler.cutSprite(TextureLoader.getInstance().getTexture("candies.png"), 274, 1569, 160, 160);
        graphic.drawImage(selector, selX, selY, 100, 100, null);
    }
}
