package com.example.projekt_ztp.Strategy;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class EnemyFour extends Enemy{
    public EnemyFour(double xPos,double yPos){
        super(xPos, yPos);
        //image = new Image("file:src/main/resources/com/example/projekt_ztp/Images/EnemyFour.png");
        image = new Image(getClass().getResource("/com/example/projekt_ztp/Images/EnemyFour.png").toExternalForm());

        imagePattern = new ImagePattern(image);

        score = 20;
        initBorder();
    }
}
