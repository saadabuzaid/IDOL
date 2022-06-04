package com.example.idol;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.shape.Rectangle;
import javafx.stage.DirectoryChooser;

import static java.awt.Color.black;

public class IDOLController {

    @FXML
    private ImageView myImage;

    BufferedImage workingCopy;              //the buffered image copy that will be used in the whole project for effects and exports

    private static final String BASE_PATH = "/images/fimage";

    private ImageVersion currentVersion;



    public void updateImage(){
        myImage.setImage(SwingFXUtils.toFXImage(workingCopy, null));
    }

    public void updateVersion(){
        currentVersion.createNewVersion(workingCopy);
        System.out.println(currentVersion);
        currentVersion = currentVersion.getNextVersion();
        System.out.println(currentVersion);

    }

    public void setInitialVersion(){currentVersion = new ImageVersion(workingCopy);}

    static String DropBox(String[] choices,String text) {
        try {
            // code to create a simple drop down box selector.
            String s = (String) JOptionPane.showInputDialog(
                    null,
                    text,
                    "Selection",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    choices,
                    choices[0]);
            return s;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    @FXML
    protected void onImportClick() {
        JFileChooser fc = new JFileChooser(BASE_PATH);
        fc.setFileFilter(new JPGfilter());
        int res = fc.showOpenDialog(null);
        try {
            if (res == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                Image image = new Image(file.toURI().toString());
                myImage.setImage(image);
                workingCopy = SwingFXUtils.fromFXImage(myImage.getImage(), null);
                setInitialVersion();
            }
            else {
                JOptionPane.showMessageDialog(null,
                        "You must select one image to be the reference.", "Aborting...",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }


    @FXML
    protected void onAddTextClick() throws FontFormatException {
//        String[] sizes = {"10","20","30","40","50","60","70","80","90","100"};
//        String[] Fonts  = {"Arial","Times New Roman"};
//        String[] MODS  = {"BOLD","Italic"};
        String Text = JOptionPane.showInputDialog("Enter your text to be added: ");
//        String size = DropBox(sizes,"select a size: ");
//        String Font = DropBox(Fonts,"select a font: ");
//        String MOD = DropBox(MODS,"select bold or italic:");
//        Color Colour = colorPicker();
//        JOptionPane.showMessageDialog(null,
//                "please click on the image where you would like the text to go.", "Location",JOptionPane.PLAIN_MESSAGE);
//
//        int Size = Integer.parseInt(size);
//        final int[] X = new int[1];
//        final int[] Y = new int[1];
//        TextAdder TA = new TextAdder();
//        TextAdder finalTA = TA;
//        myImage.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                X[0] = (int)event.getX();
//                Y[0] = (int)event.getY();
//
//                finalTA.addText(workingCopy,Text,MOD,Font,Size,Colour, X[0], Y[0]);
//                updateImage();
//                myImage.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
//            }
//        });
        int step = (workingCopy.getHeight()/100)*10;
        PreviewSlider textPreview= new PreviewSlider(0,step*10,step*2,step,"Add text: " + Text, workingCopy);
        if (textPreview.return_value() != 0) {
            workingCopy = textPreview.returnImage();
            updateImage();
        }
        updateVersion();
    }


    @FXML
    protected void onBlurredButtonClick() {
        Slider blurSlider= new PreviewSlider(0,100,0,25,"Select blur multiplier", workingCopy);
        int Blur_mult = blurSlider.return_value();
        Blurrer blur = new Blurrer(workingCopy);
        for (int i = 0; i < Blur_mult ;i++) {
            workingCopy = blur.imgBlur();

        }
        if(Blur_mult>0){
            updateVersion();
        }
        updateImage();

    }

    @FXML
    protected void OnSharpClick(){
        Sharpener sharp = new Sharpener(workingCopy);
        workingCopy = sharp.imgSharp();
        updateImage();
        updateVersion();
    }

    @FXML
    protected void onExportClick() throws IOException {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(null);
        String dir = selectedDirectory.getAbsolutePath();
        String saveDirectory = dir.replace('\\', '/')+"/";

        String fileName = JOptionPane.showInputDialog("Chose a file name: ").trim();
        if(fileName.length()==0 || fileName==""){
            fileName = "Untitled";
        }

        String[] formats = { "GIF", "PNG","jpg"};
        String format = DropBox(formats,"select a format: ");

        ImageSaver.exportImage(saveDirectory, fileName, workingCopy,format);
    }

    @FXML
    protected void onCropCLick() throws InterruptedException {
        DragSelect dragSelect = new DragSelect(this);
        dragSelect.start(myImage);
    }

    public void applyCrop(Rectangle rec) {
        int X = (int) rec.getX();
        int Y = (int) rec.getY();
        int Width = (int) rec.getWidth();
        int Height = (int) rec.getHeight();
        CroppingImage crop = new CroppingImage(workingCopy);
        workingCopy = crop.cropImg(X, Y, Width, Height);
        System.out.println(workingCopy);
        updateImage();
        updateVersion();
    }

    @FXML
    protected void onAboutClick(){
        JOptionPane.showMessageDialog(null,
                "This program is for importing, editing and exporting images. you can blur, add text and crop as well as export in multiple different formats.", "About",
                JOptionPane.PLAIN_MESSAGE);
    }

    @FXML
    protected void onChangeColour(){
        ChangeColour changeColor = new ChangeColour(workingCopy);
        workingCopy = changeColor.changeColour();
        System.out.println(workingCopy);
        updateImage();
        updateVersion();
    }

    @FXML
    protected void onBorder()throws FontFormatException{
        PreviewSlider borderSlider = new PreviewSlider(0,50,0,5,"Set border size and colour", workingCopy);
        int borderSize = borderSlider.return_value();
        if (borderSize != 0){
            workingCopy = borderSlider.returnImage();
            updateVersion();
        }
        updateImage();

    }


    @FXML
    protected void onHorizontalFlipClick() {
        workingCopy = new Flip(workingCopy).horizontalFlipped();
        updateImage();
        updateVersion();
    }
    @FXML
    protected void onVerticalFLipClick() {
        workingCopy = new Flip(workingCopy).verticalFlipped();
        updateImage();
        updateVersion();
    }

    @FXML
    protected  void onInvertClick() {
        workingCopy = new Flip(workingCopy).invert();
        updateImage();
        updateVersion();
    }
    @FXML
    protected void onClockwiseRotateClick() {
        Rotate rotate = new Rotate(workingCopy);
        workingCopy = rotate.rotateClockwise90();
        updateImage();
        updateVersion();
    }
    @FXML
    protected void onCounterClockwiseRotateClick() {
        Rotate rotate = new Rotate(workingCopy);
        workingCopy = rotate.rotateCounterClockwise90();
        updateImage();
        updateVersion();
    }
    @FXML
    protected void onNewImageClick() throws FontFormatException {
        //Create new blank single color image to be drawn on
        ImageGenerator generator = new ImageGenerator();
        workingCopy = SwingFXUtils.fromFXImage(generator.generator(500,500,0, 177.0 / 255, 65.0 / 255, 1), null);
        updateImage();
        TextAdder TA = new TextAdder();
        TA.addText(workingCopy,"","BOLD","Arial",1,black,0,0);
        setInitialVersion();
        updateImage();
    }

    @FXML
    protected void onChangeHue(){
        PreviewSlider hueSlider = new PreviewSlider(0,360,0,60,"Change hue", workingCopy);

        int hueShift = hueSlider.return_value();

        workingCopy = HueShifter.getShiftedHue(workingCopy, hueShift);
        if (hueShift> 0 ){
            updateVersion();}
        updateImage();
    }

    @FXML
    protected void OnTestClick(){
        Slider slider = new Slider(0,360,180,60,"test");
        System.out.println(slider.return_value());

    }

    @FXML
    protected void onUndo(){
        if (currentVersion == null) {return;}

        if(currentVersion.getPreviousVersion() != null){
            currentVersion = currentVersion.getPreviousVersion();
            workingCopy = currentVersion.getImage();
            updateImage();
        }
    }

    @FXML
    protected void onRedo(){
        if (currentVersion == null) {return;}

        if(currentVersion.getNextVersion() != null){
            currentVersion = currentVersion.getNextVersion();
            workingCopy = currentVersion.getImage();
            updateImage();
        }
    }
    protected static Color colorPicker() throws FontFormatException{
        String[] Colours = {"black","white","blue","red"};
        String Colour = DropBox(Colours,"select a color: ");
        int mod;
        Color color;
        try {
            Field field = Class.forName("java.awt.Color").getField(Colour);
            color = (Color)field.get(null);
        } catch (Exception e) {
            color =null; // Not defined
            e.printStackTrace();
        }

        return color;
    }


    @FXML
    protected void onDraw(){
//        updateVersion();
        Draw dt = new Draw(this);
        dt.Start(myImage);
        myImage = myImage;
//        updateImage();
    }


}