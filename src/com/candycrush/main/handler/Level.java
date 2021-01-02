package com.candycrush.main.handler;

import java.util.Properties;

public class Level {
    private String name;
    private boolean[][] empty = new boolean[9][9];

    public Level(Properties properties) {
        setProperties(properties);
    }

    public void setProperties(Properties properties) {
        this.name = properties.getProperty("name");
        setEmpty(properties.getProperty("empty"));
    }

    private void setEmpty(String string) {
        String[] emptyCell = string.split(",");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                empty[i][j] = false;
            }
        }

        for (String str : emptyCell) {
            int num = Integer.parseInt(str);
            int row, col;
            if ((float) num / 9 == 1.0*num / 9)
                row = (num / 9) - 1;
            else
                row = num / 9;
            if (num % 9 != 0)
                col = (num % 9) - 1;
            else
                col = 8;
            empty[row][col] = true;
        }
    }

    public String getName() {
        return name;
    }

    public boolean[][] getEmpty() {
        return empty;
    }
}
