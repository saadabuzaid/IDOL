package com.example.idol;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class ImageVersion {
    public static ImageVersion firstVersion;
    public static ImageVersion lastVersion;
    private final BufferedImage image;
    private ImageVersion nextVersion;
    private ImageVersion previousVersion;

    public ImageVersion(BufferedImage image) {
        this.image = deepCopyBufferedImage(image);
        this.previousVersion = null;

        ImageVersion.lastVersion = this;
        ImageVersion.firstVersion = this;
    }

    private ImageVersion(BufferedImage image, ImageVersion previousVersion) {
        this.image = deepCopyBufferedImage(image);
        System.out.println(image);
        this.previousVersion = previousVersion;
        this.nextVersion = null;

        previousVersion.nextVersion = this;
    }

    public void createNewVersion(BufferedImage image) {
        if (this.nextVersion == null) {
            ImageVersion.lastVersion = new ImageVersion(image, ImageVersion.lastVersion);
        } else {
            ImageVersion.lastVersion = new ImageVersion(image, this);
        }
    }

    public ImageVersion getNextVersion() {
        return this.nextVersion;
    }

    public BufferedImage getNextVersionImage() {
        return getNextVersion().image;
    }

    public ImageVersion getPreviousVersion() {
        return this.previousVersion;
    }

    public BufferedImage getPreviousVersionImage() {
        return getPreviousVersion().image;
    }

    public BufferedImage getImage() {
        return this.image;
    }

    private BufferedImage deepCopyBufferedImage(BufferedImage image) {
        ColorModel colorModel = image.getColorModel();
        boolean isAlphaPremultiplied = colorModel.isAlphaPremultiplied();
        WritableRaster raster = image.copyData(image.getRaster().createCompatibleWritableRaster());
        System.out.println(raster);

        return new BufferedImage(colorModel, raster, isAlphaPremultiplied, null);
    }
}
