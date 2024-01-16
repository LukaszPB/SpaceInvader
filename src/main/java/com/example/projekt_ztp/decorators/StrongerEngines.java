package com.example.projekt_ztp.decorators;

import com.example.projekt_ztp.Bullet;

public class StrongerEngines extends Module{
    public StrongerEngines(Upgrade upgrade) {
        super(upgrade);
    }

    @Override
    public double move(double x) {
        return upgrade.move(2*x);
    }

    @Override
    public Bullet shot(Bullet bullet) {
        return upgrade.shot(bullet);
    }
}
