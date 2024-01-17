package com.example.projekt_ztp.Strategy;

import com.example.projekt_ztp.StageProperties;

public class EnemyOne extends Enemy{

    public EnemyOne(){
        this.health = 100;
        //this.image = TODO:WCZYTUJEMY ZDJECIE
        //Pozycjonowanie do zmiany
        this.xPos = 15;
        this.yPos = 15;
        this.enemyWidth = StageProperties.ENEMY_WIDTH;
        this.enemyHeight = StageProperties.ENEMY_HEIGHT;
        initBorder();
    }

    public EnemyOne(double xPos,double yPos){
        this.health = 100;
        //this.image = TODO:WCZYTUJEMY ZDJECIE
        this.xPos = xPos;
        this.yPos = yPos;
        this.enemyWidth = StageProperties.ENEMY_WIDTH;
        this.enemyHeight = StageProperties.ENEMY_HEIGHT;
        initBorder();
    }



}
