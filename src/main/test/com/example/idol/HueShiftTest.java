package com.example.idol;


import org.junit.jupiter.api.Test;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


import static org.junit.jupiter.api.Assertions.*;



class HueShiftTest {

     //Shifting a red image's hue by 100 degreees should make it green
    @Test
    void greenTest() throws IOException {
        BufferedImage image = ImageLoader.loadImage("src/main/resources/red.jpg");
        BufferedImage greenImage = HueShifter.getShiftedHue(image, 100);
        assertTrue(new Color(greenImage.getRGB(10,10)).getGreen() > 250);
    }

    // Shifting a red image's hue by 250 degreees should make it blue
    @Test
    void blueTest() throws IOException {
        BufferedImage image = ImageLoader.loadImage("src/main/resources/red.jpg");
        BufferedImage greenImage = HueShifter.getShiftedHue(image, 250);
        assertTrue(new Color(greenImage.getRGB(10,10)).getBlue() > 250);
    }

}