package com.example.cybookfx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    private Button buttonOverdueBook;
    @FXML
    private Button buttonMenu;
    @FXML
    private Button buttonNew;

    @FXML
    protected void onOverdueBookButtonClick() {
        loadPage("overduebook-view.fxml");
    }

    @FXML
    protected void onMenuButtonClick() {
        loadPage("searchbook-view.fxml");
    }

    @FXML
    protected void onNewButtonClick() {
        loadPage("userconsult-view.fxml");
    }

    private void loadPage(String fxmlFile) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) buttonOverdueBook.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
