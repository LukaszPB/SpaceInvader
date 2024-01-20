package com.example.projekt_ztp.creator;

import com.example.projekt_ztp.StageProperties;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public abstract class Obstacle {
    protected int obstacleHealth = 10;
    protected double x,y,size;
    protected String imagePath;
    //protected Image image = new Image("file:src/main/resources/com/example/projekt_ztp/Images/przeszkoda_02.png");
    protected ImagePattern imagePattern;//new ImagePattern(image);
    protected Rectangle rectangle = new Rectangle(StageProperties.ENEMY_WIDTH,StageProperties.ENEMY_HEIGHT/2);

    public Rectangle getGraphicRep() {
        rectangle.setLayoutX(x);
        rectangle.setLayoutY(y);
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
