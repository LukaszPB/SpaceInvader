package com.example.projekt_ztp.decorators;

import com.example.projekt_ztp.Bullet;

public abstract class Module implements Upgrade {
    protected Upgrade upgrade;
    public Module(Upgrade upgrade) {
        this.upgrade = upgrade;
    }
    @Override
    public abstract double move(double x);
    @Override
    public abstract Bullet shot(Bullet bullet);
}
