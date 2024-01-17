package com.example.projekt_ztp.creator;

import com.example.projekt_ztp.StageProperties;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public abstract class Obstacle {
    private int obstacleHealth = 10;
    private double x,y,size;
    private String imagePath;
    private Image image = new Image("file:src/main/resources/com/example/projekt_ztp/Images/przeszkoda_02.png");
    private ImagePattern imagePattern = new ImagePattern(image);
    private Rectangle rectangle =
            new Rectangle(StageProperties.ENEMY_WIDTH,StageProperties.ENEMY_HEIGHT/2);

    public Rectangle getGraphicRep() {
        rectangle.setLayoutX(x);
        rectangle.setLayoutY(y);
        rectangle.setFill(Color.WHITE);
        //rectangle.setFill(imagePattern);
        if(obstacleHealth <= 6){rectangle.setFill(Color.YELLOW);}
        if(obstacleHealth <= 3){rectangle.setFill(Color.RED);}
        return rectangle;
    }

    public int getObstacleHealth(){
        return obstacleHealth;
    }

    public void getShot(){
        obstacleHealth -= 1;
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    public double getSize() {
        return size;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
