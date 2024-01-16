package com.example.projekt_ztp.Strategy;

public class MoveDown implements MoveStrategy{
    @Override
    public void move(Enemy enemy) {
        enemy.yPos += 10;
    }
}
