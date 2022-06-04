package com.example.idol;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Test extends Application {

    private double mouseDownX ;
    private double mouseDownY ;
    private ImageView Img;

    @Override
    public void start(Stage primaryStage) {
        Rectangle selectionRectangle = new Rectangle();
        selectionRectangle.setStroke(Color.BLACK);
        selectionRectangle.setFill(Color.TRANSPARENT);
        selectionRectangle.getStrokeDashArray().addAll(5.0, 5.0);

        Pane pane = new Pane();
        pane.setMinSize(600, 600);

        pane.getChildren().add(selectionRectangle);

        pane.setOnMousePressed(e -> {
            mouseDownX = e.getX();
            mouseDownY = e.getY();
            selectionRectangle.setX(mouseDownX);
            selectionRectangle.setY(mouseDownY);
            selectionRectangle.setWidth(0);
            selectionRectangle.setHeight(0);
        });

        pane.setOnMouseDragged(e -> {
            selectionRectangle.setX(Math.min(e.getX(), mouseDownX));
            selectionRectangle.setWidth(Math.abs(e.getX() - mouseDownX));
            selectionRectangle.setY(Math.min(e.getY(), mouseDownY));
            selectionRectangle.setHeight(Math.abs(e.getY() - mouseDownY));




        });
        pane.setOnMouseReleased(event->{
            System.out.println(selectionRectangle.getX()+" "+ selectionRectangle.getY()+" "+ selectionRectangle.getHeight()+" "+ selectionRectangle.getWidth());
        });

        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}