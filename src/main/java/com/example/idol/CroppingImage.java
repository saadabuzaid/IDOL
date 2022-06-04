package com.example.idol;

import java.awt.image.BufferedImage;

public class CroppingImage {
    BufferedImage image;

    CroppingImage(BufferedImage image) {
        this.image = image;
    }

    /**
    * cropImg() crops image with specified parameters.
    *
    * @param upperX: The starting point X-coordinate where you will start cropping the image
    * @param upperY: The starting point Y-coordinate where you will start cropping the image
    * @param width: The width from starting point that will be cropped
    * @param height: The height from starting point that will be cropped
    * @return BufferedImage that is the cropped image
    * @see "You need to write the returned file to see the output"
     * @throws java.awt.image.RasterFormatException if the coordinates added exceeds the original image it , thus check the original image size.
    */
    public BufferedImage cropImg(int upperX, int upperY, int width, int height) {
        return image.getSubimage(upperX, upperY, width, height);
    }
}
