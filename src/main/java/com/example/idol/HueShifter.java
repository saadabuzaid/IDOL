package com.example.idol;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class HueShifter {
    public static BufferedImage getShiftedHue(BufferedImage img, float hue) {
        BufferedImage image = cloneBufferedImage(img);
        for( int i = 0; i < image.getWidth(); i++ )
            for( int j = 0; j < image.getHeight(); j++ ) {
                Color colRGB = new Color(image.getRGB(i, j), true);
                int alpha = colRGB.getAlpha();

                float[] colHSB = Color.RGBtoHSB(colRGB.getRed(), colRGB.getGreen(), colRGB.getBlue(), null);
                colHSB[0] += hue/360f;

                colRGB = new Color(Color.HSBtoRGB(colHSB[0],colHSB[1],colHSB[2]));
                colRGB = new Color(colRGB.getRed(),colRGB.getGreen(),colRGB.getBlue(), alpha);

                image.setRGB(i, j, colRGB.getRGB());
            }
        return image;
    }

    public static BufferedImage cloneBufferedImage(BufferedImage image) {
        BufferedImage clone = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        Graphics2D g = clone.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return clone;
    }
}
