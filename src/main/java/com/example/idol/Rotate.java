package com.example.idol;


import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Rotate {
    BufferedImage image;

    Rotate(BufferedImage image){
        this.image= image;
    }

    public  BufferedImage rotateClockwise90() {
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        BufferedImage output = new BufferedImage(image.getHeight(), image.getWidth(), image.getType());

        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(Math.PI / 2, imageWidth / 2, imageHeight / 2);

        double offset = (imageWidth - imageHeight) / 2;
        affineTransform.translate(offset, offset);
        AffineTransformOp affineTransformOp = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_BILINEAR);
        affineTransformOp.filter(image, output);
        return output;
    }
    public  BufferedImage rotateCounterClockwise90() {
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        BufferedImage output = new BufferedImage(imageHeight, imageWidth, image.getType());

        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(-Math.PI / 2, imageWidth / 2, imageHeight / 2);

        double offset = (imageWidth - imageHeight) / 2;
        affineTransform.translate(-offset, -offset);

        AffineTransformOp affineTransformOp = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_BILINEAR);
        affineTransformOp.filter(image, output);

        return output;
    }
}
