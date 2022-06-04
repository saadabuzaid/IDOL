package com.example.idol;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

class ImageVersionTest {
    ImageVersion firstVersion;

    @BeforeEach
    void createTests() {
        try {
            this.firstVersion = new ImageVersion(ImageLoader.loadImage("src/main/resources/image.jpg"));
        } catch (IOException ignored) {
            Assertions.fail();
        }
    }

    @Test
    void createNewVersion() {
        try {
            BufferedImage newImage = ImageLoader.loadImage("src/main/resources/image.jpg");
            int originalColour = newImage.getRGB(1, 1);

            newImage.setRGB(1, 1, Color.BLACK.getRGB());
            this.firstVersion.createNewVersion(newImage);

            Assertions.assertEquals(this.firstVersion.getImage().getRGB(1, 1), originalColour);

            Assertions.assertEquals(this.firstVersion.getNextVersionImage().getRGB(1, 1), Color.BLACK.getRGB());
        } catch (IOException ignored) {
            Assertions.fail();
        }

    }
}