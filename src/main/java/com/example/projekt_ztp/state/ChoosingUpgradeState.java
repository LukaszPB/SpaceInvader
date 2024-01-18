package com.example.projekt_ztp.state;

import com.example.projekt_ztp.Bullet;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class ChoosingUpgradeState extends AppState{

    private ArrayList<Bullet> bullets = new ArrayList<>();
    public ChoosingUpgradeState(Pane pane) {
        super(pane);
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
    public void enemyMove() {

    }

    @Override
    public void bulletsMove() {

    }
}
