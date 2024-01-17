package com.example.projekt_ztp.Strategy;

import com.example.projekt_ztp.StageProperties;

public class EnemyTwo extends Enemy{

    public EnemyTwo(double xPos,double yPos){
        this.health = 100;
        //this.image = TODO:WCZYTUJEMY ZDJECIE
        this.xPos = xPos;
        this.yPos = yPos;
        this.enemyWidth = StageProperties.ENEMY_WIDTH;
        this.enemyHeight = StageProperties.ENEMY_HEIGHT;
        initBorder();
    }
}
