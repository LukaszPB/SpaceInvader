package com.example.projekt_ztp;

import com.example.projekt_ztp.decorators.BasicModule;
import com.example.projekt_ztp.decorators.StrongerEngines;
import com.example.projekt_ztp.decorators.Upgrade;

public class Ship {
    private static final Ship instance = new Ship();
    private double x=(StageProperties.GAME_WINDOW_WIDTH-StageProperties.SHIP_SIZE)/2;
    private Upgrade upgrade = new BasicModule();
    private Ship() {}
    public static Ship getInstance() { return instance; }
    public void addUpgrade(Upgrade upgrade) {
        this.upgrade = new StrongerEngines(this.upgrade);
    }
    public void move(int shift) {
        if(x+shift < 10 || x+shift > StageProperties.GAME_WINDOW_WIDTH-StageProperties.SHIP_SIZE-10) {
            return;
        }
        x += upgrade.move(shift);
    }
    public Bullet shot() {
        return upgrade.shot(new Bullet(x+StageProperties.SHIP_SIZE/2,StageProperties.GAME_WINDOW_HEIGHT-StageProperties.SHIP_SIZE));
    }
    public double getX() { return x; }
}
