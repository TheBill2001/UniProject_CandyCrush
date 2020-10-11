package com.candycrush.main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.*;

public class Window extends Canvas {
    private static final long serialVersionUID = 8102020L;   
    
    public Window(int width, int height, String title, Game game) {
        JFrame frame = new JFrame(title);

        Dimension d = new Dimension(width, height);
        game.setPreferredSize(d);
        game.setMaximumSize(d);
        game.setMinimumSize(d);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(game);
        frame.pack();
        frame.setVisible(true);
        game.start();
    }
}
