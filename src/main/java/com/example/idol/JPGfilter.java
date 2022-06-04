package com.example.idol;


import javax.swing.filechooser.FileFilter;
import java.io.File;


public class JPGfilter extends FileFilter {
    public boolean accept(File f) {
        if (f.getName().toLowerCase().endsWith(".jpeg")) return true;
        if (f.getName().toLowerCase().endsWith(".jpg")) return true;
        if (f.getName().toLowerCase().endsWith(".png")) return true;
        return f.isDirectory();
    }

    public String getDescription() {
        return "JPEG or PNG files";
    }
}
