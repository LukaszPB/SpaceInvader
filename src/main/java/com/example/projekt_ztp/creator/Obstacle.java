package com.example.projekt_ztp.creator;

public abstract class Obstacle {
    private double x,y,size;
    private String imagePath;

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
