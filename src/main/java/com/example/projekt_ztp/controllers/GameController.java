package com.example.projekt_ztp.controllers;

import com.example.projekt_ztp.Bullet;
import com.example.projekt_ztp.Main;
import com.example.projekt_ztp.Ship;
import com.example.projekt_ztp.StageProperties;
import com.example.projekt_ztp.Strategy.Enemy;
import com.example.projekt_ztp.builder.Level;
import com.example.projekt_ztp.builder.LevelsDataBase;
import com.example.projekt_ztp.creator.Obstacle;
import com.example.projekt_ztp.state.AppState;
import com.example.projekt_ztp.state.GameState;
import com.example.projekt_ztp.state.PauseState;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class GameController {
    @FXML
    private AnchorPane anchorPane;
    private Ship ship = Ship.getInstance();
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private LinkedList<Enemy> enemies = new LinkedList<>();
    private LinkedList<Obstacle> obstacles = new LinkedList<>();
    private LevelsDataBase levelsDataBase = new LevelsDataBase(StageProperties.LEVELS_FILE_PATH);
    private Iterator<Level> levelIterator;
    private boolean isGamePaused = false;
    private GameState gameState;
    private AppState currentState = new PauseState(anchorPane);

    @FXML
    private void initialize() {
        anchorPane.setPrefSize(StageProperties.GAME_WINDOW_WIDTH, StageProperties.GAME_WINDOW_HEIGHT);
        anchorPane.setMinSize(StageProperties.GAME_WINDOW_WIDTH, StageProperties.GAME_WINDOW_HEIGHT);
        anchorPane.setMaxSize(StageProperties.GAME_WINDOW_WIDTH, StageProperties.GAME_WINDOW_HEIGHT);

        anchorPane.getChildren().add(ship.getGraphicRep());

        levelIterator = levelsDataBase.iterator();
        loadLevel();

        setupEnemyTimeline();
        setupBulletsTimeline();
    }
    private void setupEnemyTimeline() {
        Timeline timelineEnemy = new Timeline(
                new KeyFrame(Duration.millis(10), event -> {
                    currentState.enemyMove();
                })
        );

        timelineEnemy.setCycleCount(Timeline.INDEFINITE);
        timelineEnemy.play();
    }
    private void setupBulletsTimeline() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(10), event -> {
                    currentState.bulletsMove();
                })
        );

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void loadLevel() {
        if(levelIterator.hasNext()) {
            gameState = new GameState(anchorPane,levelIterator.next());
            currentState = gameState;
        }
    }
    @FXML
    private void pause() {
        if(currentState instanceof PauseState) {
            gameState.setupShipMove();
            currentState = gameState;
        }
        else {
            currentState = new PauseState(anchorPane);
        }
    }
    @FXML
    private void backToMenu() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Views/menuView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), StageProperties.STAGE_WIDTH, StageProperties.STAGE_HEIGHT);
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.setScene(scene);
    }
}
