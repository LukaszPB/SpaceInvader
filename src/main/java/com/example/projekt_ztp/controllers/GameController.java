package com.example.projekt_ztp.controllers;

import com.example.projekt_ztp.Bullet;
import com.example.projekt_ztp.Main;
import com.example.projekt_ztp.Ship;
import com.example.projekt_ztp.StageProperties;
import com.example.projekt_ztp.Strategy.Enemy;
import com.example.projekt_ztp.Strategy.EnemyOne;
import com.example.projekt_ztp.Strategy.MoveRight;
import com.example.projekt_ztp.builder.Level;
import com.example.projekt_ztp.builder.LevelsDataBase;
import com.example.projekt_ztp.creator.Obstacle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

public class GameController {
    @FXML
    private AnchorPane anchorPane;
    private Ship ship = Ship.getInstance();
    private LinkedList<Bullet> playerBullets = new LinkedList<>();
    private LinkedList<Bullet> enemyBullets = new LinkedList<>();
    private LinkedList<Enemy> enemies = new LinkedList<>();
    private LinkedList<Obstacle> obstacles = new LinkedList<>();
    private LevelsDataBase levelsDataBase = new LevelsDataBase(StageProperties.LEVELS_FILE_PATH);
    private Iterator<Level> levelIterator;

    @FXML
    private void initialize() {
        anchorPane.setPrefSize(StageProperties.GAME_WINDOW_WIDTH, StageProperties.GAME_WINDOW_HEIGHT);
        anchorPane.setMinSize(StageProperties.GAME_WINDOW_WIDTH, StageProperties.GAME_WINDOW_HEIGHT);
        anchorPane.setMaxSize(StageProperties.GAME_WINDOW_WIDTH, StageProperties.GAME_WINDOW_HEIGHT);

        anchorPane.getChildren().add(ship.getGraphicRep());

        levelIterator = levelsDataBase.iterator();
        loadLevel();

        ship.getGraphicRep().setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT -> ship.move(-10);
                case RIGHT -> ship.move(10);
                case SPACE -> {
                    playerBullets.add(ship.shot());
                    anchorPane.getChildren().add(playerBullets.get(playerBullets.size() - 1).getGraphicRep());
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
        });
        Timeline timelineEnemy = new Timeline(
                new KeyFrame(Duration.millis(10), event -> {
                    Iterator<Enemy> iterator = enemies.iterator();
                    Iterator<Enemy> iteratorTmp = enemies.iterator();


                    while (iterator.hasNext()) {
                        Enemy enemy = iterator.next();
                        //TODO:PRZECIWNIK STRZELANIE
//                        if(enemy.isPlayerInRange()){
//                            enemyBullets.add(enemy.shoot());
//                            anchorPane.getChildren().add(enemyBullets.get(enemyBullets.size() -1).graphicRep);
//
                        if (enemy.move()) {
                            while (iteratorTmp.hasNext()){
                                Enemy enemy1 = iteratorTmp.next();
                                enemy1.reverseStrategy();
                            }

                        }
                    }
                    //NOWE NIE DZIALA XD
//                    Iterator<Bullet> bulletIterator = enemyBullets.iterator();
//                    while (bulletIterator.hasNext()){
//                        Bullet enemyBullet = bulletIterator.next();
//
//                        if (enemyBullet.move()) {
//                            anchorPane.getChildren().remove(enemyBullet.getGraphicRep());
//                            bulletIterator.remove();
//                        }
//
//                    }

                })
        );




        timelineEnemy.setCycleCount(Timeline.INDEFINITE);
        timelineEnemy.play();

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(10), event -> {
                    Iterator<Bullet> iterator = playerBullets.iterator();
                    Iterator<Enemy> enemyIterator = enemies.iterator();
                    Iterator<Obstacle> obstacleIterator = obstacles.iterator();


                    while (obstacleIterator.hasNext()){
                        Obstacle obstacle = obstacleIterator.next();
                        //WERYFIKACJA KONCA ZYCIA
                        if(obstacle.getObstacleHealth() <= 1){
                            anchorPane.getChildren().remove(obstacle.getGraphicRep());
                            obstacleIterator.remove();
                            System.out.println("kill1");
                        }
                    }
                    obstacleIterator = obstacles.iterator();


                    while (iterator.hasNext()) {
                        Bullet bullet = iterator.next();

                        while (obstacleIterator.hasNext()){
                            //WERYFIKACJA UDEZENIA W SCIANE
                            Obstacle obstacle = obstacleIterator.next();
                            if(obstacle.getGraphicRep().getBoundsInParent().intersects(bullet.getGraphicRep().getBoundsInParent())){
                                System.out.println("SCIANA!");
                                obstacle.getShot();
                                anchorPane.getChildren().remove(bullet.getGraphicRep());
                                iterator.remove();
                            }
                        }
                        obstacleIterator = obstacles.iterator();


                        while (enemyIterator.hasNext()){
                            Enemy enemy = enemyIterator.next();
                            if(enemy.getGraphicRep().getBoundsInParent().intersects(bullet.getGraphicRep().getBoundsInParent())){
                                System.out.println("Kolizja!");
                                anchorPane.getChildren().remove(enemy.getGraphicRep());
                                enemyIterator.remove();
                                anchorPane.getChildren().remove(bullet.getGraphicRep());
                                iterator.remove();
                            }
                        }
                        enemyIterator = enemies.iterator();

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

    private void loadLevel() {
        enemies.clear();

        if(!levelIterator.hasNext()) {

            return;
        }
        Level level = levelIterator.next();

        for(Enemy enemy : level.getEnemies()) {
            enemy.setStrategy(new MoveRight());
            enemies.add(enemy);
            anchorPane.getChildren().add(enemy.getGraphicRep());
        }
        for(Obstacle obstacle : level.getObstacles()) {
            obstacles.add(obstacle);
            anchorPane.getChildren().add(obstacle.getGraphicRep());
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
