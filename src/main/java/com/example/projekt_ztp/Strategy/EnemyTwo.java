package com.example.projekt_ztp.Strategy;

import com.example.projekt_ztp.StageProperties;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class EnemyTwo extends Enemy{

    public EnemyTwo(double xPos,double yPos){
        image = new Image("file:src/main/resources/com/example/projekt_ztp/Images/EnemyTwo.png");
        imagePattern = new ImagePattern(image);

        score = 20;
        this.health = 100;
        //this.image = TODO:WCZYTUJEMY ZDJECIE
        this.xPos = xPos;
        this.yPos = yPos;
        this.enemyWidth = StageProperties.ENEMY_WIDTH;
        this.enemyHeight = StageProperties.ENEMY_HEIGHT;
        initBorder();
    }
}
