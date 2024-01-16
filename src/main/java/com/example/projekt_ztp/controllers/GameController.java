package com.example.projekt_ztp.controllers;

import com.example.projekt_ztp.Bullet;
import com.example.projekt_ztp.Main;
import com.example.projekt_ztp.Ship;
import com.example.projekt_ztp.StageProperties;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class GameController {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button shipModel;
    private Ship ship = Ship.getInstance();
    private ArrayList<Bullet> bullets = new ArrayList<>();
    @FXML
    private void initialize() {
        anchorPane.setPrefSize(StageProperties.GAME_WINDOW_WIDTH, StageProperties.GAME_WINDOW_HEIGHT);
        anchorPane.setMinSize(StageProperties.GAME_WINDOW_WIDTH, StageProperties.GAME_WINDOW_HEIGHT);
        anchorPane.setMaxSize(StageProperties.GAME_WINDOW_WIDTH, StageProperties.GAME_WINDOW_HEIGHT);

        Image shipImage = new Image("file:src/main/resources/com/example/projekt_ztp/Images/ship.png");
        String shipStyle = "-fx-background-image: url('" + shipImage.getUrl() + "'); " +
                "-fx-background-size: cover;";

        shipModel.setStyle(shipStyle);
        shipModel.setLayoutX(ship.getX());

        shipModel.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT -> ship.move(-10);
                case RIGHT -> ship.move(10);
                case SPACE -> {
                    bullets.add(ship.shot());
                    anchorPane.getChildren().add(bullets.get(bullets.size() - 1).getGraphicRep());
                }
            }
            shipModel.setLayoutX(ship.getX());
        });

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(50), event -> {
                    Iterator<Bullet> iterator = bullets.iterator();
                    while (iterator.hasNext()) {
                        Bullet bullet = iterator.next();
                        if (bullet.move()) {
                            anchorPane.getChildren().remove(bullet.getGraphicRep());
                            iterator.remove();
                        }
                    }
                })
        );

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    @FXML
    private void backToMenu() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Views/menuView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), StageProperties.STAGE_WIDTH, StageProperties.STAGE_HEIGHT);
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.setScene(scene);
    }
}
