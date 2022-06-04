package com.example.idol;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;

import static org.junit.jupiter.api.Assertions.*;


class ImageBorderTest {

    @Test
    void addBorder() throws IOException {
        BufferedImage image =  ImageIO.read(new File("src\\main\\resources\\image.jpg"));
        ImageBorder imageBorder = new ImageBorder(image, 20, Color.BLACK);
        assertTrue(Compare_BuferredImage.compare(imageBorder.addBorder(), ImageIO.read(new File("src\\main\\resources\\AddBorder_test.jpg"))));

    }

}
