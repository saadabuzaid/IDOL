package com.example.idol;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;

public class Compare_BuferredImage {

    static boolean compare(BufferedImage img1, BufferedImage img2) {
        Raster var1 = img1.getData();
        Raster var2 = img2.getData();
        int Data1 = (var1.getDataBuffer()).getSize();
        int Data2 = (var2.getDataBuffer()).getSize();
        if (Data1 == Data2)
        {
            return true;
        }
        else
        {
            return false;
        }

    }

}
