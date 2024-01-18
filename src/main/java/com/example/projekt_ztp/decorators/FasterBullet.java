package com.example.projekt_ztp.decorators;

import com.example.projekt_ztp.Bullet;
import javafx.scene.paint.Color;

public class FasterBullet extends Module{
    public FasterBullet(Upgrade upgrade) {
        super(upgrade);
        graphicRep = Color.RED;
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
