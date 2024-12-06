package com.example.uniapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainPageApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("admin-page.fxml")));
        Scene scene = new Scene(root);
        primaryStage.setFullScreen(true);
        primaryStage.setTitle("UniApplication");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
