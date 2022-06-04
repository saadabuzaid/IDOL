package com.example.idol;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ImageSaverTest {
    Image image;

    @BeforeEach
    void start() {
        try {
            image = ImageLoader.loadImage("src/main/resources/image.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testSaveJPG() throws IOException {
        String savePath = "src/main/resources/saver/";

        ImageSaver.exportImage(savePath, "b", ImageIO.read(new File("src/main/resources/image.jpg")), "jpg");


        assertTrue( new File(savePath + "b.jpg").exists()); // && !f.isDirectory()

    }

    @Test
    public void testSavePNG() throws IOException {
        String savePath = "src/main/resources/saver/";

        ImageSaver.exportImage(savePath, "b", ImageLoader.loadImage("src/main/resources/image.jpg"), "png");

        File f = new File(savePath + "b.png");
        assertTrue(f.exists()); // && !f.isDirectory()

    }
}
