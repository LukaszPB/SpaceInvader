package com.example.projekt_ztp.creator;

public class CreateObstacleTwo implements Creator{
    @Override
    public Obstacle create() {
        return new ObstacleTwo();
    }
}
