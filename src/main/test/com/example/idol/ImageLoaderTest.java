package com.example.idol;

import org.junit.jupiter.api.Test;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ImageLoaderTest {

    @Test
    public void testHeight(){
        BufferedImage image;
        try {
            image = ImageLoader.loadImage("src/main/resources/image.jpg");
            assertEquals(183, image.getHeight());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWidth(){
        BufferedImage image;
        try {
            image = ImageLoader.loadImage("src/main/resources/image.jpg");
            assertEquals(275, image.getWidth());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFileExists(){
        File file = new File("src/main/resources/image.jpg");
        assert file.exists();
    }

    @Test
    public void testExtension(){
        String path = "src/main/resources/image.jpg";
        String[] parts = path.split("\\.");
        String extension = parts[parts.length-1];
        assertEquals("jpg", extension);
    }

}