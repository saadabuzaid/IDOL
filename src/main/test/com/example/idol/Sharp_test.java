package com.example.idol;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Sharp_test {
    Sharpener sharpTest;
    float[] sharpArray= {-0.1f,-0.1f,-0.1f,
            -0.1f,2.5f,-0.1f,
            -0.1f,-0.1f,-0.1f};

    @BeforeEach
    void Initialise() throws  IOException{
        sharpTest = new Sharpener(ImageIO.read(new File("src\\main\\resources\\Text_add_blank.jpg")));
    }



    @Test
    void SharpTest() throws IOException {
        assertTrue(Compare_BuferredImage.compare(sharpTest.imgSharp(),ImageIO.read(new File("src\\main\\resources\\Sharp_test.jpg"))));
    }

    @Test
    void SharpkernelTest(){
        int height = sharpTest.sharpKernel.getHeight();
        int width = sharpTest.sharpKernel.getWidth();
        float [] array = new float[9];
        sharpTest.sharpKernel.getKernelData(array);
        assertEquals(height, 3 ,"height correct");
        assertEquals(width, 3 ,"width correct");
        assertTrue(Arrays.equals(array, sharpArray),"array contents correct");

    }

}
