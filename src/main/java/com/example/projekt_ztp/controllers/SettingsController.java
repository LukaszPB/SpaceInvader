package com.example.projekt_ztp.controllers;

import com.example.projekt_ztp.Main;
import com.example.projekt_ztp.StageProperties;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SettingsController {
    @FXML
    private BorderPane borderPane;
    @FXML
    private Button easyButton;
    @FXML
    private Button normalButton;
    @FXML
    private Button hardButton;
    @FXML
    private Button theme1Button;
    @FXML
    private Button theme2Button;
    private int difficulty;
    private int theme;
    @FXML
    private void initialize() {
        try {
            Scanner scannerSettings = new Scanner(new File(StageProperties.SETTINGS_FILE_PATH));
            difficulty = Integer.parseInt(scannerSettings.next());
            theme = Integer.parseInt(scannerSettings.next());
            scannerSettings.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        switch (difficulty) {
            case 1 -> easy();
            case 2 -> normal();
            case 3 -> hard();
        }
        if(theme == 0) {
            theme1();
        }
        else {
            theme2();
        }
    }
    @FXML
    private void easy() {
        difficulty = 1;
        easyButton.getStyleClass().add("selectedButton");
        normalButton.getStyleClass().remove("selectedButton");
        hardButton.getStyleClass().remove("selectedButton");

    }
    @FXML
    private void normal() {
        difficulty = 2;
        easyButton.getStyleClass().remove("selectedButton");
        normalButton.getStyleClass().add("selectedButton");
        hardButton.getStyleClass().remove("selectedButton");
    }
    @FXML
    private void hard() {
        difficulty = 3;
        easyButton.getStyleClass().remove("selectedButton");
        normalButton.getStyleClass().remove("selectedButton");
        hardButton.getStyleClass().add("selectedButton");
    }
    @FXML
    private void theme1() {
        theme = 0;
        theme1Button.getStyleClass().add("selectedButton");
        theme2Button.getStyleClass().remove("selectedButton");
    }
    @FXML
    private void theme2() {
        theme = 1;
        theme1Button.getStyleClass().remove("selectedButton");
        theme2Button.getStyleClass().add("selectedButton");
    }
    @FXML
    private void backToMenu() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Views/menuView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), StageProperties.STAGE_WIDTH, StageProperties.STAGE_HEIGHT);
        Stage stage = (Stage) borderPane.getScene().getWindow();
        stage.setScene(scene);
    }
    @FXML
    private void save() {
        try (FileWriter writer = new FileWriter(StageProperties.SETTINGS_FILE_PATH,false)) {
            writer.write(difficulty + "\n" + theme);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
