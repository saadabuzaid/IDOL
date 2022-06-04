package com.example.idol;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class text_add_test {

    @Test
    void AddTextTest() throws IOException, FontFormatException {
        BufferedImage image =  ImageIO.read(new File("src\\main\\resources\\Text_add_blank.jpg"));
        TextAdder ta =new TextAdder();
        ta.addText(image,"yeet","BOLD","Arial",10,Color.WHITE,20,20);
        assertTrue(Compare_BuferredImage.compare(image, ImageIO.read(new File("src\\main\\resources\\Add_text_test.jpg"))));
    }
}
