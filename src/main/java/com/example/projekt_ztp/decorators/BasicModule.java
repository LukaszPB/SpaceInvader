package com.example.projekt_ztp.decorators;

import com.example.projekt_ztp.Bullet;

public class BasicModule implements Upgrade{
    @Override
    public double move(double x) {
        return x;
    }
    @Override
    public Bullet shot(Bullet bullet) {
        return bullet;
    }
}
