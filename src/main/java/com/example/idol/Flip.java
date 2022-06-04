package com.example.idol;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;


public class Flip {
    private BufferedImage image;

    Flip(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage flipping(int x, int y, int z) {
        AffineTransform tran;
        BufferedImage flipped = new BufferedImage(
                this.image.getWidth(),
                this.image.getHeight(),
                this.image.getType()
        );

        if (z == 1) {
            tran = AffineTransform.getTranslateInstance(this.image.getWidth(), 0);
        } else {
            tran = AffineTransform.getTranslateInstance(0, this.image.getHeight());
        }

        AffineTransform flip = AffineTransform.getScaleInstance(x, y);
        tran.concatenate(flip);
        Graphics2D g = flipped.createGraphics();
        g.setTransform(tran);
        g.drawImage(image, 0, 0, null);
        return flipped;
    }

    public BufferedImage horizontalFlipped() {
        return flipping(1, -1, 0);
    }

    public BufferedImage verticalFlipped() {
        return flipping(-1, 1, 1);
    }

    public BufferedImage invert() {
        for (int x = 0; x < this.image.getWidth(); x++) {
            for (int y = 0; y < this.image.getHeight(); y++) {
                int rgba = image.getRGB(x, y);
                Color col = new Color(rgba, true);
                col = new Color(255 - col.getRed(),
                        255 - col.getGreen(),
                        255 - col.getBlue());
                this.image.setRGB(x, y, col.getRGB());
            }
        }
        return this.image;
    }

}
