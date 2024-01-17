package com.example.projekt_ztp.controllers;

import com.example.projekt_ztp.Bullet;
import com.example.projekt_ztp.Main;
import com.example.projekt_ztp.Ship;
import com.example.projekt_ztp.StageProperties;
import com.example.projekt_ztp.Strategy.Enemy;
import com.example.projekt_ztp.Strategy.EnemyOne;
import com.example.projekt_ztp.Strategy.MoveRight;
import com.example.projekt_ztp.builder.BuilderOne;
import com.example.projekt_ztp.builder.Level;
import com.example.projekt_ztp.builder.LevelsDataBase;
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
import java.util.LinkedList;

public class GameController {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button shipModel;
    private Ship ship = Ship.getInstance();
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private LinkedList<Enemy> enemies = new LinkedList<>();
    private LevelsDataBase levelsDataBase = new LevelsDataBase(StageProperties.LEVELS_FILE_PATH);

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
                case T -> {
                    System.out.println("NEW ENEMY");
                    Enemy enemyTmp = new EnemyOne();
                    System.out.println(enemyTmp.getXandY());
                    enemyTmp.setStrategy(new MoveRight());
                    enemies.add(enemyTmp);
                    anchorPane.getChildren().add(enemies.get(enemies.size()-1).getGraphicRep());
                }
            }
            shipModel.setLayoutX(ship.getX());
        });

        Timeline timelineEnemy = new Timeline(
                new KeyFrame(Duration.millis(100), event -> {
                    Iterator<Enemy> iterator = enemies.iterator();
                    while (iterator.hasNext()) {
                        Enemy enemy = iterator.next();
                        if (enemy.move()) {
                            enemy.reverseStrategy();
                        }
                    }
                })
        );

        Level level = levelsDataBase.buildLevel(new BuilderOne(),levelsDataBase.getLevelDescriptions().get(0));
        for(Enemy enemy : level.getEnemies()) {
            enemy.setStrategy(new MoveRight());
            enemies.add(enemy);
            anchorPane.getChildren().add(enemy.getGraphicRep());
        }

        timelineEnemy.setCycleCount(Timeline.INDEFINITE);
        timelineEnemy.play();

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(50), event -> {
                    Iterator<Bullet> iterator = bullets.iterator();
                    Iterator<Enemy> enemyIterator = enemies.iterator();
                    while (iterator.hasNext()) {
                        Bullet bullet = iterator.next();
                        while (enemyIterator.hasNext()){
                            Enemy enemy = enemyIterator.next();
                            if(enemy.getGraphicRep().getBoundsInParent().intersects(bullet.getGraphicRep().getBoundsInParent())){
                                System.out.println("Kolizja!");
                            }
                        }
                        enemyIterator = enemies.iterator();
                        if (bullet.move()) {
                            //System.out.println("pociskjest");
                            anchorPane.getChildren().remove(bullet.getGraphicRep());
                            iterator.remove();
                        }
                    }
                })
//                ,
//                new KeyFrame(Duration.millis(100), event -> {
//                    Iterator<Enemy> iterator = enemies.iterator();
//                    while (iterator.hasNext()) {
//                        Enemy enemy = iterator.next();
//                        if (enemy.move()) {
//                            enemy.reverseStrategy();
//                        }
//                    }
//                })
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
