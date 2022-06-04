package com.example.idol;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageBorder {
    public BufferedImage bufferedImage;
    public  int borderSize;
    public  java.awt.Color color;

    public ImageBorder(BufferedImage bufferedImage, int size, java.awt.Color color) {
        this.bufferedImage = bufferedImage;
        this.borderSize = size;
        this.color = color;
    }

    public BufferedImage addBorder() {
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setPaint(color);
        graphics.fillRect(0,0,bufferedImage.getWidth(),borderSize);
        graphics.fillRect(0,bufferedImage.getHeight()-borderSize, bufferedImage.getWidth(),borderSize);
        graphics.fillRect(0, 0, borderSize, bufferedImage.getHeight());
        graphics.fillRect(bufferedImage.getWidth() - borderSize, 0, borderSize, bufferedImage.getHeight());

        return bufferedImage;
    }
}
