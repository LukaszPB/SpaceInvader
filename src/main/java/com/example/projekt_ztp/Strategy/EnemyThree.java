package com.example.projekt_ztp.Strategy;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class EnemyThree extends Enemy{
    public EnemyThree(double xPos,double yPos){
        super(xPos, yPos);
        //image = new Image("file:src/main/resources/com/example/projekt_ztp/Images/EnemyThree.png");
        image = new Image(getClass().getResource("/com/example/projekt_ztp/Images/EnemyThree.png").toExternalForm());

        imagePattern = new ImagePattern(image);

        score = 10;
        initBorder();
    }
}
