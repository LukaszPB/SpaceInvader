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
        if(x>0)x+=3;
        if(x<0)x-=3;
        return upgrade.move(x);
    }

    @Override
    public Bullet shot(Bullet bullet) {
        return upgrade.shot(bullet);
    }
}
