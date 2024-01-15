package com.example.projekt_ztp.Controllers;

import com.example.projekt_ztp.Main;
import com.example.projekt_ztp.StageProperties;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class GameController {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private BorderPane borderPane;
    @FXML
    private Button ship;
    @FXML
    private Label title;
    @FXML
    private void initialize() {
        Image shipImage = new Image("file:src/main/resources/com/example/projekt_ztp/Images/ship.png");
        String shipStyle = "-fx-background-image: url('" + shipImage.getUrl() + "'); " +
                "-fx-background-size: cover;";

        ship.setStyle(shipStyle);

        //-100, ponieważ trzeba odjąć grubość dwóch ramek po 10 i lewego paddingu 80
        ship.setLayoutX((StageProperties.STAGE_WIDTH-ship.getMinWidth())/2-100);

        ship.setOnKeyPressed(event -> {
            double x = ship.getLayoutX();
            switch (event.getCode()) {
                case LEFT -> x-=10;
                case RIGHT -> x+=10;
            }
            if(x>40 && x<StageProperties.STAGE_WIDTH-295) {
                ship.setLayoutX(x);
            }
        });
    }
    @FXML
    private void backToMenu() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Views/menuView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), StageProperties.STAGE_WIDTH, StageProperties.STAGE_HEIGHT);
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.setScene(scene);
    }
}
