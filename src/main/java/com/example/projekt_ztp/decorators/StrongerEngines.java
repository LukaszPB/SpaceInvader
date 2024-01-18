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
        return upgrade.move(2*x);
    }

    @Override
    public Bullet shot(Bullet bullet) {
        return upgrade.shot(bullet);
    }
}
