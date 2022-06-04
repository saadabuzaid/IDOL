package com.example.idol;

import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;


import java.util.Arrays;

public class ImageGenerator {

    public Image generator(int width, int height, double red, double green , double blue, double opacity){
            WritableImage blank = new WritableImage(width, height);
            PixelWriter pw = blank.getPixelWriter();

            int alpha = (int) (opacity * 255) ;
            int r = (int) (red * 255) ;
            int g = (int) (green * 255) ;
            int b = (int) (blue * 255) ;

            int pixel = (alpha << 24) | (r << 16) | (g << 8) | b ;
            int[] pixels = new int[width * height];
            Arrays.fill(pixels, pixel);

            pw.setPixels(0, 0, width, height, PixelFormat.getIntArgbInstance(), pixels, 0, width);
            return blank ;
        }
    }


