package com.example.idol;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class PreviewSlider extends Slider{
    int selection= 0;
    BufferedImage preview;
    String previewMethod;
    JButton confirmBtn = new JButton("Confirm");
    JButton cancel = new JButton("Cancel");
    JButton[] options = {confirmBtn, cancel};
    JColorChooser colourPicker = new JColorChooser();
    BufferedImage borderPreview;
    final BufferedImage[] textPreview = new BufferedImage[1];

    int xscale, yscale;
    double imageScale;

    private int localPosX = 100, localPosY = 100;

    final JComboBox<String> dropdownFonts = new JComboBox<String>(new String[]{"Arial","Times New Roman"});
    final JComboBox<String> dropdownStyle = new JComboBox<String>(new String[]{"Plain","Bold","Italics", "Bold & Italic"});

    String text;

    PreviewSlider(int min, int max , int start, int step, String role, BufferedImage preview){
        super(min, max, start, step, role);
        if (role.contains("text"))
            this.text = role.substring(10,role.length());
        if (preview.getWidth() > preview.getHeight())
            imageScale = 300f/preview.getWidth();
        else
            imageScale = 300f/preview.getHeight();
        xscale = (int)(preview.getWidth()*imageScale); //xs = pg * is; pg = xs / is
        yscale = (int)(preview.getHeight()*imageScale);
        this.previewMethod = previewMethod;
        this.preview = preview;
    }

    @Override
    int return_value(){
        JFrame parent = new JFrame();
        JOptionPane optionPane = new JOptionPane("a", JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION, null, options);;
        JSlider slider = getSlider();

        int value = start;
        ImageIcon ic = new ImageIcon(preview);
        ChangeListener changeListener = new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {
                JSlider theSlider = (JSlider) changeEvent.getSource();
                if (!theSlider.getValueIsAdjusting() || !(info.contains("blur"))) {
                    if (info.contains("hue")) {
                        optionPane.setMessage(new Object[]{info, theSlider, "Current value: " + theSlider.getValue(), new ImageIcon(HueShifter.getShiftedHue(resizeImage(preview,xscale,yscale), theSlider.getValue())),confirmBtn, cancel});
                    } else if (info.contains("blur")) {
                        BufferedImage blurredPreview = HueShifter.cloneBufferedImage(preview);
                        Blurrer blur = new Blurrer(blurredPreview);

                        for (int i = 0; i < theSlider.getValue(); i++) {
                            blurredPreview = blur.imgBlur();
                        }
                        optionPane.setMessage(new Object[]{info, theSlider, "Current value: " + theSlider.getValue(), new ImageIcon(resizeImage(blurredPreview,xscale,yscale)),confirmBtn, cancel});
                    } else if (info.contains("border")) {
                        final Color[] color = {Color.black};

                        colourPicker.getSelectionModel().addChangeListener(new ChangeListener() {
                            @Override
                            public void stateChanged(ChangeEvent arg0) {
                                color[0] = colourPicker.getColor();
                                displayBorderPreview(theSlider, optionPane);
                            }
                        });

                        displayBorderPreview(theSlider, optionPane);

                    } else if (info.contains("text")) {
                        textPreview[0] = HueShifter.cloneBufferedImage(preview);


                        ImageIcon img = new ImageIcon(resizeImage(preview,xscale,yscale));

                        JLabel imgContainer = new JLabel();
                        imgContainer.setIcon(new ImageIcon(resizeImage(preview,xscale,yscale)));
                        imgContainer.setOpaque(false);
                        imgContainer.addMouseListener(new MouseListener() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                            }
                            @Override
                            public void mousePressed(MouseEvent e) { }
                            @Override
                            public void mouseReleased(MouseEvent e) {
                                localPosX = (int) (e.getX() / imageScale);
                                localPosY = (int) (e.getY() /imageScale);
                                displayTextPreview(theSlider, imgContainer, optionPane);
                            }
                            @Override
                            public void mouseEntered(MouseEvent e) { }
                            @Override
                            public void mouseExited(MouseEvent e) { }
                        });


                        colourPicker.getSelectionModel().addChangeListener(new ChangeListener() {
                            @Override
                            public void stateChanged(ChangeEvent arg0) {
                                displayTextPreview(theSlider,imgContainer,optionPane);
                            }
                        });

                        ActionListener update = new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                displayTextPreview(theSlider, imgContainer, optionPane);
                            }
                        };
                        dropdownFonts.addActionListener(update);
                        dropdownStyle.addActionListener(update);

                        displayTextPreview(theSlider, imgContainer, optionPane);

                    } else
                        optionPane.setMessage(new Object[]{info, theSlider, "Current value: " + theSlider.getValue(), new ImageIcon(resizeImage(preview,xscale,yscale)) ,confirmBtn, cancel});

                }
            }
        };

        confirmBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selection =1;
                parent.setVisible(false);
            }
        } );
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selection =0;
                parent.setVisible(false);
            }
        } );

        slider.addChangeListener(changeListener);

        // Set default color chooser as HSV
        ArrayList<AbstractColorChooserPanel> chooserType = new ArrayList<>(Arrays.asList(colourPicker.getChooserPanels()));
        chooserType.remove(0);
        colourPicker.setChooserPanels(chooserType.toArray(new AbstractColorChooserPanel[chooserType.size()]));

        colourPicker.setPreviewPanel(new JPanel()); // Remove preview
        colourPicker.setColor(Color.black);
        if (info.contains("colour")) {
            optionPane.setMessage(new Object[]{info, slider, "Current value: ", start, new ImageIcon(resizeImage(preview,xscale,yscale)), confirmBtn, cancel, colourPicker});
        } else if (info.contains("text")) {
            optionPane.setMessage(new Object[]{info, "Font Size: " + start, slider, new ImageIcon(resizeImage(preview, xscale, yscale)), confirmBtn, cancel, colourPicker});
        } else
            optionPane.setMessage(new Object[]{info, slider, "Current value: " + start, new ImageIcon(resizeImage(preview,xscale,yscale)),confirmBtn, cancel});
        changeListener.stateChanged(new ChangeEvent(slider));

        optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);

        JDialog dialog = new JDialog();
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        if (info.contains("hue")) {
            dialog = optionPane.createDialog(parent, "Hue Slider");
        } else if (info.contains("blur")) {
            dialog = optionPane.createDialog(parent, "Blur Slider");
        } else if (info.contains("border")) {
            dialog = optionPane.createDialog(parent, "Border Setting");
        } else {
            dialog = optionPane.createDialog(parent, info);
        }

        dialog.setVisible(true);
        if(selection==1 ){
            return slider.getValue();
        } else
            return 0;
    }

    // Implemented for border and add text only
    BufferedImage returnImage() {
        if (info.contains("border"))
            return borderPreview;
        if (info.contains("text"))
            return textPreview[0];
        else
            return preview;
    }


    private void displayBorderPreview(JSlider theSlider, JOptionPane optionPane) {
        borderPreview = HueShifter.cloneBufferedImage(preview);
        int borderSize = theSlider.getValue();
        ImageBorder imageBorder = new ImageBorder(borderPreview,borderSize, colourPicker.getColor());
        for(int i=0; i<borderSize; i++)
            borderPreview = imageBorder.addBorder();
        optionPane.setMessage(new Object[]{info, theSlider, "Current value: " + theSlider.getValue(), new ImageIcon(resizeImage(borderPreview,xscale,yscale)),confirmBtn, cancel, colourPicker});
    }

    private void displayTextPreview(JSlider theSlider, JLabel imgContainer, JOptionPane optionPane) {
        JPanel vertPane = new JPanel();
        vertPane.add(new JLabel("Font:"));
        vertPane.add(new JLabel("Style:"));
        vertPane.add(dropdownFonts);
        vertPane.add(dropdownStyle);
        vertPane.setLayout(new GridLayout(3,2));
        textPreview[0] = HueShifter.cloneBufferedImage(preview);
        TextAdder finalTA = new TextAdder();
        finalTA.addText(textPreview[0],text,dropdownStyle.getSelectedItem().toString(),dropdownFonts.getSelectedItem().toString(),theSlider.getValue(), colourPicker.getColor(), localPosX, localPosY);
        imgContainer.setIcon(new ImageIcon(resizeImage(HueShifter.cloneBufferedImage(textPreview[0]),xscale,yscale)));
        optionPane.setMessage(new Object[]{vertPane, "\nFont Size: " + theSlider.getValue(),theSlider,"\nClick on image and release to choose text location.",imgContainer, colourPicker, confirmBtn, cancel});
    }

    // the following resizeImage method was taken from https://www.baeldung.com/java-resize-image
    static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight){
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, originalImage.getType());
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }
}
