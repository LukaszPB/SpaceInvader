package com.example.projekt_ztp.Strategy;

public class EnemyOne extends Enemy{

    public EnemyOne(){
        this.health = 100;
        //this.image = TODO:WCZYTUJEMY ZDJECIE
        //Pozycjonowanie do zmiany
        this.xPos = 15;
        this.yPos = 15;
        this.enemyWidth = 25;
        this.enemyHeight = 25;
        initBorder();
    }

    public EnemyOne(int xPos,int yPos){
        this.health = 100;
        //this.image = TODO:WCZYTUJEMY ZDJECIE
        this.xPos = xPos;
        this.yPos = yPos;
        this.enemyWidth = 60;
        this.enemyHeight = 60;
        initBorder();
    }



}
