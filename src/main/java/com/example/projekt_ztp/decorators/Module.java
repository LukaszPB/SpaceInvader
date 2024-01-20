package com.example.projekt_ztp.decorators;

import com.example.projekt_ztp.Bullet;
import javafx.scene.paint.Color;

public abstract class Module implements Upgrade {
    protected Upgrade upgrade;
    public Module(Upgrade upgrade) {
        this.upgrade = upgrade;
    }
    protected Color graphicRep;
    @Override
    public abstract double move(double x);
    @Override
    public abstract Bullet shot(Bullet bullet);
    public Color getGraphicRep() { return graphicRep; }
    public void setUpgrade(Upgrade upgrade) {
        this.upgrade = upgrade;
        System.out.println("Dodany upgrade ->" + upgrade);
    }
}
