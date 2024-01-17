package com.example.projekt_ztp.creator;

import com.example.projekt_ztp.StageProperties;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Obstacle {
    private double x,y,size;
    private String imagePath;

    private Rectangle rectangle =
            new Rectangle(StageProperties.ENEMY_WIDTH,StageProperties.ENEMY_HEIGHT/2);

    public Rectangle getGraphicRep() {
        rectangle.setLayoutX(x);
        rectangle.setLayoutY(y);
        rectangle.setFill(Color.WHITE);
        return rectangle;
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
