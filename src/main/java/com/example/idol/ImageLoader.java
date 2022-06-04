package com.example.idol;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {

    public static BufferedImage loadImage(String pathName) throws IOException {
        BufferedImage img = null;
        img = ImageIO.read(new File(pathName));
        return img;
    }


}
