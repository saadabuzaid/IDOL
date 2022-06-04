package com.example.idol;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class Blur_test {
    Blurrer blurtest;
    float[] blurarray= {1f/9f, 1f/9f, 1f/9f,

            1f/9f, 1f/9f, 1f/9f,

            1f/9f, 1f/9f, 1f/9f};

    @BeforeEach
    void Initialise() throws  IOException{
        blurtest = new Blurrer(ImageIO.read(new File("src\\main\\resources\\Text_add_blank.jpg")));
    }


    @Test
    void BlurTest() throws IOException {
        assertTrue(Compare_BuferredImage.compare(blurtest.imgBlur(),ImageIO.read(new File("src\\main\\resources\\Blur_test.jpg"))));
    }

    @Test
    void BlurkernelTest(){
        int height = blurtest.blurKernel.getHeight();
        int width = blurtest.blurKernel.getWidth();
        float [] array = new float[9];
        blurtest.blurKernel.getKernelData(array );
        assertEquals(height, 3 ,"height correct");
        assertEquals(width, 3 ,"width correct");
        assertTrue(Arrays.equals(array, blurarray),"array contents correct");

    }

}
