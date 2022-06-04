package com.example.idol;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DragSelect {
    private double mouseCoordX;
    private double mouseCoordY;
    protected static final Object sync = new Object();
    public Rectangle selectedArea = new Rectangle(0, 0, 0, 0);
    public boolean flag = false;
    Button save = new Button("Save");
    IDOLController idolController;


    //IDOLController Constructor
    DragSelect(IDOLController idolController) {
        this.idolController = idolController;
    }

    //Starts the Selection
    public Rectangle start(ImageView imageView) throws InterruptedException {
        Pane pane = (Pane) imageView.getScene().getRoot();
        Image img = imageView.getImage();
        double w = img.getWidth();
        double h = img.getHeight();
        final Canvas canvas = new Canvas(w, h);
        pane.getChildren().remove(imageView);
        pane.getChildren().add(canvas);
        pane.getChildren().add(save);

        canvas.getGraphicsContext2D().drawImage(img, imageView.getX(), imageView.getY());
        select(canvas, imageView, img, pane);


        return select(canvas, imageView, img, pane);

    }

    //Selection Loop
    public Rectangle select(Canvas canvas, ImageView imageView, Image img, Pane pane) throws InterruptedException {
        Rectangle dragRect = new Rectangle(0, 0, 0, 0);
        dragRect.setVisible(false);
        dragRect.setStroke(Color.BLACK);
        dragRect.setFill(Color.TRANSPARENT);
        dragRect.getStrokeDashArray().addAll(7.0, 7.0);

        try {
            GraphicsContext gc = canvas.getGraphicsContext2D();

            //Sets initial point
            canvas.setOnMousePressed(e -> {
                canvas.getGraphicsContext2D().drawImage(img, imageView.getX(), imageView.getY());
                dragRect.setVisible(true);
                System.out.printf("[MousePressed] mouseX: %.2f, mouseY: %.2f\n", e.getX(), e.getY());
                mouseCoordX = e.getX();
                mouseCoordY = e.getY();
                dragRect.setX(e.getX());
                dragRect.setY(e.getX());
                dragRect.setWidth(0);
                dragRect.setHeight(0);
                System.out.println(selectedArea);
            });

            canvas.setOnMouseReleased(e -> {
                dragRect.setX(Math.min(e.getX(), mouseCoordX));
                dragRect.setWidth(Math.abs(e.getX() - mouseCoordX));
                dragRect.setY(Math.min(e.getY(), mouseCoordY));
                dragRect.setHeight(Math.abs(e.getY() - mouseCoordY));
                System.out.printf("[MouseReleased] mouseX: %.2f, mouseY: %.2f\n", e.getX(), e.getY());

                //Drawing rectangle line by line
                gc.strokeLine(dragRect.getX(), dragRect.getY(), (dragRect.getX() + dragRect.getWidth()), (dragRect.getY()));
                gc.strokeLine(dragRect.getX(), dragRect.getY(), dragRect.getX(), (dragRect.getY() + dragRect.getHeight()));
                gc.strokeLine(dragRect.getX(), (dragRect.getY() + dragRect.getHeight()), (dragRect.getX() + dragRect.getWidth()), (dragRect.getY() + dragRect.getHeight()));
                gc.strokeLine((dragRect.getX() + dragRect.getWidth()), (dragRect.getY() + dragRect.getHeight()), (dragRect.getX() + dragRect.getWidth()), dragRect.getY());

            });


            save.setOnMouseClicked(e -> {
                pane.getChildren().remove(canvas);
                pane.getChildren().add(imageView);
                pane.getChildren().remove(save);

                this.selectedArea = dragRect;
                System.out.println("Saved: " + selectedArea);
                idolController.applyCrop(this.selectedArea);
                flag = true;
            });
        } catch (NullPointerException e) {
            select(canvas, imageView, img, pane);
        }

        return selectedArea;
    }
}

