package com.example.uniapplication;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SwitchScene implements SwitchInterface {
    protected Stage currentStage;
    protected Stage newStage;
    protected FXMLLoader loader;
    protected Parent root;


    @Override
    public void switchScene(Button button, String resource) throws IOException {
        currentStage = (Stage) button.getScene().getWindow();
        currentStage.hide();

        loader = new FXMLLoader(getClass().getResource(resource));
        root = loader.load();

        newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.showAndWait();
    }
}
