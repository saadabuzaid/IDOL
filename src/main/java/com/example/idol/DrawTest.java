package com.example.idol;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Cursor;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.Stack;

public class DrawTest {

    Stack<Canvas> imageViewStack = new Stack<>();
    Button undo = new Button("Undo");
    Button save = new Button("Save");
    Pane pane;
    Canvas canvas;

    public void Start(ImageView imageView){
        Stage stage = (Stage) imageView.getScene().getWindow();
        Pane pane =  (Pane) stage.getScene().getRoot();
        this.pane = pane;
        Drawing(imageView);
    }



    public void Drawing(ImageView imageView) {



        Image img = imageView.getImage();
        double w= img.getWidth();
        double h = img.getHeight();
        final Canvas canvas = new Canvas(w,h);
        this.canvas= canvas;
        Slider slider = new Slider(0,10,1);
        ColorPicker colorPicker = new ColorPicker(Color.BLACK);
        ToolBar toolbar = new ToolBar();

        canvas.getGraphicsContext2D().drawImage(img,imageView.getX(),imageView.getY());
        Button[] buttons = {undo,save};

        for(Button btn : buttons) {
            btn.setMinWidth(90);
            btn.setCursor(Cursor.HAND);
            btn.setTextFill(Color.WHITE);
            btn.setStyle("-fx-background-color: #80334d;");
        }
        save.setStyle("-fx-background-color: #1fb199;");


        toolbar.getItems().addAll(undo, save,colorPicker,slider);
        toolbar.setOrientation(Orientation.VERTICAL);
        toolbar.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");
        toolbar.setPadding(new Insets(5));
        toolbar.setStyle("-fx-background-color: #656565");
        onUpdate(canvas);
        pane.getChildren().remove(imageView);
        pane.getChildren().add(canvas);
        pane.getChildren().add(toolbar);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        canvas.setOnMouseEntered(e->{
            canvas.setCursor(Cursor.CROSSHAIR);
        });
        canvas.setOnMousePressed(e->{
            gc.beginPath();
            gc.setStroke(colorPicker.getValue());
            gc.moveTo(e.getX(),e.getY());
            gc.stroke();
        });

        canvas.setOnMouseDragged(e->{
            gc.setStroke(colorPicker.getValue());
            gc.setLineWidth(slider.getValue());
            gc.lineTo(e.getX(),e.getY());
            gc.stroke();
        });
        canvas.setOnMouseReleased(e->{
            onUpdate(canvas);
        });

        undo.setOnMouseClicked(e->{
            onUndo();
            System.out.println();
        });

        save.setOnMouseClicked(e->{
            pane.getChildren().remove(canvas);
            pane.getChildren().remove(save);
            pane.getChildren().remove(undo);
            pane.getChildren().remove(toolbar);

            WritableImage wi = canvas.snapshot(new SnapshotParameters(),null);
            imageView.setImage(wi);
            pane.getChildren().add(imageView);
        });






    }

    public void updateViewCanvas(Canvas currentCanvas){
        pane.getChildren().remove(1);
        pane.getChildren().add(1,currentCanvas);
    }



    public void onUpdate(Canvas canvas){
        WritableImage wi = canvas.snapshot(new SnapshotParameters(),null);
        Canvas copyCanvas = new Canvas(canvas.getWidth(),canvas.getHeight());
        copyCanvas.getGraphicsContext2D().drawImage(wi,canvas.getTranslateX(),canvas.getTranslateY());
          imageViewStack.push(copyCanvas);
      //  System.out.println(imageViewStack);

    }

    public void onUndo() {

        if (imageViewStack.indexOf(imageViewStack.lastElement())>0) {
            imageViewStack.pop();
            WritableImage wi = imageViewStack.lastElement().snapshot(new SnapshotParameters(),null);
            this.canvas.getGraphicsContext2D().drawImage(wi,imageViewStack.lastElement().getLayoutX(),imageViewStack.lastElement().getLayoutY());
            updateViewCanvas(canvas);
           // System.out.println("POPED: "+imageViewStack);
        }
    }




}
