package com.example.idol;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageSaver {
    public static void exportImage(String path, String filename, BufferedImage image, String extension) {
        BufferedImage bi  = image;

        // Remove transparency for jpegs
        if (extension == "jpg" || extension == "jpeg") {
            bi = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics copier = bi.getGraphics();
            copier.drawImage(image, 0, 0, null);
            copier.dispose();
        }

        File outputfile = new File(path + filename + "." + extension);
        try {
            ImageIO.write(bi, extension, outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //not needed anymore as javafx module is fixed
    /*public static void convertToFxImage(Image image) {
        WritableImage wr = null;
        PixelReader pr = image.getPixelReader();


        BufferedImage bufferedImage = new BufferedImage((int) image.getWidth(), (int)image.getHeight(), 3);
        for (int x = 0; x < image.getWidth(); x++)
            for (int y = 0; y < image.getHeight(); y++)
                bufferedImage.setRGB(x, y, pr.getArgb(x, y));


        String savePath = "C:\\Users\\Me\\Documents\\";
        ImageSaver.exportImage(savePath, "b", bufferedImage, "png");

//        return new BufferedImage();



    }*/
}
