package com.example.projekt_ztp.observer;

public class LevelCounter implements Observer{
    private int level = 0;
    @Override
    public void update(Event event) {
        if(event instanceof NewLevel) {
            level++;
            System.out.println("Level " + level);
        }
    }
    public int getLevelNumber() { return level; }
}
