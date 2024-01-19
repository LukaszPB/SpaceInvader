package com.example.projekt_ztp.state;

import com.example.projekt_ztp.Bullet;
import com.example.projekt_ztp.decorators.BiggerBullet;
import com.example.projekt_ztp.decorators.FasterBullet;
import com.example.projekt_ztp.decorators.ModuleToShot;
import com.example.projekt_ztp.decorators.StrongerEngines;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Iterator;

public class ChoosingUpgradeState extends AppState{

    public boolean upgradeChosen = false;
    private ModuleToShot[] modules = {
            new ModuleToShot(new StrongerEngines(null),100,200),
            new ModuleToShot(new FasterBullet(null),200,200),
            new ModuleToShot(new BiggerBullet(null),300,200)
    };
    private ArrayList<Bullet> bullets = new ArrayList<>();
    public ChoosingUpgradeState(Pane pane) {
        super(pane);
        for(ModuleToShot module : modules) {
            pane.getChildren().add(module.getGraphicRep());
        }
    }

    @Override
    protected void setupShipMove() {
        ship.getGraphicRep().setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT -> ship.move(-10);
                case RIGHT -> ship.move(10);
                case SPACE -> {
                    bullets.add(ship.shot());
                    pane.getChildren().add(bullets.get(bullets.size() - 1).getGraphicRep());
                }
            }
        });
    }

    @Override
    public int enemyMove() {
        if(upgradeChosen) {
            deleteAll();
            return 3;
        }
        return 0;
    }

    @Override
    public void bulletsMove() {
        Iterator<Bullet> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            for(ModuleToShot module : modules) {
                if(module.wasHit(bullet)) {
                    System.out.println("DODANIE UPGREADE");
                    if(ship.upgrade != module.getModule()){
                        ship.addUpgrade(module.getModule());
                    }else {
                        System.out.println("Ship.upgrade == module.getModule() [NIE DODAJE UPGREADA]");
                    }
                    upgradeChosen = true;
                    break;
                }
            }
            if (bullet.move()) {
                pane.getChildren().remove(bullet.getGraphicRep());
                iterator.remove();
            }
        }
    }
    public void deleteAll() {
        for(ModuleToShot module : modules) {
            pane.getChildren().remove(module.getGraphicRep());
        }

        for(Bullet bullet : bullets) {
            pane.getChildren().remove(bullet.getGraphicRep());
        }

        upgradeChosen = false;
    }
}
