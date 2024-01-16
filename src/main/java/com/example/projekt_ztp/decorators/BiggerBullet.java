package com.example.projekt_ztp.decorators;

import com.example.projekt_ztp.Bullet;

public class BiggerBullet extends Module{
    public BiggerBullet(Upgrade upgrade) {
        super(upgrade);
    }
    @Override
    public double move(double x) {
        return upgrade.move(x);
    }

    @Override
    public Bullet shot(Bullet bullet) {
        bullet.setSize(2*bullet.getSize());
        return upgrade.shot(bullet);
    }
}
