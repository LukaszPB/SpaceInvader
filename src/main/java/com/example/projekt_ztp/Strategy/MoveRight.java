package com.example.projekt_ztp.Strategy;

public class MoveRight implements MoveStrategy{
    @Override
    public void move(Enemy enemy) {
        enemy.xPos += 10;
        //System.out.println("Move");
    }
}
