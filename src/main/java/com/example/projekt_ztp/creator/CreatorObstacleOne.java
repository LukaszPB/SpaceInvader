package com.example.projekt_ztp.creator;

public class CreatorObstacleOne implements Creator{
    @Override
    public Obstacle create() {
        return new ObstacleOne();
    }
}
