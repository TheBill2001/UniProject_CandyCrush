package com.candycrush.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class LevelReader {
    private static final String path = "/level";
    private final List<boolean[][]> LEVEL_GRID = new ArrayList<>();

    public LevelReader() {
        File folder;
        File[] fileList;
        Scanner scanner;

        try {
            folder = new File(path);
            fileList = folder.listFiles();

            if (fileList != null) {
                for (File file : fileList) {
                    scanner = new Scanner(file);
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();

                        if (line.contains("empty")) {
                            line = line.replace("=","");
                            readGrid(line);
                        }
                    }
                }
            }


        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void readGrid(String line) {
        String[] cells = line.split(" ");
        boolean[][] grid = new boolean[9][9];
        for (boolean[] row : grid) {
            Arrays.fill(row, true);
        }

        for (String str : cells) {
            int cellNum = Integer.parseInt(str);
            grid[(cellNum / 9) - 1][(cellNum % 9) - 1] = false;
        }

        LEVEL_GRID.add(grid);
    }

    public boolean[][] getLevelGrid(int levelNum) {
        return LEVEL_GRID.get(levelNum);
    }
}
