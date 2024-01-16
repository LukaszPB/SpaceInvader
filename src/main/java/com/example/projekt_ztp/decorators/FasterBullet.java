package com.example.projekt_ztp.decorators;

import com.example.projekt_ztp.Bullet;

public class FasterBullet extends Module{
    public FasterBullet(Upgrade upgrade) {
        super(upgrade);
    }

    @Override
    public double move(double x) {
        return upgrade.move(x);
    }

    @Override
    public Bullet shot(Bullet bullet) {
        bullet.setSpeed(2*bullet.getSpeed());
        return upgrade.shot(bullet);
    }
}
