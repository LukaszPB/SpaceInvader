package com.example.projekt_ztp.builder;

import com.example.projekt_ztp.Strategy.Enemy;
import com.example.projekt_ztp.creator.Obstacle;

import java.util.ArrayList;

public class Level {
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Obstacle> obstacles = new ArrayList<>();
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }
    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }
}
