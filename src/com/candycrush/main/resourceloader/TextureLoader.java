package com.candycrush.main.resourceloader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class TextureLoader {
    private static final HashMap<String, BufferedImage> texture = new HashMap<>();
    private static TextureLoader textureLoader = null;

    private TextureLoader() {
        System.out.println("Resource loader started!");
        loadTexture();
        System.out.println("Resource loader ended!");
    }

    public static TextureLoader getInstance() {
        if (textureLoader == null) {
            textureLoader = new TextureLoader();
        }
        return textureLoader;
    }

    public BufferedImage getTexture(String name) {
        if (texture.containsKey(name))
            return texture.get(name);
        else {
            System.out.println("File \"" + name + "\" not found!");
            return null;
        }
    }

    private void loadTexture() {
        String path = "res/texture";
        BufferedImage image = null;
        String name;
        File folder = new File(path);
        File[] fileList = folder.listFiles();

        if (fileList != null) {
            for (File file : fileList) {
                name = file.getName();
                try {
                    image = ImageIO.read(file);
                } catch (IOException e) {
                    System.out.println("Texture " + name + " cannot be loaded!");
                }
                texture.put(name, image);
            }
        } else
            System.out.println("Texture folder not found!");
    }

}
