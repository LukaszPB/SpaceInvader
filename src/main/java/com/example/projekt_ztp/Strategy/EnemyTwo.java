package com.example.projekt_ztp.Strategy;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class EnemyTwo extends Enemy{

    public EnemyTwo(double xPos,double yPos){
        super(xPos, yPos);
        image = new Image("file:src/main/resources/com/example/projekt_ztp/Images/EnemyTwo.png");
        imagePattern = new ImagePattern(image);

        score = 20;
        initBorder();
    }
}
