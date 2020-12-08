package com.candycrush.main.resourceloader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ResourceLoader {
    private static final HashMap<String, BufferedImage> texture = new HashMap<>();

    public ResourceLoader() {
        System.out.println("Resource loader started!");
        loadTexture();
        System.out.println("Resource loader ended!");
    }

    public BufferedImage getTexture(String name) {
        return texture.get(name);
    }

    private void loadTexture() {
        String path = "/res/texture";
        BufferedImage image = null;
        String name;
        File[] fileList;
        File folder;

        folder = new File(path);
        fileList = folder.listFiles();

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
