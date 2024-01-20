package com.example.projekt_ztp.Strategy;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class EnemyOne extends Enemy{

    public EnemyOne(){
        super(15,15);
        //image = new Image("file:src/main/resources/com/example/projekt_ztp/Images/EnemyOne.png");
        image = new Image(getClass().getResource("/com/example/projekt_ztp/Images/EnemyOne.png").toExternalForm());

        imagePattern = new ImagePattern(image);

        score = 10;
        this.health = 100;
        //this.image = TODO:WCZYTUJEMY ZDJECIE
        //Pozycjonowanie do zmiany
        initBorder();
    }

    public EnemyOne(double xPos,double yPos){
        super(xPos, yPos);
        image = new Image(getClass().getResource("/com/example/projekt_ztp/Images/EnemyOne.png").toExternalForm());
        imagePattern = new ImagePattern(image);

        score = 10;
        initBorder();
    }
}
