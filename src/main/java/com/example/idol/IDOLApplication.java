package com.example.idol;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;



public class IDOLApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(IDOLApplication.class.getResource("IDOL-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 300);
        stage.setMaximized(true);
        stage.setTitle("IDOL");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}