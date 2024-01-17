package com.example.projekt_ztp.builder;

import com.example.projekt_ztp.StageProperties;
import com.example.projekt_ztp.Strategy.Enemy;
import com.example.projekt_ztp.Strategy.EnemyOne;
import com.example.projekt_ztp.Strategy.EnemyTwo;
import com.example.projekt_ztp.creator.CreateObstacleTwo;
import com.example.projekt_ztp.creator.Creator;
import com.example.projekt_ztp.creator.Obstacle;

public class BuilderTwo implements BoardBuilder{
    private Level level;
    private Creator creator;
    private double x,y;
    public BuilderTwo() {
        level = new Level();
        creator = new CreateObstacleTwo();
        x = 0;
        y = 0;
    }

    @Override
    public void addEnemy1() {
        Enemy enemy = new EnemyOne();
        enemy.setXPos(x);
        enemy.setYPos(y);
        x += StageProperties.ENEMY_WIDTH;
        level.addEnemy(enemy);
    }

    @Override
    public void addEnemy2() {
        Enemy enemy = new EnemyTwo();
        enemy.setXPos(x);
        enemy.setYPos(y);
        x += StageProperties.ENEMY_WIDTH;
        level.addEnemy(enemy);
    }

    @Override
    public void addObstacle() {
        Obstacle obstacle = creator.create();
        obstacle.setX(x);
        level.addObstacle(obstacle);
    }

    @Override
    public void nexColumn() {
        x += StageProperties.ENEMY_WIDTH;
    }
    @Override
    public void nexLine() {
        y += StageProperties.ENEMY_HEIGHT;
        x = StageProperties.FRAME_SIZE;
    }

    @Override
    public Level getLevel() {
        Level finalLevel = level;
        level = new Level();
        return finalLevel;
    }
}
