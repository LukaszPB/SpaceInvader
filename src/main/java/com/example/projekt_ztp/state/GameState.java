package com.example.projekt_ztp.state;

import com.example.projekt_ztp.Bullet;
import com.example.projekt_ztp.Ship;
import com.example.projekt_ztp.Strategy.Enemy;
import com.example.projekt_ztp.Strategy.EnemyOne;
import com.example.projekt_ztp.Strategy.MoveRight;
import com.example.projekt_ztp.builder.Level;
import com.example.projekt_ztp.creator.Obstacle;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class GameState extends AppState{
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private LinkedList<Enemy> enemies = new LinkedList<>();
    private LinkedList<Obstacle> obstacles = new LinkedList<>();
    private Ship ship = Ship.getInstance();
    public GameState(Pane pane) {
        super(pane);
        setupShipMove();
    }
    public GameState(Pane pane, Level level) {
        this(pane);
        for(Enemy enemy : level.getEnemies()) {
            enemy.setStrategy(new MoveRight());
            enemies.add(enemy);
            pane.getChildren().add(enemy.getGraphicRep());
        }
        for(Obstacle obstacle : level.getObstacles()) {
            obstacles.add(obstacle);
            pane.getChildren().add(obstacle.getGraphicRep());
        }
    }
    @Override
    protected void setupShipMove() {
        ship.getGraphicRep().setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT -> ship.move(-10);
                case RIGHT -> ship.move(10);
                case SPACE -> {
                    bullets.add(ship.shot());
                    pane.getChildren().add(bullets.get(bullets.size() - 1).getGraphicRep());
                }
                case T -> {
                    System.out.println("NEW ENEMY");
                    Enemy enemyTmp = new EnemyOne();
                    System.out.println(enemyTmp.getXandY());
                    enemyTmp.setStrategy(new MoveRight());
                    enemies.add(enemyTmp);
                    pane.getChildren().add(enemies.get(enemies.size()-1).getGraphicRep());
                }
            }
        });
    }

    @Override
    public void enemyMove() {
        Iterator<Enemy> iterator = enemies.iterator();
        Iterator<Enemy> iteratorTmp = enemies.iterator();

        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            if (enemy.move()) {

                while (iteratorTmp.hasNext()){
                    Enemy enemy1 = iteratorTmp.next();
                    enemy1.reverseStrategy();
                }

            }
        }
    }

    @Override
    public void bulletsMove() {
        Iterator<Bullet> iterator = bullets.iterator();
        Iterator<Enemy> enemyIterator = enemies.iterator();
        Iterator<Obstacle> obstacleIterator = obstacles.iterator();


        while (obstacleIterator.hasNext()){
            Obstacle obstacle = obstacleIterator.next();
            //WERYFIKACJA KONCA ZYCIA
            if(obstacle.getObstacleHealth() <= 1){
                pane.getChildren().remove(obstacle.getGraphicRep());
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
                    pane.getChildren().remove(bullet.getGraphicRep());
                    iterator.remove();
                }
            }
            obstacleIterator = obstacles.iterator();


            while (enemyIterator.hasNext()){
                Enemy enemy = enemyIterator.next();
                if(enemy.getGraphicRep().getBoundsInParent().intersects(bullet.getGraphicRep().getBoundsInParent())){
                    System.out.println("Kolizja!");
                    pane.getChildren().remove(enemy.getGraphicRep());
                    enemyIterator.remove();
                    pane.getChildren().remove(bullet.getGraphicRep());
                    iterator.remove();
                }
            }
            enemyIterator = enemies.iterator();

            if (bullet.move()) {
                pane.getChildren().remove(bullet.getGraphicRep());
                iterator.remove();
            }
        }
    }
}