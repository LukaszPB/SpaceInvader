package com.example.projekt_ztp.decorators;

import com.example.projekt_ztp.Bullet;
import javafx.scene.paint.Color;

public class StrongerEngines extends Module{
    public StrongerEngines(Upgrade upgrade) {
        super(upgrade);
        graphicRep = Color.GREEN;
    }

    @Override
    public double move(double x) {
        double tmp = x * 2;
        return upgrade.move(tmp);
    }

    @Override
    public Bullet shot(Bullet bullet) {
        return upgrade.shot(bullet);
    }
}
