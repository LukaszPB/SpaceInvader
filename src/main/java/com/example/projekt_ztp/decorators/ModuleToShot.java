package com.example.projekt_ztp.decorators;

import com.example.projekt_ztp.Bullet;
import com.example.projekt_ztp.StageProperties;
import javafx.scene.shape.Rectangle;

public class ModuleToShot {
    private Module module;
    private int x,y;
    private Rectangle graphicRep;
    private boolean badziewie;
    public ModuleToShot(Module module,int x,int y) {
        this.module = module;
        this.x = x;
        this.y = y;
        graphicRep = new Rectangle(StageProperties.ENEMY_WIDTH,StageProperties.ENEMY_HEIGHT);
        graphicRep.setLayoutX(x);
        graphicRep.setLayoutY(y);
        graphicRep.setFill(module.getGraphicRep());
    }
    public boolean wasHit(Bullet bullet) {
       return graphicRep.getBoundsInParent().intersects(bullet.getGraphicRep().getBoundsInParent());
    }

    public Module getModule() {
        System.out.println("getModule ->" + module);
        return module;
    }

    public Rectangle getGraphicRep() {
        return graphicRep;
    }
}
