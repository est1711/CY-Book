package com.example.cybookfx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("new-view.fxml"));
        Parent newRoot = fxmlLoader.load();

        Stage stage = (Stage) welcomeText.getScene().getWindow();
        javafx.geometry.Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Scene newScene = new Scene(newRoot, screenBounds.getWidth(), screenBounds.getHeight());

        // Ajouter le fichier CSS
        String css = getClass().getResource("styles.css").toExternalForm();
        newScene.getStylesheets().add(css);

        stage.setScene(newScene);
        stage.setX(screenBounds.getMinX());
        stage.setY(screenBounds.getMinY());
        stage.setWidth(screenBounds.getWidth());
        stage.setHeight(screenBounds.getHeight());
        stage.show();
    }
}
