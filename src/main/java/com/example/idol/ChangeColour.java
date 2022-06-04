package com.example.idol;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.image.BufferedImage;

public class ChangeColour {
    public BufferedImage bufferedImage;

    public ChangeColour(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public BufferedImage changeColour() {
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);

        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setHue(0);
        colorAdjust.setSaturation(-1);
        colorAdjust.setBrightness(0);
        colorAdjust.setContrast(0);

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(image.getWidth());
        imageView.setPreserveRatio(true);
        imageView.setEffect(colorAdjust);

        bufferedImage = SwingFXUtils.fromFXImage(imageView.snapshot(null, null), null);
        return bufferedImage;
    }






}
