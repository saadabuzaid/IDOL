package com.example.idol;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;


public class Blurrer {
    BufferedImage img;

    Kernel blurKernel = new Kernel(3, 3, new float[] {
            (1f / 9f), (1f / 9f), (1f / 9f),
            (1f / 9f), (1f / 9f), (1f / 9f),
            (1f / 9f), (1f / 9f), (1f / 9f)
    });


    Blurrer(BufferedImage img) {
        this.img = img;
    }

    public BufferedImage imgBlur() {
        BufferedImageOp op = new ConvolveOp(blurKernel);
        img = op.filter(img, null);
        return img;
    }




}
