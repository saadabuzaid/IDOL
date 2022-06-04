package com.example.idol;

import java.awt.*;
import java.awt.image.BufferedImage;


public class TextAdder {


    public void addText(BufferedImage img, String text, String MOD, String Style, int size, Color colour, int x, int y) {
        int mod;
        if (MOD.equals("Bold")){
            mod = 1;
        } else if (MOD.equals("Italics")){
            mod = 2;
        } else if (MOD.equals("Bold & Italic")){
            mod = 3;
        } else
            mod = 0;
        Font font = new Font(Style,mod, size);

        Graphics g = img.getGraphics();
        g.setFont(font);
        g.setColor(colour);
        g.drawString(text, x,y );
    }
}
