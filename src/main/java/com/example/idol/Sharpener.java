package com.example.idol;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class Sharpener {
    Kernel sharpKernel = new Kernel(3, 3,
            new float[]{-0.1f,-0.1f,-0.1f,
                    -   0.1f,2.5f,-0.1f,
                    -0.1f,-0.1f,-0.1f} );
    BufferedImage img;

    Sharpener(BufferedImage img) {
        this.img = img;
    }

    public BufferedImage imgSharp() {
        BufferedImageOp op = new ConvolveOp(sharpKernel);
        img = op.filter(img, null);
        return img;
    }
}