package com.example.idol;
import org.junit.jupiter.api.Test;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Rotate_test {


    @Test
    void HorizontalFlipTest() throws IOException {
        Rotate rotate = new Rotate(ImageIO.read(new File("src\\main\\resources\\Rotate_test.jpg")));
        assertTrue(Compare_BuferredImage.compare(rotate.rotateClockwise90(),ImageIO.read(new File("src\\main\\resources\\90_clockwise.jpg"))));
    }
    @Test
    void VerticalFlipTest() throws IOException {
        Rotate rotate = new Rotate(ImageIO.read(new File("src\\main\\resources\\Rotate_test.jpg")));
        assertTrue(Compare_BuferredImage.compare(rotate.rotateCounterClockwise90(),ImageIO.read(new File("src\\main\\resources\\90_counter.jpg"))));

    }
}
