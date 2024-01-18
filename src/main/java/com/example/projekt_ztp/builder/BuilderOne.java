package com.example.projekt_ztp.builder;

import com.example.projekt_ztp.StageProperties;
import com.example.projekt_ztp.Strategy.Enemy;
import com.example.projekt_ztp.Strategy.EnemyOne;
import com.example.projekt_ztp.Strategy.EnemyTwo;
import com.example.projekt_ztp.creator.Creator;
import com.example.projekt_ztp.creator.CreatorObstacleOne;
import com.example.projekt_ztp.creator.Obstacle;

public class BuilderOne implements BoardBuilder{
    private Level level;
    private Creator creator;
    private double x,y;
    public BuilderOne() {
        level = new Level();
        creator = new CreatorObstacleOne();
        x = 100;
        y = StageProperties.FRAME_SIZE;
        //test1
    }

    @Override
    public void addEnemy1() {
        Enemy enemy = new EnemyOne(x,y);
        x += StageProperties.ENEMY_WIDTH;
        level.addEnemy(enemy);
    }

    @Override
    public void addEnemy2() {
        Enemy enemy = new EnemyTwo(x,y);
        x += StageProperties.ENEMY_WIDTH;
        level.addEnemy(enemy);
    }

    @Override
    public void addObstacle() {
        Obstacle obstacle = creator.create();
        obstacle.setX(x);
        obstacle.setY(StageProperties.GAME_WINDOW_HEIGHT-2.5*StageProperties.SHIP_SIZE);
        x += StageProperties.ENEMY_WIDTH;
        level.addObstacle(obstacle);
    }

    @Override
    public void nexColumn() {
        x += StageProperties.ENEMY_WIDTH;
    }

    @Override
    public void nexLine() {
        y += StageProperties.ENEMY_HEIGHT;
        x = 100;
    }

    @Override
    public Level getLevel() {
        Level finalLevel = level;
        level = new Level();
        return finalLevel;
    }
}
