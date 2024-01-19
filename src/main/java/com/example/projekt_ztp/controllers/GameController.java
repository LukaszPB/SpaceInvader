package com.example.projekt_ztp.controllers;

import com.example.projekt_ztp.Main;
import com.example.projekt_ztp.Ship;
import com.example.projekt_ztp.StageProperties;
import com.example.projekt_ztp.builder.Level;
import com.example.projekt_ztp.builder.LevelsDataBase;
import com.example.projekt_ztp.state.AppState;
import com.example.projekt_ztp.state.ChoosingUpgradeState;
import com.example.projekt_ztp.state.GameState;
import com.example.projekt_ztp.state.PauseState;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Iterator;

public class GameController {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label message;
    @FXML
    private Button pause;
    private LevelsDataBase levelsDataBase = new LevelsDataBase(StageProperties.LEVELS_FILE_PATH);
    private Iterator<Level> levelIterator;
    private GameState gameState;
    private AppState currentState = new PauseState(anchorPane);

    @FXML
    private void initialize() {
        anchorPane.setPrefSize(StageProperties.GAME_WINDOW_WIDTH, StageProperties.GAME_WINDOW_HEIGHT);
        anchorPane.setMinSize(StageProperties.GAME_WINDOW_WIDTH, StageProperties.GAME_WINDOW_HEIGHT);
        anchorPane.setMaxSize(StageProperties.GAME_WINDOW_WIDTH, StageProperties.GAME_WINDOW_HEIGHT);

        message.setLayoutX((StageProperties.GAME_WINDOW_WIDTH-message.getWidth())/2);
        message.setLayoutY(StageProperties.GAME_WINDOW_HEIGHT/3);

        anchorPane.getChildren().add(Ship.getInstance().getGraphicRep());

        levelIterator = levelsDataBase.iterator();
        loadLevel();
        Ship.getInstance().resetUpgrade();
        setupEnemyTimeline();
    }
    private void setupEnemyTimeline() {
        Timeline timelineEnemy = new Timeline(
                new KeyFrame(Duration.millis(10), event -> {
                    switch (currentState.enemyMove()) {
                        case 3-> loadLevel();
                        case 2-> victory();
                        case 1-> defeat();
                    }
                })
                ,
                new KeyFrame(Duration.millis(10), event -> {

                    //if(!currentState.upgradeChosen){
                        currentState.bulletsMove();
                    //}

                })
        );

        timelineEnemy.setCycleCount(Timeline.INDEFINITE);
        timelineEnemy.play();
    }


    private void loadLevel() {
        if(levelIterator.hasNext()) {
            gameState = new GameState(anchorPane,levelIterator.next());
            currentState = gameState;
        }
    }
    private void victory() {
        if(levelIterator.hasNext()){
            currentState = new ChoosingUpgradeState(anchorPane);
        }else {
            totalVictory();
        }
    }
    private void totalVictory(){
        message.setText("Total Victory!");
        message.setLayoutX(200);
        message.setLayoutY(150);
        currentState = new PauseState(anchorPane);
    }
    private void defeat() {
        message.setText("Defeat");
        message.setLayoutX(230);
        message.setLayoutY(150);
        gameState.deleteAll();
        gameState.ship.resetUpgrade();
        currentState = new PauseState(anchorPane);
    }
    @FXML
    private void pause() {
        if(currentState instanceof PauseState) {
            gameState.setupShipMove();
            currentState = gameState;
            pause.setText("Pause");
        }
        else {
            currentState = new PauseState(anchorPane);
            pause.setText("Continue");
        }
    }
    @FXML
    private void restart() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Views/gameView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), StageProperties.STAGE_WIDTH, StageProperties.STAGE_HEIGHT);
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        gameState.ship.resetUpgrade();
        //currentState = new PauseState(anchorPane);
        stage.setScene(scene);
    }
    @FXML
    private void backToMenu() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Views/menuView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), StageProperties.STAGE_WIDTH, StageProperties.STAGE_HEIGHT);
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.setScene(scene);
    }
}
