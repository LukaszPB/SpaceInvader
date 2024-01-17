package com.example.projekt_ztp;

import javafx.scene.shape.Rectangle;

public class Bullet {
    public double size, speed, x, y;
    public Rectangle graphicRep;
    public Bullet(double x, double y) {
        size = 5;
        speed = 10;
        this.x = x;
        this.y = y;
        graphicRep = new Rectangle(size,size);
        graphicRep.setLayoutX(x);
        graphicRep.setLayoutY(y);
        graphicRep.getStyleClass().add("bullet");
    }

    public double getSize() {
        return size;
    }

    public double getSpeed() {
        return speed;
    }

    public Rectangle getGraphicRep() {
        return graphicRep;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
    public boolean move() {
        y -= speed;
        graphicRep.setLayoutY(y);

        return y<=10;
    }
}
