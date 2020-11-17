package com.candycrush.main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Grid extends GameObject {
    private static final int SPRITE_ROWS = 8;
    private static final int SPRITE_COLUMNS = 8;
    private static final LevelReader levelReader = new LevelReader();
    private final List<BufferedImage> texture = new ArrayList<>();
    private final int ROW_OFFSET;
    private final int COLUMN_OFFSET;
    private final boolean[][] grid;

    public Grid(int x, int y, ID id, int level) {
        super(x, y, id);
        this.ROW_OFFSET = x + 30;
        this.COLUMN_OFFSET = y + 30;

        BufferedImageLoader loader = new BufferedImageLoader();
        BufferedImage sprite = loader.loadImage("res/texture/grid.png");
        SpriteSheet ss = new SpriteSheet(sprite);
        divideSprite(ss);

        grid = levelReader.getLevelGrid(level);
    }

    private void divideSprite(SpriteSheet ss) {
        for (int i = 1; i <= SPRITE_ROWS; i++) {
            for (int j = 1; j <= SPRITE_COLUMNS; j++) {
                texture.add(ss.getImage(i, j, 50, 50));
            }
        }
    }

    public void tick() {

    }

    private int offsetDirX(int dir) {
        if (dir == 0 || dir == 2 || dir == 4)
            return 0;
        if (dir == 1)
            return -1;
        if (dir == 3)
            return 1;
        return 0;
    }

    private int offsetDirY(int dir) {
        if (dir == 1 || dir == 3)
            return 0;
        if (dir == 0 || dir == 4)
            return -1;
        if (dir == 2)
            return 1;
        return 0;
    }

    private int diagonalOffsetX(int dir) {
        if (dir == 0 || dir == 1)
            return -1;
        if (dir == 2 || dir == 3)
            return 1;
        return 0;
    }

    private int diagonalOffsetY(int dir) {
        if (dir == 0 || dir == 3)
            return -1;
        if (dir == 1 || dir == 2)
            return 1;
        return 0;
    }

    private boolean emptyAdjacent(int x, int y, int dir) {
        int dX = offsetDirX(dir);
        int dY = offsetDirY(dir);
        if (x + dX > 8 || x + dX < 0 || y + dY > 8 || y + dY < 0)
            return true;
        else
            return !grid[x + dX][y + dY];
    }

    private boolean emptyDiagonal(int x, int y, int dir) {
        int dX = diagonalOffsetX(dir);
        int dY = diagonalOffsetY(dir);
        if (x + dX > 8 || x + dX < 0 || y + dY > 8 || y + dY < 0)
            return true;
        else
            return !grid[x + dX][y + dY];
    }

    public void render(Graphics g) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int even = 0;
                if ((i + j) % 2 == 1)
                    even = 1;

                int imgPosX = ROW_OFFSET + i * 100;
                int imgPosY = COLUMN_OFFSET + j * 100;
                if (grid[i][j]) {
                    // Draw center
                    g.drawImage(texture.get(56 + even), imgPosX, COLUMN_OFFSET + j * 100, null);
                    // Draw edge
                    for (int k = 0; k < 4; k++) {
                        if (emptyAdjacent(i, j, k))
                            g.drawImage(texture.get(32 + 2 * k + even), imgPosX + offsetDirX(k) * 50, imgPosY + offsetDirY(k) * 50, null);
                        else
                            g.drawImage(texture.get(40 + 2 * k + even), imgPosX + offsetDirX(k) * 50, imgPosY + offsetDirY(k) * 50, null);
                    }
                    // Draw conner
                    for (int k = 0; k < 4; k++) {

                        if (emptyAdjacent(i, j, k) && emptyAdjacent(i, j, k + 1))
                            g.drawImage(texture.get(2 * k + even), imgPosX + diagonalOffsetX(k) * 50, imgPosY + diagonalOffsetY(k) * 50, null);
                        else if (!emptyAdjacent(i, j, k) && emptyAdjacent(i, j, k + 1) && emptyDiagonal(i,j,k))
                            g.drawImage(texture.get(16 + 2 * k + even), imgPosX + diagonalOffsetX(k) * 50, imgPosY + diagonalOffsetY(k) * 50, null);
                        else if (emptyAdjacent(i, j, k) && !emptyAdjacent(i, j, k + 1) && emptyDiagonal(i,j,k))
                            g.drawImage(texture.get(24 + 2 * k + even), imgPosX + diagonalOffsetX(k) * 50, imgPosY + diagonalOffsetY(k) * 50, null);
                        else
                            g.drawImage(texture.get(48 + 2 * k + even), imgPosX + diagonalOffsetX(k) * 50, imgPosY + diagonalOffsetY(k) * 50, null);
                    }
                } else {
                    // Draw empty conner
                    for (int k = 0; k < 4; k++) {
                        if (!emptyAdjacent(i, j, k) && !emptyAdjacent(i, j, k + 1))
                            g.drawImage(texture.get(8 + 2 * k + even), imgPosX + diagonalOffsetX(k) * 50, imgPosY + diagonalOffsetY(k) * 50, null);
                    }
                }
            }
        }
    }
}
