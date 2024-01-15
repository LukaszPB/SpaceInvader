package com.example.projekt_ztp.Controllers;

import com.example.projekt_ztp.Main;
import com.example.projekt_ztp.StageProperties;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    @FXML
    private BorderPane borderPane;
    @FXML
    protected void newGame() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Views/gameView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), StageProperties.STAGE_WIDTH, StageProperties.STAGE_HEIGHT);
        Stage stage = (Stage) borderPane.getScene().getWindow();
        stage.setScene(scene);
    }
    @FXML
    protected void options() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Views/settingsView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), StageProperties.STAGE_WIDTH, StageProperties.STAGE_HEIGHT);
        Stage stage = (Stage) borderPane.getScene().getWindow();
        stage.setScene(scene);
    }
    @FXML
    private void quit() throws IOException {
        Stage stage = (Stage) borderPane.getScene().getWindow();
        stage.close();
    }
}