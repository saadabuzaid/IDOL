package com.example.idol;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CroppingImageTest {
    BufferedImage croppedImg;

    @BeforeEach
    void setUp() throws IOException {
        CroppingImage crop = new CroppingImage(ImageIO.read(new File("src\\main\\resources\\image.jpg")));
        BufferedImage croppedImg = crop.cropImg(0, 0, 275, 100);

        this.croppedImg = croppedImg;

        ImageSaver.exportImage("src\\main\\resources\\", "croppedimage", croppedImg, ".jpg");
    }

    @Test
    void cropImg() {
        assertEquals(this.croppedImg.getHeight(),100);
        assertEquals(this.croppedImg.getWidth(),275);
    }
}