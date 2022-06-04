package com.example.idol;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


import static org.junit.jupiter.api.Assertions.*;

class FlipTest {
    Flip flip;


    @BeforeEach
    void setup() throws IOException {
        this.flip = new Flip(ImageIO.read(new File("src\\main\\resources\\Flip_test.jpg")));
    }

    @Test
    void HorizontalFlipTest() throws IOException {
        assertTrue(Compare_BuferredImage.compare(this.flip.horizontalFlipped(),ImageIO.read(new File("src\\main\\resources\\Horizontal_flip.jpg"))));
    }
    @Test
    void VerticalFlipTest() throws IOException {

        assertTrue(Compare_BuferredImage.compare(this.flip.verticalFlipped(),ImageIO.read(new File("src\\main\\resources\\Vertical_test.jpg"))));
    }
    @Test
    void InvertTest() throws IOException {
        assertTrue(Compare_BuferredImage.compare(this.flip.invert(),ImageIO.read(new File("src\\main\\resources\\Colour_invert.jpg"))));
    }

}