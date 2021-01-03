package com.candycrush.main.object.uicomponent;

import com.candycrush.main.Game;
import com.candycrush.main.handler.MouseHandler;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class Window extends JFrame {
    public Window(int width, int height, String title, Game game) {
        super(title);

        Dimension d = new Dimension(width, height);
        game.setPreferredSize(d);
        game.setMaximumSize(d);
        game.setMinimumSize(d);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().add(game);
        pack();
        setVisible(true);
        game.start();
    }
}
